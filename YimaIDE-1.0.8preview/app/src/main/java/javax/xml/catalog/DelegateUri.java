package javax.xml.catalog;

import java.net.URI;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class DelegateUri extends AltCatalog {
    String uriStartString;

    public DelegateUri(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.DELEGATEURI, str);
        setURIStartString(str2);
        setCatalog(str3);
    }

    public String getURIStartString() {
        return this.uriStartString;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        return match(str, 0);
    }

    @Override // javax.xml.catalog.AltCatalog
    public URI matchURI(String str, int i) {
        if (this.uriStartString.length() > str.length()) {
            return null;
        }
        String str2 = this.uriStartString;
        if (!str2.equals(str.substring(0, str2.length())) || i >= this.uriStartString.length()) {
            return null;
        }
        return this.catalogURI;
    }

    public void setURIStartString(String str) {
        CatalogMessages.reportNPEOnNull("uriStartString", str);
        String strNormalizeURI = Normalizer.normalizeURI(str);
        this.uriStartString = strNormalizeURI;
        setMatchId(strNormalizeURI);
    }
}
