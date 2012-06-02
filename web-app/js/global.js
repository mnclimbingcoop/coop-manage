$(document).ready(function(){
	$('a').hover(function(){
		console.log("a.hover()");
		$(this).fadeTo('fast', 0.6, function(){
			$(this).fadeTo('fast', 1.0);
		});
	});
});

function sendEmails(callingElement) {
	$('#spinner').show();
	$('#sent-expiring').html("Sending emails...");
	$('a.sendEmailLink').parent('div').hide('slow');
	return true;
}

$(function() {
	$("#tabs").tabs();
	$("input[name='id']").focus();		
});
  
