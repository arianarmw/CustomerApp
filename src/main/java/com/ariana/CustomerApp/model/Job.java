package com.ariana.CustomerApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Job {

    @Id
    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "job_name", nullable = false)
    private String jobName;
}