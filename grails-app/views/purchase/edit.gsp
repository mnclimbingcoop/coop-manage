

<%@ page import="coop.mnclimbing.Purchase" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}" />
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
            <g:hasErrors bean="${purchaseInstance}">
            <div class="errors">
                <g:renderErrors bean="${purchaseInstance}" as="list" />
            </div>
            </g:hasErrors>
            <div style="float:left; width:60%;">
            <g:form method="post" >
                <g:hiddenField name="id" value="${purchaseInstance?.id}" />
                <g:hiddenField name="version" value="${purchaseInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="purchaseDate"><g:message code="purchase.purchaseDate.label" default="Purchase Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'purchaseDate', 'errors')}">
                                    <g:datePicker name="purchaseDate" precision="day" value="${purchaseInstance?.purchaseDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="merchant"><g:message code="purchase.merchant.label" default="Merchant" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'merchant', 'errors')}">
                                    <g:textField name="merchant" value="${purchaseInstance?.merchant}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="person"><g:message code="purchase.purchaser.label" default="Purchaser" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'person', 'errors')}">
                                    <g:select name="person.id" from="${coop.mnclimbing.Person.list()}" optionKey="id" value="${purchaseInstance?.person?.id}" noSelection="['null': '']" />
                                    or
                                    <g:textField name="purchaser" value="${purchaseInstance?.purchaser}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="shipping"><g:message code="purchase.shipping.label" default="Shipping" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'shipping', 'errors')}">
                                    <g:textField name="shipping" value="${fieldValue(bean: purchaseInstance, field: 'shipping')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tax"><g:message code="purchase.tax.label" default="Tax" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'tax', 'errors')}">
                                    <g:textField name="tax" value="${fieldValue(bean: purchaseInstance, field: 'tax')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="total"><g:message code="purchase.total.label" default="Total" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'total', 'errors')}">
                                    <g:textField name="total" value="${fieldValue(bean: purchaseInstance, field: 'total')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="paid"><g:message code="purchase.paid.label" default="Paid" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'paid', 'errors')}">
                                    <g:datePicker name="paid" precision="day" value="${purchaseInstance?.paid}" default="none" noSelection="['': '--']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="comments"><g:message code="purchase.comments.label" default="Comments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'comments', 'errors')}">
                                    <g:textArea columns="50" name="comments" value="${purchaseInstance?.comments}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
            </div>

			<div style="float:right; width:35%; border: thin #ccc solid; padding: 0.5em;">
                <div class="dialog">
                	<h2>
                    	<label for="items"><g:message code="purchase.items.label" default="Items" /></label>
                    </h2>
	                
        		    <g:form method="post" controller="purchaseItem" action="save" >
					<g:hiddenField name="purchase.id" value="${purchaseInstance?.id}" />
	                <table>
	                	<thead>
	                		<tr>
	                			<th>Count</th>
	                			<th>Name</th>
	                			<th>Amount</th>
	                		</tr>
	                	</thead>
	                	<tbody>
							<g:if test="${ ! purchaseInstance?.items}">
							<tr><td colspan="3">None</td></tr>
							</g:if>
							<g:each in="${purchaseInstance?.items}" var="i">
							<tr>
								<td>${i.count}</td>
								<td>${i.name}</td>
								<td>${i.amount}</td>
							</tr>
							</g:each>
	                	</tbody>
	                	<tfoot>
	                		<tr>
	                			<td>
	                				<g:textField size="3" name="count" value="1" />
	                			</td>
	                			<td>
	                				<g:textField size="12" name="name" value="${purchaseItemInstance?.name}" />
	                			</td>
	                			<td>
	                				$<g:textField size="6" name="amount" value="0.00" />
	                			</td>
	                		</tr>
	                		<tr>
	                			<td colspan="2"></td>
	                			<td style="text-align: right;">
	                				<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
	                			</td>
	                		</tr>
	                	</tfoot>
	                </table>
					</g:form>
                </div>
            </div>       
        </div>
    </body>
</html>
