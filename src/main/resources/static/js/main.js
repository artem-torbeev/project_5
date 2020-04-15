jQuery(document).ready(function ($) {

    get_all_users();

    // login User
    // jQuery("#loginBtn").click(function (e) {
    //     e.preventDefault();
    //     let user = {
    //         "email": $("#loginEmail").val(),
    //         "password": $("#loginPassword").val(),
    //     };
    //     login_user(user);
    //     $("#creat")[0].reset();
    // });

    // создание User
    jQuery("#addBtn").click(function (e) {
        e.preventDefault();
        let user = {
            "email": $("#addEmail").val(),
            "username": $("#addLogin").val(),
            "password": $("#addPassword").val(),
            // "role": $("#addRole").val()
        };
        create_user(user);
        $("#creat")[0].reset();
    });

    // изменения user
    jQuery("#editBtn").click(function (e) {
        e.preventDefault();
        let user = {
            "id": $("#editId").val(),
            "email": $("#editEmail").val(),
            "username": $("#editLogin").val(),
            "password": $("#editPassword").val(),
            // "role": $("#editRole").val()
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
        cache: false,
        async: false,
        success: function (result) {
            let tr = [];
            for (let i = 0; i < result.length; i++) {

                tr.push(`<tr><td>${result[i].id}</td>`);
                tr.push(`<td>${result[i].role.map(role => role.role)}</td>`);
                tr.push(`<td>${result[i].username}</td>`);
                tr.push(`<td>${result[i].password}</td>`);
                tr.push(`<td>${result[i].email}</td>`);
                tr.push(`<td><button data-id=${result[i].id} type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal">Edit</button></td></tr>`);

            }
            //заменит содержимое тэга
            $("#table-user .userList").html($(tr.join('')));
        },
        error: function (xhr, status, error) {
            alert('Error - ' + xhr.status + ': ' + xhr.statusText);
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
        success: function () {
            // alert("EDIT");
            get_all_users();
        },
        error: function (xhr, status, error) {
            alert('Error - ' + xhr.status + ': ' + xhr.statusText);
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
        success: function () {
            // alert("CREATE");
            get_all_users();
        },
        error: function (xhr, status, error) {
            alert('Error - ' + xhr.status + ': ' + xhr.statusText);
        }
    });

}

// login user
function login_user(user) {
    $.ajax({
        type: 'POST',
        url: '/login',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(user),
        dataType: 'json',
        cache: false,
        async: false
        // success: function () {
        // },
        // error: function (xhr, status, error) {
        //     alert('Error - ' + xhr.status + ': ' + xhr.statusText);
        // }
    });
}

// заполнение модального окна
function fill_modal_form(id) {
    $.ajax({
        type: 'GET',
        url: '/admin/' + id,
        cache: false,
        async: false,
        success: function (data) {
            $('#editModal #editId').val(data.id);
            $('#editModal #editEmail').val(data.email);
            $('#editModal #editLogin').val(data.username);
            $('#editModal #editRole').val(data.role.map(role => role.role));
        },
        error: function (xhr, status, error) {
            alert('Error - ' + xhr.status + ': ' + xhr.statusText);
        }
    });
}


