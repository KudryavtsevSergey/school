package by.school.entity;

import javax.persistence.*;

@Entity
public class Token {
    private int masterId;
    private String value;
    private byte active;
    private User userByMasterId;

    @Id
    @Column(name = "master_id", nullable = false)
    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    @Basic
    @Column(name = "value", nullable = true, length = -1)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (masterId != token.masterId) return false;
        if (active != token.active) return false;
        if (value != null ? !value.equals(token.value) : token.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = masterId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (int) active;
        return result;
    }

    @OneToOne
    @JoinColumn(name = "master_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByMasterId() {
        return userByMasterId;
    }

    public void setUserByMasterId(User userByMasterId) {
        this.userByMasterId = userByMasterId;
    }
}
