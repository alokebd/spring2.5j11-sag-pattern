package com.vision.UserService.projection;

import com.vision.CommonService.model.CardDetails;
import com.vision.CommonService.model.User;
import com.vision.CommonService.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query) {
        //Ideally Get the details from the DB
        CardDetails cardDetails
                = CardDetails.builder()
                .name("Aloke Das")
                .validUntilYear(2022)
                .validUntilMonth(01)
                .cardNumber("6474091496")
                .cvv(111)
                .build();

        return User.builder()
                .userId(query.getUserId())
                .firstName("Aloke")
                .lastName("Das")
                .cardDetails(cardDetails)
                .build();
    }
}
