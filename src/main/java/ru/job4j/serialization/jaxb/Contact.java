package ru.job4j.serialization.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class Contact {
    @XmlAttribute
    private String email;

    public Contact() {
    }

    public Contact(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" + "email='" + email + '\'' + '}';
    }
}
