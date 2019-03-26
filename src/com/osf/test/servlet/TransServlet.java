package com.osf.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osf.test.service.CommonCodeService;
import com.osf.test.service.TransService;
import com.osf.test.service.impl.CommonCodeServiceImpl;
import com.osf.test.service.impl.TransServiceImpl;


public class TransServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommonCodeService ccs = new CommonCodeServiceImpl();
    private TransService ts = new TransServiceImpl();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/transfer/trans.jsp");
		request.setAttribute("ccList", ccs.selectCommonCodeList("trans"));
		rd.forward(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String source = request.getParameter("source"); //원래는 정말 제대로 된 소스, 타겟을 넣었는지 둘이 겹치지는 않는지 확인하는 것을 넣어줘야한다.
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		request.setAttribute("rMap", ts.transperText(source, target, text));
		doGet(request,response);
	}

}
