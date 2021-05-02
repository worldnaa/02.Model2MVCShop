package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService {
	//Field
	private PurchaseDAO purchaseDAO;
	
	//Constructor
	public PurchaseServiceImpl() {
		purchaseDAO = new PurchaseDAO();
	}

	//Method
	//���Ÿ� ���� ����Ͻ��� ����
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		System.out.println("<<<<< PurchaseServiceImpl : addPurchase() ���� >>>>>");
		purchaseDAO.insertPurchase(purchaseVO);
	}

	//���� ���� ����ȸ�� ���� ����Ͻ��� ����
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		System.out.println("<<<<< PurchaseServiceImpl : getPurchase() ���� >>>>>");
		return purchaseDAO.findPurchase(tranNo);
	}
	
	//���� ��� ���θ� �ľ��ϱ� ���� ����Ͻ��� ����
	public PurchaseVO getPurchase2(int ProdNo) throws Exception {
		System.out.println("<<<<< PurchaseServiceImpl : getPurchase2() ���� >>>>>");
		return purchaseDAO.findPurchase2(ProdNo);
	}

	//���� ��� ���⸦ ���� ����Ͻ��� ����
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		System.out.println("<<<<< PurchaseServiceImpl : getPurchaseList() ���� >>>>>");
		return purchaseDAO.getPurchaseList(searchVO, buyerId);
	}
	
	//�Ǹ� ��� ���⸦ ���� ����Ͻ��� ����
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		System.out.println("<<<<< PurchaseServiceImpl : getSaleList() ���� >>>>>");
		return purchaseDAO.getSaleList(searchVO);
	}
	
	//���� ���� ������ ���� ����Ͻ� ����
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		System.out.println("<<<<< PurchaseServiceImpl : updatePurcahse() ���� >>>>>");
		purchaseDAO.updatePurchase(purchaseVO);
	}

	//���� ���� �ڵ� ������ ���� ����Ͻ� ����
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		System.out.println("<<<<< PurchaseServiceImpl : updateTranCode() ���� >>>>>");
		purchaseDAO.updateTranCode(purchaseVO);
	}
	
}//end of class
