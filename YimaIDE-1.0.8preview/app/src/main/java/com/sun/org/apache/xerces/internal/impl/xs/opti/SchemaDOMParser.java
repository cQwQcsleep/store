package com.sun.org.apache.xerces.internal.impl.xs.opti;

import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import java.io.IOException;
import org.w3c.dom.Document;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SchemaDOMParser extends DefaultXMLDocumentHandler {
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    public static final String GENERATE_SYNTHETIC_ANNOTATION = "http://apache.org/xml/features/generate-synthetic-annotations";
    XMLParserConfiguration config;
    private ElementImpl fCurrentAnnotationElement;
    XMLErrorReporter fErrorReporter;
    protected XMLLocator fLocator;
    SchemaDOM schemaDOM;
    protected NamespaceContext fNamespaceContext = null;
    private int fAnnotationDepth = -1;
    private int fInnerAnnotationDepth = -1;
    private int fDepth = -1;
    private boolean fGenerateSyntheticAnnotation = false;
    private BooleanStack fHasNonSchemaAttributes = new BooleanStack();
    private BooleanStack fSawAnnotation = new BooleanStack();
    private XMLAttributes fEmptyAttr = new XMLAttributesImpl();

    public static final class BooleanStack {
        private boolean[] fData;
        private int fDepth;

        private void ensureCapacity(int i) {
            boolean[] zArr = this.fData;
            if (zArr == null) {
                this.fData = new boolean[32];
            } else if (zArr.length <= i) {
                boolean[] zArr2 = new boolean[zArr.length * 2];
                System.arraycopy(zArr, 0, zArr2, 0, zArr.length);
                this.fData = zArr2;
            }
        }

        public void clear() {
            this.fDepth = 0;
        }

        public boolean pop() {
            boolean[] zArr = this.fData;
            int i = this.fDepth - 1;
            this.fDepth = i;
            return zArr[i];
        }

        public void push(boolean z) {
            ensureCapacity(this.fDepth + 1);
            boolean[] zArr = this.fData;
            int i = this.fDepth;
            this.fDepth = i + 1;
            zArr[i] = z;
        }

        public int size() {
            return this.fDepth;
        }
    }

    public SchemaDOMParser(XMLParserConfiguration xMLParserConfiguration) {
        this.config = xMLParserConfiguration;
        xMLParserConfiguration.setDocumentHandler(this);
        xMLParserConfiguration.setDTDHandler(this);
        xMLParserConfiguration.setDTDContentModelHandler(this);
    }

    private boolean hasNonSchemaAttributes(QName qName, XMLAttributes xMLAttributes) {
        int length = xMLAttributes.getLength();
        for (int i = 0; i < length; i++) {
            String uri = xMLAttributes.getURI(i);
            if (uri != null && uri != SchemaSymbols.URI_SCHEMAFORSCHEMA && uri != NamespaceContext.XMLNS_URI && (uri != NamespaceContext.XML_URI || xMLAttributes.getQName(i) != SchemaSymbols.ATT_XML_LANG || qName.localpart != SchemaSymbols.ELT_SCHEMA)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fInnerAnnotationDepth != -1) {
            this.schemaDOM.characters(xMLString);
            return;
        }
        for (int i = xMLString.offset; i < xMLString.offset + xMLString.length; i++) {
            if (!XMLChar.isSpace(xMLString.ch[i])) {
                this.fErrorReporter.reportError(this.fLocator, XSMessageFormatter.SCHEMA_DOMAIN, "s4s-elt-character", new Object[]{new String(xMLString.ch, i, (xMLString.length + xMLString.offset) - i)}, (short) 1);
                return;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth > -1) {
            this.schemaDOM.comment(xMLString);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        String str;
        if (this.fGenerateSyntheticAnnotation && this.fAnnotationDepth == -1) {
            String str2 = qName.uri;
            String str3 = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            if (str2 == str3) {
                String str4 = qName.localpart;
                String str5 = SchemaSymbols.ELT_ANNOTATION;
                if (str4 != str5 && hasNonSchemaAttributes(qName, xMLAttributes)) {
                    this.schemaDOM.startElement(qName, xMLAttributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
                    xMLAttributes.removeAllAttributes();
                    String prefix = this.fNamespaceContext.getPrefix(str3);
                    if (prefix.length() != 0) {
                        str5 = prefix + ':' + str5;
                    }
                    this.schemaDOM.startAnnotation(str5, xMLAttributes, this.fNamespaceContext);
                    if (prefix.length() == 0) {
                        str = SchemaSymbols.ELT_DOCUMENTATION;
                    } else {
                        str = prefix + ':' + SchemaSymbols.ELT_DOCUMENTATION;
                    }
                    this.schemaDOM.startAnnotationElement(str, xMLAttributes);
                    this.schemaDOM.charactersRaw("SYNTHETIC_ANNOTATION");
                    this.schemaDOM.endSyntheticAnnotationElement(str, false);
                    this.schemaDOM.endSyntheticAnnotationElement(str5, true);
                    this.schemaDOM.endElement();
                    return;
                }
            }
        }
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.startAnnotationElement(qName, xMLAttributes);
        } else if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && qName.localpart == SchemaSymbols.ELT_ANNOTATION) {
            this.schemaDOM.startAnnotation(qName, xMLAttributes, this.fNamespaceContext);
        }
        ElementImpl elementImplEmptyElement = this.schemaDOM.emptyElement(qName, xMLAttributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.endAnnotationElement(qName);
        } else if (qName.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA && qName.localpart == SchemaSymbols.ELT_ANNOTATION) {
            this.schemaDOM.endAnnotation(qName, elementImplEmptyElement);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.endAnnotationCDATA();
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        String str;
        String str2;
        int i = this.fAnnotationDepth;
        if (i > -1) {
            int i2 = this.fInnerAnnotationDepth;
            int i3 = this.fDepth;
            if (i2 == i3) {
                this.fInnerAnnotationDepth = -1;
                this.schemaDOM.endAnnotationElement(qName);
                this.schemaDOM.endElement();
            } else if (i == i3) {
                this.fAnnotationDepth = -1;
                this.schemaDOM.endAnnotation(qName, this.fCurrentAnnotationElement);
                this.schemaDOM.endElement();
            } else {
                this.schemaDOM.endAnnotationElement(qName);
            }
        } else {
            String str3 = qName.uri;
            String str4 = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            if (str3 == str4 && this.fGenerateSyntheticAnnotation) {
                boolean zPop = this.fHasNonSchemaAttributes.pop();
                boolean zPop2 = this.fSawAnnotation.pop();
                if (zPop && !zPop2) {
                    String prefix = this.fNamespaceContext.getPrefix(str4);
                    if (prefix.length() == 0) {
                        str = SchemaSymbols.ELT_ANNOTATION;
                    } else {
                        str = prefix + ':' + SchemaSymbols.ELT_ANNOTATION;
                    }
                    this.schemaDOM.startAnnotation(str, this.fEmptyAttr, this.fNamespaceContext);
                    if (prefix.length() == 0) {
                        str2 = SchemaSymbols.ELT_DOCUMENTATION;
                    } else {
                        str2 = prefix + ':' + SchemaSymbols.ELT_DOCUMENTATION;
                    }
                    this.schemaDOM.startAnnotationElement(str2, this.fEmptyAttr);
                    this.schemaDOM.charactersRaw("SYNTHETIC_ANNOTATION");
                    this.schemaDOM.endSyntheticAnnotationElement(str2, false);
                    this.schemaDOM.endSyntheticAnnotationElement(str, true);
                }
            }
            this.schemaDOM.endElement();
        }
        this.fDepth--;
    }

    public Document getDocument() {
        return this.schemaDOM;
    }

    public boolean getFeature(String str) {
        return this.config.getFeature(str);
    }

    public Object getProperty(String str) {
        return this.config.getProperty(str);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.characters(xMLString);
        }
    }

    public void parse(XMLInputSource xMLInputSource) throws IOException {
        this.config.parse(xMLInputSource);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth > -1) {
            this.schemaDOM.processingInstruction(str, xMLString);
        }
    }

    public void reset() {
        ((SchemaParsingConfig) this.config).reset();
    }

    public void resetNodePool() {
        ((SchemaParsingConfig) this.config).resetNodePool();
    }

    public void setEntityResolver(XMLEntityResolver xMLEntityResolver) {
        this.config.setEntityResolver(xMLEntityResolver);
    }

    public void setFeature(String str, boolean z) {
        this.config.setFeature(str, z);
    }

    public void setProperty(String str, Object obj) {
        this.config.setProperty(str, obj);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
        if (this.fAnnotationDepth != -1) {
            this.schemaDOM.startAnnotationCDATA();
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
        this.fErrorReporter = (XMLErrorReporter) this.config.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fGenerateSyntheticAnnotation = this.config.getFeature(GENERATE_SYNTHETIC_ANNOTATION);
        this.fHasNonSchemaAttributes.clear();
        this.fSawAnnotation.clear();
        SchemaDOM schemaDOM = new SchemaDOM();
        this.schemaDOM = schemaDOM;
        this.fCurrentAnnotationElement = null;
        this.fAnnotationDepth = -1;
        this.fInnerAnnotationDepth = -1;
        this.fDepth = -1;
        this.fLocator = xMLLocator;
        this.fNamespaceContext = namespaceContext;
        schemaDOM.setDocumentURI(xMLLocator.getExpandedSystemId());
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultXMLDocumentHandler, com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        QName qName2;
        XMLAttributes xMLAttributes2;
        int i = this.fDepth + 1;
        this.fDepth = i;
        int i2 = this.fAnnotationDepth;
        if (i2 == -1) {
            String str = qName.uri;
            String str2 = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            if (str == str2 && qName.localpart == SchemaSymbols.ELT_ANNOTATION) {
                if (this.fGenerateSyntheticAnnotation) {
                    if (this.fSawAnnotation.size() > 0) {
                        this.fSawAnnotation.pop();
                    }
                    this.fSawAnnotation.push(true);
                }
                this.fAnnotationDepth = this.fDepth;
                this.schemaDOM.startAnnotation(qName, xMLAttributes, this.fNamespaceContext);
                this.fCurrentAnnotationElement = this.schemaDOM.startElement(qName, xMLAttributes, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
                return;
            }
            qName2 = qName;
            xMLAttributes2 = xMLAttributes;
            if (str == str2 && this.fGenerateSyntheticAnnotation) {
                this.fSawAnnotation.push(false);
                this.fHasNonSchemaAttributes.push(hasNonSchemaAttributes(qName2, xMLAttributes2));
            }
        } else {
            qName2 = qName;
            xMLAttributes2 = xMLAttributes;
            if (i != i2 + 1) {
                this.schemaDOM.startAnnotationElement(qName2, xMLAttributes2);
                return;
            } else {
                this.fInnerAnnotationDepth = i;
                this.schemaDOM.startAnnotationElement(qName2, xMLAttributes2);
            }
        }
        this.schemaDOM.startElement(qName2, xMLAttributes2, this.fLocator.getLineNumber(), this.fLocator.getColumnNumber(), this.fLocator.getCharacterOffset());
    }
}
