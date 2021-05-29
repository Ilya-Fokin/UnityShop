$(document).ready(function () {
    let getUrl = $(location).attr('href');
    let url = getUrl.replace("http://localhost:8080/product_page/", "/get_product_by_id/");

    let innerName = $('.product_name-zone');
    let innerImage = $('.image_zone_product_id');
    let innerPrice = $('.price_product_id');
    let innerDescription = $('.product_description_zone');

    $.get(url, function (data) {
        let nameDB = data.name;
        let srcDB = data.image;
        let priceDB = data.price;
        let descriptionDB = data.description;

        let src = srcDB.replace("C:\\fakepath\\", "/Images/Product/");

        let image = $('<img src="' + src + '">');
        let description = $('<p id="description">' + descriptionDB + '</p>');
        let price = $('<p id="price_product_id">Цена: ' + priceDB + 'руб</p>');

        innerImage.append(image);
        innerName.append(nameDB);
        innerPrice.append(price);
        innerDescription.append(description);
    })
})