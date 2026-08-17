package javax.xml.transform.sax;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface TransformerHandler extends ContentHandler, LexicalHandler, DTDHandler {
    String getSystemId();

    Transformer getTransformer();

    void setResult(Result result) throws IllegalArgumentException;

    void setSystemId(String str);
}
