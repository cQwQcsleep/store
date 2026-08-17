package com.reandroid.xml;

import com.reandroid.utils.StringsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SpanAttributesDecoder {
    private final StyleElement element;
    private final String encodedAttributes;
    private boolean error;
    private int index;
    private final int length;

    public SpanAttributesDecoder(StyleElement styleElement, String str) {
        this.element = styleElement;
        this.encodedAttributes = str;
        this.length = str.length();
    }

    private StyleAttribute getLastAttribute() {
        int attributeCount = this.element.getAttributeCount();
        if (attributeCount != 0) {
            return this.element.getAttributeAt(attributeCount - 1);
        }
        return null;
    }

    private boolean isAttributeChar(char c) {
        return StringsUtil.isAzOrDigits(c) || c == '_';
    }

    private boolean isAttributeName(String str) {
        int length = str.length();
        if (length == 0 || !isFirstAttributeChar(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (!isAttributeChar(str.charAt(i))) {
                return false;
            }
        }
        StyleAttribute lastAttribute = getLastAttribute();
        return lastAttribute == null || str.compareTo(lastAttribute.getName()) >= 0;
    }

    private boolean isFirstAttributeChar(char c) {
        return StringsUtil.isAz(c);
    }

    private void onError() {
        this.element.clearAttributes();
        this.index = this.length;
        this.error = true;
        StyleAttribute styleAttribute = new StyleAttribute();
        styleAttribute.set(Span.RAW_STYLE_TAG_ATTRIBUTE, this.encodedAttributes);
        this.element.addAttribute(styleAttribute);
    }

    private boolean parseNext() {
        String strPickAttributeName = pickAttributeName();
        String strPickAttributeValue = pickAttributeValue();
        if (strPickAttributeName == null && strPickAttributeValue == null) {
            return false;
        }
        if (strPickAttributeName != null && strPickAttributeValue != null) {
            StyleAttribute styleAttribute = new StyleAttribute();
            styleAttribute.set(strPickAttributeName, strPickAttributeValue);
            this.element.addAttribute(styleAttribute);
            return true;
        }
        StyleAttribute lastAttribute = getLastAttribute();
        if (lastAttribute == null) {
            onError();
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(lastAttribute.getValueAsString());
        sb.append(';');
        if (strPickAttributeName == null) {
            sb.append(strPickAttributeValue);
        } else {
            sb.append(strPickAttributeName);
            sb.append('=');
        }
        lastAttribute.setValue(sb.toString());
        return true;
    }

    private String pickAttributeName() {
        int i = this.length - 1;
        for (int i2 = this.index; i2 < i; i2++) {
            if (this.encodedAttributes.charAt(i2) == '=') {
                String strSubstring = this.encodedAttributes.substring(this.index, i2);
                if (!isAttributeName(strSubstring)) {
                    return null;
                }
                this.index = i2 + 1;
                return strSubstring;
            }
        }
        return null;
    }

    private String pickAttributeValue() {
        int i = this.length - 1;
        for (int i2 = this.index; i2 < i; i2++) {
            if (this.encodedAttributes.charAt(i2) == ';') {
                String strSubstring = this.encodedAttributes.substring(this.index, i2);
                this.index = i2 + 1;
                return strSubstring;
            }
        }
        int i3 = this.length;
        int i4 = this.index;
        if (i4 >= i3) {
            return null;
        }
        this.index = i3;
        return this.encodedAttributes.substring(i4);
    }

    private void reset() {
        this.element.clearAttributes();
        this.index = 0;
        this.error = false;
    }

    public boolean decode() {
        reset();
        while (parseNext()) {
        }
        return this.error;
    }
}
