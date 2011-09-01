<html>
  <head>
	<title>MNCC Membership Management</title>
	<meta name="layout" content="main" />
	<style type="text/css" media="screen">

	  #nav {
		margin-top:20px;
		margin-left:30px;
		width:228px;
		float:right;
		position: absolute;
		right: 40px;
		top: 15em;
		background-color: white;
		border: orange thin solid;
		opacity: 0.8;
	  }
	  #searchDiv {
	  	max-width: 650px;
	  }
	  .homePagePanel * {
		margin:0px;
	  }
	  .homePagePanel .panelBody ul {
		list-style-type:none;
		margin-bottom:10px;
	  }
	  .homePagePanel .panelBody h1 {
	  	color: darkorange;
		text-transform:uppercase;
		font-size:1.1em;
		margin-bottom:10px;
	  }
	  #logoutLink {
	  	float:right;
	  	padding-top: 0.5em;
	  	padding-right: 1em;
	  }
	  .homePagePanel .panelBody {
		margin:0px;
		padding:15px;
	  }
	  .homePagePanel .panelBtm {
		height:20px;
		margin:0px;
	  }

	  .homePagePanel .panelTop {
		height:11px;
		margin:0px;
	  }
	  h2 {
		padding-left: 0.5em;
		color: orange;
		margin-top:15px;
		margin-bottom:15px;
		font-size:1.2em;
	  }
	  #pageBody {
		margin-left:20px;
		margin-right:20px;
	  }
	</style>
	<script>
	$(function() {
		$("input[name='id']").focus();		
	});
	</script>
  </head>
  <body>
	<div id="nav">
	  <div class="homePagePanel">
		<div class="panelTop"></div>
		<div class="panelBody">
		  <h1>Notifications</h1>
		  <dl>
			<dt>Members: ${countMembers}</dt>
			<dt>Sold Passes: ${countSoldPasses}</dt>
			<dt>Active Passes: ${countActivePasses}</dt>
			<dt>Passes Expiring: ${countExpiringPasses}</dt>
			<dt>Outstanding Stock: &#36;${stockTotal}</dt>
		  </dl>
		  <br/>
		  <h1><g:link controller="hidDoorEvent" action="list" params="${ [ sort:'eventDate', order:'desc' ] }">Recent Activity</g:link></h1>
		  <dl>
		  	<g:each var="e" in="${hidDoorEventInstanceList}">
			<dt>
				<g:formatDate date="${e.eventDate}" format="MM/dd HH:mm" />@${e.doorName}
				<dd style="margin-left:2em;">${e.eventSubject}</dd>
			</dt>
		  	</g:each>
		  </dl>
		</div>
		<div class="panelBtm"></div>
	  </div>
	</div>
	<div id="pageBody">
	  <h1>
	  	Minnesota Climbing Cooperative
	  	<g:remoteField autocomplete="off" class="filterField" name="id" controller="person" action="find" update="searchDiv" />
	  </h1>
	  <div id="searchDiv"></div>
	  <div id="logoutLink"><g:link controller="logout" action="index" style="color:#FAA;">Logout</g:link></div>
	  <div id="mainMenu" class="ui-widget ui-widget-content ui-corner-all">

		<h2>Enter / Update Data</h2>
		<ul>
		  <li>
		  <g:link controller="person" action="list">Manage People</g:link>
		  </li>
		  <li>
		  	<g:link controller="instrument" action="list">Manage Forms</g:link>
		  </li>
		  <li>
		  	<g:link controller="paymentType" action="list">Payment Types</g:link>
		  </li>
		  <li>
			<g:link controller="dayPass" action="list">Day Passes</g:link>
		  </li>
		  <li>
			<g:link controller="signIn" action="list">Sign-In Sheets</g:link>
		  </li>
		  <li>
		  	<g:link controller="accessCard" action="list">Add/Remove Access Cards</g:link>
		  </li>
		  <li>
		  	<g:link controller="purchase" action="list">Enter Purchases</g:link>
		  </li>
		</ul>


		<h2>Membership Reports</h2>
		<ul>
		  <li>
			<g:link controller="membership" action="list">Members</g:link>
		  </li>

		  <li>
			<g:link controller="membership" action="missing">Non-Members</g:link>
		  </li>

		  <li>
			<g:link controller="access" action="missing">Members without an Access Pass</g:link>
		  </li>
		</ul>

		<h2>Access Reports</h2>
		<ul>
		  <li>
			<g:link controller="access" action="list">All Passes</g:link>
		  </li>

		  <li>
			<g:link controller="access" action="active">Active Passes</g:link>
		  </li>

		  <li>
		  <g:link controller="access" action="expired">Expired Passes</g:link>
		  </li>

		  <li>
		  <g:link controller="access" action="expiring">Expiring Passes</g:link>
		  </li>

		  <li>
		  	<g:link controller="accessCardAssignment" action="missing">Pass Holders w/o Access Card</g:link>
		  </li>

		  <li>
		  	<g:link controller="automobile" action="list">List All Member Cars</g:link>
		  </li>

		</ul>

		<h2>Form Reports</h2>
		<ul>
		  <li>
		  <g:link controller="instrument" action="show" id="1">Need to sign Membership Agreement</g:link>
		  </li>
		  <li>
		  <g:link controller="instrument" action="show" id="2">Need to sign Gym Waiver</g:link>
		  </li>
		</ul>

		<h2>Financial Reports</h2>
		<ul>
		  <li>
		  	<g:link controller="stock" action="list">List All Stock Purchases</g:link>
		  </li>
		  <li>
		  	<g:link controller="donation" action="list">List All Donations</g:link>
		  </li>
		</ul>

		<h2>Data Export</h2>
		<ul>
		  <li>
			<g:link controller="export" action="contacts">Export All Contacts</g:link>
		  </li>
		  <li>
			<g:link controller="export" action="payments">Export All Payments</g:link>
		  </li>
		  <li>
			<g:link controller="export" action="purchases">Export All Purchases</g:link>
		  </li>
		  <li>
			<g:link controller="export" action="signIn">Export All Sign-In Data</g:link>
		  </li>
		  <li>
			<g:link controller="export" action="cars">Export Car Data</g:link>
		  </li>
		  <li>
		  <g:link controller="hid" action="xml">Download HID EdgePro XML data file</g:link> (backupxml.xml)
		  </li>
		  <li>
		  <g:link controller="hid" action="buildBob">Download script to create backup BOB from XML file</g:link> (makes, backupxml.bob)
		  </li>
		</ul>

		<h2>Door Control (only works at the coop)</h2>
		<ul>
		  <li>
			<a href="https://10.19.4.130/html/en_EN/EdgeSolo.html">Gym Door</a>
		  </li>
		  <li>
			<a href="https://10.19.4.129/html/en_EN/EdgeSolo.html">External Door</a>
		  </li>
		</ul>

		<br/>
	  </div>

	</div>
  </body>
</html>
