package javax.xml.catalog;

import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class UriSuffix extends BaseEntry {
    URL uri;
    String uriSuffix;

    public UriSuffix(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.URISUFFIX, str);
        setURISuffix(str2);
        setURI(str3);
    }

    public String getURI() {
        return this.uri.toString();
    }

    public String getURISuffix() {
        return this.uriSuffix;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str, int i) {
        if (!str.endsWith(this.uriSuffix) || i >= this.uriSuffix.length()) {
            return null;
        }
        return this.uri.toString();
    }

    public void setURI(String str) {
        this.uri = verifyURI("setURI", this.baseURI, str);
    }

    public void setURISuffix(String str) {
        CatalogMessages.reportNPEOnNull("uriSuffix", str);
        this.uriSuffix = Normalizer.normalizeURI(str);
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        return match(str, 0);
    }
}
