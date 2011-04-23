<%@ page import="coop.mnclimbing.Purchase" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.id.label" default="Purchase ID" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: purchaseInstance, field: "id")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.purchaseDate.label" default="Purchase Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${purchaseInstance?.purchaseDate}" format="MM/dd/yyyy" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.merchant.label" default="Merchant" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: purchaseInstance, field: "merchant")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Purchaser</td>
                            
                            <td valign="top" class="value">
                            	<g:if test="${purchaseInstance?.person}">
                            		<g:link controller="person" action="show" id="${purchaseInstance?.person?.id}">${purchaseInstance?.person?.encodeAsHTML()}</g:link>
                            	</g:if>
                            	<g:else>
                            		${fieldValue(bean: purchaseInstance, field: "purchaser")}
                            	</g:else>
                            </td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.total.label" default="Total" /></td>
                            
                            <td valign="top" class="value"><g:formatNumber number="${purchaseInstance.subTotal}" type="currency" currencyCode="USD" /></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.shipping.label" default="Shipping" /></td>
                            
                            <td valign="top" class="value"><g:formatNumber number="${purchaseInstance.shipping}" type="currency" currencyCode="USD" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.tax.label" default="Tax" /></td>
                            
                            <td valign="top" class="value"><g:formatNumber number="${purchaseInstance.tax}" type="currency" currencyCode="USD" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.total.label" default="Total" /></td>
                            
                            <td valign="top" class="value"><g:formatNumber number="${purchaseInstance.total}" type="currency" currencyCode="USD" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.paid.label" default="Paid" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${purchaseInstance?.paid}" format="MM/dd/yyyy" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.items.label" default="Items" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul style="list-style: none; padding-left: 0;">
                                <g:each in="${purchaseInstance.items}" var="i">
                                    <li style="margin-bottom: 0.3em;"><g:link controller="purchaseItem" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="purchase.comments.label" default="Comments" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: purchaseInstance, field: "comments")}</td>
                            
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${purchaseInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
