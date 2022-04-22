package by.bsuir.entity.dto.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileLinkDto {
    private String link;

    public static FileLinkDto of(String link){
        return FileLinkDto.builder()
                .link(link)
                .build();
    }
}
