package com.codethen.shopapi;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {

    private List<Product> products;



    public ProductDAO() {

        this.products = new ArrayList<>();

        products.add(new Product(1, "tv", 1000, true));
        products.add(new Product(2, "radio", 50, false));
        products.add(new Product(3, "computer", 800, true));
        products.add(new Product(4,"fridge", 1500, true));
        products.add(new Product(5,"microwave", 200, false));

    }



    public List<Product> readProducts(Boolean available) {

        // Si ponemos boolean primitivo queremos que retorne los del filtro available solamente, y se debe indicar products?available=true or false
        // Si ponemos Boolean entonces queremos la opción que retorne todos los productos si no ponemos filtro de available /products

        if (available == null) {

            return products;
        }


        List<Product> result = new ArrayList<>();

        for (Product product : products) {

            if (product.isAvailable() == available) {       // Comparación de boooleanos para ver si la disponibilidad es la que
                                                            // yo he pedido en boolean available
                result.add(product);
            }

        }

        return result;

    }



    public Product readProduct(int id) {

        for (Product product : products) {
            if(product.getId() == id) {
                return product;
            }
        }

        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }



    public Product findBestProduct() {

        double maxPrice = 0;
        Product bestProduct = null;

        for(Product product : products) {

            if (product.isAvailable() && product.getPrice() > maxPrice) {

                maxPrice = product.getPrice();
                bestProduct = product;

            }

        }

        return bestProduct;
    }



    public Product createProduct(int id, String name, double price, boolean available) {

        Product product = new Product(id, name, price, available);

        products.add(product);

        return product;

    }



    public Product updateProduct(int id, String name, double price, boolean available) {

        for (Product product : products) {
            if(product.getId() == id) {

                product.setName(name);
                product.setPrice(price);
                product.setAvailable(available);

                return product;

            }
        }

        throw new WebApplicationException(Response.Status.NOT_FOUND);

    }



    public Product deleteProduct(int id) {

        for (Product product : products) {
            if(product.getId() == id) {
                products.remove(product);
                return product;
            }
        }

        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }



}
