package com.fasterxml.aalto.util;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class TextAccumulator {
    private String mText = null;
    private StringBuilder mBuilder = null;

    public void addText(String str) {
        int length = str.length();
        if (length > 0) {
            String str2 = this.mText;
            if (str2 != null) {
                StringBuilder sb = new StringBuilder(str2.length() + length);
                this.mBuilder = sb;
                sb.append(this.mText);
                this.mText = null;
            }
            StringBuilder sb2 = this.mBuilder;
            if (sb2 != null) {
                sb2.append(str);
            } else {
                this.mText = str;
            }
        }
    }

    public String getAndClear() {
        String str = this.mText;
        if (str != null) {
            this.mText = null;
            return str;
        }
        StringBuilder sb = this.mBuilder;
        if (sb == null) {
            return XmlPullParser.NO_NAMESPACE;
        }
        String string = sb.toString();
        this.mBuilder = null;
        return string;
    }
}
