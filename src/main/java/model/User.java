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

    @Column(name = "age")
    private Long age;

    @Column(name = "name")
    private String name;

    @Column(name = "secondName")
    private String secondName;

    public User(){

    }

    public User(Long age, String name, String secondName){
        this.age = age;
        this.name = name;
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(getAge(), that.getAge()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSecondName(), that.getSecondName());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getName(), getSecondName());
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
