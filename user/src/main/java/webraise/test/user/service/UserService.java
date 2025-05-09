package webraise.test.user.service;

import webraise.test.user.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long updatedUserid, UserDto userDto);

    UserDto getUserById(Long id);

    void deleteUser(Long id);
}
