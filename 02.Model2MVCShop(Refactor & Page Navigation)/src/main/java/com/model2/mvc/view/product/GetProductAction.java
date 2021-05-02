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

public class GetProductAction extends Action {//��ǰ�󼼺��� ��û

	//1. �ǸŻ�ǰ���� or ��ǰ�˻� �޴� Ŭ��
	//2. �ǸŻ�ǰ������ ��ǰ�� ==> http://192.168.0.96:8080/getProduct.do?prodNo=prodNo&menu=manage �̵�
	//3. ��ǰ�˻��� ��ǰ��    ==> http://192.168.0.96:8080/getProduct.do?prodNo=prodNo&menu=search �̵�
	//4. GetProductAction.java�� execute() ����
	//5. menu=manage �� ��� ==> UpdateProductViewAction.java �̵�
	//6. menu=search �� ��� ==> getProduct.jsp �̵�
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< GetProductAction : execute() ���� >>>>>");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("���� prodNo : " + prodNo);
		
		String menu = request.getParameter("menu");
		System.out.println("���� menu : " + menu);
		
		//menu�� ���� manage�� ���
		if(menu != null && menu.equals("manage")) {
			return "forward:/updateProductView.do?prodNo="+prodNo+"&menu="+menu;
		}
		
		ProductService service = new ProductServiceImpl();
		ProductVO productVO = service.getProduct(prodNo);
		System.out.println("productVO ���ÿϷ� : " + productVO);
		
		request.setAttribute("productVO", productVO);
		
		System.out.println("<<<<< GetProductAction : execute() ���� >>>>>");
		return "forward:/product/getProduct.jsp";
		
	}
}
