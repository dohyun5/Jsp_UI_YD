package com.yedam.prod.control;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;

public class ProductUploadControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savePath = req.getServletContext().getRealPath("img");
		String fileName = "";
		String fileUrl = "";
		
		MultipartRequest multi = new MultipartRequest(req, savePath,//
				1024*1024*100, "utf-8",//
				new DefaultFileRenamePolicy());
		
		Enumeration<?> files = multi.getFileNames();
		while(files.hasMoreElements()) {
			String file = (String) files.nextElement();
			fileName = multi.getFilesystemName(file);
	}
		fileUrl = req.getContextPath() + "/img/" + fileName;
		
		//ckeditor 정보확인 에디터 부분 출력.
		JsonObject json = new JsonObject();
		json.addProperty("uploaded", 1);
		json.addProperty("fileName", fileName);
		json.addProperty("url", fileUrl);
		
		return json+".json";
	}

}
