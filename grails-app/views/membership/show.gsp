
<%@ page import="coop.mnclimbing.Membership" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'membership.label', default: 'Membership')}" />
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
                            <td valign="top" class="name"><g:message code="membership.id.label" default="Membership Purchase ID" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: membershipInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="membership.type.label" default="Type" /></td>
                            
                            <td valign="top" class="value"><g:link controller="membershipType" action="show" id="${membershipInstance?.type?.id}">${membershipInstance?.type?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="membership.membershipFrom.label" default="Membership From" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${membershipInstance?.membershipFrom}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="membership.membershipTo.label" default="Membership To" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${membershipInstance?.membershipTo}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="membership.terminationReason.label" default="Termination Reason" /></td>
                            
                            <td valign="top" class="value"><g:link controller="membershipTerminationReason" action="show" id="${membershipInstance?.terminationReason?.id}">${membershipInstance?.terminationReason?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="membership.memberId.label" default="Member Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: membershipInstance, field: "memberId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="membership.person.label" default="Person" /></td>
                            
                            <td valign="top" class="value"><g:link controller="person" action="show" id="${membershipInstance?.person?.id}">${membershipInstance?.person?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${membershipInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
