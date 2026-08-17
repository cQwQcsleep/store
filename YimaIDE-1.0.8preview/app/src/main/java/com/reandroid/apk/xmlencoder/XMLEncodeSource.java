package com.reandroid.apk.xmlencoder;

import com.reandroid.apk.APKLogger;
import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.ByteInputSource;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.utils.Crc32;
import com.reandroid.utils.io.IOUtil;
import com.reandroid.xml.source.XMLParserSource;
import java.io.IOException;
import java.io.OutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class XMLEncodeSource extends ByteInputSource {
    private static final byte[] DISPOSED = new byte[0];
    private byte[] array;
    private APKLogger mLogger;
    private final PackageBlock packageBlock;
    private final XMLParserSource parserSource;

    public XMLEncodeSource(PackageBlock packageBlock, XMLParserSource xMLParserSource) {
        super(DISPOSED, xMLParserSource.getPath());
        this.packageBlock = packageBlock;
        this.parserSource = xMLParserSource;
    }

    private ResXmlDocument encode() throws XmlPullParserException, IOException {
        XMLParserSource xMLParserSource = this.parserSource;
        String path = xMLParserSource.getPath();
        logVerbose("Encoding: " + path);
        XmlPullParser parser = xMLParserSource.getParser();
        AndroidManifestBlock androidManifestBlock = AndroidManifest.FILE_NAME.equals(path) ? new AndroidManifestBlock() : new ResXmlDocument();
        androidManifestBlock.setPackageBlock(this.packageBlock);
        androidManifestBlock.parse(parser);
        IOUtil.close(parser);
        return androidManifestBlock;
    }

    private byte[] getArray() throws IOException {
        byte[] bArr = this.array;
        if (bArr != null) {
            return bArr;
        }
        try {
            byte[] bytes = encode().getBytes();
            this.array = bytes;
            return bytes;
        } catch (XmlPullParserException e) {
            throw new IOException(e);
        }
    }

    private void logVerbose(String str) {
        APKLogger aPKLogger = this.mLogger;
        if (aPKLogger != null) {
            aPKLogger.logVerbose(str);
        }
    }

    @Override // com.reandroid.archive.ByteInputSource, com.reandroid.archive.InputSource
    public void disposeInputSource() {
        this.array = DISPOSED;
    }

    @Override // com.reandroid.archive.ByteInputSource
    public byte[] getBytes() {
        try {
            return getArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // com.reandroid.archive.InputSource
    public long getCrc() throws IOException {
        Crc32 crc32 = new Crc32();
        crc32.update(getArray());
        return crc32.getValue();
    }

    @Override // com.reandroid.archive.InputSource
    public long getLength() throws IOException {
        return getArray().length;
    }

    public void setApkLogger(APKLogger aPKLogger) {
        this.mLogger = aPKLogger;
    }

    @Override // com.reandroid.archive.ByteInputSource, com.reandroid.archive.InputSource
    public long write(OutputStream outputStream) throws IOException {
        byte[] array = getArray();
        if (array != DISPOSED) {
            outputStream.write(array, 0, array.length);
            return array.length;
        }
        u8g.a("Disposed source: ", getAlias());
        return 0L;
    }
}
