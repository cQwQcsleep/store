package com.reandroid.identifiers;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.utils.StringsUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TableIdentifier {
    private final List<PackageIdentifier> mPackages = new ArrayList();
    private final Map<String, PackageIdentifier> mNameMap = new HashMap();
    private boolean mCaseInsensitive = Identifier.CASE_INSENSITIVE_FS;

    private File toPublicXmlFile(File file, String str) {
        return new File(new File(new File(new File(file, str), PackageBlock.RES_DIRECTORY_NAME), PackageBlock.VALUES_DIRECTORY_NAME), PackageBlock.PUBLIC_XML);
    }

    private void updateCaseInsensitive(boolean z) {
        Iterator<PackageIdentifier> it = getPackages().iterator();
        while (it.hasNext()) {
            it.next().setCaseInsensitive(z);
        }
    }

    public void add(PackageIdentifier packageIdentifier) {
        if (packageIdentifier != null) {
            this.mPackages.add(packageIdentifier);
            packageIdentifier.setCaseInsensitive(isCaseInsensitive());
        }
    }

    public void clear() {
        Iterator<PackageIdentifier> it = getPackages().iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.mPackages.clear();
        this.mNameMap.clear();
    }

    public int countPackages() {
        return getPackages().size();
    }

    public ResourceIdentifier get(String str, String str2, String str3) {
        ResourceIdentifier resourceIdentifier;
        ResourceIdentifier resourceIdentifier2;
        PackageIdentifier packageIdentifier = this.mNameMap.get(str);
        if (packageIdentifier != null && (resourceIdentifier2 = packageIdentifier.getResourceIdentifier(str2, str3)) != null) {
            return resourceIdentifier2;
        }
        for (PackageIdentifier packageIdentifier2 : getPackages()) {
            if (Objects.equals(str, packageIdentifier2.getName()) && (resourceIdentifier = packageIdentifier2.getResourceIdentifier(str2, str3)) != null) {
                return resourceIdentifier;
            }
        }
        return null;
    }

    public List<PackageIdentifier> getAll(String str) {
        ArrayList arrayList = new ArrayList();
        for (PackageIdentifier packageIdentifier : getPackages()) {
            if (Objects.equals(str, packageIdentifier.getName())) {
                arrayList.add(packageIdentifier);
            }
        }
        return arrayList;
    }

    public PackageIdentifier getByPackage(PackageBlock packageBlock) {
        for (PackageIdentifier packageIdentifier : getPackages()) {
            if (packageBlock == packageIdentifier.getPackageBlock()) {
                return packageIdentifier;
            }
        }
        return null;
    }

    public PackageIdentifier getByTag(Object obj) {
        for (PackageIdentifier packageIdentifier : getPackages()) {
            if (Objects.equals(obj, packageIdentifier.getTag())) {
                return packageIdentifier;
            }
        }
        return null;
    }

    public List<PackageIdentifier> getPackages() {
        return this.mPackages;
    }

    public boolean isCaseInsensitive() {
        return this.mCaseInsensitive;
    }

    public void load(TableBlock tableBlock) {
        if (tableBlock == null) {
            return;
        }
        Iterator it = tableBlock.listPackages().iterator();
        while (it.hasNext()) {
            load((PackageBlock) it.next());
        }
    }

    public void loadPublicXml(Collection<File> collection) throws IOException {
        Iterator<File> it = collection.iterator();
        while (it.hasNext()) {
            try {
                loadPublicXml(it.next());
            } catch (XmlPullParserException e) {
                throw new IOException(e);
            }
        }
    }

    public int renameBadSpecs() {
        Iterator<PackageIdentifier> it = getPackages().iterator();
        int iRenameBadSpecs = 0;
        while (it.hasNext()) {
            iRenameBadSpecs += it.next().renameBadSpecs();
        }
        return iRenameBadSpecs;
    }

    public int renameDuplicateSpecs() {
        updateCaseInsensitive(isCaseInsensitive());
        Iterator<PackageIdentifier> it = getPackages().iterator();
        int iRenameDuplicateSpecs = 0;
        while (it.hasNext()) {
            iRenameDuplicateSpecs += it.next().renameDuplicateSpecs();
        }
        return iRenameDuplicateSpecs;
    }

    public int renameSpecs() {
        Iterator<PackageIdentifier> it = getPackages().iterator();
        int iRenameSpecs = 0;
        while (it.hasNext()) {
            iRenameSpecs += it.next().renameSpecs();
        }
        return iRenameSpecs;
    }

    public void setCaseInsensitive(boolean z) {
        this.mCaseInsensitive = z;
        updateCaseInsensitive(z);
    }

    public void setTableBlock(TableBlock tableBlock) {
        for (PackageBlock packageBlock : tableBlock.listPackages()) {
            int id = packageBlock.getId();
            for (PackageIdentifier packageIdentifier : getPackages()) {
                if (packageIdentifier.getId() == id) {
                    packageIdentifier.setPackageBlock(packageBlock);
                }
            }
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": packages = " + countPackages();
    }

    public String validateSpecNames() {
        int iRenameDuplicateSpecs = renameDuplicateSpecs();
        int iRenameBadSpecs = renameBadSpecs();
        if (iRenameDuplicateSpecs == 0 && iRenameBadSpecs == 0) {
            return null;
        }
        return "Spec names validated, duplicates = " + iRenameDuplicateSpecs + ", bad = " + iRenameBadSpecs;
    }

    public void writeAllPublicXml(File file) throws IOException {
        List<PackageIdentifier> packages = getPackages();
        int i = 0;
        for (PackageIdentifier packageIdentifier : getPackages()) {
            i++;
            PackageBlock packageBlock = packageIdentifier.getPackageBlock();
            packageIdentifier.writePublicXml(toPublicXmlFile(file, packageBlock != null ? packageBlock.buildDecodeDirectoryName() : PackageBlock.DIRECTORY_NAME_PREFIX + StringsUtil.formatNumber(i, packages.size())));
        }
    }

    public PackageIdentifier load(PackageBlock packageBlock) {
        PackageIdentifier packageIdentifier = new PackageIdentifier();
        packageIdentifier.load(packageBlock);
        add(packageIdentifier);
        this.mNameMap.put(packageIdentifier.getName(), packageIdentifier);
        return packageIdentifier;
    }

    public PackageIdentifier loadPublicXml(File file) throws XmlPullParserException, IOException {
        PackageIdentifier packageIdentifier = new PackageIdentifier();
        packageIdentifier.loadPublicXml(file);
        add(packageIdentifier);
        packageIdentifier.setTag(file);
        return packageIdentifier;
    }

    public PackageIdentifier loadPublicXml(InputStream inputStream) throws XmlPullParserException, IOException {
        PackageIdentifier packageIdentifier = new PackageIdentifier();
        packageIdentifier.loadPublicXml(inputStream);
        add(packageIdentifier);
        return packageIdentifier;
    }

    public PackageIdentifier loadPublicXml(Reader reader) throws XmlPullParserException, IOException {
        PackageIdentifier packageIdentifier = new PackageIdentifier();
        packageIdentifier.loadPublicXml(reader);
        add(packageIdentifier);
        return packageIdentifier;
    }

    public PackageIdentifier loadPublicXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        PackageIdentifier packageIdentifier = new PackageIdentifier();
        packageIdentifier.loadPublicXml(xmlPullParser);
        add(packageIdentifier);
        return packageIdentifier;
    }

    public List<PackageIdentifier> getAll(int i) {
        ArrayList arrayList = new ArrayList();
        for (PackageIdentifier packageIdentifier : getPackages()) {
            if (i == packageIdentifier.getId()) {
                arrayList.add(packageIdentifier);
            }
        }
        return arrayList;
    }

    public ResourceIdentifier get(String str, String str2) {
        Iterator<PackageIdentifier> it = getPackages().iterator();
        while (it.hasNext()) {
            ResourceIdentifier resourceIdentifier = it.next().getResourceIdentifier(str, str2);
            if (resourceIdentifier != null) {
                return resourceIdentifier;
            }
        }
        return null;
    }

    public PackageIdentifier get(int i) {
        for (PackageIdentifier packageIdentifier : getPackages()) {
            if (i == packageIdentifier.getId()) {
                return packageIdentifier;
            }
        }
        return null;
    }
}
