package com.reandroid.apk;

import com.reandroid.archive.FileInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import java.io.File;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class JsonManifestInputSource extends JsonXmlInputSource {
    public JsonManifestInputSource(InputSource inputSource) {
        super(inputSource);
    }

    public static JsonManifestInputSource fromFile(File file, File file2) {
        return new JsonManifestInputSource(new FileInputSource(file2, ApkUtil.jsonToArchiveResourcePath(file, file2)));
    }

    @Override // com.reandroid.apk.JsonXmlInputSource
    /* JADX INFO: renamed from: newInstance, reason: merged with bridge method [inline-methods] */
    public AndroidManifestBlock mo6457newInstance() {
        return new AndroidManifestBlock();
    }
}
