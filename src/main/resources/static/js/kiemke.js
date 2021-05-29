function xuly1(){
	var theoso = document.querySelectorAll("#theoso");
	var kiemdinh = document.querySelectorAll("#kiemdinh");
	var chenhlech = document.querySelectorAll("#chenhlech");
	var xuly = document.querySelectorAll("#xuly");
	var n = theoso.length;
	for(i=0 ; i<n ; i++){
		var hieu = parseInt(theoso[i].value) - parseInt(kiemdinh[i].value);
		chenhlech[i].value = Math.abs(hieu);
		if(hieu>0){
			xuly[i].value = "Xuất kho";
		}
		else if(hieu ==0){
			xuly[i].value = "";
		}
		else{
			xuly[i].value = "Nhập kho";
		}
	}
}