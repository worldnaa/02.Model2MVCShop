package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdateProductViewAction extends Action {//��ǰ���������� ���� ȭ���û

	//1. �ǸŻ�ǰ������ ��ǰ���� Ŭ���ؼ� �� ��� (menu=manage)
	//2. UpdateProductViewAction.java�� execute() ����
	//3. updateProductView.jsp �̵�
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< UpdateProductViewAction : execute() ���� >>>>>");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("���� prodNo : " + prodNo);
		
		ProductService ProductService = new ProductServiceImpl();
		ProductVO productVO = ProductService.getProduct(prodNo);
		System.out.println("productVO ���ÿϷ� : " + productVO);
		
		request.setAttribute("productVO", productVO);	
		
		System.out.println("<<<<< UpdateProductViewAction : execute() ���� >>>>>");
		
		return "forward:/product/updateProductView.jsp";
	}
}
