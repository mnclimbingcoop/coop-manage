<%@ page import="coop.mnclimbing.Stock" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${stockInstance}">
            <div class="errors">
                <g:renderErrors bean="${stockInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${stockInstance?.id}" />
                <g:hiddenField name="version" value="${stockInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                     			<tr class="prop">
							  <td valign="top" class="name">
								<label for="person"><g:message code="stock.person.label" default="Person" /></label>
							  </td>
							  <td valign="top" class="value ${hasErrors(bean: stockInstance, field: 'person', 'errors')}">
						  <g:link controller="person" action="edit" id="${stockInstance?.person?.id}" >${stockInstance?.person}
						  </g:link>
						  <g:hiddenField name="person.id" value="${stockInstance?.person?.id}" />
						  </td>
						  </tr>
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="classOfStock"><g:message code="stock.classOfStock.label" default="Class Of Stock" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: stockInstance, field: 'classOfStock', 'errors')}">
                                    <g:select name="classOfStock.id" from="${coop.mnclimbing.StockClass.list()}" optionKey="id" value="${stockInstance?.classOfStock?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                    		<!-- Payment Details -->
		<fieldset>
		  <legend>Payment Details</legend>

		  <table>
			<tbody>

			  <tr class="prop">
				<td valign="top" class="name">
				  <label for="memberId"><g:message code="membership.amount.label" default="Payment Amount" /></label>
				</td>
				<td valign="top" class="value ${hasErrors(bean: stockInstance, field: 'amount', 'errors')}">
			<g:textField name="amount" value="${stockInstance?.amount}" />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="paymentType.id"><g:message code="membership.paymentType.label" default="Payment Type" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: stockInstance, field: 'paymentType', 'errors')}">
			<g:select name="paymentType.id" from="${coop.mnclimbing.PaymentType.list()}" optionKey="id" value="${stockInstance?.paymentType?.id}"  />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="membershipFrom"><g:message code="membership.paymentDate.label" default="Payment Date" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: stockInstance, field: 'paymentDate', 'errors')}">
			<g:datePicker name="paymentDate" precision="day" value="${stockInstance?.paymentDate}" noSelection="['': '--']" />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="membershipFrom"><g:message code="membership.transactionId.label" default="Check # / Transaction #" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: stockInstance, field: 'transactionId', 'errors')}">
			<g:textField name="transactionId" value="${stockInstance?.transactionId}" />
			</td>
			</tr>


			</tbody>
		  </table>
		</fieldset>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
