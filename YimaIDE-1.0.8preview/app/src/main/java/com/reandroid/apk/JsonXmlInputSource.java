package com.reandroid.apk;

import com.reandroid.archive.FileInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class JsonXmlInputSource extends InputSource {
    private APKLogger apkLogger;
    private final InputSource inputSource;

    public JsonXmlInputSource(InputSource inputSource) {
        super(inputSource.getAlias());
        this.inputSource = inputSource;
    }

    public static JsonXmlInputSource fromFile(File file, File file2) {
        return new JsonXmlInputSource(new FileInputSource(file2, ApkUtil.jsonToArchiveResourcePath(file, file2)));
    }

    private ResXmlDocument getResXmlBlock() throws IOException {
        logVerbose("From json: " + getAlias());
        ResXmlDocument resXmlDocumentMo6457newInstance = mo6457newInstance();
        try {
            resXmlDocumentMo6457newInstance.fromJson(new JSONObject(this.inputSource.openStream()));
            return resXmlDocumentMo6457newInstance;
        } catch (JSONException e) {
            throw new IOException(this.inputSource.getAlias() + ": " + e.getMessage(), e);
        }
    }

    private void logError(String str, Throwable th) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logError(str, th);
        }
    }

    private void logVerbose(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logVerbose(str);
        }
    }

    @Override // com.reandroid.archive.InputSource
    public long getLength() throws IOException {
        return getResXmlBlock().countBytes();
    }

    public void logMessage(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logMessage(str);
        }
    }

    /* JADX INFO: renamed from: newInstance */
    public ResXmlDocument mo6457newInstance() {
        return new ResXmlDocument();
    }

    @Override // com.reandroid.archive.InputSource
    public InputStream openStream() throws IOException {
        return new ByteArrayInputStream(getResXmlBlock().getBytes());
    }

    public void setAPKLogger(APKLogger aPKLogger) {
        this.apkLogger = aPKLogger;
    }

    @Override // com.reandroid.archive.InputSource
    public long write(OutputStream outputStream) throws IOException {
        return getResXmlBlock().writeBytes(outputStream);
    }
}
