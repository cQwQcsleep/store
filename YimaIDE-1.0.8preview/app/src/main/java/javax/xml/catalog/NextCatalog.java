package javax.xml.catalog;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class NextCatalog extends AltCatalog {
    public NextCatalog(String str, String str2) {
        super(BaseEntry.CatalogEntryType.NEXTCATALOG, str);
        setCatalog(str2);
    }

    @Override // javax.xml.catalog.BaseEntry
    public String match(String str) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
