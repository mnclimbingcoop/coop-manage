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
			  <g:remoteField autocomplete="off" class="filterField" name="id" controller="person" action="find" update="searchDiv" />
			</h1>
			<h2>Members, Investors, Pass Holders and Potentials</h2>
			<div id="searchDiv"></div>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
			<div class="paginateButtons">
                <g:paginate total="${personInstanceTotal}" />
            </div>
            
            <div class="element-list">
            	<ul>
            		<g:each in="${personInstanceList}" status="i" var="personInstance">
            		<li>
            			<g:link action="edit" id="${personInstance.id}">
            			<!-- Name -->
            			<div class="title">
            				<span class="memberId">#${fieldValue(bean: personInstance, field: "id")}</span>:
            				<span class="fullName">${fieldValue(bean: personInstance, field: "fullName")}</span><br/>
            			</div>
            			<!-- Membership -->
            			<strong>Membership:</strong>
            			<g:if test="${ ! personInstance.memberships }">
            				<span class="flag">Not a Member</span>
            			</g:if>
            			<g:else>
							<g:each in="${personInstance.memberships}" status="j" var="membershipInstance">
							Member since <g:formatDate date="${membershipInstance.membershipFrom}" format="yyyy-MM-dd" />
							</g:each>
            			</g:else><br/>
            			<!-- Access -->
            			<strong>Access:</strong>
            			<g:if test="${personInstance.activePass}">
							${personInstance.activePass.accessType.name} pass
							expires
							<g:formatDate date="${personInstance.activePass.endDate}" format="yyyy-MM-dd" />
						</g:if>
						<g:else>
							<span class="flag">None</span>
						</g:else><br/>
            			<!-- Card -->
            			<strong>Card:</strong>
	          			<g:if test="${personInstance.accessCardAssignment}">
	          				<g:if test="${personInstance.accessCardAssignment.accessCard.facilityAssigned}">MNCC Card ${personInstance.accessCardAssignment.accessCard.label}</g:if>
	          				<g:else>Personnal Card</g:else>
							issued
							<g:formatDate date="${personInstance.accessCardAssignment.issueDate}" format="yyyy-MM-dd" />
						</g:if>
						<g:else>
							<span class="flag">None</span>
						</g:else><br/>
            			<!-- Email -->
            			<strong>Email:</strong>
            			<span class="email">
            			<g:if test="${! personInstance.emailAddress }">
            				<span class="flag">No email on file</span>
            			</g:if>
            			<g:else>
            			${fieldValue(bean: personInstance, field: "emailAddress")}
            			</g:else>
            			</span>
            			</g:link>
            		</li>
            		</g:each>
            	</ul>
            </div>
			<div style="clear:both;"></div>
            <div class="paginateButtons">
                <g:paginate total="${personInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
