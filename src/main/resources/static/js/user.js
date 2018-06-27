function sign_in() {
    let email = $("#email").val().trim();
    let password = $("#password").val().trim();
    if (!validateEmail(email)) {
        alert("Email is illegal");
        return;
    }
    if (password.length < 6 || password.length > 20) {
        alert("Password length illegal");
        return;
    }
    $.ajax({
        url: '/session',
        type: 'POST',
        data: {
            email: email,
            password: password
        },
        success(data) {
            if (data.code === 1) {
                alert("Sign In Error, Check Email and Password");
            } else {
                sign_in_success();
            }
        },
        error() {
            alert("Check Your Net");
        }
    });
}

function sign_up() {
    registerStatus();
}

function sign_out() {
    $.ajax({
        url: '/session',
        type: 'DELETE',
        success() {
            signInStatus();
        },
        error() {
            signInStatus();
        }
    });
}

function sign_in_success(email) {
    signEdStatus();
    my_data.status = true;
    my_data.email = email;
}

function register() {
    let email = $('#email').val().trim();
    let pass1 = $('#password').val().trim();
    let pass2 = $('#password2').val().trim();
    if (!validateEmail(email)) {
        alert("Email is illegal");
        return;
    }
    if (pass1.length < 6 || pass1.length > 20) {
        alert("Password length illegal");
        return;
    }
    if (pass1 !== pass2) {
        alert("Password1 != Password2");
        return;
    }
    $.ajax({
        url: '/user',
        type: 'POST',
        data: {
            email: email,
            password: pass1
        },
        success(data) {
            if (data.code === 0) {
                signEdStatus();
            } else {
                alert("Email is Repeat!")
            }
        },
        error() {
            alert("Check your net");
        }
    });
}

function userInit() {
    if (my_data.email) {
        signEdStatus();
    } else {
        signInStatus();
    }
}

function validateEmail(email) {
    let re = /^([0-9a-zA-Z]([-_\\.]*[0-9a-zA-Z]+)*)@([0-9a-zA-Z]([-_\\.]*[0-9a-zA-Z]+)*)[\\.]([a-zA-Z]{2,9})$/;
    return re.test(email);
}

function signEdStatus() {
    $('#sign_in').hide();
    $('#sign_out').show();
}

function signInStatus() {
    $('#sign_in').show();
    $('#signIn').show();
    $('#signUp').show();

    $('#register').hide();
    $('#sign_out').hide();
    $('#confirm').hide();
}

function registerStatus() {
    $("#sign_in").show();
    $('#register').show();
    $('#confirm').show();

    $('#signUp').hide();
    $('#signIn').hide();
    $('#sign_out').hide();
}