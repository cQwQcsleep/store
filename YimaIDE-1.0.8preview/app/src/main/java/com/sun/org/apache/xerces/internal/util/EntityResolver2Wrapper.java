package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.ExternalSubsetResolver;
import com.sun.org.apache.xerces.internal.impl.XMLEntityDescription;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLDTDDescription;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import defpackage.knd;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.EntityResolver2;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EntityResolver2Wrapper implements ExternalSubsetResolver {
    protected EntityResolver2 fEntityResolver;

    public EntityResolver2Wrapper(EntityResolver2 entityResolver2) {
        setEntityResolver(entityResolver2);
    }

    private XMLInputSource createXMLInputSource(InputSource inputSource, String str) {
        String publicId = inputSource.getPublicId();
        String systemId = inputSource.getSystemId();
        InputStream byteStream = inputSource.getByteStream();
        Reader characterStream = inputSource.getCharacterStream();
        String encoding = inputSource.getEncoding();
        XMLInputSource xMLInputSource = new XMLInputSource(publicId, systemId, str, false);
        xMLInputSource.setByteStream(byteStream);
        xMLInputSource.setCharacterStream(characterStream);
        xMLInputSource.setEncoding(encoding);
        return xMLInputSource;
    }

    public EntityResolver2 getEntityResolver() {
        return this.fEntityResolver;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.ExternalSubsetResolver
    public XMLInputSource getExternalSubset(XMLDTDDescription xMLDTDDescription) throws IOException, XNIException {
        if (this.fEntityResolver != null) {
            String rootName = xMLDTDDescription.getRootName();
            String baseSystemId = xMLDTDDescription.getBaseSystemId();
            try {
                InputSource externalSubset = this.fEntityResolver.getExternalSubset(rootName, baseSystemId);
                if (externalSubset != null) {
                    return createXMLInputSource(externalSubset, baseSystemId);
                }
                return null;
            } catch (SAXException e) {
                e = e;
                Exception exception = e.getException();
                if (exception != null) {
                    e = exception;
                }
                knd.a(e);
            }
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver
    public XMLInputSource resolveEntity(XMLResourceIdentifier xMLResourceIdentifier) throws IOException, XNIException {
        String entityName;
        if (this.fEntityResolver != null) {
            String publicId = xMLResourceIdentifier.getPublicId();
            String literalSystemId = xMLResourceIdentifier.getLiteralSystemId();
            String baseSystemId = xMLResourceIdentifier.getBaseSystemId();
            if (xMLResourceIdentifier instanceof XMLDTDDescription) {
                entityName = "[dtd]";
            } else {
                entityName = xMLResourceIdentifier instanceof XMLEntityDescription ? ((XMLEntityDescription) xMLResourceIdentifier).getEntityName() : null;
            }
            if (publicId == null && literalSystemId == null) {
                return null;
            }
            try {
                InputSource inputSourceResolveEntity = this.fEntityResolver.resolveEntity(entityName, publicId, baseSystemId, literalSystemId);
                if (inputSourceResolveEntity != null) {
                    return createXMLInputSource(inputSourceResolveEntity, baseSystemId);
                }
                return null;
            } catch (SAXException e) {
                e = e;
                Exception exception = e.getException();
                if (exception != null) {
                    e = exception;
                }
                knd.a(e);
            }
        }
        return null;
    }

    public void setEntityResolver(EntityResolver2 entityResolver2) {
        this.fEntityResolver = entityResolver2;
    }

    public EntityResolver2Wrapper() {
    }
}
