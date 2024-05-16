package dsi.esprit.tn.repository;

import dsi.esprit.tn.Models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface clubRepository extends JpaRepository<Club, Long> {
    @Modifying
    @Transactional
    @Query(value = "update users set club_id=?1 where id=?2", nativeQuery = true)
    void ConfirmUserClub(Long club_id, Long id);
    @Modifying
    @Transactional
    @Query(value = "update users set club_id=null where id=?1", nativeQuery = true)
    void DeleteUserClub(Long id);
    @Modifying
    @Transactional
    @Query(value = "update users set club_id=null where club_id=?1", nativeQuery = true)
    void DeleteClubUsers(Long club_id);
    @Query(value = "SELECT * FROM users WHERE club_id=?1", nativeQuery = true)
    List<Object[]> getClubUsers(Long club_id);
    @Query(value = "SELECT * FROM clubs WHERE name=?1", nativeQuery = true)
    Club getClubByName(String name);
    @Query(value = "select clubs.* from users inner join clubs on users.club_id=clubs.id where users.username=?1",
            nativeQuery = true)
    Club getUserClub(String username);
    @Query(value = "select name from clubs",
            nativeQuery = true)
    List<String> getAllClubNames();
    @Query(value = "select clubs.id from clubs where name=?1",
            nativeQuery = true)
    Long getClubId(String name);
    @Query(value = "select clubs.id from users inner join clubs on users.club_id=clubs.id where users.username=?1",
            nativeQuery = true)
    Long getUserClubId(String username);

//    @Query(value = "select event_id from user_events group by event_id order by avg(Rating) desc", nativeQuery = true)
//    List<Integer[]> bestClubEventsOfAllTime();

    @Query(value = "select avg(user_events.Rating),name from user_events " +
            "inner join club_events on club_events.event_id = user_events.event_id " +
            "inner join events on events.id = club_events.event_id " +
            "where club_events.club_id=?1 " +
            "group by user_events.event_id " +
            "order by avg(user_events.Rating) desc",
            nativeQuery = true)
    List<Object[]> bestClubEvents(Long club_id);
    @Query(value = "select avg(user_events.Rating),name from user_events " +
            "inner join club_events on club_events.event_id = user_events.event_id " +
            "inner join clubs on clubs.id = club_events.club_id " +
            "group by clubs.id " +
            "order by avg(user_events.Rating) desc",
            nativeQuery = true)
    List<Object[]> bestClubs();
    @Query(value = "select count(*) from club_events where club_id=?1",
            nativeQuery = true)
    Integer countClubEvents(Long club_id);
    @Query(value = "SELECT COUNT(*) FROM clubs", nativeQuery = true)
    Integer countAllClubs();
    @Query(value = "SELECT COUNT(*) FROM users WHERE club_id IS NOT NULL", nativeQuery = true)
    Integer countAllClubsParticipations();
    @Query(value = "SELECT COUNT(*) FROM users where club_id=?1", nativeQuery = true)
    Integer countClubParticipations(Long idClub);

    @Query(value = "SELECT type,COUNT(*) FROM clubs GROUP BY type", nativeQuery = true)
    List<Object[]> countAllClubsByType();

    @Query(value = "SELECT status,COUNT(*) FROM clubs GROUP BY status", nativeQuery = true)
    List<Object[]> countAllClubsByStatus();

    @Query(value = "SELECT COUNT(*), clubs.name FROM users " +
            "inner join clubs on users.club_id = clubs.id " +
            "GROUP BY clubs.name " +
            "ORDER BY COUNT(*) DESC ", nativeQuery = true)
    List<Object[]> topClubParticipations();
}
