package org.example;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepositoryImpl implements PostRepository {
    private final List<Post> posts = new CopyOnWriteArrayList<>();
    private final AtomicLong postIdCounter = new AtomicLong(0);

    @Override
    public List<Post> all() {
        return posts;
    }

    @Override
    public Optional<Post> getById(long id) {
        return posts.stream().filter(post -> post.getId() == id).findFirst();
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = postIdCounter.incrementAndGet();
            post.setId(newId);
            posts.add(post);
            return post;
        } else {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).getId() == post.getId()) {
                    posts.set(i, post);
                    return post;
                }
            }
            throw new IllegalArgumentException("Post with id " + post.getId() + " not found");
        }
    }

    @Override
    public void removeById(long id) {
        posts.removeIf(post -> post.getId() == id);
    }
}