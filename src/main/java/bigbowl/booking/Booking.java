package bigbowl.booking;

import bigbowl.bookingactivity.BookingActivity;
import bigbowl.product.Product;
import bigbowl.security.entity.UserWithRoles;
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

    // Liste over bookingaktiviteter tilknyttet bookingen
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingActivity> activities;

    @ManyToMany
    private List<Product> products;

    // Bruger tilknyttet bookingen
    @ManyToOne
    private UserWithRoles user;

}
