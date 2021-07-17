package mena.gov.bf.data.fileManager;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import liquibase.pro.packaged.id;
import mena.gov.bf.data.fileManager.message.ResponseMessage;
import mena.gov.bf.data.fileManager.model.FileInfo;

@RestController
@RequestMapping("/api")
public class FileManagerResource {

    private static final Logger log = LoggerFactory.getLogger(FileManagerResource.class);

    @Autowired
    FileManagerService fileManagerService;

    @PostMapping("/create")
    public void createFolder(HttpServletResponse response, MultipartFile file, @RequestParam(name = "id") Long id) {
        fileManagerService.createFolder(response, file, id);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(MultipartFile file, @RequestParam(name = "id") Long id) {
        String message = "";
        try {
            fileManagerService.save(file, id);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            //message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles(@RequestParam(name = "id") Long id) {
        List<FileInfo> fileInfos = fileManagerService.loadAll(id).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileManagerResource.class, "getFile", path.getFileName().toString()).build()
                    .toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename, @RequestParam(name = "id") Long id) {
        Resource file = fileManagerService.load(filename, id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}