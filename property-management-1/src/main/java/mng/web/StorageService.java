package mng.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import mng.data.FilesRepository;
import mng.model.Document;

@Service
public class StorageService {

	
	private final Path fileStorageLocation;
	
	@Autowired
	public StorageService(@Value("${file.upload.dir}") String dir) {
		this.fileStorageLocation = Paths.get(dir).toAbsolutePath().normalize();
	}
	@Autowired
	FilesRepository filesRepo;
	
	public String storeFile(MultipartFile file, String prop_id,String docType) {
		
		String orgName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName = "";
		try {
			if(orgName.contains("..")) System.out.println("Sorry! Filename contains invalid path sequence");
			
			String extension = "";
			try {
				extension = orgName.substring(orgName.lastIndexOf("."));
			} catch (Exception e) {
				extension = "";
			}
			fileName = prop_id+"_"+docType+extension;
			
			Path targetLoc = fileStorageLocation.resolve(fileName);
			System.out.println(targetLoc);
			Files.copy(file.getInputStream(), targetLoc,StandardCopyOption.REPLACE_EXISTING);
			
			Document doc = new Document();
			doc.setName(docType);
			doc.setProperty(Long.valueOf(prop_id));
			filesRepo.save(doc);
			System.out.println(doc);
			
		}catch(IOException ex) {
			System.out.println("Could not store the file");
		}
		
		return fileName;
	}
	
    public Resource loadFileAsResource(String fileName) throws Exception {
        try {

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }
	
}
