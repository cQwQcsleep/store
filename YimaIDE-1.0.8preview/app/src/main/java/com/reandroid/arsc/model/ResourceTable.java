package com.reandroid.arsc.model;

import android.content.res.XmlResourceParser;
import com.reandroid.arsc.ApkFile;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.ResXmlPullParser;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResourceTable implements Iterable<ResourcePackage> {
    private final TableBlock tableBlock;

    public ResourceTable(TableBlock tableBlock) {
        this.tableBlock = tableBlock;
    }

    public static /* synthetic */ XmlResourceParser a(ResourceTable resourceTable, String str) {
        resourceTable.getClass();
        try {
            return resourceTable.loadXml(str);
        } catch (IOException unused) {
            return null;
        }
    }

    private ApkFile getApkFile() {
        return getTableBlock().getApkFile();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceTable)) {
            return false;
        }
        ResourceTable resourceTable = (ResourceTable) obj;
        if (size() != resourceTable.size()) {
            return false;
        }
        Iterator<ResourcePackage> it = iterator();
        Iterator<ResourcePackage> it2 = resourceTable.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (!it.next().equals(it2.next())) {
                return false;
            }
        }
        return !it.hasNext() && it2.hasNext();
    }

    public int getIdentifier(String str, String str2, String str3) {
        ResourceEntry resource = getResource(str3, str2, str);
        if (resource != null) {
            return resource.getResourceId();
        }
        return 0;
    }

    public XmlResourceParser getLayout(int i) throws IOException {
        return getParser(i);
    }

    public XmlResourceParser getParser(int i) throws IOException {
        ApkFile apkFile = getApkFile();
        if (apkFile == null) {
            throw new FileNotFoundException("Missing apk file");
        }
        Iterator<String> strings = getStrings(i);
        while (strings.hasNext()) {
            String next = strings.next();
            if (apkFile.containsFile(next)) {
                return loadXml(next);
            }
        }
        throw new FileNotFoundException("No resource found for: " + HexUtil.toHex8(i));
    }

    public Iterator<XmlResourceParser> getParsers(int i) {
        return ComputeIterator.of(getStrings(i), new fic(this));
    }

    public ResourceEntry getResource(String str, String str2, String str3) {
        return getTableBlock().getResource(str3, str2, str);
    }

    public String getString(int i) {
        Iterator<String> strings = getStrings(i);
        if (strings.hasNext()) {
            return strings.next();
        }
        return null;
    }

    public Iterator<String> getStrings(int i) {
        ResourceEntry resource = getResource(i);
        return resource != null ? resource.getStringValues() : EmptyIterator.of();
    }

    public TableBlock getTableBlock() {
        return this.tableBlock;
    }

    public XmlResourceParser getXml(int i) throws IOException {
        return getParser(i);
    }

    public int hashCode() {
        return getTableBlock().hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<ResourcePackage> iterator() {
        return ComputeIterator.of(getTableBlock().iterator(), new gic());
    }

    public XmlResourceParser loadXml(String str) throws IOException {
        ApkFile apkFile = getApkFile();
        if (apkFile == null || !apkFile.containsFile(str)) {
            throw new FileNotFoundException("Missing apk file");
        }
        return new ResXmlPullParser(apkFile.loadResXmlDocument(str));
    }

    public int size() {
        return getTableBlock().size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("packages = ");
        sb.append(size());
        sb.append('[');
        boolean z = false;
        for (ResourcePackage resourcePackage : this) {
            if (z) {
                sb.append(", ");
            }
            sb.append(resourcePackage.getName());
            z = true;
        }
        sb.append(']');
        return sb.toString();
    }

    public ResourceEntry getResource(int i) {
        return getTableBlock().getResource(i);
    }
}
