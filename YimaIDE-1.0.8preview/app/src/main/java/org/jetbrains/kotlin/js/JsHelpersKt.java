package org.jetbrains.kotlin.js;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"validateQualifier", "", "qualifier", "", "org.jetbrains.kotlin:compiler.common.web"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JsHelpersKt {
    public static final boolean validateQualifier(String str) {
        str.getClass();
        List listSplit$default = StringsKt.split$default(str, new char[]{AbiCompoundName.SEPARATOR}, false, 0, 6, (Object) null);
        if (listSplit$default.isEmpty()) {
            return false;
        }
        List<String> list = listSplit$default;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        for (String str2 : list) {
            if (str2.length() > 0 && Character.isJavaIdentifierStart(str2.charAt(0))) {
                String strDrop = StringsKt.drop(str2, 1);
                for (int i = 0; i < strDrop.length(); i++) {
                    if (Character.isJavaIdentifierPart(strDrop.charAt(i))) {
                    }
                }
            }
            return false;
        }
        return true;
    }
}
