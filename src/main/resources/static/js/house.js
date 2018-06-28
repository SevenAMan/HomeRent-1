function houseInit() {
    refreshIndex();
}

function cloneHouseCard(user, info) {
    let ch = $('#house_card').clone().removeAttr("id").removeAttr('hidden');
    let b = $(ch).children().first();
    $(b).children().first().children().first().attr('src', '/house/' + info.image);
    let i = $(b).children().eq(1);
    $(i).children().first().text(info.id);
    $(i).children().eq(1).children().first().text(info.area);
    $(i).children().eq(2).children().first().text(info.living);
    $(i).children().eq(3).children().first().text(info.bed);
    $(i).children().eq(4).children().first().text(info.price);
    $(i).children().eq(5).children().first().text(info.message);
    if (user) {
        $(b).children().eq(2).removeAttr('hidden');
    } else {
        $(b).children().eq(3).removeAttr('hidden');
    }
    return ch;
}

function searchHouse(price1, price2, area1, area2, bed, living, obj, user) {
    $.ajax({
        url: '/house',
        type: 'GET',
        data: {
            price1: price1,
            price2: price2,
            area1: area1,
            area2: area2,
            bed: bed,
            living: living
        },
        success(data) {
            if (data.code === 0) {
                showHouseCard(obj, data.data.houses, user);
            }
        }
    });
}

function showHouseCard(obj, houses, user) {
    $(obj).empty();
    for (let h of houses) {
        let el = cloneHouseCard(user, h);
        $(obj).append(el);
    }
}

function refreshIndex() {
    $('#houseContent').empty();
    searchHouse(0, 99999, 0, 100000, 0, 0, $('#houseContent'), false);
}

function rentThis(obj) {
    let hour = $(obj).parent().children().first().children().first().val().trim();
    if (isNaN(parseInt(hour))) {
        alert("hour illegal");
        return;
    }
    let hId = $(obj).parent().parent().children().eq(1).children().first().text().trim();
    hour = hour * 3600 * 1000;
    let begin = new Date().getTime();
    let end = begin + hour;
    $.ajax({
        url: '/order',
        type: 'POST',
        data: {
            id: hId,
            begin: begin,
            end: end
        },
        success(data) {
            if (data.code === 0) {
                alert("Success")
            } else {
                alert("Please Sign In")
            }
        },
        error() {
        }
    });
}