package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import java.text.Collator;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SortSettings {
    private String[] _caseOrders;
    private Collator[] _collators;
    private Locale[] _locales;
    private int[] _sortOrders;
    private AbstractTranslet _translet;
    private int[] _types;

    public SortSettings(AbstractTranslet abstractTranslet, int[] iArr, int[] iArr2, Locale[] localeArr, Collator[] collatorArr, String[] strArr) {
        this._translet = abstractTranslet;
        this._sortOrders = iArr;
        this._types = iArr2;
        this._locales = localeArr;
        this._collators = collatorArr;
        this._caseOrders = strArr;
    }

    public String[] getCaseOrders() {
        return this._caseOrders;
    }

    public Collator[] getCollators() {
        return this._collators;
    }

    public Locale[] getLocales() {
        return this._locales;
    }

    public int[] getSortOrders() {
        return this._sortOrders;
    }

    public AbstractTranslet getTranslet() {
        return this._translet;
    }

    public int[] getTypes() {
        return this._types;
    }
}
