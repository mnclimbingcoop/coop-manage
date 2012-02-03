
<%@ page import="coop.mnclimbing.AccessCard" %>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'accessCard.label', default: 'AccessCard')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
	<span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
	<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
	<span class="menuButton"><g:link class="create" action="createRange">Create Range of Access Cards</g:link></span>
    <span class="menuButton"><g:link class="list" action="unassigned">Unassigned Cards</g:link></span>
  </div>
  <div class="body">
	<h1>
			  Cards
	  <g:remoteField autocomplete="off" class="filterField" name="id" action="find" update="searchDiv" />
	</h1>
	<div id="searchDiv"></div>
	<h2>HID iClass Access Card List</h2>

	<g:if test="${flash.message}">
	  <div class="message">${flash.message}</div>
	</g:if>
	<div class="list">
	  <table>
		<thead>
		  <tr>

		<g:sortableColumn property="id" title="${message(code: 'accessCard.id.label', default: 'Id')}" />

		<g:sortableColumn property="cardIdentifier" title="${message(code: 'accessCard.cardIdentifier.label', default: 'Card Identifier')}" />

		<g:sortableColumn property="label" title="${message(code: 'accessCard.label.label', default: 'Label')}" />
		
		<th>Assigned To</th>

		</tr>
		</thead>
		<tbody>
		<g:each in="${accessCardInstanceList}" status="i" var="accessCardInstance">
		  <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

			<td><g:link action="edit" id="${accessCardInstance.id}">${fieldValue(bean: accessCardInstance, field: "id")}</g:link></td>

		  <td>${fieldValue(bean: accessCardInstance, field: "cardIdentifier")}</td>

		  <td>${fieldValue(bean: accessCardInstance, field: "label")}</td>
		  
		  <td>
		  	<g:each var="ac" in="${accessCardInstance?.assignments}">
		  		${ac.person}
		  	</g:each>
		  </td>

		  </tr>
		</g:each>
		</tbody>
	  </table>
	</div>
	<div class="paginateButtons">
	  <g:paginate total="${accessCardInstanceTotal}" />
	</div>
  </div>
</body>
</html>
