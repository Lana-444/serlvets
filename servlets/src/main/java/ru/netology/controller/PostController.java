package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;
  private final Gson gson = new Gson();

  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    setResponseType(response);
    response.getWriter().print(gson.toJson(service.all()));
  }

  private void setResponseType(HttpServletResponse response) {
    response.setContentType(APPLICATION_JSON);
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    setResponseType(response);
    try {
      Post post = service.getById(id);
      response.getWriter().print(gson.toJson(post));
    } catch (NotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    setResponseType(response);
    Post post = gson.fromJson(body, Post.class);
    Post savedPost = service.save(post);
    response.getWriter().print(gson.toJson(savedPost));
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    setResponseType(response);
    try {
      service.removeById(id);
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    } catch (NotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
}
