package school.journal;

import by.school.entity.Role;
import by.school.repository.IRepository;
import by.school.repository.exception.RepositoryException;
import by.school.repository.impl.RoleRepository;
import by.school.repository.specification.HibernateSpecification;
import by.school.service.exception.ServiceException;
import by.school.service.impl.RoleService;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class RoleServiceTest {

    RoleService service;
    RoleRepository repository;


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void initRepositoryAndService(){
        repository = mock(RoleRepository.class);
        service = new RoleService(repository);
    }

    @After
    public void destroyElements(){
        repository = null;
        service = null;
    }

    void initRepositoryWithException(IRepository repository) throws RepositoryException {
        doThrow(RepositoryException.class).when(repository).query(any(HibernateSpecification.class),any(Session.class));
        doThrow(RepositoryException.class).when(repository).create(any(Role.class),any(Session.class));
        doThrow(RepositoryException.class).when(repository).delete(any(Role.class),any(Session.class));
        doThrow(RepositoryException.class).when(repository).update(any(Role.class),any(Session.class));
        doThrow(RepositoryException.class).when(repository).get(anyInt(),any(Session.class));
    }

    @Test
    public void getAll_default_returnObjects()throws  Exception{
        ArrayList<Role> expectedObjects = new ArrayList<>();
        expectedObjects.add(new Role());
        expectedObjects.add(new Role());

        doReturn(expectedObjects).when(repository).query(any(HibernateSpecification.class),any(Session.class));

        assertEquals(service.read(),expectedObjects);
    }

    @Test
    public void getAll_errorInGettingObjects_returnServiceException()throws  Exception{
        initRepositoryWithException(repository);

        exception.expect(ServiceException.class);
        service.read();
    }

    @Test
    public void create_validRole_returnRole()throws  Exception{
        Role role = new Role();
        role.setName("role");
        role.setLevel(3);

        when(repository.create(any(Role.class),any(Session.class))).thenReturn(role);
        Role returnObject = service.create(role);
        assertEquals(returnObject,role);
        verify(repository).create(any(Role.class),any(Session.class));
    }

    @Test
    public void create_error_returnServiceException()throws  Exception{
        Role role = new Role();
        role.setName("role");
        role.setLevel(3);

        initRepositoryWithException(repository);

        exception.expect(ServiceException.class);
        service.create(role);
    }
    @Test(expected = ServiceException.class)
    public void create_nullName_returnServiceExceprion()throws  Exception{
        Role role = new Role();

        when(repository.create(any(Role.class),any(Session.class))).thenReturn(role);

        service.create(role);
    }

    @Test(expected = ServiceException.class)
    public void create_emptyName_returnServiceExceprion()throws  Exception{
        Role role = new Role();
        role.setName("");

        when(repository.create(any(Role.class),any(Session.class))).thenReturn(role);

        service.create(role);
    }

    @Test
    public void create_wrondLevel_returnServiceExceprion()throws  Exception{
        Role role = new Role();
        role.setName("role");
        role.setLevel(-1);

        when(repository.create(any(Role.class),any(Session.class))).thenReturn(role);
        exception.expect(ServiceException.class);
        service.create(role);
    }

    @Test
    public void update_validRole_returnClazz()throws  Exception{
        Role role = new Role();
        role.setRoleId(1);
        role.setName("role");
        role.setLevel(3);

        when(repository.update(any(Role.class),any(Session.class))).thenReturn(role);
        Role returnObject = service.update(role);
        assertEquals(returnObject,role);
        verify(repository).update(any(Role.class),any(Session.class));
    }


    @Test(expected = ServiceException.class)
    public void update_wrongId_returnServiceExceprion()throws  Exception{
        Role role = new Role();
        role.setRoleId(-1);

        when(repository.update(any(Role.class),any(Session.class))).thenReturn(role);

        service.update(role);
    }

    @Test(expected = ServiceException.class)
    public void update_nullName_returnServiceExceprion()throws  Exception{
        Role role = new Role();
        role.setRoleId(1);

        when(repository.update(any(Role.class),any(Session.class))).thenReturn(role);

        service.update(role);
    }

    @Test(expected = ServiceException.class)
    public void update_emptyName_returnServiceExceprion()throws  Exception{
        Role role = new Role();
        role.setRoleId(1);
        role.setName("");

        when(repository.update(any(Role.class),any(Session.class))).thenReturn(role);

        service.update(role);
    }

    @Test(expected = ServiceException.class)
    public void update_wrondLevel_returnServiceExceprion()throws  Exception{
        Role role = new Role();
        role.setRoleId(1);
        role.setName("role");
        role.setLevel(-1);

        when(repository.update(any(Role.class),any(Session.class))).thenReturn(role);

        service.update(role);
    }

    @Test
    public void delete_validId_deleteObject()throws  Exception{
        service.delete(1);
        verify(repository).delete(any(Role.class),any(Session.class));
    }

    @Test
    public void delete_wrongId_throwsServiceException()throws  Exception{
        exception.expect(ServiceException.class);
        service.delete(-1);
    }

    @Test
    public void getOne_validId_getObject()throws  Exception{
        service.getOne(1);
        when(repository.get(anyInt(),any(Session.class))).thenReturn(new Role());
        verify(repository).get(anyInt(),any(Session.class));
    }

}
