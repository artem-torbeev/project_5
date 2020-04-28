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
    let role;
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
                    // console.log(value);
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
                                if (k === 'role'){
                                    role = v;
                                    // console.log('role : ' + v);
                                }
                            });
                        }
                    })
                }
            });

            set_cookie('email', email, {'max-age': 600});
            set_cookie('token', token, {'max-age': 600});
            set_cookie('role', role, {'max-age': 600})
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



