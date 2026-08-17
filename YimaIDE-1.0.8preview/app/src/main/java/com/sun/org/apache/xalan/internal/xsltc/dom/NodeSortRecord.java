package com.sun.org.apache.xalan.internal.xsltc.dom;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.utils.StringComparable;
import java.text.Collator;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class NodeSortRecord {
    public static final int COMPARE_ASCENDING = 0;
    public static final int COMPARE_DESCENDING = 1;
    public static final int COMPARE_NUMERIC = 1;
    public static final int COMPARE_STRING = 0;

    @Deprecated
    private static final Collator DEFAULT_COLLATOR = Collator.getInstance();

    @Deprecated
    protected Collator _collator;
    protected Collator[] _collators;
    private DOM _dom;
    private int _last;

    @Deprecated
    protected Locale _locale;
    private int _node;
    private int _scanned;
    protected SortSettings _settings;
    private Object[] _values;

    public NodeSortRecord(int i) {
        this._collator = DEFAULT_COLLATOR;
        this._dom = null;
        this._last = 0;
        this._scanned = 0;
        this._node = i;
    }

    private final Double numericValue(int i) {
        Double dValueOf;
        if (this._scanned > i) {
            return (Double) this._values[i];
        }
        try {
            dValueOf = Double.valueOf(Double.parseDouble(extractValueFromDOM(this._dom, this._node, i, this._settings.getTranslet(), this._last)));
        } catch (NumberFormatException unused) {
            dValueOf = Double.valueOf(Double.NEGATIVE_INFINITY);
        }
        Object[] objArr = this._values;
        int i2 = this._scanned;
        this._scanned = i2 + 1;
        objArr[i2] = dValueOf;
        return dValueOf;
    }

    private final Comparable stringValue(int i) {
        if (this._scanned > i) {
            return (Comparable) this._values[i];
        }
        Comparable comparator = StringComparable.getComparator(extractValueFromDOM(this._dom, this._node, i, this._settings.getTranslet(), this._last), this._settings.getLocales()[i], this._collators[i], this._settings.getCaseOrders()[i]);
        Object[] objArr = this._values;
        int i2 = this._scanned;
        this._scanned = i2 + 1;
        objArr[i2] = comparator;
        return comparator;
    }

    public final int compareDocOrder(NodeSortRecord nodeSortRecord) {
        return this._node - nodeSortRecord._node;
    }

    public int compareTo(NodeSortRecord nodeSortRecord) {
        int[] sortOrders = this._settings.getSortOrders();
        int length = this._settings.getSortOrders().length;
        int[] types = this._settings.getTypes();
        for (int i = 0; i < length; i++) {
            int iCompareTo = types[i] == 1 ? numericValue(i).compareTo(nodeSortRecord.numericValue(i)) : stringValue(i).compareTo(nodeSortRecord.stringValue(i));
            if (iCompareTo != 0) {
                return sortOrders[i] == 1 ? 0 - iCompareTo : iCompareTo;
            }
        }
        return this._node - nodeSortRecord._node;
    }

    public abstract String extractValueFromDOM(DOM dom, int i, int i2, AbstractTranslet abstractTranslet, int i3);

    public Collator[] getCollator() {
        return this._collators;
    }

    public final int getNode() {
        return this._node;
    }

    public final void initialize(int i, int i2, DOM dom, SortSettings sortSettings) throws TransletException {
        this._dom = dom;
        this._node = i;
        this._last = i2;
        this._settings = sortSettings;
        this._values = new Object[sortSettings.getSortOrders().length];
        Collator[] collators = sortSettings.getCollators();
        this._collators = collators;
        this._collator = collators[0];
    }

    public NodeSortRecord() {
        this(0);
    }
}
