/*$(document).ready(function() {

    let contentBlock = $(".content_zone");

       let url = "/product_page/";
        let src = "../static/Images/Товары/hmgoepprod.jpg";
        let nameProduct = "Футболка поло";
        let price = "2500р";
        
        var product = $('<a href="' + url + '" class="product_block">' + 
        '<div class="image_block">' + 
        '<img src="' + src + '">' + 
        '</div>' + 
        '<div class="product_details">' + 
        '<p id="product_name">' + nameProduct + '</p>' + 
        '<p id="price">' + price + '</p>' + 
        '</div>' + 
        '</a>');


       contentBlock.append(product);


    $.get("/get_all_products/Мужчины/Футболки", function(data) { 
        for(let i = 0; i < data.length; i++) {
            console.log(data[i].id);
        }
    })

});*/


$(document).ready(function () {
    let contentBlock = $(".content_zone");

    $.get('/get_all_products/Мужчины/Футболки', function (data) {
        for (let i = 0; i < data.length; i++) {
            let name = data[i].name;
            let url = "/product_page/" + data[i].id;
            let src = data[i].image;
            let price = data[i].price;

            let replaceSrc = "/Images/Product/";
            let newSrc = src.replace("C:\\fakepath\\", replaceSrc);
            //newSrc = newSrc.replace(/\s/g, '');

            console.log(newSrc);

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
