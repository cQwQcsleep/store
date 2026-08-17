package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.validation.ValidationState;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDocumentInfo {
    protected boolean fAreLocalAttributesQualified;
    protected boolean fAreLocalElementsQualified;
    protected XSAttributeChecker fAttrChecker;
    protected short fBlockDefault;
    protected short fFinalDefault;
    protected boolean fIsChameleonSchema;
    protected SchemaNamespaceSupport fNamespaceSupport;
    protected SchemaNamespaceSupport fNamespaceSupportRoot;
    protected Object[] fSchemaAttrs;
    protected Element fSchemaElement;
    SymbolTable fSymbolTable;
    String fTargetNamespace;
    protected Stack<SchemaNamespaceSupport> SchemaNamespaceSupportStack = new Stack<>();
    List<String> fImportedNS = new ArrayList();
    protected ValidationState fValidationContext = new ValidationState();
    protected XSAnnotationInfo fAnnotations = null;
    private List<String> fReportedTNS = null;

    public XSDocumentInfo(Element element, XSAttributeChecker xSAttributeChecker, SymbolTable symbolTable) throws XMLSchemaException {
        this.fSymbolTable = null;
        this.fSchemaElement = element;
        initNamespaceSupport(element);
        this.fIsChameleonSchema = false;
        this.fSymbolTable = symbolTable;
        this.fAttrChecker = xSAttributeChecker;
        if (element != null) {
            Object[] objArrCheckAttributes = xSAttributeChecker.checkAttributes(element, true, this);
            this.fSchemaAttrs = objArrCheckAttributes;
            if (objArrCheckAttributes == null) {
                throw new XMLSchemaException(null, null);
            }
            this.fAreLocalAttributesQualified = ((XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_AFORMDEFAULT]).intValue() == 1;
            this.fAreLocalElementsQualified = ((XInt) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_EFORMDEFAULT]).intValue() == 1;
            this.fBlockDefault = ((XInt) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_BLOCKDEFAULT]).shortValue();
            this.fFinalDefault = ((XInt) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_FINALDEFAULT]).shortValue();
            String str = (String) this.fSchemaAttrs[XSAttributeChecker.ATTIDX_TARGETNAMESPACE];
            this.fTargetNamespace = str;
            if (str != null) {
                this.fTargetNamespace = symbolTable.addSymbol(str);
            }
            this.fNamespaceSupportRoot = new SchemaNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setNamespaceSupport(this.fNamespaceSupport);
            this.fValidationContext.setSymbolTable(symbolTable);
        }
    }

    private void initNamespaceSupport(Element element) {
        SchemaNamespaceSupport schemaNamespaceSupport = new SchemaNamespaceSupport();
        this.fNamespaceSupport = schemaNamespaceSupport;
        schemaNamespaceSupport.reset();
        for (Node parentNode = element.getParentNode(); parentNode != null && parentNode.getNodeType() == 1 && !parentNode.getNodeName().equals("DOCUMENT_NODE"); parentNode = parentNode.getParentNode()) {
            NamedNodeMap attributes = ((Element) parentNode).getAttributes();
            int length = attributes != null ? attributes.getLength() : 0;
            for (int i = 0; i < length; i++) {
                Attr attr = (Attr) attributes.item(i);
                String namespaceURI = attr.getNamespaceURI();
                if (namespaceURI != null && namespaceURI.equals("http://www.w3.org/2000/xmlns/")) {
                    String strIntern = attr.getLocalName().intern();
                    if (strIntern == "xmlns") {
                        strIntern = "";
                    }
                    if (this.fNamespaceSupport.getURI(strIntern) == null) {
                        this.fNamespaceSupport.declarePrefix(strIntern, attr.getValue().intern());
                    }
                }
            }
        }
    }

    public void addAllowedNS(String str) {
        List<String> list = this.fImportedNS;
        if (str == null) {
            str = "";
        }
        list.add(str);
    }

    public void addAnnotation(XSAnnotationInfo xSAnnotationInfo) {
        xSAnnotationInfo.next = this.fAnnotations;
        this.fAnnotations = xSAnnotationInfo;
    }

    public void backupNSSupport(SchemaNamespaceSupport schemaNamespaceSupport) {
        this.SchemaNamespaceSupportStack.push(this.fNamespaceSupport);
        if (schemaNamespaceSupport == null) {
            schemaNamespaceSupport = this.fNamespaceSupportRoot;
        }
        SchemaNamespaceSupport schemaNamespaceSupport2 = new SchemaNamespaceSupport(schemaNamespaceSupport);
        this.fNamespaceSupport = schemaNamespaceSupport2;
        this.fValidationContext.setNamespaceSupport(schemaNamespaceSupport2);
    }

    public XSAnnotationInfo getAnnotations() {
        return this.fAnnotations;
    }

    public Object[] getSchemaAttrs() {
        return this.fSchemaAttrs;
    }

    public boolean isAllowedNS(String str) {
        List<String> list = this.fImportedNS;
        if (str == null) {
            str = "";
        }
        return list.contains(str);
    }

    public final boolean needReportTNSError(String str) {
        List<String> list = this.fReportedTNS;
        if (list == null) {
            this.fReportedTNS = new ArrayList();
        } else if (list.contains(str)) {
            return false;
        }
        this.fReportedTNS.add(str);
        return true;
    }

    public void removeAnnotations() {
        this.fAnnotations = null;
    }

    public void restoreNSSupport() {
        SchemaNamespaceSupport schemaNamespaceSupportPop = this.SchemaNamespaceSupportStack.pop();
        this.fNamespaceSupport = schemaNamespaceSupportPop;
        this.fValidationContext.setNamespaceSupport(schemaNamespaceSupportPop);
    }

    public void returnSchemaAttrs() {
        this.fAttrChecker.returnAttrArray(this.fSchemaAttrs, null);
        this.fSchemaAttrs = null;
    }

    public String toString() {
        if (this.fTargetNamespace == null) {
            return "no targetNamspace";
        }
        return "targetNamespace is " + this.fTargetNamespace;
    }
}
