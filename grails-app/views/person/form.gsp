<fieldset>
  <legend>Demographics</legend>

<table>
  <tbody>

	<tr class="prop">
	  <td valign="top" class="name">
		<label for="nickName">Nick Name (optional)</label>
	  </td>
	  <td valign="top" class="name">
		<g:textField name="nickName" size="20" maxlength="30" value="${personInstance?.nickName}" />
	  </td>
	</tr>

	<tr class="prop">
	  <td valign="top" class="name">
		<label for="title"><g:message code="person.title.label" default="Title" /></label>
	  </td>
	  <td valign="top" class="name">
		<label for="firstName"><g:message code="person.firstName.label" default="First Name" /></label>
	  </td>
	  <td valign="top" class="name">
		<label for="middleName"><g:message code="person.middleName.label" default="Middle Name" /></label>
	  </td>
	  <td valign="top" class="name">
		<label for="lastName"><g:message code="person.lastName.label" default="Last Name" /></label>
	  </td>
	  <td valign="top" class="name">
		<label for="suffix"><g:message code="person.suffix.label" default="Suffix" /></label>
	  </td>
	</tr>

	<tr class="prop">

	  <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'title', 'errors')}">
  <g:textField name="title" size="10" maxlength="10" value="${personInstance?.title}" />
</td>
<td valign="top" class="value ${hasErrors(bean: personInstance, field: 'firstName', 'errors')}">
<g:textField name="firstName" size="20" maxlength="30" value="${personInstance?.firstName}" />
</td>
<td valign="top" class="value ${hasErrors(bean: personInstance, field: 'middleName', 'errors')}">
<g:textField name="middleName" size="10" maxlength="20" value="${personInstance?.middleName}" />
</td>
<td valign="top" class="value ${hasErrors(bean: personInstance, field: 'lastName', 'errors')}">
<g:textField name="lastName" size="20" maxlength="30" value="${personInstance?.lastName}" />
</td>
<td valign="top" class="value ${hasErrors(bean: personInstance, field: 'suffix', 'errors')}">
<g:textField name="suffix" size="10" maxlength="10" value="${personInstance?.suffix}" />
</td>

</tr>

<tr class="prop">
	  <td valign="top" class="name">
		<label for="birthDate"><g:message code="person.birthDate.label" default="Birth Date" /></label>
	  </td>
	  <td colspan="2" valign="top" class="value ${hasErrors(bean: personInstance, field: 'birthDate', 'errors')}">
  <g:datePicker name="birthDate" precision="day" value="${personInstance?.birthDate}" default="none" noSelection="['': '--']" />
</td>
</tr>
</tbody>
</table>
</fieldset>

<fieldset>
  <legend>Phone / Email</legend>

  <table>
	<tbody>
	  <tr class="prop">
		<td valign="top" class="name">
		  <label for="emailAddress"><g:message code="person.emailAddress.label" default="Email Address" /></label>
		</td>
		<td valign="top" class="value ${hasErrors(bean: personInstance, field: 'emailAddress', 'errors')}">
	<g:textField name="emailAddress" size="40" value="${personInstance?.emailAddress}" />
	</td>
	</tr>

	<tr class="prop">
		<td valign="top" class="name">
		  <label for="phoneNumber"><g:message code="person.phoneNumber.label" default="Phone Number" /></label>
		</td>
		<td valign="top" class="value ${hasErrors(bean: personInstance, field: 'phoneNumber', 'errors')}">
	<g:textField name="phoneNumber" size="15" value="${personInstance?.phoneNumber}" />
	</td>
	</tr>

	</tbody>
  </table>

</fieldset>

<fieldset>
  <legend>Mailing Address</legend>

  <table>
	<tbody>
	  <tr class="prop">
		<td valign="top" class="name">
		  <label for="address.address1"><g:message code="address.address1.label" default="Address" /></label>
		</td>
	  </tr>

	  <tr class="prop">
		<td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'address.address1', 'errors')}">
			<g:textField name="address.address1" size="64" maxlength="40" value="${addressInstance?.address1}" />
		</td>
	  </tr>
	  <tr class="prop">
		<td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'address2', 'errors')}">
		  <g:textField name="address.address2" size="64" maxlength="40" value="${addressInstance?.address2}" />
		</td>
	  </tr>
	</tbody>
  </table>

  <table>
	<tbody>
	<tr class="prop">
	  <td valign="top" class="name">
		<label for="address.city"><g:message code="address.city.label" default="City" /></label>
	  </td>
	  <td valign="top" class="name">
		<label for="address.state"><g:message code="address.state.label" default="State" /></label>
	  </td>

	  <td valign="top" class="name">
		<label for="address.zipcode"><g:message code="address.zipcode.label" default="Zip Code" /></label>
	  </td>
	</tr>

	<tr class="prop">
		<td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'city', 'errors')}">
		<g:textField name="address.city" size="30" maxlength="30" value="${addressInstance?.city}" />
		</td>
		  <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'state', 'errors')}">
		<g:textField name="address.state" size="4" maxlength="2" value="${addressInstance?.state}" />
		</td>
		  <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'zipcode', 'errors')}">
		<g:textField name="address.zipcode" size="8" maxlength="5" value="${addressInstance?.zipcode}" />
											-
		<g:textField name="address.zip4" size="7" maxlength="4" value="${addressInstance?.zip4}" />
		</td>
	</tr>
	</tbody>
  </table>

</fieldset>
