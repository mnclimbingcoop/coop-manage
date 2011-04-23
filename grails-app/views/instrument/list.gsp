
<%@ page import="coop.mnclimbing.Instrument" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'instrument.label', default: 'Instrument')}" />
        <title>Coop Forms</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create" params="${ [ currentVersion: 1 ] }"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Coop Forms</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="abbreviation" title="${message(code: 'instrument.abbreviation.label', default: 'Abbreviation')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'instrument.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="currentVersion" title="${message(code: 'instrument.currentVersion.label', default: 'Version')}" />
                        	
                        	<g:sortableColumn property="versionDate" title="${message(code: 'instrument.versionDate.label', default: 'Date')}" />
                        	
                        	<th>Obsolete?</th>

                        	<td>New Version, Same Doc</td>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${instrumentInstanceList}" status="i" var="instrumentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${instrumentInstance.id}">${fieldValue(bean: instrumentInstance, field: "abbreviation")}</g:link></td>
                        
                            <td><g:link action="show" id="${instrumentInstance.id}">${fieldValue(bean: instrumentInstance, field: "name")}</g:link></td>
                        
                            <td>${fieldValue(bean: instrumentInstance, field: "currentVersion")}</td>
                            
                            <td><g:formatDate date="${instrumentInstance.versionDate}" format="M/d/yyyy" /></td>
                            
                            <td><g:formatBoolean boolean="${instrumentInstance.obsolete}" /></td>

                        	<td>
                        		<g:link class="create" action="create" params="${ [ abbreviation:instrumentInstance.abbreviation, name:instrumentInstance.name, currentVersion: (instrumentInstance.currentVersion + 1) ] }">
                        		New Version</g:link>
                        	</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${instrumentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
