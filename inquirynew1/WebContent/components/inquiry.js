$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateinquiryForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidinquiryIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "inquirysAPI", 
 type : type, 
 data : $("#forminquiry").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 oninquirySaveComplete(response.responseText, status); 
 } 
 }); 
});

function oninquirySaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divinquirysGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidinquiryIDSave").val(""); 
$("#forminquiry")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidinquiryIDSave").val($(this).data("inquiryid")); 
		 $("#inquiryCode").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#inquiryName").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#inquiryPrice").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#inquiryDesc").val($(this).closest("tr").find('td:eq(3)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "inquirysAPI", 
		 type : "DELETE", 
		 data : "inquiryID=" + $(this).data("inquiryid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 oninquiryDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function oninquiryDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divinquirysGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateinquiryForm()
{
	// CODE
	if ($("#inquiryCode").val().trim() == "")
	{
	return "Insert inquiry Code.";
	}
	// NAME
	if ($("#inquiryName").val().trim() == "")
	{
	return "Insert inquiry Name.";
}

// PRICE-------------------------------
if ($("#inquiryPrice").val().trim() == ""){
	return "Insert inquiry Price.";
}
		// is numerical value
		var tmpPrice = $("#inquiryPrice").val().trim();
		if (!$.isNumeric(tmpPrice))
	{
	return "Insert a numerical value for inquiry Price.";
	}
		
// convert to decimal price
$("#inquiryPrice").val(parseFloat(tmpPrice).toFixed(2));

// DESCRIPTION------------------------
if ($("#inquiryDesc").val().trim() == ""){
	
	return "Insert inquiry Description.";
}
	return true;
}