package javax.xml.catalog;

import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class SystemEntry extends BaseEntry {
    String systemId;
    URL uri;

    public SystemEntry(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.SYSTEM, str);
        setSystemId(str2);
        setURI(str3);
    }

    public String getSystemId() {
        return this.systemId;
    }

    public URL getURI() {
        return this.uri;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        if (this.systemId.equals(str)) {
            return this.uri.toString();
        }
        return null;
    }

    public void setSystemId(String str) {
        CatalogMessages.reportNPEOnNull("systemId", str);
        this.systemId = Normalizer.normalizeURI(str);
    }

    public void setURI(String str) {
        this.uri = verifyURI("setURI", this.baseURI, str);
    }
}
