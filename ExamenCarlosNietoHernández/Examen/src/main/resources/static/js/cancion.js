
$(document).ready(() => {

    $('.lnkBorrarCancion').click(function (event) {
        event.preventDefault(); // para evitar que se envíe el formulario
        //var id = $(this).closest('tr').find('td').first().text();
        const id = $(event.target).closest('tr').find('.cancionId').text();
        $.ajax({
            type: "get",
            url: "/cancion/delete/show/" + id,
            success: function (htmlRecibido) {
                $('#paraelmodal').html(htmlRecibido);
                $('#delete-modal').modal('show');
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    $("#buscarPorTitulo").keyup(function () {
        $.ajax({
            type: "get",
            url: "/cancion/list/filter",
            data: {
                titulo: $('#buscarPorTitulo').val()
            },
            success: function (htmlRecibido) {
                $('#listaCanciones').html(htmlRecibido);
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

});
