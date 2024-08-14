package com.cg.in.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Employee   {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	@Column
	private String empName;
	@Column
	private String empDesignation;
	@Column
	private double salary;
}
