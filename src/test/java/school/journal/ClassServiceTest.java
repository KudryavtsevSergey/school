package school.journal;

import by.school.entity.Clazz;
import by.school.repository.IRepository;
import by.school.repository.exception.RepositoryException;
import by.school.repository.impl.ClazzRepository;
import by.school.repository.specification.HibernateSpecification;
import by.school.service.exception.ServiceException;
import by.school.service.impl.ClassService;
import org.hibernate.Session;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ClassServiceTest {

    ClassService service;
    ClazzRepository repository;

    @Before
    public void initRepositoryAndService(){
        repository = mock(ClazzRepository.class);
        service = new ClassService(repository);
    }

    @After
    public void destroyElements(){
        repository = null;
        service = null;
    }

    void initRepositoryWithException(IRepository repository) throws RepositoryException {
        doThrow(RepositoryException.class).when(repository).query(any(HibernateSpecification.class),any(Session.class));
        doThrow(RepositoryException.class).when(repository).create(any(Clazz.class),any(Session.class));
        doThrow(RepositoryException.class).when(repository).delete((any(Clazz.class)),any(Session.class));
        doThrow(RepositoryException.class).when(repository).update(any(Clazz.class),any(Session.class));
        doThrow(RepositoryException.class).when(repository).get(anyInt(),any(Session.class));
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getAll_default_returnObjects()throws  Exception{
        ArrayList<Clazz> expectedObjects = new ArrayList<>();
        expectedObjects.add(new Clazz());
        expectedObjects.add(new Clazz());

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
    public void create_validClazz_returnClazz()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setLetterMark("A");
        clazz.setNumber(2);

        when(repository.create(any(Clazz.class),any(Session.class))).thenReturn(clazz);
        Clazz returnObject = service.create(clazz);
        assertEquals(returnObject,clazz);
        verify(repository).create(any(Clazz.class),any(Session.class));
    }

    @Test(expected = ServiceException.class)
    public void create_nullLetterMark_returnServiceExceprion()throws  Exception{
        Clazz clazz = new Clazz();

        when(repository.create(any(Clazz.class),any(Session.class))).thenReturn(clazz);
        service.create(clazz);
    }

    @Test(expected =ServiceException.class)
    public void create_emptyLetterMark_returnServiceExceprion()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setLetterMark("");

        when(repository.create(any(Clazz.class),any(Session.class))).thenReturn(clazz);

        service.create(clazz);
    }

    @Test(expected =ServiceException.class)
    public void create_wrongClassNumber_returnServiceExceprion()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setLetterMark("A");
        clazz.setNumber(0);

        when(repository.create(any(Clazz.class),any(Session.class))).thenReturn(clazz);

        service.create(clazz);
    }

    @Test
    public void create_error_returnServiceException()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setLetterMark("A");
        clazz.setNumber(2);
        initRepositoryWithException(repository);

        exception.expect(ServiceException.class);
        service.create(clazz);
    }

    @Test
    public void update_validClazz_returnClazz()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setClassId(1);
        clazz.setLetterMark("A");
        clazz.setNumber(2);

        when(repository.update(any(Clazz.class),any(Session.class))).thenReturn(clazz);
        when(repository.get(anyInt(),any(Session.class))).thenReturn(clazz);

        Clazz returnObject = service.update(clazz);
        assertEquals(returnObject,clazz);
        verify(repository).update(any(Clazz.class),any(Session.class));
    }

    @Test(expected =ServiceException.class)
    public void update_noSuchElementInDataBase_returnServiceException()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setClassId(1);
        clazz.setLetterMark("A");
        clazz.setNumber(2);

        when(repository.update(any(Clazz.class),any(Session.class))).thenReturn(clazz);
        when(repository.get(anyInt(),any(Session.class))).thenReturn(null);

        service.update(clazz);
    }

    @Test
    public void update_WrongNumber_doNothing()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setClassId(1);
        clazz.setLetterMark("A");
        clazz.setNumber(0);
        Clazz dbClazz = new Clazz();
        dbClazz.setClassId(1);
        dbClazz.setLetterMark("A");
        dbClazz.setNumber(1);

        when(repository.update(any(Clazz.class),any(Session.class))).thenReturn(clazz);
        when(repository.get(anyInt(),any(Session.class))).thenReturn(dbClazz);

        service.update(clazz);
        verify(repository).update(any(Clazz.class),any(Session.class));
    }

    @Test
    public void update_emptyLetterMark_doNothing()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setClassId(1);
        clazz.setLetterMark("");
        clazz.setNumber(0);

        when(repository.update(any(Clazz.class),any(Session.class))).thenReturn(clazz);
        when(repository.get(anyInt(),any(Session.class))).thenReturn(clazz);

        service.update(clazz);
        verify(repository).update(any(Clazz.class),any(Session.class));
    }

    @Test
    public void update_wrongClassNumber_doNothing()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setClassId(1);
        clazz.setLetterMark("A");
        clazz.setNumber(0);

        when(repository.update(any(Clazz.class),any(Session.class))).thenReturn(clazz);
        when(repository.get(anyInt(),any(Session.class))).thenReturn(clazz);

        service.update(clazz);
        verify(repository).update(any(Clazz.class),any(Session.class));
    }

    @Test
    public void update_error_returnServiceException()throws  Exception{
        Clazz clazz = new Clazz();
        clazz.setClassId(1);
        clazz.setLetterMark("A");
        clazz.setNumber(0);
        initRepositoryWithException(repository);

        exception.expect(ServiceException.class);
        service.update(clazz);
    }

    @Test
    public void delete_validId_deleteObject()throws  Exception{
        service.delete(1);
        verify(repository).delete(any(Clazz.class),any(Session.class));
    }

    @Test
    public void delete_wrongId_throwsServiceException()throws  Exception{
        exception.expect(ServiceException.class);
        service.delete(-1);
    }

    @Test
    public void getOne_validId_getObject()throws  Exception{
        service.getOne(1);
        when(repository.get(anyInt(),any(Session.class))).thenReturn(new Clazz());
        verify(repository).get(anyInt(),any(Session.class));
    }

    @Test
    public void getOne_wrongId_throwsServiceException()throws  Exception{
        exception.expect(ServiceException.class);
        service.getOne(-1);
    }
}
