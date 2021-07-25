package com.demo.codingtask.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INTEGER")
	private Long id;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_EMAIL")
	private String userEmail;
	public Users(String userName, String userEmail) {
		this.userName = userName;
		this.userEmail = userEmail;
	}
}
