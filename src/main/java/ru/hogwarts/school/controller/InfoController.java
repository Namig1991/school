package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.schoolInterface.InfoServiceInterface;

@RestController
public class InfoController {

    private final InfoServiceInterface infoServiceInterface;

    public InfoController(InfoServiceInterface infoServiceInterface) {
        this.infoServiceInterface = infoServiceInterface;
    }

    @GetMapping("/port")
    public String getPort(){
        return infoServiceInterface.getPort();
    }
}
