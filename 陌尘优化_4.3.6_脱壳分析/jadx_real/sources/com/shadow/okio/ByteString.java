package com.shadow.okio;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.text.Charsets;
import com.shadow.kotlin.text.StringsKt;
import core.pro.android.notify.h;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: /workspace/unpacked/classes2.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    public static final Companion Companion = new Companion(null);
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;
    private final byte[] data;
    private transient int hashCode;
    private transient String utf8;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ByteString encodeString$default(Companion companion, String str, Charset charset, int i, Object obj) {
            if ((i & 1) != 0) {
                charset = Charsets.UTF_8;
            }
            return companion.encodeString(str, charset);
        }

        public static /* synthetic */ ByteString of$default(Companion companion, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = SegmentedByteString.getDEFAULT__ByteString_size();
            }
            return companion.of(bArr, i, i2);
        }

        /* renamed from: -deprecated_decodeBase64, reason: not valid java name */
        public final ByteString m161deprecated_decodeBase64(String str) {
            CloseableKt.checkNotNullParameter(str, "string");
            return decodeBase64(str);
        }

        /* renamed from: -deprecated_decodeHex, reason: not valid java name */
        public final ByteString m162deprecated_decodeHex(String str) {
            CloseableKt.checkNotNullParameter(str, "string");
            return decodeHex(str);
        }

        /* renamed from: -deprecated_encodeString, reason: not valid java name */
        public final ByteString m163deprecated_encodeString(String str, Charset charset) {
            CloseableKt.checkNotNullParameter(str, "string");
            CloseableKt.checkNotNullParameter(charset, "charset");
            return encodeString(str, charset);
        }

        /* renamed from: -deprecated_encodeUtf8, reason: not valid java name */
        public final ByteString m164deprecated_encodeUtf8(String str) {
            CloseableKt.checkNotNullParameter(str, "string");
            return encodeUtf8(str);
        }

        /* renamed from: -deprecated_of, reason: not valid java name */
        public final ByteString m165deprecated_of(ByteBuffer byteBuffer) {
            CloseableKt.checkNotNullParameter(byteBuffer, "buffer");
            return of(byteBuffer);
        }

        /* renamed from: -deprecated_read, reason: not valid java name */
        public final ByteString m167deprecated_read(InputStream inputStream, int i) {
            CloseableKt.checkNotNullParameter(inputStream, "inputstream");
            return read(inputStream, i);
        }

        public final ByteString decodeBase64(String str) {
            CloseableKt.checkNotNullParameter(str, "<this>");
            byte[] bArrDecodeBase64ToArray = Base64.decodeBase64ToArray(str);
            if (bArrDecodeBase64ToArray != null) {
                return new ByteString(bArrDecodeBase64ToArray);
            }
            return null;
        }

        public final ByteString decodeHex(String str) {
            CloseableKt.checkNotNullParameter(str, "<this>");
            if (str.length() % 2 != 0) {
                throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
            }
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) (com.shadow.okio.internal.ByteString.decodeHexDigit(str.charAt(i2 + 1)) + (com.shadow.okio.internal.ByteString.decodeHexDigit(str.charAt(i2)) << 4));
            }
            return new ByteString(bArr);
        }

        public final ByteString encodeString(String str, Charset charset) {
            CloseableKt.checkNotNullParameter(str, "<this>");
            CloseableKt.checkNotNullParameter(charset, "charset");
            byte[] bytes = str.getBytes(charset);
            CloseableKt.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return new ByteString(bytes);
        }

        public final ByteString encodeUtf8(String str) {
            CloseableKt.checkNotNullParameter(str, "<this>");
            ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
            byteString.setUtf8$okio(str);
            return byteString;
        }

        public final ByteString of(ByteBuffer byteBuffer) {
            CloseableKt.checkNotNullParameter(byteBuffer, "<this>");
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return new ByteString(bArr);
        }

        public final ByteString read(InputStream inputStream, int i) throws IOException {
            CloseableKt.checkNotNullParameter(inputStream, "<this>");
            if (i < 0) {
                throw new IllegalArgumentException(h.a(i, "byteCount < 0: ").toString());
            }
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int i3 = inputStream.read(bArr, i2, i - i2);
                if (i3 == -1) {
                    throw new EOFException();
                }
                i2 += i3;
            }
            return new ByteString(bArr);
        }

        private Companion() {
        }

        /* renamed from: -deprecated_of, reason: not valid java name */
        public final ByteString m166deprecated_of(byte[] bArr, int i, int i2) {
            CloseableKt.checkNotNullParameter(bArr, "array");
            return of(bArr, i, i2);
        }

        public final ByteString of(byte... bArr) {
            CloseableKt.checkNotNullParameter(bArr, "data");
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
            CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            return new ByteString(bArrCopyOf);
        }

        public final ByteString of(byte[] bArr, int i, int i2) {
            CloseableKt.checkNotNullParameter(bArr, "<this>");
            int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(bArr, i2);
            SegmentedByteString.checkOffsetAndCount(bArr.length, i, iResolveDefaultParameter);
            return new ByteString(ArraysKt.d(bArr, i, iResolveDefaultParameter + i));
        }
    }

    public ByteString(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "data");
        this.data = bArr;
    }

    public static /* synthetic */ void copyInto$default(ByteString byteString, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copyInto");
        }
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        byteString.copyInto(i, bArr, i2, i3);
    }

    public static final ByteString decodeBase64(String str) {
        return Companion.decodeBase64(str);
    }

    public static final ByteString decodeHex(String str) {
        return Companion.decodeHex(str);
    }

    public static final ByteString encodeString(String str, Charset charset) {
        return Companion.encodeString(str, charset);
    }

    public static final ByteString encodeUtf8(String str) {
        return Companion.encodeUtf8(str);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(byteString2, i);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = SegmentedByteString.getDEFAULT__ByteString_size();
        }
        return byteString.lastIndexOf(byteString2, i);
    }

    public static final ByteString of(ByteBuffer byteBuffer) {
        return Companion.of(byteBuffer);
    }

    public static final ByteString read(InputStream inputStream, int i) throws IOException {
        return Companion.read(inputStream, i);
    }

    private final void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException, SecurityException, IllegalArgumentException {
        ByteString byteString = Companion.read(objectInputStream, objectInputStream.readInt());
        Field declaredField = ByteString.class.getDeclaredField("data");
        declaredField.setAccessible(true);
        declaredField.set(this, byteString.data);
    }

    public static /* synthetic */ ByteString substring$default(ByteString byteString, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: substring");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = SegmentedByteString.getDEFAULT__ByteString_size();
        }
        return byteString.substring(i, i2);
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }

    /* renamed from: -deprecated_getByte, reason: not valid java name */
    public final byte m159deprecated_getByte(int i) {
        return getByte(i);
    }

    /* renamed from: -deprecated_size, reason: not valid java name */
    public final int m160deprecated_size() {
        return size();
    }

    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(this.data).asReadOnlyBuffer();
        CloseableKt.checkNotNullExpressionValue(byteBufferAsReadOnlyBuffer, "asReadOnlyBuffer(...)");
        return byteBufferAsReadOnlyBuffer;
    }

    public String base64() {
        return Base64.encodeBase64$default(getData$okio(), null, 1, null);
    }

    public String base64Url() {
        return Base64.encodeBase64(getData$okio(), Base64.getBASE64_URL_SAFE());
    }

    public void copyInto(int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(bArr, "target");
        ArraysKt.b(getData$okio(), i2, bArr, i, i3 + i);
    }

    public ByteString digest$okio(String str) throws NoSuchAlgorithmException {
        CloseableKt.checkNotNullParameter(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(this.data, 0, size());
        byte[] bArrDigest = messageDigest.digest();
        CloseableKt.checkNotNull(bArrDigest);
        return new ByteString(bArrDigest);
    }

    public final boolean endsWith(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "suffix");
        return rangeEquals(size() - byteString.size(), byteString, 0, byteString.size());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == getData$okio().length && byteString.rangeEquals(0, getData$okio(), 0, getData$okio().length)) {
                return true;
            }
        }
        return false;
    }

    public final byte getByte(int i) {
        return internalGet$okio(i);
    }

    public final byte[] getData$okio() {
        return this.data;
    }

    public final int getHashCode$okio() {
        return this.hashCode;
    }

    public int getSize$okio() {
        return getData$okio().length;
    }

    public final String getUtf8$okio() {
        return this.utf8;
    }

    public int hashCode() {
        int hashCode$okio = getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int iHashCode = Arrays.hashCode(getData$okio());
        setHashCode$okio(iHashCode);
        return iHashCode;
    }

    public String hex() {
        char[] cArr = new char[getData$okio().length * 2];
        int i = 0;
        for (byte b : getData$okio()) {
            int i2 = i + 1;
            cArr[i] = com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i += 2;
            cArr[i2] = com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[b & 15];
        }
        return new String(cArr);
    }

    public ByteString hmac$okio(String str, ByteString byteString) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        CloseableKt.checkNotNullParameter(str, "algorithm");
        CloseableKt.checkNotNullParameter(byteString, "key");
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            byte[] bArrDoFinal = mac.doFinal(this.data);
            CloseableKt.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ByteString hmacSha1(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "key");
        return hmac$okio("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "key");
        return hmac$okio("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "key");
        return hmac$okio("HmacSHA512", byteString);
    }

    public final int indexOf(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "other");
        return indexOf$default(this, byteString, 0, 2, (Object) null);
    }

    public byte[] internalArray$okio() {
        return getData$okio();
    }

    public byte internalGet$okio(int i) {
        return getData$okio()[i];
    }

    public final int lastIndexOf(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "other");
        return lastIndexOf$default(this, byteString, 0, 2, (Object) null);
    }

    public final ByteString md5() {
        return digest$okio("MD5");
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        CloseableKt.checkNotNullParameter(byteString, "other");
        return byteString.rangeEquals(i2, getData$okio(), i, i3);
    }

    public final void setHashCode$okio(int i) {
        this.hashCode = i;
    }

    public final void setUtf8$okio(String str) {
        this.utf8 = str;
    }

    public final ByteString sha1() {
        return digest$okio("SHA-1");
    }

    public final ByteString sha256() {
        return digest$okio("SHA-256");
    }

    public final ByteString sha512() {
        return digest$okio("SHA-512");
    }

    public final int size() {
        return getSize$okio();
    }

    public final boolean startsWith(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "prefix");
        return rangeEquals(0, byteString, 0, byteString.size());
    }

    public String string(Charset charset) {
        CloseableKt.checkNotNullParameter(charset, "charset");
        return new String(this.data, charset);
    }

    public final ByteString substring() {
        return substring$default(this, 0, 0, 3, null);
    }

    public ByteString toAsciiLowercase() {
        for (int i = 0; i < getData$okio().length; i++) {
            byte b = getData$okio()[i];
            if (b >= 65 && b <= 90) {
                byte[] data$okio = getData$okio();
                byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
                CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArrCopyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }

    public ByteString toAsciiUppercase() {
        for (int i = 0; i < getData$okio().length; i++) {
            byte b = getData$okio()[i];
            if (b >= 97 && b <= 122) {
                byte[] data$okio = getData$okio();
                byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
                CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i] = (byte) (b - 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 97 && b2 <= 122) {
                        bArrCopyOf[i2] = (byte) (b2 - 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }

    public byte[] toByteArray() {
        byte[] data$okio = getData$okio();
        byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
        CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    public String toString() {
        if (getData$okio().length == 0) {
            return "[size=0]";
        }
        int iCodePointIndexToCharIndex = com.shadow.okio.internal.ByteString.codePointIndexToCharIndex(getData$okio(), 64);
        if (iCodePointIndexToCharIndex != -1) {
            String strUtf8 = utf8();
            String strSubstring = strUtf8.substring(0, iCodePointIndexToCharIndex);
            CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String strO = StringsKt.o(StringsKt.o(StringsKt.o(strSubstring, "\\", "\\\\"), "\n", "\\n"), "\r", "\\r");
            if (iCodePointIndexToCharIndex >= strUtf8.length()) {
                return "[text=" + strO + ']';
            }
            return "[size=" + getData$okio().length + " text=" + strO + "…]";
        }
        if (getData$okio().length <= 64) {
            return "[hex=" + hex() + ']';
        }
        StringBuilder sb = new StringBuilder("[size=");
        sb.append(getData$okio().length);
        sb.append(" hex=");
        int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, 64);
        if (iResolveDefaultParameter > getData$okio().length) {
            throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
        }
        if (iResolveDefaultParameter < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        sb.append((iResolveDefaultParameter == getData$okio().length ? this : new ByteString(ArraysKt.d(getData$okio(), 0, iResolveDefaultParameter))).hex());
        sb.append("…]");
        return sb.toString();
    }

    public String utf8() {
        String utf8$okio = getUtf8$okio();
        if (utf8$okio != null) {
            return utf8$okio;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(internalArray$okio());
        setUtf8$okio(utf8String);
        return utf8String;
    }

    public void write(OutputStream outputStream) throws IOException {
        CloseableKt.checkNotNullParameter(outputStream, "out");
        outputStream.write(this.data);
    }

    public void write$okio(Buffer buffer, int i, int i2) {
        CloseableKt.checkNotNullParameter(buffer, "buffer");
        com.shadow.okio.internal.ByteString.commonWrite(this, buffer, i, i2);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(bArr, i);
    }

    public static final ByteString of(byte... bArr) {
        return Companion.of(bArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002c A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002a A[ORIG_RETURN, RETURN] */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int compareTo(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "other");
        int size = size();
        int size2 = byteString.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int i2 = getByte(i) & 255;
            int i3 = byteString.getByte(i) & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        if (size < size2) {
        }
    }

    public final boolean endsWith(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "suffix");
        return rangeEquals(size() - bArr.length, bArr, 0, bArr.length);
    }

    public final int indexOf(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        return indexOf$default(this, bArr, 0, 2, (Object) null);
    }

    public final int lastIndexOf(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        return lastIndexOf$default(this, bArr, 0, 2, (Object) null);
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        return i >= 0 && i <= getData$okio().length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && SegmentedByteString.arrayRangeEquals(getData$okio(), i, bArr, i2, i3);
    }

    public final boolean startsWith(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "prefix");
        return rangeEquals(0, bArr, 0, bArr.length);
    }

    public final ByteString substring(int i) {
        return substring$default(this, i, 0, 2, null);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = SegmentedByteString.getDEFAULT__ByteString_size();
        }
        return byteString.lastIndexOf(bArr, i);
    }

    public static final ByteString of(byte[] bArr, int i, int i2) {
        return Companion.of(bArr, i, i2);
    }

    public final int indexOf(ByteString byteString, int i) {
        CloseableKt.checkNotNullParameter(byteString, "other");
        return indexOf(byteString.internalArray$okio(), i);
    }

    public final int lastIndexOf(ByteString byteString, int i) {
        CloseableKt.checkNotNullParameter(byteString, "other");
        return lastIndexOf(byteString.internalArray$okio(), i);
    }

    public ByteString substring(int i, int i2) {
        int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, i2);
        if (i >= 0) {
            if (iResolveDefaultParameter <= getData$okio().length) {
                if (iResolveDefaultParameter - i >= 0) {
                    return (i == 0 && iResolveDefaultParameter == getData$okio().length) ? this : new ByteString(ArraysKt.d(getData$okio(), i, iResolveDefaultParameter));
                }
                throw new IllegalArgumentException("endIndex < beginIndex");
            }
            throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
        }
        throw new IllegalArgumentException("beginIndex < 0");
    }

    public int indexOf(byte[] bArr, int i) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        int length = getData$okio().length - bArr.length;
        int iMax = Math.max(i, 0);
        if (iMax <= length) {
            while (!SegmentedByteString.arrayRangeEquals(getData$okio(), iMax, bArr, 0, bArr.length)) {
                if (iMax != length) {
                    iMax++;
                }
            }
            return iMax;
        }
        return -1;
    }

    public int lastIndexOf(byte[] bArr, int i) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        for (int iMin = Math.min(SegmentedByteString.resolveDefaultParameter(this, i), getData$okio().length - bArr.length); -1 < iMin; iMin--) {
            if (SegmentedByteString.arrayRangeEquals(getData$okio(), iMin, bArr, 0, bArr.length)) {
                return iMin;
            }
        }
        return -1;
    }
}
