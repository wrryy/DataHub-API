package pl.wrydz.space;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrydz.space.entity.Mission;

@Repository
public interface MissionRepo extends JpaRepository<Mission, Long> {

}
