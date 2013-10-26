package grails.plugins.selection

/**
 * Selection item
 */
class SelectionMacroItem {
    public static final int DIFF = -1 // REMOVE
    public static final int ADD = 0 // ADD
    public static final int INTERSECT = 1 // KEEP

    int orderIndex
    int operation
    String uriString
    static belongsTo = [macro: SelectionMacro]

    static constraints = {
        operation(inList: [DIFF, ADD, INTERSECT])
        uriString(maxSize: 2000, blank: false) // TODO should we allow longer URIs?
    }

    static transients = ["uri"]

    URI getUri() {
        uriString ? new URI(uriString) : null
    }

    void setUri(URI uri) {
        uriString = uri ? uri.toASCIIString() : null
    }

    void setUri(String uri) {
        uriString = uri ? new URI(uri).toASCIIString() : null
    }

    Set process(Set result, Collection tmp) {
        switch (operation) {
            case ADD:
                result.addAll(tmp)
                break
            case DIFF:
                result.removeAll(tmp)
                break
            case INTERSECT:
                result.retainAll(tmp)
                break
        }
        result
    }

    String toString() {
        switch (operation) {
            case DIFF:
                return "- $uriString"
            case ADD:
                return "+ $uriString"
            case INTERSECT:
                return "= $uriString"
            default:
                throw new RuntimeException("Invalid operation [$operation]")
        }
    }
}
