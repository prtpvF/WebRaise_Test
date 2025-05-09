package webraise.test.subscriptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import webraise.test.subscriptions.entity.SubscriptionEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

        @Query("SELECT s FROM SubscriptionEntity s " +
                "JOIN s.usersId su " +
                "WHERE su = :userId")
        List<SubscriptionEntity> findAllByUserId(Long userId);


        @Query("SELECT se FROM SubscriptionEntity se " +
                "WHERE :userId MEMBER OF se.usersId AND se.id = :id")
        Optional<SubscriptionEntity> findByUserIdAndId(Long userId, Long id);

        @Modifying
        @Query("DELETE FROM SubscriptionEntity s " +
                "WHERE :userId MEMBER OF s.usersId AND s.id = :id")
        void deleteByUserIdAndId(Long userId, Long id);

        @Query("SELECT se FROM SubscriptionEntity se ORDER BY size(se.usersId) DESC LIMIT 3")
        List<SubscriptionEntity> getPopularSubscriptions();
}
