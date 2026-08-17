package com.sun.org.apache.xml.internal.serializer;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import defpackage.x73;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Properties;
import javax.xml.transform.ErrorListener;
import jdk.xml.internal.JdkXmlUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ToHTMLStream extends ToStream {
    private static final ElemDesc m_dummy;
    static final Trie m_elementFlags;
    private static final CharInfo m_htmlcharInfo = CharInfo.getCharInfoInternal(CharInfo.HTML_ENTITIES_RESOURCE, "html");
    private Trie m_htmlInfo;
    protected boolean m_inDTD;
    private boolean m_isprevblock;
    private boolean m_omitMetaTag;
    private boolean m_specialEscapeURLs;

    static {
        Trie trie = new Trie();
        m_elementFlags = trie;
        initTagReference(trie);
        m_dummy = new ElemDesc(8);
    }

    public ToHTMLStream(ErrorListener errorListener) {
        super(errorListener);
        this.m_inDTD = false;
        this.m_isprevblock = false;
        this.m_specialEscapeURLs = true;
        this.m_omitMetaTag = false;
        this.m_htmlInfo = new Trie(m_elementFlags);
        this.m_charInfo = m_htmlcharInfo;
        this.m_prefixMap = new NamespaceMappings();
    }

    public static final ElemDesc getElemDesc(String str) {
        Object obj = m_elementFlags.get(str);
        return obj != null ? (ElemDesc) obj : m_dummy;
    }

    private ElemDesc getElemDesc2(String str) {
        Object obj = this.m_htmlInfo.get2(str);
        return obj != null ? (ElemDesc) obj : m_dummy;
    }

    private final boolean getOmitMetaTag() {
        return this.m_omitMetaTag;
    }

    private final boolean getSpecialEscapeURLs() {
        return this.m_specialEscapeURLs;
    }

    public static void initTagReference(Trie trie) {
        trie.put("BASEFONT", new ElemDesc(2));
        trie.put("FRAME", new ElemDesc(10));
        trie.put("FRAMESET", new ElemDesc(8));
        trie.put("NOFRAMES", new ElemDesc(8));
        trie.put("ISINDEX", new ElemDesc(10));
        trie.put("APPLET", new ElemDesc(2097152));
        trie.put("CENTER", new ElemDesc(8));
        trie.put("DIR", new ElemDesc(8));
        trie.put("MENU", new ElemDesc(8));
        trie.put("TT", new ElemDesc(4096));
        trie.put("I", new ElemDesc(4096));
        trie.put("B", new ElemDesc(4096));
        trie.put("BIG", new ElemDesc(4096));
        trie.put("SMALL", new ElemDesc(4096));
        trie.put("EM", new ElemDesc(8192));
        trie.put("STRONG", new ElemDesc(8192));
        trie.put("DFN", new ElemDesc(8192));
        trie.put("CODE", new ElemDesc(8192));
        trie.put("SAMP", new ElemDesc(8192));
        trie.put("KBD", new ElemDesc(8192));
        trie.put("VAR", new ElemDesc(8192));
        trie.put("CITE", new ElemDesc(8192));
        trie.put("ABBR", new ElemDesc(8192));
        trie.put("ACRONYM", new ElemDesc(8192));
        trie.put("SUP", new ElemDesc(98304));
        trie.put("SUB", new ElemDesc(98304));
        trie.put("SPAN", new ElemDesc(98304));
        trie.put("BDO", new ElemDesc(98304));
        trie.put("BR", new ElemDesc(98314));
        trie.put("BODY", new ElemDesc(8));
        trie.put("ADDRESS", new ElemDesc(56));
        trie.put("DIV", new ElemDesc(56));
        trie.put("A", new ElemDesc(32768));
        trie.put("MAP", new ElemDesc(98312));
        trie.put("AREA", new ElemDesc(10));
        trie.put("LINK", new ElemDesc(131082));
        trie.put("IMG", new ElemDesc(2195458));
        trie.put("OBJECT", new ElemDesc(2326528));
        trie.put("PARAM", new ElemDesc(2));
        trie.put("HR", new ElemDesc(58));
        trie.put("P", new ElemDesc(56));
        trie.put("H1", new ElemDesc(262152));
        trie.put("H2", new ElemDesc(262152));
        trie.put("H3", new ElemDesc(262152));
        trie.put("H4", new ElemDesc(262152));
        trie.put("H5", new ElemDesc(262152));
        trie.put("H6", new ElemDesc(262152));
        trie.put("PRE", new ElemDesc(1048584));
        trie.put("Q", new ElemDesc(98304));
        trie.put("BLOCKQUOTE", new ElemDesc(56));
        trie.put("INS", new ElemDesc(0));
        trie.put("DEL", new ElemDesc(0));
        trie.put("DL", new ElemDesc(56));
        trie.put("DT", new ElemDesc(8));
        trie.put("DD", new ElemDesc(8));
        trie.put("OL", new ElemDesc(524296));
        trie.put("UL", new ElemDesc(524296));
        trie.put("LI", new ElemDesc(8));
        trie.put("FORM", new ElemDesc(8));
        trie.put("LABEL", new ElemDesc(16384));
        trie.put("INPUT", new ElemDesc(18434));
        trie.put("SELECT", new ElemDesc(18432));
        trie.put("OPTGROUP", new ElemDesc(0));
        trie.put("OPTION", new ElemDesc(0));
        trie.put("TEXTAREA", new ElemDesc(18432));
        trie.put("FIELDSET", new ElemDesc(24));
        trie.put("LEGEND", new ElemDesc(0));
        trie.put("BUTTON", new ElemDesc(18432));
        trie.put("TABLE", new ElemDesc(56));
        trie.put("CAPTION", new ElemDesc(8));
        trie.put("THEAD", new ElemDesc(8));
        trie.put("TFOOT", new ElemDesc(8));
        trie.put("TBODY", new ElemDesc(8));
        trie.put("COLGROUP", new ElemDesc(8));
        trie.put("COL", new ElemDesc(10));
        trie.put("TR", new ElemDesc(8));
        trie.put("TH", new ElemDesc(0));
        trie.put("TD", new ElemDesc(0));
        trie.put("HEAD", new ElemDesc(4194312));
        trie.put("TITLE", new ElemDesc(8));
        trie.put("BASE", new ElemDesc(10));
        trie.put("META", new ElemDesc(131082));
        trie.put("STYLE", new ElemDesc(131336));
        trie.put("SCRIPT", new ElemDesc(229632));
        trie.put("NOSCRIPT", new ElemDesc(56));
        trie.put("HTML", new ElemDesc(8));
        trie.put("FONT", new ElemDesc(4096));
        trie.put("S", new ElemDesc(4096));
        trie.put("STRIKE", new ElemDesc(4096));
        trie.put("U", new ElemDesc(4096));
        trie.put("NOBR", new ElemDesc(4096));
        trie.put("IFRAME", new ElemDesc(56));
        trie.put("LAYER", new ElemDesc(56));
        trie.put("ILAYER", new ElemDesc(56));
        ElemDesc elemDesc = (ElemDesc) trie.get("a");
        elemDesc.setAttr("HREF", 2);
        elemDesc.setAttr("NAME", 2);
        ElemDesc elemDesc2 = (ElemDesc) trie.get("area");
        elemDesc2.setAttr("HREF", 2);
        elemDesc2.setAttr("NOHREF", 4);
        ((ElemDesc) trie.get("base")).setAttr("HREF", 2);
        ((ElemDesc) trie.get("button")).setAttr("DISABLED", 4);
        ((ElemDesc) trie.get("blockquote")).setAttr("CITE", 2);
        ((ElemDesc) trie.get("del")).setAttr("CITE", 2);
        ((ElemDesc) trie.get("dir")).setAttr("COMPACT", 4);
        ElemDesc elemDesc3 = (ElemDesc) trie.get("div");
        elemDesc3.setAttr("SRC", 2);
        elemDesc3.setAttr("NOWRAP", 4);
        ((ElemDesc) trie.get("dl")).setAttr("COMPACT", 4);
        ((ElemDesc) trie.get("form")).setAttr("ACTION", 2);
        ElemDesc elemDesc4 = (ElemDesc) trie.get("frame");
        elemDesc4.setAttr("SRC", 2);
        elemDesc4.setAttr("LONGDESC", 2);
        elemDesc4.setAttr("NORESIZE", 4);
        ((ElemDesc) trie.get("head")).setAttr("PROFILE", 2);
        ((ElemDesc) trie.get("hr")).setAttr("NOSHADE", 4);
        ElemDesc elemDesc5 = (ElemDesc) trie.get("iframe");
        elemDesc5.setAttr("SRC", 2);
        elemDesc5.setAttr("LONGDESC", 2);
        ((ElemDesc) trie.get("ilayer")).setAttr("SRC", 2);
        ElemDesc elemDesc6 = (ElemDesc) trie.get("img");
        elemDesc6.setAttr("SRC", 2);
        elemDesc6.setAttr("LONGDESC", 2);
        elemDesc6.setAttr("USEMAP", 2);
        elemDesc6.setAttr("ISMAP", 4);
        ElemDesc elemDesc7 = (ElemDesc) trie.get("input");
        elemDesc7.setAttr("SRC", 2);
        elemDesc7.setAttr("USEMAP", 2);
        elemDesc7.setAttr("CHECKED", 4);
        elemDesc7.setAttr("DISABLED", 4);
        elemDesc7.setAttr("ISMAP", 4);
        elemDesc7.setAttr("READONLY", 4);
        ((ElemDesc) trie.get("ins")).setAttr("CITE", 2);
        ((ElemDesc) trie.get("layer")).setAttr("SRC", 2);
        ((ElemDesc) trie.get("link")).setAttr("HREF", 2);
        ((ElemDesc) trie.get("menu")).setAttr("COMPACT", 4);
        ElemDesc elemDesc8 = (ElemDesc) trie.get("object");
        elemDesc8.setAttr("CLASSID", 2);
        elemDesc8.setAttr("CODEBASE", 2);
        elemDesc8.setAttr("DATA", 2);
        elemDesc8.setAttr("ARCHIVE", 2);
        elemDesc8.setAttr("USEMAP", 2);
        elemDesc8.setAttr("DECLARE", 4);
        ((ElemDesc) trie.get("ol")).setAttr("COMPACT", 4);
        ((ElemDesc) trie.get("optgroup")).setAttr("DISABLED", 4);
        ElemDesc elemDesc9 = (ElemDesc) trie.get("option");
        elemDesc9.setAttr("SELECTED", 4);
        elemDesc9.setAttr("DISABLED", 4);
        ((ElemDesc) trie.get("q")).setAttr("CITE", 2);
        ElemDesc elemDesc10 = (ElemDesc) trie.get(Constants.ELEMNAME_SCRIPT_STRING);
        elemDesc10.setAttr("SRC", 2);
        elemDesc10.setAttr("FOR", 2);
        elemDesc10.setAttr("DEFER", 4);
        ElemDesc elemDesc11 = (ElemDesc) trie.get(Constants.ATTRNAME_SELECT);
        elemDesc11.setAttr("DISABLED", 4);
        elemDesc11.setAttr("MULTIPLE", 4);
        ((ElemDesc) trie.get("table")).setAttr("NOWRAP", 4);
        ((ElemDesc) trie.get("td")).setAttr("NOWRAP", 4);
        ElemDesc elemDesc12 = (ElemDesc) trie.get("textarea");
        elemDesc12.setAttr("DISABLED", 4);
        elemDesc12.setAttr("READONLY", 4);
        ((ElemDesc) trie.get("th")).setAttr("NOWRAP", 4);
        ((ElemDesc) trie.get("tr")).setAttr("NOWRAP", 4);
        ((ElemDesc) trie.get("ul")).setAttr("COMPACT", 4);
    }

    private void initToHTMLStream() {
        this.m_isprevblock = false;
        this.m_inDTD = false;
        this.m_omitMetaTag = false;
        this.m_specialEscapeURLs = true;
    }

    private boolean isASCIIDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isHHSign(String str) {
        try {
            Integer.parseInt(str, 16);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static String makeHHString(int i) {
        String upperCase = Integer.toHexString(i).toUpperCase();
        return upperCase.length() == 1 ? "0".concat(upperCase) : upperCase;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addUniqueAttribute(String str, String str2, int i) throws SAXException {
        try {
            Writer writer = this.m_writer;
            if ((i & 1) > 0 && m_htmlcharInfo.onlyQuotAmpLtGt) {
                writer.write(32);
                writer.write(str);
                writer.write("=\"");
                writer.write(str2);
                writer.write(34);
                return;
            }
            if ((i & 2) > 0 && (str2.length() == 0 || str2.equalsIgnoreCase(str))) {
                writer.write(32);
                writer.write(str);
                return;
            }
            writer.write(32);
            writer.write(str);
            writer.write("=\"");
            if ((i & 4) > 0) {
                writeAttrURI(writer, str2, this.m_specialEscapeURLs);
            } else {
                writeAttrString(writer, str2, getEncoding());
            }
            writer.write(34);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.DeclHandler
    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public final void cdata(char[] cArr, int i, int i2) throws SAXException {
        String str = this.m_elemContext.m_elementName;
        if (str == null || !(str.equalsIgnoreCase("SCRIPT") || this.m_elemContext.m_elementName.equalsIgnoreCase("STYLE"))) {
            super.cdata(cArr, i, i2);
            return;
        }
        try {
            if (this.m_elemContext.m_startTagOpen) {
                closeStartTag();
                this.m_elemContext.m_startTagOpen = false;
            }
            if (shouldIndent()) {
                indent();
            }
            writeNormalizedChars(cArr, i, i2, true, this.m_lineSepUse);
        } catch (IOException e) {
            throw new SAXException(Utils.messages.createMessage("ER_OIERROR", null), e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public final void characters(char[] cArr, int i, int i2) throws SAXException {
        ElemContext elemContext = this.m_elemContext;
        if (!elemContext.m_isRaw) {
            super.characters(cArr, i, i2);
            return;
        }
        try {
            if (elemContext.m_startTagOpen) {
                closeStartTag();
                this.m_elemContext.m_startTagOpen = false;
            }
            writeNormalizedChars(cArr, i, i2, false, this.m_lineSepUse);
            this.m_isprevtext = true;
            if (this.m_tracer != null) {
                super.fireCharEvent(cArr, i, i2);
            }
        } catch (IOException e) {
            throw new SAXException(Utils.messages.createMessage("ER_OIERROR", null), e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public void closeStartTag() throws SAXException {
        try {
            if (this.m_tracer != null) {
                super.fireStartElem(this.m_elemContext.m_elementName);
            }
            int length = this.m_attributes.getLength();
            if (length > 0) {
                processAttributes(this.m_writer, length);
                this.m_attributes.clear();
            }
            this.m_writer.write(62);
            if (this.m_StringOfCDATASections != null) {
                this.m_elemContext.m_isCdataSection = isCdataSection();
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_inDTD) {
            return;
        }
        super.comment(cArr, i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.DeclHandler
    public void elementDecl(String str, String str2) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
        this.m_inDTD = false;
    }

    @Override // org.xml.sax.ContentHandler
    public final void endDocument() throws SAXException {
        if (this.m_doIndent) {
            flushCharactersBuffer(false);
        }
        flushPending();
        if (this.m_doIndent && !this.m_isprevtext) {
            try {
                outputLineSep();
            } catch (IOException e) {
                x73.a(e);
                return;
            }
        }
        flushWriter();
        if (this.m_tracer != null) {
            super.fireEndDoc();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public final void endElement(String str, String str2, String str3) throws SAXException {
        boolean z;
        if (this.m_doIndent) {
            flushCharactersBuffer(false);
        }
        if (this.m_cdataTagOpen) {
            closeCDATA();
        }
        if (str != null && str.length() > 0) {
            super.endElement(str, str2, str3);
            return;
        }
        try {
            ElemContext elemContext = this.m_elemContext;
            int flags = elemContext.m_elementDesc.getFlags();
            boolean z2 = (flags & 2) != 0;
            if (this.m_doIndent) {
                boolean z3 = (flags & 8) != 0;
                if (z3 || (!this.m_isprevtext && this.m_isprevblock)) {
                    this.m_startNewLine = true;
                    z = true;
                } else {
                    z = false;
                }
                if (!elemContext.m_startTagOpen && z && (this.m_childNodeNum > 1 || !this.m_isprevtext)) {
                    indent(elemContext.m_currentElemDepth - 1);
                }
                this.m_isprevblock = z3;
            }
            Writer writer = this.m_writer;
            if (elemContext.m_startTagOpen) {
                if (this.m_tracer != null) {
                    super.fireStartElem(str3);
                }
                int length = this.m_attributes.getLength();
                if (length > 0) {
                    processAttributes(this.m_writer, length);
                    this.m_attributes.clear();
                }
                if (z2) {
                    writer.write(62);
                } else {
                    writer.write("></");
                    writer.write(str3);
                    writer.write(62);
                }
            } else {
                writer.write("</");
                writer.write(str3);
                writer.write(62);
            }
            if (this.m_doIndent) {
                List<Integer> list = this.m_childNodeNumStack;
                this.m_childNodeNum = list.remove(list.size() - 1).intValue();
                this.m_isprevtext = false;
            }
            if (this.m_tracer != null) {
                super.fireEndElem(str3);
            }
            ElemContext elemContext2 = elemContext.m_prev;
            if (z2) {
                this.m_elemContext = elemContext2;
            } else {
                this.m_elemContext = elemContext2;
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public final void entityReference(String str) throws SAXException {
        try {
            Writer writer = this.m_writer;
            writer.write(38);
            writer.write(str);
            writer.write(59);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String str, String str2) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void namespaceAfterStartElement(String str, String str2) throws SAXException {
        ElemContext elemContext = this.m_elemContext;
        if (elemContext.m_elementURI == null && SerializerBase.getPrefixPart(elemContext.m_elementName) == null && "".equals(str)) {
            this.m_elemContext.m_elementURI = str2;
        }
        startPrefixMapping(str, str2, false);
    }

    public void processAttribute(Writer writer, String str, String str2, ElemDesc elemDesc) throws SAXException, IOException {
        writer.write(32);
        if ((str2.length() == 0 || str2.equalsIgnoreCase(str)) && elemDesc != null && elemDesc.isAttrFlagSet(str, 4)) {
            writer.write(str);
            return;
        }
        writer.write(str);
        writer.write("=\"");
        if (elemDesc == null || !elemDesc.isAttrFlagSet(str, 2)) {
            writeAttrString(writer, str2, getEncoding());
        } else {
            writeAttrURI(writer, str2, this.m_specialEscapeURLs);
        }
        writer.write(34);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public void processAttributes(Writer writer, int i) throws SAXException, IOException {
        for (int i2 = 0; i2 < i; i2++) {
            processAttribute(writer, this.m_attributes.getQName(i2), this.m_attributes.getValue(i2), this.m_elemContext.m_elementDesc);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        if (this.m_doIndent) {
            this.m_childNodeNum++;
            flushCharactersBuffer(false);
        }
        flushPending();
        if (str.equals("javax.xml.transform.disable-output-escaping")) {
            startNonEscaping();
        } else if (str.equals("javax.xml.transform.enable-output-escaping")) {
            endNonEscaping();
        } else {
            try {
                if (this.m_elemContext.m_startTagOpen) {
                    closeStartTag();
                    this.m_elemContext.m_startTagOpen = false;
                } else if (this.m_needToCallStartDocument) {
                    startDocumentInternal();
                }
                if (shouldIndent()) {
                    indent();
                }
                Writer writer = this.m_writer;
                writer.write("<?");
                writer.write(str);
                if (str2.length() > 0 && !Character.isSpaceChar(str2.charAt(0))) {
                    writer.write(32);
                }
                writer.write(str2);
                writer.write(62);
                if (this.m_elemContext.m_currentElemDepth <= 0) {
                    outputLineSep();
                }
                this.m_startNewLine = true;
            } catch (IOException e) {
                x73.a(e);
                return;
            }
        }
        if (this.m_tracer != null) {
            super.fireEscapingEvent(str, str2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        if (!super.reset()) {
            return false;
        }
        initToHTMLStream();
        return true;
    }

    public void setOmitMetaTag(boolean z) {
        this.m_omitMetaTag = z;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputFormat(Properties properties) {
        this.m_specialEscapeURLs = OutputPropertyUtils.getBooleanProperty(OutputPropertiesFactory.S_USE_URL_ESCAPING, properties);
        this.m_omitMetaTag = OutputPropertyUtils.getBooleanProperty(OutputPropertiesFactory.S_OMIT_META_TAG, properties);
        super.setOutputFormat(properties);
    }

    public void setSpecialEscapeURLs(boolean z) {
        this.m_specialEscapeURLs = z;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public boolean shouldFormatOutput() {
        return this.m_doIndent;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public boolean shouldIndentForText() {
        return super.shouldIndentForText() && this.m_isprevblock;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
        this.m_inDTD = true;
        super.startDTD(str, str2, str3);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase
    public void startDocumentInternal() throws SAXException {
        super.startDocumentInternal();
        this.m_needToCallStartDocument = false;
        this.m_needToOutputDocTypeDecl = true;
        this.m_startNewLine = false;
        setOmitXMLDeclaration(true);
        if (true == this.m_needToOutputDocTypeDecl) {
            String doctypeSystem = getDoctypeSystem();
            String doctypePublic = getDoctypePublic();
            if (doctypeSystem != null || doctypePublic != null) {
                Writer writer = this.m_writer;
                try {
                    writer.write("<!DOCTYPE html");
                    writer.write(JdkXmlUtils.getDTDExternalDecl(doctypePublic, doctypeSystem));
                    writer.write(62);
                    outputLineSep();
                } catch (IOException e) {
                    x73.a(e);
                    return;
                }
            }
        }
        this.m_needToOutputDocTypeDecl = false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (this.m_doIndent) {
            this.m_childNodeNum++;
            flushCharactersBuffer(false);
        }
        ElemContext elemContext = this.m_elemContext;
        if (elemContext.m_startTagOpen) {
            closeStartTag();
            elemContext.m_startTagOpen = false;
        } else if (this.m_cdataTagOpen) {
            closeCDATA();
            this.m_cdataTagOpen = false;
        } else if (this.m_needToCallStartDocument) {
            startDocumentInternal();
            this.m_needToCallStartDocument = false;
        }
        if (str != null && str.length() > 0) {
            super.startElement(str, str2, str3, attributes);
            return;
        }
        try {
            ElemDesc elemDesc2 = getElemDesc2(str3);
            int flags = elemDesc2.getFlags();
            if (this.m_doIndent) {
                boolean z = (flags & 8) != 0;
                if (elemContext.m_elementName != null && (z || (!this.m_isprevtext && this.m_isprevblock))) {
                    this.m_startNewLine = true;
                    indent();
                }
                this.m_isprevblock = z;
            }
            if (attributes != null) {
                addAttributes(attributes);
            }
            this.m_isprevtext = false;
            Writer writer = this.m_writer;
            writer.write(60);
            writer.write(str3);
            if (this.m_doIndent) {
                this.m_childNodeNumStack.add(Integer.valueOf(this.m_childNodeNum));
                this.m_childNodeNum = 0;
            }
            if (this.m_tracer != null) {
                firePseudoAttributes();
            }
            if ((flags & 2) != 0) {
                ElemContext elemContextPush = elemContext.push();
                this.m_elemContext = elemContextPush;
                elemContextPush.m_elementName = str3;
                elemContextPush.m_elementDesc = elemDesc2;
                return;
            }
            ElemContext elemContextPush2 = elemContext.push(str, str2, str3);
            this.m_elemContext = elemContextPush2;
            elemContextPush2.m_elementDesc = elemDesc2;
            elemContextPush2.m_isRaw = (flags & 256) != 0;
            if (this.m_doIndent && (flags & 8) != 0) {
                this.m_startNewLine = true;
            }
            if ((4194304 & flags) != 0) {
                closeStartTag();
                elemContextPush2.m_startTagOpen = false;
                if (this.m_omitMetaTag) {
                    return;
                }
                if (this.m_doIndent) {
                    indent();
                }
                writer.write("<META http-equiv=\"Content-Type\" content=\"text/html; charset=");
                writer.write(Encodings.getMimeEncoding(getEncoding()));
                writer.write("\">");
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002f  */
    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public void writeAttrString(Writer writer, String str, String str2) throws SAXException, IOException {
        char[] cArr;
        Writer writer2;
        ToHTMLStream toHTMLStream;
        int i;
        int length = str.length();
        if (length > this.m_attrBuff.length) {
            this.m_attrBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_attrBuff, 0);
        char[] cArr2 = this.m_attrBuff;
        int i2 = 0;
        int i3 = 0;
        char c = 0;
        int i4 = 0;
        while (i4 < length) {
            char c2 = cArr2[i4];
            if (this.escapingNotNeeded(c2) && !this.m_charInfo.isSpecialAttrChar(c2)) {
                i2++;
                cArr = cArr2;
                writer2 = writer;
                toHTMLStream = this;
            } else if ('<' == c2 || '>' == c2) {
                cArr = cArr2;
                writer2 = writer;
                toHTMLStream = this;
                i2++;
            } else if ('&' == c2 && (i = i4 + 1) < length && '{' == cArr2[i]) {
                i2++;
                cArr = cArr2;
                writer2 = writer;
                toHTMLStream = this;
            } else {
                if (i2 > 0) {
                    writer.write(cArr2, i3, i2);
                    i2 = 0;
                }
                ToHTMLStream toHTMLStream2 = this;
                Writer writer3 = writer;
                int iAccumDefaultEntity = toHTMLStream2.accumDefaultEntity(writer3, c2, i4, cArr2, length, false, true);
                toHTMLStream = toHTMLStream2;
                cArr = cArr2;
                writer2 = writer3;
                if (i4 != iAccumDefaultEntity) {
                    i4 = iAccumDefaultEntity - 1;
                } else if (!Encodings.isHighUTF16Surrogate(c2) && !Encodings.isLowUTF16Surrogate(c2)) {
                    String outputStringForChar = toHTMLStream.m_charInfo.getOutputStringForChar(c2);
                    if (outputStringForChar != null) {
                        writer2.write(outputStringForChar);
                    } else if (toHTMLStream.escapingNotNeeded(c2)) {
                        writer2.write(c2);
                    } else {
                        writer2.write("&#");
                        writer2.write(Integer.toString(c2));
                        writer2.write(59);
                    }
                } else if (toHTMLStream.writeUTF16Surrogate(c2, cArr, i4, length) >= 0 && Encodings.isHighUTF16Surrogate(c2)) {
                    i4++;
                }
                i3 = i4 + 1;
            }
            i4++;
            this = toHTMLStream;
            c = c2;
            writer = writer2;
            cArr2 = cArr;
        }
        char[] cArr3 = cArr2;
        Writer writer4 = writer;
        if (i2 <= 1) {
            if (i2 == 1) {
                writer4.write(c);
            }
        } else if (i3 == 0) {
            writer4.write(str);
        } else {
            writer4.write(cArr3, i3, i2);
        }
    }

    public void writeAttrURI(Writer writer, String str, boolean z) throws IOException {
        int length = str.length();
        if (length > this.m_attrBuff.length) {
            this.m_attrBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_attrBuff, 0);
        char[] cArr = this.m_attrBuff;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        char c = 0;
        while (i < length) {
            c = cArr[i];
            if (c < ' ' || c > '~') {
                if (i2 > 0) {
                    writer.write(cArr, i3, i2);
                    i2 = 0;
                }
                if (z) {
                    if (c <= 127) {
                        writer.write(37);
                        writer.write(makeHHString(c));
                    } else if (c <= 2047) {
                        writer.write(37);
                        writer.write(makeHHString((c >> 6) | 192));
                        writer.write(37);
                        writer.write(makeHHString((c & '?') | 128));
                    } else if (Encodings.isHighUTF16Surrogate(c)) {
                        int i4 = ((c & 960) >> 6) + 1;
                        i++;
                        char c2 = cArr[i];
                        int i5 = (i4 >> 2) | 240;
                        writer.write(37);
                        writer.write(makeHHString(i5));
                        writer.write(37);
                        writer.write(makeHHString((((i4 & 3) << 4) & 48) | 128 | ((c & '<') >> 2)));
                        writer.write(37);
                        writer.write(makeHHString((((c & 3) << 4) & 48) | ((c2 & 960) >> 6) | 128));
                        writer.write(37);
                        writer.write(makeHHString((c2 & '?') | 128));
                        c = c2;
                    } else {
                        int i6 = (c >> '\f') | WinError.ERROR_FORMS_AUTH_REQUIRED;
                        writer.write(37);
                        writer.write(makeHHString(i6));
                        writer.write(37);
                        writer.write(makeHHString(((c & 4032) >> 6) | 128));
                        writer.write(37);
                        writer.write(makeHHString((c & '?') | 128));
                    }
                } else if (escapingNotNeeded(c)) {
                    writer.write(c);
                } else {
                    writer.write("&#");
                    writer.write(Integer.toString(c));
                    writer.write(59);
                }
            } else if (c == '\"') {
                if (i2 > 0) {
                    writer.write(cArr, i3, i2);
                    i2 = 0;
                }
                if (z) {
                    writer.write("%22");
                } else {
                    writer.write(SerializerConstants.ENTITY_QUOT);
                }
            } else {
                if (c == '&') {
                    if (i2 > 0) {
                        writer.write(cArr, i3, i2);
                        i2 = 0;
                    }
                    writer.write(SerializerConstants.ENTITY_AMP);
                } else {
                    i2++;
                }
                i++;
            }
            i3 = i + 1;
            i++;
        }
        if (i2 <= 1) {
            if (i2 == 1) {
                writer.write(c);
            }
        } else if (i3 == 0) {
            writer.write(str);
        } else {
            writer.write(cArr, i3, i2);
        }
    }

    public static class Trie {
        public static final int ALPHA_SIZE = 128;
        final Node m_Root;
        private char[] m_charBuffer;
        private final boolean m_lowerCaseOnly;

        public class Node {
            final Node[] m_nextChar = new Node[128];
            Object m_Value = null;

            public Node() {
            }
        }

        public Trie(Trie trie) {
            this.m_charBuffer = new char[0];
            this.m_Root = trie.m_Root;
            this.m_lowerCaseOnly = trie.m_lowerCaseOnly;
            this.m_charBuffer = new char[trie.getLongestKeyLength()];
        }

        public Object get(String str) {
            Node node;
            int length = str.length();
            if (this.m_charBuffer.length < length) {
                return null;
            }
            Node node2 = this.m_Root;
            if (length != 0) {
                if (length != 1) {
                    for (int i = 0; i < length; i++) {
                        char cCharAt = str.charAt(i);
                        if (128 <= cCharAt || (node2 = node2.m_nextChar[cCharAt]) == null) {
                            return null;
                        }
                    }
                    return node2.m_Value;
                }
                char cCharAt2 = str.charAt(0);
                if (cCharAt2 < 128 && (node = node2.m_nextChar[cCharAt2]) != null) {
                    return node.m_Value;
                }
            }
            return null;
        }

        public Object get2(String str) {
            Node node;
            int length = str.length();
            char[] cArr = this.m_charBuffer;
            if (cArr.length < length) {
                return null;
            }
            Node node2 = this.m_Root;
            if (length != 0) {
                if (length != 1) {
                    str.getChars(0, length, cArr, 0);
                    for (int i = 0; i < length; i++) {
                        char c = this.m_charBuffer[i];
                        if (128 <= c || (node2 = node2.m_nextChar[c]) == null) {
                            return null;
                        }
                    }
                    return node2.m_Value;
                }
                char cCharAt = str.charAt(0);
                if (cCharAt < 128 && (node = node2.m_nextChar[cCharAt]) != null) {
                    return node.m_Value;
                }
            }
            return null;
        }

        public int getLongestKeyLength() {
            return this.m_charBuffer.length;
        }

        public Object put(String str, Object obj) {
            int length = str.length();
            if (length > this.m_charBuffer.length) {
                this.m_charBuffer = new char[length];
            }
            Node node = this.m_Root;
            int i = 0;
            while (i < length) {
                Node node2 = node.m_nextChar[Character.toLowerCase(str.charAt(i))];
                if (node2 == null) {
                    while (i < length) {
                        Node node3 = new Node();
                        boolean z = this.m_lowerCaseOnly;
                        Node[] nodeArr = node.m_nextChar;
                        if (z) {
                            nodeArr[Character.toLowerCase(str.charAt(i))] = node3;
                        } else {
                            nodeArr[Character.toUpperCase(str.charAt(i))] = node3;
                            node.m_nextChar[Character.toLowerCase(str.charAt(i))] = node3;
                        }
                        i++;
                        node = node3;
                    }
                    break;
                }
                i++;
                node = node2;
            }
            Object obj2 = node.m_Value;
            node.m_Value = obj;
            return obj2;
        }

        public Trie(boolean z) {
            this.m_charBuffer = new char[0];
            this.m_Root = new Node();
            this.m_lowerCaseOnly = z;
        }

        public Trie() {
            this.m_charBuffer = new char[0];
            this.m_Root = new Node();
            this.m_lowerCaseOnly = false;
        }
    }

    public ToHTMLStream() {
        this(null);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public final void endElement(String str) throws SAXException {
        endElement(null, null, str);
    }
}
