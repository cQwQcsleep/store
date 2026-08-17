package com.sun.org.apache.xml.internal.serializer;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.serializer.dom3.DOMConstants;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException;
import defpackage.x73;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ToStream extends SerializerBase {
    private static final String COMMENT_BEGIN = "<!--";
    private static final String COMMENT_END = "-->";
    java.lang.reflect.Method m_canConvertMeth;
    protected boolean m_cdataStartCalled;
    protected CharInfo m_charInfo;
    Object m_charToByteConverter;
    protected CharacterBuffer m_charactersBuffer;
    protected int m_childNodeNum;
    protected List<Integer> m_childNodeNumStack;
    protected BoolStack m_disableOutputEscapingStates;
    EncodingInfo m_encodingInfo;
    private boolean m_escaping;
    private boolean m_expandDTDEntities;
    private char m_highSurrogate;
    protected boolean m_inDoctype;
    boolean m_isUTF8;
    protected boolean m_ispreserveSpace;
    protected boolean m_isprevtext;
    protected char[] m_lineSep;
    protected int m_lineSepLen;
    protected boolean m_lineSepUse;
    protected int m_maxCharacter;
    OutputStream m_outputStream;
    protected BoolStack m_preserveSpaces;
    boolean m_shouldFlush;
    protected boolean m_spaceBeforeClose;
    boolean m_startNewLine;
    boolean m_triedToGetConverter;
    private boolean m_writer_set_by_user;

    public class CharacterBuffer {
        private List<GenericCharacters> bufferedCharacters;

        public abstract class GenericCharacters {
            private GenericCharacters() {
            }

            public abstract boolean flush(boolean z) throws SAXException;

            public abstract char[] toChars();
        }

        private CharacterBuffer() {
            this.bufferedCharacters = new ArrayList();
        }

        public void addEntityReference(final String str) {
            this.bufferedCharacters.add(new GenericCharacters() { // from class: com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.GenericCharacters
                public boolean flush(boolean z) throws SAXException {
                    ToStream toStream = ToStream.this;
                    if (toStream.m_elemContext.m_startTagOpen) {
                        toStream.closeStartTag();
                        ToStream.this.m_elemContext.m_startTagOpen = false;
                    }
                    ToStream toStream2 = ToStream.this;
                    if (toStream2.m_cdataTagOpen) {
                        toStream2.closeCDATA();
                    }
                    char[] chars = toChars();
                    try {
                        ToStream.this.m_writer.write(chars, 0, chars.length);
                        ToStream.this.m_isprevtext = true;
                        return false;
                    } catch (IOException e) {
                        x73.a(e);
                        return false;
                    }
                }

                @Override // com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.GenericCharacters
                public char[] toChars() {
                    return ("&" + str + ";").toCharArray();
                }
            });
        }

        public void addRawText(char[] cArr, int i, int i2) {
            this.bufferedCharacters.add(new GenericCharacters(cArr, i, i2) { // from class: com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.3
                char[] text;
                final /* synthetic */ char[] val$chars;
                final /* synthetic */ int val$length;
                final /* synthetic */ int val$start;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                    this.val$chars = cArr;
                    this.val$start = i;
                    this.val$length = i2;
                    this.text = Arrays.copyOfRange(cArr, i, i2 + i);
                }

                @Override // com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.GenericCharacters
                public boolean flush(boolean z) throws SAXException {
                    int i3 = 0;
                    while (z) {
                        try {
                            char[] cArr2 = this.text;
                            if (cArr2[i3] != '\n') {
                                break;
                            }
                            i3++;
                            if (i3 == cArr2.length) {
                                return true;
                            }
                        } catch (IOException e) {
                            x73.a(e);
                            return false;
                        }
                    }
                    Writer writer = ToStream.this.m_writer;
                    char[] cArr3 = this.text;
                    writer.write(cArr3, i3, cArr3.length - i3);
                    ToStream.this.m_isprevtext = true;
                    return false;
                }

                @Override // com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.GenericCharacters
                public char[] toChars() {
                    return this.text;
                }
            });
        }

        public void addText(char[] cArr, int i, int i2) {
            this.bufferedCharacters.add(new GenericCharacters(cArr, i, i2) { // from class: com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.1
                char[] text;
                final /* synthetic */ char[] val$chars;
                final /* synthetic */ int val$length;
                final /* synthetic */ int val$start;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                    this.val$chars = cArr;
                    this.val$start = i;
                    this.val$length = i2;
                    this.text = Arrays.copyOfRange(cArr, i, i2 + i);
                }

                @Override // com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.GenericCharacters
                public boolean flush(boolean z) throws SAXException {
                    int i3 = 0;
                    while (z) {
                        char[] cArr2 = this.text;
                        if (cArr2[i3] != '\n') {
                            break;
                        }
                        i3++;
                        if (i3 == cArr2.length) {
                            return true;
                        }
                    }
                    ToStream toStream = ToStream.this;
                    char[] cArr3 = this.text;
                    toStream.outputCharacters(cArr3, i3, cArr3.length - i3);
                    return false;
                }

                @Override // com.sun.org.apache.xml.internal.serializer.ToStream.CharacterBuffer.GenericCharacters
                public char[] toChars() {
                    return this.text;
                }
            });
        }

        public void clear() {
            this.bufferedCharacters.clear();
        }

        public void flush(boolean z) throws SAXException {
            Iterator<GenericCharacters> it = this.bufferedCharacters.iterator();
            while (it.hasNext()) {
                z = it.next().flush(z);
                it.remove();
            }
        }

        public boolean isAnyCharactersBuffered() {
            return this.bufferedCharacters.size() > 0;
        }

        public char[] toChars() {
            StringBuilder sb = new StringBuilder();
            Iterator<GenericCharacters> it = this.bufferedCharacters.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toChars());
            }
            return sb.toString().toCharArray();
        }
    }

    public ToStream(ErrorListener errorListener) {
        this.m_disableOutputEscapingStates = new BoolStack();
        this.m_encodingInfo = new EncodingInfo(null, null);
        this.m_triedToGetConverter = false;
        this.m_charToByteConverter = null;
        this.m_charactersBuffer = new CharacterBuffer();
        this.m_childNodeNumStack = new ArrayList();
        this.m_childNodeNum = 0;
        this.m_preserveSpaces = new BoolStack();
        this.m_ispreserveSpace = false;
        this.m_isprevtext = false;
        this.m_maxCharacter = Encodings.getLastPrintable();
        char[] charArray = System.lineSeparator().toCharArray();
        this.m_lineSep = charArray;
        this.m_lineSepUse = true;
        this.m_lineSepLen = charArray.length;
        this.m_shouldFlush = true;
        this.m_spaceBeforeClose = false;
        this.m_inDoctype = false;
        this.m_isUTF8 = false;
        this.m_cdataStartCalled = false;
        this.m_expandDTDEntities = true;
        this.m_highSurrogate = (char) 0;
        this.m_escaping = true;
        this.m_errListener = errorListener;
    }

    private void DTDprolog() throws SAXException, IOException {
        Writer writer = this.m_writer;
        if (this.m_needToOutputDocTypeDecl) {
            outputDocTypeDecl(this.m_elemContext.m_elementName, false);
            this.m_needToOutputDocTypeDecl = false;
        }
        if (this.m_inDoctype) {
            writer.write(" [");
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
            this.m_inDoctype = false;
        }
    }

    private void addCdataSectionElement(String str, List<String> list) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "{}", false);
        String strNextToken = stringTokenizer.nextToken();
        String strNextToken2 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : null;
        if (strNextToken2 == null) {
            list.add(null);
            list.add(strNextToken);
        } else {
            list.add(strNextToken);
            list.add(strNextToken2);
        }
    }

    private boolean doAddAttributeAlways(String str, String str2, String str3, String str4, String str5, boolean z) {
        String str6;
        boolean z2;
        int index = this.m_attributes.getIndex(str3);
        if (index >= 0) {
            String str7 = null;
            if (this.m_tracer != null) {
                String value = this.m_attributes.getValue(index);
                if (!str5.equals(value)) {
                    str7 = value;
                }
            }
            this.m_attributes.setValue(index, str5);
            if (str7 != null) {
                firePseudoAttributes();
            }
            str6 = str5;
            z2 = false;
        } else {
            if (z) {
                int iIndexOf = str3.indexOf(58);
                if (iIndexOf > 0) {
                    NamespaceMappings.MappingRecord mappingFromPrefix = this.m_prefixMap.getMappingFromPrefix(str3.substring(0, iIndexOf));
                    if (mappingFromPrefix != null && mappingFromPrefix.m_declarationDepth == this.m_elemContext.m_currentElemDepth && !mappingFromPrefix.m_uri.equals(str)) {
                        String strLookupPrefix = this.m_prefixMap.lookupPrefix(str);
                        if (strLookupPrefix == null) {
                            strLookupPrefix = this.m_prefixMap.generateNextPrefix();
                        }
                        str3 = strLookupPrefix + ':' + str2;
                    }
                }
                try {
                    ensureAttributesNamespaceIsDeclared(str, str2, str3);
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
            String str8 = str3;
            str6 = str5;
            this.m_attributes.addAttribute(str, str2, str8, str4, str6);
            if (this.m_tracer != null) {
                firePseudoAttributes();
            }
            z2 = true;
            str3 = str8;
        }
        if (this.m_doIndent && str3.equals(Constants.ATTRNAME_XMLSPACE)) {
            if (str6.equals(SchemaSymbols.ATTVAL_PRESERVE)) {
                this.m_ispreserveSpace = true;
                if (this.m_preserveSpaces.size() > 0) {
                    this.m_preserveSpaces.setTop(this.m_ispreserveSpace);
                }
            } else if (str6.equals("default")) {
                this.m_ispreserveSpace = false;
                if (this.m_preserveSpaces.size() > 0) {
                    this.m_preserveSpaces.setTop(this.m_ispreserveSpace);
                }
            }
        }
        return z2;
    }

    private int handleEscaping(Writer writer, char c, char[] cArr, int i, int i2) throws SAXException, IOException {
        if (Encodings.isHighUTF16Surrogate(c) || Encodings.isLowUTF16Surrogate(c)) {
            return (writeUTF16Surrogate(c, cArr, i, i2) < 0 || !Encodings.isHighUTF16Surrogate(c)) ? i : i + 1;
        }
        writeCharRef(writer, c);
        return i;
    }

    private static boolean isCharacterInC0orC1Range(char c) {
        return (c == '\t' || c == '\n' || c == '\r' || ((c < 127 || c > 159) && (c < 1 || c > 31))) ? false : true;
    }

    private boolean isEscapingDisabled() {
        return this.m_disableOutputEscapingStates.peekOrFalse();
    }

    private static boolean isNELorLSEPCharacter(char c) {
        return c == 133 || c == 8232;
    }

    public static final boolean isUTF16Surrogate(char c) {
        return (c & 64512) == 55296;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void outputCharacters(char[] cArr, int i, int i2) throws SAXException {
        ToStream toStream;
        char[] cArr2;
        int i3 = i + i2;
        int iProcessDirty = i;
        int i4 = i - 1;
        while (iProcessDirty < i3) {
            try {
                char c = cArr[iProcessDirty];
                if (c != ' ' && ((c != '\n' || !this.m_lineSepUse) && c != '\r' && c != '\t')) {
                    break;
                }
                if (this.m_charInfo.isTextASCIIClean(c)) {
                    toStream = this;
                    cArr2 = cArr;
                } else {
                    toStream = this;
                    cArr2 = cArr;
                    iProcessDirty = toStream.processDirty(cArr2, i3, iProcessDirty, c, i4, true);
                    i4 = iProcessDirty;
                }
                iProcessDirty++;
                this = toStream;
                cArr = cArr2;
            } catch (IOException e) {
                x73.a(e);
                return;
            }
        }
        ToStream toStream2 = this;
        char[] cArr3 = cArr;
        boolean zEquals = "1.0".equals(toStream2.getVersion());
        while (iProcessDirty < i3) {
            while (iProcessDirty < i3) {
                char c2 = cArr3[iProcessDirty];
                if (c2 >= 127 || !toStream2.m_charInfo.isTextASCIIClean(c2)) {
                    break;
                } else {
                    iProcessDirty++;
                }
            }
            if (iProcessDirty == i3) {
                break;
            }
            char c3 = cArr3[iProcessDirty];
            if ((isCharacterInC0orC1Range(c3) || ((!zEquals && isNELorLSEPCharacter(c3)) || !toStream2.escapingNotNeeded(c3) || toStream2.m_charInfo.isSpecialTextChar(c3))) && '\"' != c3) {
                iProcessDirty = toStream2.processDirty(cArr3, i3, iProcessDirty, c3, i4, true);
                i4 = iProcessDirty;
            }
            iProcessDirty++;
        }
        int i5 = i4 + 1;
        if (iProcessDirty > i5) {
            toStream2.m_writer.write(cArr3, i5, iProcessDirty - i5);
        }
        toStream2.m_isprevtext = true;
    }

    private void outputEntityReference(String str) throws SAXException {
        startNonEscaping();
        characters("&" + str + ';');
        endNonEscaping();
        this.m_isprevtext = true;
    }

    private void printSpace(int i) throws IOException {
        Writer writer = this.m_writer;
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    private int processDirty(char[] cArr, int i, int i2, char c, int i3, boolean z) throws SAXException, IOException {
        int i4 = i3 + 1;
        if (i2 > i4) {
            this.m_writer.write(cArr, i4, i2 - i4);
        }
        if ('\n' != c || !z) {
            return accumDefaultEscape(this.m_writer, c, i2, cArr, i, z, false) - 1;
        }
        this.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        return i2;
    }

    private void resetToStream() {
        this.m_cdataStartCalled = false;
        this.m_disableOutputEscapingStates.clear();
        this.m_escaping = true;
        this.m_inDoctype = false;
        this.m_ispreserveSpace = false;
        this.m_preserveSpaces.clear();
        this.m_childNodeNum = 0;
        this.m_childNodeNumStack.clear();
        this.m_charactersBuffer.clear();
        this.m_isprevtext = false;
        this.m_isUTF8 = false;
        this.m_shouldFlush = true;
        this.m_spaceBeforeClose = false;
        this.m_startNewLine = false;
        this.m_lineSepUse = true;
        this.m_expandDTDEntities = true;
    }

    private void setCdataSectionElements(String str, Properties properties) {
        String property = properties.getProperty(str);
        if (property != null) {
            ArrayList arrayList = new ArrayList();
            int length = property.length();
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (int i = 0; i < length; i++) {
                char cCharAt = property.charAt(i);
                if (Character.isWhitespace(cCharAt)) {
                    if (!z) {
                        if (sb.length() > 0) {
                            addCdataSectionElement(sb.toString(), arrayList);
                            sb.setLength(0);
                        }
                    }
                } else if ('{' == cCharAt) {
                    z = true;
                } else if ('}' == cCharAt) {
                    z = false;
                }
                sb.append(cCharAt);
            }
            if (sb.length() > 0) {
                addCdataSectionElement(sb.toString(), arrayList);
                sb.setLength(0);
            }
            setCdataSectionElements(arrayList);
        }
    }

    private void setOutputStreamInternal(OutputStream outputStream, boolean z) {
        Writer writer;
        this.m_outputStream = outputStream;
        String outputProperty = getOutputProperty("encoding");
        if ("UTF-8".equalsIgnoreCase(outputProperty)) {
            try {
                setWriterInternal(new WriterToUTF8Buffered(outputStream), false);
                return;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return;
            }
        }
        if ("WINDOWS-1250".equals(outputProperty) || "US-ASCII".equals(outputProperty) || "ASCII".equals(outputProperty)) {
            setWriterInternal(new WriterToASCI(outputStream), false);
            return;
        }
        if (outputProperty == null) {
            setWriterInternal(new OutputStreamWriter(outputStream), false);
            return;
        }
        try {
            writer = Encodings.getWriter(outputStream, outputProperty);
        } catch (UnsupportedEncodingException unused) {
            writer = null;
        }
        if (writer == null) {
            System.out.println("Warning: encoding \"" + outputProperty + "\" not supported, using UTF-8");
            setEncoding("UTF-8");
            try {
                writer = Encodings.getWriter(outputStream, "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
        }
        setWriterInternal(writer, false);
    }

    private void setWriterInternal(Writer writer, boolean z) {
        this.m_writer_set_by_user = z;
        this.m_writer = writer;
        if (this.m_tracer != null) {
            for (Object writer2 = writer; writer2 instanceof WriterChain; writer2 = ((WriterChain) writer2).getWriter()) {
                if (writer2 instanceof SerializerTraceWriter) {
                    return;
                }
            }
            this.m_writer = new SerializerTraceWriter(this.m_writer, this.m_tracer);
        }
    }

    private void throwIOE(char c, char c2) throws IOException {
        throw new IOException(Utils.messages.createMessage("ER_INVALID_UTF16_SURROGATE", new Object[]{Integer.toHexString(c) + " " + Integer.toHexString(c2)}));
    }

    private int writeCharRef(Writer writer, char c, char c2) throws SAXException, IOException {
        if (this.m_cdataTagOpen) {
            closeCDATA();
        }
        int codePoint = Encodings.toCodePoint(c, c2);
        writer.write("&#");
        writer.write(Integer.toString(codePoint));
        writer.write(59);
        return codePoint;
    }

    public int accumDefaultEntity(Writer writer, char c, int i, char[] cArr, int i2, boolean z, boolean z2) throws IOException {
        String outputStringForChar;
        if (!z2 && '\n' == c) {
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } else {
            if ((!(z && this.m_charInfo.isSpecialTextChar(c)) && (z || !this.m_charInfo.isSpecialAttrChar(c))) || (outputStringForChar = this.m_charInfo.getOutputStringForChar(c)) == null) {
                return i;
            }
            writer.write(outputStringForChar);
        }
        return i + 1;
    }

    public int accumDefaultEscape(Writer writer, char c, int i, char[] cArr, int i2, boolean z, boolean z2) throws SAXException, IOException {
        int iAccumDefaultEntity = accumDefaultEntity(writer, c, i, cArr, i2, z, z2);
        if (i != iAccumDefaultEntity) {
            return iAccumDefaultEntity;
        }
        if (this.m_highSurrogate != 0) {
            if (!Encodings.isLowUTF16Surrogate(c)) {
                throwIOE(this.m_highSurrogate, c);
            }
            writeCharRef(writer, this.m_highSurrogate, c);
            this.m_highSurrogate = (char) 0;
            return iAccumDefaultEntity + 1;
        }
        if (Encodings.isHighUTF16Surrogate(c)) {
            int i3 = i + 1;
            if (i3 >= i2) {
                this.m_highSurrogate = c;
                return iAccumDefaultEntity + 1;
            }
            char c2 = cArr[i3];
            if (!Encodings.isLowUTF16Surrogate(c2)) {
                throwIOE(c, c2);
            }
            writeCharRef(writer, c, c2);
            return iAccumDefaultEntity + 2;
        }
        if (isCharacterInC0orC1Range(c) || (SerializerConstants.XMLVERSION11.equals(getVersion()) && isNELorLSEPCharacter(c))) {
            writeCharRef(writer, c);
        } else if ((!escapingNotNeeded(c) || ((z && this.m_charInfo.isSpecialTextChar(c)) || (!z && this.m_charInfo.isSpecialAttrChar(c)))) && this.m_elemContext.m_currentElemDepth > 0) {
            writeCharRef(writer, c);
        } else {
            writer.write(c);
        }
        return iAccumDefaultEntity + 1;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase
    public boolean addAttributeAlways(String str, String str2, String str3, String str4, String str5, boolean z) {
        if (this.m_charactersBuffer.isAnyCharactersBuffered()) {
            return this.m_attributes.getIndex(str3) < 0;
        }
        return doAddAttributeAlways(str, str2, str3, str4, str5, z);
    }

    public void addCdataSectionElements(String str) {
        if (str != null) {
            initCdataElems(str);
        }
        if (this.m_StringOfCDATASections == null) {
            this.m_StringOfCDATASections = str;
            return;
        }
        this.m_StringOfCDATASections += " " + str;
    }

    public void attributeDecl(String str, String str2, String str3, String str4, String str5) throws SAXException {
        if (this.m_inExternalDTD) {
            return;
        }
        try {
            Writer writer = this.m_writer;
            DTDprolog();
            writer.write("<!ATTLIST ");
            writer.write(str);
            writer.write(32);
            writer.write(str2);
            writer.write(32);
            writer.write(str3);
            if (str4 != null) {
                writer.write(32);
                writer.write(str4);
            }
            writer.write(62);
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void cdata(char[] cArr, int i, int i2) throws SAXException {
        ToStream toStream;
        char[] cArr2;
        int i3;
        int i4;
        try {
            boolean z = false;
            if (this.m_elemContext.m_startTagOpen) {
                closeStartTag();
                this.m_elemContext.m_startTagOpen = false;
            }
            if (!this.m_cdataTagOpen && shouldIndentForText()) {
                indent();
            }
            if (i2 >= 1 && escapingNotNeeded(cArr[i])) {
                z = true;
            }
            if (z && !this.m_cdataTagOpen) {
                this.m_writer.write("<![CDATA[");
                this.m_cdataTagOpen = true;
            }
            if (isEscapingDisabled()) {
                charactersRaw(cArr, i, i2);
                toStream = this;
                cArr2 = cArr;
                i3 = i;
                i4 = i2;
            } else {
                toStream = this;
                cArr2 = cArr;
                i3 = i;
                i4 = i2;
                toStream.writeNormalizedChars(cArr2, i3, i4, true, this.m_lineSepUse);
            }
            if (z && cArr2[(i3 + i4) - 1] == ']') {
                toStream.closeCDATA();
            }
            toStream.m_isprevtext = true;
            if (toStream.m_tracer != null) {
                super.fireCDATAEvent(cArr2, i3, i4);
            }
        } catch (IOException e) {
            throw new SAXException(Utils.messages.createMessage("ER_OIERROR", null), e);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (i2 != 0) {
            if (!isInEntityRef() || this.m_expandDTDEntities) {
                boolean zShouldFormatOutput = shouldFormatOutput();
                if (this.m_elemContext.m_startTagOpen) {
                    closeStartTag();
                    this.m_elemContext.m_startTagOpen = false;
                } else if (this.m_needToCallStartDocument) {
                    startDocumentInternal();
                }
                if (this.m_cdataStartCalled || this.m_elemContext.m_isCdataSection) {
                    cdata(cArr, i, i2);
                    return;
                }
                if (this.m_cdataTagOpen) {
                    closeCDATA();
                }
                if (this.m_disableOutputEscapingStates.peekOrFalse() || !this.m_escaping) {
                    if (zShouldFormatOutput) {
                        this.m_charactersBuffer.addRawText(cArr, i, i2);
                    } else {
                        charactersRaw(cArr, i, i2);
                        this.m_isprevtext = true;
                    }
                    if (this.m_tracer != null) {
                        super.fireCharEvent(cArr, i, i2);
                        return;
                    }
                    return;
                }
                if (this.m_elemContext.m_startTagOpen) {
                    closeStartTag();
                    this.m_elemContext.m_startTagOpen = false;
                }
                if (zShouldFormatOutput) {
                    this.m_charactersBuffer.addText(cArr, i, i2);
                } else {
                    outputCharacters(cArr, i, i2);
                }
                if (this.m_tracer != null) {
                    super.fireCharEvent(cArr, i, i2);
                }
            }
        }
    }

    public void charactersRaw(char[] cArr, int i, int i2) throws SAXException {
        if (isInEntityRef()) {
            return;
        }
        try {
            if (this.m_elemContext.m_startTagOpen) {
                closeStartTag();
                this.m_elemContext.m_startTagOpen = false;
            }
            this.m_writer.write(cArr, i, i2);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void closeCDATA() throws SAXException {
        try {
            this.m_writer.write("]]>");
            this.m_cdataTagOpen = false;
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void closeStartTag() throws SAXException {
        ElemContext elemContext = this.m_elemContext;
        if (elemContext.m_startTagOpen) {
            try {
                if (this.m_tracer != null) {
                    super.fireStartElem(elemContext.m_elementName);
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
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (isInEntityRef()) {
            return;
        }
        if (this.m_doIndent) {
            this.m_childNodeNum++;
            flushCharactersBuffer(false);
        }
        if (this.m_elemContext.m_startTagOpen) {
            closeStartTag();
            this.m_elemContext.m_startTagOpen = false;
        } else if (this.m_needToCallStartDocument) {
            startDocumentInternal();
            this.m_needToCallStartDocument = false;
        }
        try {
            if (shouldIndent() && this.m_isStandalone) {
                indent();
            }
            int i3 = i + i2;
            if (this.m_cdataTagOpen) {
                closeCDATA();
            }
            if (shouldIndent() && !this.m_isStandalone) {
                indent();
            }
            Writer writer = this.m_writer;
            writer.write("<!--");
            int i4 = i;
            int i5 = i4;
            boolean z = false;
            while (i4 < i3) {
                if (z && cArr[i4] == '-') {
                    writer.write(cArr, i5, i4 - i5);
                    writer.write(" -");
                    i5 = i4 + 1;
                }
                z = cArr[i4] == '-';
                i4++;
            }
            if (i2 > 0) {
                int i6 = i3 - i5;
                if (i6 > 0) {
                    writer.write(cArr, i5, i6);
                }
                if (cArr[i3 - 1] == '-') {
                    writer.write(32);
                }
            }
            writer.write("-->");
            this.m_startNewLine = true;
            if (this.m_tracer != null) {
                super.fireCommentEvent(cArr, i, i2);
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void elementDecl(String str, String str2) throws SAXException {
        if (this.m_inExternalDTD) {
            return;
        }
        try {
            Writer writer = this.m_writer;
            DTDprolog();
            writer.write("<!ELEMENT ");
            writer.write(str);
            writer.write(32);
            writer.write(str2);
            writer.write(62);
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        if (this.m_cdataTagOpen) {
            closeCDATA();
        }
        this.m_cdataStartCalled = false;
    }

    public void endDTD() throws SAXException {
        try {
            if (this.m_needToCallStartDocument) {
                return;
            }
            if (this.m_needToOutputDocTypeDecl) {
                outputDocTypeDecl(this.m_elemContext.m_elementName, false);
                this.m_needToOutputDocTypeDecl = false;
            }
            Writer writer = this.m_writer;
            if (this.m_inDoctype) {
                writer.write(62);
            } else {
                writer.write("]>");
            }
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        if (isInEntityRef()) {
            return;
        }
        if (this.m_doIndent) {
            flushCharactersBuffer(false);
        }
        this.m_prefixMap.popNamespaces(this.m_elemContext.m_currentElemDepth, null);
        try {
            Writer writer = this.m_writer;
            ElemContext elemContext = this.m_elemContext;
            if (elemContext.m_startTagOpen) {
                if (this.m_tracer != null) {
                    super.fireStartElem(elemContext.m_elementName);
                }
                int length = this.m_attributes.getLength();
                if (length > 0) {
                    processAttributes(this.m_writer, length);
                    this.m_attributes.clear();
                }
                if (this.m_spaceBeforeClose) {
                    writer.write(" />");
                } else {
                    writer.write("/>");
                }
            } else {
                if (this.m_cdataTagOpen) {
                    closeCDATA();
                }
                if (shouldIndent() && (this.m_childNodeNum > 1 || !this.m_isprevtext)) {
                    indent(this.m_elemContext.m_currentElemDepth - 1);
                }
                writer.write(60);
                writer.write(47);
                writer.write(str3);
                writer.write(62);
            }
            if (this.m_doIndent) {
                this.m_ispreserveSpace = this.m_preserveSpaces.popAndTop();
                List<Integer> list = this.m_childNodeNumStack;
                this.m_childNodeNum = list.remove(list.size() - 1).intValue();
                this.m_isprevtext = false;
            }
            if (this.m_tracer != null) {
                super.fireEndElem(str3);
            }
            this.m_elemContext = this.m_elemContext.m_prev;
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void endNonEscaping() throws SAXException {
        this.m_disableOutputEscapingStates.pop();
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
    }

    public String ensureAttributesNamespaceIsDeclared(String str, String str2, String str3) throws SAXException {
        if (str == null || str.length() <= 0) {
            return null;
        }
        int iIndexOf = str3.indexOf(":");
        String strSubstring = iIndexOf < 0 ? "" : str3.substring(0, iIndexOf);
        NamespaceMappings namespaceMappings = this.m_prefixMap;
        if (iIndexOf > 0) {
            String strLookupNamespace = namespaceMappings.lookupNamespace(strSubstring);
            if (strLookupNamespace != null && strLookupNamespace.equals(str)) {
                return null;
            }
            startPrefixMapping(strSubstring, str, false);
            addAttribute("http://www.w3.org/2000/xmlns/", strSubstring, "xmlns:".concat(strSubstring), "CDATA", str, false);
            return strSubstring;
        }
        String strLookupPrefix = namespaceMappings.lookupPrefix(str);
        if (strLookupPrefix != null) {
            return strLookupPrefix;
        }
        String strGenerateNextPrefix = this.m_prefixMap.generateNextPrefix();
        startPrefixMapping(strGenerateNextPrefix, str, false);
        addAttribute("http://www.w3.org/2000/xmlns/", strGenerateNextPrefix, "xmlns:" + strGenerateNextPrefix, "CDATA", str, false);
        return strGenerateNextPrefix;
    }

    public void ensurePrefixIsDeclared(String str, String str2) throws SAXException {
        if (str == null || str.length() <= 0) {
            return;
        }
        int iIndexOf = str2.indexOf(":");
        boolean z = iIndexOf < 0;
        String strSubstring = z ? "" : str2.substring(0, iIndexOf);
        String strLookupNamespace = this.m_prefixMap.lookupNamespace(strSubstring);
        if (strLookupNamespace == null || !strLookupNamespace.equals(str)) {
            startPrefixMapping(strSubstring, str);
            addAttributeAlways("http://www.w3.org/2000/xmlns/", z ? "xmlns" : strSubstring, z ? "xmlns" : "xmlns:".concat(strSubstring), "CDATA", str, false);
        }
    }

    public boolean escapingNotNeeded(char c) {
        if (c < 127) {
            return c >= ' ' || '\n' == c || '\r' == c || '\t' == c;
        }
        return this.m_encodingInfo.isInEncoding(c);
    }

    public void externalEntityDecl(String str, String str2, String str3) throws SAXException {
        try {
            DTDprolog();
            this.m_writer.write("<!ENTITY ");
            this.m_writer.write(str);
            this.m_writer.write(JdkXmlUtils.getDTDExternalDecl(str2, str3));
            this.m_writer.write(">");
            this.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void firePseudoAttributes() {
        if (this.m_tracer != null) {
            try {
                this.m_writer.flush();
                StringBuffer stringBuffer = new StringBuffer();
                int length = this.m_attributes.getLength();
                if (length > 0) {
                    processAttributes(new WritertoStringBuffer(stringBuffer), length);
                }
                stringBuffer.append('>');
                char[] charArray = stringBuffer.toString().toCharArray();
                this.m_tracer.fireGenerateEvent(11, charArray, 0, charArray.length);
            } catch (IOException | SAXException unused) {
            }
        }
    }

    public final void flushCharactersBuffer(boolean z) throws SAXException {
        try {
            try {
                if (shouldFormatOutput() && this.m_charactersBuffer.isAnyCharactersBuffered()) {
                    boolean z2 = false;
                    if (this.m_elemContext.m_isCdataSection) {
                        char[] chars = this.m_charactersBuffer.toChars();
                        cdata(chars, 0, chars.length);
                    } else {
                        if (!z) {
                            this.m_childNodeNum++;
                        }
                        if (shouldIndentForText()) {
                            indent();
                            this.m_startNewLine = true;
                            z2 = true;
                        }
                        this.m_charactersBuffer.flush(z2);
                    }
                }
                this.m_charactersBuffer.clear();
            } catch (IOException e) {
                throw new SAXException(e);
            }
        } catch (Throwable th) {
            this.m_charactersBuffer.clear();
            throw th;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void flushPending() throws SAXException {
        if (this.m_needToCallStartDocument) {
            startDocumentInternal();
            this.m_needToCallStartDocument = false;
        }
        if (this.m_elemContext.m_startTagOpen) {
            closeStartTag();
            this.m_elemContext.m_startTagOpen = false;
        }
        if (this.m_cdataTagOpen) {
            closeCDATA();
            this.m_cdataTagOpen = false;
        }
    }

    public final void flushWriter() throws SAXException {
        Writer writer = this.m_writer;
        if (writer != null) {
            try {
                if (writer instanceof WriterToUTF8Buffered) {
                    if (this.m_shouldFlush) {
                        ((WriterToUTF8Buffered) writer).flush();
                    } else {
                        ((WriterToUTF8Buffered) writer).flushBuffer();
                    }
                }
                if (!(writer instanceof WriterToASCI)) {
                    writer.flush();
                } else if (this.m_shouldFlush) {
                    writer.flush();
                }
            } catch (IOException e) {
                x73.a(e);
            }
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public boolean getIndent() {
        return shouldFormatOutput();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public int getIndentAmount() {
        return this.m_indentAmount;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Properties getOutputFormat() {
        Properties properties = new Properties();
        for (String str : getOutputPropDefaultKeys()) {
            properties.put(str, getOutputPropertyDefault(str));
        }
        Properties properties2 = new Properties(properties);
        for (String str2 : getOutputPropKeys()) {
            String outputPropertyNonDefault = getOutputPropertyNonDefault(str2);
            if (outputPropertyNonDefault != null) {
                properties2.put(str2, outputPropertyNonDefault);
            }
        }
        return properties2;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public OutputStream getOutputStream() {
        return this.m_outputStream;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Writer getWriter() {
        return this.m_writer;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        if (i2 == 0) {
            return;
        }
        characters(cArr, i, i2);
    }

    public void indent(int i) throws IOException {
        if (this.m_startNewLine) {
            outputLineSep();
        }
        printSpace(i * this.m_indentAmount);
    }

    public void internalEntityDecl(String str, String str2) throws SAXException {
        if (this.m_inExternalDTD) {
            return;
        }
        try {
            DTDprolog();
            outputEntityDecl(str, str2);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.DTDHandler
    public void notationDecl(String str, String str2, String str3) throws SAXException {
        try {
            DTDprolog();
            this.m_writer.write("<!NOTATION ");
            this.m_writer.write(str);
            this.m_writer.write(JdkXmlUtils.getDTDExternalDecl(str2, str3));
            this.m_writer.write(">");
            this.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputDocTypeDecl(String str, boolean z) throws SAXException {
        if (this.m_cdataTagOpen) {
            closeCDATA();
        }
        try {
            Writer writer = this.m_writer;
            writer.write("<!DOCTYPE ");
            writer.write(str);
            String doctypeSystem = getDoctypeSystem();
            writer.write(JdkXmlUtils.getDTDExternalDecl(getDoctypePublic(), doctypeSystem));
            if (doctypeSystem == null || !z) {
                return;
            }
            writer.write(">");
            writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    public void outputEntityDecl(String str, String str2) throws IOException {
        Writer writer = this.m_writer;
        writer.write("<!ENTITY ");
        writer.write(str);
        writer.write(" \"");
        writer.write(str2);
        writer.write("\">");
        writer.write(this.m_lineSep, 0, this.m_lineSepLen);
    }

    public final void outputLineSep() throws IOException {
        this.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
    }

    public void processAttributes(Writer writer, int i) throws SAXException, IOException {
        String encoding = getEncoding();
        for (int i2 = 0; i2 < i; i2++) {
            String qName = this.m_attributes.getQName(i2);
            String value = this.m_attributes.getValue(i2);
            writer.write(32);
            writer.write(qName);
            writer.write("=\"");
            writeAttrString(writer, value, encoding);
            writer.write(34);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        if (!super.reset()) {
            return false;
        }
        resetToStream();
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.DOMSerializer
    public void serialize(Node node) throws IOException {
        try {
            new TreeWalker(this).traverse(node);
        } catch (SAXException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setContentHandler(ContentHandler contentHandler) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setDTDEntityExpansion(boolean z) {
        this.m_expandDTDEntities = z;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setEncoding(String str) {
        setOutputProperty("encoding", str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public boolean setEscaping(boolean z) {
        boolean z2 = this.m_escaping;
        this.m_escaping = z;
        return z2;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setIndentAmount(int i) {
        this.m_indentAmount = i;
    }

    public boolean setLineSepUse(boolean z) {
        boolean z2 = this.m_lineSepUse;
        this.m_lineSepUse = z;
        return z2;
    }

    public void setOutputFormat(Properties properties) {
        boolean z = this.m_shouldFlush;
        if (properties != null) {
            Enumeration<?> enumerationPropertyNames = properties.propertyNames();
            while (enumerationPropertyNames.hasMoreElements()) {
                String str = (String) enumerationPropertyNames.nextElement();
                String property = properties.getProperty(str);
                String str2 = (String) properties.get(str);
                if (str2 == null && property != null) {
                    setOutputPropertyDefault(str, property);
                }
                if (str2 != null) {
                    setOutputProperty(str, str2);
                }
            }
        }
        String str3 = (String) properties.get(OutputPropertiesFactory.S_KEY_ENTITIES);
        if (str3 != null) {
            this.m_charInfo = CharInfo.getCharInfo(str3, (String) properties.get(Constants.ATTRNAME_OUTPUT_METHOD));
        }
        this.m_shouldFlush = z;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setOutputStream(OutputStream outputStream) {
        setOutputStreamInternal(outputStream, true);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase
    public void setProp(String str, String str2, boolean z) {
        if (str2 != null) {
            char firstCharLocName = SerializerBase.getFirstCharLocName(str);
            if (firstCharLocName != 'i') {
                if (firstCharLocName != 'o') {
                    if (firstCharLocName != 's') {
                        if (firstCharLocName != 'v') {
                            if (firstCharLocName != 'l') {
                                if (firstCharLocName != 'm') {
                                    switch (firstCharLocName) {
                                        case 'c':
                                            if (Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS.equals(str)) {
                                                addCdataSectionElements(str2);
                                            }
                                            break;
                                        case 'd':
                                            if (Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM.equals(str)) {
                                                this.m_doctypeSystem = str2;
                                            } else if (Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC.equals(str)) {
                                                this.m_doctypePublic = str2;
                                                if (str2.startsWith("-//W3C//DTD XHTML")) {
                                                    this.m_spaceBeforeClose = true;
                                                }
                                            }
                                            break;
                                        case 'e':
                                            if ("encoding".equals(str)) {
                                                String mimeEncoding = Encodings.getMimeEncoding(str2);
                                                if (mimeEncoding != null) {
                                                    super.setProp("mime-name", mimeEncoding, z);
                                                }
                                                String outputPropertyNonDefault = getOutputPropertyNonDefault("encoding");
                                                String outputPropertyDefault = getOutputPropertyDefault("encoding");
                                                if ((z && (outputPropertyDefault == null || !outputPropertyDefault.equalsIgnoreCase(str2))) || (!z && (outputPropertyNonDefault == null || !outputPropertyNonDefault.equalsIgnoreCase(str2)))) {
                                                    EncodingInfo encodingInfo = Encodings.getEncodingInfo(str2);
                                                    if (encodingInfo.name == null) {
                                                        String strCreateMessage = Utils.messages.createMessage("ER_ENCODING_NOT_SUPPORTED", new Object[]{str2});
                                                        String str3 = "Warning: encoding \"" + str2 + "\" not supported, using UTF-8";
                                                        try {
                                                            ErrorListener errorListener = this.m_errListener;
                                                            if (errorListener != null) {
                                                                errorListener.warning(new TransformerException(strCreateMessage, this.m_sourceLocator));
                                                                this.m_errListener.warning(new TransformerException(str3, this.m_sourceLocator));
                                                            }
                                                            break;
                                                        } catch (Exception unused) {
                                                        }
                                                        encodingInfo = Encodings.getEncodingInfo("UTF-8");
                                                        str2 = "UTF-8";
                                                    }
                                                    EncodingInfo encodingInfo2 = encodingInfo;
                                                    String str4 = str2;
                                                    if (!z || outputPropertyNonDefault == null) {
                                                        this.m_encodingInfo = encodingInfo2;
                                                        this.m_isUTF8 = str2.equals("UTF-8");
                                                        OutputStream outputStream = getOutputStream();
                                                        if (outputStream != null) {
                                                            Writer writer = getWriter();
                                                            String outputProperty = getOutputProperty("encoding");
                                                            if ((writer == null || !this.m_writer_set_by_user) && !str2.equalsIgnoreCase(outputProperty)) {
                                                                super.setProp(str, str4, z);
                                                                setOutputStreamInternal(outputStream, false);
                                                            }
                                                        }
                                                    }
                                                    str2 = str4;
                                                }
                                            }
                                            break;
                                    }
                                } else if (Constants.ATTRNAME_OUTPUT_MEDIATYPE.equals(str)) {
                                    this.m_mediatype = str2;
                                }
                            } else if (OutputPropertiesFactory.S_KEY_LINE_SEPARATOR.equals(str)) {
                                char[] charArray = str2.toCharArray();
                                this.m_lineSep = charArray;
                                this.m_lineSepLen = charArray.length;
                            }
                        } else if ("version".equals(str)) {
                            this.m_version = str2;
                        }
                    } else if (Constants.ATTRNAME_OUTPUT_STANDALONE.equals(str)) {
                        if (z) {
                            setStandaloneInternal(str2);
                        } else {
                            this.m_standaloneWasSpecified = true;
                            setStandaloneInternal(str2);
                        }
                    }
                } else if ("omit-xml-declaration".equals(str)) {
                    this.m_shouldNotWriteXMLHeader = str2.endsWith(JdkConstants.JDK_YES);
                }
            } else if (OutputPropertiesFactory.S_KEY_INDENT_AMOUNT.equals(str)) {
                setIndentAmount(Integer.parseInt(str2));
            } else if ("indent".equals(str)) {
                this.m_doIndent = str2.endsWith(JdkConstants.JDK_YES);
            } else if (DOMConstants.NS_IS_STANDALONE.equals(str)) {
                this.m_isStandalone = str2.endsWith(JdkConstants.JDK_YES);
            }
            super.setProp(str, str2, z);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setTransformer(Transformer transformer) {
        super.setTransformer(transformer);
        if (this.m_tracer == null || (this.m_writer instanceof SerializerTraceWriter)) {
            return;
        }
        this.m_writer = new SerializerTraceWriter(this.m_writer, this.m_tracer);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public void setWriter(Writer writer) {
        setWriterInternal(writer, true);
    }

    public boolean shouldFormatOutput() {
        return this.m_doIndent && !this.m_ispreserveSpace;
    }

    public boolean shouldIndent() {
        if (shouldFormatOutput()) {
            return this.m_elemContext.m_currentElemDepth > 0 || this.m_isStandalone;
        }
        return false;
    }

    public boolean shouldIndentForText() {
        return shouldIndent() && this.m_childNodeNum > 1;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        if (this.m_doIndent) {
            flushCharactersBuffer(true);
        }
        this.m_cdataStartCalled = true;
    }

    public void startDTD(String str, String str2, String str3) throws SAXException {
        setDoctypeSystem(str3);
        setDoctypePublic(str2);
        this.m_elemContext.m_elementName = str;
        this.m_inDoctype = true;
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (isInEntityRef()) {
            return;
        }
        if (this.m_doIndent) {
            this.m_childNodeNum++;
            flushCharactersBuffer(false);
        }
        if (this.m_needToCallStartDocument) {
            startDocumentInternal();
            this.m_needToCallStartDocument = false;
        } else if (this.m_cdataTagOpen) {
            closeCDATA();
        }
        try {
            if (true == this.m_needToOutputDocTypeDecl && getDoctypeSystem() != null) {
                outputDocTypeDecl(str3, true);
            }
            this.m_needToOutputDocTypeDecl = false;
            if (this.m_elemContext.m_startTagOpen) {
                closeStartTag();
                this.m_elemContext.m_startTagOpen = false;
            }
            if (str != null) {
                ensurePrefixIsDeclared(str, str3);
            }
            if (shouldIndent() && this.m_startNewLine) {
                indent();
            }
            this.m_startNewLine = true;
            Writer writer = this.m_writer;
            writer.write(60);
            writer.write(str3);
            if (this.m_doIndent) {
                boolean zPeekOrFalse = this.m_preserveSpaces.peekOrFalse();
                this.m_ispreserveSpace = zPeekOrFalse;
                this.m_preserveSpaces.push(zPeekOrFalse);
                this.m_childNodeNumStack.add(Integer.valueOf(this.m_childNodeNum));
                this.m_childNodeNum = 0;
            }
            if (attributes != null) {
                addAttributes(attributes);
            }
            this.m_elemContext = this.m_elemContext.push(str, str2, str3);
            this.m_isprevtext = false;
            if (this.m_tracer != null) {
                firePseudoAttributes();
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
        if (str.equals("[dtd]")) {
            this.m_inExternalDTD = true;
        }
        if (this.m_expandDTDEntities || this.m_inExternalDTD) {
            return;
        }
        if (!isInEntityRef()) {
            if (shouldFormatOutput()) {
                this.m_charactersBuffer.addEntityReference(str);
            } else {
                outputEntityReference(str);
            }
        }
        this.m_inEntityRef++;
    }

    public void startNonEscaping() throws SAXException {
        this.m_disableOutputEscapingStates.push(true);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public boolean startPrefixMapping(String str, String str2, boolean z) throws SAXException {
        int i;
        if (z) {
            flushPending();
            i = this.m_elemContext.m_currentElemDepth + 1;
        } else {
            i = this.m_elemContext.m_currentElemDepth;
        }
        boolean zPushNamespace = this.m_prefixMap.pushNamespace(str, str2, i);
        if (zPushNamespace) {
            if ("".equals(str)) {
                addAttributeAlways("http://www.w3.org/2000/xmlns/", "xmlns", "xmlns", "CDATA", str2, false);
                return zPushNamespace;
            }
            if (!"".equals(str2)) {
                addAttributeAlways("http://www.w3.org/2000/xmlns/", str, "xmlns:" + str, "CDATA", str2, false);
            }
        }
        return zPushNamespace;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
        try {
            DTDprolog();
            this.m_writer.write("<!ENTITY ");
            this.m_writer.write(str);
            this.m_writer.write(JdkXmlUtils.getDTDExternalDecl(str2, str3));
            this.m_writer.write(" NDATA ");
            this.m_writer.write(str4);
            this.m_writer.write(" >");
            this.m_writer.write(this.m_lineSep, 0, this.m_lineSepLen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAttrString(Writer writer, String str, String str2) throws SAXException, IOException {
        ToStream toStream;
        Writer writer2;
        int length = str.length();
        if (length > this.m_attrBuff.length) {
            this.m_attrBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_attrBuff, 0);
        char[] cArr = this.m_attrBuff;
        int iAccumDefaultEscape = 0;
        while (iAccumDefaultEscape < length) {
            char c = cArr[iAccumDefaultEscape];
            if (!this.escapingNotNeeded(c) || this.m_charInfo.isSpecialAttrChar(c)) {
                toStream = this;
                writer2 = writer;
                iAccumDefaultEscape = toStream.accumDefaultEscape(writer2, c, iAccumDefaultEscape, cArr, length, false, true);
            } else {
                writer.write(c);
                iAccumDefaultEscape++;
                toStream = this;
                writer2 = writer;
            }
            this = toStream;
            writer = writer2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004b  */
    /* JADX WARN: Code duplicated, block: B:25:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    public void writeNormalizedChars(char[] cArr, int i, int i2, boolean z, boolean z2) throws SAXException, IOException {
        ToStream toStream;
        char[] cArr2;
        Writer writer = this.m_writer;
        int i3 = i + i2;
        int iHandleEscaping = i;
        while (iHandleEscaping < i3) {
            char c = cArr[iHandleEscaping];
            if ('\n' == c && z2) {
                writer.write(this.m_lineSep, 0, this.m_lineSepLen);
                toStream = this;
                cArr2 = cArr;
            } else if (!z || this.escapingNotNeeded(c)) {
                toStream = this;
                cArr2 = cArr;
                if (z && iHandleEscaping < i3 - 2 && ']' == c && ']' == cArr2[iHandleEscaping + 1]) {
                    int i4 = iHandleEscaping + 2;
                    if ('>' == cArr2[i4]) {
                        writer.write(SerializerConstants.CDATA_CONTINUE);
                        iHandleEscaping = i4;
                    } else if (toStream.escapingNotNeeded(c)) {
                        if (z) {
                            writer.write("<![CDATA[");
                            toStream.m_cdataTagOpen = true;
                        }
                        writer.write(c);
                    } else {
                        iHandleEscaping = toStream.handleEscaping(writer, c, cArr2, iHandleEscaping, i3);
                    }
                } else if (toStream.escapingNotNeeded(c)) {
                    if (z && !toStream.m_cdataTagOpen) {
                        writer.write("<![CDATA[");
                        toStream.m_cdataTagOpen = true;
                    }
                    writer.write(c);
                } else {
                    iHandleEscaping = toStream.handleEscaping(writer, c, cArr2, iHandleEscaping, i3);
                }
            } else {
                toStream = this;
                cArr2 = cArr;
                iHandleEscaping = toStream.handleEscaping(writer, c, cArr2, iHandleEscaping, i3);
            }
            iHandleEscaping++;
            this = toStream;
            cArr = cArr2;
        }
    }

    public int writeUTF16Surrogate(char c, char[] cArr, int i, int i2) throws SAXException, IOException {
        int i3 = i + 1;
        int i4 = -1;
        if (i3 >= i2) {
            this.m_highSurrogate = c;
            return -1;
        }
        char c2 = this.m_highSurrogate;
        if (c2 == 0) {
            c2 = c;
            c = cArr[i3];
            i4 = 0;
        } else {
            this.m_highSurrogate = (char) 0;
        }
        if (!Encodings.isLowUTF16Surrogate(c)) {
            throwIOE(c2, c);
        }
        Writer writer = this.m_writer;
        if (this.m_encodingInfo.isInEncoding(c2, c)) {
            writer.write(new char[]{c2, c}, 0, 2);
            return i4;
        }
        if (getEncoding() != null) {
            return writeCharRef(writer, c2, c);
        }
        writer.write(new char[]{c2, c}, 0, 2);
        return i4;
    }

    public class WritertoStringBuffer extends Writer {
        private final StringBuffer m_stringbuf;

        public WritertoStringBuffer(StringBuffer stringBuffer) {
            this.m_stringbuf = stringBuffer;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
        }

        @Override // java.io.Writer
        public void write(int i) {
            this.m_stringbuf.append((char) i);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            this.m_stringbuf.append(cArr, i, i2);
        }

        @Override // java.io.Writer
        public void write(String str) {
            this.m_stringbuf.append(str);
        }
    }

    public static final class BoolStack {
        private int m_allocatedSize;
        private int m_index;
        private boolean[] m_values;

        public BoolStack(int i) {
            this.m_allocatedSize = i;
            this.m_values = new boolean[i];
            this.m_index = -1;
        }

        private void grow() {
            int i = this.m_allocatedSize * 2;
            this.m_allocatedSize = i;
            boolean[] zArr = new boolean[i];
            System.arraycopy(this.m_values, 0, zArr, 0, this.m_index + 1);
            this.m_values = zArr;
        }

        public final void clear() {
            this.m_index = -1;
        }

        public boolean isEmpty() {
            return this.m_index == -1;
        }

        public final boolean peek() {
            return this.m_values[this.m_index];
        }

        public final boolean peekOrFalse() {
            int i = this.m_index;
            if (i > -1) {
                return this.m_values[i];
            }
            return false;
        }

        public final boolean peekOrTrue() {
            int i = this.m_index;
            if (i > -1) {
                return this.m_values[i];
            }
            return true;
        }

        public final boolean pop() {
            boolean[] zArr = this.m_values;
            int i = this.m_index;
            this.m_index = i - 1;
            return zArr[i];
        }

        public final boolean popAndTop() {
            int i = this.m_index - 1;
            this.m_index = i;
            if (i >= 0) {
                return this.m_values[i];
            }
            return false;
        }

        public final boolean push(boolean z) {
            if (this.m_index == this.m_allocatedSize - 1) {
                grow();
            }
            boolean[] zArr = this.m_values;
            int i = this.m_index + 1;
            this.m_index = i;
            zArr[i] = z;
            return z;
        }

        public final void setTop(boolean z) {
            this.m_values[this.m_index] = z;
        }

        public final int size() {
            return this.m_index + 1;
        }

        public BoolStack() {
            this(32);
        }
    }

    public void indent() throws IOException {
        indent(this.m_elemContext.m_currentElemDepth);
    }

    private void writeCharRef(Writer writer, char c) throws SAXException, IOException {
        if (this.m_cdataTagOpen) {
            closeCDATA();
        }
        writer.write("&#");
        writer.write(Integer.toString(c));
        writer.write(59);
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        startPrefixMapping(str, str2, true);
    }

    public ToStream() {
        this(null);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setCdataSectionElements(List<String> list) {
        int size;
        if (list != null && (size = list.size() - 1) > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i += 2) {
                if (i != 0) {
                    sb.append(' ');
                }
                String str = list.get(i);
                String str2 = list.get(i + 1);
                if (str != null) {
                    sb.append('{');
                    sb.append(str);
                    sb.append('}');
                }
                sb.append(str2);
            }
            this.m_StringOfCDATASections = sb.toString();
        }
        initCdataElems(this.m_StringOfCDATASections);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(String str) throws SAXException {
        if (!isInEntityRef() || this.m_expandDTDEntities) {
            int length = str.length();
            if (length > this.m_charsBuff.length) {
                this.m_charsBuff = new char[(length * 2) + 1];
            }
            str.getChars(0, length, this.m_charsBuff, 0);
            characters(this.m_charsBuff, 0, length);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        startElement(str, str2, str3, null);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str) throws SAXException {
        startElement(null, null, str, null);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void endElement(String str) throws SAXException {
        endElement(null, null, str);
    }
}
