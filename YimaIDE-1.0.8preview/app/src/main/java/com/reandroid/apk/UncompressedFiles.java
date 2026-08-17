package com.reandroid.apk;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class UncompressedFiles implements JSONConvert<JSONObject> {
    public static String[] COMMON_EXTENSIONS = {".png", ".jpg", ".mp3", ".mp4", ".wav", ".webp"};
    public static final String JSON_FILE = "uncompressed-files.json";
    public static final String NAME_extensions = "extensions";
    public static final String NAME_paths = "paths";
    private String mResRawDir;
    private final Set<String> mPathList = new HashSet();
    private final Set<String> mExtensionList = new HashSet();

    private static String getExtension(String str) {
        String strSanitizePath = sanitizePath(str);
        if (strSanitizePath == null) {
            return null;
        }
        int iLastIndexOf = strSanitizePath.lastIndexOf(47);
        if (iLastIndexOf > 0) {
            strSanitizePath = strSanitizePath.substring(iLastIndexOf + 1);
        }
        int iLastIndexOf2 = strSanitizePath.lastIndexOf(46);
        if (iLastIndexOf2 > 0) {
            return strSanitizePath.substring(iLastIndexOf2);
        }
        return null;
    }

    private boolean isResRawDir(String str) {
        String str2 = this.mResRawDir;
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        return str.startsWith(str2);
    }

    private static String sanitizePath(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String strTrim = str.replace(File.separatorChar, '/').trim();
        while (strTrim.startsWith("/")) {
            strTrim = strTrim.substring(1);
        }
        if (strTrim.length() == 0) {
            return null;
        }
        return strTrim;
    }

    public void addCommonExtensions() {
        for (String str : COMMON_EXTENSIONS) {
            addExtension(str);
        }
    }

    public void addExtension(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        this.mExtensionList.add(str);
    }

    public void addPath(ZipEntryMap zipEntryMap) {
        for (InputSource inputSource : zipEntryMap.toArray()) {
            addPath(inputSource);
        }
    }

    public void apply(InputSource inputSource) {
        inputSource.setUncompressed(isUncompressed(inputSource.getAlias()) || isUncompressed(inputSource.getName()));
    }

    public void clearExtensions() {
        this.mExtensionList.clear();
    }

    public void clearPaths() {
        this.mPathList.clear();
    }

    public boolean containsExtension(String str) {
        if (str == null) {
            return false;
        }
        if (this.mExtensionList.contains(str)) {
            return true;
        }
        boolean zStartsWith = str.startsWith(".");
        Set<String> set = this.mExtensionList;
        return !zStartsWith ? set.contains(".".concat(str)) : set.contains(str.substring(1));
    }

    public boolean containsPath(String str) {
        String strSanitizePath = sanitizePath(str);
        if (strSanitizePath == null) {
            return false;
        }
        return this.mPathList.contains(strSanitizePath);
    }

    public void fromJson(JSONObject jSONObject) {
        clearExtensions();
        clearPaths();
        if (jSONObject == null) {
            return;
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(NAME_extensions);
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                addExtension(jSONArrayOptJSONArray.getString(i));
            }
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(NAME_paths);
        if (jSONArrayOptJSONArray2 != null) {
            int length2 = jSONArrayOptJSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                addPath(jSONArrayOptJSONArray2.getString(i2));
            }
        }
    }

    public boolean isUncompressed(String str) {
        if (str == null) {
            return false;
        }
        if (containsPath(str) || containsExtension(str) || isResRawDir(str)) {
            return true;
        }
        return containsExtension(getExtension(str));
    }

    public void merge(UncompressedFiles uncompressedFiles) {
        if (uncompressedFiles == null || uncompressedFiles == this) {
            return;
        }
        Iterator<String> it = uncompressedFiles.mPathList.iterator();
        while (it.hasNext()) {
            addPath(it.next());
        }
        Iterator<String> it2 = uncompressedFiles.mExtensionList.iterator();
        while (it2.hasNext()) {
            addExtension(it2.next());
        }
    }

    public void removePath(String str) {
        String strSanitizePath = sanitizePath(str);
        if (strSanitizePath == null) {
            return;
        }
        this.mPathList.remove(strSanitizePath);
    }

    public void replacePath(String str, String str2) {
        String strSanitizePath = sanitizePath(str);
        String strSanitizePath2 = sanitizePath(str2);
        if (strSanitizePath == null || strSanitizePath2 == null || !this.mPathList.contains(strSanitizePath)) {
            return;
        }
        this.mPathList.remove(strSanitizePath);
        this.mPathList.add(strSanitizePath2);
    }

    public void setResRawDir(String str) {
        this.mResRawDir = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray(this.mExtensionList);
        Comparator comparator = CompareUtil.STRING_COMPARATOR;
        jSONArray.sort(comparator);
        jSONObject.put(NAME_extensions, jSONArray);
        JSONArray jSONArray2 = new JSONArray(this.mPathList);
        jSONArray2.sort(comparator);
        jSONObject.put(NAME_paths, jSONArray2);
        return jSONObject;
    }

    public void addPath(InputSource inputSource) {
        if (inputSource.isUncompressed()) {
            addPath(inputSource.getAlias());
        }
    }

    public void addPath(String str) {
        String strSanitizePath = sanitizePath(str);
        if (strSanitizePath == null) {
            return;
        }
        this.mPathList.add(strSanitizePath);
    }

    public void apply(ZipEntryMap zipEntryMap) {
        for (InputSource inputSource : zipEntryMap.toArray()) {
            apply(inputSource);
        }
    }

    public void fromJson(File file) throws IOException {
        if (file.isFile()) {
            fromJson(new JSONObject(new FileInputStream(file)));
        }
    }
}
