package sina.shariati.club;

import java.util.UUID;

/**
 * The type Player.
 */
public class Player {
    private String firstName;
    private String lastName;
    private UUID nationalId;
    private int age;

    /**
     * Instantiates a new Player.
     *
     * @param firstName  the first name
     * @param lastName   the last name
     * @param nationalId the national id
     * @param age        the age
     */
    public Player(String firstName, String lastName, UUID nationalId, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.age = age;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets national id.
     *
     * @return the national id
     */
    public String getNationalId() {
        return nationalId.toString();
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }
}
