<%@ page import="coop.mnclimbing.Person" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title>Pass Holders needing Access Card/Keyfob</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">
				<g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" controller="person" action="list">
				List all Contacts</g:link></span>
        </div>
        <div class="body">
            <h1>Pass Holders needing Access Card/Keyfob</h1>
            <g:if test="${flash.message}">
			  <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
						   <th>Name</th>
                           <g:sortableColumn property="lastName" title="${message(code: 'person.lastName.label', default: 'Last Name')}" />
						   <th>Access</th>
						   <th>Card</th>
						   <th>Email</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accessPassHoldersWithoutCards}" status="i" var="personInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link controller="person" action="edit" id="${personInstance.id}">
							  ${fieldValue(bean: personInstance, field: "fullName")}
								</g:link></td>

                            <td>${fieldValue(bean: personInstance, field: "lastName")}</td>

							<td>
							  <g:if test="${personInstance.activePass}">

								${personInstance.activePass.accessType.name} pass expires
								<g:formatDate date="${personInstance.activePass.endDate}" format="yyyy-MM-dd" />
								
							  </g:if>
							  <g:else>
								None
							  </g:else>
							</td>

							<td>
							  <g:if test="${personInstance.accessCardAssignment}">

								#${personInstance.accessCardAssignment.accessCard.cardIdentifier} issued
								<g:formatDate date="${personInstance.accessCardAssignment.issueDate}" format="yyyy-MM-dd" />

							  </g:if>
							  <g:else>
								None
							  </g:else>
							</td>

                            <td>${fieldValue(bean: personInstance, field: "emailAddress")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <p>&nbsp;</p>
        </div>
    </body>
</html>
