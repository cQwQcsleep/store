package com.sun.org.apache.xalan.internal.xsltc.runtime;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xalan.internal.xsltc.dom.AbsoluteIterator;
import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMAdapter;
import com.sun.org.apache.xalan.internal.xsltc.dom.MultiDOM;
import com.sun.org.apache.xalan.internal.xsltc.dom.SingletonIterator;
import com.sun.org.apache.xalan.internal.xsltc.dom.StepIterator;
import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeProxy;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.utils.XML11Char;
import com.sun.org.apache.xpath.internal.XPath;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import jdk.xml.internal.SecuritySupport;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class BasisLibrary {
    public static final String AXIS_SUPPORT_ERR = "AXIS_SUPPORT_ERR";
    public static final String CANT_RESOLVE_RELATIVE_URI_ERR = "CANT_RESOLVE_RELATIVE_URI_ERR";
    public static final String DATA_CONVERSION_ERR = "DATA_CONVERSION_ERR";
    public static final String DOM_ADAPTER_INIT_ERR = "DOM_ADAPTER_INIT_ERR";
    private static final int DOUBLE_FRACTION_DIGITS = 340;
    private static final String EMPTYSTRING = "";
    public static final String EQUALITY_EXPR_ERR = "EQUALITY_EXPR_ERR";
    public static final String ERROR_MESSAGES_KEY = "error-messages";
    public static final String EXTERNAL_FUNC_ERR = "EXTERNAL_FUNC_ERR";
    public static final String FORMAT_NUMBER_ERR = "FORMAT_NUMBER_ERR";
    public static final String INVALID_ARGUMENT_ERR = "INVALID_ARGUMENT_ERR";
    public static final String INVALID_NCNAME_ERR = "INVALID_NCNAME_ERR";
    public static final String INVALID_QNAME_ERR = "INVALID_QNAME_ERR";
    public static final String ITERATOR_CLONE_ERR = "ITERATOR_CLONE_ERR";
    public static final String NAMESPACES_SUPPORT_ERR = "NAMESPACES_SUPPORT_ERR";
    public static final String NAMESPACE_PREFIX_ERR = "NAMESPACE_PREFIX_ERR";
    public static final String PARSER_DTD_SUPPORT_ERR = "PARSER_DTD_SUPPORT_ERR";
    public static final String RUN_TIME_COPY_ERR = "RUN_TIME_COPY_ERR";
    public static final String RUN_TIME_INTERNAL_ERR = "RUN_TIME_INTERNAL_ERR";
    public static final String STRAY_ATTRIBUTE_ERR = "STRAY_ATTRIBUTE_ERR";
    public static final String STRAY_NAMESPACE_ERR = "STRAY_NAMESPACE_ERR";
    public static final String TYPED_AXIS_SUPPORT_ERR = "TYPED_AXIS_SUPPORT_ERR";
    public static final String UNALLOWED_EXTENSION_ELEMENT_ERR = "UNALLOWED_EXTENSION_ELEMENT_ERR";
    public static final String UNALLOWED_EXTENSION_FUNCTION_ERR = "UNALLOWED_EXTENSION_FUNCTION_ERR";
    public static final String UNKNOWN_TRANSLET_VERSION_ERR = "UNKNOWN_TRANSLET_VERSION_ERR";
    public static final String UNSUPPORTED_EXT_ERR = "UNSUPPORTED_EXT_ERR";
    public static final String UNSUPPORTED_XSL_ERR = "UNSUPPORTED_XSL_ERR";
    private static DecimalFormat defaultFormatter = null;
    private static final double lowerBounds = 0.001d;
    private static ResourceBundle m_bundle = null;
    private static final double upperBounds = 1.0E7d;
    private static DecimalFormat xpathFormatter;
    private static final ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<StringBuilder>() { // from class: com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary.1
        @Override // java.lang.ThreadLocal
        public StringBuilder initialValue() {
            return new StringBuilder();
        }
    };
    private static final ThreadLocal<StringBuffer> threadLocalStringBuffer = new ThreadLocal<StringBuffer>() { // from class: com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary.2
        @Override // java.lang.ThreadLocal
        public StringBuffer initialValue() {
            return new StringBuffer();
        }
    };
    private static final ThreadLocal<AtomicInteger> threadLocalPrefixIndex = new ThreadLocal<AtomicInteger>() { // from class: com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary.3
        @Override // java.lang.ThreadLocal
        public AtomicInteger initialValue() {
            return new AtomicInteger();
        }
    };
    private static String defaultPattern = "";
    private static FieldPosition _fieldPosition = new FieldPosition(0);
    private static char[] _characterArray = new char[32];

    static {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        DecimalFormat decimalFormat = numberFormat instanceof DecimalFormat ? (DecimalFormat) numberFormat : new DecimalFormat();
        defaultFormatter = decimalFormat;
        decimalFormat.setMaximumFractionDigits(DOUBLE_FRACTION_DIGITS);
        defaultFormatter.setMinimumFractionDigits(0);
        defaultFormatter.setMinimumIntegerDigits(1);
        defaultFormatter.setGroupingUsed(false);
        DecimalFormat decimalFormat2 = new DecimalFormat("", new DecimalFormatSymbols(Locale.US));
        xpathFormatter = decimalFormat2;
        decimalFormat2.setMaximumFractionDigits(DOUBLE_FRACTION_DIGITS);
        xpathFormatter.setMinimumFractionDigits(0);
        xpathFormatter.setMinimumIntegerDigits(1);
        xpathFormatter.setGroupingUsed(false);
        m_bundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xalan.internal.xsltc.runtime.ErrorMessages");
    }

    public static boolean booleanF(Object obj) {
        if (obj instanceof Double) {
            double dDoubleValue = ((Double) obj).doubleValue();
            return (dDoubleValue == XPath.MATCH_SCORE_QNAME || Double.isNaN(dDoubleValue)) ? false : true;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue() != XPath.MATCH_SCORE_QNAME;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof String) {
            return !((String) obj).equals("");
        }
        if (obj instanceof DTMAxisIterator) {
            return ((DTMAxisIterator) obj).reset().next() != -1;
        }
        if (obj instanceof Node) {
            return true;
        }
        if (obj instanceof DOM) {
            return !((DOM) obj).getStringValue().equals("");
        }
        runTimeError(INVALID_ARGUMENT_ERR, obj.getClass().getName(), "boolean()");
        return false;
    }

    public static void checkAttribQName(String str) {
        int iIndexOf = str.indexOf(58);
        int iLastIndexOf = str.lastIndexOf(58);
        String strSubstring = str.substring(iLastIndexOf + 1);
        if (iIndexOf > 0) {
            String strSubstring2 = str.substring(0, iIndexOf);
            if (iIndexOf != iLastIndexOf) {
                String strSubstring3 = str.substring(iIndexOf + 1, iLastIndexOf);
                if (!XML11Char.isXML11ValidNCName(strSubstring3)) {
                    runTimeError("INVALID_QNAME_ERR", strSubstring3 + ":" + strSubstring);
                }
            }
            if (!XML11Char.isXML11ValidNCName(strSubstring2)) {
                runTimeError("INVALID_QNAME_ERR", strSubstring2 + ":" + strSubstring);
            }
        }
        if (!XML11Char.isXML11ValidNCName(strSubstring) || strSubstring.equals("xmlns")) {
            runTimeError("INVALID_QNAME_ERR", strSubstring);
        }
    }

    public static void checkNCName(String str) {
        if (XML11Char.isXML11ValidNCName(str)) {
            return;
        }
        runTimeError("INVALID_NCNAME_ERR", str);
    }

    public static void checkQName(String str) {
        if (XML11Char.isXML11ValidQName(str)) {
            return;
        }
        runTimeError("INVALID_QNAME_ERR", str);
    }

    /* JADX WARN: Code duplicated, block: B:65:0x00ba  */
    public static boolean compare(Object obj, Object obj2, int i, DOM dom) {
        boolean zEquals;
        boolean zEquals2 = false;
        boolean z = hasSimpleType(obj) && hasSimpleType(obj2);
        if (i != 0 && i != 1) {
            if ((obj instanceof Node) || (obj2 instanceof Node)) {
                if (obj instanceof Boolean) {
                    obj2 = Boolean.valueOf(booleanF(obj2));
                    z = true;
                }
                if (obj2 instanceof Boolean) {
                    obj = Boolean.valueOf(booleanF(obj));
                    z = true;
                }
            }
            if (z) {
                if (i == 2) {
                    return numberF(obj, dom) > numberF(obj2, dom);
                }
                if (i == 3) {
                    return numberF(obj, dom) < numberF(obj2, dom);
                }
                if (i == 4) {
                    return numberF(obj, dom) >= numberF(obj2, dom);
                }
                if (i == 5) {
                    return numberF(obj, dom) <= numberF(obj2, dom);
                }
                runTimeError(RUN_TIME_INTERNAL_ERR, "compare()");
            }
        }
        if (z) {
            if ((obj instanceof Boolean) || (obj2 instanceof Boolean)) {
                if (booleanF(obj) == booleanF(obj2)) {
                    zEquals2 = true;
                }
                zEquals = zEquals2;
            } else if ((obj instanceof Double) || (obj2 instanceof Double) || (obj instanceof Integer) || (obj2 instanceof Integer)) {
                if (numberF(obj, dom) == numberF(obj2, dom)) {
                    zEquals2 = true;
                }
                zEquals = zEquals2;
            } else {
                zEquals = stringF(obj, dom).equals(stringF(obj2, dom));
            }
            return i == 1 ? !zEquals : zEquals;
        }
        if (obj instanceof Node) {
            obj = new SingletonIterator(((Node) obj).node);
        }
        if (obj2 instanceof Node) {
            obj2 = new SingletonIterator(((Node) obj2).node);
        }
        if (hasSimpleType(obj) || ((obj instanceof DOM) && (obj2 instanceof DTMAxisIterator))) {
            i = Operators.swapOp(i);
            Object obj3 = obj2;
            obj2 = obj;
            obj = obj3;
        }
        if (obj instanceof DOM) {
            if (obj2 instanceof Boolean) {
                return ((Boolean) obj2).booleanValue() == (i == 0);
            }
            String stringValue = ((DOM) obj).getStringValue();
            if (obj2 instanceof Number) {
                if (((Number) obj2).doubleValue() == stringToReal(stringValue)) {
                    zEquals2 = true;
                }
            } else if (obj2 instanceof String) {
                zEquals2 = stringValue.equals((String) obj2);
            } else if (obj2 instanceof DOM) {
                zEquals2 = stringValue.equals(((DOM) obj2).getStringValue());
            }
            return i == 1 ? !zEquals2 : zEquals2;
        }
        DTMAxisIterator dTMAxisIteratorReset = ((DTMAxisIterator) obj).reset();
        if (obj2 instanceof DTMAxisIterator) {
            return compare(dTMAxisIteratorReset, (DTMAxisIterator) obj2, i, dom);
        }
        if (obj2 instanceof String) {
            return compare(dTMAxisIteratorReset, (String) obj2, i, dom);
        }
        if (obj2 instanceof Number) {
            return compare(dTMAxisIteratorReset, ((Number) obj2).doubleValue(), i, dom);
        }
        if (obj2 instanceof Boolean) {
            return (dTMAxisIteratorReset.reset().next() != -1) == ((Boolean) obj2).booleanValue();
        }
        if (obj2 instanceof DOM) {
            return compare(dTMAxisIteratorReset, ((DOM) obj2).getStringValue(), i, dom);
        }
        if (obj2 == null) {
            return false;
        }
        runTimeError(INVALID_ARGUMENT_ERR, obj2.getClass().getName(), "compare()");
        return false;
    }

    private static boolean compareStrings(String str, String str2, int i, DOM dom) {
        if (i == 0) {
            return str.equals(str2);
        }
        if (i == 1) {
            return !str.equals(str2);
        }
        if (i == 2) {
            return numberF(str, dom) > numberF(str2, dom);
        }
        if (i == 3) {
            return numberF(str, dom) < numberF(str2, dom);
        }
        if (i == 4) {
            return numberF(str, dom) >= numberF(str2, dom);
        }
        if (i == 5) {
            return numberF(str, dom) <= numberF(str2, dom);
        }
        runTimeError(RUN_TIME_INTERNAL_ERR, "compare()");
        return false;
    }

    public static void consoleOutput(String str) {
        System.out.println(str);
    }

    public static void copy(Object obj, SerializationHandler serializationHandler, int i, DOM dom) {
        try {
            if (obj instanceof DTMAxisIterator) {
                dom.copy(((DTMAxisIterator) obj).reset(), serializationHandler);
                return;
            }
            if (obj instanceof Node) {
                dom.copy(((Node) obj).node, serializationHandler);
                return;
            }
            if (obj instanceof DOM) {
                DOM dom2 = (DOM) obj;
                dom2.copy(dom2.getDocument(), serializationHandler);
                return;
            }
            String string = obj.toString();
            int length = string.length();
            if (length > _characterArray.length) {
                _characterArray = new char[length];
            }
            string.getChars(0, length, _characterArray, 0);
            serializationHandler.characters(_characterArray, 0, length);
        } catch (SAXException unused) {
            runTimeError(RUN_TIME_COPY_ERR);
        }
    }

    public static int countF(DTMAxisIterator dTMAxisIterator) {
        return dTMAxisIterator.getLast();
    }

    public static String formatNumber(double d, String str, DecimalFormat decimalFormat) {
        if (decimalFormat == null) {
            decimalFormat = defaultFormatter;
        }
        try {
            StringBuffer stringBuffer = threadLocalStringBuffer.get();
            stringBuffer.setLength(0);
            if (str != defaultPattern) {
                decimalFormat.applyLocalizedPattern(str);
            }
            decimalFormat.format(d, stringBuffer, _fieldPosition);
            return stringBuffer.toString();
        } catch (IllegalArgumentException unused) {
            runTimeError(FORMAT_NUMBER_ERR, Double.toString(d), str);
            return "";
        }
    }

    public static String generatePrefix() {
        return com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NS + threadLocalPrefixIndex.get().getAndIncrement();
    }

    public static String generate_idF(int i) {
        if (i <= 0) {
            return "";
        }
        return "N" + i;
    }

    public static String getLocalName(String str) {
        int iLastIndexOf = str.lastIndexOf(58);
        if (iLastIndexOf >= 0) {
            str = str.substring(iLastIndexOf + 1);
        }
        int iLastIndexOf2 = str.lastIndexOf(64);
        return iLastIndexOf2 >= 0 ? str.substring(iLastIndexOf2 + 1) : str;
    }

    public static String getPrefix(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public static DTMAxisIterator getSingleNode(DTMAxisIterator dTMAxisIterator) {
        return new SingletonIterator(dTMAxisIterator.next());
    }

    public static int getStringLength(String str) {
        return str.codePointCount(0, str.length());
    }

    private static boolean hasSimpleType(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Integer) || (obj instanceof String) || (obj instanceof Node) || (obj instanceof DOM);
    }

    private static boolean isWhiteSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    public static String mapQNameToJavaName(String str) {
        return replace(str, ".-:/{}?#%*", new String[]{"$dot$", "$dash$", "$colon$", "$slash$", "", "$colon$", "$ques$", "$hash$", "$per$", "$aster$"});
    }

    public static String namespace_uriF(int i, DOM dom) {
        String nodeName = dom.getNodeName(i);
        int iLastIndexOf = nodeName.lastIndexOf(58);
        return iLastIndexOf >= 0 ? nodeName.substring(0, iLastIndexOf) : "";
    }

    public static DTMAxisIterator node2Iterator(final org.w3c.dom.Node node, Translet translet, DOM dom) {
        return nodeList2Iterator(new NodeList() { // from class: com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary.4
            @Override // org.w3c.dom.NodeList
            public int getLength() {
                return 1;
            }

            @Override // org.w3c.dom.NodeList
            public org.w3c.dom.Node item(int i) {
                if (i == 0) {
                    return node;
                }
                return null;
            }
        }, translet, dom);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0051  */
    /* JADX WARN: Code duplicated, block: B:26:0x005b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x007e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0090  */
    /* JADX WARN: Code duplicated, block: B:38:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:74:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x005d A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:36:0x007e, please report this as an issue */
    public static DTMAxisIterator nodeList2Iterator(NodeList nodeList, Translet translet, DOM dom) {
        DTMAxisIterator axisIterator;
        AbsoluteIterator absoluteIterator;
        DTMAxisIterator dTMAxisIterator;
        short nodeType;
        int[] iArr = new int[nodeList.getLength()];
        boolean z = dom instanceof MultiDOM;
        DTMAxisIterator axisIterator2 = null;
        DTMManager dTMManager = z ? ((MultiDOM) dom).getDTMManager() : null;
        Document documentNewDocument = null;
        int i = 0;
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            org.w3c.dom.Node nodeItem = nodeList.item(i2);
            if (nodeItem instanceof DTMNodeProxy) {
                DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) nodeItem;
                DTM dtm = dTMNodeProxy.getDTM();
                int dTMNodeNumber = dTMNodeProxy.getDTMNodeNumber();
                boolean z2 = dtm == dom;
                if (!z2 && dTMManager != null) {
                    try {
                        z2 = dtm == dTMManager.getDTM(dTMNodeNumber);
                    } catch (ArrayIndexOutOfBoundsException unused) {
                    }
                }
                if (!z2) {
                    iArr[i2] = -1;
                    nodeType = nodeItem.getNodeType();
                    if (documentNewDocument == null) {
                        if (!z) {
                            runTimeError(RUN_TIME_INTERNAL_ERR, "need MultiDOM");
                            return null;
                        }
                        try {
                            documentNewDocument = ((AbstractTranslet) translet).newDocument("", "__top__");
                        } catch (ParserConfigurationException e) {
                            runTimeError(RUN_TIME_INTERNAL_ERR, e.getMessage());
                            return null;
                        }
                    }
                    switch (nodeType) {
                        case 1:
                        case 3:
                        case 4:
                        case 5:
                        case 7:
                        case 8:
                            Element elementCreateElementNS = documentNewDocument.createElementNS(null, "__dummy__");
                            elementCreateElementNS.appendChild(documentNewDocument.importNode(nodeItem, true));
                            documentNewDocument.getDocumentElement().appendChild(elementCreateElementNS);
                            break;
                        case 2:
                            Element elementCreateElementNS2 = documentNewDocument.createElementNS(null, "__dummy__");
                            elementCreateElementNS2.setAttributeNodeNS((Attr) documentNewDocument.importNode(nodeItem, true));
                            documentNewDocument.getDocumentElement().appendChild(elementCreateElementNS2);
                            break;
                        case 6:
                        default:
                            runTimeError(RUN_TIME_INTERNAL_ERR, "Don't know how to convert node type " + ((int) nodeType));
                            continue;
                            continue;
                    }
                } else {
                    iArr[i2] = dTMNodeNumber;
                }
                i++;
            } else {
                iArr[i2] = -1;
                nodeType = nodeItem.getNodeType();
                if (documentNewDocument == null) {
                    if (!z) {
                        runTimeError(RUN_TIME_INTERNAL_ERR, "need MultiDOM");
                        return null;
                    }
                    documentNewDocument = ((AbstractTranslet) translet).newDocument("", "__top__");
                }
                switch (nodeType) {
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                        Element elementCreateElementNS3 = documentNewDocument.createElementNS(null, "__dummy__");
                        elementCreateElementNS3.appendChild(documentNewDocument.importNode(nodeItem, true));
                        documentNewDocument.getDocumentElement().appendChild(elementCreateElementNS3);
                        break;
                    case 2:
                        Element elementCreateElementNS4 = documentNewDocument.createElementNS(null, "__dummy__");
                        elementCreateElementNS4.setAttributeNodeNS((Attr) documentNewDocument.importNode(nodeItem, true));
                        documentNewDocument.getDocumentElement().appendChild(elementCreateElementNS4);
                        break;
                    case 6:
                    default:
                        runTimeError(RUN_TIME_INTERNAL_ERR, "Don't know how to convert node type " + ((int) nodeType));
                        continue;
                        continue;
                }
                i++;
            }
        }
        if (documentNewDocument != null) {
            DOM dom2 = (DOM) dTMManager.getDTM(new DOMSource(documentNewDocument), false, null, true, false);
            ((MultiDOM) dom).addDOMAdapter(new DOMAdapter(dom2, translet.getNamesArray(), translet.getUrisArray(), translet.getTypesArray(), translet.getNamespaceArray()));
            absoluteIterator = new AbsoluteIterator(new StepIterator(dom2.getAxisIterator(3), dom2.getAxisIterator(3)));
            absoluteIterator.setStartNode(0);
            axisIterator2 = dom2.getAxisIterator(3);
            axisIterator = dom2.getAxisIterator(2);
        } else {
            axisIterator = null;
            absoluteIterator = null;
        }
        int[] iArr2 = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < nodeList.getLength(); i4++) {
            int i5 = iArr[i4];
            if (i5 != -1) {
                iArr2[i3] = i5;
                i3++;
            } else {
                switch (nodeList.item(i4).getNodeType()) {
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                        dTMAxisIterator = axisIterator2;
                        break;
                    case 2:
                        dTMAxisIterator = axisIterator;
                        break;
                    case 6:
                    default:
                        throw new InternalRuntimeError("Mismatched cases");
                }
                if (dTMAxisIterator != null) {
                    dTMAxisIterator.setStartNode(absoluteIterator.next());
                    int next = dTMAxisIterator.next();
                    iArr2[i3] = next;
                    if (next == -1) {
                        throw new InternalRuntimeError("Expected element missing at " + i4);
                    }
                    if (dTMAxisIterator.next() != -1) {
                        throw new InternalRuntimeError("Too many elements at " + i4);
                    }
                    i3++;
                } else {
                    continue;
                }
            }
        }
        if (i3 == i) {
            return new ArrayNodeListIterator(iArr2);
        }
        throw new InternalRuntimeError("Nodes lost in second pass");
    }

    private static DTMAxisIterator nodeList2IteratorUsingHandleFromNode(NodeList nodeList, Translet translet, DOM dom) {
        int dTMNodeNumber;
        int length = nodeList.getLength();
        int[] iArr = new int[length];
        DTMManager dTMManager = dom instanceof MultiDOM ? ((MultiDOM) dom).getDTMManager() : null;
        for (int i = 0; i < length; i++) {
            org.w3c.dom.Node nodeItem = nodeList.item(i);
            if (dTMManager == null) {
                if (nodeItem instanceof DTMNodeProxy) {
                    DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) nodeItem;
                    if (dTMNodeProxy.getDTM() == dom) {
                        dTMNodeNumber = dTMNodeProxy.getDTMNodeNumber();
                    }
                }
                runTimeError(RUN_TIME_INTERNAL_ERR, "need MultiDOM");
                return null;
            }
            dTMNodeNumber = dTMManager.getDTMHandleFromNode(nodeItem);
            iArr[i] = dTMNodeNumber;
            System.out.println("Node " + i + " has handle 0x" + Integer.toString(dTMNodeNumber, 16));
        }
        return new ArrayNodeListIterator(iArr);
    }

    public static DTMAxisIterator nodesetF(Object obj) {
        if (obj instanceof DOM) {
            return new SingletonIterator(((DOM) obj).getDocument(), true);
        }
        if (obj instanceof DTMAxisIterator) {
            return (DTMAxisIterator) obj;
        }
        runTimeError("DATA_CONVERSION_ERR", "node-set", obj.getClass().getName());
        return null;
    }

    public static String normalize_spaceF(String str) {
        int length = str.length();
        StringBuilder sb = threadLocalStringBuilder.get();
        int i = 0;
        sb.setLength(0);
        while (i < length && isWhiteSpace(str.charAt(i))) {
            i++;
        }
        while (true) {
            if (i < length && !isWhiteSpace(str.charAt(i))) {
                sb.append(str.charAt(i));
                i++;
            } else {
                if (i == length) {
                    return sb.toString();
                }
                while (i < length && isWhiteSpace(str.charAt(i))) {
                    i++;
                }
                if (i < length) {
                    sb.append(' ');
                }
            }
        }
    }

    public static double numberF(Object obj, DOM dom) {
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        }
        if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                return 1.0d;
            }
            return XPath.MATCH_SCORE_QNAME;
        }
        if (obj instanceof String) {
            return stringToReal((String) obj);
        }
        if (obj instanceof DTMAxisIterator) {
            return stringToReal(dom.getStringValueX(((DTMAxisIterator) obj).reset().next()));
        }
        if (obj instanceof Node) {
            return stringToReal(dom.getStringValueX(((Node) obj).node));
        }
        if (obj instanceof DOM) {
            return stringToReal(((DOM) obj).getStringValue());
        }
        runTimeError(INVALID_ARGUMENT_ERR, obj.getClass().getName(), "number()");
        return XPath.MATCH_SCORE_QNAME;
    }

    public static String objectTypeF(Object obj) {
        if (obj instanceof String) {
            return "string";
        }
        if (obj instanceof Boolean) {
            return "boolean";
        }
        if (obj instanceof Number) {
            return "number";
        }
        if (obj instanceof DOM) {
            return "RTF";
        }
        return obj instanceof DTMAxisIterator ? "node-set" : "unknown";
    }

    @Deprecated
    public static int positionF(DTMAxisIterator dTMAxisIterator) {
        return dTMAxisIterator.isReverse() ? (dTMAxisIterator.getLast() - dTMAxisIterator.getPosition()) + 1 : dTMAxisIterator.getPosition();
    }

    public static int realToInt(double d) {
        return (int) d;
    }

    public static String realToString(double d) {
        double dAbs = Math.abs(d);
        if (dAbs >= lowerBounds && dAbs < upperBounds) {
            String string = Double.toString(d);
            int length = string.length();
            int i = length - 2;
            return (string.charAt(i) == '.' && string.charAt(length + (-1)) == '0') ? string.substring(0, i) : string;
        }
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            return Double.toString(d);
        }
        double d2 = d + XPath.MATCH_SCORE_QNAME;
        StringBuffer stringBuffer = threadLocalStringBuffer.get();
        stringBuffer.setLength(0);
        xpathFormatter.format(d2, stringBuffer, _fieldPosition);
        return stringBuffer.toString();
    }

    public static boolean referenceToBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        runTimeError("DATA_CONVERSION_ERR", obj.getClass().getName(), Boolean.TYPE);
        return false;
    }

    public static double referenceToDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        runTimeError("DATA_CONVERSION_ERR", obj.getClass().getName(), Double.TYPE);
        return XPath.MATCH_SCORE_QNAME;
    }

    public static long referenceToLong(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        runTimeError("DATA_CONVERSION_ERR", obj.getClass().getName(), Long.TYPE);
        return 0L;
    }

    public static org.w3c.dom.Node referenceToNode(Object obj, DOM dom) {
        if ((obj instanceof Node) || (obj instanceof DTMAxisIterator)) {
            return dom.makeNode(referenceToNodeSet(obj));
        }
        if (obj instanceof DOM) {
            DOM dom2 = (DOM) obj;
            return dom2.makeNode(dom2.getChildren(0));
        }
        runTimeError("DATA_CONVERSION_ERR", obj.getClass().getName(), "org.w3c.dom.Node");
        return null;
    }

    public static NodeList referenceToNodeList(Object obj, DOM dom) {
        if ((obj instanceof Node) || (obj instanceof DTMAxisIterator)) {
            return dom.makeNodeList(referenceToNodeSet(obj));
        }
        if (obj instanceof DOM) {
            return ((DOM) obj).makeNodeList(0);
        }
        runTimeError("DATA_CONVERSION_ERR", obj.getClass().getName(), "org.w3c.dom.NodeList");
        return null;
    }

    public static DTMAxisIterator referenceToNodeSet(Object obj) {
        if (obj instanceof Node) {
            return new SingletonIterator(((Node) obj).node);
        }
        if (obj instanceof DTMAxisIterator) {
            return ((DTMAxisIterator) obj).cloneIterator().reset();
        }
        runTimeError("DATA_CONVERSION_ERR", obj.getClass().getName(), "node-set");
        return null;
    }

    public static DOM referenceToResultTree(Object obj) {
        try {
            return (DOM) obj;
        } catch (IllegalArgumentException unused) {
            runTimeError("DATA_CONVERSION_ERR", "reference", obj.getClass().getName());
            return null;
        }
    }

    public static String referenceToString(Object obj, DOM dom) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof DTMAxisIterator) {
            return dom.getStringValueX(((DTMAxisIterator) obj).reset().next());
        }
        if (obj instanceof Node) {
            return dom.getStringValueX(((Node) obj).node);
        }
        if (obj instanceof DOM) {
            return ((DOM) obj).getStringValue();
        }
        runTimeError("DATA_CONVERSION_ERR", obj.getClass().getName(), String.class);
        return null;
    }

    public static String replace(String str, String str2, String[] strArr) {
        int length = str.length();
        StringBuilder sb = threadLocalStringBuilder.get();
        sb.setLength(0);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int iIndexOf = str2.indexOf(cCharAt);
            if (iIndexOf >= 0) {
                sb.append(strArr[iIndexOf]);
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static void resetPrefixIndex() {
        threadLocalPrefixIndex.get().set(0);
    }

    public static double roundF(double d) {
        if (d < -0.5d || d > XPath.MATCH_SCORE_QNAME) {
            return Math.floor(d + 0.5d);
        }
        if (d == XPath.MATCH_SCORE_QNAME) {
            return d;
        }
        return Double.isNaN(d) ? Double.NaN : -0.0d;
    }

    public static void runTimeError(String str, Object[] objArr) {
        throw new RuntimeException(MessageFormat.format(m_bundle.getString(str), objArr));
    }

    public static String startXslElement(String str, String str2, SerializationHandler serializationHandler, DOM dom, int i) {
        try {
            int iIndexOf = str.indexOf(58);
            if (iIndexOf > 0) {
                String strSubstring = str.substring(0, iIndexOf);
                if (str2 == null || str2.length() == 0) {
                    try {
                        str2 = dom.lookupNamespace(i, strSubstring);
                    } catch (RuntimeException unused) {
                        serializationHandler.flushPending();
                        str2 = serializationHandler.getNamespaceMappings().lookupNamespace(strSubstring);
                        if (str2 == null) {
                            runTimeError(NAMESPACE_PREFIX_ERR, strSubstring);
                        }
                    }
                }
                serializationHandler.startElement(str2, str.substring(iIndexOf + 1), str);
                serializationHandler.namespaceAfterStartElement(strSubstring, str2);
            } else {
                if (str2 != null && str2.length() > 0) {
                    String strGeneratePrefix = generatePrefix();
                    String str3 = strGeneratePrefix + ':' + str;
                    serializationHandler.startElement(str2, str3, str3);
                    serializationHandler.namespaceAfterStartElement(strGeneratePrefix, str2);
                    return str3;
                }
                serializationHandler.startElement(null, null, str);
            }
            return str;
        } catch (SAXException e) {
            f63.a(e.getMessage());
            return null;
        }
    }

    public static String stringF(Object obj, int i, DOM dom) {
        if (obj instanceof DTMAxisIterator) {
            return dom.getStringValueX(((DTMAxisIterator) obj).reset().next());
        }
        if (obj instanceof Node) {
            return dom.getStringValueX(((Node) obj).node);
        }
        if (obj instanceof DOM) {
            return ((DOM) obj).getStringValue();
        }
        if (!(obj instanceof Double)) {
            return obj != null ? obj.toString() : "";
        }
        String string = ((Double) obj).toString();
        int length = string.length();
        int i2 = length - 2;
        return (string.charAt(i2) == '.' && string.charAt(length + (-1)) == '0') ? string.substring(0, i2) : string;
    }

    public static int stringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static double stringToReal(String str) {
        try {
            return Double.valueOf(str).doubleValue();
        } catch (NumberFormatException unused) {
            return Double.NaN;
        }
    }

    public static String substringF(String str, double d, double d2) {
        if (!Double.isInfinite(d) && !Double.isNaN(d) && !Double.isNaN(d2) && d2 >= XPath.MATCH_SCORE_QNAME) {
            int iRound = ((int) Math.round(d)) - 1;
            int iRound2 = (int) Math.round(d2);
            int i = Double.isInfinite(d2) ? Integer.MAX_VALUE : iRound + iRound2;
            int stringLength = getStringLength(str);
            if (i >= 0 && iRound <= stringLength) {
                if (iRound < 0) {
                    iRound2 += iRound;
                    iRound = 0;
                }
                try {
                    int iOffsetByCodePoints = str.offsetByCodePoints(0, iRound);
                    return i > stringLength ? str.substring(iOffsetByCodePoints) : str.substring(iOffsetByCodePoints, str.offsetByCodePoints(iOffsetByCodePoints, iRound2));
                } catch (IndexOutOfBoundsException unused) {
                    runTimeError(RUN_TIME_INTERNAL_ERR, "substring()");
                    return null;
                }
            }
        }
        return "";
    }

    public static String substring_afterF(String str, String str2) {
        int iIndexOf = str.indexOf(str2);
        return iIndexOf >= 0 ? str.substring(iIndexOf + str2.length()) : "";
    }

    public static String substring_beforeF(String str, String str2) {
        int iIndexOf = str.indexOf(str2);
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) : "";
    }

    public static double sumF(DTMAxisIterator dTMAxisIterator, DOM dom) {
        double d = XPath.MATCH_SCORE_QNAME;
        while (true) {
            try {
                int next = dTMAxisIterator.next();
                if (next == -1) {
                    return d;
                }
                d += Double.parseDouble(dom.getStringValueX(next));
            } catch (NumberFormatException unused) {
                return Double.NaN;
            }
        }
    }

    public static String system_propertyF(String str) {
        if (str.equals("xsl:version")) {
            return "1.0";
        }
        if (str.equals("xsl:vendor")) {
            return "Apache Software Foundation (Xalan XSLTC)";
        }
        if (str.equals("xsl:vendor-url")) {
            return "http://xml.apache.org/xalan-j";
        }
        runTimeError(INVALID_ARGUMENT_ERR, str, "system-property()");
        return "";
    }

    public static boolean testLanguage(String str, DOM dom, int i) {
        String language = dom.getLanguage(i);
        if (language == null) {
            return false;
        }
        String lowerCase = language.toLowerCase();
        String lowerCase2 = str.toLowerCase();
        return lowerCase2.length() == 2 ? lowerCase.startsWith(lowerCase2) : lowerCase.equals(lowerCase2);
    }

    public static String translateF(String str, String str2, String str3) {
        int length = str3.length();
        int length2 = str2.length();
        int length3 = str.length();
        StringBuilder sb = threadLocalStringBuilder.get();
        sb.setLength(0);
        for (int i = 0; i < length3; i++) {
            char cCharAt = str.charAt(i);
            int i2 = 0;
            while (i2 < length2) {
                if (cCharAt == str2.charAt(i2)) {
                    if (i2 >= length) {
                        break;
                    }
                    sb.append(str3.charAt(i2));
                    break;
                }
                i2++;
            }
            if (i2 == length2) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static void unallowed_extension_elementF(String str) {
        runTimeError(UNALLOWED_EXTENSION_ELEMENT_ERR, str);
    }

    public static void unallowed_extension_functionF(String str) {
        runTimeError(UNALLOWED_EXTENSION_FUNCTION_ERR, str);
    }

    public static void unresolved_externalF(String str) {
        runTimeError(EXTERNAL_FUNC_ERR, str);
    }

    public static void unsupported_ElementF(String str, boolean z) {
        if (z) {
            runTimeError("UNSUPPORTED_EXT_ERR", str);
        } else {
            runTimeError("UNSUPPORTED_XSL_ERR", str);
        }
    }

    public static void runTimeError(String str) {
        throw new RuntimeException(m_bundle.getString(str));
    }

    public static void runTimeError(String str, Object obj) {
        runTimeError(str, new Object[]{obj});
    }

    public static void runTimeError(String str, Object obj, Object obj2) {
        runTimeError(str, new Object[]{obj, obj2});
    }

    public static String namespace_uriF(DTMAxisIterator dTMAxisIterator, DOM dom) {
        return namespace_uriF(dTMAxisIterator.next(), dom);
    }

    public static String replace(String str, char c, String str2) {
        return str.indexOf(c) < 0 ? str : replace(str, String.valueOf(c), new String[]{str2});
    }

    public static String normalize_spaceF(int i, DOM dom) {
        return normalize_spaceF(dom.getStringValueX(i));
    }

    public static String stringF(Object obj, DOM dom) {
        if (obj instanceof DTMAxisIterator) {
            return dom.getStringValueX(((DTMAxisIterator) obj).reset().next());
        }
        if (obj instanceof Node) {
            return dom.getStringValueX(((Node) obj).node);
        }
        if (obj instanceof DOM) {
            return ((DOM) obj).getStringValue();
        }
        return obj.toString();
    }

    public static String substringF(String str, double d) {
        if (Double.isNaN(d)) {
            return "";
        }
        int stringLength = getStringLength(str);
        int iRound = ((int) Math.round(d)) - 1;
        if (iRound > stringLength) {
            return "";
        }
        if (iRound < 1) {
            iRound = 0;
        }
        try {
            return str.substring(str.offsetByCodePoints(0, iRound));
        } catch (IndexOutOfBoundsException unused) {
            runTimeError(RUN_TIME_INTERNAL_ERR, "substring()");
            return null;
        }
    }

    public static String stringF(int i, DOM dom) {
        return dom.getStringValueX(i);
    }

    public static double numberF(int i, DOM dom) {
        return stringToReal(dom.getStringValueX(i));
    }

    public static boolean compare(int i, DTMAxisIterator dTMAxisIterator, int i2, DOM dom) {
        int next;
        int next2;
        if (i2 == 0) {
            int next3 = dTMAxisIterator.next();
            if (next3 == -1) {
                return false;
            }
            String stringValueX = dom.getStringValueX(i);
            while (i != next3 && !stringValueX.equals(dom.getStringValueX(next3))) {
                next3 = dTMAxisIterator.next();
                if (next3 == -1) {
                    return false;
                }
            }
            return true;
        }
        if (i2 == 1) {
            int next4 = dTMAxisIterator.next();
            if (next4 == -1) {
                return false;
            }
            String stringValueX2 = dom.getStringValueX(i);
            do {
                if (i != next4 && !stringValueX2.equals(dom.getStringValueX(next4))) {
                    return true;
                }
                next4 = dTMAxisIterator.next();
            } while (next4 != -1);
            return false;
        }
        if (i2 == 2) {
            do {
                next = dTMAxisIterator.next();
                if (next == -1) {
                    return false;
                }
            } while (next >= i);
            return true;
        }
        if (i2 != 3) {
            return false;
        }
        do {
            next2 = dTMAxisIterator.next();
            if (next2 == -1) {
                return false;
            }
        } while (next2 <= i);
        return true;
    }

    public static boolean compare(DTMAxisIterator dTMAxisIterator, double d, int i, DOM dom) {
        int next;
        int next2;
        int next3;
        int next4;
        int next5;
        int next6;
        if (i == 0) {
            do {
                next = dTMAxisIterator.next();
                if (next == -1) {
                    return false;
                }
            } while (numberF(dom.getStringValueX(next), dom) != d);
            return true;
        }
        if (i == 1) {
            do {
                next2 = dTMAxisIterator.next();
                if (next2 == -1) {
                    return false;
                }
            } while (numberF(dom.getStringValueX(next2), dom) == d);
            return true;
        }
        if (i == 2) {
            do {
                next3 = dTMAxisIterator.next();
                if (next3 == -1) {
                    return false;
                }
            } while (numberF(dom.getStringValueX(next3), dom) <= d);
            return true;
        }
        if (i == 3) {
            do {
                next4 = dTMAxisIterator.next();
                if (next4 == -1) {
                    return false;
                }
            } while (numberF(dom.getStringValueX(next4), dom) >= d);
            return true;
        }
        if (i == 4) {
            do {
                next5 = dTMAxisIterator.next();
                if (next5 == -1) {
                    return false;
                }
            } while (numberF(dom.getStringValueX(next5), dom) < d);
            return true;
        }
        if (i != 5) {
            runTimeError(RUN_TIME_INTERNAL_ERR, "compare()");
            return false;
        }
        do {
            next6 = dTMAxisIterator.next();
            if (next6 == -1) {
                return false;
            }
        } while (numberF(dom.getStringValueX(next6), dom) > d);
        return true;
    }

    public static boolean compare(DTMAxisIterator dTMAxisIterator, String str, int i, DOM dom) {
        int next;
        do {
            next = dTMAxisIterator.next();
            if (next == -1) {
                return false;
            }
        } while (!compareStrings(dom.getStringValueX(next), str, i, dom));
        return true;
    }

    public static boolean compare(DTMAxisIterator dTMAxisIterator, DTMAxisIterator dTMAxisIterator2, int i, DOM dom) {
        dTMAxisIterator.reset();
        while (true) {
            int next = dTMAxisIterator.next();
            if (next == -1) {
                return false;
            }
            String stringValueX = dom.getStringValueX(next);
            dTMAxisIterator2.reset();
            while (true) {
                int next2 = dTMAxisIterator2.next();
                if (next2 != -1) {
                    if (next == next2) {
                        if (i == 0) {
                            return true;
                        }
                        if (i == 1) {
                            continue;
                        }
                    }
                    if (compareStrings(stringValueX, dom.getStringValueX(next2), i, dom)) {
                        return true;
                    }
                }
            }
        }
    }
}
