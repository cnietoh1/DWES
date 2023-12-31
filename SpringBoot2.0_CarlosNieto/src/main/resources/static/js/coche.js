$(document).ready(() => {

    $('.lnkBorrarCoche').click(function (event) {
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

    $("#buscarPorNombre").keyup(function () {
        $.ajax({
            type: "get",
            url: "/coche/list/filter",
            data: {
                nombre: $('#buscarPorNombre').val()
            },
            success: function (htmlRecibido) {
                $('#listaCoches').html(htmlRecibido);
            },
            error: function (e) {
                console.log(e);
            }
        });

    });

});