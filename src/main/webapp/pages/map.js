var arr;
var map;
var initStatus=true;
var markers=[];
var count=0;
$(function(){
	initMap();
	setInterval(function() {
		initMap();
		console.log(count);
		count++;
	}, 20000);
});
function initMap(){
	var params = "init";
	var url = "../talk/toJSON";
	$.ajax({
	    type: 'POST',
	     url: url ,
	   async: false,
	    data: params ,
	 success: function(data){
	    	arr=data;
	    	if(initStatus){
	    		start();
	    		initStatus=false;
	    	}
	    	if(!initStatus){
	    		 clearMarkers();
	    		 showMarkers();
	    	}	    	    	
	    } ,
	    dataType: 'json'
	});
}

function start(){
	function Demo() { }  
	 Demo.prototype.tileSize = new google.maps.Size(256, 256);//瓦片大小  
	 Demo.prototype.maxZoom = 13;//允许最大缩放层级  
	 Demo.prototype.minZoom = 10;//允许最小缩放层级  
	 Demo.prototype.name = "地图";  
	 Demo.prototype.getTile = function (coord, zoom, ownerDocument) {  
	   var img = ownerDocument.createElement("img");  
	   img.style.width = this.tileSize.width + "px";  
	   img.style.height = this.tileSize.height + "px";  
	   //定义瓦片的相对路径  
	   var strURL = 'expotile/';  
	   //其中zoom为层级，x可以理解为该瓦片在整个地图中的列数，y为行数，图片格式下载的时候选择png或者jpg，我这里是png格式  
	   strURL += zoom + "/" + coord.x + "/" + coord.y + '.png';  
	   img.src = strURL;  
	   CheckImgExists(strURL);
	   return img;  
	 };  
	 var localMap = new Demo();  

	//初始化参数
	 var myOptions = {  
	   center: new google.maps.LatLng(30.75143156,103.997046), //地图中心坐标  
	   zoom: 10,    //地图层级  
	   mapTypeControl: true,  //默认右上角显示地图名称  
	   mapTypeControlOptions: {  
	   mapTypeIds: ['localMap'] //'satel'
	  }  
	 };  
	 //创建一个map对象，以下代码使用参数(myOptions)在<div> 元素 (id为map_canvas) 创建了一个新的地图，并默认在地图右上角显示 卫星影像和电子地图切换  
	 map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);  
	 map.mapTypes.set('localMap', localMap);  
	 map.setMapTypeId('localMap'); //设置默认显示的地图
	 
	 //显示marker函数
	 showMarkers();
}

function showMarkers(){
	for(var i=0;i<arr.length;i++){
		 var marker = new google.maps.Marker({  
		     position: new google.maps.LatLng(arr[i].latitude,arr[i].longitude),
		     map: map,  
		     title:arr[i].userName,
		     animation:google.maps.Animation.DROP
		 });
		 markers.push(marker);
		 attachSecretMessage(marker, i);
	 }	
}

function attachSecretMessage(marker, i) {
	//定义显示内容，可以使用html标签显示内容效果   
	 var infowindow = new google.maps.InfoWindow({  
	     content: '<div>'+'<p>当前用户：'+arr[i].userName+'</p>'+'<p>纬度：'+arr[i].latitude+'</p>'+'<p>经度：'+arr[i].longitude+'</p>'+'</div>', //显示内容  
	     maxWidth:50, //定义最大宽度  
	     maxHeight:60
	 });
	 
	//点击Maker标注显示InfoWindow  
	 google.maps.event.addListener(marker, 'click', function() {  
	   infowindow.open(map,marker);  
	 });
}

function clearMarkers(){
	for(var i=0;i<markers.length;i++){
		markers[i].setMap(null);
	}
	markers=[];
}

function CheckImgExists(imgurl) {  
	$('img').error(function(){
       $(this).attr('src', "mapfiles/maperror.png");
     });

}