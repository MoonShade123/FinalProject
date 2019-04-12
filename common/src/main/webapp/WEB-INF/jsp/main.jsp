<?xml version="1.0" encoding="UTF-8"?>

-<web-app version="4.0" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee">


-<welcome-file-list>

<welcome-file>index.html</welcome-file>

<welcome-file>index.htm</welcome-file>

<welcome-file>index.jsp</welcome-file>

<welcome-file>default.html</welcome-file>

<welcome-file>default.htm</welcome-file>

<welcome-file>default.jsp</welcome-file>

</welcome-file-list>


-<servlet>

<servlet-name>FrontController</servlet-name>

<servlet-class>com.htp.controller.FrontController</servlet-class>

</servlet>


-<servlet-mapping>

<servlet-name>FrontController</servlet-name>

<url-pattern>/FrontController</url-pattern>

</servlet-mapping>


-<listener>

<listener-class>com.htp.listener.ConnectPoolListener </listener-class>

</listener>

</web-app>