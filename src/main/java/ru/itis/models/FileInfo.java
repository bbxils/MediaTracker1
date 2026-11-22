package ru.itis.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class FileInfo {
    private Long id;
    private  String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
}
