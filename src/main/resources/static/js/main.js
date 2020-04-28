jQuery(document).ready(function ($) {

    // заполнение таблицы
    get_all_users();

    // logout
    jQuery("#link-logout").click(function (e) {
        e.preventDefault();

        delete_cookie('email');
        delete_cookie('token');
        delete_cookie('role');
        // redirect login
        to_pass_page_login();
    });


    // создание User
    jQuery("#addBtn").click(function (e) {
        e.preventDefault();
        let role = [];
        let email = $("#addEmail").val();
        let username = $("#addLogin").val();
        let password = $("#addPassword").val();
        role.push($("#addRole").val());
        let user = {
            "email": email,
            "username": username,
            "password": password,
            "role": role
        };
        create_user(user);
        $("#creat")[0].reset();
    });

    // изменения user
    jQuery("#editBtn").click(function (e) {
        e.preventDefault();
        let role = [];
        let id = $("#editId").val();
        let email = $("#editEmail").val();
        let username = $("#editLogin").val();
        let password = $("#editPassword").val();
        role.push($("#editRole").val());
        let user = {
            "id": id,
            "email": email,
            "username": username,
            "password": password,
            "role": role
        };
        edit_user(user);
        $("#edit")[0].reset();
    });

    // заполнения модального окна
    jQuery('#editModal').on('show.bs.modal', function (e) {
        let button = $(e.relatedTarget);
        let id = button.data('id');
        fill_modal_form(id);
    });
});

// заполнение таблицы
function get_all_users() {
    $.ajax({
        type: "GET",
        url: "/admin/all",
        xhrFields: {withCredentials: true},
        cache: false,
        async: false,
        success: function (result) {
            let tr = [];
            for (let i = 0; i < result.length; i++) {

                tr.push(`<tr><td>${result[i].id}</td>`);
                tr.push(`<td>${result[i].role[0]}</td>`);
                tr.push(`<td>${result[i].username}</td>`);
                tr.push(`<td>${result[i].password}</td>`);
                tr.push(`<td>${result[i].email}</td>`);
                tr.push(`<td><button data-id=${result[i].id} type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal">Edit</button></td></tr>`);
            }
            //заменит содержимое тэга
            $("#table-user .userList").html($(tr.join('')));
        },
        error: function (xhr, status, error) {
            if (xhr.status === 500 || xhr.status === 403) {
                alert('You do not have access rights...');
            } else {
                alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
            }
        }
    });
}

// изменения user
function edit_user(user) {
    $.ajax({
        type: 'PUT',
        url: "/admin/edit",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(user),
        dataType: 'json',
        cache: false,
        async: false,
        xhrFields: {withCredentials: true},
        success: function () {
            get_all_users();
        },
        error: function (xhr, status, error) {
            alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
        }
    });
}

// создание user
function create_user(user) {
    $.ajax({
        type: 'POST',
        url: "/admin/create",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(user),
        dataType: 'json',
        cache: false,
        async: false,
        xhrFields: {withCredentials: true},
        success: function () {
            get_all_users();
        },
        error: function (xhr, status, error) {
            if (xhr.status === 500 || xhr.status === 403) {
                alert('Sorry, you do not have permission to view this page.');
            } else {
                alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
            }
        }
    });
}

// заполнение модального окна
function fill_modal_form(id) {
    $.ajax({
        type: 'GET',
        url: '/admin/' + id,
        cache: false,
        async: false,
        xhrFields: {withCredentials: true},
        success: function (data) {
            $('#editModal #editId').val(data.id);
            $('#editModal #editEmail').val(data.email);
            $('#editModal #editLogin').val(data.username);
            $('#editModal #editRole').val(data.role[0]);
        },
        error: function (xhr, status, error) {
            alert('Error - ' + xhr.status + ': ' + xhr.statusText + error);
        }
    });
}





