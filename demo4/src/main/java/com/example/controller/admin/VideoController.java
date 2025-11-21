package com.example.controller.admin;

import com.example.dao.VideoDao;
import com.example.model.Video;
import com.example.model.Category; // Giả sử có CategoryDao để load list category
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet(urlPatterns = {"/admin/video", "/admin/video/create", "/admin/video/search"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VideoDao videoDao = new VideoDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("search")) {
             req.getRequestDispatcher("/views/admin/video-search.jsp").forward(req, resp);
        } else {
             // List Videos
             List<Video> videos = videoDao.findAll();
             req.setAttribute("videos", videos);
             req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("create")) {
             createVideo(req, resp);
        } else if (url.contains("search")) {
             String keyword = req.getParameter("keyword");
             List<Video> videos = videoDao.findByTitle(keyword);
             req.setAttribute("videos", videos);
             req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
    }

    private void createVideo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Video video = new Video();
        video.setVideoId(req.getParameter("videoId"));
        video.setTitle(req.getParameter("title"));
        video.setDescription(req.getParameter("description"));
        video.setActive(true);
        
        // Xử lý Upload Ảnh
        Part part = req.getPart("poster");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            part.write(uploadPath + File.separator + fileName);
            video.setPoster(fileName);
        }

        // Giả sử set cứng Category hoặc lấy từ form
        // video.setCategory(...);

        videoDao.insert(video);
        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }
}