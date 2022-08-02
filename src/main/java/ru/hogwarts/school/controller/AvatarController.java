package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.schoolInterface.AvatarInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@RestController
@RequestMapping("avatar")
public class AvatarController {

    private final AvatarInterface avatarInterface;

    public AvatarController(AvatarInterface avatarInterface) {
        this.avatarInterface = avatarInterface;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
                                               @PathVariable MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is sow BIG!");
        }

        avatarInterface.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/download/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {

        Avatar avatar = avatarInterface.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/download")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {

        Avatar avatar = avatarInterface.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }

    }

    @GetMapping("/page-students-avatar")
    public ResponseEntity<List<Avatar>> getAvatarPage(@RequestParam("page") Integer pageNumber,
                                                      @RequestParam("size") Integer pageSize){
        List<Avatar> avatarListPage = avatarInterface.getAllAvatarsOnPage(pageNumber, pageSize);
        return ResponseEntity.ok(avatarListPage);
    }
}
