
$(document).ready(() => {

    $('.lnkBorrarCancion').click(function (event) {
        event.preventDefault(); // para evitar que se envíe el formulario
        //var id = $(this).closest('tr').find('td').first().text();
        const id = $(event.target).closest('tr').find('.cancionId').text();
        $.ajax({
            type: "get",
            url: "/song/delete/show/" + id,
            success: function (htmlRecibido) {
                $('#paraelmodal').html(htmlRecibido);
                $('#delete-modal').modal('show');
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    $("#fichero").change(function () {
        const file = this.files[0];
        if (file) {
            let reader = new FileReader();
            reader.onload = function (event) {
                $("#image-preview").attr("src", event.target.result);
            };
            reader.readAsDataURL(file);
        }
    });


    $("#buscarPorTitulo").keyup(function () {
        $.ajax({
            type: "get",
            url: "/song/list/filter",
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
