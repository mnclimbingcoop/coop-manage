/*
 * Run the following commands in MySQL to setup the connection for development
 *
 
 CREATE DATABASE mncc_devel;
 
 CREATE USER 'mncc-grails'@'localhost' IDENTIFIED BY 'mncc2maSqefoboeJSVudpbeDdqFx';
 GRANT ALL ON mncc_devel.* TO 'mncc-grails'@'localhost';
 FLUSH PRIVILEGES;

 * Run the following on the production server to set up the MySQL databases

 CREATE DATABASE mncc;
 CREATE DATABASE mncc_test;
 CREATE USER 'mncc-grails'@'localhost' IDENTIFIED BY 'mncc2maSqefoboeJSVudpbeDdqFx';
 GRANT ALL ON mncc.* TO 'mncc-grails'@'localhost';
 GRANT ALL ON mncc_test.* TO 'mncc-grails'@'localhost';
 FLUSH PRIVILEGES;

 *
 */
dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
	dialect = org.hibernate.dialect.MySQL5InnoDBDialect
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
