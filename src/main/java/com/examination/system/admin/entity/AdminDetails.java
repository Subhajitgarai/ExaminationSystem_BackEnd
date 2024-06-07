package com.examination.system.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDetails {
    @Id
    private String adminEmail;
    private String adminPassword;
    private String adminName;
    private String adminPhone;

}
