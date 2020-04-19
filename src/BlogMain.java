import exception.PostNotFoundException;
import exception.UserNotFoundException;
import model.Post;
import model.User;
import storage.PostStorageImpI;
import storage.UserStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class BlogMain  implements Commands {

    public static final UserStorageImpl USER_STORAGE = new UserStorageImpl();
    public static final PostStorageImpI POST_STORAGE = new PostStorageImpI();
    public static final Scanner SCANNER = new Scanner(System.in);
    public static User constUser;

    public static void main(String[] args) throws UserNotFoundException {
        boolean isRun = true;
        while (isRun) {
            Commands.maincommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
                command = -1;
            }

            switch (command) {
                case EXIT:
                    isRun = false;
                    System.out.println("Հաջողություն");
                    break;
                case REGISTER:
                    register();
                    break;
                case LOGIN:
                    login();
                    break;
                default:
                    System.out.println("Wrong command");

            }
        }
    }

    private static void register() {
        System.out.println("Please input yoyr name, surname,email,password");
        try {
            String usStr = SCANNER.nextLine();
            String[] userData = usStr.split(",");

            User user = new User();
            user.setName(userData[0]);
            user.setSurName(userData[1]);
            user.setEmail(userData[2]);
            user.setPassword(userData[3]);
            USER_STORAGE.add(user);
            System.out.println("Post added!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data");
            register();

        }

    }

    private static void login() throws UserNotFoundException {
        try {
            if (USER_STORAGE.isEmpty()) {
                System.out.println("Please register first");
            } else {
                System.out.println("Please input your email and password");
                String logStr = SCANNER.nextLine();
                String[] userData = logStr.split(",");
                try {
                    constUser = USER_STORAGE.getUserByEmailAddPassword(userData[0], userData[1]);
                    System.out.println("You entered your profile");
                    loginUserCommands();
                } catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            login();

        }
    }

    private static void loginUserCommands() {
        boolean isRun = true;
        while (isRun) {
            Commands.userCommands();

            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
                command = -1;
            }

            switch (command) {
                case LOGOUT:
                    isRun = false;
                    System.out.println("Հաջողություն");
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case GET_POST_BY_TITLE:
                    getPostByTitle();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;
                case ALL_POSTS:
                    allPosts();
                    break;
                default:
                    System.out.println("Wrong command");

            }
        }
    }

    private static void getPostByTitle() {
        if (POST_STORAGE.isEmpty()) {
            System.out.println("Post Storage is empty. Please add post first!");
            addPost();
        }

        System.out.println("Input the title for post");
        try {
            String tit = SCANNER.nextLine();
            POST_STORAGE.getPostByTitle(tit);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
            getPostByTitle();
        }

    }

    private static void allPosts() {

        POST_STORAGE.printAllPosts();
    }

    private static void postsByCategory() {
        System.out.println("Piease input category");
        String category = SCANNER.nextLine();
        POST_STORAGE.printPostsByCategory(category);

    }

    private static void searchPost() {
        System.out.println("Please input title for search");
        String tit = SCANNER.nextLine();
        try {
            if (tit.equals("title")) {
                System.out.println("Input post title");
                String titName = SCANNER.nextLine();
                System.out.println(POST_STORAGE.getPostByTitle(titName));
            } else if (tit.equals("keyword")) {
                System.out.println("Input keyword for search");
                String keyName = SCANNER.nextLine();
                POST_STORAGE.searchPostsByKeyword(keyName);
            }
        } catch (PostNotFoundException e) {
            System.out.println("No post with this title");
        }


    }

    private static void addPost() {
        try {
            System.out.println("Please input your post: title, text, category, createdDate");
            String addPostStr = SCANNER.nextLine();
            String[] addPost = addPostStr.split(",");

            Post post = new Post();
            post.setTitle(addPost[0]);
            post.setText(addPost[1]);
            post.setCategory(addPost[2]);
            post.setDate(new Date());
            post.setUser(constUser);
            POST_STORAGE.add(post);
            System.out.println(post);
            System.out.println("Thank you, model.Post was added");

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Incorrect value! please try again");
        }
    }

}


