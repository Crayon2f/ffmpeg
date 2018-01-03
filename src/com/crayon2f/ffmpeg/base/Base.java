package com.crayon2f.ffmpeg.base;

import java.io.*;

/**
 * Created by feiFan.gou on 2017/8/17 14:40.
 */
public class Base {

    private String ffmpeg_path = "E:\\workspace\\idea-local\\ffmpeg\\bin\\ffmpeg.exe ";
    private String ffprobe_path = "E:\\workspace\\idea-local\\ffmpeg\\bin\\ffprobe.exe ";

    protected Runtime runtime = Runtime.getRuntime();

    protected void executeFFMPeg(String command) {

        execute(command, ffmpeg_path);

    }

    protected void executeFFProbe(String command) {

        execute(command, ffprobe_path);
    }

    private void execute(String command, String exePath) {

        try {
            String cmd = exePath + command + " -y -v quiet";
            System.out.println(cmd);
            Process process  = runtime.exec(cmd);
            BufferedInputStream bis = new BufferedInputStream(process.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            String lineString, result = "";
            while (null != (lineString = br.readLine())) {
                result = lineString;
            }
            System.out.println("result : " + result);
            bis.close();
            br.close();
            System.out.println(" ----------------------- execute success ----------------------- ");
        } catch (IOException e) {
            System.out.println(" ----------------------- execute error ------------------------- ");
            e.printStackTrace();
        }
    }
}
