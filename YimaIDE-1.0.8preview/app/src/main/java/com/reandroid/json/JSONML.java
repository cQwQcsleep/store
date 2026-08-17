package com.reandroid.json;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JSONML {
    /* JADX WARN: Code duplicated, block: B:97:0x0138 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:98:0x0139 A[RETURN] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    private static Object parse(XMLTokener xMLTokener, boolean z, JSONArray jSONArray, boolean z2) throws JSONException {
        String str;
        while (xMLTokener.more()) {
            Object objNextContent = xMLTokener.nextContent();
            if (objNextContent == XML.LT) {
                Object objNextToken = xMLTokener.nextToken();
                if (objNextToken instanceof Character) {
                    if (objNextToken == XML.SLASH) {
                        Object objNextToken2 = xMLTokener.nextToken();
                        if (objNextToken2 instanceof String) {
                            if (xMLTokener.nextToken() == XML.GT) {
                                return objNextToken2;
                            }
                            throw xMLTokener.syntaxError("Misshaped close tag");
                        }
                        throw new JSONException("Expected a closing name instead of '" + objNextToken2 + "'.");
                    }
                    if (objNextToken == XML.BANG) {
                        char next = xMLTokener.next();
                        if (next == '-') {
                            if (xMLTokener.next() == '-') {
                                xMLTokener.skipPast("-->");
                            } else {
                                xMLTokener.back();
                            }
                        } else if (next != '[') {
                            int i = 1;
                            do {
                                Object objNextMeta = xMLTokener.nextMeta();
                                if (objNextMeta == null) {
                                    throw xMLTokener.syntaxError("Missing '>' after '<!'.");
                                }
                                if (objNextMeta == XML.LT) {
                                    i++;
                                } else if (objNextMeta == XML.GT) {
                                    i--;
                                }
                            } while (i > 0);
                        } else {
                            if (!xMLTokener.nextToken().equals("CDATA") || xMLTokener.next() != '[') {
                                throw xMLTokener.syntaxError("Expected 'CDATA['");
                            }
                            if (jSONArray != null) {
                                jSONArray.put(xMLTokener.nextCDATA());
                            }
                        }
                    } else {
                        if (objNextToken != XML.QUEST) {
                            throw xMLTokener.syntaxError("Misshaped tag");
                        }
                        xMLTokener.skipPast("?>");
                    }
                } else {
                    if (!(objNextToken instanceof String)) {
                        throw xMLTokener.syntaxError("Bad tagName '" + objNextToken + "'.");
                    }
                    String str2 = (String) objNextToken;
                    JSONArray jSONArray2 = new JSONArray();
                    JSONObject jSONObject = new JSONObject();
                    if (z) {
                        jSONArray2.put(str2);
                        if (jSONArray != null) {
                            jSONArray.put(jSONArray2);
                        }
                    } else {
                        jSONObject.put("tagName", str2);
                        if (jSONArray != null) {
                            jSONArray.put(jSONObject);
                        }
                    }
                    while (true) {
                        Object objNextToken3 = null;
                        while (true) {
                            if (objNextToken3 == null) {
                                objNextToken3 = xMLTokener.nextToken();
                            }
                            if (objNextToken3 == null) {
                                throw xMLTokener.syntaxError("Misshaped tag");
                            }
                            if (!(objNextToken3 instanceof String)) {
                                if (z && jSONObject.length() > 0) {
                                    jSONArray2.put(jSONObject);
                                }
                                if (objNextToken3 == XML.SLASH) {
                                    if (xMLTokener.nextToken() != XML.GT) {
                                        throw xMLTokener.syntaxError("Misshaped tag");
                                    }
                                    if (jSONArray != null) {
                                        break;
                                    }
                                    if (z) {
                                        return jSONArray2;
                                    }
                                    return jSONObject;
                                }
                                if (objNextToken3 != XML.GT) {
                                    throw xMLTokener.syntaxError("Misshaped tag");
                                }
                                String str3 = (String) parse(xMLTokener, z, jSONArray2, z2);
                                if (str3 == null) {
                                    break;
                                }
                                if (!str3.equals(str2)) {
                                    throw xMLTokener.syntaxError("Mismatched '" + str2 + "' and '" + str3 + "'");
                                }
                                if (!z && jSONArray2.length() > 0) {
                                    jSONObject.put("childNodes", jSONArray2);
                                }
                                if (jSONArray != null) {
                                    break;
                                }
                                if (z) {
                                    return jSONArray2;
                                }
                                return jSONObject;
                            }
                            str = (String) objNextToken3;
                            if (!z && ("tagName".equals(str) || "childNode".equals(str))) {
                                throw xMLTokener.syntaxError("Reserved attribute.");
                            }
                            Object objNextToken4 = xMLTokener.nextToken();
                            if (objNextToken4 == XML.EQ) {
                                break;
                            }
                            jSONObject.accumulate(str, "");
                            objNextToken3 = objNextToken4;
                        }
                        Object objNextToken5 = xMLTokener.nextToken();
                        if (!(objNextToken5 instanceof String)) {
                            throw xMLTokener.syntaxError("Missing value");
                        }
                        Object objStringToValue = (String) objNextToken5;
                        if (!z2) {
                            objStringToValue = XML.stringToValue(objStringToValue);
                        }
                        jSONObject.accumulate(str, objStringToValue);
                    }
                }
            } else if (jSONArray != null) {
                if (objNextContent instanceof String) {
                    String str4 = (String) objNextContent;
                    objNextContent = z2 ? XML.unescape(str4) : XML.stringToValue(str4);
                }
                jSONArray.put(objNextContent);
            }
        }
        throw xMLTokener.syntaxError("Bad XML");
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return (JSONArray) parse(new XMLTokener(str), true, null, false);
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        return (JSONObject) parse(new XMLTokener(str), false, null, false);
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuilder sb = new StringBuilder();
        String strOptString = jSONObject.optString("tagName");
        if (strOptString == null) {
            return XML.escape(jSONObject.toString());
        }
        XML.noSpace(strOptString);
        String strEscape = XML.escape(strOptString);
        sb.append('<');
        sb.append(strEscape);
        for (String str : jSONObject.keySet()) {
            if (!"tagName".equals(str) && !"childNodes".equals(str)) {
                XML.noSpace(str);
                Object objOpt = jSONObject.opt(str);
                if (objOpt != null) {
                    sb.append(' ');
                    sb.append(XML.escape(str));
                    sb.append("=\"");
                    sb.append(XML.escape(objOpt.toString()));
                    sb.append('\"');
                }
            }
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("childNodes");
        if (jSONArrayOptJSONArray == null) {
            sb.append("/>");
        } else {
            sb.append('>');
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj = jSONArrayOptJSONArray.get(i);
                if (obj != null) {
                    if (obj instanceof String) {
                        sb.append(XML.escape(obj.toString()));
                    } else if (obj instanceof JSONObject) {
                        sb.append(toString((JSONObject) obj));
                    } else if (obj instanceof JSONArray) {
                        sb.append(toString((JSONArray) obj));
                    } else {
                        sb.append(obj.toString());
                    }
                }
            }
            sb.append("</");
            sb.append(strEscape);
            sb.append('>');
        }
        return sb.toString();
    }

    public static JSONObject toJSONObject(String str, boolean z) throws JSONException {
        return (JSONObject) parse(new XMLTokener(str), false, null, z);
    }

    public static JSONArray toJSONArray(String str, boolean z) throws JSONException {
        return (JSONArray) parse(new XMLTokener(str), true, null, z);
    }

    public static JSONObject toJSONObject(XMLTokener xMLTokener) throws JSONException {
        return (JSONObject) parse(xMLTokener, false, null, false);
    }

    public static JSONArray toJSONArray(XMLTokener xMLTokener, boolean z) throws JSONException {
        return (JSONArray) parse(xMLTokener, true, null, z);
    }

    public static JSONObject toJSONObject(XMLTokener xMLTokener, boolean z) throws JSONException {
        return (JSONObject) parse(xMLTokener, false, null, z);
    }

    public static JSONArray toJSONArray(XMLTokener xMLTokener) throws JSONException {
        return (JSONArray) parse(xMLTokener, true, null, false);
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        int i;
        StringBuilder sb = new StringBuilder("<");
        String string = jSONArray.getString(0);
        XML.noSpace(string);
        String strEscape = XML.escape(string);
        sb.append(strEscape);
        Object objOpt = jSONArray.opt(1);
        if (objOpt instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) objOpt;
            for (String str : jSONObject.keySet()) {
                Object objOpt2 = jSONObject.opt(str);
                XML.noSpace(str);
                if (objOpt2 != null) {
                    sb.append(' ');
                    sb.append(XML.escape(str));
                    sb.append("=\"");
                    sb.append(XML.escape(objOpt2.toString()));
                    sb.append('\"');
                }
            }
            i = 2;
        } else {
            i = 1;
        }
        int length = jSONArray.length();
        if (i >= length) {
            sb.append("/>");
        } else {
            sb.append('>');
            do {
                Object obj = jSONArray.get(i);
                i++;
                if (obj != null) {
                    if (obj instanceof String) {
                        sb.append(XML.escape(obj.toString()));
                    } else if (obj instanceof JSONObject) {
                        sb.append(toString((JSONObject) obj));
                    } else if (obj instanceof JSONArray) {
                        sb.append(toString((JSONArray) obj));
                    } else {
                        sb.append(obj.toString());
                    }
                }
            } while (i < length);
            sb.append("</");
            sb.append(strEscape);
            sb.append('>');
        }
        return sb.toString();
    }
}
