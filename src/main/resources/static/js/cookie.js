
//возвращает куки с указанным name
function get_cookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

//Устанавливает куки с именем name и значением value, с настройкой path=/ по умолчанию
function set_cookie(name, value, options = {}) {

    options = {
        path: '/',
    };

    if (options.expires instanceof Date) {
        options.expires = options.expires.toUTCString();
    }

    let updatedCookie = encodeURIComponent(name) + "=" + encodeURIComponent(value);

    for (let optionKey in options) {
        updatedCookie += "; " + optionKey;
        let optionValue = options[optionKey];
        if (optionValue !== true) {
            updatedCookie += "=" + optionValue;
        }
    }

    document.cookie = updatedCookie;
}
// Пример использования function set_cookie() :
// set_cookie('user', 'John', {secure: true, 'max-age': 3600});

//удалить куки
function delete_cookie(name) {
    set_cookie(name, "", {
        'max-age': -1
    })
}
