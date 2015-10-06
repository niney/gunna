package gunna

import common.controller.JSONRestfulController

class CodeController extends JSONRestfulController<Code> {

    CodeController() {
        super(Code)
    }

    def index(Integer max) {

        def result = Code.getCodeListMap(params.pCodeKey, params.codeKey)

        respond result, model: [("${resourceName}Count".toString()): result.size()]
    }
}
