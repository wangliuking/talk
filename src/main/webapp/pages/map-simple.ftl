<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link href="${ctx}/mapfiles/default.css" rel="stylesheet" type="text/css" />
<title>Google Maps JavaScript API v3 Example: Map Simple</title>
<script type="text/javascript" src="${ctx}/mapapi.js"></script>
<script src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx}/pages/map.js"></script>
<style>  
  html { height: 100%; }  
  body { height: 100%; margin: 0; padding: 0; }  
  #map_canvas { height: 100%; }  
 </style>
</head>
<body>
<div id="map_canvas"></div>
</body>
</html>