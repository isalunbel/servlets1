package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private PostController controller;

    private static final String API_POSTS_PATH = "/api/posts";
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String DELETE_METHOD = "DELETE";

    @Override
    public void init() throws ServletException {
        super.init();
        this.controller = new PostController(new PostService(new PostRepositoryImpl()));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String path = req.getRequestURI();
            final String method = req.getMethod();

            // Примитивный маршрутизатор
            if (method.equals(GET_METHOD) && path.equals(API_POSTS_PATH)) {
                handleGetAllPosts(req, resp);
            } else if (method.equals(GET_METHOD) && path.matches(API_POSTS_PATH + "/\\d+")) {
                handleGetPostById(req, resp);
            } else if (method.equals(POST_METHOD) && path.equals(API_POSTS_PATH)) {
                handleCreatePost(req, resp);
            } else if (method.equals(DELETE_METHOD) && path.matches(API_POSTS_PATH + "/\\d+")) {
                handleDeletePost(req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void handleGetAllPosts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.getAllPosts(req, resp);
    }

    private void handleGetPostById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.getPostById(req, resp);
    }

    private void handleCreatePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.createPost(req, resp);
    }

    private void handleDeletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.deletePost(req, resp);
    }
}
