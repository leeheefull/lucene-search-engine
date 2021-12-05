package domain.dto;

import domain.entity.Post;

import java.util.List;

public class TotalPost {
    private final List<Post> posts;

    public TotalPost(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "AllPosts{" +
                "posts=" + posts +
                '}';
    }
}
