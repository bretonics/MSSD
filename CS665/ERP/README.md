# Implementation Description

This Enterprise Resource Planning Application uses a main `ERP` class that initiates the ERP Application. The assignment was to add HR functionality. Designed for reusability, this was implemented in modules, i.e. a [HR module](#hr-module), that is launched by the ERP instance. This makes adding additional functionality, i.e. TBD modules ([Accounts](#accounts-module) and [Marketing](#marketing-module)), be added trivially. In the main menu, we can observe the "Accounts" and "Marketing" modules that will eventually be supported.

Once the ERP application launches, it outputs a welcome message and initializes a Database instance to track the data. At this moment the Database class also loads some “dummy” data of current Employees to simulate a database. It also instantiates a `Modules` object, providing all available modules, i.e. functionalities, of the ERP application so that the user can select their desired operation and launch the appropriate functionality, e.g. the HR functionality, using the Modules class methods.

The ERP class is the entry point to all the available functionalities implemented in the `Modules` class, effectively wrapping and making it the launching point for each module, where each functionality and it's own actions (options) are controlled by it's own class, e.g. `HR`.


HR Module
---
Launching the HR functionality instantiates an Employee object. Though some operations don’t require an Employee and could be argued that this should only exist **if** an Employee is needed, in practice there are more cases where an Employee is used and in theory in real life, additional, if not most, operations by HR will be on an Employee.

The HR functionality has 6 options:

1. Enter new employee profile
2. Get employee information
3. Modify employee
4. See benefit offerings
5. Organization overview
6. Report issue

Checks were implemented to assure user input in all Prompts is within number of available options, but assumes user will enter an Integer. It would be desirable to assure text input is handled appropriately (service pack 1).

Each case is handled uniquely in the `HR` (HR.java) class usually involving an operation on an Employee object or the Database object by calling their class methods. Option 4 is handled by the Benefits class.

For instance, option `1` requires input from a user from a Prompt, but also requires storing the new employee in the database. So call on both the Employee and objects are made to ask user for input and store this Employee in the Database using their own respective class methods, `newHire()` and `add()`, respectively. Options `2`, `3`, and `6` only required method calls on the Database: `getEmployee()`, `updateRecord()`, and `addIssue()`, respectively.


Accounts Module
---
As a functionality of the ERP system, and because the ERP was designed initially with integrability and reusability in mind, the `Accounts` module follows the same principles and designs implemented for the `HR` module in the ERP; utilizing the `Modules`, `Menus`, and `Prompt` classes to initiate and run this module, and leveraging implemented methods previously created, e.g.) Databases `getEmployee()`.

Accounts is a `Modules` object instantiated and launched by the ERP class that integrated the following 8 functionalities (options) to the ERP system:

1. Generate expense report
2. View expense report
3. Generate invoice
4. View invoice
5. Settle invoice
6. Generate paycheck
7. View paycheck
8. View employee salary

It uses static helper methods to run the logic for some of the options to encapsulate functionality. For example, the `generateExpenseReport()` method helps handle option 1 and does all the steps required to generate a new report. Rather than having each step inside the switch logic, this method factors out all the logic necessary for this option in one convenient call. The same was done with `viewPaycheck(Paycheck paycheck)`, `viewInvoice(Invoice invoice)`, and `settleInvoice(Invoice i)` as these required multiple steps and further logic. Simpler options do step-trough implementation.

`Accounts` consists of **singleton** design patterns implemented specifically for `Reports`, `Invoice`, and `Paycheck` classes to be created only one at a time, where the same instance persist but is modified with different values throughout user executions. For demonstration purposes, this allows the program to call, view, and/or store the last generated report, invoice, and paycheck, and serves like a sort of "placeholders" using the same instance. Calling a "generate" option (1, 3, and 6) overrides that particular instance's information so as to contain the modified attributes and essentially making a new, current version.

Furthermore, the most significant implementation was on the `Database` instance. It was re-designed to be a **singleton** pattern as this database object persists throughout the application, storing and retrieving all the information the application utilizes.

User interface provides nicely formatted interpretations of some actions such as, a paycheck or invoice.

Ex.)
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
| Quip                                             101   |
|                                                        |
|  Paid to the                                           |
|  order of         _____ Andres _______________ $1000   |
|                                                        |
|                                                        |
|   :01340501:30430530450                                |
|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
```


Marketing Module
---
As a functionality of the ERP system, and because the ERP was designed initially with integrability and reusability in mind, the `Marketing` module follows the same principles and designs implemented for the `HR` and `Accounts` modules in the ERP; utilizing the `Modules`, `Menus`, and `Prompt` classes to initiate and run this module.

Marketing is a `Modules` object instantiated and launched by the ERP class that integrated the following  functionalities (options) to the ERP system:

1. Create campaign
2. Marketing items
3. See all campaigns
4. Modify campaign

Utilizing an **abstract factory** design pattern, the `Marketing` module integrated an **abstract** `Campaign` class as an interface for creating abstract `Campaign` objects of three different types of campaigns: SALE, OVERSTOCK, XMAS (enum type), for three different packages of promotional items: PROMO1, PROMO2, and PROMO3 (List<String> type). These `enum` types reflect the different types of campaigns that can be created.

> Using an **abstract factory** pattern allows the abstraction of creating objects with any combination from the different types of campaigns and sets of promotional items via the utilization of only generic interfaces without specifying their concrete classes.

The `CampaignFactory` class guides the creation of different `Campaign` objects using the different types and promotional packages by handling the logic and abstracting the actual object creation. Once the user selects the desired `Campaign` type (*SALE, OVERSTOCK, XMAS*) from the main menu, the `Marketing module` calls the `CampaignFactory` class to create a `Campaign`, passing it a campaign type as an argument; `CampaignFactory.createCampaign(type)`. Now that the `CampaignFactory` knows to create a specific type of campaign, it delegates the creation of this `Campaign` object to **concrete factories** for the different types of promotional item packages (*PROMO1, PROMO2, and PROMO3*) selected by the user it should include for this campaign, `PROMO1Factory.createCampaign(type)`. This is accomplished by a `switch` statement in the `CampaignFactory` that knows which concrete factory to call for the promotional items to include in the campaign creation. This ensures that we are calling the appropriate factory for the promo items accordingly in this campaign type creation.

These concrete factories (*PROMO1Factory, PROMO2Factory, PROMO3Factory*) take the original `Campaign` type passed by the user as an argument, which in turn delegate the **actual object creation** by instantiating a new object (`new SALECampaign(items)`) from *SALECampaign, OVERSTOCKCampaign, and XMASCampaign* classes that **extend** from the **abstract** `Campaign` class. At this point the concrete factory sets the appropriate promotional items because we have called a specific PROMO factory. It then delegates the *actual object creation* via another `switch` statement for the `Campaign` type (passed all the way from the initiating call in the `Marketing` client calling the `CampaignFactory`), and thus ensuring that we instantiate the appropriate `Campaign` object with the specified type (*SALE, OVERSTOCK, XMAS*) and appropriate promotional items, e.g) `new SALECampaign(items)`. This actual object is then returned to the client. In this fashion, selecting any campaign type and promotional items combination results in a `Campaign` object creation, but with different attributes, in an abstract manner using a generic interface.


Compile
---
Compiled and tested using Java 11.0.1.

`javac ERP.java`


Run
---
`java ERP`
