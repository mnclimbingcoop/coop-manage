<%@ page import="coop.mnclimbing.Access" %>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'access.label', default: 'Access')}" />
  <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
	<span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
	<span class="menuButton"><g:link class="list" controller="person" action="list">List all Contacts</g:link></span>
	<span class="menuButton"><g:link class="list" action="list">List all Access Passes</g:link></span>
  </div>
  <div class="body">
	<h1>Editing Access Pass #${accessInstance?.id}</h1>
	<g:if test="${flash.message}">
	  <div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${accessInstance}">
	  <div class="errors">
		<g:renderErrors bean="${accessInstance}" as="list" />
	  </div>
	</g:hasErrors>
	<g:form method="post" >
	  <g:hiddenField name="id" value="${accessInstance?.id}" />
	  <g:hiddenField name="version" value="${accessInstance?.version}" />
	  <g:hiddenField name="taxRate" value="${accessInstance?.taxRate}" />
	  <g:hiddenField name="taxable" value="${accessInstance?.taxable}" />
	  <div class="dialog">
		<table>
		  <tbody>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="person"><g:message code="access.person.label" default="Person" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'person', 'errors')}">
		  <g:link controller="person" action="edit" id="${accessInstance?.person?.id}" >${accessInstance?.person}
		  </g:link>
		  <g:hiddenField name="person.id" value="${accessInstance?.person?.id}" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="accessDuration"><g:message code="access.accessDuration.label" default="Access Duration" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'accessDuration', 'errors')}">
		  <g:textField name="accessDuration" value="${fieldValue(bean: accessInstance, field: 'accessDuration')}" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="accessType"><g:message code="access.accessType.label" default="Access Type" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'accessType', 'errors')}">
		  <g:select name="accessType.id" from="${coop.mnclimbing.AccessType.list()}" optionKey="id" value="${accessInstance?.accessType?.id}"  />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="startDate"><g:message code="access.startDate.label" default="Start Date" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'startDate', 'errors')}">
		  <g:datePicker name="startDate" precision="day" value="${accessInstance?.startDate}"  />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="endDate"><g:message code="access.endDate.label" default="End Date" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'endDate', 'errors')}">
		  <g:datePicker name="endDate" precision="day" value="${accessInstance?.endDate}"  />
		  </td>
		  </tr>

		  </tbody>
		</table>

		<!-- Payment Details -->
		<fieldset>
		  <legend>Payment Details</legend>

		  <table>
			<tbody>

			  <tr class="prop">
				<td valign="top" class="name">
				  <label for="memberId"><g:message code="membership.amount.label" default="Payment Amount" /></label>
				</td>
				<td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'amount', 'errors')}">
			<g:textField name="amount" value="${accessInstance?.amount}" />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="paymentType.id"><g:message code="membership.paymentType.label" default="Payment Type" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'paymentType', 'errors')}">
			<g:select name="paymentType.id" from="${coop.mnclimbing.PaymentType.list()}" optionKey="id" value="${accessInstance?.paymentType?.id}"  />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="membershipFrom"><g:message code="membership.paymentDate.label" default="Payment Date" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'paymentDate', 'errors')}">
			<g:datePicker name="paymentDate" precision="day" value="${accessInstance?.paymentDate}" noSelection="['': '--']" />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="membershipFrom"><g:message code="membership.transactionId.label" default="Check # / Transaction #" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: accessInstance, field: 'transactionId', 'errors')}">
			<g:textField name="transactionId" value="${accessInstance?.transactionId}" />
			</td>
			</tr>


			</tbody>
		  </table>
		</fieldset>

	  </div>
	  <div class="buttons">
		<span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	  </div>
	</g:form>
  </div>
</body>
</html>
