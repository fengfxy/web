package com.fxy.util.upload;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.fxy.util.UUID.UUIDUtil;



public class UploadFileUtil {

	/**
	 * 
	 * @param request 请求对象
	 * @param path 服务器存放文件路径
	 * @param formName 表单中的name值
	 * @return 返回存储好的文件名称
	 * @throws Exception
	 */
	public static String upload(HttpServletRequest request,String path, String formName) throws Exception {

		// 存储路径
		String savePath = request.getServletContext().getRealPath(path);
		// 获取上传的文件
		// Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
		// 通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
		Part part = request.getPart(formName);
		// Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
		// 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
		String header = part.getHeader("content-disposition");
		// 获取文件名
		String fileName = UUIDUtil.getUUID()+getFileName(header);
		// 把文件写到指定路径
		part.write(savePath + File.separator + fileName);
		
		return fileName;
		
		
	}
	
	 /**
     * 根据请求头解析出文件名
     * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="snmp4j--api.zip"
     *                 IE浏览器下：form-data; name="file"; filename="E:\snmp4j--api.zip"
     * @param header 请求头
     * @return 文件名
     */
    private static String getFileName(String header) {
        /**
         * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
         * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="xxx.zip"}
         * IE浏览器下：tempArr1={form-data,name="file",filename="E:\xxx.zip"}
         */
        String[] tempArr1 = header.split(";");
        /**
         *火狐或者google浏览器下：tempArr2={filename,"xxx.zip"}
         *IE浏览器下：tempArr2={filename,"E:\xxx.zip"}
         */
        String[] tempArr2 = tempArr1[2].split("=");
        //获取文件名，兼容各种浏览器的写法
        String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
        return fileName;
    }
    

}
