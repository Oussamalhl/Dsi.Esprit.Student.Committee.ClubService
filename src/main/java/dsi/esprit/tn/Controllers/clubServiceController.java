package dsi.esprit.tn.Controllers;

import dsi.esprit.tn.Models.Club;
import dsi.esprit.tn.repository.clubRepository;
import dsi.esprit.tn.security.jwt.JwtUtils;
import dsi.esprit.tn.services.IclubServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/club")
public class clubServiceController {
    @Autowired
    IclubServiceImpl clubservice;

    @Autowired
    clubRepository clubRepository;

    @Autowired
    private JwtUtils jwtUtils;


/////////////////////

    @GetMapping("/test")
    public String test() {
        return "Club api.";
    }

    @GetMapping("/test/auth")
    public String testAuth() {
        return "Club authenticated.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Boolean adminTest() {
        return true;
    }

    @GetMapping("/moderator")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public Boolean moderatorTest() {
        return true;
    }

    @PostMapping("/addClub")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Club addClub(@Valid @RequestBody Club Club) {
        return clubservice.addClub(Club);
    }
    @PostMapping("/confirmU")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void ConfirmUserClub(@RequestParam("idClub") Long idClub,@RequestParam("id") Long idUser) {
        clubservice.ConfirmUserClub(idClub,idUser);
    }
    @PostMapping("/deleteU")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void DeleteUserClub(@RequestParam("id") Long idUser) {
        clubservice.DeleteUserClub(idUser);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteClub")
    public ResponseEntity<?> deleteClub(@Valid @RequestParam long idClub) {

        clubservice.DeleteClubUsers(idClub);
        clubservice.deleteClub(idClub);
        return ResponseEntity.ok("Club Id:" + idClub + " is successfully deleted");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/showallClubs")
    public List<Club> showAllClubs() {

        return clubservice.showAllClubs();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/showClub")
    public Club showClub(@Valid @RequestParam long idClub) {

        return clubservice.showClub(idClub);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/showClubU")
    public List<Object[]> getClubUsers(@Valid @RequestParam long idClub) {

        return clubservice.getClubUsers(idClub);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/showUClub")
    public Club getUserClub(HttpServletRequest request) {
        String jwt = jwtUtils.parseJwt(request);
        if (jwt != null) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            return clubservice.getUserClub(username);
        }
        return null;

    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/getC")
    public Long getUserClubId(HttpServletRequest request) {

        String jwt = jwtUtils.parseJwt(request);
        if (jwt != null) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            return clubservice.getUserClubId(username);
        }
        return null;

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateClub")
    public Club updateClub(@RequestBody Club Club) {

        return clubservice.updateClub(Club);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/bestclubEv")
    public List<Object[]> bestClubEvents(@RequestParam("idClub") Long idClub) {
        return clubservice.bestClubEvents(idClub);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/topClPart")
    public List<Object[]> topClubParticipations() {
        return clubservice.topClubParticipations();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countAll")
    public Integer countAllClubs() {
        return clubservice.countAllClubs();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countAllPart")
    public Integer countAllClubsParticipations() {
        return clubservice.countAllClubsParticipations();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countClPart")
    public Integer countClubParticipations(@RequestParam("idClub") Long idClub) {
        return clubservice.countClubParticipations(idClub);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countClType")
    public List<Object[]> countAllClubsByType() {
        return clubservice.countAllClubsByType();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/countClStatus")
    public List<Object[]> countAllClubsByStatus() {
        return clubservice.countAllClubsByStatus();
    }

}
