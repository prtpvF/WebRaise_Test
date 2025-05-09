package webraise.test.subscriptions.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webraise.test.subscriptions.dto.SubscriptionDto;
import webraise.test.subscriptions.entity.SubscriptionEntity;
import webraise.test.subscriptions.exception.DuplicateSubscriptionException;
import webraise.test.subscriptions.exception.SubscriptionNotFoundException;
import webraise.test.subscriptions.mapper.SubscriptionMapper;
import webraise.test.subscriptions.repository.SubscriptionRepository;
import webraise.test.subscriptions.service.SubscriptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public SubscriptionDto addSubscriptionToUser(Long subscriptionId, Long userId) {
        validateSubscriptionNotExists(subscriptionId, userId);
        SubscriptionEntity subscription = getSubscriptionEntityById(subscriptionId);
        subscription.addUserToSubscription(userId);
        subscription.setId(subscriptionId);
        SubscriptionEntity savedSubscription = subscriptionRepository.save(subscription);
        log.info(String.format("user with id: %s added a new subscription: %s",
                userId, savedSubscription.getName()));
        return subscriptionMapper.subscriptionEntity2dto(savedSubscription);
    }

    @Override
    public List<SubscriptionDto> getUsersSubscriptions(Long userId) {
        return subscriptionRepository.findAllByUserId(userId)
                .stream().map(subscriptionMapper::subscriptionEntity2dto).toList();
    }

    @Override
    public List<SubscriptionDto> getPopularSubscriptions() {
        return subscriptionRepository.getPopularSubscriptions()
                .stream().map(subscriptionMapper::subscriptionEntity2dto).toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteSubscription(Long subscriptionId, Long userId) {
        subscriptionRepository.deleteByUserIdAndId(userId, subscriptionId);
    }

    private SubscriptionEntity getSubscriptionEntityById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(
                        () -> new SubscriptionNotFoundException(
                                String.format("cannot find subscription with id: %s", subscriptionId)
                        )
                );
    }

    private void validateSubscriptionNotExists(Long subscriptionId, Long userId) {
        subscriptionRepository.findByUserIdAndId(userId, subscriptionId)
                .ifPresent(
                        subscription -> {
                            throw new DuplicateSubscriptionException("You already have this subscription");
                        }
                );

    }
}
