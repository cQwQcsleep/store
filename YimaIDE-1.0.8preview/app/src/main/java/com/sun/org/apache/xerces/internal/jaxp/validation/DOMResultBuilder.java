package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.dom.AttrImpl;
import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.dom.DocumentTypeImpl;
import com.sun.org.apache.xerces.internal.dom.ElementImpl;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.EntityImpl;
import com.sun.org.apache.xerces.internal.dom.NotationImpl;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class DOMResultBuilder implements DOMDocumentHandler {
    private static final int[] kidOK = {0, 442, 40, 0, 0, 442, 442, 0, 0, WinError.ERROR_CLASS_ALREADY_EXISTS, 0, 442, 0};
    private Node fCurrentNode;
    private Document fDocument;
    private CoreDocumentImpl fDocumentImpl;
    private Node fFragmentRoot;
    private boolean fIgnoreChars;
    private Node fNextSibling;
    private boolean fStorePSVI;
    private Node fTarget;
    private final List<Node> fTargetChildren = new ArrayList();
    private final QName fAttributeQName = new QName();

    private void append(Node node) throws XNIException {
        Node node2 = this.fCurrentNode;
        if (node2 != null) {
            node2.appendChild(node);
        } else {
            if ((kidOK[this.fTarget.getNodeType()] & (1 << node.getNodeType())) == 0) {
                throw new XNIException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "HIERARCHY_REQUEST_ERR", null));
            }
            this.fTargetChildren.add(node);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void cdata(CDATASection cDATASection) throws XNIException {
        append(this.fDocument.createCDATASection(cDATASection.getNodeValue()));
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        if (this.fIgnoreChars) {
            return;
        }
        append(this.fDocument.createTextNode(xMLString.toString()));
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void comment(Comment comment) throws XNIException {
        append(this.fDocument.createComment(comment.getNodeValue()));
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void doctypeDecl(DocumentType documentType) throws XNIException {
        CoreDocumentImpl coreDocumentImpl = this.fDocumentImpl;
        if (coreDocumentImpl != null) {
            DocumentType documentTypeCreateDocumentType = coreDocumentImpl.createDocumentType(documentType.getName(), documentType.getPublicId(), documentType.getSystemId());
            String internalSubset = documentType.getInternalSubset();
            if (internalSubset != null) {
                ((DocumentTypeImpl) documentTypeCreateDocumentType).setInternalSubset(internalSubset);
            }
            NamedNodeMap entities = documentType.getEntities();
            NamedNodeMap entities2 = documentTypeCreateDocumentType.getEntities();
            int length = entities.getLength();
            for (int i = 0; i < length; i++) {
                Entity entity = (Entity) entities.item(i);
                EntityImpl entityImpl = (EntityImpl) this.fDocumentImpl.createEntity(entity.getNodeName());
                entityImpl.setPublicId(entity.getPublicId());
                entityImpl.setSystemId(entity.getSystemId());
                entityImpl.setNotationName(entity.getNotationName());
                entities2.setNamedItem(entityImpl);
            }
            NamedNodeMap notations = documentType.getNotations();
            NamedNodeMap notations2 = documentTypeCreateDocumentType.getNotations();
            int length2 = notations.getLength();
            for (int i2 = 0; i2 < length2; i2++) {
                Notation notation = (Notation) notations.item(i2);
                NotationImpl notationImpl = (NotationImpl) this.fDocumentImpl.createNotation(notation.getNodeName());
                notationImpl.setPublicId(notation.getPublicId());
                notationImpl.setSystemId(notation.getSystemId());
                notations2.setNamedItem(notationImpl);
            }
            append(documentTypeCreateDocumentType);
        }
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
        Node node = this.fNextSibling;
        List<Node> list = this.fTargetChildren;
        if (node == null) {
            Iterator<Node> it = list.iterator();
            while (it.hasNext()) {
                this.fTarget.appendChild(it.next());
            }
            return;
        }
        Iterator<Node> it2 = list.iterator();
        while (it2.hasNext()) {
            this.fTarget.insertBefore(it2.next(), this.fNextSibling);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        ElementPSVI elementPSVI;
        if (augmentations != null && this.fDocumentImpl != null && (elementPSVI = (ElementPSVI) augmentations.getItem(Constants.ELEMENT_PSVI)) != null) {
            if (this.fStorePSVI) {
                ((PSVIElementNSImpl) this.fCurrentNode).setPSVI(elementPSVI);
            }
            XSTypeDefinition memberTypeDefinition = elementPSVI.getMemberTypeDefinition();
            if (memberTypeDefinition == null) {
                memberTypeDefinition = elementPSVI.getTypeDefinition();
            }
            ((ElementNSImpl) this.fCurrentNode).setType(memberTypeDefinition);
        }
        Node node = this.fCurrentNode;
        if (node != this.fFragmentRoot) {
            this.fCurrentNode = node.getParentNode();
        } else {
            this.fCurrentNode = null;
            this.fFragmentRoot = null;
        }
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

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void processingInstruction(ProcessingInstruction processingInstruction) throws XNIException {
        append(this.fDocument.createProcessingInstruction(processingInstruction.getTarget(), processingInstruction.getData()));
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void setDOMResult(DOMResult dOMResult) {
        this.fCurrentNode = null;
        this.fFragmentRoot = null;
        this.fIgnoreChars = false;
        this.fTargetChildren.clear();
        if (dOMResult == null) {
            this.fTarget = null;
            this.fNextSibling = null;
            this.fDocument = null;
            this.fDocumentImpl = null;
            this.fStorePSVI = false;
            return;
        }
        this.fTarget = dOMResult.getNode();
        this.fNextSibling = dOMResult.getNextSibling();
        short nodeType = this.fTarget.getNodeType();
        Node node = this.fTarget;
        Document ownerDocument = nodeType == 9 ? (Document) node : node.getOwnerDocument();
        this.fDocument = ownerDocument;
        this.fDocumentImpl = ownerDocument instanceof CoreDocumentImpl ? (CoreDocumentImpl) ownerDocument : null;
        this.fStorePSVI = ownerDocument instanceof PSVIDocumentImpl;
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
        Element elementCreateElementNS;
        int length = xMLAttributes.getLength();
        CoreDocumentImpl coreDocumentImpl = this.fDocumentImpl;
        int i = 0;
        if (coreDocumentImpl == null) {
            elementCreateElementNS = this.fDocument.createElementNS(qName.uri, qName.rawname);
            while (i < length) {
                xMLAttributes.getName(i, this.fAttributeQName);
                QName qName2 = this.fAttributeQName;
                elementCreateElementNS.setAttributeNS(qName2.uri, qName2.rawname, xMLAttributes.getValue(i));
                i++;
            }
        } else {
            elementCreateElementNS = coreDocumentImpl.createElementNS(qName.uri, qName.rawname, qName.localpart);
            while (i < length) {
                xMLAttributes.getName(i, this.fAttributeQName);
                CoreDocumentImpl coreDocumentImpl2 = this.fDocumentImpl;
                QName qName3 = this.fAttributeQName;
                AttrImpl attrImpl = (AttrImpl) coreDocumentImpl2.createAttributeNS(qName3.uri, qName3.rawname, qName3.localpart);
                attrImpl.setValue(xMLAttributes.getValue(i));
                AttributePSVI attributePSVI = (AttributePSVI) xMLAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
                if (attributePSVI != null) {
                    if (this.fStorePSVI) {
                        ((PSVIAttrNSImpl) attrImpl).setPSVI(attributePSVI);
                    }
                    XSSimpleTypeDefinition memberTypeDefinition = attributePSVI.getMemberTypeDefinition();
                    if (memberTypeDefinition == null) {
                        XSTypeDefinition typeDefinition = attributePSVI.getTypeDefinition();
                        if (typeDefinition != null) {
                            attrImpl.setType(typeDefinition);
                            if (((XSSimpleType) typeDefinition).isIDType()) {
                                ((ElementImpl) elementCreateElementNS).setIdAttributeNode(attrImpl, true);
                            }
                        }
                    } else {
                        attrImpl.setType(memberTypeDefinition);
                        if (((XSSimpleType) memberTypeDefinition).isIDType()) {
                            ((ElementImpl) elementCreateElementNS).setIdAttributeNode(attrImpl, true);
                        }
                    }
                }
                attrImpl.setSpecified(xMLAttributes.isSpecified(i));
                elementCreateElementNS.setAttributeNode(attrImpl);
                i++;
            }
        }
        append(elementCreateElementNS);
        this.fCurrentNode = elementCreateElementNS;
        if (this.fFragmentRoot == null) {
            this.fFragmentRoot = elementCreateElementNS;
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

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.jaxp.validation.DOMDocumentHandler
    public void characters(Text text) throws XNIException {
        append(this.fDocument.createTextNode(text.getNodeValue()));
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
    }
}
