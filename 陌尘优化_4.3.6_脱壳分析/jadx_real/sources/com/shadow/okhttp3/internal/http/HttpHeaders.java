package com.shadow.okhttp3.internal.http;

import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.Challenge;
import com.shadow.okhttp3.Cookie;
import com.shadow.okhttp3.CookieJar;
import com.shadow.okhttp3.Headers;
import com.shadow.okhttp3.HttpUrl;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.internal.Util;
import com.shadow.okhttp3.internal.platform.Platform;
import com.shadow.okio.Buffer;
import com.shadow.okio.ByteString;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS;
    private static final ByteString TOKEN_DELIMITERS;

    static {
        ByteString.Companion companion = ByteString.Companion;
        QUOTED_STRING_DELIMITERS = companion.encodeUtf8("\"\\");
        TOKEN_DELIMITERS = companion.encodeUtf8("\t ,=");
    }

    public static final boolean hasBody(Response response) {
        CloseableKt.checkNotNullParameter(response, "response");
        return promisesBody(response);
    }

    public static final List<Challenge> parseChallenges(Headers headers, String str) {
        CloseableKt.checkNotNullParameter(headers, "<this>");
        CloseableKt.checkNotNullParameter(str, "headerName");
        ArrayList arrayList = new ArrayList();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(headers.name(i))) {
                try {
                    readChallengeHeader(new Buffer().writeUtf8(headers.value(i)), arrayList);
                } catch (EOFException e) {
                    Platform.Companion.get().log("Unable to parse challenge", 5, e);
                }
            }
        }
        return arrayList;
    }

    public static final boolean promisesBody(Response response) {
        CloseableKt.checkNotNullParameter(response, "<this>");
        if (CloseableKt.areEqual(response.request().method(), "HEAD")) {
            return false;
        }
        int iCode = response.code();
        return (((iCode >= 100 && iCode < 200) || iCode == 204 || iCode == 304) && Util.headersContentLength(response) == -1 && !"chunked".equalsIgnoreCase(Response.header$default(response, "Transfer-Encoding", null, 2, null))) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0041, code lost:
    
        r4 = new java.lang.StringBuilder();
        r4.append(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
    
        if (r5 < 0) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004d, code lost:
    
        r3 = "";
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
    
        if (r5 == 0) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
    
        if (r5 == 1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
    
        r8 = "=".length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0059, code lost:
    
        if (r8 == 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
    
        if (r8 == 1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005d, code lost:
    
        r3 = new java.lang.StringBuilder("=".length() * r5);
        r5 = new com.shadow.kotlin.ranges.IntRange(1, r5, 1).iterator2();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0075, code lost:
    
        if (r5.hasNext() == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0077, code lost:
    
        r5.nextInt();
        r3.append((java.lang.CharSequence) "=");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007e, code lost:
    
        r3 = r3.toString();
        com.shadow.kotlin.io.CloseableKt.checkNotNull(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0086, code lost:
    
        r3 = 0;
        r6 = "=".charAt(0);
        r7 = new char[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008d, code lost:
    
        if (r3 >= r5) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008f, code lost:
    
        r7[r3] = r6;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0094, code lost:
    
        r3 = new java.lang.String(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x009a, code lost:
    
        r3 = "=".toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d2, code lost:
    
        throw new java.lang.IllegalArgumentException(("Count 'n' must be non-negative, but was " + r5 + '.').toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0126, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0126, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f0  */
    /* JADX WARN: Type inference failed for: r5v9, types: [com.shadow.kotlin.ranges.IntProgressionIterator] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void readChallengeHeader(Buffer buffer, List<Challenge> list) throws EOFException {
        LinkedHashMap linkedHashMap;
        StringBuilder sb;
        String string;
        while (true) {
            String token = null;
            while (true) {
                if (token == null) {
                    skipCommasAndWhitespace(buffer);
                    token = readToken(buffer);
                    if (token == null) {
                        return;
                    }
                }
                boolean zSkipCommasAndWhitespace = skipCommasAndWhitespace(buffer);
                String token2 = readToken(buffer);
                if (token2 != null) {
                    int iSkipAll = Util.skipAll(buffer, (byte) 61);
                    boolean zSkipCommasAndWhitespace2 = skipCommasAndWhitespace(buffer);
                    if (!zSkipCommasAndWhitespace && (zSkipCommasAndWhitespace2 || buffer.exhausted())) {
                        break;
                    }
                    linkedHashMap = new LinkedHashMap();
                    int iSkipAll2 = Util.skipAll(buffer, (byte) 61) + iSkipAll;
                    while (true) {
                        if (token2 == null) {
                            token2 = readToken(buffer);
                            if (!skipCommasAndWhitespace(buffer)) {
                                iSkipAll2 = Util.skipAll(buffer, (byte) 61);
                                if (iSkipAll2 == 0) {
                                    if (iSkipAll2 > 1 || skipCommasAndWhitespace(buffer)) {
                                        return;
                                    }
                                    String quotedString = startsWith(buffer, (byte) 34) ? readQuotedString(buffer) : readToken(buffer);
                                    if (quotedString == null || ((String) linkedHashMap.put(token2, quotedString)) != null) {
                                        return;
                                    }
                                    if (!skipCommasAndWhitespace(buffer) && !buffer.exhausted()) {
                                        return;
                                    } else {
                                        token2 = null;
                                    }
                                }
                            }
                        } else if (iSkipAll2 == 0) {
                            break;
                        }
                    }
                } else {
                    if (buffer.exhausted()) {
                        list.add(new Challenge(token, (Map<String, String>) MapsKt.a()));
                        return;
                    }
                    return;
                }
                list.add(new Challenge(token, linkedHashMap));
                token = token2;
            }
            sb.append(string);
            Map mapSingletonMap = Collections.singletonMap(null, sb.toString());
            CloseableKt.checkNotNullExpressionValue(mapSingletonMap, "singletonMap<String, Str…ek + \"=\".repeat(eqCount))");
            list.add(new Challenge(token, (Map<String, String>) mapSingletonMap));
        }
    }

    private static final String readQuotedString(Buffer buffer) throws EOFException {
        if (buffer.readByte() != 34) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        Buffer buffer2 = new Buffer();
        while (true) {
            long jIndexOfElement = buffer.indexOfElement(QUOTED_STRING_DELIMITERS);
            if (jIndexOfElement == -1) {
                return null;
            }
            if (buffer.getByte(jIndexOfElement) == 34) {
                buffer2.write(buffer, jIndexOfElement);
                buffer.readByte();
                return buffer2.readUtf8();
            }
            if (buffer.size() == jIndexOfElement + 1) {
                return null;
            }
            buffer2.write(buffer, jIndexOfElement);
            buffer.readByte();
            buffer2.write(buffer, 1L);
        }
    }

    private static final String readToken(Buffer buffer) {
        long jIndexOfElement = buffer.indexOfElement(TOKEN_DELIMITERS);
        if (jIndexOfElement == -1) {
            jIndexOfElement = buffer.size();
        }
        if (jIndexOfElement != 0) {
            return buffer.readUtf8(jIndexOfElement);
        }
        return null;
    }

    public static final void receiveHeaders(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        CloseableKt.checkNotNullParameter(cookieJar, "<this>");
        CloseableKt.checkNotNullParameter(httpUrl, "url");
        CloseableKt.checkNotNullParameter(headers, "headers");
        if (cookieJar == CookieJar.NO_COOKIES) {
            return;
        }
        List<Cookie> all = Cookie.Companion.parseAll(httpUrl, headers);
        if (all.isEmpty()) {
            return;
        }
        cookieJar.saveFromResponse(httpUrl, all);
    }

    private static final boolean skipCommasAndWhitespace(Buffer buffer) throws EOFException {
        boolean z = false;
        while (!buffer.exhausted()) {
            byte b = buffer.getByte(0L);
            if (b == 44) {
                buffer.readByte();
                z = true;
            } else {
                if (b != 32 && b != 9) {
                    break;
                }
                buffer.readByte();
            }
        }
        return z;
    }

    private static final boolean startsWith(Buffer buffer, byte b) {
        return !buffer.exhausted() && buffer.getByte(0L) == b;
    }
}
