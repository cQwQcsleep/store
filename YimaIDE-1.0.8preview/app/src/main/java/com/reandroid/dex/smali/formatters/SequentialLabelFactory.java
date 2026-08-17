package com.reandroid.dex.smali.formatters;

import com.reandroid.dex.ins.Label;
import com.reandroid.dex.smali.formatters.SequentialLabelFactory;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.collection.ArrayCollection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SequentialLabelFactory {
    private final Map<String, String> labelMap = new HashMap();

    private String dropSuffix(String str) {
        int iLastIndexOf;
        if (str.length() == 0 || str.charAt(0) != ':' || (iLastIndexOf = str.lastIndexOf(95)) <= 0) {
            return null;
        }
        return str.substring(0, iLastIndexOf + 1);
    }

    public static int getLabelSuffix(String str) {
        int iLastIndexOf;
        if (str.length() == 0 || str.charAt(0) != ':' || (iLastIndexOf = str.lastIndexOf(95)) <= 0) {
            return 0;
        }
        return HexUtil.decodeHex(str.substring(iLastIndexOf + 1), 0);
    }

    public void build(Iterator<? extends Label> it) {
        reset();
        HashMap map = new HashMap();
        while (it.hasNext()) {
            String labelName = it.next().getLabelName();
            String strDropSuffix = dropSuffix(labelName);
            if (strDropSuffix != null) {
                Set hashSet = (Set) map.get(strDropSuffix);
                if (hashSet == null) {
                    hashSet = new HashSet();
                    map.put(strDropSuffix, hashSet);
                }
                hashSet.add(labelName);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            build((String) entry.getKey(), (Set) entry.getValue());
        }
    }

    public String get(String str) {
        String str2 = this.labelMap.get(str);
        return str2 == null ? str : str2;
    }

    public void reset() {
        this.labelMap.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void build(String str, Set<String> set) {
        Map<String, String> map = this.labelMap;
        ArrayCollection arrayCollection = new ArrayCollection(set);
        arrayCollection.sort(new Comparator() { // from class: z6d
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return CompareUtil.compare(SequentialLabelFactory.getLabelSuffix((String) obj), SequentialLabelFactory.getLabelSuffix((String) obj2));
            }
        });
        int size = arrayCollection.size();
        for (int i = 0; i < size; i++) {
            map.put((String) arrayCollection.get(i), HexUtil.toHex(str, i, 1));
        }
    }
}
