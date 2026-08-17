package com.reandroid.xml;

import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArraySort;
import com.reandroid.xml.StyleAttribute;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SpanAttributesEncoder {
    public static String encodeAttributes(StyleElement styleElement) {
        StyleAttribute[] sortedAttributes = getSortedAttributes(styleElement);
        if (sortedAttributes == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String str = Span.RAW_STYLE_TAG_ATTRIBUTE;
        for (StyleAttribute styleAttribute : sortedAttributes) {
            if (!str.equals(styleAttribute.getName())) {
                sb.append(';');
                sb.append(styleAttribute.getName());
                sb.append('=');
            }
            sb.append(styleAttribute.getValueAsString());
        }
        return sb.toString();
    }

    private static StyleAttribute[] getSortedAttributes(StyleElement styleElement) {
        int attributeCount = styleElement.getAttributeCount();
        if (attributeCount == 0) {
            return null;
        }
        StyleAttribute[] styleAttributeArr = new StyleAttribute[attributeCount];
        for (int i = 0; i < attributeCount; i++) {
            styleAttributeArr[i] = styleElement.getAttributeAt(i);
        }
        if (attributeCount != 1) {
            ArraySort.sort(styleAttributeArr, new Comparator() { // from class: wjd
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return CompareUtil.compare(((StyleAttribute) obj).getName(), ((StyleAttribute) obj2).getName());
                }
            });
        }
        return styleAttributeArr;
    }
}
