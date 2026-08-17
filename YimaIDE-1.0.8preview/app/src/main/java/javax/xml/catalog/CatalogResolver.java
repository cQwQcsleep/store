package javax.xml.catalog;

import java.io.InputStream;
import javax.xml.stream.XMLResolver;
import javax.xml.transform.Source;
import javax.xml.transform.URIResolver;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface CatalogResolver extends EntityResolver, XMLResolver, URIResolver, LSResourceResolver {
    @Override // javax.xml.transform.URIResolver
    Source resolve(String str, String str2);

    @Override // javax.xml.stream.XMLResolver
    InputStream resolveEntity(String str, String str2, String str3, String str4);

    @Override // org.xml.sax.EntityResolver
    InputSource resolveEntity(String str, String str2);

    @Override // org.w3c.dom.ls.LSResourceResolver
    LSInput resolveResource(String str, String str2, String str3, String str4, String str5);
}
