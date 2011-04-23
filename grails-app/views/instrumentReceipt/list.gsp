
<%@ page import="coop.mnclimbing.InstrumentReceipt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'instrumentReceipt.id.label', default: 'Id')}" />
                        
                            <th><g:message code="instrumentReceipt.instrument.label" default="Instrument" /></th>
                        
                            <g:sortableColumn property="transactionDate" title="${message(code: 'instrumentReceipt.transactionDate.label', default: 'Transaction Date')}" />
                        
                            <g:sortableColumn property="outgoing" title="${message(code: 'instrumentReceipt.outgoing.label', default: 'Outgoing')}" />
                        
                            <th><g:message code="instrumentReceipt.person.label" default="Person" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${instrumentReceiptInstanceList}" status="i" var="instrumentReceiptInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${instrumentReceiptInstance.id}">${fieldValue(bean: instrumentReceiptInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: instrumentReceiptInstance, field: "instrument")}</td>
                        
                            <td><g:formatDate date="${instrumentReceiptInstance.transactionDate}" /></td>
                        
                            <td><g:formatBoolean boolean="${instrumentReceiptInstance.outgoing}" /></td>
                        
                            <td>${fieldValue(bean: instrumentReceiptInstance, field: "person")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${instrumentReceiptInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
