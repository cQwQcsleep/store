package jdk.xml.internal;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TransformErrorListener implements ErrorListener {
    @Override // javax.xml.transform.ErrorListener
    public void error(TransformerException transformerException) throws TransformerException {
        throw transformerException;
    }

    @Override // javax.xml.transform.ErrorListener
    public void fatalError(TransformerException transformerException) throws TransformerException {
        throw transformerException;
    }

    @Override // javax.xml.transform.ErrorListener
    public void warning(TransformerException transformerException) throws TransformerException {
    }
}
