package webraise.test.subscriptions.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {

    private Long id;

    private String username;
}
