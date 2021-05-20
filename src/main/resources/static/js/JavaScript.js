function signUp() {
    let name = $("#name_field").val();
    let username = $("#username_field").val();
    let email = $("#email_field").val();
    let password = $("#password_field").val();
    let date = $("#date_field").val();

    let user = JSON.stringify({
        'name' : name,
        'username' : username,
        'email' : email,
        'password' : password,
        'date' : date
    });

    console.log(user);

    $.ajax({
        url: "/sign_up_user",
        method: "POST",
        data: user,
        contentType: 'application/json',
        dataType: 'text',

        success: function (data) {
            $("#msg").html(data);
            show();
            console.log(data);
        }
    })
}

function hide() {
    $('.containerMain').css("filter", "none");
    $('.header').css("filter", "none");
    $('#message_block').css("display", "none");
}

function show() {
    $('.containerMain').css("filter", "blur(10px)");
    $('.header').css("filter", "blur(10px)");
    $('#message_block').css("display", "block");
}
