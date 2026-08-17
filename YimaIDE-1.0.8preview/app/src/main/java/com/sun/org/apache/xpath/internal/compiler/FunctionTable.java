package com.sun.org.apache.xpath.internal.compiler;

import com.sun.org.apache.xpath.internal.functions.FuncBoolean;
import com.sun.org.apache.xpath.internal.functions.FuncCeiling;
import com.sun.org.apache.xpath.internal.functions.FuncConcat;
import com.sun.org.apache.xpath.internal.functions.FuncContains;
import com.sun.org.apache.xpath.internal.functions.FuncCount;
import com.sun.org.apache.xpath.internal.functions.FuncCurrent;
import com.sun.org.apache.xpath.internal.functions.FuncDoclocation;
import com.sun.org.apache.xpath.internal.functions.FuncExtElementAvailable;
import com.sun.org.apache.xpath.internal.functions.FuncExtFunctionAvailable;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.sun.org.apache.xpath.internal.functions.FuncFloor;
import com.sun.org.apache.xpath.internal.functions.FuncGenerateId;
import com.sun.org.apache.xpath.internal.functions.FuncHere;
import com.sun.org.apache.xpath.internal.functions.FuncId;
import com.sun.org.apache.xpath.internal.functions.FuncLang;
import com.sun.org.apache.xpath.internal.functions.FuncLast;
import com.sun.org.apache.xpath.internal.functions.FuncLocalPart;
import com.sun.org.apache.xpath.internal.functions.FuncNamespace;
import com.sun.org.apache.xpath.internal.functions.FuncNormalizeSpace;
import com.sun.org.apache.xpath.internal.functions.FuncNot;
import com.sun.org.apache.xpath.internal.functions.FuncNumber;
import com.sun.org.apache.xpath.internal.functions.FuncPosition;
import com.sun.org.apache.xpath.internal.functions.FuncQname;
import com.sun.org.apache.xpath.internal.functions.FuncRound;
import com.sun.org.apache.xpath.internal.functions.FuncStartsWith;
import com.sun.org.apache.xpath.internal.functions.FuncString;
import com.sun.org.apache.xpath.internal.functions.FuncStringLength;
import com.sun.org.apache.xpath.internal.functions.FuncSubstring;
import com.sun.org.apache.xpath.internal.functions.FuncSubstringAfter;
import com.sun.org.apache.xpath.internal.functions.FuncSubstringBefore;
import com.sun.org.apache.xpath.internal.functions.FuncSum;
import com.sun.org.apache.xpath.internal.functions.FuncSystemProperty;
import com.sun.org.apache.xpath.internal.functions.FuncTranslate;
import com.sun.org.apache.xpath.internal.functions.FuncTrue;
import com.sun.org.apache.xpath.internal.functions.FuncUnparsedEntityURI;
import com.sun.org.apache.xpath.internal.functions.Function;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FunctionTable {
    public static final int FUNC_BOOLEAN = 14;
    public static final int FUNC_CEILING = 17;
    public static final int FUNC_CONCAT = 27;
    public static final int FUNC_CONTAINS = 22;
    public static final int FUNC_COUNT = 3;
    public static final int FUNC_CURRENT = 0;
    public static final int FUNC_DOCLOCATION = 35;
    public static final int FUNC_EXT_ELEM_AVAILABLE = 34;
    public static final int FUNC_EXT_FUNCTION_AVAILABLE = 33;
    public static final int FUNC_FALSE = 13;
    public static final int FUNC_FLOOR = 16;
    public static final int FUNC_GENERATE_ID = 10;
    public static final int FUNC_HERE = 37;
    public static final int FUNC_ID = 4;
    public static final int FUNC_KEY = 5;
    public static final int FUNC_LANG = 32;
    public static final int FUNC_LAST = 1;
    public static final int FUNC_LOCAL_PART = 7;
    public static final int FUNC_NAMESPACE = 8;
    public static final int FUNC_NORMALIZE_SPACE = 25;
    public static final int FUNC_NOT = 11;
    public static final int FUNC_NUMBER = 15;
    public static final int FUNC_POSITION = 2;
    public static final int FUNC_QNAME = 9;
    public static final int FUNC_ROUND = 18;
    public static final int FUNC_STARTS_WITH = 21;
    public static final int FUNC_STRING = 20;
    public static final int FUNC_STRING_LENGTH = 30;
    public static final int FUNC_SUBSTRING = 29;
    public static final int FUNC_SUBSTRING_AFTER = 24;
    public static final int FUNC_SUBSTRING_BEFORE = 23;
    public static final int FUNC_SUM = 19;
    public static final int FUNC_SYSTEM_PROPERTY = 31;
    public static final int FUNC_TRANSLATE = 26;
    public static final int FUNC_TRUE = 12;
    public static final int FUNC_UNPARSED_ENTITY_URI = 36;
    private static final int NUM_ALLOWABLE_ADDINS = 30;
    private static final int NUM_BUILT_IN_FUNCS = 38;
    private static final Map<String, Integer> m_functionID;
    private static Class<?>[] m_functions;
    private Class<?>[] m_functions_customer = new Class[30];
    private Map<String, Integer> m_functionID_customer = new HashMap();
    private int m_funcNextFreeIndex = 38;

    static {
        HashMap map = new HashMap();
        m_functionID = map;
        Class<?>[] clsArr = new Class[38];
        m_functions = clsArr;
        clsArr[0] = FuncCurrent.class;
        clsArr[1] = FuncLast.class;
        clsArr[2] = FuncPosition.class;
        clsArr[3] = FuncCount.class;
        clsArr[4] = FuncId.class;
        clsArr[7] = FuncLocalPart.class;
        clsArr[8] = FuncNamespace.class;
        clsArr[9] = FuncQname.class;
        clsArr[10] = FuncGenerateId.class;
        clsArr[11] = FuncNot.class;
        clsArr[12] = FuncTrue.class;
        clsArr[13] = FuncFalse.class;
        clsArr[14] = FuncBoolean.class;
        clsArr[32] = FuncLang.class;
        clsArr[15] = FuncNumber.class;
        clsArr[16] = FuncFloor.class;
        clsArr[17] = FuncCeiling.class;
        clsArr[18] = FuncRound.class;
        clsArr[19] = FuncSum.class;
        clsArr[20] = FuncString.class;
        clsArr[21] = FuncStartsWith.class;
        clsArr[22] = FuncContains.class;
        clsArr[23] = FuncSubstringBefore.class;
        clsArr[24] = FuncSubstringAfter.class;
        clsArr[25] = FuncNormalizeSpace.class;
        clsArr[26] = FuncTranslate.class;
        clsArr[27] = FuncConcat.class;
        clsArr[31] = FuncSystemProperty.class;
        clsArr[33] = FuncExtFunctionAvailable.class;
        clsArr[34] = FuncExtElementAvailable.class;
        clsArr[29] = FuncSubstring.class;
        clsArr[30] = FuncStringLength.class;
        clsArr[35] = FuncDoclocation.class;
        clsArr[36] = FuncUnparsedEntityURI.class;
        clsArr[37] = FuncHere.class;
        map.put(Keywords.FUNC_CURRENT_STRING, 0);
        map.put(Keywords.FUNC_LAST_STRING, 1);
        map.put(Keywords.FUNC_POSITION_STRING, 2);
        map.put("count", 3);
        map.put("id", 4);
        map.put("key", 5);
        map.put(Keywords.FUNC_LOCAL_PART_STRING, 7);
        map.put(Keywords.FUNC_NAMESPACE_STRING, 8);
        map.put("name", 9);
        map.put(Keywords.FUNC_GENERATE_ID_STRING, 10);
        map.put(Keywords.FUNC_NOT_STRING, 11);
        map.put("true", 12);
        map.put("false", 13);
        map.put("boolean", 14);
        map.put("lang", 32);
        map.put("number", 15);
        map.put(Keywords.FUNC_FLOOR_STRING, 16);
        map.put(Keywords.FUNC_CEILING_STRING, 17);
        map.put(Keywords.FUNC_ROUND_STRING, 18);
        map.put(Keywords.FUNC_SUM_STRING, 19);
        map.put("string", 20);
        map.put(Keywords.FUNC_STARTS_WITH_STRING, 21);
        map.put(Keywords.FUNC_CONTAINS_STRING, 22);
        map.put(Keywords.FUNC_SUBSTRING_BEFORE_STRING, 23);
        map.put(Keywords.FUNC_SUBSTRING_AFTER_STRING, 24);
        map.put(Keywords.FUNC_NORMALIZE_SPACE_STRING, 25);
        map.put(Keywords.FUNC_TRANSLATE_STRING, 26);
        map.put(Keywords.FUNC_CONCAT_STRING, 27);
        map.put(Keywords.FUNC_SYSTEM_PROPERTY_STRING, 31);
        map.put(Keywords.FUNC_EXT_FUNCTION_AVAILABLE_STRING, 33);
        map.put(Keywords.FUNC_EXT_ELEM_AVAILABLE_STRING, 34);
        map.put(Keywords.FUNC_SUBSTRING_STRING, 29);
        map.put(Keywords.FUNC_STRING_LENGTH_STRING, 30);
        map.put(Keywords.FUNC_UNPARSED_ENTITY_URI_STRING, 36);
        map.put(Keywords.FUNC_DOCLOCATION_STRING, 35);
        map.put(Keywords.FUNC_HERE_STRING, 37);
    }

    public boolean functionAvailable(String str) {
        return (m_functionID.get(str) == null && this.m_functionID_customer.get(str) == null) ? false : true;
    }

    public Function getFunction(int i) throws TransformerException {
        try {
            return i < 38 ? (Function) m_functions[i].getConstructor(null).newInstance(null) : (Function) this.m_functions_customer[i - 38].getConstructor(null).newInstance(null);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new TransformerException(e.getMessage());
        }
    }

    public Integer getFunctionID(String str) {
        Integer num = this.m_functionID_customer.get(str);
        return num == null ? m_functionID.get(str) : num;
    }

    public String getFunctionName(int i) {
        return i < 38 ? m_functions[i].getName() : this.m_functions_customer[i - 38].getName();
    }

    public int installFunction(String str, Class<?> cls) {
        Integer functionID = getFunctionID(str);
        if (cls != null && !Function.class.isAssignableFrom(cls)) {
            throw new ClassCastException(cls.getName() + " cannot be cast to " + Function.class.getName());
        }
        if (functionID == null) {
            int i = this.m_funcNextFreeIndex;
            this.m_funcNextFreeIndex = i + 1;
            this.m_functions_customer[i - 38] = cls;
            this.m_functionID_customer.put(str, Integer.valueOf(i));
            return i;
        }
        int iIntValue = functionID.intValue();
        if (iIntValue < 38) {
            iIntValue = this.m_funcNextFreeIndex;
            this.m_funcNextFreeIndex = iIntValue + 1;
            this.m_functionID_customer.put(str, Integer.valueOf(iIntValue));
        }
        this.m_functions_customer[iIntValue - 38] = cls;
        return iIntValue;
    }
}
