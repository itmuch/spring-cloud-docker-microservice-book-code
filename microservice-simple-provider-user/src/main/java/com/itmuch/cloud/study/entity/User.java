package com.itmuch.cloud.study.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class User {
  public User() {
  }

  public User(Long id, String username, String name, Integer age, BigDecimal balance) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.age = age;
    this.balance = balance;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String username;
  @Column
  private String name;
  @Column
  private Integer age;
  @Column
  private BigDecimal balance;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return this.age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

}
