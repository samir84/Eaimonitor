function refreshProjectPage(){
	
	var projectsUrl = getBaseUrl()+"/projects.html";
	var data = "startIndex=1&maxResult=10";
	AjaxReloadProjectsTable(projectsUrl, data);
}

function projectFullTextSearch(){
	
	var searchTerm = $(+"#searchProjectsText").val();
	//var url = "http://localhost:8080/eaimonitor/projects.html/search";
	var searchUrl = getBaseUrl()+"/projects.html/search";
	var data = "q="+searchTerm;
	AjaxReloadProjectsTable(searchUrl, data);
	//
}
function projectRecordselected(){
	    //var element = document.getElementById("projectRecordsOptions");
	    //var record = element.options[element.selectedIndex].text;
		var record = $("#projectRecordsOptions option:selected").text();
	    var projectsUrl = getBaseUrl()+"/projects.html";
	    var data = "startIndex=1&maxResult="+record;
	    AjaxReloadProjectsTable(projectsUrl, data);
} 
function PaginationProjects(startIndex, maxResults){
	
	 var projectsUrl = getBaseUrl()+"/projects.html";
    var data = "startIndex="+startIndex+"&maxResult="+maxResults;
    AjaxReloadProjectsTable(projectsUrl, data);
}
function AjaxReloadProjectsTable(url, data){
	
	$.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function(response) {
        	
        	$("#projectTableWrapper").html(response); // here i need to bind the response list to doctor combobox item.
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