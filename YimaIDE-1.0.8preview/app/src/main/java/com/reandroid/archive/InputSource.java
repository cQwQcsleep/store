package com.reandroid.archive;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.Crc32OutputStream;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class InputSource {
    private static final String ASSETS = "assets";
    private static final int LAST_ORDER = 10;
    private static final String LIB = "lib";
    private static final String META_INF = "meta-inf";
    private static final int ORDER_android_manifest = 0;
    private static final int ORDER_assets = 6;
    private static final int ORDER_classes = 3;
    private static final int ORDER_lib = 4;
    private static final int ORDER_meta_inf = 2;
    private static final int ORDER_res = 5;
    private static final int ORDER_resources = 1;
    private static final String RES = "res";
    private String alias;
    private long mCrc;
    private long mLength;
    private final String name;
    private String[] splitAlias;
    public static final Comparator<? super InputSource> ALIAS_COMPARATOR = new Comparator() { // from class: hq6
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return InputSource.compareSortOrAlias((InputSource) obj, (InputSource) obj2);
        }
    };
    private static final String ANDROID_MANIFEST = StringsUtil.toLowercase(AndroidManifest.FILE_NAME);
    private static final String RESOURCES = StringsUtil.toLowercase(TableBlock.FILE_NAME);
    private int method = Archive.DEFLATED;
    private int sort = -1;

    public InputSource(String str) {
        this.name = str;
        this.alias = ArchiveUtil.sanitizePath(str);
    }

    private void calculateCrc() throws IOException {
        Crc32OutputStream crc32OutputStream = new Crc32OutputStream();
        write(crc32OutputStream);
        this.mCrc = crc32OutputStream.getValue();
        this.mLength = crc32OutputStream.getLength();
    }

    private static int compareAlias(InputSource inputSource, InputSource inputSource2) {
        String[] splitAlias = inputSource.getSplitAlias();
        String[] splitAlias2 = inputSource2.getSplitAlias();
        int sortOrder = getSortOrder(splitAlias);
        int sortOrder2 = getSortOrder(splitAlias2);
        if (sortOrder != sortOrder2) {
            return CompareUtil.compare(sortOrder, sortOrder2);
        }
        return sortOrder == 3 ? compareDex(splitAlias[0], splitAlias2[0]) : StringsUtil.compare(splitAlias, splitAlias2);
    }

    public static int compareDex(String str, String str2) {
        int dexNumber = getDexNumber(str);
        int dexNumber2 = getDexNumber(str2);
        if (dexNumber == dexNumber2) {
            return 0;
        }
        if (dexNumber < 0) {
            return 1;
        }
        if (dexNumber2 < 0) {
            return -1;
        }
        return Integer.compare(dexNumber, dexNumber2);
    }

    public static int compareSortOrAlias(InputSource inputSource, InputSource inputSource2) {
        if (inputSource == inputSource2) {
            return 0;
        }
        if (inputSource == null) {
            return 1;
        }
        if (inputSource2 == null) {
            return -1;
        }
        int sort = inputSource.getSort();
        int sort2 = inputSource.getSort();
        if (sort == -1 && sort2 == -1) {
            return compareAlias(inputSource, inputSource2);
        }
        if (sort == -1) {
            return 1;
        }
        if (sort2 == -1) {
            return -1;
        }
        return Integer.compare(sort, sort2);
    }

    public static int getDexNumber(String str) {
        if (str.equals("classes.dex")) {
            return 0;
        }
        if (str.startsWith("classes") && str.endsWith(".dex")) {
            try {
                int i = Integer.parseInt(str.substring(7, str.length() - 4));
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    private static int getSortOrder(String[] strArr) {
        int length = strArr.length;
        if (length == 0) {
            return 10;
        }
        String str = strArr[0];
        if (StringsUtil.isEmpty(str)) {
            return 10;
        }
        if (length == 1) {
            if (ANDROID_MANIFEST.equals(str)) {
                return 0;
            }
            if (RESOURCES.equals(str)) {
                return 1;
            }
            return (str.startsWith("classes") && str.endsWith(".dex")) ? 3 : 10;
        }
        if (META_INF.equals(str)) {
            return 2;
        }
        if (LIB.equals(str)) {
            return 4;
        }
        if (RES.equals(str)) {
            return 5;
        }
        return ASSETS.equals(str) ? 6 : 10;
    }

    private String[] getSplitAlias() {
        if (this.splitAlias == null) {
            this.splitAlias = StringsUtil.split(StringsUtil.toLowercase(getAlias()), '/');
        }
        return this.splitAlias;
    }

    public void close(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    public void copyAttributes(InputSource inputSource) {
        if (inputSource == null || inputSource == this) {
            return;
        }
        setSort(inputSource.getSort());
        setMethod(inputSource.getMethod());
        setAlias(inputSource.getAlias());
    }

    public void disposeInputSource() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InputSource) {
            return getName().equals(((InputSource) obj).getName());
        }
        return false;
    }

    public String getAlias() {
        String str = this.alias;
        return str != null ? str : getName();
    }

    public byte[] getBytes(int i) throws IOException {
        InputStream inputStreamOpenStream = openStream();
        byte[] bArr = new byte[i];
        inputStreamOpenStream.read(bArr, 0, i);
        close(inputStreamOpenStream);
        return bArr;
    }

    public long getCrc() throws IOException {
        if (this.mCrc == 0) {
            calculateCrc();
        }
        return this.mCrc;
    }

    public String getExtension() {
        return FileUtil.getExtension(getAlias());
    }

    public long getLength() throws IOException {
        if (this.mLength == 0) {
            calculateCrc();
        }
        return this.mLength;
    }

    public int getMethod() {
        return this.method;
    }

    public String getName() {
        return this.name;
    }

    public String getParentPath() {
        return FileUtil.getParent(getAlias());
    }

    public String getSimpleName() {
        return FileUtil.getFileName(getAlias());
    }

    public String getSimpleNameWoExtension() {
        return FileUtil.getNameWoExtension(getAlias());
    }

    public int getSort() {
        return this.sort;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isUncompressed() {
        return getMethod() != Archive.DEFLATED;
    }

    public abstract InputStream openStream() throws IOException;

    public void setAlias(String str) {
        this.alias = str;
        this.splitAlias = null;
    }

    public void setMethod(int i) {
        this.method = i;
    }

    public void setSort(int i) {
        this.sort = i;
    }

    public void setUncompressed(boolean z) {
        if (z) {
            this.method = Archive.STORED;
        } else {
            this.method = Archive.DEFLATED;
        }
    }

    public File toFile(File file) {
        return new File(file, getAlias().replace('/', File.separatorChar));
    }

    public String toString() {
        return getClass().getSimpleName() + ": " + getName();
    }

    public void write(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        write(fileOutputStream);
        fileOutputStream.close();
    }

    public long write(OutputStream outputStream) throws IOException {
        return write(outputStream, openStream());
    }

    private long write(OutputStream outputStream, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024000];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i > 0) {
                outputStream.write(bArr, 0, i);
                j += (long) i;
            } else {
                close(inputStream);
                return j;
            }
        }
    }
}
