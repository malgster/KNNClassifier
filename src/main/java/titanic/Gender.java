package titanic;

public enum Gender {
    FEMALE(0), MALE(1);

    int norm;

    private Gender(int norm){
        this.norm = norm;
    }
}
