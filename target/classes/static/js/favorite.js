function addIntoFavorite() {
    let url = $(location).attr('href');
    let id = url.replace("http://localhost:8080/product_page/", "");

    console.log(id);

    $.ajax({
        url: '/add_product_in_favorite/' + id,
        method: 'POST',
        dataType: 'text',

        success: function (message) {
            console.log(message);
            $("#msg").text(message);
            show();

        },
        error: function (message_error) {
            console.log(message_error);
            $("#msg").text(message_error);
            show();
        }
    })
}