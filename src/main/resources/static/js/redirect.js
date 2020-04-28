// перенапровление на страницу user
function to_pass_page_user() {
    setTimeout(function () {
        location.assign("http://localhost:8080/user");
    }, 500);
}

// перенапровление на страницу 403
function to_pass_page_403() {
    location.assign("http://localhost:8080/error");
}

// перенапровление на страницу admin
function to_pass_page_admin() {
    location.assign("http://localhost:8080/admin");
}

// перенапровление на страницу login
function to_pass_page_login() {
    location.assign("http://localhost:8080/login");
}

