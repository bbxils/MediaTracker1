package ru.itis.services;

import ru.itis.models.FileInfo;
import ru.itis.repositories.FilesRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    private FilesRepository filesRepository;
    public FileServiceImpl (FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .type(contentType)
                .build();

        // нужно попробовать сделать транзакцию (чтобы если в первом ошибка то ничего не происходило)
        try {
            Files.copy(file, Paths.get("/Users/arina/Desktop/ОРИС/files/" + fileInfo.getStorageFileName() + "." +
                    fileInfo.getType().split("/")[1]));
            filesRepository.save(fileInfo);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        FileInfo fileInfo = filesRepository.findById(fileId);
        File file = new File("/Users/arina/Desktop/ОРИС/files/" + fileInfo.getStorageFileName() + "." +
                fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return filesRepository.findById(fileId);
    }
}
