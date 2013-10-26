class SelectionMacroGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.0 > *"
    def pluginExcludes = [
            "grails-app/views/error.gsp",
            "grails-app/domain/test/**"
    ]
    def loadAfter = ['selection']
    def title = "Selection Macro Plugin" // Headline display name of the plugin
    def author = "Goran Ehrsson"
    def authorEmail = "goran@©technipelago.se"
    def description = '''\
This plugin is a companion plugin to the 'selection' plugin. The selection plugin
uses a URI based syntax to select any information from any resource.

Example 1: gorm://person/list?name=Gr%25
Example 2: ldap:dc=my-company&dc=com&cn=users
Example 3: bean://myService/method
Example 4: https://dialer.mycompany.com/outbound/next?agent=liza&campaign=ikea

This plugin (selection-macro) can stack multiple selections on top of each other
and perform union, diff and intersections on the result. For example:

1. Add all customers in east territory
2. Add all customers in north territory
3. Keep only those with revenue above $1000000
4. Remove those that we have an active discussion with
5. Randomly pick 1000 customers from the result

The final result can be used anywhere in your Grails application.
(print reports, export to file, generate emails, create call lists, etc.)
'''
    def documentation = "https://github.com/goeh/grails-selection-macro"
    def license = "APACHE"
    def organization = [name: "Technipelago AB", url: "http://www.technipelago.se/"]
    def issueManagement = [system: "github", url: "https://github.com/goeh/grails-selection-macro/issues"]
    def scm = [url: "https://github.com/goeh/grails-selection-macro"]
}
