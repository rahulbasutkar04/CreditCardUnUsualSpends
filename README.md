# Credit Card Management System

## Overview

This system provides functionalities for managing customer information, credit cards, transactions, expenditures, and notifications. The services are designed to handle creating customers, associating credit cards, performing transactions, analyzing expenditures, and sending notifications.

## Controllers

### CustomerController
- **CreateCustomer(int id, String Name, String Email)**
    - Calls the service to create a customer.
    - Generates a unique ID automatically.
    - Saves the customer data in the database associated with the ID.

### CreditCardController
- **GetCreditCard(Long CreditCardNumber)**
    - Takes a credit card number and saves it in the database.
    - Associates the credit card number with the customer ID generated during customer creation.

### TransactionController
- **performTransactionfor(Long CreditCardNumber)**
    - Calls the service that performs all transactions under the given credit card number.

### ExpenditureController
- **getSpendsOf(Long CreditCardNumber)**
    - Retrieves unusual and usual spending.
    - Composes and sends an email accordingly.

### NotifierController
- **SendNotification()**
    - Sends notifications to customers.

## Outer Services

### CustomerService
- **createCustomer(int id, String Name, String Email)**
    - Creates a unique customer and saves them into the repository.

### CreditCardService
- **ReciveCard(Long ccNumber)**
    - Takes the credit card number from the user.
    - Retrieves the user ID from the customer repository.
    - Saves the credit card number associated with the user ID.

### TransactionService
- **performTransaction(Long CreditCardNumber)**
    - Performs all transactions under the given credit card number and saves them in the repository.

### ExpenditureService
- **getUnusualSpendFor(Long creditCardNumber)**
    - Sends transactions from the repository to the inner service.
    - Calls the inner service to get the unusual spend amount with category and saves it in the repository.

- **getUsualSpend(Long creditCardNumber)**
    - Sends transactions from the repository to the inner service.
    - Calls the inner service to get the usual spend amount with category and saves it in the repository.

### NotificationService
- **sendEmailTo(int id)**
    - Takes data from the repository for usual and unusual spending.
    - Retrieves customer details from the customer repository.
    - Composes and sends an email.

## Domain Model

### Enum: Categories
- Defines categories for expenditures.

## Inner Services

### Transaction
- **performTransaction()**
    - Initializes the transaction.
    - Sends the transaction data back to the outer service to save into the repository.

### UnusualSpendAnalyser
- **getUnusualSpend(TransactionData)**
    - Returns unusual spend with category as a map `<Category, Int>`.

### UsualSpendAnalyser
- **getUsualSpend(TransactionData)**
    - Returns total usual spend amount with category.

## Repositories

### CustomerRepository
- Stores customer data (ID, name, email).

### CreditCardRepository
- Stores credit card data (ID, credit card number).

### TransactionRepository
- **SaveCurrentMonthTransaction()**
    - Saves current month transactions.

- **SavePreviousMonthTransaction()**
    - Saves previous month transactions.

### ExpenditureRepository
- **SaveUnusualSpend(Map<Category, Int> amount)**
    - Saves unusual spend data.

- **SaveUsualSpend(Int amount)**
    - Saves usual spend data.
