package org.eclipse.tm4e.core.internal.parser;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface PropertySettable<V> {

    public static class ArrayList<T> extends java.util.ArrayList<T> implements PropertySettable<T> {
        private static final long serialVersionUID = 1;

        @Override // org.eclipse.tm4e.core.internal.parser.PropertySettable
        public void setProperty(String str, T t) {
            int i = Integer.parseInt(str);
            if (i == size()) {
                add(t);
            } else {
                set(i, t);
            }
        }
    }

    public static class HashMap<T> extends java.util.HashMap<String, T> implements PropertySettable<T> {
        private static final long serialVersionUID = 1;

        @Override // org.eclipse.tm4e.core.internal.parser.PropertySettable
        public void setProperty(String str, T t) {
            put(str, t);
        }
    }

    void setProperty(String str, V v);
}
