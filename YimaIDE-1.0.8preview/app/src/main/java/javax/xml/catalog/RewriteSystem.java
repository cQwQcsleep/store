package javax.xml.catalog;

import java.net.URL;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class RewriteSystem extends BaseEntry {
    URL rewritePrefix;
    String systemIdStartString;

    public RewriteSystem(String str, String str2, String str3) {
        super(BaseEntry.CatalogEntryType.REWRITESYSTEM, str);
        setSystemIdStartString(str2);
        setRewritePrefix(str3);
    }

    public URL getRewritePrefix() {
        return this.rewritePrefix;
    }

    public String getSystemIdStartString() {
        return this.systemIdStartString;
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str, int i) {
        if (this.systemIdStartString.length() >= str.length()) {
            return null;
        }
        String str2 = this.systemIdStartString;
        if (!str2.equals(str.substring(0, str2.length())) || i >= this.systemIdStartString.length()) {
            return null;
        }
        String externalForm = this.rewritePrefix.toExternalForm();
        boolean zEndsWith = this.systemIdStartString.endsWith("/");
        String str3 = this.systemIdStartString;
        String strSubstring = zEndsWith ? str.substring(str3.length()) : str.substring(str3.length() + 1);
        if (externalForm.endsWith("/")) {
            return externalForm.concat(strSubstring);
        }
        return externalForm + "/" + strSubstring;
    }

    public void setRewritePrefix(String str) {
        this.rewritePrefix = verifyURI("setRewritePrefix", this.baseURI, str);
    }

    public void setSystemIdStartString(String str) {
        CatalogMessages.reportNPEOnNull("systemIdStartString", str);
        this.systemIdStartString = Normalizer.normalizeURI(str);
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        return match(str, 0);
    }
}
