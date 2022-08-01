package ru.hogwarts.school.schoolInterface;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarInterface {
    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Avatar findAvatar(Long studentId);

    List<Avatar> getAllAvatarsOnPage(Integer pageNumber, Integer pageSize);
}
