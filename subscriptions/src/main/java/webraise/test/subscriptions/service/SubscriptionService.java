package webraise.test.subscriptions.service;


import webraise.test.subscriptions.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDto addSubscriptionToUser(Long userId, Long subscriptionId);

    List<SubscriptionDto> getUsersSubscriptions(Long userId);

    List<SubscriptionDto> getPopularSubscriptions();

    void deleteSubscription(Long userId, Long subscriptionId);
}
