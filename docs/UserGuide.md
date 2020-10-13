---
layout: page
title: User Guide
---

# LogOnce

**LogOnce** is a one-stop logistics tracker app for clerks to monitor shipping statuses of all clients and perform common logistics operations. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, LogOnce can get your tracking tasks done faster than traditional GUI apps. **This application is currently being developed and has not been deployed yet.**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in angle brackets `<parameter>` are the parameters to be supplied by the user.<br>
  e.g. in `order --description <order description --client <client ID>`, `<client ID>` is a integer parameter such as `123`.

* Words with `--` before them indicate keywords that must be used.<br>
  e.g. `order --description Wooden Table --client 301`. `--description` and `--client` must be typed used.

* Words with `-` before them indicate tokens that are optional and change the functionality of the command.<br>
  e.g `list -a`. `-a` is a token that allows the `list` command to list completed orders in addition to current pending orders.

</div>

### Adding a Client Order : `order`

Creates an order made by a client. The order of the orders made by the client will be (by default) the natural ordering.<br>
Orders will be assigned an ID number automatically when created. This ID number is not unique and must be used in conjunction with the client ID to identify an order.

Format : `order --description <order description> --client <client ID>` 

`<order description>` must exist in the order command
`<client id>` must exist in the order command (an order is always linked to a client)

Example :
`order --description shoes --client 123` : creates an order ordered by client 123 called “shoes”


### Adding a Client : `client`

Creates a new client with the name `<client name>` and the address `<client address>`.

Format : `client --name <client name> --address <client address>`
  
`<client name>` must not be blank.<br>
`<client address>` must not be blank.

Example :<br>
`client --name John Wick --address New York Continental` : adds a client named John Wick whose address is the New York Continental

### Listing Orders : `list` 

Returns a list of orders. The token `-a` can be used to list completed orders as well. 

Format : `listO`<br>
Displays a list of all orders in the order list (and the client that placed the order respectively)

Format: `listC`<br>
Displays a list of all clients in the client list 

`list -a`<br>
Displays a list of current orders, and past orders (completed)

### Deleting a Client : `delete-client`

Deletes a client from client list.

Format : `delete --client <client id>`

Deletes client identified by `<client id>`
`<client id>` must exist in the client list.

Example :
`delete-client --client 999` : deletes the client with client id 999 

### Deleting an Order : `delete-order`

Deletes an order from order list.

Format : `delete --order <order id>`

Deletes order identified by `<order id>`
`<order id>` must exist in the order list.

Example :
`delete-order --order 123` : deletes the order with order id 123

### Completing Orders : `done`

Marks a certain order attached to a certain client as done.

Format : `done --client <client id> --order <order id>`

Marks `<order id>` of client identified by `<client id>` as done.
`<client id>` must exist in the client list
`<order id>` must exist in the client order list

Example :
`done --client 10 --order 3` : deletes the order no. 3 of client with client id 10 

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Order** | `order --description <order description> --client <client ID>` 
**Client** | `client --name <client name> --address <client address>`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**List** | `listC` <br> `listO` <br> `list -a`
**Done** | `done <order id>`
