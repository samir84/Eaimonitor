<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<meta content="Henry Schein Eai monitor" name="description">

<title>Henry Schein Eai monitor</title>
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" />
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<c:url value="/resources/font-awesome/4.6.3/css/font-awesome.min.css"/>" />
<!-- Ionicons -->
<link rel="stylesheet"
	href="<c:url value="/resources/ionicons/2.0.1/css/ionicons.min.css"/>" />
<!-- DataTables -->
  <link rel="stylesheet" href="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.css"/>" />

<!-- Project style -->
<link rel="stylesheet"
	href="<c:url value="/resources/dist/css/project.css"/>" />
<!-- Theme style -->
<link rel="stylesheet"
	href="<c:url value="/resources/dist/css/AdminLTE.css"/>" />
<!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="<c:url value="/resources/dist/css/skins/_all-skins.min.css"/>" />
<!-- iCheck -->
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/iCheck/flat/blue.css"/>" />
<!-- Morris chart -->
<%-- <link rel="stylesheet"
	href="<c:url value="/resources/plugins/morris/morris.css"/>" /> --%>

<!-- jvectormap -->
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css"/>" />
<!-- Date Picker -->
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/datepicker/datepicker3.css"/>" />
<!-- Daterange picker -->
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/daterangepicker/daterangepicker-bs3.css"/>" />
  <!-- Bootstrap time Picker -->
  <link rel="stylesheet" href="<c:url value="/resources/plugins/timepicker/bootstrap-timepicker.min.css"/>" />
	<!--  Select2 -->
 <link rel="stylesheet" href="<c:url value="/resources/plugins/select2/select2.min.css"/>" />
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="<c:url value="/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/plugins/fullcalendar/fullcalendar.css"/>" />
  <link rel="stylesheet" href="<c:url value="/resources/plugins/fullcalendar/fullcalendar.print.css" />" media="print" />
  
  <!-- Theme style -->
<link rel="stylesheet"
	href="<c:url value="/resources/dist/css/AdminLTE.min.css"/>" />
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="mainSidebar" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>

	<!-- jQuery 2.2.0 -->
	<script src="<c:url value="/resources/plugins/jQuery/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
	<!-- jQuery UI 1.11.4 -->
	<script src="<c:url value="/resources/jquery-ui/1.11.4/jquery-ui.min.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/ajax/jquery.validate/1.7/jquery.validate.min.js"/>"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.6 -->
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
	<!-- Morris.js charts -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="<c:url value="/resources/plugins/morris/morris.min.js" />"></script>
	<!-- Sparkline -->
	<script src="<c:url value="/resources/plugins/sparkline/jquery.sparkline.min.js"/>"></script>
	<!-- jvectormap -->
	<script src="<c:url value="/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"/>"></script>
	<script src="<c:url value="/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"/>"></script>
	<!-- jQuery Knob Chart -->
	<script src="<c:url value="/resources/plugins/knob/jquery.knob.js"/>"></script>
	<!-- daterangepicker -->
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<script src="<c:url value="/resources/plugins/daterangepicker/daterangepicker.js"/>"></script>
	<!-- DataTables -->
	<script src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js"/>"></script>
	<script src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js"/>"></script>
	<!-- datepicker -->
	<script src="<c:url value="/resources/plugins/datepicker/bootstrap-datepicker.js"/>"></script>
	<!-- Time picker -->
	<!-- bootstrap time picker -->
<script src="<c:url value="/resources/plugins/timepicker/bootstrap-timepicker.min.js"/>"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script src="<c:url value="/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"/>"></script>
	<!-- Slimscroll -->
	<script src="<c:url value="/resources/plugins/slimScroll/jquery.slimscroll.min.js"/>"></script>
	<!-- FastClick -->
	<script src="<c:url value="/resources/plugins/fastclick/fastclick.js"/>"></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/resources/dist/js/app.min.js"/>"></script>
<!-- Select2 -->
<script src="<c:url value="/resources/plugins/select2/select2.full.min.js"/>"></script>
<!-- fullCalendar 2.2.5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="<c:url value="/resources/plugins/fullcalendar/fullcalendar2.js"/>"></script>
 <script src="<c:url value="/resources/dist/js/letterAvatar.js"/>"></script>
  <script src="<c:url value="/resources/dist/js/projects.js"/>"></script>
  <!-- Pagination -->
  <script src="<c:url value="/resources/dist/js/jquery.bootpag.min.js"/>"></script>
  <script src="<c:url value="/resources/dist/js/jobs.js"/>"></script>
 <script src="<c:url value="/resources/dist/js/timesheets.js"/>"></script>
 <script src="<c:url value="/resources/dist/js/projectsPlanning.js"/>"></script>	
  
  
</body>

</html>