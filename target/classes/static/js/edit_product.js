function addProduct() {
    let nameProduct = $("#name_input").val();
    let categorySelect = $("#category_select option:selected").text();
    let chapterSelect = $("#chapter_select option:selected").text();
    let description = $("#description_input").val();
    let image = $("#image_product").val();

    var sizeXS = $("#sizeXS").val();
    var sizeS = $("#sizeS").val();
    var sizeM = $("#sizeM").val();
    var sizeL = $("#sizeL").val();
    var sizeXL = $("#sizeXL").val();

    console.log(sizeXS + sizeS + sizeM + sizeL + sizeXL);

    if((sizeXS + sizeS + sizeM + sizeL + sizeXL) === 0) {
        console.log("Вы не можете добавить товар, не указав размеры");
    } 

    let price = $("#price_input").val();

    let product = JSON.stringify({
        'name' : nameProduct,
        'category' : categorySelect,
        'chapter' : chapterSelect,
        'description' : description,
        'image' : image,
        'price' : price,
        'XS' : sizeXS,
        'S' : sizeS,
        'M' : sizeM,
        'L' : sizeL,
        'XL' : sizeXL,
    });

    console.log(product);

    $.ajax({
        url: '/add_product_item',
        method: 'POST',
        contentType: 'application/json',
        dataType: 'text',
        data: product,

        success: function(message) {
            console.log(message);
            $("#info_msg_id").text(message);
            $('#info_msg_id').css("display", "block");
        },

        error: function(message_error) {
            console.log(message_error);
            $("#info_msg_id").text("Возникла проблема при добавлении товара, убедитесь, что в магазине нет продукта с таким же названием");
            $('#info_msg_id').css("display", "block");
        }
    })
}


function deleteProduct() {
    let name = $("#name_product").val();

    console.log("Удалить товар: " + name + "...");

    $.ajax({
        url: '/delete_product_item/' + name,
        method: 'POST',
        dataType: 'text',

        success: function(message) {
            console.log(message);
            $("#info_msg_id").text(message);
            $('#info_msg_id').css("display", "block");
        },

        error: function(message_error) {
            console.log(message_error);
            $("#info_msg_id").text(message_error);
            $('#info_msg_id').css("display", "block");

        }
    })
}

function searchProduct() {
    let name = $("#search_input").val();
    let jsonName = JSON.stringify({
        'name' : name,
    });
    console.log(jsonName);

    $.ajax({
        url: "/search_product",
        method: 'post',
        data: jsonName,
        dataType: "json",
        contentType: "application/json; charset = utf-8",

        success: function (product) {
            console.log(product[0].price);

            showAllProductsByName(product)
        },
        error: function (error_product) {
            console.log(error_product);
        }

    })
}

function showAllProductsByName(product) {
    var contentBlock = $('.content_zone');
    contentBlock.empty();

    for (let i = 0; i < product.length; i++) {
        let url = "/product_page/" + product[i].id;
        let src = product[i].image;
        let replaceSrc = "/Images/Product/";
        let newSrc = src.replace("C:\\fakepath\\", replaceSrc);

        let productElem = $('<a href="' + url + '" class="product_block">' +
            '<div class="image_block">' +
            '<img src="' + newSrc + '">' +
            '</div>' +
            '<div class="product_details">' +
            '<p id="product_name">' + product[i].name + '</p>' +
            '<p id="price">' + product[i].price + 'руб.' +'</p>' +
            '</div>' +
            '</a>');

        contentBlock.append(productElem);
    }
}

