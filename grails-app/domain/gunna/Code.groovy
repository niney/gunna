package gunna

import org.hibernate.criterion.CriteriaSpecification

class Code {

    String pCodeKey
    String codeKey
    String codeValue
    String description

    static constraints = {
    }

    private static searchCodeListMap = [:]

    private static getInitCodeListMap(String pCodeKey, String codeKey) {
        def props = ['pCodeKey', 'codeKey', 'codeValue', 'description']
        searchCodeListMap[pCodeKey][codeKey] = Code.createCriteria().list() {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections {
                props.each {
                    property(it,it)
                }
            }
            and {
                eq('pCodeKey', pCodeKey)
                eq('codeKey', codeKey)
            }
        }
    }

    private static getInitCodeListMap(String pCodeKey) {
        def props = ['id', 'pCodeKey', 'codeKey', 'codeValue', 'description']
        def pCodeList = Code.createCriteria().list() {
            resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
            projections {
                props.each {
                    property(it,it)
                }
            }
            and {
                eq('pCodeKey', pCodeKey)
            }
        }
        pCodeList.each {
            searchCodeListMap[pCodeKey][it.codeKey] = it
        }
        return pCodeList
    }

    public static synchronized getCodeListMap(String pCodeKey, String codeKey) {
        // 1. 이미 있느지 검사
        if(searchCodeListMap[pCodeKey] != null && searchCodeListMap[pCodeKey][codeKey] != null)
            return searchCodeListMap[pCodeKey][codeKey]

        // 2. 첫 테이블이면 초기화
        if(searchCodeListMap[pCodeKey] == null)
            searchCodeListMap[pCodeKey] = [:]

        // 3. 정보 가져오기
        if(codeKey == null )
            return getInitCodeListMap(pCodeKey)
        else
            getInitCodeListMap(pCodeKey, codeKey)

        return searchCodeListMap[pCodeKey][codeKey]

    }

    public static reload(String tableName, String fieldType) {
        getInitCodeListMap(tableName, fieldType)
        return searchCodeListMap[tableName][fieldType]
    }

    public static void reload() {
        searchCodeListMap = null
    }
}
