package webraise.test.subscriptions.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webraise.test.subscriptions.dto.SubscriptionDto;
import webraise.test.subscriptions.service.impl.SubscriptionServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionServiceImpl subscriptionService;

    @PostMapping("/{subscriptionId}/users/{userId}")
    public SubscriptionDto addSubscriptionToUser(@PathVariable("subscriptionId") Long subscriptionId,
                                                 @PathVariable("userId") Long userId) {
        return subscriptionService.addSubscriptionToUser(subscriptionId, userId);
    }

    @GetMapping("/allByUser/{userId}")
    public List<SubscriptionDto> getUserSubscriptions(@PathVariable("userId") Long userId) {
        return subscriptionService.getUsersSubscriptions(userId);
    }

    @GetMapping("/top")
    public List<SubscriptionDto> getPopularSubscriptions() {
        return subscriptionService.getPopularSubscriptions();
    }

    @DeleteMapping("/{subscriptionId}/users/{userId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId,
                                                @PathVariable("userId") Long userId) {
        subscriptionService.deleteSubscription(subscriptionId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
