function getFiles(id){
		$.ajax({
			url: "/getDoc",
			data: {
				"id": id,
			},
			success: function(result){
				
				var mySelect = $("#uploadDoc"+id+" select");
				$("#uploadDoc"+id+" select").empty();
				var len = Object.keys(result).length;
				if(len > 0){
					$.each(result, function(val, text) {
					    mySelect.append(
					        $('<option></option>').val(id+"_"+text).html(text));
					})
				}
				else{
					mySelect.append(
					        $('<option selected disabled></option>').val("#").html("No documents available"));
				
			}
		}
		})
		
	}

function toggle(id){
	var item = $("#upload"+id);
	item.toggle("slow","linear");
}


$(document).ready(function(){
	
	$('.custom-file-input').on('change',function(){
		  var item = $(event.target).closest("input[type=file]");
		  var fileName = item[0].files[0].name;
		  $(this).next('.custom-file-label').addClass("selected").html(fileName);
	});
	
//	$("#add").on("click",function(){
//		console.log("fired");	
//		var item = $(event.target).parent().siblings("div").children("form");
//		item.toggle("slow","linear");
//	})
//	'
	
	 $('#loading-image').bind('ajaxStart', function(){
		    $(this).show();
		    console.log("yes");
		}).bind('ajaxStop', function(){
		    $(this).hide();
		});
	
	$("#preview").on("click",function(){
		
		var item = $(event.target).parent().siblings("select");
		var selected = item.children("option:selected").val();
		
		$.ajax({
			url: "/download",
			data:{
				"name": selected,
			},
			success:function(result){
				window.open("data:application/pdf," + result); 
			}
		})
		
	})
		

		
		
		
		
})