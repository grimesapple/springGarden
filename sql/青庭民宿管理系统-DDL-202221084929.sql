DROP TABLE IF EXISTS `orderT`;
CREATE TABLE `orderT`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `p_id` INT    COMMENT '客房id' ,
    `u_id` INT    COMMENT '用户账号' ,
    `order_code` VARCHAR(255)    COMMENT '订单编码' ,
    `number` INT    COMMENT '入住人数' ,
    `r_id` VARCHAR(255)    COMMENT '入住信息id' ,
    `user_message` VARCHAR(255)    COMMENT '用户备注' ,
    `start_time` DATETIME    COMMENT '入住时间' ,
    `end_time` DATETIME    COMMENT '离开时间' ,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间' ,
    `pay_date` DATETIME    COMMENT '支付时间' ,
    `delivery_date` DATETIME    COMMENT '提交时间' ,
    `confirm_date` DATETIME    COMMENT '批准时间' ,
    `status` INT   DEFAULT 1 COMMENT '状态：0离店、1预定、2已入住' ,
    `total` DECIMAL(24,2)    COMMENT '价格总数' ,
    PRIMARY KEY (id)
)  COMMENT = '订单表';

DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `o_id` INT    COMMENT '订单id' ,
    `receiver` VARCHAR(255)    COMMENT '住户姓名' ,
    `cardID` VARCHAR(255)    COMMENT '身份证号码' ,
    `mobile` VARCHAR(255)    COMMENT '联系方式' ,
    PRIMARY KEY (id)
)  COMMENT = '订单详情-入住人信息';

DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `content` VARCHAR(255)    COMMENT '评论内容' ,
    `reply` VARCHAR(255)    COMMENT '商家回复' ,
    `u_id` INT    COMMENT '用户id' ,
    `p_id` INT    COMMENT '评论的产品' ,
    `create_time` DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    PRIMARY KEY (id)
)  COMMENT = '评论表';

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键;' ,
    `type_name` VARCHAR(255)    COMMENT '类型' ,
    PRIMARY KEY (id)
)  COMMENT = '房间类型';

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `name` VARCHAR(255)    COMMENT '房间名称' ,
    `sub_title` VARCHAR(255)    COMMENT '标题' ,
    `orignal_price` DECIMAL(24,2)    COMMENT '原价格' ,
    `promote_price` DECIMAL(24,2)    COMMENT '促销价格' ,
    `stock` INT    COMMENT '库存' ,
    `c_id` INT    COMMENT '房间类型' ,
    `create_time` DATETIME   DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    PRIMARY KEY (id)
)  COMMENT = '客房表;商品信息相关表：分类表，商品图片表，商品规格表，商品参数表';

DROP TABLE IF EXISTS `productimage`;
CREATE TABLE `productimage`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键;' ,
    `p_id` INT    COMMENT '客房id;' ,
    `url` VARCHAR(255)    COMMENT '图片链接;' ,
    PRIMARY KEY (id)
)  COMMENT = '客房图片';

DROP TABLE IF EXISTS `property`;
CREATE TABLE `property`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `c_id` INT    COMMENT '房间类型id' ,
    `name` VARCHAR(255)    COMMENT '属性' ,
    PRIMARY KEY (id)
)  COMMENT = '房间属性字典';

DROP TABLE IF EXISTS `propertyvalue`;
CREATE TABLE `propertyvalue`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `p_id` INT    COMMENT '房间id' ,
    `pt_id` INT    COMMENT '房间属性id' ,
    `status` INT    COMMENT '对应的属性情况：1正常、0损坏' ,
    PRIMARY KEY (id)
)  COMMENT = '房间属性情况';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`(
    `id` INT NOT NULL   COMMENT '主键' ,
    `type` INT    COMMENT '角色类别' ,
    `name` VARCHAR(255)    COMMENT '权限名称' ,
    PRIMARY KEY (id)
)  COMMENT = '角色表';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    `id` INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    `name` VARCHAR(255)    COMMENT '用户账号' ,
    `password` VARCHAR(255) NOT NULL   COMMENT '用户密码' ,
    `r_id` INT    COMMENT '权限表id' ,
    `realname` VARCHAR(255)    COMMENT '真实姓名' ,
    `telphone` VARCHAR(255)    COMMENT '手机号码' ,
    `eamil` VARCHAR(255)    COMMENT '邮箱' ,
    `cardID` VARCHAR(255)    COMMENT '身份证号码' ,
    PRIMARY KEY (id)
)  COMMENT = '用户表';

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`(
    `TENANT_ID` INT    COMMENT '租户号' ,
    `REVISION` INT    COMMENT '乐观锁' ,
    `CREATED_BY` INT    COMMENT '创建人' ,
    `CREATED_TIME` DATETIME    COMMENT '创建时间' ,
    `UPDATED_BY` INT    COMMENT '更新人' ,
    `UPDATED_TIME` DATETIME    COMMENT '更新时间' 
)  COMMENT = '权限表';

