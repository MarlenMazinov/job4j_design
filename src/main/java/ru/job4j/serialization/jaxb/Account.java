package ru.job4j.serialization.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private boolean isActive;
    @XmlElement(name = "contact")
    private Contact contact;
    @XmlElementWrapper(name = "usageServices")
    @XmlElement(name = "usageService")
    private String[] usageServices;

    public Account() {
    }

    public Account(int id, String name, boolean isActive, Contact contact, String[] usageServices) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.contact = contact;
        this.usageServices = usageServices;
    }

    public static void main(String[] args) throws JAXBException {

        final Account account = new Account(152, "User#1", false, new Contact("user1@mail.ru"),
                new String[]{"Netflix", "Youtube"});

        JAXBContext context = JAXBContext.newInstance(Account.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(account, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", name='" + name + '\'' + ", isActive=" + isActive
                + ", contact=" + contact + ", usageServices="
                + Arrays.toString(usageServices) + '}';
    }
}
