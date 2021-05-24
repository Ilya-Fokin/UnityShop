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


function signIn() {
    let form = $("#sign_in_form");
    let user = form.serialize();


    $.ajax({
        url: "/sign_in_user",
        method: "POST",
        data: user,

        success: function (url) {

            $(location).attr("href", url);
        },

        error: function (msg) {
            console.log(msg);
            $('#info_msg_id').css("display", "block");

        }
    })
}

function validationForm() {
    var error = 0;
    let email = $("#email_field").val();
    let username = $("#username_field").val();
    let password = $("#password_field").val();

    let out = $("#info_msg_valid");

    if (password.length < 8 && password.length > 16) {
        out.text("Пароль не должен быть короче 8 и длиннее 16 символов");
        $('#info_msg_valid').css("display", "block");
        error++;
    }
    if (!password.match(/[A-z]/)) {
        out.text("Пароль должен включать в себя хотя бы 1 строчную букву");
        $('#info_msg_valid').css("display", "block");
        error++;
    }
    if (!password.match(/[A-Z]/)) {
        out.text("Пароль должен включать в себя хотя бы 1 заглавную букву");
        $('#info_msg_valid').css("display", "block");
        error++;
    }
    if (!password.match(/[0-9]/)) {
        out.text("Пароль должен включать в себя хотя бы 1 цифру");
        $('#info_msg_valid').css("display", "block");
        error++;
    }




}
