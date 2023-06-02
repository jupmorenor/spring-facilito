function actorSelected(select) {
    let index = select.selectedIndex;
    let option  = select.options[index];
    let id = option.value;
    let nombre = option.text;
    let urlImagen = option.dataset.url;

    option.disabled = "disabled";
    select.selectedIndex = 0;

    agregarActor(id, nombre, urlImagen);

    let ids = $('#ids').val();

    if (ids === "") {
        $('#ids').val(id);
    } else {
        $('#ids').val(ids + ',' + id);
    }
}

function agregarActor(id, nombre, urlImagen) {
    let htmlString = `
    <div class="card col-md-3 m-2"  style="width: 10rem">
        <img src="{URLIMAGEN}" class="card-img-top">
        <div class="card-body">
            <p class="card-text">{NOMBRE}</p>
            <button class="btn btn-danger" data-id="{ID}" onclick="eliminarActor(this); return false;">Eliminar</button>
        </div>
    </div>
    `;
    htmlString = htmlString.replace("{ID}", id);
    htmlString = htmlString.replace("{NOMBRE}", nombre);
    htmlString = htmlString.replace("{URLIMAGEN}", urlImagen);

    $("#protagonistas-container").append(htmlString);
}

function eliminarActor(btn) {
    let id = btn.dataset.id;
    let node = btn.parentElement.parentElement;
    let idsFiltrados = $("#ids").val().split(",").filter(actor => actor != id);

    $("#ids").val(idsFiltrados.join(","));

    $("#actores option[value='"+ id +"']").prop("disabled", false);

    $(node).remove();
}
