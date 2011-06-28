$(document).ready(function(){
	$('a').hover(function(){
		console.log("a.hover()");
		$(this).fadeTo('fast', 0.6, function(){
			$(this).fadeTo('fast', 1.0);
		});
	});
});