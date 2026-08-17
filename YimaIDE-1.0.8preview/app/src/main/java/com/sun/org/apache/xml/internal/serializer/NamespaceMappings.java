package com.sun.org.apache.xml.internal.serializer;

import com.sun.org.apache.xalan.internal.templates.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NamespaceMappings {
    private static final String EMPTYSTRING = "";
    private static final String XML_PREFIX = "xml";
    private int count;
    private HashMap<String, Stack<MappingRecord>> m_namespaces = new HashMap<>();
    private Stack<MappingRecord> m_nodeStack = new Stack<>();

    public class MappingRecord {
        final int m_declarationDepth;
        final String m_prefix;
        final String m_uri;

        public MappingRecord(String str, String str2, int i) {
            this.m_prefix = str;
            this.m_uri = str2;
            this.m_declarationDepth = i;
        }
    }

    public NamespaceMappings() {
        initNamespaces();
    }

    private void initNamespaces() {
        HashMap<String, Stack<MappingRecord>> map = this.m_namespaces;
        Stack<MappingRecord> stack = new Stack<>();
        map.put("", stack);
        stack.push(new MappingRecord("", "", 0));
        HashMap<String, Stack<MappingRecord>> map2 = this.m_namespaces;
        Stack<MappingRecord> stack2 = new Stack<>();
        map2.put("xml", stack2);
        stack2.push(new MappingRecord("xml", "http://www.w3.org/XML/1998/namespace", 0));
        this.m_nodeStack.push(new MappingRecord(null, null, -1));
    }

    public Object clone() throws CloneNotSupportedException {
        NamespaceMappings namespaceMappings = new NamespaceMappings();
        namespaceMappings.m_nodeStack = (Stack) this.m_nodeStack.clone();
        namespaceMappings.m_namespaces = (HashMap) this.m_namespaces.clone();
        namespaceMappings.count = this.count;
        return namespaceMappings;
    }

    public String generateNextPrefix() {
        StringBuilder sb = new StringBuilder(Constants.ATTRNAME_NS);
        int i = this.count;
        this.count = i + 1;
        sb.append(i);
        return sb.toString();
    }

    public MappingRecord getMappingFromPrefix(String str) {
        Stack<MappingRecord> stack = this.m_namespaces.get(str);
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    public MappingRecord getMappingFromURI(String str) {
        Iterator<String> it = this.m_namespaces.keySet().iterator();
        while (it.hasNext()) {
            MappingRecord mappingFromPrefix = getMappingFromPrefix(it.next());
            if (mappingFromPrefix != null && mappingFromPrefix.m_uri.equals(str)) {
                return mappingFromPrefix;
            }
        }
        return null;
    }

    public String lookupNamespace(String str) {
        Stack<MappingRecord> stack = this.m_namespaces.get(str);
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.peek().m_uri;
    }

    public String lookupPrefix(String str) {
        for (String str2 : this.m_namespaces.keySet()) {
            String strLookupNamespace = lookupNamespace(str2);
            if (strLookupNamespace != null && strLookupNamespace.equals(str)) {
                return str2;
            }
        }
        return null;
    }

    public boolean popNamespace(String str) {
        Stack<MappingRecord> stack;
        if (str.startsWith("xml") || (stack = this.m_namespaces.get(str)) == null) {
            return false;
        }
        stack.pop();
        return true;
    }

    public void popNamespaces(int i, ContentHandler contentHandler) {
        while (!this.m_nodeStack.isEmpty() && this.m_nodeStack.peek().m_declarationDepth >= i) {
            String str = this.m_nodeStack.pop().m_prefix;
            popNamespace(str);
            if (contentHandler != null) {
                try {
                    contentHandler.endPrefixMapping(str);
                } catch (SAXException unused) {
                }
            }
        }
    }

    public boolean pushNamespace(String str, String str2, int i) {
        if (str.startsWith("xml")) {
            return false;
        }
        Stack<MappingRecord> stack = this.m_namespaces.get(str);
        if (stack == null) {
            HashMap<String, Stack<MappingRecord>> map = this.m_namespaces;
            Stack<MappingRecord> stack2 = new Stack<>();
            map.put(str, stack2);
            stack = stack2;
        }
        if (!stack.empty() && str2.equals(stack.peek().m_uri)) {
            return false;
        }
        MappingRecord mappingRecord = new MappingRecord(str, str2, i);
        stack.push(mappingRecord);
        this.m_nodeStack.push(mappingRecord);
        return true;
    }

    public final void reset() {
        this.count = 0;
        this.m_namespaces.clear();
        this.m_nodeStack.clear();
        initNamespaces();
    }
}
