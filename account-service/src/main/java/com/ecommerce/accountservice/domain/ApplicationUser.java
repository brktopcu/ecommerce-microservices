package com.ecommerce.accountservice.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.awt.print.Book;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID applicationUserId;

    @NotBlank(message = "Username should not be blank")
    @Email(message = "Username should be an e-mail")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Name field should not be blank")
    private String fullName;

    private String userAddress;

    @NotBlank(message = "Password field should not be blank")
    private String password;

    @Transient
    private String confirmPassword;

    private Long userPhoneNumber;

    /*private Date createdAt;
    private Date updatedAt;
    @PrePersist
    protected void onCreate() {this.createdAt = new Date();}
    @PreUpdate
    protected void onUpdate() {this.updatedAt = new Date();}*/

    //@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    //List<Comment> userCommentList = new ArrayList<>();

    //@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    //Set<Book> favoriteBookSet = new HashSet<>();

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "shopping_cart_id")
    //ShoppingCart shoppingCart = new ShoppingCart();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
