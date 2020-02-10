$("document").ready(function () {

    $('#person_details').hide("slow");
    getAllPersons();

});

function deleteItem() {

    var id = $('#d_id').text();

    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: '/persons/deleteperson/' + id,
        type: 'DELETE',
        complete: function (data) {
            window.location.reload();
        }
    });
}


function itemClicked(id) {

    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: '/persons/get/' + id,
        type: 'GET',

        success: function (data) {

            var firstName = data.first_name;
            var lastName = data.last_name;
            var age = data.age;
            var favouriteColour = data.favourite_colour;
            var hobby = data.hobby;
            var id = data.id;

            var hobbies = "<ol>";

            hobby.forEach(function (element) {
                hobbies += '<li>' + element + '</li>';
            })

            hobbies = hobbies + "</ol>";

            $('#d_id').text(id);
            $('#d_f_name').text(firstName);
            $('#d_age').text(age);
            $('#d_l_name').text(lastName);
            $('#d_colour').text(favouriteColour);
            $('#d_hobbies').html(hobbies);

            $('#person_details').show("slow");
        }

    });
}

function getAllPersons() {

    var string = "<ol>";

    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: '/persons/getallpersons',
        type: 'GET',
        success: function (data) {

            data.forEach(element => {

                string += "<div><li onclick='itemClicked(" + element.id + ")'><b>" + element.first_name + "</b>   </li></div>";

            });

            string += "</ol>";
            $('#person_list').html(string);
        }
    });


}

function submit() {

    var add_f_name = $('#add_f_name').val();
    var add_l_name = $('#add_l_name').val();
    var add_age = $('#add_age').val();
    var add_colour = $('#add_colour').val();
    var add_hobbies = $('#add_hobbies').val();

    var hobbies = add_hobbies.split(",");

    var person = {};

    person.first_name = add_f_name;
    person.last_name = add_l_name;
    person.age = add_age;
    person.favourite_colour = add_colour;
    person.hobby = hobbies;

    var person_list = Array();

    person_list.push(person);

    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: '/persons/saveperson',
        type: 'PUT',
        data: JSON.stringify(person_list),
        success: function () {
            parent.history.back();
        },
        complete: function () {
            parent.history.back();
        }
    }).fail(function () {
        parent.history.back();
    });
}


function updateclicked() {

    var id = $('#d_id').text();
    window.location.href = "update.html?id=" + id;
}

function updateload() {

    var id = window.location.href.split("?")[1].split("=")[1];

    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: '/persons/get/' + id,
        type: 'GET',

        success: function (data) {

            var firstName = data.first_name;
            var lastName = data.last_name;
            var age = data.age;
            var favouriteColour = data.favourite_colour;
            var hobby = data.hobby;

            var hobbies = "";

            hobby.forEach(function (element) {
                hobbies += element + ", ";
            });

            hobbies = hobbies.replace(/(^\s*,)|(,\s*$)/g, '');

            $('#u_f_name').val(firstName);
            $('#u_age').val(age);
            $('#u_l_name').val(lastName);
            $('#u_colour').val(favouriteColour);
            $('#u_hobbies').val(hobbies);

        }
    });

}

function updateItem() {

    var id = window.location.href.split("?")[1].split("=")[1];

    var u_f_name = $('#u_f_name').val();
    var u_l_name = $('#u_l_name').val();
    var u_age = $('#u_age').val();
    var u_colour = $('#u_colour').val();
    var u_hobbies = $('#u_hobbies').val();

    u_hobbies = u_hobbies.replace(/(^\s*,)|(,\s*$)/g, '');

    var hobbies = u_hobbies.split(",");

    var person = {};

    person.first_name = u_f_name;
    person.last_name = u_l_name;
    person.age = u_age;
    person.favourite_colour = u_colour;
    person.hobby = hobbies;
    person.id = id;

    console.log(person);

    $.ajax({
        dataType: "json",
        contentType: "application/json",
        url: '/persons/updateperson',
        type: 'POST',
        data: JSON.stringify(person),

        complete: function () {
            parent.history.back();
        }
    });
}

