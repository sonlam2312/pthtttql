function tab_thanhtoan(){
    if(this.value === "chuathanhtoan"){
        $(".nav-tabs li:nth-child(3)").css({"display":"none"});
    }else if(this.value === "thanhtoanngay"){
        $(".nav-tabs li:nth-child(3)").css({"display":"block"});
    }
    document.getElementById("thanhtoanngay").setAttribute("checked","checked");
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
function select_loaimuahang(){
	var x = $("#loaimuahang").val();
    if(x == "muahangnhapkho"){
    	$(".nav-tabs li:first-child").css({"display":"block"});
    	$(".nav-tabs li:nth-child(2)").css({"display":"block"});
    }
    if(x == "muahangkhongquakho"){
    	$(".nav-tabs li:first-child").css({"display":"block"});
    	$(".nav-tabs li:nth-child(2)").css({"display":"none"});
    }
}
function select_loaimuahang1(){
    var x = $("#loaimuahang").val();
    if(x == "muahangnhapkho"){  
        $(".nav-tabs li:first-child").css({"display":"block"}); 
        $(".nav-tabs li:nth-child(2)").css({"display":"block"});
        $(".nav-tabs li:nth-child(3)").css({"display":"block"});
        $(".nav-tabs li:nth-child(4)").css({"display":"block"});
        $("input[type=radio][name=thanhtoan]").change(function(){
            if(this.value == "chuathanhtoan"){
                $(".nav-tabs li:first-child").css({"display":"none"}); 
            }
            if(this.value == "thanhtoanngay"){
                $(".nav-tabs li:first-child").css({"display":"none"}); 
            }
        });
    }
    if(x == "muahangkhongquakho"){
    	$(".nav-tabs li:nth-child(1)").css({"display":"block"});
        $(".nav-tabs li:nth-child(2)").css({"display":"none"});
        $(".nav-tabs li:nth-child(3)").css({"display":"block"});
        $(".nav-tabs li:nth-child(4)").css({"display":"block"});
        $("input[type=radio][name=thanhtoan]").change(function(){
            if(this.value == "chuathanhtoan"){
                $(".nav-tabs li:first-child").css({"display":"block"}); 
            }
            if(this.value == "thanhtoanngay"){
                $(".nav-tabs li:first-child").css({"display":"none"}); 
            }
        });
    }
}

$(document).ready(function(){
    tab_hoadon();
    tab_thanhtoan();
    select_loaimuahang();
    $("input[type=radio]").change(tab_thanhtoan);
    $("#select_hoadon").change(tab_hoadon);
    $("#loaimuahang").change(select_loaimuahang);
    $('.nav-tabs a').on('shown.bs.tab', function(event){
    	var x = $(event.target).text();         // active tab
        if(x == "Ch???ng t??? mua h??ng"){
        	$("#f_phieuchi").css({"display":"none"});
        	$("#f_phieunhap").css({"display":"none"});
            $("#f_chungtu").css({"display":"block"});
            $("#f_hoadon").css({"display":"none"});
        }
        if(x == "Phi???u nh???p"){
        	$("#f_phieuchi").css({"display":"none"});
        	$("#f_phieunhap").css({"display":"block"});
            $("#f_chungtu").css({"display":"none"});
            $("#f_hoadon").css({"display":"none"});
        }
        if(x == "Phi???u chi"){
        	$("#f_phieuchi").css({"display":"block"});
        	$("#f_phieunhap").css({"display":"none"});
            $("#f_chungtu").css({"display":"none"});
            $("#f_hoadon").css({"display":"none"});
        }
        if(x == "H??a ????n"){
            $("#f_phieuchi").css({"display":"none"});
        	$("#f_phieunhap").css({"display":"none"});
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