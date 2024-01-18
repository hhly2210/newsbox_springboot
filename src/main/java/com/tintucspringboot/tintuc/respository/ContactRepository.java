package com.tintucspringboot.tintuc.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tintucspringboot.tintuc.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
