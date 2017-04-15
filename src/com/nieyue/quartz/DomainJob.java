package com.nieyue.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.nieyue.bean.Domain;
import com.nieyue.service.DomainService;
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
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
