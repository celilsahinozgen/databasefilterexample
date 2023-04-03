package com.filterdatabase.databasefilterexample.service;


import com.filterdatabase.databasefilterexample.dto.UserCreateDTO;
import com.filterdatabase.databasefilterexample.dto.UserResponseDTO;
import com.filterdatabase.databasefilterexample.dto.UserUpdateDTO;
import com.filterdatabase.databasefilterexample.model.User;
import com.filterdatabase.databasefilterexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserCreateDTO createUser(UserCreateDTO userCreateDTO) {
        User newUser = modelMapper.map(userCreateDTO, User.class);
        return modelMapper.map(userRepository.save(newUser), UserCreateDTO.class);

    }

    public UserUpdateDTO updateUSer(String tckNO, UserUpdateDTO userUpdateDTO) {
        Optional<User> optionalUser = userRepository.findByTckNo(tckNO);
        if (optionalUser.isPresent()){
            optionalUser.get().setOkul(userUpdateDTO.getOkul());
            optionalUser.get().setTelefon(userUpdateDTO.getTelefon());
            optionalUser.get().setSerhir(userUpdateDTO.getSerhir());
            optionalUser.get().setUpdateDate(LocalDateTime.now());
            return modelMapper.map(userRepository.save(optionalUser.get()),UserUpdateDTO.class);
        }
        throw new RuntimeException("güncellenemedi");
    }


    public List<UserResponseDTO> findAllUser() {
        List<User> listuser = userRepository.findAll();
        List<UserResponseDTO> listDto = listuser.stream().map(user -> modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
        return listDto;
    }

    public UserResponseDTO findByTckNo(String tckNo) {
        Optional<User> optionalUser = userRepository.findByTckNo(tckNo);
        if (optionalUser.isPresent()){
            return modelMapper.map(optionalUser.get(),UserResponseDTO.class);
        }
        throw new RuntimeException("kullanıcıbulunamadı");
    }

    public UserResponseDTO findByName(String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent()){
            return modelMapper.map(optionalUser.get(), UserResponseDTO.class);
        }
        throw new RuntimeException("kullanıcıbulunamadı");
    }

    //stream filter ile yeni bir data olusdurulup sorulmusdur
    public List<User> getQueryByFilter(String name, String lastName, String tckNo, String memleket, String anneadi, String telefon) {


        Stream<User> streamUser = userRepository.findAll().stream();


        return userRepository.findAll().stream()
                .filter(user -> name == null || user.getName().contains(name))
                .filter(user -> lastName == null || user.getLastName().contains(lastName))
                .filter(user -> tckNo == null || user.getTckNo().contains(tckNo))
                .filter(user -> memleket == null || user.getMemleket().contains(memleket))
                .filter(user -> anneadi == null || user.getAnneadi().contains(anneadi))
                .filter(user -> telefon == null || user.getTelefon().contains(telefon))
                .collect(Collectors.toList());

    }
    public List<UserResponseDTO> getfilterUsers(String name, String lastName, String tckNo, String serhir, String okul, String telefon) {


        //Stream<User> streamUser = userRepository.findAll().stream();
        List<User> listuser2 = userRepository.findAll();
        List<UserResponseDTO> newlist = listuser2.stream().map(user -> modelMapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());

        return newlist.stream()
                .filter(user -> name == null || user.getName().contains(name))
                .filter(user -> lastName == null || user.getLastName().contains(lastName))
                .filter(user -> tckNo == null || user.getTckNo().contains(tckNo))
                .filter(user -> serhir == null || user.getSerhir().contains(serhir))
                .filter(user -> okul == null || user.getOkul().contains(okul))
                .filter(user -> telefon == null || user.getTelefon().contains(telefon))
                .collect(Collectors.toList());

    }
    public List<User> getUserswithmap(String name, String lastName, String tckNo, String memleket, String anneadi, String telefon) {


        Stream<User> streamUser = userRepository.findAll().stream();


        if (name != null) {
            streamUser = streamUser.parallel().filter(user -> user.getName().contains(name));
        }

        if (lastName != null) {
            streamUser = streamUser.parallel().filter(user -> user.getLastName().contains(lastName));
        }

        if (tckNo != null) {
            streamUser = streamUser.parallel().filter(user -> user.getTckNo().contains(tckNo));
        }
        if (memleket != null) {
            streamUser = streamUser.parallel().filter(user -> user.getMemleket().contains(memleket));
        }
        if (anneadi != null) {
            streamUser = streamUser.parallel().filter(user -> user.getAnneadi().contains(anneadi));
        }
        if (telefon != null) {
            streamUser = streamUser.parallel().filter(user -> user.getTelefon().contains(telefon));
        }


        return streamUser.collect(Collectors.toList());

    }


//    public List<UserResponseDTO> getFilteredPosts(String authorName, String lastName, String tckNo, String okul, String telefon, String serhir) {
//        Stream<User> postStream = userRepository.findByAuthor(authorName);
//        List<UserResponseDTO> userDto = postStream
//                .filter(user -> user.get.equals(true))
//                .collect(Collectors.toList());
//        return userDto;
//    }

//    public List<UserResponseDTO> getFilteredPosts(String name, String lastName, String tckNo, String okul, String telefon, String serhir) {
//        List<User> users = userRepository.findAll();
//
//        // Filtrelemek için kullanılacak koşullar
//        Predicate<User> nameFilter = u -> name == null || u.getName().equalsIgnoreCase(name);
//        Predicate<User> lastNameFilter = u -> lastName == null || u.getLastName().equalsIgnoreCase(lastName);
//        Predicate<User> tckNoFilter = u -> tckNo == null || u.getTckNo().equalsIgnoreCase(tckNo);
//        Predicate<User> okulFilter = u -> okul == null || u.getOkul().equalsIgnoreCase(okul);
//        Predicate<User> telefonFilter = u -> telefon == null || u.getTelefon().equalsIgnoreCase(telefon);
//        Predicate<User> serhirFilter = u -> serhir == null || u.getSerhir().equalsIgnoreCase(serhir);
//
//        List<User> filteredUsers = users.stream()
//                .filter(nameFilter.and(lastNameFilter).and(tckNoFilter)
//                        .and(okulFilter).and(telefonFilter).and(serhirFilter))
//                .collect(Collectors.toList());
//
//        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
//        for (User user : filteredUsers) {
//            UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(), user.getName(), user.getLastName(), user.getTckNo(), user.getOkul(), user.getTelefon(), user.getSerhir());
//            userResponseDTOs.add(userResponseDTO);
//        }
//        return userResponseDTOs;
//    }

}
