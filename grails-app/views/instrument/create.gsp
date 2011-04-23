<%@ page import="coop.mnclimbing.Instrument" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'instrument.label', default: 'Instrument')}" />
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
            <g:hasErrors bean="${instrumentInstance}">
            <div class="errors">
                <g:renderErrors bean="${instrumentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="abbreviation"><g:message code="instrument.abbreviation.label" default="Abbreviation" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instrumentInstance, field: 'abbreviation', 'errors')}">
                                    <g:textField name="abbreviation" value="${instrumentInstance?.abbreviation}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="instrument.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instrumentInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${instrumentInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="currentVersion"><g:message code="instrument.currentVersion.label" default="Version" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instrumentInstance, field: 'currentVersion', 'errors')}">
                                    <g:textField name="currentVersion" value="${fieldValue(bean: instrumentInstance, field: 'currentVersion')}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="versionDate"><g:message code="instrument.versionDate.label" default="Version Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instrumentInstance, field: 'versionDate', 'errors')}">
                                    <g:datePicker precision="day" name="versionDate" value="${finstrumentInstance?.versionDate}" noSelection="['null':'']" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="required">Is this form required?</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instrumentInstance, field: 'required', 'errors')}">
                                    <g:checkBox name="required" value="${instrumentInstance?.required}" />
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
