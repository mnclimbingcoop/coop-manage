
<%@ page import="coop.mnclimbing.Instrument" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'instrument.label', default: 'Form')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Instruments Due</h1>
			<p>This lists all non-obsolete instruments that are marked as required,
			and will list all people who are in need of one.</p>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>

			<h2>Required Forms</h2>
			<div id="tabs">
				<ul>
					<g:each var="i" in="${requiredInstruments}">
					<li><a href="#${i.name.replaceAll(' ','-')}">${i.name}</a></li>
					</g:each>
				</ul>
				<g:each var="i" in="${requiredInstruments}">
				<div id="${i.name.replaceAll(' ','-')}">
					<dl>
						<dt>Current Version</dt>
						<dd>${i.currentVersion}
						<g:if test="${i.versionDate}">, ${i.versionDate}</dd></g:if>
						<g:if test="${i.daysValidFor}">
							<dt>Valid for</dt>
							<dd>${i.daysValidFor} days</dd>
						</g:if>
						<h3>Members who have not filled out this form as long as it's been valid for.</h3>
						<p>Total: ${instrumentHaveNots[i.id]?.size()}</p>
						<ul>
						<g:each var="p" in="${instrumentHaveNots[i.id]}">
						<li><g:link controller="person" action="edit" id="${p.id}">${p}</g:link>
						(
						<g:link controller="instrumentReceipt" action="create" params="${[outgoing:false, 'person.id': p.id, 'instrument.id': i.id] }">Receive</g:link>
						)
						</li>
						</g:each>
						</ul>
					</dl>
				</div>
				</g:each>
			</div>
            
        </div>
    </body>
</html>
