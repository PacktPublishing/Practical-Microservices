package com.practicalMicroservcies.contoller;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practicalMicroservcies.entity.BankAccountDetail;
import com.practicalMicroservcies.entity.ObligationDetails;
import com.practicalMicroservcies.service.FinancialServices;

@RestController
@RequestMapping("/PM/finance/")
public class FinancialController {
	private static final Logger logger = Logger.getLogger(FinancialController.class);

	@Resource
	FinancialServices financialService;

	@Resource
	ObjectMapper mapper;
	/**
	 * Method is responsible for adding new AccountDetails.
	 * 
	 * @param address
	 * @param userId
	 * @return
	 */
	public static final String addAccountDetails = "addAccountDetails(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/account", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> addAccountDetails(@RequestBody BankAccountDetail accountDetail, @PathVariable("userId") UUID userId) {
		logger.debug(addAccountDetails + " Account for user Id " + userId + " is creating.");
		accountDetail.setUserId(userId.toString());
		financialService.saveAccountDetail(accountDetail);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for creating a obligation Details.
	 * 
	 * @param userDetail
	 * @param userId
	 * @return
	 */

	public static final String addObligationDetails = "addObligationDetails(): ";

	@RequestMapping(method = RequestMethod.POST, value = "{userId}/obligation", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> addObligationDetails(@RequestBody ObligationDetails obligationDetails, @PathVariable("userId") UUID userId) {
		logger.debug(addObligationDetails + " Creating user's obligation with Id " + userId + " and details : " + obligationDetails);
		obligationDetails.setUserId(userId.toString());
		financialService.saveObligation(obligationDetails);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 *  Deleting Financial Detail of user
	 * @param userDetail
	 * @param userId
	 * @return
	 */

	public static final String deleteFinancialDetails = "deleteFinancialDetails(): ";

	@RequestMapping(method = RequestMethod.DELETE, value = "{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> deleteFinancialDetails( @PathVariable("userId") UUID userId) {
		logger.debug(deleteFinancialDetails + " deleting user with Id " + userId);
		financialService.deleteFinancialDetail(userId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Method is responsible for getting the account detail for given ID.
	 * 
	 * @param userId
	 * @return
	 */
	public static final String getAccountDetails = "getAccountDetails(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BankAccountDetail> getAccountDetails(@PathVariable("userId") UUID userId) {
		logger.debug(getAccountDetails + " getting information for userId " + userId);
		BankAccountDetail objectToReturn = financialService.getAccountDetail(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}

	/**
	 * Method is responsible getting the Obligation Detail.
	 * @param userId
	 * @return
	 */
	public static final String getObligationDetails = "getObligationDetails(): ";

	@RequestMapping(method = RequestMethod.GET, value = "{userId}/address", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ObligationDetails> getObligationDetails(@PathVariable("userId") UUID userId) {
		logger.debug(getObligationDetails + " getting Obligation Details for user Id: " + userId);
		ObligationDetails objectToReturn = financialService.getObligationDetail(userId);
		if (objectToReturn == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(objectToReturn, HttpStatus.OK);
	}
}
