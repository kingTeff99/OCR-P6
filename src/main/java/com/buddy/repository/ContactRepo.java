package com.buddy.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddy.model.Contact;
import com.buddy.model.Users;

@Repository
@Transactional
public interface ContactRepo extends JpaRepository<Contact, Long> {
	
	List<Contact> findByUserRelatedId(Users userRelatedId);
	
	List<Contact> findByUserRelatingId(Users userRelatingId);
	
	Optional<Contact> findById(Long id);
	
	Contact findByUserRelatingIdAndUserRelatedId(Users userRelatingId, Users userRelatedId);
	
	
}
