package com.housee.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.housee.app.json.UserMonthlyChart;
import com.housee.app.json.UserTotalBalance;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.UserProfile;

@Transactional
public class ChartDaoImpl {

	@Autowired
	@PersistenceContext
	protected EntityManager em;
	
	protected EntityManagerFactory entityManagerFactory;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManagerFactory = entityManagerFactory;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void savePaymentTransaction(UserPPTransaction paymentTransaction){
		em.persist(paymentTransaction);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserMonthlyChart> getMonthlyChart(String startMonth,String startYear,String endMonth,String endYear){
		String queryStr = "Select new com.housee.app.json.UserMonthlyChart(MONTHNAME(u.registrationDate) as month, YEAR(u.registrationDate) as year, Count(*) as value) From UserProfile u GROUP BY YEAR(u.registrationDate), MONTH(u.registrationDate)";
		TypedQuery<UserMonthlyChart> query = em.createQuery(queryStr,UserMonthlyChart.class);
		List<UserMonthlyChart> chartList = query.getResultList();
		return chartList;
	}
	
	//------------------------yearlyChart
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserMonthlyChart> getYearlyChart(String startMonth,String startYear,String endMonth,String endYear){
		String queryStr = "Select new com.housee.app.json.UserMonthlyChart(YEAR(u.registrationDate) as year, Count(*) as value) From UserProfile u GROUP BY YEAR(u.registrationDate)";
		TypedQuery<UserMonthlyChart> query = em.createQuery(queryStr,UserMonthlyChart.class);
		List<UserMonthlyChart> chartList = query.getResultList();
		return chartList;
	}
	
	
	//--------------------------UserAccountBalenceChart---------------------------
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserMonthlyChart> getMonthlyTransactionChart(String startMonth,String startYear,String endMonth,String endYear){
		String queryStr = "Select new com.housee.app.json.UserMonthlyChart(MONTHNAME(u.timestamp) as month, YEAR(u.timestamp) as year, sum(u.rechargeAmount + u.serviceFeeAmount) as value, sum(u.serviceFeeAmount) as value1, sum(u.rechargeAmount + u.winAmount - u.redeemAmount - u.playedGameAmount - u.kylAmount) as value2, sum(u.winAmount) as value3, sum(u.redeemAmount) as value4, sum(u.playedGameAmount) as value5, sum(u.kylAmount) as value6) From UserAccountBalance u GROUP BY YEAR(u.timestamp), MONTH(u.timestamp)";
		TypedQuery<UserMonthlyChart> query = em.createQuery(queryStr,UserMonthlyChart.class);
		List<UserMonthlyChart> chartList = query.getResultList();
		System.out.println("monthly transaction chart ******** " + chartList);
		return chartList;
		
	}
	
	//------------------------yearlyChart
	//--------------10-Dec. Changes------------------------------
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserMonthlyChart> getYearlyTransactionChart(String startMonth,String startYear,String endMonth,String endYear){
		String queryStr = "Select new com.housee.app.json.UserMonthlyChart(YEAR(u.timestamp) as year, sum(u.rechargeAmount + u.serviceFeeAmount) as value, sum(u.serviceFeeAmount) as value1, sum(u.rechargeAmount + u.winAmount - u.redeemAmount - u.playedGameAmount - u.kylAmount) as value2, sum(u.winAmount) as value3, sum(u.redeemAmount) as value4, sum(u.playedGameAmount) as value5, sum(u.kylAmount) as value6) From UserAccountBalance u GROUP BY YEAR(u.timestamp)";
		TypedQuery<UserMonthlyChart> query = em.createQuery(queryStr,UserMonthlyChart.class);
		List<UserMonthlyChart> chartList = query.getResultList();
		return chartList;
	}
//-----------------********------------------
}
