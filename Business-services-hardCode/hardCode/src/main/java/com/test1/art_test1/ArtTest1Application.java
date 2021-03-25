package com.test1.art_test1;

import com.test1.art_test1.api.Demon;
import com.test1.art_test1.config.DataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class ArtTest1Application {

    public static void main(String[] args) throws IOException, SQLException {

        //System.getProperties().put( "server.port", 8183 );
        SpringApplication app = new SpringApplication(ArtTest1Application.class);
        app.run(args);

        System.out.println("Hit 'Enter' to terminate");
        System.in.read();
    }

}
