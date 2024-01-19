$(document).ready(function () {

    var sum = 0;
    //Выводим информацию о кол-ве денег
    $.get("/get_money", function (count) {
        let moneyBlock = $('.right_header');
        let innerMoney = $('<p id="money">' + 'На счету: ' + count + 'р' + '</p>');
        moneyBlock.prepend(innerMoney);
    })

    //Получаем и выводим все товары из корзины в бд
    $.get("/get_all_basket", function (data) {
        var listProducts = $(".list_products");
        if (data.length == 0) {
            $("#msg").text("Корзина пуста");
            show();
        } else {

            for (let i = 0; i < data.length; i++) {
                var count = data[i].count;
                var productId = data[i].productId;

                $.get("get_product_by_id/" + productId, function (product) {
                    let name = product.name;
                    let price = product.price;
                    let description = product.description;
                    let image = product.image;
                    let srcImage = image.replace("C:\\fakepath\\", "/Images/Product/");
                    let url = "/product_page/" + product.id;

                    var productElement = $('<div class="product_in_basket">' +
                        '<a href="' + url + '" class="image_info_block">' +
                        '<div class="image_product_in_basket">' +
                        '<img src="' + srcImage + '">' +
                        '</div>' +
                        '<div class="info_about_product_basket">' +
                        '<p class="product_name" id="product_name_basket_' + i + '">' + name + '</p>' +
                        '<p class="price_product_basket">' + price + 'р</p>' +
                        '</div>' +
                        '</a>' +
                        '<div class="description_size_bloc_basket">' +
                        '<div class="description_product_basket">' +
                        '<p class="description_in_basket" >Описание: ' + description + '</p>' +
                        '</div>' +
                        '<div class="size_product_basket">' +
                        '<p class="size_in_basket">Размер: ' + data[i].size + '</p>' +
                        '</div>' +
                        '</div>' +
                        '<div class="count_in_basket">' +
                        '<p class="count_in_basket_p">В корзине: ' + data[i].count + '</p>' +
                        '</div>' +
                        '</div>');
                    sum = sum + price;
                    listProducts.append(productElement);
                    $("#sum").text("Итого: " + sum + "р");
                })
            }
        }
    })


    $(document).on('click', '#place_order', function () {
        $.get("/get_money", function (money) {
            if (money < sum) {
                console.log("Недостаточно средств");
                $("#msg").text("Недостаточно средств");
                show();
            } else {

                $.ajax({
                    url: "/create_order",
                    method: 'post',
                    dataType: 'text',

                    success: function (msg) {
                        $("#msg").text(msg);
                        show();
                    },

                    error: function (error_msg) {
                        $("#msg").text(error_msg);
                        show();
                    }
                })

            }

        })
    })
})



function redirectAddMoney() {
    $(location).attr('href', "/add_money");
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


