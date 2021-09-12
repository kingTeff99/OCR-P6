package com.buddy.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id",
		  resolver = EntityIdResolver.class,
	      scope = Contact.class)
@Entity @Data @NoArgsConstructor @AllArgsConstructor@Builder
public class Contact implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @ManyToOne
	 @JoinColumn(name = "userRelating_id")
	 private Users userRelatingId;
	 
	 @ManyToOne
	 @JoinColumn(name = "userRelated_id")
	 private Users userRelatedId;
	 
}
