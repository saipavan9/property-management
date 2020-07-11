$(document).ready(function(){
	
	var valid1 = true;
	var valid2 = true;
	
	$("#renter-email").on('blur',function(){
		console.log("fired");
		var email = $("#renter-email").val();
		
		if (email == '') {
		 return;
		}
		
		
//		fetch("/validEmail",{
//			method: "POST",
//			body: JSON.stringify({
//				"email": email,
//				"type": "renter"
//			})
//		})
//		.then(response => response.json())
//		.then(json => console.log(json));
		
		$.ajax({
			  url: "/validEmail",
			  type: "POST",
			  data: {
			    "email": email,
			    "type": "renter",
			  },
			  success: function( result ) {
				  
				  if(result == "true") {
					  $("#valid-emailr").show();
					  valid1 = false;
				  }
				  else $("#valid-emailr").hide();
				  
				  
					if(!valid1) {
						$("#renter-submit").attr("disabled", true);
					}else{
						$("#renter-submit").attr("disabled", false);
					}
			  }
			});
		
	});
	
	$("#renter-username").on('blur',function(){
		
		var username = $("#renter-username").val();
		
		if (username == '') { return;}
		
		$.ajax({
			  url: "/validUsername",
			  type: "POST",
			  data: {
			    "username": username,
			    "type": "renter",
			  },
			  success: function( result ) {
				  if(result == "true") {
					  $("#valid-userr").show();
					  valid1 = false;
				  }
				  else $("#valid-userr").hide();
				  
					if(!valid1) {
						$("#renter-submit").attr("disabled", true);
					}else{
						$("#renter-submit").attr("disabled", false);
					}
			  }
			});
		
	});
	
	$("#tenant-email").on('blur',function(){
		
		var email = $("#tenant-email").val();
		
		if (email == '') {return;}
		
		$.ajax({
			  url: "/validEmail",
			  type: "POST",
			  data: {
			    "email": email,
			    "type": "tenant",
			  },
			  success: function( result ) {
//				  console.log(result);
				  if(result == "true") {
					  valid2 = false;
					  $("#valid-emailt").show();
				  }
				  
				  else $("#valid-emailt").hide();
			  }
			});
		
	});
	
	$("#tenant-username").on('blur',function(){
		
		var username = $("#tenant-username").val();
		
		if (username == '') {return;}
		
		$.ajax({
			  url: "/validUsername",
			  type: "POST",
			  data: {
			    "username": username,
			    "type": "tenant",
			  },
			  success: function( result ) {
				  if(result == "true") {
					  valid2 = false;
					  $("#valid-usert").show();
				  }
				  else $("#valid-usert").hide();
			  }
			});
		
	});
	

	
	
	if(!valid2){
		$("#tenant-submit").attr("disabled", true);
	}else{
		$("#tenant-submit").attr("disabled", false);
	}
	
	
	
	
	
	
	

	$("#upload").hide();

	
 $("#add").one("click",function(){
	 $("#upload").toggle();
 })
	
});





