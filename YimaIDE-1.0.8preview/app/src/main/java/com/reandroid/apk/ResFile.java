package com.reandroid.apk;

import com.reandroid.apk.xmlencoder.XMLEncodeSource;
import com.reandroid.archive.BlockInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.ResValue;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ResFile implements Iterable<Entry> {
    public static String EXT_9_PNG = ObjectsUtil.of(".9.png");
    private final List<Entry> entryList;
    private final InputSource inputSource;
    private boolean mBinXml;
    private boolean mBinXmlChecked;
    private String mFileExtension;
    private Entry mSelectedEntry;

    public ResFile(InputSource inputSource, List<Entry> list) {
        this.inputSource = inputSource;
        this.entryList = list;
    }

    private String computeFileExtension() {
        String extensionFromMagic;
        if (isBinaryXml()) {
            return ".xml";
        }
        String filePath = getFilePath();
        if (filePath.endsWith(EXT_9_PNG)) {
            return EXT_9_PNG;
        }
        int iLastIndexOf = filePath.lastIndexOf(46);
        if (iLastIndexOf > 0) {
            return filePath.substring(iLastIndexOf);
        }
        try {
            extensionFromMagic = FileMagic.getExtensionFromMagic(getInputSource());
        } catch (IOException unused) {
            extensionFromMagic = null;
        }
        return extensionFromMagic == null ? StringsUtil.EMPTY : extensionFromMagic;
    }

    private List<Entry> getEntries() {
        return this.entryList;
    }

    private ResConfig getResConfigFromPath() {
        String[] strArrSplitPath = splitPath();
        if (strArrSplitPath.length != 3) {
            return null;
        }
        String str = strArrSplitPath[1];
        int iIndexOf = str.indexOf(45);
        return ResConfig.parse(iIndexOf > 0 ? str.substring(iIndexOf) : StringsUtil.EMPTY);
    }

    private Entry selectConfigMatching(List<Entry> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        ResConfig resConfigFromPath = getResConfigFromPath();
        Entry entry = null;
        for (Entry entry2 : list) {
            ResConfig resConfig = entry2.getResConfig();
            if (resConfig.equals(resConfigFromPath)) {
                return entry2;
            }
            if (entry == null || (!entry.getResConfig().isDefault() && resConfig.isDefault())) {
                entry = entry2;
            }
        }
        return entry;
    }

    private Entry selectMatching() {
        final String typeNameFromPath;
        final String entryNameFromPath;
        if (size() >= 2 && (typeNameFromPath = getTypeNameFromPath()) != null) {
            List list = CollectionUtil.toList(iterator(new Predicate() { // from class: bfc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return typeNameFromPath.equals(((Entry) obj).getTypeName());
                }
            }));
            if (list.isEmpty()) {
                return getFirst();
            }
            if (list.size() != 1 && (entryNameFromPath = getEntryNameFromPath()) != null) {
                List<Entry> list2 = CollectionUtil.toList(iterator(new Predicate() { // from class: cfc
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return entryNameFromPath.equals(((Entry) obj).getName());
                    }
                }));
                return list2.isEmpty() ? (Entry) list.get(0) : selectConfigMatching(list2);
            }
            return (Entry) list.get(0);
        }
        return getFirst();
    }

    private String[] splitPath() {
        return StringsUtil.split(getFilePath(), '/', true);
    }

    public File buildOutFile(File file) {
        return new File(file, getFilePath().replace('/', File.separatorChar));
    }

    public String buildPath(String str) {
        Entry entryPickOne = pickOne();
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            if (!str.endsWith("/")) {
                sb.append('/');
            }
        }
        sb.append(entryPickOne.getTypeName());
        sb.append(entryPickOne.getResConfig().getQualifiers());
        sb.append('/');
        sb.append(entryPickOne.getName());
        sb.append(getFileExtension());
        return sb.toString();
    }

    public void delete(boolean z) {
        for (Entry entry : this) {
            entry.setNull(true);
            if (!z) {
                entry.getTypeBlock().removeNullEntries(entry.getId());
            }
        }
        getEntries().clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ResFile) {
            return getFilePath().equals(((ResFile) obj).getFilePath());
        }
        return false;
    }

    public Entry get(int i) {
        return getEntries().get(i);
    }

    @Deprecated
    public List<Entry> getEntryList() {
        return getEntries();
    }

    public String getEntryNameFromPath() {
        String[] strArrSplitPath = splitPath();
        if (strArrSplitPath.length != 3) {
            return null;
        }
        String str = strArrSplitPath[2];
        String str2 = EXT_9_PNG;
        if (str.endsWith(str2)) {
            return str.substring(0, str.length() - str2.length());
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf > 0 ? str.substring(0, iLastIndexOf) : str;
    }

    public String getFileExtension() {
        String str = this.mFileExtension;
        if (str != null) {
            return str;
        }
        String strComputeFileExtension = computeFileExtension();
        this.mFileExtension = strComputeFileExtension;
        return strComputeFileExtension;
    }

    public String getFilePath() {
        return getInputSource().getAlias();
    }

    public Entry getFirst() {
        if (size() != 0) {
            return get(0);
        }
        return null;
    }

    public InputSource getInputSource() {
        return this.inputSource;
    }

    public PackageBlock getPackageBlock() {
        Entry entryPickOne = pickOne();
        if (entryPickOne != null) {
            return entryPickOne.getPackageBlock();
        }
        return null;
    }

    public ResXmlDocument getResXmlDocument() {
        if (!isBinaryXml()) {
            return null;
        }
        try {
            return readAsXmlDocument();
        } catch (IOException unused) {
            return null;
        }
    }

    public String getRootNameFromPath() {
        String[] strArrSplitPath = splitPath();
        if (strArrSplitPath.length > 1) {
            return strArrSplitPath[0];
        }
        return null;
    }

    public String getSimpleName() {
        String[] strArrSplitPath = splitPath();
        return strArrSplitPath[strArrSplitPath.length - 1];
    }

    public String getTypeNameFromPath() {
        String[] strArrSplitPath = splitPath();
        if (strArrSplitPath.length != 3) {
            return null;
        }
        String str = strArrSplitPath[1];
        int iIndexOf = str.indexOf(45);
        return iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
    }

    public int hashCode() {
        return getFilePath().hashCode();
    }

    public boolean isBinaryXml() {
        if (this.mBinXmlChecked) {
            return this.mBinXml;
        }
        this.mBinXmlChecked = true;
        InputSource inputSource = getInputSource();
        if ((inputSource instanceof XMLEncodeSource) || (inputSource instanceof JsonXmlInputSource)) {
            this.mBinXml = true;
        } else if ((inputSource instanceof BlockInputSource) && (((BlockInputSource) inputSource).getBlock() instanceof ResXmlDocument)) {
            this.mBinXml = true;
        }
        if (!this.mBinXml) {
            try {
                this.mBinXml = ResXmlDocument.isResXmlBlock(inputSource.getBytes(8));
            } catch (IOException unused) {
            }
            if (!this.mBinXml && getFilePath().endsWith(".xml")) {
                try {
                    this.mBinXml = true ^ readAsXmlDocument().getStringPool().isEmpty();
                } catch (IOException unused2) {
                }
            }
        }
        return this.mBinXml;
    }

    @Override // java.lang.Iterable
    public Iterator<Entry> iterator() {
        return getEntries().iterator();
    }

    public Entry pickOne() {
        if (this.mSelectedEntry == null) {
            this.mSelectedEntry = selectMatching();
        }
        return this.mSelectedEntry;
    }

    public ResXmlDocument readAsXmlDocument() throws IOException {
        InputSource inputSource = getInputSource();
        if (inputSource instanceof BlockInputSource) {
            ResXmlDocument block = ((BlockInputSource) inputSource).getBlock();
            if (block instanceof ResXmlDocument) {
                return block;
            }
        }
        ResXmlDocument resXmlDocument = new ResXmlDocument();
        resXmlDocument.setPackageBlock(getPackageBlock());
        resXmlDocument.readBytes(getInputSource().openStream());
        return resXmlDocument;
    }

    public void setFilePath(String str) {
        getInputSource().setAlias(str);
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            ResValue resValue = it.next().getResValue();
            if (resValue != null) {
                resValue.setValueAsString(str);
            }
        }
    }

    public int size() {
        return getEntries().size();
    }

    public String toString() {
        return getFilePath();
    }

    public String validateTypeDirectoryName(String str) {
        Entry entryPickOne = pickOne();
        if (entryPickOne == null) {
            return null;
        }
        return FileUtil.combineUnixPath(FileUtil.combineUnixPath(str, entryPickOne.getTypeName() + entryPickOne.getResConfig().getQualifiers()), getSimpleName());
    }

    public Iterator<Entry> iterator(Predicate<? super Entry> predicate) {
        return FilterIterator.of(iterator(), predicate);
    }

    public void delete() {
        delete(true);
    }

    public String validateTypeDirectoryName() {
        String rootNameFromPath = getRootNameFromPath();
        if (StringsUtil.isEmpty(rootNameFromPath)) {
            rootNameFromPath = PackageBlock.RES_DIRECTORY_NAME;
        }
        return validateTypeDirectoryName(rootNameFromPath);
    }

    public String buildPath() {
        String rootNameFromPath = getRootNameFromPath();
        if (StringsUtil.isEmpty(rootNameFromPath)) {
            rootNameFromPath = PackageBlock.RES_DIRECTORY_NAME;
        }
        return buildPath(rootNameFromPath);
    }
}
