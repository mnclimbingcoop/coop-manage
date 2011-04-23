
<%@ page import="coop.mnclimbing.Purchase" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'purchase.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="purchaseDate" title="${message(code: 'purchase.purchaseDate.label', default: 'Purchase Date')}" />
                        
                            <g:sortableColumn property="merchant" title="${message(code: 'purchase.merchant.label', default: 'Merchant')}" />
                        
                            <th><g:message code="purchase.person.label" default="Purchaser" /></th>
                        
                            <g:sortableColumn property="total" title="${message(code: 'purchase.shipping.label', default: 'Total')}" />

                            <g:sortableColumn property="paid" title="${message(code: 'purchase.purchaseDate.label', default: 'Paid?')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${purchaseInstanceList}" status="i" var="purchaseInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${purchaseInstance.id}">${fieldValue(bean: purchaseInstance, field: "id")}</g:link></td>
                        
                            <td><g:formatDate date="${purchaseInstance.purchaseDate}" /></td>
                        
                            <td>${fieldValue(bean: purchaseInstance, field: "merchant")}</td>
                        
                            <td>
                            	<g:if test="${purchaseInstance.person}">
                            		${purchaseInstance.person}
                            	</g:if>
                            	<g:else>
                            		${purchaseInstance.purchaser}
                            	</g:else>
                           	</td>
                        
                            <td>${fieldValue(bean: purchaseInstance, field: "total")}</td>
                        
                            <td>${fieldValue(bean: purchaseInstance, field: "paid")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${purchaseInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
