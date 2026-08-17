package com.sun.org.apache.xerces.internal.impl;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import com.sun.org.apache.xerces.internal.util.AugmentationsImpl;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLAttributesIteratorImpl;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLLimitAnalyzer;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.xml.internal.stream.XMLBufferListener;
import com.sun.xml.internal.stream.XMLEntityStorage;
import com.sun.xml.internal.stream.dtd.DTDGrammarUtil;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDocumentFragmentScannerImpl extends XMLScanner implements XMLDocumentScanner, XMLComponent, XMLEntityHandler, XMLBufferListener {
    private static final char[] CDATA;
    protected static final String CREATE_ENTITY_REF_NODES = "http://apache.org/xml/features/dom/create-entity-ref-nodes";
    protected static final boolean DEBUG = false;
    private static final boolean DEBUG_DISPATCHER = false;
    private static final boolean DEBUG_SCANNER_STATE = false;
    static final boolean DEBUG_SKIP_ALGORITHM = false;
    protected static final boolean DEBUG_START_END_ELEMENT = false;
    static final short ELEMENT_ARRAY_LENGTH = 200;
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    static final String EXTERNAL_ACCESS_DEFAULT = "all";
    private static final Boolean[] FEATURE_DEFAULTS;
    static final short MAX_DEPTH_LIMIT = 5;
    static final short MAX_POINTER_AT_A_DEPTH = 4;
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final int SCANNER_STATE_ATTRIBUTE = 29;
    protected static final int SCANNER_STATE_ATTRIBUTE_VALUE = 30;
    protected static final int SCANNER_STATE_BUILT_IN_REFS = 41;
    protected static final int SCANNER_STATE_CDATA = 35;
    protected static final int SCANNER_STATE_CHARACTER_DATA = 37;
    protected static final int SCANNER_STATE_CHAR_REFERENCE = 40;
    protected static final int SCANNER_STATE_COMMENT = 27;
    protected static final int SCANNER_STATE_CONTENT = 22;
    protected static final int SCANNER_STATE_DOCTYPE = 24;
    protected static final int SCANNER_STATE_END_ELEMENT_TAG = 39;
    protected static final int SCANNER_STATE_END_OF_INPUT = 33;
    protected static final int SCANNER_STATE_PI = 23;
    protected static final int SCANNER_STATE_REFERENCE = 28;
    protected static final int SCANNER_STATE_ROOT_ELEMENT = 26;
    protected static final int SCANNER_STATE_START_ELEMENT_TAG = 38;
    protected static final int SCANNER_STATE_START_OF_MARKUP = 21;
    protected static final int SCANNER_STATE_TERMINATED = 34;
    protected static final int SCANNER_STATE_TEXT_DECL = 36;
    protected static final int SCANNER_STATE_XML_DECL = 25;
    protected static final String STANDARD_URI_CONFORMANT = "http://apache.org/xml/features/standard-uri-conformant";
    static final char[] XMLDECL;
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private int fChunkSize;
    protected QName fCurrentElement;
    protected XMLDocumentHandler fDocumentHandler;
    protected Driver fDriver;
    protected int fElementAttributeLimit;
    protected String fElementRawname;
    protected boolean fEmptyElement;
    protected XMLEntityStorage fEntityStore;
    protected ExternalSubsetResolver fExternalSubsetResolver;
    protected boolean fHasExternalDTD;
    protected int fMarkupDepth;
    protected String fPITarget;
    protected int fScannerLastState;
    protected int fScannerState;
    protected boolean fStandalone;
    protected boolean fStandaloneSet;
    protected boolean fStrictURI;
    protected boolean fUsebuffer;
    protected String fVersion;
    protected int fXMLNameLimit;
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    private static final String[] RECOGNIZED_FEATURES = {"http://xml.org/sax/features/namespaces", "http://xml.org/sax/features/validation", NOTIFY_BUILTIN_REFS, "http://apache.org/xml/features/scanner/notify-char-refs", Constants.STAX_REPORT_CDATA_EVENT, "http://javax.xml.XMLConstants/feature/useCatalog"};
    protected int[] fEntityStack = new int[4];
    protected boolean fReadingAttributes = false;
    protected boolean fInScanContent = false;
    protected boolean fLastSectionWasCData = false;
    protected boolean fCDataStart = false;
    protected boolean fInCData = false;
    protected boolean fCDataEnd = false;
    protected boolean fLastSectionWasEntityReference = false;
    protected boolean fLastSectionWasCharacterData = false;
    protected ElementStack fElementStack = new ElementStack();
    protected ElementStack2 fElementStack2 = new ElementStack2();
    protected XMLString fPIData = new XMLString();
    protected boolean fNotifyBuiltInRefs = false;
    protected boolean fSupportDTD = true;
    protected boolean fReplaceEntityReferences = true;
    protected boolean fSupportExternalEntities = false;
    protected boolean fReportCdataEvent = false;
    protected boolean fIsCoalesce = false;
    protected String fDeclaredEncoding = null;
    protected boolean fDisallowDoctype = false;
    protected boolean fCreateEntityRefNodes = false;
    protected String fAccessExternalDTD = "all";
    protected Driver fContentDriver = createContentDriver();
    protected QName fElementQName = new QName();
    protected QName fAttributeQName = new QName();
    protected XMLAttributesIteratorImpl fAttributes = new XMLAttributesIteratorImpl();
    protected XMLString fTempString = new XMLString();
    protected XMLString fTempString2 = new XMLString();
    private final String[] fStrings = new String[3];
    protected XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    protected XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    protected XMLStringBuffer fContentBuffer = new XMLStringBuffer();
    private final char[] fSingleChar = new char[1];
    private String fCurrentEntityName = null;
    protected boolean fScanToEnd = false;
    protected DTDGrammarUtil dtdGrammarUtil = null;
    protected boolean fAddDefaultAttr = false;
    protected boolean foundBuiltInRefs = false;
    protected boolean builtInRefCharacterHandled = false;
    String[] fElementArray = new String[200];
    short fLastPointerLocation = 0;
    short fElementPointer = 0;
    short[][] fPointerInfo = (short[][]) Array.newInstance((Class<?>) Short.TYPE, 5, 4);
    protected boolean fShouldSkip = false;
    protected boolean fAdd = false;
    protected boolean fSkip = false;
    private Augmentations fTempAugmentations = null;

    public interface Driver {
        int next() throws IOException, XNIException;
    }

    public static final class Element {
        public char[] fRawname;
        public Element next;
        public QName qname;

        public Element(QName qName, Element element) {
            this.qname.setValues(qName);
            this.fRawname = qName.rawname.toCharArray();
            this.next = element;
        }
    }

    public class ElementStack {
        protected int fCount;
        protected int fDepth;
        protected int fLastDepth;
        protected int fMark;
        protected int fPosition;
        protected int[] fInt = new int[20];
        protected QName[] fElements = new QName[20];

        public ElementStack() {
            int i = 0;
            while (true) {
                QName[] qNameArr = this.fElements;
                if (i >= qNameArr.length) {
                    return;
                }
                qNameArr[i] = new QName();
                i++;
            }
        }

        public void clear() {
            this.fDepth = 0;
            this.fLastDepth = 0;
            this.fCount = 0;
            this.fMark = 1;
            this.fPosition = 1;
        }

        public QName getLastPoppedElement() {
            return this.fElements[this.fDepth];
        }

        public QName getNext() {
            if (this.fPosition == this.fCount) {
                this.fPosition = this.fMark;
            }
            return this.fElements[this.fPosition];
        }

        public boolean matchElement(QName qName) {
            boolean z;
            int i = this.fLastDepth;
            int i2 = this.fDepth;
            if (i <= i2 || i2 > 3) {
                z = false;
            } else {
                String str = qName.rawname;
                String str2 = this.fElements[i2 - 1].rawname;
                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                if (str == str2) {
                    xMLDocumentFragmentScannerImpl.fAdd = false;
                    int i3 = i2 - 1;
                    this.fMark = i3;
                    this.fPosition = i3;
                    this.fCount--;
                    z = true;
                } else {
                    xMLDocumentFragmentScannerImpl.fAdd = true;
                    z = false;
                }
            }
            int[] iArr = this.fInt;
            if (z) {
                int i4 = this.fPosition;
                this.fPosition = i4 + 1;
                iArr[i2] = i4;
            } else {
                iArr[i2] = this.fCount - 1;
            }
            if (this.fCount != this.fElements.length) {
                this.fLastDepth = i2;
                return z;
            }
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl2 = XMLDocumentFragmentScannerImpl.this;
            xMLDocumentFragmentScannerImpl2.fSkip = false;
            xMLDocumentFragmentScannerImpl2.fAdd = false;
            reposition();
            return false;
        }

        public QName nextElement() {
            boolean z = XMLDocumentFragmentScannerImpl.this.fSkip;
            int i = this.fDepth;
            if (z) {
                this.fDepth = i + 1;
                QName[] qNameArr = this.fElements;
                int i2 = this.fCount;
                this.fCount = i2 + 1;
                return qNameArr[i2];
            }
            QName[] qNameArr2 = this.fElements;
            if (i == qNameArr2.length) {
                QName[] qNameArr3 = new QName[qNameArr2.length * 2];
                System.arraycopy(qNameArr2, 0, qNameArr3, 0, i);
                this.fElements = qNameArr3;
                int i3 = this.fDepth;
                while (true) {
                    QName[] qNameArr4 = this.fElements;
                    if (i3 >= qNameArr4.length) {
                        break;
                    }
                    qNameArr4[i3] = new QName();
                    i3++;
                }
            }
            QName[] qNameArr5 = this.fElements;
            int i4 = this.fDepth;
            this.fDepth = i4 + 1;
            return qNameArr5[i4];
        }

        public QName popElement() {
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
            if (!xMLDocumentFragmentScannerImpl.fSkip && !xMLDocumentFragmentScannerImpl.fAdd) {
                QName[] qNameArr = this.fElements;
                int i = this.fDepth - 1;
                this.fDepth = i;
                return qNameArr[i];
            }
            QName[] qNameArr2 = this.fElements;
            int[] iArr = this.fInt;
            int i2 = this.fDepth;
            this.fDepth = i2 - 1;
            return qNameArr2[iArr[i2]];
        }

        public void push() {
            int[] iArr = this.fInt;
            int i = this.fDepth + 1;
            this.fDepth = i;
            int i2 = this.fPosition;
            this.fPosition = i2 + 1;
            iArr[i] = i2;
        }

        public QName pushElement(QName qName) {
            int i = this.fDepth;
            QName[] qNameArr = this.fElements;
            if (i == qNameArr.length) {
                QName[] qNameArr2 = new QName[qNameArr.length * 2];
                System.arraycopy(qNameArr, 0, qNameArr2, 0, i);
                this.fElements = qNameArr2;
                int i2 = this.fDepth;
                while (true) {
                    QName[] qNameArr3 = this.fElements;
                    if (i2 >= qNameArr3.length) {
                        break;
                    }
                    qNameArr3[i2] = new QName();
                    i2++;
                }
            }
            this.fElements[this.fDepth].setValues(qName);
            QName[] qNameArr4 = this.fElements;
            int i3 = this.fDepth;
            this.fDepth = i3 + 1;
            return qNameArr4[i3];
        }

        public void reposition() {
            for (int i = 2; i <= this.fDepth; i++) {
                QName[] qNameArr = this.fElements;
                qNameArr[i - 1] = qNameArr[this.fInt[i]];
            }
        }
    }

    public class ElementStack2 {
        protected int fCount;
        protected int fDepth;
        protected int fLastDepth;
        protected int fMark;
        protected int fPosition;
        protected QName[] fQName = new QName[20];

        public ElementStack2() {
            int i = 0;
            while (true) {
                QName[] qNameArr = this.fQName;
                if (i >= qNameArr.length) {
                    this.fPosition = 1;
                    this.fMark = 1;
                    return;
                } else {
                    qNameArr[i] = new QName();
                    i++;
                }
            }
        }

        public void clear() {
            this.fLastDepth = 0;
            this.fDepth = 0;
            this.fCount = 0;
            this.fMark = 1;
            this.fPosition = 1;
        }

        public QName getNext() {
            if (this.fPosition == this.fCount) {
                this.fPosition = this.fMark;
            }
            QName[] qNameArr = this.fQName;
            int i = this.fPosition;
            this.fPosition = i + 1;
            return qNameArr[i];
        }

        public boolean matchElement(QName qName) {
            int i = this.fLastDepth;
            int i2 = this.fDepth;
            boolean z = false;
            if (i > i2 && i2 <= 2) {
                String str = qName.rawname;
                String str2 = this.fQName[i2].rawname;
                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                if (str == str2) {
                    xMLDocumentFragmentScannerImpl.fAdd = false;
                    this.fMark = i2 - 1;
                    this.fPosition = i2;
                    this.fCount--;
                    z = true;
                } else {
                    xMLDocumentFragmentScannerImpl.fAdd = true;
                }
            }
            this.fDepth = i2 + 1;
            this.fLastDepth = i2;
            return z;
        }

        public QName nextElement() {
            int i = this.fCount;
            QName[] qNameArr = this.fQName;
            if (i != qNameArr.length) {
                this.fCount = i + 1;
                return qNameArr[i];
            }
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
            xMLDocumentFragmentScannerImpl.fShouldSkip = false;
            xMLDocumentFragmentScannerImpl.fAdd = false;
            int i2 = i - 1;
            this.fCount = i2;
            return qNameArr[i2];
        }

        public int popElement() {
            int i = this.fDepth;
            this.fDepth = i - 1;
            return i;
        }

        public void resize() {
            QName[] qNameArr = this.fQName;
            int length = qNameArr.length;
            QName[] qNameArr2 = new QName[length * 2];
            System.arraycopy(qNameArr, 0, qNameArr2, 0, length);
            this.fQName = qNameArr2;
            while (true) {
                QName[] qNameArr3 = this.fQName;
                if (length >= qNameArr3.length) {
                    return;
                }
                qNameArr3[length] = new QName();
                length++;
            }
        }
    }

    public class FragmentContentDriver implements Driver {
        public FragmentContentDriver() {
        }

        private void startOfContent() throws IOException {
            boolean zSkipChar = XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(60, null);
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
            if (zSkipChar) {
                xMLDocumentFragmentScannerImpl.setScannerState(21);
                return;
            }
            boolean zSkipChar2 = xMLDocumentFragmentScannerImpl.fEntityScanner.skipChar(38, XMLScanner.NameType.REFERENCE);
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl2 = XMLDocumentFragmentScannerImpl.this;
            if (zSkipChar2) {
                xMLDocumentFragmentScannerImpl2.setScannerState(28);
            } else {
                xMLDocumentFragmentScannerImpl2.setScannerState(37);
            }
        }

        private void startOfMarkup() throws IOException {
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
            xMLDocumentFragmentScannerImpl.fMarkupDepth++;
            int iPeekChar = xMLDocumentFragmentScannerImpl.fEntityScanner.peekChar();
            if (XMLDocumentFragmentScannerImpl.this.isValidNameStartChar(iPeekChar) || XMLDocumentFragmentScannerImpl.this.isValidNameStartHighSurrogate(iPeekChar)) {
                XMLDocumentFragmentScannerImpl.this.setScannerState(38);
                return;
            }
            if (iPeekChar != 33) {
                if (iPeekChar == 47) {
                    XMLDocumentFragmentScannerImpl.this.setScannerState(39);
                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(iPeekChar, XMLScanner.NameType.ELEMENTEND);
                    return;
                }
                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl2 = XMLDocumentFragmentScannerImpl.this;
                if (iPeekChar != 63) {
                    xMLDocumentFragmentScannerImpl2.reportFatalError("MarkupNotRecognizedInContent", null);
                    return;
                } else {
                    xMLDocumentFragmentScannerImpl2.setScannerState(23);
                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(iPeekChar, null);
                    return;
                }
            }
            XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(iPeekChar, null);
            boolean zSkipChar = XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(45, null);
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl3 = XMLDocumentFragmentScannerImpl.this;
            if (zSkipChar) {
                if (!xMLDocumentFragmentScannerImpl3.fEntityScanner.skipChar(45, XMLScanner.NameType.COMMENT)) {
                    XMLDocumentFragmentScannerImpl.this.reportFatalError("InvalidCommentStart", null);
                }
                XMLDocumentFragmentScannerImpl.this.setScannerState(27);
            } else if (xMLDocumentFragmentScannerImpl3.fEntityScanner.skipString(XMLDocumentFragmentScannerImpl.CDATA)) {
                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl4 = XMLDocumentFragmentScannerImpl.this;
                xMLDocumentFragmentScannerImpl4.fCDataStart = true;
                xMLDocumentFragmentScannerImpl4.setScannerState(35);
            } else {
                if (scanForDoctypeHook()) {
                    return;
                }
                XMLDocumentFragmentScannerImpl.this.reportFatalError("MarkupNotRecognizedInContent", null);
            }
        }

        public void decideSubState() throws IOException {
            while (true) {
                int i = XMLDocumentFragmentScannerImpl.this.fScannerState;
                if (i != 22 && i != 21) {
                    return;
                }
                if (i == 21) {
                    startOfMarkup();
                } else if (i == 22) {
                    startOfContent();
                }
            }
        }

        public boolean elementDepthIsZeroHook() throws IOException, XNIException {
            return false;
        }

        public void endOfFileHook(EOFException eOFException) throws IOException, XNIException {
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
            if (xMLDocumentFragmentScannerImpl.fMarkupDepth != 0) {
                xMLDocumentFragmentScannerImpl.reportFatalError("PrematureEOF", null);
            }
        }

        /* JADX WARN: Code duplicated, block: B:131:0x021d A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:133:0x022e  */
        /* JADX WARN: Code duplicated, block: B:141:0x0262 A[Catch: EOFException -> 0x0029, CharConversionException -> 0x002c, MalformedByteSequenceException -> 0x0030, TryCatch #2 {MalformedByteSequenceException -> 0x0030, CharConversionException -> 0x002c, EOFException -> 0x0029, blocks: (B:3:0x0001, B:5:0x0014, B:7:0x001c, B:17:0x0038, B:18:0x0045, B:19:0x0048, B:21:0x004e, B:22:0x0051, B:24:0x005c, B:26:0x0062, B:30:0x006a, B:32:0x006d, B:34:0x0071, B:36:0x0075, B:40:0x007d, B:42:0x0082, B:46:0x008b, B:48:0x0090, B:49:0x0093, B:50:0x00b2, B:51:0x00b3, B:53:0x00ca, B:55:0x00cf, B:57:0x00da, B:59:0x00de, B:61:0x00e4, B:64:0x00eb, B:66:0x00f1, B:68:0x00fd, B:69:0x0101, B:71:0x0105, B:73:0x0109, B:75:0x010d, B:80:0x0115, B:83:0x011b, B:85:0x011f, B:87:0x0123, B:89:0x0131, B:92:0x014a, B:95:0x0158, B:103:0x018c, B:105:0x0192, B:106:0x0195, B:108:0x019b, B:96:0x0163, B:99:0x0171, B:100:0x017c, B:102:0x0185, B:112:0x01a5, B:114:0x01ac, B:130:0x0210, B:144:0x0273, B:146:0x0279, B:147:0x027d, B:149:0x0281, B:132:0x021f, B:134:0x0230, B:136:0x0238, B:139:0x0240, B:140:0x024b, B:141:0x0262, B:143:0x0270, B:118:0x01c3, B:120:0x01df, B:121:0x01e6, B:124:0x01f2, B:125:0x01f8, B:127:0x0202, B:128:0x0209, B:88:0x012c, B:153:0x028b, B:155:0x0295, B:158:0x02aa, B:160:0x02bf, B:162:0x02cd, B:166:0x02f9, B:163:0x02dc, B:165:0x02ea, B:167:0x0318, B:168:0x031b, B:170:0x032a, B:172:0x032e, B:174:0x0332, B:176:0x0336, B:178:0x0342, B:180:0x0351, B:182:0x0358, B:184:0x035e, B:185:0x0362, B:181:0x0355, B:177:0x033d, B:190:0x036a, B:192:0x0373, B:194:0x0377, B:196:0x037b, B:198:0x037f, B:200:0x038b, B:203:0x039b, B:205:0x03ab, B:226:0x03f1, B:207:0x03af, B:209:0x03be, B:211:0x03c2, B:213:0x03cb, B:218:0x03d3, B:220:0x03d9, B:222:0x03e2, B:199:0x0386, B:227:0x03fc, B:229:0x0406, B:232:0x040e, B:234:0x0411, B:236:0x0415), top: B:246:0x0001 }] */
        /* JADX WARN: Code duplicated, block: B:146:0x0279 A[Catch: EOFException -> 0x0029, CharConversionException -> 0x002c, MalformedByteSequenceException -> 0x0030, TryCatch #2 {MalformedByteSequenceException -> 0x0030, CharConversionException -> 0x002c, EOFException -> 0x0029, blocks: (B:3:0x0001, B:5:0x0014, B:7:0x001c, B:17:0x0038, B:18:0x0045, B:19:0x0048, B:21:0x004e, B:22:0x0051, B:24:0x005c, B:26:0x0062, B:30:0x006a, B:32:0x006d, B:34:0x0071, B:36:0x0075, B:40:0x007d, B:42:0x0082, B:46:0x008b, B:48:0x0090, B:49:0x0093, B:50:0x00b2, B:51:0x00b3, B:53:0x00ca, B:55:0x00cf, B:57:0x00da, B:59:0x00de, B:61:0x00e4, B:64:0x00eb, B:66:0x00f1, B:68:0x00fd, B:69:0x0101, B:71:0x0105, B:73:0x0109, B:75:0x010d, B:80:0x0115, B:83:0x011b, B:85:0x011f, B:87:0x0123, B:89:0x0131, B:92:0x014a, B:95:0x0158, B:103:0x018c, B:105:0x0192, B:106:0x0195, B:108:0x019b, B:96:0x0163, B:99:0x0171, B:100:0x017c, B:102:0x0185, B:112:0x01a5, B:114:0x01ac, B:130:0x0210, B:144:0x0273, B:146:0x0279, B:147:0x027d, B:149:0x0281, B:132:0x021f, B:134:0x0230, B:136:0x0238, B:139:0x0240, B:140:0x024b, B:141:0x0262, B:143:0x0270, B:118:0x01c3, B:120:0x01df, B:121:0x01e6, B:124:0x01f2, B:125:0x01f8, B:127:0x0202, B:128:0x0209, B:88:0x012c, B:153:0x028b, B:155:0x0295, B:158:0x02aa, B:160:0x02bf, B:162:0x02cd, B:166:0x02f9, B:163:0x02dc, B:165:0x02ea, B:167:0x0318, B:168:0x031b, B:170:0x032a, B:172:0x032e, B:174:0x0332, B:176:0x0336, B:178:0x0342, B:180:0x0351, B:182:0x0358, B:184:0x035e, B:185:0x0362, B:181:0x0355, B:177:0x033d, B:190:0x036a, B:192:0x0373, B:194:0x0377, B:196:0x037b, B:198:0x037f, B:200:0x038b, B:203:0x039b, B:205:0x03ab, B:226:0x03f1, B:207:0x03af, B:209:0x03be, B:211:0x03c2, B:213:0x03cb, B:218:0x03d3, B:220:0x03d9, B:222:0x03e2, B:199:0x0386, B:227:0x03fc, B:229:0x0406, B:232:0x040e, B:234:0x0411, B:236:0x0415), top: B:246:0x0001 }] */
        /* JADX WARN: Code duplicated, block: B:264:0x027d A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:288:0x0210 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:289:0x021f A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:291:0x0270 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:293:? A[LOOP:3: B:129:0x020e->B:293:?, LOOP_END, SYNTHETIC] */
        @Override // com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.Driver
        public int next() throws IOException, XNIException {
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl;
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl2;
            int i;
            while (true) {
                try {
                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl3 = XMLDocumentFragmentScannerImpl.this;
                    if (xMLDocumentFragmentScannerImpl3.fScannerState == 22) {
                        int iPeekChar = xMLDocumentFragmentScannerImpl3.fEntityScanner.peekChar();
                        if (iPeekChar == 60) {
                            XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar(null);
                            XMLDocumentFragmentScannerImpl.this.setScannerState(21);
                        } else {
                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl4 = XMLDocumentFragmentScannerImpl.this;
                            if (iPeekChar == 38) {
                                xMLDocumentFragmentScannerImpl4.fEntityScanner.scanChar(XMLScanner.NameType.REFERENCE);
                                XMLDocumentFragmentScannerImpl.this.setScannerState(28);
                            } else {
                                xMLDocumentFragmentScannerImpl4.setScannerState(37);
                            }
                        }
                    }
                    if (XMLDocumentFragmentScannerImpl.this.fScannerState == 21) {
                        startOfMarkup();
                    }
                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl5 = XMLDocumentFragmentScannerImpl.this;
                    boolean z = xMLDocumentFragmentScannerImpl5.fIsCoalesce;
                    if (z) {
                        xMLDocumentFragmentScannerImpl5.fUsebuffer = true;
                        if (xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData) {
                            int i2 = xMLDocumentFragmentScannerImpl5.fScannerState;
                            if (i2 != 35 && i2 != 28 && i2 != 37) {
                                xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData = false;
                                return 4;
                            }
                        } else if ((xMLDocumentFragmentScannerImpl5.fLastSectionWasCData || xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference) && (i = xMLDocumentFragmentScannerImpl5.fScannerState) != 35 && i != 28 && i != 37) {
                            xMLDocumentFragmentScannerImpl5.fLastSectionWasCData = false;
                            xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference = false;
                            return 4;
                        }
                    }
                    int i3 = xMLDocumentFragmentScannerImpl5.fScannerState;
                    if (i3 == 7) {
                        return 7;
                    }
                    if (i3 == 23) {
                        xMLDocumentFragmentScannerImpl5.fContentBuffer.clear();
                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl6 = XMLDocumentFragmentScannerImpl.this;
                        xMLDocumentFragmentScannerImpl6.scanPI(xMLDocumentFragmentScannerImpl6.fContentBuffer);
                        XMLDocumentFragmentScannerImpl.this.setScannerState(22);
                        return 3;
                    }
                    switch (i3) {
                        case 26:
                            boolean zScanRootElementHook = scanRootElementHook();
                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl7 = XMLDocumentFragmentScannerImpl.this;
                            if (zScanRootElementHook) {
                                xMLDocumentFragmentScannerImpl7.fEmptyElement = true;
                                return 1;
                            }
                            xMLDocumentFragmentScannerImpl7.setScannerState(22);
                            return 1;
                        case 27:
                            xMLDocumentFragmentScannerImpl5.scanComment();
                            XMLDocumentFragmentScannerImpl.this.setScannerState(22);
                            return 5;
                        case 28:
                            xMLDocumentFragmentScannerImpl5.fMarkupDepth++;
                            xMLDocumentFragmentScannerImpl5.foundBuiltInRefs = false;
                            if (z && (xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference || xMLDocumentFragmentScannerImpl5.fLastSectionWasCData || xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData)) {
                                xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference = true;
                                xMLDocumentFragmentScannerImpl5.fLastSectionWasCData = false;
                                xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData = false;
                            } else {
                                xMLDocumentFragmentScannerImpl5.fContentBuffer.clear();
                            }
                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl8 = XMLDocumentFragmentScannerImpl.this;
                            xMLDocumentFragmentScannerImpl8.fUsebuffer = true;
                            boolean zSkipChar = xMLDocumentFragmentScannerImpl8.fEntityScanner.skipChar(35, XMLScanner.NameType.REFERENCE);
                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl9 = XMLDocumentFragmentScannerImpl.this;
                            if (zSkipChar) {
                                xMLDocumentFragmentScannerImpl9.scanCharReferenceValue(xMLDocumentFragmentScannerImpl9.fContentBuffer, null);
                                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl10 = XMLDocumentFragmentScannerImpl.this;
                                xMLDocumentFragmentScannerImpl10.fMarkupDepth--;
                                if (!xMLDocumentFragmentScannerImpl10.fIsCoalesce) {
                                    xMLDocumentFragmentScannerImpl10.setScannerState(22);
                                    return 4;
                                }
                            } else {
                                xMLDocumentFragmentScannerImpl9.scanEntityReference(xMLDocumentFragmentScannerImpl9.fContentBuffer);
                                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl11 = XMLDocumentFragmentScannerImpl.this;
                                int i4 = xMLDocumentFragmentScannerImpl11.fScannerState;
                                if (i4 == 41 && !xMLDocumentFragmentScannerImpl11.fIsCoalesce) {
                                    xMLDocumentFragmentScannerImpl11.setScannerState(22);
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl12 = XMLDocumentFragmentScannerImpl.this;
                                    if (!xMLDocumentFragmentScannerImpl12.builtInRefCharacterHandled) {
                                        return 4;
                                    }
                                    xMLDocumentFragmentScannerImpl12.builtInRefCharacterHandled = false;
                                    return 9;
                                }
                                if (i4 == 36) {
                                    xMLDocumentFragmentScannerImpl11.fLastSectionWasEntityReference = true;
                                    break;
                                } else if (i4 == 28) {
                                    xMLDocumentFragmentScannerImpl11.setScannerState(22);
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl13 = XMLDocumentFragmentScannerImpl.this;
                                    if (!xMLDocumentFragmentScannerImpl13.fReplaceEntityReferences || !xMLDocumentFragmentScannerImpl13.fEntityStore.isDeclaredEntity(xMLDocumentFragmentScannerImpl13.fCurrentEntityName)) {
                                        return 9;
                                    }
                                    break;
                                }
                            }
                            XMLDocumentFragmentScannerImpl.this.setScannerState(22);
                            XMLDocumentFragmentScannerImpl.this.fLastSectionWasEntityReference = true;
                            break;
                        default:
                            switch (i3) {
                                case 35:
                                    if (z && (xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference || xMLDocumentFragmentScannerImpl5.fLastSectionWasCData || xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData)) {
                                        xMLDocumentFragmentScannerImpl5.fLastSectionWasCData = true;
                                        xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference = false;
                                        xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData = false;
                                    } else {
                                        xMLDocumentFragmentScannerImpl5.fContentBuffer.clear();
                                    }
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl14 = XMLDocumentFragmentScannerImpl.this;
                                    xMLDocumentFragmentScannerImpl14.fUsebuffer = true;
                                    xMLDocumentFragmentScannerImpl14.scanCDATASection(xMLDocumentFragmentScannerImpl14.fContentBuffer, true);
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl15 = XMLDocumentFragmentScannerImpl.this;
                                    if (xMLDocumentFragmentScannerImpl15.fCDataEnd) {
                                        xMLDocumentFragmentScannerImpl15.setScannerState(22);
                                    } else {
                                        xMLDocumentFragmentScannerImpl15.setScannerState(35);
                                    }
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl16 = XMLDocumentFragmentScannerImpl.this;
                                    if (!xMLDocumentFragmentScannerImpl16.fIsCoalesce) {
                                        return xMLDocumentFragmentScannerImpl16.fReportCdataEvent ? 12 : 4;
                                    }
                                    xMLDocumentFragmentScannerImpl16.fLastSectionWasCData = true;
                                    break;
                                    break;
                                case 36:
                                    if (xMLDocumentFragmentScannerImpl5.fEntityScanner.skipString("<?xml")) {
                                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl17 = XMLDocumentFragmentScannerImpl.this;
                                        xMLDocumentFragmentScannerImpl17.fMarkupDepth++;
                                        boolean zIsValidNameChar = xMLDocumentFragmentScannerImpl17.isValidNameChar(xMLDocumentFragmentScannerImpl17.fEntityScanner.peekChar());
                                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl18 = XMLDocumentFragmentScannerImpl.this;
                                        if (zIsValidNameChar) {
                                            xMLDocumentFragmentScannerImpl18.fStringBuffer.clear();
                                            XMLDocumentFragmentScannerImpl.this.fStringBuffer.append("xml");
                                            if (XMLDocumentFragmentScannerImpl.this.fNamespaces) {
                                                while (true) {
                                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl19 = XMLDocumentFragmentScannerImpl.this;
                                                    if (xMLDocumentFragmentScannerImpl19.isValidNCName(xMLDocumentFragmentScannerImpl19.fEntityScanner.peekChar())) {
                                                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl20 = XMLDocumentFragmentScannerImpl.this;
                                                        xMLDocumentFragmentScannerImpl20.fStringBuffer.append((char) xMLDocumentFragmentScannerImpl20.fEntityScanner.scanChar(null));
                                                    }
                                                }
                                            } else {
                                                while (true) {
                                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl21 = XMLDocumentFragmentScannerImpl.this;
                                                    if (xMLDocumentFragmentScannerImpl21.isValidNameChar(xMLDocumentFragmentScannerImpl21.fEntityScanner.peekChar())) {
                                                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl22 = XMLDocumentFragmentScannerImpl.this;
                                                        xMLDocumentFragmentScannerImpl22.fStringBuffer.append((char) xMLDocumentFragmentScannerImpl22.fEntityScanner.scanChar(null));
                                                    }
                                                }
                                            }
                                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl23 = XMLDocumentFragmentScannerImpl.this;
                                            SymbolTable symbolTable = xMLDocumentFragmentScannerImpl23.fSymbolTable;
                                            XMLStringBuffer xMLStringBuffer = xMLDocumentFragmentScannerImpl23.fStringBuffer;
                                            String strAddSymbol = symbolTable.addSymbol(xMLStringBuffer.ch, xMLStringBuffer.offset, xMLStringBuffer.length);
                                            XMLDocumentFragmentScannerImpl.this.fContentBuffer.clear();
                                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl24 = XMLDocumentFragmentScannerImpl.this;
                                            xMLDocumentFragmentScannerImpl24.scanPIData(strAddSymbol, xMLDocumentFragmentScannerImpl24.fContentBuffer);
                                        } else {
                                            xMLDocumentFragmentScannerImpl18.scanXMLDeclOrTextDecl(true);
                                        }
                                    }
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl25 = XMLDocumentFragmentScannerImpl.this;
                                    xMLDocumentFragmentScannerImpl25.fEntityManager.fCurrentEntity.mayReadChunks = true;
                                    xMLDocumentFragmentScannerImpl25.setScannerState(22);
                                    break;
                                case 37:
                                    boolean z2 = xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference;
                                    xMLDocumentFragmentScannerImpl5.fUsebuffer = z2 || xMLDocumentFragmentScannerImpl5.fLastSectionWasCData || xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData;
                                    if (z && (z2 || xMLDocumentFragmentScannerImpl5.fLastSectionWasCData || xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData)) {
                                        xMLDocumentFragmentScannerImpl5.fLastSectionWasEntityReference = false;
                                        xMLDocumentFragmentScannerImpl5.fLastSectionWasCData = false;
                                        xMLDocumentFragmentScannerImpl5.fLastSectionWasCharacterData = true;
                                        xMLDocumentFragmentScannerImpl5.fUsebuffer = true;
                                    } else {
                                        xMLDocumentFragmentScannerImpl5.fContentBuffer.clear();
                                    }
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl26 = XMLDocumentFragmentScannerImpl.this;
                                    XMLString xMLString = xMLDocumentFragmentScannerImpl26.fTempString;
                                    xMLString.length = 0;
                                    int iScanContent = xMLDocumentFragmentScannerImpl26.fEntityScanner.scanContent(xMLString);
                                    boolean zSkipChar2 = XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(60, null);
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl27 = XMLDocumentFragmentScannerImpl.this;
                                    if (zSkipChar2) {
                                        boolean zSkipChar3 = xMLDocumentFragmentScannerImpl27.fEntityScanner.skipChar(47, XMLScanner.NameType.ELEMENTEND);
                                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl28 = XMLDocumentFragmentScannerImpl.this;
                                        if (!zSkipChar3) {
                                            boolean zIsNameStart = XMLChar.isNameStart(xMLDocumentFragmentScannerImpl28.fEntityScanner.peekChar());
                                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl29 = XMLDocumentFragmentScannerImpl.this;
                                            if (!zIsNameStart) {
                                                xMLDocumentFragmentScannerImpl29.setScannerState(21);
                                                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl30 = XMLDocumentFragmentScannerImpl.this;
                                                if (xMLDocumentFragmentScannerImpl30.fIsCoalesce) {
                                                    xMLDocumentFragmentScannerImpl30.fLastSectionWasCharacterData = true;
                                                    xMLDocumentFragmentScannerImpl30.bufferContent();
                                                }
                                            } else {
                                                xMLDocumentFragmentScannerImpl29.fMarkupDepth++;
                                                xMLDocumentFragmentScannerImpl29.fLastSectionWasCharacterData = false;
                                                xMLDocumentFragmentScannerImpl29.setScannerState(38);
                                            }
                                        } else {
                                            xMLDocumentFragmentScannerImpl28.fMarkupDepth++;
                                            xMLDocumentFragmentScannerImpl28.fLastSectionWasCharacterData = false;
                                            xMLDocumentFragmentScannerImpl28.setScannerState(39);
                                        }
                                    } else {
                                        xMLDocumentFragmentScannerImpl27.bufferContent();
                                        if (iScanContent == 13) {
                                            XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar(null);
                                            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl31 = XMLDocumentFragmentScannerImpl.this;
                                            xMLDocumentFragmentScannerImpl31.fUsebuffer = true;
                                            xMLDocumentFragmentScannerImpl31.fContentBuffer.append((char) iScanContent);
                                        } else {
                                            if (iScanContent == 93) {
                                                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl32 = XMLDocumentFragmentScannerImpl.this;
                                                xMLDocumentFragmentScannerImpl32.fUsebuffer = true;
                                                xMLDocumentFragmentScannerImpl32.fContentBuffer.append((char) xMLDocumentFragmentScannerImpl32.fEntityScanner.scanChar(null));
                                                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl33 = XMLDocumentFragmentScannerImpl.this;
                                                xMLDocumentFragmentScannerImpl33.fInScanContent = true;
                                                if (xMLDocumentFragmentScannerImpl33.fEntityScanner.skipChar(93, null)) {
                                                    XMLDocumentFragmentScannerImpl.this.fContentBuffer.append(']');
                                                    while (true) {
                                                        boolean zSkipChar4 = XMLDocumentFragmentScannerImpl.this.fEntityScanner.skipChar(93, null);
                                                        XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl34 = XMLDocumentFragmentScannerImpl.this;
                                                        if (zSkipChar4) {
                                                            xMLDocumentFragmentScannerImpl34.fContentBuffer.append(']');
                                                        } else if (xMLDocumentFragmentScannerImpl34.fEntityScanner.skipChar(62, null)) {
                                                            XMLDocumentFragmentScannerImpl.this.reportFatalError("CDEndInContent", null);
                                                        }
                                                    }
                                                }
                                                XMLDocumentFragmentScannerImpl.this.fInScanContent = false;
                                            }
                                            while (true) {
                                                if (iScanContent == 60) {
                                                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar(null);
                                                    XMLDocumentFragmentScannerImpl.this.setScannerState(21);
                                                } else if (iScanContent == 38) {
                                                    XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar(XMLScanner.NameType.REFERENCE);
                                                    XMLDocumentFragmentScannerImpl.this.setScannerState(28);
                                                } else if (iScanContent == -1 && XMLDocumentFragmentScannerImpl.this.isInvalidLiteral(iScanContent)) {
                                                    boolean zIsHighSurrogate = XMLChar.isHighSurrogate(iScanContent);
                                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl35 = XMLDocumentFragmentScannerImpl.this;
                                                    if (zIsHighSurrogate) {
                                                        xMLDocumentFragmentScannerImpl35.scanSurrogates(xMLDocumentFragmentScannerImpl35.fContentBuffer);
                                                        XMLDocumentFragmentScannerImpl.this.setScannerState(22);
                                                    } else {
                                                        xMLDocumentFragmentScannerImpl35.reportFatalError("InvalidCharInContent", new Object[]{Integer.toString(iScanContent, 16)});
                                                        XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar(null);
                                                    }
                                                } else {
                                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl36 = XMLDocumentFragmentScannerImpl.this;
                                                    iScanContent = xMLDocumentFragmentScannerImpl36.scanContent(xMLDocumentFragmentScannerImpl36.fContentBuffer);
                                                    xMLDocumentFragmentScannerImpl2 = XMLDocumentFragmentScannerImpl.this;
                                                    if (!xMLDocumentFragmentScannerImpl2.fIsCoalesce) {
                                                        xMLDocumentFragmentScannerImpl2.setScannerState(22);
                                                    }
                                                }
                                            }
                                            xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                                            if (xMLDocumentFragmentScannerImpl.fIsCoalesce) {
                                                DTDGrammarUtil dTDGrammarUtil = xMLDocumentFragmentScannerImpl.dtdGrammarUtil;
                                                return (dTDGrammarUtil == null && dTDGrammarUtil.isIgnorableWhiteSpace(xMLDocumentFragmentScannerImpl.fContentBuffer)) ? 6 : 4;
                                            }
                                            xMLDocumentFragmentScannerImpl.fLastSectionWasCharacterData = true;
                                        }
                                        iScanContent = -1;
                                        while (true) {
                                            if (iScanContent == 60) {
                                                XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar(null);
                                                XMLDocumentFragmentScannerImpl.this.setScannerState(21);
                                            } else if (iScanContent == 38) {
                                                XMLDocumentFragmentScannerImpl.this.fEntityScanner.scanChar(XMLScanner.NameType.REFERENCE);
                                                XMLDocumentFragmentScannerImpl.this.setScannerState(28);
                                            } else if (iScanContent == -1) {
                                                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl37 = XMLDocumentFragmentScannerImpl.this;
                                                iScanContent = xMLDocumentFragmentScannerImpl37.scanContent(xMLDocumentFragmentScannerImpl37.fContentBuffer);
                                                xMLDocumentFragmentScannerImpl2 = XMLDocumentFragmentScannerImpl.this;
                                                if (!xMLDocumentFragmentScannerImpl2.fIsCoalesce) {
                                                    xMLDocumentFragmentScannerImpl2.setScannerState(22);
                                                }
                                            } else {
                                                XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl38 = XMLDocumentFragmentScannerImpl.this;
                                                iScanContent = xMLDocumentFragmentScannerImpl38.scanContent(xMLDocumentFragmentScannerImpl38.fContentBuffer);
                                                xMLDocumentFragmentScannerImpl2 = XMLDocumentFragmentScannerImpl.this;
                                                if (!xMLDocumentFragmentScannerImpl2.fIsCoalesce) {
                                                    xMLDocumentFragmentScannerImpl2.setScannerState(22);
                                                }
                                            }
                                        }
                                        xMLDocumentFragmentScannerImpl = XMLDocumentFragmentScannerImpl.this;
                                        if (xMLDocumentFragmentScannerImpl.fIsCoalesce) {
                                            DTDGrammarUtil dTDGrammarUtil2 = xMLDocumentFragmentScannerImpl.dtdGrammarUtil;
                                            if (dTDGrammarUtil2 == null) {
                                            }
                                        }
                                        xMLDocumentFragmentScannerImpl.fLastSectionWasCharacterData = true;
                                    }
                                    break;
                                case 38:
                                    xMLDocumentFragmentScannerImpl5.fEmptyElement = xMLDocumentFragmentScannerImpl5.scanStartElement();
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl39 = XMLDocumentFragmentScannerImpl.this;
                                    if (xMLDocumentFragmentScannerImpl39.fEmptyElement) {
                                        xMLDocumentFragmentScannerImpl39.setScannerState(39);
                                    } else {
                                        xMLDocumentFragmentScannerImpl39.setScannerState(22);
                                    }
                                    return 1;
                                case 39:
                                    if (xMLDocumentFragmentScannerImpl5.fEmptyElement) {
                                        xMLDocumentFragmentScannerImpl5.fEmptyElement = false;
                                        xMLDocumentFragmentScannerImpl5.setScannerState(22);
                                        if (XMLDocumentFragmentScannerImpl.this.fMarkupDepth == 0) {
                                            elementDepthIsZeroHook();
                                        }
                                        return 2;
                                    }
                                    if (xMLDocumentFragmentScannerImpl5.scanEndElement() == 0 && elementDepthIsZeroHook()) {
                                        return 2;
                                    }
                                    XMLDocumentFragmentScannerImpl.this.setScannerState(22);
                                    return 2;
                                case 40:
                                    xMLDocumentFragmentScannerImpl5.fContentBuffer.clear();
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl40 = XMLDocumentFragmentScannerImpl.this;
                                    xMLDocumentFragmentScannerImpl40.scanCharReferenceValue(xMLDocumentFragmentScannerImpl40.fContentBuffer, null);
                                    XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl41 = XMLDocumentFragmentScannerImpl.this;
                                    xMLDocumentFragmentScannerImpl41.fMarkupDepth--;
                                    xMLDocumentFragmentScannerImpl41.setScannerState(22);
                                    return 4;
                                default:
                                    throw new XNIException("Scanner State " + XMLDocumentFragmentScannerImpl.this.fScannerState + " not Recognized ");
                            }
                            break;
                    }
                } catch (MalformedByteSequenceException e) {
                    XMLDocumentFragmentScannerImpl.this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, (Exception) e);
                    return -1;
                } catch (CharConversionException e2) {
                    XMLDocumentFragmentScannerImpl.this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", (Object[]) null, (short) 2, (Exception) e2);
                    return -1;
                } catch (EOFException e3) {
                    endOfFileHook(e3);
                    return -1;
                }
            }
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl42 = XMLDocumentFragmentScannerImpl.this;
            if (xMLDocumentFragmentScannerImpl42.fUsebuffer) {
                xMLDocumentFragmentScannerImpl42.bufferContent();
            }
            XMLDocumentFragmentScannerImpl xMLDocumentFragmentScannerImpl43 = XMLDocumentFragmentScannerImpl.this;
            DTDGrammarUtil dTDGrammarUtil3 = xMLDocumentFragmentScannerImpl43.dtdGrammarUtil;
            return (dTDGrammarUtil3 == null || !dTDGrammarUtil3.isIgnorableWhiteSpace(xMLDocumentFragmentScannerImpl43.fContentBuffer)) ? 4 : 6;
        }

        public boolean scanForDoctypeHook() throws IOException, XNIException {
            return false;
        }

        public boolean scanRootElementHook() throws IOException, XNIException {
            return false;
        }
    }

    static {
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        FEATURE_DEFAULTS = new Boolean[]{bool, null, bool2, bool2, bool, Boolean.valueOf(JdkXmlUtils.USE_CATALOG_DEFAULT)};
        RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-manager", "jdk.xml.xmlSecurityPropertyManager", JdkXmlUtils.CATALOG_DEFER, JdkXmlUtils.CATALOG_FILES, JdkXmlUtils.CATALOG_PREFER, JdkXmlUtils.CATALOG_RESOLVE, JdkConstants.CDATA_CHUNK_SIZE};
        PROPERTY_DEFAULTS = new Object[]{null, null, null, null, null, null, null, null, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT)};
        CDATA = new char[]{'[', 'C', 'D', 'A', 'T', 'A', '['};
        XMLDECL = new char[]{'<', '?', 'x', 'm', 'l'};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bufferContent() {
        this.fContentBuffer.append(this.fTempString);
        this.fTempString.length = 0;
        this.fUsebuffer = true;
    }

    private void handleCharacter(char c, String str, XMLStringBuffer xMLStringBuffer) throws XNIException {
        this.foundBuiltInRefs = true;
        checkEntityLimit(false, this.fEntityScanner.fCurrentEntity.name, 1);
        xMLStringBuffer.append(c);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            this.fSingleChar[0] = c;
            if (this.fNotifyBuiltInRefs) {
                xMLDocumentHandler.startGeneralEntity(str, null, null, null);
            }
            this.fTempString.setValues(this.fSingleChar, 0, 1);
            if (!this.fIsCoalesce) {
                this.fDocumentHandler.characters(this.fTempString, null);
                this.builtInRefCharacterHandled = true;
            }
            if (this.fNotifyBuiltInRefs) {
                this.fDocumentHandler.endGeneralEntity(str, null);
            }
        }
    }

    public static void pr(String str) {
        System.out.println(str);
    }

    public void addElement(String str) {
        short sStorePointerForADepth;
        short s = this.fElementPointer;
        if (s < 200) {
            this.fElementArray[s] = str;
            if (this.fElementStack.fDepth < 5 && (sStorePointerForADepth = storePointerForADepth(s)) > 0) {
                short elementPointer = getElementPointer((short) this.fElementStack.fDepth, (short) (sStorePointerForADepth - 1));
                if (str == this.fElementArray[elementPointer]) {
                    this.fShouldSkip = true;
                    this.fLastPointerLocation = elementPointer;
                    resetPointer((short) this.fElementStack.fDepth, sStorePointerForADepth);
                    this.fElementArray[this.fElementPointer] = null;
                    return;
                }
                this.fShouldSkip = false;
            }
            this.fElementPointer = (short) (this.fElementPointer + 1);
        }
    }

    public boolean canStore(short s, short s2) {
        return this.fPointerInfo[s][s2] == 0;
    }

    public String checkAccess(String str, String str2) throws IOException {
        return SecuritySupport.checkAccess(XMLEntityManager.expandSystemId(str, this.fEntityScanner.getBaseSystemId(), this.fStrictURI), str2, "all");
    }

    public void checkDepth(String str) {
        XMLLimitAnalyzer xMLLimitAnalyzer = this.fLimitAnalyzer;
        XMLSecurityManager.Limit limit = XMLSecurityManager.Limit.MAX_ELEMENT_DEPTH_LIMIT;
        xMLLimitAnalyzer.addValue(limit, str, this.fElementStack.fDepth);
        if (this.fSecurityManager.isOverLimit(limit, this.fLimitAnalyzer)) {
            this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
            reportFatalError("MaxElementDepthLimit", new Object[]{str, Integer.valueOf(this.fLimitAnalyzer.getTotalValue(limit)), Integer.valueOf(this.fSecurityManager.getLimit(limit)), "maxElementDepth"});
        }
    }

    public Driver createContentDriver() {
        return new FragmentContentDriver();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.impl.XMLEntityHandler
    public void endEntity(String str, Augmentations augmentations) throws IOException, XNIException {
        super.endEntity(str, augmentations);
        if (this.fMarkupDepth != this.fEntityStack[this.fEntityDepth]) {
            reportFatalError("MarkupEntityMismatch", null);
        }
        if (this.fDocumentHandler == null || this.fScanningAttribute || str.equals("[xml]")) {
            return;
        }
        this.fDocumentHandler.endGeneralEntity(str, augmentations);
    }

    public XMLAttributesIteratorImpl getAttributeIterator() {
        DTDGrammarUtil dTDGrammarUtil = this.dtdGrammarUtil;
        if (dTDGrammarUtil != null && this.fAddDefaultAttr) {
            dTDGrammarUtil.addDTDDefaultAttrs(this.fElementQName, this.fAttributes);
            this.fAddDefaultAttr = false;
        }
        return this.fAttributes;
    }

    public XMLString getCharacterData() {
        return this.fUsebuffer ? this.fContentBuffer : this.fTempString;
    }

    public String getComment() {
        return this.fContentBuffer.toString();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    public String getDriverName(Driver driver) {
        return PsiKeyword.NULL;
    }

    public short getElementPointer(short s, short s2) {
        return this.fPointerInfo[s][s2];
    }

    public QName getElementQName() {
        if (this.fScannerLastState == 2) {
            this.fElementQName.setValues(this.fElementStack.getLastPoppedElement());
        }
        return this.fElementQName;
    }

    public String getEntityName() {
        return this.fCurrentEntityName;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_FEATURES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return FEATURE_DEFAULTS[i];
            }
            i++;
        }
    }

    public XMLStringBuffer getPIData() {
        return this.fContentBuffer;
    }

    public String getPITarget() {
        return this.fPITarget;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_PROPERTIES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return PROPERTY_DEFAULTS[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public String getScannerStateName(int i) {
        switch (i) {
            case 21:
                return "SCANNER_STATE_START_OF_MARKUP";
            case 22:
                return "SCANNER_STATE_CONTENT";
            case 23:
                return "SCANNER_STATE_PI";
            case 24:
                return "SCANNER_STATE_DOCTYPE";
            case 25:
            case 31:
            case 32:
            default:
                return "??? (" + i + ')';
            case 26:
                return "SCANNER_STATE_ROOT_ELEMENT";
            case 27:
                return "SCANNER_STATE_COMMENT";
            case 28:
                return "SCANNER_STATE_REFERENCE";
            case 29:
                return "SCANNER_STATE_ATTRIBUTE";
            case 30:
                return "SCANNER_STATE_ATTRIBUTE_VALUE";
            case 33:
                return "SCANNER_STATE_END_OF_INPUT";
            case 34:
                return "SCANNER_STATE_TERMINATED";
            case 35:
                return "SCANNER_STATE_CDATA";
            case 36:
                return "SCANNER_STATE_TEXT_DECL";
            case 37:
                return "SCANNER_STATE_CHARACTER_DATA";
            case 38:
                return "SCANNER_STATE_START_ELEMENT_TAG";
            case 39:
                return "SCANNER_STATE_END_ELEMENT_TAG";
        }
    }

    public XMLString getString() {
        int i = this.fAttributeCacheUsedCount;
        if (i < this.initialCacheCount || i < this.attributeValueCache.size()) {
            ArrayList<XMLString> arrayList = this.attributeValueCache;
            int i2 = this.fAttributeCacheUsedCount;
            this.fAttributeCacheUsedCount = i2 + 1;
            return arrayList.get(i2);
        }
        XMLString xMLString = new XMLString();
        this.fAttributeCacheUsedCount++;
        this.attributeValueCache.add(xMLString);
        return xMLString;
    }

    public boolean hasAttributes() {
        return this.fAttributes.getLength() > 0;
    }

    public boolean isStandAlone() {
        return this.fStandalone;
    }

    public int next() throws IOException, XNIException {
        return this.fDriver.next();
    }

    @Override // com.sun.xml.internal.stream.XMLBufferListener
    public void refresh(int i) {
        if (this.fReadingAttributes) {
            this.fAttributes.refresh();
        }
        if (this.fScannerState == 37) {
            bufferContent();
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public void reset(PropertyManager propertyManager) {
        super.reset(propertyManager);
        this.fNamespaces = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_NAMESPACE_AWARE)).booleanValue();
        boolean z = false;
        this.fNotifyBuiltInRefs = false;
        this.fReplaceEntityReferences = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES)).booleanValue();
        this.fSupportExternalEntities = ((Boolean) propertyManager.getProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES)).booleanValue();
        Boolean bool = (Boolean) propertyManager.getProperty("http://java.sun.com/xml/stream/properties/report-cdata-event");
        if (bool != null) {
            this.fReportCdataEvent = bool.booleanValue();
        }
        Boolean bool2 = (Boolean) propertyManager.getProperty(XMLInputFactory.IS_COALESCING);
        if (bool2 != null) {
            this.fIsCoalesce = bool2.booleanValue();
        }
        boolean z2 = this.fIsCoalesce;
        if (!z2 && this.fReportCdataEvent) {
            z = true;
        }
        this.fReportCdataEvent = z;
        this.fReplaceEntityReferences = z2 ? true : this.fReplaceEntityReferences;
        this.fAccessExternalDTD = ((XMLSecurityPropertyManager) propertyManager.getProperty("jdk.xml.xmlSecurityPropertyManager")).getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
        this.fSecurityManager = (XMLSecurityManager) propertyManager.getProperty("http://apache.org/xml/properties/security-manager");
        this.fChunkSize = JdkXmlUtils.getValue(propertyManager.getProperty(JdkConstants.CDATA_CHUNK_SIZE), JdkConstants.CDATA_CHUNK_SIZE_DEFAULT);
        resetCommon();
    }

    public void resetCommon() {
        this.fMarkupDepth = 0;
        this.fCurrentElement = null;
        this.fElementStack.clear();
        this.fHasExternalDTD = false;
        this.fStandaloneSet = false;
        this.fStandalone = false;
        this.fInScanContent = false;
        this.fShouldSkip = false;
        this.fAdd = false;
        this.fSkip = false;
        this.fEntityStore = this.fEntityManager.getEntityStore();
        this.dtdGrammarUtil = null;
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        if (xMLSecurityManager != null) {
            this.fElementAttributeLimit = xMLSecurityManager.getLimit(XMLSecurityManager.Limit.ELEMENT_ATTRIBUTE_LIMIT);
            this.fXMLNameLimit = this.fSecurityManager.getLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT);
        } else {
            this.fElementAttributeLimit = 0;
            this.fXMLNameLimit = XMLSecurityManager.Limit.MAX_NAME_LIMIT.defaultValue();
        }
        this.fLimitAnalyzer = this.fEntityManager.fLimitAnalyzer;
    }

    public void resetPointer(short s, short s2) {
        this.fPointerInfo[s][s2] = 0;
    }

    public void scanAttribute(XMLAttributes xMLAttributes) throws IOException, XNIException {
        boolean z = this.fNamespaces;
        XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
        if (z) {
            xMLEntityScanner.scanQName(this.fAttributeQName, XMLScanner.NameType.ATTRIBUTENAME);
        } else {
            String strScanName = xMLEntityScanner.scanName(XMLScanner.NameType.ATTRIBUTENAME);
            this.fAttributeQName.setValues(null, strScanName, strScanName, null);
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(61, XMLScanner.NameType.ATTRIBUTE)) {
            reportFatalError("EqRequiredInAttribute", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        this.fEntityScanner.skipSpaces();
        boolean z2 = this.fHasExternalDTD && !this.fStandalone;
        XMLString string = getString();
        scanAttributeValue(string, this.fTempString2, this.fAttributeQName.rawname, xMLAttributes, 0, z2, this.fCurrentElement.rawname, false);
        int length = xMLAttributes.getLength();
        int iAddAttribute = xMLAttributes.addAttribute(this.fAttributeQName, XMLSymbols.fCDATASymbol, null);
        if (length == xMLAttributes.getLength()) {
            reportFatalError("AttributeNotUnique", new Object[]{this.fCurrentElement.rawname, this.fAttributeQName.rawname});
        }
        xMLAttributes.setValue(iAddAttribute, null, string);
        xMLAttributes.setSpecified(iAddAttribute, true);
    }

    public boolean scanCDATASection(XMLStringBuffer xMLStringBuffer, boolean z) throws IOException, XNIException {
        while (this.fEntityScanner.scanData("]]>", xMLStringBuffer, this.fChunkSize)) {
            int iPeekChar = this.fEntityScanner.peekChar();
            if (iPeekChar == -1 || !isInvalidLiteral(iPeekChar)) {
                this.fInCData = true;
                this.fCDataEnd = false;
                return true;
            }
            if (XMLChar.isHighSurrogate(iPeekChar)) {
                scanSurrogates(xMLStringBuffer);
            } else {
                reportFatalError("InvalidCharInCDSect", new Object[]{Integer.toString(iPeekChar, 16)});
                this.fEntityScanner.scanChar(null);
            }
        }
        this.fInCData = false;
        this.fCDataEnd = true;
        this.fMarkupDepth--;
        return true;
    }

    public void scanCharReference() throws IOException, XNIException {
        XMLDocumentHandler xMLDocumentHandler;
        this.fStringBuffer2.clear();
        int iScanCharReferenceValue = scanCharReferenceValue(this.fStringBuffer2, null);
        this.fMarkupDepth--;
        if (iScanCharReferenceValue == -1 || (xMLDocumentHandler = this.fDocumentHandler) == null) {
            return;
        }
        if (this.fNotifyCharRefs) {
            xMLDocumentHandler.startGeneralEntity(this.fCharRefLiteral, null, null, null);
        }
        if (this.fValidation && iScanCharReferenceValue <= 32) {
            Augmentations augmentations = this.fTempAugmentations;
            if (augmentations != null) {
                augmentations.removeAllItems();
            } else {
                this.fTempAugmentations = new AugmentationsImpl();
            }
            this.fTempAugmentations.putItem(Constants.CHAR_REF_PROBABLE_WS, Boolean.TRUE);
        }
        if (this.fNotifyCharRefs) {
            this.fDocumentHandler.endGeneralEntity(this.fCharRefLiteral, null);
        }
    }

    public void scanComment() throws IOException, XNIException {
        this.fContentBuffer.clear();
        scanComment(this.fContentBuffer);
        this.fUsebuffer = true;
        this.fMarkupDepth--;
    }

    public int scanContent(XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        XMLString xMLString = this.fTempString;
        xMLString.length = 0;
        int iScanContent = this.fEntityScanner.scanContent(xMLString);
        xMLStringBuffer.append(this.fTempString);
        this.fTempString.length = 0;
        if (iScanContent == 13) {
            this.fEntityScanner.scanChar(null);
            xMLStringBuffer.append((char) iScanContent);
        } else {
            if (iScanContent != 93) {
                return iScanContent;
            }
            xMLStringBuffer.append((char) this.fEntityScanner.scanChar(null));
            this.fInScanContent = true;
            if (this.fEntityScanner.skipChar(93, null)) {
                xMLStringBuffer.append(']');
                while (this.fEntityScanner.skipChar(93, null)) {
                    xMLStringBuffer.append(']');
                }
                if (this.fEntityScanner.skipChar(62, null)) {
                    reportFatalError("CDEndInContent", null);
                }
            }
            this.fInScanContent = false;
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0083  */
    /* JADX WARN: Code duplicated, block: B:22:0x0089 A[RETURN] */
    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner
    public boolean scanDocument(boolean z) throws IOException, XNIException {
        this.fEntityManager.setEntityHandler(this);
        int next = next();
        do {
            switch (next) {
                case 1:
                case 2:
                case 6:
                case 7:
                case 10:
                case 11:
                case 13:
                case 14:
                case 15:
                    break;
                case 3:
                    XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
                    xMLEntityScanner.checkNodeCount(xMLEntityScanner.fCurrentEntity);
                    this.fDocumentHandler.processingInstruction(getPITarget(), getPIData(), null);
                    break;
                case 4:
                    XMLEntityScanner xMLEntityScanner2 = this.fEntityScanner;
                    xMLEntityScanner2.checkNodeCount(xMLEntityScanner2.fCurrentEntity);
                    this.fDocumentHandler.characters(getCharacterData(), null);
                    break;
                case 5:
                    XMLEntityScanner xMLEntityScanner3 = this.fEntityScanner;
                    xMLEntityScanner3.checkNodeCount(xMLEntityScanner3.fCurrentEntity);
                    this.fDocumentHandler.comment(getCharacterData(), null);
                    break;
                case 8:
                default:
                    return false;
                case 9:
                    XMLEntityScanner xMLEntityScanner4 = this.fEntityScanner;
                    xMLEntityScanner4.checkNodeCount(xMLEntityScanner4.fCurrentEntity);
                    break;
                case 12:
                    XMLEntityScanner xMLEntityScanner5 = this.fEntityScanner;
                    xMLEntityScanner5.checkNodeCount(xMLEntityScanner5.fCurrentEntity);
                    if (this.fCDataStart) {
                        this.fDocumentHandler.startCDATA(null);
                        this.fCDataStart = false;
                        this.fInCData = true;
                    }
                    this.fDocumentHandler.characters(getCharacterData(), null);
                    if (this.fCDataEnd) {
                        this.fDocumentHandler.endCDATA(null);
                        this.fCDataEnd = false;
                    }
                    break;
            }
            next = next();
            if (next != 8) {
            }
            if (next == 8) {
                return true;
            }
            this.fDocumentHandler.endDocument(null);
            return false;
        } while (z);
        if (next == 8) {
            return true;
        }
        this.fDocumentHandler.endDocument(null);
        return false;
    }

    public int scanEndElement() throws IOException, XNIException {
        QName qNamePopElement = this.fElementStack.popElement();
        String str = qNamePopElement.rawname;
        if (!this.fEntityScanner.skipString(str)) {
            reportFatalError("ETagRequired", new Object[]{str});
        }
        this.fEntityScanner.skipSpaces();
        if (!this.fEntityScanner.skipChar(62, XMLScanner.NameType.ELEMENTEND)) {
            reportFatalError("ETagUnterminated", new Object[]{str});
        }
        int i = this.fMarkupDepth - 2;
        this.fMarkupDepth = i;
        if (i < this.fEntityStack[this.fEntityDepth - 1]) {
            reportFatalError("ElementEntityMismatch", new Object[]{str});
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.endElement(qNamePopElement, null);
        }
        DTDGrammarUtil dTDGrammarUtil = this.dtdGrammarUtil;
        if (dTDGrammarUtil != null) {
            dTDGrammarUtil.endElement(qNamePopElement);
        }
        return this.fMarkupDepth;
    }

    public void scanEntityReference(XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
        XMLScanner.NameType nameType = XMLScanner.NameType.REFERENCE;
        String strScanName = xMLEntityScanner.scanName(nameType);
        if (strScanName == null) {
            reportFatalError("NameRequiredInReference", null);
            return;
        }
        if (!this.fEntityScanner.skipChar(59, nameType)) {
            reportFatalError("SemicolonRequiredInReference", new Object[]{strScanName});
        }
        if (this.fEntityStore.isUnparsedEntity(strScanName)) {
            reportFatalError("ReferenceToUnparsedEntity", new Object[]{strScanName});
        }
        this.fMarkupDepth--;
        this.fCurrentEntityName = strScanName;
        String str = XMLScanner.fAmpSymbol;
        if (strScanName == str) {
            handleCharacter('&', str, xMLStringBuffer);
            this.fScannerState = 41;
            return;
        }
        String str2 = XMLScanner.fLtSymbol;
        if (strScanName == str2) {
            handleCharacter('<', str2, xMLStringBuffer);
            this.fScannerState = 41;
            return;
        }
        String str3 = XMLScanner.fGtSymbol;
        if (strScanName == str3) {
            handleCharacter('>', str3, xMLStringBuffer);
            this.fScannerState = 41;
            return;
        }
        String str4 = XMLScanner.fQuotSymbol;
        if (strScanName == str4) {
            handleCharacter('\"', str4, xMLStringBuffer);
            this.fScannerState = 41;
            return;
        }
        String str5 = XMLScanner.fAposSymbol;
        if (strScanName == str5) {
            handleCharacter('\'', str5, xMLStringBuffer);
            this.fScannerState = 41;
            return;
        }
        boolean zIsExternalEntity = this.fEntityStore.isExternalEntity(strScanName);
        if ((zIsExternalEntity && !this.fSupportExternalEntities) || ((!zIsExternalEntity && !this.fReplaceEntityReferences) || this.foundBuiltInRefs)) {
            this.fScannerState = 28;
            return;
        }
        if (!this.fEntityStore.isDeclaredEntity(strScanName)) {
            if (!this.fSupportDTD && this.fReplaceEntityReferences) {
                reportFatalError("EntityNotDeclared", new Object[]{strScanName});
                return;
            } else if (!this.fHasExternalDTD || this.fStandalone) {
                reportFatalError("EntityNotDeclared", new Object[]{strScanName});
            } else if (this.fValidation) {
                this.fErrorReporter.reportError((XMLLocator) this.fEntityScanner, "http://www.w3.org/TR/1998/REC-xml-19980210", "EntityNotDeclared", new Object[]{strScanName}, (short) 1);
            }
        }
        if (this.fCreateEntityRefNodes) {
            this.fDocumentHandler.startGeneralEntity(strScanName, null, null, null);
        } else {
            this.fEntityManager.startEntity(true, strScanName, false);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner
    public void scanPIData(String str, XMLStringBuffer xMLStringBuffer) throws IOException, XNIException {
        super.scanPIData(str, xMLStringBuffer);
        this.fPITarget = str;
        this.fMarkupDepth--;
    }

    public boolean scanStartElement() throws IOException, XNIException {
        if (this.fSkip && !this.fAdd) {
            QName next = this.fElementStack.getNext();
            boolean zSkipString = this.fEntityScanner.skipString(next.rawname);
            this.fSkip = zSkipString;
            ElementStack elementStack = this.fElementStack;
            if (zSkipString) {
                elementStack.push();
                this.fElementQName = next;
            } else {
                elementStack.reposition();
            }
        }
        if (!this.fSkip || this.fAdd) {
            QName qNameNextElement = this.fElementStack.nextElement();
            this.fElementQName = qNameNextElement;
            boolean z = this.fNamespaces;
            XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
            if (z) {
                xMLEntityScanner.scanQName(qNameNextElement, XMLScanner.NameType.ELEMENTSTART);
            } else {
                String strScanName = xMLEntityScanner.scanName(XMLScanner.NameType.ELEMENTSTART);
                this.fElementQName.setValues(null, strScanName, strScanName, null);
            }
        }
        if (this.fAdd) {
            this.fElementStack.matchElement(this.fElementQName);
        }
        QName qName = this.fElementQName;
        this.fCurrentElement = qName;
        String str = qName.rawname;
        this.fEmptyElement = false;
        this.fAttributes.removeAllAttributes();
        checkDepth(str);
        if (!seekCloseOfStartTag()) {
            this.fReadingAttributes = true;
            this.fAttributeCacheUsedCount = 0;
            this.fStringBufferIndex = 0;
            this.fAddDefaultAttr = true;
            do {
                scanAttribute(this.fAttributes);
                XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
                if (xMLSecurityManager != null && !xMLSecurityManager.isNoLimit(this.fElementAttributeLimit)) {
                    int length = this.fAttributes.getLength();
                    int i = this.fElementAttributeLimit;
                    if (length > i) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "ElementAttributeLimit", new Object[]{str, Integer.valueOf(i)}, (short) 2);
                    }
                }
            } while (!seekCloseOfStartTag());
            this.fReadingAttributes = false;
        }
        if (this.fEmptyElement) {
            int i2 = this.fMarkupDepth - 1;
            this.fMarkupDepth = i2;
            if (i2 < this.fEntityStack[this.fEntityDepth - 1]) {
                reportFatalError("ElementEntityMismatch", new Object[]{this.fCurrentElement.rawname});
            }
            XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
            if (xMLDocumentHandler != null) {
                xMLDocumentHandler.emptyElement(this.fElementQName, this.fAttributes, null);
            }
            this.fElementStack.popElement();
        } else {
            DTDGrammarUtil dTDGrammarUtil = this.dtdGrammarUtil;
            if (dTDGrammarUtil != null) {
                dTDGrammarUtil.startElement(this.fElementQName, this.fAttributes);
            }
            XMLDocumentHandler xMLDocumentHandler2 = this.fDocumentHandler;
            if (xMLDocumentHandler2 != null) {
                xMLDocumentHandler2.startElement(this.fElementQName, this.fAttributes, null);
            }
        }
        return this.fEmptyElement;
    }

    public void scanXMLDeclOrTextDecl(boolean z) throws IOException, XNIException {
        super.scanXMLDeclOrTextDecl(z, this.fStrings);
        this.fMarkupDepth--;
        String[] strArr = this.fStrings;
        String str = strArr[0];
        String str2 = strArr[1];
        String str3 = strArr[2];
        this.fDeclaredEncoding = str2;
        boolean z2 = str3 != null;
        this.fStandaloneSet = z2;
        boolean z3 = z2 && str3.equals(JdkConstants.JDK_YES);
        this.fStandalone = z3;
        this.fEntityManager.setStandalone(z3);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            if (z) {
                xMLDocumentHandler.textDecl(str, str2, null);
            } else {
                xMLDocumentHandler.xmlDecl(str, str2, str3, null);
            }
        }
        if (str != null) {
            this.fEntityScanner.setVersion(str);
            this.fEntityScanner.setXMLVersion(str);
        }
        if (str2 == null || this.fEntityScanner.getCurrentEntity().isEncodingExternallySpecified()) {
            return;
        }
        this.fEntityScanner.setEncoding(str2);
    }

    public boolean seekCloseOfStartTag() throws IOException, XNIException {
        boolean zSkipSpaces = this.fEntityScanner.skipSpaces();
        int iPeekChar = this.fEntityScanner.peekChar();
        if (iPeekChar == 62) {
            this.fEntityScanner.scanChar(null);
            return true;
        }
        if (iPeekChar == 47) {
            this.fEntityScanner.scanChar(null);
            if (!this.fEntityScanner.skipChar(62, XMLScanner.NameType.ELEMENTEND)) {
                reportFatalError("ElementUnterminated", new Object[]{this.fElementQName.rawname});
            }
            this.fEmptyElement = true;
            return true;
        }
        if (isValidNameStartChar(iPeekChar) && zSkipSpaces) {
            return false;
        }
        if (isValidNameStartHighSurrogate(iPeekChar) && zSkipSpaces) {
            return false;
        }
        reportFatalError("ElementUnterminated", new Object[]{this.fElementQName.rawname});
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
    }

    public final void setDriver(Driver driver) {
        this.fDriver = driver;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
        super.setFeature(str, z);
        if (str.startsWith(Constants.XERCES_FEATURE_PREFIX) && str.substring(31).equals(Constants.NOTIFY_BUILTIN_REFS_FEATURE)) {
            this.fNotifyBuiltInRefs = z;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentScanner
    public void setInputSource(XMLInputSource xMLInputSource) throws IOException {
        this.fEntityManager.setEntityHandler(this);
        this.fEntityManager.startEntity(false, "$fragment$", xMLInputSource, false, true);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        super.setProperty(str, obj);
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int length = str.length() - 33;
            if (length == 23 && str.endsWith(Constants.ENTITY_MANAGER_PROPERTY)) {
                this.fEntityManager = (XMLEntityManager) obj;
                return;
            } else if (length == 24 && str.endsWith(Constants.ENTITY_RESOLVER_PROPERTY)) {
                this.fExternalSubsetResolver = obj instanceof ExternalSubsetResolver ? (ExternalSubsetResolver) obj : null;
                return;
            }
        }
        if (str.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            if (str.substring(33).equals(Constants.ENTITY_MANAGER_PROPERTY)) {
                this.fEntityManager = (XMLEntityManager) obj;
            }
        } else if (str.equals("jdk.xml.xmlSecurityPropertyManager")) {
            this.fAccessExternalDTD = ((XMLSecurityPropertyManager) obj).getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
        }
    }

    public final void setScannerState(int i) {
        this.fScannerState = i;
    }

    public boolean skipElement(short s) throws IOException {
        short s2 = (short) this.fElementStack.fDepth;
        if (s2 > 5) {
            this.fShouldSkip = false;
            return false;
        }
        while (s < 4) {
            short elementPointer = getElementPointer(s2, s);
            if (elementPointer == 0) {
                this.fShouldSkip = false;
                return false;
            }
            String str = this.fElementArray[elementPointer];
            if (str != null && skipFromTheBuffer(str)) {
                this.fLastPointerLocation = elementPointer;
                this.fShouldSkip = true;
                return true;
            }
            s = (short) (s + 1);
        }
        this.fShouldSkip = false;
        return false;
    }

    public boolean skipFromTheBuffer(String str) throws IOException {
        if (!this.fEntityScanner.skipString(str)) {
            return false;
        }
        char cPeekChar = (char) this.fEntityScanner.peekChar();
        if (cPeekChar != ' ' && cPeekChar != '/' && cPeekChar != '>') {
            return false;
        }
        this.fElementRawname = str;
        return true;
    }

    public boolean skipQElement(String str) throws IOException {
        if (XMLChar.isName(this.fEntityScanner.getChar(str.length()))) {
            return false;
        }
        return this.fEntityScanner.skipString(str);
    }

    public boolean standaloneSet() {
        return this.fStandaloneSet;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.impl.XMLEntityHandler
    public void startEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        int i = this.fEntityDepth;
        int[] iArr = this.fEntityStack;
        if (i == iArr.length) {
            int[] iArr2 = new int[iArr.length * 2];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.fEntityStack = iArr2;
        }
        this.fEntityStack[this.fEntityDepth] = this.fMarkupDepth;
        super.startEntity(str, xMLResourceIdentifier, str2, augmentations);
        if (this.fStandalone && this.fEntityStore.isEntityDeclInExternalSubset(str)) {
            reportFatalError("MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[]{str});
        }
        if (this.fDocumentHandler == null || this.fScanningAttribute || str.equals("[xml]")) {
            return;
        }
        this.fDocumentHandler.startGeneralEntity(str, xMLResourceIdentifier, str2, augmentations);
    }

    public short storePointerForADepth(short s) {
        short s2 = (short) this.fElementStack.fDepth;
        for (short s3 = 0; s3 < 4; s3 = (short) (s3 + 1)) {
            if (canStore(s2, s3)) {
                this.fPointerInfo[s2][s3] = s;
                return s3;
            }
        }
        return (short) -1;
    }

    @Override // com.sun.xml.internal.stream.XMLBufferListener
    public void refresh() {
        refresh(0);
    }

    public boolean skipElement() throws IOException {
        if (!this.fShouldSkip) {
            return false;
        }
        short s = this.fLastPointerLocation;
        if (s != 0) {
            String str = this.fElementArray[s + 1];
            if (str != null && skipFromTheBuffer(str)) {
                this.fLastPointerLocation = (short) (this.fLastPointerLocation + 1);
                return true;
            }
            this.fLastPointerLocation = (short) 0;
        }
        return this.fShouldSkip && skipElement((short) 0);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLScanner, com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        super.reset(xMLComponentManager);
        this.fReportCdataEvent = xMLComponentManager.getFeature(Constants.STAX_REPORT_CDATA_EVENT, true);
        this.fSecurityManager = (XMLSecurityManager) xMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager", null);
        this.fNotifyBuiltInRefs = xMLComponentManager.getFeature(NOTIFY_BUILTIN_REFS, false);
        this.fCreateEntityRefNodes = xMLComponentManager.getFeature(CREATE_ENTITY_REF_NODES, this.fCreateEntityRefNodes);
        Object property = xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver", null);
        this.fExternalSubsetResolver = property instanceof ExternalSubsetResolver ? (ExternalSubsetResolver) property : null;
        this.fReadingAttributes = false;
        this.fSupportExternalEntities = true;
        this.fReplaceEntityReferences = true;
        this.fIsCoalesce = false;
        setScannerState(22);
        setDriver(this.fContentDriver);
        this.fAccessExternalDTD = ((XMLSecurityPropertyManager) xMLComponentManager.getProperty("jdk.xml.xmlSecurityPropertyManager", null)).getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
        this.fStrictURI = xMLComponentManager.getFeature(STANDARD_URI_CONFORMANT, false);
        this.fChunkSize = JdkXmlUtils.getValue(xMLComponentManager.getProperty(JdkConstants.CDATA_CHUNK_SIZE), JdkConstants.CDATA_CHUNK_SIZE_DEFAULT);
        resetCommon();
    }
}
