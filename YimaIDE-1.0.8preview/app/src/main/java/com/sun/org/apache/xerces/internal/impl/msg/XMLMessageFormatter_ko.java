package com.sun.org.apache.xerces.internal.impl.msg;

import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLMessageFormatter_ko implements MessageFormatter {
    public static final String XMLNS_DOMAIN = "http://www.w3.org/TR/1999/REC-xml-names-19990114";
    public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
    private Locale fLocale = null;
    private ResourceBundle fResourceBundle = null;

    @Override // com.sun.org.apache.xerces.internal.util.MessageFormatter
    public String formatMessage(Locale locale, String str, Object[] objArr) throws MissingResourceException {
        if (this.fResourceBundle == null || locale != this.fLocale) {
            if (locale != null) {
                this.fResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XMLMessages", locale);
                this.fLocale = locale;
            }
            if (this.fResourceBundle == null) {
                this.fResourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XMLMessages");
            }
        }
        try {
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
            throw new MissingResourceException(str, this.fResourceBundle.getString("BadMessageKey"), str);
        }
    }
}
