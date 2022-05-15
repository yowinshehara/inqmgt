<%@page import="com.inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/inquirys.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>inquiry Management V10.1</h1>
<form id="forminquiry" name="forminquiry" method="post" action="inquirys.jsp">
 inquiry code: 
 <input id="inquiryCode" name="inquiryCode" type="text" 
 class="form-control form-control-sm">
 <br> inquiry name: 
 <input id="inquiryName" name="inquiryName" type="text" 
 class="form-control form-control-sm">
 <br> inquiry price: 
 <input id="inquiryPrice" name="inquiryPrice" type="text" 
 class="form-control form-control-sm">
 <br> inquiry description: 
 <input id="inquiryDesc" name="inquiryDesc" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidinquiryIDSave" 
 name="hidinquiryIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divinquirysGrid">
 <%
 inquiry inquiryObj = new inquiry(); 
 out.print(inquiryObj.readinquirys()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>