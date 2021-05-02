package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdateProductAction extends Action {//��ǰ�������� ��û

	//1. �ǸŻ�ǰ������ ��ǰ���� Ŭ���ؼ� �� ��� (menu=manage)
	//2. UpdateProductViewAction.java�� execute() ����
	//3. updateProductView.jsp �̵�
	//4. [����] ��ư Ŭ�� �� ==> UpdateProductAction.java �̵�
	//5. GetProductAction.java �̵�
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< UpdateProductAction : execute() ���� >>>>>");
				
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("���� prodNo : " + prodNo);
		
		String menu = request.getParameter("menu");
		System.out.println("���� menu : " + menu);
		
		ProductVO productVO = new ProductVO();
		productVO.setProdNo(prodNo);
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setManuDate(request.getParameter("manuDate"));
		productVO.setPrice(Integer.parseInt(request.getParameter("price")));
		productVO.setFileName(request.getParameter("fileName"));
		System.out.println("productVO ���ÿϷ� : " + productVO);
		
		ProductService ProductService = new ProductServiceImpl();
		ProductService.updateProduct(productVO);
		
		System.out.println("<<<<< UpdateProductAction : execute() ���� >>>>>");
		
		return "redirect:/getProduct.do?prodNo="+prodNo+"&menu="+menu;
	}
}
