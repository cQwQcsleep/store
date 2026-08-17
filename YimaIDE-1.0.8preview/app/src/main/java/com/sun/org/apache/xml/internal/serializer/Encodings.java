package com.sun.org.apache.xml.internal.serializer;

import com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Encodings {
    static final String DEFAULT_MIME_ENCODING = "UTF-8";
    private static final String ENCODINGS_FILE = "com/sun/org/apache/xml/internal/serializer/Encodings.properties";
    private static final String ENCODINGS_PROP = "com.sun.org.apache.xalan.internal.serialize.encodings";
    private static final EncodingInfos _encodingInfos = new EncodingInfos();
    private static final int m_defaultLastPrintable = 127;

    private static String convertJava2MimeEncoding(String str) {
        EncodingInfo encodingFromJavaKey = _encodingInfos.getEncodingFromJavaKey(toUpperCaseFast(str));
        return encodingFromJavaKey != null ? encodingFromJavaKey.name : str;
    }

    public static String convertMime2JavaEncoding(String str) {
        EncodingInfo encodingInfoFindEncoding = _encodingInfos.findEncoding(toUpperCaseFast(str));
        return encodingInfoFindEncoding != null ? encodingInfoFindEncoding.javaName : str;
    }

    public static EncodingInfo getEncodingInfo(String str) {
        String upperCaseFast = toUpperCaseFast(str);
        EncodingInfos encodingInfos = _encodingInfos;
        EncodingInfo encodingInfoFindEncoding = encodingInfos.findEncoding(upperCaseFast);
        if (encodingInfoFindEncoding != null) {
            return encodingInfoFindEncoding;
        }
        try {
            String strName = Charset.forName(str).name();
            EncodingInfo encodingInfo = new EncodingInfo(strName, strName);
            encodingInfos.putEncoding(upperCaseFast, encodingInfo);
            return encodingInfo;
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            return new EncodingInfo(null, null);
        }
    }

    public static int getLastPrintable() {
        return 127;
    }

    public static String getMimeEncoding(String str) {
        if (str != null) {
            return convertJava2MimeEncoding(str);
        }
        try {
            String systemProperty = SecuritySupport.getSystemProperty("file.encoding", "UTF8");
            if (systemProperty != null) {
                String strConvertJava2MimeEncoding = (systemProperty.equalsIgnoreCase("Cp1252") || systemProperty.equalsIgnoreCase("ISO8859_1") || systemProperty.equalsIgnoreCase("8859_1") || systemProperty.equalsIgnoreCase("UTF8")) ? "UTF-8" : convertJava2MimeEncoding(systemProperty);
                if (strConvertJava2MimeEncoding != null) {
                    return strConvertJava2MimeEncoding;
                }
            }
        } catch (SecurityException unused) {
        }
        return "UTF-8";
    }

    public static Writer getWriter(OutputStream outputStream, String str) throws UnsupportedEncodingException {
        EncodingInfo encodingInfoFindEncoding = _encodingInfos.findEncoding(toUpperCaseFast(str));
        if (encodingInfoFindEncoding != null) {
            try {
                return new BufferedWriter(new OutputStreamWriter(outputStream, encodingInfoFindEncoding.javaName));
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new BufferedWriter(new OutputStreamWriter(outputStream, str));
    }

    public static boolean isHighUTF16Surrogate(char c) {
        return 55296 <= c && c <= 56319;
    }

    public static boolean isLowUTF16Surrogate(char c) {
        return 56320 <= c && c <= 57343;
    }

    public static boolean isRecognizedEncoding(String str) {
        return _encodingInfos.findEncoding(toUpperCaseFast(str)) != null;
    }

    public static int toCodePoint(char c, char c2) {
        return ((c - 55296) << 10) + (c2 - 56320) + 65536;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String toUpperCaseFast(String str) {
        int length = str.length();
        char[] cArr = new char[length];
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ('a' <= cCharAt && cCharAt <= 'z') {
                cCharAt = (char) (cCharAt - ' ');
                z = true;
            }
            cArr[i] = cCharAt;
        }
        return z ? String.valueOf(cArr) : str;
    }

    public static int toCodePoint(char c) {
        return c;
    }

    public static final class EncodingInfos {
        private final Map<String, EncodingInfo> _encodingDynamicTable;
        private final Map<String, EncodingInfo> _encodingTableKeyJava;
        private final Map<String, EncodingInfo> _encodingTableKeyMime;

        private EncodingInfos() {
            this._encodingTableKeyJava = new HashMap();
            this._encodingTableKeyMime = new HashMap();
            this._encodingDynamicTable = Collections.synchronizedMap(new HashMap());
            loadEncodingInfo();
        }

        private String findCharsetNameFor(String str, String[] strArr) {
            String strFindCharsetNameFor = findCharsetNameFor(str);
            if (strFindCharsetNameFor != null) {
                return str;
            }
            for (String str2 : strArr) {
                strFindCharsetNameFor = findCharsetNameFor(str2);
                if (strFindCharsetNameFor != null) {
                    return strFindCharsetNameFor;
                }
            }
            return strFindCharsetNameFor;
        }

        private void loadEncodingInfo() {
            try {
                Properties propertiesLoadProperties = loadProperties();
                Enumeration enumerationKeys = propertiesLoadProperties.keys();
                HashMap map = new HashMap();
                while (enumerationKeys.hasMoreElements()) {
                    String str = (String) enumerationKeys.nextElement();
                    String[] mimeTypes = parseMimeTypes(propertiesLoadProperties.getProperty(str));
                    String strFindCharsetNameFor = findCharsetNameFor(str, mimeTypes);
                    if (strFindCharsetNameFor != null) {
                        String upperCaseFast = Encodings.toUpperCaseFast(str);
                        String upperCaseFast2 = Encodings.toUpperCaseFast(strFindCharsetNameFor);
                        for (String str2 : mimeTypes) {
                            String upperCaseFast3 = Encodings.toUpperCaseFast(str2);
                            EncodingInfo encodingInfo = new EncodingInfo(str2, strFindCharsetNameFor);
                            this._encodingTableKeyMime.put(upperCaseFast3, encodingInfo);
                            if (!map.containsKey(upperCaseFast2)) {
                                map.put(upperCaseFast2, encodingInfo);
                                this._encodingTableKeyJava.put(upperCaseFast2, encodingInfo);
                            }
                            this._encodingTableKeyJava.put(upperCaseFast, encodingInfo);
                        }
                    }
                }
                for (Map.Entry<String, EncodingInfo> entry : this._encodingTableKeyJava.entrySet()) {
                    entry.setValue((EncodingInfo) map.get(Encodings.toUpperCaseFast(entry.getValue().javaName)));
                }
            } catch (MalformedURLException e) {
                throw new WrappedRuntimeException(e);
            } catch (IOException e2) {
                throw new WrappedRuntimeException(e2);
            }
        }

        private Properties loadProperties() throws IOException {
            Properties properties = new Properties();
            InputStream inputStreamOpenEncodingsFileStream = openEncodingsFileStream();
            if (inputStreamOpenEncodingsFileStream != null) {
                try {
                    properties.load(inputStreamOpenEncodingsFileStream);
                } catch (Throwable th) {
                    try {
                        inputStreamOpenEncodingsFileStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            if (inputStreamOpenEncodingsFileStream != null) {
                inputStreamOpenEncodingsFileStream.close();
            }
            return properties;
        }

        private InputStream openEncodingsFileStream() throws IOException {
            String systemProperty;
            InputStream inputStreamOpenStream = null;
            try {
                systemProperty = SecuritySupport.getSystemProperty(Encodings.ENCODINGS_PROP, "");
            } catch (SecurityException unused) {
                systemProperty = null;
            }
            if (systemProperty != null && systemProperty.length() > 0) {
                inputStreamOpenStream = new URL(systemProperty).openStream();
            }
            return inputStreamOpenStream == null ? SecuritySupport.getResourceAsStream(Encodings.ENCODINGS_FILE) : inputStreamOpenStream;
        }

        private String[] parseMimeTypes(String str) {
            int iIndexOf = str.indexOf(32);
            if (iIndexOf < 0) {
                return new String[]{str};
            }
            int i = 0;
            StringTokenizer stringTokenizer = new StringTokenizer(str.substring(0, iIndexOf), ",");
            String[] strArr = new String[stringTokenizer.countTokens()];
            while (stringTokenizer.hasMoreTokens()) {
                strArr[i] = stringTokenizer.nextToken();
                i++;
            }
            return strArr;
        }

        public EncodingInfo findEncoding(String str) {
            EncodingInfo encodingInfo = this._encodingTableKeyJava.get(str);
            if (encodingInfo == null) {
                encodingInfo = this._encodingTableKeyMime.get(str);
            }
            return encodingInfo == null ? this._encodingDynamicTable.get(str) : encodingInfo;
        }

        public EncodingInfo getEncodingFromJavaKey(String str) {
            return this._encodingTableKeyJava.get(str);
        }

        public EncodingInfo getEncodingFromMimeKey(String str) {
            return this._encodingTableKeyMime.get(str);
        }

        public void putEncoding(String str, EncodingInfo encodingInfo) {
            this._encodingDynamicTable.put(str, encodingInfo);
        }

        private String findCharsetNameFor(String str) {
            try {
                return Charset.forName(str).name();
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
