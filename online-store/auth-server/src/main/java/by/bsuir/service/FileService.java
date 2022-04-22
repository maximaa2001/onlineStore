package by.bsuir.service;

import by.bsuir.entity.dto.file.FileLinkListDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileLinkListDto upload(List<MultipartFile> files, Integer productId) throws IOException;
}
