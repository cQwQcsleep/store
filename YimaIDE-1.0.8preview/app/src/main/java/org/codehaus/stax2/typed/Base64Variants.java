package org.codehaus.stax2.typed;

import org.eclipse.jdt.internal.compiler.lookup.TypeIds;
import org.eclipse.jdt.internal.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class Base64Variants {
    public static final Base64Variant MIME;
    public static final Base64Variant MIME_NO_LINEFEEDS;
    public static final Base64Variant MODIFIED_FOR_URL;
    public static final Base64Variant PEM;
    static final String STD_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    static {
        Base64Variant base64Variant = new Base64Variant("MIME", STD_BASE64_ALPHABET, true, '=', 76);
        MIME = base64Variant;
        MIME_NO_LINEFEEDS = new Base64Variant(base64Variant, "MIME-NO-LINEFEEDS", TypeIds.NoId);
        PEM = new Base64Variant(base64Variant, "PEM", true, '=', 64);
        StringBuilder sb = new StringBuilder(STD_BASE64_ALPHABET);
        sb.setCharAt(sb.indexOf("+"), Util.C_SUPER);
        sb.setCharAt(sb.indexOf("/"), '_');
        MODIFIED_FOR_URL = new Base64Variant("MODIFIED-FOR-URL", sb.toString(), false, (char) 0, TypeIds.NoId);
    }

    public static Base64Variant getDefaultVariant() {
        return MIME;
    }
}
