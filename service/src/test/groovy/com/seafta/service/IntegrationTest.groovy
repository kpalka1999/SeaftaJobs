package com.seafta.service

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.TYPE
import static java.lang.annotation.RetentionPolicy.RUNTIME
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@Retention(RUNTIME)
@Target(TYPE)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@interface IntegrationTest {
}

