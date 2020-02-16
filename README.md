## Java学习社区

## 资料
[Spring 文档](https://spring.io/guides)
[Spring Web文档](https://spring.io/guides/gs/serving-web-content/)
[项目地址](https://github.com/LXJLXJLL/community)
[elasticsearch社区](https://elasticsearch.cn/explore)
[Github_deplot_key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[BootStrap](https://v3.bootcss.com/getting-started/)
[Github_OAuth_Document](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
[Spring Web MVC文档](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-introduction)

## 工具
[Git](https://git-scm.com/download)
[Visual_Paradigm](https://www.visual-paradigm.com)
[FlyWay](https://flywaydb.org/getstarted/)
[LomBok](https://projectlombok.org/)

## 脚本
``` sql
create table if not exists user
(
	id int auto_increment primary key,
	account_id varchar(100),
	name varchar(50),
	token char(36),
	gmt_create bigint,
	gmt_modified bigint
);
```

```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```