package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String email;
    private static String group;

    public ContactData(String firstName, String middleName, String email, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public static String getGroup() {
        return group;
    }
}
