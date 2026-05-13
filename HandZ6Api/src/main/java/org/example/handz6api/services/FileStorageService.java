// services/FileStorageService.java
package org.example.handz6api.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${app.base.url}")
    private String baseUrl;

    public String guardarLogo(MultipartFile file) throws IOException {
        // Crea el directorio si no existe
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Nombre único para evitar colisiones
        String extension = getExtension(file.getOriginalFilename());
        String nombreFichero = UUID.randomUUID() + "." + extension;

        // Guarda el fichero
        Path filePath = uploadPath.resolve(nombreFichero);
        Files.copy(file.getInputStream(), filePath);

        // Devuelve la URL pública
        return baseUrl + "/uploads/logos/" + nombreFichero;
    }

    public void eliminarLogo(String logoUrl) throws IOException {
        if (logoUrl == null || logoUrl.isEmpty()) return;
        String nombreFichero = logoUrl.substring(logoUrl.lastIndexOf("/") + 1);
        Path filePath = Paths.get(uploadDir).resolve(nombreFichero);
        Files.deleteIfExists(filePath);
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "jpg";
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }
}