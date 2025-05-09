package webraise.test.subscriptions.dto;

import lombok.Builder;
import lombok.Getter;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class SubscriptionDto {

    private Long id;

    private String name;

    private BigDecimal cost;

    private List<UserDto> users = new ArrayList<>();

    private LocalDateTime createdAt;
}