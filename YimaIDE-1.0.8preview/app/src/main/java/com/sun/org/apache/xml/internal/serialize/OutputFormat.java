package com.sun.org.apache.xml.internal.serialize;

import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class OutputFormat {
    private String[] _cdataElements;
    private String _doctypePublic;
    private String _doctypeSystem;
    private String _mediaType;
    private String _method;
    private String[] _nonEscapingElements;
    private String _version;
    private int _indent = 0;
    private String _encoding = "UTF-8";
    private EncodingInfo _encodingInfo = null;
    private boolean _allowJavaNames = false;
    private boolean _omitXmlDeclaration = false;
    private boolean _omitDoctype = false;
    private boolean _omitComments = false;
    private boolean _stripComments = false;
    private boolean _standalone = false;
    private String _lineSeparator = "\n";
    private int _lineWidth = 72;
    private boolean _preserve = false;
    private boolean _preserveEmptyAttributes = false;

    public static class DTD {
        public static final String HTMLPublicId = "-//W3C//DTD HTML 4.01//EN";
        public static final String HTMLSystemId = "http://www.w3.org/TR/html4/strict.dtd";
        public static final String XHTMLPublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
        public static final String XHTMLSystemId = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
    }

    public static class Defaults {
        public static final String Encoding = "UTF-8";
        public static final int Indent = 4;
        public static final int LineWidth = 72;
    }

    public OutputFormat(String str, String str2, boolean z) {
        setMethod(str);
        setEncoding(str2);
        setIndenting(z);
    }

    public static String whichMediaType(String str) {
        if (str.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        if (str.equalsIgnoreCase("html") || str.equalsIgnoreCase("xhtml")) {
            return "text/html";
        }
        if (str.equalsIgnoreCase("text")) {
            return "text/plain";
        }
        if (str.equalsIgnoreCase(Method.FOP)) {
            return "application/pdf";
        }
        return null;
    }

    public String[] getCDataElements() {
        return this._cdataElements;
    }

    public String getDoctypePublic() {
        return this._doctypePublic;
    }

    public String getDoctypeSystem() {
        return this._doctypeSystem;
    }

    public String getEncoding() {
        return this._encoding;
    }

    public EncodingInfo getEncodingInfo() throws UnsupportedEncodingException {
        if (this._encodingInfo == null) {
            this._encodingInfo = Encodings.getEncodingInfo(this._encoding, this._allowJavaNames);
        }
        return this._encodingInfo;
    }

    public int getIndent() {
        return this._indent;
    }

    public boolean getIndenting() {
        return this._indent > 0;
    }

    public char getLastPrintable() {
        return (getEncoding() == null || !getEncoding().equalsIgnoreCase("ASCII")) ? (char) 65535 : (char) 255;
    }

    public String getLineSeparator() {
        return this._lineSeparator;
    }

    public int getLineWidth() {
        return this._lineWidth;
    }

    public String getMediaType() {
        return this._mediaType;
    }

    public String getMethod() {
        return this._method;
    }

    public String[] getNonEscapingElements() {
        return this._nonEscapingElements;
    }

    public boolean getOmitComments() {
        return this._omitComments;
    }

    public boolean getOmitDocumentType() {
        return this._omitDoctype;
    }

    public boolean getOmitXMLDeclaration() {
        return this._omitXmlDeclaration;
    }

    public boolean getPreserveEmptyAttributes() {
        return this._preserveEmptyAttributes;
    }

    public boolean getPreserveSpace() {
        return this._preserve;
    }

    public boolean getStandalone() {
        return this._standalone;
    }

    public String getVersion() {
        return this._version;
    }

    public boolean isCDataElement(String str) {
        if (this._cdataElements == null) {
            return false;
        }
        int i = 0;
        while (true) {
            String[] strArr = this._cdataElements;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(str)) {
                return true;
            }
            i++;
        }
    }

    public boolean isNonEscapingElement(String str) {
        if (this._nonEscapingElements == null) {
            return false;
        }
        int i = 0;
        while (true) {
            String[] strArr = this._nonEscapingElements;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(str)) {
                return true;
            }
            i++;
        }
    }

    public void setAllowJavaNames(boolean z) {
        this._allowJavaNames = z;
    }

    public void setCDataElements(String[] strArr) {
        this._cdataElements = strArr;
    }

    public void setDoctype(String str, String str2) {
        this._doctypePublic = str;
        this._doctypeSystem = str2;
    }

    public void setEncoding(EncodingInfo encodingInfo) {
        this._encoding = encodingInfo.getIANAName();
        this._encodingInfo = encodingInfo;
    }

    public void setIndent(int i) {
        if (i < 0) {
            this._indent = 0;
        } else {
            this._indent = i;
        }
    }

    public void setIndenting(boolean z) {
        if (z) {
            this._indent = 4;
            this._lineWidth = 72;
        } else {
            this._indent = 0;
            this._lineWidth = 0;
        }
    }

    public void setLineSeparator(String str) {
        if (str == null) {
            this._lineSeparator = "\n";
        } else {
            this._lineSeparator = str;
        }
    }

    public void setLineWidth(int i) {
        if (i <= 0) {
            this._lineWidth = 0;
        } else {
            this._lineWidth = i;
        }
    }

    public void setMediaType(String str) {
        this._mediaType = str;
    }

    public void setMethod(String str) {
        this._method = str;
    }

    public void setNonEscapingElements(String[] strArr) {
        this._nonEscapingElements = strArr;
    }

    public void setOmitComments(boolean z) {
        this._omitComments = z;
    }

    public void setOmitDocumentType(boolean z) {
        this._omitDoctype = z;
    }

    public void setOmitXMLDeclaration(boolean z) {
        this._omitXmlDeclaration = z;
    }

    public void setPreserveEmptyAttributes(boolean z) {
        this._preserveEmptyAttributes = z;
    }

    public void setPreserveSpace(boolean z) {
        this._preserve = z;
    }

    public void setStandalone(boolean z) {
        this._standalone = z;
    }

    public void setVersion(String str) {
        this._version = str;
    }

    public boolean setAllowJavaNames() {
        return this._allowJavaNames;
    }

    public void setEncoding(String str) {
        this._encoding = str;
        this._encodingInfo = null;
    }

    public OutputFormat() {
    }
}
