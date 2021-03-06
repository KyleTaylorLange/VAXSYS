package com.vaxsys.entity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name="idNumber")
    private String idNumber;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getIdNumber() {return idNumber;}

    public void setIdNumber(String idNumber) {this.idNumber = idNumber ;}

    public Account(String firstName, String lastName, String email, String password, Role role, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = firstName + " " + lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.idNumber = idNumber;
    }

    public Account() {

    }
}
