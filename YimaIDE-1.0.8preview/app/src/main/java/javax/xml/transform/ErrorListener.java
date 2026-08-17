package javax.xml.transform;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ErrorListener {
    void error(TransformerException transformerException) throws TransformerException;

    void fatalError(TransformerException transformerException) throws TransformerException;

    void warning(TransformerException transformerException) throws TransformerException;
}
