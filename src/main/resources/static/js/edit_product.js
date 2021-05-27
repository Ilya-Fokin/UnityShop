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

