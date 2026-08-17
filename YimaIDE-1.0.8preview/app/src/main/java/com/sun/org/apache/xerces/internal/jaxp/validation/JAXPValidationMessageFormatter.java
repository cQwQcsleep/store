package com.sun.org.apache.xerces.internal.jaxp.validation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class JAXPValidationMessageFormatter {
    public static String formatMessage(Locale locale, String str, Object[] objArr) throws MissingResourceException {
        ResourceBundle resourceBundle = locale != null ? SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.JAXPValidationMessages", locale) : SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.JAXPValidationMessages");
        try {
            String string = resourceBundle.getString(str);
            if (objArr != null) {
                try {
                    string = MessageFormat.format(string, objArr);
                } catch (Exception unused) {
                    string = resourceBundle.getString("FormatFailed") + " " + resourceBundle.getString(str);
                }
            }
            if (string != null) {
                return string;
            }
            if (objArr.length <= 0) {
                return str;
            }
            StringBuffer stringBuffer = new StringBuffer(str);
            stringBuffer.append('?');
            for (int i = 0; i < objArr.length; i++) {
                if (i > 0) {
                    stringBuffer.append('&');
                }
                stringBuffer.append(String.valueOf(objArr[i]));
            }
            return str;
        } catch (MissingResourceException unused2) {
            throw new MissingResourceException(str, resourceBundle.getString("BadMessageKey"), str);
        }
    }
}
