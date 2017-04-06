console.log("Setting up jQuery!");
getAllProducts();
$(".manage-list").hide();
$("#button-new").click(function () {
    $("#button-register").html("Save");
    $("input").val(null);
    $("#label-id").hide();
    $("#product-id").hide();
    $(".manage-list").show();
    prepareSaveOrUpdateButton();
});
function clickEditButton() {
    $(".button-edit").click(function () {
        var buttonClick = $(this);
        var productID = buttonClick.data("productid");
        $("#button-register").html("Update");
        $("#label-id").show();
        $("#product-id").show();
        $(".manage-list").show();
        getProductById(productID);
        prepareSaveOrUpdateButton();
    });
}
function prepareSaveOrUpdateButton() {
    $("#button-register").unbind("click");
    $("#button-register").click(function () {
        if ($("#button-register").html() == "Save") {
            addProduct();
        }
        else {
            updateProduct();
            $("#product-id").prop("disabled", false);
        }
        $(".manage-list").hide();
    });
}
function clickDeleteButton() {
    $(".button-delete").click(function () {
        if ($(".manage-list").show()) {
            $(".manage-list").hide();
        }
        var buttonClick = $(this);
        var productID = buttonClick.data("productid");
        deleteProduct(productID);
    });
}
$("#button-cancel").click(function () {
    $("#product-id").prop("disabled", false);
    $("input").val(null);
    $(".manage-list").hide();
});
function getAllProducts() {
    var settings = {
        method: "GET",
        url: "http://localhost:8080/api/products/",
        dataType: "json"
    };
    console.log("Sending AJAX call!");
    var promise = $.ajax(settings);
    promise.done(function (data) {
        console.log("Received a successful response! Getting all products!");
        console.log(data);
        $("#products li").remove();
        var products = data;
        for (var _i = 0, products_1 = products; _i < products_1.length; _i++) {
            var product = products_1[_i];
            var productLi = createProductsList(product);
            $("#products").append(productLi);
        }
        clickEditButton();
        clickDeleteButton();
    });
    promise.fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Call failed: ", jqXHR);
    });
}
function createProductsList(product) {
    var name = product.name;
    var id = product.id;
    var nameElem = $("<p>").addClass("product-name").text(name);
    var buttonDiv = $("<div>").addClass("list-buttons");
    var buttonEdit = $("<button>").addClass("button-edit").attr("data-productid", id);
    var imageEdit = $("<i>").addClass("fa fa-pencil-square-o fa-lg").attr("aria-hidden", "true");
    var buttonDelete = $("<button>").addClass("button-delete").attr("data-productid", id);
    var imageDelete = $("<i>").addClass("fa fa-trash-o fa-lg").attr("aria-hidden", "true");
    buttonEdit.append(imageEdit);
    buttonDiv.append(buttonEdit);
    buttonDelete.append(imageDelete);
    buttonDiv.append(buttonDelete);
    var productLi = $("<li>");
    productLi.append(nameElem);
    productLi.append(buttonDiv);
    return productLi;
}
function getProductById(productID) {
    var settings = {
        method: "GET",
        url: "http://localhost:8080/api/products/" + productID,
        dataType: "json"
    };
    console.log("Sending AJAX call!");
    var promise = $.ajax(settings);
    promise.done(function (data) {
        console.log("Received a successful response! Getting the product: " + data.id);
        console.log(data);
        var product = data;
        $("#product-id").val(product.id);
        $("#product-id").prop("disabled", true);
        $("#product-name").val(product.name);
        $("#product-price").val(product.price);
        $("#product-available").val(product.available);
    });
    promise.fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Call failed: ", jqXHR);
    });
}
function addProduct() {
    var settings = {
        method: "POST",
        url: "http://localhost:8080/api/products/",
        contentType: 'application/json',
        dataType: "json",
        data: formToJSON()
    };
    console.log("Sending AJAX call!");
    var promise = $.ajax(settings);
    promise.done(function (data) {
        console.log("Received a successful response! Adding a product!");
        getAllProducts();
    });
    promise.fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Call failed: ", jqXHR);
    });
}
function updateProduct() {
    var productID = $("#product-id").val();
    var settings = {
        method: "PUT",
        url: "http://localhost:8080/api/products/" + productID,
        contentType: 'application/json',
        dataType: "json",
        data: formToJSON()
    };
    console.log("Sending AJAX call!");
    var promise = $.ajax(settings);
    promise.done(function (data) {
        console.log("Received a successful response! Updating the product!");
    });
    promise.fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Call failed: ", jqXHR);
    });
}
function deleteProduct(productID) {
    var settings = {
        method: "DELETE",
        url: "http://localhost:8080/api/products/" + productID
    };
    console.log("Sending AJAX call!");
    var promise = $.ajax(settings);
    promise.done(function (data) {
        console.log("Received a successful response! Deleting!");
        getAllProducts();
    });
    promise.fail(function (jqXHR, textStatus, errorThrown) {
        console.error("Call failed: ", jqXHR);
    });
}
function formToJSON() {
    return JSON.stringify({
        "id": $("#product-id").val(),
        "name": $("#product-name").val(),
        "price": $("#product-price").val(),
        "available": $("#product-available").val()
    });
}
