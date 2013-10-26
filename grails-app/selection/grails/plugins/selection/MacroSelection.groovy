package grails.plugins.selection

class MacroSelection {

    def selectionService

    /**
     * This selection handler is just a container for other selections and uses the 'macro' scheme.
     * @param uri the URI to check support for
     * @return true if uri.scheme is 'macro'
     */
    boolean supports(URI uri) {
        return uri?.scheme == 'macro'
    }

    /**
     * Invoke select on all selection contained in this container class.
     * @param uri the encoded bean/method
     * @param params (optional) parameters to the method invocation
     * @return union of all results
     */
    def select(URI uri, Map params) {
        // Convert the specified primary key to Long
        def id = Long.valueOf(uri.schemeSpecificPart)

        // Find selection in repository
        def macro = SelectionMacro.get(id)
        if (!macro) {
            throw new IllegalArgumentException("Invalid macro URI $uri")
        }

        if (log.isDebugEnabled()) {
            log.debug macro.toString()
        }
        def result = [] as Set
        def items = macro.items
        if(items) {
            for (item in items.sort{it.orderIndex}) {
                if (log.isDebugEnabled()) {
                    log.debug item.toString()
                }
                result = item.process(result, selectionService.select(item.uri, params))
            }
        }
        return result


    }
}
