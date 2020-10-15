package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "addressbook")
@XStreamAlias("ContactData")
public class ContactData {

    @XStreamOmitField
    @Id
    private int id  = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private  String firstName;

    @Expose
    @Column(name = "lastname")
    private  String lastName;

    @Column(name = "email")
    @Type(type="text")
    private  String email;

    @Transient
    private  String email1;
    @Transient
    private  String email2;
    @Transient
    private  String allEmails;

    @Column(name = "home")
    @Type(type = "text")
    private  String homePhone;

    @Column(name = "mobile")
    @Type(type = "text")
    private  String mobilePhone;

    @Column(name = "work")
    @Type(type = "text")
    private  String workPhone;

    @Transient
    private  String allPhones;


    @Column(name = "address")
    @Type(type = "text")
    private  String address;

    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id") )
    private Set<GroupData> groups = new HashSet<GroupData>();


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
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

    public Groups getGroups() {
        return new Groups(groups);
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
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

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
