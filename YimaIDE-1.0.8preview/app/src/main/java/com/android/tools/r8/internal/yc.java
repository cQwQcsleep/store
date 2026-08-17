package com.android.tools.r8.internal;

import java.util.LinkedHashMap;
import java.util.List;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class yc {
    public static final String a = ie.a(ke.a(new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", (String) null, (String) null, (er) null, 62);
    public static final LinkedHashMap b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listA = ke.a(new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", "J", "Double", "D"});
        int iA = VY.a(0, listA.size() - 1, 2);
        if (iA >= 0) {
            int i = 0;
            while (true) {
                StringBuilder sb = new StringBuilder();
                String str = a;
                sb.append(str);
                sb.append(AbiQualifiedName.SEPARATOR);
                sb.append((String) listA.get(i));
                int i2 = i + 1;
                linkedHashMap.put(sb.toString(), listA.get(i2));
                linkedHashMap.put(str + AbiQualifiedName.SEPARATOR + ((String) listA.get(i)) + "Array", "[" + ((String) listA.get(i2)));
                if (i == iA) {
                    break;
                } else {
                    i += 2;
                }
            }
        }
        linkedHashMap.put(a + "/Unit", "V");
        a(linkedHashMap, "Any", "java/lang/Object");
        a(linkedHashMap, "Nothing", "java/lang/Void");
        a(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : ke.a(new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"})) {
            a(linkedHashMap, str2, "java/lang/" + str2);
        }
        for (String str3 : ke.a(new String[]{"Iterator", "Collection", "List", "Set", "Map", "ListIterator"})) {
            a(linkedHashMap, F40.a("collections/", str3), "java/util/" + str3);
            a(linkedHashMap, "collections/Mutable" + str3, "java/util/" + str3);
        }
        a(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        a(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int i3 = 0; i3 < 23; i3++) {
            String strA = CX.a(i3, "Function");
            StringBuilder sb2 = new StringBuilder();
            String str4 = a;
            sb2.append(str4);
            sb2.append("/jvm/functions/Function");
            sb2.append(i3);
            a(linkedHashMap, strA, sb2.toString());
            a(linkedHashMap, "reflect/KFunction" + i3, str4 + "/reflect/KFunction");
        }
        for (String str5 : ke.a(new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum"})) {
            a(linkedHashMap, str5 + ".Companion", a + "/jvm/internal/" + str5 + "CompanionObject");
        }
        b = linkedHashMap;
    }

    public static final void a(LinkedHashMap linkedHashMap, String str, String str2) {
        linkedHashMap.put(a + AbiQualifiedName.SEPARATOR + str, "L" + str2 + ';');
    }
}
