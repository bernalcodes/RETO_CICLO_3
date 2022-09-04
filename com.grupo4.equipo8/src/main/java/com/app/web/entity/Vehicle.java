package com.app.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate", nullable = false)
    private String plate;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "type", nullable = false, length = 15)
    private String type;

    @Column(name = "brand", nullable = false, length = 25)
    private String brand;

    @Column(name = "model", nullable = false, length = 25)
    private String model;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "owner", nullable = false, length = 50)
    private String owner;

    public Vehicle() {
    }

    public Vehicle(String plate, Date date, String type, String brand, String model, String color,
            String owner) {
        this.plate = plate;
        this.date = date;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.owner = owner;
    }

    public Vehicle(Long id, String plate, Date date, String type, String brand, String model, String color,
            String owner) {
        this.id = id;
        this.plate = plate;
        this.date = date;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
