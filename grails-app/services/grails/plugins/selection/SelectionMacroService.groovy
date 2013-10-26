package grails.plugins.selection

/**
 * Selection Macro Services.
 */
class SelectionMacroService {

    def currentTenant

    SelectionMacro createMacro(Map properties = [:]) {
        def m = new SelectionMacro(properties)
        m.tenantId = currentTenant?.get()
        return m
    }

    SelectionMacro getMacro(Long id) {
        def tenant = currentTenant?.get()
        def m
        if (tenant != null) {
            m = SelectionMacro.findByIdAndTenantId(id, tenant)
        } else {
            m = SelectionMacro.get(id)
        }
        return m
    }
}
