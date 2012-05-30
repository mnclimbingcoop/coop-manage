
<%@ page import="coop.mnclimbing.Access" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'access.label', default: 'Access')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" controller="person" action="list">All Contacts</g:link></span>
        </div>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>

			<g:if test="${accessSalesTotal && accessSalesTax && accessSalesAmount}">
			  <ul>
				<li>Total Sales: <g:formatNumber number="${accessSalesTotal}" type="currency" currencyCode="USD" /></li>
				<li>Total Sales Tax: <g:formatNumber number="${accessSalesTax}" type="currency" currencyCode="USD" /></li>
				<li>Total Amount: <g:formatNumber number="${accessSalesAmount}" type="currency" currencyCode="USD" /></li>
			  </ul>
			</g:if>

			<g:if test="${accessNoticeInstanceList}">
			<h2>The following people are due for email notifications:</h2>
			<ol>
				<g:each var="pass" in="${accessNoticeInstanceList}">
				<li>${pass.person.fullName} &lt;${pass.person.emailAddress}&gt;
				${pass.accessType} pass expiring on <g:formatDate date="${pass.endDate}" format="MMMM, d" /></li>
				</g:each>
			</ol>

			<div class="send-expiring">
				<g:link action="sendExpiring" class="sendEmailLink" onclick="return sendEmails();">Click here to send up to 10 notification emails at once</g:link>.<br/>
				<em>Be patient after clicking this.  It takes just under 10 seconds per email to send through
					Google's email servers.</em>
			</div><div id="sent-exipring">&nbsp;</div>

			</g:if>


            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'access.id.label', default: 'Id')}" />

                            <th>Owner</th>

                            <th>Email</th>

                            <g:sortableColumn property="amount" title="${message(code: 'access.amount.label', default: 'Amount')}" />
                        
                            <th><g:message code="access.accessType.label" default="Type" /></th>
                        
                            <g:sortableColumn property="startDate" title="${message(code: 'access.startDate.label', default: 'Start Date')}" />
                            <g:sortableColumn property="endDate" title="${message(code: 'access.endDate.label', default: 'End Date')}" />
                            <g:sortableColumn property="expirationNotificationSent" title="Reminder Sent" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accessInstanceList}" status="i" var="accessInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${accessInstance.id}">${fieldValue(bean: accessInstance, field: "id")}</g:link></td>
                        
                            <td>${accessInstance.person.fullName}</td>

							<td>${accessInstance.person.emailAddress}</td>

                            <td>&#36;${fieldValue(bean: accessInstance, field: "amount")}</td>
                        
                            <td>${fieldValue(bean: accessInstance, field: "accessType")}</td>
                        
                            <td><g:formatDate date="${accessInstance.startDate}" format="yyyy-MM-dd" /></td>
                            <g:if test="${lastMonth && lastMonth < accessInstance.endDate}">
                            	<td style="background-color: pink;"><g:formatDate date="${accessInstance.endDate}" format="yyyy-MM-dd" /></td>
                            </g:if>
                            <g:else>
                            	<td><g:formatDate date="${accessInstance.endDate}" format="yyyy-MM-dd" /></td>
                            </g:else>

							<td><g:formatDate date="${accessInstance.expirationNotificationSent}" format="yyyy-MM-dd" /></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
			<p>&nbsp;</p>
        </div>
    </body>
</html>
