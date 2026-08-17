package javax.xml.catalog;

import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Catalog {
    Stream<Catalog> catalogs();

    String matchPublic(String str);

    String matchSystem(String str);

    String matchURI(String str);
}
