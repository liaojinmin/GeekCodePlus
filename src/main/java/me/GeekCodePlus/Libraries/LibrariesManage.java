package me.GeekCodePlus.Libraries;


import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.data.DataBaseManage;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public final class LibrariesManage {

    final String httpUrl = "https://web-1301331373.cos.ap-guangzhou.myqcloud.com/";
    final File LibrariesPath = new File(System.getProperty("user.dir"),"GeekLibs");
    final File HikariCP = new File(LibrariesPath, "HikariCP-4.0.3-Revise.jar");

    public LibrariesManage() {
        if (isExists()) {
            new DataBaseManage();
            return;
        }
//        if (!LibrariesPath.exists()) LibrariesPath.mkdirs();
//        if (!HikariCP.exists() || HikariCP.length() <= 100) {
//            HikariCP.delete();
//            GeekCodeMain.say("§e依赖库不存在，开始自动下载.");
//            Download("HikariCP-4.0.3-Revise.jar");
//        }
        loadJarPath(LibrariesPath);
        new DataBaseManage();
    }


    public static boolean isServer(String s) {
        switch (s) {
            case "1.18.2":
            case "1.18.1":
            case "1.18":
            case "1.17.1":
            case "1.17":
            case "1.16.5":
            case "1.16.4":
            case "1.16.3":
            case "1.16.2":
            case "1.16.1":
            case "1.16":
                return true;
        }
        return false;
    }

    public void Download(String jarName) {
        try {
            PostWeb(jarName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void loadJarPath(File path) {
        List<File> list = new ArrayList<>();
        ForFile(path, list);
        for (File f : list) {
            GeekCodeMain.ClassLoader.addPath(f, LibrariesManage.class.getClassLoader());
        }
    }
    private void ForFile(File f, List<File> list) {
        //如果传入的是目录则获取里面的文件
        if (f.isDirectory()) {
            //获取目录文件数量
            File[] amt = f.listFiles();
            //遍历目录内，获取文件
            for (File tmp : amt) {
                ForFile(tmp, list);
            }
            //传入的不是目录则往传入的合集中添加完整路径
        } else {
            //文件的后戳是 .jar
            if (f.getAbsolutePath().endsWith(".jar")) {
                list.add(f);
            }
        }
    }


    private boolean isExists() {
        try {
            Class.forName("com.GeekLib.perm.zaxxer.hikari.HikariDataSource");
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    private void PostWeb(String jarName) throws Throwable{
        FileOutputStream savePath = new FileOutputStream(LibrariesPath.getCanonicalPath() + File.separator + jarName);
        URLConnection conn = new URL(httpUrl + jarName).openConnection();
        conn.setReadTimeout(300000);
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        GeekCodeMain.say("§8下载库: §7" + jarName);
        try {
            InputStream i = conn.getInputStream();
            savePath.write(readInputStream(i));
        } catch (FileNotFoundException e) {
            GeekCodeMain.say("§c扩展库: §7" + jarName + "§c下载失败，请检查你的网络链接");
             e.printStackTrace();
        }
    }
    private byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
