<%@ page import="coop.mnclimbing.DayPass" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dayPass.label', default: 'DayPass')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Day Passes</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="name" title="${message(code: 'dayPass.amount.label', default: 'Name')}" />
                        
                            <th>Sponsor</th>
                        
                            <g:sortableColumn property="paymentDate" title="${message(code: 'dayPass.paymentDate.label', default: 'Payment Date')}" />
                                                
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dayPassInstanceList}" status="i" var="dayPassInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean: dayPassInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: dayPassInstance, field: "sponsor")}</td>
                        
                            <td><g:formatDate date="${dayPassInstance.paymentDate}" format="MM/dd/yyyy" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
