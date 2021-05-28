$(document).ready(function () {
    $.get("/get_all_products_warehouse", function (products) {
        for (let i = 0; i < products.length; i++) {
            var name = products[i].name;
            var price = products[i].price;
            var description = products[i].description;
            console.log(name, price, description);
        }
    })
})