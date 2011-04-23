
<%@ page import="coop.mnclimbing.Access" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'access.label', default: 'Access')}" />
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
                            <td valign="top" class="name"><g:message code="access.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: accessInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.amount.label" default="Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: accessInstance, field: "amount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.paymentDate.label" default="Payment Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${accessInstance?.paymentDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.accessDuration.label" default="Access Duration" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: accessInstance, field: "accessDuration")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.accessType.label" default="Access Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="accessType" action="show" id="${accessInstance?.accessType?.id}">${accessInstance?.accessType?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.endDate.label" default="End Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${accessInstance?.endDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.paymentType.label" default="Payment Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="paymentType" action="show" id="${accessInstance?.paymentType?.id}">${accessInstance?.paymentType?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.person.label" default="Person" /></td>
                            
                            <td valign="top" class="value"><g:link controller="person" action="show" id="${accessInstance?.person?.id}">${accessInstance?.person?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="access.startDate.label" default="Start Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${accessInstance?.startDate}" /></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${accessInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
