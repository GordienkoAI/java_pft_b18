package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("ContactData")
public class ContactData {
    @XStreamOmitField
    private int id  = Integer.MAX_VALUE;
    @Expose
    private  String firstName;
    @Expose
    private  String lastName;
    private  String email;
    private  String email1;
    private  String email2;
    private  String allEmails;
    @Expose
    private  String group;
    private  String homePhone;
    private  String mobilePhone;
    private  String workPhone;
    private  String allPhones;
    private  String address;
    private File photo;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withEmails(String allEmails){
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }
    public String getEmail1() {
        return this.email1;
    }

    public String getEmail2() {
        return this.email2;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGroup() {
        return this.group;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public String getWorkPhone() {
        return this.workPhone;
    }

    public int getId() {
        return  this.id;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails(){
        return allEmails;
    }

    public String getAddress() {return address; }

}
