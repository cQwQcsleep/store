package javax.xml.catalog;

import java.io.InputStream;
import java.io.Reader;
import org.w3c.dom.ls.LSInput;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CatalogResolverImpl$LSInputImpl implements LSInput {
    private String systemId;
    final /* synthetic */ CatalogResolverImpl this$0;

    public CatalogResolverImpl$LSInputImpl(CatalogResolverImpl catalogResolverImpl, String str) {
        this.this$0 = catalogResolverImpl;
        this.systemId = str;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getBaseURI() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public InputStream getByteStream() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public boolean getCertifiedText() {
        return false;
    }

    @Override // org.w3c.dom.ls.LSInput
    public Reader getCharacterStream() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getEncoding() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getPublicId() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getStringData() {
        return null;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getSystemId() {
        return this.systemId;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setBaseURI(String str) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setByteStream(InputStream inputStream) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setCertifiedText(boolean z) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setCharacterStream(Reader reader) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setEncoding(String str) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setPublicId(String str) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setStringData(String str) {
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setSystemId(String str) {
        this.systemId = str;
    }
}
