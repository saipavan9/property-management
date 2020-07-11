package mng.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import mng.data.FilesRepository;
import mng.model.Document;


@Controller
public class FilesController {

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private FilesRepository filesRepo;
	
	@PostMapping("/uploadFile")
	public String upload(@RequestParam("file") MultipartFile file,@RequestParam("doc_name")String docType,@RequestParam("id")String prop_id) {
		
		
		String fileName = storageService.storeFile(file, prop_id, docType);	
		System.out.println(fileName);
		return "redirect:/property";
	}
	
	@GetMapping("/getDoc")
	public @ResponseBody Map<String,String> getDocNames(@RequestParam String id) {
		
		List<Document> docs = filesRepo.findByProperty(Long.valueOf(id));
		Map<String,String> names = new HashMap<>();
		for(Document doc:docs) {
			names.put(String.valueOf(doc.getDoc_id()), doc.getName());
			
		}
		
		return names;
	}
	
	@GetMapping("/download")
	public ResponseEntity<Resource> getDownloadUrl(@RequestParam String name,
			HttpServletRequest request) {
		
		String fileName = name+".pdf";
        Resource resource = null;
       
        if(fileName !=null && !fileName.isEmpty()) {
        try {
            resource = storageService.loadFileAsResource(fileName);
            byte[] temp = resource.getInputStream().readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contentType = null;
        try {
             contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
           } catch (IOException ex) {
        	 ex.printStackTrace();  
            }
           if(contentType == null) {
                contentType = "application/octet-stream";
          }
        
       return ResponseEntity.ok()
    		   .contentType(MediaType.parseMediaType(contentType))
    		   .header(HttpHeaders.CONTENT_DISPOSITION,String.format("inline; filename=\"" + resource.getFilename() + "\""))
    		   .body(resource);
	 }else {
		return ResponseEntity.notFound().build();
	}
        
	}
	
	@GetMapping("/demo")
	public String demo() {
		return "demo";
	}
	
	
}
