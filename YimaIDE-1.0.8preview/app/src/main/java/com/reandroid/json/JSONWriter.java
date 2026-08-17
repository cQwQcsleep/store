package com.reandroid.json;

import com.intellij.psi.PsiKeyword;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JSONWriter {
    private static final int DEFAULT_INDENT_FACTOR = 1;
    private static final int MAX_DEPTH = 200;
    protected final Appendable writer;
    private boolean comma = false;
    protected char mode = 'i';
    private final JSONObject[] stack = new JSONObject[200];
    private int top = 0;
    private int indentFactor = 1;

    public JSONWriter(Appendable appendable) {
        this.writer = appendable;
    }

    private JSONWriter append(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null pointer");
        }
        char c = this.mode;
        if (c != 'o' && c != 'a') {
            throw new JSONException("Value out of sequence.");
        }
        try {
            if (this.comma && c == 'a') {
                this.writer.append(',');
                writeIndent();
            }
            this.writer.append(str);
            if (this.mode == 'o') {
                this.mode = 'k';
            }
            this.comma = true;
            return this;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    private JSONWriter end(char c, char c2) throws JSONException {
        if (this.mode != c) {
            throw new JSONException(c == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
        }
        pop(c);
        try {
            writeIndent();
            this.writer.append(c2);
            this.comma = true;
            return this;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    private void pop(char c) throws JSONException {
        int i = this.top;
        if (i <= 0) {
            throw new JSONException("Nesting error.");
        }
        JSONObject[] jSONObjectArr = this.stack;
        char c2 = 'k';
        if ((jSONObjectArr[i + (-1)] == null ? 'a' : 'k') != c) {
            throw new JSONException("Nesting error.");
        }
        int i2 = i - 1;
        this.top = i2;
        if (i2 == 0) {
            c2 = 'd';
        } else if (jSONObjectArr[i - 2] == null) {
            c2 = 'a';
        }
        this.mode = c2;
    }

    private void push(JSONObject jSONObject) throws JSONException {
        int i = this.top;
        if (i >= 200) {
            throw new JSONException("Nesting too deep.");
        }
        this.stack[i] = jSONObject;
        this.mode = jSONObject == null ? 'a' : 'k';
        this.top = i + 1;
    }

    public static String valueToString(Object obj) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return PsiKeyword.NULL;
        }
        if (obj instanceof JSONString) {
            try {
                String jSONString = ((JSONString) obj).toJSONString();
                if (jSONString != null) {
                    return jSONString;
                }
                throw new JSONException("Bad value from toJSONString: " + jSONString);
            } catch (Exception e) {
                throw new JSONException(e);
            }
        }
        if (obj instanceof Number) {
            return JSONItem.numberToString((Number) obj);
        }
        if ((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new JSONObject((Map<?, ?>) obj).toString();
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection<?>) obj).toString();
        }
        if (obj.getClass().isArray()) {
            return new JSONArray(obj).toString();
        }
        return obj instanceof Enum ? JSONItem.quote(((Enum) obj).name()) : JSONItem.quote(obj.toString());
    }

    private void writeIndent() throws JSONException {
        if (this.indentFactor < 0 || this.mode == 'i') {
            return;
        }
        try {
            Appendable appendable = this.writer;
            appendable.append('\n');
            int i = this.top * this.indentFactor;
            for (int i2 = 0; i2 < i; i2++) {
                appendable.append(' ');
            }
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public JSONWriter array() throws JSONException {
        writeIndent();
        char c = this.mode;
        if (c != 'i' && c != 'o' && c != 'a') {
            throw new JSONException("Misplaced array.");
        }
        push(null);
        append("[");
        this.comma = false;
        writeIndent();
        return this;
    }

    public JSONWriter endArray() throws JSONException {
        return end('a', ']');
    }

    public JSONWriter endObject() throws JSONException {
        return end('k', '}');
    }

    public JSONWriter key(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        if (this.mode != 'k') {
            throw new JSONException("Misplaced key.");
        }
        try {
            JSONObject jSONObject = this.stack[this.top - 1];
            if (jSONObject.has(str)) {
                throw new JSONException("Duplicate key \"" + str + "\"");
            }
            jSONObject.put(str, true);
            if (this.comma) {
                this.writer.append(',');
                writeIndent();
            }
            this.writer.append(JSONItem.quote(str));
            this.writer.append(':');
            this.comma = false;
            this.mode = 'o';
            return this;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public JSONWriter object() throws JSONException {
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        char c = this.mode;
        if (c != 'o' && c != 'a') {
            throw new JSONException("Misplaced object.");
        }
        append("{");
        push(new JSONObject());
        this.comma = false;
        writeIndent();
        return this;
    }

    public void setIndentFactor(int i) {
        this.indentFactor = i;
    }

    public JSONWriter value(JSONObject jSONObject) throws JSONException {
        JSONWriter jSONWriterObject = object();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            jSONWriterObject.key(next).value(jSONObject.get(next));
        }
        jSONWriterObject.endObject();
        return this;
    }

    public JSONWriter value(double d) throws JSONException {
        return value(Double.valueOf(d));
    }

    public JSONWriter value(long j) throws JSONException {
        return append(Long.toString(j));
    }

    public JSONWriter value(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            return value((JSONArray) obj);
        }
        if (obj instanceof JSONObject) {
            return value((JSONObject) obj);
        }
        return append(valueToString(obj));
    }

    public JSONWriter value(JSONArray jSONArray) throws JSONException {
        JSONWriter jSONWriterArray = array();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            jSONWriterArray.value(jSONArray.get(i));
        }
        jSONWriterArray.endArray();
        return this;
    }

    public JSONWriter value(boolean z) throws JSONException {
        return append(z ? "true" : "false");
    }
}
