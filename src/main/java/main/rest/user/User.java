package main.rest.user;

import javax.persistence.*;

@Entity
@Table(name = "userdb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String computerID;

    public User(Integer id, String name, String computerID) {
        this.id = id;
        this.name = name;
        this.computerID = computerID;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComputerID() {
        return computerID;
    }

    public void setComputerID(String computerID) {
        this.computerID = computerID;
    }
}
