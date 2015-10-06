package gunna

public enum Gender {
    MALE('m'),
    FEMALE('f')

    String id

    private Gender(String id) {
        this.id = id
    }

    String toString() {id}
}