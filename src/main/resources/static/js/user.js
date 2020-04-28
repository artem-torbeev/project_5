jQuery(document).ready(function ($) {

    let name = get_cookie('email');
    if (name === ''){
        insert_value('Unknown')
    }else {
        insert_value(name);
    }

    // проверка role of user
    jQuery("#admin-btn").click(function (e) {
        e.preventDefault();
        let role = get_cookie('role');
        if (role === 'ROLE_ADMIN') {
            to_pass_page_admin();
        }else {
            to_pass_page_403();
        }
    });

    // logout
    jQuery("#link-logout").click(function (e) {
        e.preventDefault();

        delete_cookie('email');
        delete_cookie('token');
        delete_cookie('role');
        // redirect login
        to_pass_page_login();
    });

});


//устанавливает имя для преведствия
function insert_value(value) {
    document.getElementById("email").innerHTML = value;
}