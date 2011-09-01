
<%@ page import="coop.mnclimbing.HidDoorEvent" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'hidDoorEvent.label', default: 'HID Door Event')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="doorName" title="${message(code: 'hidDoorEvent.doorName.label', default: 'Door')}" />
                        
                            <g:sortableColumn property="eventCode" title="${message(code: 'hidDoorEvent.eventCode.label', default: 'HID Code')}" />
                        
                            <g:sortableColumn property="eventDate" title="${message(code: 'hidDoorEvent.eventDate.label', default: 'Event Date')}" />
                        
                            <g:sortableColumn property="eventSubject" title="${message(code: 'hidDoorEvent.eventSubject.label', default: 'Subject')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${hidDoorEventInstanceList}" status="i" var="hidDoorEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${hidDoorEventInstance.id}">
                            	${fieldValue(bean: hidDoorEventInstance, field: "doorName")}
                            </g:link></td>
                        
                            <td><g:link action="show" id="${hidDoorEventInstance.id}">
                            	${hidDoorEventInstance.eventCode}
                            </g:link></td>

                            <td><g:link action="show" id="${hidDoorEventInstance.id}">
                            	<g:formatDate date="${hidDoorEventInstance.eventDate}" />
                            </g:link></td>

                            <td><g:link action="show" id="${hidDoorEventInstance.id}">
                            	${fieldValue(bean: hidDoorEventInstance, field: "eventSubject")}
                            </g:link></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${hidDoorEventInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
