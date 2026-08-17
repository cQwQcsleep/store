package com.sun.org.apache.xml.internal.serializer;

import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class CharInfo {
    private static final int ASCII_MAX = 128;
    public static final String HTML_ENTITIES_RESOURCE = "com.sun.org.apache.xml.internal.serializer.HTMLEntities";
    private static final int LOW_ORDER_BITMASK = 31;
    private static final int SHIFT_PER_WORD = 5;
    public static final char S_CARRIAGERETURN = '\r';
    public static final char S_HORIZONAL_TAB = '\t';
    public static final char S_LINEFEED = '\n';
    public static final String XML_ENTITIES_RESOURCE = "com.sun.org.apache.xml.internal.serializer.XMLEntities";
    private static Map<String, CharInfo> m_getCharInfoCache = new HashMap();
    private int[] array_of_bits;
    private int firstWordNotUsed;
    private boolean[] isCleanTextASCII;
    private boolean[] isSpecialAttrASCII;
    private boolean[] isSpecialTextASCII;
    private Map<CharKey, String> m_charToString;
    final boolean onlyQuotAmpLtGt;

    /* JADX WARN: Code restructure failed: missing block: B:109:0x003d, code lost:
    
        r0 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private CharInfo(String str, String str2, boolean z) {
        ResourceBundle bundle;
        String message;
        BufferedReader bufferedReader;
        boolean z2;
        this.m_charToString = new HashMap();
        this.isSpecialAttrASCII = new boolean[128];
        this.isSpecialTextASCII = new boolean[128];
        this.isCleanTextASCII = new boolean[128];
        this.array_of_bits = createEmptySetOfIntegers(65535);
        InputStream resourceAsStream = null;
        try {
            if (z) {
                bundle = ResourceBundle.getBundle(str);
            } else {
                ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
                bundle = contextClassLoader != null ? ResourceBundle.getBundle(str, Locale.getDefault(), contextClassLoader) : null;
            }
        } catch (Exception unused) {
        }
        if (bundle != null) {
            Enumeration<String> keys = bundle.getKeys();
            z2 = true;
            while (keys.hasMoreElements()) {
                String strNextElement = keys.nextElement();
                int i = Integer.parseInt(bundle.getString(strNextElement));
                defineEntity(strNextElement, (char) i);
                if (extraEntity(i)) {
                    z2 = false;
                }
            }
            set(10);
            set(13);
        } else {
            try {
                try {
                    if (z) {
                        resourceAsStream = CharInfo.class.getResourceAsStream(str);
                        message = null;
                    } else {
                        ClassLoader contextClassLoader2 = SecuritySupport.getContextClassLoader();
                        if (contextClassLoader2 != null) {
                            try {
                                resourceAsStream = contextClassLoader2.getResourceAsStream(str);
                                message = null;
                            } catch (Exception e) {
                                message = e.getMessage();
                            }
                        } else {
                            message = null;
                        }
                        if (resourceAsStream == null) {
                            try {
                                resourceAsStream = new URL(str).openStream();
                            } catch (Exception e2) {
                                message = e2.getMessage();
                            }
                        }
                    }
                    if (resourceAsStream == null) {
                        throw new RuntimeException(Utils.messages.createMessage("ER_RESOURCE_COULD_NOT_FIND", new Object[]{str, message}));
                    }
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));
                    } catch (UnsupportedEncodingException unused2) {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                    }
                    String line = bufferedReader.readLine();
                    boolean z3 = true;
                    while (line != null) {
                        if (line.length() == 0 || line.charAt(0) == '#') {
                            line = bufferedReader.readLine();
                        } else {
                            int iIndexOf = line.indexOf(32);
                            if (iIndexOf > 1) {
                                String strSubstring = line.substring(0, iIndexOf);
                                int i2 = iIndexOf + 1;
                                if (i2 < line.length()) {
                                    String strSubstring2 = line.substring(i2);
                                    int iIndexOf2 = strSubstring2.indexOf(32);
                                    int i3 = Integer.parseInt(iIndexOf2 > 0 ? strSubstring2.substring(0, iIndexOf2) : strSubstring2);
                                    defineEntity(strSubstring, (char) i3);
                                    if (extraEntity(i3)) {
                                        z3 = false;
                                    }
                                }
                            }
                            line = bufferedReader.readLine();
                        }
                    }
                    resourceAsStream.close();
                    set(10);
                    set(13);
                    try {
                        resourceAsStream.close();
                    } catch (Exception unused3) {
                    }
                    z2 = z3;
                } catch (Throwable th) {
                    if (0 != 0) {
                        try {
                            resourceAsStream.close();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                throw new RuntimeException(Utils.messages.createMessage("ER_RESOURCE_COULD_NOT_LOAD", new Object[]{str, e3.toString(), str, e3.toString()}));
            }
        }
        for (int i4 = 0; i4 < 128; i4++) {
            if (((32 <= i4 || 10 == i4 || 13 == i4 || 9 == i4) && !get(i4)) || 34 == i4) {
                this.isCleanTextASCII[i4] = true;
                this.isSpecialTextASCII[i4] = false;
            } else {
                this.isCleanTextASCII[i4] = false;
                this.isSpecialTextASCII[i4] = true;
            }
        }
        this.onlyQuotAmpLtGt = z2;
        for (int i5 = 0; i5 < 128; i5++) {
            this.isSpecialAttrASCII[i5] = get(i5);
        }
        if ("xml".equals(str2)) {
            this.isSpecialAttrASCII[9] = true;
        }
    }

    private static int arrayIndex(int i) {
        return i >> 5;
    }

    private static int bit(int i) {
        return 1 << (i & 31);
    }

    private int[] createEmptySetOfIntegers(int i) {
        this.firstWordNotUsed = 0;
        return new int[arrayIndex(i - 1) + 1];
    }

    private void defineChar2StringMapping(String str, char c) {
        this.m_charToString.put(new CharKey(c), str);
        set(c);
    }

    private void defineEntity(String str, char c) {
        defineChar2StringMapping("&" + str + ';', c);
    }

    private boolean extraEntity(int i) {
        return (i >= 128 || i == 34 || i == 38 || i == 60 || i == 62) ? false : true;
    }

    private final boolean get(int i) {
        int i2 = i >> 5;
        return i2 < this.firstWordNotUsed && (this.array_of_bits[i2] & (1 << (i & 31))) != 0;
    }

    public static CharInfo getCharInfo(String str, String str2) {
        String absoluteURI;
        try {
            return new CharInfo(str, str2, false);
        } catch (Exception unused) {
            if (str.indexOf(58) < 0) {
                absoluteURI = SystemIDResolver.getAbsoluteURIFromRelative(str);
            } else {
                try {
                    absoluteURI = SystemIDResolver.getAbsoluteURI(str, null);
                } catch (TransformerException e) {
                    throw new WrappedRuntimeException(e);
                }
            }
            return new CharInfo(absoluteURI, str2, false);
        }
    }

    public static CharInfo getCharInfoInternal(String str, String str2) {
        CharInfo charInfo = m_getCharInfoCache.get(str);
        if (charInfo != null) {
            return charInfo;
        }
        CharInfo charInfo2 = new CharInfo(str, str2, true);
        m_getCharInfoCache.put(str, charInfo2);
        return charInfo2;
    }

    private final void set(int i) {
        setASCIIdirty(i);
        int i2 = i >> 5;
        int i3 = i2 + 1;
        if (this.firstWordNotUsed < i3) {
            this.firstWordNotUsed = i3;
        }
        int[] iArr = this.array_of_bits;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    private void setASCIIclean(int i) {
        if (i < 0 || i >= 128) {
            return;
        }
        this.isCleanTextASCII[i] = true;
        this.isSpecialTextASCII[i] = false;
    }

    private void setASCIIdirty(int i) {
        if (i < 0 || i >= 128) {
            return;
        }
        this.isCleanTextASCII[i] = false;
        this.isSpecialTextASCII[i] = true;
    }

    public String getOutputStringForChar(char c) {
        CharKey charKey = new CharKey();
        charKey.setChar(c);
        return this.m_charToString.get(charKey);
    }

    public final boolean isSpecialAttrChar(int i) {
        return i < 128 ? this.isSpecialAttrASCII[i] : get(i);
    }

    public final boolean isSpecialTextChar(int i) {
        return i < 128 ? this.isSpecialTextASCII[i] : get(i);
    }

    public final boolean isTextASCIIClean(int i) {
        return this.isCleanTextASCII[i];
    }

    public static class CharKey {
        private char m_char;

        public CharKey(char c) {
            this.m_char = c;
        }

        public final boolean equals(Object obj) {
            return ((CharKey) obj).m_char == this.m_char;
        }

        public final int hashCode() {
            return this.m_char;
        }

        public final void setChar(char c) {
            this.m_char = c;
        }

        public CharKey() {
        }
    }

    private CharInfo(String str, String str2) {
        this(str, str2, false);
    }
}
