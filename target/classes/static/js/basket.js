function addToBasket() {
    let url = $(location).attr('href');
    let id = url.replace("http://localhost:8080/product_page/", "");
    let size = $("#size_product_select option:selected").text();

    console.log(id, size);

    $.ajax({
        url: '/add_product_in_basket/' + id + "/" + size,
        method: 'POST',
        dataType: 'text',

        success: function (message) {
            console.log(message);

        },
        error: function (message_error) {
            console.log(message_error);

        }
    })
}

$(document).ready(function () {
    $.get("/get_all_product_basket", function (data) {
        let listProducts = $(".list_products");

        let sum = data[0].price + 500;

        console.log(sum);
    })


})