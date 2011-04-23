
<%@ page import="coop.mnclimbing.PurchaseItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchaseItem.label', default: 'PurchaseItem')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'purchaseItem.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'purchaseItem.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'purchaseItem.amount.label', default: 'Amount')}" />
                        
                            <th><g:message code="purchaseItem.purchase.label" default="Purchase" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${purchaseItemInstanceList}" status="i" var="purchaseItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${purchaseItemInstance.id}">${fieldValue(bean: purchaseItemInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: purchaseItemInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: purchaseItemInstance, field: "amount")}</td>
                        
                            <td>${fieldValue(bean: purchaseItemInstance, field: "purchase")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${purchaseItemInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
