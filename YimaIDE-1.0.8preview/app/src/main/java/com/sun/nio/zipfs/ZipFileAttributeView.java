package com.sun.nio.zipfs;

import defpackage.b9g;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ZipFileAttributeView implements BasicFileAttributeView {
    private final boolean isZipView;
    private final ZipPath path;

    /* JADX INFO: renamed from: com.sun.nio.zipfs.ZipFileAttributeView$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID;

        static {
            int[] iArr = new int[AttrID.values().length];
            $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID = iArr;
            try {
                iArr[AttrID.size.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.creationTime.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.lastAccessTime.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.lastModifiedTime.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.isDirectory.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.isRegularFile.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.isSymbolicLink.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.isOther.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.fileKey.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.compressedSize.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.crc.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[AttrID.method.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

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
        crc,
        method
    }

    private ZipFileAttributeView(ZipPath zipPath, boolean z) {
        this.path = zipPath;
        this.isZipView = z;
    }

    public static ZipFileAttributeView get(ZipPath zipPath, String str) {
        str.getClass();
        if (str.equals("basic")) {
            return new ZipFileAttributeView(zipPath, false);
        }
        if (str.equals("zip")) {
            return new ZipFileAttributeView(zipPath, true);
        }
        return null;
    }

    public Object attribute(AttrID attrID, ZipFileAttributes zipFileAttributes) {
        switch (AnonymousClass1.$SwitchMap$com$sun$nio$zipfs$ZipFileAttributeView$AttrID[attrID.ordinal()]) {
            case 1:
                return Long.valueOf(zipFileAttributes.size());
            case 2:
                return zipFileAttributes.creationTime();
            case 3:
                return zipFileAttributes.lastAccessTime();
            case 4:
                return zipFileAttributes.lastModifiedTime();
            case 5:
                return Boolean.valueOf(zipFileAttributes.isDirectory());
            case 6:
                return Boolean.valueOf(zipFileAttributes.isRegularFile());
            case 7:
                return Boolean.valueOf(zipFileAttributes.isSymbolicLink());
            case 8:
                return Boolean.valueOf(zipFileAttributes.isOther());
            case 9:
                return zipFileAttributes.fileKey();
            case 10:
                if (this.isZipView) {
                    return Long.valueOf(zipFileAttributes.compressedSize());
                }
                return null;
            case 11:
                if (this.isZipView) {
                    return Long.valueOf(zipFileAttributes.crc());
                }
                return null;
            case 12:
                if (this.isZipView) {
                    return Integer.valueOf(zipFileAttributes.method());
                }
                return null;
            default:
                return null;
        }
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView
    public String name() {
        return this.isZipView ? "zip" : "basic";
    }

    public Map<String, Object> readAttributes(String str) throws IOException {
        ZipFileAttributes attributes = readAttributes();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        if ("*".equals(str)) {
            AttrID[] attrIDArrValues = AttrID.values();
            int length = attrIDArrValues.length;
            while (i < length) {
                AttrID attrID = attrIDArrValues[i];
                try {
                    linkedHashMap.put(attrID.name(), attribute(attrID, attributes));
                } catch (IllegalArgumentException unused) {
                }
                i++;
            }
        } else {
            String[] strArrSplit = str.split(",");
            int length2 = strArrSplit.length;
            while (i < length2) {
                String str2 = strArrSplit[i];
                try {
                    linkedHashMap.put(str2, attribute(AttrID.valueOf(str2), attributes));
                } catch (IllegalArgumentException unused2) {
                }
                i++;
            }
        }
        return linkedHashMap;
    }

    public void setAttribute(String str, Object obj) throws IOException {
        try {
            if (AttrID.valueOf(str) == AttrID.lastModifiedTime) {
                setTimes((FileTime) obj, null, null);
            }
            if (AttrID.valueOf(str) == AttrID.lastAccessTime) {
                setTimes(null, (FileTime) obj, null);
            }
            if (AttrID.valueOf(str) == AttrID.creationTime) {
                setTimes(null, null, (FileTime) obj);
            }
        } catch (IllegalArgumentException unused) {
            b9g.a("'", str, "' is unknown or read-only attribute");
        }
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView
    public void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
        this.path.setTimes(fileTime, fileTime2, fileTime3);
    }

    public static <V extends FileAttributeView> V get(ZipPath zipPath, Class<V> cls) {
        cls.getClass();
        if (cls == BasicFileAttributeView.class) {
            return new ZipFileAttributeView(zipPath, false);
        }
        if (cls == ZipFileAttributeView.class) {
            return new ZipFileAttributeView(zipPath, true);
        }
        return null;
    }

    @Override // java.nio.file.attribute.BasicFileAttributeView
    public ZipFileAttributes readAttributes() throws IOException {
        return this.path.getAttributes();
    }
}
