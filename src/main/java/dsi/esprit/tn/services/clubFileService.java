package dsi.esprit.tn.services;

import dsi.esprit.tn.Models.Club;
import dsi.esprit.tn.Models.clubFile;
import dsi.esprit.tn.repository.clubFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class clubFileService implements IclubFileService {
    @Autowired
    IclubServiceImpl IES;
    @Autowired
    clubFileRepository fr;

    public clubFile addFile(MultipartFile file, Long id) {
        Club club = IES.showClub(id);
        try {

            //FTPService.uFileUpload(file, "event", id);


            clubFile f = new clubFile();
            f.setUploadDate(new Date());
            f.setFileName(file.getOriginalFilename());
            //f.setFilePath(TMP_UPLOAD_FOLDER + "event" + "\\" + file.getOriginalFilename());
            System.out.println("original file size: " + file.getBytes().length);
            f.setPicByte(file.getBytes());
            f.setClub(club);
            return fr.save(f);
        } catch (Exception e) {
            System.out.println("Error Uploading file");
        }
        return null;
    }


    public void removeFile(Long f) {
        clubFile file = fr.findById(f).orElse(null);
        if (!(file == null)) {
            try {
                fr.delete(file);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public List<clubFile> findAll() {
        return fr.findAll();

    }

    public List<clubFile> GetclubFiles(Long id) {
        List<clubFile> list = new ArrayList<clubFile>();
        fr.findAll().forEach(f -> {
            if (f.getClub().getId() == id)
                list.add(f);
        });
        return list;
    }
}
