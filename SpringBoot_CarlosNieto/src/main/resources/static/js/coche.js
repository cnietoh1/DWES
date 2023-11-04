$(document).ready(() => {

    $('#borrarCoche').click(function (event) {
        event.preventDefault();
        const id = $(event.target).closest('tr').find('.cocheId').text();
        $.ajax({
            type: "get",
            url: "/coche/delete/show/" + id,
            success: function (htmlRecibido) {
                $('#modal1').html(htmlRecibido);
                $('#delete-modal').modal('show');
            },
            error: function (e) {
                console.log(e);
            }
        });
    });
});