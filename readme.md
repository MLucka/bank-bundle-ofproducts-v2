There are three JSON files containing default data:
products.json
bundles.json
quesitons.json

the commands to run the program: 
mvn install
mvn spring-boot:run


the first page:
http://localhost:8080/welcome
To view a product:
http://localhost:8080/product/3
To view a bundle:
http://localhost:8080/bundle/1
To view the list of products
http://localhost:8080/list-products
To view the list of bundles
http://localhost:8080/list-bundles
To choose a bundle:
http://localhost:8080/choose-a-bundle


There is also available:
Json of bundles:
http://localhost:8080/bundles-json
To add a product to a bundle:
http://localhost:8080/add-product-to-bundle-json?bundleId=1&productId=5
To view Json of questions:
http://localhost:8080/questions
To view Json of products filtered by answers:
http://localhost:8080/products-by-questions?age=20&isStudent=false&income=23000
To view the Json of bundles filtered by answers:
http://localhost:8080/bundles-by-questions?age=20&isStudent=false&income=23000
To remove a product from a bundle:
http://localhost:8080/remove-product-from-bundle?bundleId=1&productId=3



