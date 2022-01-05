package com.seafta.service

import com.seafta.service.helper.rest.RestIT
import com.seafta.service.helper.WebHelper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = [WebHelper, SeaftaJobsApplication] )
@ActiveProfiles(value = "test")
class BaseSpecification extends RestIT {
}
