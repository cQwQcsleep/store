package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class G50 {
    public static final boolean a(R50 r50, String str) {
        KB.c(r50, "<this>");
        KB.c(str, "path");
        List listA = AbstractC1679hg0.a(str, new char[]{DataResource.SEPARATOR}, 3);
        String strSubstring = (String) listA.get(1);
        String str2 = (String) listA.get(2);
        E50 e50 = E50.c;
        int iIndexOf = strSubstring.indexOf(45);
        if (iIndexOf != -1) {
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        if (!E50.s && strSubstring.indexOf(45) != -1) {
            x01.a(strSubstring);
            return false;
        }
        E50 e51 = (E50) E50.q.get(strSubstring);
        if (e51 != null) {
            String strB = AbstractC1679hg0.b(str2, '.');
            List list = (List) AbstractC0754Pq.b.get(e51);
            if (list == null) {
                list = Collections.EMPTY_LIST;
            }
            KB.b(list, "getRelatedResourceTypes(folderType)");
            ArrayList<U50> arrayList = new ArrayList();
            for (Object obj : list) {
                if (((U50) obj) != U50.ID) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (U50 u50 : arrayList) {
                KB.b(u50, "it");
                arrayList2.addAll(r50.a(u50, strB));
            }
            if (!arrayList2.isEmpty()) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    if (((W50) it.next()).a()) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
