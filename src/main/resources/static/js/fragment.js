$(document).ready(function(){
	$(".sub-btn").click(function(){
	   $(this).next(".sub-menu").slideToggle();
	   $(this).find(".dropdown").toggleClass("icon_rotate");
	   $("input[type=date]").value = new Date();
	});
});
function tongtien1(){
	var soluong = document.querySelectorAll("#soluong");
	var gia = document.querySelectorAll("#gia");
	var chietkhau = document.querySelectorAll("#chietkhau");
	var GTGT = document.querySelectorAll("#GTGT");
	var thanhtien = document.querySelectorAll("#thanhtien");
	var n = soluong.length;
	var i, sum=0, tienhang=0, tongGTGT=0, tongCK=0;
	for(i=0; i<n; i++){
		var tien = parseInt(gia[i].value)*parseInt(soluong[i].value);
		var tong = tien - tien*0.01*parseInt(chietkhau[i].value) + tien*0.01*parseInt(GTGT[i].value);
		thanhtien[i].value = tong;
		sum+=tong;
		tongGTGT += tien*parseInt(GTGT[i].value)/100;
		tongCK += tien*parseInt(chietkhau[i].value)/100;
		tienhang += tien;
	}
	document.getElementById("tongtien").value = sum;
	document.getElementById("tienhang").value = tienhang;
	document.getElementById("tongGTGT").value = tongGTGT;
	document.getElementById("tongchietkhau").value = tongCK;
}
tongtien1();
function tongtien2(){
	var soluong = document.querySelectorAll("#soluong");
	var gia = document.querySelectorAll("#gia");
	var chietkhau = document.querySelectorAll("#chietkhau");
	var GTGT = document.querySelectorAll("#GTGT");
	var thanhtien = document.querySelectorAll("#thanhtien");
	var n = soluong.length;
	var i, sum=0, tienhang=0, tongGTGT=0, tongCK=0;
	for(i=0; i<n; i++){
		var tien = parseInt(gia[i].value)*parseInt(soluong[i].value);
		var tong = tien - tien*0.01*parseInt(chietkhau[i].value) + tien*0.01*parseInt(GTGT[i].value);
		thanhtien[i].value = tong;
		sum+=tong;
		tongGTGT += tien*parseInt(GTGT[i].value)/100;
		tongCK += tien*parseInt(chietkhau[i].value)/100;
		tienhang += tien;
	}
	document.getElementById("tienhang").value = tienhang;
	document.getElementById("tongGTGT").value = tongGTGT;
	document.getElementById("tongchietkhau").value = tongCK;
	var x = parseInt(document.getElementById("tienship").value);
	sum += x;
	document.getElementById("tongtien").value = sum;
}
function getDate(){
    var today = new Date();
	document.querySelectorAll("input[type=date]").value = today;
}
window.onload = function(){
	getDate();
}