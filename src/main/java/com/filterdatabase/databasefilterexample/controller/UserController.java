package com.filterdatabase.databasefilterexample.controller;

import com.filterdatabase.databasefilterexample.dto.UserCreateDTO;
import com.filterdatabase.databasefilterexample.dto.UserResponseDTO;
import com.filterdatabase.databasefilterexample.dto.UserUpdateDTO;
import com.filterdatabase.databasefilterexample.model.User;
import com.filterdatabase.databasefilterexample.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.filterdatabase.databasefilterexample.model.User.YILPATTERIN;

@RequiredArgsConstructor
@RequestMapping("/api1/user")
@RestController
public class UserController {


    private final UserService userService;


    @PostMapping("/save")
    public ResponseEntity<UserCreateDTO> createUser(@RequestBody UserCreateDTO userCreateDTO){
        UserCreateDTO newUserCreate = userService.createUser(userCreateDTO);
        return ResponseEntity.ok(newUserCreate);

    }


    @PutMapping("/edit/{tckNO}")
    public ResponseEntity<UserUpdateDTO>  updateUser(@PathVariable String tckNO, @RequestBody UserUpdateDTO userUpdateDTO){
        UserUpdateDTO updateUser = userService.updateUSer(tckNO,userUpdateDTO);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDTO>> findAllUser(){
        List<UserResponseDTO> findAll = userService.findAllUser();
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String tckNo,
            @RequestParam(required = false) String serhir,
            @RequestParam(required = false) String okul,
            @RequestParam(required = false) String telefon){

        List<User> listQueryFilter = userService.getQueryByFilter(name, lastName, tckNo,serhir,okul,telefon);
        return ResponseEntity.ok(listQueryFilter);
    }

    @GetMapping("/newfilter")
    public ResponseEntity<List<UserResponseDTO>> getfilterUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String tckNo,
            @RequestParam(required = false) String serhir,
            @RequestParam(required = false) String okul,
            @RequestParam(required = false) String telefon){

        List<UserResponseDTO> newfilterquery = userService.getfilterUsers(name, lastName, tckNo,serhir,okul,telefon);
        return ResponseEntity.ok(newfilterquery);
    }

    @GetMapping("/map")
    public ResponseEntity<List<User>> getUserswithmap(@RequestBody
           Map<String, String> datas){
        System.out.println(datas);
        List<User> listQueryFilter = userService.getUserswithmap(datas.getOrDefault("name", null),
                datas.getOrDefault("tckNo", null)
                , datas.getOrDefault("lastName", null),
                datas.getOrDefault("serhir", null),
                datas.getOrDefault("okul", null),
                datas.getOrDefault("telefon", null));
        return ResponseEntity.ok(listQueryFilter);
    }


    @GetMapping("/findBytc/{tckNo}")
    public ResponseEntity<UserResponseDTO> findByTC(@PathVariable String  tckNo){
        UserResponseDTO findTC = userService.findByTckNo(tckNo);
        return ResponseEntity.ok(findTC);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<UserResponseDTO> findByName(@PathVariable String name){
        UserResponseDTO userResponseDTO = userService.findByName(name);
        return  ResponseEntity.ok(userResponseDTO);
    }

//    @GetMapping("/posts")
//    public List<UserResponseDTO> getFilteredPosts(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) String tckNo,
//            @RequestParam(required = false) String okul,
//            @RequestParam(required = false) String telefon,
//            @RequestParam(required = false) String serhir
//    ) {
//        List<UserResponseDTO> posts = userService.getFilteredPosts(name, lastName, tckNo,okul,telefon,serhir);
//        return posts;
//    }




//    @GetMapping("findbydate")
//    public ResponseEntity<List<UserResponseDTO>> findByDate(@RequestParam (name = "startDate") @DateTimeFormat(pattern = YILPATTERIN)LocalDate startDate,
//                                                                   @RequestParam (name = "endDate") @DateTimeFormat(pattern = YILPATTERIN) LocalDate endDate){
//       List<UserResponseDTO> findByDateBetween = userService.findByDate(startDate,endDate);
//       return ResponseEntity.ok(findByDateBetween);
//    }
//
//    @DeleteMapping("/{tckNO}")
//    public ResponseEntity<Boolean> userDelete(@PathVariable String tckNO){
//        Boolean status = userService.userDelete(tckNO);
//        return ResponseEntity.ok(status);
//    }


}
