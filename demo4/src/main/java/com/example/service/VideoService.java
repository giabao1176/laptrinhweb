package com.example.service;

import com.example.dao.VideoDao;
import com.example.model.Video;
import java.util.List;

public class VideoService {
    private VideoDao videoDao = new VideoDao();

    public List<Video> findAll() {
        return videoDao.findAll();
    }

    public Video findById(String id) {
        // Bạn cần thêm hàm findById vào VideoDao nếu chưa có
        // Ở đây tôi giả định code VideoDao bạn đã có hàm insert/findAll
        return null; 
    }

    public void insert(Video video) {
        videoDao.insert(video);
    }
    
    public List<Video> findByTitle(String keyword) {
        return videoDao.findByTitle(keyword);
    }
}