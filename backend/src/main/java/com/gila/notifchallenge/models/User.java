package com.gila.notifchallenge.models;

import com.gila.notifchallenge.enums.Category;
import com.gila.notifchallenge.enums.Channel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    @ElementCollection(targetClass = Category.class)
    @CollectionTable(name = "user_subscriptions", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private List<Category> subscribedCategories;

    @ElementCollection(targetClass = Channel.class)
    @CollectionTable(name = "user_channels", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "channel")
    private List<Channel> notificationChannels;

}
