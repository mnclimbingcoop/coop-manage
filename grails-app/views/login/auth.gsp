<html>
  <head>
	<title>MNCC Membership Management</title>
	<style type="text/css" media="screen">
		body {
			color: black;
		}
		#logo { 
			text-align: center;
			}
		legend {
			display: block;
			-webkit-padding-start: 2px;
			-webkit-padding-end: 2px;
			border: none;
		}
		fieldset {
			border-style: solid;
			border-width: 2px;
			margin-top: 0.5em;
			margin-bottom: 0.5em;
			padding: 0.25em;
			-moz-border-radius: 0.5em;
			-webkit-border-radius: 0.5em;
		}
		#mainMenu {
			text-align: center;
			margin-left: auto;
			margin-right: auto;
			}
	</style>
  	<g:javascript library="jquery" plugin="jquery"/>
	<script type='text/javascript'>
		$(document).ready(function() {
			$("input[name='j_username']").focus();
		});
	</script>
  </head>
  <body>
	<div id="pageBody">
		<div id="logo">
			<img src="${resource(dir:'images',file:'mncc-square-logo.png')}" alt="MNCC" />
		</div>
	  <div id="mainMenu" class="ui-widget ui-widget-content ui-corner-all" style="width:20em;">

		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
		  <div margin-left: auto; margin-right: auto;">
			<fieldset>
			  <legend>Please, do login.  Thank you.</legend>
			  <p>
				<label for='username'>Username</label>
				<input type='text' class='text_' name='j_username' id='username' />
			  </p>
			  <p>
				<label for='password'>Password</label>
				<input type='password' class='text_' name='j_password' id='password' />
			  </p>
			  <p>
				<input type='submit' value='Login' />
			  </p>
			</fieldset>
		  </div>
		</form>

		<br/>
	  </div>

	</div>
  </body>
</html>
