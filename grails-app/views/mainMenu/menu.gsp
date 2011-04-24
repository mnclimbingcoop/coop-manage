<html>
  <head>
	<title>MNCC Membership Management</title>
	<meta name="layout" content="main" />
	<style type="text/css" media="screen">

	  #nav {
		margin-top:20px;
		margin-left:30px;
		width:228px;
		float:left;

	  }
	  .homePagePanel * {
		margin:0px;
	  }
	  .homePagePanel .panelBody ul {
		list-style-type:none;
		margin-bottom:10px;
	  }
	  .homePagePanel .panelBody h1 {
		text-transform:uppercase;
		font-size:1.1em;
		margin-bottom:10px;
	  }
	  .homePagePanel .panelBody {
		background: url(images/leftnav_midstretch.png) repeat-y top;
		margin:0px;
		padding:15px;
	  }
	  .homePagePanel .panelBtm {
		background: url(images/leftnav_btm.png) no-repeat top;
		height:20px;
		margin:0px;
	  }

	  .homePagePanel .panelTop {
		background: url(images/leftnav_top.png) no-repeat top;
		height:11px;
		margin:0px;
	  }
	  h2 {
		margin-top:15px;
		margin-bottom:15px;
		font-size:1.2em;
	  }
	  #pageBody {
		margin-left:280px;
		margin-right:20px;
	  }
	</style>
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
		</div>
		<div class="panelBtm"></div>
	  </div>
	</div>
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

		<h2 style="color:#dfd;">Data Export</h2>
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
		  <g:link controller="hid" action="xml">Download HID EdgePro XML data file</g:link> (backupxml.xml)
		  </li>
		  <li>
		  <g:link controller="hid" action="buildBob">Download script to create backup BOB from XML file</g:link> (makes, backupxml.bob)
		  </li>
		</ul>


		<br/>
	  </div>

	</div>
  </body>
</html>
