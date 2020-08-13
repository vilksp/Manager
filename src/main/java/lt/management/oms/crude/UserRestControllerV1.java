package lt.management.oms.crude;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/api/v1/")
public class UserRestControllerV1 {

    @GetMapping("test")
    public String testMethod(){
        return "User OK";
    }
    
    @RequestMapping(value = "/username", method = RequestMethod.GET)
   	@ResponseBody
   	public UserDetails currentUserName() {
       	return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
   	}
}
