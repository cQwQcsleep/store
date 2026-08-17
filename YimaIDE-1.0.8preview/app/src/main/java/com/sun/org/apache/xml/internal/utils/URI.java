package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.oye;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class URI implements Serializable {
    private static boolean DEBUG = false;
    private static final String MARK_CHARACTERS = "-_.!~*'() ";
    private static final String RESERVED_CHARACTERS = ";/?:@&=+$,";
    private static final String SCHEME_CHARACTERS = "+-.";
    private static final String USERINFO_CHARACTERS = ";:&=+$,";
    private static final long serialVersionUID = 7096266377907081897L;
    private String m_fragment;
    private String m_host;
    private String m_path;
    private int m_port;
    private String m_queryString;
    private String m_scheme;
    private String m_userinfo;

    public URI(String str, String str2, String str3, int i, String str4, String str5, String str6) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (str == null || str.trim().length() == 0) {
            oye.a(XMLMessages.createXMLMessage("ER_SCHEME_REQUIRED", null));
            throw null;
        }
        if (str3 == null) {
            if (str2 != null) {
                oye.a(XMLMessages.createXMLMessage("ER_NO_USERINFO_IF_NO_HOST", null));
                throw null;
            }
            if (i != -1) {
                oye.a(XMLMessages.createXMLMessage("ER_NO_PORT_IF_NO_HOST", null));
                throw null;
            }
        }
        if (str4 != null) {
            if (str4.indexOf(63) != -1 && str5 != null) {
                oye.a(XMLMessages.createXMLMessage("ER_NO_QUERY_STRING_IN_PATH", null));
                throw null;
            }
            if (str4.indexOf(35) != -1 && str6 != null) {
                oye.a(XMLMessages.createXMLMessage("ER_NO_FRAGMENT_STRING_IN_PATH", null));
                throw null;
            }
        }
        setScheme(str);
        setHost(str3);
        setPort(i);
        setUserinfo(str2);
        setPath(str4);
        setQueryString(str5);
        setFragment(str6);
    }

    private void initialize(URI uri, String str) throws MalformedURIException {
        int i;
        int iLastIndexOf;
        int iLastIndexOf2;
        if (uri == null && (str == null || str.trim().length() == 0)) {
            oye.a(XMLMessages.createXMLMessage("ER_CANNOT_INIT_URI_EMPTY_PARMS", null));
            return;
        }
        if (str == null || str.trim().length() == 0) {
            initialize(uri);
            return;
        }
        String strTrim = str.trim();
        int length = strTrim.length();
        int iIndexOf = strTrim.indexOf(58);
        if (iIndexOf >= 0) {
            initializeScheme(strTrim);
            strTrim = strTrim.substring(iIndexOf + 1);
            if (this.m_scheme != null && uri != null && (strTrim.startsWith(PsuedoNames.PSEUDONAME_ROOT) || !this.m_scheme.equals(uri.m_scheme) || !uri.getSchemeSpecificPart().startsWith(PsuedoNames.PSEUDONAME_ROOT))) {
                uri = null;
            }
            length = strTrim.length();
        } else if (uri == null) {
            oye.a(XMLMessages.createXMLMessage("ER_NO_SCHEME_IN_URI", new Object[]{strTrim}));
            return;
        }
        String strSubstring = "";
        if (1 >= length || !strTrim.substring(0).startsWith("//")) {
            i = 0;
        } else {
            i = 2;
            while (i < length) {
                char cCharAt = strTrim.charAt(i);
                if (cCharAt == '/' || cCharAt == '?' || cCharAt == '#') {
                    break;
                } else {
                    i++;
                }
            }
            if (i > 2) {
                initializeAuthority(strTrim.substring(2, i));
            } else {
                this.m_host = "";
            }
        }
        initializePath(strTrim.substring(i));
        if (uri != null) {
            if (this.m_path.length() == 0 && this.m_scheme == null && this.m_host == null) {
                this.m_scheme = uri.getScheme();
                this.m_userinfo = uri.getUserinfo();
                this.m_host = uri.getHost();
                this.m_port = uri.getPort();
                this.m_path = uri.getPath();
                if (this.m_queryString == null) {
                    this.m_queryString = uri.getQueryString();
                    return;
                }
                return;
            }
            if (this.m_scheme == null) {
                this.m_scheme = uri.getScheme();
            }
            if (this.m_host == null) {
                this.m_userinfo = uri.getUserinfo();
                this.m_host = uri.getHost();
                this.m_port = uri.getPort();
                if (this.m_path.length() <= 0 || !this.m_path.startsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                    String path = uri.getPath();
                    if (path != null && (iLastIndexOf2 = path.lastIndexOf(47)) != -1) {
                        strSubstring = path.substring(0, iLastIndexOf2 + 1);
                    }
                    String strConcat = strSubstring.concat(this.m_path);
                    while (true) {
                        int iIndexOf2 = strConcat.indexOf("/./");
                        if (iIndexOf2 == -1) {
                            break;
                        } else {
                            strConcat = strConcat.substring(0, iIndexOf2 + 1).concat(strConcat.substring(iIndexOf2 + 3));
                        }
                    }
                    if (strConcat.endsWith("/.")) {
                        strConcat = strConcat.substring(0, strConcat.length() - 1);
                    }
                    while (true) {
                        int iIndexOf3 = strConcat.indexOf("/../");
                        if (iIndexOf3 <= 0) {
                            break;
                        }
                        String strSubstring2 = strConcat.substring(0, strConcat.indexOf("/../"));
                        int iLastIndexOf3 = strSubstring2.lastIndexOf(47);
                        if (iLastIndexOf3 != -1) {
                            int i2 = iLastIndexOf3 + 1;
                            if (!strSubstring2.substring(iLastIndexOf3).equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_PARENT)) {
                                strConcat = strConcat.substring(0, i2).concat(strConcat.substring(iIndexOf3 + 4));
                            }
                        }
                    }
                    if (strConcat.endsWith("/..") && (iLastIndexOf = strConcat.substring(0, strConcat.length() - 3).lastIndexOf(47)) != -1) {
                        strConcat = strConcat.substring(0, iLastIndexOf + 1);
                    }
                    this.m_path = strConcat;
                }
            }
        }
    }

    private void initializeAuthority(String str) throws MalformedURIException {
        String strSubstring;
        int i;
        char cCharAt;
        int length = str.length();
        int i2 = -1;
        if (str.indexOf(64, 0) != -1) {
            int i3 = 0;
            cCharAt = 0;
            while (i3 < length) {
                cCharAt = str.charAt(i3);
                if (cCharAt == '@') {
                    break;
                } else {
                    i3++;
                }
            }
            strSubstring = str.substring(0, i3);
            i = i3 + 1;
        } else {
            strSubstring = null;
            i = 0;
            cCharAt = 0;
        }
        int i4 = i;
        while (i4 < length && (cCharAt = str.charAt(i4)) != ':') {
            i4++;
        }
        String strSubstring2 = str.substring(i, i4);
        if (strSubstring2.length() > 0 && cCharAt == ':') {
            int i5 = i4 + 1;
            int i6 = i5;
            while (i6 < length) {
                i6++;
            }
            String strSubstring3 = str.substring(i5, i6);
            if (strSubstring3.length() > 0) {
                for (int i7 = 0; i7 < strSubstring3.length(); i7++) {
                    if (!isDigit(strSubstring3.charAt(i7))) {
                        oye.a(strSubstring3.concat(" is invalid. Port should only contain digits!"));
                        return;
                    }
                }
                try {
                    i2 = Integer.parseInt(strSubstring3);
                } catch (NumberFormatException unused) {
                }
            }
        }
        setHost(strSubstring2);
        setPort(i2);
        setUserinfo(strSubstring);
    }

    private void initializePath(String str) throws MalformedURIException {
        if (str == null) {
            oye.a("Cannot initialize path from null string!");
            return;
        }
        int length = str.length();
        int i = 0;
        char cCharAt = 0;
        while (i < length && (cCharAt = str.charAt(i)) != '?' && cCharAt != '#') {
            if (cCharAt == '%') {
                int i2 = i + 2;
                if (i2 >= length || !isHex(str.charAt(i + 1)) || !isHex(str.charAt(i2))) {
                    oye.a(XMLMessages.createXMLMessage("ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE", null));
                    return;
                }
            } else if (!isReservedCharacter(cCharAt) && !isUnreservedCharacter(cCharAt) && '\\' != cCharAt) {
                oye.a(XMLMessages.createXMLMessage("ER_PATH_INVALID_CHAR", new Object[]{String.valueOf(cCharAt)}));
                return;
            }
            i++;
        }
        this.m_path = str.substring(0, i);
        if (cCharAt == '?') {
            int i3 = i + 1;
            int i4 = i3;
            while (i4 < length) {
                cCharAt = str.charAt(i4);
                if (cCharAt == '#') {
                    break;
                }
                if (cCharAt == '%') {
                    int i5 = i4 + 2;
                    if (i5 >= length || !isHex(str.charAt(i4 + 1)) || !isHex(str.charAt(i5))) {
                        oye.a("Query string contains invalid escape sequence!");
                        return;
                    }
                } else if (!isReservedCharacter(cCharAt) && !isUnreservedCharacter(cCharAt)) {
                    throw new MalformedURIException("Query string contains invalid character:" + cCharAt);
                }
                i4++;
            }
            this.m_queryString = str.substring(i3, i4);
            i = i4;
        }
        if (cCharAt == '#') {
            int i6 = i + 1;
            int i7 = i6;
            while (i7 < length) {
                char cCharAt2 = str.charAt(i7);
                if (cCharAt2 == '%') {
                    int i8 = i7 + 2;
                    if (i8 >= length || !isHex(str.charAt(i7 + 1)) || !isHex(str.charAt(i8))) {
                        oye.a("Fragment contains invalid escape sequence!");
                        return;
                    }
                } else if (!isReservedCharacter(cCharAt2) && !isUnreservedCharacter(cCharAt2)) {
                    throw new MalformedURIException("Fragment contains invalid character:" + cCharAt2);
                }
                i7++;
            }
            this.m_fragment = str.substring(i6, i7);
        }
    }

    private void initializeScheme(String str) throws MalformedURIException {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ':' || cCharAt == '/' || cCharAt == '?' || cCharAt == '#') {
                break;
            } else {
                i++;
            }
        }
        String strSubstring = str.substring(0, i);
        if (strSubstring.length() != 0) {
            setScheme(strSubstring);
        } else {
            oye.a(XMLMessages.createXMLMessage("ER_NO_SCHEME_INURI", null));
        }
    }

    private static boolean isAlpha(char c) {
        if (c < 'a' || c > 'z') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    private static boolean isAlphanum(char c) {
        return isAlpha(c) || isDigit(c);
    }

    public static boolean isConformantSchemeName(String str) {
        if (str == null || str.trim().length() == 0 || !isAlpha(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (!isAlphanum(cCharAt) && SCHEME_CHARACTERS.indexOf(cCharAt) == -1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isHex(char c) {
        if (isDigit(c)) {
            return true;
        }
        if (c < 'a' || c > 'f') {
            return c >= 'A' && c <= 'F';
        }
        return true;
    }

    private static boolean isReservedCharacter(char c) {
        return RESERVED_CHARACTERS.indexOf(c) != -1;
    }

    private static boolean isURIString(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '%') {
                int i2 = i + 2;
                if (i2 >= length || !isHex(str.charAt(i + 1)) || !isHex(str.charAt(i2))) {
                    return false;
                }
                i = i2;
            } else if (!isReservedCharacter(cCharAt) && !isUnreservedCharacter(cCharAt)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean isUnreservedCharacter(char c) {
        return isAlphanum(c) || MARK_CHARACTERS.indexOf(c) != -1;
    }

    public static boolean isWellFormedAddress(String str) {
        String strTrim;
        int length;
        int i;
        if (str == null || (length = (strTrim = str.trim()).length()) == 0 || length > 255 || strTrim.startsWith(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS) || strTrim.startsWith("-")) {
            return false;
        }
        int iLastIndexOf = strTrim.lastIndexOf(46);
        if (strTrim.endsWith(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS)) {
            iLastIndexOf = strTrim.substring(0, iLastIndexOf).lastIndexOf(46);
        }
        int i2 = iLastIndexOf + 1;
        if (i2 >= length || !isDigit(str.charAt(i2))) {
            for (int i3 = 0; i3 < length; i3++) {
                char cCharAt = strTrim.charAt(i3);
                if (cCharAt == '.') {
                    if (!isAlphanum(strTrim.charAt(i3 - 1))) {
                        return false;
                    }
                    int i4 = i3 + 1;
                    if (i4 < length && !isAlphanum(strTrim.charAt(i4))) {
                        return false;
                    }
                } else if (!isAlphanum(cCharAt) && cCharAt != '-') {
                    return false;
                }
            }
        } else {
            int i5 = 0;
            for (int i6 = 0; i6 < length; i6++) {
                char cCharAt2 = strTrim.charAt(i6);
                if (cCharAt2 == '.') {
                    if (!isDigit(strTrim.charAt(i6 - 1)) || ((i = i6 + 1) < length && !isDigit(strTrim.charAt(i)))) {
                        return false;
                    }
                    i5++;
                } else if (!isDigit(cCharAt2)) {
                    return false;
                }
            }
            if (i5 != 3) {
                return false;
            }
        }
        return true;
    }

    public void appendPath(String str) throws MalformedURIException {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        if (!isURIString(str)) {
            oye.a(XMLMessages.createXMLMessage("ER_PATH_INVALID_CHAR", new Object[]{str}));
            return;
        }
        String str2 = this.m_path;
        if (str2 == null || str2.trim().length() == 0) {
            if (str.startsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                this.m_path = str;
                return;
            } else {
                this.m_path = PsuedoNames.PSEUDONAME_ROOT.concat(str);
                return;
            }
        }
        if (this.m_path.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
            boolean zStartsWith = str.startsWith(PsuedoNames.PSEUDONAME_ROOT);
            String str3 = this.m_path;
            if (zStartsWith) {
                this.m_path = str3.concat(str.substring(1));
                return;
            } else {
                this.m_path = str3.concat(str);
                return;
            }
        }
        boolean zStartsWith2 = str.startsWith(PsuedoNames.PSEUDONAME_ROOT);
        String str4 = this.m_path;
        if (zStartsWith2) {
            this.m_path = str4.concat(str);
        } else {
            this.m_path = str4.concat(PsuedoNames.PSEUDONAME_ROOT.concat(str));
        }
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (!(obj instanceof URI)) {
            return false;
        }
        URI uri = (URI) obj;
        String str7 = this.m_scheme;
        if (!(str7 == null && uri.m_scheme == null) && (str7 == null || (str = uri.m_scheme) == null || !str7.equals(str))) {
            return false;
        }
        String str8 = this.m_userinfo;
        if (!(str8 == null && uri.m_userinfo == null) && (str8 == null || (str2 = uri.m_userinfo) == null || !str8.equals(str2))) {
            return false;
        }
        String str9 = this.m_host;
        if ((!(str9 == null && uri.m_host == null) && (str9 == null || (str3 = uri.m_host) == null || !str9.equals(str3))) || this.m_port != uri.m_port) {
            return false;
        }
        String str10 = this.m_path;
        if (!(str10 == null && uri.m_path == null) && (str10 == null || (str4 = uri.m_path) == null || !str10.equals(str4))) {
            return false;
        }
        String str11 = this.m_queryString;
        if (!(str11 == null && uri.m_queryString == null) && (str11 == null || (str5 = uri.m_queryString) == null || !str11.equals(str5))) {
            return false;
        }
        String str12 = this.m_fragment;
        if (str12 == null && uri.m_fragment == null) {
            return true;
        }
        return (str12 == null || (str6 = uri.m_fragment) == null || !str12.equals(str6)) ? false : true;
    }

    public String getFragment() {
        return this.m_fragment;
    }

    public String getHost() {
        return this.m_host;
    }

    public String getPath(boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder(this.m_path);
        if (z && this.m_queryString != null) {
            sb.append('?');
            sb.append(this.m_queryString);
        }
        if (z2 && this.m_fragment != null) {
            sb.append('#');
            sb.append(this.m_fragment);
        }
        return sb.toString();
    }

    public int getPort() {
        return this.m_port;
    }

    public String getQueryString() {
        return this.m_queryString;
    }

    public String getScheme() {
        return this.m_scheme;
    }

    public String getSchemeSpecificPart() {
        StringBuilder sb = new StringBuilder();
        if (this.m_userinfo != null || this.m_host != null || this.m_port != -1) {
            sb.append("//");
        }
        String str = this.m_userinfo;
        if (str != null) {
            sb.append(str);
            sb.append('@');
        }
        String str2 = this.m_host;
        if (str2 != null) {
            sb.append(str2);
        }
        if (this.m_port != -1) {
            sb.append(':');
            sb.append(this.m_port);
        }
        String str3 = this.m_path;
        if (str3 != null) {
            sb.append(str3);
        }
        if (this.m_queryString != null) {
            sb.append('?');
            sb.append(this.m_queryString);
        }
        if (this.m_fragment != null) {
            sb.append('#');
            sb.append(this.m_fragment);
        }
        return sb.toString();
    }

    public String getUserinfo() {
        return this.m_userinfo;
    }

    public int hashCode() {
        return ((((((((((((413 + Objects.hashCode(this.m_scheme)) * 59) + Objects.hashCode(this.m_userinfo)) * 59) + Objects.hashCode(this.m_host)) * 59) + this.m_port) * 59) + Objects.hashCode(this.m_path)) * 59) + Objects.hashCode(this.m_queryString)) * 59) + Objects.hashCode(this.m_fragment);
    }

    public boolean isGenericURI() {
        return this.m_host != null;
    }

    public void setFragment(String str) throws MalformedURIException {
        if (str == null) {
            this.m_fragment = null;
            return;
        }
        if (!isGenericURI()) {
            oye.a(XMLMessages.createXMLMessage("ER_FRAG_FOR_GENERIC_URI", null));
            return;
        }
        if (getPath() == null) {
            oye.a(XMLMessages.createXMLMessage("ER_FRAG_WHEN_PATH_NULL", null));
        } else if (isURIString(str)) {
            this.m_fragment = str;
        } else {
            oye.a(XMLMessages.createXMLMessage("ER_FRAG_INVALID_CHAR", null));
        }
    }

    public void setHost(String str) throws MalformedURIException {
        if (str == null || str.trim().length() == 0) {
            this.m_host = str;
            this.m_userinfo = null;
            this.m_port = -1;
        } else if (!isWellFormedAddress(str)) {
            oye.a(XMLMessages.createXMLMessage("ER_HOST_ADDRESS_NOT_WELLFORMED", null));
            return;
        }
        this.m_host = str;
    }

    public void setPath(String str) throws MalformedURIException {
        if (str != null) {
            initializePath(str);
            return;
        }
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
    }

    public void setPort(int i) throws MalformedURIException {
        if (i < 0 || i > 65535) {
            if (i != -1) {
                oye.a(XMLMessages.createXMLMessage("ER_INVALID_PORT", null));
                return;
            }
        } else if (this.m_host == null) {
            oye.a(XMLMessages.createXMLMessage("ER_PORT_WHEN_HOST_NULL", null));
            return;
        }
        this.m_port = i;
    }

    public void setQueryString(String str) throws MalformedURIException {
        if (str == null) {
            this.m_queryString = null;
            return;
        }
        if (!isGenericURI()) {
            oye.a("Query string can only be set for a generic URI!");
            return;
        }
        if (getPath() == null) {
            oye.a("Query string cannot be set when path is null!");
        } else if (isURIString(str)) {
            this.m_queryString = str;
        } else {
            oye.a("Query string contains invalid character!");
        }
    }

    public void setScheme(String str) throws MalformedURIException {
        if (str == null) {
            oye.a(XMLMessages.createXMLMessage("ER_SCHEME_FROM_NULL_STRING", null));
        } else if (isConformantSchemeName(str)) {
            this.m_scheme = str.toLowerCase();
        } else {
            oye.a(XMLMessages.createXMLMessage("ER_SCHEME_NOT_CONFORMANT", null));
        }
    }

    public void setUserinfo(String str) throws MalformedURIException {
        if (str == null) {
            this.m_userinfo = null;
        } else {
            if (this.m_host == null) {
                oye.a("Userinfo cannot be set when host is null!");
                return;
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '%') {
                    int i2 = i + 2;
                    if (i2 >= length || !isHex(str.charAt(i + 1)) || !isHex(str.charAt(i2))) {
                        oye.a("Userinfo contains invalid escape sequence!");
                        return;
                    }
                } else if (!isUnreservedCharacter(cCharAt) && USERINFO_CHARACTERS.indexOf(cCharAt) == -1) {
                    throw new MalformedURIException("Userinfo contains invalid character:" + cCharAt);
                }
            }
        }
        this.m_userinfo = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.m_scheme;
        if (str != null) {
            sb.append(str);
            sb.append(':');
        }
        sb.append(getSchemeSpecificPart());
        return sb.toString();
    }

    public static class MalformedURIException extends IOException {
        private static final long serialVersionUID = -8498313684991136829L;

        public MalformedURIException() {
        }

        public MalformedURIException(String str) {
            super(str);
        }
    }

    public String getPath() {
        return this.m_path;
    }

    public URI(URI uri) {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        initialize(uri);
    }

    public URI(String str) throws MalformedURIException {
        this((URI) null, str);
    }

    public URI(URI uri, String str) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        initialize(uri, str);
    }

    public URI(String str, String str2) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (str != null && str.trim().length() != 0) {
            if (str2 != null && str2.trim().length() != 0) {
                setScheme(str);
                setPath(str2);
                return;
            } else {
                oye.a("Cannot construct URI with null/empty scheme-specific part!");
                throw null;
            }
        }
        oye.a("Cannot construct URI with null/empty scheme!");
        throw null;
    }

    public URI(String str, String str2, String str3, String str4, String str5) throws MalformedURIException {
        this(str, null, str2, -1, str3, str4, str5);
    }

    public URI() {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
    }

    private void initialize(URI uri) {
        this.m_scheme = uri.getScheme();
        this.m_userinfo = uri.getUserinfo();
        this.m_host = uri.getHost();
        this.m_port = uri.getPort();
        this.m_path = uri.getPath();
        this.m_queryString = uri.getQueryString();
        this.m_fragment = uri.getFragment();
    }
}
