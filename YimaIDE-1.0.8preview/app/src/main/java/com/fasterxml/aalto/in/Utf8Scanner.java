package com.fasterxml.aalto.in;

import com.android.apksig.internal.util.AndroidSdkVersion;
import com.fasterxml.aalto.impl.ErrorConsts;
import com.fasterxml.aalto.util.DataUtil;
import com.fasterxml.aalto.util.XmlCharTypes;
import com.fasterxml.aalto.util.XmlChars;
import java.io.InputStream;
import javax.xml.stream.XMLStreamException;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Utf8Scanner extends StreamScanner {
    public Utf8Scanner(ReaderConfig readerConfig, InputStream inputStream, byte[] bArr, int i, int i2) {
        super(readerConfig, inputStream, bArr, i, i2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:30:0x007a  */
    /* JADX WARN: Code duplicated, block: B:33:0x0081  */
    /* JADX WARN: Code duplicated, block: B:35:0x0092  */
    /* JADX WARN: Code duplicated, block: B:37:0x009c  */
    /* JADX WARN: Code duplicated, block: B:41:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:44:0x00b4  */
    private final int collectValue(int i, byte b, PName pName) throws XMLStreamException {
        byte[] bArr;
        int i2;
        int iHandleEntityInText;
        int i3;
        char[] cArrStartNewValue = this._attrCollector.startNewValue(pName, i);
        int[] iArr = this._charTypes.ATTR_CHARS;
        while (true) {
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                loadMoreGuaranteed();
                i4 = this._inputPtr;
            }
            if (i >= cArrStartNewValue.length) {
                cArrStartNewValue = this._attrCollector.valueBufferFull();
            }
            int i5 = this._inputEnd;
            int length = (cArrStartNewValue.length - i) + i4;
            if (length < i5) {
                i5 = length;
            }
            while (true) {
                if (i4 >= i5) {
                    this._inputPtr = i4;
                    break;
                }
                int i6 = i4 + 1;
                int iDecodeUtf8_2 = this._inputBuffer[i4] & 255;
                int i7 = iArr[iDecodeUtf8_2];
                if (i7 != 0) {
                    this._inputPtr = i6;
                    if (i7 != 14) {
                        switch (i7) {
                            case 1:
                                handleInvalidXmlChar(iDecodeUtf8_2);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                bArr = this._inputBuffer;
                                i2 = this._inputPtr;
                                if (bArr[i2] == 10) {
                                    this._inputPtr = i2 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 32;
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                bArr = this._inputBuffer;
                                i2 = this._inputPtr;
                                if (bArr[i2] == 10) {
                                    this._inputPtr = i2 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 32;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                iDecodeUtf8_2 = 32;
                                break;
                            case 4:
                                reportInvalidInitial(iDecodeUtf8_2);
                                throwUnexpectedChar(iDecodeUtf8_2, "'<' not allowed in attribute value");
                                iHandleEntityInText = handleEntityInText(false);
                                if (iHandleEntityInText == 0) {
                                    reportUnexpandedEntityInAttr(pName, false);
                                }
                                if ((iHandleEntityInText >> 16) == 0) {
                                    iDecodeUtf8_2 = iHandleEntityInText;
                                } else {
                                    int i8 = iHandleEntityInText - 65536;
                                    i3 = i + 1;
                                    cArrStartNewValue[i] = (char) (55296 | (i8 >> 10));
                                    int i9 = (i8 & 1023) | 56320;
                                    if (i3 >= cArrStartNewValue.length) {
                                        cArrStartNewValue = this._attrCollector.valueBufferFull();
                                    }
                                    iDecodeUtf8_2 = i9;
                                    i = i3;
                                }
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                                break;
                            case 7:
                                int iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                                int i10 = i + 1;
                                cArrStartNewValue[i] = (char) (55296 | (iDecodeUtf8_4 >> 10));
                                iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                                if (i10 >= cArrStartNewValue.length) {
                                    cArrStartNewValue = this._attrCollector.valueBufferFull();
                                }
                                i = i10;
                                break;
                            case 8:
                                iDecodeUtf8_2 = 32;
                                break;
                            case 9:
                                throwUnexpectedChar(iDecodeUtf8_2, "'<' not allowed in attribute value");
                                iHandleEntityInText = handleEntityInText(false);
                                if (iHandleEntityInText == 0) {
                                    reportUnexpandedEntityInAttr(pName, false);
                                }
                                if ((iHandleEntityInText >> 16) == 0) {
                                    iDecodeUtf8_2 = iHandleEntityInText;
                                } else {
                                    int i11 = iHandleEntityInText - 65536;
                                    i3 = i + 1;
                                    cArrStartNewValue[i] = (char) (55296 | (i11 >> 10));
                                    int i12 = (i11 & 1023) | 56320;
                                    if (i3 >= cArrStartNewValue.length) {
                                        cArrStartNewValue = this._attrCollector.valueBufferFull();
                                    }
                                    iDecodeUtf8_2 = i12;
                                    i = i3;
                                }
                                break;
                            case XmlPullParser.DOCDECL /* 10 */:
                                iHandleEntityInText = handleEntityInText(false);
                                if (iHandleEntityInText == 0) {
                                    reportUnexpandedEntityInAttr(pName, false);
                                }
                                if ((iHandleEntityInText >> 16) == 0) {
                                    iDecodeUtf8_2 = iHandleEntityInText;
                                } else {
                                    int i13 = iHandleEntityInText - 65536;
                                    i3 = i + 1;
                                    cArrStartNewValue[i] = (char) (55296 | (i13 >> 10));
                                    int i14 = (i13 & 1023) | 56320;
                                    if (i3 >= cArrStartNewValue.length) {
                                        cArrStartNewValue = this._attrCollector.valueBufferFull();
                                    }
                                    iDecodeUtf8_2 = i14;
                                    i = i3;
                                }
                                break;
                        }
                    } else if (iDecodeUtf8_2 == b) {
                        return i;
                    }
                    cArrStartNewValue[i] = (char) iDecodeUtf8_2;
                    i++;
                    break;
                }
                cArrStartNewValue[i] = (char) iDecodeUtf8_2;
                i++;
                i4 = i6;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:15:0x002a  */
    /* JADX WARN: Code duplicated, block: B:18:0x003b  */
    /* JADX WARN: Code duplicated, block: B:21:0x0047  */
    /* JADX WARN: Code duplicated, block: B:23:0x004b  */
    /* JADX WARN: Code duplicated, block: B:26:0x005a  */
    /* JADX WARN: Code duplicated, block: B:29:0x0066  */
    /* JADX WARN: Code duplicated, block: B:31:0x006a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0079  */
    /* JADX WARN: Code duplicated, block: B:36:0x0085  */
    private final int decodeMultiByteChar(int i, int i2) throws XMLStreamException {
        char c;
        int i3;
        byte b;
        int i4;
        int i5;
        byte b2;
        byte b3;
        if ((i & 224) != 192) {
            if ((i & 240) == 224) {
                i &= 15;
                c = 2;
            } else if ((i & 248) == 240) {
                i &= 7;
                c = 3;
            } else {
                reportInvalidInitial(i & 255);
            }
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            i3 = i2 + 1;
            b = this._inputBuffer[i2];
            if ((b & 192) != 128) {
                reportInvalidOther(b & 255, i3);
            }
            i4 = (i << 6) | (b & 63);
            if (c > 1) {
                if (i3 >= this._inputEnd) {
                    loadMoreGuaranteed();
                    i3 = this._inputPtr;
                }
                i5 = i3 + 1;
                b2 = this._inputBuffer[i3];
                if ((b2 & 192) != 128) {
                    reportInvalidOther(b2 & 255, i5);
                }
                i4 = (i4 << 6) | (b2 & 63);
                if (c > 2) {
                    if (i5 >= this._inputEnd) {
                        loadMoreGuaranteed();
                        i5 = this._inputPtr;
                    }
                    i3 = i5 + 1;
                    b3 = this._inputBuffer[i5];
                    if ((b3 & 192) != 128) {
                        reportInvalidOther(b3 & 255, i3);
                    }
                    i4 = -((i4 << 6) | (b3 & 63));
                } else {
                    i3 = i5;
                }
            }
            this._inputPtr = i3;
            return i4;
        }
        i &= 31;
        c = 1;
        if (i2 >= this._inputEnd) {
            loadMoreGuaranteed();
            i2 = this._inputPtr;
        }
        i3 = i2 + 1;
        b = this._inputBuffer[i2];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i3);
        }
        i4 = (i << 6) | (b & 63);
        if (c > 1) {
            if (i3 >= this._inputEnd) {
                loadMoreGuaranteed();
                i3 = this._inputPtr;
            }
            i5 = i3 + 1;
            b2 = this._inputBuffer[i3];
            if ((b2 & 192) != 128) {
                reportInvalidOther(b2 & 255, i5);
            }
            i4 = (i4 << 6) | (b2 & 63);
            if (c > 2) {
                if (i5 >= this._inputEnd) {
                    loadMoreGuaranteed();
                    i5 = this._inputPtr;
                }
                i3 = i5 + 1;
                b3 = this._inputBuffer[i5];
                if ((b3 & 192) != 128) {
                    reportInvalidOther(b3 & 255, i3);
                }
                i4 = -((i4 << 6) | (b3 & 63));
            } else {
                i3 = i5;
            }
        }
        this._inputPtr = i3;
        return i4;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int decodeUtf8_2(int i) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        int i3 = i2 + 1;
        this._inputPtr = i3;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i3);
        }
        return ((i & 31) << 6) | (b & 63);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int decodeUtf8_3(int i) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i4);
        }
        int i5 = (b & 63) | (i2 << 6);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        this._inputPtr = i7;
        byte b2 = bArr2[i6];
        if ((b2 & 192) != 128) {
            reportInvalidOther(b2 & 255, i7);
        }
        int i8 = (i5 << 6) | (b2 & 63);
        return (i2 < 13 || i8 < 55296 || (i8 >= 57344 && (i8 < 65534 || i8 > 65535))) ? i8 : handleInvalidXmlChar(i8);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int decodeUtf8_3fast(int i) throws XMLStreamException {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        int i4 = i3 + 1;
        this._inputPtr = i4;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i4);
        }
        int i5 = (b & 63) | (i2 << 6);
        byte[] bArr2 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        this._inputPtr = i7;
        byte b2 = bArr2[i6];
        if ((b2 & 192) != 128) {
            reportInvalidOther(b2 & 255, i7);
        }
        int i8 = (i5 << 6) | (b2 & 63);
        return (i2 < 13 || i8 < 55296 || (i8 >= 57344 && (i8 < 65534 || i8 > 65535))) ? i8 : handleInvalidXmlChar(i8);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int decodeUtf8_4(int i) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        int i3 = i2 + 1;
        this._inputPtr = i3;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i3);
        }
        int i4 = ((i & 7) << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i5 = this._inputPtr;
        int i6 = i5 + 1;
        this._inputPtr = i6;
        byte b2 = bArr2[i5];
        if ((b2 & 192) != 128) {
            reportInvalidOther(b2 & 255, i6);
        }
        int i7 = (i4 << 6) | (b2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i8 = this._inputPtr;
        int i9 = i8 + 1;
        this._inputPtr = i9;
        byte b3 = bArr3[i8];
        if ((b3 & 192) != 128) {
            reportInvalidOther(b3 & 255, i9);
        }
        return ((i7 << 6) | (b3 & 63)) - 65536;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private void handleNsDeclaration(PName pName, byte b) throws XMLStreamException {
        int i;
        int i2;
        char[] cArrGrowArrayBy = this._nameBuffer;
        int i3 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i4 = this._inputPtr;
            int i5 = i4 + 1;
            this._inputPtr = i5;
            int iDecodeMultiByteChar = bArr[i4];
            if (iDecodeMultiByteChar == b) {
                break;
            }
            if (iDecodeMultiByteChar == 38) {
                iDecodeMultiByteChar = handleEntityInText(false);
                if (iDecodeMultiByteChar == 0) {
                    reportUnexpandedEntityInAttr(pName, true);
                }
                if ((iDecodeMultiByteChar >> 16) != 0) {
                    if (i3 >= cArrGrowArrayBy.length) {
                        cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                        this._nameBuffer = cArrGrowArrayBy;
                    }
                    i = iDecodeMultiByteChar - 65536;
                    i2 = i3 + 1;
                    cArrGrowArrayBy[i3] = (char) ((i >> 10) | 55296);
                    iDecodeMultiByteChar = (i & 1023) | 56320;
                    i3 = i2;
                }
            } else if (iDecodeMultiByteChar == 60) {
                throwUnexpectedChar(iDecodeMultiByteChar, "'<' not allowed in attribute value");
            } else {
                iDecodeMultiByteChar &= 255;
                if (iDecodeMultiByteChar < 32) {
                    if (iDecodeMultiByteChar == 10) {
                        markLF();
                    } else if (iDecodeMultiByteChar == 13) {
                        if (i5 >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr2 = this._inputBuffer;
                        int i6 = this._inputPtr;
                        if (bArr2[i6] == 10) {
                            this._inputPtr = i6 + 1;
                        }
                        markLF();
                    } else if (iDecodeMultiByteChar != 9) {
                        throwInvalidSpace(iDecodeMultiByteChar);
                    }
                } else if (iDecodeMultiByteChar > 127 && (iDecodeMultiByteChar = decodeMultiByteChar(iDecodeMultiByteChar, i5)) < 0) {
                    int i7 = -iDecodeMultiByteChar;
                    if (i3 >= cArrGrowArrayBy.length) {
                        cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                        this._nameBuffer = cArrGrowArrayBy;
                    }
                    i = i7 - 65536;
                    i2 = i3 + 1;
                    cArrGrowArrayBy[i3] = (char) ((i >> 10) | 55296);
                    iDecodeMultiByteChar = (i & 1023) | 56320;
                    i3 = i2;
                }
            }
            if (i3 >= cArrGrowArrayBy.length) {
                cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                this._nameBuffer = cArrGrowArrayBy;
            }
            cArrGrowArrayBy[i3] = (char) iDecodeMultiByteChar;
            i3++;
        }
        if (i3 == 0) {
            bindNs(pName, XmlPullParser.NO_NAMESPACE);
        } else {
            bindNs(pName, this._config.canonicalizeURI(cArrGrowArrayBy, i3));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final void skipUtf8_2(int i) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        int i3 = i2 + 1;
        this._inputPtr = i3;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i3);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final void skipUtf8_3(int i) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        if (i2 < 13) {
            int i3 = this._inputPtr;
            int i4 = i3 + 1;
            this._inputPtr = i4;
            byte b = bArr[i3];
            if ((b & 192) != 128) {
                reportInvalidOther(b & 255, i4);
            }
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i5 = this._inputPtr;
            int i6 = i5 + 1;
            this._inputPtr = i6;
            byte b2 = bArr2[i5];
            if ((b2 & 192) != 128) {
                reportInvalidOther(b2 & 255, i6);
                return;
            }
            return;
        }
        int i7 = i2 << 6;
        int i8 = this._inputPtr;
        int i9 = i8 + 1;
        this._inputPtr = i9;
        byte b3 = bArr[i8];
        if ((b3 & 192) != 128) {
            reportInvalidOther(b3 & 255, i9);
        }
        int i10 = i7 | (b3 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i11 = this._inputPtr;
        int i12 = i11 + 1;
        this._inputPtr = i12;
        byte b4 = bArr3[i11];
        if ((b4 & 192) != 128) {
            reportInvalidOther(b4 & 255, i12);
        }
        int i13 = (i10 << 6) | (b4 & 63);
        if (i13 >= 55296) {
            if (i13 < 57344 || (i13 >= 65534 && i13 <= 65535)) {
                handleInvalidXmlChar(i13);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final void skipUtf8_4(int i) throws XMLStreamException {
        int i2 = this._inputPtr;
        if (i2 + 4 > this._inputEnd) {
            skipUtf8_4Slow(i);
            return;
        }
        byte[] bArr = this._inputBuffer;
        int i3 = i2 + 1;
        this._inputPtr = i3;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i3);
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            reportInvalidOther(b2 & 255, i5);
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        this._inputPtr = i7;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            reportInvalidOther(b3 & 255, i7);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final void skipUtf8_4Slow(int i) throws XMLStreamException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        int i3 = i2 + 1;
        this._inputPtr = i3;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            reportInvalidOther(b & 255, i3);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        int i5 = i4 + 1;
        this._inputPtr = i5;
        byte b2 = bArr2[i4];
        if ((b2 & 192) != 128) {
            reportInvalidOther(b2 & 255, i5);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i6 = this._inputPtr;
        int i7 = i6 + 1;
        this._inputPtr = i7;
        byte b3 = bArr3[i6];
        if ((b3 & 192) != 128) {
            reportInvalidOther(b3 & 255, i7);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:17:0x0033  */
    /* JADX WARN: Code duplicated, block: B:20:0x003f  */
    /* JADX WARN: Code duplicated, block: B:22:0x0047  */
    /* JADX WARN: Code duplicated, block: B:25:0x0053  */
    /* JADX WARN: Code duplicated, block: B:27:0x005b  */
    @Override // com.fasterxml.aalto.in.ByteBasedScanner
    public int decodeCharForError(byte b) throws XMLStreamException {
        char c;
        int i;
        byte bNextByte;
        int i2;
        byte bNextByte2;
        byte bNextByte3;
        int i3;
        if (b >= 0) {
            return b;
        }
        if ((b & 224) != 192) {
            if ((b & 240) == 224) {
                c = 2;
                i = b & 15;
            } else if ((b & 248) == 240) {
                c = 3;
                i = b & 7;
            } else {
                reportInvalidInitial(b & 255);
                i3 = b;
            }
            bNextByte = nextByte();
            if ((bNextByte & 192) != 128) {
                reportInvalidOther(bNextByte & 255);
            }
            i2 = (i << 6) | (bNextByte & 63);
            if (c > 1) {
                bNextByte2 = nextByte();
                if ((bNextByte2 & 192) != 128) {
                    reportInvalidOther(bNextByte2 & 255);
                }
                i2 = (i2 << 6) | (bNextByte2 & 63);
                if (c > 2) {
                    bNextByte3 = nextByte();
                    if ((bNextByte3 & 192) != 128) {
                        reportInvalidOther(bNextByte3 & 255);
                    }
                    return (i2 << 6) | (bNextByte3 & 63);
                }
            }
            return i2;
        }
        i3 = b & 31;
        c = 1;
        i = i3;
        bNextByte = nextByte();
        if ((bNextByte & 192) != 128) {
            reportInvalidOther(bNextByte & 255);
        }
        i2 = (i << 6) | (bNextByte & 63);
        if (c > 1) {
            bNextByte2 = nextByte();
            if ((bNextByte2 & 192) != 128) {
                reportInvalidOther(bNextByte2 & 255);
            }
            i2 = (i2 << 6) | (bNextByte2 & 63);
            if (c > 2) {
                bNextByte3 = nextByte();
                if ((bNextByte3 & 192) != 128) {
                    reportInvalidOther(bNextByte3 & 255);
                }
                return (i2 << 6) | (bNextByte3 & 63);
            }
        }
        return i2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x0083  */
    /* JADX WARN: Code duplicated, block: B:34:0x008e  */
    /* JADX WARN: Code duplicated, block: B:39:0x009e  */
    /* JADX WARN: Code duplicated, block: B:46:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:50:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ec A[LOOP:2: B:37:0x0098->B:62:0x00ec, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:68:0x00ce A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x00ab A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x00ca A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00c2 A[SYNTHETIC] */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void finishCData() throws XMLStreamException {
        int i;
        int i2;
        byte b;
        boolean z;
        int i3;
        int i4;
        int[] iArr = this._charTypes.OTHER_CHARS;
        byte[] bArr = this._inputBuffer;
        char[] cArrResetWithEmpty = this._textBuilder.resetWithEmpty();
        int i5 = 0;
        while (true) {
            int i6 = this._inputPtr;
            if (i6 >= this._inputEnd) {
                loadMoreGuaranteed();
                i6 = this._inputPtr;
            }
            if (i5 >= cArrResetWithEmpty.length) {
                cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                i5 = 0;
            }
            int i7 = this._inputEnd;
            int length = (cArrResetWithEmpty.length - i5) + i6;
            if (length < i7) {
                i7 = length;
            }
            while (true) {
                if (i6 >= i7) {
                    this._inputPtr = i6;
                    break;
                }
                int i8 = i6 + 1;
                int iDecodeUtf8_2 = bArr[i6] & 255;
                int i9 = iArr[iDecodeUtf8_2];
                if (i9 != 0) {
                    this._inputPtr = i8;
                    if (i9 != 11) {
                        switch (i9) {
                            case 1:
                                handleInvalidXmlChar(iDecodeUtf8_2);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i4 = this._inputPtr;
                                if (bArr[i4] == 10) {
                                    this._inputPtr = i4 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i4 = this._inputPtr;
                                if (bArr[i4] == 10) {
                                    this._inputPtr = i4 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                break;
                            case 4:
                                reportInvalidInitial(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                                break;
                            case 7:
                                int iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                                int i10 = i5 + 1;
                                cArrResetWithEmpty[i5] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                                if (i10 >= cArrResetWithEmpty.length) {
                                    cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                                    i5 = 0;
                                } else {
                                    i5 = i10;
                                }
                                iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                                break;
                        }
                        i = 0;
                        while (true) {
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            byte[] bArr2 = this._inputBuffer;
                            i2 = this._inputPtr;
                            b = bArr2[i2];
                            if (b != 93) {
                                if (b == 62 || i < 1) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (z) {
                                    i--;
                                }
                                while (i > 0) {
                                    i3 = i5 + 1;
                                    cArrResetWithEmpty[i5] = ']';
                                    if (i3 >= cArrResetWithEmpty.length) {
                                        cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                                        i5 = 0;
                                        i--;
                                    } else {
                                        i5 = i3;
                                        i--;
                                    }
                                }
                                if (z) {
                                    this._inputPtr++;
                                    this._textBuilder.setCurrentLength(i5);
                                    if (this._cfgCoalescing || this._entityPending) {
                                        return;
                                    }
                                    finishCoalescedText();
                                    return;
                                }
                            } else {
                                this._inputPtr = i2 + 1;
                                i++;
                            }
                        }
                    } else {
                        i = 0;
                        while (true) {
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            byte[] bArr3 = this._inputBuffer;
                            i2 = this._inputPtr;
                            b = bArr3[i2];
                            if (b != 93) {
                                if (b == 62) {
                                    z = false;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    i--;
                                }
                                while (i > 0) {
                                    i3 = i5 + 1;
                                    cArrResetWithEmpty[i5] = ']';
                                    if (i3 >= cArrResetWithEmpty.length) {
                                        cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                                        i5 = 0;
                                        i--;
                                    } else {
                                        i5 = i3;
                                        i--;
                                    }
                                }
                                if (z) {
                                    this._inputPtr++;
                                    this._textBuilder.setCurrentLength(i5);
                                    if (this._cfgCoalescing) {
                                        return;
                                    } else {
                                        return;
                                    }
                                }
                            } else {
                                this._inputPtr = i2 + 1;
                                i++;
                            }
                        }
                    }
                    cArrResetWithEmpty[i5] = (char) iDecodeUtf8_2;
                    i5++;
                    break;
                }
                cArrResetWithEmpty[i5] = (char) iDecodeUtf8_2;
                i6 = i8;
                i5++;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:60:0x00de A[PHI: r10 r11
      0x00de: PHI (r10v15 int) = (r10v14 int), (r10v21 int) binds: [B:63:0x00f0, B:57:0x00d4] A[DONT_GENERATE, DONT_INLINE]
      0x00de: PHI (r11v10 int) = (r11v9 int), (r11v12 int) binds: [B:63:0x00f0, B:57:0x00d4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:82:0x0134  */
    /* JADX WARN: Code duplicated, block: B:85:0x013d  */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void finishCharacters() throws XMLStreamException {
        int iCheckInTreeIndentation;
        char[] bufferWithoutReset;
        int i;
        int iDecodeUtf8_4;
        int i2;
        int i3;
        int i4 = this._tmpChar;
        if (i4 < 0) {
            int i5 = -i4;
            bufferWithoutReset = this._textBuilder.resetWithEmpty();
            if ((i5 >> 16) != 0) {
                int i6 = i5 - 65536;
                bufferWithoutReset[0] = (char) ((i6 >> 10) | 55296);
                i5 = (i6 & 1023) | 56320;
                i3 = 1;
            } else {
                i3 = 0;
            }
            iCheckInTreeIndentation = i3 + 1;
            bufferWithoutReset[i3] = (char) i5;
        } else if (i4 == 13 || i4 == 10) {
            this._inputPtr++;
            iCheckInTreeIndentation = checkInTreeIndentation(i4);
            if (iCheckInTreeIndentation < 0) {
                return;
            } else {
                bufferWithoutReset = this._textBuilder.getBufferWithoutReset();
            }
        } else {
            bufferWithoutReset = this._textBuilder.resetWithEmpty();
            iCheckInTreeIndentation = 0;
        }
        int[] iArr = this._charTypes.TEXT_CHARS;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i7 = this._inputPtr;
            if (i7 >= this._inputEnd) {
                loadMoreGuaranteed();
                i7 = this._inputPtr;
            }
            if (iCheckInTreeIndentation >= bufferWithoutReset.length) {
                bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                iCheckInTreeIndentation = 0;
            }
            int i8 = this._inputEnd;
            int length = (bufferWithoutReset.length - iCheckInTreeIndentation) + i7;
            if (length < i8) {
                i8 = length;
            }
            while (true) {
                if (i7 >= i8) {
                    this._inputPtr = i7;
                    break;
                }
                int i9 = i7 + 1;
                int iDecodeUtf8_2 = bArr[i7] & 255;
                int i10 = iArr[iDecodeUtf8_2];
                if (i10 != 0) {
                    this._inputPtr = i9;
                    switch (i10) {
                        case 1:
                            handleInvalidXmlChar(iDecodeUtf8_2);
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                            iCheckInTreeIndentation++;
                        case 2:
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                            iCheckInTreeIndentation++;
                            break;
                        case XmlPullParser.END_TAG /* 3 */:
                            markLF();
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                            iCheckInTreeIndentation++;
                            break;
                        case 4:
                            reportInvalidInitial(iDecodeUtf8_2);
                            this._inputPtr--;
                            break;
                        case XmlPullParser.CDSECT /* 5 */:
                            iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                            iCheckInTreeIndentation++;
                            break;
                        case XmlPullParser.ENTITY_REF /* 6 */:
                            iDecodeUtf8_2 = this._inputEnd - i9 >= 2 ? decodeUtf8_3fast(iDecodeUtf8_2) : decodeUtf8_3(iDecodeUtf8_2);
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                            iCheckInTreeIndentation++;
                            break;
                        case 7:
                            iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                            i2 = iCheckInTreeIndentation + 1;
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                            if (i2 >= bufferWithoutReset.length) {
                                bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                iCheckInTreeIndentation = 0;
                            } else {
                                iCheckInTreeIndentation = i2;
                            }
                            iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                            iCheckInTreeIndentation++;
                            break;
                        case 8:
                        default:
                            bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                            iCheckInTreeIndentation++;
                            break;
                        case 9:
                            this._inputPtr--;
                            break;
                        case XmlPullParser.DOCDECL /* 10 */:
                            iDecodeUtf8_2 = handleEntityInText(false);
                            if (iDecodeUtf8_2 != 0) {
                                if ((iDecodeUtf8_2 >> 16) != 0) {
                                    iDecodeUtf8_4 = iDecodeUtf8_2 - 65536;
                                    i2 = iCheckInTreeIndentation + 1;
                                    bufferWithoutReset[iCheckInTreeIndentation] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                                    if (i2 >= bufferWithoutReset.length) {
                                        bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                        iCheckInTreeIndentation = 0;
                                    } else {
                                        iCheckInTreeIndentation = i2;
                                    }
                                    iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                                }
                                bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                                iCheckInTreeIndentation++;
                            }
                            this._entityPending = true;
                            break;
                            break;
                        case AndroidSdkVersion.HONEYCOMB /* 11 */:
                            int i11 = 1;
                            while (true) {
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                int i12 = this._inputPtr;
                                byte b = bArr[i12];
                                if (b != 93) {
                                    if (b == 62 && i11 > 1) {
                                        reportIllegalCDataEnd();
                                    }
                                    while (i11 > 1) {
                                        int i13 = iCheckInTreeIndentation + 1;
                                        bufferWithoutReset[iCheckInTreeIndentation] = ']';
                                        if (i13 >= bufferWithoutReset.length) {
                                            bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                            iCheckInTreeIndentation = 0;
                                        } else {
                                            iCheckInTreeIndentation = i13;
                                        }
                                        i11--;
                                    }
                                    bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                                    iCheckInTreeIndentation++;
                                }
                                this._inputPtr = i12 + 1;
                                i11++;
                                break;
                            }
                            break;
                    }
                } else {
                    bufferWithoutReset[iCheckInTreeIndentation] = (char) iDecodeUtf8_2;
                    i7 = i9;
                    iCheckInTreeIndentation++;
                }
            }
        }
        this._textBuilder.setCurrentLength(iCheckInTreeIndentation);
        if (!this._cfgCoalescing || this._entityPending) {
            return;
        }
        finishCoalescedText();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x0088  */
    /* JADX WARN: Code duplicated, block: B:34:0x0093  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:46:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:48:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:50:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:58:0x00e6 A[LOOP:2: B:37:0x009d->B:58:0x00e6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:64:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x00b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x00cf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x00c7 A[SYNTHETIC] */
    public final void finishCoalescedCData() throws XMLStreamException {
        int i;
        int i2;
        byte b;
        boolean z;
        int i3;
        int i4;
        int[] iArr = this._charTypes.OTHER_CHARS;
        byte[] bArr = this._inputBuffer;
        char[] bufferWithoutReset = this._textBuilder.getBufferWithoutReset();
        int currentLength = this._textBuilder.getCurrentLength();
        while (true) {
            int i5 = this._inputPtr;
            if (i5 >= this._inputEnd) {
                loadMoreGuaranteed();
                i5 = this._inputPtr;
            }
            int i6 = 0;
            if (currentLength >= bufferWithoutReset.length) {
                bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                currentLength = 0;
            }
            int i7 = this._inputEnd;
            int length = (bufferWithoutReset.length - currentLength) + i5;
            if (length < i7) {
                i7 = length;
            }
            while (true) {
                if (i5 >= i7) {
                    this._inputPtr = i5;
                    break;
                }
                int i8 = i5 + 1;
                int iDecodeUtf8_2 = bArr[i5] & 255;
                int i9 = iArr[iDecodeUtf8_2];
                if (i9 != 0) {
                    this._inputPtr = i8;
                    if (i9 != 11) {
                        switch (i9) {
                            case 1:
                                handleInvalidXmlChar(iDecodeUtf8_2);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i4 = this._inputPtr;
                                if (bArr[i4] == 10) {
                                    this._inputPtr = i4 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i4 = this._inputPtr;
                                if (bArr[i4] == 10) {
                                    this._inputPtr = i4 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                break;
                            case 4:
                                reportInvalidInitial(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                                break;
                            case 7:
                                int iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                                int i10 = currentLength + 1;
                                bufferWithoutReset[currentLength] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                                if (i10 >= bufferWithoutReset.length) {
                                    bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                } else {
                                    i6 = i10;
                                }
                                iDecodeUtf8_2 = 56320 | (iDecodeUtf8_4 & 1023);
                                currentLength = i6;
                                break;
                        }
                        i = 0;
                        while (true) {
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            byte[] bArr2 = this._inputBuffer;
                            i2 = this._inputPtr;
                            b = bArr2[i2];
                            if (b != 93) {
                                if (b == 62 || i < 1) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (z) {
                                    i--;
                                }
                                while (i > 0) {
                                    i3 = currentLength + 1;
                                    bufferWithoutReset[currentLength] = ']';
                                    if (i3 >= bufferWithoutReset.length) {
                                        bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                        currentLength = 0;
                                        i--;
                                    } else {
                                        currentLength = i3;
                                        i--;
                                    }
                                }
                                if (z) {
                                    this._inputPtr++;
                                    this._textBuilder.setCurrentLength(currentLength);
                                    return;
                                }
                            } else {
                                this._inputPtr = i2 + 1;
                                i++;
                            }
                        }
                    } else {
                        i = 0;
                        while (true) {
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            byte[] bArr3 = this._inputBuffer;
                            i2 = this._inputPtr;
                            b = bArr3[i2];
                            if (b != 93) {
                                if (b == 62) {
                                    z = false;
                                } else {
                                    z = false;
                                }
                                if (z) {
                                    i--;
                                }
                                while (i > 0) {
                                    i3 = currentLength + 1;
                                    bufferWithoutReset[currentLength] = ']';
                                    if (i3 >= bufferWithoutReset.length) {
                                        bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                        currentLength = 0;
                                        i--;
                                    } else {
                                        currentLength = i3;
                                        i--;
                                    }
                                }
                                if (z) {
                                    this._inputPtr++;
                                    this._textBuilder.setCurrentLength(currentLength);
                                    return;
                                }
                            } else {
                                this._inputPtr = i2 + 1;
                                i++;
                            }
                        }
                    }
                    bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                    currentLength++;
                    break;
                }
                bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                i5 = i8;
                currentLength++;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:43:0x00a3 A[PHI: r4 r7
      0x00a3: PHI (r4v15 int) = (r4v14 int), (r4v19 int) binds: [B:46:0x00b7, B:41:0x009a] A[DONT_GENERATE, DONT_INLINE]
      0x00a3: PHI (r7v5 int) = (r7v4 int), (r7v8 int) binds: [B:46:0x00b7, B:41:0x009a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:61:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00fb  */
    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x0045. Please report as an issue. */
    public final void finishCoalescedCharacters() throws XMLStreamException {
        int i;
        int iDecodeUtf8_4;
        int i2;
        int[] iArr = this._charTypes.TEXT_CHARS;
        byte[] bArr = this._inputBuffer;
        char[] bufferWithoutReset = this._textBuilder.getBufferWithoutReset();
        int currentLength = this._textBuilder.getCurrentLength();
        while (true) {
            int i3 = this._inputPtr;
            if (i3 >= this._inputEnd) {
                loadMoreGuaranteed();
                i3 = this._inputPtr;
            }
            int i4 = 0;
            if (currentLength >= bufferWithoutReset.length) {
                bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                currentLength = 0;
            }
            int i5 = this._inputEnd;
            int length = (bufferWithoutReset.length - currentLength) + i3;
            if (length < i5) {
                i5 = length;
            }
            while (true) {
                if (i3 >= i5) {
                    this._inputPtr = i3;
                    break;
                }
                int i6 = i3 + 1;
                int iDecodeUtf8_2 = bArr[i3] & 255;
                int i7 = iArr[iDecodeUtf8_2];
                if (i7 != 0) {
                    this._inputPtr = i6;
                    switch (i7) {
                        case 1:
                            handleInvalidXmlChar(iDecodeUtf8_2);
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                            currentLength++;
                        case 2:
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                            currentLength++;
                            break;
                        case XmlPullParser.END_TAG /* 3 */:
                            markLF();
                            bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                            currentLength++;
                            break;
                        case 4:
                            reportInvalidInitial(iDecodeUtf8_2);
                            break;
                        case XmlPullParser.CDSECT /* 5 */:
                            iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                            bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                            currentLength++;
                            break;
                        case XmlPullParser.ENTITY_REF /* 6 */:
                            iDecodeUtf8_2 = this._inputEnd - i6 >= 2 ? decodeUtf8_3fast(iDecodeUtf8_2) : decodeUtf8_3(iDecodeUtf8_2);
                            bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                            currentLength++;
                            break;
                        case 7:
                            iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                            i2 = currentLength + 1;
                            bufferWithoutReset[currentLength] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                            if (i2 >= bufferWithoutReset.length) {
                                bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                            } else {
                                i4 = i2;
                            }
                            iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                            currentLength = i4;
                            bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                            currentLength++;
                            break;
                        case 8:
                        default:
                            bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                            currentLength++;
                            break;
                        case 9:
                            break;
                        case XmlPullParser.DOCDECL /* 10 */:
                            iDecodeUtf8_2 = handleEntityInText(false);
                            if (iDecodeUtf8_2 != 0) {
                                if ((iDecodeUtf8_2 >> 16) != 0) {
                                    iDecodeUtf8_4 = iDecodeUtf8_2 - 65536;
                                    i2 = currentLength + 1;
                                    bufferWithoutReset[currentLength] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                                    if (i2 >= bufferWithoutReset.length) {
                                        bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                    } else {
                                        i4 = i2;
                                    }
                                    iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                                    currentLength = i4;
                                }
                                bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                                currentLength++;
                            }
                            this._entityPending = true;
                            break;
                            break;
                        case AndroidSdkVersion.HONEYCOMB /* 11 */:
                            int i8 = 1;
                            while (true) {
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                int i9 = this._inputPtr;
                                byte b = bArr[i9];
                                if (b != 93) {
                                    if (b == 62 && i8 > 1) {
                                        reportIllegalCDataEnd();
                                    }
                                    while (i8 > 1) {
                                        int i10 = currentLength + 1;
                                        bufferWithoutReset[currentLength] = ']';
                                        if (i10 >= bufferWithoutReset.length) {
                                            bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                                            currentLength = 0;
                                        } else {
                                            currentLength = i10;
                                        }
                                        i8--;
                                    }
                                    bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                                    currentLength++;
                                }
                                this._inputPtr = i9 + 1;
                                i8++;
                                break;
                            }
                            break;
                    }
                    this._textBuilder.setCurrentLength(currentLength);
                }
                bufferWithoutReset[currentLength] = (char) iDecodeUtf8_2;
                i3 = i6;
                currentLength++;
            }
        }
        this._inputPtr--;
        this._textBuilder.setCurrentLength(currentLength);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public final void finishCoalescedText() throws XMLStreamException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            if (bArr[i] != 60) {
                finishCoalescedCharacters();
                if (this._entityPending) {
                    return;
                }
            } else {
                if (i + 3 >= this._inputEnd && !loadAndRetain(3)) {
                    return;
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                if (bArr2[i2 + 1] != 33 || bArr2[i2 + 2] != 91) {
                    return;
                }
                this._inputPtr = i2 + 3;
                for (int i3 = 0; i3 < 6; i3++) {
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr3 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    byte b = bArr3[i4];
                    if (b != ((byte) "CDATA[".charAt(i3))) {
                        reportTreeUnexpChar(decodeCharForError(b), " (expected '" + "CDATA[".charAt(i3) + "' for CDATA section)");
                    }
                }
                finishCoalescedCData();
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x007f  */
    /* JADX WARN: Code duplicated, block: B:34:0x008a  */
    /* JADX WARN: Code duplicated, block: B:38:0x0099  */
    /* JADX WARN: Code duplicated, block: B:43:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:46:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:53:0x00a6 A[SYNTHETIC] */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void finishComment() throws XMLStreamException {
        byte[] bArr;
        int i;
        int i2;
        byte[] bArr2;
        int i3;
        int i4;
        int[] iArr = this._charTypes.OTHER_CHARS;
        byte[] bArr3 = this._inputBuffer;
        char[] cArrResetWithEmpty = this._textBuilder.resetWithEmpty();
        int i5 = 0;
        while (true) {
            int i6 = this._inputPtr;
            if (i6 >= this._inputEnd) {
                loadMoreGuaranteed();
                i6 = this._inputPtr;
            }
            if (i5 >= cArrResetWithEmpty.length) {
                cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                i5 = 0;
            }
            int i7 = this._inputEnd;
            int length = (cArrResetWithEmpty.length - i5) + i6;
            if (length < i7) {
                i7 = length;
            }
            while (true) {
                if (i6 >= i7) {
                    this._inputPtr = i6;
                    break;
                }
                int i8 = i6 + 1;
                int iDecodeUtf8_2 = bArr3[i6] & 255;
                int i9 = iArr[iDecodeUtf8_2];
                if (i9 != 0) {
                    this._inputPtr = i8;
                    if (i9 != 13) {
                        switch (i9) {
                            case 1:
                                handleInvalidXmlChar(iDecodeUtf8_2);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i4 = this._inputPtr;
                                if (bArr3[i4] == 10) {
                                    this._inputPtr = i4 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i4 = this._inputPtr;
                                if (bArr3[i4] == 10) {
                                    this._inputPtr = i4 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                break;
                            case 4:
                                reportInvalidInitial(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                                break;
                            case 7:
                                int iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                                int i10 = i5 + 1;
                                cArrResetWithEmpty[i5] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                                if (i10 >= cArrResetWithEmpty.length) {
                                    cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                                    i5 = 0;
                                } else {
                                    i5 = i10;
                                }
                                iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                                break;
                        }
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        bArr = this._inputBuffer;
                        i = this._inputPtr;
                        if (bArr[i] == 45) {
                            i2 = i + 1;
                            this._inputPtr = i2;
                            if (i2 >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            bArr2 = this._inputBuffer;
                            i3 = this._inputPtr;
                            this._inputPtr = i3 + 1;
                            if (bArr2[i3] != 62) {
                                reportDoubleHyphenInComments();
                            }
                            this._textBuilder.setCurrentLength(i5);
                            return;
                        }
                    } else {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        bArr = this._inputBuffer;
                        i = this._inputPtr;
                        if (bArr[i] == 45) {
                            i2 = i + 1;
                            this._inputPtr = i2;
                            if (i2 >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            bArr2 = this._inputBuffer;
                            i3 = this._inputPtr;
                            this._inputPtr = i3 + 1;
                            if (bArr2[i3] != 62) {
                                reportDoubleHyphenInComments();
                            }
                            this._textBuilder.setCurrentLength(i5);
                            return;
                        }
                    }
                    cArrResetWithEmpty[i5] = (char) iDecodeUtf8_2;
                    i5++;
                    break;
                }
                cArrResetWithEmpty[i5] = (char) iDecodeUtf8_2;
                i6 = i8;
                i5++;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:46:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:48:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:53:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:56:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ca A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x0013 A[EDGE_INSN: B:69:0x0013->B:68:0x0013 BREAK  A[LOOP:1: B:18:0x0032->B:62:0x00da], SYNTHETIC] */
    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x0042. Please report as an issue. */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void finishDTD(boolean z) throws XMLStreamException {
        byte[] bArr;
        int i;
        char[] cArrResetWithEmpty = z ? this._textBuilder.resetWithEmpty() : null;
        int[] iArr = this._charTypes.DTD_CHARS;
        int i2 = 0;
        int i3 = 0;
        boolean z2 = false;
        while (true) {
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                loadMoreGuaranteed();
                i4 = this._inputPtr;
            }
            int i5 = this._inputEnd;
            if (cArrResetWithEmpty != null) {
                if (i2 >= cArrResetWithEmpty.length) {
                    cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                    i2 = 0;
                }
                int length = (cArrResetWithEmpty.length - i2) + i4;
                if (length < i5) {
                    i5 = length;
                }
            }
            while (true) {
                if (i4 >= i5) {
                    this._inputPtr = i4;
                    break;
                }
                int i6 = i4 + 1;
                int iDecodeUtf8_2 = this._inputBuffer[i4] & 255;
                int i7 = iArr[iDecodeUtf8_2];
                if (i7 != 0) {
                    this._inputPtr = i6;
                    switch (i7) {
                        case 1:
                            handleInvalidXmlChar(iDecodeUtf8_2);
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            bArr = this._inputBuffer;
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            if (cArrResetWithEmpty != null) {
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                        case 2:
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            bArr = this._inputBuffer;
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case XmlPullParser.END_TAG /* 3 */:
                            markLF();
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case 4:
                            reportInvalidInitial(iDecodeUtf8_2);
                            if (i3 == 0) {
                                i3 = iDecodeUtf8_2;
                            } else if (i3 == iDecodeUtf8_2) {
                                i3 = 0;
                            }
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case XmlPullParser.CDSECT /* 5 */:
                            iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case XmlPullParser.ENTITY_REF /* 6 */:
                            iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case 7:
                            iDecodeUtf8_2 = decodeUtf8_4(iDecodeUtf8_2);
                            if (cArrResetWithEmpty != null) {
                                int i8 = i2 + 1;
                                cArrResetWithEmpty[i2] = (char) ((iDecodeUtf8_2 >> 10) | 55296);
                                iDecodeUtf8_2 = 56320 | (iDecodeUtf8_2 & 1023);
                                if (i8 >= cArrResetWithEmpty.length) {
                                    cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                                    i2 = 0;
                                } else {
                                    i2 = i8;
                                }
                            }
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case 8:
                            if (i3 == 0) {
                                i3 = iDecodeUtf8_2;
                            } else if (i3 == iDecodeUtf8_2) {
                                i3 = 0;
                            }
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case 9:
                            if (!z2) {
                                z2 = true;
                            }
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case XmlPullParser.DOCDECL /* 10 */:
                            if (i3 == 0) {
                                z2 = false;
                            }
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                        case AndroidSdkVersion.HONEYCOMB /* 11 */:
                            break;
                        default:
                            if (cArrResetWithEmpty != null) {
                                break;
                                break;
                            }
                            cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                            i2++;
                            break;
                            break;
                    }
                    if (!z2 && i3 == 0) {
                        if (cArrResetWithEmpty != null) {
                            this._textBuilder.setCurrentLength(i2);
                        }
                        byte bSkipInternalWs = skipInternalWs(false, null);
                        if (bSkipInternalWs != 62) {
                            throwUnexpectedChar(decodeCharForError(bSkipInternalWs), " expected '>' after the internal subset");
                            return;
                        }
                        return;
                    }
                    if (cArrResetWithEmpty != null) {
                        break;
                        break;
                    }
                    cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                    i2++;
                    break;
                    break;
                }
                if (cArrResetWithEmpty != null) {
                    cArrResetWithEmpty[i2] = (char) iDecodeUtf8_2;
                    i2++;
                }
                i4 = i6;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x007f  */
    /* JADX WARN: Code duplicated, block: B:34:0x008a  */
    /* JADX WARN: Code duplicated, block: B:38:0x0099  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a6 A[SYNTHETIC] */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void finishPI() throws XMLStreamException {
        byte[] bArr;
        int i;
        int i2;
        int[] iArr = this._charTypes.OTHER_CHARS;
        byte[] bArr2 = this._inputBuffer;
        char[] cArrResetWithEmpty = this._textBuilder.resetWithEmpty();
        int i3 = 0;
        while (true) {
            int i4 = this._inputPtr;
            if (i4 >= this._inputEnd) {
                loadMoreGuaranteed();
                i4 = this._inputPtr;
            }
            if (i3 >= cArrResetWithEmpty.length) {
                cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                i3 = 0;
            }
            int i5 = this._inputEnd;
            int length = (cArrResetWithEmpty.length - i3) + i4;
            if (length < i5) {
                i5 = length;
            }
            while (true) {
                if (i4 >= i5) {
                    this._inputPtr = i4;
                    break;
                }
                int i6 = i4 + 1;
                int iDecodeUtf8_2 = bArr2[i4] & 255;
                int i7 = iArr[iDecodeUtf8_2];
                if (i7 != 0) {
                    this._inputPtr = i6;
                    if (i7 != 12) {
                        switch (i7) {
                            case 1:
                                handleInvalidXmlChar(iDecodeUtf8_2);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i2 = this._inputPtr;
                                if (bArr2[i2] == 10) {
                                    this._inputPtr = i2 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i2 = this._inputPtr;
                                if (bArr2[i2] == 10) {
                                    this._inputPtr = i2 + 1;
                                }
                                markLF();
                                iDecodeUtf8_2 = 10;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                break;
                            case 4:
                                reportInvalidInitial(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                                break;
                            case 7:
                                int iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                                int i8 = i3 + 1;
                                cArrResetWithEmpty[i3] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                                if (i8 >= cArrResetWithEmpty.length) {
                                    cArrResetWithEmpty = this._textBuilder.finishCurrentSegment();
                                    i3 = 0;
                                } else {
                                    i3 = i8;
                                }
                                iDecodeUtf8_2 = (iDecodeUtf8_4 & 1023) | 56320;
                                break;
                        }
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        bArr = this._inputBuffer;
                        i = this._inputPtr;
                        if (bArr[i] == 62) {
                            this._inputPtr = i + 1;
                            this._textBuilder.setCurrentLength(i3);
                            return;
                        }
                    } else {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        bArr = this._inputBuffer;
                        i = this._inputPtr;
                        if (bArr[i] == 62) {
                            this._inputPtr = i + 1;
                            this._textBuilder.setCurrentLength(i3);
                            return;
                        }
                    }
                    cArrResetWithEmpty[i3] = (char) iDecodeUtf8_2;
                    i3++;
                    break;
                }
                cArrResetWithEmpty[i3] = (char) iDecodeUtf8_2;
                i4 = i6;
                i3++;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:21:0x003e  */
    /* JADX WARN: Code duplicated, block: B:23:0x0042  */
    /* JADX WARN: Code duplicated, block: B:24:0x0046 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:25:0x0048  */
    /* JADX WARN: Code duplicated, block: B:27:0x004c  */
    /* JADX WARN: Code duplicated, block: B:31:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0069  */
    /* JADX WARN: Code duplicated, block: B:39:0x0071  */
    /* JADX WARN: Code duplicated, block: B:41:0x0078  */
    /* JADX WARN: Code duplicated, block: B:47:0x0086  */
    /* JADX WARN: Code duplicated, block: B:50:0x0061 A[EDGE_INSN: B:50:0x0061->B:34:0x0061 BREAK  A[LOOP:0: B:12:0x0026->B:48:0x008e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x008e A[SYNTHETIC] */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void finishSpace() throws XMLStreamException {
        int iCheckPrologIndentation;
        char[] bufferWithoutReset;
        int i;
        int i2 = this._tmpChar;
        int i3 = 0;
        if (i2 == 13 || i2 == 10) {
            iCheckPrologIndentation = checkPrologIndentation(i2);
            if (iCheckPrologIndentation < 0) {
                return;
            } else {
                bufferWithoutReset = this._textBuilder.getBufferWithoutReset();
            }
        } else {
            bufferWithoutReset = this._textBuilder.resetWithEmpty();
            bufferWithoutReset[0] = (char) i2;
            iCheckPrologIndentation = 1;
        }
        int i4 = this._inputPtr;
        while (true) {
            if (i4 < this._inputEnd) {
                i = this._inputBuffer[i4] & 255;
                if (i > 32) {
                    break;
                }
                i4++;
                if (i == 10) {
                    markLF(i4);
                } else if (i == 13) {
                    if (i4 >= this._inputEnd) {
                        if (!loadMore()) {
                            if (iCheckPrologIndentation >= bufferWithoutReset.length) {
                                bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                            } else {
                                i3 = iCheckPrologIndentation;
                            }
                            iCheckPrologIndentation = i3 + 1;
                            bufferWithoutReset[i3] = '\n';
                            break;
                        }
                        i4 = this._inputPtr;
                    }
                    if (this._inputBuffer[i4] == 10) {
                        i4++;
                    }
                    markLF(i4);
                    i = 10;
                } else if (i != 32 && i != 9) {
                    this._inputPtr = i4;
                    throwInvalidSpace(i);
                }
                if (iCheckPrologIndentation >= bufferWithoutReset.length) {
                    bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                    iCheckPrologIndentation = 0;
                }
                bufferWithoutReset[iCheckPrologIndentation] = (char) i;
                iCheckPrologIndentation++;
            } else {
                if (!loadMore()) {
                    break;
                }
                i4 = this._inputPtr;
                i = this._inputBuffer[i4] & 255;
                if (i > 32) {
                    break;
                    break;
                }
                i4++;
                if (i == 10) {
                    markLF(i4);
                } else if (i == 13) {
                    if (i4 >= this._inputEnd) {
                        if (!loadMore()) {
                            if (iCheckPrologIndentation >= bufferWithoutReset.length) {
                                bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                            } else {
                                i3 = iCheckPrologIndentation;
                            }
                            iCheckPrologIndentation = i3 + 1;
                            bufferWithoutReset[i3] = '\n';
                            break;
                        }
                        i4 = this._inputPtr;
                    }
                    if (this._inputBuffer[i4] == 10) {
                        i4++;
                    }
                    markLF(i4);
                    i = 10;
                } else if (i != 32) {
                    this._inputPtr = i4;
                    throwInvalidSpace(i);
                }
                if (iCheckPrologIndentation >= bufferWithoutReset.length) {
                    bufferWithoutReset = this._textBuilder.finishCurrentSegment();
                    iCheckPrologIndentation = 0;
                }
                bufferWithoutReset[iCheckPrologIndentation] = (char) i;
                iCheckPrologIndentation++;
            }
        }
        this._inputPtr = i4;
        this._textBuilder.setCurrentLength(iCheckPrologIndentation);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void finishToken() throws XMLStreamException {
        this._tokenIncomplete = false;
        int i = this._currToken;
        if (i == 3) {
            finishPI();
            return;
        }
        if (i == 4) {
            finishCharacters();
            return;
        }
        if (i == 5) {
            finishComment();
            return;
        }
        if (i == 6) {
            finishSpace();
            return;
        }
        if (i == 11) {
            finishDTD(true);
        } else if (i != 12) {
            ErrorConsts.throwInternalError();
        } else {
            finishCData();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:146:0x01e0  */
    @Override // com.fasterxml.aalto.in.StreamScanner
    public final int handleEntityInText(boolean z) throws XMLStreamException {
        String str;
        byte bLoadOne;
        byte bLoadOne2;
        byte bLoadOne3;
        byte bLoadOne4;
        byte bLoadOne5;
        byte bLoadOne6;
        byte bLoadOne7;
        byte bLoadOne8;
        byte bLoadOne9;
        byte bLoadOne10;
        byte bLoadOne11;
        byte bLoadOne12;
        byte bLoadOne13;
        byte bLoadOne14;
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        int i2 = i + 1;
        this._inputPtr = i2;
        byte b = bArr[i];
        if (b == 35) {
            return handleCharEntity();
        }
        if (b == 97) {
            if (i2 < this._inputEnd) {
                this._inputPtr = i + 2;
                bLoadOne9 = bArr[i2];
            } else {
                bLoadOne9 = loadOne();
            }
            b = bLoadOne9;
            if (b == 109) {
                int i3 = this._inputPtr;
                if (i3 < this._inputEnd) {
                    byte[] bArr2 = this._inputBuffer;
                    this._inputPtr = i3 + 1;
                    bLoadOne13 = bArr2[i3];
                } else {
                    bLoadOne13 = loadOne();
                }
                b = bLoadOne13;
                if (b == 112) {
                    int i4 = this._inputPtr;
                    if (i4 < this._inputEnd) {
                        byte[] bArr3 = this._inputBuffer;
                        this._inputPtr = i4 + 1;
                        bLoadOne14 = bArr3[i4];
                    } else {
                        bLoadOne14 = loadOne();
                    }
                    b = bLoadOne14;
                    if (b == 59) {
                        return 38;
                    }
                    str = "amp";
                } else {
                    str = "am";
                }
            } else if (b == 112) {
                int i5 = this._inputPtr;
                if (i5 < this._inputEnd) {
                    byte[] bArr4 = this._inputBuffer;
                    this._inputPtr = i5 + 1;
                    bLoadOne10 = bArr4[i5];
                } else {
                    bLoadOne10 = loadOne();
                }
                b = bLoadOne10;
                if (b == 111) {
                    int i6 = this._inputPtr;
                    if (i6 < this._inputEnd) {
                        byte[] bArr5 = this._inputBuffer;
                        this._inputPtr = i6 + 1;
                        bLoadOne11 = bArr5[i6];
                    } else {
                        bLoadOne11 = loadOne();
                    }
                    b = bLoadOne11;
                    if (b == 115) {
                        int i7 = this._inputPtr;
                        if (i7 < this._inputEnd) {
                            byte[] bArr6 = this._inputBuffer;
                            this._inputPtr = i7 + 1;
                            bLoadOne12 = bArr6[i7];
                        } else {
                            bLoadOne12 = loadOne();
                        }
                        b = bLoadOne12;
                        if (b == 59) {
                            return 39;
                        }
                        str = "apos";
                    } else {
                        str = "apo";
                    }
                } else {
                    str = "ap";
                }
            } else {
                str = "a";
            }
        } else if (b == 108) {
            if (i2 < this._inputEnd) {
                this._inputPtr = i + 2;
                bLoadOne7 = bArr[i2];
            } else {
                bLoadOne7 = loadOne();
            }
            b = bLoadOne7;
            if (b == 116) {
                int i8 = this._inputPtr;
                if (i8 < this._inputEnd) {
                    byte[] bArr7 = this._inputBuffer;
                    this._inputPtr = i8 + 1;
                    bLoadOne8 = bArr7[i8];
                } else {
                    bLoadOne8 = loadOne();
                }
                b = bLoadOne8;
                if (b == 59) {
                    return 60;
                }
                str = "lt";
            } else {
                str = "l";
            }
        } else if (b == 103) {
            if (i2 < this._inputEnd) {
                this._inputPtr = i + 2;
                bLoadOne5 = bArr[i2];
            } else {
                bLoadOne5 = loadOne();
            }
            b = bLoadOne5;
            if (b == 116) {
                int i9 = this._inputPtr;
                if (i9 < this._inputEnd) {
                    byte[] bArr8 = this._inputBuffer;
                    this._inputPtr = i9 + 1;
                    bLoadOne6 = bArr8[i9];
                } else {
                    bLoadOne6 = loadOne();
                }
                b = bLoadOne6;
                if (b == 59) {
                    return 62;
                }
                str = "gt";
            } else {
                str = "g";
            }
        } else if (b == 113) {
            if (i2 < this._inputEnd) {
                this._inputPtr = i + 2;
                bLoadOne = bArr[i2];
            } else {
                bLoadOne = loadOne();
            }
            b = bLoadOne;
            if (b == 117) {
                int i10 = this._inputPtr;
                if (i10 < this._inputEnd) {
                    byte[] bArr9 = this._inputBuffer;
                    this._inputPtr = i10 + 1;
                    bLoadOne2 = bArr9[i10];
                } else {
                    bLoadOne2 = loadOne();
                }
                b = bLoadOne2;
                if (b == 111) {
                    int i11 = this._inputPtr;
                    if (i11 < this._inputEnd) {
                        byte[] bArr10 = this._inputBuffer;
                        this._inputPtr = i11 + 1;
                        bLoadOne3 = bArr10[i11];
                    } else {
                        bLoadOne3 = loadOne();
                    }
                    b = bLoadOne3;
                    if (b == 116) {
                        int i12 = this._inputPtr;
                        if (i12 < this._inputEnd) {
                            byte[] bArr11 = this._inputBuffer;
                            this._inputPtr = i12 + 1;
                            bLoadOne4 = bArr11[i12];
                        } else {
                            bLoadOne4 = loadOne();
                        }
                        b = bLoadOne4;
                        if (b == 59) {
                            return 34;
                        }
                        str = "quot";
                    } else {
                        str = "quo";
                    }
                } else {
                    str = "qu";
                }
            } else {
                str = "q";
            }
        } else {
            str = XmlPullParser.NO_NAMESPACE;
        }
        int[] iArr = this._charTypes.NAME_CHARS;
        char[] cArrGrowArrayBy = this._nameBuffer;
        int length = str.length();
        int i13 = 0;
        while (i13 < length) {
            cArrGrowArrayBy[i13] = str.charAt(i13);
            i13++;
        }
        while (b != 59) {
            int iDecodeUtf8_2 = b & 255;
            int i14 = iArr[iDecodeUtf8_2];
            boolean zIs10NameStartChar = true;
            if (i14 == 0 || i14 == 1 || i14 == 2) {
                if (i13 <= 0) {
                    zIs10NameStartChar = false;
                }
            } else if (i14 != 3) {
                if (i14 == 5) {
                    iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                    zIs10NameStartChar = XmlChars.is10NameStartChar(iDecodeUtf8_2);
                } else if (i14 == 6) {
                    iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                    zIs10NameStartChar = XmlChars.is10NameStartChar(iDecodeUtf8_2);
                } else if (i14 != 7) {
                    zIs10NameStartChar = false;
                } else {
                    iDecodeUtf8_2 = decodeUtf8_4(iDecodeUtf8_2);
                    zIs10NameStartChar = XmlChars.is10NameStartChar(iDecodeUtf8_2);
                    if (zIs10NameStartChar) {
                        if (i13 >= cArrGrowArrayBy.length) {
                            cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                            this._nameBuffer = cArrGrowArrayBy;
                        }
                        int i15 = iDecodeUtf8_2 - 65536;
                        cArrGrowArrayBy[i13] = (char) ((i15 >> 10) | 55296);
                        iDecodeUtf8_2 = (i15 & 1023) | 56320;
                        i13++;
                    }
                }
            }
            if (!zIs10NameStartChar) {
                reportInvalidNameChar(iDecodeUtf8_2, i13);
            }
            if (i13 >= cArrGrowArrayBy.length) {
                cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                this._nameBuffer = cArrGrowArrayBy;
            }
            int i16 = i13 + 1;
            cArrGrowArrayBy[i13] = (char) iDecodeUtf8_2;
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr12 = this._inputBuffer;
            int i17 = this._inputPtr;
            this._inputPtr = i17 + 1;
            i13 = i16;
            b = bArr12[i17];
        }
        String str2 = new String(cArrGrowArrayBy, 0, i13);
        this._tokenName = new PNameC(str2, null, str2, 0);
        if (this._config.willExpandEntities()) {
            reportInputProblem("General entity reference (&" + str2 + ";) encountered in entity expanding mode: operation not (yet) implemented");
        }
        if (z) {
            reportInputProblem("General entity reference (&" + str2 + ";) encountered in attribute value, in non-entity-expanding mode: no way to handle it");
        }
        return 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:68:0x011d  */
    @Override // com.fasterxml.aalto.in.StreamScanner
    public int handleStartElement(byte b) throws XMLStreamException {
        boolean zIsBound;
        boolean z;
        byte b2;
        int i;
        byte b3;
        int i2;
        this._currToken = 1;
        this._currNsCount = 0;
        PName pName = parsePName(b);
        String prefix = pName.getPrefix();
        if (prefix == null) {
            zIsBound = true;
        } else {
            pName = bindName(pName, prefix);
            zIsBound = pName.isBound();
        }
        this._tokenName = pName;
        this._currElem = new ElementScope(pName, this._currElem);
        int iCollectValue = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b4 = bArr[i3];
            int iDecodeCharForError = b4 & 255;
            if (iDecodeCharForError <= 32) {
                do {
                    if (iDecodeCharForError == 10) {
                        markLF();
                    } else if (iDecodeCharForError == 13) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr2 = this._inputBuffer;
                        int i4 = this._inputPtr;
                        if (bArr2[i4] == 10) {
                            this._inputPtr = i4 + 1;
                        }
                        markLF();
                    } else if (iDecodeCharForError != 32 && iDecodeCharForError != 9) {
                        throwInvalidSpace(iDecodeCharForError);
                    }
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr3 = this._inputBuffer;
                    int i5 = this._inputPtr;
                    this._inputPtr = i5 + 1;
                    b4 = bArr3[i5];
                    iDecodeCharForError = b4 & 255;
                } while (iDecodeCharForError <= 32);
            } else if (iDecodeCharForError != 47 && iDecodeCharForError != 62) {
                iDecodeCharForError = decodeCharForError(b4);
                throwUnexpectedChar(iDecodeCharForError, " expected space, or '>' or \"/>\"");
            }
            if (iDecodeCharForError == 47) {
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr4 = this._inputBuffer;
                int i6 = this._inputPtr;
                this._inputPtr = i6 + 1;
                byte b5 = bArr4[i6];
                if (b5 != 62) {
                    throwUnexpectedChar(decodeCharForError(b5), " expected '>'");
                }
                this._isEmptyTag = true;
                break;
            }
            if (iDecodeCharForError == 62) {
                this._isEmptyTag = false;
                break;
            }
            if (iDecodeCharForError == 60) {
                reportInputProblem("Unexpected '<' character in element (missing closing '>'?)");
            }
            PName pName2 = parsePName(b4);
            String prefix2 = pName2.getPrefix();
            if (prefix2 == null) {
                if (pName2.getLocalName() == "xmlns") {
                    z = true;
                } else {
                    z = false;
                }
            } else if (prefix2 == "xmlns") {
                z = true;
            } else {
                pName2 = bindName(pName2, prefix2);
                if (zIsBound) {
                    zIsBound = pName2.isBound();
                }
                z = false;
            }
            while (true) {
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i7 = this._inputPtr;
                int i8 = i7 + 1;
                this._inputPtr = i8;
                b2 = bArr5[i7];
                i = b2 & 255;
                if (i > 32) {
                    break;
                }
                if (i == 10) {
                    markLF();
                } else if (i == 13) {
                    if (i8 >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr6 = this._inputBuffer;
                    int i9 = this._inputPtr;
                    if (bArr6[i9] == 10) {
                        this._inputPtr = i9 + 1;
                    }
                    markLF();
                } else if (i != 32 && i != 9) {
                    throwInvalidSpace(i);
                }
            }
            if (i != 61) {
                throwUnexpectedChar(decodeCharForError(b2), " expected '='");
            }
            while (true) {
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr7 = this._inputBuffer;
                int i10 = this._inputPtr;
                int i11 = i10 + 1;
                this._inputPtr = i11;
                b3 = bArr7[i10];
                i2 = b3 & 255;
                if (i2 > 32) {
                    break;
                }
                if (i2 == 10) {
                    markLF();
                } else if (i2 == 13) {
                    if (i11 >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr8 = this._inputBuffer;
                    int i12 = this._inputPtr;
                    if (bArr8[i12] == 10) {
                        this._inputPtr = i12 + 1;
                    }
                    markLF();
                } else if (i2 != 32 && i2 != 9) {
                    throwInvalidSpace(i2);
                }
            }
            if (i2 != 34 && i2 != 39) {
                throwUnexpectedChar(decodeCharForError(b3), " Expected a quote");
            }
            if (z) {
                handleNsDeclaration(pName2, b3);
                this._currNsCount++;
            } else {
                iCollectValue = collectValue(iCollectValue, b3, pName2);
            }
        }
        int iFinishLastValue = this._attrCollector.finishLastValue(iCollectValue);
        if (iFinishLastValue < 0) {
            iFinishLastValue = this._attrCollector.getCount();
            reportInputProblem(this._attrCollector.getErrorMsg());
        }
        this._attrCount = iFinishLastValue;
        this._depth++;
        if (!zIsBound) {
            if (!pName.isBound()) {
                reportUnboundPrefix(this._tokenName, false);
            }
            int i13 = this._attrCount;
            for (int i14 = 0; i14 < i13; i14++) {
                PName name = this._attrCollector.getName(i14);
                if (!name.isBound()) {
                    reportUnboundPrefix(name, true);
                }
            }
        }
        return 1;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.aalto.in.StreamScanner
    public String parsePublicId(byte b) throws XMLStreamException {
        char[] cArrGrowArrayBy = this._nameBuffer;
        int[] iArr = XmlCharTypes.PUBID_CHARS;
        int i = 0;
        boolean z = false;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 == b) {
                return new String(cArrGrowArrayBy, 0, i);
            }
            int i3 = b2 & 255;
            if (iArr[i3] != 1) {
                throwUnexpectedChar(i3, " in public identifier");
            }
            if (i3 <= 32) {
                z = true;
            } else {
                if (z) {
                    if (i >= cArrGrowArrayBy.length) {
                        cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                        this._nameBuffer = cArrGrowArrayBy;
                        i = 0;
                    }
                    cArrGrowArrayBy[i] = ' ';
                    i++;
                    z = false;
                }
                if (i >= cArrGrowArrayBy.length) {
                    cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                    this._nameBuffer = cArrGrowArrayBy;
                    i = 0;
                }
                cArrGrowArrayBy[i] = (char) i3;
                i++;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0065  */
    /* JADX WARN: Code duplicated, block: B:26:0x0072  */
    /* JADX WARN: Code duplicated, block: B:35:0x007d A[SYNTHETIC] */
    @Override // com.fasterxml.aalto.in.StreamScanner
    public String parseSystemId(byte b) throws XMLStreamException {
        byte[] bArr;
        int i;
        char[] cArrGrowArrayBy = this._nameBuffer;
        int[] iArr = this._charTypes.ATTR_CHARS;
        int i2 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int iDecodeUtf8_2 = bArr2[i3] & 255;
            int i4 = iArr[iDecodeUtf8_2];
            if (i4 != 0) {
                if (i4 != 14) {
                    switch (i4) {
                        case 1:
                            handleInvalidXmlChar(iDecodeUtf8_2);
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            bArr = this._inputBuffer;
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            break;
                        case 2:
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            bArr = this._inputBuffer;
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            iDecodeUtf8_2 = 10;
                            break;
                        case XmlPullParser.END_TAG /* 3 */:
                            markLF();
                            break;
                        case 4:
                            reportInvalidInitial(iDecodeUtf8_2);
                            break;
                        case XmlPullParser.CDSECT /* 5 */:
                            iDecodeUtf8_2 = decodeUtf8_2(iDecodeUtf8_2);
                            break;
                        case XmlPullParser.ENTITY_REF /* 6 */:
                            iDecodeUtf8_2 = decodeUtf8_3(iDecodeUtf8_2);
                            break;
                        case 7:
                            int iDecodeUtf8_4 = decodeUtf8_4(iDecodeUtf8_2);
                            if (i2 >= cArrGrowArrayBy.length) {
                                cArrGrowArrayBy = this._textBuilder.finishCurrentSegment();
                                i2 = 0;
                            }
                            cArrGrowArrayBy[i2] = (char) ((iDecodeUtf8_4 >> 10) | 55296);
                            iDecodeUtf8_2 = 56320 | (iDecodeUtf8_4 & 1023);
                            i2++;
                            break;
                    }
                    if (iDecodeUtf8_2 == b) {
                        return new String(cArrGrowArrayBy, 0, i2);
                    }
                } else if (iDecodeUtf8_2 == b) {
                    return new String(cArrGrowArrayBy, 0, i2);
                }
            }
            if (i2 >= cArrGrowArrayBy.length) {
                cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                this._nameBuffer = cArrGrowArrayBy;
                i2 = 0;
            }
            cArrGrowArrayBy[i2] = (char) iDecodeUtf8_2;
            i2++;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportInvalidOther(int i, int i2) throws XMLStreamException {
        this._inputPtr = i2;
        reportInvalidOther(i);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:21:0x0046  */
    /* JADX WARN: Code duplicated, block: B:24:0x0051  */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void skipCData() throws XMLStreamException {
        int i;
        byte b;
        int i2;
        int[] iArr = this._charTypes.OTHER_CHARS;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i3 = this._inputPtr;
            int i4 = this._inputEnd;
            if (i3 >= i4) {
                loadMoreGuaranteed();
                i3 = this._inputPtr;
                i4 = this._inputEnd;
            }
            while (true) {
                if (i3 >= i4) {
                    this._inputPtr = i3;
                    break;
                }
                int i5 = i3 + 1;
                int i6 = bArr[i3] & 255;
                int i7 = iArr[i6];
                if (i7 != 0) {
                    this._inputPtr = i5;
                    if (i7 != 11) {
                        switch (i7) {
                            case 1:
                                handleInvalidXmlChar(i6);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i2 = this._inputPtr;
                                if (bArr[i2] == 10) {
                                    this._inputPtr = i2 + 1;
                                }
                                markLF();
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i2 = this._inputPtr;
                                if (bArr[i2] == 10) {
                                    this._inputPtr = i2 + 1;
                                }
                                markLF();
                                break;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                break;
                            case 4:
                                reportInvalidInitial(i6);
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                skipUtf8_2(i6);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                skipUtf8_3(i6);
                                break;
                            case 7:
                                skipUtf8_4(i6);
                                break;
                            default:
                                break;
                        }
                    }
                    int i8 = 0;
                    do {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        i8++;
                        byte[] bArr2 = this._inputBuffer;
                        i = this._inputPtr;
                        this._inputPtr = i + 1;
                        b = bArr2[i];
                    } while (b == 93);
                    if (b != 62) {
                        this._inputPtr = i;
                        break;
                    } else if (i8 <= 1) {
                        break;
                    } else {
                        return;
                    }
                }
                i3 = i5;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:36:0x0073  */
    /* JADX WARN: Code duplicated, block: B:39:0x007e  */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final boolean skipCharacters() throws XMLStreamException {
        int i;
        int[] iArr = this._charTypes.TEXT_CHARS;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            int i3 = this._inputEnd;
            if (i2 >= i3) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
                i3 = this._inputEnd;
            }
            while (true) {
                if (i2 >= i3) {
                    this._inputPtr = i2;
                    break;
                }
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                int i6 = iArr[i5];
                if (i6 != 0) {
                    this._inputPtr = i4;
                    switch (i6) {
                        case 1:
                            handleInvalidXmlChar(i5);
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            break;
                        case 2:
                            if (this._inputPtr >= this._inputEnd) {
                                loadMoreGuaranteed();
                            }
                            i = this._inputPtr;
                            if (bArr[i] == 10) {
                                this._inputPtr = i + 1;
                            }
                            markLF();
                            break;
                            break;
                        case XmlPullParser.END_TAG /* 3 */:
                            markLF();
                            break;
                        case 4:
                            reportInvalidInitial(i5);
                            break;
                        case XmlPullParser.CDSECT /* 5 */:
                            skipUtf8_2(i5);
                            break;
                        case XmlPullParser.ENTITY_REF /* 6 */:
                            skipUtf8_3(i5);
                            break;
                        case 7:
                            skipUtf8_4(i5);
                            break;
                        case 8:
                        default:
                            break;
                        case 9:
                            break;
                        case XmlPullParser.DOCDECL /* 10 */:
                            if (handleEntityInText(false) != 0) {
                                break;
                            }
                            return true;
                        case AndroidSdkVersion.HONEYCOMB /* 11 */:
                            int i7 = 1;
                            while (true) {
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                int i8 = this._inputPtr;
                                byte b = bArr[i8];
                                if (b != 93) {
                                    if (b == 62 && i7 > 1) {
                                        reportIllegalCDataEnd();
                                    }
                                }
                                this._inputPtr = i8 + 1;
                                i7++;
                                break;
                            }
                            break;
                    }
                } else {
                    i2 = i4;
                }
            }
        }
        this._inputPtr--;
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final boolean skipCoalescedText() throws XMLStreamException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return false;
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            if (bArr[i] == 60) {
                if (i + 3 >= this._inputEnd && !loadAndRetain(3)) {
                    return false;
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                if (bArr2[i2 + 1] != 33 || bArr2[i2 + 2] != 91) {
                    return false;
                }
                this._inputPtr = i2 + 3;
                for (int i3 = 0; i3 < 6; i3++) {
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr3 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    byte b = bArr3[i4];
                    if (b != ((byte) "CDATA[".charAt(i3))) {
                        reportTreeUnexpChar(decodeCharForError(b), " (expected '" + "CDATA[".charAt(i3) + "' for CDATA section)");
                    }
                }
                skipCData();
            } else if (skipCharacters()) {
                return true;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:21:0x0046  */
    /* JADX WARN: Code duplicated, block: B:24:0x0051  */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void skipComment() throws XMLStreamException {
        int i;
        int[] iArr = this._charTypes.OTHER_CHARS;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            int i3 = this._inputEnd;
            if (i2 >= i3) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
                i3 = this._inputEnd;
            }
            while (true) {
                if (i2 >= i3) {
                    this._inputPtr = i2;
                    break;
                }
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                int i6 = iArr[i5];
                if (i6 != 0) {
                    this._inputPtr = i4;
                    if (i6 != 13) {
                        switch (i6) {
                            case 1:
                                handleInvalidXmlChar(i5);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i = this._inputPtr;
                                if (bArr[i] == 10) {
                                    this._inputPtr = i + 1;
                                }
                                markLF();
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i = this._inputPtr;
                                if (bArr[i] == 10) {
                                    this._inputPtr = i + 1;
                                }
                                markLF();
                                break;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                break;
                            case 4:
                                reportInvalidInitial(i5);
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                skipUtf8_2(i5);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                skipUtf8_3(i5);
                                break;
                            case 7:
                                skipUtf8_4(i5);
                                break;
                            default:
                                break;
                        }
                    }
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i7 = this._inputPtr;
                    if (bArr2[i7] != 45) {
                        break;
                    }
                    int i8 = i7 + 1;
                    this._inputPtr = i8;
                    if (i8 >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr3 = this._inputBuffer;
                    int i9 = this._inputPtr;
                    this._inputPtr = i9 + 1;
                    if (bArr3[i9] != 62) {
                        reportDoubleHyphenInComments();
                        return;
                    }
                    return;
                }
                i2 = i4;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:21:0x0046  */
    /* JADX WARN: Code duplicated, block: B:24:0x0051  */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void skipPI() throws XMLStreamException {
        int i;
        int[] iArr = this._charTypes.OTHER_CHARS;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            int i3 = this._inputEnd;
            if (i2 >= i3) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
                i3 = this._inputEnd;
            }
            while (true) {
                if (i2 >= i3) {
                    this._inputPtr = i2;
                    break;
                }
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                int i6 = iArr[i5];
                if (i6 != 0) {
                    this._inputPtr = i4;
                    if (i6 != 12) {
                        switch (i6) {
                            case 1:
                                handleInvalidXmlChar(i5);
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i = this._inputPtr;
                                if (bArr[i] == 10) {
                                    this._inputPtr = i + 1;
                                }
                                markLF();
                                break;
                            case 2:
                                if (this._inputPtr >= this._inputEnd) {
                                    loadMoreGuaranteed();
                                }
                                i = this._inputPtr;
                                if (bArr[i] == 10) {
                                    this._inputPtr = i + 1;
                                }
                                markLF();
                                break;
                                break;
                            case XmlPullParser.END_TAG /* 3 */:
                                markLF();
                                break;
                            case 4:
                                reportInvalidInitial(i5);
                                break;
                            case XmlPullParser.CDSECT /* 5 */:
                                skipUtf8_2(i5);
                                break;
                            case XmlPullParser.ENTITY_REF /* 6 */:
                                skipUtf8_3(i5);
                                break;
                            case 7:
                                skipUtf8_4(i5);
                                break;
                            default:
                                break;
                        }
                    }
                    if (this._inputPtr >= this._inputEnd) {
                        loadMoreGuaranteed();
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i7 = this._inputPtr;
                    if (bArr2[i7] != 62) {
                        break;
                    }
                    this._inputPtr = i7 + 1;
                    return;
                }
                i2 = i4;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:12:0x001a  */
    /* JADX WARN: Code duplicated, block: B:19:0x002c  */
    /* JADX WARN: Code duplicated, block: B:23:0x0035  */
    /* JADX WARN: Code duplicated, block: B:26:0x003d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0032 A[EDGE_INSN: B:33:0x0032->B:21:0x0032 BREAK  A[LOOP:0: B:3:0x0002->B:41:0x0002], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x0024 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:35:0x0020 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x0032 A[EDGE_INSN: B:37:0x0032->B:21:0x0032 BREAK  A[LOOP:0: B:3:0x0002->B:41:0x0002], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:38:0x0028 A[SYNTHETIC] */
    @Override // com.fasterxml.aalto.in.XmlScanner
    public final void skipSpace() throws XMLStreamException {
        int i;
        int i2 = this._inputPtr;
        while (true) {
            if (i2 < this._inputEnd) {
                i = this._inputBuffer[i2] & 255;
                if (i > 32) {
                    break;
                }
                i2++;
                if (i == 10) {
                    markLF(i2);
                } else if (i == 13) {
                    if (i2 >= this._inputEnd) {
                        if (!loadMore()) {
                            break;
                        } else {
                            i2 = this._inputPtr;
                        }
                    }
                    if (this._inputBuffer[i2] == 10) {
                        i2++;
                    }
                    markLF(i2);
                } else if (i == 32 && i != 9) {
                    this._inputPtr = i2;
                    throwInvalidSpace(i);
                }
            } else {
                if (!loadMore()) {
                    break;
                }
                i2 = this._inputPtr;
                i = this._inputBuffer[i2] & 255;
                if (i > 32) {
                    break;
                    break;
                }
                i2++;
                if (i == 10) {
                    markLF(i2);
                } else if (i == 13) {
                    if (i2 >= this._inputEnd) {
                        if (!loadMore()) {
                            break;
                            break;
                        }
                        i2 = this._inputPtr;
                    }
                    if (this._inputBuffer[i2] == 10) {
                        i2++;
                    }
                    markLF(i2);
                } else if (i == 32) {
                }
            }
        }
        this._inputPtr = i2;
    }
}
