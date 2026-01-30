// UserController.java - Secure authentication endpoint
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            // Input validation to prevent SQL injection
            if (!request.getUsername().matches("^[a-zA-Z0-9_]{3,20}$")) {
                return ResponseEntity.badRequest().body("Invalid username format");
            }
            
            String token = userService.authenticate(
                request.getUsername(), 
                request.getPassword()  // Password hashed with BCrypt
            );
            
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
