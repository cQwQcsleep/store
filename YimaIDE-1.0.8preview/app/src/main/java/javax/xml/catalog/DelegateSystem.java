package javax.xml.catalog;

import java.net.URI;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class DelegateSystem extends AltCatalog {
    String systemIdStartString;

    public DelegateSystem(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.DELEGATESYSTEM, str);
        setSystemIdStartString(str2);
        setCatalog(str3);
    }

    public String getSystemIdStartString() {
        return this.systemIdStartString;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        return match(str, 0);
    }

    @Override // javax.xml.catalog.AltCatalog
    public URI matchURI(String str, int i) {
        if (this.systemIdStartString.length() > str.length()) {
            return null;
        }
        String str2 = this.systemIdStartString;
        if (!str2.equals(str.substring(0, str2.length())) || i >= this.systemIdStartString.length()) {
            return null;
        }
        return this.catalogURI;
    }

    public void setSystemIdStartString(String str) {
        CatalogMessages.reportNPEOnNull("systemIdStartString", str);
        String strNormalizeURI = Normalizer.normalizeURI(str);
        this.systemIdStartString = strNormalizeURI;
        setMatchId(strNormalizeURI);
    }
}
