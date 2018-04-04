package school.journal.Factories;

import by.school.repository.IRepository;
import by.school.repository.impl.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class BaseFactory  {

    public abstract SessionFactory createMockSessionFactory(Session session);
    public abstract Session createMockSession();
    public abstract Session createMockSessionWithException();
    public abstract Session createMockSessionWithGetAll(List expectedObjects);
    public abstract Session createMockSessionWithGet(Object returnedObject);
    public abstract Session createMockSessionWithSave();
    public abstract Session createMockSessionWithUpdate();
    public abstract Session createMockSessionWithGetByName(Object deletedObject, String name);
    public abstract IRepository createMockContactDataRepository();

    public ClazzRepository createClazzRepository(){
        return new ClazzRepository();
    }

    public RoleRepository createRoleRepository(){
        return new RoleRepository();
    }

    public UserRepository createUserRepository(){
        return new UserRepository();
    }

    public MarkRepository createMarkRepository(){
        return new MarkRepository();
    }

    public PupilRepository createPupilRepository(){
        return new PupilRepository();
    }

    public SubjectInScheduleRepository createSubjectInScheduleRepository(){
        return new SubjectInScheduleRepository();
    }

    public SubjectRepository createSubjectRepository(){
        return new SubjectRepository();
    }

    public TeacherRepository createTeacherRepository(){
        return new TeacherRepository();
    }

    public TokenRepository createTokenRepository(){
        return new TokenRepository();
    }

}