package com.reandroid.xml.base;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XmlReader {
    void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException;
}
