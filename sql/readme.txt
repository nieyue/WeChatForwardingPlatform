INSERT wechat_forwarding_platform_db.news_tb(news_id,
    	type,
    	title,
    	time,
    	fixed_recommend,
    	is_recommend,
    	img_address,
    	content) SELECT * FROM manhelp_db.news_tb


INSERT wechat_forwarding_platform_db.img_tb(img_id,
	 img_address,
	 number,
     news_id) SELECT * FROM manhelp_db.img_tb


update news_tb 
set 
unit_price='0',
total_price='0',
reading_number='0',
now_total_price='0',
status='正常',
manager_id='1000'
