package by.school.repository.specification.teacher;

import by.school.entity.Teacher;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class TeacherSpecificationByClassId extends TeacherSpecification {
    private int classId;

    public TeacherSpecificationByClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public Criterion toCriteria() {
       return Restrictions.eq("classId", classId);
    }

    @Override
    public boolean specified(Teacher teacher) {
        return teacher.getClassId() == classId;
    }
}
