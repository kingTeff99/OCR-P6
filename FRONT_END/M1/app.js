
$(document).ready(function() {
    
    $("#login1").click(function () {

        console.log("login");

        var email1 = $("#email1").val();
        var password1 = $("#password1").val();

        authenticate(email1, password1);

        return false;
    });
    
});

    function authenticate(email1, password1) {

        $.ajax
        ({
            type: "POST",
            url: 'http://localhost:8080/api/login',
            data: {'username' : email1, 'password' : password1},
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            dataType: 'json',
            // success: function (data) {
            //     console.log("succes")
            //     document.location.href = "teffery.com";
                
            // },
            error: function(XMLHttpRequest, textStatus, errorThrown) {

                if(XMLHttpRequest.status == 200){

                    document.location.href = "../M2/index2.html";

                } else {

                    alert("Hello !!");

                    document.location.href = "../M2/index2.html";

                }
             }
         })
    };
