package com.reandroid.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JsonUtil {
    public static byte[] parseBase64(String str) {
        if (str == null || !str.startsWith(JSONItem.MIME_BIN_BASE64)) {
            return null;
        }
        try {
            return Base64.getUrlDecoder().decode(str.substring(32));
        } catch (Throwable th) {
            throw new JSONException(th);
        }
    }

    public static void readJSONArray(Reader reader, JSONConvert<JSONArray> jSONConvert) {
        jSONConvert.fromJson(new JSONArray(new JSONTokener(reader)));
    }

    public static void readJSONObject(Reader reader, JSONConvert<JSONObject> jSONConvert) {
        jSONConvert.fromJson(new JSONObject(new JSONTokener(reader)));
    }

    public static void readJSONArray(InputStream inputStream, JSONConvert<JSONArray> jSONConvert) {
        readJSONArray(new InputStreamReader(inputStream, StandardCharsets.UTF_8), jSONConvert);
    }

    public static void readJSONObject(InputStream inputStream, JSONConvert<JSONObject> jSONConvert) {
        readJSONObject(new InputStreamReader(inputStream, StandardCharsets.UTF_8), jSONConvert);
    }

    public static void readJSONArray(File file, JSONConvert<JSONArray> jSONConvert) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        readJSONArray(fileInputStream, jSONConvert);
        fileInputStream.close();
    }

    public static void readJSONObject(File file, JSONConvert<JSONObject> jSONConvert) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        readJSONObject(fileInputStream, jSONConvert);
        fileInputStream.close();
    }
}
