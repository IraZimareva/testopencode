$(document).ready(function () {

    // Invoke the corresponding URL to update the contacts section using Ajax
    $('.update-questions').on('click', 'button[data-update-questions-url]', function () {
        let url = $(this).data('update-questions-url');

        // adding the row index, needed when deleting a contact
        let formData = $('form').serializeArray();
        console.log(formData);
        let param = {};
        param["name"] = $(this).attr('name');
        param["value"] = $(this).val();
        console.log(param);
        formData.push(param);

        // updating the contact section
        $('#tblQuestions').load(url,formData)
    });

    // autodismiss alerts
    window.setTimeout(function() {
        $(".alert").fadeTo(500, 0).slideUp(500, function(){
            $(this).remove();
        });
    }, 4000);
});