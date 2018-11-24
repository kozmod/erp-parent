/**
 * Sent "Get" request to  @param reference and  change div by id/ use @param divId
 * @param divId
 * @param reference
 */
function getFragmentAndChangeDiv(divId,reference) {
    $.ajax({
        headers: {
            Accept: "text/plain; charset=utf-8", "Content-Type": "text/plain; charset=utf-8"
        },
        url: reference,
        method: "GET",
        data: {},
        success: function (data, textStatus, response) {
            var text = response.responseText;
            if (text === "") {
                return;
            }
            // console.log(text);
            $(divId).html(text)
            loadOptions();
        },
        error: function (response) {
            alert(response);
            // terminate the script
        }
    });
}


function call_XXX() {
    $.ajax({
        headers: {
            Accept: "text/plain; charset=utf-8", "Content-Type": "text/plain; charset=utf-8"
        },
        url: "/xxx",
        method: "POST",
        data: {
            name : "AAAAAAA",
        },
        success: function (data, textStatus, response) {
            alert("SUCC");

        },
        error: function (response) {
            alert("ERR");
            // terminate the script
        }
    });

}

// function updateUserPost(userId) {
//
//     var formUserId = "#form_update_user_".concat(userId);
//
//     // SUBMIT FORM
//     $(formUserId).submit(function(event) {
//         // Prevent the form from submitting via the browser.
//         event.preventDefault();
//         ajaxUserUpdatePost();
//     });
//
//
//     function ajaxUserUpdatePost(){
//
//         // PREPARE FORM DATA
//         var formData = {
//             id : userId,
//             firstName : $("#user_first_name_".concat(userId)).val(),
//             surname :  $("#user_surname_".concat(userId)).val()
//         }
//
//         // DO POST
//         $.ajax({
//             type : "POST",
//             contentType : "application/json",
//             url : window.location + "api/customer/save",
//             data : JSON.stringify(formData),
//             dataType : 'json',
//             success : function(result) {
//                 if(result.status == "Done"){
//                     $("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" +
//                         "Post Successfully! <br>" +
//                         "---> Customer's Info: FirstName = " +
//                         result.data.firstname + " ,LastName = " + result.data.lastname + "</p>");
//                 }else{
//                     $("#postResultDiv").html("<strong>Error</strong>");
//                 }
//                 console.log(result);
//             },
//             error : function(e) {
//                 alert("Error!")
//                 console.log("ERROR: ", e);
//             }
//         });
//
//         // Reset FormData after Posting
//         resetData();
//
//     }
// }