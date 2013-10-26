#Grails Selection Macro Plugin

This plugin is a companion plugin to the
[selection](https://github.com/goeh/grails-selection) plugin.
The selection plugin uses a URI based syntax to select any information
from any resource.

Examples:

* gorm://person/list?name=Gr%25
* ldap:dc=my-company&dc=com&cn=users
* bean://myService/method
* https://dialer.mycompany.com/outbound/next?agent=liza&campaign=ikea

This plugin **selection-macro** can stack multiple selections
on top of each other and perform union, diff and intersections on the result.
For example:

1. Add all customers in east territory
2. Add all customers in north territory
3. Keep only those with revenue above $1000000
4. Remove those that we have an active discussion with
5. Randomly pick 1000 customers from the result

The final result can be used anywhere in your Grails application.
(print reports, export to file, generate emails, create call lists, etc.)
