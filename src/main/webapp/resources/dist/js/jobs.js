function showPagination(totalPages){

$('.demo4_top,.demo4_bottom').bootpag({
    total: totalPages,
    page: 2,
    maxVisible: 10,
    leaps: true,
    firstLastUse: true,
    first: 'First',
    last: 'Last',
    wrapClass: 'pagination',
    activeClass: 'active',
    disabledClass: 'disabled',
    nextClass: 'next',
    prevClass: 'prev',
    lastClass: 'last',
    firstClass: 'first'
}).on("page", function(event, num){
    $(".content4").html("Page " + num); // or some ajax content loading...
});

}