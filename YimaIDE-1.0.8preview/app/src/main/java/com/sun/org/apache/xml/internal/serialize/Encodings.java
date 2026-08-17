package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.util.EncodingMap;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
class Encodings {
    static final int DEFAULT_LAST_PRINTABLE = 127;
    static final String JIS_DANGER_CHARS = "\\~\u007f¢£¥¬—―‖…‾‾∥∯〜＼～￠￡￢￣";
    static final int LAST_PRINTABLE_UNICODE = 65535;
    static final String DEFAULT_ENCODING = "UTF8";
    static final String[] UNICODE_ENCODINGS = {"Unicode", "UnicodeBig", "UnicodeLittle", "GB2312", DEFAULT_ENCODING, XMLEntityManager.EncodingInfo.STR_UTF16};
    private static final Map<String, EncodingInfo> _encodings = new ConcurrentHashMap();

    public static EncodingInfo getEncodingInfo(String str, boolean z) throws UnsupportedEncodingException {
        String[] strArr;
        String[] strArr2;
        if (str == null) {
            Map<String, EncodingInfo> map = _encodings;
            EncodingInfo encodingInfo = map.get(DEFAULT_ENCODING);
            if (encodingInfo != null) {
                return encodingInfo;
            }
            EncodingInfo encodingInfo2 = new EncodingInfo(EncodingMap.getJava2IANAMapping(DEFAULT_ENCODING), DEFAULT_ENCODING, 65535);
            map.put(DEFAULT_ENCODING, encodingInfo2);
            return encodingInfo2;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(upperCase);
        int i = 0;
        if (iANA2JavaMapping != null) {
            EncodingInfo encodingInfo3 = _encodings.get(iANA2JavaMapping);
            if (encodingInfo3 != null) {
                return encodingInfo3;
            }
            while (true) {
                strArr = UNICODE_ENCODINGS;
                if (i >= strArr.length) {
                    break;
                }
                if (strArr[i].equalsIgnoreCase(iANA2JavaMapping)) {
                    encodingInfo3 = new EncodingInfo(upperCase, iANA2JavaMapping, 65535);
                    break;
                }
                i++;
            }
            if (i == strArr.length) {
                encodingInfo3 = new EncodingInfo(upperCase, iANA2JavaMapping, 127);
            }
            _encodings.put(iANA2JavaMapping, encodingInfo3);
            return encodingInfo3;
        }
        if (!z) {
            throw new UnsupportedEncodingException(upperCase);
        }
        EncodingInfo.testJavaEncodingName(upperCase);
        EncodingInfo encodingInfo4 = _encodings.get(upperCase);
        if (encodingInfo4 != null) {
            return encodingInfo4;
        }
        while (true) {
            strArr2 = UNICODE_ENCODINGS;
            if (i >= strArr2.length) {
                break;
            }
            if (strArr2[i].equalsIgnoreCase(upperCase)) {
                encodingInfo4 = new EncodingInfo(EncodingMap.getJava2IANAMapping(upperCase), upperCase, 65535);
                break;
            }
            i++;
        }
        if (i == strArr2.length) {
            encodingInfo4 = new EncodingInfo(EncodingMap.getJava2IANAMapping(upperCase), upperCase, 127);
        }
        _encodings.put(upperCase, encodingInfo4);
        return encodingInfo4;
    }
}
