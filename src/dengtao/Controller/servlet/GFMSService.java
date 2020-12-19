package dengtao.Controller.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dengtao.Model.service.gasField.GasFiledservice;
import dengtao.Model.service.gasField.GasFiledserviceImpl;

/**
 * Servlet implementation class GFMSService
 */
@SuppressWarnings("serial")
@WebServlet("/GFMSService.do")
public class GFMSService extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do GFMSService.do!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String rs = "{}";
		GasFiledservice gasFiledservice=new GasFiledserviceImpl();
		String operation = request.getParameter("operation");
		System.out.println(operation);
		Map<String, Object> dataMap=new HashMap<>();
		switch (operation) {
		case "getAllGasFields":{
			rs = gasFiledservice.getAllGasfiled();
			break;
		}
		case "addGasField":{
			dataMap.put("name", request.getParameter("name"));
			dataMap.put("area", request.getParameter("area"));
			dataMap.put("type", request.getParameter("type"));
			dataMap.put("depthType", request.getParameter("depthType"));
			dataMap.put("reserves", request.getParameter("reserves"));
			rs = gasFiledservice.addGasField(dataMap);
			break;
		}
		case "modifyGasField":{
			dataMap.put("id", request.getParameter("id"));
			dataMap.put("newId", request.getParameter("newId"));
			dataMap.put("name", request.getParameter("name"));
			dataMap.put("area", request.getParameter("area"));
			dataMap.put("type", request.getParameter("type"));
			dataMap.put("depthType", request.getParameter("depthType"));
			dataMap.put("reserves", request.getParameter("reserves"));
			rs = gasFiledservice.modifyGasField(dataMap);
			break;
		}
		case "deleteGasField":{
			dataMap.put("id", request.getParameter("id"));
			rs = gasFiledservice.deleteGasfield(dataMap);
			break;
		}
		case "getAllGasfieldByArea":{
			dataMap.put("area", request.getParameter("area"));
			rs=gasFiledservice.getAllGasfieldByArea(dataMap);
			break;
		}
		case"getInitChart":{
			rs = gasFiledservice.getChartInitData();
			break;
		}
		case "getChartData":{
			if (request.getParameter("ops").equals("0")) {
				dataMap.put("type", request.getParameter("type"));
			}else {
				dataMap.put("depthType", request.getParameter("depthType"));
			}
			rs=gasFiledservice.getgetAllGasfieldPipe(dataMap);
			break;
		}
		default:
			break;
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
