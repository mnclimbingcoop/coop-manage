
<%@ page import="coop.mnclimbing.Automobile" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <g:set var="entityName" value="${message(code: 'automobile.label', default: 'Automobile')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style>
        	body {
        		font-size: 0.8em;
        	}
        	table tr th {
        		border: thin black solid;
        	}
        	table {
        		font-size: 0.8em;
        	}
        	h1 {
        		margin-bottom: 0.1em;
        	}
        	h2 {
        		margin-top: 0.1;
        		margin-bottom: 0.1;
        	}
        </style>
    </head>
    <body>
		<img src="${resource(dir:'images',file:'BOHM.png')}" alt="BOHM" />
        <div class="body">
            <h1>Minnesota Climbing Cooperative</h1>
            <h2>Thorp Building - Suite 178 - Car List</h2>
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
