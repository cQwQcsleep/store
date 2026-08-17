package org.jetbrains.kotlin.js.backend.ast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.kotlin.js.util.Maps;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public abstract class JsScope {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String description;
    private Map<String, JsName> names;
    private final JsScope parent;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 8 || i == 10) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 8 || i == 10) ? 2 : 3];
        switch (i) {
            case 2:
                objArr[0] = "identifier";
                break;
            case 3:
            case 8:
            case 10:
                objArr[0] = "org/jetbrains/kotlin/js/backend/ast/JsScope";
                break;
            case 4:
                objArr[0] = "suggestedName";
                break;
            case 5:
            case 9:
            case 11:
                objArr[0] = "ident";
                break;
            case 6:
            case 7:
                objArr[0] = "name";
                break;
            default:
                objArr[0] = "description";
                break;
        }
        if (i == 3) {
            objArr[1] = "declareName";
        } else if (i == 8) {
            objArr[1] = "getDescription";
        } else if (i != 10) {
            objArr[1] = "org/jetbrains/kotlin/js/backend/ast/JsScope";
        } else {
            objArr[1] = "doCreateName";
        }
        switch (i) {
            case 2:
                objArr[2] = "declareName";
                break;
            case 3:
            case 8:
            case 10:
                break;
            case 4:
                objArr[2] = "declareTemporaryName";
                break;
            case 5:
                objArr[2] = "findName";
                break;
            case 6:
                objArr[2] = "hasOwnName";
                break;
            case 7:
                objArr[2] = "hasName";
                break;
            case 9:
                objArr[2] = "doCreateName";
                break;
            case 11:
                objArr[2] = "findOwnName";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 8 && i != 10) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public JsScope(String str) {
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        this.names = Collections.EMPTY_MAP;
        this.description = str;
        this.parent = null;
    }

    public static JsName declareTemporaryName(String str) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        return new JsName(str, true);
    }

    public void copyOwnNames(JsScope jsScope) {
        if (jsScope.names.isEmpty()) {
            return;
        }
        HashMap map = new HashMap(this.names);
        this.names = map;
        map.putAll(jsScope.names);
    }

    public JsName declareName(String str) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        JsName jsNameFindOwnName = findOwnName(str);
        if (jsNameFindOwnName == null) {
            jsNameFindOwnName = doCreateName(str);
        }
        if (jsNameFindOwnName == null) {
            $$$reportNull$$$0(3);
        }
        return jsNameFindOwnName;
    }

    public JsName doCreateName(String str) {
        if (str == null) {
            $$$reportNull$$$0(9);
        }
        JsName jsName = new JsName(str, false);
        this.names = Maps.put(this.names, str, jsName);
        return jsName;
    }

    public final JsName findName(String str) {
        JsScope jsScope;
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        JsName jsNameFindOwnName = findOwnName(str);
        return (jsNameFindOwnName != null || (jsScope = this.parent) == null) ? jsNameFindOwnName : jsScope.findName(str);
    }

    public JsName findOwnName(String str) {
        if (str == null) {
            $$$reportNull$$$0(11);
        }
        return this.names.get(str);
    }

    public String getDescription() {
        String str = this.description;
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        return str;
    }

    public final JsScope getParent() {
        return this.parent;
    }

    public boolean hasOwnName(String str) {
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        return this.names.containsKey(str);
    }

    public final String toString() {
        JsScope jsScope = this.parent;
        String str = this.description;
        if (jsScope == null) {
            return str;
        }
        return str + "->" + this.parent;
    }

    public JsScope(JsScope jsScope, String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.names = Collections.EMPTY_MAP;
        this.description = str;
        this.parent = jsScope;
    }
}
