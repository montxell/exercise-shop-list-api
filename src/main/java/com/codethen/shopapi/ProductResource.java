package com.codethen.shopapi;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

// El ProductResource define los endpoints como get, post, put, delete, etc.


@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {


    private ProductDAO productDAO;  // Propiedad Lista de productos como base de datos


    public ProductResource(ProductDAO productDAO) {

        this.productDAO = productDAO;

    }



    //CRUD Methods

    /** returns all the products based on the status of availability */

    @GET
    public List<Product> readProducts(@QueryParam("available") Boolean available) {

        return productDAO.readProducts(available);

    }



    /** returns the product based on the id */

    @GET
    @Path("{id}")

    public Product readProduct(@PathParam("id") int id) {

        return productDAO.readProduct(id);
    }



    /** returns the best product in our catalog: the most expensive of the available products */

    @GET
    @Path("best")
    public Product findBestProduct() {

        return productDAO.findBestProduct();

    }



    /** adds a new product to our catalog */

    @POST
    @Path("create")
    public Product createProduct(@QueryParam("id") int id,
                                 @QueryParam("name") String name,
                                 @QueryParam("price") double price,
                                 @QueryParam("available") boolean available) {


        return productDAO.createProduct(id, name, price, available);

    }



    /** updates a product in our catalog */

    @PUT
    @Path("{id}")
    public Product updateProduct(@PathParam("id") int id,
                                 @QueryParam("name") String name,
                                 @QueryParam("price") double price,
                                 @QueryParam("available") boolean available) {

        return productDAO.updateProduct(id, name, price, available);

    }



    /** removes a product from our catalog */

    @DELETE
    @Path("{id}")
    public Product deleteProduct(@PathParam("id") int id) {

        return productDAO.deleteProduct(id);

    }

}
