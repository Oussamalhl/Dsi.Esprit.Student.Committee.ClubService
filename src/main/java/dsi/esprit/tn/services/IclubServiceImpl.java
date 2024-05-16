package dsi.esprit.tn.services;

import dsi.esprit.tn.Models.Club;

import java.util.List;

public interface IclubServiceImpl {
    Club addClub(Club club);
    void deleteClub(long idClub);
    void DeleteClubUsers(long idClub);
    Club updateClub(Club club);
    List<Club> showAllClubs();
    Club getClubByName(String name);
    Club showClub(Long idClub);
    Long showClubId(String name);
    List<String> getAllClubNames();
    List<Object[]> bestClubEvents(Long idClub);
    List<Object[]> bestClubs();
    Integer countAllClubs();
    Integer countClubEvents(Long idClub);
    Integer countAllClubsParticipations();
    Integer countClubParticipations(Long idClub);
    List<Object[]> countAllClubsByType();
    List<Object[]> countAllClubsByStatus();
    List<Object[]> topClubParticipations();
    void ConfirmUserClub(Long idClub,Long idUser);
    void DeleteUserClub(Long idUser);
    List<Object[]> getClubUsers(Long club_id);
    Club getUserClub(String username);
    Long getUserClubId(String username);
}
