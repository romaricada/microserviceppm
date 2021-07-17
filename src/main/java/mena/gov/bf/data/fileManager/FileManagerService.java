package mena.gov.bf.data.fileManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import mena.gov.bf.data.importPPM.ImportPpmService;
import mena.gov.bf.domain.Activite;
import mena.gov.bf.domain.ExerciceBudgetaire;
import mena.gov.bf.domain.PpmActivite;
import mena.gov.bf.repository.ExerciceBudgetaireRepository;
import mena.gov.bf.repository.PpmActiviteRepository;

@Service
public class FileManagerService {

    private final ExerciceBudgetaireRepository exerciceBudgetaireRepository;
    private final PpmActiviteRepository ppmActiviteRepository;
    private final ImportPpmService importPpmService;
    Path path;

    public FileManagerService(ExerciceBudgetaireRepository exerciceBudgetaireRepository,
            PpmActiviteRepository ppmActiviteRepository, ImportPpmService importPpmService) {
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.ppmActiviteRepository = ppmActiviteRepository;
        this.importPpmService = importPpmService;
    }

    @Transactional
    public void createFolder(HttpServletResponse response, MultipartFile file, Long idAnnee) {
        String chemin = getPath(idAnnee);
        path = Paths.get(chemin);

        // if directory exists?
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                File file1 = importPpmService.createFile(file.getInputStream(),
                        new File(chemin + file.getOriginalFilename()));
            } else {
                File file1 = importPpmService.createFile(file.getInputStream(),
                        new File(chemin + file.getOriginalFilename()));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public String getPath(Long id) {
        String chemin = "./src/main/resources/documents/";
        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireRepository.getOne(id);
        chemin = chemin + exerciceBudgetaire.getAnnee() + "/";
        System.out.println("================ " + chemin + " ==================");
        return chemin;
    }

    public void init(Long idAnnee) {
        String chemin = getPath(idAnnee);
        path = Paths.get(chemin);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Transactional
    public void save(MultipartFile file, Long idAnnee) {
        String chemin = getPath(idAnnee);
        path = Paths.get(chemin);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            File file1 = importPpmService.createFile(file.getInputStream(),
                    new File(chemin + file.getOriginalFilename()));
        } catch (Exception e) {
            // throw new RuntimeException("Could not store the file. Error: " +
            // e.getMessage());
        }
    }

    @Transactional
    public Resource load(String filename, Long idAnnee) {
        String chemin = getPath(idAnnee);
        path = Paths.get(chemin);
        try {
            Path file = path.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteAll(Long idAnnee) {
        String chemin = getPath(idAnnee);
        path = Paths.get(chemin);
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    @Transactional
    public Stream<Path> loadAll(Long idAnnee) {
        String chemin = getPath(idAnnee);
        path = Paths.get(chemin);
        try {
            return Files.walk(this.path, 1).filter(path -> !path.equals(this.path)).map(this.path::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

}