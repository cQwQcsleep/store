package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;
import com.sun.org.apache.xerces.internal.impl.io.UCSReader;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.org.apache.xerces.internal.util.EncodingMap;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.utils.XMLLimitAnalyzer;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.stream.XMLBufferListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLEntityScanner implements XMLLocator {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_ENCODINGS = false;
    private static final boolean DEBUG_SKIP_STRING = false;
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    boolean counted;
    boolean detectingVersion;
    protected boolean fAllowJavaEncodings;
    protected int fBufferSize;
    protected Entity.ScannedEntity fCurrentEntity;
    protected XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    protected XMLLimitAnalyzer fLimitAnalyzer;
    protected PropertyManager fPropertyManager;
    protected XMLSecurityManager fSecurityManager;
    protected SymbolTable fSymbolTable;
    boolean isExternal;
    private ArrayList<XMLBufferListener> listeners;
    int newlines;
    int offset;
    boolean whiteSpaceInfoNeeded;
    int whiteSpaceLen;
    int[] whiteSpaceLookup;
    protected boolean xmlVersionSetExplicitly;
    private static final boolean[] VALID_NAMES = new boolean[127];
    private static final EOFException END_OF_DOCUMENT_ENTITY = new EOFException() { // from class: com.sun.org.apache.xerces.internal.impl.XMLEntityScanner.1
        private static final long serialVersionUID = 980337771224675268L;

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            return this;
        }
    };

    static {
        for (int i = 65; i <= 90; i++) {
            VALID_NAMES[i] = true;
        }
        for (int i2 = 97; i2 <= 122; i2++) {
            VALID_NAMES[i2] = true;
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            VALID_NAMES[i3] = true;
        }
        boolean[] zArr = VALID_NAMES;
        zArr[45] = true;
        zArr[46] = true;
        zArr[58] = true;
        zArr[95] = true;
    }

    public XMLEntityScanner(PropertyManager propertyManager, XMLEntityManager xMLEntityManager) {
        this.fCurrentEntity = null;
        this.fBufferSize = 8192;
        this.fSecurityManager = null;
        this.fLimitAnalyzer = null;
        this.listeners = new ArrayList<>();
        this.fSymbolTable = null;
        this.fErrorReporter = null;
        this.whiteSpaceLookup = new int[100];
        this.whiteSpaceLen = 0;
        this.whiteSpaceInfoNeeded = true;
        this.fPropertyManager = null;
        this.isExternal = false;
        this.xmlVersionSetExplicitly = false;
        this.detectingVersion = false;
        this.offset = 0;
        this.newlines = 0;
        this.counted = false;
        this.fEntityManager = xMLEntityManager;
        reset(propertyManager);
    }

    private void resetCommon() {
        this.fCurrentEntity = null;
        this.whiteSpaceLen = 0;
        this.whiteSpaceInfoNeeded = true;
        this.listeners.clear();
        XMLEntityManager xMLEntityManager = this.fEntityManager;
        this.fLimitAnalyzer = xMLEntityManager.fLimitAnalyzer;
        this.fSecurityManager = xMLEntityManager.fSecurityManager;
    }

    public boolean arrangeCapacity(int i, boolean z) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.count - scannedEntity.position >= i) {
            return true;
        }
        while (true) {
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            int i2 = scannedEntity2.count;
            int i3 = scannedEntity2.position;
            if (i2 - i3 >= i) {
                break;
            }
            if (scannedEntity2.ch.length - i3 < i) {
                invokeListeners(0);
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                char[] cArr = scannedEntity3.ch;
                int i4 = scannedEntity3.position;
                System.arraycopy(cArr, i4, cArr, 0, scannedEntity3.count - i4);
                Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                scannedEntity4.count -= scannedEntity4.position;
                scannedEntity4.position = 0;
            }
            Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
            int i5 = scannedEntity5.count;
            int i6 = scannedEntity5.position;
            if (i5 - i6 < i) {
                invokeListeners(i6);
                boolean zLoad = load(this.fCurrentEntity.count, z, false);
                this.fCurrentEntity.position = i6;
                if (zLoad) {
                    break;
                }
            }
        }
        Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
        return scannedEntity6.count - scannedEntity6.position >= i;
    }

    public int checkBeforeLoad(Entity.ScannedEntity scannedEntity, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5 = scannedEntity.position + 1;
        scannedEntity.position = i5;
        if (i5 != scannedEntity.count) {
            return 0;
        }
        int i6 = i5 - i;
        if (i2 != -1) {
            i3 = i2 - i;
            i4 = i6 - i3;
        } else {
            i3 = i;
            i4 = i6;
        }
        checkLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT, scannedEntity, i3, i4);
        invokeListeners(i6);
        char[] cArr = scannedEntity.ch;
        if (i6 != cArr.length) {
            System.arraycopy(cArr, i, cArr, 0, i6);
            return i6;
        }
        char[] cArr2 = new char[scannedEntity.fBufferSize * 2];
        System.arraycopy(cArr, i, cArr2, 0, i6);
        scannedEntity.ch = cArr2;
        scannedEntity.fBufferSize *= 2;
        return i6;
    }

    public void checkEntityLimit(XMLScanner.NameType nameType, Entity.ScannedEntity scannedEntity, int i, int i2) {
        if (scannedEntity == null || !scannedEntity.isGE) {
            return;
        }
        if (nameType != XMLScanner.NameType.REFERENCE) {
            checkLimit(XMLSecurityManager.Limit.GENERAL_ENTITY_SIZE_LIMIT, scannedEntity, i, i2);
        }
        if (nameType == XMLScanner.NameType.ELEMENTSTART || nameType == XMLScanner.NameType.ATTRIBUTENAME) {
            checkNodeCount(scannedEntity);
        }
    }

    public void checkLimit(XMLSecurityManager.Limit limit, Entity.ScannedEntity scannedEntity, int i, int i2) {
        this.fLimitAnalyzer.addValue(limit, scannedEntity.name, i2);
        if (this.fSecurityManager.isOverLimit(limit, this.fLimitAnalyzer)) {
            this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", limit.key(), limit == XMLSecurityManager.Limit.ENTITY_REPLACEMENT_LIMIT ? new Object[]{Integer.valueOf(this.fLimitAnalyzer.getValue(limit)), Integer.valueOf(this.fSecurityManager.getLimit(limit)), this.fSecurityManager.getStateLiteral(limit)} : new Object[]{scannedEntity.name, Integer.valueOf(this.fLimitAnalyzer.getValue(limit)), Integer.valueOf(this.fSecurityManager.getLimit(limit)), this.fSecurityManager.getStateLiteral(limit)}, (short) 2);
        }
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        XMLSecurityManager.Limit limit2 = XMLSecurityManager.Limit.TOTAL_ENTITY_SIZE_LIMIT;
        if (xMLSecurityManager.isOverLimit(limit2, this.fLimitAnalyzer)) {
            this.fSecurityManager.debugPrint(this.fLimitAnalyzer);
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "TotalEntitySizeLimit", new Object[]{Integer.valueOf(this.fLimitAnalyzer.getTotalValue(limit2)), Integer.valueOf(this.fSecurityManager.getLimit(limit2)), this.fSecurityManager.getStateLiteral(limit2)}, (short) 2);
        }
    }

    public void checkNodeCount(Entity.ScannedEntity scannedEntity) {
        if (scannedEntity == null || !scannedEntity.isGE) {
            return;
        }
        checkLimit(XMLSecurityManager.Limit.ENTITY_REPLACEMENT_LIMIT, scannedEntity, 0, 1);
    }

    public Reader createReader(InputStream inputStream, String str, Boolean bool) throws IOException {
        if (str == null) {
            str = "UTF-8";
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        if (upperCase.equals("UTF-8")) {
            return new UTF8Reader(inputStream, this.fCurrentEntity.fBufferSize, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
        }
        if (upperCase.equals("US-ASCII")) {
            return new ASCIIReader(inputStream, this.fCurrentEntity.fBufferSize, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
        }
        if (upperCase.equals(XMLEntityManager.EncodingInfo.STR_UCS4)) {
            if (bool != null) {
                return bool.booleanValue() ? new UCSReader(inputStream, (short) 8) : new UCSReader(inputStream, (short) 4);
            }
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
        }
        if (upperCase.equals(XMLEntityManager.EncodingInfo.STR_UCS2)) {
            if (bool != null) {
                return bool.booleanValue() ? new UCSReader(inputStream, (short) 2) : new UCSReader(inputStream, (short) 1);
            }
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[]{str}, (short) 2);
        }
        boolean zIsValidIANAEncoding = XMLChar.isValidIANAEncoding(str);
        boolean zIsValidJavaEncoding = XMLChar.isValidJavaEncoding(str);
        if (!zIsValidIANAEncoding || (this.fAllowJavaEncodings && !zIsValidJavaEncoding)) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[]{str}, (short) 2);
            str = "ISO-8859-1";
        }
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(upperCase);
        if (iANA2JavaMapping == null) {
            if (!this.fAllowJavaEncodings) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[]{str}, (short) 2);
                str = "ISO8859_1";
            }
        } else {
            if (iANA2JavaMapping.equals("ASCII")) {
                return new ASCIIReader(inputStream, this.fBufferSize, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
            }
            str = iANA2JavaMapping;
        }
        return new InputStreamReader(inputStream, str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final String getBaseSystemId() {
        XMLResourceIdentifier xMLResourceIdentifier;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null || (xMLResourceIdentifier = scannedEntity.entityLocation) == null) {
            return null;
        }
        return xMLResourceIdentifier.getExpandedSystemId();
    }

    public int getChar(int i) throws IOException {
        if (!arrangeCapacity(i + 1, false)) {
            return -1;
        }
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        return scannedEntity.ch[scannedEntity.position + i];
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final int getCharacterOffset() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity != null) {
            return scannedEntity.fTotalCountTillLastLoad + scannedEntity.position;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final int getColumnNumber() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity != null) {
            return scannedEntity.columnNumber;
        }
        return -1;
    }

    public Entity.ScannedEntity getCurrentEntity() {
        return this.fCurrentEntity;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final String getEncoding() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity != null) {
            return scannedEntity.encoding;
        }
        return null;
    }

    public Object[] getEncodingName(byte[] bArr, int i) {
        if (i < 2) {
            return new Object[]{"UTF-8", null};
        }
        int i2 = bArr[0] & 255;
        int i3 = bArr[1] & 255;
        if (i2 == 254 && i3 == 255) {
            return new Object[]{XMLEntityManager.EncodingInfo.STR_UTF16BE, Boolean.TRUE};
        }
        if (i2 == 255 && i3 == 254) {
            return new Object[]{XMLEntityManager.EncodingInfo.STR_UTF16LE, Boolean.FALSE};
        }
        if (i < 3) {
            return new Object[]{"UTF-8", null};
        }
        int i4 = bArr[2] & 255;
        if ((i2 != 239 || i3 != 187 || i4 != 191) && i >= 4) {
            int i5 = bArr[3] & 255;
            if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 60) {
                return new Object[]{XMLEntityManager.EncodingInfo.STR_UCS4, Boolean.TRUE};
            }
            if (i2 == 60 && i3 == 0 && i4 == 0 && i5 == 0) {
                return new Object[]{XMLEntityManager.EncodingInfo.STR_UCS4, Boolean.FALSE};
            }
            if (i2 == 0 && i3 == 0 && i4 == 60 && i5 == 0) {
                return new Object[]{XMLEntityManager.EncodingInfo.STR_UCS4, null};
            }
            if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 0) {
                return new Object[]{XMLEntityManager.EncodingInfo.STR_UCS4, null};
            }
            if (i2 == 0 && i3 == 60 && i4 == 0 && i5 == 63) {
                return new Object[]{XMLEntityManager.EncodingInfo.STR_UTF16BE, Boolean.TRUE};
            }
            if (i2 == 60 && i3 == 0 && i4 == 63 && i5 == 0) {
                return new Object[]{XMLEntityManager.EncodingInfo.STR_UTF16LE, Boolean.FALSE};
            }
            return (i2 == 76 && i3 == 111 && i4 == 167 && i5 == 148) ? new Object[]{XMLEntityManager.EncodingInfo.STR_CP037, null} : new Object[]{"UTF-8", null};
        }
        return new Object[]{"UTF-8", null};
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final String getExpandedSystemId() {
        XMLResourceIdentifier xMLResourceIdentifier;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null || (xMLResourceIdentifier = scannedEntity.entityLocation) == null) {
            return null;
        }
        return xMLResourceIdentifier.getExpandedSystemId();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final int getLineNumber() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity != null) {
            return scannedEntity.lineNumber;
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final String getLiteralSystemId() {
        XMLResourceIdentifier xMLResourceIdentifier;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null || (xMLResourceIdentifier = scannedEntity.entityLocation) == null) {
            return null;
        }
        return xMLResourceIdentifier.getLiteralSystemId();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final String getPublicId() {
        XMLResourceIdentifier xMLResourceIdentifier;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity == null || (xMLResourceIdentifier = scannedEntity.entityLocation) == null) {
            return null;
        }
        return xMLResourceIdentifier.getPublicId();
    }

    public String getVersion() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity != null) {
            return scannedEntity.version;
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public final String getXMLVersion() {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity != null) {
            return scannedEntity.xmlVersion;
        }
        return null;
    }

    public void invokeListeners(int i) {
        for (int i2 = 0; i2 < this.listeners.size(); i2++) {
            this.listeners.get(i2).refresh(i);
        }
    }

    public final boolean isExternal() {
        return this.fCurrentEntity.isExternal();
    }

    public boolean isSpace(char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r';
    }

    public final boolean load(int i, boolean z, boolean z2) throws IOException {
        if (z2) {
            invokeListeners(i);
        }
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        scannedEntity.fTotalCountTillLastLoad += scannedEntity.fLastCount;
        char[] cArr = scannedEntity.ch;
        int length = cArr.length - i;
        if (!scannedEntity.mayReadChunks && length > 64) {
            length = 64;
        }
        int i2 = scannedEntity.reader.read(cArr, i, length);
        if (i2 != -1) {
            if (i2 != 0) {
                Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
                scannedEntity2.fLastCount = i2;
                scannedEntity2.count = i2 + i;
                scannedEntity2.position = i;
            }
            return false;
        }
        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
        scannedEntity3.count = i;
        scannedEntity3.position = i;
        if (z) {
            this.fEntityManager.endEntity();
            Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
            if (scannedEntity4 == null) {
                throw END_OF_DOCUMENT_ENTITY;
            }
            if (scannedEntity4.position == scannedEntity4.count) {
                load(0, true, false);
                return true;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:24:0x0071  */
    /* JADX WARN: Code duplicated, block: B:30:0x0086  */
    public boolean normalizeNewlines(short s, XMLString xMLString, boolean z, boolean z2, XMLScanner.NameType nameType) throws IOException {
        Entity.ScannedEntity scannedEntity;
        Entity.ScannedEntity scannedEntity2;
        int i;
        char c;
        Entity.ScannedEntity scannedEntity3;
        int i2;
        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
        int i3 = scannedEntity4.position;
        this.offset = i3;
        char c2 = scannedEntity4.ch[i3];
        this.newlines = 0;
        this.counted = false;
        if (c2 == '\n' || c2 == '\r' || (s == 2 && ((c2 == 133 || c2 == 8232) && this.isExternal))) {
            do {
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                char[] cArr = scannedEntity5.ch;
                int i4 = scannedEntity5.position;
                int i5 = i4 + 1;
                scannedEntity5.position = i5;
                char c3 = cArr[i4];
                if (c3 != '\n' && c3 != '\r' && (s != 2 || (c3 != 133 && c3 != 8232))) {
                    scannedEntity5.position = i4;
                    break;
                }
                int i6 = this.newlines + 1;
                this.newlines = i6;
                scannedEntity5.lineNumber++;
                scannedEntity5.columnNumber = 1;
                if (i5 == scannedEntity5.count) {
                    checkEntityLimit(nameType, scannedEntity5, this.offset, i6);
                    this.offset = 0;
                    Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                    int i7 = this.newlines;
                    scannedEntity6.position = i7;
                    if (load(i7, false, true)) {
                        this.counted = true;
                        break;
                    }
                    if (c3 == '\r') {
                        scannedEntity2 = this.fCurrentEntity;
                        char[] cArr2 = scannedEntity2.ch;
                        i = scannedEntity2.position;
                        c = cArr2[i];
                        if (c != '\n' || (s == 2 && c == 133)) {
                            scannedEntity2.position = i + 1;
                            this.offset++;
                        } else {
                            this.newlines++;
                        }
                    }
                    scannedEntity = this.fCurrentEntity;
                } else {
                    if (c3 == '\r') {
                        scannedEntity2 = this.fCurrentEntity;
                        char[] cArr3 = scannedEntity2.ch;
                        i = scannedEntity2.position;
                        c = cArr3[i];
                        if (c != '\n') {
                            scannedEntity2.position = i + 1;
                            this.offset++;
                        } else {
                            scannedEntity2.position = i + 1;
                            this.offset++;
                        }
                    }
                    scannedEntity = this.fCurrentEntity;
                }
            } while (scannedEntity.position < scannedEntity.count - 1);
            int i8 = this.offset;
            while (true) {
                scannedEntity3 = this.fCurrentEntity;
                i2 = scannedEntity3.position;
                if (i8 >= i2) {
                    break;
                }
                scannedEntity3.ch[i8] = '\n';
                if (z2) {
                    storeWhiteSpace(i8);
                }
                i8++;
            }
            int i9 = this.offset;
            int i10 = i2 - i9;
            if (i2 == scannedEntity3.count - 1) {
                checkEntityLimit(nameType, scannedEntity3, i9, i10);
                Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
                if (z) {
                    xMLString.append(scannedEntity7.ch, this.offset, i10);
                } else {
                    xMLString.setValues(scannedEntity7.ch, this.offset, i10);
                }
                return true;
            }
        }
        return false;
    }

    public int peekChar() throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        char c = scannedEntity2.ch[scannedEntity2.position];
        if (this.isExternal && c == '\r') {
            return 10;
        }
        return c;
    }

    public final void print() {
    }

    public void registerListener(XMLBufferListener xMLBufferListener) {
        if (this.listeners.contains(xMLBufferListener)) {
            return;
        }
        this.listeners.add(xMLBufferListener);
    }

    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        this.fAllowJavaEncodings = xMLComponentManager.getFeature(ALLOW_JAVA_ENCODINGS, false);
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        resetCommon();
    }

    public int scanChar(XMLScanner.NameType nameType) throws IOException {
        Entity.ScannedEntity scannedEntity;
        int i;
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        if (scannedEntity2.position == scannedEntity2.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
        int i2 = scannedEntity3.position;
        this.offset = i2;
        char[] cArr = scannedEntity3.ch;
        int i3 = i2 + 1;
        scannedEntity3.position = i3;
        char c = cArr[i2];
        if (c == '\n' || (c == '\r' && this.isExternal)) {
            scannedEntity3.lineNumber++;
            scannedEntity3.columnNumber = 1;
            if (i3 == scannedEntity3.count) {
                invokeListeners(1);
                this.fCurrentEntity.ch[0] = c;
                load(1, true, false);
                this.offset = 0;
            }
            if (c == '\r' && this.isExternal && (i = (scannedEntity = this.fCurrentEntity).position) < scannedEntity.count) {
                char[] cArr2 = scannedEntity.ch;
                scannedEntity.position = i + 1;
                if (cArr2[i] != '\n') {
                    scannedEntity.position = i;
                }
                c = '\n';
            }
        }
        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
        scannedEntity4.columnNumber++;
        if (!this.detectingVersion) {
            int i4 = this.offset;
            checkEntityLimit(nameType, scannedEntity4, i4, scannedEntity4.position - i4);
        }
        return c;
    }

    public int scanContent(XMLString xMLString) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        int i = scannedEntity.position;
        int i2 = scannedEntity.count;
        if (i == i2) {
            load(0, true, true);
        } else if (i == i2 - 1) {
            invokeListeners(1);
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            char[] cArr = scannedEntity2.ch;
            cArr[0] = cArr[scannedEntity2.count - 1];
            load(1, false, false);
            this.fCurrentEntity.position = 0;
        }
        if (normalizeNewlines((short) 1, xMLString, false, false, null)) {
            return -1;
        }
        while (true) {
            Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
            int i3 = scannedEntity3.position;
            if (i3 >= scannedEntity3.count) {
                break;
            }
            char[] cArr2 = scannedEntity3.ch;
            scannedEntity3.position = i3 + 1;
            if (!XMLChar.isContent(cArr2[i3])) {
                this.fCurrentEntity.position--;
                break;
            }
        }
        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
        int i4 = scannedEntity4.position;
        int i5 = this.offset;
        int i6 = i4 - i5;
        scannedEntity4.columnNumber += i6 - this.newlines;
        if (!this.counted) {
            checkEntityLimit(null, scannedEntity4, i5, i6);
        }
        xMLString.setValues(this.fCurrentEntity.ch, this.offset, i6);
        Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
        int i7 = scannedEntity5.position;
        if (i7 == scannedEntity5.count) {
            return -1;
        }
        char c = scannedEntity5.ch[i7];
        if (c == '\r' && this.isExternal) {
            return 10;
        }
        return c;
    }

    public boolean scanData(String str, XMLStringBuffer xMLStringBuffer, int i) throws IOException {
        Entity.ScannedEntity scannedEntity;
        int i2;
        int i3;
        int length = str.length();
        char cCharAt = str.charAt(0);
        boolean z = false;
        do {
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            if (scannedEntity2.position == scannedEntity2.count) {
                load(0, true, false);
            }
            boolean zLoad = false;
            while (true) {
                scannedEntity = this.fCurrentEntity;
                i2 = scannedEntity.position;
                i3 = scannedEntity.count;
                if (i2 <= i3 - length || zLoad) {
                    break;
                }
                char[] cArr = scannedEntity.ch;
                System.arraycopy(cArr, i2, cArr, 0, i3 - i2);
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                zLoad = load(scannedEntity3.count - scannedEntity3.position, false, false);
                Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                scannedEntity4.position = 0;
                scannedEntity4.startPosition = 0;
            }
            if (i2 > i3 - length) {
                int i4 = i3 - i2;
                checkEntityLimit(XMLScanner.NameType.COMMENT, scannedEntity, i2, i4);
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                xMLStringBuffer.append(scannedEntity5.ch, scannedEntity5.position, i4);
                Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                int i5 = scannedEntity6.columnNumber;
                int i6 = scannedEntity6.count;
                scannedEntity6.columnNumber = i5 + i6;
                scannedEntity6.baseCharOffset += scannedEntity6.position - scannedEntity6.startPosition;
                scannedEntity6.position = i6;
                scannedEntity6.startPosition = i6;
                load(0, true, false);
                return false;
            }
            if (normalizeNewlines((short) 1, xMLStringBuffer, true, false, XMLScanner.NameType.COMMENT)) {
                return true;
            }
            while (true) {
                Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
                int i7 = scannedEntity7.position;
                if (i7 >= scannedEntity7.count) {
                    break;
                }
                char[] cArr2 = scannedEntity7.ch;
                scannedEntity7.position = i7 + 1;
                char c = cArr2[i7];
                if (c == cCharAt) {
                    for (int i8 = 1; i8 < length; i8++) {
                        Entity.ScannedEntity scannedEntity8 = this.fCurrentEntity;
                        int i9 = scannedEntity8.position;
                        if (i9 == scannedEntity8.count) {
                            scannedEntity8.position = i9 - i8;
                            break;
                        }
                        char[] cArr3 = scannedEntity8.ch;
                        scannedEntity8.position = i9 + 1;
                        if (str.charAt(i8) != cArr3[i9]) {
                            this.fCurrentEntity.position -= i8;
                            break;
                        }
                    }
                    if (this.fCurrentEntity.position == i7 + length) {
                        z = true;
                        break;
                    }
                    if (i <= 0 && (xMLStringBuffer.length + this.fCurrentEntity.position) - this.offset >= i) {
                        break;
                    }
                } else {
                    if (c == '\n' || (this.isExternal && c == '\r')) {
                        scannedEntity7.position = i7;
                        break;
                    }
                    if (XMLChar.isInvalid(c)) {
                        Entity.ScannedEntity scannedEntity9 = this.fCurrentEntity;
                        int i10 = scannedEntity9.position - 1;
                        scannedEntity9.position = i10;
                        int i11 = this.offset;
                        int i12 = i10 - i11;
                        scannedEntity9.columnNumber += i12 - this.newlines;
                        checkEntityLimit(XMLScanner.NameType.COMMENT, scannedEntity9, i11, i12);
                        xMLStringBuffer.append(this.fCurrentEntity.ch, this.offset, i12);
                        return true;
                    }
                    if (i <= 0) {
                    }
                }
            }
            Entity.ScannedEntity scannedEntity10 = this.fCurrentEntity;
            int i13 = scannedEntity10.position;
            int i14 = this.offset;
            int i15 = i13 - i14;
            scannedEntity10.columnNumber += i15 - this.newlines;
            checkEntityLimit(XMLScanner.NameType.COMMENT, scannedEntity10, i14, i15);
            if (z) {
                i15 -= length;
            }
            xMLStringBuffer.append(this.fCurrentEntity.ch, this.offset, i15);
            if ((i > 0 && xMLStringBuffer.length >= i) || z) {
                break;
            }
        } while (i == 0);
        return !z;
    }

    public int scanLiteral(int i, XMLString xMLString, boolean z) throws IOException {
        char c;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        int i2 = scannedEntity.position;
        int i3 = scannedEntity.count;
        if (i2 == i3) {
            load(0, true, true);
        } else if (i2 == i3 - 1) {
            invokeListeners(1);
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            char[] cArr = scannedEntity2.ch;
            cArr[0] = cArr[scannedEntity2.count - 1];
            load(1, false, false);
            this.fCurrentEntity.position = 0;
        }
        if (this.whiteSpaceInfoNeeded) {
            this.whiteSpaceLen = 0;
        }
        if (normalizeNewlines((short) 1, xMLString, false, true, null)) {
            return -1;
        }
        while (true) {
            Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
            int i4 = scannedEntity3.position;
            if (i4 >= scannedEntity3.count || (((c = scannedEntity3.ch[i4]) == i && (!scannedEntity3.literal || this.isExternal)) || c == '%' || !XMLChar.isContent(c) || (c == '\r' && !this.isExternal))) {
                break;
            }
            if (this.whiteSpaceInfoNeeded && c == '\t') {
                storeWhiteSpace(this.fCurrentEntity.position);
            }
            this.fCurrentEntity.position++;
        }
        Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
        int i5 = scannedEntity4.position;
        int i6 = this.offset;
        int i7 = i5 - i6;
        scannedEntity4.columnNumber += i7 - this.newlines;
        checkEntityLimit(null, scannedEntity4, i6, i7);
        if (z) {
            checkLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT, this.fCurrentEntity, this.offset, i7);
        }
        xMLString.setValues(this.fCurrentEntity.ch, this.offset, i7);
        Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
        int i8 = scannedEntity5.position;
        if (i8 == scannedEntity5.count) {
            return -1;
        }
        char c2 = scannedEntity5.ch[i8];
        if (c2 == i && scannedEntity5.literal) {
            return -1;
        }
        return c2;
    }

    public String scanName(XMLScanner.NameType nameType) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        int i = scannedEntity2.position;
        this.offset = i;
        if (XMLChar.isNameStart(scannedEntity2.ch[i])) {
            Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
            int i2 = scannedEntity3.position + 1;
            scannedEntity3.position = i2;
            if (i2 == scannedEntity3.count) {
                invokeListeners(1);
                char[] cArr = this.fCurrentEntity.ch;
                cArr[0] = cArr[this.offset];
                this.offset = 0;
                if (load(1, false, false)) {
                    Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                    scannedEntity4.columnNumber++;
                    return this.fSymbolTable.addSymbol(scannedEntity4.ch, 0, 1);
                }
            }
            while (true) {
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                char c = scannedEntity5.ch[scannedEntity5.position];
                if (!(c < 127 ? VALID_NAMES[c] : XMLChar.isName(c))) {
                    break;
                }
                Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                int i3 = this.offset;
                int iCheckBeforeLoad = checkBeforeLoad(scannedEntity6, i3, i3);
                if (iCheckBeforeLoad > 0) {
                    this.offset = 0;
                    if (load(iCheckBeforeLoad, false, false)) {
                        break;
                    }
                }
            }
        }
        Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
        int i4 = scannedEntity7.position;
        int i5 = this.offset;
        int i6 = i4 - i5;
        scannedEntity7.columnNumber += i6;
        if (i6 <= 0) {
            return null;
        }
        checkLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT, scannedEntity7, i5, i6);
        checkEntityLimit(nameType, this.fCurrentEntity, this.offset, i6);
        return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, this.offset, i6);
    }

    public String scanNmtoken() throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        this.offset = this.fCurrentEntity.position;
        while (true) {
            Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
            char c = scannedEntity2.ch[scannedEntity2.position];
            if (!(c < 127 ? VALID_NAMES[c] : XMLChar.isName(c))) {
                break;
            }
            Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
            int i = scannedEntity3.position + 1;
            scannedEntity3.position = i;
            if (i == scannedEntity3.count) {
                int i2 = i - this.offset;
                invokeListeners(i2);
                Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                int i3 = scannedEntity4.fBufferSize;
                if (i2 == i3) {
                    char[] cArr = new char[i3 * 2];
                    System.arraycopy(scannedEntity4.ch, this.offset, cArr, 0, i2);
                    Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                    scannedEntity5.ch = cArr;
                    scannedEntity5.fBufferSize *= 2;
                } else {
                    char[] cArr2 = scannedEntity4.ch;
                    System.arraycopy(cArr2, this.offset, cArr2, 0, i2);
                }
                this.offset = 0;
                if (load(i2, false, false)) {
                    break;
                }
            }
        }
        Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
        int i4 = scannedEntity6.position;
        int i5 = this.offset;
        int i6 = i4 - i5;
        scannedEntity6.columnNumber += i6;
        if (i6 > 0) {
            return this.fSymbolTable.addSymbol(scannedEntity6.ch, i5, i6);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0091  */
    /* JADX WARN: Code duplicated, block: B:45:0x008f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x009c A[EDGE_INSN: B:46:0x009c->B:31:0x009c BREAK  A[LOOP:0: B:14:0x0058->B:47:0x0058], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x0058 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x0058 A[SYNTHETIC] */
    public boolean scanQName(QName qName, XMLScanner.NameType nameType) throws IOException {
        String strAddSymbol;
        String strAddSymbol2;
        int iCheckBeforeLoad;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        int i = scannedEntity2.position;
        this.offset = i;
        if (XMLChar.isNameStart(scannedEntity2.ch[i])) {
            Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
            int i2 = scannedEntity3.position + 1;
            scannedEntity3.position = i2;
            if (i2 == scannedEntity3.count) {
                invokeListeners(1);
                char[] cArr = this.fCurrentEntity.ch;
                cArr[0] = cArr[this.offset];
                this.offset = 0;
                if (load(1, false, false)) {
                    Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                    scannedEntity4.columnNumber++;
                    String strAddSymbol3 = this.fSymbolTable.addSymbol(scannedEntity4.ch, 0, 1);
                    qName.setValues(null, strAddSymbol3, strAddSymbol3, null);
                    checkEntityLimit(nameType, this.fCurrentEntity, 0, 1);
                    return true;
                }
            }
            int i3 = -1;
            while (true) {
                Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                char c = scannedEntity5.ch[scannedEntity5.position];
                if (!(c < 127 ? VALID_NAMES[c] : XMLChar.isName(c))) {
                    break;
                }
                if (c != ':') {
                    iCheckBeforeLoad = checkBeforeLoad(this.fCurrentEntity, this.offset, i3);
                    if (iCheckBeforeLoad <= 0) {
                        if (i3 != -1) {
                            i3 -= this.offset;
                        }
                        this.offset = 0;
                        if (load(iCheckBeforeLoad, false, false)) {
                            break;
                        }
                    }
                } else {
                    if (i3 != -1) {
                        break;
                    }
                    Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
                    int i4 = scannedEntity6.position;
                    XMLSecurityManager.Limit limit = XMLSecurityManager.Limit.MAX_NAME_LIMIT;
                    int i5 = this.offset;
                    checkLimit(limit, scannedEntity6, i5, i4 - i5);
                    i3 = i4;
                    iCheckBeforeLoad = checkBeforeLoad(this.fCurrentEntity, this.offset, i3);
                    if (iCheckBeforeLoad <= 0) {
                        if (i3 != -1) {
                            i3 -= this.offset;
                        }
                        this.offset = 0;
                        if (load(iCheckBeforeLoad, false, false)) {
                            break;
                            break;
                        }
                    }
                }
            }
            Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
            int i6 = scannedEntity7.position;
            int i7 = this.offset;
            int i8 = i6 - i7;
            scannedEntity7.columnNumber += i8;
            if (i8 > 0) {
                String strAddSymbol4 = this.fSymbolTable.addSymbol(scannedEntity7.ch, i7, i8);
                if (i3 != -1) {
                    int i9 = this.offset;
                    int i10 = i3 - i9;
                    XMLSecurityManager.Limit limit2 = XMLSecurityManager.Limit.MAX_NAME_LIMIT;
                    checkLimit(limit2, this.fCurrentEntity, i9, i10);
                    strAddSymbol2 = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, this.offset, i10);
                    int i11 = (i8 - i10) - 1;
                    int i12 = i3 + 1;
                    if (!XMLChar.isNCNameStart(this.fCurrentEntity.ch[i12])) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IllegalQName", new Object[]{strAddSymbol4}, (short) 2);
                    }
                    checkLimit(limit2, this.fCurrentEntity, i12, i11);
                    strAddSymbol = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, i12, i11);
                } else {
                    checkLimit(XMLSecurityManager.Limit.MAX_NAME_LIMIT, this.fCurrentEntity, this.offset, i8);
                    strAddSymbol = strAddSymbol4;
                    strAddSymbol2 = null;
                }
                qName.setValues(strAddSymbol2, strAddSymbol, strAddSymbol4, null);
                checkEntityLimit(nameType, this.fCurrentEntity, this.offset, i8);
                return true;
            }
        }
        return false;
    }

    public void setBaseSystemId(String str) {
    }

    public final void setBufferSize(int i) {
        this.fBufferSize = i;
    }

    public void setColumnNumber(int i) {
    }

    public final void setCurrentEntity(Entity.ScannedEntity scannedEntity) {
        this.fCurrentEntity = scannedEntity;
        if (scannedEntity != null) {
            this.isExternal = scannedEntity.isExternal();
        }
    }

    public final void setEncoding(String str) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.stream != null) {
            String str2 = scannedEntity.encoding;
            if (str2 == null || !str2.equals(str)) {
                String str3 = this.fCurrentEntity.encoding;
                if (str3 != null && str3.startsWith(XMLEntityManager.EncodingInfo.STR_UTF16)) {
                    String upperCase = str.toUpperCase(Locale.ENGLISH);
                    if (upperCase.equals(XMLEntityManager.EncodingInfo.STR_UTF16)) {
                        return;
                    }
                    if (upperCase.equals(XMLEntityManager.EncodingInfo.STR_UCS4)) {
                        boolean zEquals = this.fCurrentEntity.encoding.equals(XMLEntityManager.EncodingInfo.STR_UTF16BE);
                        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
                        if (zEquals) {
                            scannedEntity2.reader = new UCSReader(this.fCurrentEntity.stream, (short) 8);
                            return;
                        } else {
                            scannedEntity2.reader = new UCSReader(this.fCurrentEntity.stream, (short) 4);
                            return;
                        }
                    }
                    if (upperCase.equals(XMLEntityManager.EncodingInfo.STR_UCS2)) {
                        boolean zEquals2 = this.fCurrentEntity.encoding.equals(XMLEntityManager.EncodingInfo.STR_UTF16BE);
                        Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                        if (zEquals2) {
                            scannedEntity3.reader = new UCSReader(this.fCurrentEntity.stream, (short) 2);
                            return;
                        } else {
                            scannedEntity3.reader = new UCSReader(this.fCurrentEntity.stream, (short) 1);
                            return;
                        }
                    }
                }
                Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                scannedEntity4.reader = createReader(scannedEntity4.stream, str, null);
                this.fCurrentEntity.encoding = str;
            }
        }
    }

    public void setExpandedSystemId(String str) {
    }

    public void setLineNumber(int i) {
    }

    public void setLiteralSystemId(String str) {
    }

    public void setPublicId(String str) {
    }

    public void setVersion(String str) {
        this.fCurrentEntity.version = str;
    }

    public final void setXMLVersion(String str) {
        this.xmlVersionSetExplicitly = true;
        this.fCurrentEntity.xmlVersion = str;
    }

    public boolean skipChar(int i, XMLScanner.NameType nameType) throws IOException {
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        int i2 = scannedEntity2.position;
        this.offset = i2;
        if (scannedEntity2.ch[i2] != i) {
            return false;
        }
        int i3 = i2 + 1;
        scannedEntity2.position = i3;
        if (i == 10) {
            scannedEntity2.lineNumber++;
            scannedEntity2.columnNumber = 1;
        } else {
            scannedEntity2.columnNumber++;
        }
        checkEntityLimit(nameType, scannedEntity2, i2, i3 - i2);
        return true;
    }

    public final boolean skipDeclSpaces() throws IOException {
        boolean zLoad;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, false);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        char c = scannedEntity2.ch[scannedEntity2.position];
        if (!XMLChar.isSpace(c)) {
            return false;
        }
        boolean zIsExternal = this.fCurrentEntity.isExternal();
        do {
            if (c == '\n' || (zIsExternal && c == '\r')) {
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                scannedEntity3.lineNumber++;
                scannedEntity3.columnNumber = 1;
                if (scannedEntity3.position == scannedEntity3.count - 1) {
                    scannedEntity3.ch[0] = c;
                    zLoad = load(1, true, false);
                    if (!zLoad) {
                        this.fCurrentEntity.position = 0;
                    }
                } else {
                    zLoad = false;
                }
                if (c == '\r' && zIsExternal) {
                    Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                    char[] cArr = scannedEntity4.ch;
                    int i = scannedEntity4.position;
                    int i2 = i + 1;
                    scannedEntity4.position = i2;
                    if (cArr[i2] != '\n') {
                        scannedEntity4.position = i;
                    }
                }
            } else {
                this.fCurrentEntity.columnNumber++;
                zLoad = false;
            }
            if (!zLoad) {
                this.fCurrentEntity.position++;
            }
            Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
            if (scannedEntity5.position == scannedEntity5.count) {
                load(0, true, false);
            }
            Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
            c = scannedEntity6.ch[scannedEntity6.position];
        } while (XMLChar.isSpace(c));
        return true;
    }

    public boolean skipSpaces() throws IOException {
        boolean zLoad;
        Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
        if (scannedEntity.position == scannedEntity.count) {
            load(0, true, true);
        }
        Entity.ScannedEntity scannedEntity2 = this.fCurrentEntity;
        if (scannedEntity2 == null) {
            return false;
        }
        char[] cArr = scannedEntity2.ch;
        int i = scannedEntity2.position;
        char c = cArr[i];
        this.offset = i - 1;
        if (!XMLChar.isSpace(c)) {
            return false;
        }
        do {
            if (c == '\n' || (this.isExternal && c == '\r')) {
                Entity.ScannedEntity scannedEntity3 = this.fCurrentEntity;
                scannedEntity3.lineNumber++;
                scannedEntity3.columnNumber = 1;
                if (scannedEntity3.position == scannedEntity3.count - 1) {
                    invokeListeners(1);
                    this.fCurrentEntity.ch[0] = c;
                    zLoad = load(1, true, false);
                    Entity.ScannedEntity scannedEntity4 = this.fCurrentEntity;
                    if (!zLoad) {
                        scannedEntity4.position = 0;
                    } else if (scannedEntity4 == null) {
                        return true;
                    }
                } else {
                    zLoad = false;
                }
                if (c == '\r' && this.isExternal) {
                    Entity.ScannedEntity scannedEntity5 = this.fCurrentEntity;
                    char[] cArr2 = scannedEntity5.ch;
                    int i2 = scannedEntity5.position;
                    int i3 = i2 + 1;
                    scannedEntity5.position = i3;
                    if (cArr2[i3] != '\n') {
                        scannedEntity5.position = i2;
                    }
                }
            } else {
                this.fCurrentEntity.columnNumber++;
                zLoad = false;
            }
            Entity.ScannedEntity scannedEntity6 = this.fCurrentEntity;
            int i4 = this.offset;
            checkEntityLimit(null, scannedEntity6, i4, scannedEntity6.position - i4);
            Entity.ScannedEntity scannedEntity7 = this.fCurrentEntity;
            int i5 = scannedEntity7.position;
            this.offset = i5;
            if (!zLoad) {
                scannedEntity7.position = i5 + 1;
            }
            if (scannedEntity7.position == scannedEntity7.count) {
                load(0, true, true);
                if (this.fCurrentEntity == null) {
                    return true;
                }
            }
            Entity.ScannedEntity scannedEntity8 = this.fCurrentEntity;
            c = scannedEntity8.ch[scannedEntity8.position];
        } while (XMLChar.isSpace(c));
        return true;
    }

    public boolean skipString(String str) throws IOException {
        int length = str.length();
        if (arrangeCapacity(length, false)) {
            int i = this.fCurrentEntity.position;
            int i2 = (i + length) - 1;
            int i3 = length - 1;
            while (true) {
                int i4 = i3 - 1;
                char cCharAt = str.charAt(i3);
                Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
                if (cCharAt != scannedEntity.ch[i2]) {
                    break;
                }
                int i5 = i2 - 1;
                if (i2 == i) {
                    scannedEntity.position += length;
                    scannedEntity.columnNumber += length;
                    if (!this.detectingVersion) {
                        checkEntityLimit(null, scannedEntity, i, length);
                    }
                    return true;
                }
                i2 = i5;
                i3 = i4;
            }
        }
        return false;
    }

    public void storeWhiteSpace(int i) {
        int i2 = this.whiteSpaceLen;
        int[] iArr = this.whiteSpaceLookup;
        if (i2 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 100];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.whiteSpaceLookup = iArr2;
        }
        int[] iArr3 = this.whiteSpaceLookup;
        int i3 = this.whiteSpaceLen;
        this.whiteSpaceLen = i3 + 1;
        iArr3[i3] = i;
    }

    public void reset(PropertyManager propertyManager) {
        this.fSymbolTable = (SymbolTable) propertyManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) propertyManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        resetCommon();
    }

    public final void reset(SymbolTable symbolTable, XMLEntityManager xMLEntityManager, XMLErrorReporter xMLErrorReporter) {
        this.fCurrentEntity = null;
        this.fSymbolTable = symbolTable;
        this.fEntityManager = xMLEntityManager;
        this.fErrorReporter = xMLErrorReporter;
        this.fLimitAnalyzer = xMLEntityManager.fLimitAnalyzer;
        this.fSecurityManager = xMLEntityManager.fSecurityManager;
    }

    public XMLEntityScanner() {
        this.fCurrentEntity = null;
        this.fBufferSize = 8192;
        this.fSecurityManager = null;
        this.fLimitAnalyzer = null;
        this.listeners = new ArrayList<>();
        this.fSymbolTable = null;
        this.fErrorReporter = null;
        this.whiteSpaceLookup = new int[100];
        this.whiteSpaceLen = 0;
        this.whiteSpaceInfoNeeded = true;
        this.fPropertyManager = null;
        this.isExternal = false;
        this.xmlVersionSetExplicitly = false;
        this.detectingVersion = false;
        this.offset = 0;
        this.newlines = 0;
        this.counted = false;
    }

    public boolean skipString(char[] cArr) throws IOException {
        int length = cArr.length;
        if (!arrangeCapacity(length, false)) {
            return false;
        }
        int i = this.fCurrentEntity.position;
        int i2 = 0;
        while (true) {
            Entity.ScannedEntity scannedEntity = this.fCurrentEntity;
            if (i2 < length) {
                int i3 = i + 1;
                if (scannedEntity.ch[i] != cArr[i2]) {
                    return false;
                }
                i2++;
                i = i3;
            } else {
                scannedEntity.position += length;
                scannedEntity.columnNumber += length;
                if (this.detectingVersion) {
                    return true;
                }
                checkEntityLimit(null, scannedEntity, i, length);
                return true;
            }
        }
    }

    public boolean arrangeCapacity(int i) throws IOException {
        return arrangeCapacity(i, false);
    }
}
