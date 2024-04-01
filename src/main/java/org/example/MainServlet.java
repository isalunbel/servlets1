package org.example;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private final PostController controller;

    public MainServlet() {
        final PostRepository repository = new PostRepository();
        final PostService service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String path = req.getRequestURI();
            final String method = req.getMethod();

            // Примитивный маршрутизатор
            if (method.equals("GET") && path.equals("/api/posts")) {
                handleGetAllPosts(req, resp);
            } else if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
                handleGetPostById(req, resp);
            } else if (method.equals("POST") && path.equals("/api/posts")) {
                handleCreatePost(req, resp);
            } else if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
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
        // Обработка GET запроса для получения всех постов
    }

    private void handleGetPostById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Обработка GET запроса для получения поста по id
    }

    private void handleCreatePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Обработка POST запроса для создания нового поста
    }

    private void handleDeletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Обработка DELETE запроса для удаления поста по id
    }
}