package com.mg.samartrent.renter.integration.service

import com.mg.samartrent.renter.integration.IntegrationTestsSetup
import com.mg.smartrent.domain.models.RenterView
import com.mg.smartrent.renter.RenterApplication
import com.mg.smartrent.renter.service.RenterViewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static com.mg.samartrent.renter.TestUtils.generateRenterView

@SpringBootTest(classes = RenterApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = ["eureka.client.enabled:false"]
)
class TestRenterViewsService extends IntegrationTestsSetup {

    @Autowired
    private RenterViewsService viewsService

    static boolean initialized

    def setup() {
        if (!initialized) {
            purgeCollection(RenterView.class)
            initialized = true
        }
    }


    def "test: create renter view"() {

        setup:
        RenterView view = generateRenterView()

        when: "saving"
        def dbReview = viewsService.save(view)

        then: "successfully saved"
        dbReview.getTrackingId() != null
        dbReview.getCreatedDate() != null
        dbReview.getModifiedDate() != null
        dbReview.getUserTID() == view.getUserTID()
        dbReview.getRenterTID() == view.getRenterTID()
    }

    def "test: get count of Views by renter TID"() {
        setup:
        RenterView view = generateRenterView()
        viewsService.save(view)

        when:
        Long count = viewsService.count(view.getRenterTID())

        then:
        count == 1
    }


}
