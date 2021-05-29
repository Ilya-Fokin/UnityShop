function addToBasket() {
    let url = $(location).attr('href');
    let id = url.replace("http://localhost:8080/product_page/", "");
    let size = $("#size_product_select option:selected").text();

    $.ajax({
        url: '/add_product_in_basket/' + id + "/" + size,
        method: 'POST',
        dataType: 'text',

        success: function (message) {
            console.log(message);
            $("#msg").text(message);
            show();

        },
        error: function (message_error) {
            console.log(message_error);
            $("#msg").text(message_error);
            show();
        }
    })
}

function deleteIntoBasket() {
    let url = $(location).attr('href');
    let id = url.replace("http://localhost:8080/product_page/", "");
    let size = $("#size_product_select option:selected").text();

    $.ajax({
        url: '/delete_product_in_basket/' + id + "/" + size,
        method: 'post',
        dataType: 'text',

        success: function (message) {
            console.log(message);
            $("#msg").text(message);
            show();

        },
        error: function (message_error) {
            console.log(message_error);
            $("#msg").text(message_error);
            show();
        }
    })
}

function addMoney() {
    let sum = $("#search_input").val();
    console.log(sum);

    $.ajax({
        url: '/add_money_user/' + sum,
        method: 'post',
        dataType: 'text',

        success: function (msg) {
            console.log(msg);
            $(location).attr("href", "/basket");
        },
        error: function (message_error) {
            console.log(message_error);
            $("#msg").text(message_error);
            show();
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

