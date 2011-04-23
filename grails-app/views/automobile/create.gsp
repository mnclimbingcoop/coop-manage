<%@ page import="coop.mnclimbing.Automobile" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'automobile.label', default: 'Car')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
			<span class="menuButton"><g:link class="list" controller="person" action="list">Member List</g:link></span>
        </div>
        <div class="body">
            <h1>Add New Car</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
            <g:hasErrors bean="${automobileInstance}">
            <div class="errors">
                <g:renderErrors bean="${automobileInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
            
				          <tr class="prop">
							  <td valign="top" class="name">
								<label for="person">Belongs To</label>
							  </td>
							  <td valign="top" class="value ${hasErrors(bean: automobileInstance, field: 'person', 'errors')}">
						  <g:link controller="person" action="edit" id="${automobileInstance?.person?.id}" >${automobileInstance?.person}
						  </g:link>
						  <g:hiddenField name="person.id" value="${automobileInstance?.person?.id}" />
						  </td>
						  </tr>
            
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="licencePlateNumber"><g:message code="automobile.licencePlateNumber.label" default="Licence Plate Number" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: automobileInstance, field: 'licencePlateNumber', 'errors')}">
                                    <g:textField name="licencePlateNumber" value="${automobileInstance?.licencePlateNumber}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="licencePlateState"><g:message code="automobile.licencePlateState.label" default="Licence Plate State" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: automobileInstance, field: 'licencePlateState', 'errors')}">
                                    <g:textField name="licencePlateState" value="${automobileInstance?.licencePlateState}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="year"><g:message code="automobile.year.label" default="Year" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: automobileInstance, field: 'year', 'errors')}">
                                    <g:textField name="year" value="${fieldValue(bean: automobileInstance, field: 'year')}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="make"><g:message code="automobile.make.label" default="Make" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: automobileInstance, field: 'make', 'errors')}">
                                    <g:textField name="make" value="${automobileInstance?.make}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="model"><g:message code="automobile.model.label" default="Model" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: automobileInstance, field: 'model', 'errors')}">
                                    <g:textField name="model" value="${automobileInstance?.model}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="color"><g:message code="automobile.color.label" default="Color" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: automobileInstance, field: 'color', 'errors')}">
                                    <g:textField name="color" value="${automobileInstance?.color}" />
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
