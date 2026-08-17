package com.reandroid.json;

import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HTTP {
    public static final String CRLF = "\r\n";

    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        HTTPTokener hTTPTokener = new HTTPTokener(str);
        String strNextToken = hTTPTokener.nextToken();
        if (strNextToken.toUpperCase(Locale.ROOT).startsWith("HTTP")) {
            jSONObject.put("HTTP-Version", strNextToken);
            jSONObject.put("Status-Code", hTTPTokener.nextToken());
            jSONObject.put("Reason-Phrase", hTTPTokener.nextTo((char) 0));
            hTTPTokener.next();
        } else {
            jSONObject.put("Method", strNextToken);
            jSONObject.put("Request-URI", hTTPTokener.nextToken());
            jSONObject.put("HTTP-Version", hTTPTokener.nextToken());
        }
        while (hTTPTokener.more()) {
            String strNextTo = hTTPTokener.nextTo(':');
            hTTPTokener.next(':');
            jSONObject.put(strNextTo, hTTPTokener.nextTo((char) 0));
            hTTPTokener.next();
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuilder sb = new StringBuilder();
        if (jSONObject.has("Status-Code") && jSONObject.has("Reason-Phrase")) {
            sb.append(jSONObject.getString("HTTP-Version"));
            sb.append(' ');
            sb.append(jSONObject.getString("Status-Code"));
            sb.append(' ');
            sb.append(jSONObject.getString("Reason-Phrase"));
        } else {
            if (!jSONObject.has("Method") || !jSONObject.has("Request-URI")) {
                throw new JSONException("Not enough material for an HTTP header.");
            }
            sb.append(jSONObject.getString("Method"));
            sb.append(" \"");
            sb.append(jSONObject.getString("Request-URI"));
            sb.append("\" ");
            sb.append(jSONObject.getString("HTTP-Version"));
        }
        sb.append(CRLF);
        for (String str : jSONObject.keySet()) {
            String strOptString = jSONObject.optString(str);
            if (!"HTTP-Version".equals(str) && !"Status-Code".equals(str) && !"Reason-Phrase".equals(str) && !"Method".equals(str) && !"Request-URI".equals(str) && !JSONItem.NULL.equals(strOptString)) {
                sb.append(str);
                sb.append(": ");
                sb.append(jSONObject.optString(str));
                sb.append(CRLF);
            }
        }
        sb.append(CRLF);
        return sb.toString();
    }
}
