package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.jarfs.LargeDynamicMappedBuffer;
import org.jetbrains.kotlin.cli.jvm.compiler.jarfs.ZipImplementationKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\u0012\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b*\u00020\u0007H\u0000\u001a\u0018\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u0007H\u0002\u001a\u0018\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u0010H\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"END_OF_CENTRAL_DIR_SIZE", Argument.Delimiters.none, "END_OF_CENTRAL_DIR_ZIP64_SIZE", "LOCAL_FILE_HEADER_EXTRA_OFFSET", "LOCAL_FILE_HEADER_SIZE", "contentsToByteArray", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer;", "zipEntryDescription", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription;", "parseCentralDirectory", Argument.Delimiters.none, "parseCentralDirectoryRecordsNumberAndOffset", "Lkotlin/Pair;", Argument.Delimiters.none, "parseZip64CentralDirectoryRecordsNumberAndOffset", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/LargeDynamicMappedBuffer$Mapping;", "getUnsignedShort", "offset", "kotlin-compiler"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ZipImplementationKt {
    private static final int END_OF_CENTRAL_DIR_SIZE = 22;
    private static final int END_OF_CENTRAL_DIR_ZIP64_SIZE = 56;
    private static final int LOCAL_FILE_HEADER_EXTRA_OFFSET = 28;
    private static final int LOCAL_FILE_HEADER_SIZE = 30;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ZipEntryDescription.CompressionKind.values().length];
            try {
                iArr[ZipEntryDescription.CompressionKind.DEFLATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ZipEntryDescription.CompressionKind.PLAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static Unit a(Ref.LongRef longRef, List list, long j, LargeDynamicMappedBuffer.Mapping mapping) {
        CharSequence byteArrayCharSequence;
        ZipEntryDescription.CompressionKind compressionKind;
        mapping.getClass();
        int i = 0;
        int i2 = mapping.getInt(0);
        if (i2 != 33639248) {
            throw new IllegalArgumentException((j + ": " + i2).toString());
        }
        short s = mapping.getShort(10);
        int unsignedShort = getUnsignedShort(mapping, LOCAL_FILE_HEADER_EXTRA_OFFSET);
        int unsignedShort2 = getUnsignedShort(mapping, LOCAL_FILE_HEADER_SIZE);
        int i3 = unsignedShort + 46;
        int i4 = mapping.getInt(20);
        int i5 = mapping.getInt(24);
        int unsignedShort3 = getUnsignedShort(mapping, 32);
        int i6 = mapping.getInt(42);
        Ref.IntRef intRef = new Ref.IntRef();
        short s2 = mapping.getShort(unsignedShort + 48);
        long centralDirectory$lambda$0$toLongOrNextZip64ExtrField = parseCentralDirectory$lambda$0$toLongOrNextZip64ExtrField(i4, s2, intRef, mapping, i3);
        long centralDirectory$lambda$0$toLongOrNextZip64ExtrField2 = parseCentralDirectory$lambda$0$toLongOrNextZip64ExtrField(i5, s2, intRef, mapping, i3);
        long centralDirectory$lambda$0$toLongOrNextZip64ExtrField3 = parseCentralDirectory$lambda$0$toLongOrNextZip64ExtrField(i6, s2, intRef, mapping, i3);
        byte[] bytes = mapping.getBytes(46, unsignedShort);
        int length = bytes.length;
        while (true) {
            if (i >= length) {
                byteArrayCharSequence = new ByteArrayCharSequence(bytes, 0, 0, 6, null);
                break;
            }
            if (bytes[i] < 0) {
                byteArrayCharSequence = new String(bytes, Charsets.UTF_8);
                break;
            }
            i++;
        }
        longRef.element = ((long) (i3 + unsignedShort2 + unsignedShort3)) + longRef.element;
        if (s == 0) {
            compressionKind = ZipEntryDescription.CompressionKind.PLAIN;
        } else {
            if (s != 8) {
                throw new IllegalStateException(("Unexpected compression method (" + ((int) s) + ") at " + ((Object) byteArrayCharSequence)).toString());
            }
            compressionKind = ZipEntryDescription.CompressionKind.DEFLATE;
        }
        list.add(new ZipEntryDescription(byteArrayCharSequence, centralDirectory$lambda$0$toLongOrNextZip64ExtrField, centralDirectory$lambda$0$toLongOrNextZip64ExtrField2, centralDirectory$lambda$0$toLongOrNextZip64ExtrField3, compressionKind, unsignedShort));
        return Unit.INSTANCE;
    }

    public static byte[] b(ZipEntryDescription zipEntryDescription, LargeDynamicMappedBuffer.Mapping mapping) throws DataFormatException {
        mapping.getClass();
        int fileNameSize = zipEntryDescription.getFileNameSize() + LOCAL_FILE_HEADER_SIZE + getUnsignedShort(mapping, LOCAL_FILE_HEADER_EXTRA_OFFSET);
        if (zipEntryDescription.getCompressedSize() - ((long) fileNameSize) >= 2147483647L || zipEntryDescription.getUncompressedSize() > 2147483647L) {
            z2d.a("Reading files bigger than Int.MAX_VALUE - ", fileNameSize, " is not supported yet");
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[zipEntryDescription.getCompressionKind().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return mapping.getBytes(fileNameSize, (int) zipEntryDescription.getCompressedSize());
            }
            bu8.a();
            return null;
        }
        Inflater inflater = new Inflater(true);
        inflater.setInput(mapping.getBytes(fileNameSize, (int) zipEntryDescription.getCompressedSize()));
        byte[] bArr = new byte[(int) zipEntryDescription.getUncompressedSize()];
        inflater.inflate(bArr);
        inflater.end();
        return bArr;
    }

    public static Pair c(LargeDynamicMappedBuffer.Mapping mapping) {
        mapping.getClass();
        int iEndOffset = mapping.endOffset() - 22;
        while (iEndOffset >= 0 && mapping.getInt(iEndOffset) != 101010256) {
            iEndOffset--;
        }
        int unsignedShort = getUnsignedShort(mapping, iEndOffset + 10);
        int i = mapping.getInt(iEndOffset + 16);
        return (unsignedShort == 65535 || i == -1) ? parseZip64CentralDirectoryRecordsNumberAndOffset(mapping) : new Pair(Long.valueOf(unsignedShort), Long.valueOf(((long) UInt.constructor-impl(i)) & 4294967295L));
    }

    public static final byte[] contentsToByteArray(LargeDynamicMappedBuffer largeDynamicMappedBuffer, final ZipEntryDescription zipEntryDescription) {
        largeDynamicMappedBuffer.getClass();
        zipEntryDescription.getClass();
        return (byte[]) largeDynamicMappedBuffer.withMappedRangeFrom(zipEntryDescription.getOffsetInFile(), new Function1() { // from class: y8g
            public final Object invoke(Object obj) {
                return ZipImplementationKt.b(zipEntryDescription, (LargeDynamicMappedBuffer.Mapping) obj);
            }
        });
    }

    private static final int getUnsignedShort(LargeDynamicMappedBuffer.Mapping mapping, int i) {
        return Short.toUnsignedInt(mapping.getShort(i));
    }

    public static final List<ZipEntryDescription> parseCentralDirectory(LargeDynamicMappedBuffer largeDynamicMappedBuffer) {
        largeDynamicMappedBuffer.getClass();
        Pair<Long, Long> centralDirectoryRecordsNumberAndOffset = parseCentralDirectoryRecordsNumberAndOffset(largeDynamicMappedBuffer);
        long jLongValue = ((Number) centralDirectoryRecordsNumberAndOffset.component1()).longValue();
        long jLongValue2 = ((Number) centralDirectoryRecordsNumberAndOffset.component2()).longValue();
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = jLongValue2;
        final ArrayList arrayList = new ArrayList();
        for (final long j = 0; j < jLongValue; j++) {
            largeDynamicMappedBuffer.withMappedRangeFrom(longRef.element, new Function1() { // from class: z8g
                public final Object invoke(Object obj) {
                    return ZipImplementationKt.a(longRef, arrayList, j, (LargeDynamicMappedBuffer.Mapping) obj);
                }
            });
        }
        return arrayList;
    }

    private static final long parseCentralDirectory$lambda$0$toLongOrNextZip64ExtrField(int i, short s, Ref.IntRef intRef, LargeDynamicMappedBuffer.Mapping mapping, int i2) {
        if (i != -1) {
            return ((long) UInt.constructor-impl(i)) & 4294967295L;
        }
        int i3 = intRef.element;
        if (s < (i3 + 1) * 8) {
            w01.a("Failed requirement.");
            return 0L;
        }
        long j = mapping.getLong(i2 + 4 + (i3 * 8));
        intRef.element++;
        return j;
    }

    private static final Pair<Long, Long> parseCentralDirectoryRecordsNumberAndOffset(LargeDynamicMappedBuffer largeDynamicMappedBuffer) {
        return (Pair) largeDynamicMappedBuffer.withMappedTail(new Function1() { // from class: x8g
            public final Object invoke(Object obj) {
                return ZipImplementationKt.c((LargeDynamicMappedBuffer.Mapping) obj);
            }
        });
    }

    private static final Pair<Long, Long> parseZip64CentralDirectoryRecordsNumberAndOffset(LargeDynamicMappedBuffer.Mapping mapping) {
        int iEndOffset = mapping.endOffset() - 56;
        while (iEndOffset >= 0 && mapping.getInt(iEndOffset) != 101075792) {
            iEndOffset--;
        }
        return new Pair<>(Long.valueOf(mapping.getLong(iEndOffset + 32)), Long.valueOf(mapping.getLong(iEndOffset + 48)));
    }
}
