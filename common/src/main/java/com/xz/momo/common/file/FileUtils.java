package com.xz.momo.common.file;

import android.os.Environment;

import com.xz.momo.common.log.RLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 【xinzhong】 on 2017/5/22.
 */

public class FileUtils {

    public static final String MAIN= Environment.getExternalStorageDirectory().getPath()+"/cenco/";
    public static final String LOTTORY_PATH=MAIN+"lottory/";
    public static final String LOTTORY_DB_PATH=LOTTORY_PATH+"db/";

    /**
     * 检查文件夹路径，不存在则创建
     * @param folderPath
     * @return
     */
    public static boolean confirmFolder(String folderPath){

        File dir = new File(folderPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return true;
    }

    /**
     * 将字符串写入到具体路径中
     * @param path
     * @param sb
     * @return
     * @throws Exception
     */
    public static String writeFile(String path, String sb) throws Exception {

        FileOutputStream fos = new FileOutputStream(path, true);
        fos.write(sb.getBytes());
        fos.flush();
        fos.close();

        return path;
    }


    public static void writeToPath(InputStream in , String outPath){
        try {
            OutputStream os = new FileOutputStream(outPath);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            in.close();
            os.close();
            RLog.d("writeToPath "+outPath);
        } catch (IOException e) {
            e.printStackTrace();
            RLog.e(e.getLocalizedMessage());
        }
    }

}
