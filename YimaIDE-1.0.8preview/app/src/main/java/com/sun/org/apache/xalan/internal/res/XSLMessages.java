package com.sun.org.apache.xalan.internal.res;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSLMessages extends XPATHMessages {
    private static ResourceBundle XSLTBundle = null;
    private static final String XSLT_ERROR_RESOURCES = "com.sun.org.apache.xalan.internal.res.XSLTErrorResources";

    public static String createMessage(String str, Object[] objArr) {
        if (XSLTBundle == null) {
            XSLTBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xalan.internal.res.XSLTErrorResources");
        }
        ResourceBundle resourceBundle = XSLTBundle;
        return resourceBundle != null ? XMLMessages.createMsg(resourceBundle, str, objArr) : "Could not load any resource bundles.";
    }

    public static String createWarning(String str, Object[] objArr) {
        if (XSLTBundle == null) {
            XSLTBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xalan.internal.res.XSLTErrorResources");
        }
        ResourceBundle resourceBundle = XSLTBundle;
        return resourceBundle != null ? XMLMessages.createMsg(resourceBundle, str, objArr) : "Could not load any resource bundles.";
    }
}
