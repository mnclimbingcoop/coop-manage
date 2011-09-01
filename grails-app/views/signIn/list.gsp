<%@ page import="coop.mnclimbing.SignIn" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'signIn.label', default: 'SignIn')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
			$(function() {
				$("input[name='fullName']").autocomplete({
					source: ${memberNames},
					select: function() { 
						$("select[name='memberGuestVisitor']").val("Member");
						$("input[name='signInTime']").focus(); }
					});
				$("input[name='fullName']").focus();	
			});
		  </script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
        	<g:link action="importDoor">Import Sign-Ins from Coop Door Scans.</g:link><br/>
            <div style="float:right;">
            	<g:form>
				<g:datePicker name="signInDate" precision="day" value="${signInInstance?.signInDate}" />
				<g:submitButton name="Set" value="Set" />
				</g:form>
            </div>

            <h1>Sign-In</h1>
            
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
			<g:form action="save" >
            	<g:hiddenField name="person.id" value="${signInInstance?.person?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <th valign="top" class="name">
                                    <label for="fullName">Name</label>
                                </th>
                                <th valign="top" class="name">
                                    <label for="memberGuestVisitor">M/G/V</label>
                                </th>
                                <th valign="top" class="name">
                                    <label for="signInDate"><g:message code="signIn.signInDate.label" default="Time" /></label>
                                </th>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="value ${hasErrors(bean: signInInstance, field: 'fullName', 'errors')}">
                                    <g:textField name="fullName" value="${signInInstance?.fullName}" />
                                </td>
                                <td valign="top" class="value">
                                	<g:select name="memberGuestVisitor" value="${memberGuestVisitor}" from="${['Unknown','Member','Guest','Visitor']}" />
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: signInInstance, field: 'signInTime', 'errors')}">
                                    <g:textField name="signInTime" size="6" value="${signInTime}"  />
                                    <g:select name="amPm" value="${amPm}" from="${ ['AM','PM'] }" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
              	<div style="display:none;">
				<g:datePicker name="signInDate" value="${signInInstance?.signInDate}" style="display;none" />
				</div>
                
            </g:form>            
            
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <th>Attendee</th>
                            <th>M/G/V</th>
                            <g:sortableColumn property="signInDate" title="Time" />
                            <th><img alt="Delete" src="${resource(dir:'images',file:'skin/database_delete.png')}" /></th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${signInInstanceList}" status="i" var="si">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                            	<g:if test="${si.person}">
                            		<g:link controller="person" action="edit" id="${si.person.id}">${si.person}</g:link>
                            	</g:if>
                            	<g:else>
                            		${si.fullName}
                            	</g:else>
                            </td>
                            <td>${si.memberVisitorGuest}</td>
                            <td><g:formatDate date="${si.signInDate}" format="MM/dd/yyyy hh:mm a" /></td>
                            <td>
                            	<g:form>
                            	<g:hiddenField name="id" value="${si.id}" />
                           		<g:actionSubmit class="delete"
									action="delete"
									value="${message(code: 'default.button.delete.label', default: 'Delete')}"
									onclick="return confirm('Are you sure you wish to remove this record?');" />
								</g:form>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
