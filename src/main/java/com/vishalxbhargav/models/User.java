package com.vishalxbhargav.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="my_name")
	private String firstName;
	private String lastName;
	private List<Integer> following=new ArrayList<>();
	private List<Integer> followers=new ArrayList<>();
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPost=new ArrayList<>();
	private String gender;
	@Column(name="gmail")
	private String email;
	private String password;


}
