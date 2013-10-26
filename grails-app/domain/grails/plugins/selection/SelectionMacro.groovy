package grails.plugins.selection

/**
 * Selection Macro.
 */
class SelectionMacro {

    Long tenantId

    static hasMany = [items: SelectionMacroItem]

    static constraints = {
        tenantId(nullable: true)
    }

    static mapping = {
        items sort: 'orderIndex'
    }

    private SelectionMacroItem doAddUri(final URI uri, final int op) {
        def size = items ? items.size() : 0
        def item = new SelectionMacroItem(macro: this, orderIndex: size + 1, operation: op, uri: uri)
        if (item.validate()) {
            addToItems(item)
            save()
        }
        return item
    }

    private SelectionMacroItem doAddMacro(final SelectionMacro macro, final int op) {
        if(! macro.ident()) {
            throw new IllegalArgumentException("Selection Macro must be saved before it can be used in macro composition")
        }
        doAddUri(new URI(macro.toString()), op)
    }

    SelectionMacroItem add(URI uri) {
        doAddUri(uri, SelectionMacroItem.ADD)
    }

    SelectionMacroItem diff(URI uri) {
        doAddUri(uri, SelectionMacroItem.DIFF)
    }

    SelectionMacroItem intersect(URI uri) {
        doAddUri(uri, SelectionMacroItem.INTERSECT)
    }


    SelectionMacroItem add(SelectionMacro macro) {
        doAddMacro(macro, SelectionMacroItem.ADD)
    }

    SelectionMacroItem diff(SelectionMacro macro) {
        doAddMacro(macro, SelectionMacroItem.DIFF)
    }

    SelectionMacroItem intersect(SelectionMacro macro) {
        doAddMacro(macro, SelectionMacroItem.INTERSECT)
    }

    String toString() {
        "macro:$id"
    }
}
