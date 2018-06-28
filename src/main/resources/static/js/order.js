function orderInit() {
    searchOrder();
}
function searchOrder() {
    let mo = $('#orderModel').clone();
    $('#orderTable').empty().append(mo);
    $.ajax({
        url: '/order',
        type: 'GET',
        success(data) {
            if (data.code === 0) {
                showOrder(data.data.orders)
            }
        }
    });
}
function showOrder(lis) {
    let tt = $('#orderTable')
    for (let o of lis) {
        let m = $('#orderModel').clone().removeAttr('id').removeAttr("hidden");
        m.children().eq(0).text(o.id);
        m.children().eq(1).text(formatTime(o.begin));
        m.children().eq(2).text(formatTime(o.end));
        m.children().eq(3).text(o.house);
        m.children().eq(4).text(o.price/100);
        if(o.type === 1) {
            m.children.eq(5).removeAttr('hidden');
        }
        tt.append(m);
    }
}
function formatTime(ti) {
    let t = new Date(ti);
    let y = t.getFullYear();
    let m = t.getMonth() + 1;
    let d = t.getDate();
    let h = t.getHours();
    let min = t.getMinutes();
    let se = t.getSeconds();
    return str = "" + y + "/" + m + "/" + d + " " + h + ":" + min + ":" + se;
}