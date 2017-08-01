package com.practicalMicroservcies.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicalMicroservcies.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Address findByUserId(String userId);
}
