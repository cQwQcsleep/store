package com.reandroid.json;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JSONPointer {
    private static final String ENCODING = "utf-8";
    private final List<String> refTokens;

    public JSONPointer(String str) {
        String strSubstring;
        if (str == null) {
            x0e.a("pointer cannot be null");
            throw null;
        }
        if (str.isEmpty() || str.equals("#")) {
            this.refTokens = Collections.EMPTY_LIST;
            return;
        }
        if (str.startsWith("#/")) {
            try {
                strSubstring = URLDecoder.decode(str.substring(2), "utf-8");
            } catch (UnsupportedEncodingException e) {
                rc6.a(e);
                throw null;
            }
        } else {
            if (!str.startsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                w01.a("a JSON pointer should start with '/' or '#/'");
                throw null;
            }
            strSubstring = str.substring(1);
        }
        this.refTokens = new ArrayList();
        int i = -1;
        while (true) {
            int i2 = i + 1;
            int iIndexOf = strSubstring.indexOf(47, i2);
            if (i2 == iIndexOf || i2 == strSubstring.length()) {
                this.refTokens.add("");
            } else if (iIndexOf >= 0) {
                this.refTokens.add(unescape(strSubstring.substring(i2, iIndexOf)));
            } else {
                this.refTokens.add(unescape(strSubstring.substring(i2)));
            }
            if (iIndexOf < 0) {
                return;
            } else {
                i = iIndexOf;
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static String escape(String str) {
        return str.replace("~", "~0").replace(PsuedoNames.PSEUDONAME_ROOT, "~1").replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static Object readByIndexToken(Object obj, String str) throws JSONPointerException {
        try {
            int i = Integer.parseInt(str);
            JSONArray jSONArray = (JSONArray) obj;
            if (i >= jSONArray.length()) {
                throw new JSONPointerException(String.format("index %s is out of bounds - the array has %d elements", str, Integer.valueOf(jSONArray.length())));
            }
            try {
                return jSONArray.get(i);
            } catch (JSONException e) {
                throw new JSONPointerException("Error reading value at index position " + i, e);
            }
        } catch (NumberFormatException e2) {
            throw new JSONPointerException(String.format("%s is not an array index", str), e2);
        }
    }

    private static String unescape(String str) {
        return str.replace("~1", PsuedoNames.PSEUDONAME_ROOT).replace("~0", "~").replace("\\\"", "\"").replace("\\\\", "\\");
    }

    public Object queryFrom(Object obj) throws JSONPointerException {
        if (this.refTokens.isEmpty()) {
            return obj;
        }
        for (String str : this.refTokens) {
            if (obj instanceof JSONObject) {
                obj = ((JSONObject) obj).opt(unescape(str));
            } else {
                if (!(obj instanceof JSONArray)) {
                    throw new JSONPointerException(String.format("value [%s] is not an array or object therefore its key %s cannot be resolved", obj, str));
                }
                obj = readByIndexToken(obj, str);
            }
        }
        return obj;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (String str : this.refTokens) {
            sb.append('/');
            sb.append(escape(str));
        }
        return sb.toString();
    }

    public String toURIFragment() {
        try {
            StringBuilder sb = new StringBuilder("#");
            for (String str : this.refTokens) {
                sb.append('/');
                sb.append(URLEncoder.encode(str, "utf-8"));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            rc6.a(e);
            return null;
        }
    }

    public static class Builder {
        private final List<String> refTokens = new ArrayList();

        public Builder append(String str) {
            if (str != null) {
                this.refTokens.add(str);
                return this;
            }
            x0e.a("token cannot be null");
            return null;
        }

        public JSONPointer build() {
            return new JSONPointer(this.refTokens);
        }

        public Builder append(int i) {
            this.refTokens.add(String.valueOf(i));
            return this;
        }
    }

    public JSONPointer(List<String> list) {
        this.refTokens = new ArrayList(list);
    }
}
