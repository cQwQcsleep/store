package com.sun.org.apache.xerces.internal.xinclude;

import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XIncludeMessageFormatter implements MessageFormatter {
    public static final String XINCLUDE_DOMAIN = "http://www.w3.org/TR/xinclude";
    private Locale fLocale = null;
    private ResourceBundle fResourceBundle = null;

    @Override // com.sun.org.apache.xerces.internal.util.MessageFormatter
    public String formatMessage(Locale locale, String str, Object[] objArr) throws MissingResourceException {
        if (this.fResourceBundle == null || locale != this.fLocale) {
            if (locale != null) {
                this.fResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XIncludeMessages", locale);
                this.fLocale = locale;
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XIncludeMessages");
            }
        }
        String string = this.fResourceBundle.getString(str);
        if (objArr != null) {
            try {
                string = MessageFormat.format(string, objArr);
            } catch (Exception unused) {
                string = this.fResourceBundle.getString("FormatFailed") + " " + this.fResourceBundle.getString(str);
            }
        }
        if (string != null) {
            return string;
        }
        throw new MissingResourceException(this.fResourceBundle.getString("BadMessageKey"), "com.sun.org.apache.xerces.internal.impl.msg.XIncludeMessages", str);
    }
}
