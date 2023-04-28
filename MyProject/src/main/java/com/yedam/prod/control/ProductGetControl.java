package com.yedam.prod.control;

import java.io.IOException;
import java.util.List;

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
		ProductService service = new ProductServiceImpl();
		List<ProductVO> list = service.productList();
		req.setAttribute("List", list);
		
		List<ProductVO> Dlist = service.productDlist();
		req.setAttribute("DList", Dlist);
		System.out.println(Dlist);
		
		String PN = req.getParameter("PN");
		System.out.println(PN);
		
		ProductVO vo = service.getProduct(Integer.parseInt(PN));
		req.setAttribute("productGet", vo);
		System.out.println(vo);
		
		return "prod/prodMain.tiles";
		
	
	}

}
	