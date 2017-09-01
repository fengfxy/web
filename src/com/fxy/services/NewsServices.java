package com.fxy.services;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.News;
import com.fxy.beans.NewsInfo;
import com.fxy.beans.NewsQuery;
import com.fxy.dao.NewsInfoMapper;
import com.fxy.dao.NewsMapper;
import com.fxy.util.app.AppUtil;
import com.fxy.util.log.LogUtil;
import com.fxy.util.mybatis.MyBatisUtil;
import com.fxy.util.upload.UploadFileUtil;

public class NewsServices {

	// 上传数据的服务器路径【绝对路径】【文件操作都是绝对路径】
	String savePath = AppUtil.SC.getRealPath("/admin/upload");

	public ArrayList<News> findByAll() throws Exception {
		ArrayList<News> result = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		result = (ArrayList<News>) newsMapper.selectByExample();
		sqlSession.close();
		return result;
	}

	public ArrayList<News> findByQuery(NewsQuery newsQuery) throws Exception {
		ArrayList<News> result = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		result = (ArrayList<News>) newsMapper.selectByQuery(newsQuery);
		sqlSession.close();
		return result;

	}

	public News findById(Integer id) throws Exception {
		News result = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		result = newsMapper.selectByPrimaryKey(id);
		sqlSession.close();
		return result;
	}

	// 添加业务里面同时调用新闻DAO和新闻内容DAO
	public boolean add(News news, NewsInfo newsInfo, Part part) throws Exception {
		boolean result = false;

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);

		NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);

		// 添加新闻
		int temp = newsMapper.insert(news);
		if (temp > 0) {
			LogUtil.log("**********服务层****新闻添加****新闻概述(News)添加成功，获取ID");
			// 设置新闻内容的新闻ID
			newsInfo.setNewsId(news.getId());
			// 添加新闻内容
			int temp2 = newsInfoMapper.insert(newsInfo);
			if (temp2 > 0) {
				LogUtil.log("**********服务层****新闻添加****新闻内容(NewsInfo)添加成功，开始上传图片文件");
				// 开始上传文件
				String photoANewFileName = UploadFileUtil.upload(savePath, part);
				// 修改新闻的三个图片
				news.setPhotoA("A" + photoANewFileName);
				news.setPhotoB("B" + photoANewFileName);
				news.setPhotoC("C" + photoANewFileName);

				int temp3 = newsMapper.updateByPhoto(news);
				if (temp3 > 0) {
					result = true;
					// 如果都添加成，则提交
					// 否则自动回滚
					sqlSession.commit();
					LogUtil.log("**********服务层****新闻添加****新闻整体添加成功返回，true");
				} else {
					LogUtil.log("**********服务层****新闻添加****新闻内容(NewsInfo)上传图片文件出问题");
				}
			} else {
				LogUtil.log("**********服务层****新闻添加****新闻内容(NewsInfo)失败");
			}

		} else {
			LogUtil.log("**********服务层****新闻添加****新闻概述(News)添加失败");
		}
		// 最后关闭
		sqlSession.close();

		return result;

	}

	public boolean edit(News news, NewsInfo newsInfo, boolean isPhoto, Part part) throws Exception {
		boolean result = false;

		// 1：修改新闻
		// 2：修改内容
		// 3：修改图片如果isPhoto为真
		// 3-1:修改图片基本方案为，先删除旧的，再上传新的，最后更改数据库

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		// 获取新闻DAO
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		// 获取新闻内容DAO
		NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);

		// 修改新闻
		int temp = newsMapper.updateByPrimaryKey(news);
		// 修改新闻成功
		if (temp > 0) {
			// 修改新闻内容
			int temp2 = newsInfoMapper.updateByNews(newsInfo);
			// 修改新闻内容成功
			if (temp2 > 0) {

				// 设置标记判断是否在处理文件之后修改失败
				boolean tag = true;
				// 如果需要处理图片
				if (isPhoto) {
					// 删除旧图片================================================
					// 拿到文件物理路径
					String fA = savePath + File.separator + news.getPhotoA();
					String fB = savePath + File.separator + news.getPhotoB();
					String fC = savePath + File.separator + news.getPhotoC();
					File fileA = new File(fA);
					File fileB = new File(fB);
					File fileC = new File(fC);
					// 删除图片成功
					if (fileA.exists()) {
						fileA.delete();
					}
					if (fileB.exists()) {
						fileB.delete();
					}
					if (fileC.exists()) {
						fileC.delete();
					}

					// 上传新文件
					String photoANewFileName = UploadFileUtil.upload(savePath, part);
					// 从新设置news的新文件属性
					news.setPhotoA("A" + photoANewFileName);
					news.setPhotoB("B" + photoANewFileName);
					news.setPhotoC("C" + photoANewFileName);

					// 更改数据库
					int temp3 = newsMapper.updateByPhoto(news);
					if (temp3 <= 0) {
						// 修改数据库文件失败
						tag = false;
						LogUtil.log("修改图片失败!");
					}
				}

				// 最终确定是否修改成功
				if (tag) {
					result = true;
					sqlSession.commit();
					LogUtil.log("新闻和内容都修改成功!");
				}

			} else {
				LogUtil.log("新闻内容修改失败!");
			}

		} else {
			LogUtil.log("新闻修改失败!");
		}
		// 最后关闭
		sqlSession.close();
		return result;

	}

	public boolean del(Integer id) throws Exception {
		boolean result = false;
		// 1：删除新闻
		// 2：删除内容
		// 3：删除图片
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		// 获取新闻DAO
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		// 获取新闻内容DAO
		NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);
		// 获取新闻，因为要准备删除新闻图片
		News news = findById(id);

		// 删除新闻
		int temp = newsMapper.deleteByPrimaryKey(id);
		// 删除新闻成功
		if (temp > 0) {
			// 删除新闻内容
			int temp2 = newsInfoMapper.deleteByNews(id);
			// 删除内容成功
			if (temp2 > 0) {
				// 删除新闻图片【不做为正常业务范围，也就是删除图片不失败也胡删除新闻】
				// 拿到文件物理路径
				String savePath = AppUtil.SC.getRealPath("/admin/upload");
				String fA = savePath + File.separator + news.getPhotoA();
				String fB = savePath + File.separator + news.getPhotoB();
				String fC = savePath + File.separator + news.getPhotoC();
				File fileA = new File(fA);
				File fileB = new File(fB);
				File fileC = new File(fC);
				// 删除图片成功
				if (fileA.exists()) {
					fileA.delete();
				}
				if (fileB.exists()) {
					fileB.delete();
				}
				if (fileC.exists()) {
					fileC.delete();
				}

				result = true;
				sqlSession.commit();

			} else {
				LogUtil.log("新闻内容删除失败!");
			}

		} else {
			LogUtil.log("新闻删除失败!");
		}

		sqlSession.close();
		return result;

	}

}
