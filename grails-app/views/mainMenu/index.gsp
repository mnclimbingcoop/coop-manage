<html>
  <head>
	<title>Welcome</title>
	<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
	<link rel="stylesheet" type="text/css" href="${resource(dir:'css/le-frog',file:'jquery.ui.css')}" />
	<link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
  	<g:javascript library="jquery" plugin="jquery"/>
  	<g:javascript src="jquery/jquery.ui.js" />
  	<g:javascript src="jumpstart.js" />
	<style type="text/css" media="screen">
	  #dialog {
	    border-style: solid;
	    border-width: 2px;
	    margin-top: 0.5em;
	    margin-bottom: 0.5em;
	    padding: 0.25em;
	    -moz-border-radius: 0.5em;
	    -webkit-border-radius: 0.5em;
	    text-align: center;
	    margin-left:auto;
	    margin-right:auto;
	    width: 50%;
	    padding-top: 1em;
	    padding-bottom: 1em;
	    font-size: 2em;
	  }
	</style>
  </head>
  <body>
	<div id="dialog">
		Loading...<br/>
		<div id="result"></div>
	</div>
	<g:form name="ping" id="ping" action="dbping" />
	<g:form name="next" id="next" action="${destination}" />
  </body>
</html>
