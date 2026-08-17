package javax.xml.catalog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import kotlin.UByte;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class Normalizer {
    public static String decodeURN(String str) {
        if (str != null && str.startsWith("urn:publicid:")) {
            str = str.substring(13);
            try {
                return URLDecoder.decode(str.replace(":", "//").replace(";", "::"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                CatalogMessages.reportRunTimeError(CatalogMessages.ERR_OTHER, e);
            }
        }
        return str;
    }

    public static String encodeURN(String str) {
        String strNormalizePublicId = normalizePublicId(str);
        try {
            strNormalizePublicId = URLEncoder.encode(strNormalizePublicId, "UTF-8").replace("::", ";").replace("//", ":");
        } catch (UnsupportedEncodingException e) {
            CatalogMessages.reportRunTimeError(CatalogMessages.ERR_OTHER, e);
        }
        return "urn:publicid:" + strNormalizePublicId;
    }

    public static String normalizePublicId(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str.length());
        char c = 'a';
        for (char c2 : str.toCharArray()) {
            if (c2 != ' ' || (sb.length() != 0 && c != ' ')) {
                if (c2 != '\t' && c2 != '\r' && c2 != '\n') {
                    sb.append(c2);
                    c = c2;
                } else if (c != ' ') {
                    sb.append(' ');
                    c = ' ';
                }
            }
        }
        if (c == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String normalizeURI(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        try {
            byte[] bytes = strTrim.getBytes("UTF-8");
            StringBuilder sb = new StringBuilder(bytes.length);
            for (byte b : bytes) {
                int i = b & UByte.MAX_VALUE;
                if (i <= 32 || i > 127 || i == 34 || i == 60 || i == 62 || i == 92 || i == 94 || i == 96 || i == 123 || i == 124 || i == 125 || i == 127) {
                    sb.append("%");
                    sb.append(String.format("%02X", Integer.valueOf(i)));
                } else {
                    sb.append((char) b);
                }
            }
            return sb.toString().trim();
        } catch (UnsupportedEncodingException unused) {
            return strTrim;
        }
    }
}
