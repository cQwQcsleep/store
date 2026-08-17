package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.ThreadLocalCachedValue;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.util.ThrowableNotNullFunction;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.SystemProperties;
import com.intellij.util.io.IOUtil;
import com.intellij.util.text.ByteArrayCharSequence;
import defpackage.hv3;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UTFDataFormatException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class IOUtil {
    private static final AtomicLong BITS_RESERVED_MEMORY_FIELD;
    public static final int GiB = 1073741824;
    public static final int KiB = 1024;
    public static final int MiB = 1048576;
    public static final String SHARED_CACHES_PROP = "idea.shared.caches";
    public static final ThreadLocal<Boolean> OVERRIDE_BYTE_BUFFERS_USE_NATIVE_BYTE_ORDER_PROP = new ThreadLocal<Boolean>() { // from class: com.intellij.util.io.IOUtil.1
        @Override // java.lang.ThreadLocal
        public void set(Boolean bool) {
            if (get() == null) {
                super.set(bool);
            } else {
                f63.a("Reentrant access");
            }
        }
    };
    private static final ThreadLocalCachedValue<byte[]> ourReadWriteBuffersCache = new ThreadLocalCachedValue<byte[]>() { // from class: com.intellij.util.io.IOUtil.2
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/io/IOUtil$2", "create"));
        }

        @Override // com.intellij.openapi.util.ThreadLocalCachedValue
        public byte[] create() {
            byte[] bArrAllocReadWriteUTFBuffer = IOUtil.allocReadWriteUTFBuffer();
            if (bArrAllocReadWriteUTFBuffer == null) {
                $$$reportNull$$$0(0);
            }
            return bArrAllocReadWriteUTFBuffer;
        }
    };
    private static final byte[] ZEROES = new byte[65536];

    /* JADX WARN: Code duplicated, block: B:34:0x0086  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 36 || i == 47) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 36 || i == 47) ? 2 : 3];
        if (i != 1) {
            switch (i) {
                case 5:
                    objArr[0] = "text";
                    break;
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                case 8:
                case 10:
                case 11:
                case 12:
                case 15:
                case 18:
                case 20:
                case 21:
                    objArr[0] = "storage";
                    break;
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 9:
                case 13:
                case 16:
                    objArr[0] = "value";
                    break;
                case 14:
                case 17:
                case 19:
                case 44:
                case 45:
                case 46:
                    objArr[0] = "buffer";
                    break;
                case 22:
                case 23:
                    objArr[0] = "str";
                    break;
                case 24:
                case 25:
                case 27:
                case 29:
                    objArr[0] = "file";
                    break;
                case 26:
                case 28:
                case 30:
                    objArr[0] = "factoryComputable";
                    break;
                case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                    objArr[0] = "cleanupCallback";
                    break;
                case 32:
                    objArr[0] = "out";
                    break;
                case 33:
                    objArr[0] = "list";
                    break;
                case 34:
                case 37:
                    objArr[0] = "in";
                    break;
                case 35:
                    objArr[0] = "collectionGenerator";
                    break;
                case 36:
                case 47:
                    objArr[0] = "com/intellij/util/io/IOUtil";
                    break;
                case 38:
                case 39:
                    objArr[0] = "log";
                    break;
                case 40:
                case 41:
                    objArr[0] = "channel";
                    break;
                case 42:
                case 43:
                    objArr[0] = "externalizer";
                    break;
                case 48:
                    objArr[0] = "ascii";
                    break;
                case 49:
                    objArr[0] = "storageToWrap";
                    break;
                case 50:
                    objArr[0] = "wrapperer";
                    break;
                default:
                    objArr[0] = "stream";
                    break;
            }
        } else {
            objArr[0] = "buffer";
        }
        if (i == 36) {
            objArr[1] = "readStringCollection";
        } else if (i != 47) {
            objArr[1] = "com/intellij/util/io/IOUtil";
        } else {
            objArr[1] = "toHexString";
        }
        switch (i) {
            case 2:
                objArr[2] = "writeString";
                break;
            case 3:
                objArr[2] = "writeCharSequence";
                break;
            case 4:
            case 5:
                objArr[2] = "writeUTFTruncated";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
                objArr[2] = "writeUTF";
                break;
            case 10:
                objArr[2] = "readUTF";
                break;
            case 11:
                objArr[2] = "readUTFCharSequence";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "writeUTFFast";
                break;
            case 18:
            case 19:
                objArr[2] = "readUTFFast";
                break;
            case 20:
                objArr[2] = "readLongString";
                break;
            case 21:
                objArr[2] = "readUTFFastCharSequence";
                break;
            case 22:
            case 23:
                objArr[2] = "isAscii";
                break;
            case 24:
            case 25:
                objArr[2] = "deleteAllFilesStartingWith";
                break;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[2] = "openCleanOrResetBroken";
                break;
            case 32:
            case 33:
                objArr[2] = "writeStringList";
                break;
            case 34:
            case 35:
                objArr[2] = "readStringCollection";
                break;
            case 36:
            case 47:
                break;
            case 37:
                objArr[2] = "readStringList";
                break;
            case 38:
            case 39:
                objArr[2] = "closeSafe";
                break;
            case 40:
                objArr[2] = "allocateFileRegion";
                break;
            case 41:
                objArr[2] = "fillFileRegionWithZeros";
                break;
            case 42:
                objArr[2] = "toBytes";
                break;
            case 43:
                objArr[2] = "fromBytes";
                break;
            case 44:
                objArr[2] = "toString";
                break;
            case 45:
            case 46:
                objArr[2] = "toHexString";
                break;
            case 48:
                objArr[2] = "asciiToMagicWord";
                break;
            case 49:
            case 50:
                objArr[2] = "wrapSafely";
                break;
            default:
                objArr[2] = "readString";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 36 && i != 47) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    static {
        AtomicLong atomicLong = null;
        try {
            Field declaredField = Class.forName("java.nio.Bits").getDeclaredField("RESERVED_MEMORY");
            declaredField.setAccessible(true);
            atomicLong = (AtomicLong) declaredField.get(null);
        } catch (Throwable th) {
            Logger logger = Logger.getInstance(IOUtil.class);
            if (logger.isDebugEnabled()) {
                logger.warn("Can't get java.nio.Bits.RESERVED_MEMORY", th);
            }
        }
        BITS_RESERVED_MEMORY_FIELD = atomicLong;
    }

    private IOUtil() {
    }

    public static byte[] allocReadWriteUTFBuffer() {
        return new byte[256];
    }

    public static void allocateFileRegion(FileChannel fileChannel, long j) throws IOException {
        if (fileChannel == null) {
            $$$reportNull$$$0(40);
        }
        long size = fileChannel.size();
        if (size < j) {
            fillFileRegionWithZeros(fileChannel, size, j);
        }
    }

    public static int asciiToMagicWord(String str) {
        if (str == null) {
            $$$reportNull$$$0(48);
        }
        if (str.length() != 4) {
            kg9.a("ascii[", str, "] must be 4 ASCII chars long");
            return 0;
        }
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        if (bytes.length == 4) {
            return Byte.toUnsignedInt(bytes[3]) | (Byte.toUnsignedInt(bytes[0]) << 24) | (Byte.toUnsignedInt(bytes[1]) << 16) | (Byte.toUnsignedInt(bytes[2]) << 8);
        }
        yba.a("ascii bytes [", toHexString(bytes), "].length must be 4");
        return 0;
    }

    public static void closeAllSafely(Closeable... closeableArr) throws IOException {
        IOException iOException = null;
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable th) {
                    if (iOException == null) {
                        iOException = th;
                    } else {
                        iOException.addSuppressed(th);
                    }
                }
            }
        }
        if (iOException != null) {
            if (!(iOException instanceof IOException)) {
                throw new IOException(iOException);
            }
            throw iOException;
        }
    }

    public static void closeSafe(Logger logger, Closeable... closeableArr) {
        if (logger == null) {
            $$$reportNull$$$0(38);
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
    }

    public static boolean deleteAllFilesStartingWith(Path path) {
        if (path == null) {
            $$$reportNull$$$0(24);
        }
        String string = path.getFileName().toString();
        Path parent = path.getParent();
        boolean z = true;
        if (parent == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        try {
            DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(parent);
            try {
                for (Path path2 : directoryStreamNewDirectoryStream) {
                    if (path2.getFileName().toString().startsWith(string)) {
                        arrayList.add(path2);
                    }
                }
                directoryStreamNewDirectoryStream.close();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    try {
                        FileUtil.delete((Path) it.next());
                    } catch (IOException unused) {
                        z = false;
                    }
                }
                return z;
            } catch (Throwable th) {
                if (directoryStreamNewDirectoryStream != null) {
                    try {
                        directoryStreamNewDirectoryStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (NoSuchFileException unused2) {
            return true;
        } catch (IOException unused3) {
            return false;
        }
    }

    public static long directBuffersTotalAllocatedSize() {
        AtomicLong atomicLong = BITS_RESERVED_MEMORY_FIELD;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return -1L;
    }

    public static void fillFileRegionWithZeros(FileChannel fileChannel, long j, long j2) throws IOException {
        if (fileChannel == null) {
            $$$reportNull$$$0(41);
        }
        byte[] bArr = ZEROES;
        int length = bArr.length;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        while (j < j2) {
            long j3 = length;
            byteBufferWrap.clear().limit(Math.toIntExact(Math.min(j3, j2 - j)));
            fileChannel.write(byteBufferWrap, j);
            j += j3;
        }
    }

    public static <T> T fromBytes(byte[] bArr, DataExternalizer<? extends T> dataExternalizer) throws IOException {
        if (dataExternalizer == null) {
            $$$reportNull$$$0(43);
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        try {
            T t = dataExternalizer.read(dataInputStream);
            dataInputStream.close();
            return t;
        } catch (Throwable th) {
            try {
                dataInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static boolean isAscii(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(23);
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) >= 128) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSharedCachesEnabled() {
        return SystemProperties.getBooleanProperty(SHARED_CACHES_PROP, false);
    }

    public static String magicWordToASCII(int i) {
        return new String(new byte[]{(byte) ((i >> 24) & PartialGapBuffer.BUF_SIZE), (byte) ((i >> 16) & PartialGapBuffer.BUF_SIZE), (byte) ((i >> 8) & PartialGapBuffer.BUF_SIZE), (byte) (i & PartialGapBuffer.BUF_SIZE)}, StandardCharsets.US_ASCII);
    }

    public static <T> T openCleanOrResetBroken(ThrowableComputable<T, ? extends IOException> throwableComputable, Runnable runnable) throws IOException {
        if (throwableComputable == null) {
            $$$reportNull$$$0(30);
        }
        if (runnable == null) {
            $$$reportNull$$$0(31);
        }
        try {
            return (T) throwableComputable.compute();
        } catch (IOException unused) {
            runnable.run();
            return (T) throwableComputable.compute();
        }
    }

    private static String readLongString(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(20);
        }
        String utf = dataInput.readUTF();
        return "LONGER_THAN_64K".equals(utf) ? readString(dataInput) : utf;
    }

    public static String readString(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(0);
        }
        try {
            int i = dataInput.readInt();
            if (i == -1) {
                return null;
            }
            if (i == 0) {
                return "";
            }
            int i2 = i * 2;
            byte[] bArr = new byte[i2];
            dataInput.readFully(bArr);
            return new String(bArr, 0, i2, StandardCharsets.UTF_16BE);
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    public static <C extends Collection<String>> C readStringCollection(DataInput dataInput, IntFunction<? extends C> intFunction) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(34);
        }
        if (intFunction == null) {
            $$$reportNull$$$0(35);
        }
        int i = DataInputOutputUtil.readINT(dataInput);
        C cApply = intFunction.apply(i);
        for (int i2 = 0; i2 < i; i2++) {
            cApply.add(readUTF(dataInput));
        }
        if (cApply == null) {
            $$$reportNull$$$0(36);
        }
        return cApply;
    }

    public static List<String> readStringList(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(37);
        }
        return (List) readStringCollection(dataInput, new IntFunction() { // from class: rg6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return new ArrayList(i);
            }
        });
    }

    public static String readUTF(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(10);
        }
        return readUTFFast(ourReadWriteBuffersCache.getValue(), dataInput);
    }

    public static CharSequence readUTFCharSequence(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(11);
        }
        return readUTFFastCharSequence(dataInput);
    }

    public static String readUTFFast(byte[] bArr, DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(18);
        }
        if (bArr == null) {
            $$$reportNull$$$0(19);
        }
        int i = dataInput.readByte() & 255;
        if (i == 255) {
            return readLongString(dataInput);
        }
        if (i == 0) {
            return "";
        }
        dataInput.readFully(bArr, 0, i);
        return new String(bArr, 0, i, StandardCharsets.ISO_8859_1);
    }

    public static CharSequence readUTFFastCharSequence(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(21);
        }
        int i = dataInput.readByte() & 255;
        if (i == 255) {
            return readLongString(dataInput);
        }
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        dataInput.readFully(bArr, 0, i);
        return new ByteArrayCharSequence(bArr, 0, i);
    }

    public static <T> byte[] toBytes(T t, DataExternalizer<? super T> dataExternalizer) throws IOException {
        if (dataExternalizer == null) {
            $$$reportNull$$$0(42);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        java.io.DataOutputStream dataOutputStream = new java.io.DataOutputStream(byteArrayOutputStream);
        try {
            dataExternalizer.save(dataOutputStream, t);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String toHexString(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder(bArr.length * 3);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int unsignedInt = Byte.toUnsignedInt(bArr[i2]);
            if (unsignedInt < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(unsignedInt));
            if (i2 < bArr.length - 1) {
                if (i <= 0 || i2 % i != i - 1) {
                    sb.append(' ');
                } else {
                    sb.append('\n');
                }
            }
        }
        return sb.toString();
    }

    public static String toString(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(44);
        }
        byte[] bArr = new byte[byteBuffer.capacity()];
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(0).limit(byteBuffer.capacity());
        byteBufferDuplicate.get(bArr);
        return Arrays.toString(bArr);
    }

    public static boolean useNativeByteOrderForByteBuffers() {
        Boolean bool = OVERRIDE_BYTE_BUFFERS_USE_NATIVE_BYTE_ORDER_PROP.get();
        return bool == null || bool.booleanValue();
    }

    public static <Out, In extends AutoCloseable, E extends Throwable> Out wrapSafely(In in, ThrowableNotNullFunction<In, Out, E> throwableNotNullFunction) throws Throwable {
        if (in == null) {
            $$$reportNull$$$0(49);
        }
        if (throwableNotNullFunction == null) {
            $$$reportNull$$$0(50);
        }
        try {
            return throwableNotNullFunction.fun(in);
        } catch (Throwable th) {
            try {
                if (in instanceof Unmappable) {
                    ((Unmappable) in).closeAndUnsafelyUnmap();
                } else {
                    hv3.a(in);
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void writeCharSequence(CharSequence charSequence, DataOutput dataOutput) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(3);
        }
        if (charSequence == null) {
            dataOutput.writeInt(-1);
            return;
        }
        dataOutput.writeInt(charSequence.length());
        if (charSequence.length() == 0) {
            return;
        }
        byte[] bArr = new byte[charSequence.length() * 2];
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char cCharAt = charSequence.charAt(i);
            bArr[i2] = (byte) ((cCharAt >>> '\b') & PartialGapBuffer.BUF_SIZE);
            bArr[i2 + 1] = (byte) (cCharAt & 255);
            i++;
            i2 += 2;
        }
        dataOutput.write(bArr);
    }

    public static void writeString(String str, DataOutput dataOutput) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(2);
        }
        writeCharSequence(str, dataOutput);
    }

    public static void writeStringList(DataOutput dataOutput, Collection<String> collection) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(32);
        }
        if (collection == null) {
            $$$reportNull$$$0(33);
        }
        DataInputOutputUtil.writeINT(dataOutput, collection.size());
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            writeUTF(dataOutput, it.next());
        }
    }

    public static void writeUTF(DataOutput dataOutput, CharSequence charSequence) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(8);
        }
        if (charSequence == null) {
            $$$reportNull$$$0(9);
        }
        writeUTFFast(ourReadWriteBuffersCache.getValue(), dataOutput, charSequence);
    }

    public static void writeUTFFast(byte[] bArr, DataOutput dataOutput, CharSequence charSequence) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(15);
        }
        if (charSequence == null) {
            $$$reportNull$$$0(16);
        }
        if (bArr == null) {
            $$$reportNull$$$0(17);
        }
        int length = charSequence.length();
        if (length < 255) {
            bArr[0] = (byte) length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    dataOutput.write(bArr, 0, length + 1);
                    return;
                }
                char cCharAt = charSequence.charAt(i);
                if (cCharAt >= 128) {
                    break;
                }
                i++;
                bArr[i] = (byte) cCharAt;
            }
        }
        dataOutput.writeByte(-1);
        try {
            dataOutput.writeUTF(charSequence.toString());
        } catch (UTFDataFormatException unused) {
            dataOutput.writeUTF("LONGER_THAN_64K");
            writeCharSequence(charSequence, dataOutput);
        }
    }

    public static void writeUTFTruncated(DataOutput dataOutput, String str) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(4);
        }
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        if (str.length() > 16383) {
            dataOutput.writeUTF(str.substring(0, 16383));
        } else {
            dataOutput.writeUTF(str);
        }
    }

    public static void writeUTF(DataOutput dataOutput, String str) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(6);
        }
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        writeUTFFast(ourReadWriteBuffersCache.getValue(), dataOutput, str);
    }

    public static void closeSafe(Logger logger, AutoCloseable... autoCloseableArr) {
        if (logger == null) {
            $$$reportNull$$$0(39);
        }
        for (AutoCloseable autoCloseable : autoCloseableArr) {
            if (autoCloseable != null) {
                try {
                    hv3.a(autoCloseable);
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }
    }

    public static <T> T openCleanOrResetBroken(ThrowableComputable<T, ? extends IOException> throwableComputable, final File file) throws IOException {
        if (throwableComputable == null) {
            $$$reportNull$$$0(28);
        }
        if (file == null) {
            $$$reportNull$$$0(29);
        }
        return (T) openCleanOrResetBroken(throwableComputable, new Runnable() { // from class: qg6
            @Override // java.lang.Runnable
            public final void run() {
                IOUtil.deleteAllFilesStartingWith(file);
            }
        });
    }

    public static <T> T openCleanOrResetBroken(ThrowableComputable<T, ? extends IOException> throwableComputable, final Path path) throws IOException {
        if (throwableComputable == null) {
            $$$reportNull$$$0(26);
        }
        if (path == null) {
            $$$reportNull$$$0(27);
        }
        return (T) openCleanOrResetBroken(throwableComputable, new Runnable() { // from class: pg6
            @Override // java.lang.Runnable
            public final void run() {
                IOUtil.deleteAllFilesStartingWith(path);
            }
        });
    }

    public static boolean isAscii(String str) {
        if (str == null) {
            $$$reportNull$$$0(22);
        }
        return isAscii((CharSequence) str);
    }

    public static boolean isAscii(char c) {
        return c < 128;
    }

    public static String readString(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(1);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return new String(bArr, StandardCharsets.UTF_8);
    }

    public static String toHexString(ByteBuffer byteBuffer, int i) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(46);
        }
        byte[] bArr = new byte[byteBuffer.capacity()];
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(0).limit(byteBuffer.capacity());
        byteBufferDuplicate.get(bArr);
        return toHexString(bArr, i);
    }

    public static String toHexString(byte[] bArr) {
        return toHexString(bArr, -1);
    }

    public static String toHexString(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(45);
        }
        return toHexString(byteBuffer, -1);
    }

    public static void writeUTFFast(byte[] bArr, DataOutput dataOutput, String str) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(12);
        }
        if (str == null) {
            $$$reportNull$$$0(13);
        }
        if (bArr == null) {
            $$$reportNull$$$0(14);
        }
        writeUTFFast(bArr, dataOutput, (CharSequence) str);
    }

    public static boolean deleteAllFilesStartingWith(File file) {
        if (file == null) {
            $$$reportNull$$$0(25);
        }
        final String name = file.getName();
        File parentFile = file.getParentFile();
        File[] fileArrListFiles = parentFile != null ? parentFile.listFiles(new FileFilter() { // from class: og6
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return file2.getName().startsWith(name);
            }
        }) : null;
        boolean zDelete = true;
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                zDelete &= FileUtil.delete(file2);
            }
        }
        return zDelete;
    }
}
