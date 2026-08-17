package org.eclipse.tm4e.core.internal.grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.fusesource.jansi.AnsiRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ScopeStack {
    public final ScopeStack parent;
    public final String scopeName;

    public ScopeStack(ScopeStack scopeStack, String str) {
        this.parent = scopeStack;
        this.scopeName = str;
    }

    public static ScopeStack from(List<String> list) {
        ScopeStack scopeStack = null;
        int i = 0;
        while (i < list.size()) {
            ScopeStack scopeStack2 = new ScopeStack(scopeStack, list.get(i));
            i++;
            scopeStack = scopeStack2;
        }
        return scopeStack;
    }

    public static ScopeStack push(ScopeStack scopeStack, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            scopeStack = new ScopeStack(scopeStack, it.next());
        }
        return scopeStack;
    }

    public List<String> getExtensionIfDefined(ScopeStack scopeStack) {
        ArrayList arrayList = new ArrayList();
        while (this != null && this != scopeStack) {
            arrayList.add(this.scopeName);
            this = this.parent;
        }
        if (this != scopeStack) {
            return Collections.EMPTY_LIST;
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public List<String> getSegments() {
        ArrayList arrayList = new ArrayList();
        while (this != null) {
            arrayList.add(this.scopeName);
            this = this.parent;
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public boolean isExtending(ScopeStack scopeStack) {
        if (this == scopeStack) {
            return true;
        }
        ScopeStack scopeStack2 = this.parent;
        if (scopeStack2 == null) {
            return false;
        }
        return scopeStack2.isExtending(scopeStack);
    }

    public String toString() {
        return String.join(AnsiRenderer.CODE_TEXT_SEPARATOR, getSegments());
    }

    public static ScopeStack from(String... strArr) {
        ScopeStack scopeStack = null;
        int i = 0;
        while (i < strArr.length) {
            ScopeStack scopeStack2 = new ScopeStack(scopeStack, strArr[i]);
            i++;
            scopeStack = scopeStack2;
        }
        return scopeStack;
    }

    public ScopeStack push(String str) {
        return new ScopeStack(this, str);
    }

    public static ScopeStack from(String str) {
        return new ScopeStack(null, str);
    }
}
