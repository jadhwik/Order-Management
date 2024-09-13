package co.instio.service;

import co.instio.dto.UserView;
import co.instio.entity.Users;
import co.instio.enums.CommonErrorCodeEnum;
import co.instio.exceptions.ServiceException;
import co.instio.mapper.CommonMapper;
import co.instio.repo.UsersRepo;
import co.instio.service.emailAlert.EmailAlert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UsersService {

    private final UsersRepo usersRepo;
    private final CommonMapper commonMapper;
    private final EmailAlert emailAlert;


    @Override
    public List<UserView> createUsers(List<Users> users){
        if(users == null){
            log.error("Empty data !");
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
        List <Users> savedUsers = usersRepo.saveAll(users);
        return savedUsers.stream()
                .map(commonMapper::toUserView)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserView> getAllUsers(){
        List<Users> fetchedUsers = usersRepo.findAll();
        if(fetchedUsers.isEmpty()){
            log.error("No data found!");
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
        return fetchedUsers.stream()
                .map(commonMapper::toUserView)
                .collect(Collectors.toList());
    }

    @Override
    public UserView getById(Long userId){
        Users fetchedUser = usersRepo.findById(userId).orElse(null);

        if(fetchedUser == null){
            log.error("No data found for id:{}",userId);
            throw new ServiceException(CommonErrorCodeEnum.NOT_FOUND);
        }
        return commonMapper.toUserView(fetchedUser);
    }

    @Override
    public UserView updateUsers(Long userId , Users user){
        String oldEmail = user.getEmail();
        Users fetchedUser = usersRepo.findById(userId).orElse(null);
        if(fetchedUser == null){
            log.error("No data found for updation for id:{}",userId);
            throw new ServiceException(CommonErrorCodeEnum.NOT_FOUND);
        }
        fetchedUser.setLocation(user.getLocation());
        fetchedUser.setEmail(user.getEmail());
        Users savedUser = usersRepo.save(fetchedUser);
        emailAlert.sendEmailAlert(oldEmail,savedUser);
        return commonMapper.toUserView(savedUser);
    }

    @Override
    public void deleteUser(Long userId){
        Users fetchedUser = usersRepo.findById(userId).orElse(null);
        if(fetchedUser == null){
            log.error("No data found for deletion with id:{}",userId);
            throw new ServiceException(CommonErrorCodeEnum.NOT_FOUND);
        }
        usersRepo.deleteById(fetchedUser.getUserId());
    }
    }
