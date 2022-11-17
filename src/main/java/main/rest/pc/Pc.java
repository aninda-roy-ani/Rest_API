package main.rest.pc;

import javax.persistence.*;

@Entity
@Table(name = "pctbl")
public class Pc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    public Pc(Integer id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public Pc() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
