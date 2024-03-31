package dsi.esprit.tn.services;

import dsi.esprit.tn.Models.clubFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IclubFileService {
    clubFile addFile(MultipartFile file, Long id);
    void removeFile(Long f);
    List<clubFile> findAll();
    List<clubFile> GetclubFiles(Long id);
}
