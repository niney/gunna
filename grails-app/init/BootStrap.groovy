import gunna.*

class BootStrap {

    def init = { servletContext ->

        new Code(pCodeKey: 0, codeKey: 1000, codeValue: "국적", description: "국적").save()
            new Code(pCodeKey: 1000, codeKey: 1010, codeValue: "한국", description: "대한민국").save()
            new Code(pCodeKey: 1000, codeKey: 1020, codeValue: "필리핀", description: "필리핀").save()
            new Code(pCodeKey: 1000, codeKey: 1030, codeValue: "네팔", description: "네팔").save()
            new Code(pCodeKey: 1000, codeKey: 1040, codeValue: "중국", description: "중국").save()
        new Code(pCodeKey: 0, codeKey: 2000, codeValue: "진료과", description: "진료과").save()
            new Code(pCodeKey: 2000, codeKey: 2100, codeValue: "치과", description: "치과").save()
                new Code(pCodeKey: 2100, codeKey: 2110, codeValue: "충", description: "충치").save()
                new Code(pCodeKey: 2100, codeKey: 2120, codeValue: "잇", description: "잇몸병").save()
                new Code(pCodeKey: 2100, codeKey: 2130, codeValue: "풍", description: "풍치").save()
            new Code(pCodeKey: 2000, codeKey: 2200, codeValue: "문진", description: "문진").save()
            new Code(pCodeKey: 2000, codeKey: 2300, codeValue: "의과", description: "의과").save()
            new Code(pCodeKey: 2000, codeKey: 2400, codeValue: "한의과", description: "한의과").save()
        new Code(pCodeKey: 0, codeKey: 3000, codeValue: "직업", description: "직업").save()
            new Code(pCodeKey: 3000, codeKey: 3010, codeValue: "조립", description: "조립").save()
            new Code(pCodeKey: 3000, codeKey: 3020, codeValue: "공장", description: "공장").save()
            new Code(pCodeKey: 3000, codeKey: 3030, codeValue: "반출", description: "반출").save()

        /*(1..20).each {
            new Member(name: "홍길동_" + it, engName: 'hong', gender: Gender.MALE, nationality: Code.findByCodeKey(1010),
                    job: Code.findByCodeKey(3020), birthDay: new Date(), entryDate: new Date(),
                    address: "서울시 양천구", addressDetail: "목동중앙남로 47길").save()
        }*/
        new Member(name: "홍길동", engName: 'hong', gender: Gender.MALE, nationality: Code.findByCodeKey(1010),
                job: Code.findByCodeKey(3020), birthDay: new Date(), entryDate: new Date(),
                address: "서울시 양천구", addressDetail: "목동중앙남로 47길").save()
        new MedRecord(Member: Member.get(1), subject: Code.findByCodeKey(2120)).save()
    }
    def destroy = {
    }
}
