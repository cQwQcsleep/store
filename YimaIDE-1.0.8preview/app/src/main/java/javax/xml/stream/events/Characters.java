package javax.xml.stream.events;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface Characters extends XMLEvent {
    String getData();

    boolean isCData();

    boolean isIgnorableWhiteSpace();

    boolean isWhiteSpace();
}
