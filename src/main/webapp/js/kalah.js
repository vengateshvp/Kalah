/*
* Contains the javascript needed for this application.
*/

$('document').ready(function(){

    $('div.pit').click(function() {
        $.ajax({
        url: "/?pitNumber=" + $(this).attr('pitNumber'),
        success: function (result) {
            location.reload();
        },

        error: function (error) {
            alert('An error occured !');
        }
        });
    });

});