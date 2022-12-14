package com.example.calucalteage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String name;

	private String lastname;

	@Column(name = "birth_date")
	private LocalDate birthDate;
}
