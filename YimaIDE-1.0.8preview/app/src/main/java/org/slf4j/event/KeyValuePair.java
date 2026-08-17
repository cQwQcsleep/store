package org.slf4j.event;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class KeyValuePair {
    public final String key;
    public final Object value;

    public KeyValuePair(String str, Object obj) {
        this.key = str;
        this.value = obj;
    }

    public String toString() {
        return String.valueOf(this.key) + "=\"" + String.valueOf(this.value) + "\"";
    }
}
