package com.buddy.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddy.model.Contact;

@Repository
@Transactional
public interface ContactRepo extends JpaRepository<Contact, Long> {
	
	List<Contact> findByUserRelatedId(Long uerRelatedId);
	
	List<Contact> findByUserRelatingId(Long userRelatingId);
	
	Optional<Contact> findById(Long id);
	
	Contact findByUserRelatingIdAndUserRelatedId(Long userRelatingId, Long userRelatedId);
	
}
