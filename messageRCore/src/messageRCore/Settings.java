package messageRCore;

/**
 *
 * @author maritn
 */
public final class Settings {

    public static final class Actions {

        public static final String LOGIN = "login";
        public static final String SUBMIT = "submit";
        public static final String CHECK = "check";
    }

    public static final class Endpoints {

        public static final String SERVER = "http://localhost:619/";
        public static final String LOGIN = SERVER + Actions.LOGIN;
        public static final String SUBMIT = SERVER + Actions.SUBMIT;
        public static final String CHECK = SERVER + Actions.CHECK;
    }

    public static final class Headers {

        public static final String USER_NAME = "username";
    }
}
