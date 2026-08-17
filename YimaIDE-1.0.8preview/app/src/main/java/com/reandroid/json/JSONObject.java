package com.reandroid.json;

import com.intellij.psi.PsiKeyword;
import com.reandroid.common.FileChannelInputStream;
import com.sun.org.apache.xalan.internal.templates.Constants;
import defpackage.sxf;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JSONObject extends JSONItem {
    private final LinkedHashMap<String, Object> map;

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() != '{') {
            throw jSONTokener.syntaxError("A JSONObject text must begin with '{'");
        }
        while (true) {
            char cNextClean = jSONTokener.nextClean();
            if (cNextClean == 0) {
                throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
            }
            if (cNextClean == '}') {
                return;
            }
            jSONTokener.back();
            String string = jSONTokener.nextValue().toString();
            if (jSONTokener.nextClean() != ':') {
                throw jSONTokener.syntaxError("Expected a ':' after a key");
            }
            if (string != null) {
                if (opt(string) != null) {
                    throw jSONTokener.syntaxError("Duplicate key \"" + string + "\"");
                }
                Object objNextValue = jSONTokener.nextValue();
                if (objNextValue != null) {
                    put(string, objNextValue);
                }
            }
            char cNextClean2 = jSONTokener.nextClean();
            if (cNextClean2 != ',' && cNextClean2 != ';') {
                if (cNextClean2 != '}') {
                    throw jSONTokener.syntaxError("Expected a ',' or '}'");
                }
                return;
            } else if (jSONTokener.nextClean() == '}') {
                return;
            } else {
                jSONTokener.back();
            }
        }
    }

    public static String doubleToString(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return PsiKeyword.NULL;
        }
        String string = Double.toString(d);
        if (string.indexOf(46) <= 0 || string.indexOf(101) >= 0 || string.indexOf(69) >= 0) {
            return string;
        }
        while (string.endsWith("0")) {
            string = string.substring(0, string.length() - 1);
        }
        return string.endsWith(Constants.ATTRVAL_THIS) ? string.substring(0, string.length() - 1) : string;
    }

    private static <A extends Annotation> A getAnnotation(Method method, Class<A> cls) {
        if (method != null && cls != null) {
            if (method.isAnnotationPresent(cls)) {
                return (A) method.getAnnotation(cls);
            }
            Class<?> declaringClass = method.getDeclaringClass();
            if (declaringClass.getSuperclass() == null) {
                return null;
            }
            for (Class<?> cls2 : declaringClass.getInterfaces()) {
                try {
                    return (A) getAnnotation(cls2.getMethod(method.getName(), method.getParameterTypes()), cls);
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            }
            try {
                return (A) getAnnotation(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), cls);
            } catch (NoSuchMethodException | SecurityException unused2) {
            }
        }
        return null;
    }

    private static int getAnnotationDepth(Method method, Class<? extends Annotation> cls) {
        if (method != null && cls != null) {
            if (method.isAnnotationPresent(cls)) {
                return 1;
            }
            Class<?> declaringClass = method.getDeclaringClass();
            if (declaringClass.getSuperclass() == null) {
                return -1;
            }
            for (Class<?> cls2 : declaringClass.getInterfaces()) {
                try {
                    int annotationDepth = getAnnotationDepth(cls2.getMethod(method.getName(), method.getParameterTypes()), cls);
                    if (annotationDepth > 0) {
                        return annotationDepth + 1;
                    }
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            }
            try {
                int annotationDepth2 = getAnnotationDepth(declaringClass.getSuperclass().getMethod(method.getName(), method.getParameterTypes()), cls);
                if (annotationDepth2 > 0) {
                    return annotationDepth2 + 1;
                }
            } catch (NoSuchMethodException | SecurityException unused2) {
            }
        }
        return -1;
    }

    private static String getKeyNameFromMethod(Method method) {
        String strSubstring;
        int annotationDepth;
        int annotationDepth2 = getAnnotationDepth(method, JSONPropertyIgnore.class);
        if (annotationDepth2 > 0 && ((annotationDepth = getAnnotationDepth(method, JSONPropertyName.class)) < 0 || annotationDepth2 <= annotationDepth)) {
            return null;
        }
        JSONPropertyName jSONPropertyName = (JSONPropertyName) getAnnotation(method, JSONPropertyName.class);
        if (jSONPropertyName != null && jSONPropertyName.value() != null && !jSONPropertyName.value().isEmpty()) {
            return jSONPropertyName.value();
        }
        String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) {
            strSubstring = name.substring(3);
        } else {
            if (!name.startsWith("is") || name.length() <= 2) {
                return null;
            }
            strSubstring = name.substring(2);
        }
        if (Character.isLowerCase(strSubstring.charAt(0))) {
            return null;
        }
        if (strSubstring.length() == 1) {
            return strSubstring.toLowerCase(Locale.ROOT);
        }
        if (Character.isUpperCase(strSubstring.charAt(1))) {
            return strSubstring;
        }
        return strSubstring.substring(0, 1).toLowerCase(Locale.ROOT) + strSubstring.substring(1);
    }

    public static String[] getNames(Object obj) {
        Field[] fields;
        int length;
        if (obj == null || (length = (fields = obj.getClass().getFields()).length) == 0) {
            return null;
        }
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = fields[i].getName();
        }
        return strArr;
    }

    public static boolean isDecimalNotation(String str) {
        return str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1 || "-0".equals(str);
    }

    private static boolean isValidMethodName(String str) {
        return ("getClass".equals(str) || "getDeclaringClass".equals(str)) ? false : true;
    }

    public static BigDecimal objectToBigDecimal(Object obj, BigDecimal bigDecimal) {
        if (!JSONItem.NULL.equals(obj)) {
            if (obj instanceof BigDecimal) {
                return (BigDecimal) obj;
            }
            if (obj instanceof BigInteger) {
                return new BigDecimal((BigInteger) obj);
            }
            if ((obj instanceof Double) || (obj instanceof Float)) {
                Number number = (Number) obj;
                if (!Double.isNaN(number.doubleValue())) {
                    return new BigDecimal(number.doubleValue());
                }
            } else {
                if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                    return new BigDecimal(((Number) obj).longValue());
                }
                try {
                    return new BigDecimal(obj.toString());
                } catch (Exception unused) {
                }
            }
        }
        return bigDecimal;
    }

    public static BigInteger objectToBigInteger(Object obj, BigInteger bigInteger) {
        if (!JSONItem.NULL.equals(obj)) {
            if (obj instanceof BigInteger) {
                return (BigInteger) obj;
            }
            if (obj instanceof BigDecimal) {
                return ((BigDecimal) obj).toBigInteger();
            }
            if ((obj instanceof Double) || (obj instanceof Float)) {
                double dDoubleValue = ((Number) obj).doubleValue();
                if (!Double.isNaN(dDoubleValue)) {
                    return new BigDecimal(dDoubleValue).toBigInteger();
                }
            } else {
                if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                    return BigInteger.valueOf(((Number) obj).longValue());
                }
                try {
                    String string = obj.toString();
                    return isDecimalNotation(string) ? new BigDecimal(string).toBigInteger() : new BigInteger(string);
                } catch (Exception unused) {
                }
            }
        }
        return bigInteger;
    }

    private void populateMap(Object obj) {
        String keyNameFromMethod;
        Class<?> cls = obj.getClass();
        for (Method method : cls.getClassLoader() != null ? cls.getMethods() : cls.getDeclaredMethods()) {
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers) && method.getParameterTypes().length == 0 && !method.isBridge() && method.getReturnType() != Void.TYPE && isValidMethodName(method.getName()) && (keyNameFromMethod = getKeyNameFromMethod(method)) != null && !keyNameFromMethod.isEmpty()) {
                try {
                    Object objInvoke = method.invoke(obj, null);
                    if (objInvoke != null) {
                        this.map.put(keyNameFromMethod, JSONItem.wrap(objInvoke));
                        if (objInvoke instanceof Closeable) {
                            ((Closeable) objInvoke).close();
                        }
                    }
                } catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                }
            }
        }
    }

    public static Number stringToNumber(String str) throws NumberFormatException {
        char cCharAt = str.charAt(0);
        if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '-') {
            sxf.a("val [", str, "] is not a valid number.");
            return null;
        }
        if (isDecimalNotation(str)) {
            try {
                try {
                    BigDecimal bigDecimal = new BigDecimal(str);
                    return (cCharAt == '-' && BigDecimal.ZERO.compareTo(bigDecimal) == 0) ? Double.valueOf(-0.0d) : bigDecimal;
                } catch (NumberFormatException unused) {
                    Double dValueOf = Double.valueOf(str);
                    if (!dValueOf.isNaN() && !dValueOf.isInfinite()) {
                        return dValueOf;
                    }
                    throw new NumberFormatException("val [" + str + "] is not a valid number.");
                }
            } catch (NumberFormatException unused2) {
                sxf.a("val [", str, "] is not a valid number.");
                return null;
            }
            sxf.a("val [", str, "] is not a valid number.");
            return null;
        }
        if (cCharAt == '0' && str.length() > 1) {
            char cCharAt2 = str.charAt(1);
            if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                sxf.a("val [", str, "] is not a valid number.");
                return null;
            }
        } else if (cCharAt == '-' && str.length() > 2) {
            char cCharAt3 = str.charAt(1);
            char cCharAt4 = str.charAt(2);
            if (cCharAt3 == '0' && cCharAt4 >= '0' && cCharAt4 <= '9') {
                sxf.a("val [", str, "] is not a valid number.");
                return null;
            }
        }
        BigInteger bigInteger = new BigInteger(str);
        if (bigInteger.bitLength() <= 31) {
            return Integer.valueOf(bigInteger.intValue());
        }
        return bigInteger.bitLength() <= 63 ? Long.valueOf(bigInteger.longValue()) : bigInteger;
    }

    public static Object stringToValue(String str) {
        if ("".equals(str)) {
            return str;
        }
        if ("true".equalsIgnoreCase(str)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        }
        if (PsiKeyword.NULL.equalsIgnoreCase(str)) {
            return JSONItem.NULL;
        }
        char cCharAt = str.charAt(0);
        if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '-') {
            return str;
        }
        try {
            return stringToNumber(str);
        } catch (Exception unused) {
            return str;
        }
    }

    private static JSONException wrongValueFormatException(String str, String str2, Object obj, Throwable th) {
        return new JSONException("JSONObject[" + JSONItem.quote(str) + "] is not a " + str2 + " (" + obj + ").", th);
    }

    public JSONObject accumulate(String str, Object obj) throws JSONException {
        JSONItem.testValidity(obj);
        Object objOpt = opt(str);
        if (objOpt == null) {
            if (obj instanceof JSONArray) {
                obj = new JSONArray().put(obj);
            }
            put(str, obj);
            return this;
        }
        if (objOpt instanceof JSONArray) {
            ((JSONArray) objOpt).put(obj);
            return this;
        }
        put(str, new JSONArray().put(objOpt).put(obj));
        return this;
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        JSONItem.testValidity(obj);
        Object objOpt = opt(str);
        if (objOpt == null) {
            put(str, new JSONArray().put(obj));
            return this;
        }
        if (!(objOpt instanceof JSONArray)) {
            throw wrongValueFormatException(str, "JSONArray", null, null);
        }
        put(str, ((JSONArray) objOpt).put(obj));
        return this;
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public Object get(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        }
        Object objOpt = opt(str);
        if (objOpt != null) {
            return objOpt;
        }
        throw new JSONException("JSONObject[" + JSONItem.quote(str) + "] not found.");
    }

    public BigDecimal getBigDecimal(String str) throws JSONException {
        Object obj = get(str);
        BigDecimal bigDecimalObjectToBigDecimal = objectToBigDecimal(obj, null);
        if (bigDecimalObjectToBigDecimal != null) {
            return bigDecimalObjectToBigDecimal;
        }
        throw wrongValueFormatException(str, "BigDecimal", obj, null);
    }

    public BigInteger getBigInteger(String str) throws JSONException {
        Object obj = get(str);
        BigInteger bigIntegerObjectToBigInteger = objectToBigInteger(obj, null);
        if (bigIntegerObjectToBigInteger != null) {
            return bigIntegerObjectToBigInteger;
        }
        throw wrongValueFormatException(str, "BigInteger", obj, null);
    }

    public boolean getBoolean(String str) throws JSONException {
        Object obj = get(str);
        if (obj.equals(Boolean.FALSE)) {
            return false;
        }
        boolean z = obj instanceof String;
        if (z && ((String) obj).equalsIgnoreCase("false")) {
            return false;
        }
        if (obj.equals(Boolean.TRUE)) {
            return true;
        }
        if (z && ((String) obj).equalsIgnoreCase("true")) {
            return true;
        }
        throw wrongValueFormatException(str, "Boolean", null);
    }

    public double getDouble(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "double", e);
        }
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls, String str) throws JSONException {
        E e = (E) optEnum(cls, str);
        if (e != null) {
            return e;
        }
        throw wrongValueFormatException(str, "enum of type " + JSONItem.quote(cls.getSimpleName()), null);
    }

    public float getFloat(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).floatValue();
        }
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "float", e);
        }
    }

    public int getInt(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "int", e);
        }
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw wrongValueFormatException(str, "JSONArray", null);
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw wrongValueFormatException(str, "JSONObject", null);
    }

    public long getLong(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "long", e);
        }
    }

    public Number getNumber(String str) throws JSONException {
        Object obj = get(str);
        try {
            return obj instanceof Number ? (Number) obj : stringToNumber(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(str, "number", e);
        }
    }

    public String getString(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw wrongValueFormatException(str, "string", null);
    }

    public boolean has(String str) {
        return this.map.containsKey(str);
    }

    public JSONObject increment(String str) throws JSONException {
        Object objOpt = opt(str);
        if (objOpt == null) {
            put(str, 1);
            return this;
        }
        if (objOpt instanceof Integer) {
            put(str, ((Integer) objOpt).intValue() + 1);
            return this;
        }
        if (objOpt instanceof Long) {
            put(str, ((Long) objOpt).longValue() + 1);
            return this;
        }
        if (objOpt instanceof BigInteger) {
            put(str, ((BigInteger) objOpt).add(BigInteger.ONE));
            return this;
        }
        if (objOpt instanceof Float) {
            put(str, ((Float) objOpt).floatValue() + 1.0f);
            return this;
        }
        if (objOpt instanceof Double) {
            put(str, ((Double) objOpt).doubleValue() + 1.0d);
            return this;
        }
        if (objOpt instanceof BigDecimal) {
            put(str, ((BigDecimal) objOpt).add(BigDecimal.ONE));
            return this;
        }
        throw new JSONException("Unable to increment [" + JSONItem.quote(str) + "].");
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean isNull(String str) {
        return JSONItem.NULL.equals(opt(str));
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public Iterator<String> keys() {
        return keySet().iterator();
    }

    public int length() {
        return this.map.size();
    }

    public JSONArray names() {
        if (this.map.isEmpty()) {
            return null;
        }
        return new JSONArray((Collection<?>) this.map.keySet());
    }

    public Object opt(String str) {
        if (str == null) {
            return null;
        }
        return this.map.get(str);
    }

    public BigDecimal optBigDecimal(String str, BigDecimal bigDecimal) {
        return objectToBigDecimal(opt(str), bigDecimal);
    }

    public BigInteger optBigInteger(String str, BigInteger bigInteger) {
        return objectToBigInteger(opt(str), bigInteger);
    }

    public boolean optBoolean(String str, boolean z) {
        Object objOpt = opt(str);
        if (JSONItem.NULL.equals(objOpt)) {
            return z;
        }
        if (objOpt instanceof Boolean) {
            return ((Boolean) objOpt).booleanValue();
        }
        try {
            return getBoolean(str);
        } catch (Exception unused) {
            return z;
        }
    }

    public double optDouble(String str, double d) {
        Number numberOptNumber = optNumber(str);
        return numberOptNumber == null ? d : numberOptNumber.doubleValue();
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str, E e) {
        try {
            Object objOpt = opt(str);
            if (JSONItem.NULL.equals(objOpt)) {
                return e;
            }
            return cls.isAssignableFrom(objOpt.getClass()) ? (E) objOpt : (E) Enum.valueOf(cls, objOpt.toString());
        } catch (IllegalArgumentException | NullPointerException unused) {
        }
    }

    public float optFloat(String str, float f) {
        Number numberOptNumber = optNumber(str);
        return numberOptNumber == null ? f : numberOptNumber.floatValue();
    }

    public int optInt(String str, int i) {
        Number numberOptNumber = optNumber(str, null);
        return numberOptNumber == null ? i : numberOptNumber.intValue();
    }

    public JSONArray optJSONArray(String str) {
        Object objOpt = opt(str);
        if (objOpt instanceof JSONArray) {
            return (JSONArray) objOpt;
        }
        return null;
    }

    public JSONObject optJSONObject(String str) {
        Object objOpt = opt(str);
        if (objOpt instanceof JSONObject) {
            return (JSONObject) objOpt;
        }
        return null;
    }

    public long optLong(String str, long j) {
        Number numberOptNumber = optNumber(str, null);
        return numberOptNumber == null ? j : numberOptNumber.longValue();
    }

    public Number optNumber(String str, Number number) {
        Object objOpt = opt(str);
        if (!JSONItem.NULL.equals(objOpt)) {
            if (objOpt instanceof Number) {
                return (Number) objOpt;
            }
            try {
                return stringToNumber(objOpt.toString());
            } catch (Exception unused) {
            }
        }
        return number;
    }

    public Object optQuery(String str) {
        return optQuery(new JSONPointer(str));
    }

    public String optString(String str, String str2) {
        Object objOpt = opt(str);
        return JSONItem.NULL.equals(objOpt) ? str2 : objOpt.toString();
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        if (str == null) {
            x0e.a("Null key.");
            return null;
        }
        if (obj == null) {
            remove(str);
            return this;
        }
        JSONItem.testValidity(obj);
        this.map.put(str, obj);
        return this;
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        if (str == null || obj == null) {
            return this;
        }
        if (opt(str) == null) {
            return put(str, obj);
        }
        throw new JSONException("Duplicate key \"" + str + "\"");
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        return (str == null || obj == null) ? this : put(str, obj);
    }

    public Object query(String str) {
        return query(new JSONPointer(str));
    }

    public Object remove(String str) {
        return this.map.remove(str);
    }

    public boolean similar(Object obj) {
        try {
            if (!(obj instanceof JSONObject) || !keySet().equals(((JSONObject) obj).keySet())) {
                return false;
            }
            for (Map.Entry<String, Object> entry : entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Object obj2 = ((JSONObject) obj).get(key);
                if (value != obj2) {
                    if (value == null) {
                        return false;
                    }
                    if (value instanceof JSONObject) {
                        if (!((JSONObject) value).similar(obj2)) {
                            return false;
                        }
                    } else if (value instanceof JSONArray) {
                        if (!((JSONArray) value).similar(obj2)) {
                            return false;
                        }
                    } else if (!value.equals(obj2)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public void sort(Comparator<String> comparator, boolean z) {
        LinkedHashMap<String, Object> linkedHashMap = this.map;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap.clear();
        ArrayList<String> arrayList = new ArrayList(linkedHashMap2.keySet());
        arrayList.sort(comparator);
        for (String str : arrayList) {
            linkedHashMap.put(str, linkedHashMap2.get(str));
        }
        linkedHashMap2.clear();
        if (z) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object obj = linkedHashMap.get((String) it.next());
                if (obj instanceof JSONObject) {
                    ((JSONObject) obj).sort(comparator, true);
                }
            }
        }
    }

    public JSONArray toJSONArray(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.isEmpty()) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            jSONArray2.put(opt(jSONArray.getString(i)));
        }
        return jSONArray2;
    }

    public LinkedHashMap<String, Object> toMap() {
        Object list;
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : entrySet()) {
            if (entry.getValue() == null || JSONItem.NULL.equals(entry.getValue())) {
                list = null;
            } else if (entry.getValue() instanceof JSONObject) {
                list = ((JSONObject) entry.getValue()).toMap();
            } else {
                list = entry.getValue() instanceof JSONArray ? ((JSONArray) entry.getValue()).toList() : entry.getValue();
            }
            linkedHashMap.put(entry.getKey(), list);
        }
        return linkedHashMap;
    }

    @Override // com.reandroid.json.JSONItem
    public Writer write(Writer writer, int i, int i2) throws JSONException {
        try {
            writer.write(123);
            int i3 = i2 + i;
            boolean z = false;
            for (Map.Entry<String, Object> entry : entrySet()) {
                if (z) {
                    writer.write(44);
                }
                if (i > 0) {
                    writer.write(10);
                }
                JSONItem.indent(writer, i3);
                String key = entry.getKey();
                writer.write(JSONItem.quote(key));
                writer.write(58);
                if (i > 0) {
                    writer.write(32);
                }
                try {
                    JSONItem.writeValue(writer, entry.getValue(), i, i3);
                    z = true;
                } catch (Exception e) {
                    throw new JSONException("Unable to write JSONObject value for key: " + key, e);
                }
            }
            if (z) {
                if (i > 0) {
                    writer.write(10);
                }
                JSONItem.indent(writer, i2);
            }
            writer.write(125);
            return writer;
        } catch (IOException e2) {
            throw new JSONException(e2);
        }
    }

    public Object optQuery(JSONPointer jSONPointer) {
        try {
            return jSONPointer.queryFrom(this);
        } catch (JSONPointerException unused) {
            return null;
        }
    }

    public Object query(JSONPointer jSONPointer) {
        return jSONPointer.queryFrom(this);
    }

    public double optDouble(String str) {
        return optDouble(str, Double.NaN);
    }

    public float optFloat(String str) {
        return optFloat(str, Float.NaN);
    }

    public int optInt(String str) {
        return optInt(str, 0);
    }

    public long optLong(String str) {
        return optLong(str, 0L);
    }

    public String optString(String str) {
        return optString(str, "");
    }

    public JSONObject put(String str, Collection<?> collection) throws JSONException {
        return put(str, new JSONArray(collection));
    }

    public JSONObject put(String str, double d) throws JSONException {
        return put(str, Double.valueOf(d));
    }

    public JSONObject put(String str, float f) throws JSONException {
        return put(str, Float.valueOf(f));
    }

    public JSONObject put(String str, int i) throws JSONException {
        return put(str, Integer.valueOf(i));
    }

    public JSONObject put(String str, long j) throws JSONException {
        return put(str, Long.valueOf(j));
    }

    public JSONObject put(String str, Map<?, ?> map) throws JSONException {
        return put(str, new JSONObject(map));
    }

    public boolean optBoolean(String str) {
        return optBoolean(str, false);
    }

    public Number optNumber(String str) {
        return optNumber(str, null);
    }

    public JSONObject put(String str, boolean z) throws JSONException {
        return put(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public static String[] getNames(JSONObject jSONObject) {
        if (jSONObject.isEmpty()) {
            return null;
        }
        return (String[]) jSONObject.keySet().toArray(new String[jSONObject.length()]);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str) {
        return (E) optEnum(cls, str, null);
    }

    private static JSONException wrongValueFormatException(String str, String str2, Throwable th) {
        return new JSONException("JSONObject[" + JSONItem.quote(str) + "] is not a " + str2 + Constants.ATTRVAL_THIS, th);
    }

    public void sort(Comparator<String> comparator) {
        sort(comparator, false);
    }

    public JSONObject(JSONObject jSONObject, String... strArr) {
        this(strArr.length);
        for (String str : strArr) {
            try {
                putOnce(str, jSONObject.opt(str));
            } catch (Exception unused) {
            }
        }
    }

    public JSONObject() {
        this.map = new LinkedHashMap<>();
    }

    public JSONObject(Map<?, ?> map) {
        if (map == null) {
            this.map = new LinkedHashMap<>();
            return;
        }
        this.map = new LinkedHashMap<>(map.size());
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                Object value = entry.getValue();
                if (value != null) {
                    this.map.put(String.valueOf(entry.getKey()), JSONItem.wrap(value));
                }
            } else {
                x0e.a("Null key.");
                throw null;
            }
        }
    }

    public JSONObject(Object obj) {
        this();
        populateMap(obj);
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String strNextElement = keys.nextElement();
            if (strNextElement != null) {
                String str2 = strNextElement;
                String[] strArrSplit = str2.split("\\.");
                int length = strArrSplit.length - 1;
                JSONObject jSONObject = this;
                for (int i = 0; i < length; i++) {
                    String str3 = strArrSplit[i];
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str3);
                    if (jSONObjectOptJSONObject == null) {
                        jSONObjectOptJSONObject = new JSONObject();
                        jSONObject.put(str3, jSONObjectOptJSONObject);
                    }
                    jSONObject = jSONObjectOptJSONObject;
                }
                jSONObject.put(strArrSplit[length], bundle.getString(str2));
            }
        }
    }

    public JSONObject(int i) {
        this.map = new LinkedHashMap<>(i);
    }

    public JSONObject(File file) throws IOException {
        this((InputStream) new FileChannelInputStream(file));
    }

    public JSONObject(Reader reader) {
        this(new JSONTokener(reader));
    }

    public JSONObject(InputStream inputStream) throws JSONException {
        this(new JSONTokener(inputStream));
        try {
            inputStream.close();
        } catch (IOException unused) {
        }
    }
}
