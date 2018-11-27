package com.hbvhuwe.musicshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
public class App {
  private static final String url = "jdbc:mysql://database:3306/music_shop_db";

  public static void main(String[] args) throws InterruptedException {
    boolean connectionAvailable = false;
    Properties connectionProps = new Properties();
    connectionProps.setProperty("user", "music_shop");
    String password = "7Ts2V4NzxiHAr7FVgm7qYQFL9k0=";
    connectionProps.setProperty("password", password);
    while (!connectionAvailable) {
      try {
        DriverManager.getConnection(url, connectionProps);
        connectionAvailable = true;
      } catch (SQLException e) {
        System.err.println("Waiting for database to up");
        Thread.sleep(2000);
      }
    }
    SpringApplication.run(App.class, args);
  }
}
