package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.file.upload.FileUpload;

@SuppressWarnings("serial")
public class FileUploadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUpload.upload(request);
	}
	
	package com.file.upload;

	import java.io.File;
	import java.util.Iterator;
	import java.util.List;

	import javax.servlet.http.HttpServletRequest;

	import org.apache.commons.fileupload.FileItem;
	import org.apache.commons.fileupload.FileUploadException;
	import org.apache.commons.fileupload.disk.DiskFileItemFactory;
	import org.apache.commons.fileupload.servlet.ServletFileUpload;

	import com.file.helper.FileConst;
	import com.file.helper.ReadUploadFileType;

	public class FileUpload {
	private static String uploadPath = null;// �ϴ��ļ���Ŀ¼
	private static String tempPath = null; // ��ʱ�ļ�Ŀ¼
	private static File uploadFile = null;
	private static File tempPathFile = null;
	private static int sizeThreshold = 1024;
	private static int sizeMax = 4194304;
	//��ʼ��
	static{
	   sizeMax  = Integer.parseInt(FileConst.getValue("sizeMax"));
	   sizeThreshold = Integer.parseInt(FileConst.getValue("sizeThreshold"));
	   uploadPath = FileConst.getValue("uploadPath");
	   uploadFile = new File(uploadPath);
	      if (!uploadFile.exists()) {
	          uploadFile.mkdirs();
	      }
	      tempPath = FileConst.getValue("tempPath");
	      tempPathFile = new File(tempPath);
	      if (!tempPathFile.exists()) {
	          tempPathFile.mkdirs();
	      }
	}
	/**
	* �ϴ��ļ�
	* @param request
	* @return true �ϴ��ɹ� false�ϴ�ʧ��
	*/
	@SuppressWarnings("unchecked")
	public static boolean upload(HttpServletRequest request){
	boolean flag = true;
	//������������Ƿ�Ϊmultipart�����ݡ�
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	//�����ǵĻ�
	if(isMultipart){
	 /**Ϊ�����󴴽�һ��DiskFileItemFactory����ͨ��������������ִ�н��������еı���Ŀ��������һ��List�С�**/
	 try {
	  DiskFileItemFactory factory = new DiskFileItemFactory();
	  factory.setSizeThreshold(sizeThreshold); // ���û�������С��������4kb
	  factory.setRepository(tempPathFile);// ���û�����Ŀ¼
	  ServletFileUpload upload = new ServletFileUpload(factory);
	  upload.setHeaderEncoding("UTF-8");//����ļ���������
	  upload.setSizeMax(sizeMax);// ��������ļ��ߴ�
	  List<FileItem> items = upload.parseRequest(request);
	  // ����Ƿ�����ϴ�������
	  if(!checkFileType(items)) return false;
	  Iterator<FileItem> itr = items.iterator();//���еı���
	  //�����ļ�
	  while (itr.hasNext()){
	    FileItem item = (FileItem) itr.next();//ѭ�����ÿ������
	    if (!item.isFormField()){//������ļ�����
	      String name = item.getName();//����ļ��� ����·����
	      if(name!=null){
	       File fullFile=new File(item.getName());
	       File savedFile=new File(uploadPath,fullFile.getName());
	       item.write(savedFile);
	      }
	    }
	  }
	 } catch (FileUploadException e) {
	  flag = false;
	  e.printStackTrace();
	 }catch (Exception e) {
	  flag = false;
	  e.printStackTrace();
	 }
	}else{
	 flag = false;
	 System.out.println("the enctype must be multipart/form-data");
	}
	return flag;
	}

	/**
	* ɾ��һ���ļ�
	* @param filePath �ļ�·������
	*/
	public static void deleteFile(String [] filePath){
	if(filePath!=null && filePath.length>0){
	 for(String path:filePath){
	  String realPath = uploadPath+path;
	  File delfile = new File(realPath);
	  if(delfile.exists()){
	   delfile.delete();
	  }
	 }
	 
	}
	}

	/**
	* ɾ�������ļ�
	* @param filePath �����ļ�·��
	*/
	public static void deleteFile(String filePath){
	if(filePath!=null && !"".equals(filePath)){
	 String [] path = new String []{filePath};
	 deleteFile(path);
	}
	}

	/**
	* �ж��ϴ��ļ�����
	* @param items
	* @return
	*/
	private static Boolean checkFileType(List<FileItem> items){
	Iterator<FileItem> itr = items.iterator();//���еı���
	while (itr.hasNext()){
	  FileItem item = (FileItem) itr.next();//ѭ�����ÿ������
	  if (!item.isFormField()){//������ļ�����
	    String name = item.getName();//����ļ��� ����·����
	    if(name!=null){
	     File fullFile=new File(item.getName());
	     boolean isType = ReadUploadFileType.readUploadFileType(fullFile);
	     if(!isType) return false;
	     break;
	    }
	  }
	}

	return true;
	}
	}
}
