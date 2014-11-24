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

import com.housee.app.json.UserTotalBalance;
import com.housee.app.model.UserAccountBalance;
import com.housee.app.model.UserPPTransaction;
import com.housee.app.model.Winner;

@Transactional
public class PaymentDaoImpl {

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
	public void mergePaymentTransaction(UserPPTransaction paymentTransaction){
		em.merge(paymentTransaction);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void finalPaymentTransaction(UserPPTransaction paymentTransaction,UserAccountBalance userAccBal){
		em.merge(paymentTransaction);
		em.merge(userAccBal);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAccountBalance(UserAccountBalance userAccBal){
		em.merge(userAccBal);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateAccountBalance(UserAccountBalance userAccBal,Winner winner){
		em.merge(userAccBal);
		em.merge(winner);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserPPTransaction getUserPPTransactionByToken(String token){
		UserPPTransaction userProfile =  em.createQuery("Select pp From UserPPTransaction pp Where pp.token=:token",UserPPTransaction.class)
		.setParameter("token", token)
		.getSingleResult();
		
		return userProfile; 
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserPPTransaction> getUserPPTransactionListByUsername(String username,int pageIndex, int pageSize){
		List<UserPPTransaction> userProfileList =  em.createQuery("Select pp From UserPPTransaction pp Where pp.username=:username Order By pp.id Desc  ",UserPPTransaction.class)
		.setParameter("username", username)
		.setFirstResult(pageIndex)
		.setMaxResults(pageSize)
		.getResultList();
		return userProfileList; 
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserPPTransaction> getUserAllPPTransactionListByUsername(String username,int pageIndex, int pageSize){
		List<UserPPTransaction> userProfileList =  em.createQuery("Select pp From UserPPTransaction pp Where pp.username=:username Order By pp.id Desc",UserPPTransaction.class)
		.setParameter("username", username)
		.setFirstResult(pageIndex)
		.setMaxResults(pageSize)
		.getResultList();
		return userProfileList; 
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccountBalance getLastUserAccBalByUsername(String username){
		List<UserAccountBalance> userAccBalList =  em.createQuery("Select uac From UserAccountBalance uac Where uac.username=:username ORDER BY uac.id DESC LIMIT 1",UserAccountBalance.class)
		.setParameter("username", username)
		.getResultList();
		
		if(userAccBalList != null && userAccBalList.size() > 0){
			return userAccBalList.get(0);
		}
		return new UserAccountBalance(); 
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserTotalBalance getUserTotalAccBalByUsername(String username){
		
		String queryStr = "Select new com.housee.app.json.UserTotalBalance(sum(uac.totalAvailableBalance) ,sum(uac.rechargeAmount) , sum(uac.redeemAmount), sum(uac.winAmount), sum(uac.serviceFeeAmount), sum(uac.playedGameAmount), sum(uac.kylAmount)) From UserAccountBalance uac WHERE uac.username=:username group by uac.username";
		TypedQuery<UserTotalBalance> query = em.createQuery(queryStr,UserTotalBalance.class).setParameter("username", username);
		List<UserTotalBalance> resultList = query.getResultList();

		if(resultList != null && resultList.size()>0){
			return resultList.get(0);
		}
		System.out.println("TotalAvailableBalance ---> " + resultList.toString());
		return new UserTotalBalance(); 
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserAccountBalance> getUserTransactionListByUsername(String username,int firstPage, int pageSize){
		List<UserAccountBalance> userAccBalList =  em.createQuery("Select uac From UserAccountBalance uac Where uac.username=:username ORDER BY uac.id DESC",UserAccountBalance.class)
		.setParameter("username", username)
		.setFirstResult(firstPage)
		.setMaxResults(pageSize)
		.getResultList();
		
		return userAccBalList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getUserAccountBalanceCount(String username){
		Query query = em.createNativeQuery("SELECT Count(*) From UserAccountBalance uac Where uac.username=:username ORDER BY uac.id DESC")
				.setParameter("username", username);
		System.out.println("User Row Count --> " + query.getSingleResult());
		return query.getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Object getUserPPTransCount(String username){
		Query query = em.createNativeQuery("SELECT Count(*) From UserPPTransaction pp Where pp.username=:username and pp.transStatus='Completed' ORDER BY pp.id DESC")
				.setParameter("username", username);
		System.out.println("User Row Count --> " + query.getSingleResult());
		return query.getSingleResult();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserAccountBalance> getUserTransactionForPlayedGameListByUsername(String username,int pageIndex, int pageSize){
		List<UserAccountBalance> userAccBalList =  em.createQuery("Select uac From UserAccountBalance uac Where uac.username=:username and uac.playedGameAmount > 0 ORDER BY uac.id DESC",UserAccountBalance.class)
		.setParameter("username", username)
		.setFirstResult(pageIndex)
		.setMaxResults(pageSize)
		.getResultList();
		
		return userAccBalList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserAccountBalance> getUserTransactionForRedeemListByUsername(String username,int pageIndex, int pageSize){
		List<UserAccountBalance> userAccBalList =  em.createQuery("Select uac From UserAccountBalance uac Where uac.username=:username and uac.redeemAmount > 0 ORDER BY uac.id DESC",UserAccountBalance.class)
		.setParameter("username", username)
		.setFirstResult(pageIndex)
		.setMaxResults(pageSize)
		.getResultList();
		
		return userAccBalList;
	}

}
