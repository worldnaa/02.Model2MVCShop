package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {//���� ��û

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< AddPurchaseAction : execute() ���� >>>>>");
		
		//addPurchaseView.jsp���� ������ ���� DB�� ���� �����ϱ� ���� PurchaseVO �ν��Ͻ��� �����Ͽ�, ������ ���� ����
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setPaymentOption(request.getParameter("paymentOption")); //���Ź��
		purchaseVO.setReceiverName(request.getParameter("receiverName"));   //�������̸�
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone")); //�����ڿ���ó
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));       //�������ּ�
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest")); //���ſ�û����
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));       //����������
		purchaseVO.setTranCode("1");
		
		//2. ProductVO�� ���� PurchaseVO�� ����
		//���1)
		ProductService productService = new ProductServiceImpl();
		ProductVO productVO = productService.getProduct((Integer.parseInt(request.getParameter("prodNo"))));
		purchaseVO.setPurchaseProd(productVO);                              //��ǰ����
		//���2)
//		ProductVO productVO = new ProductVO();
//		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo"))); 
//		purchaseVO.setPurchaseProd(productVO);
		
		//3. UserVO�� ���� PurchaseVO�� ����
		//���1)
		HttpSession session = request.getSession();
		purchaseVO.setBuyer((UserVO)session.getAttribute("user"));		    //�����ھ��̵�
		//���2)
//		UserVO userVO = new UserVO();
//		userVO.setUserId(request.getParameter("userId"));
//		purchaseVO.setBuyer(userVO);	
		
		System.out.println("purchaseVO ���ÿϷ� : " + purchaseVO);
		
		//purchaseVO�� DB�� �����ϱ� ���� PurchaseServiceImpl �ν��Ͻ� ����
		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.addPurchase(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		System.out.println("<<<<< AddPurchaseAction : execute() ���� >>>>>");
		return "forward:/purchase/addPurchase.jsp";		
	}
}
