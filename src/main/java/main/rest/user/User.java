package main.rest.user;

import main.rest.computer.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class User {

    private Integer userId;
    private String name;
    private List<Computer> computers = new ArrayList<>();

    /*
    (
            Arrays.asList(
                    new Computer("a", "Fujitsu"),
                    new Computer( "b" , "Dell")
            )
    );
     */

    public User() {
    }

    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public User(Integer userId, String name, List<Computer> computers) {
        this.userId = userId;
        this.name = name;
        this.computers = computers;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", computers=" + computers +
                '}';
    }

    public void setUser(User user){
        this.setUserId(user.getUserId());
        this.setName(user.getName());
        this.setComputers(user.getComputers());
    }
}
