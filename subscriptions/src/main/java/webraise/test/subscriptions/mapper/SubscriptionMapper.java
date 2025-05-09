package webraise.test.subscriptions.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import webraise.test.subscriptions.dto.UserDto;
import webraise.test.subscriptions.entity.SubscriptionEntity;
import webraise.test.subscriptions.dto.SubscriptionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

        @Mapping(source = "id", target = "id")
        SubscriptionDto subscriptionEntity2dto(SubscriptionEntity subscription);

        @Mapping(source = "users", target = "usersId", qualifiedByName = "mapUsersToUserIds",
                nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        SubscriptionEntity dto2SubscriptionEntity(SubscriptionDto subscriptionDto);

        @Named("mapUsersToUserIds")
        default List<Long> mapUsersToUserIds(List<UserDto> users) {
                if (users == null) {
                        return new ArrayList<>();
                }
                return users.stream()
                        .map(UserDto::getId)
                        .collect(Collectors.toList());
        }
}