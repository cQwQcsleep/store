package com.shadow.okio;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Path implements Comparable<Path> {
    public static final Companion Companion = new Companion(null);
    public static final String DIRECTORY_SEPARATOR;
    private final ByteString bytes;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Path get$default(Companion companion, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(str, z);
        }

        public final Path get(File file) {
            CloseableKt.checkNotNullParameter(file, "<this>");
            return get$default(this, file, false, 1, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ Path get$default(Companion companion, File file, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(file, z);
        }

        public final Path get(String str) {
            CloseableKt.checkNotNullParameter(str, "<this>");
            return get$default(this, str, false, 1, (Object) null);
        }

        public static /* synthetic */ Path get$default(Companion companion, java.nio.file.Path path, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(path, z);
        }

        public final Path get(java.nio.file.Path path) {
            CloseableKt.checkNotNullParameter(path, "<this>");
            return get$default(this, path, false, 1, (Object) null);
        }

        public final Path get(String str, boolean z) {
            CloseableKt.checkNotNullParameter(str, "<this>");
            return com.shadow.okio.internal.Path.commonToPath(str, z);
        }

        public final Path get(File file, boolean z) {
            CloseableKt.checkNotNullParameter(file, "<this>");
            String string = file.toString();
            CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
            return get(string, z);
        }

        public final Path get(java.nio.file.Path path, boolean z) {
            CloseableKt.checkNotNullParameter(path, "<this>");
            return get(path.toString(), z);
        }
    }

    static {
        String str = File.separator;
        CloseableKt.checkNotNullExpressionValue(str, "separator");
        DIRECTORY_SEPARATOR = str;
    }

    public Path(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "bytes");
        this.bytes = byteString;
    }

    public static final Path get(File file) {
        return Companion.get(file);
    }

    public static /* synthetic */ Path resolve$default(Path path, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(str, z);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Path) && CloseableKt.areEqual(((Path) obj).getBytes$okio(), getBytes$okio());
    }

    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    public final Path getRoot() {
        int iRootLength = com.shadow.okio.internal.Path.rootLength(this);
        if (iRootLength == -1) {
            return null;
        }
        return new Path(getBytes$okio().substring(0, iRootLength));
    }

    public final List<String> getSegments() {
        ArrayList arrayList = new ArrayList();
        int iRootLength = com.shadow.okio.internal.Path.rootLength(this);
        if (iRootLength == -1) {
            iRootLength = 0;
        } else if (iRootLength < getBytes$okio().size() && getBytes$okio().getByte(iRootLength) == 92) {
            iRootLength++;
        }
        int size = getBytes$okio().size();
        int i = iRootLength;
        while (iRootLength < size) {
            if (getBytes$okio().getByte(iRootLength) == 47 || getBytes$okio().getByte(iRootLength) == 92) {
                arrayList.add(getBytes$okio().substring(i, iRootLength));
                i = iRootLength + 1;
            }
            iRootLength++;
        }
        if (i < getBytes$okio().size()) {
            arrayList.add(getBytes$okio().substring(i, getBytes$okio().size()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ByteString) it.next()).utf8());
        }
        return arrayList2;
    }

    public final List<ByteString> getSegmentsBytes() {
        ArrayList arrayList = new ArrayList();
        int iRootLength = com.shadow.okio.internal.Path.rootLength(this);
        if (iRootLength == -1) {
            iRootLength = 0;
        } else if (iRootLength < getBytes$okio().size() && getBytes$okio().getByte(iRootLength) == 92) {
            iRootLength++;
        }
        int size = getBytes$okio().size();
        int i = iRootLength;
        while (iRootLength < size) {
            if (getBytes$okio().getByte(iRootLength) == 47 || getBytes$okio().getByte(iRootLength) == 92) {
                arrayList.add(getBytes$okio().substring(i, iRootLength));
                i = iRootLength + 1;
            }
            iRootLength++;
        }
        if (i < getBytes$okio().size()) {
            arrayList.add(getBytes$okio().substring(i, getBytes$okio().size()));
        }
        return arrayList;
    }

    public int hashCode() {
        return getBytes$okio().hashCode();
    }

    public final boolean isAbsolute() {
        return com.shadow.okio.internal.Path.rootLength(this) != -1;
    }

    public final boolean isRelative() {
        return com.shadow.okio.internal.Path.rootLength(this) == -1;
    }

    public final boolean isRoot() {
        return com.shadow.okio.internal.Path.rootLength(this) == getBytes$okio().size();
    }

    public final String name() {
        return nameBytes().utf8();
    }

    public final ByteString nameBytes() {
        int indexOfLastSlash = com.shadow.okio.internal.Path.getIndexOfLastSlash(this);
        return indexOfLastSlash != -1 ? ByteString.substring$default(getBytes$okio(), indexOfLastSlash + 1, 0, 2, null) : (volumeLetter() == null || getBytes$okio().size() != 2) ? getBytes$okio() : ByteString.EMPTY;
    }

    public final Path normalized() {
        return Companion.get(toString(), true);
    }

    public final Path parent() {
        Path path;
        if (CloseableKt.areEqual(getBytes$okio(), com.shadow.okio.internal.Path.DOT) || CloseableKt.areEqual(getBytes$okio(), com.shadow.okio.internal.Path.SLASH) || CloseableKt.areEqual(getBytes$okio(), com.shadow.okio.internal.Path.BACKSLASH) || com.shadow.okio.internal.Path.lastSegmentIsDotDot(this)) {
            return null;
        }
        int indexOfLastSlash = com.shadow.okio.internal.Path.getIndexOfLastSlash(this);
        if (indexOfLastSlash != 2 || volumeLetter() == null) {
            if (indexOfLastSlash == 1 && getBytes$okio().startsWith(com.shadow.okio.internal.Path.BACKSLASH)) {
                return null;
            }
            if (indexOfLastSlash != -1 || volumeLetter() == null) {
                if (indexOfLastSlash == -1) {
                    return new Path(com.shadow.okio.internal.Path.DOT);
                }
                if (indexOfLastSlash != 0) {
                    return new Path(ByteString.substring$default(getBytes$okio(), 0, indexOfLastSlash, 1, null));
                }
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 1, 1, null));
            } else {
                if (getBytes$okio().size() == 2) {
                    return null;
                }
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 2, 1, null));
            }
        } else {
            if (getBytes$okio().size() == 3) {
                return null;
            }
            path = new Path(ByteString.substring$default(getBytes$okio(), 0, 3, 1, null));
        }
        return path;
    }

    public final Path relativeTo(Path path) {
        CloseableKt.checkNotNullParameter(path, "other");
        if (!CloseableKt.areEqual(getRoot(), path.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + path).toString());
        }
        List<ByteString> segmentsBytes = getSegmentsBytes();
        List<ByteString> segmentsBytes2 = path.getSegmentsBytes();
        int iMin = Math.min(segmentsBytes.size(), segmentsBytes2.size());
        int i = 0;
        while (i < iMin && CloseableKt.areEqual(segmentsBytes.get(i), segmentsBytes2.get(i))) {
            i++;
        }
        if (i == iMin && getBytes$okio().size() == path.getBytes$okio().size()) {
            return Companion.get$default(Companion, ".", false, 1, (Object) null);
        }
        if (segmentsBytes2.subList(i, segmentsBytes2.size()).indexOf(com.shadow.okio.internal.Path.DOT_DOT) != -1) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + path).toString());
        }
        Buffer buffer = new Buffer();
        ByteString slash = com.shadow.okio.internal.Path.getSlash(path);
        if (slash == null && (slash = com.shadow.okio.internal.Path.getSlash(this)) == null) {
            slash = com.shadow.okio.internal.Path.toSlash(DIRECTORY_SEPARATOR);
        }
        int size = segmentsBytes2.size();
        for (int i2 = i; i2 < size; i2++) {
            buffer.write(com.shadow.okio.internal.Path.DOT_DOT);
            buffer.write(slash);
        }
        int size2 = segmentsBytes.size();
        while (i < size2) {
            buffer.write(segmentsBytes.get(i));
            buffer.write(slash);
            i++;
        }
        return com.shadow.okio.internal.Path.toPath(buffer, false);
    }

    public final Path resolve(Path path) {
        CloseableKt.checkNotNullParameter(path, "child");
        return com.shadow.okio.internal.Path.commonResolve(this, path, false);
    }

    public final File toFile() {
        return new File(toString());
    }

    public final java.nio.file.Path toNioPath() {
        java.nio.file.Path path = Paths.get(toString(), new String[0]);
        CloseableKt.checkNotNullExpressionValue(path, "get(...)");
        return path;
    }

    public String toString() {
        return getBytes$okio().utf8();
    }

    public final Character volumeLetter() {
        if (ByteString.indexOf$default(getBytes$okio(), com.shadow.okio.internal.Path.SLASH, 0, 2, (Object) null) != -1 || getBytes$okio().size() < 2 || getBytes$okio().getByte(1) != 58) {
            return null;
        }
        char c = (char) getBytes$okio().getByte(0);
        if (('a' > c || c >= '{') && ('A' > c || c >= '[')) {
            return null;
        }
        return Character.valueOf(c);
    }

    public static final Path get(File file, boolean z) {
        return Companion.get(file, z);
    }

    public static /* synthetic */ Path resolve$default(Path path, ByteString byteString, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(byteString, z);
    }

    @Override // java.lang.Comparable
    public int compareTo(Path path) {
        CloseableKt.checkNotNullParameter(path, "other");
        return getBytes$okio().compareTo(path.getBytes$okio());
    }

    public final Path resolve(Path path, boolean z) {
        CloseableKt.checkNotNullParameter(path, "child");
        return com.shadow.okio.internal.Path.commonResolve(this, path, z);
    }

    public static final Path get(String str) {
        return Companion.get(str);
    }

    public static /* synthetic */ Path resolve$default(Path path, Path path2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return path.resolve(path2, z);
    }

    public final Path resolve(String str) {
        CloseableKt.checkNotNullParameter(str, "child");
        return com.shadow.okio.internal.Path.commonResolve(this, com.shadow.okio.internal.Path.toPath(new Buffer().writeUtf8(str), false), false);
    }

    public static final Path get(String str, boolean z) {
        return Companion.get(str, z);
    }

    public static final Path get(java.nio.file.Path path) {
        return Companion.get(path);
    }

    public final Path resolve(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "child");
        return com.shadow.okio.internal.Path.commonResolve(this, com.shadow.okio.internal.Path.toPath(new Buffer().write(byteString), false), false);
    }

    public static final Path get(java.nio.file.Path path, boolean z) {
        return Companion.get(path, z);
    }

    public final Path resolve(String str, boolean z) {
        CloseableKt.checkNotNullParameter(str, "child");
        return com.shadow.okio.internal.Path.commonResolve(this, com.shadow.okio.internal.Path.toPath(new Buffer().writeUtf8(str), false), z);
    }

    public final Path resolve(ByteString byteString, boolean z) {
        CloseableKt.checkNotNullParameter(byteString, "child");
        return com.shadow.okio.internal.Path.commonResolve(this, com.shadow.okio.internal.Path.toPath(new Buffer().write(byteString), false), z);
    }
}
