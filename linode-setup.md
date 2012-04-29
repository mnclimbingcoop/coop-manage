Setting up MN Climbing Coop Management Server
=============================================

System Setup
------------

Initial Login and account setup
	ssh root@ip.address
	groupadd admin
	useradd -c "Aaron J. Zirbes" -G admin,adm -m -s /bin/bash ajz
	passwd ajz
	exit

Initial Login using normal account
	ssh ajz@ip.address

Install Updates and Patches
	sudo apt-get update
	sudo apt-get dist-upgrade
	sudo apt-get purge
	reboot

Install Required Software
	sudo apt-get install openjdk-6-jdk apache2 libapache2-mod-jk tomcat6 tomcat6-admin libmysql-java ufw libtcnative-1 groovy mysql-server git-core python-software-properties

Configure Certificates
----------------------

SCP/SFTP the SSL key and cert to the server

	scp manage.mnclimbingcoop.com.* manage.mnclimbingcoop.com:

copy the certificates to the ssl folder

	sudo cp manage.mnclimbingcoop.com.crt /etc/ssl/certs/
	sudo cp manage.mnclimbingcoop.com.key /etc/ssl/private/

set the permissions and ownership for the private key

	sudo adduser www-data ssl-cert
	sudo chmod 0640 /etc/ssl/private/manage.mnclimbingcoop.com.key
	sudo chgrp ssl-cert /etc/ssl/private/manage.mnclimbingcoop.com.key

Configure Tomcat
----------------

Update Mod-JK Settings
	sudo sed -i -e 's/tomcat5/tomcat6/g' /etc/libapache2-mod-jk/workers.properties
	sudo sed -i -e 's/java-gcj/java-6-openjdk/g' /etc/libapache2-mod-jk/workers.properties

Disable mod_jk (temporarily)

	sudo a2dismod jk

Change the following settings in /etc/default/tomcat6

	TOMCAT6_SECURITY=no
	JAVA_OPTS="-Djava.awt.headless=true -Xms128m -Xmx128m -XX:PermSize=64m -XX:MaxPermSize=64m"
	JAVA_OPTS="$JAVA_OPTS -XX:+UseConcMarkSweepGC"

Enable the connector in /etc/tomcat6/server.xml

	<Connector port="8009" protocol="AJP/1.3" redirectPort="443" />

Let tomcat see the mysql driver

	sudo ln -s /usr/share/java/mysql.jar /usr/share/tomcat6/lib/mysql.jar

Add yourself to the tomcat managers file /etc/tomcat6/tomcat-users.xml

	<tomcat-users>
	  <role rolename="admin"/>
	  <role rolename="manager"/>
	  <user username="ajz" password="SecretPassword" roles="admin,manager"/>
	</tomcat-users>

Create the file /etc/apache2/sites-available/tomcat6-ssl

	<VirtualHost _default_:80>
	  ServerAdmin webmaster@mnclimbingcoop.com
	  Redirect permanent / https://manage.mnclimbingcoop.com/
	</VirtualHost>

	<VirtualHost _default_:443>
	  ServerAdmin webmaster@mnclimbingcoop.com

	  DocumentRoot /var/www
	  <Directory />
		Options FollowSymLinks
		AllowOverride None
	  </Directory>
	  <Directory /var/www/>
		Options Indexes FollowSymLinks MultiViews
		AllowOverride None
		Order allow,deny
		allow from all
	  </Directory>

	  # Assign specific URLs to Tomcat. In general the structure of a 
	  # JkMount directive is: JkMount [URL prefix] [Worker name]

	  # send all requests ajp13_worker
	  JkMount /* ajp13_worker

	  ErrorLog /var/log/apache2/error.log

	  # Possible values include: debug, info, notice, warn, error, crit,
	  # alert, emerg.
	  LogLevel warn

	  CustomLog /var/log/apache2/ssl_access.log combined

	  #   SSL Engine Switch:
	  #   Enable/Disable SSL for this virtual host.
	  SSLEngine On

	  SSLCertificateFile      /etc/ssl/certs/manage.mnclimbingcoop.com.crt
	  SSLCertificateKeyFile   /etc/ssl/private/manage.mnclimbingcoop.com.key

	  # MSIE Fixes
	  BrowserMatch "MSIE [2-6]" \
		nokeepalive ssl-unclean-shutdown \
		downgrade-1.0 force-response-1.0
	  # MSIE 7 and newer should be able to use keepalive
	  BrowserMatch "MSIE [17-9]" ssl-unclean-shutdown

	</VirtualHost>

Configure Apache
----------------

	sudo a2enmod ssl
	sudo a2enmod jk
	sudo a2dissite 000-default
	sudo a2ensite tomcat6-ssl

Restart the core services

	sudo service apache2 restart
	sudo service tomcat6 restart
	sudo service mysql restart

Configure the firewall
----------------------

	sudo ufw allow proto tcp from any to any port 22
	sudo ufw allow proto tcp from any to any port 80
	sudo ufw allow proto tcp from any to any port 443
	sudo ufw enable

Configure MySQL
---------------

Login to the MySQL server as root

	mysql -u root -p

Run the production create database commands listed at the top of
the grails-app/conf/DataSource.groovy file.

Install Grails
--------------

	sudo add-apt-repository ppa:groovy-dev/grails
	sudo apt-get update
	sudo apt-get install grails-1.3.7

	#switch between versions
	sudo update-alternatives --config grails

Choose Grails 1.3.7

Deploy the application
----------------------

Clone the git repository from github

	git clone git://github.com/mnclimbingcoop/coop-manage.git

Build the application from Grails using the following command

	cd coop-manage
	git checkout grails13
	grails clean
	grails compile
	grails war mncc.war

Copy the WAR file to the tomcat application server folder

	sudo cp mncc.war /var/lib/tomcat6/webapps/

MySQL Backups
-------------


