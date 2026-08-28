package com.shadow.okio.internal;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Buffer;
import com.shadow.okio.ByteString;
import com.shadow.okio.Path;
import core.pro.android.notify.h;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* renamed from: com.shadow.okio.internal.-Path, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class Path {
    private static final ByteString ANY_SLASH;
    private static final ByteString BACKSLASH;
    private static final ByteString DOT;
    private static final ByteString DOT_DOT;
    private static final ByteString SLASH;

    static {
        ByteString.Companion companion = ByteString.Companion;
        SLASH = companion.encodeUtf8("/");
        BACKSLASH = companion.encodeUtf8("\\");
        ANY_SLASH = companion.encodeUtf8("/\\");
        DOT = companion.encodeUtf8(".");
        DOT_DOT = companion.encodeUtf8("..");
    }

    public static final int commonCompareTo(com.shadow.okio.Path path, com.shadow.okio.Path path2) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(path2, "other");
        return path.getBytes$okio().compareTo(path2.getBytes$okio());
    }

    public static final boolean commonEquals(com.shadow.okio.Path path, Object obj) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return (obj instanceof com.shadow.okio.Path) && CloseableKt.areEqual(((com.shadow.okio.Path) obj).getBytes$okio(), path.getBytes$okio());
    }

    public static final int commonHashCode(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return path.getBytes$okio().hashCode();
    }

    public static final boolean commonIsAbsolute(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return rootLength(path) != -1;
    }

    public static final boolean commonIsRelative(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return rootLength(path) == -1;
    }

    public static final boolean commonIsRoot(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return rootLength(path) == path.getBytes$okio().size();
    }

    public static final String commonName(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return path.nameBytes().utf8();
    }

    public static final ByteString commonNameBytes(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        int indexOfLastSlash = getIndexOfLastSlash(path);
        return indexOfLastSlash != -1 ? ByteString.substring$default(path.getBytes$okio(), indexOfLastSlash + 1, 0, 2, null) : (path.volumeLetter() == null || path.getBytes$okio().size() != 2) ? path.getBytes$okio() : ByteString.EMPTY;
    }

    public static final com.shadow.okio.Path commonNormalized(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return com.shadow.okio.Path.Companion.get(path.toString(), true);
    }

    public static final com.shadow.okio.Path commonParent(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        if (CloseableKt.areEqual(path.getBytes$okio(), DOT) || CloseableKt.areEqual(path.getBytes$okio(), SLASH) || CloseableKt.areEqual(path.getBytes$okio(), BACKSLASH) || lastSegmentIsDotDot(path)) {
            return null;
        }
        int indexOfLastSlash = getIndexOfLastSlash(path);
        if (indexOfLastSlash == 2 && path.volumeLetter() != null) {
            if (path.getBytes$okio().size() == 3) {
                return null;
            }
            return new com.shadow.okio.Path(ByteString.substring$default(path.getBytes$okio(), 0, 3, 1, null));
        }
        if (indexOfLastSlash == 1 && path.getBytes$okio().startsWith(BACKSLASH)) {
            return null;
        }
        if (indexOfLastSlash != -1 || path.volumeLetter() == null) {
            return indexOfLastSlash == -1 ? new com.shadow.okio.Path(DOT) : indexOfLastSlash == 0 ? new com.shadow.okio.Path(ByteString.substring$default(path.getBytes$okio(), 0, 1, 1, null)) : new com.shadow.okio.Path(ByteString.substring$default(path.getBytes$okio(), 0, indexOfLastSlash, 1, null));
        }
        if (path.getBytes$okio().size() == 2) {
            return null;
        }
        return new com.shadow.okio.Path(ByteString.substring$default(path.getBytes$okio(), 0, 2, 1, null));
    }

    public static final com.shadow.okio.Path commonRelativeTo(com.shadow.okio.Path path, com.shadow.okio.Path path2) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(path2, "other");
        if (!CloseableKt.areEqual(path.getRoot(), path2.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + path + " and " + path2).toString());
        }
        List<ByteString> segmentsBytes = path.getSegmentsBytes();
        List<ByteString> segmentsBytes2 = path2.getSegmentsBytes();
        int iMin = Math.min(segmentsBytes.size(), segmentsBytes2.size());
        int i = 0;
        while (i < iMin && CloseableKt.areEqual(segmentsBytes.get(i), segmentsBytes2.get(i))) {
            i++;
        }
        if (i == iMin && path.getBytes$okio().size() == path2.getBytes$okio().size()) {
            return Path.Companion.get$default(com.shadow.okio.Path.Companion, ".", false, 1, (Object) null);
        }
        if (segmentsBytes2.subList(i, segmentsBytes2.size()).indexOf(DOT_DOT) != -1) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + path + " and " + path2).toString());
        }
        Buffer buffer = new Buffer();
        ByteString slash = getSlash(path2);
        if (slash == null && (slash = getSlash(path)) == null) {
            slash = toSlash(com.shadow.okio.Path.DIRECTORY_SEPARATOR);
        }
        int size = segmentsBytes2.size();
        for (int i2 = i; i2 < size; i2++) {
            buffer.write(DOT_DOT);
            buffer.write(slash);
        }
        int size2 = segmentsBytes.size();
        while (i < size2) {
            buffer.write(segmentsBytes.get(i));
            buffer.write(slash);
            i++;
        }
        return toPath(buffer, false);
    }

    public static final com.shadow.okio.Path commonResolve(com.shadow.okio.Path path, String str, boolean z) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(str, "child");
        return commonResolve(path, toPath(new Buffer().writeUtf8(str), false), z);
    }

    public static final com.shadow.okio.Path commonRoot(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        int iRootLength = rootLength(path);
        if (iRootLength == -1) {
            return null;
        }
        return new com.shadow.okio.Path(path.getBytes$okio().substring(0, iRootLength));
    }

    public static final List<String> commonSegments(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        ArrayList arrayList = new ArrayList();
        int iRootLength = rootLength(path);
        if (iRootLength == -1) {
            iRootLength = 0;
        } else if (iRootLength < path.getBytes$okio().size() && path.getBytes$okio().getByte(iRootLength) == 92) {
            iRootLength++;
        }
        int size = path.getBytes$okio().size();
        int i = iRootLength;
        while (iRootLength < size) {
            if (path.getBytes$okio().getByte(iRootLength) == 47 || path.getBytes$okio().getByte(iRootLength) == 92) {
                arrayList.add(path.getBytes$okio().substring(i, iRootLength));
                i = iRootLength + 1;
            }
            iRootLength++;
        }
        if (i < path.getBytes$okio().size()) {
            arrayList.add(path.getBytes$okio().substring(i, path.getBytes$okio().size()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ByteString) it.next()).utf8());
        }
        return arrayList2;
    }

    public static final List<ByteString> commonSegmentsBytes(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        ArrayList arrayList = new ArrayList();
        int iRootLength = rootLength(path);
        if (iRootLength == -1) {
            iRootLength = 0;
        } else if (iRootLength < path.getBytes$okio().size() && path.getBytes$okio().getByte(iRootLength) == 92) {
            iRootLength++;
        }
        int size = path.getBytes$okio().size();
        int i = iRootLength;
        while (iRootLength < size) {
            if (path.getBytes$okio().getByte(iRootLength) == 47 || path.getBytes$okio().getByte(iRootLength) == 92) {
                arrayList.add(path.getBytes$okio().substring(i, iRootLength));
                i = iRootLength + 1;
            }
            iRootLength++;
        }
        if (i < path.getBytes$okio().size()) {
            arrayList.add(path.getBytes$okio().substring(i, path.getBytes$okio().size()));
        }
        return arrayList;
    }

    public static final com.shadow.okio.Path commonToPath(String str, boolean z) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        return toPath(new Buffer().writeUtf8(str), z);
    }

    public static final String commonToString(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        return path.getBytes$okio().utf8();
    }

    public static final Character commonVolumeLetter(com.shadow.okio.Path path) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        if (ByteString.indexOf$default(path.getBytes$okio(), SLASH, 0, 2, (Object) null) != -1 || path.getBytes$okio().size() < 2 || path.getBytes$okio().getByte(1) != 58) {
            return null;
        }
        char c = (char) path.getBytes$okio().getByte(0);
        if (('a' > c || c >= '{') && ('A' > c || c >= '[')) {
            return null;
        }
        return Character.valueOf(c);
    }

    private static /* synthetic */ void getANY_SLASH$annotations() {
    }

    private static /* synthetic */ void getBACKSLASH$annotations() {
    }

    private static /* synthetic */ void getDOT$annotations() {
    }

    private static /* synthetic */ void getDOT_DOT$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getIndexOfLastSlash(com.shadow.okio.Path path) {
        int iLastIndexOf$default = ByteString.lastIndexOf$default(path.getBytes$okio(), SLASH, 0, 2, (Object) null);
        return iLastIndexOf$default != -1 ? iLastIndexOf$default : ByteString.lastIndexOf$default(path.getBytes$okio(), BACKSLASH, 0, 2, (Object) null);
    }

    private static /* synthetic */ void getSLASH$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ByteString getSlash(com.shadow.okio.Path path) {
        ByteString bytes$okio = path.getBytes$okio();
        ByteString byteString = SLASH;
        if (ByteString.indexOf$default(bytes$okio, byteString, 0, 2, (Object) null) != -1) {
            return byteString;
        }
        ByteString bytes$okio2 = path.getBytes$okio();
        ByteString byteString2 = BACKSLASH;
        if (ByteString.indexOf$default(bytes$okio2, byteString2, 0, 2, (Object) null) != -1) {
            return byteString2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean lastSegmentIsDotDot(com.shadow.okio.Path path) {
        return path.getBytes$okio().endsWith(DOT_DOT) && (path.getBytes$okio().size() == 2 || path.getBytes$okio().rangeEquals(path.getBytes$okio().size() + (-3), SLASH, 0, 1) || path.getBytes$okio().rangeEquals(path.getBytes$okio().size() + (-3), BACKSLASH, 0, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int rootLength(com.shadow.okio.Path path) {
        if (path.getBytes$okio().size() == 0) {
            return -1;
        }
        if (path.getBytes$okio().getByte(0) == 47) {
            return 1;
        }
        if (path.getBytes$okio().getByte(0) == 92) {
            if (path.getBytes$okio().size() <= 2 || path.getBytes$okio().getByte(1) != 92) {
                return 1;
            }
            int iIndexOf = path.getBytes$okio().indexOf(BACKSLASH, 2);
            return iIndexOf == -1 ? path.getBytes$okio().size() : iIndexOf;
        }
        if (path.getBytes$okio().size() > 2 && path.getBytes$okio().getByte(1) == 58 && path.getBytes$okio().getByte(2) == 92) {
            char c = (char) path.getBytes$okio().getByte(0);
            if ('a' <= c && c < '{') {
                return 3;
            }
            if ('A' <= c && c < '[') {
                return 3;
            }
        }
        return -1;
    }

    private static final boolean startsWithVolumeLetterAndColon(Buffer buffer, ByteString byteString) {
        if (!CloseableKt.areEqual(byteString, BACKSLASH) || buffer.size() < 2 || buffer.getByte(1L) != 58) {
            return false;
        }
        char c = (char) buffer.getByte(0L);
        return ('a' <= c && c < '{') || ('A' <= c && c < '[');
    }

    public static final com.shadow.okio.Path toPath(Buffer buffer, boolean z) throws EOFException {
        ByteString byteString;
        ByteString byteString2;
        CloseableKt.checkNotNullParameter(buffer, "<this>");
        Buffer buffer2 = new Buffer();
        ByteString slash = null;
        int i = 0;
        while (true) {
            if (!buffer.rangeEquals(0L, SLASH)) {
                byteString = BACKSLASH;
                if (!buffer.rangeEquals(0L, byteString)) {
                    break;
                }
            }
            byte b = buffer.readByte();
            if (slash == null) {
                slash = toSlash(b);
            }
            i++;
        }
        boolean z2 = i >= 2 && CloseableKt.areEqual(slash, byteString);
        if (z2) {
            CloseableKt.checkNotNull(slash);
            buffer2.write(slash);
            buffer2.write(slash);
        } else if (i > 0) {
            CloseableKt.checkNotNull(slash);
            buffer2.write(slash);
        } else {
            long jIndexOfElement = buffer.indexOfElement(ANY_SLASH);
            if (slash == null) {
                slash = jIndexOfElement == -1 ? toSlash(com.shadow.okio.Path.DIRECTORY_SEPARATOR) : toSlash(buffer.getByte(jIndexOfElement));
            }
            if (startsWithVolumeLetterAndColon(buffer, slash)) {
                if (jIndexOfElement == 2) {
                    buffer2.write(buffer, 3L);
                } else {
                    buffer2.write(buffer, 2L);
                }
            }
        }
        boolean z3 = buffer2.size() > 0;
        ArrayList arrayList = new ArrayList();
        while (!buffer.exhausted()) {
            long jIndexOfElement2 = buffer.indexOfElement(ANY_SLASH);
            if (jIndexOfElement2 == -1) {
                byteString2 = buffer.readByteString();
            } else {
                byteString2 = buffer.readByteString(jIndexOfElement2);
                buffer.readByte();
            }
            ByteString byteString3 = DOT_DOT;
            if (CloseableKt.areEqual(byteString2, byteString3)) {
                if (!z3 || !arrayList.isEmpty()) {
                    if (z) {
                        if (!z3) {
                            if (!arrayList.isEmpty()) {
                                if (arrayList.isEmpty()) {
                                    throw new NoSuchElementException("List is empty.");
                                }
                                if (CloseableKt.areEqual(arrayList.get(arrayList.size() - 1), byteString3)) {
                                }
                            }
                        }
                        if (!z2 || arrayList.size() != 1) {
                            if (!arrayList.isEmpty()) {
                                arrayList.remove(arrayList.size() - 1);
                            }
                        }
                    }
                    arrayList.add(byteString2);
                }
            } else if (!CloseableKt.areEqual(byteString2, DOT) && !CloseableKt.areEqual(byteString2, ByteString.EMPTY)) {
                arrayList.add(byteString2);
            }
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                buffer2.write(slash);
            }
            buffer2.write((ByteString) arrayList.get(i2));
        }
        if (buffer2.size() == 0) {
            buffer2.write(DOT);
        }
        return new com.shadow.okio.Path(buffer2.readByteString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ByteString toSlash(String str) {
        if (CloseableKt.areEqual(str, "/")) {
            return SLASH;
        }
        if (CloseableKt.areEqual(str, "\\")) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException("not a directory separator: " + str);
    }

    public static final com.shadow.okio.Path commonResolve(com.shadow.okio.Path path, ByteString byteString, boolean z) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(byteString, "child");
        return commonResolve(path, toPath(new Buffer().write(byteString), false), z);
    }

    private static final ByteString toSlash(byte b) {
        if (b == 47) {
            return SLASH;
        }
        if (b == 92) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException(h.a(b, "not a directory separator: "));
    }

    public static final com.shadow.okio.Path commonResolve(com.shadow.okio.Path path, Buffer buffer, boolean z) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(buffer, "child");
        return commonResolve(path, toPath(buffer, false), z);
    }

    public static final com.shadow.okio.Path commonResolve(com.shadow.okio.Path path, com.shadow.okio.Path path2, boolean z) {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(path2, "child");
        if (path2.isAbsolute() || path2.volumeLetter() != null) {
            return path2;
        }
        ByteString slash = getSlash(path);
        if (slash == null && (slash = getSlash(path2)) == null) {
            slash = toSlash(com.shadow.okio.Path.DIRECTORY_SEPARATOR);
        }
        Buffer buffer = new Buffer();
        buffer.write(path.getBytes$okio());
        if (buffer.size() > 0) {
            buffer.write(slash);
        }
        buffer.write(path2.getBytes$okio());
        return toPath(buffer, z);
    }
}
