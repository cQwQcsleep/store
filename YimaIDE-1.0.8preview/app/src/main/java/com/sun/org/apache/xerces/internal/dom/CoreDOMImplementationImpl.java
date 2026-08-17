package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;
import com.sun.org.apache.xerces.internal.impl.dtd.XML11DTDProcessor;
import com.sun.org.apache.xerces.internal.impl.dtd.XML11DTDValidator;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator;
import com.sun.org.apache.xerces.internal.parsers.DOMParserImpl;
import com.sun.org.apache.xerces.internal.parsers.XIncludeAwareParserConfiguration;
import com.sun.org.apache.xerces.internal.parsers.XML11DTDConfiguration;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.org.apache.xml.internal.serializer.dom3.LSSerializerImpl;
import defpackage.zi0;
import java.lang.ref.SoftReference;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CoreDOMImplementationImpl implements DOMImplementation, DOMImplementationLS {
    private static final int SIZE = 2;
    static final CoreDOMImplementationImpl singleton = new CoreDOMImplementationImpl();
    private SoftReference[] schemaValidators = new SoftReference[2];
    private SoftReference[] xml10DTDValidators = new SoftReference[2];
    private SoftReference[] xml11DTDValidators = new SoftReference[2];
    private int freeSchemaValidatorIndex = -1;
    private int freeXML10DTDValidatorIndex = -1;
    private int freeXML11DTDValidatorIndex = -1;
    private int schemaValidatorsCurrentSize = 2;
    private int xml10DTDValidatorsCurrentSize = 2;
    private int xml11DTDValidatorsCurrentSize = 2;
    private SoftReference[] xml10DTDLoaders = new SoftReference[2];
    private SoftReference[] xml11DTDLoaders = new SoftReference[2];
    private int freeXML10DTDLoaderIndex = -1;
    private int freeXML11DTDLoaderIndex = -1;
    private int xml10DTDLoaderCurrentSize = 2;
    private int xml11DTDLoaderCurrentSize = 2;
    private int docAndDoctypeCounter = 0;

    public static final class RevalidationHandlerHolder {
        RevalidationHandler handler;

        public RevalidationHandlerHolder(RevalidationHandler revalidationHandler) {
            this.handler = revalidationHandler;
        }
    }

    public static final class XMLDTDLoaderHolder {
        XMLDTDLoader loader;

        public XMLDTDLoaderHolder(XMLDTDLoader xMLDTDLoader) {
            this.loader = xMLDTDLoader;
        }
    }

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    public synchronized int assignDocTypeNumber() {
        int i;
        i = this.docAndDoctypeCounter + 1;
        this.docAndDoctypeCounter = i;
        return i;
    }

    public synchronized int assignDocumentNumber() {
        int i;
        i = this.docAndDoctypeCounter + 1;
        this.docAndDoctypeCounter = i;
        return i;
    }

    public final void checkQName(String str) {
        int iIndexOf = str.indexOf(58);
        int iLastIndexOf = str.lastIndexOf(58);
        int length = str.length();
        if (iIndexOf == 0 || iIndexOf == length - 1 || iLastIndexOf != iIndexOf) {
            zi0.a(14, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NAMESPACE_ERR", null));
            return;
        }
        int i = 0;
        if (iIndexOf > 0) {
            if (!XMLChar.isNCNameStart(str.charAt(0))) {
                zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                return;
            }
            for (int i2 = 1; i2 < iIndexOf; i2++) {
                if (!XMLChar.isNCName(str.charAt(i2))) {
                    zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                    return;
                }
            }
            i = iIndexOf + 1;
        }
        if (!XMLChar.isNCNameStart(str.charAt(i))) {
            zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
            return;
        }
        for (int i3 = i + 1; i3 < length; i3++) {
            if (!XMLChar.isNCName(str.charAt(i3))) {
                zi0.a(5, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "INVALID_CHARACTER_ERR", null));
                return;
            }
        }
    }

    @Override // org.w3c.dom.DOMImplementation
    public Document createDocument(String str, String str2, DocumentType documentType) throws DOMException {
        if (documentType != null && documentType.getOwnerDocument() != null) {
            zi0.a(4, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "WRONG_DOCUMENT_ERR", null));
            return null;
        }
        CoreDocumentImpl coreDocumentImplCreateDocument = createDocument(documentType);
        if (str2 == null && str == null) {
            return coreDocumentImplCreateDocument;
        }
        coreDocumentImplCreateDocument.appendChild(coreDocumentImplCreateDocument.createElementNS(str, str2));
        return coreDocumentImplCreateDocument;
    }

    @Override // org.w3c.dom.DOMImplementation
    public DocumentType createDocumentType(String str, String str2, String str3) {
        checkQName(str);
        return new DocumentTypeImpl(null, str, str2, str3);
    }

    @Override // org.w3c.dom.ls.DOMImplementationLS
    public LSInput createLSInput() {
        return new DOMInputImpl();
    }

    @Override // org.w3c.dom.ls.DOMImplementationLS
    public LSOutput createLSOutput() {
        return new DOMOutputImpl();
    }

    @Override // org.w3c.dom.ls.DOMImplementationLS
    public LSParser createLSParser(short s, String str) throws DOMException {
        if (s == 1 && (str == null || "http://www.w3.org/2001/XMLSchema".equals(str) || "http://www.w3.org/TR/REC-xml".equals(str))) {
            return (str == null || !str.equals("http://www.w3.org/TR/REC-xml")) ? new DOMParserImpl(new XIncludeAwareParserConfiguration(), str) : new DOMParserImpl(new XML11DTDConfiguration(), str);
        }
        zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.ls.DOMImplementationLS
    public LSSerializer createLSSerializer() {
        return new LSSerializerImpl();
    }

    public final synchronized XMLDTDLoader getDTDLoader(String str) {
        XMLDTDLoader xMLDTDLoader;
        XMLDTDLoader xMLDTDLoader2;
        if (SerializerConstants.XMLVERSION11.equals(str)) {
            while (true) {
                int i = this.freeXML11DTDLoaderIndex;
                if (i < 0) {
                    return new XML11DTDProcessor();
                }
                XMLDTDLoaderHolder xMLDTDLoaderHolder = (XMLDTDLoaderHolder) this.xml11DTDLoaders[i].get();
                if (xMLDTDLoaderHolder != null && (xMLDTDLoader2 = xMLDTDLoaderHolder.loader) != null) {
                    xMLDTDLoaderHolder.loader = null;
                    this.freeXML11DTDLoaderIndex--;
                    return xMLDTDLoader2;
                }
                SoftReference[] softReferenceArr = this.xml11DTDLoaders;
                int i2 = this.freeXML11DTDLoaderIndex;
                this.freeXML11DTDLoaderIndex = i2 - 1;
                softReferenceArr[i2] = null;
            }
        } else {
            while (true) {
                int i3 = this.freeXML10DTDLoaderIndex;
                if (i3 < 0) {
                    return new XMLDTDLoader();
                }
                XMLDTDLoaderHolder xMLDTDLoaderHolder2 = (XMLDTDLoaderHolder) this.xml10DTDLoaders[i3].get();
                if (xMLDTDLoaderHolder2 != null && (xMLDTDLoader = xMLDTDLoaderHolder2.loader) != null) {
                    xMLDTDLoaderHolder2.loader = null;
                    this.freeXML10DTDLoaderIndex--;
                    return xMLDTDLoader;
                }
                SoftReference[] softReferenceArr2 = this.xml10DTDLoaders;
                int i4 = this.freeXML10DTDLoaderIndex;
                this.freeXML10DTDLoaderIndex = i4 - 1;
                softReferenceArr2[i4] = null;
            }
        }
    }

    @Override // org.w3c.dom.DOMImplementation
    public Object getFeature(String str, String str2) {
        CoreDOMImplementationImpl coreDOMImplementationImpl = singleton;
        if (coreDOMImplementationImpl.hasFeature(str, str2)) {
            return coreDOMImplementationImpl;
        }
        return null;
    }

    public synchronized RevalidationHandler getValidator(String str, String str2) {
        RevalidationHandler revalidationHandler;
        RevalidationHandler revalidationHandler2;
        RevalidationHandler revalidationHandler3;
        try {
            if (str == "http://www.w3.org/2001/XMLSchema") {
                while (true) {
                    int i = this.freeSchemaValidatorIndex;
                    if (i < 0) {
                        return new XMLSchemaValidator();
                    }
                    RevalidationHandlerHolder revalidationHandlerHolder = (RevalidationHandlerHolder) this.schemaValidators[i].get();
                    if (revalidationHandlerHolder != null && (revalidationHandler3 = revalidationHandlerHolder.handler) != null) {
                        revalidationHandlerHolder.handler = null;
                        this.freeSchemaValidatorIndex--;
                        return revalidationHandler3;
                    }
                    SoftReference[] softReferenceArr = this.schemaValidators;
                    int i2 = this.freeSchemaValidatorIndex;
                    this.freeSchemaValidatorIndex = i2 - 1;
                    softReferenceArr[i2] = null;
                }
            } else {
                if (str != "http://www.w3.org/TR/REC-xml") {
                    return null;
                }
                if (SerializerConstants.XMLVERSION11.equals(str2)) {
                    while (true) {
                        int i3 = this.freeXML11DTDValidatorIndex;
                        if (i3 < 0) {
                            return new XML11DTDValidator();
                        }
                        RevalidationHandlerHolder revalidationHandlerHolder2 = (RevalidationHandlerHolder) this.xml11DTDValidators[i3].get();
                        if (revalidationHandlerHolder2 != null && (revalidationHandler2 = revalidationHandlerHolder2.handler) != null) {
                            revalidationHandlerHolder2.handler = null;
                            this.freeXML11DTDValidatorIndex--;
                            return revalidationHandler2;
                        }
                        SoftReference[] softReferenceArr2 = this.xml11DTDValidators;
                        int i4 = this.freeXML11DTDValidatorIndex;
                        this.freeXML11DTDValidatorIndex = i4 - 1;
                        softReferenceArr2[i4] = null;
                    }
                } else {
                    while (true) {
                        int i5 = this.freeXML10DTDValidatorIndex;
                        if (i5 < 0) {
                            return new XMLDTDValidator();
                        }
                        RevalidationHandlerHolder revalidationHandlerHolder3 = (RevalidationHandlerHolder) this.xml10DTDValidators[i5].get();
                        if (revalidationHandlerHolder3 != null && (revalidationHandler = revalidationHandlerHolder3.handler) != null) {
                            revalidationHandlerHolder3.handler = null;
                            this.freeXML10DTDValidatorIndex--;
                            return revalidationHandler;
                        }
                        SoftReference[] softReferenceArr3 = this.xml10DTDValidators;
                        int i6 = this.freeXML10DTDValidatorIndex;
                        this.freeXML10DTDValidatorIndex = i6 - 1;
                        softReferenceArr3[i6] = null;
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // org.w3c.dom.DOMImplementation
    public boolean hasFeature(String str, String str2) {
        boolean z = str2 == null || str2.length() == 0;
        if (str.startsWith("+")) {
            str = str.substring(1);
        }
        return (str.equalsIgnoreCase("Core") && (z || str2.equals("1.0") || str2.equals("2.0") || str2.equals("3.0"))) || (str.equalsIgnoreCase("XML") && (z || str2.equals("1.0") || str2.equals("2.0") || str2.equals("3.0"))) || ((str.equalsIgnoreCase("XMLVersion") && (z || str2.equals("1.0") || str2.equals(SerializerConstants.XMLVERSION11))) || ((str.equalsIgnoreCase("LS") && (z || str2.equals("3.0"))) || (str.equalsIgnoreCase("ElementTraversal") && (z || str2.equals("1.0")))));
    }

    public final synchronized void releaseDTDLoader(String str, XMLDTDLoader xMLDTDLoader) {
        XMLDTDLoaderHolder xMLDTDLoaderHolder;
        XMLDTDLoaderHolder xMLDTDLoaderHolder2;
        try {
            if (SerializerConstants.XMLVERSION11.equals(str)) {
                int i = this.freeXML11DTDLoaderIndex + 1;
                this.freeXML11DTDLoaderIndex = i;
                SoftReference[] softReferenceArr = this.xml11DTDLoaders;
                if (softReferenceArr.length == i) {
                    int i2 = this.xml11DTDLoaderCurrentSize + 2;
                    this.xml11DTDLoaderCurrentSize = i2;
                    SoftReference[] softReferenceArr2 = new SoftReference[i2];
                    System.arraycopy(softReferenceArr, 0, softReferenceArr2, 0, softReferenceArr.length);
                    this.xml11DTDLoaders = softReferenceArr2;
                }
                SoftReference softReference = this.xml11DTDLoaders[this.freeXML11DTDLoaderIndex];
                if (softReference != null && (xMLDTDLoaderHolder2 = (XMLDTDLoaderHolder) softReference.get()) != null) {
                    xMLDTDLoaderHolder2.loader = xMLDTDLoader;
                    return;
                }
                this.xml11DTDLoaders[this.freeXML11DTDLoaderIndex] = new SoftReference(new XMLDTDLoaderHolder(xMLDTDLoader));
            } else {
                int i3 = this.freeXML10DTDLoaderIndex + 1;
                this.freeXML10DTDLoaderIndex = i3;
                SoftReference[] softReferenceArr3 = this.xml10DTDLoaders;
                if (softReferenceArr3.length == i3) {
                    int i4 = this.xml10DTDLoaderCurrentSize + 2;
                    this.xml10DTDLoaderCurrentSize = i4;
                    SoftReference[] softReferenceArr4 = new SoftReference[i4];
                    System.arraycopy(softReferenceArr3, 0, softReferenceArr4, 0, softReferenceArr3.length);
                    this.xml10DTDLoaders = softReferenceArr4;
                }
                SoftReference softReference2 = this.xml10DTDLoaders[this.freeXML10DTDLoaderIndex];
                if (softReference2 != null && (xMLDTDLoaderHolder = (XMLDTDLoaderHolder) softReference2.get()) != null) {
                    xMLDTDLoaderHolder.loader = xMLDTDLoader;
                    return;
                }
                this.xml10DTDLoaders[this.freeXML10DTDLoaderIndex] = new SoftReference(new XMLDTDLoaderHolder(xMLDTDLoader));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void releaseValidator(String str, String str2, RevalidationHandler revalidationHandler) {
        RevalidationHandlerHolder revalidationHandlerHolder;
        RevalidationHandlerHolder revalidationHandlerHolder2;
        RevalidationHandlerHolder revalidationHandlerHolder3;
        try {
            if (str == "http://www.w3.org/2001/XMLSchema") {
                int i = this.freeSchemaValidatorIndex + 1;
                this.freeSchemaValidatorIndex = i;
                SoftReference[] softReferenceArr = this.schemaValidators;
                if (softReferenceArr.length == i) {
                    int i2 = this.schemaValidatorsCurrentSize + 2;
                    this.schemaValidatorsCurrentSize = i2;
                    SoftReference[] softReferenceArr2 = new SoftReference[i2];
                    System.arraycopy(softReferenceArr, 0, softReferenceArr2, 0, softReferenceArr.length);
                    this.schemaValidators = softReferenceArr2;
                }
                SoftReference softReference = this.schemaValidators[this.freeSchemaValidatorIndex];
                if (softReference != null && (revalidationHandlerHolder3 = (RevalidationHandlerHolder) softReference.get()) != null) {
                    revalidationHandlerHolder3.handler = revalidationHandler;
                    return;
                }
                this.schemaValidators[this.freeSchemaValidatorIndex] = new SoftReference(new RevalidationHandlerHolder(revalidationHandler));
            } else if (str == "http://www.w3.org/TR/REC-xml") {
                if (SerializerConstants.XMLVERSION11.equals(str2)) {
                    int i3 = this.freeXML11DTDValidatorIndex + 1;
                    this.freeXML11DTDValidatorIndex = i3;
                    SoftReference[] softReferenceArr3 = this.xml11DTDValidators;
                    if (softReferenceArr3.length == i3) {
                        int i4 = this.xml11DTDValidatorsCurrentSize + 2;
                        this.xml11DTDValidatorsCurrentSize = i4;
                        SoftReference[] softReferenceArr4 = new SoftReference[i4];
                        System.arraycopy(softReferenceArr3, 0, softReferenceArr4, 0, softReferenceArr3.length);
                        this.xml11DTDValidators = softReferenceArr4;
                    }
                    SoftReference softReference2 = this.xml11DTDValidators[this.freeXML11DTDValidatorIndex];
                    if (softReference2 != null && (revalidationHandlerHolder2 = (RevalidationHandlerHolder) softReference2.get()) != null) {
                        revalidationHandlerHolder2.handler = revalidationHandler;
                        return;
                    }
                    this.xml11DTDValidators[this.freeXML11DTDValidatorIndex] = new SoftReference(new RevalidationHandlerHolder(revalidationHandler));
                } else {
                    int i5 = this.freeXML10DTDValidatorIndex + 1;
                    this.freeXML10DTDValidatorIndex = i5;
                    SoftReference[] softReferenceArr5 = this.xml10DTDValidators;
                    if (softReferenceArr5.length == i5) {
                        int i6 = this.xml10DTDValidatorsCurrentSize + 2;
                        this.xml10DTDValidatorsCurrentSize = i6;
                        SoftReference[] softReferenceArr6 = new SoftReference[i6];
                        System.arraycopy(softReferenceArr5, 0, softReferenceArr6, 0, softReferenceArr5.length);
                        this.xml10DTDValidators = softReferenceArr6;
                    }
                    SoftReference softReference3 = this.xml10DTDValidators[this.freeXML10DTDValidatorIndex];
                    if (softReference3 != null && (revalidationHandlerHolder = (RevalidationHandlerHolder) softReference3.get()) != null) {
                        revalidationHandlerHolder.handler = revalidationHandler;
                        return;
                    }
                    this.xml10DTDValidators[this.freeXML10DTDValidatorIndex] = new SoftReference(new RevalidationHandlerHolder(revalidationHandler));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public CoreDocumentImpl createDocument(DocumentType documentType) {
        return new CoreDocumentImpl(documentType);
    }
}
