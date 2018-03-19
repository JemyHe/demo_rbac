create table demo_rbac_module
(
	id bigint(4) not null auto_increment
		primary key,
	name varchar(32) not null,
	pid bigint(4) not null,
	code varchar(64) null
)
;

create table demo_rbac_order
(
	id bigint(4) not null auto_increment
		primary key,
	customer_id bigint(4) not null,
	org_id bigint(4) not null,
	total bigint(10) not null
)
;

create table demo_rbac_org
(
	id bigint(4) not null auto_increment
		primary key,
	name varchar(32) not null,
	pid bigint(4) not null
)
;

create table demo_rbac_role
(
	id bigint(4) not null auto_increment
		primary key,
	name varchar(32) not null
)
;

create table demo_rbac_role_module
(
	role_id bigint(4) not null,
	module_id bigint(4) not null,
	primary key (role_id, module_id)
)
;

create table demo_rbac_user
(
	id bigint(4) not null auto_increment
		primary key,
	username varchar(32) not null,
	password varchar(40) not null,
	org_id bigint(4) not null,
	org_ids varchar(512) null
)
;

create table demo_rbac_user_role
(
	user_id bigint(4) not null,
	role_id bigint(4) not null,
	primary key (user_id, role_id)
)
;

