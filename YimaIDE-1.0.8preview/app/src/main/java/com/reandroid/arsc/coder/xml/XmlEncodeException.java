package com.reandroid.arsc.coder.xml;

import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlEncodeException extends IOException {
    public XmlEncodeException(XmlPullParser xmlPullParser, String str) {
        this(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\n" + str);
    }

    public XmlEncodeException(String str) {
        super(str);
    }
}
