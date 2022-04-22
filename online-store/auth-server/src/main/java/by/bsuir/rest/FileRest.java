package by.bsuir.rest;

import by.bsuir.entity.dto.file.FileLinkListDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import static by.bsuir.constant.ApiPath.*;

@RestController
@Slf4j
public class FileRest {

    private FileService fileService;

    @Autowired
    public FileRest(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(UPLOAD_IMAGE)
    public FileLinkListDto upload(@RequestParam(value = "file0", required = false) MultipartFile file0,
                                  @RequestParam(value = "file1", required = false) MultipartFile file1,
                                  @RequestParam(value = "file2", required = false) MultipartFile file2,
                                  @RequestParam(value = "file3", required = false) MultipartFile file3,
                                  @RequestParam(value = "file4", required = false) MultipartFile file4,
                                  @RequestParam("productId") Integer productId) {
        List<MultipartFile> files = new ArrayList<>();
        if(file0 != null){
            files.add(file0);
        }
        if(file1 != null){
            files.add(file1);
        }
        if(file2 != null){
            files.add(file2);
        }
        if(file3 != null){
            files.add(file2);
        }
        if(file4 != null){
            files.add(file4);
        }
        log.info("request to upload file endpoint, count of files: {}", files.size());
        try {
            return fileService.upload(files, productId);
        } catch (IOException e) {
            log.info("error");
        }
        return null;
    }
}
