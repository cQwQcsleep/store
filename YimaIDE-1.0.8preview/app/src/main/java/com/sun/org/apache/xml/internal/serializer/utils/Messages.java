package com.sun.org.apache.xml.internal.serializer.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Messages {
    private final Locale m_locale = Locale.getDefault();
    private ResourceBundle m_resourceBundle;
    private String m_resourceBundleName;

    public Messages(String str) {
        this.m_resourceBundleName = str;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0074 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x0075  */
    private final String createMsg(ResourceBundle resourceBundle, String str, Object[] objArr) {
        String string;
        String str2;
        if (str != null) {
            string = resourceBundle.getString(str);
        } else {
            str = "";
            string = null;
        }
        boolean z = true;
        if (string == null) {
            try {
                MessageFormat.format(MsgKey.BAD_MSGKEY, str, this.m_resourceBundleName);
            } catch (Exception unused) {
            }
            string = null;
        } else {
            if (objArr != null) {
                try {
                    try {
                        int length = objArr.length;
                        for (int i = 0; i < length; i++) {
                            if (objArr[i] == null) {
                                objArr[i] = "";
                            }
                        }
                        string = MessageFormat.format(string, objArr);
                    } catch (Exception unused2) {
                        str2 = "The format of message '" + str + "' in message class '" + this.m_resourceBundleName + "' failed.";
                        string = str2;
                    }
                } catch (Exception unused3) {
                    str2 = MessageFormat.format(MsgKey.BAD_MSGFORMAT, str, this.m_resourceBundleName) + " " + string;
                    string = str2;
                    if (!z) {
                        return string;
                    }
                    f63.a(string);
                    return null;
                }
            }
            z = false;
        }
        if (!z) {
            return string;
        }
        f63.a(string);
        return null;
    }

    private Locale getLocale() {
        return this.m_locale;
    }

    public final String createMessage(String str, Object[] objArr) {
        if (this.m_resourceBundle == null) {
            this.m_resourceBundle = SecuritySupport.getResourceBundle(this.m_resourceBundleName);
        }
        ResourceBundle resourceBundle = this.m_resourceBundle;
        if (resourceBundle != null) {
            return createMsg(resourceBundle, str, objArr);
        }
        return "Could not load the resource bundles: " + this.m_resourceBundleName;
    }
}
