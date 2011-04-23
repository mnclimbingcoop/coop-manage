
<%@ page import="coop.mnclimbing.Donation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'donation.label', default: 'Donation')}" />
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

			<g:if test="${donationTotal}">
			  <h2>Total Donations: <g:formatNumber number="${donationTotal}" type="currency" currencyCode="USD" /></h2>
			</g:if>

            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'donation.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="amount" title="${message(code: 'donation.amount.label', default: 'Amount')}" />
                        
                            <g:sortableColumn property="paymentDate" title="${message(code: 'donation.paymentDate.label', default: 'Payment Date')}" />
                        
                            <g:sortableColumn property="comments" title="${message(code: 'donation.comments.label', default: 'Comments')}" />
                        
                            <th><g:message code="donation.paymentType.label" default="Payment Type" /></th>
                        
                            <th><g:message code="donation.person.label" default="Person" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${donationInstanceList}" status="i" var="donationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${donationInstance.id}">${fieldValue(bean: donationInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: donationInstance, field: "amount")}</td>
                        
                            <td><g:formatDate date="${donationInstance.paymentDate}" /></td>
                        
                            <td>${fieldValue(bean: donationInstance, field: "comments")}</td>
                        
                            <td>${fieldValue(bean: donationInstance, field: "paymentType")}</td>
                        
                            <td>${fieldValue(bean: donationInstance, field: "person")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${donationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
