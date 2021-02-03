package com.cd.dtstanley.carsgroup.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

//purpose to rate the cars; thus, need a relationship with cars
@Entity
@Table(name="ratings")
public class Rating {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@Range(min=1, max=10)
private int rating;
@Column(updatable=false)
private Date createdAt;
private Date updatedAt;

//Relationship ManyToOne with User for rating
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="user_id")
private User user;

//Relationship ManyToOne with Car for rating
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="car_id")
private Car car;

@PrePersist
protected void onCreate() {
	this.createdAt = new Date();
}

@PreUpdate
protected void onUpdate() {
	this.updatedAt = new Date();
}

//getters and setters
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Car getCar() {
	return car;
}

public void setCar(Car car) {
	this.car = car;
}



//constructor
public Rating() {
}

}//end of rating
