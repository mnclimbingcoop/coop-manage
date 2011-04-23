<html>
  <head>
	<title>MNCC Membership Management</title>
	<meta name="layout" content="mobile" />
  </head>
  <body>
	<div id="pageBody">
	  <h1>Minnesota Climbing Cooperative</h1>
	  <div style="float:right;"><g:link controller="logout" action="index" style="color:#FAA;">Logout</g:link></div>
	  <div id="mainMenu" class="ui-widget ui-widget-content ui-corner-all">

		<h2 style="color:#dfd;">Enter / Update Data</h2>
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
		  	<g:link controller="accessCard" action="list">Add/Remove Access Cards</g:link>
		  </li>
		  <li>
		  	<g:link controller="purchase" action="list">Enter Purchases</g:link>
		  </li>
		</ul>


		<h2 style="color:#dfd;">Membership Reports</h2>
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

		<h2 style="color:#dfd;">Access Reports</h2>
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

		<h2 style="color:#dfd;">Form Reports</h2>
		<ul>
		  <li>
		  <g:link controller="instrument" action="show" id="1">Need to sign Membership Agreement</g:link>
		  </li>
		  <li>
		  <g:link controller="instrument" action="show" id="2">Need to sign Gym Waiver</g:link>
		  </li>
		</ul>

		<h2 style="color:#dfd;">Financial Reports</h2>
		<ul>
		  <li>
		  	<g:link controller="stock" action="list">List All Stock Purchases</g:link>
		  </li>
		  <li>
		  	<g:link controller="donation" action="list">List All Donations</g:link>
		  </li>
		</ul>

		<br/>
	  </div>

	</div>
  </body>
</html>
