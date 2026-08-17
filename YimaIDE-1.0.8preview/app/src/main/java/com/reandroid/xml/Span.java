package com.reandroid.xml;

import com.reandroid.utils.ObjectsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Span {
    public static final String RAW_STYLE_TAG_ATTRIBUTE = ObjectsUtil.of("raw_style_tag_attribute");

    static String splitAttribute(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.charAt(0) == ' ') {
            str = str.trim();
        }
        int iIndexOf = str.indexOf(59);
        int iIndexOf2 = str.indexOf(32);
        if (iIndexOf < 0 || (iIndexOf2 >= 0 && iIndexOf2 < iIndexOf)) {
            iIndexOf = iIndexOf2;
        }
        if (iIndexOf < 0) {
            return null;
        }
        String strSubstring = str.substring(iIndexOf + 1);
        if (strSubstring.length() == 0) {
            return null;
        }
        return strSubstring;
    }

    static String splitTagName(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf(59);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(32);
        }
        return iIndexOf < 0 ? str : str.substring(0, iIndexOf);
    }

    int getFirstChar();

    int getLastChar();

    String getSpanAttributes();

    int getSpanOrder();

    String getTagName();

    default StyleElement toElement() {
        StyleElement styleElement = new StyleElement();
        styleElement.setName(getTagName());
        String spanAttributes = getSpanAttributes();
        if (spanAttributes != null) {
            new SpanAttributesDecoder(styleElement, spanAttributes).decode();
        }
        return styleElement;
    }
}
