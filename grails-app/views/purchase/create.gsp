

<%@ page import="coop.mnclimbing.Purchase" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>New Purchase</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${purchaseInstance}">
            <div class="errors">
                <g:renderErrors bean="${purchaseInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
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
                                    <label for="person">Purchaser</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'person', 'errors')}">
                                    <g:select name="person.id" from="${coop.mnclimbing.Person.list()}" optionKey="id" value="${purchaseInstance?.person?.id}" noSelection="['null': '']" />
									or
                                    <g:textField name="purchaser" value="${purchaseInstance?.purchaser}" />
                                </td>
                            </tr>
                                                
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="total"><g:message code="purchase.total.label" default="Total" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: purchaseInstance, field: 'total', 'errors')}">
                                    $<g:textField name="total" size="8" value="${fieldValue(bean: purchaseInstance, field: 'total')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
