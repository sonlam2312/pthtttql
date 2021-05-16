$(document).ready(function(){
	$(".sub-btn").click(function(){
	   $(this).next(".sub-menu").slideToggle();
	   $(this).find(".dropdown").toggleClass("icon_rotate");
	});
});
function tongtien(){
	var soluong = document.querySelectorAll("#soluong");
	var gia = document.querySelectorAll("#gia");
	var chietkhau = document.querySelectorAll("#chietkhau");
	var GTGT = document.querySelectorAll("#GTGT");
	var thanhtien = document.querySelectorAll("#thanhtien");
	var n = soluong.length;
	var i, sum=0, tienhang=0, maxGTGT=0, maxCK=0;
	for(i=0; i<n; i++){
		var tien = parseInt(gia[i].value)*parseInt(soluong[i].value);
		var tong = tien - tien*0.01*parseInt(chietkhau[i].value) + tien*0.01*parseInt(GTGT[i].value);
		thanhtien[i].value = tong;
		sum+=tong;
		maxGTGT = Math.max(maxGTGT,parseInt(GTGT[i].value));
		maxCK = Math.max(maxCK,parseInt(chietkhau[i].value));
		tienhang += tien;
	}
	document.getElementById("tongtien").value = sum;
	document.getElementById("tienhang").value = tienhang;
	document.getElementById("tongGTGT").value = maxGTGT;
	document.getElementById("tongchietkhau").value = maxCK;
}
function tongtien2(){
	var soluong = document.querySelectorAll("#soluong");
	var gia = document.querySelectorAll("#gia");
	var chietkhau = document.querySelectorAll("#chietkhau");
	var GTGT = document.querySelectorAll("#GTGT");
	var thanhtien = document.querySelectorAll("#thanhtien");
	var n = soluong.length;
	var i, sum=0, tienhang=0, maxGTGT=0, maxCK=0;
	for(i=0; i<n; i++){
		var tien = parseInt(gia[i].value)*parseInt(soluong[i].value);
		var tong = tien - tien*0.01*parseInt(chietkhau[i].value) + tien*0.01*parseInt(GTGT[i].value);
		thanhtien[i].value = tong;
		sum+=tong;
		maxGTGT = Math.max(maxGTGT,parseInt(GTGT[i].value));
		maxCK = Math.max(maxCK,parseInt(chietkhau[i].value));
		tienhang += tien;
	}
	document.getElementById("tienhang").value = tienhang;
	document.getElementById("tongGTGT").value = maxGTGT;
	document.getElementById("tongchietkhau").value = maxCK;
	var x = parseInt(document.getElementById("tienship").value);
	sum += x;
	document.getElementById("tongtien").value = sum;
}