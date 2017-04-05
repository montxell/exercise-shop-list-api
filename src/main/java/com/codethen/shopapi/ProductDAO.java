package com.codethen.shopapi;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class ProductDAO {

    private List<Product> products;
    private int id;



    public ProductDAO() {

        this.products = new ArrayList<>();
        this.id = 1;



        Product p1 = new Product();

        p1.setId(incrementId());
        p1.setName("tv");
        p1.setPrice(1000);
        p1.setAvailable(true);

        products.add(p1);


        Product p2 = new Product();

        p2.setId(incrementId());
        p2.setName("radio");
        p2.setPrice(50);
        p2.setAvailable(false);

        products.add(p2);


        Product p3 = new Product();

        p3.setId(incrementId());
        p3.setName("computer");
        p3.setPrice(800);
        p3.setAvailable(true);

        products.add(p3);


        Product p4 = new Product();

        p4.setId(incrementId());
        p4.setName("fridge");
        p4.setPrice(1500);
        p4.setAvailable(true);

        products.add(p4);


        Product p5 = new Product();

        p5.setId(incrementId());
        p5.setName("microwave");
        p5.setPrice(200);
        p5.setAvailable(false);

        products.add(p5);


    }



    public List<Product> getProducts() {
        return products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int incrementId() {
        return this.id ++;
    }




    public List<Product> getProductsByAvailability(Boolean available) {

        if (available == null) {

            return products;
        }


        // OPTION with FILTER

        return products.stream()
                .filter(product -> product.isAvailable())
                .collect(Collectors.toList());


/*
    // ALTERNATIVE OPTION

        List<Product> result = new ArrayList<>();

        for (Product product : productDAO.getProducts()) {

            if (product.isAvailable() == available) {

                result.add(product);
            }
        }

        return result;
    }
*/

    }



    public Product findProductById(int id) {

        for (Product product : products) {
            if(product.getId() == id) {
                return product;
            }
        }

        return null;
    }



    public Product findBestProduct() {

        double maxPrice = 0;
        Product bestProduct = null;

        for (Product product : products) {

            if (product.isAvailable() && product.getPrice() > maxPrice) {

                maxPrice = product.getPrice();
                bestProduct = product;

            }

        }

        return bestProduct;
    }



    public Product createProduct(Product product) {

        int newId = incrementId();

        product.setId(newId);

        System.out.println("Creating the product: " + product.getName());

        products.add(product);

        return product;

    }



    public Product updateProduct(int id, Product product) {

        Product currentProduct = findProductById(id);

        System.out.println("Updating the product ID: " + id);

        products.set(products.indexOf(currentProduct), product);

        return product;
    }



    public Product deleteProduct(int id) {

        Iterator<Product> iter = products.iterator();

        while (iter.hasNext()) {
            Product product = iter.next();
            if(product.getId() == id ) {
                iter.remove();
                System.out.println("Deleting the product: " + product.getName());
                return product;
            }
        }

        return null;
    }

}
