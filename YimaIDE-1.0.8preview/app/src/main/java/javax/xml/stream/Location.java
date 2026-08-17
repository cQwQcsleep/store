package javax.xml.stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Location {
    int getCharacterOffset();

    int getColumnNumber();

    int getLineNumber();

    String getPublicId();

    String getSystemId();
}
