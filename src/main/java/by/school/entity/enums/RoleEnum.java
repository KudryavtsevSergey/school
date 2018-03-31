package by.school.entity.enums;

import java.io.Serializable;

public enum RoleEnum implements Serializable {
    USER(2),
    PUPIL(4),
    TEACHER(6),
    CLASS_TEACHER(7),
    DIRECTOR_OF_STUDIES(8),
    DIRECTOR(10),
    ADMIN(12);

    private int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
