package kotlin.io.encoding;

import defpackage.dn0;
import defpackage.en0;
import defpackage.qf1;
import defpackage.rnd;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.IOException;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.AbstractList;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0017\u0018\u0000 F2\u00020\u0001:\u0002EFB)\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ \u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\bH\u0087\u0080\u0004b\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017J&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0086\u0080\u0004J8\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0086\u0080\u0004J&\u0010 \u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0086\u0080\u0004JE\u0010\"\u001a\u0002H#\"\f\b\u0000\u0010#*\u00060$j\u0002`%2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u0002H#2\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0087\u0080\bb\u0002\b'¢\u0006\u0002\u0010&J&\u0010(\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0086\u0080\u0004J8\u0010)\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0086\u0080\u0004J&\u0010(\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020*2\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0086\u0080\u0004J8\u0010)\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020*2\u0006\u0010\u001e\u001a\u00020\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u0006H\u0086\u0080\u0004J'\u0010+\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b,J7\u0010-\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b.J\u0017\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b1J\n\u00102\u001a\u00020\u0003H\u0082\u0080\u0004J2\u00103\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0082\u0080\u0004J'\u00104\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b5J'\u00106\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020*2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b7J\u0017\u00108\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u0019H\u0080\u0080\u0004¢\u0006\u0002\b9J*\u0010:\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010;\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u0006H\u0082\u0080\u0004J\u0012\u0010=\u001a\u00020>2\u0006\u0010;\u001a\u00020\u0006H\u0082\u0080\u0004J\"\u0010?\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0082\u0080\u0004J'\u0010@\u001a\u00020>2\u0006\u00100\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\bAJ\"\u0010B\u001a\u00020>2\u0006\u0010C\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010D\u001a\u00020\u0006H\u0082\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u00020\u0003X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0005\u001a\u00020\u0006X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0007\u001a\u00020\bX\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000f\u0010\u0012\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000Ê\u0001\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(HÊ\u0001\u0010\bI\u0012\f\bJ\u0012\b\b\fJ\u0004\b\t0K¨\u0006G"}, d2 = {"Lkotlin/io/encoding/Base64;", "", "isUrlSafe", "", "isMimeScheme", "mimeLineLength", "", "paddingOption", "Lkotlin/io/encoding/Base64$PaddingOption;", "<init>", "(ZZILkotlin/io/encoding/Base64$PaddingOption;)V", "isUrlSafe$kotlin_stdlib", "()Z", "isMimeScheme$kotlin_stdlib", "getMimeLineLength$kotlin_stdlib", "()I", "getPaddingOption$kotlin_stdlib", "()Lkotlin/io/encoding/Base64$PaddingOption;", "mimeGroupsPerLine", "withPadding", "option", "Lkotlin/SinceKotlin;", "version", "2.0", "encodeToByteArray", "", "source", "startIndex", "endIndex", "encodeIntoByteArray", "destination", "destinationOffset", "encode", "", "encodeToAppendable", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "([BLjava/lang/Appendable;II)Ljava/lang/Appendable;", "Lkotlin/IgnorableReturnValue;", "decode", "decodeIntoByteArray", "", "encodeToByteArrayImpl", "encodeToByteArrayImpl$kotlin_stdlib", "encodeIntoByteArrayImpl", "encodeIntoByteArrayImpl$kotlin_stdlib", "encodeSize", "sourceSize", "encodeSize$kotlin_stdlib", "shouldPadOnEncode", "decodeImpl", "decodeSize", "decodeSize$kotlin_stdlib", "charsToBytesImpl", "charsToBytesImpl$kotlin_stdlib", "bytesToStringImpl", "bytesToStringImpl$kotlin_stdlib", "handlePaddingSymbol", "padIndex", "byteStart", "checkPaddingIsAllowed", "", "skipIllegalSymbolsIfMime", "checkSourceBounds", "checkSourceBounds$kotlin_stdlib", "checkDestinationBounds", "destinationSize", "capacityNeeded", "PaddingOption", "Default", "kotlin-stdlib", "2.2", "Lkotlin/WasExperimental;", "markerClass", "Lkotlin/io/encoding/ExperimentalEncodingApi;"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public class Base64 {
    private static final Base64 Mime;
    private static final Base64 Pem;
    private static final Base64 UrlSafe;
    private static final int bitsPerByte = 8;
    private static final int bitsPerSymbol = 6;
    public static final int bytesPerGroup = 3;
    private static final int lineLengthMime = 76;
    private static final int lineLengthPem = 64;
    public static final byte padSymbol = 61;
    public static final int symbolsPerGroup = 4;
    private final boolean isMimeScheme;
    private final boolean isUrlSafe;
    private final int mimeGroupsPerLine;
    private final int mimeLineLength;
    private final PaddingOption paddingOption;

    /* JADX INFO: renamed from: Default, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte[] mimeLineSeparatorSymbols = {13, 10};

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007Ê\u0001\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b¨\u0006\b"}, d2 = {"Lkotlin/io/encoding/Base64$PaddingOption;", "", "<init>", "(Ljava/lang/String;I)V", "PRESENT", "ABSENT", "PRESENT_OPTIONAL", "ABSENT_OPTIONAL", "kotlin-stdlib", "Lkotlin/SinceKotlin;", "version", "2.0"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public enum PaddingOption {
        PRESENT,
        ABSENT,
        PRESENT_OPTIONAL,
        ABSENT_OPTIONAL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<PaddingOption> getEntries() {
            return $ENTRIES;
        }
    }

    static {
        PaddingOption paddingOption = PaddingOption.PRESENT;
        UrlSafe = new Base64(true, false, -1, paddingOption);
        Mime = new Base64(false, true, 76, paddingOption);
        Pem = new Base64(false, true, 64, paddingOption);
    }

    private Base64(boolean z, boolean z2, int i, PaddingOption paddingOption) {
        this.isUrlSafe = z;
        this.isMimeScheme = z2;
        this.mimeLineLength = i;
        this.paddingOption = paddingOption;
        if (z && z2) {
            w01.a("Failed requirement.");
            throw null;
        }
        this.mimeGroupsPerLine = i / 4;
    }

    private final void checkDestinationBounds(int destinationSize, int destinationOffset, int capacityNeeded) {
        if (destinationOffset < 0 || destinationOffset > destinationSize) {
            rnd.a("destination offset: ", destinationOffset, ", destination size: ", destinationSize);
            return;
        }
        int i = destinationOffset + capacityNeeded;
        if (i < 0 || i > destinationSize) {
            en0.a("The destination array does not have enough capacity, destination offset: ", destinationOffset, ", destination size: ", destinationSize, ", capacity needed: ", capacityNeeded);
        }
    }

    private final void checkPaddingIsAllowed(int padIndex) {
        if (this.paddingOption != PaddingOption.ABSENT) {
            return;
        }
        qf1.a("The padding option is set to ABSENT, but the input has a pad character at index ", padIndex);
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: decode");
            return null;
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return base64.decode(charSequence, i, i2);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0085  */
    /* JADX WARN: Code duplicated, block: B:22:0x0089  */
    /* JADX WARN: Code duplicated, block: B:54:0x00b8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:55:0x007e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x008c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x007c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x00c2 A[SYNTHETIC] */
    private final int decodeImpl(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArr = this.isUrlSafe ? Base64Kt.base64UrlDecodeMap : Base64Kt.base64DecodeMap;
        int i8 = destinationOffset;
        int iHandlePaddingSymbol = startIndex;
        int i9 = -8;
        int i10 = 0;
        while (true) {
            if (iHandlePaddingSymbol >= endIndex) {
                i = 8;
                i2 = 0;
                i3 = 0;
                break;
            }
            if (i9 != -8 || iHandlePaddingSymbol + 3 >= endIndex) {
                i = 8;
                i2 = 0;
                i4 = 1;
                i5 = source[iHandlePaddingSymbol] & UByte.MAX_VALUE;
                i6 = iArr[i5];
                if (i6 < 0) {
                    iHandlePaddingSymbol++;
                    i10 = (i10 << 6) | i6;
                    i7 = i9 + 6;
                    if (i7 >= 0) {
                        destination[i8] = (byte) (i10 >>> i7);
                        i10 &= (i4 << i7) - 1;
                        i9 -= 2;
                        i8++;
                    } else {
                        i9 = i7;
                    }
                } else {
                    if (i6 == -2) {
                        iHandlePaddingSymbol = handlePaddingSymbol(source, iHandlePaddingSymbol, endIndex, i9);
                        i3 = i4;
                        break;
                    }
                    if (this.isMimeScheme) {
                        String string = Integer.toString(i5, CharsKt.checkRadix(i));
                        string.getClass();
                        throw new IllegalArgumentException("Invalid symbol '" + ((char) i5) + "'(" + string + ") at index " + iHandlePaddingSymbol);
                    }
                    iHandlePaddingSymbol++;
                }
            } else {
                i2 = 0;
                i = 8;
                int i11 = iHandlePaddingSymbol + 4;
                i4 = 1;
                int i12 = (iArr[source[iHandlePaddingSymbol] & UByte.MAX_VALUE] << 18) | (iArr[source[iHandlePaddingSymbol + 1] & UByte.MAX_VALUE] << 12) | (iArr[source[iHandlePaddingSymbol + 2] & UByte.MAX_VALUE] << 6) | iArr[source[iHandlePaddingSymbol + 3] & UByte.MAX_VALUE];
                if (i12 >= 0) {
                    destination[i8] = (byte) (i12 >> 16);
                    int i13 = i8 + 2;
                    destination[i8 + 1] = (byte) (i12 >> 8);
                    i8 += 3;
                    destination[i13] = (byte) i12;
                    iHandlePaddingSymbol = i11;
                } else {
                    i5 = source[iHandlePaddingSymbol] & UByte.MAX_VALUE;
                    i6 = iArr[i5];
                    if (i6 < 0) {
                        iHandlePaddingSymbol++;
                        i10 = (i10 << 6) | i6;
                        i7 = i9 + 6;
                        if (i7 >= 0) {
                            destination[i8] = (byte) (i10 >>> i7);
                            i10 &= (i4 << i7) - 1;
                            i9 -= 2;
                            i8++;
                        } else {
                            i9 = i7;
                        }
                    } else {
                        if (i6 == -2) {
                            iHandlePaddingSymbol = handlePaddingSymbol(source, iHandlePaddingSymbol, endIndex, i9);
                            i3 = i4;
                            break;
                        }
                        if (this.isMimeScheme) {
                            String string2 = Integer.toString(i5, CharsKt.checkRadix(i));
                            string2.getClass();
                            throw new IllegalArgumentException("Invalid symbol '" + ((char) i5) + "'(" + string2 + ") at index " + iHandlePaddingSymbol);
                        }
                        iHandlePaddingSymbol++;
                    }
                }
            }
        }
        if (i9 == -2) {
            w01.a("The last unit of input does not have enough bits");
            return i2;
        }
        if (i9 != -8 && i3 == 0 && this.paddingOption == PaddingOption.PRESENT) {
            w01.a("The padding option is set to PRESENT, but the input is not properly padded");
            return i2;
        }
        if (i10 != 0) {
            w01.a("The pad bits must be zeros");
            return i2;
        }
        int iSkipIllegalSymbolsIfMime = skipIllegalSymbolsIfMime(source, iHandlePaddingSymbol, endIndex);
        if (iSkipIllegalSymbolsIfMime >= endIndex) {
            return i8 - destinationOffset;
        }
        int i14 = source[iSkipIllegalSymbolsIfMime] & UByte.MAX_VALUE;
        char c = (char) i14;
        String string3 = Integer.toString(i14, CharsKt.checkRadix(i));
        string3.getClass();
        StringBuilder sb = new StringBuilder("Symbol '");
        sb.append(c);
        sb.append("'(");
        sb.append(string3);
        sb.append(") at index ");
        sb.append(iSkipIllegalSymbolsIfMime - 1);
        sb.append(" is prohibited after the pad character");
        throw new IllegalArgumentException(sb.toString());
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, CharSequence charSequence, byte[] bArr, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
            return 0;
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = charSequence.length();
        }
        return base64.decodeIntoByteArray(charSequence, bArr, i, i2, i3);
    }

    public static /* synthetic */ String encode$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: encode");
            return null;
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.encode(bArr, i, i2);
    }

    public static /* synthetic */ int encodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: encodeIntoByteArray");
            return 0;
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return base64.encodeIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    public static /* synthetic */ Appendable encodeToAppendable$default(Base64 base64, byte[] bArr, Appendable appendable, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: encodeToAppendable");
            return null;
        }
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        return base64.encodeToAppendable(bArr, appendable, i, i2);
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: encodeToByteArray");
            return null;
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.encodeToByteArray(bArr, i, i2);
    }

    private final int handlePaddingSymbol(byte[] source, int padIndex, int endIndex, int byteStart) {
        if (byteStart == -8) {
            qf1.a("Redundant pad character at index ", padIndex);
            return 0;
        }
        if (byteStart == -6) {
            checkPaddingIsAllowed(padIndex);
            return padIndex + 1;
        }
        if (byteStart != -4) {
            if (byteStart == -2) {
                return padIndex + 1;
            }
            k2d.a("Unreachable");
            return 0;
        }
        checkPaddingIsAllowed(padIndex);
        int iSkipIllegalSymbolsIfMime = skipIllegalSymbolsIfMime(source, padIndex + 1, endIndex);
        if (iSkipIllegalSymbolsIfMime != endIndex && source[iSkipIllegalSymbolsIfMime] == 61) {
            return iSkipIllegalSymbolsIfMime + 1;
        }
        qf1.a("Missing one pad character at index ", iSkipIllegalSymbolsIfMime);
        return 0;
    }

    private final boolean shouldPadOnEncode() {
        PaddingOption paddingOption = this.paddingOption;
        return paddingOption == PaddingOption.PRESENT || paddingOption == PaddingOption.PRESENT_OPTIONAL;
    }

    private final int skipIllegalSymbolsIfMime(byte[] source, int startIndex, int endIndex) {
        if (!this.isMimeScheme) {
            return startIndex;
        }
        while (startIndex < endIndex) {
            if (Base64Kt.base64DecodeMap[source[startIndex] & UByte.MAX_VALUE] != -1) {
                break;
            }
            startIndex++;
        }
        return startIndex;
    }

    public final String bytesToStringImpl$kotlin_stdlib(byte[] source) {
        source.getClass();
        StringBuilder sb = new StringBuilder(source.length);
        for (byte b : source) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    public final byte[] charsToBytesImpl$kotlin_stdlib(CharSequence source, int startIndex, int endIndex) {
        source.getClass();
        checkSourceBounds$kotlin_stdlib(source.length(), startIndex, endIndex);
        byte[] bArr = new byte[endIndex - startIndex];
        int i = 0;
        while (startIndex < endIndex) {
            char cCharAt = source.charAt(startIndex);
            if (cCharAt <= 255) {
                bArr[i] = (byte) cCharAt;
                i++;
            } else {
                bArr[i] = 63;
                i++;
            }
            startIndex++;
        }
        return bArr;
    }

    public final void checkSourceBounds$kotlin_stdlib(int sourceSize, int startIndex, int endIndex) {
        AbstractList.INSTANCE.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, sourceSize);
    }

    public final byte[] decode(CharSequence source, int startIndex, int endIndex) {
        byte[] bArrCharsToBytesImpl$kotlin_stdlib;
        source.getClass();
        if (source instanceof String) {
            String str = (String) source;
            checkSourceBounds$kotlin_stdlib(str.length(), startIndex, endIndex);
            bArrCharsToBytesImpl$kotlin_stdlib = str.substring(startIndex, endIndex).getBytes(Charsets.ISO_8859_1);
            bArrCharsToBytesImpl$kotlin_stdlib.getClass();
        } else {
            bArrCharsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
        }
        return decode$default(this, bArrCharsToBytesImpl$kotlin_stdlib, 0, 0, 6, (Object) null);
    }

    public final int decodeIntoByteArray(CharSequence source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        byte[] bArrCharsToBytesImpl$kotlin_stdlib;
        source.getClass();
        destination.getClass();
        if (source instanceof String) {
            String str = (String) source;
            checkSourceBounds$kotlin_stdlib(str.length(), startIndex, endIndex);
            bArrCharsToBytesImpl$kotlin_stdlib = str.substring(startIndex, endIndex).getBytes(Charsets.ISO_8859_1);
            bArrCharsToBytesImpl$kotlin_stdlib.getClass();
        } else {
            bArrCharsToBytesImpl$kotlin_stdlib = charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
        }
        return decodeIntoByteArray$default(this, bArrCharsToBytesImpl$kotlin_stdlib, destination, destinationOffset, 0, 0, 24, (Object) null);
    }

    public final int decodeSize$kotlin_stdlib(byte[] source, int startIndex, int endIndex) {
        source.getClass();
        int i = endIndex - startIndex;
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            dn0.a("Input should have at least 2 symbols for Base64 decoding, startIndex: ", startIndex, ", endIndex: ", endIndex);
            return 0;
        }
        if (this.isMimeScheme) {
            while (startIndex < endIndex) {
                int i2 = Base64Kt.base64DecodeMap[source[startIndex] & UByte.MAX_VALUE];
                if (i2 < 0) {
                    if (i2 == -2) {
                        i -= endIndex - startIndex;
                        break;
                    }
                    i--;
                }
                startIndex++;
            }
        } else if (source[endIndex - 1] == 61) {
            i = source[endIndex + (-2)] == 61 ? i - 2 : i - 1;
        }
        return (int) ((((long) i) * 6) / 8);
    }

    public final String encode(byte[] source, int startIndex, int endIndex) {
        source.getClass();
        return new String(encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex), Charsets.ISO_8859_1);
    }

    public final int encodeIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        source.getClass();
        destination.getClass();
        return encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, destinationOffset, startIndex, endIndex);
    }

    public final int encodeIntoByteArrayImpl$kotlin_stdlib(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        int i = startIndex;
        source.getClass();
        destination.getClass();
        checkSourceBounds$kotlin_stdlib(source.length, i, endIndex);
        checkDestinationBounds(destination.length, destinationOffset, encodeSize$kotlin_stdlib(endIndex - i));
        byte[] bArr = this.isUrlSafe ? Base64Kt.base64UrlEncodeMap : Base64Kt.base64EncodeMap;
        int i2 = this.isMimeScheme ? this.mimeGroupsPerLine : Integer.MAX_VALUE;
        int i3 = destinationOffset;
        while (i + 2 < endIndex) {
            int iMin = Math.min((endIndex - i) / 3, i2);
            for (int i4 = 0; i4 < iMin; i4++) {
                int i5 = source[i] & UByte.MAX_VALUE;
                int i6 = i + 2;
                int i7 = source[i + 1] & UByte.MAX_VALUE;
                i += 3;
                int i8 = (i7 << 8) | (i5 << 16) | (source[i6] & UByte.MAX_VALUE);
                destination[i3] = bArr[i8 >>> 18];
                destination[i3 + 1] = bArr[(i8 >>> 12) & 63];
                int i9 = i3 + 3;
                destination[i3 + 2] = bArr[(i8 >>> 6) & 63];
                i3 += 4;
                destination[i9] = bArr[i8 & 63];
            }
            if (iMin == i2 && i != endIndex) {
                int i10 = i3 + 1;
                byte[] bArr2 = mimeLineSeparatorSymbols;
                destination[i3] = bArr2[0];
                i3 += 2;
                destination[i10] = bArr2[1];
            }
        }
        int i11 = endIndex - i;
        if (i11 == 1) {
            int i12 = i + 1;
            int i13 = (source[i] & UByte.MAX_VALUE) << 4;
            destination[i3] = bArr[i13 >>> 6];
            int i14 = i3 + 2;
            destination[i3 + 1] = bArr[i13 & 63];
            if (shouldPadOnEncode()) {
                int i15 = i3 + 3;
                destination[i14] = padSymbol;
                i3 += 4;
                destination[i15] = padSymbol;
                i = i12;
            } else {
                i = i12;
                i3 = i14;
            }
        } else if (i11 == 2) {
            int i16 = i + 1;
            int i17 = source[i] & UByte.MAX_VALUE;
            i += 2;
            int i18 = ((source[i16] & UByte.MAX_VALUE) << 2) | (i17 << 10);
            destination[i3] = bArr[i18 >>> 12];
            destination[i3 + 1] = bArr[(i18 >>> 6) & 63];
            int i19 = i3 + 3;
            destination[i3 + 2] = bArr[i18 & 63];
            if (shouldPadOnEncode()) {
                i3 += 4;
                destination[i19] = padSymbol;
            } else {
                i3 = i19;
            }
        }
        if (i == endIndex) {
            return i3 - destinationOffset;
        }
        k2d.a("Check failed.");
        return 0;
    }

    public final int encodeSize$kotlin_stdlib(int sourceSize) {
        int i = sourceSize / 3;
        int i2 = sourceSize % 3;
        int i3 = i * 4;
        if (i2 != 0) {
            i3 += shouldPadOnEncode() ? 4 : i2 + 1;
        }
        if (i3 < 0) {
            w01.a("Input is too big");
            return 0;
        }
        if (this.isMimeScheme) {
            i3 += ((i3 - 1) / this.mimeLineLength) * 2;
        }
        if (i3 >= 0) {
            return i3;
        }
        w01.a("Input is too big");
        return 0;
    }

    @IgnorableReturnValue
    public final <A extends Appendable> A encodeToAppendable(byte[] source, A destination, int startIndex, int endIndex) throws IOException {
        source.getClass();
        destination.getClass();
        destination.append(new String(encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex), Charsets.ISO_8859_1));
        return destination;
    }

    public final byte[] encodeToByteArray(byte[] source, int startIndex, int endIndex) {
        source.getClass();
        return encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex);
    }

    public final byte[] encodeToByteArrayImpl$kotlin_stdlib(byte[] source, int startIndex, int endIndex) {
        source.getClass();
        checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
        byte[] bArr = new byte[encodeSize$kotlin_stdlib(endIndex - startIndex)];
        encodeIntoByteArrayImpl$kotlin_stdlib(source, bArr, 0, startIndex, endIndex);
        return bArr;
    }

    /* JADX INFO: renamed from: getMimeLineLength$kotlin_stdlib, reason: from getter */
    public final int getMimeLineLength() {
        return this.mimeLineLength;
    }

    /* JADX INFO: renamed from: getPaddingOption$kotlin_stdlib, reason: from getter */
    public final PaddingOption getPaddingOption() {
        return this.paddingOption;
    }

    /* JADX INFO: renamed from: isMimeScheme$kotlin_stdlib, reason: from getter */
    public final boolean getIsMimeScheme() {
        return this.isMimeScheme;
    }

    /* JADX INFO: renamed from: isUrlSafe$kotlin_stdlib, reason: from getter */
    public final boolean getIsUrlSafe() {
        return this.isUrlSafe;
    }

    public final Base64 withPadding(PaddingOption option) {
        option.getClass();
        return this.paddingOption == option ? this : new Base64(this.isUrlSafe, this.isMimeScheme, this.mimeLineLength, option);
    }

    /* JADX INFO: renamed from: kotlin.io.encoding.Base64$Default, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\nX\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u0015\u0010\r\u001a\u00020\u000eX\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0011\u001a\u00020\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00020\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0015\u0010\u0016\u001a\u00020\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006\u0018"}, d2 = {"Lkotlin/io/encoding/Base64$Default;", "Lkotlin/io/encoding/Base64;", "<init>", "()V", "bitsPerByte", "", "bitsPerSymbol", "bytesPerGroup", "symbolsPerGroup", "padSymbol", "", "lineLengthMime", "lineLengthPem", "mimeLineSeparatorSymbols", "", "getMimeLineSeparatorSymbols$kotlin_stdlib", "()[B", "UrlSafe", "getUrlSafe", "()Lkotlin/io/encoding/Base64;", "Mime", "getMime", "Pem", "getPem", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
    public static final class Companion extends Base64 {
        private Companion() {
            super(false, false, -1, PaddingOption.PRESENT, null);
        }

        public final Base64 getMime() {
            return Base64.Mime;
        }

        public final byte[] getMimeLineSeparatorSymbols$kotlin_stdlib() {
            return Base64.mimeLineSeparatorSymbols;
        }

        public final Base64 getPem() {
            return Base64.Pem;
        }

        public final Base64 getUrlSafe() {
            return Base64.UrlSafe;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static /* synthetic */ byte[] decode$default(Base64 base64, byte[] bArr, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: decode");
            return null;
        }
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return base64.decode(bArr, i, i2);
    }

    public /* synthetic */ Base64(boolean z, boolean z2, int i, PaddingOption paddingOption, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, i, paddingOption);
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 base64, byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
            return 0;
        }
        if ((i4 & 4) != 0) {
            i = 0;
        }
        if ((i4 & 8) != 0) {
            i2 = 0;
        }
        if ((i4 & 16) != 0) {
            i3 = bArr.length;
        }
        return base64.decodeIntoByteArray(bArr, bArr2, i, i2, i3);
    }

    public final byte[] decode(byte[] source, int startIndex, int endIndex) {
        source.getClass();
        checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
        int iDecodeSize$kotlin_stdlib = decodeSize$kotlin_stdlib(source, startIndex, endIndex);
        byte[] bArr = new byte[iDecodeSize$kotlin_stdlib];
        if (decodeImpl(source, bArr, 0, startIndex, endIndex) == iDecodeSize$kotlin_stdlib) {
            return bArr;
        }
        k2d.a("Check failed.");
        return null;
    }

    public final int decodeIntoByteArray(byte[] source, byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        source.getClass();
        destination.getClass();
        checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
        checkDestinationBounds(destination.length, destinationOffset, decodeSize$kotlin_stdlib(source, startIndex, endIndex));
        return decodeImpl(source, destination, destinationOffset, startIndex, endIndex);
    }
}
