
$(document).ready(function(){

	var Ids = new Object();
	var $input = $("#searchUser");
	$input.typeahead({
		hint: true,
		highlight: true, /* Enable substring highlighting */
		minLength: 1,
		source: function(query,process){
			$.ajax({
				url: "/all",
				dataType: "JSON",
			})
			.done(function(response){
				var users = [];
				$.each(response, function(val, text) {
					users.push(text);
					var map1 = new Map([["id",val],["name",text]]);
					Ids[text] = val;
				})
				
				 $(".dropdown-menu").css("width", "90%");
                 $(".dropdown-menu").css("height", "auto");
                 $(".dropdown-menu").css("font", "15px Verdana");
				
				return process(users);
			})
		}
	});
	$input.change(function() {
	  var current = $input.typeahead("getActive");
	  if (current) {
		  console.log(Ids[current]);
	  } else {
	    // Nothing is active so it is a new value (or maybe empty value)
	  }
	});
});
