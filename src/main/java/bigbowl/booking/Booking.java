package bigbowl.booking;

import bigbowl.bookingactivity.BookingActivity;
import bigbowl.product.Product;
import bigbowl.security.entity.UserWithRoles;
import bigbowl.security_demo.entity.SpecialUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bruger tilknyttet bookingen
    @ManyToOne
    private SpecialUser user;

    // Liste over bookingaktiviteter tilknyttet bookingen
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingActivity> activities;

    @ManyToMany
    private List<Product> products;

    private String phoneNumber;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", activities=" + activities +
                ", products=" + products +
                '}';
    }

}
