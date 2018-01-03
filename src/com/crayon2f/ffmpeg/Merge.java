package com.crayon2f.ffmpeg;

import com.crayon2f.ffmpeg.base.Base;
import com.crayon2f.ffmpeg.kit.CommonKit;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Created by feiFan.gou on 2017/8/22 14:54.
 */
class Merge extends Base {


    @Test
    void test() {

//        audioJoin("D:\\application\\program\\ffmpeg-3.3.2-win64\\bin",
//                Format.audio_type.MP3,
//                "D:\\application\\program\\ffmpeg-3.3.2-win64\\bin\\test.mp3",
//                "D:\\application\\program\\ffmpeg-3.3.2-win64\\bin\\test.mp3");

        audioJoin("D:\\application\\program\\ffmpeg-3.3.2-win64\\bin",
                Format.audio_type.WAV,
                "D:\\application\\program\\ffmpeg-3.3.2-win64\\bin\\test.wav",
                "D:\\application\\program\\ffmpeg-3.3.2-win64\\bin\\test1.wav",
                "D:\\application\\program\\ffmpeg-3.3.2-win64\\bin\\test2.wav");
    }


    /**
     * 拼接音频
     *
     * @param outputPath 输入路径
     * @param audioType  输入音频类型
     * @param sources    合成音频的子音频
     */
    private void audioJoin(String outputPath, Format.audio_type audioType, String... sources) {


        if (!CommonKit.checkParam(outputPath) || null == sources || sources.length == 0) {
            throw new RuntimeException("参数不能为空");
        }
        if (!CommonKit.fileExist(outputPath)) {
            throw new RuntimeException("输出路径为空");
        }
        for (String source : sources) {
            if (!CommonKit.fileExist(source)) {
                throw new RuntimeException("[" + source + "]视频源不存在");
            }
        }
        outputPath += (File.separator + "audio_merge" + audioType.suffix);

        File oldFile = new File(outputPath);
        if (oldFile.exists()) {
            if (!oldFile.delete()) {
                throw new RuntimeException("删除文件失败");
            }
        }
        StringBuilder builder = new StringBuilder("-i \"concat:");
        for (int i = 0; i < sources.length; i++) {
            if (0 == i) {
                builder.append(sources[i]);
            } else {
                builder.append("|").append(sources[i]);
            }
        }
        builder.append("\"").append(" ").append("-acodec copy ").append(outputPath);
        executeFFMPeg(builder.toString());
    }


}
