package planeServiceTraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import planeServiceTraining.domain.Buyer;
import planeServiceTraining.service.BuyerService;


import java.util.List;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping(value = "/buyer/all")
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("buyer-list");

        List<Buyer> buyers = buyerService.getAllBuyers();

        mav.addObject("buyers", buyers);
        return mav;
    }

}
