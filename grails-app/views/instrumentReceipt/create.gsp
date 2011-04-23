<%@ page import="coop.mnclimbing.InstrumentReceipt" %>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'instrumentReceipt.label', default: 'InstrumentReceipt')}" />
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
	<g:hasErrors bean="${instrumentReceiptInstance}">
	  <div class="errors">
		<g:renderErrors bean="${instrumentReceiptInstance}" as="list" />
	  </div>
	</g:hasErrors>
	<g:form action="save" >
	  <div class="dialog">
		<table>
		  <tbody>

			<tr class="prop">
			  <td valign="top" class="name">
				<label for="person"><g:message code="instrumentReceipt.person.label" default="Person" /></label>
			  </td>
			  <td valign="top" class="value ${hasErrors(bean: instrumentReceiptInstance, field: 'person', 'errors')}">
		  <g:link controller="person" action="edit" id="${instrumentReceiptInstance?.person?.id}" >${instrumentReceiptInstance?.person}
		  </g:link>
		  <g:hiddenField name="person.id" value="${instrumentReceiptInstance?.person?.id}" />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="instrument"><g:message code="instrumentReceipt.instrument.label" default="Instrument" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: instrumentReceiptInstance, field: 'instrument', 'errors')}">
		  <g:select name="instrument.id" from="${instrumentInstanceList}" optionKey="id" value="${instrumentReceiptInstance?.instrument?.id}"  />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="transactionDate"><g:message code="instrumentReceipt.transactionDate.label" default="Transaction Date" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: instrumentReceiptInstance, field: 'transactionDate', 'errors')}">
		  <g:datePicker name="transactionDate" precision="day" value="${instrumentReceiptInstance?.transactionDate}"  />
		  </td>
		  </tr>

		  <tr class="prop">
			<td valign="top" class="name">
			  <label for="outgoing"><g:message code="instrumentReceipt.outgoing.label" default="Outgoing" /></label>
			</td>
			<td valign="top" class="value ${hasErrors(bean: instrumentReceiptInstance, field: 'outgoing', 'errors')}">
		  <g:checkBox name="outgoing" value="${instrumentReceiptInstance?.outgoing}" />
		  </td>
		  </tr>

		  </tbody>
		</table>
	  </div>
	  <div class="buttons">
		<span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
	  </div>
	</g:form>
  </div>
</body>
</html>
