// jQuery(document).ready(function ($) {
//
//     //  авториза́ция User
//     jQuery("#loginBtn").click(function (e) {
//         e.preventDefault();
//         let email = $("#loginEmail").val();
//         let password = $("#loginPassword").val();
//         let user = {
//             "email": email,
//             "password": password,
//         };
//         authorization_user(user);
//         $("#creat")[0].reset();
//     });
//
// });
//
// // авториза́ция user
// function authorization_user(user) {
//     $.ajax({
//         type: 'POST',
//         url: "/login",
//         contentType: 'application/json; charset=utf-8',
//         data: JSON.stringify(user),
//         dataType: 'json',
//         cache: false,
//         async: false,
//         success: function () {
//             alert("Authorization...")
//         },
//         error: function (xhr, status, error) {
//             alert('Error - ' + xhr.status + ': ' + xhr.statusText);
//         }
//     });
//
// }