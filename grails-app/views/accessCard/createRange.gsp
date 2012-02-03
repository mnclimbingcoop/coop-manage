<%@ page import="coop.mnclimbing.AccessCard" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'accessCard.label', default: 'AccessCard')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${accessCardInstance}">
            <div class="errors">
                <g:renderErrors bean="${accessCardInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveRange" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="facilityCode"><g:message code="accessCard.facilityCode.label" default="Facility Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accessCardInstance, field: 'facilityCode', 'errors')}">
                                    <g:textField name="facilityCode" value="${accessCardInstance?.facilityCode}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cardIdentifierStart"><g:message code="accessCard.cardIdentifierStart.label" default="Card Identifier Start Number" /></label>
                                </td>
                                <td valign="top" class="value">
                                    <g:textField name="cardIdentifierStart" value="${cardIdentifierStart}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cardIdentifierEnd"><g:message code="accessCard.cardIdentifierEnd.label" default="Card Identifier End Number" /></label>
                                </td>
                                <td valign="top" class="value">
                                    <g:textField name="cardIdentifierEnd" value="${cardIdentifierEnd}" />
                                </td>
                            </tr>
                        
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="facilityAssigned">Was this provided by us?</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: accessCardAssignmentInstance, field: 'facilityAssigned', 'errors')}">
                                    <g:checkBox name="facilityAssigned" value="${accessCardAssignmentInstance?.facilityAssigned}" />
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
