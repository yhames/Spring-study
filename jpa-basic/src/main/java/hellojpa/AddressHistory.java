package hellojpa;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class AddressHistory {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    public AddressHistory(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }

    public AddressHistory(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
