package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeIterator;
import com.sun.org.apache.xpath.internal.NodeSet;
import com.sun.org.apache.xpath.internal.axes.RTFIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ExsltCommon {
    public static NodeSet nodeSet(ExpressionContext expressionContext, Object obj) {
        return Extensions.nodeset(expressionContext, obj);
    }

    public static String objectType(Object obj) {
        if (obj instanceof String) {
            return "string";
        }
        if (obj instanceof Boolean) {
            return "boolean";
        }
        if (obj instanceof Number) {
            return "number";
        }
        if (obj instanceof DTMNodeIterator) {
            return ((DTMNodeIterator) obj).getDTMIterator() instanceof RTFIterator ? "RTF" : "node-set";
        }
        return "unknown";
    }
}
