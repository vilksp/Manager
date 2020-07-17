package lt.management.oms.crude;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Edgaras Venzlauskas
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/auth/")
@CrossOrigin(origins = { "http://localhost:3000" })
public class AuthenticationRestControllerV1 {

    @GetMapping("login")
    public String testLogin(){
        return "Login OK";
    }
}
