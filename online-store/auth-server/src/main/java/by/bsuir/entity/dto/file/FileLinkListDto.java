package by.bsuir.entity.dto.file;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FileLinkListDto {
    private List<FileLinkDto> links;

    public static FileLinkListDto of(List<String> links){
        return FileLinkListDto.builder()
                .links(links.stream()
                        .map(FileLinkDto::of)
                        .collect(Collectors.toList()))
                .build();
    }

}
