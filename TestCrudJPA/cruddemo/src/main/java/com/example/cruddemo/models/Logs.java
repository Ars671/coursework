package com.example.cruddemo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.sql.Date;

@Entity
@Table(name = "audit_log")


public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")

    private String action;

    private String table_name;

    private String change_timestamp;

    public Logs() {

    }

    public Logs(String action, String table_name, String change_timestamp) {
        this.action = action;
        this.table_name = table_name;
        this.change_timestamp = change_timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getChange_timestamp() {
        return change_timestamp;
    }

    public void setChange_timestamp(String change_timestamp) {
        this.change_timestamp = change_timestamp;
    }
}

