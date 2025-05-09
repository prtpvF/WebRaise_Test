package webraise.test.user.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webraise.test.user.dto.UserDto;
import webraise.test.user.entity.UserEntity;
import webraise.test.user.exception.UserNotFoundException;
import webraise.test.user.exception.UsernameIsTakenException;
import webraise.test.user.mapper.UserMapper;
import webraise.test.user.repository.UserRepository;
import webraise.test.user.service.UserService;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        validateUsernameNotTaken(userDto.getUsername());
        UserEntity savedUser = userRepository.save(userMapper.userDto2UserEntity(userDto));
        log.info(String.format("User created: %s", userDto.getUsername()));
        return userMapper.userEntity2Dto(savedUser);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserDto updateUser(Long updateUserId, UserDto userDto) {
        UserEntity foundedUser = getUserEntityById(updateUserId);

        if (!foundedUser.getUsername().equals(userDto.getUsername())) {
            validateUsernameNotTaken(userDto.getUsername());
        }
        UserEntity user = userMapper.userDto2UserEntity(userDto);
        user.setId(updateUserId);
        UserEntity updatedUser = userRepository.save(user);
        log.info(String.format("User with id: %s was updated", updateUserId));
        return userMapper.userEntity2Dto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userMapper.userEntity2Dto(getUserEntityById(userId));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(
                getUserEntityById(userId)
        );
        log.info(String.format("User with id was deleted: %s", userId));
    }

    private UserEntity getUserEntityById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("User with id %s not found", userId))
        );
    }

    private void validateUsernameNotTaken(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new UsernameIsTakenException("Username '" + username + "' is already taken");
        });
    }
}