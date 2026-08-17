package net.schmizz.sshj.sftp;

import java.io.IOException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PathHelper {
    public static final String DEFAULT_PATH_SEPARATOR = "/";
    private final Canonicalizer canonicalizer;
    private String dotDir;
    private final String pathSep;

    public interface Canonicalizer {
        String canonicalize(String str) throws IOException;
    }

    public PathHelper(Canonicalizer canonicalizer, String str) {
        this.canonicalizer = canonicalizer;
        this.pathSep = str;
    }

    private synchronized String getDotDir() throws IOException {
        String strCanonicalize;
        strCanonicalize = this.dotDir;
        if (strCanonicalize == null) {
            strCanonicalize = this.canonicalizer.canonicalize(".");
            this.dotDir = strCanonicalize;
        }
        return strCanonicalize;
    }

    public String adjustForParent(String str, String str2) {
        return PathComponents.adjustForParent(str, str2, this.pathSep);
    }

    public PathComponents getComponents(String str) throws IOException {
        boolean zEquals = str.equals(this.pathSep);
        String strSubstring = DEFAULT_PATH_SEPARATOR;
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (zEquals) {
            return getComponents(HttpUrl.FRAGMENT_ENCODE_SET, DEFAULT_PATH_SEPARATOR);
        }
        if (!str.isEmpty() && !".".equals(str)) {
            if (!("." + this.pathSep).equals(str)) {
                String strTrimTrailingSeparator = trimTrailingSeparator(str);
                int iLastIndexOf = strTrimTrailingSeparator.lastIndexOf(this.pathSep);
                if (iLastIndexOf != -1) {
                    if (iLastIndexOf != 0) {
                        strSubstring = strTrimTrailingSeparator.substring(0, iLastIndexOf);
                    }
                    strTrimTrailingSeparator = strTrimTrailingSeparator.substring(iLastIndexOf + this.pathSep.length());
                    str2 = strSubstring;
                }
                return (".".equals(strTrimTrailingSeparator) || "..".equals(strTrimTrailingSeparator)) ? getComponents(this.canonicalizer.canonicalize(str)) : getComponents(str2, strTrimTrailingSeparator);
            }
        }
        return getComponents(getDotDir());
    }

    public String getPathSeparator() {
        return this.pathSep;
    }

    public String trimTrailingSeparator(String str) {
        return PathComponents.trimTrailingSeparator(str, this.pathSep);
    }

    public PathComponents getComponents(String str, String str2) {
        return new PathComponents(str, str2, this.pathSep);
    }
}
