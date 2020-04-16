import exception.PostNotFoundException;

public class PostStorageImpI {
    private Post[] posts = new Post[10];
    private int size = 0;

    public void addPost(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] temp = new Post[posts.length + 10];
        System.arraycopy(posts, 0, temp, 0, posts.length);
        posts = temp;

    }


    public Post getPostByTitle(String title) throws PostNotFoundException {

        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                return posts[i];
            }
        }
        throw new PostNotFoundException("Post with " + title + " already exists ") ;
    }


    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getText().contains(keyword) || posts[i].getTitle().contains(keyword)) {
                System.out.println(posts[i].getText());
            } else {
                System.out.println("Incorrect value! Please try again");
            }
        }
    }


    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }

    }


    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (category.equals(posts[i].getCategory())) {
                System.out.println(posts[i].getText());
            } else {
                System.out.println("Incorrect value! Please try again");
            }
        }

    }


}
