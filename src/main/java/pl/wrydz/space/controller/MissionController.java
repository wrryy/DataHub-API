package pl.wrydz.space.controller;

import org.graalvm.compiler.options.OptionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.wrydz.space.entity.Mission;
import pl.wrydz.space.service.MissionService;

import java.util.Optional;

@RestController
@RequestMapping("/admin/missions")
public class MissionController {

    @Autowired
    private MissionService service;

    @PostMapping
    public long addMission(@RequestParam String name, @RequestParam String imageType,
                           @RequestParam Optional<String> startTime, @RequestParam Optional<String> finishTime){
        return service.addMission(name, imageType, startTime, finishTime);
    }

    @PutMapping("/{id}")
    public Mission editMission(@PathVariable long id, @RequestParam Optional<String> name,
                               @RequestParam Optional<String> imageType, @RequestParam Optional<String> startTime,
                               @RequestParam Optional<String> finishTime){
        return service.editMission(id, name, imageType, startTime, finishTime);
    }

    @DeleteMapping("/{id}")
    public String deleteMission(@PathVariable long id /*,i cos jeszcze*/ ){
                service.deleteMission(id);
        return "";
    }

}
