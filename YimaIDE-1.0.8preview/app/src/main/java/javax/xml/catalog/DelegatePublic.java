package javax.xml.catalog;

import java.net.URI;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class DelegatePublic extends AltCatalog {
    String publicIdStartString;

    public DelegatePublic(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.DELEGATEPUBLIC, str);
        setPublicIdStartString(str2);
        setCatalog(str3);
    }

    public String getPublicIdStartString() {
        return this.publicIdStartString;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        return match(str, 0);
    }

    @Override // javax.xml.catalog.AltCatalog
    public URI matchURI(String str, int i) {
        if (this.publicIdStartString.length() > str.length()) {
            return null;
        }
        String str2 = this.publicIdStartString;
        if (!str2.equals(str.substring(0, str2.length())) || i >= this.publicIdStartString.length()) {
            return null;
        }
        return this.catalogURI;
    }

    public void setPublicIdStartString(String str) {
        CatalogMessages.reportNPEOnNull("publicIdStartString", str);
        String strNormalizePublicId = Normalizer.normalizePublicId(str);
        this.publicIdStartString = strNormalizePublicId;
        setMatchId(strNormalizePublicId);
    }
}
