package com.test.model;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private String authot;

    @Column
    private int haort;

    @Column
    private  int value;

    @Column(name = "value_rent")
    private  int valueRent;

    @Column(name="who_is")  //Who is it with
    private String whoIs;

    @Column(name = "tream_rent")
    private String tremRent;

    @Column
    private String waiting;

    public String getWaiting() {
        return waiting;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
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

    public String getAuthot() {
        return authot;
    }

    public void setAuthot(String authot) {
        this.authot = authot;
    }

    public int getHaort() {
        return haort;
    }

    public void setHaort(int haort) {
        this.haort = haort;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValueRent() {
        return valueRent;
    }

    public void setValueRent(int valueRent) {
        this.valueRent = valueRent;
    }

    public String getWhoIs() {
        return whoIs;
    }

    public void setWhoIs(String whoIs) {
        this.whoIs = whoIs;
    }

    public String getTremRent() {
        return tremRent;
    }

    public void setTremRent(String tremRent) {
        this.tremRent = tremRent;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authot='" + authot + '\'' +
                ", haort=" + haort +
                ", value=" + value +
                ", valueRent=" + valueRent +
                ", whoIs='" + whoIs + '\'' +
                ", tremRent='" + tremRent + '\'' +
                '}';
    }
}
