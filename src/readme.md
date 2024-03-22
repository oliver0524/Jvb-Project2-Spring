# This application is using Spring framework to initiate a web server. 
# The application is handling two models, Products and Sellers through http Postman requests

# Samples JSON requests
- POST/GET Product (seller could be an existing or a new one): http://localhost:9002/product
  {
    "name": "galaxy22",
    "price": 1500,
    "sellername": {
    "sellername": "Android"  // Seller name within the nested object
    }
- POST/GET Sellers: http://localhost:9002/seller
  {
  "sellername": "Android"
  }
  - UPDATE/DELETE Product: http://localhost:9002/product/2
    {
    "name": "iphone12",
    "price": 100,
    "sellername": {
    "sellername": "Apple"
    }
    }

# Product Model
-  Product Model structure:
  * Product Product Id (Primary key) 
  * Product Name 
  * Price 
  * Seller Name

-  Product Service requests:
  * GET all products
  * POST product 
    * Add a single product
    * Product ids should be non-null and unique
    * Product names should be non-null
    * Price should be over 0
    * Seller name (can be an existing or a new one)
  * PUT by product-id
    * Update a single product
    * Product names should be non-null
    * Price should be over 0
    * Seller name should refer to an actually existing seller
  * DELETE by product-id
    * Delete a single product
    * DELETE should always return 200, regardless of if the item existed at the start or not. This is convention.

# Seller Model
- Seller Model structure:
  * Seller Name

- Seller Service requests:
    * GET all sellers
    * POST seller
      * Selle name must be non-null & unique
 

# Logging
- Logging is not working with Javalin

