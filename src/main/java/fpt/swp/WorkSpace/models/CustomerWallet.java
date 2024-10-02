//package fpt.swp.WorkSpace.models;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.ColumnDefault;
//
//@Entity
//@Table(name = "customer_wallet")
//@Data
//public class CustomerWallet {
//    @Id
//    private String walletId;
//
//    @ColumnDefault("0")
//    private int walletBalance;
//
//    @OneToOne(mappedBy = "wallet")
//    @JsonBackReference
//    private Customer customer;
//
//
//}
