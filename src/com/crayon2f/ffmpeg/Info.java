package com.crayon2f.ffmpeg;

import com.crayon2f.ffmpeg.base.Base;
import org.junit.jupiter.api.Test;

/**
 * Created by feiFan.gou on 2017/8/18 14:09.
 */
public class Info extends Base {

    @Test
    public void test() {

        size("C:\\Users\\feifan.gou\\Desktop\\download\\JTSJ0090.mp4");
    }

    public void size(String sourcePath) {

        String command= "-i " + sourcePath + " -show_streams 2>&1 | grep \"width\\|height\\|rotation\" | grep -v coded | awk -F\"=\" '{a[$1]=$2}END{if(length(a[\"rotation\"])>0){print a[\"height\"]\"x\"a[\"width\"]} else {print a[\"width\"]\"x\"a[\"height\"]}}'";
//        String command= "-i " + sourcePath ;

        executeFFMPeg(command);
    }
}
