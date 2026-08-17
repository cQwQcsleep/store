package com.sun.org.apache.xerces.internal.xinclude;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;
import com.sun.org.apache.xerces.internal.impl.io.Latin1Reader;
import com.sun.org.apache.xerces.internal.impl.io.UTF16Reader;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.org.apache.xerces.internal.util.EncodingMap;
import com.sun.org.apache.xerces.internal.util.HTTPInputSource;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XIncludeTextReader {
    private XMLErrorReporter fErrorReporter;
    private final XIncludeHandler fHandler;
    private Reader fReader;
    private XMLInputSource fSource;
    private XMLString fTempString;

    public XIncludeTextReader(XMLInputSource xMLInputSource, XIncludeHandler xIncludeHandler, int i) throws IOException {
        this.fTempString = new XMLString();
        this.fHandler = xIncludeHandler;
        this.fSource = xMLInputSource;
        this.fTempString = new XMLString(new char[i + 1], 0, 0);
    }

    private Reader createASCIIReader(InputStream inputStream) {
        return new ASCIIReader(inputStream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createLatin1Reader(InputStream inputStream) {
        return new Latin1Reader(inputStream, this.fTempString.ch.length);
    }

    private Reader createUTF16Reader(InputStream inputStream, boolean z) {
        return new UTF16Reader(inputStream, this.fTempString.ch.length << 1, z, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createUTF8Reader(InputStream inputStream) {
        return new UTF8Reader(inputStream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    public void close() throws IOException {
        Reader reader = this.fReader;
        if (reader != null) {
            reader.close();
            this.fReader = null;
        }
    }

    public String consumeBOM(InputStream inputStream, String str) throws IOException {
        byte[] bArr = new byte[3];
        inputStream.mark(3);
        if (str.equals("UTF-8")) {
            if (inputStream.read(bArr, 0, 3) != 3) {
                inputStream.reset();
                return str;
            }
            int i = bArr[0] & 255;
            int i2 = bArr[1] & 255;
            int i3 = bArr[2] & 255;
            if (i != 239 || i2 != 187 || i3 != 191) {
                inputStream.reset();
                return str;
            }
        } else if (str.startsWith(XMLEntityManager.EncodingInfo.STR_UTF16)) {
            if (inputStream.read(bArr, 0, 2) == 2) {
                int i4 = bArr[0] & 255;
                int i5 = bArr[1] & 255;
                if (i4 == 254 && i5 == 255) {
                    return XMLEntityManager.EncodingInfo.STR_UTF16BE;
                }
                if (i4 == 255 && i5 == 254) {
                    return XMLEntityManager.EncodingInfo.STR_UTF16LE;
                }
            }
            inputStream.reset();
        }
        return str;
    }

    public String getEncodingName(byte[] bArr) {
        int i = bArr[0] & 255;
        int i2 = bArr[1] & 255;
        if (i == 254 && i2 == 255) {
            return XMLEntityManager.EncodingInfo.STR_UTF16BE;
        }
        if (i == 255 && i2 == 254) {
            return XMLEntityManager.EncodingInfo.STR_UTF16LE;
        }
        int i3 = bArr[2] & 255;
        if (i == 239 && i2 == 187 && i3 == 191) {
            return "UTF-8";
        }
        int i4 = bArr[3] & 255;
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 60) {
            return XMLEntityManager.EncodingInfo.STR_UCS4;
        }
        if (i == 60 && i2 == 0 && i3 == 0 && i4 == 0) {
            return XMLEntityManager.EncodingInfo.STR_UCS4;
        }
        if (i == 0 && i2 == 0 && i3 == 60 && i4 == 0) {
            return XMLEntityManager.EncodingInfo.STR_UCS4;
        }
        if (i == 0 && i2 == 60 && i3 == 0 && i4 == 0) {
            return XMLEntityManager.EncodingInfo.STR_UCS4;
        }
        if (i == 0 && i2 == 60 && i3 == 0 && i4 == 63) {
            return XMLEntityManager.EncodingInfo.STR_UTF16BE;
        }
        if (i == 60 && i2 == 0 && i3 == 63 && i4 == 0) {
            return XMLEntityManager.EncodingInfo.STR_UTF16LE;
        }
        if (i == 76 && i2 == 111 && i3 == 167 && i4 == 148) {
            return XMLEntityManager.EncodingInfo.STR_CP037;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0100 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x0103  */
    /* JADX WARN: Code duplicated, block: B:51:0x0106  */
    /* JADX WARN: Code duplicated, block: B:53:0x010e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:55:0x0111  */
    /* JADX WARN: Code duplicated, block: B:56:0x0116  */
    /* JADX WARN: Code duplicated, block: B:58:0x011e  */
    /* JADX WARN: Code duplicated, block: B:59:0x0123  */
    /* JADX WARN: Code duplicated, block: B:61:0x0126  */
    public Reader getReader(XMLInputSource xMLInputSource) throws IOException {
        InputStream bufferedInputStream;
        String strTrim;
        String encodingName;
        if (xMLInputSource.getCharacterStream() != null) {
            return xMLInputSource.getCharacterStream();
        }
        String encoding = xMLInputSource.getEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }
        if (xMLInputSource.getByteStream() != null) {
            bufferedInputStream = xMLInputSource.getByteStream();
            if (!(bufferedInputStream instanceof BufferedInputStream)) {
                bufferedInputStream = new BufferedInputStream(bufferedInputStream, this.fTempString.ch.length);
            }
        } else {
            URLConnection uRLConnectionOpenConnection = new URL(XMLEntityManager.expandSystemId(xMLInputSource.getSystemId(), xMLInputSource.getBaseSystemId(), false)).openConnection();
            if ((uRLConnectionOpenConnection instanceof HttpURLConnection) && (xMLInputSource instanceof HTTPInputSource)) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                HTTPInputSource hTTPInputSource = (HTTPInputSource) xMLInputSource;
                Iterator<Map.Entry<String, String>> hTTPRequestProperties = hTTPInputSource.getHTTPRequestProperties();
                while (hTTPRequestProperties.hasNext()) {
                    Map.Entry<String, String> next = hTTPRequestProperties.next();
                    httpURLConnection.setRequestProperty(next.getKey(), next.getValue());
                }
                boolean followHTTPRedirects = hTTPInputSource.getFollowHTTPRedirects();
                if (!followHTTPRedirects) {
                    httpURLConnection.setInstanceFollowRedirects(followHTTPRedirects);
                }
            }
            bufferedInputStream = new BufferedInputStream(uRLConnectionOpenConnection.getInputStream());
            String contentType = uRLConnectionOpenConnection.getContentType();
            int iIndexOf = contentType != null ? contentType.indexOf(59) : -1;
            if (iIndexOf != -1) {
                strTrim = contentType.substring(0, iIndexOf).trim();
                String strTrim2 = contentType.substring(iIndexOf + 1).trim();
                if (strTrim2.startsWith("charset=")) {
                    encodingName = strTrim2.substring(8).trim();
                    if ((encodingName.charAt(0) == '\"' && encodingName.charAt(encodingName.length() - 1) == '\"') || (encodingName.charAt(0) == '\'' && encodingName.charAt(encodingName.length() - 1) == '\'')) {
                        encodingName = encodingName.substring(1, encodingName.length() - 1);
                    }
                }
                if (strTrim.equals("text/xml")) {
                    if (encodingName == null) {
                        encodingName = "US-ASCII";
                    }
                } else if (strTrim.equals("application/xml")) {
                    if (encodingName == null) {
                        encodingName = getEncodingName(bufferedInputStream);
                    }
                } else if (strTrim.endsWith("+xml")) {
                    encodingName = getEncodingName(bufferedInputStream);
                } else {
                    encodingName = null;
                }
                if (encodingName != null) {
                    encoding = encodingName;
                }
            } else {
                strTrim = contentType != null ? contentType.trim() : "";
            }
            encodingName = null;
            if (strTrim.equals("text/xml")) {
                if (encodingName == null) {
                    encodingName = "US-ASCII";
                }
            } else if (strTrim.equals("application/xml")) {
                if (encodingName == null) {
                    encodingName = getEncodingName(bufferedInputStream);
                }
            } else if (strTrim.endsWith("+xml")) {
                encodingName = getEncodingName(bufferedInputStream);
            } else {
                encodingName = null;
            }
            if (encodingName != null) {
                encoding = encodingName;
            }
        }
        String strConsumeBOM = consumeBOM(bufferedInputStream, encoding.toUpperCase(Locale.ENGLISH));
        if (strConsumeBOM.equals("UTF-8")) {
            return createUTF8Reader(bufferedInputStream);
        }
        if (strConsumeBOM.equals(XMLEntityManager.EncodingInfo.STR_UTF16BE)) {
            return createUTF16Reader(bufferedInputStream, true);
        }
        if (strConsumeBOM.equals(XMLEntityManager.EncodingInfo.STR_UTF16LE)) {
            return createUTF16Reader(bufferedInputStream, false);
        }
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(strConsumeBOM);
        if (iANA2JavaMapping == null) {
            a16.a(this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210").formatMessage(this.fErrorReporter.getLocale(), "EncodingDeclInvalid", new Object[]{strConsumeBOM}));
            return null;
        }
        if (iANA2JavaMapping.equals("ASCII")) {
            return createASCIIReader(bufferedInputStream);
        }
        return iANA2JavaMapping.equals("ISO8859_1") ? createLatin1Reader(bufferedInputStream) : new InputStreamReader(bufferedInputStream, iANA2JavaMapping);
    }

    public boolean isValid(int i) {
        return XMLChar.isValid(i);
    }

    public void parse() throws IOException {
        int i;
        int i2;
        Reader reader = getReader(this.fSource);
        this.fReader = reader;
        this.fSource = null;
        char[] cArr = this.fTempString.ch;
        int i3 = reader.read(cArr, 0, cArr.length - 1);
        this.fHandler.fHasIncludeReportedContent = true;
        while (i3 != -1) {
            int i4 = 0;
            while (i4 < i3) {
                char c = this.fTempString.ch[i4];
                if (!isValid(c)) {
                    if (XMLChar.isHighSurrogate(c)) {
                        i4++;
                        if (i4 < i3) {
                            i2 = this.fTempString.ch[i4];
                        } else {
                            i = this.fReader.read();
                            if (i != -1) {
                                i2 = i;
                                this.fTempString.ch[i3] = (char) i;
                                i3++;
                                i2 = i;
                            }
                        }
                        i2 = i;
                        if (XMLChar.isLowSurrogate(i2)) {
                            int iSupplemental = XMLChar.supplemental(c, (char) i2);
                            if (!isValid(iSupplemental)) {
                                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[]{Integer.toString(iSupplemental, 16)}, (short) 2);
                            }
                        } else {
                            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[]{Integer.toString(i2, 16)}, (short) 2);
                        }
                    } else {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[]{Integer.toString(c, 16)}, (short) 2);
                    }
                }
                i4++;
            }
            XIncludeHandler xIncludeHandler = this.fHandler;
            if (xIncludeHandler != null && i3 > 0) {
                XMLString xMLString = this.fTempString;
                xMLString.offset = 0;
                xMLString.length = i3;
                xIncludeHandler.characters(xMLString, xIncludeHandler.modifyAugmentations(null, true));
            }
            Reader reader2 = this.fReader;
            char[] cArr2 = this.fTempString.ch;
            i3 = reader2.read(cArr2, 0, cArr2.length - 1);
        }
    }

    public void setBufferSize(int i) {
        XMLString xMLString = this.fTempString;
        int i2 = i + 1;
        if (xMLString.ch.length != i2) {
            xMLString.ch = new char[i2];
        }
    }

    public void setErrorReporter(XMLErrorReporter xMLErrorReporter) {
        this.fErrorReporter = xMLErrorReporter;
    }

    public void setInputSource(XMLInputSource xMLInputSource) {
        this.fSource = xMLInputSource;
    }

    public String getEncodingName(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4];
        inputStream.mark(4);
        int i = inputStream.read(bArr, 0, 4);
        inputStream.reset();
        if (i == 4) {
            return getEncodingName(bArr);
        }
        return null;
    }
}
