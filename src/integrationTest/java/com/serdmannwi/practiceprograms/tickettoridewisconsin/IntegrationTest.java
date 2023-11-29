package com.serdmannwi.practiceprograms.tickettoridewisconsin;

import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureJson
@AutoConfigureJsonTesters
//@TestPropertySource(properties = {
//    "spring.datasource.url=jdbc:tc:mysql:5.7:///testdb",
//    "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
//})
public @interface IntegrationTest {
}
