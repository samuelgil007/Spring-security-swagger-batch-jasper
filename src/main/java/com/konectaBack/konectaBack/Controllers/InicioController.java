package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Constants.ControllerConstants;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(ControllerConstants.FRONT_END)
public class InicioController {

    @GetMapping("/")
    public String inicio(){
        return "Bievenido a la API ヽ(ヅ)ノ";
    }
}
