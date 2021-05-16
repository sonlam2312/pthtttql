function xuly(){
	var theoso = parseInt(document.getElementById("theoso").value);
	console.log(theoso);
	var kiemdinh = parseInt(document.getElementById("kiemdinh").value);
	var chenhlech = theoso - kiemdinh;
	document.getElementById("chenhlech").value = Math.abs(chenhlech);
	var xulykiemke;
	if(chenhlech>=0){
		xulykiemke = "Xuất kho";
	}else{
		xulykiemke = "Nhập kho";
	}
	document.getElementById("xuly").value = xulykiemke;
}