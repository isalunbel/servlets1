package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepositoryImpl implements PostRepository {
    private static final String POST_NOT_FOUND_MESSAGE = "Post with id %d not found";

    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong postIdCounter = new AtomicLong(0);

    @Override
    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = postIdCounter.incrementAndGet();
            post.setId(newId);
            posts.put(newId, post);
            return post;
        } else {
            if (posts.containsKey(post.getId())) {
                posts.put(post.getId(), post);
                return post;
            } else {
                throw new IllegalArgumentException(String.format(POST_NOT_FOUND_MESSAGE, post.getId()));
            }
        }
    }

    @Override
    public void removeById(long id) {
        posts.remove(id);
    }
}
