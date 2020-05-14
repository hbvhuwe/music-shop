package com.hbvhuwe.musicshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Admin")
public class Admin implements Serializable, Model {

  @Id
  @Column(name = "AdminID")
  private int adminId;

  @Column(name = "Login")
  private String login;

  @Column(name = "Name")
  private String name;

  @Column(name = "Surname")
  private String surname;

  @Column(name = "Password")
  private String password;

  public int getAdminId() {
    return adminId;
  }

  public void setAdminId(int adminId) {
    this.adminId = adminId;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int getId() {
    return getAdminId();
  }

  @Override
  public void setId(int id) {
    setAdminId(id);
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
