package javax.xml.catalog;

import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class UriEntry extends BaseEntry {
    String name;
    URL uri;

    public UriEntry(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.URI, str);
        setName(str2);
        setURI(str3);
    }

    public String getName() {
        return this.name;
    }

    public URL getURI() {
        return this.uri;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        if (this.name.equals(str)) {
            return this.uri.toString();
        }
        return null;
    }

    public void setName(String str) {
        CatalogMessages.reportNPEOnNull("name", str);
        if (str.startsWith("-//") || str.startsWith("+//")) {
            this.name = Normalizer.normalizePublicId(str);
        } else {
            this.name = Normalizer.normalizeURI(str);
        }
    }

    public void setURI(String str) {
        this.uri = verifyURI("setURI", this.baseURI, str);
    }
}
