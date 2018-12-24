/**
 * Sent "Get" request to  @param reference and  change div by id/ use @param divId
 * @param divId
 * @param reference
 */
function getFragmentAndChangeDiv(divId, reference) {
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


function updateUserRequest(userId) {
        var JSONObject= {
            id: userId,
            firstName:
                $("#user_first_name_".concat(userId)).val(),
            surname:
                $("#user_surname_".concat(userId)).val(),
            patronymic:
                $("#user_patronymic_".concat(userId)).val(),
            employeePosition:
                $("#user_employee_position_".concat(userId)).val(),
            mail:
                $("#user_mail_".concat(userId)).val(),
            password:
                $("#user_password_".concat(userId)).val(),
            phoneNumber:
                $("#user_phone_number_".concat(userId)).val()
        };

        $.ajax({
            type: 'PUT',
            url:  '/user',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: true,
            success: function(result) {
                alert('At ' + result.time
                    + ': ' + result.message);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
}

function deleteUser(userId) {
    $.ajax({
        type: 'DELETE',
        url:  '/user/'.concat(userId),
        dataType: 'json',
        async: true,
        success: function(result) {
            alert('At ' + result.time
                + ': ' + result.message);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}