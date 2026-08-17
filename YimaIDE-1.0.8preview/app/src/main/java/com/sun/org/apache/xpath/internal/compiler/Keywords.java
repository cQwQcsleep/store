package com.sun.org.apache.xpath.internal.compiler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Keywords {
    private static final String FROM_ANCESTORS_OR_SELF_STRING = "ancestor-or-self";
    private static final String FROM_ANCESTORS_STRING = "ancestor";
    private static final String FROM_ATTRIBUTES_STRING = "attribute";
    private static final String FROM_CHILDREN_STRING = "child";
    private static final String FROM_DESCENDANTS_OR_SELF_STRING = "descendant-or-self";
    private static final String FROM_DESCENDANTS_STRING = "descendant";
    private static final String FROM_FOLLOWING_SIBLINGS_STRING = "following-sibling";
    private static final String FROM_FOLLOWING_STRING = "following";
    private static final String FROM_NAMESPACE_STRING = "namespace";
    private static final String FROM_PARENT_STRING = "parent";
    private static final String FROM_PRECEDING_SIBLINGS_STRING = "preceding-sibling";
    private static final String FROM_PRECEDING_STRING = "preceding";
    private static final String FROM_SELF_ABBREVIATED_STRING = ".";
    private static final String FROM_SELF_STRING = "self";
    public static final String FUNC_BOOLEAN_STRING = "boolean";
    public static final String FUNC_CEILING_STRING = "ceiling";
    public static final String FUNC_CONCAT_STRING = "concat";
    public static final String FUNC_CONTAINS_STRING = "contains";
    public static final String FUNC_COUNT_STRING = "count";
    public static final String FUNC_CURRENT_STRING = "current";
    public static final String FUNC_DOCLOCATION_STRING = "document-location";
    public static final String FUNC_EXT_ELEM_AVAILABLE_STRING = "element-available";
    public static final String FUNC_EXT_FUNCTION_AVAILABLE_STRING = "function-available";
    public static final String FUNC_FALSE_STRING = "false";
    public static final String FUNC_FLOOR_STRING = "floor";
    public static final String FUNC_GENERATE_ID_STRING = "generate-id";
    public static final String FUNC_HERE_STRING = "here";
    static final String FUNC_ID_STRING = "id";
    public static final String FUNC_KEY_STRING = "key";
    public static final String FUNC_LANG_STRING = "lang";
    public static final String FUNC_LAST_STRING = "last";
    public static final String FUNC_LOCAL_PART_STRING = "local-name";
    public static final String FUNC_NAMESPACE_STRING = "namespace-uri";
    public static final String FUNC_NAME_STRING = "name";
    public static final String FUNC_NORMALIZE_SPACE_STRING = "normalize-space";
    public static final String FUNC_NOT_STRING = "not";
    public static final String FUNC_NUMBER_STRING = "number";
    public static final String FUNC_POSITION_STRING = "position";
    public static final String FUNC_ROUND_STRING = "round";
    public static final String FUNC_STARTS_WITH_STRING = "starts-with";
    public static final String FUNC_STRING_LENGTH_STRING = "string-length";
    public static final String FUNC_STRING_STRING = "string";
    public static final String FUNC_SUBSTRING_AFTER_STRING = "substring-after";
    public static final String FUNC_SUBSTRING_BEFORE_STRING = "substring-before";
    public static final String FUNC_SUBSTRING_STRING = "substring";
    public static final String FUNC_SUM_STRING = "sum";
    public static final String FUNC_SYSTEM_PROPERTY_STRING = "system-property";
    public static final String FUNC_TRANSLATE_STRING = "translate";
    public static final String FUNC_TRUE_STRING = "true";
    public static final String FUNC_UNPARSED_ENTITY_URI_STRING = "unparsed-entity-uri";
    private static final String NODETYPE_ANYELEMENT_STRING = "*";
    private static final String NODETYPE_COMMENT_STRING = "comment";
    private static final String NODETYPE_NODE_STRING = "node";
    private static final String NODETYPE_PI_STRING = "processing-instruction";
    private static final String NODETYPE_TEXT_STRING = "text";
    private static final Map<String, Integer> m_axisnames;
    private static final Map<String, Integer> m_keywords;
    private static final Map<String, Integer> m_nodetests;
    private static final Map<String, Integer> m_nodetypes;

    static {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        map2.put("ancestor", 37);
        map2.put(FROM_ANCESTORS_OR_SELF_STRING, 38);
        map2.put("attribute", 39);
        map2.put(FROM_CHILDREN_STRING, 40);
        map2.put(FROM_DESCENDANTS_STRING, 41);
        map2.put(FROM_DESCENDANTS_OR_SELF_STRING, 42);
        map2.put(FROM_FOLLOWING_STRING, 43);
        map2.put(FROM_FOLLOWING_SIBLINGS_STRING, 44);
        map2.put(FROM_PARENT_STRING, 45);
        map2.put(FROM_PRECEDING_STRING, 46);
        map2.put(FROM_PRECEDING_SIBLINGS_STRING, 47);
        map2.put(FROM_SELF_STRING, 48);
        map2.put("namespace", 49);
        m_axisnames = Collections.unmodifiableMap(map2);
        Integer numValueOf = Integer.valueOf(OpCodes.NODETYPE_COMMENT);
        map4.put("comment", numValueOf);
        Integer numValueOf2 = Integer.valueOf(OpCodes.NODETYPE_TEXT);
        map4.put("text", numValueOf2);
        Integer numValueOf3 = Integer.valueOf(OpCodes.NODETYPE_PI);
        map4.put("processing-instruction", numValueOf3);
        Integer numValueOf4 = Integer.valueOf(OpCodes.NODETYPE_NODE);
        map4.put("node", numValueOf4);
        map4.put("*", 36);
        m_nodetypes = Collections.unmodifiableMap(map4);
        map.put(".", 48);
        map.put("id", 4);
        map.put("key", 5);
        m_keywords = Collections.unmodifiableMap(map);
        map3.put("comment", numValueOf);
        map3.put("text", numValueOf2);
        map3.put("processing-instruction", numValueOf3);
        map3.put("node", numValueOf4);
        m_nodetests = Collections.unmodifiableMap(map3);
    }

    public static Integer getAxisName(String str) {
        return m_axisnames.get(str);
    }

    public static Integer getKeyWord(String str) {
        return m_keywords.get(str);
    }

    public static Integer getNodeType(String str) {
        return m_nodetypes.get(str);
    }

    public static Integer lookupNodeTest(String str) {
        return m_nodetests.get(str);
    }
}
