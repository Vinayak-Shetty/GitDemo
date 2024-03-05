 @tag
 Feature: Purchase the order from Ecommerce Website
   I want to use this template for my feature file
  
   Background: 
   Given I landed on Ecommerce Page
  
   @tag2
   Scenario Outline: Positive test of submitting the order
  
  	 Given Logged in with username <name> and password <password>
  	 When I add product <productName> to Cart
  	 And Checkout <productName> and submit the order
  	 Then "THANKYOU FOR THE ORDER." is displayed on Confirmation Page
  	 
  	 Examples : 
  	 
  	 | name | password | productName
  	 | vinayak01@gmail.com | Vinayak@12 | ADIDAS ORIGINAL 