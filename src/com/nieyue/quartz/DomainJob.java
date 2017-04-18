package com.nieyue.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.nieyue.bean.Domain;
import com.nieyue.bean.News;
import com.nieyue.dto.StateResult;
import com.nieyue.mail.MailSenderInfo;
import com.nieyue.mail.SendMailDemo;
import com.nieyue.service.DomainService;
import com.nieyue.service.ManagerService;
import com.nieyue.util.DateUtil;
import com.nieyue.util.HttpClientUtil;

import net.sf.json.JSONObject;

/**
 * 广告位工作
 * @author yy
 *
 */
public class DomainJob {
	@Resource
	private DomainService domainService;
	@Resource
	private ManagerService managerService;
	/**
	 * 每隔2秒调用一次
	 */
	public void doGetApps(){
		List<Domain> l = domainService.browsePagingDomain("正常", 1, 1, "domain_id", "asc");
		Domain domain = l.get(0);
		String domainName=domain.getName();
		String url="http://vip.weixin139.com/weixin/wx_domain.php?user=changsheng&key=a75b9dd795e34ad490cc6dda82dc112f&domain="+domainName;
		try {
			String s = HttpClientUtil.doGet(url);
			if(!JSONObject.fromObject(s).get("status").equals(0)){
				domain.setStatus("停用");
				domain.setStopDate(new Date());
				domainService.updateDomain(domain);
				//通知邮箱
				List<String> le = managerService.browseAllManagerEmail();
				for (int i = 0; i < le.size(); i++) {
					String se = le.get(i);
					if(se!=null&&!se.equals("")){
						// 设置邮件服务器信息
						MailSenderInfo mailInfo = new MailSenderInfo();
						mailInfo.setMailServerHost("smtp.mxhichina.com");
						mailInfo.setMailServerPort("25");
						mailInfo.setValidate(true);
						// 邮箱用户名
						mailInfo.setUserName("benzhenchayuan@yayao8.com");
						// 邮箱密码
						mailInfo.setPassword("yayao123+++");
						// 发件人邮箱
						mailInfo.setFromAddress("benzhenchayuan@yayao8.com");
						// 收件人邮箱
						mailInfo.setToAddress(se);
						// 邮件标题
						mailInfo.setSubject(domainName+"域名被封！");
						// 邮件内容
						mailInfo.setContent("请及时更换链接！时间："+DateUtil.getCurrentTime());
				       SendMailDemo.sendSelfMail(mailInfo);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
