package com.practicalMicroservcies.service;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.practicalMicroservcies.entity.ObligationDetails;
import com.practicalMicroservcies.entity.BankAccountDetail;
import com.practicalMicroservcies.repo.BankAccountDetailRepository;
import com.practicalMicroservcies.repo.ObligationRepository;

@Service
@Transactional
public class FinancialServices {

	@Resource
	BankAccountDetailRepository accountDetailRepo;

	@Resource
	ObligationRepository obligationRepo;

	public void saveAccountDetail(BankAccountDetail accountDetail) {
		accountDetailRepo.save(accountDetail);
		System.out.println("AccountDetails Saved!");

	}

	public void saveObligation(ObligationDetails obligationDetails) {
		obligationRepo.save(obligationDetails);
		System.out.println("Obligation Details Saved!");

	}

	public ObligationDetails getObligationDetail(UUID userId) {
		ObligationDetails returnAddressObject = obligationRepo.findByUserId(userId.toString());
		return returnAddressObject;

	}

	public BankAccountDetail getAccountDetail(UUID userId) {
		BankAccountDetail userObjectToRetrun = accountDetailRepo.findByUserId(userId.toString());
		return userObjectToRetrun;

	}

	public void deleteFinancialDetail(UUID userId) {
		BankAccountDetail accountObject = accountDetailRepo.findByUserId(userId.toString());
		accountObject.setDeletedOn(new Date());
		accountDetailRepo.saveAndFlush(accountObject);
		ObligationDetails obligationObject = obligationRepo.findByUserId(userId.toString());
		obligationObject.setDeletedOn(new Date());
		obligationRepo.saveAndFlush(obligationObject);
		
	}

}
