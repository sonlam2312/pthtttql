function tab_thanhtoan(){
    if(this.value === "chuathanhtoan"){
        $(".nav-tabs li:nth-child(3)").css({"display":"none"});
    }else if(this.value === "thanhtoanngay"){
        $(".nav-tabs li:nth-child(3)").css({"display":"block"});
    }
}
function tab_hoadon(){
    var x = $("#select_hoadon").val();
    if(x == "nhankem"){
        $(".nav-tabs li:last-child").css({"display":"block"});
    }
    if(x == "khongkem"){
        $(".nav-tabs li:last-child").css({"display":"none"});
    }
}

function tab_phieuxuat(){
	if(this.checked){
		$(".nav-tabs li:nth-child(2)").css({"display":"block"});
	}else{
		$(".nav-tabs li:nth-child(2)").css({"display":"none"});
	}
}

$(document).ready(function(){
    tab_hoadon();
    tab_thanhtoan();
    tab_phieuxuat();
    $("input[type=radio][name=thanhtoan]").change(tab_thanhtoan);
    $("#kemphieuxuat").change(tab_phieuxuat);
    $("#select_hoadon").change(tab_hoadon);
    $('.nav-tabs a').on('shown.bs.tab', function(event){
    	var x = $(event.target).text();         // active tab
        if(x == "Chứng từ ghi nợ"){
        	$("#f_phieuthu").css({"display":"none"});
        	$("#f_phieuxuat").css({"display":"none"});
            $("#f_chungtu").css({"display":"block"});
            $("#f_hoadon").css({"display":"none"});
        }
        if(x == "Phiếu xuất"){
        	$("#f_phieuthu").css({"display":"none"});
        	$("#f_phieuxuat").css({"display":"block"});
            $("#f_chungtu").css({"display":"none"});
            $("#f_hoadon").css({"display":"none"});
        }
        if(x == "Phiếu thu"){
        	$("#f_phieuthu").css({"display":"block"});
        	$("#f_phieuxuat").css({"display":"none"});
            $("#f_chungtu").css({"display":"none"});
            $("#f_hoadon").css({"display":"none"});
        }
        if(x == "Hóa Đơn"){
            $("#f_phieuthu").css({"display":"none"});
        	$("#f_phieuxuat").css({"display":"none"});
            $("#f_chungtu").css({"display":"none"});
            $("#f_hoadon").css({"display":"block"});
        }
    });
    
    $("#thanhtien").focus(function(){
        var gia = document.getElementById("gia").value;
        var soluong = document.getElementById("soluong").value;
        var chietkhau = document.getElementById("chietkhau").value;
        var GTGT = document.getElementById("GTGT").value;
        var tien = gia*soluong;
        var thanhTien = tien - tien*chietkhau/100 + tien*GTGT/100;
        document.getElementById("thanhtien").value = thanhTien;
    });
});