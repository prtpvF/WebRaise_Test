package webraise.test.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import webraise.test.user.dto.UserDto;
import webraise.test.user.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

        @Mapping(source = "id", target = "id")
        UserDto userEntity2Dto(UserEntity userEntity);

        @Mapping(source = "id", target = "id")
        @Mapping(source = "username", target = "username",
                nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        UserEntity userDto2UserEntity(UserDto userDto);
}
