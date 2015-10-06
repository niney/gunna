package gunna
import org.grails.databinding.BindingFormat

class Member {

    String name
    String engName
    Gender gender
    Code nationality
    @BindingFormat('yyyy/MM/dd')
    Date birthDay
    @BindingFormat('yyyy/MM/dd')
    Date entryDate
    Code job
    String address
    String addressDetail

    static constraints = {
    }
}
