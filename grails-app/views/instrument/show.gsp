
<%@ page import="coop.mnclimbing.Instrument" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'instrument.label', default: 'Form')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create" params="${ [ abbreviation:instrumentInstance.abbreviation, name:instrumentInstance.name, currentVersion: (instrumentInstance.currentVersion + 1) ] }">New Version</g:link></span>
        </div>
        <div class="body">
            <h1>${instrumentInstance.name}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
               	<g:if test="${instrumentInstance.obsolete}">
              		<strong>This form is obsolete as of <g:formatDate date="${instrumentInstance.obsoletionDate}" format="MM/dd/yyyy" /></strong>
              	</g:if>
                <table>
                    <tbody>
                    	
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="instrument.id.label" default="Form ID" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: instrumentInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="instrument.abbreviation.label" default="Abbreviation" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: instrumentInstance, field: "abbreviation")}</td>
                            
                        </tr>
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="instrument.currentVersion.label" default="Version" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: instrumentInstance, field: "currentVersion")}, <g:formatDate date="${instrumentInstance.versionDate}" format="M/d/yyyy" /></td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name">Required by Members?</td>
                            
                            <td valign="top" class="value"><g:if test="${instrumentInstance.required}">Yes</g:if><g:else>No</g:else> </td>
                        </tr>

                        <tr class="prop">
							<td valign="top" class="value">
							<g:if test="${instrumentInstance.daysValidFor}">
								This form is valid for ${instrumentInstance.daysValidFor} days
							</g:if>
							<g:else>
								This form does not expire
							</g:else>
							</td>
                        </tr>
                                        
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${instrumentInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            
            <g:if test="${instrumentReceiptInstanceList}">
	            <div id="completedForms">
	            	<h2>Completed Forms</h2>
	            	<table>
	            		<thead>
	            			<tr>
	            				<th>Name</th>
	            				<th>Completed</th>
	            			</tr>
	            		</thead>
	            		<tbody>
			            	<g:each var="ir" in="${instrumentReceiptInstanceList}">
	            			<tr>
	            				<td><g:link controller="instrumentReceipt" action="show" id="${ir.id}">${ir.person}</g:link></td>
	            				<td>${ir.transactionDate }</td>
	            			</tr>
			            	</g:each>
	            		</tbody>
	            	</table>
	            </div>
			</g:if>
			            
            <g:if test="${instrumentInstance.required}">
            	<div id="neededForms">
	            	<h2>Forms Needed</h2>
	            	<table>
	            		<thead>
	            			<tr>
	            				<th>Name</th>
	            			</tr>
	            		</thead>
	            		<tbody>
			            	<g:each var="p" in="${needInstrumentReceiptInstanceList}">
	            			<tr>
	            				<td><g:link controller="person" action="edit" id="${p.id}"> ${p}</g:link></td>
	            			</tr>
			            	</g:each>
	            		</tbody>
	            	</table>
	            </div>
            </g:if>
        </div>
    </body>
</html>
