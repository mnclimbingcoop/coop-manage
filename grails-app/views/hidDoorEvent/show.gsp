
<%@ page import="coop.mnclimbing.HidDoorEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'hidDoorEvent.label', default: 'HID Door Event')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="hidDoorEvent.uploadDate.label" default="Upload Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${hidDoorEventInstance?.uploadDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="hidDoorEvent.ipAddress.label" default="Uploaded from IP Address" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: hidDoorEventInstance, field: "ipAddress")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="hidDoorEvent.doorName.label" default="Door Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: hidDoorEventInstance, field: "doorName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="hidDoorEvent.eventCode.label" default="Event Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: hidDoorEventInstance, field: "eventCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="hidDoorEvent.eventDate.label" default="Event Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${hidDoorEventInstance?.eventDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="hidDoorEvent.eventSubject.label" default="Event Subject" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: hidDoorEventInstance, field: "eventSubject")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="hidDoorEvent.imported.label" default="Imported" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${hidDoorEventInstance?.imported}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
