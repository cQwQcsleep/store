package androidx.core.net;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ParseException extends RuntimeException {
    public final String response;

    public ParseException(String str) {
        super(str);
        this.response = str;
    }
}
