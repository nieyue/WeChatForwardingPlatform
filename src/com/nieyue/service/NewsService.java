package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.News;
import com.nieyue.dto.NewsDTO;
/**
 * 新闻接口
 * @author yy
 *
 */
public interface NewsService {
	/** 新增新闻 */	
	public boolean addNews(News news) ;	
	/** 新增新闻DTO */	
	public boolean addNewsDTO(NewsDTO newsDTO) ;	
	/** 删除新闻 */	
	public boolean delNews(Integer newsId) ;	
	/** 删除重复新闻 */	
	public boolean delNewsReview() ;	
	/** 更新新闻 */	
	public boolean updateNews(News news);
	/** 更新新闻 DTO*/	
	public boolean updateNewsDTO(NewsDTO newsDTO);
	/** 装载新闻 */	
	public News loadNews(Integer newsId);	
	/** 装载新闻DTO */	
	public NewsDTO loadNewsDTO(Integer newsId);	
	/** 根据类别浏览新闻 */
	public List<News> browseNews(Integer managerId,String type,String orderName,String orderWay);
	/** 根据类别浏览新闻DTO */
	public List<NewsDTO> browseNewsDTO(Integer managerId,String type,String orderName,String orderWay);
	/** 根据标题分页新闻 */
	public List<News> browsePagingNewsByTitle(Integer managerId,String title,int pageNum,int pageSize,String orderName,String orderWay) ;
	/** 根据标题分页新闻 DTO*/
	public List<NewsDTO> browsePagingNewsDTOByTitle(Integer managerId,String title,int pageNum,int pageSize,String orderName,String orderWay) ;
	/** 分页新闻 */
	public List<News> browsePagingNews(Integer managerId,String type,int pageNum,int pageSize,String orderName,String orderWay) ;
	/** 分页新闻DTO */
	public List<NewsDTO> browsePagingNewsDTO(Integer managerId,String type,int pageNum,int pageSize,String orderName,String orderWay) ;
	/** 随机查询置顶新闻 */
	public List<News> browseFixedRecommendRandomNews(Integer managerId,int pageNum,int pageSize,int fixedRecommend);
	/** 随机查询置顶新闻DTO */
	public List<NewsDTO> browseFixedRecommendRandomNewsDTO(Integer managerId,int pageNum,int pageSize,int fixedRecommend);
	/** 查询推荐新闻 */
	public List<News> browseRecommendNews(Integer managerId,int pageNum,int pageSize,int isRecommend);
	/** 查询推荐新闻DTO */
	public List<NewsDTO> browseRecommendNewsDTO(Integer managerId,int pageNum,int pageSize,int isRecommend);
	/** 随机查询推荐新闻 */
	public List<News> browseRandomRecommendNews(Integer managerId,int pageSize,int isRecommend);
	/** 随机查询推荐新闻DTO */
	public List<NewsDTO> browseRandomRecommendNewsDTO(Integer managerId,int pageSize,int isRecommend);
	/** 查询所有类型 去空 去重*/
	public List<String> browseTypeNews(Integer managerId);
}
