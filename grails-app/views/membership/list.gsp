
<%@ page import="coop.mnclimbing.Membership" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'membership.label', default: 'Membership')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" controller="person" action="list">List All Contacts</g:link></span>
        </div>
        <div class="body">
            <h1>All Memberships</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>

			<g:if test="${membershipSalesTotal}">
			  <ul>
				<li>Total Sales: <g:formatNumber number="${membershipSalesTotal}" type="currency" currencyCode="USD" /></li>
			  </ul>
			</g:if>

            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <th><g:message code="membership.type.label" default="Type" /></th>
                        
                            <g:sortableColumn property="membershipFrom" title="${message(code: 'membership.membershipFrom.label', default: 'Membership From')}" />
                        
                            <g:sortableColumn property="memberId" title="${message(code: 'membership.memberId.label', default: 'Purchase #')}" />
                        
                            <g:sortableColumn property="person" title="${message(code: 'membership.person.lastName', default: 'Name')}" />

							<th>Payment</th>
							
							<th>Amount</th>

							<th>Transaction #</th>
							</tr>
                    </thead>
                    <tbody>
                    <g:each in="${membershipInstanceList}" status="i" var="membershipInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="edit" id="${membershipInstance.id}">${fieldValue(bean: membershipInstance, field: "type")}</g:link></td>
                            <td><g:link action="edit" id="${membershipInstance.id}"><g:formatDate date="${membershipInstance.membershipFrom}" format="yyyy-MM-dd" /></g:link></td>
                            <td>${membershipInstance.memberId}</td>
                            <td>${membershipInstance.person.fullName}</td>
                            <td>${membershipInstance.paymentType}</td>
                            <td><g:formatNumber number="${membershipInstance.amount}" type="currency" currencyCode="USD" /> </td>
                            <td>${membershipInstance.transactionId}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
			<p>&nbsp;</p>
        </div>
    </body>
</html>
