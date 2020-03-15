package com.yshen.studyspringbootvideo.demo998.service.impl;

import com.yshen.studyspringbootvideo.demo998.mapper.DownloadMapper;
import com.yshen.studyspringbootvideo.demo998.model.Download;
import com.yshen.studyspringbootvideo.demo998.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郑悦恺
 * @Classname DwonloadServiceImpl
 * @Description TODO
 * @Date 2020/3/14 21:53
 */


@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    DownloadMapper downloadMapper;

    @Override
    public Download getOne() {

//        Download download = downloadMapper.selectMax();
        Download download = downloadMapper.selectByPrimaryKey(4);
        return download;
    }
}
