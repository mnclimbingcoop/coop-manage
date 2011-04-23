<%@ page import="coop.mnclimbing.Person" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title>Members, Investors, Pass Holders and Potentials</title>
        <script>
		$(function() {
			$("input[name='id']").focus();		
		});
		</script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create">Create new Contact</g:link></span>
        </div>
        <div class="body">
            <h1>
			  MNCC Contacts
			  <g:remoteField autocomplete="off" class="filterField" name="id" action="find" update="searchDiv" />
			</h1>
			<h2>Members, Investors, Pass Holders and Potentials</h2>
			<div id="searchDiv"></div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
						   <th>Name</th>
                           <g:sortableColumn property="lastName" title="${message(code: 'person.lastName.label', default: 'Last Name')}" />
						   <th>Member</th>
						   <th>Access</th>
						   <th>Card</th>
						   <th>Email</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${personInstanceList}" status="i" var="personInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="edit" id="${personInstance.id}">
							  ${fieldValue(bean: personInstance, field: "fullName")}
								</g:link></td>

                            <td>${fieldValue(bean: personInstance, field: "lastName")}</td>

                            <td>
								<g:if test="${ !personInstance.memberships}">No</g:if>
							  <g:each in="${personInstance.memberships}" status="j" var="membershipInstance">
								  since <g:formatDate date="${membershipInstance.membershipFrom}" format="yyyy-MM-dd" />
							  </g:each>
							</td>

							<td>
							  <g:if test="${personInstance.activePass}">

								${personInstance.activePass.accessType.name} pass <br/>
								expires
								<g:formatDate date="${personInstance.activePass.endDate}" format="yyyy-MM-dd" />
								
							  </g:if>
							  <g:else>
								None
							  </g:else>
							</td>

							<td>
							  <g:if test="${personInstance.accessCardAssignment}">

								${personInstance.accessCardAssignment.accessCard.label}
								<br/> issued
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
            <div class="paginateButtons">
                <g:paginate total="${personInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
