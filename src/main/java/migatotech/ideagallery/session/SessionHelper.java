package migatotech.ideagallery.session;

public class SessionHelper {

    public static Integer retrieveMandatoryAccountId(Session session) {
        return session.accountId()
                .orElseThrow(() -> new IllegalStateException("Account id is null"));
    }
}
