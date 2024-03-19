package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**SpringBootApplication introduces autoconfiguration:
 *  - component scan so that we don't have to define beans with @Bean or XML
 *  - define this class as a configuration file
 *  - autoconfiguration for some defaults (eg set up a integrated tomcat server
 *  and attempt to form a DB connection)*/
@SpringBootApplication
public class Main {

    public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
    }
}


//import io.javalin.Javalin;
//import org.example.Controller.ProductController;
//import org.example.DAO.ProductDAO;
//import org.example.DAO.SellerDAO;
//import org.example.Service.ProductService;
//import org.example.Service.SellerService;
//import org.example.Util.ConnectionSingleton;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Connection;
//
//public class Application {
//
//    public static Logger log = LoggerFactory.getLogger(Application.class);
//
//    public static void main(String[] args) {
//
//        Connection conn = ConnectionSingleton.getConnection();
//        SellerDAO sellerDAO = new SellerDAO(conn);
//        ProductDAO productDAO = new ProductDAO(conn);
//        SellerService sellerService = new SellerService(sellerDAO);
//        ProductService productService = new ProductService(productDAO, sellerDAO);
//        ProductController productController = new ProductController(sellerService, productService);
//
//        Javalin api = productController.getAPI();
//
//        api.start(9002);
//
//
//    }
//}