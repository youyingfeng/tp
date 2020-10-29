---
layout: page
title: User Guide
---

# LogOnce
![LogOnce Header](../docs/images/LogOnceHeader.png)

**LogOnce** is a one-stop logistics tracker app for clerks to monitor shipping statuses of all clients and perform common logistics operations. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, LogOnce can get your tracking tasks done faster than traditional GUI apps. **This application is currently being developed and has not been deployed yet.**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in angle brackets `<parameter>` are the parameters to be supplied by the user.<br>
  e.g. in `order --description <order description --client <client ID>`, `<client ID>` is a integer parameter such as `123`.

* Words with `--` before them indicate keywords that must be used unless otherwise specified.<br>
  e.g. `order --description Wooden Table --client 301`. `--description` and `--client` must be typed used.
  
* Words within `()` indicate optional keywords or tokens (most notably for `find` and `findorder`)

</div>

------------------------------------------------------------------------------------------------------------------------

### Adding a Client Order : `order`

Creates an order made by a client. The order of the orders made by the client will be (by default) the natural ordering.<br>
Orders will be assigned an ID number automatically when created. This ID number is not unique and must be used in conjunction with the client ID to identify an order.

Format : `order --description <order description> --client <client ID>` 

`<order description>` must exist in the order command
`<client id>` must exist in the order command (an order is always linked to a client)

Example :
`order --description shoes --client 123` : creates an order ordered by client 123 called “shoes”

------------------------------------------------------------------------------------------------------------------------

### Adding a Client : `client`

Creates a new client with the name `<client name>` and the address `<client address>`.

Format : `client --name <client name> --address <client address> --email <client email> --phone <client phone no.>`
  
`<client name>` must not be blank.<br>
`<client address>` must not be blank. <br>
`<client email>` must not be blank. <br>
`<client phone>` must not be blank.

Example :<br>
`client --name John Wick --address New York Continental` : adds a client named John Wick whose address is the New York Continental

------------------------------------------------------------------------------------------------------------------------

### Listing Orders : `list` 

Returns a list of orders. The token `-a` can be used to list completed orders as well. 

Format : `listO`<br>
Displays a list of all orders in the order list (and the client that placed the order respectively)

Format: `listC`<br>
Displays a list of all clients in the client list 

`list -a`<br>
Displays a list of current orders, and past orders (completed)

------------------------------------------------------------------------------------------------------------------------

### Deleting a Client : `delete-client`

**Description** : Deletes a client from client list.

**Format** : `delete-client --client <client index>`

Deletes client that has `<client index>` as its index in the client list. 

Note that `<client index>` is not the unique client id given to each client when added. 

`<client index>` is determined solely from the client list indexing, which **starts from `1`**.

`<client index>` must exist as a valid index in the client list, **starting from `1`**.

**Invalid Usage Example** :
![Invalid Delete Client Command](../docs/images/InvalidDeleteClientCommand.png)
Client index should be a valid index in the client list

**Valid Usage Example** :
![Valid Delete Client Command](../docs/images/ValidDeleteClientCommand.png)
`delete-client --client 1` : deletes the client with client index 1

------------------------------------------------------------------------------------------------------------------------

### Deleting an Order : `delete-order`

**Description** : Deletes an order from order list.

**Format** : `delete-order --order <order index>`

Deletes order that has `<order index>` as its index in the order list. 

Note that `<order index>` is not the unique order id given to each order when added. 

`<order index>` is determined solely from the order list indexing, which **starts from `1`**.

`<order id>` must exist as a valid index in the order list, **starting from `1`**.

**Invalid Usage Example** :
![Invalid Delete Order Command](../docs/images/InvalidDeleteOrderCommand.png)
Order index should be a valid index in the order list

**Valid Usage Example** :
![Valid Delete Order Command](../docs/images/ValidDeleteOrderCommand.png)
`delete-order --order 2` : deletes the order with order index 2

