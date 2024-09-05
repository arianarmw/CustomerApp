package com.ariana.CustomerApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Gender {

    @Id
    @Column(name = "gender_id")
    private Integer genderId;

    @Column(name = "gender_name", nullable = false)
    private String genderName;
}
