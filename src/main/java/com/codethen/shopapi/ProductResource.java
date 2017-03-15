package com.codethen.shopapi;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ProductResource {


    private ProductDAO productDAO;


    public ProductResource(ProductDAO productDAO) {

        this.productDAO = productDAO;

    }



    /** returns products based on the availability */
    @GET
    public List<Product> getProductsByAvailability(@QueryParam("available") Boolean available) {

        return productDAO.getProductsByAvailability(available);

    }



    /** returns the product based on the id */
    @GET
    @Path("{id}")

    public Product findProductById(@PathParam("id") int id) {

        return productDAO.findProductById(id);

    }



    /** returns the best product in our catalog: the most expensive of the available products */
    @GET
    @Path("best")
    public Product findBestProduct() {

        return productDAO.findBestProduct();

    }



    /** creates a new product to our catalog */
    @POST
    @Path("create")
    public void createProduct(Product product) {

        productDAO.createProduct(product);

    }



    /** updates a product of our catalog */
    @PUT
    @Path("{id}")
    public void updateProduct(@PathParam("id") int id, Product product) {

        productDAO.updateProduct(id, product);

    }



    /** removes a product from our catalog */
    @DELETE
    @Path("{id}")
    public void deleteProduct(@PathParam("id") int id) {

        productDAO.deleteProduct(id);

    }

}
