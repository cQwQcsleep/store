package jdk.internal.jrtfs;

import defpackage.eq7;
import defpackage.fq7;
import defpackage.z0e;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
final class JrtPath implements Path {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long H_ALPHA;
    private static final long H_ALPHANUM;
    private static final long H_DIGIT = 0;
    private static final long H_LOWALPHA;
    private static final long H_MARK;
    private static final long H_PATH;
    private static final long H_PCHAR;
    private static final long H_UNRESERVED;
    private static final long H_UPALPHA;
    private static final long L_ALPHA = 0;
    private static final long L_ALPHANUM;
    private static final long L_DIGIT;
    private static final long L_LOWALPHA = 0;
    private static final long L_MARK;
    private static final long L_PATH;
    private static final long L_PCHAR;
    private static final long L_UNRESERVED;
    private static final long L_UPALPHA = 0;
    private static final char[] hexDigits;
    final JrtFileSystem jrtfs;
    private volatile int[] offsets;
    private final String path;
    private volatile String resolved;

    static {
        long jLowMask = lowMask('0', '9');
        L_DIGIT = jLowMask;
        long jHighMask = highMask('A', Matrix.MATRIX_TYPE_ZERO);
        H_UPALPHA = jHighMask;
        long jHighMask2 = highMask('a', 'z');
        H_LOWALPHA = jHighMask2;
        long j = jHighMask | jHighMask2;
        H_ALPHA = j;
        L_ALPHANUM = jLowMask;
        H_ALPHANUM = j;
        long jLowMask2 = lowMask("-_.!~*'()");
        L_MARK = jLowMask2;
        long jHighMask3 = highMask("-_.!~*'()");
        H_MARK = jHighMask3;
        long j2 = jLowMask | jLowMask2;
        L_UNRESERVED = j2;
        long j3 = j | jHighMask3;
        H_UNRESERVED = j3;
        long jLowMask3 = j2 | lowMask(":@&=+$,");
        L_PCHAR = jLowMask3;
        long jHighMask4 = j3 | highMask(":@&=+$,");
        H_PCHAR = jHighMask4;
        L_PATH = jLowMask3 | lowMask(";/");
        H_PATH = highMask(";/") | jHighMask4;
        hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public JrtPath(JrtFileSystem jrtFileSystem, String str, boolean z) {
        this.jrtfs = jrtFileSystem;
        this.path = z ? str : normalize(str);
        this.resolved = null;
    }

    private JrtPath checkPath(Path path) {
        Objects.requireNonNull(path);
        if (path instanceof JrtPath) {
            return (JrtPath) path;
        }
        throw new ProviderMismatchException("path class: " + path.getClass());
    }

    /* JADX WARN: Code duplicated, block: B:63:0x008e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void copyToTarget(JrtPath jrtPath, CopyOption... copyOptionArr) throws IOException {
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
        JrtFileAttributes attributes = getAttributes(new LinkOption[0]);
        if (z) {
            try {
                jrtPath.deleteIfExists();
                zExists = false;
            } catch (DirectoryNotEmptyException unused) {
            }
        } else {
            zExists = jrtPath.exists();
        }
        if (zExists) {
            throw new FileAlreadyExistsException(jrtPath.toString());
        }
        if (attributes.isDirectory()) {
            jrtPath.createDirectory(new FileAttribute[0]);
        } else {
            InputStream inputStreamNewInputStream = this.jrtfs.newInputStream(this);
            try {
                OutputStream outputStreamNewOutputStream = jrtPath.newOutputStream(new OpenOption[0]);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i2 = inputStreamNewInputStream.read(bArr);
                        if (i2 == -1) {
                            break;
                        } else {
                            outputStreamNewOutputStream.write(bArr, 0, i2);
                        }
                        if (inputStreamNewInputStream != null) {
                            try {
                                inputStreamNewInputStream.close();
                            } catch (Throwable th) {
                                th.addSuppressed(th);
                            }
                        }
                        throw th;
                    }
                    if (outputStreamNewOutputStream != null) {
                        outputStreamNewOutputStream.close();
                    }
                    inputStreamNewInputStream.close();
                } catch (Throwable th2) {
                    if (outputStreamNewOutputStream != null) {
                        try {
                            outputStreamNewOutputStream.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                if (inputStreamNewInputStream != null) {
                    inputStreamNewInputStream.close();
                }
                throw th4;
            }
        }
        if (z2) {
            try {
                ((BasicFileAttributeView) Files.getFileAttributeView(jrtPath, BasicFileAttributeView.class, new LinkOption[0])).setTimes(attributes.lastModifiedTime(), attributes.lastAccessTime(), attributes.creationTime());
            } catch (IOException e) {
                try {
                    jrtPath.delete();
                } catch (IOException unused2) {
                }
                throw e;
            }
        }
    }

    private boolean equalsNameAt(JrtPath jrtPath, int i) {
        int i2 = this.offsets[i];
        int length = i == this.offsets.length - 1 ? this.path.length() - i2 : (this.offsets[i + 1] - i2) - 1;
        int i3 = jrtPath.offsets[i];
        if (length != (i == jrtPath.offsets.length - 1 ? jrtPath.path.length() - i3 : (jrtPath.offsets[i + 1] - i3) - 1)) {
            return false;
        }
        for (int i4 = 0; i4 < length; i4++) {
            if (this.path.charAt(i2 + i4) != jrtPath.path.charAt(i3 + i4)) {
                return false;
            }
        }
        return true;
    }

    private String getResolved() {
        int length = this.path.length();
        return (length == 0 || !(this.path.contains("./") || this.path.charAt(length + (-1)) == '.')) ? this.path : resolvePath();
    }

    private static long highMask(char c, char c2) {
        long j = 0;
        for (int iMax = Math.max(Math.min((int) c, 127), 64) - 64; iMax <= Math.max(Math.min((int) c2, 127), 64) - 64; iMax++) {
            j |= 1 << iMax;
        }
        return j;
    }

    private void initOffsets() {
        if (this.offsets == null) {
            int length = this.path.length();
            int iIndexOf = 0;
            int iIndexOf2 = 0;
            int i = 0;
            while (iIndexOf2 < length) {
                int i2 = iIndexOf2 + 1;
                if (this.path.charAt(iIndexOf2) != '/') {
                    i++;
                    iIndexOf2 = this.path.indexOf(47, i2);
                    if (iIndexOf2 == -1) {
                        break;
                    }
                } else {
                    iIndexOf2 = i2;
                }
            }
            int[] iArr = new int[i];
            int i3 = 0;
            while (iIndexOf < length) {
                if (this.path.charAt(iIndexOf) == '/') {
                    iIndexOf++;
                } else {
                    int i4 = i3 + 1;
                    iArr[i3] = iIndexOf;
                    iIndexOf = this.path.indexOf(47, iIndexOf + 1);
                    if (iIndexOf == -1) {
                        break;
                    } else {
                        i3 = i4;
                    }
                }
            }
            this.offsets = iArr;
        }
    }

    private static long lowMask(char c, char c2) {
        long j = 0;
        for (int iMax = Math.max(Math.min((int) c, 63), 0); iMax <= Math.max(Math.min((int) c2, 63), 0); iMax++) {
            j |= 1 << iMax;
        }
        return j;
    }

    private static boolean match(char c, long j, long j2) {
        if (c < '@') {
            return ((1 << c) & j) != 0;
        }
        return c < 128 && ((1 << (c - 64)) & j2) != 0;
    }

    private static String normalize(String str, int i) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        char c = 0;
        sb.append((CharSequence) str, 0, i);
        while (i < length) {
            int i2 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                cCharAt = '/';
            }
            if (cCharAt != '/' || c != '/') {
                if (cCharAt == 0) {
                    throw new InvalidPathException(str, "Path: NUL character not allowed");
                }
                sb.append(cCharAt);
                c = cCharAt;
            }
            i = i2;
        }
        int length2 = sb.length();
        if (length2 > 1) {
            int i3 = length2 - 1;
            if (sb.charAt(i3) == '/') {
                sb.deleteCharAt(i3);
            }
        }
        return sb.toString();
    }

    private String resolvePath() {
        int i;
        int length = this.path.length();
        char[] cArr = new char[length];
        int nameCount = getNameCount();
        int[] iArr = new int[nameCount];
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < nameCount) {
            int i5 = this.offsets[i3];
            int i6 = i3 == this.offsets.length - 1 ? length - i5 : (this.offsets[i3 + 1] - i5) - 1;
            if (i6 == 1 && this.path.charAt(i5) == '.') {
                if (i4 == 0 && this.path.charAt(0) == '/') {
                    i = i4 + 1;
                    cArr[i4] = '/';
                    i4 = i;
                }
            } else if (i6 != 2 || this.path.charAt(i5) != '.' || this.path.charAt(i5 + 1) != '.') {
                if ((i4 == 0 && this.path.charAt(0) == '/') || (i4 != 0 && cArr[i4 - 1] != '/')) {
                    cArr[i4] = '/';
                    i4++;
                }
                i2++;
                iArr[i2] = i4;
                while (true) {
                    int i7 = i6 - 1;
                    if (i6 > 0) {
                        cArr[i4] = this.path.charAt(i5);
                        i6 = i7;
                        i4++;
                        i5++;
                    }
                }
            } else if (i2 >= 0) {
                i4 = iArr[i2];
                i2--;
            } else if (this.path.charAt(0) != '/') {
                if (i4 != 0 && cArr[i4 - 1] != '/') {
                    cArr[i4] = '/';
                    i4++;
                }
                while (true) {
                    int i8 = i6 - 1;
                    if (i6 > 0) {
                        cArr[i4] = this.path.charAt(i5);
                        i6 = i8;
                        i4++;
                        i5++;
                    }
                }
            } else if (i4 == 0) {
                i = i4 + 1;
                cArr[i4] = '/';
                i4 = i;
            }
            i3++;
        }
        if (i4 > 1 && cArr[i4 - 1] == '/') {
            i4--;
        }
        return i4 == length ? new String(cArr) : new String(cArr, 0, i4);
    }

    private static URI toUri(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(charArray[0]);
        for (int i = 1; i < charArray.length; i++) {
            char c = (char) (charArray[i] & 255);
            if (match(c, L_PATH, H_PATH)) {
                sb.append(c);
            } else {
                sb.append('%');
                char[] cArr = hexDigits;
                sb.append(cArr[(c >> 4) & 15]);
                sb.append(cArr[c & 15]);
            }
        }
        try {
            return new URI("jrt:" + sb.toString());
        } catch (URISyntaxException e) {
            x01.a(e);
            return null;
        }
    }

    public final void checkAccess(AccessMode... accessModeArr) throws IOException {
        if (accessModeArr.length == 0) {
            this.jrtfs.checkNode(this);
            return;
        }
        boolean z = false;
        for (AccessMode accessMode : accessModeArr) {
            int i = 2.$SwitchMap$java$nio$file$AccessMode[accessMode.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        throw new AccessDeniedException(toString());
                    }
                    a9g.a();
                    return;
                }
                z = true;
            }
        }
        this.jrtfs.checkNode(this);
        if (z && this.jrtfs.isReadOnly()) {
            throw new AccessDeniedException(toString());
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Path path) {
        return this.path.compareTo(checkPath(path).path);
    }

    public final void copy(JrtPath jrtPath, CopyOption... copyOptionArr) throws IOException {
        JrtFileSystem jrtFileSystem = this.jrtfs;
        if (jrtFileSystem == jrtPath.jrtfs) {
            jrtFileSystem.copyFile(false, this, jrtPath, copyOptionArr);
        } else {
            copyToTarget(jrtPath, copyOptionArr);
        }
    }

    public final void createDirectory(FileAttribute<?>... fileAttributeArr) throws IOException {
        this.jrtfs.createDirectory(this, fileAttributeArr);
    }

    public final void delete() throws IOException {
        this.jrtfs.deleteFile(this, true);
    }

    public final void deleteIfExists() throws IOException {
        this.jrtfs.deleteFile(this, false);
    }

    @Override // java.nio.file.Path
    public final boolean endsWith(Path path) {
        Objects.requireNonNull(path);
        if (!(path instanceof JrtPath)) {
            return false;
        }
        JrtPath jrtPath = (JrtPath) path;
        int length = jrtPath.path.length();
        int i = length - 1;
        if (i > 0 && jrtPath.path.charAt(i) == '/') {
            i = length - 2;
        }
        int length2 = this.path.length();
        int i2 = length2 - 1;
        if (i2 > 0 && this.path.charAt(i2) == '/') {
            i2 = length2 - 2;
        }
        if (i == -1) {
            return i2 == -1;
        }
        if ((jrtPath.isAbsolute() && (!isAbsolute() || i != i2)) || i2 < i) {
            return false;
        }
        while (true) {
            String str = jrtPath.path;
            if (i < 0) {
                return str.charAt(i + 1) == '/' || i2 == -1 || this.path.charAt(i2) == '/';
            }
            if (str.charAt(i) != this.path.charAt(i2)) {
                return false;
            }
            i--;
            i2--;
        }
    }

    @Override // java.nio.file.Path
    public final boolean equals(Object obj) {
        return (obj instanceof JrtPath) && this.path.equals(((JrtPath) obj).path);
    }

    public final boolean exists() {
        try {
            return this.jrtfs.exists(this);
        } catch (IOException unused) {
            return false;
        }
    }

    public final JrtFileAttributes getAttributes(LinkOption... linkOptionArr) throws IOException {
        return this.jrtfs.getFileAttributes(this, linkOptionArr);
    }

    @Override // java.nio.file.Path
    public final JrtPath getFileName() {
        if (!this.path.isEmpty()) {
            if (this.path.length() == 1 && this.path.charAt(0) == '/') {
                return null;
            }
            int iLastIndexOf = this.path.lastIndexOf(47);
            if (iLastIndexOf != -1) {
                return new JrtPath(this.jrtfs, this.path.substring(iLastIndexOf + 1), true);
            }
        }
        return this;
    }

    public final FileStore getFileStore() throws IOException {
        if (exists()) {
            return this.jrtfs.getFileStore(this);
        }
        throw new NoSuchFileException(this.path);
    }

    @Override // java.nio.file.Path
    public final JrtPath getName(int i) {
        initOffsets();
        if (i >= 0 && i < this.offsets.length) {
            return new JrtPath(this.jrtfs, this.path.substring(this.offsets[i], i == this.offsets.length + (-1) ? this.path.length() : this.offsets[i + 1]));
        }
        eq7.a("index: ", i, ", offsets length: ", this.offsets.length);
        return null;
    }

    @Override // java.nio.file.Path
    public final int getNameCount() {
        initOffsets();
        return this.offsets.length;
    }

    @Override // java.nio.file.Path
    public final JrtPath getParent() {
        initOffsets();
        int length = this.offsets.length;
        if (length == 0) {
            return null;
        }
        int i = this.offsets[length - 1] - 1;
        return i <= 0 ? getRoot() : new JrtPath(this.jrtfs, this.path.substring(0, i));
    }

    public final String getResolvedPath() {
        String resolved = this.resolved;
        if (resolved == null) {
            resolved = isAbsolute() ? getResolved() : toAbsolutePath().getResolvedPath();
            this.resolved = resolved;
        }
        return resolved;
    }

    @Override // java.nio.file.Path
    public final JrtPath getRoot() {
        if (isAbsolute()) {
            return this.jrtfs.getRootPath();
        }
        return null;
    }

    @Override // java.nio.file.Path
    public final int hashCode() {
        return this.path.hashCode();
    }

    @Override // java.nio.file.Path
    public final boolean isAbsolute() {
        return !this.path.isEmpty() && this.path.charAt(0) == '/';
    }

    public final boolean isHidden() {
        return false;
    }

    public final boolean isSameFile(Path path) throws IOException {
        if (this == path || equals(path)) {
            return true;
        }
        if (path == null || getFileSystem() != path.getFileSystem()) {
            return false;
        }
        checkAccess(new AccessMode[0]);
        JrtPath jrtPath = (JrtPath) path;
        jrtPath.checkAccess(new AccessMode[0]);
        return getResolvedPath().equals(jrtPath.getResolvedPath()) || this.jrtfs.isSameFile(this, jrtPath);
    }

    @Override // java.nio.file.Path, java.lang.Iterable
    public final Iterator<Path> iterator() {
        return new Iterator<Path>() { // from class: jdk.internal.jrtfs.JrtPath.1
            private int i = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.i < JrtPath.this.getNameCount();
            }

            @Override // java.util.Iterator
            public Path next() {
                if (this.i >= JrtPath.this.getNameCount()) {
                    z0e.a();
                    return null;
                }
                JrtPath name = JrtPath.this.getName(this.i);
                this.i++;
                return name;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new ReadOnlyFileSystemException();
            }
        };
    }

    public final void move(JrtPath jrtPath, CopyOption... copyOptionArr) throws IOException {
        JrtFileSystem jrtFileSystem = this.jrtfs;
        if (jrtFileSystem == jrtPath.jrtfs) {
            jrtFileSystem.copyFile(true, this, jrtPath, copyOptionArr);
        } else {
            copyToTarget(jrtPath, copyOptionArr);
            delete();
        }
    }

    public final SeekableByteChannel newByteChannel(Set<? extends OpenOption> set, FileAttribute<?>... fileAttributeArr) throws IOException {
        return this.jrtfs.newByteChannel(this, set, fileAttributeArr);
    }

    public final DirectoryStream<Path> newDirectoryStream(DirectoryStream.Filter<? super Path> filter) throws IOException {
        return new JrtDirectoryStream(this, filter);
    }

    public final FileChannel newFileChannel(Set<? extends OpenOption> set, FileAttribute<?>... fileAttributeArr) throws IOException {
        return this.jrtfs.newFileChannel(this, set, fileAttributeArr);
    }

    public final InputStream newInputStream(OpenOption... openOptionArr) throws IOException {
        for (OpenOption openOption : openOptionArr) {
            if (openOption != StandardOpenOption.READ) {
                b9g.a("'", openOption, "' not allowed");
                return null;
            }
        }
        return this.jrtfs.newInputStream(this);
    }

    public final OutputStream newOutputStream(OpenOption... openOptionArr) throws IOException {
        int length = openOptionArr.length;
        JrtFileSystem jrtFileSystem = this.jrtfs;
        return length == 0 ? jrtFileSystem.newOutputStream(this, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE) : jrtFileSystem.newOutputStream(this, openOptionArr);
    }

    public final Map<String, Object> readAttributes(String str, LinkOption... linkOptionArr) throws IOException {
        return JrtFileAttributeView.readAttributes(this, str, linkOptionArr);
    }

    public final JrtPath readSymbolicLink() throws IOException {
        if (this.jrtfs.isLink(this)) {
            return this.jrtfs.resolveLink(this);
        }
        a16.a("not a symbolic link");
        return null;
    }

    @Override // java.nio.file.Path, java.nio.file.Watchable
    public final WatchKey register(WatchService watchService, WatchEvent.Kind<?>[] kindArr, WatchEvent.Modifier... modifierArr) {
        Objects.requireNonNull(watchService, "watcher");
        Objects.requireNonNull(kindArr, "events");
        Objects.requireNonNull(modifierArr, "modifiers");
        throw new UnsupportedOperationException();
    }

    @Override // java.nio.file.Path
    public final JrtPath relativize(Path path) {
        JrtPath jrtPathCheckPath = checkPath(path);
        if (jrtPathCheckPath.equals(this)) {
            return new JrtPath(this.jrtfs, "", true);
        }
        if (this.path.isEmpty()) {
            return jrtPathCheckPath;
        }
        if (this.jrtfs != jrtPathCheckPath.jrtfs || isAbsolute() != jrtPathCheckPath.isAbsolute()) {
            aca.a("Incorrect filesystem or path: ", path);
            return null;
        }
        String str = this.path;
        String str2 = jrtPathCheckPath.path;
        if (str2.startsWith(str)) {
            int length = str.length();
            if (str2.charAt(length - 1) == '/') {
                return new JrtPath(this.jrtfs, str2.substring(length), true);
            }
            if (str2.charAt(length) == '/') {
                return new JrtPath(this.jrtfs, str2.substring(length + 1), true);
            }
        }
        int nameCount = getNameCount();
        int nameCount2 = jrtPathCheckPath.getNameCount();
        int iMin = Math.min(nameCount, nameCount2);
        int i = 0;
        while (i < iMin && equalsNameAt(jrtPathCheckPath, i)) {
            i++;
        }
        int i2 = nameCount - i;
        int length2 = (i2 * 3) - 1;
        if (i < nameCount2) {
            length2 += (jrtPathCheckPath.path.length() - jrtPathCheckPath.offsets[i]) + 1;
        }
        StringBuilder sb = new StringBuilder(length2);
        while (i2 > 0) {
            sb.append("..");
            if (sb.length() < length2) {
                sb.append('/');
            }
            i2--;
        }
        if (i < nameCount2) {
            sb.append((CharSequence) jrtPathCheckPath.path, jrtPathCheckPath.offsets[i], jrtPathCheckPath.path.length());
        }
        return new JrtPath(this.jrtfs, sb.toString(), true);
    }

    @Override // java.nio.file.Path
    public final JrtPath resolve(Path path) {
        JrtPath jrtPathCheckPath = checkPath(path);
        if (this.path.isEmpty() || jrtPathCheckPath.isAbsolute()) {
            return jrtPathCheckPath;
        }
        if (jrtPathCheckPath.path.isEmpty()) {
            return this;
        }
        StringBuilder sb = new StringBuilder(this.path.length() + jrtPathCheckPath.path.length() + 1);
        sb.append(this.path);
        String str = this.path;
        if (str.charAt(str.length() - 1) != '/') {
            sb.append('/');
        }
        sb.append(jrtPathCheckPath.path);
        return new JrtPath(this.jrtfs, sb.toString(), true);
    }

    @Override // java.nio.file.Path
    public final Path resolveSibling(Path path) {
        Objects.requireNonNull(path, "other");
        JrtPath parent = getParent();
        return parent == null ? path : parent.resolve(path);
    }

    public final void setAttribute(String str, Object obj, LinkOption... linkOptionArr) throws IOException {
        JrtFileAttributeView.setAttribute(this, str, obj);
    }

    public final void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
        this.jrtfs.setTimes(this, fileTime, fileTime2, fileTime3);
    }

    @Override // java.nio.file.Path
    public final boolean startsWith(Path path) {
        Objects.requireNonNull(path);
        if (!(path instanceof JrtPath)) {
            return false;
        }
        JrtPath jrtPath = (JrtPath) path;
        String str = this.path;
        String str2 = jrtPath.path;
        if (isAbsolute() != jrtPath.isAbsolute() || !str.startsWith(str2)) {
            return false;
        }
        int length = str2.length();
        if (length == 0) {
            return str.isEmpty();
        }
        return str.length() == length || str.charAt(length) == '/' || length == 0 || str2.charAt(length - 1) == '/';
    }

    @Override // java.nio.file.Path
    public final JrtPath subpath(int i, int i2) {
        initOffsets();
        if (i >= 0 && i2 <= this.offsets.length && i < i2) {
            return new JrtPath(this.jrtfs, this.path.substring(this.offsets[i], i2 == this.offsets.length ? this.path.length() : this.offsets[i2]));
        }
        fq7.a("beginIndex: ", i, ", endIndex: ", i2, ", offsets length: ", this.offsets.length);
        return null;
    }

    @Override // java.nio.file.Path
    public final JrtPath toAbsolutePath() {
        if (isAbsolute()) {
            return this;
        }
        return new JrtPath(this.jrtfs, "/" + this.path, true);
    }

    @Override // java.nio.file.Path
    public final File toFile() {
        throw new UnsupportedOperationException();
    }

    @Override // java.nio.file.Path
    public final JrtPath toRealPath(LinkOption... linkOptionArr) throws IOException {
        return this.jrtfs.toRealPath(this, linkOptionArr);
    }

    @Override // java.nio.file.Path
    public final String toString() {
        return this.path;
    }

    @Override // java.nio.file.Path
    public JrtFileSystem getFileSystem() {
        return this.jrtfs;
    }

    @Override // java.nio.file.Path
    public final Path resolveSibling(String str) {
        return resolveSibling(getFileSystem().getPath(str, new String[0]));
    }

    public JrtPath(JrtFileSystem jrtFileSystem, String str) {
        this.jrtfs = jrtFileSystem;
        this.path = normalize(str);
        this.resolved = null;
    }

    @Override // java.nio.file.Path, java.nio.file.Watchable
    public final WatchKey register(WatchService watchService, WatchEvent.Kind<?>... kindArr) {
        return register(watchService, kindArr, new WatchEvent.Modifier[0]);
    }

    private static long lowMask(String str) {
        int length = str.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '@') {
                j |= 1 << cCharAt;
            }
        }
        return j;
    }

    private static long highMask(String str) {
        int length = str.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= '@' && cCharAt < 128) {
                j |= 1 << (cCharAt - '@');
            }
        }
        return j;
    }

    public final String getName() {
        return this.path;
    }

    @Override // java.nio.file.Path
    public final JrtPath normalize() {
        String resolved = getResolved();
        return resolved == this.path ? this : new JrtPath(this.jrtfs, resolved, true);
    }

    @Override // java.nio.file.Path
    public final boolean startsWith(String str) {
        return startsWith(getFileSystem().getPath(str, new String[0]));
    }

    private static String normalize(String str) {
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int i = 0;
        char c = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\' || cCharAt == 0) {
                return normalize(str, i);
            }
            if (cCharAt == '/' && c == '/') {
                return normalize(str, i - 1);
            }
            i++;
            c = cCharAt;
        }
        return (c != '/' || length <= 1) ? str : str.substring(0, length - 1);
    }

    @Override // java.nio.file.Path
    public final JrtPath resolve(String str) {
        return resolve((Path) getFileSystem().getPath(str, new String[0]));
    }

    @Override // java.nio.file.Path
    public final URI toUri() {
        String str = toAbsolutePath().path;
        if (str.startsWith("/modules") && !str.contains("..")) {
            String strSubstring = str.substring(8);
            if (strSubstring.isEmpty()) {
                strSubstring = "/";
            }
            return toUri(strSubstring);
        }
        throw new IOError(new RuntimeException(str.concat(" cannot be represented as URI")));
    }

    @Override // java.nio.file.Path
    public final boolean endsWith(String str) {
        return endsWith(getFileSystem().getPath(str, new String[0]));
    }
}
