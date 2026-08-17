package javax.xml.catalog;

import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class PublicEntry extends BaseEntry {
    String publicId;
    URL uri;

    public PublicEntry(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.PUBLIC, str);
        setPublicId(str2);
        setURI(str3);
    }

    public String getPublicId() {
        return this.publicId;
    }

    public URL getURI() {
        return this.uri;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        if (this.publicId.equals(str)) {
            return this.uri.toString();
        }
        return null;
    }

    public void setPublicId(String str) {
        CatalogMessages.reportNPEOnNull("publicId", str);
        this.publicId = Normalizer.normalizePublicId(str);
    }

    public void setURI(String str) {
        this.uri = verifyURI("uri", this.baseURI, str);
    }
}
