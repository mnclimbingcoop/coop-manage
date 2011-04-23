

<%@ page import="coop.mnclimbing.AccessCardAssignment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accessCardAssignment.label', default: 'AccessCardAssignment')}" />
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
            <g:hasErrors bean="${accessCardAssignmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${accessCardAssignmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${accessCardAssignmentInstance?.id}" />
                <g:hiddenField name="version" value="${accessCardAssignmentInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="accessCard"><g:message code="accessCardAssignment.accessCard.label" default="Access Card" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accessCardAssignmentInstance, field: 'accessCard', 'errors')}">
                                    <g:select name="accessCard.id" from="${coop.mnclimbing.AccessCard.list()}" optionKey="id" value="${accessCardAssignmentInstance?.accessCard?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="person"><g:message code="accessCardAssignment.person.label" default="Person" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accessCardAssignmentInstance, field: 'person', 'errors')}">
                                    <g:select name="person.id" from="${coop.mnclimbing.Person.list()}" optionKey="id" value="${accessCardAssignmentInstance?.person?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="issueDate"><g:message code="accessCardAssignment.issueDate.label" default="Issue Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accessCardAssignmentInstance, field: 'issueDate', 'errors')}">
                                    <g:datePicker name="issueDate" precision="day" value="${accessCardAssignmentInstance?.issueDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="returnDate"><g:message code="accessCardAssignment.returnDate.label" default="Return Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accessCardAssignmentInstance, field: 'returnDate', 'errors')}">
                                    <g:datePicker name="returnDate" precision="day" value="${accessCardAssignmentInstance?.returnDate}" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lost"><g:message code="accessCardAssignment.lost.label" default="Lost" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accessCardAssignmentInstance, field: 'lost', 'errors')}">
                                    <g:checkBox name="lost" value="${accessCardAssignmentInstance?.lost}" />
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
