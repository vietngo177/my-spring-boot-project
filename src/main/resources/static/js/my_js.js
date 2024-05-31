function kiemTraMatKhau(){
    let password = document.getElementById("matKhau").value;
    let rePassword = document.getElementById("matKhauNhapLai").value;
    if(password!==rePassword){
        document.getElementById("thongBao").innerHTML = "Mật khẩu không trùng khớp!";
    }else{
        document.getElementById("thongBao").innerHTML = "";
    }
}

function xuLyDieuKhoan(){
    let data = document.getElementById("dieuKhoan").checked;
    if(data===true){
        document.getElementById("submit").style.visibility = "visible";
    }else{
        document.getElementById("submit").style.visibility = "hidden";
    }
}