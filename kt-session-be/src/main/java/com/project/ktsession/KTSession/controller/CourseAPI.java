//package com.project.ktsession.KTSession.controller;
//
//import com.project.ktsession.KTSession.repository.UserRepository;
//import com.project.ktsession.KTSession.entity.CourseDTO;
//import com.project.ktsession.KTSession.entity.LoginDTO;
//import com.project.ktsession.KTSession.entity.ResponseDTO;
//import com.project.ktsession.KTSession.entity.LoginRequest;
//import com.project.ktsession.KTSession.entity.SignupRequest;
//
//import com.project.ktsession.KTSession.service.CourseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
////@CrossOrigin(origins = "http://localhost:8080")
//@RestController
//@RequestMapping("/kt-session")
//public class CourseAPI {
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/fetchAllCourses")
//    public ResponseEntity<Object> fetchAllCourses() {
//        try {
//            // FIX: Changed from List<LoginDTO> to List<CourseDTO>
//            List<CourseDTO> courses = courseService.findAll();
//            return ResponseEntity.ok(courses);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO("ERR_500", "Failed to retrieve courses: " + e.getMessage()));
//        }
//    }
//
//    @GetMapping("/fetchCourse/{id}")
//    public ResponseEntity<Object> fetchCourse(@PathVariable("id") String id) {
//        try {
//            // FIX: Changed from Optional<CourseRepository> to Optional<CourseDTO>
//            Optional<CourseDTO> courseOpt = courseRepository.findById(id);
//
//            if (courseOpt.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new ResponseDTO("ERR_404", "Course with ID '" + id + "' not found."));
//            }
//
//            return ResponseEntity.ok(courseOpt.get());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO("ERR_500", "Error fetching course: " + e.getMessage()));
//        }
//    }
//
//    @PostMapping("/addCourse")
//    public ResponseEntity<Object> addCourse(@RequestBody CourseDTO course) {
//        try {
//            if (course.getId() == null || course.getId().trim().isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new ResponseDTO("ERR_400_ID", "Validation Error: Course ID is required."));
//            }
//            if (course.getTitle() == null || course.getTitle().trim().isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(new ResponseDTO("ERR_400_TITLE", "Validation Error: Course Title is required."));
//            } // <-- This brace was missing!
//
//            courseRepository.save(course);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("SUCCESS", "Added Successfully.."));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO("ERR_500", "Error saving course: " + e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/deleteCourse/{id}")
//    public ResponseEntity<Object> deleteCourse(@PathVariable("id") String id) {
//        try {
//            if (!courseRepository.existsById(id)) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new ResponseDTO("ERR_404", "Delete Failed: Course with ID '" + id + "' does not exist."));
//            }
//
//            courseRepository.deleteById(id);
//            return ResponseEntity.ok(new ResponseDTO("SUCCESS_200", "Course Deleted Successfully."));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO("ERR_500", "Error deleting course: " + e.getMessage()));
//        }
//    }
//
//    @PutMapping("/updateCourse/{id}")
//    public ResponseEntity<Object> updateCourse(@PathVariable("id") String id, @RequestBody CourseDTO course) { // FIX: Changed Course to CourseDTO
//        try {
//            if (!courseRepository.existsById(id)) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new ResponseDTO("ERR_404", "Update Failed: Course with ID '" + id + "' does not exist."));
//            }
//
//            course.setId(id);
//            courseRepository.save(course);
//            return ResponseEntity.ok(new ResponseDTO("SUCCESS", "Course updated successfully..."));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ResponseDTO("ERR_500", "Error updating course: " + e.getMessage()));
//        }
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
//        try {
//            if (userRepository.existsByUsername(req.getUsername())) {
//                return ResponseEntity.status(HttpStatus.CONFLICT)
//                        .body(Map.of("message", "Username already exists"));
//            }
//
//            LoginDTO user = new LoginDTO();
//            user.setUsername(req.getUsername());
//            user.setEmail(req.getEmail());
//            user.setPassword(req.getPassword());
//            user.setRole("USER");
//
//            userRepository.save(user);
//            return ResponseEntity.ok(Map.of("token", "demo-token", "role", "USER"));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("message", "Signup failed: " + e.getMessage()));
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
//        try {
//            // 1. Check for the hardcoded admin fallback first (optional)
//            if ("admin".equals(req.getUsername()) && "admin123".equals(req.getPassword())) {
//                return ResponseEntity.ok(Map.of("token", "demo-token", "role", "ADMIN"));
//            }
//
//            // 2. Look up the user in the database by username
//            Optional<LoginDTO> userOpt = userRepository.findByUsername(req.getUsername());
//
//            // 3. Verify if the user exists
//            if (userOpt.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(Map.of("message", "Invalid credentials: Username not found."));
//            }
//
//            LoginDTO user = userOpt.get();
//
//           if (!user.isEnabled()) {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                        .body(Map.of("message", "Account is disabled."));
//            }
//
//           if (!user.getPassword().equals(req.getPassword())) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(Map.of("message", "Invalid credentials: Password incorrect."));
//            }
//
//            // 6. Login successful -> Return token and role from DB
//            return ResponseEntity.ok(Map.of(
//                    "token", "demo-token",
//                    "role", user.getRole(),
//                    "fullName", user.getFullName() != null ? user.getFullName() : user.getUsername()
//            ));
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Map.of("message", "An error occurred during login: " + e.getMessage()));
//        }
//    }
//}