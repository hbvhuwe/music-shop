package com.hbvhuwe.musicshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Checks")
public class Checks implements Serializable, Model {

  @Id
  @Column(name = "CheckID")
  private int checkId;

  @Column(name = "DiskID")
  private long diskId;

  @Column(name = "ClientID")
  private long clientId;

  @Column(name = "PurchaseValue")
  private double purchaseValue;

  @Column(name = "PurchaseAmount")
  private long purchaseAmount;

  @Column(name = "PurchaseDate")
  private java.sql.Date purchaseDate;

  @Column(name = "PurchaseType")
  private long purchaseType;

  public int getCheckId() {
    return checkId;
  }

  public void setCheckId(int checkId) {
    this.checkId = checkId;
  }

  public long getDiskId() {
    return diskId;
  }

  public void setDiskId(long diskId) {
    this.diskId = diskId;
  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  public double getPurchaseValue() {
    return purchaseValue;
  }

  public void setPurchaseValue(double purchaseValue) {
    this.purchaseValue = purchaseValue;
  }

  public long getPurchaseAmount() {
    return purchaseAmount;
  }

  public void setPurchaseAmount(long purchaseAmount) {
    this.purchaseAmount = purchaseAmount;
  }

  public java.sql.Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(java.sql.Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public long getPurchaseType() {
    return purchaseType;
  }

  public void setPurchaseType(long purchaseType) {
    this.purchaseType = purchaseType;
  }

  @Override
  public int getId() {
    return getCheckId();
  }

  @Override
  public void setId(int id) {
    setCheckId(id);
  }
}
