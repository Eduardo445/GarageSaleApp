# GarageSaleApp

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Allows users to upload their garage sale location as well as the items that they are selling. Other uses that are looking at the garage sale can buy the items on sale.

### App Evaluation 
[Evaluation of your app across the following attributes]
- **Category:** Online Shopping
- **Mobile:** Having this on mobile gives users the convenience to search for garage sales near the area as well as ability to sell their items
- **Story:** 
- **Market:** Anyone that wants to get more publicity on their garage sale as well as those who are on the lookout on cheap/free second hand items  
- **Habit:** Users can browse through garage sales and buy items. Users can also list their garage sale and items.
- **Scope:** 
- V1 User can create a garage sale and items to sell, other users will be able to see the garage sales and purchase items. 
- V2 Garage sale location can be seen via Google maps (If time permits)

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

 * Set up the Database with Parse
 * Login, Logout, Signout 
 * Home Screen that shows all the garage sales itens
 * Able to make a new listing item
 * Able to click on garage sale item for details and purchase
 * See a list of items purchase in profile
 * See a list of items that you are selling in profile

**Optional Nice-to-have Stories**

 * Filter the Garage Sales in Home Screen

### 2. Screen Archetypes

 * Login Screen
   * Enter username and password to log into your account
   * Signup button if you don't have an account
 * Signup Screen
   * Enter a unique username
   * Enter a strong password
 * List of items (Home Page)
   * Show a list of all items that are being sold
   * Navigation tab with home, new item, profile
 * Item Page
   * Name of garage sale
   * Description of the item being sold
   * Able to purchase it
 * New Listing Page
   * Set the name of your garage sale
   * Have a list of your items
   * Button to add a new item
 * New Item Page
   * Updload an image of the item
   * Description and other labels needed
   * Button to add the item
 * Profile Page
   * A list of items that you have purchased
   * A list of items that you are selling
   * A logout button

### 3. Navigation

**Tab Navigation** (Tab to Screen)

 * Home
 * New Item
 * Profile

**Flow Navigation** (Screen to Screen)

 * Login Page -> Home Screen
 * Login Page -> Signup Page -> Login Page
 * Home Page -> Item Page
 * New Listing Page -> New Item Page
 * New Item Page -> Home Screen
 
   
## Wireframes
![GarageSaleApp](https://i.imgur.com/CxH3dkJ.png)

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
