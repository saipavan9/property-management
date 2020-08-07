package mng.web;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
	
	@Value("${file.upload.dir}") String dir;
	
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
	
	@GetMapping(value = "/download", produces=MediaType.APPLICATION_PDF_VALUE)
	public @ResponseBody ResponseEntity<Resource> getDownloadUrl(@RequestParam String name,
			HttpServletRequest request) {
		
		String fileName = "/"+name+".pdf";
		Path path = Paths.get(dir+fileName);
				
        Resource resource = null;
        
        try {
        	resource = new UrlResource(path.toUri());
        }catch(MalformedURLException ex) {
        	ex.printStackTrace();
        }
        String contentType = "application/octet-stream";
        
        
       return ResponseEntity.ok()
    		   .contentType(MediaType.parseMediaType(contentType))
    		   .header(HttpHeaders.CONTENT_DISPOSITION,String.format("inline; filename=\"" + resource.getFilename() + "\""))
    		   .body(resource);
	 }
	
	@GetMapping("/demo")
	public String demo() {
		return "demo";
	}
	
	
}
