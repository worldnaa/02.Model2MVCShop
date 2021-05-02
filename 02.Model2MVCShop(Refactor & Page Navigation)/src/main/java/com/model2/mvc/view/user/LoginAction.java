package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class LoginAction extends Action{ //�α��� ��û

	@Override
	public String execute(	HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("<<<<< LoginAction : execute() ���� >>>>>");
		
		//1. user������ �������� ���� UserVO �ν��Ͻ� ����
		UserVO userVO = new UserVO();
		
		//2. userVO�� "userId","password"�� value ���� ����
		userVO.setUserId(request.getParameter("userId"));
		userVO.setPassword(request.getParameter("password"));
		
		//3. loginUser() ������ ���� UserServiceImpl �ν��Ͻ� ����
		UserService service = new UserServiceImpl();
		
		//4. ���ڷ� userId,password�� �Ѱ��ָ� �޼��� ���� ==> ������� user ������ dbVO�� ����
		UserVO dbVO = service.loginUser(userVO);
		
		//5. ������ ����� "user"��� name����, dbVO�� value�� ����
		HttpSession session = request.getSession();
		session.setAttribute("user", dbVO);
		
		System.out.println("<<<<< LoginAction : execute() ���� >>>>>");
		
		return "redirect:/index.jsp";
	}
}