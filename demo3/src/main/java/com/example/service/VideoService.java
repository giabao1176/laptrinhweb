package com.example.service;

import com.example.dao.VideoDao;
import com.example.model.Video;
import java.util.List;

public class VideoService {
    private final VideoDao dao = new VideoDao();

    public List<Video> findAll() {
        return dao.findAll();
    }

    public Video findById(String videoId) {
        return dao.findById(videoId);
    }

    public List<Video> searchByTitle(String keyword) {
        return dao.searchByTitle(keyword == null ? "" : keyword.trim());
    }

    public List<Video> findByCategory(int categoryId) {
        return dao.findByCategory(categoryId);
    }

    public void save(Video video) {
        dao.save(video);
    }

    public void delete(String videoId) {
        dao.delete(videoId);
    }

    public void increaseViews(String videoId) {
        dao.increaseViews(videoId);
    }
}