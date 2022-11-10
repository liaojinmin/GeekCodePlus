package me.GeekCodePlus.Libraries;


public final class LibrariesManage {
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
}
