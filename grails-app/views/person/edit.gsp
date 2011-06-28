<%@ page import="coop.mnclimbing.Person" %>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
  <title><g:message code="default.edit.label" args="[entityName]" /></title>
  <script>
	$(function() {
		$("#tabs").tabs();
		$("input[name='id']").focus();		
	});
  </script>
</head>
<body>
  <div class="nav">
	<span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
	<span class="menuButton"><g:link class="list" action="list">All Contacts</g:link></span>
	<span class="menuButton"><g:link class="create" action="create">New Contact</g:link></span>
  </div>
  <div class="body">

	<!-- Page Header -->

	<h1>
		${personInstance.fullName}, MNCCID: ${personInstance.id}
		<g:remoteField autocomplete="off" class="filterField" name="id" action="find" update="searchDiv" />
	</h1>

	<g:if test="${personInstance.memberships}">
	  <p>
	  <g:each in="${personInstance.memberships}" status="j" var="membershipInstance">
		  member since
		<g:formatDate date="${membershipInstance.membershipFrom}" format="yyyy-MM-dd" />
	  </g:each>
	  </p>
	</g:if>

	<div class="ui-widget ui-widget-content ui-corner-all">
	  <g:if test="${personInstance.user}">
		<h2>Username: ${personInstance.user.username}</h2>
		<p style="padding-left: 0.5em;"><g:link controller="user" action="edit" id="${personInstance.user.id}">Edit Account</g:link></p>
	  </g:if>
	  <g:else>
		<h2>No system account</h2>
		<p>Click <g:link controller="user" action="create" params="${[ 'person.id': personInstance.id ]}">here</g:link> to create a login account for the system.</p>
	  </g:else>
	</div>

	<div id="searchDiv"></div>

	<g:if test="${flash.message}">
	  <div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${personInstance}">
	  <div class="errors">
		<g:renderErrors bean="${personInstance}" as="list" />
	  </div>
	</g:hasErrors>
	<g:form method="post" autocomplete="off" >
	  <g:hiddenField name="id" value="${personInstance?.id}" />
	  <g:hiddenField name="version" value="${personInstance?.version}" />

	  <div class="dialog">

		<!-- Begin Tabs -->

		<div id="tabs">

		  <ul>
			<li><a href="#tab-access">Access</a></li>
			<li><a href="#tab-forms">Paperwork</a></li>
			<li><a href="#tab-contact">Contact</a></li>
			<li><a href="#tab-emergency">Emergency</a></li>
			<li><a href="#tab-cars">Cars</a></li>
			<li><a href="#tab-donate">Donations</a></li>
			<li><a href="#tab-stock">Stock</a></li>
		  </ul>

		  <!-- List Access Information	-->
		  <div id="tab-access">
		  
			<h3>Membership</h3>
			<ul>
				<g:if test="${personInstance.memberships}">
				  <g:each var="m" in="${personInstance.memberships}">
					<li>
	${m.type.name} member since <g:formatDate date="${m.membershipFrom}" format="yyyy-MM-dd" />
					(<g:link controller="membership" action="edit" id="${m.id}">edit</g:link>)
					</li>
				  </g:each>
			  </g:if>
			  <g:else>
				  <li>
				  <g:link controller="membership" action="create" params="${['person.id': personInstance.id]}">Purchase Membership</g:link>
				  </li>
			  </g:else>
			</ul>
		  
		  	<h3>Access Passes</h3>
			<ul>
			  <g:each var="a" in="${personInstance.passes}">
				<li>
${a.accessType.name} pass from 
				<g:formatDate date="${a.startDate}" format="yyyy-MM-dd" />
				to
				<g:formatDate date="${a.endDate}" format="yyyy-MM-dd" />
				(<g:link controller="access" action="edit" id="${a.id}">edit</g:link>)
				</li>
			  </g:each>
			  <li>
			  <g:link controller="access" action="create" params="${['person.id': personInstance.id]}">Purchase Access Pass</g:link>
			  </li>
			</ul>

			<h3>Key-Fobs and Key-Cards</h3>
			<ul>
			  <g:each var="c" in="${personInstance.cards}">
				<li>
					${c.accessCard}, issued on <g:formatDate date="${c.issueDate}" format="yyyy-MM-dd" />
				(<g:link controller="accessCardAssignment" action="edit" id="${c.id}">edit</g:link>)
				</li>
			  </g:each>
			  <li>
			  <g:link controller="accessCardAssignment" action="create" params="${['person.id': personInstance.id]}">Assign Access Card/Key-Fob</g:link>
			  </li>
			</ul>

			<g:if test="${instrumentInstanceList}">
			<h3>Needed Forms</h3>
			<ul>
				<g:each var="f" in="${instrumentInstanceList}">
				<li>
					<g:link controller="instrumentReceipt" action="create" params="${['person.id': personInstance.id, 'outgoing':false, 'instrument.id':f.id ]}">
						${f}
					</g:link>
				</li>
				</g:each>
			</ul>
			</g:if>
			
			<g:if test="${ ( ! personInstance.emergencyContacts && ( personInstance.memberships || personInstance.passes) ) }">
				<h3>Needs Emergency Contact</h3>
				<ul>
					<li>
					<g:link controller="emergencyContact" action="create" params="${['person.id': personInstance.id]}">Add Emergency Contact</g:link>
					</li>
				</ul>
			</g:if>
			
			<g:if test="${ ( ! personInstance.automobiles && ( personInstance.passes) ) }">
				<h3>No Car Information</h3>
				<ul>
					<li>
					<g:link controller="automobile" action="create" params="${['person.id': personInstance.id]}">No Car Information</g:link>
					</li>
				</ul>
			</g:if>

		  </div>

		  <!-- Forms -->
		  <div id="tab-forms">
		  
		  	<h3>Forms Received or Mailed</h3>
			<ul>
			  <g:each var="f" in="${personInstance.forms}">
				<li>
