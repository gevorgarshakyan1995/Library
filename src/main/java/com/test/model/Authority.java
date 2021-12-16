package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    public Authority() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}