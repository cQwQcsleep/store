package com.sun.org.apache.xml.internal.serializer;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.serializer.dom3.DOMConstants;
import java.util.Properties;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class OutputPropertiesFactory {
    public static final String S_BUILTIN_EXTENSIONS_UNIVERSAL = "{http://xml.apache.org/xalan}";
    private static final String S_BUILTIN_EXTENSIONS_URL = "http://xml.apache.org/xalan";
    public static final String S_BUILTIN_OLD_EXTENSIONS_UNIVERSAL = "{http://xml.apache.org/xslt}";
    public static final int S_BUILTIN_OLD_EXTENSIONS_UNIVERSAL_LEN = 28;
    private static final String S_BUILTIN_OLD_EXTENSIONS_URL = "http://xml.apache.org/xslt";
    public static final String S_KEY_LINE_SEPARATOR = "{http://xml.apache.org/xalan}line-separator";
    public static final String S_KEY_INDENT_AMOUNT = "{http://xml.apache.org/xalan}indent-amount";
    public static final String S_KEY_CONTENT_HANDLER = "{http://xml.apache.org/xalan}content-handler";
    public static final String S_KEY_ENTITIES = "{http://xml.apache.org/xalan}entities";
    private static final String[] PROP_XML = {Constants.ATTRNAME_OUTPUT_METHOD, "version", "encoding", "indent", "omit-xml-declaration", Constants.ATTRNAME_OUTPUT_STANDALONE, Constants.ATTRNAME_OUTPUT_MEDIATYPE, S_KEY_INDENT_AMOUNT, S_KEY_CONTENT_HANDLER, S_KEY_ENTITIES};
    private static final String[] PROP_XML_VALUE = {"xml", "1.0", "UTF-8", "no", "no", "no", "text/xml", "0", com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.STREAM_XML_OUTPUT, DOMConstants.S_XSL_VALUE_ENTITIES};
    public static final String S_USE_URL_ESCAPING = "{http://xml.apache.org/xalan}use-url-escaping";
    public static final String S_OMIT_META_TAG = "{http://xml.apache.org/xalan}omit-meta-tag";
    private static final String[] PROP_HTML = {Constants.ATTRNAME_OUTPUT_METHOD, "indent", Constants.ATTRNAME_OUTPUT_MEDIATYPE, "version", S_KEY_INDENT_AMOUNT, S_KEY_CONTENT_HANDLER, S_KEY_ENTITIES, S_USE_URL_ESCAPING, S_OMIT_META_TAG};
    private static final String[] PROP_HTML_VALUE = {"html", JdkConstants.JDK_YES, "text/html", "4.0", TlbConst.TYPELIB_MINOR_VERSION_WORD, "com.sun.org.apache.xml.internal.serializer.ToHTMLStream", "com/sun/org/apache/xml/internal/serializer/HTMLEntities", JdkConstants.JDK_YES, "no"};
    private static final String[] PROP_TEXT = {Constants.ATTRNAME_OUTPUT_METHOD, Constants.ATTRNAME_OUTPUT_MEDIATYPE, S_KEY_CONTENT_HANDLER};
    private static final String[] PROP_TEXT_VALUE = {"text", "text/plain", "com.sun.org.apache.xml.internal.serializer.ToTextStream"};
    private static final String[] PROP_UNKNOWN = {Constants.ATTRNAME_OUTPUT_METHOD, "version", "encoding", "indent", "omit-xml-declaration", Constants.ATTRNAME_OUTPUT_STANDALONE, Constants.ATTRNAME_OUTPUT_MEDIATYPE, S_KEY_INDENT_AMOUNT, S_KEY_CONTENT_HANDLER};
    private static final String[] PROP_UNKNOWN_VALUE = {"xml", "1.0", "UTF-8", "no", "no", "no", "text/xml", "0", "com.sun.org.apache.xml.internal.serializer.ToUnknownStream"};
    private static Properties m_xml_properties = null;
    private static Properties m_html_properties = null;
    private static Properties m_text_properties = null;
    private static Properties m_unknown_properties = null;

    public static final Properties getDefaultMethodProperties(String str) {
        Properties properties;
        if (m_xml_properties == null) {
            m_xml_properties = initProperties(PROP_XML, PROP_XML_VALUE, null);
        }
        str.getClass();
        switch (str) {
            case "":
                if (m_unknown_properties == null) {
                    m_unknown_properties = initProperties(PROP_UNKNOWN, PROP_UNKNOWN_VALUE, m_xml_properties);
                }
                properties = m_unknown_properties;
                break;
            case "xml":
                properties = m_xml_properties;
                break;
            case "html":
                if (m_html_properties == null) {
                    m_html_properties = initProperties(PROP_HTML, PROP_HTML_VALUE, m_xml_properties);
                }
                properties = m_html_properties;
                break;
            case "text":
                if (m_text_properties == null) {
                    Properties propertiesInitProperties = initProperties(PROP_TEXT, PROP_TEXT_VALUE, m_xml_properties);
                    m_text_properties = propertiesInitProperties;
                    if (propertiesInitProperties.getProperty("encoding") == null) {
                        m_text_properties.put("encoding", Encodings.getMimeEncoding(null));
                    }
                }
                properties = m_text_properties;
                break;
            default:
                properties = m_xml_properties;
                break;
        }
        return new Properties(properties);
    }

    private static Properties initProperties(String[] strArr, String[] strArr2, Properties properties) {
        Properties properties2 = new Properties(properties);
        for (int i = 0; i < strArr.length; i++) {
            String systemProperty = SecuritySupport.getSystemProperty(strArr[i]);
            String str = strArr[i];
            if (systemProperty == null) {
                systemProperty = strArr2[i];
            }
            properties2.put(str, systemProperty);
        }
        return properties2;
    }
}
