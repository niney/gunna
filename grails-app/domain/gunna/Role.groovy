package gunna

class Role {

	String authority
	Boolean deleteRestrict = 0

	static constraints = {
		authority maxSize: 255, blank: false, unique: true
	}

	static mapping = {
		cache true
		table comment: '이용자의 시스템상의 역할'
		authority comment: '권한'
		deleteRestrict comment: '삭제불가플래그'
	}

}
