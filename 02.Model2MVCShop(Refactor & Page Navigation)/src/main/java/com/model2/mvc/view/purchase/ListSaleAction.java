package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class ListSaleAction extends Action {//�ǸŸ�� ��û(Adminȭ��)

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< ListSaleAction : execute() ���� >>>>>");
		
		SearchVO searchVO = new SearchVO();
		
		int page = 1; //ó�� ���� ��� page�� 1
		
		//"page"�� value�� null�� �ƴ� ���(page�� ���� ���� ���) page�� ���� ������ �� ����
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println("if�� �� page��? " + page); 
		}
		
		//SearchVO�� page�� "page"�� �� ����(ó�� ���� ��� 1 ����)
		//SearchVO�� searchCondition�� "searchCondition"�� �� ����
		//SearchVO�� searchKeyword�� "searchKeyword"�� �� ����
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		//�����
		System.out.println("page��? "+page);
		System.out.println("searchCondition��? "+request.getParameter("searchCondition"));
		System.out.println("searchKeyword��? "+request.getParameter("searchKeyword"));
		
		//SearchVO�� pageUnit�� web.xml�� "pageSize" �� 3 ����
		searchVO.setPageUnit(Integer.parseInt(getServletContext().getInitParameter("pageSize")));
		System.out.println("pageUnit��? "+ getServletContext().getInitParameter("pageSize"));
		
		//getPurchaseList()�� ���� �������� ������ �����͸� DB���� ������ map�� ����
		PurchaseService purchaseService = new PurchaseServiceImpl();
		HashMap<String, Object> map = purchaseService.getSaleList(searchVO);
		
		//menu�� "menu"�� value(manage Ȥ�� search)�� �ҷ��� ����
		String menu = request.getParameter("menu");
		System.out.println("menu��? "+menu);//�����
		
		//������ ������ listPurchase.jsp�� �Ѱ��ֱ� ���� Request Object Scope�� �� ����
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("map", map);
		request.setAttribute("menu", menu);
		
		System.out.println("<<<<< ListSaleAction : execute() ���� >>>>>");
		
		return "forward:/purchase/listSale.jsp";
		
	}//end of execute()	
}//end of class
