public interface Commands {
    int EXIT = 0;
    int REGISTER = 1;
    int LOGIN = 2;
    int LOGOUT = 3;
    int ADD_POST = 4;
    int SEARCH_POST = 5;
    int GET_POST_BY_TITLE = 6;
    int POSTS_BY_CATEGORY = 7;
    int ALL_POSTS = 8;


    static void maincommands() {
        System.out.println("Input " + EXIT + " for EXIT");
        System.out.println("Input " + REGISTER + " for REGISTER");
        System.out.println("Input " + LOGIN + " for LOGIN");
    }

    static void userCommands() {
        System.out.println("Input " + LOGOUT + " for LOGOUT");
        System.out.println("Input " + ADD_POST + " for ADD_POST");
        System.out.println("Input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Input " + GET_POST_BY_TITLE + " for GET_POST_BY_TITLE");
        System.out.println("Input " + POSTS_BY_CATEGORY + " for POSTS_BY_CATEGORY");
        System.out.println("Input " + ALL_POSTS + " for ALL_POSTS");
    }
}
