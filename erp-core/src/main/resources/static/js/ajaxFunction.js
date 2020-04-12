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
            change_and_run_error_popup(response);
        }
    });
}

function RefreshFragmentChangeDiv() {
    $.ajax({
        headers: {
            Accept: "text/plain; charset=utf-8", "Content-Type": "text/plain; charset=utf-8"
        },
        //url: reference,
        method: "GET",
        data: {},
        success: function (data, textStatus, response) {
            var text = response.responseText;
            if (text === "") {
                return;
            }
            // console.log(text);
            window.location.reload(true);
            //$(divId).load(window.location +"/dashboard#")
            //$(divId).load(text)
            loadOptions();
        },
        error: function (response) {
            alert(response);
            // terminate the script
        }
    });
}
/**
 * Sent "Get" request to  @param reference and  change div by id/ use @param divId and to use @param field as id
 * @param divId
 * @param reference
 * @param field
 */
function getFragmentParamAndChangeDiv(divId, reference, id_grand_parent, id_parent, parent_name, grand_parent_name, delay) {
    var delay = delay;

    $.ajax({
        headers: {
            Accept: "application/json; charset=utf-8", "Content-Type": "text/html; charset=utf-8"},
        url: reference,
        method: "GET",
        data: {id_grand_parent: id_grand_parent, id_parent: id_parent, parent_name: parent_name, grand_parent_name: grand_parent_name},
        success: function (data, textStatus, response) {


            var text = response.responseText;
            if (text === "") {
                console.log("");
                return;
            }
            //setTimeout(function() {
            console.log(text);
            $(divId).html(text);
            //}, delay);

            loadOptions();
        },
        error: function (response) {
            change_and_run_error_popup(response);
        }
    });
}



function RefreshDiv(divId, reference) {
    function refreshDiv()
    {
        $.get(reference, function(data){$('#divId').html(data);});
    }
    $(function()
    {
        refreshDiv();
        setInterval(refreshDiv, 3 * 1000); // x is the number of seconds
    });
}
/**
 * Gets the ID of the clicked element - necessary to implement the fragment change properly

 */
function reply_click(clicked_id)
{
    var id_name=clicked_id;
    console.log(clicked_id);
    alert(clicked_id);
    return id_name;
}

