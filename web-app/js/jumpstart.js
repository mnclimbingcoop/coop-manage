 $(document).ready(function(){
   // Load some stuff
	 var url = $("#ping").attr("action");
	 var menuUrl = $("#next").attr("action");
	 
	 $("#result").load(url, function(){
		// simulates similar behavior as an HTTP redirect
		 window.location.replace(menuUrl);
	 });
});