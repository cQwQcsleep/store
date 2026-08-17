package com.reandroid.json;

import com.intellij.psi.PsiKeyword;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class JSONItem {
    private static final int INDENT_FACTOR = 2;
    public static final String MIME_BIN_BASE64 = "data:binary/octet-stream;base64,";
    public static final Object NULL = new Null();

    public static final class Null {
        private Null() {
        }

        public final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return PsiKeyword.NULL;
        }
    }

    public static void indent(Writer writer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    public static boolean isNull(Object obj) {
        return obj == null || obj == NULL;
    }

    public static String numberToString(Number number) throws JSONException {
        return number.toString();
    }

    /* JADX WARN: Code duplicated, block: B:38:0x007f  */
    public static Writer quote(String str, Writer writer) throws IOException {
        if (str == null || str.isEmpty()) {
            writer.write("\"\"");
            return writer;
        }
        int length = str.length();
        writer.write(34);
        int i = 0;
        char c = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\f') {
                writer.write("\\f");
            } else if (cCharAt == '\r') {
                writer.write("\\r");
            } else if (cCharAt == '\"') {
                writer.write(92);
                writer.write(cCharAt);
            } else if (cCharAt == '/') {
                if (c == '<') {
                    writer.write(92);
                }
                writer.write(cCharAt);
            } else if (cCharAt != '\\') {
                switch (cCharAt) {
                    case '\b':
                        writer.write("\\b");
                        break;
                    case '\t':
                        writer.write("\\t");
                        break;
                    case '\n':
                        writer.write("\\n");
                        break;
                    default:
                        if (cCharAt >= ' ' && ((cCharAt < 128 || cCharAt >= 160) && (cCharAt < 8192 || cCharAt >= 8448))) {
                            writer.write(cCharAt);
                        } else {
                            writer.write("\\u");
                            String hexString = Integer.toHexString(cCharAt);
                            writer.write("0000", 0, 4 - hexString.length());
                            writer.write(hexString);
                        }
                        break;
                }
            } else {
                writer.write(92);
                writer.write(cCharAt);
            }
            i++;
            c = cCharAt;
        }
        writer.write(34);
        return writer;
    }

    public static void testValidity(Object obj) throws JSONException {
        if (obj != null) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                if (d.isInfinite() || d.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
                return;
            }
            if (obj instanceof Float) {
                Float f = (Float) obj;
                if (f.isInfinite() || f.isNaN()) {
                    throw new JSONException("JSON does not allow non-finite numbers.");
                }
            }
        }
    }

    public static String valueToString(Object obj) throws JSONException {
        return JSONWriter.valueToString(obj);
    }

    public static Object wrap(Object obj) {
        try {
            if (obj == null) {
                return NULL;
            }
            if ((obj instanceof JSONObject) || (obj instanceof JSONArray) || NULL.equals(obj) || (obj instanceof JSONString) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof BigInteger) || (obj instanceof BigDecimal) || (obj instanceof Enum)) {
                return obj;
            }
            if (obj instanceof Collection) {
                return new JSONArray((Collection<?>) obj);
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj);
            }
            if (obj instanceof Map) {
                return new JSONObject((Map<?, ?>) obj);
            }
            Package r0 = obj.getClass().getPackage();
            String name = r0 != null ? r0.getName() : "";
            if (!name.startsWith("java.") && !name.startsWith("javax.") && obj.getClass().getClassLoader() != null) {
                return new JSONObject(obj);
            }
            return obj.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    private static void writeBase64(Writer writer, byte[] bArr) throws IOException {
        writer.write("\"");
        writer.write(MIME_BIN_BASE64);
        try {
            writer.write(Base64.getUrlEncoder().encodeToString(bArr));
            writer.write("\"");
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            throw new JSONException(th);
        }
    }

    public static void writeValue(Writer writer, Object obj, int i, int i2) throws JSONException, IOException {
        if (isNull(obj)) {
            writer.write(PsiKeyword.NULL);
            return;
        }
        if (obj instanceof JSONString) {
            try {
                String jSONString = ((JSONString) obj).toJSONString();
                writer.write(jSONString != null ? jSONString.toString() : quote(obj.toString()));
                return;
            } catch (Exception e) {
                throw new JSONException(e);
            }
        }
        if (obj instanceof Number) {
            writer.write(numberToString((Number) obj));
            return;
        }
        if (obj instanceof Boolean) {
            writer.write(obj.toString());
            return;
        }
        if (obj instanceof Enum) {
            writer.write(quote(((Enum) obj).name()));
            return;
        }
        if (obj instanceof JSONObject) {
            ((JSONObject) obj).write(writer, i, i2);
            return;
        }
        if (obj instanceof JSONArray) {
            ((JSONArray) obj).write(writer, i, i2);
            return;
        }
        if (obj instanceof Map) {
            new JSONObject((Map<?, ?>) obj).write(writer, i, i2);
            return;
        }
        if (obj instanceof Collection) {
            new JSONArray((Collection<?>) obj).write(writer, i, i2);
            return;
        }
        if (obj.getClass() == byte[].class) {
            writeBase64(writer, (byte[]) obj);
        } else if (obj.getClass().isArray()) {
            new JSONArray(obj).write(writer, i, i2);
        } else {
            quote(obj.toString(), writer);
        }
    }

    public String toString(int i) throws JSONException {
        String string;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            string = write(stringWriter, i, 0).toString();
        }
        return string;
    }

    public abstract Writer write(Writer writer, int i, int i2) throws JSONException;

    public void write(File file, int i) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        write(fileOutputStream, i);
        fileOutputStream.close();
    }

    public String toString() {
        try {
            return toString(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public void write(File file) throws IOException {
        write(file, 2);
    }

    public void write(OutputStream outputStream) throws IOException {
        write(outputStream, 2);
    }

    public void write(OutputStream outputStream, int i) throws IOException {
        Writer writerWrite = write(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), i, 0);
        writerWrite.flush();
        writerWrite.close();
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    public static String quote(String str) {
        String string;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                try {
                    string = quote(str, stringWriter).toString();
                } catch (IOException unused) {
                    return "";
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return string;
    }
}
