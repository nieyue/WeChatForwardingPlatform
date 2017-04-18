package com.nieyue.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nieyue.bean.News;
import com.nieyue.comments.IPCountUtil;
import com.nieyue.comments.MyJoup;
import com.nieyue.dto.NewsDTO;
import com.nieyue.dto.StateResult;
import com.nieyue.mail.MailSenderInfo;
import com.nieyue.mail.SendMailDemo;
import com.nieyue.service.NewsService;
import com.nieyue.util.DateUtil;
import com.nieyue.util.FileUploadUtil;
import com.nieyue.util.UploaderPath;

import net.sf.json.JSONObject;

/**
 * 新闻控制类
 * @author yy
 *
 */
@Controller("newsController")
@RequestMapping("/news")
public class NewsController {
	@Resource
	private NewsService newsService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 分页浏览所有新闻
	 * @param orderName 排序数据库字段
	 * @param orderWay 排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<News> browsePagingNews(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="pageNum",defaultValue="0",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="type",defaultValue="首页",required=false) String type,
			@RequestParam(value="orderName",required=false,defaultValue="news_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc") String orderWay,HttpSession session)  {
			List<News> list = new ArrayList<News>();
			if(pageNum==0 ||pageSize==0){//查询所有
				list= newsService.browseNews(managerId,type, orderName, orderWay);
				return list;
			}
			list= newsService.browsePagingNews(managerId,type,pageNum, pageSize, orderName, orderWay);
			return list;
	}
	/**
	 * 分页浏览所有新闻DTO
	 * @param orderName 排序数据库字段
	 * @param orderWay 排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/dto/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<NewsDTO> browsePagingNewsDTO(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="type",defaultValue="首页",required=false) String type,
			@RequestParam(value="pageNum",defaultValue="0",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="news_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc") String orderWay,HttpSession session)  {
		List<NewsDTO> list = new ArrayList<NewsDTO>();
		if(pageNum==0 ||pageSize==0){//查询所有
			list= newsService.browseNewsDTO(managerId,type, orderName, orderWay);
			return list;
		}
		list= newsService.browsePagingNewsDTO(managerId,type,pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 分页推荐新闻DTO
	 * @param orderName 排序数据库字段
	 * @param orderWay 排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/dto/list/isRecommend", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<NewsDTO> browseRecommendNewsDTO(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="isRecommend",defaultValue="1",required=false) int isRecommend,
			@RequestParam(value="pageNum",defaultValue="0",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			HttpSession session)  {
		List<NewsDTO> list = new ArrayList<NewsDTO>();
		list= newsService.browseRecommendNewsDTO(managerId,pageNum, pageSize, isRecommend);
		return list;
	}
	/**
	 * 根据标题分页浏览所有新闻
	 * @param orderName 排序数据库字段
	 * @param orderWay 排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/title", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<News> browsePagingNewsByTitle(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="title",defaultValue="无",required=false) String title,
			@RequestParam(value="pageNum",defaultValue="0",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="news_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc") String orderWay,HttpSession session)  {
		List<News> list = new ArrayList<News>();
		list= newsService.browsePagingNewsByTitle(managerId,title, pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 根据标题分页浏览所有新闻DTO
	 * @param orderName 排序数据库字段
	 * @param orderWay 排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/dto/list/title", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<NewsDTO> browsePagingNewsByTitleDTO(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="title",defaultValue="无",required=false) String title,
			@RequestParam(value="pageNum",defaultValue="0",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="news_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="asc") String orderWay,HttpSession session)  {
		List<NewsDTO> list = new ArrayList<NewsDTO>();
		list= newsService.browsePagingNewsDTOByTitle(managerId,title, pageNum, pageSize, orderName, orderWay);
		return list;
	}
	/**
	 * 随机浏览推荐新闻
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/random/isRecommend", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<News> browseRandomRecommendNews(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="pageNum",defaultValue="0",required=false) int pageNum,
			@RequestParam(value="pageSize",defaultValue="5",required=false) int pageSize,
			HttpSession session)  {
		List<News> list = new ArrayList<News>();
		if(pageNum==0){
			list= newsService.browseRandomRecommendNews(managerId,pageSize,1);			
		}else{
			list=newsService.browseRecommendNews(managerId,pageNum, pageSize, 1);
		}
		return list;
	}
	/**
	 * 随机浏览推荐新闻DTO
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/dto/list/random/isRecommend", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<NewsDTO> browseRandomRecommendNewsDTO(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="pageNum",defaultValue="0",required=false) int pageNum,
			@RequestParam(value="pageSize",defaultValue="5",required=false) int pageSize,
			HttpSession session)  {
		List<NewsDTO> list = new ArrayList<NewsDTO>();
		if(pageNum==0){
			list= newsService.browseRandomRecommendNewsDTO(managerId,pageSize,1);			
		}else{
			list=newsService.browseRecommendNewsDTO(managerId,pageNum, pageSize, 1);
		}
		return list;
	}
	/**
	 * 浏览置顶新闻
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list/random/fixedRecommend", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<News> browseFixedRecommendRandomNews(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="pageNum",defaultValue="1",required=false) int pageNum,
			@RequestParam(value="pageSize",defaultValue="3",required=false) int pageSize,
			HttpSession session)  {
		List<News> list = new ArrayList<News>();
		list= newsService.browseFixedRecommendRandomNews(managerId,pageNum,pageSize,1);
		return list;
	}
	/**
	 * 浏览置顶新闻DTO
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/dto/list/random/fixedRecommend", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<NewsDTO> browseFixedRecommendRandomNewsDTO(
			@RequestParam(value="managerId",required=false)Integer managerId,
			@RequestParam(value="pageNum",defaultValue="1",required=false) int pageNum,
			@RequestParam(value="pageSize",defaultValue="3",required=false) int pageSize,
			HttpSession session)  {
		List<NewsDTO> list = new ArrayList<NewsDTO>();
		list= newsService.browseFixedRecommendRandomNewsDTO(managerId,pageNum,pageSize,1);
		return list;
	}
	/**
	 * 新闻修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateNews(@ModelAttribute News news,HttpSession session)  {
		boolean um = newsService.updateNews(news);
		return StateResult.getSR(um);
	}
	/**
	 * 新闻修改
	 * @return
	 */
	@RequestMapping(value = "/dto/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateNewsDTO(@RequestBody NewsDTO newsDTO,HttpSession session)  {
		boolean um = newsService.updateNewsDTO(newsDTO);
		return StateResult.getSR(um);
	}
	/**
	 * 新闻增加
	 * @return
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addNews(@ModelAttribute News news, HttpSession session)  {
		if(news.getImgAddress()==null||news.getImgAddress().equals("")){
		Document doc = Jsoup.parse(news.getContent());
		if(!doc.select("img").equals(new Elements())){
			news.setImgAddress(doc.select("img").get(0).attr("src"));
		}
		}else{
			news.setImgAddress(news.getImgAddress());
		}
		boolean am = newsService.addNews(news);
		return StateResult.getSR(am);
	}
	/**
	 * 新闻增加DTO
	 * @return
	 */
	@RequestMapping(value = "/dto/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addNewsDTO(@RequestBody NewsDTO newsDTO, HttpSession session)  {
		boolean am = newsService.addNewsDTO(newsDTO);
		return StateResult.getSR(am);
	}
	/**
	 * 新闻抓取
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/grab", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult grabNews(@RequestParam(value="url")String url,@RequestParam(value="params")String params) throws Exception  {
		boolean b = false;
		News news;
		JSONObject p = JSONObject.fromObject(params);
		String link=p.getString("link");
		String title=p.getString("title");
		String time=p.getString("time");
		String type=p.getString("type");
		String img_address=p.getString("img_address");
		String content=p.getString("content");
		try {
				List<News> l = MyJoup.getNewsLink(url,link, title,
				time, type, img_address, content);
				for (int i = 0; i < l.size(); i++) {
					news=l.get(i);
					b=newsService.addNews(news);
				}
			newsService.delNewsReview();//去重
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return StateResult.getSR(b);
	}
	/**
	 * 东方新闻抓取
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/dfgrab", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult dfgrabNews(@RequestParam(value="url")String url,@RequestParam(value="content")String content) throws Exception  {
		boolean b = false;
		News news;
		try {
			List<News> l = MyJoup.getDFNewsDetails(url, content);
			for (int i = 0; i < l.size(); i++) {
				news=l.get(i);
				b=newsService.addNews(news);
			}
			newsService.delNewsReview();//去重
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return StateResult.getSR(b);
	}
	/**
	 * 新闻去重删除
	 * @return
	 */
	@RequestMapping(value = "/delete/review", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delNewsReview()  {
		boolean dm = newsService.delNewsReview();
		return StateResult.getSR(dm);
	}
	/**
	 * 新闻删除
	 * @return
	 */
	@RequestMapping(value = "/{newsId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delNews(@PathVariable("newsId") Integer newsId,HttpSession session)  {
		boolean dm = newsService.delNews(newsId);
		return StateResult.getSR(dm);
	}
	
	/**
	 * 单个新闻加载 ,增加
	 * @return
	 */
	@RequestMapping(value = "/{newsId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody News loadNews(@PathVariable("newsId") Integer newsId,HttpSession session,HttpServletRequest request)  {
		News news=new News();
		news = newsService.loadNews(newsId);
		int newSize=0;
		BoundSetOperations<String, String> bso = stringRedisTemplate.boundSetOps("WeChatForwardingPlatformNewsId"+newsId);
		if(bso.members()!=null && bso.members().size()>0){
			int oldSize = bso.members().size();
			bso.add(IPCountUtil.getIpAddr(request));
			 newSize = bso.members().size();
				//ip增加了
				if(oldSize<newSize){
					news.setReadingNumber(news.getReadingNumber()+1);
				}
		}else{
			//bso.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);//按天计算有用
			bso.add(IPCountUtil.getIpAddr(request));
			newSize=1;
			news.setReadingNumber(newSize);
		}
		if(news.getUnitPrice()==null || news.getUnitPrice().equals("")){
			news.setUnitPrice(0.0);
		}
		news.setNowTotalPrice(Double.valueOf(news.getUnitPrice()*news.getReadingNumber()));
		newsService.updateNews(news);
		return news;
	}
	
	/**
	 * 单个新闻加载 ,增加
	 * @return
	 */
	@RequestMapping(value = "/click", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody News clickNews(
			@RequestParam(value="newsId") Integer newsId,
			@RequestParam(value="uv",defaultValue="0",required=false) Integer uv,
			HttpSession session,HttpServletRequest request)  {
		News news=new News();
		news = newsService.loadNews(newsId);
		//pvs
		news.setPvs(news.getPvs()+1);
		//uvs
		if(uv.equals(1)){
			news.setUvs(news.getUvs()+1);
		}
		//ips
		int newSize=0;
		BoundSetOperations<String, String> bso = stringRedisTemplate.boundSetOps("WeChatForwardingPlatformNewsId"+newsId);
		if(bso.members()!=null && bso.members().size()>0){
			int oldSize = bso.members().size();
			bso.add(IPCountUtil.getIpAddr(request));
			newSize = bso.members().size();
			//ip增加了
			if(oldSize<newSize){
				news.setIps(news.getIps()+1);
				news.setReadingNumber(news.getReadingNumber()+1);
			}
		}else{
			//bso.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);//按天计算有用
			bso.add(IPCountUtil.getIpAddr(request));
			newSize=1;
			news.setIps(Long.valueOf(newSize));
			news.setReadingNumber(newSize);
		}
		if(news.getUnitPrice()==null || news.getUnitPrice().equals("")){
			news.setUnitPrice(0.0);
		}
		news.setNowTotalPrice(Double.valueOf(news.getUnitPrice()*news.getReadingNumber()));
		newsService.updateNews(news);
		return news;
	}
	
	/**
	 * 单个新闻加载DTO
	 * @return
	 */
	@RequestMapping(value = "/dto/{newsId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody NewsDTO loadNewsDTO(@PathVariable("newsId") Integer newsId,HttpSession session)  {
		NewsDTO newsDTO=new NewsDTO();
		newsDTO = newsService.loadNewsDTO(newsId);
		return newsDTO;
	}
	/**
	 * 查询所有类型 去空 去重
	 * @return
	 */
	@RequestMapping(value = "/types", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody List<String> browseTypeNews(
			@RequestParam(value="managerId",required=false)Integer managerId
			)  {
		List<String> tl = newsService.browseTypeNews(managerId);
		return tl;
	}
	/**
	 * 图片增加、修改
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/img/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String addAdvertiseImg(
			@RequestParam("testFileUpload") MultipartFile file,
			HttpSession session ) throws IOException  {
		String imgUrl = null;
		String imgdir=DateUtil.getImgDir();
		try{
			imgUrl = FileUploadUtil.FormDataMerImgFileUpload(file, session,UploaderPath.GetValueByKey(UploaderPath.ROOTPATH),UploaderPath.GetValueByKey(UploaderPath.IMG),imgdir);
		}catch (IOException e) {
			throw new IOException();
		}
		return imgUrl;
	}
	/**
	 * 单个新闻发送邮件
	 * @return
	 */
	@RequestMapping(value = "/email/{newsId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult emailNews(
			@PathVariable("newsId") Integer newsId,
			@RequestParam(value="mailServerHost")String mailServerHost,
			@RequestParam(value="mailServerPort")String mailServerPort,
			@RequestParam(value="userName")String userName,
			@RequestParam(value="password")String password,
			@RequestParam(value="toAddress")String toAddress,
			HttpServletRequest request)  {
		News news=new News();
		news = newsService.loadNews(newsId);
		// 设置邮件服务器信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(true);
		// 邮箱用户名
		mailInfo.setUserName(userName);
		// 邮箱密码
		mailInfo.setPassword(password);
		// 发件人邮箱
		mailInfo.setFromAddress(userName);
		// 收件人邮箱
		mailInfo.setToAddress(toAddress);
		// 邮件标题
		mailInfo.setSubject(news.getTitle());
		//获取域名
		StringBuffer url = request.getRequestURL();  
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("<strong style='font-size:38px;'>"+news.getTitle()+"</strong><br/><hr/>");
		Document newsContent = Jsoup.parse(news.getContent());
		Elements imgs = newsContent.select("img");
		for (int i = 0; i < imgs.size(); i++) {
			imgs.get(i).attr("src", tempContextUrl+imgs.get(i).attr("src"));
		}
		buffer.append(newsContent);
		//buffer.append("<a href='http://man.fuwu88.cn/man/news_details?type=%E5%A5%87%E9%97%BB&news_id=9331#'><img src='http://man.fuwu88.cn/uploaderPath/ueditor/image/20170207/1486431046149068348.jpg'/></a><br/>");
		mailInfo.setContent(buffer.toString());
       boolean s = SendMailDemo.sendSelfMail(mailInfo);
		return StateResult.getSR(s);
	}
	
	
}
