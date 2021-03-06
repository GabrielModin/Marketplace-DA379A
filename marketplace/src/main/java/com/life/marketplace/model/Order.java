package com.life.marketplace.model;


public class Order {

  private String id;
  private String user;
  private java.sql.Date date;
  private String status;
  private String product;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

}
