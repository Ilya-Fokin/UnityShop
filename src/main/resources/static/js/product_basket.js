$(document).ready(function () {
    $.get("/get_all_basket", function (data) {
        var listProducts = $(".list_products");
        var sum = 0;

        for (let i = 0; i < data.length; i++) {
            var count = data[i].count;
            var productId = data[i].productId;
            console.log(data[i].userId, productId, count, data[i].size);

            $.get("get_product_by_id/" + productId, function (product) {
                let name = product.name;
                let price = product.price;
                let description = product.description;
                let image = product.image;
                let srcImage = image.replace("C:\\fakepath\\", "/Images/Product/");
                let url = "/product_page/" + product.id;

                console.log(name, price,description);

                var productElement = $('<div class="product_in_basket">' +
                    '<a href="' + url + '" class="image_info_block">' +
                    '<div class="image_product_in_basket">' +
                    '<img src="' + srcImage + '">' +
                    '</div>' +
                    '<div class="info_about_product_basket">' +
                    '<p class="product_name" id="product_name_basket">' + name + '</p>' +
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
                    '<input id="count_change_input" type="number" min="0" placeholder="Кол-во">' +
                    '<button type="submit" id="change_count_product">Обновить</button>' +
                    '</div>' +
                    '</div>');
                sum = sum + price;
                listProducts.append(productElement);
                $("#sum").text("Итого: " + sum + "р");
        })
    }
    })
})


function editBasket() {

    $.get("/get_all_basket", function (data) {
        var selectProducts = $(".list_products_in_basket");
        var num;

        for (let i = 0; i < data.length; i++) {
            num = i+1;
            var count = data[i].count;
            var productId = data[i].productId;
            console.log(data[i].userId, productId, count, data[i].size);

            $.get("get_product_by_id/" + productId, function (product) {
                var name = product.name;
                var price = product.price;

                console.log(name, price);
            })
            var products = $('<p id="product_info_edit_basket">' + num + '. ' + name + ' <b>' + data[i].size + '</b>' + '<b>' + data[i].count + '</b>' + '</p>');
            selectProducts.append(products);
            console.log(num);
        }
    })


    $("#edit_basket_block").css("display", "block");
    console.log("ятут");
}


