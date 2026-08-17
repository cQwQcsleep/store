package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import com.sun.org.apache.xpath.internal.objects.XString;
import java.io.BufferedInputStream;
import java.util.Properties;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FuncSystemProperty extends FunctionOneArg {
    static final String XSLT_PROPERTIES = "com/sun/org/apache/xalan/internal/res/XSLTInfo.properties";
    static final long serialVersionUID = 3694874980992204867L;

    private void loadPropertyFile(Properties properties) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(SecuritySupport.getResourceAsStream(XSLT_PROPERTIES));
            try {
                properties.load(bufferedInputStream);
                bufferedInputStream.close();
            } catch (Throwable th) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.functions.Function, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        String systemProperty;
        String str = this.m_arg0.execute(xPathContext).str();
        int iIndexOf = str.indexOf(58);
        Properties properties = new Properties();
        loadPropertyFile(properties);
        String str2 = "";
        if (iIndexOf > 0) {
            String namespaceForPrefix = xPathContext.getNamespaceContext().getNamespaceForPrefix(iIndexOf >= 0 ? str.substring(0, iIndexOf) : "");
            String strSubstring = iIndexOf < 0 ? str : str.substring(iIndexOf + 1);
            if (namespaceForPrefix.startsWith("http://www.w3.org/XSL/Transform") || namespaceForPrefix.equals("http://www.w3.org/1999/XSL/Transform")) {
                systemProperty = properties.getProperty(strSubstring);
                if (systemProperty == null) {
                    warn(xPathContext, "WG_PROPERTY_NOT_SUPPORTED", new Object[]{str});
                    return XString.EMPTYSTRING;
                }
            } else {
                warn(xPathContext, "WG_DONT_DO_ANYTHING_WITH_NS", new Object[]{namespaceForPrefix, str});
                try {
                    systemProperty = SecuritySupport.getSystemProperty(strSubstring);
                    if (systemProperty == null) {
                        return XString.EMPTYSTRING;
                    }
                } catch (SecurityException unused) {
                    warn(xPathContext, "WG_SECURITY_EXCEPTION", new Object[]{str});
                    return XString.EMPTYSTRING;
                }
            }
            str2 = strSubstring;
        } else {
            try {
                systemProperty = SecuritySupport.getSystemProperty(str);
                if (systemProperty == null) {
                    return XString.EMPTYSTRING;
                }
            } catch (SecurityException unused2) {
                warn(xPathContext, "WG_SECURITY_EXCEPTION", new Object[]{str});
                return XString.EMPTYSTRING;
            }
        }
        if (!str2.equals("version") || systemProperty.length() <= 0) {
            return new XString(systemProperty);
        }
        try {
            return new XString("1.0");
        } catch (Exception unused3) {
            return new XString(systemProperty);
        }
    }
}
