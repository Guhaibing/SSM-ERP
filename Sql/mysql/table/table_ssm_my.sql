use ssm_flowerstore;

-- begin 表 ssm_sys_config 全量脚本
create table if not exists `ssm_sys_config` (
    `sys_name` int not null default 0 comment '系统名称',
    `config_no` int not null default 0 comment '配置编号',
    `config_name` varchar(64) not null default '' comment '配置名称',
    `config_type` char(1) not null default ' ' comment '配置类型',
    `en_sys_str` varchar(100) not null default '' comment '允许使用的系统（以逗号隔开）',
    `data_type` char(1) not null default ' ' comment '数据类型',
    `char_config` char(1) not null default ' ' comment '字符值',
    `int_config` int not null default 0 comment '整数值',
    `str_config` varchar(255) not null default '' comment '字符串值',
    `remark` varchar(2000) not null default '' comment '备注',
unique index `uk_sysconfig` (`config_no`)
)comment = '系统配置表';
commit;
-- end   表 ssm_sys_config 全量脚本

-- begin 表 ssm_function 全量脚本
create table if not exists `ssm_function` (
    `function_str` varchar(64) not null default '' comment '系统功能号',
    `function_name` varchar(64) not null default '' comment '功能名称',
    `func_busi_type` char(1) not null default ' ' comment '功能业务类别',
    `en_sys_status` varchar(16) not null default '' comment '允许系统状态',
    `right_type` char(1) not null default ' ' comment '权限类别',
unique index `uk_ssmfunction` (`function_str`)
)comment = '系统功能表';
commit;
-- end   表 ssm_function 全量脚本


-- begin 表 ssm_arg 全量脚本
create table if not exists `ssm_arg` (
	`sys_name` varchar(32) not null default '' comment '系统名称',
	`init_date` int not null default 0 comment '系统日期',
	`sys_status` char(1) not null default ' ' comment '系统状态',
	`update_date` int not null default 0 comment '记录更新日期',
	`update_time` int not null default 0 comment '最后冻结更新时间',
unique index `uk_arg` (`sys_name`)
)comment = '系统参数表' ;
commit;
-- end   表 ssm_arg 全量脚本

-- begin 表 ssm_user 全量脚本
create table if not exists `ssm_user` (
	`user_id` varchar(18) unique not null default '' comment '客户编号',
	`user_name` varchar(18) unique not null default '' comment '客户名字',
	`user_type` char(1) not null default ' ' comment '客户类别',
	`user_status` char(1) not null default ' ' comment '用户状态',
	`user_pwd` varchar(64) not null default '' comment '认证密码',
	`last_update_date` int not null default 0 comment '上次更新日期',
	`last_update_time` int not null default 0 comment '上次更新时间',
	`remark` varchar(255) not null default '' comment '备注',
	`login_flag` char(1) not null default ' ' comment '登录标志',
unique index `uk_user` (`user_id`)
)comment = '认证用户表';
commit;
-- end   表 ssm_user 全量脚本

-- begin 表 ssm_good 全量脚本
create table if not exists `ssm_good` (
	`good_id` varchar(18) unique not null default '' comment '商品编号',
	`good_name` varchar(18) not null default '' comment '商品名称',
	`good_type` char(1) not null default ' ' comment '商品类别',
	`good_price` decimal not null default 0 comment '商品价格',
	`good_stock` int not null default 0 comment '商品库存',
	`good_status` char(1) not null default ' ' comment '商品状态',
	`remark` varchar(255) not null default '' comment '商品信息',
unique index `uk_good` (`good_id`)
)comment = '商品信息表';
commit;
-- end   表 ssm_good 全量脚本

-- begin 表 ssm_order 全量脚本
create table if not exists `ssm_order` (
    `order_id` varchar(18) unique not null default '' comment '订单编号',
	`user_id` varchar(18) not null default '' comment '客户编号',
	`good_id` varchar(18) not null default '' comment '商品编号',
	`good_name` varchar(18) not null default '' comment '商品名称',
	`good_price` decimal not null default 0 comment '商品价格',
	`good_nums` int not null default 0 comment '商品数量',
	`good_amount` decimal not null default 0 comment '商品金额',
	`bus_date` int not null default 0 comment '订单交易日期',
	`bus_time` int not null default 0 comment '订单交易时间',
	`remark` varchar(255) not null default '' comment '订单备注信息',
unique index `uk_order` (`order_id`),
index `idx_order_user` (`user_id`),
index `idx_order_good` (`good_id`),
index `idx_date` (`bus_date`)
)comment = '订单信息表';
commit;
-- end   表 ssm_order 全量脚本