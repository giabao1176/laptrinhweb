package com.example.controller.admin;

import com.example.model.Category;
import com.example.model.Video;
import com.example.service.CategoryService;
import com.example.service.VideoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/admin/videos")
public class VideoController extends HttpServlet {
    private final VideoService videoService = new VideoService();
    private final CategoryService categoryService = new CategoryService();
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            String videoId = req.getParameter("id");
            Video video = videoService.findById(videoId);
            req.setAttribute("video", video);
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/video-form.jsp").forward(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            String videoId = req.getParameter("id");
            videoService.delete(videoId);
            resp.sendRedirect("videos");
            return;
        }

        if ("search".equals(action)) {
            String keyword = req.getParameter("keyword");
            req.setAttribute("videos", videoService.searchByTitle(keyword));
            req.setAttribute("keyword", keyword);
        } else {
            req.setAttribute("videos", videoService.findAll());
        }

        req.setAttribute("categories", categoryService.findAll());
        req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Video video = new Video();
        String videoId = req.getParameter("videoId");
        video.setVideoId(videoId);

        video.setTitle(req.getParameter("title"));
        video.setDescription(req.getParameter("description"));
        video.setActive("on".equals(req.getParameter("active")));

        // Category
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Category category = categoryService.findById(categoryId);
        video.setCategory(category);

        // Upload poster
        Part part = req.getPart("poster");
        if (part != null && part.getSize() > 0) {
            String fileName = UUID.randomUUID() + "_" + part.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            new File(uploadPath).mkdirs();
            part.write(uploadPath + File.separator + fileName);
            video.setPoster("/" + UPLOAD_DIR + "/" + fileName);
        }

        videoService.save(video); // dùng save()
        resp.sendRedirect("videos");
    }
}