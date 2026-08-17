package com.sun.org.apache.xml.internal.dtm.ref;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CustomStringPool extends DTMStringPool {
    public static final int NULL = -1;
    final Map<String, Integer> m_stringToInt = new HashMap();

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMStringPool
    public String indexToString(int i) throws IndexOutOfBoundsException {
        return this.m_intToString.get(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMStringPool
    public void removeAllElements() {
        this.m_intToString.clear();
        Map<String, Integer> map = this.m_stringToInt;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMStringPool
    public int stringToIndex(String str) {
        if (str == null) {
            return -1;
        }
        Integer numValueOf = this.m_stringToInt.get(str);
        if (numValueOf == null) {
            this.m_intToString.add(str);
            numValueOf = Integer.valueOf(this.m_intToString.size());
            this.m_stringToInt.put(str, numValueOf);
        }
        return numValueOf.intValue();
    }
}
