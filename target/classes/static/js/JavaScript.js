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
            console.log(data);
        }
    })
}