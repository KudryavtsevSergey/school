package by.school.service.impl;

import by.school.controller.exception.ExceptionEnum;
import by.school.entity.Role;
import by.school.repository.IRepository;
import by.school.service.CRUDService;
import by.school.service.IService;
import by.school.service.exception.ServiceException;
import by.school.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.school.utils.ValidateServiceUtils.validateId;
import static by.school.utils.ValidateServiceUtils.validateString;

@Service("RoleService")
public class RoleService extends CRUDService<Role> implements IService<Role> {

    @Autowired
    public RoleService(@Qualifier("RoleRepository")IRepository<Role> repository) {
        this.repository = repository;
    }

    @Override
    public Role create(Role obj) throws ServiceException {
        try {
            validateString(obj.getName(),"Name");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.role_not_found);
        }
        validateLevel(obj.getLevel());
        return super.create(obj);
    }

    @Override
    public Role update(Role obj) throws ServiceException {
        try {
            validateId(obj.getRoleId(), "Role");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.role_not_found);
        }
        try {
            validateString(obj.getName(), "Name");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.role_has_wrong_name);
        }
        validateLevel(obj.getLevel());
        return super.update(obj);
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            validateId(id, "Role");
        } catch (ValidationException exc) {
            throw new ClassifiedServiceException(ExceptionEnum.role_not_found);
        }
        Role role = new Role();
        role.setRoleId(id);
        super.delete(role);
    }

    @Override
    public List<Role> read() throws ServiceException {
        return super.read();

    }

    public Role getOne(int id) throws ServiceException {
        return  super.getOne(id);
    }

    private void validateLevel(int level) throws ServiceException {
        if (level <= 0) {
            throw new ClassifiedServiceException(ExceptionEnum.role_has_wrong_level);
        }
    }
}