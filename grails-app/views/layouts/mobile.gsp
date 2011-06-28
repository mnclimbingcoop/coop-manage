<html>
  <head>
	<title><g:layoutTitle default="Grails" /></title>
	<link rel="stylesheet" href="${resource(dir:'css',file:'mobile.css')}" />
	<link rel="stylesheet" type="text/css" href="${resource(dir:'css/sunny',file:'jquery.ui.css')}" />
	<link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
  	<g:javascript library="jquery" plugin="jquery"/>
  	<g:javascript src="jquery/jquery.ui.js" />
  <g:layoutHead />
</head>
<body>
  <div id="spinner" class="spinner" style="display:none;">
	<img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
  </div>
  <div id="coopLogo"><img src="${resource(dir:'images',file:'mncc-logo.png')}" alt="Minnesota Climbing Cooperative" border="0" width="451" height="155"/></div>
<g:layoutBody />
</body>
</html>