function saveCounteragentRequest() {
    var JSONObject = {
        counteragentName:
            $("#counteragent_name").val(),
        groupName:null,
        //$("#group_name").val(),
        directorFirstName:
            $("#director_first_name").val(),
        directorSurname:
            $("#director_surname").val(),
        directorPatronymic:
            $("#director_patronymic").val(),
        mail:
            $("#mail").val(),
        phoneNumber:
            $("#phone_number").val(),
        address:
            $("#address").val()
    };

    $.ajax({
        type: 'POST',
        url: '/counteragent',
        contentType: 'application/json',
        data: JSON.stringify(JSONObject),
        async: true,
        success: function (JSONObject) {
            console.log("SUCCESS: ", JSONObject);
            //alert('At ' + result.time
            //    + ': ' + result.message);
            getFragmentAndChangeDiv('#content','/counteragent');
        },

        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function saveContractRequest(counteragent_id, counteragent_name) {
    var JSONObject = {
        counteragentId:
        counteragent_id,
        contractType:
            '1',
        contractDate:
            $("#contract_date").val(),
        contractNumber:
            $("#contract_number").val(),
        contractSubject:
            $("#contract_subject").val()
    };

    $.ajax({
        type: 'POST',
        url: '/contract',
        contentType: 'application/json',
        data: JSON.stringify(JSONObject),
        async: true,
        success: function (JSONObject) {
            console.log("SUCCESS: ", JSONObject);
            //alert('At ' + result.time
            //    + ': ' + result.message);
            getFragmentParamAndChangeDiv('#content','/contract',counteragent_id,counteragent_id,counteragent_name,counteragent_name,0)
            //$("#content").html(data);
            //$("#content").replaceWith('/contract');
            //$("#content").load('/counteragent');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }

    });
}

function saveKSRequest(contract_id, counteragent_id, contract_name, counteragent_name) {
    var JSONObject = {
        contractId:
        contract_id,
        ksDate:
            $("#ks_date").val(),
        ksNumber:
            $("#ks_number").val(),
        paymentStatus:
            $('input[name=payment_switcher_add]:checked').val(),
        ksSum:
            $("#ks_sum").val(),
        garantSum:
            $("#garant_sum").val(),
        garantDate:
            $("#garant_date").val()
    };
    $.ajax({
        type: 'POST',
        url: '/ks',
        contentType: 'application/json',
        data: JSON.stringify(JSONObject),
        async: true,
        success: function (JSONObject) {
            console.log("SUCCESS: ", JSONObject);
            //alert('At ' + result.time
            //    + ': ' + result.message);
            getFragmentParamAndChangeDiv('#content','/ks',counteragent_id,contract_id,contract_name,counteragent_name,0)
            //$("#content").html(data);
            //$("#content").replaceWith('/contract');
            //$("#content").load('/counteragent');
        },
        error: function (response) {
            change_and_run_error_popup(response);

            //var responseJSON = jQuery.parseJSON(jqXHR.responseText);
            //var errors=JSON.parse(responseJSON);
            //var error_message=responseJSON.message();
            //alert(jqXHR.status + ' ' + error_message);
            //error_dialog(jqXHR.responseText);
        }

    });
}

function updateKSRequest(KSId, contractId, counteragent_id, contract_name, counteragent_name) {
    var JSONKS = {
        id:
        KSId,
        contractId:
        contractId,
        ksDate:
            $("#ks_date_".concat(KSId)).val(),
        ksNumber:
            $("#ks_number_".concat(KSId)).val(),
        ksSum:
            $("#ks_sum_".concat(KSId)).val(),
        garantSum:
            $("#garant_sum_".concat(KSId)).val(),
        garantDate:
            $("#garant_date_".concat(KSId)).val(),
        paymentStatus:
            $("#payment_switcher_".concat(KSId)).val()
    };

    $.ajax({
        type: 'PUT',
        url: '/ks',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONKS),
        async: true,
        success: function (JSONKS) {
            console.log("SUCCESS: ", JSONKS);
            //alert('At ' + result.time
            //    + ': ' + result.message);
            getFragmentParamAndChangeDiv('#content','/ks',counteragent_id,contract_id,contract_name,counteragent_name,0)
        },
        error: function (jqXHR, textStatus, errorThrown) {

        }
    });
}

function updateContractRequest(contractId, counteragentId, counteragentName) {
    var JSONContract = {
        id:
        contractId,
        counteragentId:
        counteragentId,
        contractType:
            '1',
        contractNumber:
            $("#contract_number_".concat(contractId)).val(),
        contractDate:
            $("#contract_date_".concat(contractId)).val(),
        contractSubject:
            $("#contract_subject_".concat(contractId)).val()
    };

    $.ajax({
        type: 'PUT',
        url: '/contract',
        contentType: 'application/json; charset=utf-8',
        //accessControlAllowOrigin:"http://localhost:8080",
        data: JSON.stringify(JSONContract),
        async: true,
        success: function (JSONContract) {
            console.log("SUCCESS: ", JSONContract);
            //alert('At ' + result.time
            //    + ': ' + result.message);
            getFragmentParamAndChangeDiv('#content','/contract',counteragentId,counteragentId,counteragentName,counteragentName,0)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function updateCounteragentRequest(counteragentId) {
    var JSONCounteragent = {
        id: counteragentId,
        counteragentName:
            $("#counteragent_name_".concat(counteragentId)).val(),
        groupName:null,
        //$("#group_name_".concat(counteragentId)).val(),
        directorFirstName:
            $("#director_first_name_".concat(counteragentId)).val(),
        directorSurname:
            $("#director_surname_".concat(counteragentId)).val(),
        directorPatronymic:
            $("#director_patronymic_".concat(counteragentId)).val(),
        phoneNumber:
            $("#phone_number_".concat(counteragentId)).val(),
        mail:
            $("#mail_".concat(counteragentId)).val(),
        address:
            $("#address_".concat(counteragentId)).val()
    };

    $.ajax({
        type: 'PUT',
        url: '/counteragent',
        contentType: 'application/json; charset=utf-8',
        accessControlAllowOrigin:"http://localhost:8080",
        data: JSON.stringify(JSONCounteragent),
        async: true,
        success: function (JSONCounteragent) {
            console.log("SUCCESS: ", JSONCounteragent);
            //$(url).appendTo('#content');

            getFragmentAndChangeDiv('#content','/counteragent');
            //$('#content').load('/counteragent');
            //$('#content').addClass('popover_menu');
            //$('#content').addClass('module_menu');

            //location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function error_dialog(error_message) {
    var responseJSON = jQuery.parseJSON(error_message);
    var error=JSON.parse(responseJSON);
    var message=error.message;
    //var responseText=error_message.message();
    var dialogWindow = $(this).data('dialog');
    $('#container').toggleClass('blur_3');
    $('#content').toggleClass('locked');
    $('#dialog_wrapper').fadeToggle(300);


    document.getElementById('dialog_wrapper').style='display: block';
    document.getElementById('id_error_message').innerHTML=error_message;
    document.getElementById('id_error_message_title').innerHTML='Сообщение об ошибках';

    setTimeout(function() {
        $('#dialog_wrapper .dialog_window.dialog_error_message').toggleClass('hide_me');
    }, 150);

    setTimeout(function() {
        $('#dialog_wrapper .dialog_window.dialog_error_message').toggleClass('open');
    }, 300);
}
function change_and_run_error_popup(error_data) {
    var text = error_data.responseText;
    if (text === "") {
        return;
    }
    $('#error_content').html(text)
    loadOptions();
    run_error_popup_dialog();
}

function run_error_popup_dialog() {

    var dialogWindow = $(this).data('dialog');
    $('#container').toggleClass('blur_3');
    $('#content').toggleClass('locked');
    $('#dialog_wrapper').fadeToggle(300);

    document.getElementById('dialog_wrapper').style='display: block';

    setTimeout(function() {
        $('#dialog_wrapper .dialog_window.dialog_runtime_error_message').toggleClass('hide_me');
    }, 150);
    setTimeout(function() {
        $('#dialog_wrapper .dialog_window.dialog_runtime_error_message').toggleClass('open');
    }, 300);

}


function updateUserRequest(userId) {
    var JSONObject = {
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
        url: '/user',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        async: true,
        success: function (result) {
            alert('At ' + result.time
                + ': ' + result.message);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function updateUserRoleRequest(userId) {

    var moduleAuthorityDtoList = [];
    var foundModules = $(".user_module_name_".concat(userId));
    alert(foundModules);
    for (var i = 0; i < foundModules.length; i++) {
        var foundModuleName = foundModules[i].val();
        // var rolesMap = new Map();
        // var foundRoles = $("#user_module_role_name_".concat(userId, "_", foundModuleName));
        // for (var j = 0; i < foundRoles.length; j++) {
        //     rolesMap.set(foundRoles[j].text, true)
        // }
        moduleAuthorityDtoList.push({
            moduleName: foundModuleName,
            moduleMap: {
                "AAAA": true
            }
        })

    }
    var userAuthorityDto = {
        userId: userId,
        moduleAuthorityDtoList: moduleAuthorityDtoList

    };

    $.ajax({
        type: 'POST',
        url: '/updateUserRoles',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userAuthorityDto),
        dataType: 'json',
        async: true,
        success: function (result) {
            alert('At ' + result.time
                + ': ' + result.message);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function deleteUser(userId) {
    $.ajax({
        type: 'DELETE',
        url: '/user/'.concat(userId),
        dataType: 'json',
        async: true,
        success: function (result) {
            alert('At ' + result.time
                + ': ' + result.message);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function deleteKS(url,Id,counteragent_id,contract_id,contract_name,counteragent_name) {
    $.ajax({
        type: 'DELETE',
        url: url.concat(Id),
        dataType: 'json',
        async: true,
        success: function () {
            getFragmentParamAndChangeDiv('#content','/ks',counteragent_id,contract_id,contract_name,counteragent_name,0);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //alert(jqXHR.status + ' МАМОЧКИ ' + jqXHR.responseText);
            getFragmentParamAndChangeDiv('#content','/ks',counteragent_id,contract_id,contract_name,counteragent_name,0);
        }
    });
}

function deleteContract(url,Id,counteragent_id,counteragent_name) {
    $.ajax({
        type: 'DELETE',
        url: url.concat(Id),
        dataType: 'json',
        async: true,
        success: function () {
            getFragmentParamAndChangeDiv('#content','/contract',counteragent_id,counteragent_id,counteragent_name,counteragent_name,0);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //alert(jqXHR.status + ' ' + jqXHR.responseText);
            getFragmentParamAndChangeDiv('#content','/contract',counteragent_id,counteragent_id,counteragent_name,counteragent_name,0);
        }
    });
}

function deleteCounteragent(url,Id) {
    $.ajax({
        type: 'DELETE',
        url: url.concat(Id),
        dataType: 'json',
        async: true,
        success: function () {
            getFragmentAndChangeDiv('#content','/counteragent');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            //alert(jqXHR.status + ' ' + jqXHR.responseText);
            getFragmentAndChangeDiv('#content','/counteragent');
        }
    });
}



function updateEventCount(url) {
    $.ajax({
        url: url, // указываем URL и
        dataType: 'json', // тип загружаемых данных
        success: function (resul) {
// вешаем свой обработчик события success
            $("#content").replaceWith(url)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}