package com.sample.prometheusgrafana.api;

import com.sample.prometheusgrafana.dao.UserRepository;
import com.sample.prometheusgrafana.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    @PostMapping
    public ResponseEntity<Map<String,String>> saveUser(@RequestBody UserEntity payload){
        repository.save(payload);
        return ResponseEntity.ok(Map.of("message","User Saved successfully"));
    }

    @GetMapping
    public ResponseEntity<Page<UserEntity>> fetchUsers(){
        var pageRequest = PageRequest.of(0, 10);
        return ResponseEntity.ok(repository.findAllBy(pageRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable Long userId){
        repository.deleteById(userId);
        return ResponseEntity.ok(Map.of("message","User deleted successfully"));
    }

}
