---
layout: page
title: You Yingfeng's Project Portfolio Page
---

## Project: LogOnce

LogOnce is a one-stop logistics tracker app for clerks to monitor shipping statuses of all clients and perform common 
logistics operations. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: GUI forms and panels for all CRUD operations : 
    Pull requests [\#81](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/81), 
    [\#83](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/83), 
    and [\#159](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/159)
  * **What it does:** Allows the user to create new clients and orders, view and update client and order information, 
    and delete existing clients and orders from the application.
  * **Justification:** This feature improves the product significantly because it vastly increases the ease of use for 
    both experienced and new users. New users can use the application immediately without needing to learn a single 
    line of code, while experienced users are given the additional option of using a GUI to perform operations.
    In addition, it presents information in a clearer and more intuitive manner, and makes it more accessible 
    to everyone.
  * **Highlights:** This feature required one to learn more about JavaFX events in depth in order to reduce coupling 
    in the code. It also required some thought about the ideal GUI workflow and layout that would be the most intuitive for end-users. 
    This was also quite time-consuming as the forms and panels created had to cover the entire CRUD functionality of 
    the application, unlike other projects where the UI capabilities were much more limited.
  * **Credits:** GUI layout was made in SceneBuilder.

* **Code contributed**: 
  [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=youyingfeng)

* **Project management**:
  * Helped manage deadlines for deliverables throughout the entire project cycle.
  * Helped to manage the issue tracker for the entire project cycle.

* **Enhancements to existing features**:
  * Refactored existing Person class to Client : 
    [\#41](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/41)
  * Augmented Client and Order with additional fields : 
    [\#57](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/57)
  * Reworked the GUI into a new layout : 
    [\#45](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/45)
  * Wrote additional tests for existing features to increase coverage from 50% to 54% : 
    [\#174](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/174)

* **Testing**:
  * Added tests for `Order`, `OrderCommand`, `OrderCommandParser` and `JsonAdaptedOrder` : 
    [\#174](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/174)

* **Documentation**:
  * User Guide:
    * Added documentation for the entire GUI workflow : 
      [\#166](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/166)
    * Added documentation for the features `update-order` and `update-client` : 
      [\#161](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/161)
    * Performed bug fixes for the existing documentation of features `done`, `order` : 
      [\#164](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/164)
  * Developer Guide:
    * Added implementation details of the GUI forms and information panels : 
    [\#161](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/161)
