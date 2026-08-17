package org.eclipse.jdt.internal.compiler.apt.model;

import javax.lang.model.element.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class NameImpl implements Name {
    private final String _name;

    public NameImpl(CharSequence charSequence) {
        this._name = charSequence.toString();
    }

    public char charAt(int i) {
        return this._name.charAt(i);
    }

    public boolean contentEquals(CharSequence charSequence) {
        return this._name.equals(charSequence.toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this._name.equals(((NameImpl) obj)._name);
        }
        return false;
    }

    public int hashCode() {
        return this._name.hashCode();
    }

    public int length() {
        return this._name.length();
    }

    public CharSequence subSequence(int i, int i2) {
        return this._name.subSequence(i, i2);
    }

    public String toString() {
        return this._name;
    }

    private NameImpl() {
        this._name = null;
    }

    public NameImpl(char[] cArr) {
        this._name = String.valueOf(cArr);
    }
}
