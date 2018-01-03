package com.crayon2f.ffmpeg;

import com.crayon2f.ffmpeg.base.Base;
import com.crayon2f.ffmpeg.kit.CommonKit;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by feiFan.gou on 2017/8/17 14:39.
 */
public class Cut extends Base {

    ///usr/local/ffmpeg/bin/ffmpeg -i d.mp4 -vcodec copy -acodec copy -ss 00:00:04 -to 00:00:19 E.mp4 -y -v quiet

    @Test
    public void testCut() throws IOException {

        File file = new File("D:\\downloads\\video\\JTSJ0090.mp4");
        System.out.println(file.getParentFile().getName());
//        cutWithStartAndEnd("C:\\Users\\feifan.gou\\Desktop\\download\\orig-2.mp4", "C:\\Users\\feifan.gou\\Desktop\\download", "00:00:05", "00:00:19");
//        cutWithDuration("C:\\Users\\feifan.gou\\Desktop\\download\\orig-4.mp4", "C:\\Users\\feifan.gou\\Desktop\\download", "00:00:07", "15");

    }

    /**
     * 根据开始时间,结束时间截取
     * @param sourcePath 原视频
     * @param outputPath 输出视频，输出视频的目录必须存在！！！！
     * @param start 开始截取时间
     * @param end 结束时间
     */
    public void cutWithStartAndEnd(String sourcePath, String outputPath, String start, String end) {

        if (!CommonKit.checkParam(sourcePath, outputPath, start, end)) {
            throw new RuntimeException("参数不能为空");
        }
        if (!CommonKit.fileExist(sourcePath)) {
            throw new RuntimeException("原视频不存在");
        }
        if (!CommonKit.fileExist(outputPath)) {
            throw new RuntimeException("输出路径不存在");
        }
        if (!CommonKit.checkTime(start)) {
            throw new RuntimeException("开始时间格式有误");
        }
        if (!CommonKit.checkTime(end)) {
            throw new RuntimeException("结束时间格式有误");
        }

        outputPath += (File.separator + "cut_with_start_end.mp4");
//        String command = String.format("-i %s -vcodec copy -acodec copy -ss %s -to %s %s -y -v quiet", sourcePath, start, end, outputPath);
        String command = String.format("-i %s -codec copy -acodec copy -ss %s -to %s %s -y -v quiet", sourcePath, start, end, outputPath);
        executeFFMPeg(command);
    }

    /**
     * 根据开始时间,时长截取
     * @param sourcePath 原视频路径
     * @param outputPath 输出视频，输出视频的目录必须存在！！！！
     * @param start 开始截取时间
     * @param duration 时长
     */
    public void cutWithDuration(String sourcePath, String outputPath, String start, String duration) {

        if (!CommonKit.checkParam(sourcePath, outputPath, start, duration)) {
            throw new RuntimeException("参数不能为空");
        }
        if (!CommonKit.fileExist(sourcePath)) {
            throw new RuntimeException("原视频不存在");
        }
        if (!CommonKit.fileExist(outputPath)) {
            throw new RuntimeException("输出路径不存在");
        }
        if (!CommonKit.checkTime(start)) {
            throw new RuntimeException("开始时间格式有误");
        }
        if (!CommonKit.checkNumber(duration)) {
            throw new RuntimeException("时长有误");
        }

        outputPath += (File.separator + "cut_with_duration.mp4");
        //ffmpeg -i JTSJ0090.mp4 -codec copy -acodec copy -ss 4 -t 15 b2.mp4 -y -v quiet
//        String command = String.format("-ss 4 -t %s -accurate_seek -i %s -vcodec copy -acodec copy %s -y -v quiet", duration, sourcePath, outputPath);
        String command = String.format("-i %s -codec copy -ss %s -t %s %s -y -v quiet", sourcePath, start, duration, outputPath);
        executeFFMPeg(command);


    }


}
