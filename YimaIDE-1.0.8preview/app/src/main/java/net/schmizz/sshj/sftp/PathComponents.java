package net.schmizz.sshj.sftp;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PathComponents {
    private final String name;
    private final String parent;
    private final String path;

    public PathComponents(String str, String str2, String str3) {
        this.parent = str;
        this.name = str2;
        String strAdjustForParent = adjustForParent(str, str2, str3);
        this.path = str3.equals(strAdjustForParent) ? strAdjustForParent : trimTrailingSeparator(strAdjustForParent, str3);
    }

    public static String adjustForParent(String str, String str2, String str3) {
        if (!str2.startsWith(str3)) {
            if (str.endsWith(str3)) {
                return str.concat(str2);
            }
            if (!str.isEmpty()) {
                return str + str3 + str2;
            }
        }
        return str2;
    }

    public static String trimTrailingSeparator(String str, String str2) {
        return str.endsWith(str2) ? str.substring(0, str.length() - str2.length()) : str;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof PathComponents) && this.path.equals(((PathComponents) obj).path);
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public String getParent() {
        return this.parent;
    }

    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public String toString() {
        return "[parent=" + this.parent + "; name=" + this.name + "; path=" + this.path + "]";
    }
}
