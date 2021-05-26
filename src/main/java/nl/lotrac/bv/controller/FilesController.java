package nl.lotrac.bv.controller;

import lombok.extern.slf4j.Slf4j;
import nl.lotrac.bv.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/files")


@Slf4j

public class FilesController {

    private final FileService fileService;

    @Autowired
    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

//    test

    @GetMapping(value = "")
    public ResponseEntity<Object> getMessage() {
        return new ResponseEntity<>("endpoint: /files", HttpStatus.OK);
    }



    @PostMapping (value= "/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        log.debug("FilesController, upload:");
        try {
            fileService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

//    @GetMapping
//    public List<FileResponse> list() {
//        return fileService.getAllFiles()
//                .stream()
//                .map(this::mapToFileResponse)
//                .collect(Collectors.toList());
//    }
//
//    private FileResponse mapToFileResponse(FileEntity fileEntity) {
//        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/files/")
//                .path(fileEntity.getId())
//                .toUriString();
//        FileResponse fileResponse = new FileResponse();
//        fileResponse.setId(fileEntity.getId());
//        fileResponse.setName(fileEntity.getName());
//        fileResponse.setContentType(fileEntity.getContentType());
//        fileResponse.setSize(fileEntity.getSize());
//        fileResponse.setUrl(downloadURL);
//
//        return fileResponse;
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);
//
//        if (!fileEntityOptional.isPresent()) {
//            return ResponseEntity.notFound()
//                    .build();
//        }
//
//        FileEntity fileEntity = fileEntityOptional.get();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
//                .contentType(MediaType.valueOf(fileEntity.getContentType()))
//                .body(fileEntity.getData());
//    }
}