let panels = ['house', 'order', 'bill', 'user'];
let my_data = window.data = {};

$(document).ready(() => {
    console.log('Init');
    for (let p of panels) {
        $('#' + p).hide();
    }
    $('#house').show();
    checkStatus();
    refreshIndex();
});

function changePanel(obj) {
    for (let p of panels) {
        if ($(obj).text().toLowerCase() === p) {
            $('#' + p).show();
        } else {
            $('#' + p).hide();
        }
    }
}

function checkStatus() {
    $.ajax({
        url: '/session/status',
        type: 'GET',
        success(data) {
            console.log(data.code);
            if (data.data.status === true) {
                my_data.email = data.data.email;
                console.log(data.data.status + " init");
            } else {
                console.log('No Sign');
            }
            userInit();
        },
        error(){
            userInit();
            alert('Net Error')
        }
    });

}