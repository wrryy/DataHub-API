package pl.wrydz.space.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wrydz.space.MissionRepo;
import pl.wrydz.space.entity.Mission;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class MissionService {

    private MissionRepo missionRepo;

    @Autowired
    public MissionService(MissionRepo missionRepo) {
        this.missionRepo = missionRepo;
    }

    public long addMission(String name, String imageType, Optional<String> startTime,
                           Optional<String> finishTime){
        Mission mission = new Mission();
        mission.setName(name);
        mission.setImageType(Mission.ImageType.valueOf(imageType.toUpperCase()));
        setMissionTime(mission, startTime, finishTime);
        return missionRepo.save(mission).getId();
    }

    public Mission editMission(long id, Optional<String> name, Optional<String> imageType, Optional<String> startTime,
                               Optional<String> finishTime){
        Mission mission = missionRepo.getOne(id);
        name.ifPresent(mission::setName);
        imageType.ifPresent(s -> mission.setImageType(Mission.ImageType.valueOf(s)));
        setMissionTime(mission, startTime, finishTime);
        missionRepo.save(mission);
        return mission;
    }
    public void deleteMission(long id){
        missionRepo.deleteById(id);
    }
    private void setMissionTime(Mission mission, Optional<String> startTime,Optional<String> finishTime){
        startTime.ifPresent(s -> mission.setStartTime(OffsetDateTime.parse(s, DateTimeFormatter.ISO_ZONED_DATE_TIME)));
        finishTime.ifPresent(s -> mission.setFinishTime(OffsetDateTime.parse(s, DateTimeFormatter.ISO_ZONED_DATE_TIME)));
    }
}
