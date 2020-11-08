---
layout: page
title: Ting Xiao's Project Portfolio Page
---

## Project: LogOnce

LogOnce is a one-stop logistics tracker app for clerks to monitor shipping statuses of all clients and perform common 
logistics operations. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to search for orders using `find-order` and optional tokens.
    [\#65](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/65)
    [\#86](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/86)
  * What it does: allows the user to search for specific orders quickly by its description, associated client, delivery address and delivery date.
  * Justification: This feature improves the product significantly because a user can now quickly search for a specific order based on different criteria. It is especially useful
  when the user has a list of many orders and it becomes difficult to scroll through a list to pinpoint a single order.
  * Highlights: This implementation was based on an enhancement of the original `find` which I also wrote (found under **Enhancements**). The usage of optional tokens meant that I had to significantly change
  the implementation of basic `find` and `find-order` features. Multiple predicate classes had to be added including 2 new predicate wrapper classes to 
  allow for the use of optional tokens.
  * Credits: The basic implementation was inspired by the original `find` in AB3. 

* **New Feature**: Added a done command to allow users to mark certain orders as done
    [\#46](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/46)
    [\#157](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/157)
  * What it does: allows the user to mark orders as completed or done by using their unique order ID
  * Justification: It is a basic feature of our application that is meant to help logistic clerks to track outstanding orders.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=tigerting98)

* **Project management**:
  * Managed releases `v1.2.1` - `v1.4` (3 releases) on GitHub
  * Created GitHub team and team repository on GitHub
  * Created labels used for issues on GitHub
  * Helped out with label indicators and pull requests on GitHub

* **Enhancements to existing features**:
  * Enhanced and adapted the original find command in AB3 for finding persons to find clients instead and allow for the use of optional tokens
  to narrow down the search range [\#86](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/86)
  

* **Documentation**:
  * User Guide:
    * Added documentation for the features `done`, `find` and `findorder`
    [\#94](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/94)
    [\#190](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/190)
    * Initial edits to UG during project setup
    [\#25](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/25)
    
  * Developer Guide:
    * Added implementation details of the `find` and `find-order` feature
    [\#67](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/67)
    [\#190](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/190)

* **Community**:
  * Reported bugs and suggestions for other teams in the class (examples:
  [1](https://github.com/tigerting98/ped/issues/2), 
  [2](https://github.com/tigerting98/ped/issues/2),
  [3](https://github.com/tigerting98/ped/issues/2))

* **Testing**
  * Added tests to cover all new classes authored by me
  [\#173](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/173)
  [\#183](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/183)
  [\#185](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/185)
  [\#186](https://github.com/AY2021S1-CS2103-F09-4/tp/pull/186)
