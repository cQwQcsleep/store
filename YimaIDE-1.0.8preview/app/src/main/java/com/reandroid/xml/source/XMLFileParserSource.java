package com.reandroid.xml.source;

import com.reandroid.xml.XMLFactory;
import java.io.File;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class XMLFileParserSource implements XMLParserSource {
    private final File file;
    private final String path;

    public XMLFileParserSource(String str, File file) {
        this.path = str;
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    @Override // com.reandroid.xml.source.XMLParserSource
    public XmlPullParser getParser() throws XmlPullParserException {
        return XMLFactory.newPullParser(getFile());
    }

    @Override // com.reandroid.xml.source.XMLParserSource
    public String getPath() {
        return this.path;
    }
}
