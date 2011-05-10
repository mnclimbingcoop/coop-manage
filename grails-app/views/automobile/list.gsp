
<%@ page import="coop.mnclimbing.Automobile" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'automobile.label', default: 'Automobile')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1>MN Climbing Coop - Car List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="person" title="Owner" />
                        
                        	<th>Phone #</th>
                        
                        	<th>email</th>

                            <g:sortableColumn property="licencePlateNumber" title="${message(code: 'automobile.licencePlateNumber.label', default: 'Plate #')}" />
                        
                            <g:sortableColumn property="licencePlateState" title="${message(code: 'automobile.licencePlateState.label', default: 'State')}" />
                        
                            <g:sortableColumn property="year" title="${message(code: 'automobile.make.label', default: 'Year')}" />

                            <g:sortableColumn property="make" title="${message(code: 'automobile.make.label', default: 'Make')}" />
                        
                            <g:sortableColumn property="model" title="${message(code: 'automobile.model.label', default: 'Model')}" />
                        
                            <g:sortableColumn property="color" title="${message(code: 'automobile.color.label', default: 'Color')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${automobileInstanceList}" status="i" var="automobileInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link controller="person" action="show" id="${automobileInstance.person.id}" target="tab-cars">${fieldValue(bean: automobileInstance, field: "person")}</g:link></td>
                            
                            <td>${automobileInstance.person?.phoneNumber}</td>
                            
                            <td>${automobileInstance.person?.emailAddress}</td>
                        
                            <td>${fieldValue(bean: automobileInstance, field: "licencePlateNumber")}</td>
                        
                            <td>${fieldValue(bean: automobileInstance, field: "licencePlateState")}</td>
                        
                            <td>${automobileInstance.year}</td>

                            <td>${fieldValue(bean: automobileInstance, field: "make")}</td>
                        
                            <td>${fieldValue(bean: automobileInstance, field: "model")}</td>
                        
                            <td>${fieldValue(bean: automobileInstance, field: "color")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
