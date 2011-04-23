<html>
  <head>
	<title>MNCC Membership Management</title>
	<style type="text/css" media="screen">
		body {
			color: green;
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
	</style>
	<script type='text/javascript'>
(function(){
	document.forms['loginForm'].elements['j_username'].focus();
})();
	</script>

  </head>
  <body>
	<div id="pageBody">
	  <h1>Authentication Required</h1>

	  <div id="mainMenu" class="ui-widget ui-widget-content ui-corner-all" style="width:20em;">

		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
		  <div margin-left: auto; margin-right: auto;">
			<fieldset>
			  <legend>Login</legend>
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
