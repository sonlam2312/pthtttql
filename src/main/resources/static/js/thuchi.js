function phuongthuc(){
    var x = $("#phuongthuc").val();
    if(x == "tienmat"){
        $("#taikhoan").css({"display":"none"});
    }
    if(x == "nganhang"){
        $("#taikhoan").css({"display":"table-row"});
    }
}
$(document).ready(function(){
	phuongthuc();
	$("#phuongthuc").change(phuongthuc);
});