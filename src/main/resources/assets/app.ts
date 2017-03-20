
console.log("Setting up jQuery!");

let $ = jQuery;


// Start showing the list of products
getAllProducts();

// Start hiding the application form
$(".manage-list").hide();



// **** CLICK ON BUTTONS **** //

// Click on button to create a new product
$("#button-new").click(function() {

  // Show button name: save
  $("#button-register").html("Save");

  // Clear the application form from previous actions
  $("input").val(null);

  // Set ID input to disabled as the id is set automatically
  //$("#product-id").attr("disabled", true);

  // Hide inputID
  $("#label-id").hide();
  $("#product-id").hide();

  // Show application form to fill the attributes
  $(".manage-list").show();

  // Call to click button Save method
  clickRegister();

});



// Click on button to edit a product
function clickEdit() {

  $(".button-edit").click(function() {

    // Click on the selected product
    let buttonClick = $(this);
    let productID = buttonClick.data("productid");

    // Show button name: update
    $("#button-register").html("Update");

    // Show inputID
    $("#label-id").show();
    $("#product-id").show();

    // Show application form
    $(".manage-list").show();

    // Get attributes of the selected product to show on the application form.
    // Call to end-point:
    getProductById(productID);

    // Click on button to save(update) the changes of the attributes
    clickRegister();

  });

}



// Click on button to add a product to the list or to update a product from the list
function clickRegister() {

  $("#button-register").unbind("click");

  $("#button-register").click(function() {

    if ($("#button-register").html() == "Save") {

      // Add the new product. Call to end-point (it will get all the products with the new addition):
      addProduct();

    } else {

      // Update the attributes. Call to end-point:
      updateProduct();

      // Set ID input to enabled
      $("#product-id").attr("disabled", false);

    }

    // Hide application form
    $(".manage-list").hide();

  });

}



// Click on button to remove a product from the list
function clickDelete() {

  $(".button-delete").click(function() {

    // Hide application form
    if ($(".manage-list").show()) {
      $(".manage-list").hide();
    }

    // Click on the selected product
    let buttonClick = $(this);
    let productID = buttonClick.data("productid");

    // Delete the product by its id. Call to end-point:
    deleteProduct(productID);

  });
}



// Click on button to cancel
$("#button-cancel").click(function(){

  // Set ID input to enabled
  $("#product-id").attr("disabled", false);

  // Empty the application form
  $("input").val(null);

  // Hide application form
  $(".manage-list").hide();

});



// **** CALL TO END-POINTS **** //


// GET ALL THE PRODUCTS
function getAllProducts() {

  let settings = {
      method: "GET",
      url: "http://localhost:8080/api/products/",
      dataType: "json"
  }

  console.log("Sending AJAX call!");

  let promise = $.ajax(settings);

  promise.done(function(data) {

      console.log("Received a successful response! Getting all products!");
      console.log(data);

      // Remove the previous list of products
      $("#products li").remove();

      // Create the list of productts
      let products = data;

      for (let product of products) {
        let productLi = createProductsList(product);
        $("#products").append(productLi);

      }

      clickEdit();

      clickDelete();

  });

  promise.fail(function(jqXHR, textStatus, errorThrown) {
  console.error("Call failed: ", jqXHR);
  });
}



function createProductsList(product) {

  let name = product.name;
  let id = product.id;

  let nameElem = $("<p>").addClass("product-name").text(name);

  let buttonDiv = $("<div>").addClass("list-buttons");
  let buttonEdit = $("<button>").addClass("button-edit").attr("data-productid", id);
  let imageEdit = $("<i>").addClass("fa fa-pencil-square-o fa-lg").attr("aria-hidden", "true");
  let buttonDelete = $("<button>").addClass("button-delete").attr("data-productid", id);
  let imageDelete = $("<i>").addClass("fa fa-trash-o fa-lg").attr("aria-hidden", "true");

  buttonEdit.append(imageEdit);
  buttonDiv.append(buttonEdit);
  buttonDelete.append(imageDelete);
  buttonDiv.append(buttonDelete);

  let productLi = $("<li>");
  productLi.append(nameElem);
  productLi.append(buttonDiv);
  return productLi;

}



// GET A PRODUCT BY ITS ID
function getProductById(productID) {

  let settings = {
      method: "GET",
      url: "http://localhost:8080/api/products/" + productID,
      dataType: "json"
  }

  console.log("Sending AJAX call!");

  let promise = $.ajax(settings);

  promise.done(function(data) {

      console.log("Received a successful response! Getting the product: " + data.id);
      console.log(data);

      let product = data;

      $("#product-id").val(product.id);
      $("#product-id").attr("disabled", true);  // Set ID input to disabled

      $("#product-name").val(product.name);
      $("#product-price").val(product.price);
      $("#product-available").val(product.available);

  });

  promise.fail(function(jqXHR, textStatus, errorThrown) {
  console.error("Call failed: ", jqXHR);
  });
}



// ADD A NEW PRODUCT TO THE LIST
function addProduct() {

  let settings = {
      method: "POST",
      url: "http://localhost:8080/api/products/create",
      contentType: 'application/json',
      dataType: "json",
      data: formToJSON()
  }


  console.log("Sending AJAX call!");

  let promise = $.ajax(settings);

  promise.done(function(data) {

      console.log("Received a successful response! Adding a product!");

      // Refresh the list of products to show the new addition. Call to end-point:
      getAllProducts();

  });

  promise.fail(function(jqXHR, textStatus, errorThrown) {
  console.error("Call failed: ", jqXHR);
  });
}



// UPDATE A PRODUCT BY ITS ID
function updateProduct() {

  let productID = $("#product-id").val();

  let settings = {
      method: "PUT",
      url: "http://localhost:8080/api/products/" + productID,
      contentType: 'application/json',
      dataType: "json",
      data: formToJSON()
  }

      console.log("Sending AJAX call!");

      let promise = $.ajax(settings);

      promise.done(function(data) {

          console.log("Received a successful response! Updating the product!");

      });

      promise.fail(function(jqXHR, textStatus, errorThrown) {
      console.error("Call failed: ", jqXHR);
      });

}



// DELETE A PRODUCT BY ITS ID
function deleteProduct(productID) {

  let settings = {
      method: "DELETE",
      url: "http://localhost:8080/api/products/" + productID
  }

  console.log("Sending AJAX call!");

  let promise = $.ajax(settings);

  promise.done(function(data) {

      console.log("Received a successful response! Deleting!");

      // Refresh the list of products to show it without the deleted product.
      // Call to end-point:
      getAllProducts();

  });

  promise.fail(function(jqXHR, textStatus, errorThrown) {
  console.error("Call failed: ", jqXHR);
  });
}



// Turns a Javascript object into JSON String
function formToJSON() {
	return JSON.stringify({
		"id": $("#product-id").val(),
		"name": $("#product-name").val(),
		"price": $("#product-price").val(),
		"available": $("#product-available").val()
		});
}
