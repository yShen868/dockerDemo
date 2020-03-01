package com.yshen.studyspringbootvideo.demo998.util;

import lombok.Data;
import org.thymeleaf.util.StringUtils;

/**
 * @author 郑悦恺
 * @Classname RangeVo
 * @Description TODO
 * @Date 2020/2/28 20:10
 */

@Data
public class RangeVo {
    long rangeStart;
    long rangeEnd;
    long contentLength;
    long fileLength;
    String contentRange;


    public RangeVo(long rangeStart, long rangeEnd, long contentLength, long fileLength) {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        this.contentLength = contentLength;
        this.fileLength = fileLength;
        this.contentRange = "bytes " +this.rangeStart+"-"+this.rangeEnd+"/"+fileLength;


    }

    public RangeVo(long fileLength,String range) {


        this(0,fileLength-1,fileLength,fileLength);

//        System.out.println(fileLength);
//        System.out.println(range);

        if (!StringUtils.isEmpty(range)) {
            if (range.startsWith("-")) {

                this.contentLength = Long.parseLong(range.replace("-",""));
                this.rangeStart = fileLength - contentLength;
                this.rangeEnd = fileLength -1;
            }else if (range.endsWith("-")){
                this.rangeStart = Long.parseLong(range.replace("-",""));

                this.rangeEnd = fileLength-1;
                this.contentLength = rangeEnd - rangeStart +1;

            }else {
                String[] strings = range.split("-");
                this.rangeStart= Long.parseLong(strings[0]);
                this.rangeEnd= Long.parseLong(strings[0]);
                this.contentLength = rangeStart-rangeEnd+1;

            }

            this.fileLength = fileLength;
            this.contentRange = "bytes " +this.rangeStart+"-"+this.rangeEnd+"/"+fileLength;
//            System.out.println("---------------");
//            System.out.println(fileLength);
//            System.out.println(rangeStart);
//            System.out.println(rangeEnd);
//            System.out.println(contentRange);
//            System.out.println("------------------");





        }



    }
}
