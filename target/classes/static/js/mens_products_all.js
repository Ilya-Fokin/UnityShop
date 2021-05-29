$(document).ready(function () {
    let contentBlock = $(".content_zone");
    let currentUrl = $(location).attr('href');
    let getUrl = currentUrl.replace("http://localhost:8080/", "/get_all_products/")

    $.get(getUrl, function (data) {
        for (let i = 0; i < data.length; i++) {
            let name = data[i].name;
            let url = "/product_page/" + data[i].id;
            let src = data[i].image;
            let price = data[i].price;

            let replaceSrc = "/Images/Product/";
            let newSrc = src.replace("C:\\fakepath\\", replaceSrc);

            let product = $('<a href="' + url + '" class="product_block">' +
                '<div class="image_block">' +
                '<img src="' + newSrc + '">' +
                '</div>' +
                '<div class="product_details">' +
                '<p id="product_name">' + name + '</p>' +
                '<p id="price">' + price + 'руб.' +'</p>' +
                '</div>' +
                '</a>');

            contentBlock.append(product);
        }
    })
})
