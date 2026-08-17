package com.sun.org.apache.xpath.internal.res;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPATHMessages extends XMLMessages {
    private static ResourceBundle XPATHBundle = null;
    private static final String XPATH_ERROR_RESOURCES = "com.sun.org.apache.xpath.internal.res.XPATHErrorResources";

    public static final String createXPATHMessage(String str, Object[] objArr) {
        if (XPATHBundle == null) {
            XPATHBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xpath.internal.res.XPATHErrorResources");
        }
        ResourceBundle resourceBundle = XPATHBundle;
        return resourceBundle != null ? createXPATHMsg(resourceBundle, str, objArr) : "Could not load any resource bundles.";
    }

    private static final String createXPATHMsg(ResourceBundle resourceBundle, String str, Object[] objArr) {
        boolean z;
        String string = str != null ? resourceBundle.getString(str) : null;
        if (string == null) {
            string = resourceBundle.getString("BAD_CODE");
            z = true;
        } else {
            z = false;
        }
        if (objArr != null) {
            try {
                int length = objArr.length;
                for (int i = 0; i < length; i++) {
                    if (objArr[i] == null) {
                        objArr[i] = "";
                    }
                }
                string = MessageFormat.format(string, objArr);
            } catch (Exception unused) {
                string = resourceBundle.getString("FORMAT_FAILED") + " " + string;
            }
        }
        if (!z) {
            return string;
        }
        f63.a(string);
        return null;
    }

    public static final String createXPATHWarning(String str, Object[] objArr) {
        if (XPATHBundle == null) {
            XPATHBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xpath.internal.res.XPATHErrorResources");
        }
        ResourceBundle resourceBundle = XPATHBundle;
        return resourceBundle != null ? createXPATHMsg(resourceBundle, str, objArr) : "Could not load any resource bundles.";
    }
}
