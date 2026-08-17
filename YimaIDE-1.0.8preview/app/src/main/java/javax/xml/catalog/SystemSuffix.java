package javax.xml.catalog;

import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class SystemSuffix extends BaseEntry {
    String systemIdSuffix;
    URL uri;

    public SystemSuffix(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.SYSTEMSUFFIX, str);
        setSystemIdSuffix(str2);
        setURI(str3);
    }

    public String getSystemIdSuffix() {
        return this.systemIdSuffix;
    }

    public URL getURI() {
        return this.uri;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str, int i) {
        if (!str.endsWith(this.systemIdSuffix) || i >= this.systemIdSuffix.length()) {
            return null;
        }
        return this.uri.toString();
    }

    public void setSystemIdSuffix(String str) {
        CatalogMessages.reportNPEOnNull("systemIdSuffix", str);
        this.systemIdSuffix = Normalizer.normalizeURI(str);
    }

    public void setURI(String str) {
        this.uri = verifyURI("setURI", this.baseURI, str);
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        return match(str, 0);
    }
}
