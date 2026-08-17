package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.dom.AttrImpl;
import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import com.sun.org.apache.xerces.internal.dom.ElementImpl;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import com.sun.org.apache.xerces.internal.dom.PSVIDocumentImpl;
import com.sun.org.apache.xerces.internal.dom.PSVIElementNSImpl;
import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class DOMResultAugmentor implements DOMDocumentHandler {
    private final QName fAttributeQName = new QName();
    private DOMValidatorHelper fDOMValidatorHelper;
    private Document fDocument;
    private CoreDocumentImpl fDocumentImpl;
    private boolean fIgnoreChars;
    private boolean fStorePSVI;

    public DOMResultAugmentor(DOMValidatorHelper dOMValidatorHelper) {
        this.fDOMValidatorHelper = dOMValidatorHelper;
    }

    private boolean processAttributePSVI(AttrImpl attrImpl, AttributePSVI attributePSVI) {
        if (this.fStorePSVI) {
            ((PSVIAttrNSImpl) attrImpl).setPSVI(attributePSVI);
        }
        XSSimpleTypeDefinition memberTypeDefinition = attributePSVI.getMemberTypeDefinition();
        if (memberTypeDefinition != null) {
            attrImpl.setType(memberTypeDefinition);
            return ((XSSimpleType) memberTypeDefinition).isIDType();
        }
        XSTypeDefinition typeDefinition = attributePSVI.getTypeDefinition();
        if (typeDefinition == null) {
            return false;
        }
        attrImpl.setType(typeDefinition);
        return ((XSSimpleType) typeDefinition).isIDType();
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void cdata(CDATASection cDATASection) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fIgnoreChars) {
            return;
        }
        ((Element) this.fDOMValidatorHelper.getCurrentElement()).appendChild(this.fDocument.createTextNode(xMLString.toString()));
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void comment(Comment comment) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void doctypeDecl(DocumentType documentType) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        startElement(qName, xMLAttributes, augmentations);
        endElement(qName, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        ElementPSVI elementPSVI;
        Node currentElement = this.fDOMValidatorHelper.getCurrentElement();
        if (augmentations == null || this.fDocumentImpl == null || (elementPSVI = (ElementPSVI) augmentations.getItem(Constants.ELEMENT_PSVI)) == null) {
            return;
        }
        if (this.fStorePSVI) {
            ((PSVIElementNSImpl) currentElement).setPSVI(elementPSVI);
        }
        XSTypeDefinition memberTypeDefinition = elementPSVI.getMemberTypeDefinition();
        if (memberTypeDefinition == null) {
            memberTypeDefinition = elementPSVI.getTypeDefinition();
        }
        ((ElementNSImpl) currentElement).setType(memberTypeDefinition);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public XMLDocumentSource getDocumentSource() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        characters(xMLString, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void processingInstruction(ProcessingInstruction processingInstruction) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void setDOMResult(DOMResult dOMResult) {
        this.fIgnoreChars = false;
        if (dOMResult == null) {
            this.fDocument = null;
            this.fDocumentImpl = null;
            this.fStorePSVI = false;
        } else {
            Node node = dOMResult.getNode();
            Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
            this.fDocument = ownerDocument;
            this.fDocumentImpl = ownerDocument instanceof CoreDocumentImpl ? (CoreDocumentImpl) ownerDocument : null;
            this.fStorePSVI = ownerDocument instanceof PSVIDocumentImpl;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void setIgnoringCharacters(boolean z) {
        this.fIgnoreChars = z;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        Element element = (Element) this.fDOMValidatorHelper.getCurrentElement();
        NamedNodeMap attributes = element.getAttributes();
        int length = attributes.getLength();
        if (this.fDocumentImpl != null) {
            for (int i = 0; i < length; i++) {
                AttrImpl attrImpl = (AttrImpl) attributes.item(i);
                AttributePSVI attributePSVI = (AttributePSVI) xMLAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
                if (attributePSVI != null && processAttributePSVI(attrImpl, attributePSVI)) {
                    ((ElementImpl) element).setIdAttributeNode(attrImpl, true);
                }
            }
        }
        int length2 = xMLAttributes.getLength();
        if (length2 > length) {
            if (this.fDocumentImpl == null) {
                while (length < length2) {
                    xMLAttributes.getName(length, this.fAttributeQName);
                    QName qName2 = this.fAttributeQName;
                    element.setAttributeNS(qName2.uri, qName2.rawname, xMLAttributes.getValue(length));
                    length++;
                }
                return;
            }
            while (length < length2) {
                xMLAttributes.getName(length, this.fAttributeQName);
                CoreDocumentImpl coreDocumentImpl = this.fDocumentImpl;
                QName qName3 = this.fAttributeQName;
                AttrImpl attrImpl2 = (AttrImpl) coreDocumentImpl.createAttributeNS(qName3.uri, qName3.rawname, qName3.localpart);
                attrImpl2.setValue(xMLAttributes.getValue(length));
                AttributePSVI attributePSVI2 = (AttributePSVI) xMLAttributes.getAugmentations(length).getItem(Constants.ATTRIBUTE_PSVI);
                if (attributePSVI2 != null && processAttributePSVI(attrImpl2, attributePSVI2)) {
                    ((ElementImpl) element).setIdAttributeNode(attrImpl2, true);
                }
                attrImpl2.setSpecified(false);
                element.setAttributeNode(attrImpl2);
                length++;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void characters(Text text) throws XNIException {
    }
}
