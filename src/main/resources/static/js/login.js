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
    $.ajax({
        type: 'POST',
        url: "/login",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(user),
        dataType: 'text',
        cache: false,
        async: false,
        success: function () {
            // alert("Authorization...")
        },
        error: function (xhr, status, error) {
            if (xhr.status === 500||xhr.status===204) {
                alert('Not a valid login or password...');
            } else {
                alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
            }
        }
    });
}
// перенапровление на страницу user
function to_pass_page_user() {
    location.assign("http://localhost:8080/user");
}

