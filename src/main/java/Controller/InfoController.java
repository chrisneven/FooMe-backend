package Controller;

import DAO.InfoDAO;
import Model.CPUTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chrisneven
 * The InfoController class is responsible for handeling requests by using the DAO's for saving and the model for defining objects that are received.
 * from the frontend. The Controller is the bridge between mobile application and the DAO - database.
 */

@Controller
@RequestMapping("/info")
public class InfoController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private InfoDAO infoDAO;

    /**
     * Method for capturing CPUTemp objects and giving it to the InfoDAO wich adds it to the db
     * @param cpuTemp Object of the CPUTemp model
     * @return A string in JSON format so the mobile application can handle it properly. This can be either a succesful one or an error.
     */
    @PostMapping(path = "/cputemp/add") // POST request enabled
    public @ResponseBody
    String addNewCPUTemp (@RequestBody CPUTemp cpuTemp) {
        try {
            infoDAO.save(cpuTemp);
            return "{saved:true}";
        }
        catch (Exception e){ //When an error occurs, the error will be returned as a message in the json response
            return "{saved:false, error:" + e + "}";
        }
    }

}
