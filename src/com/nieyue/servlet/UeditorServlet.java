package com.nieyue.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.ueditor.ActionEnter;
@WebServlet(urlPatterns="/ueditor/config")
public class UeditorServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	   
		
		String rootPath = request.getServletContext().getRealPath("/");
		String uploaderPath=request.getParameter("uploaderPath");
		System.out.println("rootPath="+rootPath);
		System.out.println("uploaderPath="+request.getParameter("uploaderPath"));
		//String rootPath = "c:/";
		//String action = request.getParameter("action");
		String result = new ActionEnter( request,uploaderPath, rootPath ).exec();
		 System.out.println("result="+result);
		 PrintWriter writer = response.getWriter();
		 /*  if( action!=null && 
		   (action.equals("listfile") || action.equals("listimage") ) ){
		    rootPath = rootPath.replace("\\", "/");
		    result = result.replaceAll(rootPath, "");
		}   */
		 writer.write( result );
		 writer.flush();
         writer.close();
	}
	
}
