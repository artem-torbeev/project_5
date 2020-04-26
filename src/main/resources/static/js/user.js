jQuery(document).ready(function ($) {

    let name = get_cookie('email');
    // console.log('COOKIE USER_NAME == >> ' + name);
    insert_value(name);

    console.log('COOKIE LOGIN == >> ' + get_cookie('email'));
    console.log('COOKIE TOKEN == >> ' + get_cookie('token'));
    console.log(document.cookie);

});

//устанавливает имя для преведствия
function insert_value(value) {
    document.getElementById("email").innerHTML = value;
}

//возвращает куки с указанным name
function get_cookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
