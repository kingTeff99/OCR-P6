package com.buddy.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Contact {
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name = "userRelating_id")
	 private Users userRelatingId;
	 
	 @ManyToOne(fetch=FetchType.EAGER)
	 @JoinColumn(name = "userRelated_id")
	 private Users userRelatedId;
	 
//	 private Long userRelatingId;
//	 
//	 private Long userRelatedId;

}
