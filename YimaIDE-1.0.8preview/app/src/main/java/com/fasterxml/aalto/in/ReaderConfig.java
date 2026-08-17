package com.fasterxml.aalto.in;

import com.fasterxml.aalto.impl.CommonConfig;
import com.fasterxml.aalto.util.BufferRecycler;
import com.fasterxml.aalto.util.IllegalCharHandler;
import com.fasterxml.aalto.util.UriCanonicalizer;
import com.fasterxml.aalto.util.XmlCharTypes;
import com.sun.jna.platform.linux.Fcntl;
import defpackage.f63;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLResolver;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamProperties;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ReaderConfig extends CommonConfig {
    static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;
    private static final HashMap<String, Object> sProperties;
    protected BufferRecycler _currRecycler;
    private IllegalCharHandler illegalCharHandler;
    private String mActualEncoding;
    private final UriCanonicalizer mCanonicalizer;
    private final EncodingContext mEncCtxt;
    private final String mExtEncoding;
    private final String mPublicId;
    private XMLReporter mReporter;
    private XMLResolver mResolver;
    private final String mSystemId;
    private String mXmlDeclEncoding;
    private int mXmlDeclStandalone;
    private String mXmlDeclVersion;

    public static final class EncodingContext {
        ByteBasedPNameTable mAsciiTable;
        CharBasedPNameTable mGeneralTable;
        ByteBasedPNameTable mLatin1Table;
        ByteBasedPNameTable mUtf8Table;

        public synchronized ByteBasedPNameTable getAsciiSymbols() {
            try {
                if (this.mAsciiTable == null) {
                    this.mAsciiTable = new ByteBasedPNameTable(64);
                }
            } catch (Throwable th) {
                throw th;
            }
            return new ByteBasedPNameTable(this.mAsciiTable);
        }

        public synchronized ByteBasedPNameTable getLatin1Symbols() {
            try {
                if (this.mLatin1Table == null) {
                    this.mLatin1Table = new ByteBasedPNameTable(64);
                }
            } catch (Throwable th) {
                throw th;
            }
            return new ByteBasedPNameTable(this.mLatin1Table);
        }

        public synchronized CharBasedPNameTable getSymbols() {
            try {
                if (this.mGeneralTable == null) {
                    this.mGeneralTable = new CharBasedPNameTable(64);
                }
            } catch (Throwable th) {
                throw th;
            }
            return new CharBasedPNameTable(this.mGeneralTable);
        }

        public synchronized ByteBasedPNameTable getUtf8Symbols() {
            try {
                if (this.mUtf8Table == null) {
                    this.mUtf8Table = new ByteBasedPNameTable(64);
                }
            } catch (Throwable th) {
                throw th;
            }
            return new ByteBasedPNameTable(this.mUtf8Table);
        }

        public synchronized void updateAsciiSymbols(ByteBasedPNameTable byteBasedPNameTable) {
            this.mAsciiTable.mergeFromChild(byteBasedPNameTable);
        }

        public synchronized void updateLatin1Symbols(ByteBasedPNameTable byteBasedPNameTable) {
            this.mLatin1Table.mergeFromChild(byteBasedPNameTable);
        }

        public synchronized void updateSymbols(CharBasedPNameTable charBasedPNameTable) {
            this.mGeneralTable.mergeFromChild(charBasedPNameTable);
        }

        public synchronized void updateUtf8Symbols(ByteBasedPNameTable byteBasedPNameTable) {
            this.mUtf8Table.mergeFromChild(byteBasedPNameTable);
        }
    }

    static {
        HashMap<String, Object> map = new HashMap<>();
        sProperties = map;
        Boolean bool = Boolean.TRUE;
        map.put(XMLStreamProperties.XSP_NAMESPACE_AWARE, bool);
        map.put("javax.xml.stream.isValidating", 8);
        map.put("javax.xml.stream.isCoalescing", 2);
        map.put("javax.xml.stream.isReplacingEntityReferences", 16);
        Boolean bool2 = Boolean.FALSE;
        map.put("javax.xml.stream.isSupportingExternalEntities", bool2);
        map.put("javax.xml.stream.supportDTD", 4);
        map.put(XMLStreamProperties.XSP_PROBLEM_REPORTER, null);
        map.put("javax.xml.stream.resolver", null);
        map.put("javax.xml.stream.allocator", null);
        map.put(XMLInputFactory2.P_LAZY_PARSING, Integer.valueOf(Fcntl.S_IRUSR));
        map.put(XMLInputFactory2.P_INTERN_NAMES, 512);
        map.put(XMLInputFactory2.P_INTERN_NS_URIS, Integer.valueOf(Fcntl.S_ISGID));
        map.put(XMLInputFactory2.P_AUTO_CLOSE_INPUT, 8192);
        map.put(XMLInputFactory2.P_PRESERVE_LOCATION, 4096);
        map.put(XMLInputFactory2.P_REPORT_PROLOG_WHITESPACE, bool2);
        map.put(XMLInputFactory2.P_REPORT_CDATA, Integer.valueOf(Fcntl.S_ISUID));
        map.put(XMLInputFactory2.P_PRESERVE_LOCATION, bool);
        map.put(XMLInputFactory2.P_DTD_OVERRIDE, null);
        map.put("com.fasterxml.aalto.retainAttributeGeneralEntities", 16384);
        _recyclerRef = new ThreadLocal<>();
    }

    private ReaderConfig(String str, String str2, String str3, EncodingContext encodingContext, int i, int i2, XMLReporter xMLReporter, XMLResolver xMLResolver, UriCanonicalizer uriCanonicalizer) {
        super(i, i2);
        this.mActualEncoding = null;
        this.mXmlDeclVersion = null;
        this.mXmlDeclEncoding = null;
        this.mXmlDeclStandalone = 0;
        this._currRecycler = null;
        this.mPublicId = str;
        this.mSystemId = str2;
        this.mExtEncoding = str3;
        SoftReference<BufferRecycler> softReference = _recyclerRef.get();
        if (softReference != null) {
            this._currRecycler = softReference.get();
        }
        this.mEncCtxt = encodingContext;
        this._flags = i;
        this._flagMods = i2;
        this.mReporter = xMLReporter;
        this.mResolver = xMLResolver;
        this.mCanonicalizer = uriCanonicalizer;
    }

    private BufferRecycler createRecycler() {
        BufferRecycler bufferRecycler = new BufferRecycler();
        _recyclerRef.set(new SoftReference<>(bufferRecycler));
        return bufferRecycler;
    }

    public byte[] allocFullBBuffer(int i) {
        byte[] fullBBuffer;
        BufferRecycler bufferRecycler = this._currRecycler;
        return (bufferRecycler == null || (fullBBuffer = bufferRecycler.getFullBBuffer(i)) == null) ? new byte[i] : fullBBuffer;
    }

    public char[] allocFullCBuffer(int i) {
        char[] fullCBuffer;
        BufferRecycler bufferRecycler = this._currRecycler;
        return (bufferRecycler == null || (fullCBuffer = bufferRecycler.getFullCBuffer(i)) == null) ? new char[i] : fullCBuffer;
    }

    public char[] allocMediumCBuffer(int i) {
        char[] mediumCBuffer;
        BufferRecycler bufferRecycler = this._currRecycler;
        return (bufferRecycler == null || (mediumCBuffer = bufferRecycler.getMediumCBuffer(i)) == null) ? new char[i] : mediumCBuffer;
    }

    public char[] allocSmallCBuffer(int i) {
        char[] smallCBuffer;
        BufferRecycler bufferRecycler = this._currRecycler;
        return (bufferRecycler == null || (smallCBuffer = bufferRecycler.getSmallCBuffer(i)) == null) ? new char[i] : smallCBuffer;
    }

    public String canonicalizeURI(char[] cArr, int i) {
        return this.mCanonicalizer.canonicalizeURI(cArr, i);
    }

    public ReaderConfig createNonShared(String str, String str2, String str3) {
        return new ReaderConfig(str, str2, str3, this.mEncCtxt, this._flags, this._flagMods, this.mReporter, this.mResolver, this.mCanonicalizer);
    }

    public void doAutoCloseInput(boolean z) {
        setFlag(8192, z);
    }

    public void doCoalesceText(boolean z) {
        setFlag(2, z);
    }

    public void doParseLazily(boolean z) {
        setFlag(Fcntl.S_IRUSR, z);
    }

    public void doPreserveLocation(boolean z) {
        setFlag(4096, z);
    }

    public void freeFullBBuffer(byte[] bArr) {
        if (this._currRecycler == null) {
            this._currRecycler = createRecycler();
        }
        this._currRecycler.returnFullBBuffer(bArr);
    }

    public void freeFullCBuffer(char[] cArr) {
        if (this._currRecycler == null) {
            this._currRecycler = createRecycler();
        }
        this._currRecycler.returnFullCBuffer(cArr);
    }

    public void freeMediumCBuffer(char[] cArr) {
        if (this._currRecycler == null) {
            this._currRecycler = createRecycler();
        }
        this._currRecycler.returnMediumCBuffer(cArr);
    }

    public void freeSmallCBuffer(char[] cArr) {
        if (this._currRecycler == null) {
            this._currRecycler = createRecycler();
        }
        this._currRecycler.returnSmallCBuffer(cArr);
    }

    public String getActualEncoding() {
        return this.mActualEncoding;
    }

    public ByteBasedPNameTable getBBSymbols() {
        String str = this.mActualEncoding;
        if (str == "UTF-8") {
            return this.mEncCtxt.getUtf8Symbols();
        }
        if (str == "ISO-8859-1") {
            return this.mEncCtxt.getLatin1Symbols();
        }
        if (str == "US-ASCII") {
            return this.mEncCtxt.getAsciiSymbols();
        }
        throw new Error("Internal error, unknown encoding '" + this.mActualEncoding + "'");
    }

    public CharBasedPNameTable getCBSymbols() {
        return this.mEncCtxt.getSymbols();
    }

    public XmlCharTypes getCharTypes() {
        String str = this.mActualEncoding;
        if (str == "UTF-8") {
            return InputCharTypes.getUtf8CharTypes();
        }
        if (str == "ISO-8859-1") {
            return InputCharTypes.getLatin1CharTypes();
        }
        if (str == "US-ASCII") {
            return InputCharTypes.getAsciiCharTypes();
        }
        throw new Error("Internal error, unknown encoding '" + this.mActualEncoding + "'");
    }

    public String getExternalEncoding() {
        return this.mExtEncoding;
    }

    public IllegalCharHandler getIllegalCharHandler() {
        return this.illegalCharHandler;
    }

    @Override // com.fasterxml.aalto.impl.CommonConfig
    public final Object getProperty(String str, boolean z) {
        HashMap<String, Object> map = sProperties;
        Object obj = map.get(str);
        if (obj == null) {
            if (map.containsKey(str)) {
                return null;
            }
            return super.getProperty(str, z);
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Integer) {
            return Boolean.valueOf(hasFlag(((Integer) obj).intValue()));
        }
        f63.a("Internal error: unrecognized property value type: ".concat(obj.getClass().getName()));
        return null;
    }

    public String getPublicId() {
        return this.mPublicId;
    }

    public String getSystemId() {
        return this.mSystemId;
    }

    public XMLReporter getXMLReporter() {
        return this.mReporter;
    }

    public String getXmlDeclEncoding() {
        return this.mXmlDeclEncoding;
    }

    public int getXmlDeclStandalone() {
        return this.mXmlDeclStandalone;
    }

    public String getXmlDeclVersion() {
        return this.mXmlDeclVersion;
    }

    public boolean isXml11() {
        return false;
    }

    public void setActualEncoding(String str) {
        this.mActualEncoding = str;
    }

    @Override // com.fasterxml.aalto.impl.CommonConfig
    public boolean setProperty(String str, Object obj) {
        HashMap<String, Object> map = sProperties;
        Object obj2 = map.get(str);
        if (obj2 == null) {
            if (map.containsKey(str)) {
                return false;
            }
            return super.setProperty(str, obj);
        }
        if (obj2 instanceof Boolean) {
            return false;
        }
        if (obj2 instanceof Integer) {
            setFlag(((Integer) obj2).intValue(), ((Boolean) obj).booleanValue());
            return true;
        }
        f63.a("Internal error");
        return false;
    }

    public void setXmlDeclInfo(int i, String str, String str2) {
        if (i == 256) {
            this.mXmlDeclVersion = "1.0";
        } else if (i == 272) {
            this.mXmlDeclVersion = "1.1";
        } else {
            this.mXmlDeclVersion = null;
        }
        this.mXmlDeclEncoding = str;
        if (str2 == "yes") {
            this.mXmlDeclStandalone = 1;
        } else if (str2 == "no") {
            this.mXmlDeclStandalone = 2;
        } else {
            this.mXmlDeclStandalone = 0;
        }
    }

    public final void setXmlEncoding(String str) {
        this.mXmlDeclEncoding = str;
    }

    public void updateBBSymbols(ByteBasedPNameTable byteBasedPNameTable) {
        String str = this.mActualEncoding;
        if (str == "UTF-8") {
            this.mEncCtxt.updateUtf8Symbols(byteBasedPNameTable);
            return;
        }
        if (str == "ISO-8859-1") {
            this.mEncCtxt.updateLatin1Symbols(byteBasedPNameTable);
            return;
        }
        if (str == "US-ASCII") {
            this.mEncCtxt.updateAsciiSymbols(byteBasedPNameTable);
            return;
        }
        throw new Error("Internal error, unknown encoding '" + this.mActualEncoding + "'");
    }

    public void updateCBSymbols(CharBasedPNameTable charBasedPNameTable) {
        this.mEncCtxt.updateSymbols(charBasedPNameTable);
    }

    public boolean willAutoCloseInput() {
        return hasFlag(8192);
    }

    public boolean willCoalesceText() {
        return hasFlag(2);
    }

    public boolean willExpandEntities() {
        return hasFlag(16);
    }

    public boolean willParseLazily() {
        return hasFlag(Fcntl.S_IRUSR);
    }

    public boolean willReportCData() {
        return hasFlag(Fcntl.S_ISUID);
    }

    public boolean willRetainAttributeGeneralEntities() {
        return hasFlag(16384);
    }

    public ReaderConfig() {
        this(null, null, null, new EncodingContext(), 7957, 0, null, null, new UriCanonicalizer());
    }
}
