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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

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

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
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

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

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

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


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

* has a need to manage a significant number of contacts
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
      
**Use case: create client **

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
        
**Use case: create order **

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
        
**Use case: delete client **

**MSS**

1. User requests to delete a client from client list
2. LogOnce shows a success message along with the client that was just removed.

    Use case ends.
    
**Extensions**

* 1a. The client id is invalid.
    
   * 1a1. LogOnce shows an error message.
   
        Use case ends.
        
**Use case: delete order **

**MSS**

1. User requests to delete an order from order list
2. LogOnce shows a success message, along with the order that was just removed, and the client that initially placed the order.

    Use case ends.
    
**Extensions**

* 1a. The order id is invalid.

    * 1a1. LogOnce shows an error message.
        
        Use case ends.

**Use case: list **

**MSS**

1. User requests to list all orders in the client list
2. LogOnce displays a list of all orders in the order list (and the client that placed the order respectively).

    Use case ends.
    
**Use case: done order **

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

*{More to be added}*

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

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
