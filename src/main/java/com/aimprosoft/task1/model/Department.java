package com.aimprosoft.task1.model;

import javax.persistence.*;

@Entity
@Table(name="departments", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Department {

    private long id;
    private String name;
    private String info;

    public Department() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "department_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
