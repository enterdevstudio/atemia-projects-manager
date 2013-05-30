Atemia projects manager
=======================

This is a JEE application for managing projects and human resources on a planning

The project is built by [Xinta](http://xinta.fr/), a small web agency which loves
Free Softwares, and funded by [Atemia](http://atemia.org/), a company which works
to protect the environment.

We hope you'll enjoy it :)

Copyright 2013 Xinta ©

    AGPL

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
## How to deploy

### Database

Atemia was developed using MySQL. You can use the database of your choice, but if you change, add the needed driver in the Maven dependencies.
Connect to your database and create a database and a user:

    CREATE DATABASE atemia;
    CREATE USER "atemia"@"localhost";
    SET password FOR "atemia"@"localhost" = password('yourpassword');
    GRANT ALL ON atemia.* TO "atemia"@"localhost";
    
### Server

This application was tested on the GlassFish server. You can use another server if you want but it needs to support servlet and EJB.
To have the lastest version of Glassfish, go on https://glassfish.java.net/ and download the zip file.

Inside this zip file, you'll find a /bin/ directory which contains the "asadmin" script.
If you have a graphic access, you'll probably prefer administrate from localhost:4848

You can start and stop default domain with

    asadmin start-domain
    asadmin stop-domain

We need to create a datasource to allow the application to communicate with the DB.
First, let's create a pool:

    asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property user=atemia:password=yourpassword:DatabaseName=atemia:ServerName=localhost:port=3306 atemia-pool
    
Then, let's create the resource:

    asadmin create-jdbc-resource --connectionpoolid atemia-pool jdbc/atemia
    
Run ping-connection-pool to be sure the connection pool can connect to the database

    asadmin ping-connection-pool atemia-pool
    
Now, let's deploy the application:

    asadmin deploy path-to-the-war.war
    
The application should now be accessible on localhost:8080
