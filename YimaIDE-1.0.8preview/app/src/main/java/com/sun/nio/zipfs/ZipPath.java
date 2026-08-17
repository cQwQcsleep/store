package com.sun.nio.zipfs;

import defpackage.a9g;
import defpackage.b9g;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.AccessMode;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.ProviderMismatchException;
import java.nio.file.ReadOnlyFileSystemException;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ZipPath implements Path {
    private int hashcode;
    private volatile int[] offsets;
    private final byte[] path;
    private volatile byte[] resolved;
    private final ZipFileSystem zfs;

    /* JADX INFO: renamed from: com.sun.nio.zipfs.ZipPath$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$java$nio$file$AccessMode;

        static {
            int[] iArr = new int[AccessMode.values().length];
            $SwitchMap$java$nio$file$AccessMode = iArr;
            try {
                iArr[AccessMode.READ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$nio$file$AccessMode[AccessMode.WRITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$nio$file$AccessMode[AccessMode.EXECUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ZipPath(ZipFileSystem zipFileSystem, byte[] bArr, boolean z) {
        this.hashcode = 0;
        this.resolved = null;
        this.zfs = zipFileSystem;
        if (z) {
            this.path = bArr;
        } else if (zipFileSystem.zc.isUTF8()) {
            this.path = normalize(bArr);
        } else {
            this.path = normalize(zipFileSystem.getString(bArr));
        }
    }

    private ZipPath checkPath(Path path) {
        path.getClass();
        if (path instanceof ZipPath) {
            return (ZipPath) path;
        }
        throw new ProviderMismatchException();
    }

    private void copyToTarget(ZipPath zipPath, CopyOption... copyOptionArr) throws IOException {
        boolean zExists;
        int length = copyOptionArr.length;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            zExists = true;
            if (i >= length) {
                break;
            }
            CopyOption copyOption = copyOptionArr[i];
            if (copyOption == StandardCopyOption.REPLACE_EXISTING) {
                z = true;
            } else if (copyOption == StandardCopyOption.COPY_ATTRIBUTES) {
                z2 = true;
            }
            i++;
        }
        ZipFileAttributes attributes = getAttributes();
        if (z) {
            try {
                zipPath.deleteIfExists();
                zExists = false;
            } catch (DirectoryNotEmptyException unused) {
            }
        } else {
            zExists = zipPath.exists();
        }
        if (zExists) {
            throw new FileAlreadyExistsException(zipPath.toString());
        }
        if (attributes.isDirectory()) {
            zipPath.createDirectory(new FileAttribute[0]);
        } else {
            InputStream inputStreamNewInputStream = this.zfs.newInputStream(getResolvedPath());
            try {
                OutputStream outputStreamNewOutputStream = zipPath.newOutputStream(new OpenOption[0]);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i2 = inputStreamNewInputStream.read(bArr);
                        if (i2 == -1) {
                            break;
                        } else {
                            outputStreamNewOutputStream.write(bArr, 0, i2);
                        }
                    }
                    outputStreamNewOutputStream.close();
                    inputStreamNewInputStream.close();
                } catch (Throwable th) {
                    outputStreamNewOutputStream.close();
                    throw th;
                }
            } catch (Throwable th2) {
                inputStreamNewInputStream.close();
                throw th2;
            }
        }
        if (z2) {
            try {
                ((BasicFileAttributeView) ZipFileAttributeView.get(zipPath, BasicFileAttributeView.class)).setTimes(attributes.lastModifiedTime(), attributes.lastAccessTime(), attributes.creationTime());
            } catch (IOException e) {
                try {
                    zipPath.delete();
                } catch (IOException unused2) {
                }
                throw e;
            }
        }
    }

    private boolean equalsNameAt(ZipPath zipPath, int i) {
        int i2 = this.offsets[i];
        int length = i == this.offsets.length - 1 ? this.path.length - i2 : (this.offsets[i + 1] - i2) - 1;
        int i3 = zipPath.offsets[i];
        if (length != (i == zipPath.offsets.length - 1 ? zipPath.path.length - i3 : (zipPath.offsets[i + 1] - i3) - 1)) {
            return false;
        }
        for (int i4 = 0; i4 < length; i4++) {
            if (this.path[i2 + i4] != zipPath.path[i3 + i4]) {
                return false;
            }
        }
        return true;
    }

    private byte[] getResolved() {
        byte[] bArr = this.path;
        if (bArr.length == 0) {
            return bArr;
        }
        int i = 0;
        while (true) {
            byte[] bArr2 = this.path;
            if (i >= bArr2.length) {
                return bArr2;
            }
            if (bArr2[i] == 46) {
                return resolve0();
            }
            i++;
        }
    }

    private void initOffsets() {
        if (this.offsets == null) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[] bArr = this.path;
                if (i2 >= bArr.length) {
                    break;
                }
                int i4 = i2 + 1;
                if (bArr[i2] != 47) {
                    i3++;
                    while (true) {
                        byte[] bArr2 = this.path;
                        if (i4 >= bArr2.length || bArr2[i4] == 47) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                }
                i2 = i4;
            }
            int[] iArr = new int[i3];
            int i5 = 0;
            while (true) {
                byte[] bArr3 = this.path;
                if (i >= bArr3.length) {
                    break;
                }
                if (bArr3[i] == 47) {
                    i++;
                } else {
                    int i6 = i5 + 1;
                    int i7 = i + 1;
                    iArr[i5] = i;
                    while (true) {
                        byte[] bArr4 = this.path;
                        if (i7 >= bArr4.length || bArr4[i7] == 47) {
                            break;
                        } else {
                            i7++;
                        }
                    }
                    i5 = i6;
                    i = i7;
                }
            }
            synchronized (this) {
                try {
                    if (this.offsets == null) {
                        this.offsets = iArr;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private byte[] normalize(byte[] bArr, int i) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        byte b = 0;
        int i2 = 0;
        while (i2 < i) {
            bArr2[i2] = bArr[i2];
            i2++;
        }
        int i3 = i2;
        while (i2 < bArr.length) {
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 == 92) {
                b2 = 47;
            }
            if (b2 != 47 || b != 47) {
                if (b2 == 0) {
                    throw new InvalidPathException(this.zfs.getString(bArr), "Path: nul character not allowed");
                }
                bArr2[i3] = b2;
                i3++;
                b = b2;
            }
            i2 = i4;
        }
        if (i3 > 1 && bArr2[i3 - 1] == 47) {
            i3--;
        }
        return i3 == length ? bArr2 : Arrays.copyOf(bArr2, i3);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0042  */
    /* JADX WARN: Code duplicated, block: B:20:0x0045  */
    /* JADX WARN: Code duplicated, block: B:37:0x0085  */
    /* JADX WARN: Code duplicated, block: B:38:0x0087  */
    /* JADX WARN: Code duplicated, block: B:40:0x008d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:47:0x00a2 A[LOOP:2: B:45:0x009e->B:47:0x00a2, LOOP_END] */
    private byte[] resolve0() {
        int i;
        byte[] bArr;
        int i2;
        int length = this.path.length;
        byte[] bArr2 = new byte[length];
        int nameCount = getNameCount();
        int[] iArr = new int[nameCount];
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < nameCount) {
            int i6 = this.offsets[i4];
            int length2 = i4 == this.offsets.length - 1 ? this.path.length - i6 : (this.offsets[i4 + 1] - i6) - 1;
            if (length2 == 1) {
                byte[] bArr3 = this.path;
                if (bArr3[i6] == 46) {
                    if (i5 == 0 && bArr3[0] == 47) {
                        i2 = i5 + 1;
                        bArr2[i5] = 47;
                        i5 = i2;
                    }
                } else if (length2 == 2) {
                    bArr = this.path;
                    if (bArr[i6] == 46 || bArr[i6 + 1] != 46) {
                        if ((i5 != 0 && this.path[0] == 47) || (i5 != 0 && bArr2[i5 - 1] != 47)) {
                            bArr2[i5] = 47;
                            i5++;
                        }
                        i3++;
                        iArr[i3] = i5;
                        while (true) {
                            i = length2 - 1;
                            if (length2 > 0) {
                                bArr2[i5] = this.path[i6];
                                length2 = i;
                                i5++;
                                i6++;
                            }
                        }
                    } else if (i3 >= 0) {
                        i5 = iArr[i3];
                        i3--;
                    } else if (bArr[0] != 47) {
                        if (i5 != 0 && bArr2[i5 - 1] != 47) {
                            bArr2[i5] = 47;
                            i5++;
                        }
                        while (true) {
                            int i7 = length2 - 1;
                            if (length2 > 0) {
                                bArr2[i5] = this.path[i6];
                                length2 = i7;
                                i5++;
                                i6++;
                            }
                        }
                    } else if (i5 == 0) {
                        i2 = i5 + 1;
                        bArr2[i5] = 47;
                        i5 = i2;
                    }
                } else {
                    if (i5 != 0) {
                        bArr2[i5] = 47;
                        i5++;
                    } else {
                        bArr2[i5] = 47;
                        i5++;
                    }
                    i3++;
                    iArr[i3] = i5;
                    while (true) {
                        i = length2 - 1;
                        if (length2 > 0) {
                            bArr2[i5] = this.path[i6];
                            length2 = i;
                            i5++;
                            i6++;
                        }
                    }
                }
            } else if (length2 == 2) {
                bArr = this.path;
                if (bArr[i6] == 46) {
                    if (i5 != 0) {
                        bArr2[i5] = 47;
                        i5++;
                    } else {
                        bArr2[i5] = 47;
                        i5++;
                    }
                    i3++;
                    iArr[i3] = i5;
                    while (true) {
                        i = length2 - 1;
                        if (length2 > 0) {
                            bArr2[i5] = this.path[i6];
                            length2 = i;
                            i5++;
                            i6++;
                        }
                    }
                } else {
                    if (i5 != 0) {
                        bArr2[i5] = 47;
                        i5++;
                    } else {
                        bArr2[i5] = 47;
                        i5++;
                    }
                    i3++;
                    iArr[i3] = i5;
                    while (true) {
                        i = length2 - 1;
                        if (length2 > 0) {
                            bArr2[i5] = this.path[i6];
                            length2 = i;
                            i5++;
                            i6++;
                        }
                    }
                }
            } else {
                if (i5 != 0) {
                    bArr2[i5] = 47;
                    i5++;
                } else {
                    bArr2[i5] = 47;
                    i5++;
                }
                i3++;
                iArr[i3] = i5;
                while (true) {
                    i = length2 - 1;
                    if (length2 > 0) {
                        bArr2[i5] = this.path[i6];
                        length2 = i;
                        i5++;
                        i6++;
                    }
                }
            }
            i4++;
        }
        if (i5 > 1 && bArr2[i5 - 1] == 47) {
            i5--;
        }
        return i5 == length ? bArr2 : Arrays.copyOf(bArr2, i5);
    }

    public void checkAccess(AccessMode... accessModeArr) throws IOException {
        boolean z = false;
        boolean z2 = false;
        for (AccessMode accessMode : accessModeArr) {
            int i = AnonymousClass2.$SwitchMap$java$nio$file$AccessMode[accessMode.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    z = true;
                } else {
                    if (i != 3) {
                        a9g.a();
                        return;
                    }
                    z2 = true;
                }
            }
        }
        if (this.zfs.getFileAttributes(getResolvedPath()) == null) {
            byte[] bArr = this.path;
            if (bArr.length != 1 || bArr[0] != 47) {
                throw new NoSuchFileException(toString());
            }
        }
        if (z && this.zfs.isReadOnly()) {
            throw new AccessDeniedException(toString());
        }
        if (z2) {
            throw new AccessDeniedException(toString());
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Path path) {
        ZipPath zipPathCheckPath = checkPath(path);
        int length = this.path.length;
        int length2 = zipPathCheckPath.path.length;
        int iMin = Math.min(length, length2);
        byte[] bArr = this.path;
        byte[] bArr2 = zipPathCheckPath.path;
        for (int i = 0; i < iMin; i++) {
            int i2 = bArr[i] & 255;
            int i3 = bArr2[i] & 255;
            if (i2 != i3) {
                return i2 - i3;
            }
        }
        return length - length2;
    }

    public void copy(ZipPath zipPath, CopyOption... copyOptionArr) throws IOException {
        if (Files.isSameFile(this.zfs.getZipFile(), zipPath.zfs.getZipFile())) {
            this.zfs.copyFile(false, getResolvedPath(), zipPath.getResolvedPath(), copyOptionArr);
        } else {
            copyToTarget(zipPath, copyOptionArr);
        }
    }

    public void createDirectory(FileAttribute<?>... fileAttributeArr) throws IOException {
        this.zfs.createDirectory(getResolvedPath(), fileAttributeArr);
    }

    public void delete() throws IOException {
        this.zfs.deleteFile(getResolvedPath(), true);
    }

    public void deleteIfExists() throws IOException {
        this.zfs.deleteFile(getResolvedPath(), false);
    }

    @Override // java.nio.file.Path
    public boolean endsWith(Path path) {
        ZipPath zipPathCheckPath = checkPath(path);
        byte[] bArr = zipPathCheckPath.path;
        int length = bArr.length;
        int i = length - 1;
        if (i > 0 && bArr[i] == 47) {
            i = length - 2;
        }
        byte[] bArr2 = this.path;
        int length2 = bArr2.length;
        int i2 = length2 - 1;
        if (i2 > 0 && bArr2[i2] == 47) {
            i2 = length2 - 2;
        }
        if (i == -1) {
            return i2 == -1;
        }
        if ((zipPathCheckPath.isAbsolute() && (!isAbsolute() || i != i2)) || i2 < i) {
            return false;
        }
        while (true) {
            byte[] bArr3 = zipPathCheckPath.path;
            if (i < 0) {
                return bArr3[i + 1] == 47 || i2 == -1 || this.path[i2] == 47;
            }
            if (bArr3[i] != this.path[i2]) {
                return false;
            }
            i--;
            i2--;
        }
    }

    @Override // java.nio.file.Path
    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ZipPath) && this.zfs == ((ZipPath) obj).zfs && compareTo((Path) obj) == 0;
    }

    public boolean exists() {
        byte[] bArr = this.path;
        if (bArr.length == 1 && bArr[0] == 47) {
            return true;
        }
        try {
            return this.zfs.exists(getResolvedPath());
        } catch (IOException unused) {
            return false;
        }
    }

    public ZipFileAttributes getAttributes() throws IOException {
        ZipFileAttributes fileAttributes = this.zfs.getFileAttributes(getResolvedPath());
        if (fileAttributes != null) {
            return fileAttributes;
        }
        throw new NoSuchFileException(toString());
    }

    @Override // java.nio.file.Path
    public Path getFileName() {
        initOffsets();
        int length = this.offsets.length;
        if (length == 0) {
            return null;
        }
        if (length == 1 && this.path[0] != 47) {
            return this;
        }
        int i = this.offsets[length - 1];
        byte[] bArr = this.path;
        int length2 = bArr.length - i;
        byte[] bArr2 = new byte[length2];
        System.arraycopy(bArr, i, bArr2, 0, length2);
        return new ZipPath(this.zfs, bArr2);
    }

    public FileStore getFileStore() throws IOException {
        if (exists()) {
            return this.zfs.getFileStore(this);
        }
        throw new NoSuchFileException(this.zfs.getString(this.path));
    }

    @Override // java.nio.file.Path
    public ZipPath getName(int i) {
        initOffsets();
        if (i < 0 || i >= this.offsets.length) {
            j2d.a();
            return null;
        }
        int i2 = this.offsets[i];
        int length = i == this.offsets.length + (-1) ? this.path.length - i2 : (this.offsets[i + 1] - i2) - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(this.path, i2, bArr, 0, length);
        return new ZipPath(this.zfs, bArr);
    }

    @Override // java.nio.file.Path
    public int getNameCount() {
        initOffsets();
        return this.offsets.length;
    }

    @Override // java.nio.file.Path
    public ZipPath getParent() {
        initOffsets();
        int length = this.offsets.length;
        if (length == 0) {
            return null;
        }
        int i = this.offsets[length - 1] - 1;
        if (i <= 0) {
            return getRoot();
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.path, 0, bArr, 0, i);
        return new ZipPath(this.zfs, bArr);
    }

    public byte[] getResolvedPath() {
        if (this.resolved == null) {
            byte[] resolved = isAbsolute() ? getResolved() : toAbsolutePath().getResolvedPath();
            if (resolved[0] == 47) {
                resolved = Arrays.copyOfRange(resolved, 1, resolved.length);
            }
            this.resolved = resolved;
        }
        return this.resolved;
    }

    @Override // java.nio.file.Path
    public ZipPath getRoot() {
        if (isAbsolute()) {
            return new ZipPath(this.zfs, new byte[]{this.path[0]});
        }
        return null;
    }

    @Override // java.nio.file.Path
    public int hashCode() {
        int i = this.hashcode;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.path);
        this.hashcode = iHashCode;
        return iHashCode;
    }

    @Override // java.nio.file.Path
    public boolean isAbsolute() {
        byte[] bArr = this.path;
        return bArr.length > 0 && bArr[0] == 47;
    }

    public boolean isHidden() {
        return false;
    }

    public boolean isSameFile(Path path) throws IOException {
        if (equals(path)) {
            return true;
        }
        if (path == null || getFileSystem() != path.getFileSystem()) {
            return false;
        }
        checkAccess(new AccessMode[0]);
        ZipPath zipPath = (ZipPath) path;
        zipPath.checkAccess(new AccessMode[0]);
        return Arrays.equals(getResolvedPath(), zipPath.getResolvedPath());
    }

    @Override // java.nio.file.Path, java.lang.Iterable
    public Iterator<Path> iterator() {
        return new Iterator<Path>() { // from class: com.sun.nio.zipfs.ZipPath.1
            private int i = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < ZipPath.this.getNameCount();
            }

            @Override // java.util.Iterator
            public Path next() {
                if (this.i >= ZipPath.this.getNameCount()) {
                    z0e.a();
                    return null;
                }
                ZipPath name = ZipPath.this.getName(this.i);
                this.i++;
                return name;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new ReadOnlyFileSystemException();
            }
        };
    }

    public void move(ZipPath zipPath, CopyOption... copyOptionArr) throws IOException {
        if (Files.isSameFile(this.zfs.getZipFile(), zipPath.zfs.getZipFile())) {
            this.zfs.copyFile(true, getResolvedPath(), zipPath.getResolvedPath(), copyOptionArr);
        } else {
            copyToTarget(zipPath, copyOptionArr);
            delete();
        }
    }

    public SeekableByteChannel newByteChannel(Set<? extends OpenOption> set, FileAttribute<?>... fileAttributeArr) throws IOException {
        return this.zfs.newByteChannel(getResolvedPath(), set, fileAttributeArr);
    }

    public DirectoryStream<Path> newDirectoryStream(DirectoryStream.Filter<? super Path> filter) throws IOException {
        return new ZipDirectoryStream(this, filter);
    }

    public FileChannel newFileChannel(Set<? extends OpenOption> set, FileAttribute<?>... fileAttributeArr) throws IOException {
        return this.zfs.newFileChannel(getResolvedPath(), set, fileAttributeArr);
    }

    public InputStream newInputStream(OpenOption... openOptionArr) throws IOException {
        if (openOptionArr.length > 0) {
            for (OpenOption openOption : openOptionArr) {
                if (openOption != StandardOpenOption.READ) {
                    b9g.a("'", openOption, "' not allowed");
                    return null;
                }
            }
        }
        return this.zfs.newInputStream(getResolvedPath());
    }

    public OutputStream newOutputStream(OpenOption... openOptionArr) throws IOException {
        int length = openOptionArr.length;
        ZipFileSystem zipFileSystem = this.zfs;
        return length == 0 ? zipFileSystem.newOutputStream(getResolvedPath(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE) : zipFileSystem.newOutputStream(getResolvedPath(), openOptionArr);
    }

    public Map<String, Object> readAttributes(String str, LinkOption... linkOptionArr) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            strSubstring = "basic";
        } else {
            int i = iIndexOf + 1;
            strSubstring = str.substring(0, iIndexOf);
            str = str.substring(i);
        }
        ZipFileAttributeView zipFileAttributeView = ZipFileAttributeView.get(this, strSubstring);
        if (zipFileAttributeView != null) {
            return zipFileAttributeView.readAttributes(str);
        }
        c41.a("view not supported");
        return null;
    }

    @Override // java.nio.file.Path, java.nio.file.Watchable
    public WatchKey register(WatchService watchService, WatchEvent.Kind<?>[] kindArr, WatchEvent.Modifier... modifierArr) {
        if (watchService == null || kindArr == null || modifierArr == null) {
            throw null;
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.nio.file.Path
    public Path relativize(Path path) {
        ZipPath zipPathCheckPath = checkPath(path);
        int i = 0;
        if (zipPathCheckPath.equals(this)) {
            return new ZipPath(getFileSystem(), new byte[0], true);
        }
        if (isAbsolute() != zipPathCheckPath.isAbsolute()) {
            j2d.a();
            return null;
        }
        int nameCount = getNameCount();
        int nameCount2 = zipPathCheckPath.getNameCount();
        int iMin = Math.min(nameCount, nameCount2);
        int i2 = 0;
        while (i2 < iMin && equalsNameAt(zipPathCheckPath, i2)) {
            i2++;
        }
        int i3 = nameCount - i2;
        int length = (i3 * 3) - 1;
        if (i2 < nameCount2) {
            length += (zipPathCheckPath.path.length - zipPathCheckPath.offsets[i2]) + 1;
        }
        byte[] bArr = new byte[length];
        while (i3 > 0) {
            bArr[i] = 46;
            int i4 = i + 2;
            bArr[i + 1] = 46;
            if (i4 < length) {
                i += 3;
                bArr[i4] = 47;
            } else {
                i = i4;
            }
            i3--;
        }
        if (i2 < nameCount2) {
            System.arraycopy(zipPathCheckPath.path, zipPathCheckPath.offsets[i2], bArr, i, zipPathCheckPath.path.length - zipPathCheckPath.offsets[i2]);
        }
        return new ZipPath(getFileSystem(), bArr);
    }

    @Override // java.nio.file.Path
    public ZipPath resolve(Path path) {
        byte[] bArr;
        ZipPath zipPathCheckPath = checkPath(path);
        if (zipPathCheckPath.isAbsolute()) {
            return zipPathCheckPath;
        }
        byte[] bArr2 = this.path;
        if (bArr2[bArr2.length - 1] == 47) {
            bArr = new byte[bArr2.length + zipPathCheckPath.path.length];
            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
            byte[] bArr3 = zipPathCheckPath.path;
            System.arraycopy(bArr3, 0, bArr, this.path.length, bArr3.length);
        } else {
            bArr = new byte[bArr2.length + 1 + zipPathCheckPath.path.length];
            System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
            byte[] bArr4 = this.path;
            bArr[bArr4.length] = 47;
            byte[] bArr5 = zipPathCheckPath.path;
            System.arraycopy(bArr5, 0, bArr, bArr4.length + 1, bArr5.length);
        }
        return new ZipPath(this.zfs, bArr);
    }

    @Override // java.nio.file.Path
    public final Path resolveSibling(String str) {
        return resolveSibling(getFileSystem().getPath(str, new String[0]));
    }

    public void setAttribute(String str, Object obj, LinkOption... linkOptionArr) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            strSubstring = "basic";
        } else {
            int i = iIndexOf + 1;
            strSubstring = str.substring(0, iIndexOf);
            str = str.substring(i);
        }
        ZipFileAttributeView zipFileAttributeView = ZipFileAttributeView.get(this, strSubstring);
        if (zipFileAttributeView != null) {
            zipFileAttributeView.setAttribute(str, obj);
        } else {
            b9g.a("view <", zipFileAttributeView, "> is not supported");
        }
    }

    public void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
        this.zfs.setTimes(getResolvedPath(), fileTime, fileTime2, fileTime3);
    }

    @Override // java.nio.file.Path
    public boolean startsWith(Path path) {
        ZipPath zipPathCheckPath = checkPath(path);
        if (zipPathCheckPath.isAbsolute() == isAbsolute()) {
            byte[] bArr = zipPathCheckPath.path;
            if (bArr.length <= this.path.length) {
                int length = bArr.length;
                int i = 0;
                while (true) {
                    byte[] bArr2 = zipPathCheckPath.path;
                    if (i >= length) {
                        int i2 = length - 1;
                        int length2 = bArr2.length;
                        byte[] bArr3 = this.path;
                        return length2 == bArr3.length || bArr2[i2] == 47 || bArr3[length] == 47;
                    }
                    if (bArr2[i] != this.path[i]) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return false;
    }

    @Override // java.nio.file.Path
    public ZipPath subpath(int i, int i2) {
        initOffsets();
        if (i < 0 || i >= this.offsets.length || i2 > this.offsets.length || i >= i2) {
            j2d.a();
            return null;
        }
        int i3 = this.offsets[i];
        int length = i2 == this.offsets.length ? this.path.length - i3 : (this.offsets[i2] - i3) - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(this.path, i3, bArr, 0, length);
        return new ZipPath(this.zfs, bArr);
    }

    @Override // java.nio.file.Path
    public ZipPath toAbsolutePath() {
        if (isAbsolute()) {
            return this;
        }
        byte[] bArr = this.zfs.getDefaultDir().path;
        int length = bArr.length;
        boolean z = bArr[length + (-1)] == 47;
        byte[] bArr2 = this.path;
        byte[] bArr3 = z ? new byte[bArr2.length + length] : new byte[length + 1 + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        if (!z) {
            bArr3[length] = 47;
            length++;
        }
        byte[] bArr4 = this.path;
        System.arraycopy(bArr4, 0, bArr3, length, bArr4.length);
        return new ZipPath(this.zfs, bArr3, true);
    }

    @Override // java.nio.file.Path
    public final File toFile() {
        throw new UnsupportedOperationException();
    }

    @Override // java.nio.file.Path
    public ZipPath toRealPath(LinkOption... linkOptionArr) throws IOException {
        ZipPath absolutePath = new ZipPath(this.zfs, getResolvedPath()).toAbsolutePath();
        absolutePath.checkAccess(new AccessMode[0]);
        return absolutePath;
    }

    @Override // java.nio.file.Path
    public String toString() {
        return this.zfs.getString(this.path);
    }

    @Override // java.nio.file.Path
    public URI toUri() {
        try {
            return new URI("jar", this.zfs.getZipFile().toUri() + "!" + this.zfs.getString(toAbsolutePath().path), null);
        } catch (Exception e) {
            x01.a(e);
            return null;
        }
    }

    @Override // java.nio.file.Path
    public ZipFileSystem getFileSystem() {
        return this.zfs;
    }

    @Override // java.nio.file.Path, java.nio.file.Watchable
    public WatchKey register(WatchService watchService, WatchEvent.Kind<?>... kindArr) {
        return register(watchService, kindArr, new WatchEvent.Modifier[0]);
    }

    @Override // java.nio.file.Path
    public Path resolveSibling(Path path) {
        path.getClass();
        ZipPath parent = getParent();
        return parent == null ? path : parent.resolve(path);
    }

    public ZipPath(ZipFileSystem zipFileSystem, byte[] bArr) {
        this(zipFileSystem, bArr, false);
    }

    public ZipPath(ZipFileSystem zipFileSystem, String str) {
        this.hashcode = 0;
        this.resolved = null;
        this.zfs = zipFileSystem;
        if (zipFileSystem.zc.isUTF8()) {
            this.path = normalize(zipFileSystem.getBytes(str));
        } else {
            this.path = normalize(str);
        }
    }

    @Override // java.nio.file.Path
    public final boolean startsWith(String str) {
        return startsWith(getFileSystem().getPath(str, new String[0]));
    }

    private byte[] normalize(byte[] bArr) {
        if (bArr.length != 0) {
            int i = 0;
            byte b = 0;
            while (i < bArr.length) {
                byte b2 = bArr[i];
                if (b2 == 92) {
                    return normalize(bArr, i);
                }
                if (b2 == 47 && b == 47) {
                    return normalize(bArr, i - 1);
                }
                if (b2 == 0) {
                    throw new InvalidPathException(this.zfs.getString(bArr), "Path: nul character not allowed");
                }
                i++;
                b = b2;
            }
        }
        return bArr;
    }

    @Override // java.nio.file.Path
    public ZipPath resolve(String str) {
        return resolve((Path) getFileSystem().getPath(str, new String[0]));
    }

    @Override // java.nio.file.Path
    public Path normalize() {
        byte[] resolved = getResolved();
        return resolved == this.path ? this : new ZipPath(this.zfs, resolved, true);
    }

    private byte[] normalize(String str) {
        int length = str.length();
        if (length == 0) {
            return new byte[0];
        }
        int i = 0;
        char c = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\' || cCharAt == 0) {
                return normalize(str, i, length);
            }
            if (cCharAt == '/' && c == '/') {
                return normalize(str, i - 1, length);
            }
            i++;
            c = cCharAt;
        }
        if (length > 1 && c == '/') {
            str = str.substring(0, length - 1);
        }
        return this.zfs.getBytes(str);
    }

    @Override // java.nio.file.Path
    public final boolean endsWith(String str) {
        return endsWith(getFileSystem().getPath(str, new String[0]));
    }

    private byte[] normalize(String str, int i, int i2) {
        StringBuilder sb = new StringBuilder(i2);
        char c = 0;
        sb.append((CharSequence) str, 0, i);
        while (i < i2) {
            int i3 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                cCharAt = '/';
            }
            if (cCharAt != '/' || c != '/') {
                if (cCharAt != 0) {
                    sb.append(cCharAt);
                    c = cCharAt;
                } else {
                    throw new InvalidPathException(str, "Path: nul character not allowed");
                }
            }
            i = i3;
        }
        int length = sb.length();
        if (length > 1 && c == '/') {
            sb.delete(length - 1, length);
        }
        return this.zfs.getBytes(sb.toString());
    }
}
