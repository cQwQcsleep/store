package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeProxy;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NodeInfo {
    public static int columnNumber(NodeList nodeList) {
        if (nodeList != null && nodeList.getLength() != 0) {
            DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) nodeList.item(0);
            SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
            if (sourceLocatorFor != null) {
                return sourceLocatorFor.getColumnNumber();
            }
        }
        return -1;
    }

    public static int lineNumber(NodeList nodeList) {
        if (nodeList != null && nodeList.getLength() != 0) {
            DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) nodeList.item(0);
            SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
            if (sourceLocatorFor != null) {
                return sourceLocatorFor.getLineNumber();
            }
        }
        return -1;
    }

    public static String publicId(NodeList nodeList) {
        if (nodeList != null && nodeList.getLength() != 0) {
            DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) nodeList.item(0);
            SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
            if (sourceLocatorFor != null) {
                return sourceLocatorFor.getPublicId();
            }
        }
        return null;
    }

    public static String systemId(NodeList nodeList) {
        if (nodeList != null && nodeList.getLength() != 0) {
            DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) nodeList.item(0);
            SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
            if (sourceLocatorFor != null) {
                return sourceLocatorFor.getSystemId();
            }
        }
        return null;
    }

    public static int columnNumber(ExpressionContext expressionContext) {
        DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) expressionContext.getContextNode();
        SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
        if (sourceLocatorFor != null) {
            return sourceLocatorFor.getColumnNumber();
        }
        return -1;
    }

    public static int lineNumber(ExpressionContext expressionContext) {
        DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) expressionContext.getContextNode();
        SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
        if (sourceLocatorFor != null) {
            return sourceLocatorFor.getLineNumber();
        }
        return -1;
    }

    public static String publicId(ExpressionContext expressionContext) {
        DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) expressionContext.getContextNode();
        SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
        if (sourceLocatorFor != null) {
            return sourceLocatorFor.getPublicId();
        }
        return null;
    }

    public static String systemId(ExpressionContext expressionContext) {
        DTMNodeProxy dTMNodeProxy = (DTMNodeProxy) expressionContext.getContextNode();
        SourceLocator sourceLocatorFor = dTMNodeProxy.getDTM().getSourceLocatorFor(dTMNodeProxy.getDTMNodeNumber());
        if (sourceLocatorFor != null) {
            return sourceLocatorFor.getSystemId();
        }
        return null;
    }
}
