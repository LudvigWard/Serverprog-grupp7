package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.yrgo.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{ 
} 
