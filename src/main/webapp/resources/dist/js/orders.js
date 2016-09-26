function refreshOrderPage(){
	
	var projectsUrl = getBaseUrl()+"/projects.html";
	var data = "startIndex=1&maxResult=10";
	AjaxReloadProjectsTable(projectsUrl, data);
}
function orderRecordselected(){
	    var element = document.getElementById("orderRecordsOptions");
	    var record = element.options[element.selectedIndex].text;
		var pageSize = $("#orderRecordsOptions option:selected").text();
	    var ordersUrl = getBaseUrl()+"/orders.html";
	    var data = "page=1&pageSize="+pageSize;
	    AjaxReloadOrdersTable(ordersUrl, data);
} 


function showPagination(totalPages){

    var ordersUrl = getBaseUrl()+"/orders.html";
  
    $('.dataTables_paginate,.paging_simple_numbers').bootpag({
    	total: totalPages,
    	page: 1,
    	maxVisible: 10,
    	leaps: true,
    	firstLastUse: true,
    	first: 'First',
    	last: 'Last',
    	wrapClass: 'pagination',
    	activeClass: 'active',
    	disabledClass: 'disabled',
    	nextClass: 'next',
    	prevClass: 'prev',
    	lastClass: 'last',
    	firstClass: 'first'
    	
    	}).on("page", function(event, num){
    			
    		var data = "page="+num+"&maxResult="+totalPages;
    		AjaxReloadOrdersTable(ordersUrl, data);
    	});

}

function AjaxReloadOrdersTable(url, data){
	
	$.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function(response) {
        	
        	$("#orderTableWrapper").html(response); // here i need to bind the response list to doctor combobox item.
        },
        error: function(error) {
            alert('Failed to get details: ' + error);
        }
    });
}
function getBaseUrl(){
	var getUrl = window.location;
	var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
	return baseUrl;
}