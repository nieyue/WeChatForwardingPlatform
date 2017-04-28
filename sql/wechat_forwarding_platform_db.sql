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

#创建管理表 
CREATE TABLE manager_tb(
manager_id int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
name varchar(255) COMMENT '名称',
manager_phone varchar(255) COMMENT '电话',
email varchar(255) COMMENT 'email',
manager_password varchar(255) COMMENT '密码',
create_date datetime COMMENT '创建时间',
last_login_date datetime COMMENT '最后登陆时间',
role_id int(11) COMMENT '角色id外键',
PRIMARY KEY (manager_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='管理表';

#创建新闻表 
CREATE TABLE news_tb(
news_id int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
type varchar(255) COMMENT '类别',
title varchar(255) COMMENT '标题',
time timestamp COMMENT '时间',
fixed_recommend tinyint(4) DEFAULT 0  COMMENT '是否置顶 默认否',
is_recommend tinyint(4) DEFAULT 0 COMMENT '是否推荐 默认否',
img_address varchar(255) COMMENT '图片地址',
content longtext COMMENT '文章内容',
unit_price decimal(11,2) DEFAULT 0 COMMENT '广告主单价',
total_price decimal(11,2) DEFAULT 0 COMMENT '广告主总价',
reading_number int(11) DEFAULT 0 COMMENT '阅读数',
now_total_price decimal(11,2) DEFAULT 0 COMMENT '消耗金额',
unit_profit decimal(11,2) DEFAULT 0 COMMENT '渠道主单价',
now_total_profit decimal(11,2) DEFAULT 0 COMMENT '渠道主收益',
scale decimal(11,2) DEFAULT 0 COMMENT '扣量比例',
pvs bigint(20) DEFAULT 0 COMMENT 'pvs',
uvs bigint(20) DEFAULT 0 COMMENT 'uvs',
ips bigint(20) DEFAULT 0 COMMENT 'ips',
status varchar(255) COMMENT '状态',
manager_id int(11) COMMENT '管理员id外键',
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

#创建数据表 
CREATE TABLE data_tb(
data_id int(11) NOT NULL AUTO_INCREMENT COMMENT '数据id',
pvs bigint(20) DEFAULT 0 COMMENT 'pvs',
uvs bigint(20) DEFAULT 0 COMMENT 'uvs',
ips bigint(20) DEFAULT 0 COMMENT 'ips',
create_date date COMMENT '创建时间',
news_id int(11) COMMENT '新闻id,外键',
PRIMARY KEY (data_id),
CONSTRAINT FK_NEWS_DATA FOREIGN KEY (news_id) REFERENCES news_tb (news_id) ON DELETE CASCADE ON UPDATE CASCADE,
UNIQUE INDEX idx_data_newsid_create_date (create_date,news_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='数据表';

#创建广告位表
CREATE TABLE adsense_tb(
adsense_id int(11) NOT NULL AUTO_INCREMENT COMMENT '广告位id',
type tinyint(11) COMMENT '广告位类型',
content longtext COMMENT '广告位代码',
PRIMARY KEY (adsense_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='广告位表';

#创建域名表
CREATE TABLE domain_tb(
domain_id int(11) NOT NULL AUTO_INCREMENT COMMENT '域名id',
name varchar(255) COMMENT '域名名称',
status varchar(255) COMMENT '状态',
create_date datetime COMMENT '创建时间',
stop_date datetime COMMENT '停用时间',
PRIMARY KEY (domain_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='域名表';


#设置初始角色
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("超级管理员","超级管理员",now());
INSERT IGNORE INTO role_tb (name,duty,update_date) 
VALUES ("广告主","广告主",now()); 
#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO manager_tb (name,manager_phone,manager_password,create_date,last_login_date,role_id) 
VALUES ("聂跃","15111336587","11874bb6149dd45428da628c9766b252",now(),now(),"1000");  
 