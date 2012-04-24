<%@ page import="coop.mnclimbing.Person" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
			<sec:ifAnyGranted roles="ROLE_BOARD">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
			</sec:ifAnyGranted>
        </div>
        <div class="body">
			<h1>${personInstance.fullName}</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
							<td valign="top" class="name"><h2><g:message code="person.id.label" default="Id" /></h2></td>
                            <td valign="top" class="value"><h2>${fieldValue(bean: personInstance, field: "id")}</h2></td>
                        </tr>
                    
                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.phoneNumber.label" default="Phone Number" /></td>
							<td valign="top" class="value">${personInstance?.phoneNumber}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.address.label" default="Address" /></td>
                            <td valign="top" class="value">${personInstance?.address?.encodeAsHTML()}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="person.emailAddress.label" default="Email Address" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: personInstance, field: "emailAddress")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>

			<h3>Key-Fobs and Key-Cards</h3>
			<ul>
			  <g:each var="c" in="${personInstance.cards}">
				<li>
					${c.accessCard}, issued on <g:formatDate date="${c.issueDate}" format="yyyy-MM-dd" />
				</li>
			  </g:each>
			</ul>

				<fieldset>
					<legend>Forms</legend>

					<h3>Forms Received or Mailed</h3>
					<ul>
						<g:each var="f" in="${personInstance.forms.sort{it.instrument.name}}">
						<li>
						${f.instrument.name}
						<g:if test="${f.outgoing}">
						send
						</g:if>
						<g:else>
						received
						</g:else>
						on <g:formatDate date="${f.transactionDate}" format="yyyy-MM-dd" />
						<g:if test="${f.expirationDate}">
						<ul><li>
							<g:if test="${f.isExpired}">
							<span style="color:darkred;">
								This form <strong>EXPIRED</strong> on <g:formatDate date="${f.expirationDate}" format="yyyy-MM-dd" />.
							</span>
							</g:if>
							<g:else>
								<g:if test="${f.expiresIn < 30}">
								<span style="color:orange;">
								</g:if><g:else><span style="color:green;"></g:else>
								This form will expire on <g:formatDate date="${f.expirationDate}" format="yyyy-MM-dd" /> (${f.expiresIn} days).
								</span>
							</g:else>
							</li></ul>
						</g:if>
						</li>
						</g:each>
					</ul>

					<g:if test="${instrumentInstanceList}">
					<h3>Needed Forms</h3>
					<ul>
						<g:each var="f" in="${instrumentInstanceList}">
						<li>
						<g:link controller="instrumentReceipt" action="create" params="${['person.id': personInstance.id, 'outgoing':false, 'instrument.id':f.id ]}">${f}</g:link>
						</li>
						</g:each>
					</ul>
					</g:if>

		  	<h3>Access Passes</h3>
			<ul>
			  <g:each var="a" in="${personInstance.passes}">
				<li>
${a.accessType.name} pass from 
				<g:formatDate date="${a.startDate}" format="yyyy-MM-dd" />
				to
				<g:formatDate date="${a.endDate}" format="yyyy-MM-dd" />
				</li>
			  </g:each>
			</ul>

				</fieldset>
            </div>
			<sec:ifAnyGranted roles="ROLE_BOARD">
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${personInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                </g:form>
            </div>
			</sec:ifAnyGranted>
        </div>
    </body>
</html>
