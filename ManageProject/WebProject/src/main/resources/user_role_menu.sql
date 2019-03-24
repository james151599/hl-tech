CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_code` varchar(45) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `password_salt` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_code_UNIQUE` (`login_code`)
);

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
);

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `role_sort` int(11) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
);

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
);

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(45) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `menu_sort` int(11) DEFAULT NULL,
  `menu_type` char(1) NOT NULL,
  `menu_href` varchar(45) DEFAULT NULL,
  `permission` varchar(45) DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_name_UNIQUE` (`menu_name`)
);