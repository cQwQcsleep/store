package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import defpackage.knd;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import javax.xml.catalog.CatalogException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EntityResolverWrapper implements XMLEntityResolver {
    protected EntityResolver fEntityResolver;

    public EntityResolverWrapper(EntityResolver entityResolver) {
        setEntityResolver(entityResolver);
    }

    public EntityResolver getEntityResolver() {
        return this.fEntityResolver;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver
    public XMLInputSource resolveEntity(XMLResourceIdentifier xMLResourceIdentifier) throws IOException, XNIException {
        EntityResolver entityResolver;
        String publicId = xMLResourceIdentifier.getPublicId();
        String expandedSystemId = xMLResourceIdentifier.getExpandedSystemId();
        if ((publicId != null || expandedSystemId != null) && (entityResolver = this.fEntityResolver) != null) {
            try {
                InputSource inputSourceResolveEntity = entityResolver.resolveEntity(publicId, expandedSystemId);
                if (inputSourceResolveEntity != null) {
                    String publicId2 = inputSourceResolveEntity.getPublicId();
                    String systemId = inputSourceResolveEntity.getSystemId();
                    String baseSystemId = xMLResourceIdentifier.getBaseSystemId();
                    InputStream byteStream = inputSourceResolveEntity.getByteStream();
                    Reader characterStream = inputSourceResolveEntity.getCharacterStream();
                    String encoding = inputSourceResolveEntity.getEncoding();
                    XMLInputSource xMLInputSource = new XMLInputSource(publicId2, systemId, baseSystemId, true);
                    xMLInputSource.setByteStream(byteStream);
                    xMLInputSource.setCharacterStream(characterStream);
                    xMLInputSource.setEncoding(encoding);
                    return xMLInputSource;
                }
            } catch (CatalogException e) {
                knd.a(e);
                return null;
            } catch (SAXException e2) {
                e = e2;
                Exception exception = e.getException();
                if (exception != null) {
                    e = exception;
                }
                knd.a(e);
            }
        }
        return null;
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.fEntityResolver = entityResolver;
    }

    public EntityResolverWrapper() {
    }
}
