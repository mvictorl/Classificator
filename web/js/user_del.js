$('#ModalDelete').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var name = button.data('name')
    var id = button.data('id')
    var modal = $(this)

    modal.find('.modal-body h4').text(name)
    modal.find('.modal-footer a').attr('href', 'deleteUser?id='+id)
})