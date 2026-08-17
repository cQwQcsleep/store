package com.sun.xml.internal.stream.dtd;

import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.xml.internal.stream.dtd.nonvalidating.DTDGrammar;
import com.sun.xml.internal.stream.dtd.nonvalidating.XMLAttributeDecl;
import com.sun.xml.internal.stream.dtd.nonvalidating.XMLSimpleType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTDGrammarUtil {
    private static final boolean DEBUG_ATTRIBUTES = false;
    private static final boolean DEBUG_ELEMENT_CHILDREN = false;
    protected static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private StringBuilder fBuffer;
    private int fCurrentContentSpecType;
    private int fCurrentElementIndex;
    protected DTDGrammar fDTDGrammar;
    private boolean[] fElementContentState;
    private int fElementDepth;
    private boolean fInElementContent;
    private NamespaceContext fNamespaceContext;
    protected boolean fNamespaces;
    protected SymbolTable fSymbolTable;
    private XMLAttributeDecl fTempAttDecl;
    private QName fTempQName;

    public DTDGrammarUtil(DTDGrammar dTDGrammar, SymbolTable symbolTable) {
        this.fDTDGrammar = null;
        this.fSymbolTable = null;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fElementContentState = new boolean[8];
        this.fElementDepth = -1;
        this.fInElementContent = false;
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuilder();
        this.fNamespaceContext = null;
        this.fDTDGrammar = dTDGrammar;
        this.fSymbolTable = symbolTable;
    }

    private void ensureStackCapacity(int i) {
        boolean[] zArr = this.fElementContentState;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[i * 2];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.fElementContentState = zArr2;
        }
    }

    private String getAttributeTypeName(XMLAttributeDecl xMLAttributeDecl) {
        XMLSimpleType xMLSimpleType = xMLAttributeDecl.simpleType;
        switch (xMLSimpleType.type) {
            case 1:
                return xMLSimpleType.list ? XMLSymbols.fENTITIESSymbol : XMLSymbols.fENTITYSymbol;
            case 2:
                StringBuilder sb = new StringBuilder("(");
                for (int i = 0; i < xMLAttributeDecl.simpleType.enumeration.length; i++) {
                    if (i > 0) {
                        sb.append("|");
                    }
                    sb.append(xMLAttributeDecl.simpleType.enumeration[i]);
                }
                sb.append(')');
                return this.fSymbolTable.addSymbol(sb.toString());
            case 3:
                return XMLSymbols.fIDSymbol;
            case 4:
                return xMLSimpleType.list ? XMLSymbols.fIDREFSSymbol : XMLSymbols.fIDREFSymbol;
            case 5:
                return xMLSimpleType.list ? XMLSymbols.fNMTOKENSSymbol : XMLSymbols.fNMTOKENSymbol;
            case 6:
                return XMLSymbols.fNOTATIONSymbol;
            default:
                return XMLSymbols.fCDATASymbol;
        }
    }

    private boolean normalizeAttrValue(XMLAttributes xMLAttributes, int i) {
        String value = xMLAttributes.getValue(i);
        int length = value.length();
        char[] cArr = new char[length];
        this.fBuffer.setLength(0);
        value.getChars(0, value.length(), cArr, 0);
        boolean z = true;
        int i2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        for (int i3 = 0; i3 < length; i3++) {
            char c = cArr[i3];
            if (c == ' ') {
                if (z2) {
                    z3 = true;
                    z2 = false;
                }
                if (z3 && !z) {
                    this.fBuffer.append(c);
                    i2++;
                    z3 = false;
                }
            } else {
                this.fBuffer.append(c);
                i2++;
                z2 = true;
                z3 = false;
                z = false;
            }
        }
        if (i2 > 0) {
            int i4 = i2 - 1;
            if (this.fBuffer.charAt(i4) == ' ') {
                this.fBuffer.setLength(i4);
            }
        }
        String string = this.fBuffer.toString();
        xMLAttributes.setValue(i, string);
        return !value.equals(string);
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0087  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b7 A[SYNTHETIC] */
    public void addDTDDefaultAttrs(QName qName, XMLAttributes xMLAttributes) throws XNIException {
        DTDGrammar dTDGrammar;
        int i;
        int iIndexOf;
        int elementDeclIndex = this.fDTDGrammar.getElementDeclIndex(qName);
        if (elementDeclIndex == -1 || (dTDGrammar = this.fDTDGrammar) == null) {
            return;
        }
        int firstAttributeDeclIndex = dTDGrammar.getFirstAttributeDeclIndex(elementDeclIndex);
        while (true) {
            if (firstAttributeDeclIndex == -1) {
                break;
            }
            this.fDTDGrammar.getAttributeDecl(firstAttributeDeclIndex, this.fTempAttDecl);
            XMLAttributeDecl xMLAttributeDecl = this.fTempAttDecl;
            QName qName2 = xMLAttributeDecl.name;
            String strAddSymbol = qName2.prefix;
            String strAddSymbol2 = qName2.localpart;
            String strSubstring = qName2.rawname;
            String attributeTypeName = getAttributeTypeName(xMLAttributeDecl);
            XMLSimpleType xMLSimpleType = this.fTempAttDecl.simpleType;
            short s = xMLSimpleType.defaultType;
            String str = xMLSimpleType.defaultValue;
            if (str == null) {
                str = null;
            }
            boolean z = s == 2;
            if (attributeTypeName == XMLSymbols.fCDATASymbol && !z && str == null) {
                if (str == null) {
                    if (this.fNamespaces) {
                        strAddSymbol = this.fSymbolTable.addSymbol(strSubstring.substring(0, iIndexOf));
                        strAddSymbol2 = this.fSymbolTable.addSymbol(strSubstring.substring(iIndexOf + 1));
                    }
                    this.fTempQName.setValues(strAddSymbol, strAddSymbol2, strSubstring, this.fTempAttDecl.name.uri);
                    xMLAttributes.addAttribute(this.fTempQName, attributeTypeName, str);
                }
            } else if (this.fNamespaceContext == null || !strSubstring.startsWith("xmlns")) {
                int length = xMLAttributes.getLength();
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        if (xMLAttributes.getQName(i2) != strSubstring) {
                            i2++;
                        }
                    } else if (str == null) {
                        if (this.fNamespaces && (iIndexOf = strSubstring.indexOf(58)) != -1) {
                            strAddSymbol = this.fSymbolTable.addSymbol(strSubstring.substring(0, iIndexOf));
                            strAddSymbol2 = this.fSymbolTable.addSymbol(strSubstring.substring(iIndexOf + 1));
                        }
                        this.fTempQName.setValues(strAddSymbol, strAddSymbol2, strSubstring, this.fTempAttDecl.name.uri);
                        xMLAttributes.addAttribute(this.fTempQName, attributeTypeName, str);
                    }
                }
            } else {
                int iIndexOf2 = strSubstring.indexOf(58);
                if (iIndexOf2 != -1) {
                    strSubstring = strSubstring.substring(0, iIndexOf2);
                }
                String strAddSymbol3 = this.fSymbolTable.addSymbol(strSubstring);
                if (!((NamespaceSupport) this.fNamespaceContext).containsPrefixInCurrentContext(strAddSymbol3)) {
                    this.fNamespaceContext.declarePrefix(strAddSymbol3, str);
                }
            }
            firstAttributeDeclIndex = this.fDTDGrammar.getNextAttributeDeclIndex(firstAttributeDeclIndex);
        }
        int length2 = xMLAttributes.getLength();
        for (i = 0; i < length2; i++) {
            String qName3 = xMLAttributes.getQName(i);
            int firstAttributeDeclIndex2 = this.fDTDGrammar.getFirstAttributeDeclIndex(elementDeclIndex);
            while (firstAttributeDeclIndex2 != -1) {
                this.fDTDGrammar.getAttributeDecl(firstAttributeDeclIndex2, this.fTempAttDecl);
                XMLAttributeDecl xMLAttributeDecl2 = this.fTempAttDecl;
                if (xMLAttributeDecl2.name.rawname == qName3) {
                    String attributeTypeName2 = getAttributeTypeName(xMLAttributeDecl2);
                    xMLAttributes.setType(i, attributeTypeName2);
                    if (xMLAttributes.isSpecified(i) && attributeTypeName2 != XMLSymbols.fCDATASymbol) {
                        normalizeAttrValue(xMLAttributes, i);
                        break;
                    }
                    break;
                }
                firstAttributeDeclIndex2 = this.fDTDGrammar.getNextAttributeDeclIndex(firstAttributeDeclIndex2);
            }
        }
    }

    public void endCDATA(Augmentations augmentations) throws XNIException {
    }

    public void endElement(QName qName) throws XNIException {
        handleEndElement(qName);
    }

    public void handleEndElement(QName qName) throws XNIException {
        if (this.fDTDGrammar == null) {
            return;
        }
        int i = this.fElementDepth - 1;
        this.fElementDepth = i;
        if (i < -1) {
            f63.a("FWK008 Element stack underflow");
        } else {
            if (i >= 0) {
                this.fInElementContent = this.fElementContentState[i];
                return;
            }
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            this.fInElementContent = false;
        }
    }

    public void handleStartElement(QName qName, XMLAttributes xMLAttributes) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar == null) {
            this.fCurrentElementIndex = -1;
            this.fCurrentContentSpecType = -1;
            this.fInElementContent = false;
            return;
        }
        int elementDeclIndex = dTDGrammar.getElementDeclIndex(qName);
        this.fCurrentElementIndex = elementDeclIndex;
        this.fCurrentContentSpecType = this.fDTDGrammar.getContentSpecType(elementDeclIndex);
        addDTDDefaultAttrs(qName, xMLAttributes);
        this.fInElementContent = this.fCurrentContentSpecType == 3;
        int i = this.fElementDepth + 1;
        this.fElementDepth = i;
        ensureStackCapacity(i);
        this.fElementContentState[this.fElementDepth] = this.fInElementContent;
    }

    public boolean isIgnorableWhiteSpace(XMLString xMLString) {
        if (!isInElementContent()) {
            return false;
        }
        for (int i = xMLString.offset; i < xMLString.offset + xMLString.length; i++) {
            if (!XMLChar.isSpace(xMLString.ch[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isInElementContent() {
        return this.fInElementContent;
    }

    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        this.fDTDGrammar = null;
        this.fInElementContent = false;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fNamespaces = xMLComponentManager.getFeature("http://xml.org/sax/features/namespaces", true);
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fElementDepth = -1;
    }

    public void startCDATA(Augmentations augmentations) throws XNIException {
    }

    public void startElement(QName qName, XMLAttributes xMLAttributes) throws XNIException {
        handleStartElement(qName, xMLAttributes);
    }

    public DTDGrammarUtil(SymbolTable symbolTable) {
        this.fDTDGrammar = null;
        this.fSymbolTable = null;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fElementContentState = new boolean[8];
        this.fElementDepth = -1;
        this.fInElementContent = false;
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuilder();
        this.fNamespaceContext = null;
        this.fSymbolTable = symbolTable;
    }

    public DTDGrammarUtil(DTDGrammar dTDGrammar, SymbolTable symbolTable, NamespaceContext namespaceContext) {
        this.fDTDGrammar = null;
        this.fSymbolTable = null;
        this.fCurrentElementIndex = -1;
        this.fCurrentContentSpecType = -1;
        this.fElementContentState = new boolean[8];
        this.fElementDepth = -1;
        this.fInElementContent = false;
        this.fTempAttDecl = new XMLAttributeDecl();
        this.fTempQName = new QName();
        this.fBuffer = new StringBuilder();
        this.fDTDGrammar = dTDGrammar;
        this.fSymbolTable = symbolTable;
        this.fNamespaceContext = namespaceContext;
    }
}
