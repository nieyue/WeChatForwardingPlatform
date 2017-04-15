package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.News;
/**
 * 新闻接口
 * @author yy
 *
 */
public interface NewsDao {
	/** 新增新闻 */	
	public boolean addNews(News news) ;	
	/** 删除新闻 */	
	public boolean delNews(Integer newsId) ;
	/** 删除重复新闻 */	
	public boolean delNewsReview() ;
	/** 更新新闻 */	
	public boolean updateNews(News news);
	/** 装载新闻 */	
	public News loadNews(Integer newsId);
	/** 根据类别浏览新闻 */
	public List<News> browseNews(@Param("managerId")Integer managerId,@Param("type")String type,@Param("orderName")String orderName,@Param("orderWay")String orderWay);
	/** 根据标题分页新闻 */
	public List<News> browsePagingNewsByTitle(@Param("managerId")Integer managerId,@Param("title")String title,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;
	/** 分页新闻 */
	public List<News> browsePagingNews(@Param("managerId")Integer managerId,@Param("type")String type,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;
	/** 随机查询置顶新闻 */
	public List<News> browseFixedRecommendRandomNews(@Param("managerId")Integer managerId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("fixedRecommend")int fixedRecommend);
	/** 查询推荐新闻 */
	public List<News> browseRecommendNews(@Param("managerId")Integer managerId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("isRecommend")int isRecommend);
	/** 随机查询推荐新闻 */
	public List<News> browseRandomRecommendNews(@Param("managerId")Integer managerId,@Param("pageSize")int pageSize,@Param("isRecommend")int isRecommend);
	/** 查询所有类型 去空 去重*/
	public List<String> browseTypeNews(@Param("managerId")Integer managerId);
}
