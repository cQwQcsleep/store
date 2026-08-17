package javax.xml.catalog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
abstract class BaseEntry {
    final String SLASH = "/";
    boolean baseSpecified = false;
    URL baseURI;
    String id;
    String matchId;
    CatalogEntryType type;

    public enum CatalogEntryType {
        CATALOG("catalogfile"),
        CATALOGENTRY("catalog"),
        GROUP("group"),
        PUBLIC("public"),
        SYSTEM("system"),
        REWRITESYSTEM("rewriteSystem"),
        SYSTEMSUFFIX("systemSuffix"),
        DELEGATEPUBLIC("delegatePublic"),
        DELEGATESYSTEM("delegateSystem"),
        URI("uri"),
        REWRITEURI("rewriteURI"),
        URISUFFIX("uriSuffix"),
        DELEGATEURI("delegateURI"),
        NEXTCATALOG("nextCatalog");

        final String literal;

        CatalogEntryType(String str) {
            this.literal = str;
        }

        public static CatalogEntryType getType(String str) {
            for (CatalogEntryType catalogEntryType : values()) {
                if (catalogEntryType.isType(str)) {
                    return catalogEntryType;
                }
            }
            return null;
        }

        public boolean isType(String str) {
            return this.literal.equals(str);
        }
    }

    public BaseEntry(CatalogEntryType catalogEntryType, String str) {
        Objects.requireNonNull(catalogEntryType);
        this.type = catalogEntryType;
        setBaseURI(str);
    }

    public URL getBaseURI() {
        return this.baseURI;
    }

    public String getId() {
        return this.id;
    }

    public String getMatchId() {
        return this.matchId;
    }

    public CatalogEntryType getType() {
        return this.type;
    }

    public String match(String str) {
        return null;
    }

    public final void setBaseURI(String str) {
        this.baseURI = verifyURI("base", null, str);
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMatchId(String str) {
        this.matchId = str;
    }

    public void setType(CatalogEntryType catalogEntryType) {
        this.type = catalogEntryType;
    }

    public URL verifyURI(String str, URL url, String str2) {
        CatalogMessages.reportNPEOnNull(str, str2);
        String strNormalizeURI = Normalizer.normalizeURI(str2);
        try {
            return url != null ? new URL(url, strNormalizeURI) : new URL(strNormalizeURI);
        } catch (MalformedURLException e) {
            CatalogMessages.reportIAE(CatalogMessages.ERR_INVALID_ARGUMENT, new Object[]{strNormalizeURI, str}, e);
            return null;
        }
    }

    public String match(String str, int i) {
        return null;
    }

    public BaseEntry(CatalogEntryType catalogEntryType) {
        Objects.requireNonNull(catalogEntryType);
        this.type = catalogEntryType;
    }
}
