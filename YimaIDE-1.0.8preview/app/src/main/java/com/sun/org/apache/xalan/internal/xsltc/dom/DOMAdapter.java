package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.DOMEnhancedForDTM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class DOMAdapter implements DOM {
    private DOM _dom;
    private DOMEnhancedForDTM _enhancedDOM;
    private int _multiDOMMask;
    private String[] _namesArray;
    private String[] _namespaceArray;
    private int[] _typesArray;
    private String[] _urisArray;
    private short[] _mapping = null;
    private int[] _reverse = null;
    private short[] _NSmapping = null;
    private short[] _NSreverse = null;

    public DOMAdapter(DOM dom, String[] strArr, String[] strArr2, int[] iArr, String[] strArr3) {
        if (dom instanceof DOMEnhancedForDTM) {
            this._enhancedDOM = (DOMEnhancedForDTM) dom;
        }
        this._dom = dom;
        this._namesArray = strArr;
        this._urisArray = strArr2;
        this._typesArray = iArr;
        this._namespaceArray = strArr3;
    }

    private short[] getMapping() {
        DOMEnhancedForDTM dOMEnhancedForDTM;
        if (this._mapping == null && (dOMEnhancedForDTM = this._enhancedDOM) != null) {
            this._mapping = dOMEnhancedForDTM.getMapping(this._namesArray, this._urisArray, this._typesArray);
        }
        return this._mapping;
    }

    private short[] getNSMapping() {
        DOMEnhancedForDTM dOMEnhancedForDTM;
        if (this._NSmapping == null && (dOMEnhancedForDTM = this._enhancedDOM) != null) {
            this._NSmapping = dOMEnhancedForDTM.getNamespaceMapping(this._namespaceArray);
        }
        return this._NSmapping;
    }

    private short[] getNSReverse() {
        DOMEnhancedForDTM dOMEnhancedForDTM;
        if (this._NSreverse == null && (dOMEnhancedForDTM = this._enhancedDOM) != null) {
            this._NSreverse = dOMEnhancedForDTM.getReverseNamespaceMapping(this._namespaceArray);
        }
        return this._NSreverse;
    }

    private int[] getReverse() {
        DOMEnhancedForDTM dOMEnhancedForDTM;
        if (this._reverse == null && (dOMEnhancedForDTM = this._enhancedDOM) != null) {
            this._reverse = dOMEnhancedForDTM.getReverseMapping(this._namesArray, this._urisArray, this._typesArray);
        }
        return this._reverse;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void characters(int i, SerializationHandler serializationHandler) throws TransletException {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        if (dOMEnhancedForDTM != null) {
            dOMEnhancedForDTM.characters(i, serializationHandler);
        } else {
            this._dom.characters(i, serializationHandler);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void copy(int i, SerializationHandler serializationHandler) throws TransletException {
        this._dom.copy(i, serializationHandler);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getAttributeNode(int i, int i2) {
        return this._dom.getAttributeNode(getReverse()[i], i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getAxisIterator(int i) {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        return dOMEnhancedForDTM != null ? dOMEnhancedForDTM.getAxisIterator(i) : this._dom.getAxisIterator(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getChildren(int i) {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        return dOMEnhancedForDTM != null ? dOMEnhancedForDTM.getChildren(i) : this._dom.getChildren(i).setStartNode(i);
    }

    public DOM getDOMImpl() {
        return this._dom;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getDocument() {
        return this._dom.getDocument();
    }

    public String getDocumentURI() {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        return dOMEnhancedForDTM != null ? dOMEnhancedForDTM.getDocumentURI() : "";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Map<String, Integer> getElementsWithIDs() {
        return this._dom.getElementsWithIDs();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getExpandedTypeID(int i) {
        short[] mapping = getMapping();
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        if (dOMEnhancedForDTM != null) {
            return mapping[dOMEnhancedForDTM.getExpandedTypeID2(i)];
        }
        DOM dom = this._dom;
        return mapping != null ? mapping[dom.getExpandedTypeID(i)] : dom.getExpandedTypeID(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getIterator() {
        return this._dom.getIterator();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getLanguage(int i) {
        return this._dom.getLanguage(i);
    }

    public int getMultiDOMMask() {
        return this._multiDOMMask;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNSType(int i) {
        return this._dom.getNSType(i);
    }

    public String[] getNamesArray() {
        return this._namesArray;
    }

    public String[] getNamespaceArray() {
        return this._namespaceArray;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNamespaceAxisIterator(int i, int i2) {
        return this._dom.getNamespaceAxisIterator(i, getNSReverse()[i2]);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getNamespaceName(int i) {
        return i == -1 ? "" : this._dom.getNamespaceName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNamespaceType(int i) {
        return getNSMapping()[this._dom.getNSType(i)];
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNodeHandle(int i) {
        return this._dom.getNodeHandle(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getNodeIdent(int i) {
        return this._dom.getNodeIdent(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeName(int i) {
        return i == -1 ? "" : this._dom.getNodeName(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getNodeNameX(int i) {
        return i == -1 ? "" : this._dom.getNodeNameX(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNodeValueIterator(DTMAxisIterator dTMAxisIterator, int i, String str, boolean z) {
        return this._dom.getNodeValueIterator(dTMAxisIterator, i, str, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getNthDescendant(int i, int i2, boolean z) {
        return this._dom.getNthDescendant(getReverse()[i], i2, z);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public SerializationHandler getOutputDomBuilder() {
        return this._dom.getOutputDomBuilder();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public int getParent(int i) {
        return this._dom.getParent(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2) {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        return dOMEnhancedForDTM != null ? dOMEnhancedForDTM.getResultTreeFrag(i, i2) : this._dom.getResultTreeFrag(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public int getSize() {
        return this._dom.getSize();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getStringValue() {
        return this._dom.getStringValue();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getStringValueX(int i) {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        if (dOMEnhancedForDTM != null) {
            return dOMEnhancedForDTM.getStringValueX(i);
        }
        return i == -1 ? "" : this._dom.getStringValueX(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public DTMAxisIterator getTypedAxisIterator(int i, int i2) {
        int[] reverse = getReverse();
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        return dOMEnhancedForDTM != null ? dOMEnhancedForDTM.getTypedAxisIterator(i, reverse[i2]) : this._dom.getTypedAxisIterator(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator getTypedChildren(int i) {
        int[] reverse = getReverse();
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        return dOMEnhancedForDTM != null ? dOMEnhancedForDTM.getTypedChildren(reverse[i]) : this._dom.getTypedChildren(i);
    }

    public int[] getTypesArray() {
        return this._typesArray;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM, com.sun.org.apache.xml.internal.dtm.DTM
    public String getUnparsedEntityURI(String str) {
        return this._dom.getUnparsedEntityURI(str);
    }

    public String[] getUrisArray() {
        return this._urisArray;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isAttribute(int i) {
        return this._dom.isAttribute(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean isElement(int i) {
        return this._dom.isElement(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public boolean lessThan(int i, int i2) {
        return this._dom.lessThan(i, i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String lookupNamespace(int i, String str) throws TransletException {
        return this._dom.lookupNamespace(i, str);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(int i) {
        return this._dom.makeNode(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(int i) {
        return this._dom.makeNodeList(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DTMAxisIterator orderNodes(DTMAxisIterator dTMAxisIterator, int i) {
        return this._dom.orderNodes(dTMAxisIterator, i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void release() {
        this._dom.release();
    }

    public void setDocumentURI(String str) {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        if (dOMEnhancedForDTM != null) {
            dOMEnhancedForDTM.setDocumentURI(str);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setFilter(StripFilter stripFilter) {
    }

    public void setMultiDOMMask(int i) {
        this._multiDOMMask = i;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void setupMapping(String[] strArr, String[] strArr2, int[] iArr, String[] strArr3) {
        this._namesArray = strArr;
        this._urisArray = strArr2;
        this._typesArray = iArr;
        this._namespaceArray = strArr3;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String shallowCopy(int i, SerializationHandler serializationHandler) throws TransletException {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        return dOMEnhancedForDTM != null ? dOMEnhancedForDTM.shallowCopy(i, serializationHandler) : this._dom.shallowCopy(i, serializationHandler);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public void copy(DTMAxisIterator dTMAxisIterator, SerializationHandler serializationHandler) throws TransletException {
        this._dom.copy(dTMAxisIterator, serializationHandler);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public Node makeNode(DTMAxisIterator dTMAxisIterator) {
        return this._dom.makeNode(dTMAxisIterator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public NodeList makeNodeList(DTMAxisIterator dTMAxisIterator) {
        return this._dom.makeNodeList(dTMAxisIterator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public String getDocumentURI(int i) {
        return this._dom.getDocumentURI(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.DOM
    public DOM getResultTreeFrag(int i, int i2, boolean z) {
        DOMEnhancedForDTM dOMEnhancedForDTM = this._enhancedDOM;
        if (dOMEnhancedForDTM != null) {
            return dOMEnhancedForDTM.getResultTreeFrag(i, i2, z);
        }
        return this._dom.getResultTreeFrag(i, i2, z);
    }
}
