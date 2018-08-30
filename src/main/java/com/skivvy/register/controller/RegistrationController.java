package com.skivvy.register.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.skivvy.register.model.User;
import com.skivvy.register.repo.UserRepository;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.util.*;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Value("${com.cloudinary.cloud_name}")
    String mCloudName;

    @Value("${com.cloudinary.api_key}")
    String mApiKey;

    @Value("${com.cloudinary.api_secret}")
    String mApiSecret;

    @PostMapping(value = "/media")
    public ResponseEntity<String> post(@RequestParam(value = "maidId", required = true) String maidId, @RequestParam(value = "type", required = true) String type,
                                       @RequestParam(value = "upload", required = true) MultipartFile aFile) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", mCloudName,
                "api_key", mApiKey,
                "api_secret", mApiSecret));

        try {
            File f = Files.createTempFile("temp", aFile.getOriginalFilename()).toFile();
            aFile.transferTo(f);
            Transformation transformation = new Transformation().overlay("ekyb9qr1vetwrz8rxfrg").width(150).height(27).gravity("south_east")
                    .x(5).y(5).effect("brightness:100");
            Map params = ObjectUtils.asMap("public_id", maidId + "/" + type,
                                                  "eager", Arrays.asList(transformation));
            Map response = cloudinary.uploader().upload(f, params);
            JSONObject json = new JSONObject(response);
            String url = json.getString("url");
            return new ResponseEntity<String>(json.toString(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {
        Optional<User> savedUser = userRepository.findById(id);
        return savedUser.orElseThrow(() -> new RuntimeException("No User found for Id - " + id));
    }

    @DeleteMapping("/users")
    public ResponseEntity deleteUsers() {
        userRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
 /*@GetMapping(value = "/media")
    public ResponseEntity<List<String>> get(
            @RequestParam(value = "name", required = false) String aName) {
        Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
        List<String> retval = new ArrayList<String>();
        try {
            Map response = c.api().resource("", ObjectUtils.asMap("type", "upload"));
            JSONObject json = new JSONObject(response);
            JSONArray ja = json.getJSONArray("resources");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject j = ja.getJSONObject(i);
                retval.add(j.getString("url"));
            }

            return new ResponseEntity<List<String>>(retval, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        }
    }*/
}
