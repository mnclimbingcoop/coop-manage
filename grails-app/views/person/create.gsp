<%@ page import="coop.mnclimbing.Person" %>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
  <title><g:message code="default.create.label" args="[entityName]" /></title>
  <script>
	$(function() {
		$("#tabs").tabs();
		$("#firstName").focus();
	});
  </script>
  <style>
	body { color: #fff; }
  </style>
</head>
<body>
  <div class="nav">
	<span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
	<span class="menuButton"><g:link class="list" action="list">Back to all contacts</g:link></span>
  </div>
  <div class="body">
	<h1><g:message code="default.create.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
	  <div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${personInstance}">
	  <div class="errors">
		<g:renderErrors bean="${personInstance}" as="list" />
	  </div>
	</g:hasErrors>
	<g:form action="save" autocomplete="off">
	  <div class="dialog">

		<div id="tabs">

		  <ul>
			<li><a href="#tab-contact">Contact</a></li>
			<li><a href="#tab-membership">Membership</a></li>
		  </ul>

		  <!-- List Access Passes	-->
		  <div id="tab-membership">

		<fieldset>
		  <legend>Membership</legend>
		  <p>Temporarily Unavailable</p>
		  </table>
		</fieldset>
		  </div>

		  <div id="tab-contact">

		<g:include action="form" model="${[personInstance:personInstance, addressInstance:addressInstance ]}" ></g:include>
		  </div>
		</div>
	  </div>
	  <div class="buttons">
		<span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
	  </div>
	</g:form>
  </div>
</body>
</html>
