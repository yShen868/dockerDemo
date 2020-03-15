package com.yshen.studyspringbootvideo.demo998.mapper;

import com.yshen.studyspringbootvideo.demo998.model.Download;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DownloadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Download record);

    Download selectByPrimaryKey(Integer id);

    List<Download> selectAll();

    int updateByPrimaryKey(Download record);

    Download selectMax();
}