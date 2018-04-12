package by.school.entity;

import javax.persistence.*;

@Entity
public class User {
    private int userId;
    private int roleId;
    private String username;
    private String passHash;
    private byte locked;
    private String email;
    private Pupil pupilByUserId;
    private Teacher teacherByUserId;
    private Token tokenByUserId;
    private Role roleByRoleId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "pass_hash", nullable = false, length = 40)
    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @Basic
    @Column(name = "locked", nullable = false)
    public byte getLocked() {
        return locked;
    }

    public void setLocked(byte locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (roleId != user.roleId) return false;
        if (locked != user.locked) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (passHash != null ? !passHash.equals(user.passHash) : user.passHash != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + roleId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (passHash != null ? passHash.hashCode() : 0);
        result = 31 * result + (int) locked;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "userByPupilId", cascade = CascadeType.ALL)
    public Pupil getPupilByUserId() {
        return pupilByUserId;
    }

    public void setPupilByUserId(Pupil pupilByUserId) {
        this.pupilByUserId = pupilByUserId;
    }

    @OneToOne(mappedBy = "userByTeacherId")
    public Teacher getTeacherByUserId() {
        return teacherByUserId;
    }

    public void setTeacherByUserId(Teacher teacherByUserId) {
        this.teacherByUserId = teacherByUserId;
    }

    @OneToOne(mappedBy = "userByMasterId")
    public Token getTokenByUserId() {
        return tokenByUserId;
    }

    public void setTokenByUserId(Token tokenByUserId) {
        this.tokenByUserId = tokenByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
