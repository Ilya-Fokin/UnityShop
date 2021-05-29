$(document).ready(function () {
    var contentBlock = $('.content_zone');
    contentBlock.empty();

    $.get("/get_all_favorite", function (products) {
        for (let i = 0; i < products.length; i++) {
            let url = "/product_page/" + products[i].id;
            let src = products[i].image;
            let replaceSrc = "/Images/Product/";
            let newSrc = src.replace("C:\\fakepath\\", replaceSrc);

            let productElem = $('<a href="' + url + '" class="product_block">' +
                '<div class="image_block">' +
                '<img src="' + newSrc + '">' +
                '</div>' +
                '<div class="product_details">' +
                '<p id="product_name">' + products[i].name + '</p>' +
                '<p id="price">' + products[i].price + 'руб.' +'</p>' +
                '</div>' +
                '</a>');

            contentBlock.append(productElem);
        }
    })
})