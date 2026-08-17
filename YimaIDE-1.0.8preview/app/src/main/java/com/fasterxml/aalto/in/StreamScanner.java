package com.fasterxml.aalto.in;

import com.fasterxml.aalto.impl.ErrorConsts;
import com.fasterxml.aalto.impl.IoStreamException;
import com.fasterxml.aalto.util.DataUtil;
import com.fasterxml.aalto.util.TextBuilder;
import com.fasterxml.aalto.util.XmlCharTypes;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class StreamScanner extends ByteBasedScanner {
    protected final XmlCharTypes _charTypes;
    protected InputStream _in;
    protected byte[] _inputBuffer;
    protected int[] _quadBuffer;
    protected final ByteBasedPNameTable _symbols;

    public StreamScanner(ReaderConfig readerConfig, InputStream inputStream, byte[] bArr, int i, int i2) {
        super(readerConfig);
        this._quadBuffer = new int[32];
        this._charTypes = readerConfig.getCharTypes();
        this._symbols = readerConfig.getBBSymbols();
        this._in = inputStream;
        this._inputBuffer = bArr;
        this._inputPtr = i;
        this._inputEnd = i2;
    }

    private final PName findPName(int i, int[] iArr, int i2, int i3) throws XMLStreamException {
        this._inputPtr--;
        if (i2 >= iArr.length) {
            iArr = DataUtil.growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i2 + 1;
        iArr[i2] = i;
        int iCalcHash = ByteBasedPNameTable.calcHash(iArr, i4);
        ByteBasedPName byteBasedPNameFindSymbol = this._symbols.findSymbol(iCalcHash, iArr, i4);
        return byteBasedPNameFindSymbol == null ? addPName(iCalcHash, iArr, i4, i3) : byteBasedPNameFindSymbol;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int handleCommentOrCdataStart() throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        byte b = bArr[i];
        if (b == 45) {
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b2 = bArr2[i3];
            if (b2 != 45) {
                reportTreeUnexpChar(decodeCharForError(b2), " (expected '-' for COMMENT)");
            }
            if (this._cfgLazyParsing) {
                this._tokenIncomplete = true;
            } else {
                finishComment();
            }
            this._currToken = 5;
            return 5;
        }
        if (b != 91) {
            reportTreeUnexpChar(decodeCharForError(b), " (expected either '-' for COMMENT or '[CDATA[' for CDATA section)");
            return -1;
        }
        this._currToken = 12;
        for (int i4 = 0; i4 < 6; i4++) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr3 = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            byte b3 = bArr3[i5];
            if (b3 != ((byte) "CDATA[".charAt(i4))) {
                reportTreeUnexpChar(decodeCharForError(b3), " (expected '" + "CDATA[".charAt(i4) + "' for CDATA section)");
            }
        }
        if (this._cfgLazyParsing) {
            this._tokenIncomplete = true;
        } else {
            finishCData();
        }
        return 12;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int handleDtdStart() throws XMLStreamException {
        matchAsciiKeyword("DOCTYPE");
        this._tokenName = parsePName(skipInternalWs(true, "after DOCTYPE keyword, before root name"));
        byte bSkipInternalWs = skipInternalWs(false, null);
        if (bSkipInternalWs == 80) {
            matchAsciiKeyword("PUBLIC");
            this._publicId = parsePublicId(skipInternalWs(true, null));
            this._systemId = parseSystemId(skipInternalWs(true, null));
            bSkipInternalWs = skipInternalWs(false, null);
        } else if (bSkipInternalWs == 83) {
            matchAsciiKeyword("SYSTEM");
            byte bSkipInternalWs2 = skipInternalWs(true, null);
            this._publicId = null;
            this._systemId = parseSystemId(bSkipInternalWs2);
            bSkipInternalWs = skipInternalWs(false, null);
        } else {
            this._systemId = null;
            this._publicId = null;
        }
        if (bSkipInternalWs == 62) {
            this._tokenIncomplete = false;
            this._currToken = 11;
            return 11;
        }
        if (bSkipInternalWs != 91) {
            reportTreeUnexpChar(decodeCharForError(bSkipInternalWs), this._systemId != null ? " (expected '[' for the internal subset, or '>' to end DOCTYPE declaration)" : " (expected a 'PUBLIC' or 'SYSTEM' keyword, '[' for the internal subset, or '>' to end DOCTYPE declaration)");
        }
        this._tokenIncomplete = true;
        this._currToken = 11;
        return 11;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int handleEndElementSlow(int i) throws XMLStreamException {
        byte bLoadOne;
        int i2 = i - 1;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < 4; i6++) {
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                i5 = (i5 << 8) | (bArr[i7] & 255);
            }
            if (i5 != this._tokenName.getQuad(i4)) {
                reportUnexpectedEndTag(this._tokenName.getPrefixedName());
            }
        }
        int quad = this._tokenName.getQuad(i2);
        int i8 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i9 = this._inputPtr;
            this._inputPtr = i9 + 1;
            i3 = (i3 << 8) | (bArr2[i9] & 255);
            if (i3 == quad) {
                break;
            }
            i8++;
            if (i8 > 3) {
                reportUnexpectedEndTag(this._tokenName.getPrefixedName());
                break;
            }
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i10 = this._inputPtr;
        this._inputPtr = i10 + 1;
        int i11 = bArr3[i10];
        while (i11 <= 32) {
            if (i11 == 10) {
                markLF();
            } else if (i11 == 13) {
                int i12 = this._inputPtr;
                if (i12 < this._inputEnd) {
                    byte[] bArr4 = this._inputBuffer;
                    this._inputPtr = i12 + 1;
                    bLoadOne = bArr4[i12];
                } else {
                    bLoadOne = loadOne();
                }
                if (bLoadOne != 10) {
                    markLF(this._inputPtr - 1);
                } else {
                    markLF();
                }
                i11 = bLoadOne & 255;
            } else if (i11 != 32 && i11 != 9) {
                throwInvalidSpace(i11);
            }
            int i13 = this._inputPtr;
            if (i13 < this._inputEnd) {
                byte[] bArr5 = this._inputBuffer;
                this._inputPtr = i13 + 1;
                bLoadOne = bArr5[i13];
            } else {
                bLoadOne = loadOne();
            }
            i11 = bLoadOne & 255;
        }
        if (i11 == 62) {
            return 2;
        }
        throwUnexpectedChar(decodeCharForError((byte) i11), " expected space or closing '>'");
        return 2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int handlePIStart() throws XMLStreamException {
        this._currToken = 3;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        PName pName = parsePName(bArr[i]);
        this._tokenName = pName;
        String localName = pName.getLocalName();
        if (localName.length() == 3 && localName.equalsIgnoreCase("xml") && this._tokenName.getPrefix() == null) {
            reportInputProblem(ErrorConsts.ERR_WF_PI_XML_TARGET);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int i3 = bArr2[i2] & 255;
        if (i3 <= 32) {
            while (true) {
                if (i3 == 10) {
                    markLF();
                } else if (i3 == 13) {
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr3 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    if (bArr3[i4] == 10) {
                        this._inputPtr = i4 + 1;
                    }
                    markLF();
                } else if (i3 != 32 && i3 != 9) {
                    throwInvalidSpace(i3);
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr4 = this._inputBuffer;
                int i5 = this._inputPtr;
                i3 = bArr4[i5] & 255;
                if (i3 > 32) {
                    break;
                }
                this._inputPtr = i5 + 1;
            }
            if (this._cfgLazyParsing) {
                this._tokenIncomplete = true;
            } else {
                finishPI();
            }
        } else {
            if (i3 != 63) {
                reportMissingPISpace(decodeCharForError((byte) i3));
            }
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr5 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            byte b = bArr5[i6];
            if (b != 62) {
                reportMissingPISpace(decodeCharForError(b));
            }
            this._textBuilder.resetWithEmpty();
            this._tokenIncomplete = false;
        }
        return 3;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int handlePrologDeclStart(boolean z) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        byte b = bArr[i];
        if (b == 45) {
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            b = bArr2[i3];
            if (b == 45) {
                if (this._cfgLazyParsing) {
                    this._tokenIncomplete = true;
                } else {
                    finishComment();
                }
                this._currToken = 5;
                return 5;
            }
        } else if (b == 68 && z) {
            handleDtdStart();
            if (this._cfgLazyParsing || !this._tokenIncomplete) {
                return 11;
            }
            finishDTD(true);
            this._tokenIncomplete = false;
            return 11;
        }
        this._tokenIncomplete = true;
        this._currToken = 4;
        reportPrologUnexpChar(z, decodeCharForError(b), " (expected '-' for COMMENT)");
        return this._currToken;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final void matchAsciiKeyword(String str) throws XMLStreamException {
        int length = str.length();
        for (int i = 1; i < length; i++) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            byte b = bArr[i2];
            if (b != ((byte) str.charAt(i))) {
                reportTreeUnexpChar(decodeCharForError(b), " (expected '" + str.charAt(i) + "' for " + str + " keyword)");
            }
        }
    }

    @Override // com.fasterxml.aalto.in.ByteBasedScanner, com.fasterxml.aalto.in.XmlScanner
    public void _closeSource() throws IOException {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            inputStream.close();
            this._in = null;
        }
    }

    public int _nextEntity() {
        this._textBuilder.resetWithEmpty();
        this._currToken = 9;
        return 9;
    }

    @Override // com.fasterxml.aalto.in.XmlScanner
    public void _releaseBuffers() {
        byte[] bArr;
        super._releaseBuffers();
        if (this._symbols.maybeDirty()) {
            this._config.updateBBSymbols(this._symbols);
        }
        if (this._in == null || (bArr = this._inputBuffer) == null) {
            return;
        }
        this._config.freeFullBBuffer(bArr);
        this._inputBuffer = null;
    }

    public final PName addPName(int i, int[] iArr, int i2, int i3) throws XMLStreamException {
        return addUTFPName(this._symbols, this._charTypes, i, iArr, i2, i3);
    }

    public final int checkInTreeIndentation(int i) throws XMLStreamException {
        if (i == 13) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                this._textBuilder.resetWithIndentation(0, ' ');
                return -1;
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            if (bArr[i2] == 10) {
                this._inputPtr = i2 + 1;
            }
        }
        markLF();
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        byte b = bArr2[i3];
        if (b != 32 && b != 9) {
            if (b == 60 && i3 + 1 < this._inputEnd && bArr2[i3 + 1] != 33) {
                this._textBuilder.resetWithIndentation(0, ' ');
                return -1;
            }
            this._textBuilder.resetWithEmpty()[0] = '\n';
            this._textBuilder.setCurrentLength(1);
            return 1;
        }
        this._inputPtr = i3 + 1;
        int i4 = b != 32 ? 8 : 32;
        int i5 = 1;
        while (i5 <= i4) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr3 = this._inputBuffer;
            int i6 = this._inputPtr;
            byte b2 = bArr3[i6];
            if (b2 != b) {
                if (b2 != 60 || i6 + 1 >= this._inputEnd || bArr3[i6 + 1] == 33) {
                    break;
                    break;
                    break;
                }
                this._textBuilder.resetWithIndentation(i5, (char) b);
                return -1;
            }
            this._inputPtr = i6 + 1;
            i5++;
        }
        char[] cArrResetWithEmpty = this._textBuilder.resetWithEmpty();
        cArrResetWithEmpty[0] = '\n';
        char c = (char) b;
        for (int i7 = 1; i7 <= i5; i7++) {
            cArrResetWithEmpty[i7] = c;
        }
        int i8 = i5 + 1;
        this._textBuilder.setCurrentLength(i8);
        return i8;
    }

    public final int checkPrologIndentation(int i) throws XMLStreamException {
        if (i == 13) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                this._textBuilder.resetWithIndentation(0, ' ');
                return -1;
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            if (bArr[i2] == 10) {
                this._inputPtr = i2 + 1;
            }
        }
        markLF();
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            this._textBuilder.resetWithIndentation(0, ' ');
            return -1;
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        byte b = bArr2[i3];
        if (b != 32 && b != 9) {
            TextBuilder textBuilder = this._textBuilder;
            if (b == 60) {
                textBuilder.resetWithIndentation(0, ' ');
                return -1;
            }
            textBuilder.resetWithEmpty()[0] = '\n';
            this._textBuilder.setCurrentLength(1);
            return 1;
        }
        this._inputPtr = i3 + 1;
        int i4 = b != 32 ? 8 : 32;
        int i5 = 1;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            byte[] bArr3 = this._inputBuffer;
            int i6 = this._inputPtr;
            if (bArr3[i6] != b) {
                break;
            }
            this._inputPtr = i6 + 1;
            int i7 = i5 + 1;
            if (i7 >= i4) {
                char[] cArrResetWithEmpty = this._textBuilder.resetWithEmpty();
                cArrResetWithEmpty[0] = '\n';
                char c = (char) b;
                for (int i8 = 1; i8 <= i7; i8++) {
                    cArrResetWithEmpty[i8] = c;
                }
                int i9 = i5 + 2;
                this._textBuilder.setCurrentLength(i9);
                return i9;
            }
            i5 = i7;
        }
        this._textBuilder.resetWithIndentation(i5, (char) b);
        return -1;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:46:0x0064 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x0021 A[SYNTHETIC] */
    public final int handleCharEntity() throws XMLStreamException {
        int i;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        int i3 = 0;
        if (b == 120) {
            while (true) {
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b2 = bArr2[i4];
                if (b2 == 59) {
                    break;
                }
                int i5 = i3 << 4;
                if (b2 <= 57 && b2 >= 48) {
                    i = b2 - 48;
                } else if (b2 < 97 || b2 > 102) {
                    if (b2 < 65 || b2 > 70) {
                        throwUnexpectedChar(decodeCharForError(b2), "; expected a hex digit (0-9a-fA-F)");
                    } else {
                        i = b2 - 55;
                    }
                    i3 = i5;
                    if (i3 > 1114111) {
                        reportEntityOverflow();
                    }
                } else {
                    i = b2 - 87;
                }
                i5 += i;
                i3 = i5;
                if (i3 > 1114111) {
                    reportEntityOverflow();
                }
            }
        } else {
            while (b != 59) {
                if (b > 57 || b < 48) {
                    throwUnexpectedChar(decodeCharForError(b), "; expected a decimal number");
                } else {
                    i3 = (i3 * 10) + (b - 48);
                    if (i3 > 1114111) {
                        reportEntityOverflow();
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                b = bArr3[i6];
            }
        }
        verifyXmlChar(i3);
        return i3;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final int handleEndElement() throws XMLStreamException {
        byte bLoadOne;
        this._depth--;
        this._currToken = 2;
        PName name = this._currElem.getName();
        this._tokenName = name;
        int iSizeInQuads = name.sizeInQuads();
        int i = this._inputEnd;
        int i2 = this._inputPtr;
        if (i - i2 < (iSizeInQuads << 2) + 1) {
            return handleEndElementSlow(iSizeInQuads);
        }
        byte[] bArr = this._inputBuffer;
        int i3 = iSizeInQuads - 1;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (bArr[i2] << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8) | (bArr[i2 + 3] & 255);
            i2 += 4;
            if (i5 != this._tokenName.getQuad(i4)) {
                this._inputPtr = i2;
                reportUnexpectedEndTag(this._tokenName.getPrefixedName());
            }
        }
        int quad = this._tokenName.getQuad(i3);
        int i6 = i2 + 1;
        int i7 = bArr[i2] & 255;
        if (i7 != quad) {
            int i8 = i2 + 2;
            int i9 = (bArr[i6] & 255) | (i7 << 8);
            if (i9 != quad) {
                int i10 = i2 + 3;
                int i11 = (i9 << 8) | (bArr[i8] & 255);
                if (i11 != quad) {
                    int i12 = i2 + 4;
                    if (((bArr[i10] & 255) | (i11 << 8)) != quad) {
                        this._inputPtr = i12;
                        reportUnexpectedEndTag(this._tokenName.getPrefixedName());
                    }
                    i6 = i12;
                } else {
                    i6 = i10;
                }
            } else {
                i6 = i8;
            }
        }
        int i13 = this._inputBuffer[i6] & 255;
        this._inputPtr = i6 + 1;
        while (i13 <= 32) {
            if (i13 == 10) {
                markLF();
            } else if (i13 == 13) {
                int i14 = this._inputPtr;
                if (i14 < this._inputEnd) {
                    byte[] bArr2 = this._inputBuffer;
                    this._inputPtr = i14 + 1;
                    bLoadOne = bArr2[i14];
                } else {
                    bLoadOne = loadOne();
                }
                if (bLoadOne != 10) {
                    markLF(this._inputPtr - 1);
                } else {
                    markLF();
                }
                i13 = bLoadOne & 255;
            } else if (i13 != 32 && i13 != 9) {
                throwInvalidSpace(i13);
            }
            int i15 = this._inputPtr;
            if (i15 < this._inputEnd) {
                byte[] bArr3 = this._inputBuffer;
                this._inputPtr = i15 + 1;
                bLoadOne = bArr3[i15];
            } else {
                bLoadOne = loadOne();
            }
            i13 = bLoadOne & 255;
        }
        if (i13 != 62) {
            throwUnexpectedChar(decodeCharForError((byte) i13), " expected space or closing '>'");
        }
        return 2;
    }

    public abstract int handleEntityInText(boolean z) throws XMLStreamException;

    public abstract int handleStartElement(byte b) throws XMLStreamException;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final boolean loadAndRetain(int i) throws XMLStreamException {
        int i2;
        if (this._in == null) {
            return false;
        }
        long j = this._pastBytesOrChars;
        int i3 = this._inputPtr;
        this._pastBytesOrChars = j + ((long) i3);
        this._rowStartOffset -= i3;
        int i4 = this._inputEnd - i3;
        byte[] bArr = this._inputBuffer;
        System.arraycopy(bArr, i3, bArr, 0, i4);
        this._inputPtr = 0;
        this._inputEnd = i4;
        do {
            try {
                byte[] bArr2 = this._inputBuffer;
                int length = bArr2.length;
                int i5 = this._inputEnd;
                int i6 = length - i5;
                int i7 = this._in.read(bArr2, i5, i6);
                if (i7 < 1) {
                    if (i7 == 0) {
                        reportInputProblem("InputStream returned 0 bytes, even when asked to read up to " + i6);
                    }
                    return false;
                }
                i2 = this._inputEnd + i7;
                this._inputEnd = i2;
            } catch (IOException e) {
                throw new IoStreamException(e);
            }
        } while (i2 < i);
        return true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final boolean loadMore() throws XMLStreamException {
        long j = this._pastBytesOrChars;
        int i = this._inputEnd;
        this._pastBytesOrChars = j + ((long) i);
        this._rowStartOffset -= i;
        this._inputPtr = 0;
        InputStream inputStream = this._in;
        if (inputStream == null) {
            this._inputEnd = 0;
            return false;
        }
        try {
            byte[] bArr = this._inputBuffer;
            int i2 = inputStream.read(bArr, 0, bArr.length);
            if (i2 >= 1) {
                this._inputEnd = i2;
                return true;
            }
            this._inputEnd = 0;
            if (i2 == 0) {
                reportInputProblem("InputStream returned 0 bytes, even when asked to read up to " + this._inputBuffer.length);
            }
            return false;
        } catch (IOException e) {
            throw new IoStreamException(e);
        }
    }

    public final byte loadOne() throws XMLStreamException {
        if (!loadMore()) {
            reportInputProblem("Unexpected end-of-input when trying to parse " + ErrorConsts.tokenTypeDesc(this._currToken));
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i];
    }

    public final byte nextByte() throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            reportInputProblem("Unexpected end-of-input when trying to parse " + ErrorConsts.tokenTypeDesc(this._currToken));
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i];
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final int nextFromProlog(boolean z) throws XMLStreamException {
        if (this._tokenIncomplete) {
            skipToken();
        }
        setStartLocation();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                setStartLocation();
                return -1;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            int i2 = i + 1;
            this._inputPtr = i2;
            int i3 = bArr[i] & 255;
            if (i3 == 60) {
                if (i2 >= this._inputEnd) {
                    loadMoreGuaranteed(5);
                }
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b = bArr2[i4];
                if (b == 33) {
                    return handlePrologDeclStart(z);
                }
                if (b == 63) {
                    return handlePIStart();
                }
                if (b == 47 || !z) {
                    reportPrologUnexpElement(z, b);
                }
                return handleStartElement(b);
            }
            if (i3 != 32) {
                if (i3 == 10) {
                    markLF();
                } else if (i3 == 13) {
                    if (i2 >= this._inputEnd && !loadMore()) {
                        markLF();
                        setStartLocation();
                        return -1;
                    }
                    byte[] bArr3 = this._inputBuffer;
                    int i5 = this._inputPtr;
                    if (bArr3[i5] == 10) {
                        this._inputPtr = i5 + 1;
                    }
                    markLF();
                } else if (i3 != 9) {
                    reportPrologUnexpChar(z, decodeCharForError((byte) i3), null);
                }
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final int nextFromTree() throws XMLStreamException {
        byte bLoadOne;
        if (!this._tokenIncomplete) {
            int i = this._currToken;
            if (i == 1) {
                if (this._isEmptyTag) {
                    this._depth--;
                    this._currToken = 2;
                    return 2;
                }
            } else if (i == 2) {
                this._currElem = this._currElem.getParent();
                while (true) {
                    NsDeclaration nsDeclaration = this._lastNsDecl;
                    if (nsDeclaration == null || nsDeclaration.getLevel() < this._depth) {
                        break;
                    }
                    this._lastNsDecl = this._lastNsDecl.unbind();
                }
            } else if (this._entityPending) {
                this._entityPending = false;
                return _nextEntity();
            }
        } else if (skipToken()) {
            return _nextEntity();
        }
        setStartLocation();
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            setStartLocation();
            return -1;
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        byte b = bArr[i2];
        if (b == 60) {
            int i3 = i2 + 1;
            this._inputPtr = i3;
            if (i3 < this._inputEnd) {
                this._inputPtr = i2 + 2;
                bLoadOne = bArr[i3];
            } else {
                bLoadOne = loadOne(5);
            }
            if (bLoadOne == 33) {
                return handleCommentOrCdataStart();
            }
            if (bLoadOne == 63) {
                return handlePIStart();
            }
            return bLoadOne == 47 ? handleEndElement() : handleStartElement(bLoadOne);
        }
        if (b == 38) {
            this._inputPtr = i2 + 1;
            int iHandleEntityInText = handleEntityInText(false);
            if (iHandleEntityInText == 0) {
                this._currToken = 9;
                return 9;
            }
            this._tmpChar = -iHandleEntityInText;
        } else {
            this._tmpChar = b & 255;
        }
        if (this._cfgLazyParsing) {
            this._tokenIncomplete = true;
        } else {
            finishCharacters();
        }
        this._currToken = 4;
        return 4;
    }

    public final PName parsePName(byte b) throws XMLStreamException {
        if (this._inputEnd - this._inputPtr < 8) {
            return parsePNameSlow(b);
        }
        int i = b & 255;
        if (i < 65) {
            throwUnexpectedChar(i, "; expected a name start character");
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        int i3 = i2 + 1;
        this._inputPtr = i3;
        int i4 = bArr[i2] & 255;
        if (i4 < 65 && (i4 < 45 || i4 > 58 || i4 == 47)) {
            return findPName(i, 1);
        }
        int i5 = (i << 8) | i4;
        int i6 = i2 + 2;
        this._inputPtr = i6;
        int i7 = bArr[i3] & 255;
        if (i7 < 65 && (i7 < 45 || i7 > 58 || i7 == 47)) {
            return findPName(i5, 2);
        }
        int i8 = (i5 << 8) | i7;
        int i9 = i2 + 3;
        this._inputPtr = i9;
        int i10 = bArr[i6] & 255;
        if (i10 < 65 && (i10 < 45 || i10 > 58 || i10 == 47)) {
            return findPName(i8, 3);
        }
        int i11 = (i8 << 8) | i10;
        this._inputPtr = i2 + 4;
        int i12 = bArr[i9] & 255;
        return (i12 >= 65 || (i12 >= 45 && i12 <= 58 && i12 != 47)) ? parsePNameMedium(i12, i11) : findPName(i11, 4);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final PName parsePNameLong(int i, int[] iArr) throws XMLStreamException {
        byte bLoadOne;
        byte bLoadOne2;
        byte bLoadOne3;
        int i2 = 2;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            int i5 = bArr[i3] & 255;
            if (i5 < 65 && (i5 < 45 || i5 > 58 || i5 == 47)) {
                break;
            }
            int i6 = (i << 8) | i5;
            if (i4 < this._inputEnd) {
                this._inputPtr = i3 + 2;
                bLoadOne = bArr[i4];
            } else {
                bLoadOne = loadOne();
            }
            int i7 = bLoadOne & 255;
            if (i7 < 65 && (i7 < 45 || i7 > 58 || i7 == 47)) {
                return findPName(i6, iArr, i2, 2);
            }
            int i8 = (i6 << 8) | i7;
            int i9 = this._inputPtr;
            if (i9 < this._inputEnd) {
                byte[] bArr2 = this._inputBuffer;
                this._inputPtr = i9 + 1;
                bLoadOne2 = bArr2[i9];
            } else {
                bLoadOne2 = loadOne();
            }
            int i10 = bLoadOne2 & 255;
            if (i10 < 65 && (i10 < 45 || i10 > 58 || i10 == 47)) {
                return findPName(i8, iArr, i2, 3);
            }
            int i11 = (i8 << 8) | i10;
            int i12 = this._inputPtr;
            if (i12 < this._inputEnd) {
                byte[] bArr3 = this._inputBuffer;
                this._inputPtr = i12 + 1;
                bLoadOne3 = bArr3[i12];
            } else {
                bLoadOne3 = loadOne();
            }
            int i13 = bLoadOne3 & 255;
            if (i13 < 65 && (i13 < 45 || i13 > 58 || i13 == 47)) {
                return findPName(i11, iArr, i2, 4);
            }
            if (i2 >= iArr.length) {
                iArr = DataUtil.growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i2] = i11;
            i2++;
            i = i13;
        }
        return findPName(i, iArr, i2, 1);
    }

    public PName parsePNameMedium(int i, int i2) throws XMLStreamException {
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        int i5 = bArr[i3] & 255;
        if (i5 < 65 && (i5 < 45 || i5 > 58 || i5 == 47)) {
            return findPName(i2, i, 1);
        }
        int i6 = (i << 8) | i5;
        int i7 = i3 + 2;
        this._inputPtr = i7;
        int i8 = bArr[i4] & 255;
        if (i8 < 65 && (i8 < 45 || i8 > 58 || i8 == 47)) {
            return findPName(i2, i6, 2);
        }
        int i9 = (i6 << 8) | i8;
        int i10 = i3 + 3;
        this._inputPtr = i10;
        int i11 = bArr[i7] & 255;
        if (i11 < 65 && (i11 < 45 || i11 > 58 || i11 == 47)) {
            return findPName(i2, i9, 3);
        }
        int i12 = (i9 << 8) | i11;
        this._inputPtr = i3 + 4;
        int i13 = bArr[i10] & 255;
        if (i13 < 65 && (i13 < 45 || i13 > 58 || i13 == 47)) {
            return findPName(i2, i12, 4);
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i2;
        iArr[1] = i12;
        return parsePNameLong(i13, iArr);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final PName parsePNameSlow(byte b) throws XMLStreamException {
        byte bLoadOne;
        byte bLoadOne2;
        byte bLoadOne3;
        int i = b & 255;
        if (i < 65) {
            throwUnexpectedChar(i, "; expected a name start character");
        }
        int i2 = i;
        int[] iArrGrowArrayBy = this._quadBuffer;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                this.loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i5 = this._inputPtr;
            int i6 = i5 + 1;
            this._inputPtr = i6;
            int i7 = bArr[i5] & 255;
            if (i7 < 65 && (i7 < 45 || i7 > 58 || i7 == 47)) {
                break;
            }
            StreamScanner streamScanner = this;
            int i8 = i7 | (i2 << 8);
            if (i6 < streamScanner._inputEnd) {
                streamScanner._inputPtr = i5 + 2;
                bLoadOne = bArr[i6];
            } else {
                bLoadOne = streamScanner.loadOne();
            }
            int i9 = bLoadOne & 255;
            if (i9 < 65 && (i9 < 45 || i9 > 58 || i9 == 47)) {
                return streamScanner.findPName(i8, 2, i3, i4, iArrGrowArrayBy);
            }
            int i10 = (i8 << 8) | i9;
            int i11 = streamScanner._inputPtr;
            if (i11 < streamScanner._inputEnd) {
                byte[] bArr2 = streamScanner._inputBuffer;
                streamScanner._inputPtr = i11 + 1;
                bLoadOne2 = bArr2[i11];
            } else {
                bLoadOne2 = streamScanner.loadOne();
            }
            int i12 = bLoadOne2 & 255;
            if (i12 < 65 && (i12 < 45 || i12 > 58 || i12 == 47)) {
                return streamScanner.findPName(i10, 3, i3, i4, iArrGrowArrayBy);
            }
            int i13 = (i10 << 8) | i12;
            int i14 = streamScanner._inputPtr;
            if (i14 < streamScanner._inputEnd) {
                byte[] bArr3 = streamScanner._inputBuffer;
                streamScanner._inputPtr = i14 + 1;
                bLoadOne3 = bArr3[i14];
            } else {
                bLoadOne3 = streamScanner.loadOne();
            }
            i2 = bLoadOne3 & 255;
            if (i2 < 65 && (i2 < 45 || i2 > 58 || i2 == 47)) {
                return streamScanner.findPName(i13, 4, i3, i4, iArrGrowArrayBy);
            }
            if (i4 == 0) {
                i3 = i13;
            } else if (i4 == 1) {
                iArrGrowArrayBy[0] = i3;
                iArrGrowArrayBy[1] = i13;
            } else {
                if (i4 >= iArrGrowArrayBy.length) {
                    iArrGrowArrayBy = DataUtil.growArrayBy(iArrGrowArrayBy, iArrGrowArrayBy.length);
                    streamScanner._quadBuffer = iArrGrowArrayBy;
                }
                iArrGrowArrayBy[i4] = i13;
            }
            i4++;
            this = streamScanner;
        }
        return this.findPName(i2, 1, i3, i4, iArrGrowArrayBy);
    }

    public abstract String parsePublicId(byte b) throws XMLStreamException;

    public abstract String parseSystemId(byte b) throws XMLStreamException;

    public byte skipInternalWs(boolean z, String str) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if ((b & 255) > 32) {
            if (!z) {
                return b;
            }
            reportTreeUnexpChar(decodeCharForError(b), " (expected white space " + str + ")");
        }
        do {
            if (b == 10) {
                markLF();
            } else if (b == 13) {
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                if (bArr2[i2] == 10) {
                    this._inputPtr = i2 + 1;
                }
                markLF();
            } else if (b != 32 && b != 9) {
                throwInvalidSpace(b);
            }
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr3 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            b = bArr3[i3];
        } while ((b & 255) <= 32);
        return b;
    }

    private final PName findPName(int i, int i2, int i3) throws XMLStreamException {
        this._inputPtr--;
        int iCalcHash = ByteBasedPNameTable.calcHash(i, i2);
        ByteBasedPName byteBasedPNameFindSymbol = this._symbols.findSymbol(iCalcHash, i, i2);
        if (byteBasedPNameFindSymbol != null) {
            return byteBasedPNameFindSymbol;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        return addPName(iCalcHash, iArr, 2, i3);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final byte loadOne(int i) throws XMLStreamException {
        if (!loadMore()) {
            reportInputProblem("Unexpected end-of-input when trying to parse " + ErrorConsts.tokenTypeDesc(i));
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        return bArr[i2];
    }

    private final PName findPName(int i, int i2) throws XMLStreamException {
        this._inputPtr--;
        int iCalcHash = ByteBasedPNameTable.calcHash(i);
        ByteBasedPName byteBasedPNameFindSymbol = this._symbols.findSymbol(iCalcHash, i, 0);
        if (byteBasedPNameFindSymbol != null) {
            return byteBasedPNameFindSymbol;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return addPName(iCalcHash, iArr, 1, i2);
    }

    private final PName findPName(int i, int i2, int i3, int i4, int[] iArr) throws XMLStreamException {
        if (i4 > 1) {
            return findPName(i, iArr, i4, i2);
        }
        if (i4 == 0) {
            return findPName(i, i2);
        }
        return findPName(i3, i, i2);
    }
}
