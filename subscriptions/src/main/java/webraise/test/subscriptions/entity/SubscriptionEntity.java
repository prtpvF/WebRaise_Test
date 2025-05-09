package webraise.test.subscriptions.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50, message = "Subscription's name must be between 1 and 50 characters")
    private String name;

    @Min(value = 0, message = "cost must be equal or bigger than 0")
    private BigDecimal cost;

    @NotNull(message = "user id is required")
    @ElementCollection
    @CollectionTable(name = "subscriptions_users",
            joinColumns = @JoinColumn(name = "subscription_id"))
    @Column(name = "user_id")
    private List<Long> usersId = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void addUserToSubscription(@NotNull Long userId) {
        usersId.add(userId);
    }
}