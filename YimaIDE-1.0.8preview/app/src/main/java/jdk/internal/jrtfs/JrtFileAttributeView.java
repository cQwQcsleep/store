package jdk.internal.jrtfs;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class JrtFileAttributeView implements BasicFileAttributeView {
    private final boolean isJrtView;
    private final LinkOption[] options;
    private final JrtPath path;

    public enum AttrID {
        size,
        creationTime,
        lastAccessTime,
        lastModifiedTime,
        isDirectory,
        isRegularFile,
        isSymbolicLink,
        isOther,
        fileKey,
        compressedSize,
        extension
    }

    private JrtFileAttributeView(JrtPath jrtPath, boolean z, LinkOption... linkOptionArr) {
        this.path = jrtPath;
        this.isJrtView = z;
        this.options = linkOptionArr;
    }

    public static Object attribute(AttrID attrID, JrtFileAttributes jrtFileAttributes, boolean z) {
        switch (attrID) {
            case size:
                return Long.valueOf(jrtFileAttributes.size());
            case creationTime:
                return jrtFileAttributes.creationTime();
            case lastAccessTime:
                return jrtFileAttributes.lastAccessTime();
            case lastModifiedTime:
                return jrtFileAttributes.lastModifiedTime();
            case isDirectory:
                return Boolean.valueOf(jrtFileAttributes.isDirectory());
            case isRegularFile:
                return Boolean.valueOf(jrtFileAttributes.isRegularFile());
            case isSymbolicLink:
                return Boolean.valueOf(jrtFileAttributes.isSymbolicLink());
            case isOther:
                return Boolean.valueOf(jrtFileAttributes.isOther());
            case fileKey:
                return jrtFileAttributes.fileKey();
            case compressedSize:
                if (z) {
                    return Long.valueOf(jrtFileAttributes.compressedSize());
                }
                return null;
            case extension:
                if (z) {
                    return jrtFileAttributes.extension();
                }
                return null;
            default:
                return null;
        }
    }

    public static JrtFileAttributeView get(JrtPath jrtPath, String str, LinkOption... linkOptionArr) {
        Objects.requireNonNull(str);
        if (str.equals("basic")) {
            return new JrtFileAttributeView(jrtPath, false, linkOptionArr);
        }
        if (str.equals("jrt")) {
            return new JrtFileAttributeView(jrtPath, true, linkOptionArr);
        }
        return null;
    }

    public static Map<String, Object> readAttributes(JrtPath jrtPath, String str, LinkOption... linkOptionArr) throws IOException {
        boolean z;
        int iIndexOf = str.indexOf(58);
        int i = 0;
        if (iIndexOf != -1) {
            int i2 = iIndexOf + 1;
            String strSubstring = str.substring(0, iIndexOf);
            if (!strSubstring.equals("basic") && !strSubstring.equals("jrt")) {
                b9g.a("view <", strSubstring, "> is not supported");
                return null;
            }
            str = str.substring(i2);
            z = true;
        } else {
            z = false;
        }
        JrtFileAttributes attributes = jrtPath.getAttributes(new LinkOption[0]);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if ("*".equals(str)) {
            AttrID[] attrIDArrValues = AttrID.values();
            int length = attrIDArrValues.length;
            while (i < length) {
                AttrID attrID = attrIDArrValues[i];
                linkedHashMap.put(attrID.name(), attribute(attrID, attributes, z));
                i++;
            }
        } else {
            String[] strArrSplit = str.split(",");
            int length2 = strArrSplit.length;
            while (i < length2) {
                String str2 = strArrSplit[i];
                linkedHashMap.put(str2, attribute(AttrID.valueOf(str2), attributes, z));
                i++;
            }
        }
        return linkedHashMap;
    }

    public static void setAttribute(JrtPath jrtPath, String str, Object obj) throws IOException {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf != -1) {
            int i = iIndexOf + 1;
            String strSubstring = str.substring(0, iIndexOf);
            if (!strSubstring.equals("basic") && !strSubstring.equals("jrt")) {
                b9g.a("view <", strSubstring, "> is not supported");
                return;
            }
            str = str.substring(i);
        }
        try {
            AttrID attrIDValueOf = AttrID.valueOf(str);
            if (attrIDValueOf == AttrID.lastModifiedTime) {
                jrtPath.setTimes((FileTime) obj, null, null);
            } else if (attrIDValueOf == AttrID.lastAccessTime) {
                jrtPath.setTimes(null, (FileTime) obj, null);
            } else if (attrIDValueOf == AttrID.creationTime) {
                jrtPath.setTimes(null, null, (FileTime) obj);
            }
        } catch (IllegalArgumentException unused) {
            b9g.a("'", str, "' is unknown or read-only attribute");
        }
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView
    public String name() {
        return this.isJrtView ? "jrt" : "basic";
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView
    public void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
        this.path.setTimes(fileTime, fileTime2, fileTime3);
    }

    public static <V extends FileAttributeView> V get(JrtPath jrtPath, Class<V> cls, LinkOption... linkOptionArr) {
        Objects.requireNonNull(cls);
        if (cls == BasicFileAttributeView.class) {
            return new JrtFileAttributeView(jrtPath, false, linkOptionArr);
        }
        if (cls == JrtFileAttributeView.class) {
            return new JrtFileAttributeView(jrtPath, true, linkOptionArr);
        }
        return null;
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView
    public JrtFileAttributes readAttributes() throws IOException {
        return this.path.getAttributes(this.options);
    }
}
