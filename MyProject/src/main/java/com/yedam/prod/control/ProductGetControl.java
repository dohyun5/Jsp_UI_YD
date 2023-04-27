package com.yedam.prod.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.prod.domain.ProductVO;
import com.yedam.prod.service.ProductService;
import com.yedam.prod.service.ProductServiceImpl;


public class ProductGetControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String PN = req.getParameter("PN");
		System.out.println(PN);
		ProductService service = new ProductServiceImpl();
		ProductVO vo = service.getProduct(Integer.parseInt(PN));
		req.setAttribute("productGet", vo);
		
		return "prod/prodMain.tiles";
	}

}
