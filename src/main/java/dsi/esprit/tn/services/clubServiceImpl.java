package dsi.esprit.tn.services;

import dsi.esprit.tn.Models.Club;
import dsi.esprit.tn.repository.clubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class clubServiceImpl implements IclubServiceImpl {

    @Autowired
    clubRepository clubRepository;

    @Override
    public Club addClub(Club club) {

        return clubRepository.save(club);
    }
    @Override
    public void ConfirmUserClub(Long idClub,Long idUser) {

        clubRepository.ConfirmUserClub(idClub,idUser);
    }
    @Override
    public void DeleteUserClub(Long idUser) {

        clubRepository.DeleteUserClub(idUser);
    }
    @Override
    public void deleteClub(long idClub){
        clubRepository.deleteById(idClub);
    }

    @Override
    public void DeleteClubUsers(long idClub){
        clubRepository.DeleteClubUsers(idClub);
    }
    @Override
    public Club updateClub(Club Club){
        return clubRepository.save(Club);
    }
    @Override
    public List<Club> showAllClubs() {
        return clubRepository.findAll();
    }

    @Override
    public Club showClub(Long idClub) {
        return clubRepository.findById(idClub).orElse(null);

    }
    @Override
    public Club getUserClub(String username) {
        return clubRepository.getUserClub(username);

    }
    @Override
    public Long getUserClubId(String username) {
        return clubRepository.getUserClubId(username);

    }
    @Override
    public List<Object[]> getClubUsers(Long idClub) {
        return clubRepository.getClubUsers(idClub);

    }
    @Override
    public List<Object[]> bestClubEvents(Long idClub){
        return clubRepository.bestClubEvents(idClub);
    }
    @Override
    public List<Object[]> topClubParticipations(){
        return clubRepository.topClubParticipations();
    }
    @Override
    public Integer countAllClubs(){
        return clubRepository.countAllClubs();
    }
    @Override
    public Integer countAllClubsParticipations(){
        return clubRepository.countAllClubsParticipations();
    }
    @Override
    public Integer countClubParticipations(Long idClub){
        return clubRepository.countClubParticipations(idClub);
    }
    @Override
    public List<Object[]> countAllClubsByType(){
        return clubRepository.countAllClubsByType();
    }
    @Override
    public List<Object[]> countAllClubsByStatus(){
        return clubRepository.countAllClubsByStatus();
    }
}
