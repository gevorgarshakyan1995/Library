package com.test.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "user_Library")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private Status status;

    @Column(name = "reset_password_token", unique = true)
    private String ResetPasswordToken;

    @Column(name = "reset_password_token_req_age")
    private Long timeMillis;

    @Column
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn
    private Address address;


    @Column(name = "Number")
    private String number;

    @Column(name = "amount")
    private Integer Wallet;


    @Column(name = "penalty_days")
    private Integer penaltyDays;


    @ManyToMany
    @JoinTable(name = "user_authoriti_library",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authoriti_id"))
    private List<Authority> authoriti;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }

    public Integer getPenaltyDays() {
        return penaltyDays;
    }

    public void setPenaltyDays(Integer penaltyDays) {
        this.penaltyDays = penaltyDays;
    }

    public int getId() {
        return id;
    }

    public Integer getWallet() {
        return Wallet;
    }

    public void setWallet(Integer wallet) {
        Wallet = wallet;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResetPasswordToken() {
        return ResetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        ResetPasswordToken = resetPasswordToken;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Authority> getAuthoriti() {
        return authoriti;
    }

    public void setAuthoriti(List<Authority> authoriti) {
        this.authoriti = authoriti;
    }
}
