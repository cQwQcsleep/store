package com.reandroid.apk.xmlencoder;

import com.reandroid.xml.XMLUtil;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class EncodeException extends IllegalArgumentException {
    public EncodeException(XmlPullParser xmlPullParser, String str) {
        this(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\n" + str);
    }

    public EncodeException(String str) {
        super(str);
    }

    public EncodeException(String str, Throwable th) {
        super(str, th);
    }
}
