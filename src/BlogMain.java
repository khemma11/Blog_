import exception.PostNotFoundException;

import java.util.Date;
import java.util.Scanner;

public class BlogMain  implements Commands {


    static PostStorageImpI postSt = new PostStorageImpI();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            String commandStr = scanner.nextLine();
            int command;
            try {
                command = Integer.parseInt(commandStr);
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
                command = -1;
            }

            switch (command) {
                case EXIT:
                    isRun = false;
                    System.out.println("Հաջողություն");
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;
                case ALL_POSTS:
                    allPosts();
                    break;
                case GET_POST_BY_TITLE:
                    getPostByTitle();
                    break;
                default:
                    System.out.println("Wrong command");

            }
        }
    }

    private static void getPostByTitle() {
        System.out.println("Input the title for post");
        try {
            String tit = scanner.nextLine();
            postSt.getPostByTitle(tit);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void allPosts() {

        postSt.printAllPosts();
    }

    private static void postsByCategory() {
        System.out.println("Piease import category");
        String category = scanner.nextLine();
        postSt.printPostsByCategory(category);

    }

    private static void searchPost() {
        System.out.println("Please import title for search");
        String tit = scanner.nextLine();
        try {
            if (tit.equals("title")) {
                System.out.println("Import post title");
                String titName = scanner.nextLine();
                System.out.println(postSt.getPostByTitle(titName));
            } else if (tit.equals("keyword")) {
                System.out.println("Import keyword for search");
                String keyName = scanner.nextLine();
                postSt.searchPostsByKeyword(keyName);
            }
        } catch (PostNotFoundException e) {
            System.out.println("No post with this title");
        }


    }

    private static void addPost() {
        try {
            System.out.println("Please input your post: title, text, category, createdDate");
            String addPostStr = scanner.nextLine();
            String[] addPost = addPostStr.split(",");

            Post post = new Post();
            post.setTitle(addPost[0]);
            post.setText(addPost[1]);
            post.setCategory(addPost[2]);
            post.setDate(new Date());
            postSt.addPost(post);
            System.out.println(post);
            System.out.println("Thank you, Post was added");

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Incorrect value! please try again");
        }
    }

    private static void printCommands() {
        System.out.println("Input " + EXIT + " for EXIT");
        System.out.println("Input " + ADD_POST + " for ADD_POST");
        System.out.println("Input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Input " + POSTS_BY_CATEGORY + " for POSTS_BY_CATEGORY");
        System.out.println("Input " + ALL_POSTS + " for ALL_POSTS");
        System.out.println("Input " + GET_POST_BY_TITLE + " GET_POST_BY_TITLE");
    }
}

