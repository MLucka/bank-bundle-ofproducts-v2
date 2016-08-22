There are three JSON files containing default data:
products.json
bundles.json
quesitons.json

the commands to run the program: 
mvn install
mvn spring-boot:run

To view a product:
http://localhost:8080/product?id=1

To view a bundle:
http://localhost:8080/bundle?id=1

To view the list of products
http://localhost:8080/products

To view the list of bundles
http://localhost:8080/bundles

To add a product
http://localhost:8080/addproduct?name=Debit%20Card%20Gold

To add a product to a bundle
http://localhost:8080/addproductobundle?bundleId=1&productId=5

To remove a product from a bundle
http://localhost:8080/removeproductfrombunlde?bundleId=1&productId=5

To view the list of questions
http://localhost:8080/questions

To view the list of products filtered by answers
http://localhost:8080/productsbyquestions?age=20&isStudent=false&income=23000

To view the list of bundles filtered by answers
http://localhost:8080/bundlesbyquestions?age=20&isStudent=false&income=23000