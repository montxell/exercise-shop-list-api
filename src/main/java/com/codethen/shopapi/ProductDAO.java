package com.codethen.shopapi;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ProductDAO {

    private List<Product> products;



    public ProductDAO() {

        this.products = new ArrayList<>();


        Product p1 = new Product();

        p1.setId(1);
        p1.setName("tv");
        p1.setPrice(1000);
        p1.setAvailable(true);

        products.add(p1);


        Product p2 = new Product();

        p2.setId(2);
        p2.setName("radio");
        p2.setPrice(50);
        p2.setAvailable(false);

        products.add(p2);


        Product p3 = new Product();

        p3.setId(3);
        p3.setName("computer");
        p3.setPrice(800);
        p3.setAvailable(true);

        products.add(p3);


        Product p4 = new Product();

        p4.setId(4);
        p4.setName("fridge");
        p4.setPrice(1500);
        p4.setAvailable(true);

        products.add(p4);


        Product p5 = new Product();

        p5.setId(5);
        p5.setName("microwave");
        p5.setPrice(200);
        p5.setAvailable(false);

        products.add(p5);


    }



    public List<Product> getProducts() {
        return products;
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

        int newId = maxIdProduct();
        newId++;

        product.setId(newId);

        System.out.println("Creating the product: " + product.getName());

        products.add(product);

        return product;

    }


    // Get the maxId from the list of products to set automatically the new product Id
    public int maxIdProduct() {

        int maxId = 0;

        for (Product product : products) {

            if (product.getId() > maxId) {

                maxId = product.getId();

            }
        }

        return maxId;
    }



    public Product updateProduct(int id, Product product) {

        Product currentProduct = findProductById(id);

        System.out.println("Updating the product ID: " + id);

        products.set(products.indexOf(currentProduct), product);

        return product;
    }



    public Product deleteProduct(int id) {

        for (Product product : products) {
            if(product.getId() == id) {
                products.remove(product);
                System.out.println("Deleting the product: " + product.getName());
                return product;
            }
        }

        return null;
    }


}
