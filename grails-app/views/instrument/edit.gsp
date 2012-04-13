<%@ page import="coop.mnclimbing.Instrument" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'instrument.label', default: 'Instrument')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${instrumentInstance}">
            <div class="errors">
                <g:renderErrors bean="${instrumentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${instrumentInstance?.id}" />
                <g:hiddenField name="version" value="${instrumentInstance?.version}" />
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
                                	<g:datePicker precision="day" name="versionDate" value="${instrumentInstance.versionDate}" default="none" noSelection="['null':'--']" />
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

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="daysValidFor">How many days is this valid for? (blank = forever)</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instrumentInstance, field: 'daysValidFor', 'errors')}">
                                    <g:textField name="daysValidFor" value="${fieldValue(bean: instrumentInstance, field: 'daysValidFor')}" />
                                </td>
                            </tr>  

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="obsoletionDate"><g:message code="instrument.obsoletionDate.label" default="When does this become obsolete?" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: instrumentInstance, field: 'obsoletionDate', 'errors')}">
                                	<g:datePicker precision="day" name="obsoletionDate" value="${instrumentInstance.obsoletionDate}" default="none" noSelection="['null':'--']" />
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
