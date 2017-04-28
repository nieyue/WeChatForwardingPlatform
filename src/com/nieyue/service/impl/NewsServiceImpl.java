package com.nieyue.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nieyue.bean.Img;
import com.nieyue.bean.News;
import com.nieyue.dao.ImgDao;
import com.nieyue.dao.NewsDao;
import com.nieyue.dto.NewsDTO;
import com.nieyue.service.DataService;
import com.nieyue.service.NewsService;

/**
 * 新闻业务实现类
 * @author Administrator
 *
 */
@Service
public class NewsServiceImpl implements NewsService {
	@Resource
	NewsDao newsDao;
	@Resource
	ImgDao imgDao;
	@Resource
	DataService dataService;
	/**
	 * 增加新闻
	 * @param 
	 */
	@Override
	public boolean addNews(News news) {
		news.setReadingNumber(0);
		news.setStatus("正常");
		news.setPvs(0l);
		news.setUvs(0l);
		news.setIps(0l);
		news.setNowTotalPrice(0.0);
		news.setScale(0.0);
		news.setNowTotalProfit(0.0);
		boolean b = newsDao.addNews(news);
		/*Data data = new Data();
		data.setIps(0l);
		data.setUvs(0l);
		data.setPvs(0l);
		data.setNewsId(news.getNewsId());
		b=dataService.addData(data);*/
		return b;
	}
	/**
	 *删除新闻
	 * @param 
	 */
	@Override
	public boolean delNews(Integer newsId) {
		boolean b = newsDao.delNews(newsId);
		return b;
	}
	/**
	 * 更新新闻
	 * @param 
	 */
	@Override
	public boolean updateNews(News news) {
		boolean b = newsDao.updateNews(news);
		return b;
	}
	/**
	 * 加载新闻
	 * @param 
	 */
	@Override
	public News loadNews(Integer newsId) {
		News b = newsDao.loadNews(newsId);
		return b;
	}
	/**
	 * 浏览新闻
	 * @param 
	 */
	@Override
	public List<News> browseNews(Integer managerId,String type, String orderName, String orderWay) {
		List<News> l = newsDao.browseNews( managerId,type, orderName, orderWay);
		return l;
	}
	/**
	 * 分页浏览新闻
	 * @param 
	 */
	@Override
	public List<News> browsePagingNews(Integer managerId,String type, int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browsePagingNews(managerId,type, pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
	/** 删除重复新闻 */	
	@Override
	public boolean delNewsReview() {
		boolean b = newsDao.delNewsReview();
		return b;
	}
	
	/** 随机查询置顶新闻 */
	@Override
	public List<News> browseFixedRecommendRandomNews(Integer managerId,int pageNum,int pageSize,
			int fixedRecommend) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browseFixedRecommendRandomNews(managerId,pageNum-1,pageSize,fixedRecommend);
		return l;
	}
	/** 随机查询推荐新闻 */
	@Override
	public List<News> browseRandomRecommendNews(Integer managerId,int pageSize, int isRecommend) {
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browseRandomRecommendNews( managerId,pageSize,isRecommend);
		return l;
	}
	@Override
	public List<String> browseTypeNews(Integer managerId) {
		List<String> tl = newsDao.browseTypeNews(managerId);
		return tl;
	}
	@Override
	public List<News> browseRecommendNews(Integer managerId,int pageNum, int pageSize, int isRecommend) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browseRecommendNews(managerId,pageNum-1,pageSize,isRecommend);
		return l;
	}
	@Override
	public List<News> browsePagingNewsByTitle(Integer managerId,String title, int pageNum, int pageSize, String orderName,
			String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browsePagingNewsByTitle(managerId,title,pageNum-1,pageSize,orderName,orderWay);
		return l;
	}
	@Override
	public boolean addNewsDTO(NewsDTO newsDTO) {
		boolean b=false;
		News news = newsDTO.getNews();
		news.setReadingNumber(0);
		news.setStatus("正常");
		news.setPvs(0l);
		news.setUvs(0l);
		news.setIps(0l);
		news.setScale(0.0);
		news.setNowTotalPrice(0.0);
		news.setNowTotalProfit(0.0);
		b = newsDao.addNews(news);
		List<Img> il = newsDTO.getImgList();
		for (int i = 0; i < il.size(); i++) {
			Img img = il.get(i);
			img.setNewsId(news.getNewsId());
			b=imgDao.addImg(img);
		}
		return b;
	}
	@Override
	public boolean updateNewsDTO(NewsDTO newsDTO) {
		boolean b=false;
		News news = newsDTO.getNews();
		b = newsDao.updateNews(news);
		List<Img> il = newsDTO.getImgList();
		for (int i = 0; i < il.size(); i++) {
			Img img = il.get(i);
			b=imgDao.updateImg(img);
		}
		return b;
	}
	@Override
	public NewsDTO loadNewsDTO(Integer newsId) {
		NewsDTO newsDTO=new NewsDTO();
		News news = newsDao.loadNews(newsId);
		newsDTO.setNews(news);
		List<Img> il = imgDao.browseImg(newsId, "number", "asc");
		newsDTO.setImgList(il);
		return newsDTO;
	}
	/**
	 * 浏览新闻DTO
	 * @param 
	 */
	@Override
	public List<NewsDTO> browseNewsDTO(Integer managerId,String type, String orderName, String orderWay) {
		if(type.equals("首页")){
			type=null;
		}
		List<News> l = newsDao.browseNews(managerId,type, orderName, orderWay);
		List<NewsDTO> nl = new ArrayList<NewsDTO>();
		for (int i = 0; i < l.size(); i++) {
			News news = l.get(i);
			NewsDTO nnews = new NewsDTO();
			nnews.setNews(news);
			List<Img> il = imgDao.browseImg(news.getNewsId(), "number", "desc");
			nnews.setImgList(il);
			nl.add(nnews);
		}
		return nl;
	}
	@Override
	public List<NewsDTO> browsePagingNewsDTOByTitle(Integer managerId,String title, int pageNum, int pageSize, String orderName,
			String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browsePagingNewsByTitle( managerId,title,pageNum-1,pageSize,orderName,orderWay);
		List<NewsDTO> nl = new ArrayList<NewsDTO>();
		for (int i = 0; i < l.size(); i++) {
			News news = l.get(i);
			NewsDTO nnews = new NewsDTO();
			nnews.setNews(news);
			List<Img> il = imgDao.browseImg(news.getNewsId(), "number", "desc");
			nnews.setImgList(il);
			nl.add(nnews);
		}
		return nl;
	}
	@Override
	public List<NewsDTO> browsePagingNewsDTO(Integer managerId,String type, int pageNum, int pageSize, String orderName,
			String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browsePagingNews(managerId,type, pageNum-1, pageSize, orderName, orderWay);
		List<NewsDTO> nl = new ArrayList<NewsDTO>();
		for (int i = 0; i < l.size(); i++) {
			News news = l.get(i);
			NewsDTO nnews = new NewsDTO();
			nnews.setNews(news);
			List<Img> il = imgDao.browseImg(news.getNewsId(), "number", "desc");
			nnews.setImgList(il);
			nl.add(nnews);
		}
		return nl;
	}
	@Override
	public List<NewsDTO> browseFixedRecommendRandomNewsDTO(Integer managerId,int pageNum, int pageSize, int fixedRecommend) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browseFixedRecommendRandomNews(managerId,pageNum-1,pageSize,fixedRecommend);
		List<NewsDTO> nl = new ArrayList<NewsDTO>();
		for (int i = 0; i < l.size(); i++) {
			News news = l.get(i);
			NewsDTO nnews = new NewsDTO();
			nnews.setNews(news);
			List<Img> il = imgDao.browseImg(news.getNewsId(), "number", "desc");
			nnews.setImgList(il);
			nl.add(nnews);
		}
		return nl;
	}
	@Override
	public List<NewsDTO> browseRecommendNewsDTO(Integer managerId,int pageNum, int pageSize, int isRecommend) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browseRecommendNews( managerId,pageNum-1,pageSize,isRecommend);
		List<NewsDTO> nl = new ArrayList<NewsDTO>();
		for (int i = 0; i < l.size(); i++) {
			News news = l.get(i);
			NewsDTO nnews = new NewsDTO();
			nnews.setNews(news);
			List<Img> il = imgDao.browseImg(news.getNewsId(), "number", "desc");
			nnews.setImgList(il);
			nl.add(nnews);
		}
		return nl;
	}
	@Override
	public List<NewsDTO> browseRandomRecommendNewsDTO(Integer managerId,int pageSize, int isRecommend) {
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<News> l = newsDao.browseRandomRecommendNews( managerId,pageSize,isRecommend);
		List<NewsDTO> nl = new ArrayList<NewsDTO>();
		for (int i = 0; i < l.size(); i++) {
			News news = l.get(i);
			NewsDTO nnews = new NewsDTO();
			nnews.setNews(news);
			List<Img> il = imgDao.browseImg(news.getNewsId(), "number", "desc");
			nnews.setImgList(il);
			nl.add(nnews);
		}
		return nl;
	}
	
}