${f.instrument.name}
				<g:if test="${f.outgoing}">
				  send
				</g:if>
				<g:else>
				  received
				</g:else>
				on <g:formatDate date="${f.transactionDate}" format="yyyy-MM-dd" />
				(<g:link controller="instrumentReceipt" action="edit" id="${f.id}">edit</g:link>)
				</li>
			  </g:each>
			  <li>
			  <g:link controller="instrumentReceipt" action="create" params="${['person.id': personInstance.id]}">Send</g:link>
			or
			  <g:link controller="instrumentReceipt" action="create" params="${['person.id': personInstance.id, 'outgoing':false]}">Receive</g:link>
			Form
			  </li>
			</ul>
			
			<g:if test="${instrumentInstanceList}">
			<h3>Needed Forms</h3>
			<ul>
				<g:each var="f" in="${instrumentInstanceList}">
				<li>
					<g:link controller="instrumentReceipt" action="create" params="${['person.id': personInstance.id, 'outgoing':false, 'instrument.id':f.id ]}">${f}</g:link>
				</li>
				</g:each>
			</ul>
			</g:if>
		  </div>

		  <!-- List Emergency Contacts -->
		  <div id="tab-emergency">
			<ul>
			  <g:each var="e" in="${personInstance.emergencyContacts}">
				<li>
${e.fullName}, ${e.relation}, ${e.phoneNumber}
				(<g:link controller="emergencyContact" action="edit" id="${e.id}">edit</g:link>)
				</li>
			  </g:each>
			  <li>
			  <g:link controller="emergencyContact" action="create" params="${['person.id': personInstance.id]}">New Emergency Contact</g:link>
			  </li>
			</ul>
		  </div>

		  <!-- List Cars -->
		  <div id="tab-cars">
			<ul>
			  <g:each var="a" in="${personInstance.automobiles}">
				<li>
					${a}
				(<g:link controller="automobile" action="edit" id="${a.id}">edit</g:link>)
				</li>
			  </g:each>
			  <li>
			  <g:link controller="automobile" action="create" params="${['person.id': personInstance.id]}">Add Car</g:link>
			  </li>
			</ul>
		  </div>

		  <!-- List Donations	-->
		  <div id="tab-donate">
			<ul>
			  <g:each var="d" in="${personInstance.donations}">
				<li>
				$${d.amount} donation on <g:formatDate date="${d.paymentDate}" format="yyyy-MM-dd" />
				(<g:link controller="donation" action="edit" id="${d.id}">edit</g:link>)
				</li>
			  </g:each>
			  <li>
			  <g:link controller="donation" action="create" params="${['person.id': personInstance.id]}">New Donation</g:link>
			  </li>
			</ul>
		  </div>

		  <!-- List Stock Purchases -->
		  <div id="tab-stock">
			<ul>
			  <g:each var="s" in="${personInstance.stock}">
				<li>
				<g:if test="${s.amount > 0}">
				  $${s.amount} in ${s.classOfStock.name} stock.
				  (<g:link controller="stock" action="edit" id="${s.id}">edit</g:link>)
				</g:if>
				<g:else>
				  Repayment of $${s.amount} in ${s.classOfStock.name} stock.
				  (<g:link controller="stock" action="edit" id="${s.id}">edit</g:link>)
				</g:else>
				</li>
			  </g:each>
			  <li>
			  <g:link controller="stock" action="create" params="${['person.id': personInstance.id]}">Purchase or Repay Stock</g:link>
			  </li>
			</ul>
		  </div>

		  <div id="tab-contact">
			<g:include action="form" model="${[personInstance:personInstance, addressInstance:addressInstance ]}" />
		  </div>

		</div>

	  </div>

	  <div class="buttons">
		<span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
		<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
	  </div>
	</g:form>
  </div>
</body>
</html>
