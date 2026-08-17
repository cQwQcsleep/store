package javax.xml.catalog;

import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class RewriteUri extends BaseEntry {
    URL rewritePrefix;
    String uriStartString;

    public RewriteUri(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.REWRITEURI, str);
        setURIStartString(str2);
        setRewritePrefix(str3);
    }

    public URL getRewritePrefix() {
        return this.rewritePrefix;
    }

    public String getURIStartString() {
        return this.uriStartString;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str, int i) {
        if (this.uriStartString.length() >= str.length()) {
            return null;
        }
        String str2 = this.uriStartString;
        if (!str2.equals(str.substring(0, str2.length())) || i >= this.uriStartString.length()) {
            return null;
        }
        String externalForm = this.rewritePrefix.toExternalForm();
        boolean zEndsWith = this.uriStartString.endsWith("/");
        String str3 = this.uriStartString;
        String strSubstring = zEndsWith ? str.substring(str3.length()) : str.substring(str3.length() + 1);
        if (externalForm.endsWith("/")) {
            return externalForm.concat(strSubstring);
        }
        return externalForm + "/" + strSubstring;
    }

    public void setRewritePrefix(String str) {
        this.rewritePrefix = verifyURI("setRewritePrefix", this.baseURI, str);
    }

    public void setURIStartString(String str) {
        CatalogMessages.reportNPEOnNull("uriStartString", str);
        this.uriStartString = Normalizer.normalizeURI(str);
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        return match(str, 0);
    }
}
