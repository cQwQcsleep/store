package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMEntityResolverWrapper implements XMLEntityResolver {
    private static final String XML_TYPE = "http://www.w3.org/TR/REC-xml";
    private static final String XSD_TYPE = "http://www.w3.org/2001/XMLSchema";
    protected LSResourceResolver fEntityResolver;

    public DOMEntityResolverWrapper(LSResourceResolver lSResourceResolver) {
        setEntityResolver(lSResourceResolver);
    }

    private String getType(XMLResourceIdentifier xMLResourceIdentifier) {
        return ((xMLResourceIdentifier instanceof XMLGrammarDescription) && "http://www.w3.org/2001/XMLSchema".equals(((XMLGrammarDescription) xMLResourceIdentifier).getGrammarType())) ? "http://www.w3.org/2001/XMLSchema" : "http://www.w3.org/TR/REC-xml";
    }

    public LSResourceResolver getEntityResolver() {
        return this.fEntityResolver;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver
    public XMLInputSource resolveEntity(XMLResourceIdentifier xMLResourceIdentifier) throws IOException, XNIException {
        LSResourceResolver lSResourceResolver = this.fEntityResolver;
        if (lSResourceResolver == null) {
            return null;
        }
        LSInput lSInputResolveResource = xMLResourceIdentifier == null ? lSResourceResolver.resolveResource(null, null, null, null, null) : lSResourceResolver.resolveResource(getType(xMLResourceIdentifier), xMLResourceIdentifier.getNamespace(), xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId());
        if (lSInputResolveResource == null) {
            return null;
        }
        String publicId = lSInputResolveResource.getPublicId();
        String systemId = lSInputResolveResource.getSystemId();
        String baseURI = lSInputResolveResource.getBaseURI();
        InputStream byteStream = lSInputResolveResource.getByteStream();
        Reader characterStream = lSInputResolveResource.getCharacterStream();
        String encoding = lSInputResolveResource.getEncoding();
        String stringData = lSInputResolveResource.getStringData();
        XMLInputSource xMLInputSource = new XMLInputSource(publicId, systemId, baseURI, true);
        if (characterStream != null) {
            xMLInputSource.setCharacterStream(characterStream);
        } else if (byteStream != null) {
            xMLInputSource.setByteStream(byteStream);
        } else if (stringData != null && stringData.length() != 0) {
            xMLInputSource.setCharacterStream(new StringReader(stringData));
        }
        xMLInputSource.setEncoding(encoding);
        return xMLInputSource;
    }

    public void setEntityResolver(LSResourceResolver lSResourceResolver) {
        this.fEntityResolver = lSResourceResolver;
    }

    public DOMEntityResolverWrapper() {
    }
}
