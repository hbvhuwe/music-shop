package com.hbvhuwe.musicshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Client")
public class Client implements Serializable, Model {

  @Id
  @Column(name = "ClientID")
  private int clientId;

  @Column(name = "Name")
  private String name;

  @Column(name = "Surname")
  private String surname;

  @Column(name = "Password")
  private String password;

  @Column(name = "Discount")
  private double discount;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  @Override
  public int getId() {
    return getClientId();
  }

  @Override
  public void setId(int id) {
    setClientId(id);
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
