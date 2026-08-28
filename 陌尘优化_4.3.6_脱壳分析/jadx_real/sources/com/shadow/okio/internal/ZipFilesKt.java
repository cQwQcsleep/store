package com.shadow.okio.internal;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.Pair;
import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.Ref$BooleanRef;
import com.shadow.kotlin.jvm.internal.Ref$LongRef;
import com.shadow.kotlin.jvm.internal.Ref$ObjectRef;
import com.shadow.kotlin.text.CharsKt;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okhttp3.internal.ws.WebSocketProtocol;
import com.shadow.okio.BufferedSource;
import com.shadow.okio.FileHandle;
import com.shadow.okio.FileSystem;
import com.shadow.okio.Okio;
import com.shadow.okio.Path;
import com.shadow.okio.ZipFileSystem;
import core.pro.android.notify.h;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ZipFilesKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_NTFS_EXTRA = 10;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        List listA;
        Path path = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
        Pair[] pairArr = {new Pair(path, new ZipEntry(path, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, null, null, null, 65532, null))};
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.b(1));
        Pair pair = pairArr[0];
        linkedHashMap.put(pair.component1(), pair.component2());
        Comparator comparator = new Comparator() { // from class: com.shadow.okio.internal.ZipFilesKt$buildIndex$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Path canonicalPath = ((ZipEntry) t).getCanonicalPath();
                Path canonicalPath2 = ((ZipEntry) t2).getCanonicalPath();
                if (canonicalPath == canonicalPath2) {
                    return 0;
                }
                if (canonicalPath == null) {
                    return -1;
                }
                if (canonicalPath2 == null) {
                    return 1;
                }
                return canonicalPath.compareTo(canonicalPath2);
            }
        };
        CloseableKt.checkNotNullParameter(list, "<this>");
        if (list.size() <= 1) {
            listA = CollectionsKt.g(list);
        } else {
            Object[] array = list.toArray(new Object[0]);
            CloseableKt.checkNotNullParameter(array, "<this>");
            if (array.length > 1) {
                Arrays.sort(array, comparator);
            }
            listA = ArraysKt.a(array);
        }
        Iterator it = listA.iterator();
        while (it.hasNext()) {
            ZipEntry zipEntry = (ZipEntry) it.next();
            if (((ZipEntry) linkedHashMap.put(zipEntry.getCanonicalPath(), zipEntry)) == null) {
                while (true) {
                    Path pathParent = zipEntry.getCanonicalPath().parent();
                    if (pathParent != null) {
                        ZipEntry zipEntry2 = (ZipEntry) linkedHashMap.get(pathParent);
                        if (zipEntry2 != null) {
                            zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(pathParent, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, null, null, null, 65532, null);
                        linkedHashMap.put(pathParent, zipEntry3);
                        zipEntry3.getChildren().add(zipEntry.getCanonicalPath());
                        zipEntry = zipEntry3;
                        it = it;
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        return Long.valueOf(_ZlibJvmKt.datePartsToEpochMillis(((i >> 9) & 127) + 1980, (i >> 5) & 15, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1));
    }

    public static final long filetimeToEpochMillis(long j) {
        return (j / 10000) - 11644473600000L;
    }

    private static final String getHex(int i) {
        CharsKt.a(16);
        String string = Integer.toString(i, 16);
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        return "0x".concat(string);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00da A[Catch: all -> 0x00d3, TryCatch #3 {all -> 0x00d3, blocks: (B:13:0x0064, B:15:0x006d, B:18:0x007e, B:43:0x00da, B:37:0x00cf, B:44:0x00db, B:45:0x00e2, B:33:0x00c9, B:19:0x0086, B:21:0x008f, B:30:0x00a0, B:31:0x00c6), top: B:119:0x0064, outer: #8, inners: #10, #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0101 A[Catch: all -> 0x00f9, TryCatch #8 {all -> 0x00f9, blocks: (B:3:0x001b, B:5:0x0029, B:6:0x0032, B:10:0x0050, B:12:0x005c, B:63:0x0101, B:57:0x00f5, B:64:0x0102, B:90:0x0160, B:94:0x016f, B:88:0x015b, B:97:0x0172, B:100:0x017e, B:101:0x0185, B:102:0x0186, B:103:0x0189, B:104:0x018a, B:105:0x019f, B:7:0x003a, B:9:0x0043, B:53:0x00ef, B:13:0x0064, B:15:0x006d, B:18:0x007e, B:43:0x00da, B:37:0x00cf, B:44:0x00db, B:45:0x00e2, B:33:0x00c9, B:19:0x0086, B:21:0x008f, B:30:0x00a0, B:31:0x00c6, B:84:0x0155, B:65:0x0113, B:68:0x011b, B:70:0x012b, B:72:0x0137, B:75:0x013e, B:76:0x0142, B:77:0x0149), top: B:128:0x001b, inners: #1, #2, #3, #5, #9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final ZipFileSystem openZip(Path path, FileSystem fileSystem, Function1<? super ZipEntry, Boolean> function1) throws IOException {
        Throwable th;
        Throwable th2;
        int intLe;
        CloseableKt.checkNotNullParameter(path, "zipPath");
        CloseableKt.checkNotNullParameter(fileSystem, "fileSystem");
        CloseableKt.checkNotNullParameter(function1, "predicate");
        FileHandle fileHandleOpenReadOnly = fileSystem.openReadOnly(path);
        try {
            long size = fileHandleOpenReadOnly.size() - 22;
            if (size < 0) {
                throw new IOException("not a zip: size=" + fileHandleOpenReadOnly.size());
            }
            long jMax = Math.max(size - 65536, 0L);
            do {
                BufferedSource bufferedSourceBuffer = Okio.buffer(fileHandleOpenReadOnly.source(size));
                try {
                    if (bufferedSourceBuffer.readIntLe() == END_OF_CENTRAL_DIRECTORY_SIGNATURE) {
                        EocdRecord eocdRecord = readEocdRecord(bufferedSourceBuffer);
                        String utf8 = bufferedSourceBuffer.readUtf8(eocdRecord.getCommentByteCount());
                        bufferedSourceBuffer.close();
                        long j = size - 20;
                        Throwable th3 = null;
                        if (j > 0) {
                            BufferedSource bufferedSourceBuffer2 = Okio.buffer(fileHandleOpenReadOnly.source(j));
                            try {
                                if (bufferedSourceBuffer2.readIntLe() == ZIP64_LOCATOR_SIGNATURE) {
                                    int intLe2 = bufferedSourceBuffer2.readIntLe();
                                    long longLe = bufferedSourceBuffer2.readLongLe();
                                    if (bufferedSourceBuffer2.readIntLe() != 1 || intLe2 != 0) {
                                        throw new IOException("unsupported zip: spanned");
                                    }
                                    BufferedSource bufferedSourceBuffer3 = Okio.buffer(fileHandleOpenReadOnly.source(longLe));
                                    try {
                                        intLe = bufferedSourceBuffer3.readIntLe();
                                    } catch (Throwable th4) {
                                        if (bufferedSourceBuffer3 != null) {
                                            try {
                                                bufferedSourceBuffer3.close();
                                            } catch (Throwable th5) {
                                                LazyKt.a(th4, th5);
                                            }
                                        }
                                        th2 = th4;
                                    }
                                    if (intLe != ZIP64_EOCD_RECORD_SIGNATURE) {
                                        throw new IOException("bad zip: expected " + getHex(ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(intLe));
                                    }
                                    eocdRecord = readZip64EocdRecord(bufferedSourceBuffer3, eocdRecord);
                                    if (bufferedSourceBuffer3 != null) {
                                        try {
                                            bufferedSourceBuffer3.close();
                                            th2 = null;
                                        } catch (Throwable th6) {
                                            th2 = th6;
                                        }
                                        if (th2 == null) {
                                            throw th2;
                                        }
                                    } else {
                                        th2 = null;
                                        if (th2 == null) {
                                        }
                                    }
                                }
                            } catch (Throwable th7) {
                                if (bufferedSourceBuffer2 != null) {
                                    try {
                                        bufferedSourceBuffer2.close();
                                    } catch (Throwable th8) {
                                        LazyKt.a(th7, th8);
                                    }
                                }
                                th = th7;
                            }
                            if (bufferedSourceBuffer2 != null) {
                                try {
                                    bufferedSourceBuffer2.close();
                                    th = null;
                                } catch (Throwable th9) {
                                    th = th9;
                                }
                                if (th == null) {
                                    throw th;
                                }
                            } else {
                                th = null;
                                if (th == null) {
                                }
                            }
                        }
                        ArrayList arrayList = new ArrayList();
                        BufferedSource bufferedSourceBuffer4 = Okio.buffer(fileHandleOpenReadOnly.source(eocdRecord.getCentralDirectoryOffset()));
                        try {
                            long entryCount = eocdRecord.getEntryCount();
                            for (long j2 = 0; j2 < entryCount; j2++) {
                                ZipEntry centralDirectoryZipEntry = readCentralDirectoryZipEntry(bufferedSourceBuffer4);
                                if (centralDirectoryZipEntry.getOffset() >= eocdRecord.getCentralDirectoryOffset()) {
                                    throw new IOException("bad zip: local file header offset >= central directory offset");
                                }
                                if (((Boolean) function1.invoke(centralDirectoryZipEntry)).booleanValue()) {
                                    arrayList.add(centralDirectoryZipEntry);
                                }
                            }
                            if (bufferedSourceBuffer4 != null) {
                                try {
                                    bufferedSourceBuffer4.close();
                                } catch (Throwable th10) {
                                    th3 = th10;
                                }
                            }
                        } catch (Throwable th11) {
                            th3 = th11;
                            if (bufferedSourceBuffer4 != null) {
                                try {
                                    bufferedSourceBuffer4.close();
                                } catch (Throwable th12) {
                                    LazyKt.a(th3, th12);
                                }
                            }
                        }
                        if (th3 != null) {
                            throw th3;
                        }
                        ZipFileSystem zipFileSystem = new ZipFileSystem(path, fileSystem, buildIndex(arrayList), utf8);
                        if (fileHandleOpenReadOnly != null) {
                            try {
                                fileHandleOpenReadOnly.close();
                            } catch (Throwable unused) {
                            }
                        }
                        return zipFileSystem;
                    }
                    bufferedSourceBuffer.close();
                    size--;
                } finally {
                    bufferedSourceBuffer.close();
                }
            } while (size >= jMax);
            throw new IOException("not a zip: end of central directory signature not found");
        } finally {
        }
    }

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, com.shadow.kotlin.jvm.functions.Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = new Function1<ZipEntry, Boolean>() { // from class: com.shadow.okio.internal.ZipFilesKt.openZip.1
                public final Boolean invoke(ZipEntry zipEntry) {
                    CloseableKt.checkNotNullParameter(zipEntry, "it");
                    return Boolean.TRUE;
                }
            };
        }
        return openZip(path, fileSystem, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ZipEntry readCentralDirectoryZipEntry(final BufferedSource bufferedSource) throws IOException {
        String str;
        long j;
        CloseableKt.checkNotNullParameter(bufferedSource, "<this>");
        int intLe = bufferedSource.readIntLe();
        if (intLe != CENTRAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(4L);
        short shortLe = bufferedSource.readShortLe();
        int i = shortLe & 65535;
        if ((shortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i));
        }
        int shortLe2 = bufferedSource.readShortLe() & 65535;
        int shortLe3 = bufferedSource.readShortLe() & 65535;
        int shortLe4 = bufferedSource.readShortLe() & 65535;
        long intLe2 = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref$LongRef ref$LongRef = new Ref$LongRef();
        ref$LongRef.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        final Ref$LongRef ref$LongRef2 = new Ref$LongRef();
        ref$LongRef2.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        int shortLe5 = bufferedSource.readShortLe() & 65535;
        int shortLe6 = bufferedSource.readShortLe() & 65535;
        int shortLe7 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(8L);
        final Ref$LongRef ref$LongRef3 = new Ref$LongRef();
        ref$LongRef3.element = bufferedSource.readIntLe() & MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE;
        String utf8 = bufferedSource.readUtf8(shortLe5);
        if (StringsKt.d(utf8, (char) 0)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        if (ref$LongRef2.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j = 8;
            str = utf8;
        } else {
            str = utf8;
            j = 0;
        }
        if (ref$LongRef.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        if (ref$LongRef3.element == MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
            j += 8;
        }
        final long j2 = j;
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        String str2 = str;
        readExtra(bufferedSource, shortLe6, new Function2<Integer, Long, Unit>() { // from class: com.shadow.okio.internal.ZipFilesKt.readCentralDirectoryZipEntry.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) throws IOException {
                invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
                return com.shadow.kotlin.Unit.INSTANCE;
            }

            public final void invoke(int i2, long j3) throws IOException {
                if (i2 != 1) {
                    if (i2 != 10) {
                        return;
                    }
                    if (j3 < 4) {
                        throw new IOException("bad zip: NTFS extra too short");
                    }
                    bufferedSource.skip(4L);
                    final BufferedSource bufferedSource2 = bufferedSource;
                    final Ref.ObjectRef<Long> objectRef = ref$ObjectRef;
                    final Ref.ObjectRef<Long> objectRef2 = ref$ObjectRef2;
                    final Ref.ObjectRef<Long> objectRef3 = ref$ObjectRef3;
                    ZipFilesKt.readExtra(bufferedSource2, (int) (j3 - 4), new Function2<Integer, Long, Unit>() { // from class: com.shadow.okio.internal.ZipFilesKt.readCentralDirectoryZipEntry.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) throws IOException {
                            invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
                            return com.shadow.kotlin.Unit.INSTANCE;
                        }

                        /* JADX WARN: Type inference failed for: r5v11, types: [T, java.lang.Long] */
                        /* JADX WARN: Type inference failed for: r5v5, types: [T, java.lang.Long] */
                        /* JADX WARN: Type inference failed for: r5v8, types: [T, java.lang.Long] */
                        public final void invoke(int i3, long j4) throws IOException {
                            if (i3 == 1) {
                                Ref.ObjectRef<Long> objectRef4 = objectRef;
                                if (objectRef4.element != 0) {
                                    throw new IOException("bad zip: NTFS extra attribute tag 0x0001 repeated");
                                }
                                if (j4 != 24) {
                                    throw new IOException("bad zip: NTFS extra attribute tag 0x0001 size != 24");
                                }
                                objectRef4.element = Long.valueOf(bufferedSource2.readLongLe());
                                objectRef2.element = Long.valueOf(bufferedSource2.readLongLe());
                                objectRef3.element = Long.valueOf(bufferedSource2.readLongLe());
                            }
                        }
                    });
                    return;
                }
                Ref$BooleanRef ref$BooleanRef2 = ref$BooleanRef;
                if (ref$BooleanRef2.element) {
                    throw new IOException("bad zip: zip64 extra repeated");
                }
                ref$BooleanRef2.element = true;
                if (j3 < j2) {
                    throw new IOException("bad zip: zip64 extra too short");
                }
                Ref$LongRef ref$LongRef4 = ref$LongRef2;
                long longLe = ref$LongRef4.element;
                if (longLe == ZipFilesKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE) {
                    longLe = bufferedSource.readLongLe();
                }
                ref$LongRef4.element = longLe;
                Ref$LongRef ref$LongRef5 = ref$LongRef;
                ref$LongRef5.element = ref$LongRef5.element == ZipFilesKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : 0L;
                Ref$LongRef ref$LongRef6 = ref$LongRef3;
                ref$LongRef6.element = ref$LongRef6.element == ZipFilesKt.MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE ? bufferedSource.readLongLe() : 0L;
            }
        });
        if (j2 > 0 && !ref$BooleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        return new ZipEntry(Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null).resolve(str2), StringsKt.f(str2, "/", false), bufferedSource.readUtf8(shortLe7), intLe2, ref$LongRef.element, ref$LongRef2.element, shortLe2, ref$LongRef3.element, shortLe4, shortLe3, (Long) ref$ObjectRef.element, (Long) ref$ObjectRef2.element, (Long) ref$ObjectRef3.element, null, null, null, 57344, null);
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) throws IOException {
        int shortLe = bufferedSource.readShortLe() & 65535;
        int shortLe2 = bufferedSource.readShortLe() & 65535;
        long shortLe3 = bufferedSource.readShortLe() & 65535;
        if (shortLe3 != (bufferedSource.readShortLe() & 65535) || shortLe != 0 || shortLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(4L);
        return new EocdRecord(shortLe3, MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE & bufferedSource.readIntLe(), bufferedSource.readShortLe() & 65535);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void readExtra(BufferedSource bufferedSource, int i, Function2<? super Integer, ? super Long, Unit> function2) throws IOException {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int shortLe = bufferedSource.readShortLe() & 65535;
            long shortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
            long j2 = j - 4;
            if (j2 < shortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            bufferedSource.require(shortLe2);
            long size = bufferedSource.getBuffer().size();
            function2.invoke(Integer.valueOf(shortLe), Long.valueOf(shortLe2));
            long size2 = (bufferedSource.getBuffer().size() + shortLe2) - size;
            if (size2 < 0) {
                throw new IOException(h.a(shortLe, "unsupported zip: too many bytes processed for "));
            }
            if (size2 > 0) {
                bufferedSource.getBuffer().skip(size2);
            }
            j = j2 - shortLe2;
        }
    }

    public static final ZipEntry readLocalHeader(BufferedSource bufferedSource, ZipEntry zipEntry) throws IOException {
        CloseableKt.checkNotNullParameter(bufferedSource, "<this>");
        CloseableKt.checkNotNullParameter(zipEntry, "centralDirectoryZipEntry");
        ZipEntry orSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, zipEntry);
        CloseableKt.checkNotNull(orSkipLocalHeader);
        return orSkipLocalHeader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ZipEntry readOrSkipLocalHeader(final BufferedSource bufferedSource, ZipEntry zipEntry) throws IOException {
        int intLe = bufferedSource.readIntLe();
        if (intLe != LOCAL_FILE_HEADER_SIGNATURE) {
            throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(intLe));
        }
        bufferedSource.skip(2L);
        short shortLe = bufferedSource.readShortLe();
        int i = shortLe & 65535;
        if ((shortLe & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(i));
        }
        bufferedSource.skip(18L);
        long shortLe2 = bufferedSource.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        int shortLe3 = bufferedSource.readShortLe() & 65535;
        bufferedSource.skip(shortLe2);
        if (zipEntry == null) {
            bufferedSource.skip(shortLe3);
            return null;
        }
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        readExtra(bufferedSource, shortLe3, new Function2<Integer, Long, Unit>() { // from class: com.shadow.okio.internal.ZipFilesKt.readOrSkipLocalHeader.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) throws IOException {
                invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
                return com.shadow.kotlin.Unit.INSTANCE;
            }

            /* JADX WARN: Type inference failed for: r11v10, types: [T, java.lang.Integer] */
            /* JADX WARN: Type inference failed for: r11v5, types: [T, java.lang.Integer] */
            /* JADX WARN: Type inference failed for: r11v8, types: [T, java.lang.Integer] */
            public final void invoke(int i2, long j) throws IOException {
                if (i2 == ZipFilesKt.HEADER_ID_EXTENDED_TIMESTAMP) {
                    if (j < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    byte b = bufferedSource.readByte();
                    boolean z = (b & 1) == 1;
                    boolean z2 = (b & 2) == 2;
                    boolean z3 = (b & 4) == 4;
                    BufferedSource bufferedSource2 = bufferedSource;
                    long j2 = z ? 5L : 1L;
                    if (z2) {
                        j2 += 4;
                    }
                    if (z3) {
                        j2 += 4;
                    }
                    if (j < j2) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        ref$ObjectRef.element = Integer.valueOf(bufferedSource2.readIntLe());
                    }
                    if (z2) {
                        ref$ObjectRef2.element = Integer.valueOf(bufferedSource.readIntLe());
                    }
                    if (z3) {
                        ref$ObjectRef3.element = Integer.valueOf(bufferedSource.readIntLe());
                    }
                }
            }
        });
        return zipEntry.copy$okio((Integer) ref$ObjectRef.element, (Integer) ref$ObjectRef2.element, (Integer) ref$ObjectRef3.element);
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) throws IOException {
        bufferedSource.skip(12L);
        int intLe = bufferedSource.readIntLe();
        int intLe2 = bufferedSource.readIntLe();
        long longLe = bufferedSource.readLongLe();
        if (longLe != bufferedSource.readLongLe() || intLe != 0 || intLe2 != 0) {
            throw new IOException("unsupported zip: spanned");
        }
        bufferedSource.skip(8L);
        return new EocdRecord(longLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) throws IOException {
        CloseableKt.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, null);
    }
}
