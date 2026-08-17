package com.reandroid.arsc;

import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ApkFile {
    void add(InputSource inputSource);

    default void addAll(Collection<? extends InputSource> collection) {
        if (collection == null) {
            return;
        }
        Iterator<? extends InputSource> it = collection.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    boolean containsFile(String str);

    AndroidManifestBlock getAndroidManifest();

    InputSource getInputSource(String str);

    TableBlock getLoadedTableBlock();

    ResXmlDocument getResXmlDocument(String str);

    TableBlock getTableBlock();

    ResXmlDocument loadResXmlDocument(String str) throws IOException;

    default void mergeWithName(ResourceMergeOption resourceMergeOption, ApkFile apkFile, String str) {
        resourceMergeOption.mergeFileWithName(apkFile, this, str);
    }
}
