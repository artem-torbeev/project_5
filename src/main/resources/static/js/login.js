jQuery(document).ready(function ($) {
    //  авториза́ция User
    jQuery("#loginBtn").click(function (e) {
        e.preventDefault();
        let email = $("#loginEmail").val();
        let password = $("#loginPassword").val();
        let user = {
            "email": email,
            "password": password,
        };
        authorization_user(user);

        $("#login")[0].reset();

        to_pass_page_user();
    });
});

// авториза́ция user
function authorization_user(user) {
    let email;
    let token;
    $.ajax({
        type: 'POST',
        url: "/login",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(user),
        dataType: 'json',
        cache: false,
        async: false,
        success: function (data) {
            $.each(data, function (key, value) {
                if (key === 'body') {
                    $.each(value, function (i, j) {
                        if (i === 'email') {
                            email = j;
                            // console.log('email : ' + email);
                        }
                        if (i === 'model') {
                            $.each(j, function (k, v) {
                                if (k === 'token') {
                                    token = v;
                                    // console.log('token : ' + token);
                                }
                            });
                        }
                    })
                }
            });

            set_cookie('email', email, {'max-age': 600});
            set_cookie('token', token, {'max-age': 600});
        },
        error: function (xhr, status, error) {
            if (xhr.status === 500 || xhr.status === 204) {
                alert('Not a valid login or password...');
            } else {
                alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
            }
        }
    });
}

// перенапровление на страницу user
function to_pass_page_user() {
    setTimeout(function () {
        location.assign("http://localhost:8080/user");
    }, 1500);
}

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

// Пример использования:
// setCookie('user', 'John', {secure: true, 'max-age': 3600});

//удалить куки
function delete_cookie(name) {
    set_cookie(name, "", {
        'max-age': -1
    })
}


