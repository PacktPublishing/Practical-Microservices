package com.practicalMicroservcies.service;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.practicalMicroservcies.entity.Address;
import com.practicalMicroservcies.entity.UserDetail;
import com.practicalMicroservcies.repo.AddressRepository;
import com.practicalMicroservcies.repo.UserDetailRepository;

@Service
@Transactional
public class UserDetailServices {

	@Resource
	AddressRepository addressRepo;

	@Resource
	UserDetailRepository userRepo;

	public void saveAddress(Address address) {
		addressRepo.save(address);
		System.out.println("User Saved!");

	}

	public void saveUser(UserDetail userDetail) {
		userRepo.save(userDetail);
		System.out.println("User Saved!");

	}

	public Address getAddress(UUID userId) {
		Address returnAddressObject = addressRepo.findByUserId(userId.toString());
		return returnAddressObject;

	}

	public UserDetail getUser(UUID userId) {
		UserDetail userObjectToRetrun = userRepo.findByUserId(userId.toString());
		System.out.println("User Saved!");
		return userObjectToRetrun;

	}

	public void deleteUser(UUID userId) {
		Address addressObject = addressRepo.findByUserId(userId.toString());
		addressObject.setDeletedOn(new Date());
		addressRepo.saveAndFlush(addressObject);
		UserDetail userObject = userRepo.findByUserId(userId.toString());
		userObject.setDeletedOn(new Date());
		userRepo.saveAndFlush(userObject);
		System.out.println("User Deleted!");

	}

}
