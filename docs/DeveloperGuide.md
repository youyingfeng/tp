---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete-client --client 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete-client --client 1")` API call.

![Interactions Inside the Logic Component for the `delete-client --client 1` Command](images/DeleteClientSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Client>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Client` references. <This>                                                            </This> allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### User Interface

<img src = "images/UiClassDiagram.png" width = "574">

#### Implementation

The updated user interface has three columns: the leftmost column acts as a navigation menu, the middle column displays 
the list of Clients/Orders and the rightmost column is supposed to display more details about the Client/Order.

The switching of views is done by calling methods defined in MainWindow from the FXML file, upon clicking on the 
respective button. These methods are exposed in the `MainWindow` class as `handleClients()` and `handleOrders()`.

Step 1. The user launches the application, and the User Interface is created.

Step 2. The user clicks on the `Orders` button in order to view the orders. The button will call the function 
`handleOrders()`, causing the view in the middle column to display the list of Orders.

Step 2. The user clicks on the `Clients` button in order to view the clients. The button will call the function 
`handleClients()`, causing the view in the middle column to display the list of Clients.

Step 3. The user clicks on the `Help` button in order to find the link to the user guide. The button will call the 
function `handleHelp()`, which will open a dialog box with the link to the user guide.

Step 4. The user clicks on the `Exit` button in order to close the application. The button will call the function 
`handleExit`, which will close the application.

### User Interface: Information panels

Information panels function by taking in an object (`Client` or `Order`) and displaying its information. It provides buttons to do simple actions (e.g. mark an `Order` as done, edit the `Order`/`Client`, and delete the `Order`/`Client`).

The buttons in the information panels communicate with the MainWindow through JavaFX events. When a button is clicked, it fires off a specific event, which is bubbled up to the parent nodes of the information panel. Eventually, the event will reach the MainWindow, where the event will be picked up by the event handlers set in the MainWindow. The MainWindow will then execute actions based on the type of events specified.

Step 1: The information panel is accessed via the user interface. An object is passed to a new instance of the panel, which will convert all the data in the object to a displayable format.

Step 2: The user clicks on the `Done` button to mark an order as complete (`Orders` only). Upon the click, the button will fire off an `OrderCompleteEvent`, which contains the order being marked as complete. The event will be bubbled up and picked up by the event handlers set in the MainWindow, which will then convert this event to a parseable command and execute it.

Step 3: The user clicks on the `Edit` button to modify the Client/Order. Upon the click, the button will fire off an `EditClientEvent()`/`EditOrderEvent()` which contains the specific Client/Order to be edited, which will be bubbled up to the MainWindow and handled there.

Upon receipt of the event, the MainWindow will take the Client/Order wrapped in the event and pass it to a new instance of an EditClientForm/EditOrderForm. It will then clear the side panel and set this form as the child, to replace the view with the view of the form.

Step 4: The user clicks on the `Delete` button in order to delete the Client/Order. Upon clicking, the information panel will generate a command to be executed, and pass the command to a new instance of `DeletionEvent`, which will be fired off.

When this event is bubbled up and captured by the MainWindow, the MainWindow will take the command string wrapped inside the DeletionEvent and execute it.

### User Interface: Forms

Communication between the forms and the user interface is achieves in one of two ways: the first way is through passing the MainWindow object to the creation form, and the second is through using JavaFX's inbuilt event messaging system to pass commands up to the MainWindow, where they will be executed by the handlers.

#### Method 1: by passing the MainWindow object directly

<img src = "images/OrderSequenceDiagram.png" width = "574">

This method is only used by the client creation form `NewClientForm`.

Step 1: User accesses the form through the user interface.

Step 2: User fills in the text fields with the corresponding information.

Step 3: User clicks `Create`. The form will first conduct rudimentary validation checks on the data (primarily to check for nulls and empty fields). 

If the rudimentary checks fail, the form will update the error message display fields to show the relevant error messages.

Once these checks are done, the form will concatenate the content of the fields into a parseable `client` command, and pass it to the MainWindow to be executed.

Step 4: User clicks `Clear`. The form will clear all text in the text fields.

#### Method 2: by messaging the MainWindow through JavaFX events
This method is used in all other forms (both creation and update).

Step 1: User accesses the form through the user interface

Step 2: User fills in the text fields with the corresponding information.

Step 3: User clicks `Create`/`Submit`. The form will first conduct rudimentary validation checks on the data (primarily to check for nulls and empty fields). 

If these rudimentary checks fail, the form will update the error message display fields to show the relevant error messages.

Once these checks are done, the form will concatenate the content of the fields into a parseable `client` command, and pass it to the MainWindow to be executed.

Step 4: User clicks `Clear` (creation forms only). The form will clear all text in the text fields.

Step 5: User clicks `Reset` (update forms only). The form will reset the text in the text fields to the original text of the `Client` or `Order` being updated.

### Find and FindOrder feature

The find mechanism is facilitated by `FindCommand`, `FindCommandParser`, `FindOrderCommand` and `FindOrderCommandParser`. `FindCommandParser` and `FindOrderCommandParser` implement `Parser`.
`FindCommand` and `FindOrderCommand` extend `Command`.

These two commands allows the user to search for `Clients` and `Orders` by the name or description respectively.

The implementations for search operations for both `Clients` and `Orders` are very similar. Given a command (e.g. `find adam jane` or `findorder iPhone iPad`), the command is processed by `AddressBookParser` and
passed to the corresponding parsers. There, the parser will process the given arguments and will search for all `Clients`/`Orders` whose name or description contain any
of the keywords give. For example, If there were two clients, one named 'Adam' and one named 'Jane', both clients will show up in the search results. Likewise, if there were two orders, 
one with the description 'iPhone' and one with the description 'iPad' will be displayed. This is the most basic use of the search operations.
The parsers will split the provided search terms into an array, and match them with client names or order descriptions through the use of streams.

As of v1.4, the two commands have been enhanced to accept optional tokens that can increase the specificity of the search. The token search is facilitated by new predicate classes, the most crucial of which
are the `ClientMultiPredicate` and the `OrderMultiPredicate`. These two `Predicate` classes wrap a list of other predicates and only return true if all predicates in its
list return true when testing the give `Client`/`Order`.

The other predicate classes as follows:
* for `Client`
    * `NameContainsKeywordsPredicate`
    * `ClientPhonePredicate`
    * `ClientEmailPredicate`
    * `ClientAddressPredicate`
* for `Order` 
    * `DescriptionContainsKeywordsPredicate`
    * `OrderDatePredicate`
    * `OrderAddressPredicate`
    * `OrderClientIdPredicate`

Tokens:
* for `Client`
    * `--phone`: the phone number to be searched. A `Client` will only be displayed if its `phone` is an exact match.
    * `--email`: the email address to be searched. A `Client` will only be displayed if its `email` is an exact match.
    * `--address`: the address keywords to be searched. A `Client`'s `address` has to contain at least one of the searched keywords.
* for `Order`
    * `--date`: the delivery date to be searched. An `Order` will only be displayed if its `deliveryDate` is an exact match.
    * `--client`: the clientID to be searched. An `Order` will only be displayed if its associated `Client`'s `clientId` is an exact match.
    * `--address`: the address keywords to be searched. An `Order`'s `deliveryAddress` has to contain at least one of the searched keywords.
    
With the addition of these tokens, searching by name or description keywords is now optional as well, provided at least one other search token is present.
For example `find-order --client 32` will now display all orders of the `Client` with a ID of #00032, regardless of their description.

One or more of the optional tokens can be used in a single search operation regardless of their order. The only rule is that description or name keywords to be searched
must be placed before the first optional token used. Note that using more tokens would limit the search range further, as a `Client`/`Order` must fulfil all the 
search criteria to be displayed.
For example, both of the following commands are valid:
* `find-order iPhone --date 2020-10-23 --client 32 --address Pasir Ris`
* `find-order iPhone --address Pasir Ris --client 32 --date 2020-10-23`

Given below is an activity diagram describing how a user input for finding clients is parsed. The process for parsing commands for finding orders
is also the same, albeit with different tokens and different predicates. Following the diagram, an example usage scenario is also given.
![Activity diagram of FindCommandParser](images/FindActivityDiagram.png)

Step 1. The user launches LogOnce and sees their list of clients. In this scenario, LogOnce has one order with description "iPhone", date "2020-10-23", address
"Pasir Ris" and an associated client with ID #00032.

Step 2. The user executes `find-order iPhone --date 2020-10-23 --client 32 --address Pasir Ris`.

Step 3. `AddressBookParser` parses the command and directs it to `FindOrderCommandParser`.

Step 4. `FindOrderCommandParser` checks the preamble (words before the first token) and recognizes `iPhone`. A `DescriptionContainsKeywordsPredicate` is created
and added to the list of predicates.

Step 5. `FindOrderCommandParser` checks for the presence of the `--client` token. It recognizes `32` as the client ID to be searched.
An `OrderClientIdPredicate` is created and added to the list of predicates.

Step 6. `FindOrderCommandParser` checks for the presence of the `--date` token. It recognizes `2020-10-23` as the date to be searched.
An `OrderDatePredicate` is created and added to the list of predicates.

Step 7. `FindOrderCommandParser` checks for the presence of the `--address` token. It recognizes `Pasir` and `Ris` as the address keywords to be searched.
An `OrderAdressPredicate` is created and added to the list of predicates.

Step 8. `FindOrderCommandParser` then creates a `OrderMultiPredicate` from the list of predicates and uses it to create and return a `FindOrderCommand`.

Step 9. The `FindOrderCommand` is executed and the `OrderMultiPredicate` is used to update the `filteredOrderList`, which will return the only order present in the list,
as that order fulfils all the predicates found inside the `OrderMultiPredicate`.


### Delete Feature

The delete mechanism is split into two functionalities - `Delete Order` and `Delete Client`.

The delete mechanism is facilitated by `DeleteOrderCommand`, `DeleteOrderCommandParser`, `DeleteClientCommand`, 
`DeleteClientCommandParser`, `AddressBook` and `ModelManager`. 

`DeleteOrderCommand` and `DeleteClientCommand` extends `Command`, `DeleteOrderCommandParser` and 
`DeleteClientCommandParser` implements `Parser`, `AddressBook` implements `ReadOnlyAddressBook` and `ModelManager` 
implements `Model`.

These operations are exposed in the `Model` interface as `Model#deletePerson()` and `Model#deleteOrder()`. They are also
exposed in the `AddressBook` class as `AddressBook#removeClient()` and `AddressBook#removeOrder()`.

the `Delete Order` and `Delete Client` differs such that `Delete Order` is dependent on `Delete Client`. This is the 
most distinctive difference where deleting a client from the client list will delete some orders from the order 
list too.

Given below is an example usage scenario and how the delete mechanism behaves at each step.


**Delete Order**

Step 1. The user launches application and views his/her list of orders. In this case, the order list is empty.

Step 2. The user executes `order --description book --client 1 --address 123 --date 2020-12-12 2359`. An order is 
created and appended to the end of the order list.

Step 3. The user decides to remove the order he had just created. The user notes that the unique order ID of the order 
is `#00001`. The user executes `delete-order --order 1` to remove the order from the order list.

The following sequence diagram shows how the delete order operation works :

![Delete Order Sequence Diagram](images/DeleteOrderSequenceDiagram.png)

**Delete Client**

Step 1. The user launches application and views his/her list of clients. In this scenario, the client list is empty.

Step 2. The user executes `client --name john --address 123 --email john@gmail.com --phone 12345678`. A client is 
created and appended to the end of the client list.

Step 3. The user decides to remove the client he had just created. The user notes that the unique client ID of the 
client is `#00001`. The user executes `delete-client --client 1` to remove the client from the client list.

Step 4. When user deletes the client, all orders from the order list that are linked to the client will also be deleted.

The following sequence diagram shows how the delete client operation works :

![Delete Client Sequence Diagram](images/DeleteClientSequenceDiagram.png)

\[Proposed Enhancements\]

Allow user to delete clients and orders based on specific tokens. For example, a client can be deleted as long as all 
relevant tokens (i.e `name`, `address`, `email`, `phone`) are identified correctly and matches a client in the client 
list. Similarly, delete-order can receive `description`, `client`, `address`, `date` in order to delete a specific 
order.

Although the mentioned enhancement might be slower than the original delete commands, it may be useful for very long 
client or order lists, where it is time consuming to scroll through the lists in order to find a client or order's 
unqiue ID. Accepting the mentioned tokens would be beneficial if user already has the specific client details at hand.

Another enhancement would be to allow `delete-order` to delete all orders linked to a specific client ID. This would 
allow the user to clear all orders under a client without having to delete the client itself.

### List Feature

The list mechanism is split into two functionality - `List Order` and `List Client`.

These two commands allows the user to list the `Clients` and `Orders` present in the system respectively.

The list mechanism is facilitated by `ListCommand`, `ListOrderCommand`, `Model`, `ModelManager` and `AddressBook`.

Users can also click on the "Orders" or "Clients" GUI buttons to view the respective lists.

`ListCommand` and `ListOrderCommand` extends `Command`. `AddressBook` implements `ReadOnlyAddressBook` and `ModelManager` implements `Model`.

These operations are exposed in the `Model` interface as `getFilteredOrderList()` and `getFilteredPersonList()`. They are also exposed in the `AddressBook` as `getOrderList` and `getPersonList`.

\[Proposed enhancements\]:
* Use `list` to display the orders of each client in the same panel. 


### Undo Feature

The undo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()` and `Model#undoAddressBook()` respectively.

Given below is an example usage scenario and how the undo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>


<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. 

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()` or `Model#undoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo by
  itself.
  * Pros: Will use less memory (e.g. for `delete-client`, just save the client being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.
  
  

### Update-client feature

The Update-client mechanism is facilitated by `UpdateClientCommand` and `UpdateClientCommandParser`. `UpdateClientCommandParser` implement `Parser`.
`UpdateClientCommand` extend `Command`.

This command allows the user to update an existing `Client` by their index.

The parser will split the provided terms into an array, obtain what field is to be updated (`Name`, `Address`, `Email`, `Phone`) and obtain the field that is to be updated.

Upon calling the `Execute` method in UpdateClientCommand, the model is queried to obtain the list of clients. From this list, the client is obtained, removed and a new client with the updated field is added into the same index as the deleted client.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of client contacts and their respective list of orders
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Allows for convenient and fast generation of internal documents for archiving for the logistics clerk.

The application would speed up their work as they would no longer need to fill up separate forms (invoices, 
shipping manifests etc) with overlapping data as it would now be automated, thus increasing productivity. 
The product will be a base application that can be customized to fit different company standards.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                | I want to …​                                             | So that I can…​                                                    |
| -------- | --------------------- | ------------------------------------------------------- | ----------------------------------------------------------------- |
| `* * *`  | logistics clerk       | add client information                                  | store data of clients                                             |
| `* * *`  | logistics clerk       | add order information                                   | store order data of clients                                       |          
| `* * *`  | logistics clerk       | add warehouse inventory information                     | store inventory data of warehouse                                 |
| `* * *`  | logistics clerk       | delete client information                               | remove client from the client list                                |
| `* * *`  | logistics clerk       | delete order information                                | remove order from the data                                        |
| `* * *`  | logistics clerk       | delete warehouse inventory information                  | remove outdated information                                       |
| `* * *`  | logistics clerk       | view details of individual orders                       | easily view the information i need for every order placed         | 
| `* * *`  | logistics clerk       | view details of individual clients                      | easily view the information i need for every client in the system |
| `* * *`  | logistics clerk       | attach orders to clients                                | easily track all orders involving the particular client           | 
*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `LogOnce` and the **Actor** is the `user`, unless specified otherwise)
      
**Use case: create client**

**MSS**

1. User requests to create a client

2. LogOnce shows a success message, along with the client that was just added.

    Use case ends.
    
**Extensions**

* 1a. The client name is missing
    
   * 1a1. LogOnce shows an error message.
   
        Use case ends.

* 1b. The client's address is missing

    * 1b1. LogOnce shows an error message.
        
        Use case ends.
        
**Use case: create order**

**MSS**

1. User requests to create a order, which was ordered by a specific client.

2. LogOnce shows a success message, along with the order that was just created and attached to the specified client.

    Use case ends.
    
**Extensions**

* 1a. The client name is invalid
    
   * 1a1. LogOnce shows an error message.
   
        Use case ends.

* 1b. The order description is missing

    * 1b1. LogOnce shows an error message.
        
        Use case ends.
        
**Use case: delete client**

**MSS**

1. User requests to delete a client from client list

2. LogOnce shows a success message along with the client that was just removed.

    Use case ends.
    
**Extensions**

* 1a. The client id is invalid.
    
   * 1a1. LogOnce shows an error message.
   
        Use case ends.
        
**Use case: delete order**

**MSS**

1. User requests to delete an order from order list

2. LogOnce shows a success message, along with the order that was just removed, and the client that initially placed the order.

    Use case ends.
    
**Extensions**

* 1a. The order id is invalid.

    * 1a1. LogOnce shows an error message.
        
        Use case ends.
        

**Use case: list order**

**MSS**

1. User requests to list all orders in the order list

2. LogOnce displays a list of all orders in the order list.

    Use case ends.
    
**Use case: list client**

**MSS**

1. User requests to list all clients in the client list

2. LogOnce displays a list of all clients in the client list.

    Use case ends.
        
**Use case: done order**

**MSS**

1. User requests to marks an order attached to a certain client as done.

2. LogOnce shows a success message, along with the order that was marked done, and the client that ordered the order.

    Use case ends.
    
**Extensions**

* 1a. The client id is invalid.
    
   * 1a1. LogOnce shows an error message.
   
        Use case ends.

* 1b. The order id is invalid.
    * 1b1. LogOnce shows an error message.
        
        Use case ends.

**Use case: find order**

**MSS**

1. User requests to find an order

2. LogOnce shows a success message, along with the details of the order.

    Use case ends. 

**Extensions**

* 1a. The arguments are invalid.
    
   * 1a1. LogOnce shows an error message.
   
       Use case ends.
      
**Use case: find client**

**MSS**

1. User requests to find a client

2. LogOnce shows a success message, along with the details of the client.

    Use case ends. 

**Extensions**

* 1a. The arguments are invalid.
    
   * 1a1. LogOnce shows an error message.
   
       Use case ends.

### Non-Functional Requirements

1. Technical Requirements
    * Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
    * The system software should work without requiring an installer.
    * The system software should not depend on any of the developers' or user's remote server.
    * The file sizes of the deliverables should not exceed the limit of 100Mb (JAR file) or 15Mb (PDF file).
2. Performance Requirements
    * Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
    * Should be able to send a response to user within 2 seconds.
    * Should be able to access the data storage within 2 seconds.
    * Tasks that require accessing data storage should be performed immediately upon accessing data storage.
3. Quality Requirements
    * A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
    * The system should be usable by a novice logistics clerk who has prior knowledge on logistical operations but no work experience
    * The system should not contain any audible information or data.
    * A user with experience on handling logistical data should be able to adapt to the system easily.
    * The system should contain visual information or data.
    * The user input should be primarily Command Line Interface (CLI).
    * The system should use Graphical User Interface (GUI) to give visual feedback to users.
4. Security Requirements
    * Any user with access to the system will be able to use it, regardless of administrator rights.
5. Business/Domain Requirements
    * The system should abide to general logistics guidelines and operations.
    * The system does not need to cater specifically to any company's logistics operations.
6. Constraints
    * The system provides general logistical operations and is not catered to any organisation's guidelines or needs.
    * The system should be for a single user.
    * The data should be stored locally and should be in a human editable text file.
    * The system should not use any form of Database Management System.
    * The system software should primarily follow the Object-oriented paradigm.
    * The system software should not be heavily dependent on remote or public APIs.
7. Others
    * The source code should be open source.
    * The system product should be a free online service.     

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Data entry**: An information that is to be keyed into the logistics database
* **Dangerous goods declaration**: Document(s) prepared by a consignor or shipper to certify that the dangerous goods 
being transported have been packaged, labeled, and declared in accordance with the standard international shipping regulations
* **Shipping manifests**: A document that contains a summary of cargo shipping details relevant to the shipping process
* **Certificate of origin**: international trade document, certifying that goods in a particular export shipment are 
wholly obtained, produced, manufactured or processed in a particular country
* **Bill of lading**: legal document issued by a carrier to a shipper that details the type, quantity and destination 
of the goods being carried
* **Freight forwarder**: Intermediary between a shipper and various transportation services
* **Third party warehousing**: organization's use of third-party businesses to outsource elements of its distribution, 
warehousing, and fulfillment services


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting 
point for testers and does not encompass the whole set of available commands.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.
       
1. Viewing available commands

   1. Type `help` in the command box
   
   1. A pop-up window will appear with the URL link to the user guide.
   
   1. Alternatively, the `help` button on the left panel can be clicked to open the same pop-up window. 
   
1. Shutdown application

   1. Type `exit` in the command box
   
   1. Application will automatically close and save the current client and order list details, if any.
   
   1. Alternatively, the `exit` button on the left panel also closes the application.

### Adding a client

1. Adding a client to the client list

   1. Prerequisite(s): Client to be added must not exist in the client list.
   
   1. Test case: `client --name John Doe --address Blk 123 --email john@gmail.com --phone 87654321`<br>
      Expected: Client is added to the list. Details of added client shown in status message. Upon clicking the client, 
      client details will be shown as an enlarged version on the right panel.
      
   1. Test case: `client --name John Doe --address Blk 123 --email john@gmail.com --phone 87654321`(add same client)<br>
      Expected: Client is not added to list as it already exists. Error details shown in status message.

### Adding an order

1. Adding an order to the order list

   1. Prerequisite(s): Order to be added must be linked to an existing client in the client list.
   
   1. Test case: `order --description shoes --client 1 --date 2020-12-12 2359 --address Blk 123`<br>
      Expected: Order is added to the list. Details of added order shown in status message. Order is linked to an 
      existing client with ID `#00001` which was added previously. Upon clicking the order, order details will be shown 
      as an enlarged version on the right panel.
      
   1. Test case: `order --description shoes --client 1 --date 2020-12-12 2359 --address Blk 123`<br>
      Exapected: Similar to previous. There are now two identical orders linked to the same client.

### Deleting a client

1. Deleting a client while all clients are being shown

   1. Prerequisites: List all persons using the `list-client` command. There should only be one client in the 
      client list.

   1. Test case: `delete--client --client 1`<br>
      Expected: Client with unique client ID `#00001` is deleted. Details of the deleted client shown in the status 
      message. 
      
   1. Both orders in the order list will also be deleted as they are linked to the deleted client. Type `list-order` to 
      confirm the deletion of orders.

   1. Other incorrect delete commands to try: `delete-client`, `delete-client --client x`, `...` (where x is larger than
      the list size)<br>
      Expected: Similar to previous.
