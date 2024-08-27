package migatotech.ideagallery.session;

import migatotech.ideagallery.exception.NotAuthorizedException;

public class SessionHelper {

    public static Integer retrieveMandatoryAccountId(Session session) {
        return session.accountId()
                .orElseThrow(() -> new NotAuthorizedException("Account id is not present in session."));
    }

    public static Integer retrieveOptionalAccountId(Session session) {
        return session.accountId()
                .orElse(null);
    }
}
