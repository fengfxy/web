package com.fxy.util.upload;

import java.io.File;

import javax.servlet.http.Part;

import com.fxy.util.UUID.UUIDUtil;
import com.fxy.util.log.LogUtil;

import net.coobird.thumbnailator.Thumbnails;



public class UploadFileUtil {

	/**
	 * 
	 * @param path 服务器物理路径
	 * @param part Part 上传对象
	 * @return
	 * @throws Exception
	 */
	public static String upload(String path, Part part) throws Exception {		
		// 获取上传的文件
		// Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
		// 通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象	
		// Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
		// 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
		// String header = part.getHeader("content-disposition");
		// 由于各种奇葩的文件名的存在，这里获取原始文件名的方案出现问题，修改为获取文件扩展名，并重新组合
		// 获取文件扩展名可以Content-Type中【可以参考http://tools.jb51.net/table/http_content_type】
		String fileExtension = part.getHeader("Content-Type");
		LogUtil.log("fileExtension:" + fileExtension);
		String fileName = UUIDUtil.getUUID();
		// 组合文件名
		if (fileExtension.equalsIgnoreCase("image/tiff")) {
			fileName+=".tif";
		}
		if (fileExtension.equalsIgnoreCase("image/fax")) {
			fileName+=".fax";
		}
		if (fileExtension.equalsIgnoreCase("image/gif")) {
			fileName+=".gif";
		}
		if (fileExtension.equalsIgnoreCase("image/x-icon")) {
			fileName+=".ico";
		}
		if (fileExtension.equalsIgnoreCase("image/jpeg")) {
			fileName+=".jpg";
		}
		if (fileExtension.equalsIgnoreCase("image/pnetvue")) {
			fileName+=".net";
		}
		if (fileExtension.equalsIgnoreCase("image/png")) {
			fileName+=".png";
		}
		if (fileExtension.equalsIgnoreCase("image/vnd.rn-realpix")) {
			fileName+=".rp";
		}
		if (fileExtension.equalsIgnoreCase("image/vnd.wap.wbmp")) {
			fileName+=".wbmp";
		}

		if (fileExtension.equalsIgnoreCase("image/bmp")) {
			fileName+=".bmp";
		}
		// String fileName = UUIDUtil.getUUID() + getFileName(header);
		// 把文件写到指定服务器路径路径
		part.write(path + File.separator + fileName);
		LogUtil.log("fileName:" + fileName);
		// 开始压缩文件分为A、B、C三种【规则为：在原文件名前面追加A\B\C表示区别】
		// 压缩A
		Thumbnails.of(path + File.separator + fileName).forceSize(640, 426).toFile(path + File.separator + "A" + fileName);
		// 压缩B
		Thumbnails.of(path + File.separator + fileName).forceSize(240, 170).toFile(path + File.separator + "B" + fileName);
		// 压缩C
		Thumbnails.of(path + File.separator + fileName).forceSize(134, 90).toFile(path + File.separator + "C" + fileName);

		// 删除没有压缩的文件
		File file = new File(path + File.separator + fileName);
		if (file.exists()) {
			file.delete();
		}
		// 返回原始文件名
		return fileName;

	}

	/**
	 * 根据请求头解析出文件名 请求头的格式：火狐和google浏览器下：form-data; name="file";
	 * filename="snmp4j--api.zip" IE浏览器下：form-data; name="file";
	 * filename="E:\snmp4j--api.zip"
	 * 
	 * @param header
	 *            请求头
	 * @return 文件名
	 */
	/* private static String getFileName(String header) {
	 *//**
	 * String[] tempArr1 =
	 * header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
	 * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="xxx.zip"}
	 * IE浏览器下：tempArr1={form-data,name="file",filename="E:\xxx.zip"}
	 *//*
	 String[] tempArr1 = header.split(";");
	 *//**
	 * 火狐或者google浏览器下：tempArr2={filename,"xxx.zip"}
	 * IE浏览器下：tempArr2={filename,"E:\xxx.zip"}
	 *//*
	 String[] tempArr2 = tempArr1[2].split("=");
	 // 获取文件名，兼容各种浏览器的写法
	 String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") +
	 1).replaceAll("\"", "");
	 return fileName;
	 }*/
    

}