------------------------------------------------------------------------------------------------------------------------

### Completing Orders : `done`

Marks a certain order as done.

Format : `done --order <order id>`

Marks `Order` with `<order id>` as done.

`<order id>` must exist in the order list.
`<order id>` is based on the index number of orders in the GUI window.

Example :
`done --client 10 --order 3` : deletes the order no. 3 of client with client id 10 

<<<<<<< HEAD
### Finding clients : `find`

Finds clients by their name and optionally by their phone number, email address and residential address.

Format : `find <name keywords> (--address <address keywords>) (--email <email address>) (--phone <phone number>)`

This command will return clients that match all the tokens provided. The matching criteria for each token
will be explained below.

If any of the optional tokens are present, `<name keywords>` may be left blank.

`<name keywords>` is any amount of keywords, each separated by a space. A client's whose name contains any of these
keywords will be considered a match. For example: `find alex chungus`. Both `Alex Rider` and `Big Chungus` will be
considered matches.

`<address keywords>` is any amount of keywords, each separated by a space. The matching of these keywords works the same
as it does for `<name keywords>`. For example: `find --address jurong clementi` will display clients that have their
address as `Jurong West`, `Jurong East`, `Clementi Avenue 6`, `Clementi Mall`.

`<email address>` is a string that should be a valid email address. The email address has to be an
 exact match for the client to be displayed. For example: `find --email doe@gmail.com`. 
 
`<phone number>` is a string of numbers only with a minimum length of 3. The phone number also has to be an exact match
for the client to be displayed. For example: `find --phone 99223344`.

More examples:
`find alice --phone 9123 --address changi` will display any clients with 'Alice' in their names, with a phone number of 
9123 and who live in Changi. This would be a very specific search, as phone numbers must be an exact match.

`find bob charles --address queenstown commonwealth` will display any clients with either 'Bob' or 'Charles' in their names who also
live in Queenstown or Commonwealth. This would be a more general search, as each client only has to match one keyword in
each category of searching.

### Finding orders : `findorder`

Finds orders by their description and optionally by their attached client IDs, delivery addresses and delivery dates.
Very similar usage to `find`

Format : `findorder <description keywords> (--address <address keywords>) (--date <date>) (--client <client ID>)`

This command will return orders that match all the tokens provided. The matching criteria for each token
will be explained below.

If any of the optional tokens are present, `<description keywords>` may be left blank.

`<description keywords>` is any amount of keywords, each separated by a space. An order whose description contains any of these
keywords will be considered a match. For example: `findorder iphone` will display all orders involving iPhones.

`<address keywords>` is any amount of keywords, each separated by a space. Functions the same as it does in `find` except
for orders.

`<client ID>` is an integer that should be greater than 0. The ID has to be an exact match for the order to be displayed.
For example: `findorder --client 1` will show all orders that are related to the client with an ID of 1.
 
`<date>` is a string of numbers separated by dashes in the format of `YYYY-MM-DD`. The date entered has to be valid and an
exact match for the order to be displayed. For example: `findorder --date 2020-11-27` will display all orders to be
delivered on the 27th of November, 2020.

--------------------------------------------------------------------------------------------------------------------
=======
------------------------------------------------------------------------------------------------------------------------
>>>>>>> b4464db290c7e275ae80c740f039ae9bfa7452d2

## Command summary

Action | Format
--------|------------------
**Order** | `order --description <order description> --client <client ID>` 
**Client** | `client --name <client name> --address <client address>`
**Delete** | `delete-order --order <order index>` <br> `delete-client --client <client index>`
**List** | `listC` <br> `listO` <br> `list -a`
**Done** | `done --order <order id>`
**Find** | `find <name keywords> (--address <address keywords>) (--email <email address>) (--phone <phone number>)`
**FindOrder**| `findorder <description keywords> (--address <address keywords>) (--date <date>) (--client <client ID>)`
