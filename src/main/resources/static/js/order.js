function orderInit() {
    searchOrder();
}
function searchOrder(){
    let mo = $('#orderModel').clone();
    $('#orderTable').empty().append(mo);
    $.ajax({
        url: '/order',
        type: 'GET',
        success(data){
            
        }
    });
}