package javax.xml.catalog;

import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class AltCatalog extends BaseEntry {
    URI catalogURI;

    public AltCatalog(BaseEntry.CatalogEntryType catalogEntryType, String str) {
        super(catalogEntryType, str);
    }

    public String getCatalogId() {
        return this.catalogURI.toASCIIString();
    }

    public URI getCatalogURI() {
        return this.catalogURI;
    }

    public URI matchURI(String str, int i) {
        return null;
    }

    public void setCatalog(String str) {
        try {
            this.catalogURI = verifyURI("catalog", this.baseURI, str).toURI();
        } catch (URISyntaxException e) {
            CatalogMessages.reportRunTimeError(CatalogMessages.ERR_OTHER, e);
        }
    }
}
