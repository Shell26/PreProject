package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "age")
    private Long age;

    public User(){

    }

    public User(String name, String secondName, Long age){
        this.name = name;
        this.secondName = secondName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSecondName(), that.getSecondName()) &&
                Objects.equals(getAge(), that.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSecondName(), getAge());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public Long getAge() {
        return age;
    }
}
