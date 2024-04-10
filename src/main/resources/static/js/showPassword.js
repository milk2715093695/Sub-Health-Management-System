'use strict';

function showPassword(passwordFieldId) {
    let inputField = document.getElementById(passwordFieldId);
    if (inputField) {
        if (inputField.type === "password") {
            inputField.type = "text";
        } else {
            inputField.type = "password";
        }
    }
}