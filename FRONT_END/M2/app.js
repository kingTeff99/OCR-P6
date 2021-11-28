
$(document).ready(function () {

    $("#profile").click(function () {
        console.log("test profile");
        $.ajax
        ({
            type: 'GET',
            url: 'http://localhost:8080/users/1/infos',
            headers: {
                'Content-Type': 'application/json',
                'X-Content-Type-Options': 'nosniff',
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Headers':'application/json',
              },
         })
    });

    $("#contact").click(function () {
        $.ajax
        ({
            type: 'GET',
            url: 'http://localhost:8080/users/1/contact',
            headers: {
                'Content-Type': 'application/json',
                'X-Content-Type-Options': 'nosniff',
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Headers':'application/json',
              },
         })
    });

    $("#add_connexion").click(function () {
        $.ajax
        ({
            type: 'POST',
            url: 'http://localhost:8080/contact/add',
            dataType: 'json',
            contentType: 'application/json',
            headers: {
            'Content-Type': 'application/json',
            'X-Content-Type-Options': 'nosniff',
            'Access-Control-Allow-Origin':'*',
            'Access-Control-Allow-Headers':'application/json'
                },
            data: JSON.stringify({"username": "smithwesson@gmail.com" ,
                "myUsername" : "clarakata@gmail.com" 
               })
           
         })
    return false;
    });

    $("#pay").click(function () {

        console.log("pay123")

        var name = $("#connex").val();
        var amount = $("#money").val();
        transaction(name, amount);

        return false;

    });

});

function transaction(name, amount) {

    var transaction1 = JSON.stringify({
        "id": name,
        "amount": amount,
        "userSenderId": 1,
        "userReceiverId": 2,
        "bankSenderId": 1,
        "bankReceiverId": 2,
        "fees": null,
        "description": "Buy a fragrance "
    })

    $.ajax 
    ({
            method: "POST",
            url: 'http://localhost:8080/transaction/make',
            data: transaction1,
            headers: {
                'Content-Type': 'application/json',
                'X-Content-Type-Options': 'nosniff',
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Headers':'application/json'
                    },
            success: function (data) {
                alert('Login status: ' + data.status);
            }
         })
    };


