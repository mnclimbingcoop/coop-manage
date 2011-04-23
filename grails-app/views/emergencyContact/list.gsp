
<%@ page import="coop.mnclimbing.EmergencyContact" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'emergencyContact.label', default: 'EmergencyContact')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'emergencyContact.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="title" title="${message(code: 'emergencyContact.title.label', default: 'Title')}" />
                        
                            <g:sortableColumn property="firstName" title="${message(code: 'emergencyContact.firstName.label', default: 'First Name')}" />
                        
                            <g:sortableColumn property="middleName" title="${message(code: 'emergencyContact.middleName.label', default: 'Middle Name')}" />
                        
                            <g:sortableColumn property="lastName" title="${message(code: 'emergencyContact.lastName.label', default: 'Last Name')}" />
                        
                            <g:sortableColumn property="suffix" title="${message(code: 'emergencyContact.suffix.label', default: 'Suffix')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${emergencyContactInstanceList}" status="i" var="emergencyContactInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${emergencyContactInstance.id}">${fieldValue(bean: emergencyContactInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: emergencyContactInstance, field: "title")}</td>
                        
                            <td>${fieldValue(bean: emergencyContactInstance, field: "firstName")}</td>
                        
                            <td>${fieldValue(bean: emergencyContactInstance, field: "middleName")}</td>
                        
                            <td>${fieldValue(bean: emergencyContactInstance, field: "lastName")}</td>
                        
                            <td>${fieldValue(bean: emergencyContactInstance, field: "suffix")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${emergencyContactInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
