package cz.nigol.obec.services;

import java.util.List;

import cz.nigol.obec.entities.FileMetadata;
import cz.nigol.obec.entities.User;
import cz.nigol.obec.exceptions.UploadFailedException;
import java.io.*;

public interface FileMetadataService {
    List<FileMetadata> getByUser(User user);
    FileMetadata save(FileMetadata fileMetadata, String path, byte[] bytes) throws UploadFailedException;
    List<FileMetadata> getAll();
    void delete(FileMetadata fileMetadata, String path) throws IOException;
}
