package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {//���Ż����ڵ� ���� ��û (���� : tranNo)

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< UpdateTranCodeAction : execute() ���� >>>>>");
		
		PurchaseVO purchaseVO = new PurchaseVO();
		
		//listPurchase.jsp���� ���ǵ��� ��ư Ŭ�� �� 'tranNo' ������
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		System.out.println("tranNo ��? " + purchaseVO.getTranNo());	
		
		//listPurchase.jsp���� ���ǵ��� ��ư Ŭ�� �� 'tranCode = 3' ������ ==> ��ۿϷ� ����
		purchaseVO.setTranCode(request.getParameter("tranCode")); 
		System.out.println("tranCode ��? " + purchaseVO.getTranCode());
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updateTranCode(purchaseVO);
		
		System.out.println("<<<<< UpdateTranCodeAction : execute() ���� >>>>>");
		
		return "redirect:/listPurchase.do?menu=manage&page="+request.getParameter("page");
	}

}
