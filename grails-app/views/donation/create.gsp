<%@ page import="coop.mnclimbing.Donation" %>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'donation.label', default: 'Donation')}" />
  <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
  <div class="nav">
	<span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
	<span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
  </div>
  <div class="body">
	<h1><g:message code="default.create.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
	  <div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${donationInstance}">
	  <div class="errors">
		<g:renderErrors bean="${donationInstance}" as="list" />
	  </div>
	</g:hasErrors>
	<g:form action="save" >
	  <div class="dialog">
		<table>
		  <tbody>
			<tr class="prop">
			  <td valign="top" class="name">
				<label for="person"><g:message code="membership.person.label" default="Person" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: donationInstance, field: 'person', 'errors')}">
		  <g:link controller="person" action="edit" id="${donationInstance?.person?.id}" >${donationInstance?.person}
		  </g:link>
		  <g:hiddenField name="person.id" value="${donationInstance?.person?.id}" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="comments"><g:message code="donation.comments.label" default="Comments" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: donationInstance, field: 'comments', 'errors')}">
		  <g:textField name="comments" value="${donationInstance?.comments}" />
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
				<td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'amount', 'errors')}">
			<g:textField name="amount" value="${membershipInstance?.amount}" />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="paymentType.id"><g:message code="membership.paymentType.label" default="Payment Type" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'paymentType', 'errors')}">
			<g:select name="paymentType.id" from="${coop.mnclimbing.PaymentType.list()}" optionKey="id" value="${membershipInstance?.paymentType?.id}"  />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="membershipFrom"><g:message code="membership.paymentDate.label" default="Payment Date" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'paymentDate', 'errors')}">
			<g:datePicker name="paymentDate" precision="day" value="${membershipInstance?.paymentDate}" noSelection="['': '--']" />
			</td>
			</tr>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="membershipFrom"><g:message code="membership.transactionId.label" default="Check # / Transaction #" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: membershipInstance, field: 'transactionId', 'errors')}">
			<g:textField name="transactionId" value="${membershipInstance?.transactionId}" />
			</td>
			</tr>


			</tbody>
		  </table>
		</fieldset>


	  </div>
	  <div class="buttons">
		<span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
	  </div>
	</g:form>
  </div>
</body>
</html>
