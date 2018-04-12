package by.school.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Pupil {
    private int pupilId;
    private Integer classId;
    private String firstName;
    private String pathronymic;
    private String lastName;
    private String phoneNumber;
    private String characteristic;
    private Collection<Mark> marksByPupilId;
    private User userByPupilId;
    private Clazz clazzByClassId;

    @Id
    @Column(name = "pupil_id", nullable = false)
    public int getPupilId() {
        return pupilId;
    }

    public void setPupilId(int pupilId) {
        this.pupilId = pupilId;
    }

    @Basic
    @Column(name = "class_id", nullable = true)
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "pathronymic", nullable = false, length = 100)
    public String getPathronymic() {
        return pathronymic;
    }

    public void setPathronymic(String pathronymic) {
        this.pathronymic = pathronymic;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 45)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "characteristic", nullable = true, length = -1)
    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pupil pupil = (Pupil) o;

        if (pupilId != pupil.pupilId) return false;
        if (classId != null ? !classId.equals(pupil.classId) : pupil.classId != null) return false;
        if (firstName != null ? !firstName.equals(pupil.firstName) : pupil.firstName != null) return false;
        if (pathronymic != null ? !pathronymic.equals(pupil.pathronymic) : pupil.pathronymic != null) return false;
        if (lastName != null ? !lastName.equals(pupil.lastName) : pupil.lastName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(pupil.phoneNumber) : pupil.phoneNumber != null) return false;
        if (characteristic != null ? !characteristic.equals(pupil.characteristic) : pupil.characteristic != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pupilId;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (pathronymic != null ? pathronymic.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (characteristic != null ? characteristic.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pupilByPupilId", cascade = CascadeType.ALL)
    public Collection<Mark> getMarksByPupilId() {
        return marksByPupilId;
    }

    public void setMarksByPupilId(Collection<Mark> marksByPupilId) {
        this.marksByPupilId = marksByPupilId;
    }

    @OneToOne
    @JoinColumn(name = "pupil_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByPupilId() {
        return userByPupilId;
    }

    public void setUserByPupilId(User userByPupilId) {
        this.userByPupilId = userByPupilId;
    }

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    public Clazz getClazzByClassId() {
        return clazzByClassId;
    }

    public void setClazzByClassId(Clazz clazzByClassId) {
        this.clazzByClassId = clazzByClassId;
    }
}
