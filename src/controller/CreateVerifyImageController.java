package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CreateImage;


@WebServlet(urlPatterns="/CreateVerifyImageController")
public class CreateVerifyImageController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		CreateImage createImage = new CreateImage(); 
		
		String vCode=createImage.createCode();
		
		HttpSession session=request.getSession();
		
		session.setAttribute("verityCode", vCode);
		
		response.setContentType("img/jpg");

		response.setHeader("Prama", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
	
		response.setDateHeader("Expires", 0);
		
		BufferedImage image =createImage.CreateImage(vCode);
		ServletOutputStream out= response.getOutputStream();
		ImageIO.write(image, "JPG", out);
		out.flush();
		out.close();
	}

}
