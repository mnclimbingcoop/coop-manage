<%@ page import="coop.mnclimbing.DayPass" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dayPass.label', default: 'DayPass')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script>
		$(function() {
			$("input[name='name']").autocomplete({
				source: ${memberNames},
				select: function() { 
					$("select[name='sponsor']").focus(); }
				});
			$("input[name='name']").focus();	
		});
	  </script>
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
            <g:hasErrors bean="${dayPassInstance}">
            <div class="errors">
                <g:renderErrors bean="${dayPassInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="passDate"><g:message code="dayPass.passDate.label" default="Pass Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'passDate', 'errors')}">
                                    <g:datePicker name="passDate" precision="day" value="${dayPassInstance?.passDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="dayPass.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${dayPassInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sponsor"><g:message code="dayPass.sponsor.label" default="Sponsor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'sponsor', 'errors')}">
                                    <g:select name="sponsor.id" from="${sponsorList}" optionKey="id" value="${dayPassInstance?.sponsor?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comments"><g:message code="dayPass.comments.label" default="Comments" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'comments', 'errors')}">
                                    <g:textField name="comments" value="${dayPassInstance?.comments}" />
                                </td>
                            </tr>                        

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="dayPass.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'amount', 'errors')}">
                                    <g:textField size="8" name="amount" value="${fieldValue(bean: dayPassInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentType"><g:message code="dayPass.paymentType.label" default="Payment Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'paymentType', 'errors')}">
                                    <g:select name="paymentType.id" from="${coop.mnclimbing.PaymentType.list()}" optionKey="id" value="${dayPassInstance?.paymentType?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="transactionId">Check # / Transaction ID</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'transactionId', 'errors')}">
                                    <g:textField name="transactionId" value="${dayPassInstance?.transactionId}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="taxable"><g:message code="dayPass.taxable.label" default="Taxable" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: dayPassInstance, field: 'taxable', 'errors')}">
                                    <g:checkBox name="taxable" value="${dayPassInstance?.taxable}" />
                                    at <label for="taxRate"><g:message code="dayPass.taxRate.label" default="Rate" /></label>
                                    <g:textField size="8" name="taxRate" value="${fieldValue(bean: dayPassInstance, field: 'taxRate')}" />
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
