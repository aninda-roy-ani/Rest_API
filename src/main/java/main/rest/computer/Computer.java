package main.rest.computer;

import main.rest.user.User;

public class Computer {

    private String id;
    private String model;


    public Computer(String id, String model) {
        this.id = id;
        this.model = model;
    }

    public Computer() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
