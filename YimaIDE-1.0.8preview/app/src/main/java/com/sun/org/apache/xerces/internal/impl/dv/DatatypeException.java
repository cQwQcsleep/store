package com.sun.org.apache.xerces.internal.impl.dv;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DatatypeException extends Exception {
    static final long serialVersionUID = 1940805832730465578L;
    protected Object[] args;
    protected String key;

    public DatatypeException(String str, Object[] objArr) {
        super(str);
        this.key = str;
        this.args = objArr;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getKey() {
        return this.key;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        ResourceBundle resourceBundle = SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.XMLSchemaMessages");
        if (resourceBundle == null) {
            throw new MissingResourceException("Property file not found!", "com.sun.org.apache.xerces.internal.impl.msg.XMLSchemaMessages", this.key);
        }
        String string = resourceBundle.getString(this.key);
        if (string == null) {
            throw new MissingResourceException(resourceBundle.getString("BadMessageKey"), "com.sun.org.apache.xerces.internal.impl.msg.XMLSchemaMessages", this.key);
        }
        Object[] objArr = this.args;
        if (objArr == null) {
            return string;
        }
        try {
            return MessageFormat.format(string, objArr);
        } catch (Exception unused) {
            return resourceBundle.getString("FormatFailed") + " " + resourceBundle.getString(this.key);
        }
    }
}
