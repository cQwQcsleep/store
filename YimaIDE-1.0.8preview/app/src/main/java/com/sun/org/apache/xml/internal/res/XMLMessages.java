package com.sun.org.apache.xml.internal.res;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLMessages {
    protected static final String BAD_CODE = "BAD_CODE";
    protected static final String FORMAT_FAILED = "FORMAT_FAILED";
    private static ResourceBundle XMLBundle = null;
    private static final String XML_ERROR_RESOURCES = "com.sun.org.apache.xml.internal.res.XMLErrorResources";
    protected Locale fLocale = Locale.getDefault();

    public static final String createMsg(ResourceBundle resourceBundle, String str, Object[] objArr) {
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

    public static final String createXMLMessage(String str, Object[] objArr) {
        if (XMLBundle == null) {
            XMLBundle = SecuritySupport.getResourceBundle(XML_ERROR_RESOURCES);
        }
        ResourceBundle resourceBundle = XMLBundle;
        return resourceBundle != null ? createMsg(resourceBundle, str, objArr) : "Could not load any resource bundles.";
    }

    public Locale getLocale() {
        return this.fLocale;
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
    }
}
