package com.nieyue.service.impl.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.mchange.v2.ser.SerializableUtils;
import com.nieyue.bean.Data;
import com.nieyue.bean.Img;
import com.nieyue.bean.News;
import com.nieyue.comments.MyObjectMapper;
import com.nieyue.service.ImgService;
import com.nieyue.service.NewsService;
import com.nieyue.util.DateUtil;
import com.nieyue.util.MyJson;
import com.thoughtworks.xstream.converters.reflection.SerializableConverter;

import net.sf.json.JSONObject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring-dao.xml","classpath:config/spring-service.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class NewsServiceImplTest {
	@Resource
	NewsService newsService;
	@Resource
	ImgService imgService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private MyJson myJson;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddNews() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelNews() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateNews() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadNews() {
		System.out.println("freeMemory:");
		System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
		System.out.println("maxMemory:");
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
		System.out.println("totalMemory:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
		List<News> al=new ArrayList<News>();
		for (int i = 0; i < 3600; i++) {
			News nes = newsService.loadNews(6582);
			al.add(nes);
			System.out.println("freeMemory:");
			System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
			System.out.println("maxMemory:");
			System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
			System.out.println("totalMemory:");
			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
		}
		
	}
	@Test
	public void testTypeNews() {
		List<News> l = newsService.browseNews(null,null, "news_id", "asc");
		System.out.println(l);
		for (int j = 0; j < l.size(); j++) {
			News news = l.get(j);
			System.out.println(news.getType());
		}
	}

	@Test
	public void testBrowseNews() {
		List<String> t = newsService.browseTypeNews(null);
		for (int i = 0; i < t.size(); i++) {
			List<News> l = newsService.browseNews(null,t.get(i), "news_id", "asc");
			System.out.println(l);
			for (int j = 0; j < l.size(); j++) {
				News news = l.get(j);
				String imgaddress = news.getImgAddress();
				Img img = new Img();
				img.setImgAddress(imgaddress);
				img.setNumber(1);
				img.setNewsId(news.getNewsId());
				imgService.addImg(img);
			}
		}
//		List<News> l = newsService.browseNews("奇闻","news_id", "asc");
//		System.out.println(l.get(7).getContent());
//		System.out.println(l.size());
	}

	@Test
	public void testBrowsePagingNews() {
		//List<News> l = newsService.browseNews("首页","news_id", "asc");
		List<News> l = newsService.browsePagingNews(null,"首页", 10, 10, "news_id", "asc");
		System.out.println(l.size());
	}
	@Test
	public void testBrowsePagingNews2(){
		//BoundSetOperations<String, String> bso = stringRedisTemplate.boundSetOps("WeChatForwardingPlatformNewsId"+0+"Data"+DateUtil.getImgDir());
//		Set<String> ms = bso.members();
//		System.out.println(ms.size());
//		for (String m : ms) {
//			System.out.println(m);
//		}
		Data data = new Data();
		data.setPvs(555l);
		data.setUvs(553l);
		data.setIps(552l);
		data.setNewsId(6597);
		data.setCreateDate(new Date());
		JSONObject jsondata = JSONObject.fromObject(data,myJson.getJsonConfig());
		BoundValueOperations<String, String> bvodata = stringRedisTemplate.boundValueOps("WeChatForwardingPlatformNewsId"+6597+"Data"+DateUtil.getImgDir());
		System.out.println(bvodata.get());
		System.out.println(bvodata.size());
		bvodata.set(jsondata.toString(), 1, TimeUnit.MINUTES);
		Data data2 = (Data)JSONObject.toBean(JSONObject.fromObject(bvodata.get()),Data.class);
		System.out.println(bvodata.get());
		System.out.println(bvodata.size());
		System.out.println(data2.getPvs());
		System.out.println(data2.getUvs());
		System.out.println(data2.getIps());
		System.out.println(data2.getNewsId());
		System.out.println(DateUtil.dateFormatSimpleDate(data2.getCreateDate(),"yyyy-MM-dd"));
		System.out.println(Data.getSerialversionuid());
	}
	@Test
	public void testGetNews() {
		News nes = newsService.loadNews(1000);
		System.out.println(nes);
		System.out.println(nes.getContent());
	}

}
