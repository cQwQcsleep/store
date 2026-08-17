package com.sun.org.apache.xml.internal.utils;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
import defpackage.x73;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;
import jdk.xml.internal.JdkConstants;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StylesheetPIHandler extends DefaultHandler {
    String m_baseID;
    String m_charset;
    String m_media;
    List<Source> m_stylesheets = new ArrayList();
    String m_title;
    URIResolver m_uriResolver;

    public StylesheetPIHandler(String str, String str2, String str3, String str4) {
        this.m_baseID = str;
        this.m_media = str2;
        this.m_title = str3;
        this.m_charset = str4;
    }

    public Source getAssociatedStylesheet() {
        int size = this.m_stylesheets.size();
        if (size > 0) {
            return this.m_stylesheets.get(size - 1);
        }
        return null;
    }

    public String getBaseId() {
        return this.m_baseID;
    }

    public URIResolver getURIResolver() {
        return this.m_uriResolver;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        String str3;
        Source sAXSource;
        if (str.equals("xml-stylesheet")) {
            StringTokenizer stringTokenizer = new StringTokenizer(str2, " \t=\n", true);
            String strSubstring = null;
            boolean z = false;
            String strSubstring2 = null;
            String strSubstring3 = null;
            String strSubstring4 = null;
            Source source = null;
            String strNextToken = "";
            String strSubstring5 = null;
            while (stringTokenizer.hasMoreTokens()) {
                if (z) {
                    z = false;
                } else {
                    strNextToken = stringTokenizer.nextToken();
                }
                if (!stringTokenizer.hasMoreTokens() || (!strNextToken.equals(" ") && !strNextToken.equals(TlbBase.TAB) && !strNextToken.equals("="))) {
                    if (strNextToken.equals("type")) {
                        String strNextToken2 = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens() && (strNextToken2.equals(" ") || strNextToken2.equals(TlbBase.TAB) || strNextToken2.equals("="))) {
                            strNextToken2 = stringTokenizer.nextToken();
                        }
                        strNextToken = strNextToken2;
                        strSubstring = strNextToken2.substring(1, strNextToken2.length() - 1);
                    } else if (strNextToken.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_HREF)) {
                        String strNextToken3 = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens() && (strNextToken3.equals(" ") || strNextToken3.equals(TlbBase.TAB) || strNextToken3.equals("="))) {
                            strNextToken3 = stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            String str4 = strNextToken3;
                            String strNextToken4 = stringTokenizer.nextToken();
                            while (strNextToken4.equals("=") && stringTokenizer.hasMoreTokens()) {
                                str4 = str4 + strNextToken4 + stringTokenizer.nextToken();
                                if (!stringTokenizer.hasMoreTokens()) {
                                    break;
                                }
                                strNextToken4 = stringTokenizer.nextToken();
                                z = true;
                            }
                            String str5 = str4;
                            str3 = strNextToken4;
                            strNextToken3 = str5;
                        } else {
                            str3 = strNextToken3;
                        }
                        strSubstring5 = strNextToken3.substring(1, strNextToken3.length() - 1);
                        try {
                            URIResolver uRIResolver = this.m_uriResolver;
                            if (uRIResolver != null) {
                                sAXSource = uRIResolver.resolve(strSubstring5, this.m_baseID);
                            } else {
                                strSubstring5 = SystemIDResolver.getAbsoluteURI(strSubstring5, this.m_baseID);
                                sAXSource = new SAXSource(new InputSource(strSubstring5));
                            }
                            Source source2 = sAXSource;
                            strNextToken = str3;
                            source = source2;
                        } catch (TransformerException e) {
                            x73.a(e);
                            return;
                        }
                    } else if (strNextToken.equals("title")) {
                        String strNextToken5 = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens() && (strNextToken5.equals(" ") || strNextToken5.equals(TlbBase.TAB) || strNextToken5.equals("="))) {
                            strNextToken5 = stringTokenizer.nextToken();
                        }
                        strNextToken = strNextToken5;
                        strSubstring4 = strNextToken5.substring(1, strNextToken5.length() - 1);
                    } else if (strNextToken.equals("media")) {
                        String strNextToken6 = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens() && (strNextToken6.equals(" ") || strNextToken6.equals(TlbBase.TAB) || strNextToken6.equals("="))) {
                            strNextToken6 = stringTokenizer.nextToken();
                        }
                        strNextToken = strNextToken6;
                        strSubstring2 = strNextToken6.substring(1, strNextToken6.length() - 1);
                    } else if (strNextToken.equals("charset")) {
                        String strNextToken7 = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens() && (strNextToken7.equals(" ") || strNextToken7.equals(TlbBase.TAB) || strNextToken7.equals("="))) {
                            strNextToken7 = stringTokenizer.nextToken();
                        }
                        strNextToken = strNextToken7;
                        strSubstring3 = strNextToken7.substring(1, strNextToken7.length() - 1);
                    } else if (strNextToken.equals("alternate")) {
                        strNextToken = stringTokenizer.nextToken();
                        while (stringTokenizer.hasMoreTokens() && (strNextToken.equals(" ") || strNextToken.equals(TlbBase.TAB) || strNextToken.equals("="))) {
                            strNextToken = stringTokenizer.nextToken();
                        }
                        strNextToken.substring(1, strNextToken.length() - 1).equals(JdkConstants.JDK_YES);
                    }
                }
            }
            if (strSubstring != null) {
                if ((strSubstring.equals("text/xsl") || strSubstring.equals("text/xml") || strSubstring.equals("application/xml+xslt")) && strSubstring5 != null) {
                    String str6 = this.m_media;
                    if (str6 == null || (strSubstring2 != null && strSubstring2.equals(str6))) {
                        String str7 = this.m_charset;
                        if (str7 == null || (strSubstring3 != null && strSubstring3.equals(str7))) {
                            String str8 = this.m_title;
                            if (str8 == null || (strSubstring4 != null && strSubstring4.equals(str8))) {
                                this.m_stylesheets.add(source);
                            }
                        }
                    }
                }
            }
        }
    }

    public void setBaseId(String str) {
        this.m_baseID = str;
    }

    public void setURIResolver(URIResolver uRIResolver) {
        this.m_uriResolver = uRIResolver;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        throw new StopParseException();
    }
}
