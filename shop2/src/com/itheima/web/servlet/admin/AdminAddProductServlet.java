package com.itheima.web.servlet.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.bean.Product;
import com.itheima.service.AdminProductService;
import com.itheima.utils.CommonsUtils;

public class AdminAddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		Map<String, Object> map = new HashMap<String, Object>();
		String imagePath = null;
		String imagePathServer = null;
		try {
			List<FileItem> parseRequest = upload.parseRequest(request);
			for (FileItem fileItem : parseRequest) {
				boolean formField = fileItem.isFormField();
				if(formField){
					//是普通表单项
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("UTF-8");
					map.put(fieldName, fieldValue);
				}else{
					//文件上传表单项
					String fileName = fileItem.getName();
					InputStream input = fileItem.getInputStream();
					imagePath = "H:/MarsEE_WorkSpace/shop/WebContent/products/1/"+fileName;
					imagePathServer = this.getServletContext().getRealPath("products/1/")+fileName;
					//将图片上传到服务器
					OutputStream output = new FileOutputStream(imagePathServer);
					IOUtils.copy(input, output);
					//将图片上传到本地工作空间的目录下
					output = new FileOutputStream(imagePath);
					IOUtils.copy(input, output);
					map.put("pimage", "products/1/"+fileName);
					output.close();
					input.close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//封装数据
		Product pro = new Product();
		try {
			BeanUtils.populate(pro, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pro.setPid(CommonsUtils.getUUID());
		pro.setPdate(new Date());
		pro.setPflag(0);//0表示上架中  1 表示下架了
		
		AdminProductService service = new AdminProductService();
		
		service.addProduct(pro);
		
		response.sendRedirect(request.getContextPath()+"/adminProductList");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}