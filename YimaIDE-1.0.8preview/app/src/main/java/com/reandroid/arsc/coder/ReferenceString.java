package com.reandroid.arsc.coder;

import com.android.tools.r8.DataResource;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.ValueType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ReferenceString {
    public final String name;
    public final String packageName;
    public final String prefix;
    public final String type;

    public ReferenceString(String str, String str2, String str3, String str4) {
        this.prefix = str;
        this.packageName = str2;
        this.type = str3;
        this.name = str4;
    }

    public static EncodeResult encodeReference(PackageBlock packageBlock, String str, EncodeResult encodeResult) throws IOException {
        EncodeResult encodeResultEncodeUnknownResourceId = ValueCoder.encodeUnknownResourceId(str);
        if (encodeResultEncodeUnknownResourceId != null) {
            return encodeResultEncodeUnknownResourceId;
        }
        ReferenceString reference = parseReference(str);
        if (reference == null) {
            return null;
        }
        EncodeResult encodeResultEncode = reference.encode(packageBlock.getTableBlock());
        if (encodeResultEncode != null) {
            return encodeResultEncode;
        }
        if (encodeResult != null) {
            return encodeResult;
        }
        r8g.a("Unknown reference: ", str);
        return null;
    }

    private static boolean isValidResourceName(char c) {
        if (c == '\"' || c == '/' || c == ':' || c == '<' || c == '*' || c == '+') {
            return false;
        }
        switch (c) {
            case '>':
            case '?':
            case '@':
                return false;
            default:
                return true;
        }
    }

    public static ReferenceString parseReference(String str) {
        String strSubstring;
        if (str == null) {
            return null;
        }
        if (str.length() < 2 || str.indexOf(47) < 0 || str.indexOf(32) > 0) {
            return null;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt != '@' && cCharAt != '?') {
            return null;
        }
        int i = str.charAt(1) != '+' ? 1 : 2;
        String strSubstring2 = str.substring(0, i);
        String strSubstring3 = str.substring(i);
        int iIndexOf = strSubstring3.indexOf(58);
        if (iIndexOf > 0) {
            strSubstring = strSubstring3.substring(0, iIndexOf);
            strSubstring3 = strSubstring3.substring(iIndexOf + 1);
        } else {
            strSubstring = null;
        }
        int iIndexOf2 = strSubstring3.indexOf(47);
        if (iIndexOf2 < 0) {
            return null;
        }
        String strSubstring4 = strSubstring3.substring(0, iIndexOf2);
        String strSubstring5 = strSubstring3.substring(iIndexOf2 + 1);
        if (isValidResourceName(strSubstring5)) {
            return new ReferenceString(strSubstring2, strSubstring, strSubstring4, strSubstring5);
        }
        return null;
    }

    public EncodeResult encode(PackageBlock packageBlock, EncodeResult encodeResult) {
        ResourceEntry resource = packageBlock.getTableBlock().getResource(packageBlock, this.packageName, this.type, this.name);
        return resource != null ? new EncodeResult(getValueType(), resource.getResourceId()) : encodeResult;
    }

    public ValueType getValueType() {
        return "?".equals(this.prefix) ? ValueType.ATTRIBUTE : ValueType.REFERENCE;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.prefix;
        if (str != null) {
            sb.append(str);
        }
        String str2 = this.packageName;
        if (str2 != null) {
            sb.append(str2);
            sb.append(':');
        }
        String str3 = this.type;
        if (str3 != null) {
            sb.append(str3);
            sb.append(DataResource.SEPARATOR);
        }
        sb.append(this.name);
        return sb.toString();
    }

    public EncodeResult encode(TableBlock tableBlock, EncodeResult encodeResult) {
        ResourceEntry resource = tableBlock.getResource(this.packageName, this.type, this.name);
        return resource != null ? new EncodeResult(getValueType(), resource.getResourceId()) : encodeResult;
    }

    private static boolean isValidResourceName(String str) {
        char[] charArray = str.toCharArray();
        if (charArray.length == 0) {
            return false;
        }
        for (char c : charArray) {
            if (!isValidResourceName(c)) {
                return false;
            }
        }
        return true;
    }

    public EncodeResult encode(PackageBlock packageBlock) {
        return encode(packageBlock, (EncodeResult) null);
    }

    public EncodeResult encode(TableBlock tableBlock) {
        return encode(tableBlock, (EncodeResult) null);
    }

    public static EncodeResult encodeReference(PackageBlock packageBlock, String str) throws IOException {
        return encodeReference(packageBlock, str, null);
    }
}
