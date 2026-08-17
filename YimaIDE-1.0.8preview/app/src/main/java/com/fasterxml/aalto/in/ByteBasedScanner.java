package com.fasterxml.aalto.in;

import com.fasterxml.aalto.impl.LocationImpl;
import com.fasterxml.aalto.util.DataUtil;
import com.fasterxml.aalto.util.XmlCharTypes;
import com.fasterxml.aalto.util.XmlChars;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.XMLStreamLocation2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ByteBasedScanner extends XmlScanner {
    protected int _inputEnd;
    protected int _inputPtr;
    protected int _tmpChar;

    public ByteBasedScanner(ReaderConfig readerConfig) {
        super(readerConfig);
        this._tmpChar = 0;
        this._pastBytesOrChars = 0L;
        this._rowStartOffset = 0;
    }

    @Override // com.fasterxml.aalto.in.XmlScanner
    public abstract void _closeSource() throws IOException;

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    /* JADX WARN: Code duplicated, block: B:43:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:85:0x0187 A[PHI: r4 r10
      0x0187: PHI (r4v19 int) = (r4v18 int), (r4v25 int) binds: [B:75:0x0146, B:80:0x0165] A[DONT_GENERATE, DONT_INLINE]
      0x0187: PHI (r10v9 int) = (r10v8 int), (r10v14 int) binds: [B:75:0x0146, B:80:0x0165] A[DONT_GENERATE, DONT_INLINE]] */
    public final PName addUTFPName(ByteBasedPNameTable byteBasedPNameTable, XmlCharTypes xmlCharTypes, int i, int[] iArr, int i2, int i3) throws XMLStreamException {
        int i4;
        int i5;
        int i6;
        int i7;
        boolean zIs10NameStartChar;
        int i8;
        boolean z;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = ((i2 << 2) - 4) + i3;
        int i14 = 0;
        if (i3 < 4) {
            int i15 = i2 - 1;
            i4 = iArr[i15];
            iArr[i15] = i4 << ((4 - i3) << 3);
        } else {
            i4 = 0;
        }
        int i16 = iArr[0] >>> 24;
        char[] cArrGrowArrayBy = this._nameBuffer;
        int[] iArr2 = xmlCharTypes.NAME_CHARS;
        int i17 = iArr2[i16];
        if (i17 == 0 || i17 == 1 || i17 == 2) {
            i5 = 0;
            i6 = 3;
            i7 = 1;
            zIs10NameStartChar = false;
            i14 = 0;
        } else if (i17 == 3) {
            i5 = 0;
            i6 = 3;
            zIs10NameStartChar = true;
            i7 = 1;
        } else if (i17 != 4) {
            if ((i16 & 224) == 192) {
                i11 = i16 & 31;
                i12 = 1;
            } else if ((i16 & 240) == 224) {
                i11 = i16 & 15;
                i12 = 2;
            } else if ((i16 & 248) == 240) {
                i11 = i16 & 7;
                i12 = 3;
            } else {
                reportInvalidInitial(i16);
                i11 = 1;
                i12 = 1;
            }
            i7 = 1 + i12;
            if (i7 > i13) {
                reportEofInName(cArrGrowArrayBy, 0);
            }
            int i18 = iArr[0];
            int i19 = i18 >> 16;
            int i20 = i19 & 255;
            i6 = 3;
            i5 = 0;
            if ((i19 & 192) != 128) {
                reportInvalidOther(i20);
            }
            int i21 = (i11 << 6) | (i19 & 63);
            if (i12 > 1) {
                int i22 = i18 >> 8;
                int i23 = i22 & 255;
                if ((i22 & 192) != 128) {
                    reportInvalidOther(i23);
                }
                i21 = (i21 << 6) | (i22 & 63);
                if (i12 > 2) {
                    if ((i18 & 192) != 128) {
                        reportInvalidOther(i18 & 255);
                    }
                    i21 = (i21 << 6) | (i18 & 63);
                }
            }
            i16 = i21;
            zIs10NameStartChar = XmlChars.is10NameStartChar(i16);
            if (i12 > 2) {
                int i24 = i16 - 65536;
                cArrGrowArrayBy[0] = (char) ((i24 >> 10) + 55296);
                i16 = (i24 & 1023) | 56320;
                i14 = 1;
            } else {
                i14 = 0;
            }
        } else {
            i5 = 0;
            i6 = 3;
            i7 = 1;
            zIs10NameStartChar = false;
            i14 = 0;
        }
        if (!zIs10NameStartChar) {
            reportInvalidNameChar(i16, i5);
        }
        int i25 = i14 + 1;
        cArrGrowArrayBy[i14] = (char) i16;
        int i26 = -1;
        while (i7 < i13) {
            int i27 = iArr[i7 >> 2] >> ((3 - (i7 & 3)) << 3);
            int i28 = i27 & 255;
            i7++;
            int i29 = iArr2[i28];
            if (i29 == 0) {
                i28 = i28;
                i7 = i7;
                i8 = i25;
                z = false;
            } else {
                if (i29 != 1) {
                    if (i29 != 2) {
                        int i30 = i6;
                        if (i29 == i30) {
                            i6 = i30;
                        } else if (i29 != 4) {
                            if ((i27 & 224) == 192) {
                                i9 = i27 & 31;
                                i10 = 1;
                            } else if ((i27 & 240) == 224) {
                                i9 = i27 & 15;
                                i10 = 2;
                            } else if ((i27 & 248) == 240) {
                                i9 = i27 & 7;
                                i10 = 3;
                            } else {
                                reportInvalidInitial(i28);
                                i9 = 1;
                                i10 = 1;
                            }
                            if (i7 + i10 > i13) {
                                reportEofInName(cArrGrowArrayBy, i25);
                            }
                            int i31 = iArr[i7 >> 2] >> ((3 - (i7 & 3)) << 3);
                            int i32 = i7 + 2;
                            if ((i31 & 192) != 128) {
                                reportInvalidOther(i31);
                            }
                            i28 = (i9 << 6) | (i31 & 63);
                            if (i10 > 1) {
                                int i33 = iArr[i32 >> 2] >> ((3 - (i32 & 3)) << 3);
                                i32 = i7 + 3;
                                if ((i33 & 192) != 128) {
                                    reportInvalidOther(i33);
                                }
                                i28 = (i28 << 6) | (i33 & 63);
                                if (i10 > 2) {
                                    i6 = 3;
                                    int i34 = iArr[i32 >> 2] >> ((3 - (i32 & 3)) << 3);
                                    i32 = i7 + 4;
                                    if ((i34 & 192) != 128) {
                                        reportInvalidOther(i34 & 255);
                                    }
                                    i28 = (i28 << 6) | (i34 & 63);
                                } else {
                                    i6 = 3;
                                }
                            } else {
                                i6 = 3;
                            }
                            boolean zIs10NameChar = XmlChars.is10NameChar(i28);
                            if (i10 > 2) {
                                int i35 = i28 - 65536;
                                if (i25 >= cArrGrowArrayBy.length) {
                                    cArrGrowArrayBy = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                                    this._nameBuffer = cArrGrowArrayBy;
                                }
                                i8 = i25 + 1;
                                cArrGrowArrayBy[i25] = (char) ((i35 >> 10) + 55296);
                                i28 = (i35 & 1023) | 56320;
                            } else {
                                i8 = i25;
                            }
                            z = zIs10NameChar;
                            i7 = i32;
                        } else {
                            i6 = 3;
                            i28 = i28;
                            i7 = i7;
                            i8 = i25;
                            z = false;
                        }
                    }
                    i8 = i25;
                } else {
                    if (i26 >= 0) {
                        reportMultipleColonsInName();
                    }
                    i26 = i25;
                    i8 = i26;
                }
                z = true;
            }
            if (!z) {
                reportInvalidNameChar(i28, i8);
            }
            if (i8 >= cArrGrowArrayBy.length) {
                char[] cArrGrowArrayBy2 = DataUtil.growArrayBy(cArrGrowArrayBy, cArrGrowArrayBy.length);
                this._nameBuffer = cArrGrowArrayBy2;
                cArrGrowArrayBy = cArrGrowArrayBy2;
            }
            i25 = i8 + 1;
            cArrGrowArrayBy[i8] = (char) i28;
        }
        String str = new String(cArrGrowArrayBy, 0, i25);
        if (i3 < 4) {
            iArr[i2 - 1] = i4;
        }
        return byteBasedPNameTable.addSymbol(i, str, i26, iArr, i2);
    }

    public abstract int decodeCharForError(byte b) throws XMLStreamException;

    @Override // com.fasterxml.aalto.in.XmlScanner
    public XMLStreamLocation2 getCurrentLocation() {
        String publicId = this._config.getPublicId();
        String systemId = this._config.getSystemId();
        long j = this._pastBytesOrChars;
        int i = this._inputPtr;
        return LocationImpl.fromZeroBased(publicId, systemId, j + ((long) i), this._currRow, i - this._rowStartOffset);
    }

    public final void markLF() {
        this._rowStartOffset = this._inputPtr;
        this._currRow++;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportInvalidInitial(int i) throws XMLStreamException {
        reportInputProblem("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void reportInvalidOther(int i) throws XMLStreamException {
        reportInputProblem("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    public final void setStartLocation() {
        long j = this._pastBytesOrChars;
        int i = this._inputPtr;
        this._startRawOffset = j + ((long) i);
        this._startRow = this._currRow;
        this._startColumn = i - this._rowStartOffset;
    }

    public final void markLF(int i) {
        this._rowStartOffset = i;
        this._currRow++;
    }
}
