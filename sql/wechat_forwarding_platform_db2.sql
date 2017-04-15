# 数据库 
#创建数据库
DROP DATABASE IF EXISTS wechat_forwarding_platform_db;
CREATE DATABASE wechat_forwarding_platform_db;

#使用数据库 
use wechat_forwarding_platform_db;

#创建角色表
CREATE TABLE role_tb(
role_id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
name varchar(255) COMMENT '角色名',
duty varchar(255) COMMENT '角色职责',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (role_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

#创建财务表 
CREATE TABLE finance_tb(
finance_id int(11) NOT NULL AUTO_INCREMENT COMMENT '财务id',
money decimal(11,2) COMMENT '余额',
recharge decimal(11,2) COMMENT '充值金额',
consume decimal(11,2) COMMENT '消费金额',
bank_user_name varchar(255) COMMENT '开户人',
bank_name varchar(255) COMMENT '开户银行',
bank_account varchar(255) COMMENT '银行账号',
bank_address varchar(255) COMMENT '开户银行地址',
update_date datetime COMMENT '更新时间',
admin_id int(11) COMMENT '管理员id外键',
PRIMARY KEY (finance_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='财务表';

#创建管理表 
CREATE TABLE admin_tb(
admin_id int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
phone varchar(255) COMMENT '电话',
email varchar(255) COMMENT 'email',
password varchar(255) COMMENT '密码',
identity_cards varchar(255) COMMENT '身份证',
qq varchar(255) COMMENT 'QQ',
wechat varchar(255) COMMENT '微信号',
create_date datetime COMMENT '更新时间',
login_date datetime COMMENT '登陆时间',
role_id int(11) COMMENT '角色id外键',
PRIMARY KEY (admin_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='管理表';

#创建新闻表 
CREATE TABLE news_tb(
news_id int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
type varchar(255) COMMENT '类别',
title varchar(255) COMMENT '标题',
time timestamp COMMENT '时间',
fixed_recommend tinyint(4) COMMENT '是否置顶 默认否',
is_recommend tinyint(4) COMMENT '是否推荐 默认否',
img_address varchar(255) COMMENT '图片地址',
content longtext COMMENT '文章内容',
status varchar(255) COMMENT '状态',
admin_id int(11) COMMENT '管理员id外键',
PRIMARY KEY (news_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='新闻表';

#创建新闻图片表 
CREATE TABLE img_tb(
img_id int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻图片id',
img_address varchar(255) COMMENT '新闻图地址',
number int(11) COMMENT '图片顺序',
news_id int(11) COMMENT '新闻id,外键',
PRIMARY KEY (img_id),
CONSTRAINT FK_NEWS_IMG FOREIGN KEY (news_id) REFERENCES news_tb (news_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

#创建单个新闻金额表 
CREATE TABLE news_price_tb(
news_price_id int(11) NOT NULL AUTO_INCREMENT COMMENT '单个新闻金额id',
unit_price decimal(11,2) COMMENT '广告主单价',
total_price decimal(11,2) COMMENT '广告主总价',
charging_mode  tinyint(4) COMMENT '计费模式',
now_total_price decimal(11,2) COMMENT '消耗金额',
news_id int(11) COMMENT '新闻id,外键',
PRIMARY KEY (price_id),
CONSTRAINT FK_NEWS_PRICE FOREIGN KEY (news_id) REFERENCES news_tb (news_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='单个新闻金额表';

#创建新闻数据表 
CREATE TABLE data_tb(
data_id int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻数据id',
reading_number int(11) COMMENT '阅读数',
circle_number  int(11) COMMENT '朋友圈转发数',
user_number  int(11) COMMENT '分享给朋友数',
news_id int(11) COMMENT '新闻id,外键',
PRIMARY KEY (img_id),
CONSTRAINT FK_NEWS_DATA FOREIGN KEY (news_id) REFERENCES news_tb (news_id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

#创建广告位表
CREATE TABLE adsense_tb(
adsense_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告位id',
type tinyint(11) COMMENT '广告位类型',
location tinyint(11) COMMENT '广告位位置',
content longtext COMMENT '广告位代码',
PRIMARY KEY (adsense_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告位表';