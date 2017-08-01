package com.practicalMicroservcies.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "obligations_details")
public class ObligationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7123033147694699766L;

	/**
	 * unique Id for each record.
	 */

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * User Id , unique for every user
	 */

	@Column(name = "user_id", nullable = false, unique = true, length = 36)
	private String userId;

	/**
	 * Totally monthly income , may be from himself or wife (combined)
	 */
	@Column(name = "monthly_income_supports", nullable = false)
	private BigDecimal monthlyIncome;

	/**
	 * Monthly Emi to payout
	 */
	@Column(name = "monthly_emi_payout", nullable = false)
	private BigDecimal monthlyemi;
	/**
	 * totally month spending
	 */
	@Column(name = "total_monthly_spending", nullable = false)
	private BigDecimal monthlySpending;

	/**
	 * Date on which Entry is created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdOn = new Date();;

	/**
	 * Date on which Entry is deleted.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_on", columnDefinition = "TIMESTAMP DEFAULT NULL")
	private Date deletedOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public BigDecimal getMonthlyemi() {
		return monthlyemi;
	}

	public void setMonthlyemi(BigDecimal monthlyemi) {
		this.monthlyemi = monthlyemi;
	}

	public BigDecimal getMonthlySpending() {
		return monthlySpending;
	}

	public void setMonthlySpending(BigDecimal monthlySpending) {
		this.monthlySpending = monthlySpending;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	@Override
	public String toString() {
		return "ObligationDetails [userId=" + userId + ", monthlyIncome=" + monthlyIncome + ", monthlyemi=" + monthlyemi
				+ ", monthlySpending=" + monthlySpending + ", createdOn=" + createdOn + ", deletedOn=" + deletedOn
				+ "]";
	}

}
