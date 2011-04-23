<%@ page import="coop.mnclimbing.Membership"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<g:set var="entityName"
	value="${message(code: 'membership.label', default: 'Membership')}" />
<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
	<span class="menuButton">
		<a class="home"
	href="${createLink(uri: '/')}">
		<g:message code="default.home.label" /></a></span>
		
            <span class="menuButton"><g:link class="create" controller="person" action="list">List All Contacts</g:link></span>
            <span class="menuButton"><g:link class="create" controller="membership" action="list">List All Memberships</g:link></span>
</div>
<div class="body">
<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
<g:if test="${flash.message}">
	<div class="message">
	${flash.message}
	</div>
</g:if> <g:hasErrors bean="${membershipInstance}">
	<div class="errors"><g:renderErrors bean="${membershipInstance}"
		as="list" /></div>
</g:hasErrors> <g:form method="post">
	<g:hiddenField name="id" value="${membershipInstance?.id}" />
	<g:hiddenField name="version" value="${membershipInstance?.version}" />
	<g:hiddenField name="taxRate" value="${membershipInstance?.taxRate}" />
  	<g:hiddenField name="taxable" value="${membershipInstance?.taxable}" />
	<div class="dialog">
	
	<table>
		  <tbody>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="person"><g:message code="membership.person.label" default="Person" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'person', 'errors')}">
		  <g:link controller="person" action="edit" id="${membershipInstance?.person?.id}" >${membershipInstance?.person}
		  </g:link>
		  <g:hiddenField name="person.id" value="${membershipInstance?.person?.id}" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="type.id"><g:message code="membership.type.label" default="Type" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'type', 'errors')}">
		  <g:select name="type.id" from="${coop.mnclimbing.MembershipType.list()}" optionKey="id" value="${membershipInstance?.type?.id}"  />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="memberId"><g:message code="membership.memberId.label" default="Member Id" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'memberId', 'errors')}">
		  <g:textField name="memberId" value="${membershipInstance?.memberId}" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="membershipFrom"><g:message code="membership.membershipFrom.label" default="Membership Starts" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'membershipFrom', 'errors')}">
		  <g:datePicker name="membershipFrom" precision="day" value="${membershipInstance?.membershipFrom}" default="none" noSelection="['': '--']" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="membershipTo"><g:message code="membership.membershipTo.label" default="Membership Termination Date" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'membershipTo', 'errors')}">
		  <g:datePicker name="membershipTo" precision="day" value="${membershipInstance?.membershipTo}" default="none" noSelection="['': '--']" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="terminationReason.id"><g:message code="membership.type.label" default="Termination Reason" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'terminationReason', 'errors')}">
		  <g:select name="terminationReason.id" from="${coop.mnclimbing.MembershipTerminationReason.list()}" optionKey="id" noSelection="['null': '--']" value="${membershipInstance?.terminationReason?.id}"  />
		  </td>
		  </tr>


		  </tbody>
		</table>

	<!-- Payment Details -->
	<fieldset><legend>Payment Details</legend>

	<table>
		<tbody>

			<tr class="prop">
				<td valign="top" class="name"><label for="memberId"><g:message
					code="membership.amount.label" default="Payment Amount" /></label></td>
				<td valign="top"
					class="value ${hasErrors(bean: membershipInstance, field: 'amount', 'errors')}">
				<g:textField name="amount" value="${membershipInstance?.amount}" />
				</td>
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><label for="paymentType.id"><g:message
					code="membership.paymentType.label" default="Payment Type" /></label></td>
				<td valign="top"
					class="value ${hasErrors(bean: membershipInstance, field: 'paymentType', 'errors')}">
				<g:select name="paymentType.id"
					from="${coop.mnclimbing.PaymentType.list()}" optionKey="id"
					value="${membershipInstance?.paymentType?.id}" /></td>
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><label for="membershipFrom"><g:message
					code="membership.paymentDate.label" default="Payment Date" /></label></td>
				<td valign="top"
					class="value ${hasErrors(bean: membershipInstance, field: 'paymentDate', 'errors')}">
				<g:datePicker name="paymentDate" precision="day"
					value="${membershipInstance?.paymentDate}" noSelection="['': '--']" />
				</td>
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><label for="membershipFrom"><g:message
					code="membership.transactionId.label"
					default="Check # / Transaction #" /></label></td>
				<td valign="top"
					class="value ${hasErrors(bean: membershipInstance, field: 'transactionId', 'errors')}">
				<g:textField name="transactionId"
					value="${membershipInstance?.transactionId}" /></td>
			</tr>


		</tbody>
	</table>
	</fieldset>

	</div>

	<div class="buttons"><span class="button"><g:actionSubmit
		class="save" action="update"
		value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
	<span class="button"><g:actionSubmit class="delete"
		action="delete"
		value="${message(code: 'default.button.delete.label', default: 'Delete')}"
		onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	</div>
</g:form></div>
</body>
</html>
