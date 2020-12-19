package dengtao.Controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dengtao.Model.service.gasField.GasFiledservice;
import dengtao.Model.service.gasField.GasFiledserviceImpl;

/**
 * Servlet implementation class GetData
 */
@WebServlet("/GetData.do")
public class DetDate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do GetData!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String rs = null;
		String dataName = request.getParameter("dataName");
		switch (dataName) {
		case "AllGasFields":{
			GasFiledservice gasFiledservice=new GasFiledserviceImpl();
			rs = gasFiledservice.getAllGasfiled();
			break;
		}
		
		default:{
			rs = "null";
			break;
			}
		}
		
		System.out.println(rs);
		response.getWriter().write(rs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
