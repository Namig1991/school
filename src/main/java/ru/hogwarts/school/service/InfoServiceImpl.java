package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.schoolInterface.InfoServiceInterface;

@Service
@Profile("production")
public class InfoServiceImpl implements InfoServiceInterface {

    Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    @Value("${server.port}")
    private String port;

    @Override
    public String getPort(){
        logger.info("Вы используете порт = " + port);
        return port;
    }
}
