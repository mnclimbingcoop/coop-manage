
<%@ page import="coop.mnclimbing.AccessCardAssignment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'accessCardAssignment.id.label', default: 'Id')}" />
                        
                            <th><g:message code="accessCardAssignment.accessCard.label" default="Access Card" /></th>
                        
                            <th><g:message code="accessCardAssignment.person.label" default="Person" /></th>
                        
                            <g:sortableColumn property="issueDate" title="${message(code: 'accessCardAssignment.issueDate.label', default: 'Issue Date')}" />
                        
                            <g:sortableColumn property="returnDate" title="${message(code: 'accessCardAssignment.returnDate.label', default: 'Return Date')}" />
                        
                            <g:sortableColumn property="lost" title="${message(code: 'accessCardAssignment.lost.label', default: 'Lost')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accessCardAssignmentInstanceList}" status="i" var="accessCardAssignmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${accessCardAssignmentInstance.id}">${fieldValue(bean: accessCardAssignmentInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: accessCardAssignmentInstance, field: "accessCard")}</td>
                        
                            <td>${fieldValue(bean: accessCardAssignmentInstance, field: "person")}</td>
                        
                            <td><g:formatDate date="${accessCardAssignmentInstance.issueDate}" /></td>
                        
                            <td><g:formatDate date="${accessCardAssignmentInstance.returnDate}" /></td>
                        
                            <td><g:formatBoolean boolean="${accessCardAssignmentInstance.lost}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${accessCardAssignmentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
