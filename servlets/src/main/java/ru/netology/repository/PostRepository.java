package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
  private final HashMap<Long, Post> posts = new HashMap<>();
  private long idCounter = 0;

  public synchronized List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public synchronized Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public synchronized Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(++idCounter);
    }
    posts.put(post.getId(), post);
    return post;
  }

  public synchronized boolean removeById(long id) {
    return posts.remove(id) != null;

  }
}