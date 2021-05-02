package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class ListProductAction extends Action {//��ǰ�����ȸ ��û

	//1. �ǸŻ�ǰ���� or ��ǰ�˻� �޴� Ŭ��
	//2. �ǸŻ�ǰ���� ==> http://192.168.0.96:8080/listProduct.do?menu=manage �̵�
	//3. ��ǰ�˻�    ==> http://192.168.0.96:8080/listProduct.do?menu=search �̵�
	//4. ListProductAction.java�� execute() ����
	//5. listProduct.jsp �̵�
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< ListProductAction : execute() ���� >>>>>");
		
		SearchVO searchVO = new SearchVO();
		
		int page = 1;

		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println("���� page : " + page);
		}
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

		System.out.println("searchCondition : "+request.getParameter("searchCondition"));
		System.out.println("searchKeyword : "+request.getParameter("searchKeyword"));
		
		//pageUnit�� web.xml�� "pageSize" �� 3�� �����ϰ�, SearchVO�� pageUnit�� 3 ����
		String pageUnit = getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		System.out.println("searchVO ���ÿϷ� : " + searchVO);
		
		ProductService service = new ProductServiceImpl();
		HashMap<String,Object> map = service.getProductList(searchVO);
		System.out.println("map ���ÿϷ� : " + map);
		
		String menu = request.getParameter("menu");
		System.out.println("menu ���ÿϷ� : " + menu);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("menu", menu);
		
		System.out.println("<<<<< ListProductAction : execute() ���� >>>>>");
		
		return "forward:/product/listProduct.jsp";		
	}
}
