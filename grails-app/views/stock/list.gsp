
<%@ page import="coop.mnclimbing.Stock" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<h2>Total Outstanding Stock: &#36;${stockTotal}</h2>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'stock.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'stock.amount.label', default: 'Amount')}" />
                        
                            <g:sortableColumn property="paymentDate" title="${message(code: 'stock.paymentDate.label', default: 'Payment Date')}" />
                        
                            <th><g:message code="stock.classOfStock.label" default="Class Of Stock" /></th>
                        
                            <th><g:message code="stock.paymentType.label" default="Payment Type" /></th>
                        
                            <th><g:message code="stock.person.label" default="Person" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${stockInstanceList}" status="i" var="stockInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${stockInstance.id}">${fieldValue(bean: stockInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: stockInstance, field: "amount")}</td>
                        
                            <td><g:formatDate date="${stockInstance.paymentDate}" format="yyyy-MM-dd" /></td>
                        
                            <td>${fieldValue(bean: stockInstance, field: "classOfStock")}</td>
                        
                            <td>${fieldValue(bean: stockInstance, field: "paymentType")}</td>
                        
                            <td>${fieldValue(bean: stockInstance, field: "person")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${stockInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
