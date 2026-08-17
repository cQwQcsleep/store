package javax.xml.transform;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Source {
    String getSystemId();

    default boolean isEmpty() {
        throw new UnsupportedOperationException("The isEmpty method is not supported.");
    }

    void setSystemId(String str);
}
