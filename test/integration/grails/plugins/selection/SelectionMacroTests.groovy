package grails.plugins.selection

import test.TestEntity

/**
 * Selection Macro Tests.
 */
class SelectionMacroTests extends GroovyTestCase {

    def selectionService
    def selectionMacroService

    void testMacro() {

        new TestEntity(name: "Hipster Software AB", postalCode: "12345", city: "Stockholm").save(failOnError: true)
        new TestEntity(name: "Telecom Architects AB", postalCode: "25431", city: "Malmoe").save(failOnError: true)
        new TestEntity(name: "Marine Coders AB", postalCode: "32145", city: "Gothenburg").save(failOnError: true)
        new TestEntity(name: "Sunpower Development AB", postalCode: "65010", city: "Karlstad").save(failOnError: true)
        new TestEntity(name: "Educational Software AB", postalCode: "72145", city: "Uppsala").save(failOnError: true)
        new TestEntity(name: "Gurus up North AB", postalCode: "83452", city: "Sundsvall").save(failOnError: true)

        def macro = selectionMacroService.createMacro()
        macro.add(new URI('gorm://testEntity/list'))

        assert selectionService.select(macro, [:]).size() == 6

        def bigCities = selectionMacroService.createMacro()
        bigCities.add(new URI('gorm://testEntity/list?postalCode=1'))
        bigCities.add(new URI('gorm://testEntity/list?postalCode=2'))
        bigCities.add(new URI('gorm://testEntity/list?postalCode=3'))

        macro.diff(bigCities)

        def result = selectionService.select(macro, [:])
        assert result.size() == 3
        assert !result.find { it.postalCode.startsWith('1') }
        assert !result.find { it.postalCode.startsWith('2') }
        assert !result.find { it.postalCode.startsWith('3') }
        assert result.find { it.postalCode.startsWith('6') }
        assert result.find { it.postalCode.startsWith('7') }
        assert result.find { it.postalCode.startsWith('8') }

        macro = selectionMacroService.getMacro(macro.ident())
        assert macro
    }
}
