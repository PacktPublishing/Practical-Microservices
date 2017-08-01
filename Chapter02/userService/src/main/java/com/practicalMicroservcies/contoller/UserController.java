package com.practicalMicroservcies.contoller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicalMicroservcies.entity.Address;
import com.practicalMicroservcies.entity.UserDetail;
import com.practicalMicroservcies.service.UserDetailServices;

@RestController
@RequestMapping("/PM/user/")
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Resource
	UserDetailServices userService;

	@Resource
	ObjectMapper mapper;
	/**
	 * Method is responsible for adding nerw address.
	 * 
	 * @param address
	 * @param userId
	 * @return
	 */
	public static final String createUserAddress = "createUserAddress(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/address", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> createUserAddress(@RequestBody Address address, @PathVariable("userId") UUID userId) {
		logger.debug(createUserAddress + " Address for user Id " + userId + " is updated as " + address);
		address.setUserId(userId.toString());
		userService.saveAddress(address);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for creating a user.
	 * 
	 * @param userDetail
	 * @param userId
	 * @return
	 */

	public static final String createUser = "createUser(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> createUser(@RequestBody UserDetail userDetail, @PathVariable("userId") UUID userId) {
		logger.debug(createUser + " creating user with Id " + userId + " and details : " + userDetail);
		userDetail.setUserId(userId.toString());
		userService.saveUser(userDetail);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param userDetail
	 * @param userId
	 * @return
	 */

	public static final String deleteUser = "deleteUser(): ";

	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId) {
		logger.debug(deleteUser + " deleting user with Id " + userId);
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for getting the user detail from ID.
	 * 
	 * @param userId
	 * @return
	 */
	public static final String getUser = "getUser(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<UserDetail> getUser(@PathVariable("userId") UUID userId) {
		logger.debug(getUser + " getting information for userId " + userId);
		UserDetail objectToReturn = userService.getUser(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}

	/**
	 * Method is responsible getting the address
	 * 
	 * @param userId
	 * @return
	 */
	public static final String getAddress = "getAddress(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}/address", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Address> getAddress(@PathVariable("userId") UUID userId) {
		logger.debug(getAddress + " getting address for user Id: " + userId);
		Address objectToReturn = userService.getAddress(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}
}
