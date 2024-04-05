package org.example;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> all();
    Optional<Post> getById(long id);
    Post save(Post post);
    void removeById(long id);

    static final String ALL_METHOD_NAME = "all";
    static final String GET_BY_ID_METHOD_NAME = "getById";
    static final String SAVE_METHOD_NAME = "save";
    static final String REMOVE_BY_ID_METHOD_NAME = "removeById";
}
