<fieldset>
  <legend>Contact / Member</legend>
  <table>
	<tbody>

	  <tr class="prop">
		<td valign="top" class="name">
		  <label for="person"><g:message code="access.person.label" default="Person" /></label>
		</td>
		<td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'person', 'errors')}">
	<g:link controller="person" action="edit" id="${emergencyContactInstance?.person?.id}" >
	  ${emergencyContactInstance?.person}
	</g:link>
	<g:hiddenField name="person.id" value="${emergencyContactInstance?.person?.id}" />
	</td>
	</tr>
	</body>
  </table>
</fieldset>


<fieldset>
  <legend>Relation</legend>
  <table>
	<tbody>
	  <tr class="prop">
		<td valign="top" class="name">
		  <label for="relation">Relation to ${emergencyContactInstance.person.firstName}</label>
		</td>
		<td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'phoneNumber', 'errors')}">
	<g:textField name="relation" size="10" value="${emergencyContactInstance?.relation}" />
	</td>
	</tr>
	</body>

  </table>
</fieldset>



<fieldset>
  <legend>Name</legend>

  <table>
	<tbody>

	  <tr class="prop">
		<td valign="top" class="name">
		  <label for="title"><g:message code="emergencyContact.title.label" default="Title" /></label>
		</td>
		<td valign="top" class="name">
		  <label for="firstName"><g:message code="emergencyContact.firstName.label" default="First Name" /></label>
		</td>
		<td valign="top" class="name">
		  <label for="middleName"><g:message code="emergencyContact.middleName.label" default="Middle Name" /></label>
		</td>
		<td valign="top" class="name">
		  <label for="lastName"><g:message code="emergencyContact.lastName.label" default="Last Name" /></label>
		</td>
		<td valign="top" class="name">
		  <label for="suffix"><g:message code="emergencyContact.suffix.label" default="Suffix" /></label>
		</td>

	  </tr>

	  <tr class="prop">

		<td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'title', 'errors')}">
	<g:textField name="title" size="10" maxlength="10" value="${emergencyContactInstance?.title}" />
	</td>
	<td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'firstName', 'errors')}">
	<g:textField name="firstName" size="20" maxlength="30" value="${emergencyContactInstance?.firstName}" />
	</td>
	<td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'middleName', 'errors')}">
	<g:textField name="middleName" size="10" maxlength="20" value="${emergencyContactInstance?.middleName}" />
	</td>
	<td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'lastName', 'errors')}">
	<g:textField name="lastName" size="20" maxlength="30" value="${emergencyContactInstance?.lastName}" />
	</td>
	<td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'suffix', 'errors')}">
	<g:textField name="suffix" size="10" maxlength="10" value="${emergencyContactInstance?.suffix}" />
	</td>

	</tr>
	</tbody>
  </table>
</fieldset>

<fieldset>
  <legend>Phone</legend>
  <table>
	<tr class="prop">
	  <td valign="top" class="name">
		<label for="phoneNumber"><g:message code="emergencyContact.phoneNumber.label" default="Phone Number" /></label>
	  </td>
	  <td valign="top" class="value ${hasErrors(bean: emergencyContactInstance, field: 'phoneNumber', 'errors')}">
	<g:textField name="phoneNumber" size="14" value="${emergencyContactInstance?.phoneNumber}" />
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
		<td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'address.address1', 'errors')}">
	<g:textField name="address.address1" size="40" maxlength="40" value="${addressInstance?.address1}" />
	</td>
	</tr>

	<tr class="prop">
	  <td valign="top" class="name"></td>
	  <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'address2', 'errors')}">
	<g:textField name="address.address2" size="40" maxlength="40" value="${addressInstance?.address2}" />
	</td>
	</tr>

	<tr class="prop">
	  <td valign="top" class="name">
		<label for="address.city"><g:message code="address.city.label" default="City" /></label>
	  </td>
	  <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'city', 'errors')}">
	<g:textField name="address.city" size="30" maxlength="30" value="${addressInstance?.city}" />
	</td>
	</tr>

	<tr class="prop">
	  <td valign="top" class="name">
		<label for="address.state"><g:message code="address.state.label" default="State" /></label>
	  </td>
	  <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'state', 'errors')}">
	<g:textField name="address.state" size="4" maxlength="2" value="${addressInstance?.state}" />
	</td>
	</tr>

	<tr class="prop">
	  <td valign="top" class="name">
		<label for="address.zipcode"><g:message code="address.zipcode.label" default="Zip Code" /></label>
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
