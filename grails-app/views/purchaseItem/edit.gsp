

<%@ page import="coop.mnclimbing.PurchaseItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchaseItem.label', default: 'PurchaseItem')}" />
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
            <g:hasErrors bean="${purchaseItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${purchaseItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${purchaseItemInstance?.id}" />
                <g:hiddenField name="version" value="${purchaseItemInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="purchaseItem.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseItemInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${purchaseItemInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="amount"><g:message code="purchaseItem.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseItemInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${fieldValue(bean: purchaseItemInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="purchase"><g:message code="purchaseItem.purchase.label" default="Purchase" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseItemInstance, field: 'purchase', 'errors')}">
                                    <g:select name="purchase.id" from="${coop.mnclimbing.Purchase.list()}" optionKey="id" value="${purchaseItemInstance?.purchase?.id}"  />
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
    </body>
</html>
