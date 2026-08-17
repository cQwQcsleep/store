package org.eclipse.tm4e.core.internal.theme;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.tm4e.core.TMException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ColorMap {
    private final Map<String, Integer> _color2id;
    private final List<String> _id2color;
    private final boolean _isFrozen;
    private int _lastColorId;

    public ColorMap(List<String> list) {
        this._lastColorId = -1;
        this._id2color = new ArrayList();
        this._color2id = new LinkedHashMap();
        if (list == null) {
            this._isFrozen = false;
            return;
        }
        this._isFrozen = true;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this._color2id.put(list.get(i), Integer.valueOf(i));
            this._id2color.add(list.get(i));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ColorMap) {
            ColorMap colorMap = (ColorMap) obj;
            if (this._lastColorId == colorMap._lastColorId && this._color2id.equals(colorMap._color2id)) {
                return true;
            }
        }
        return false;
    }

    public String getColor(int i) {
        return this._id2color.get(i);
    }

    public List<String> getColorMap() {
        return new ArrayList(this._color2id.keySet());
    }

    public int getId(String str) {
        if (str == null) {
            return 0;
        }
        String upperCase = str.toUpperCase();
        Integer num = this._color2id.get(upperCase);
        if (num != null) {
            return num.intValue();
        }
        if (this._isFrozen) {
            throw new TMException("Missing color in color map - " + upperCase);
        }
        int i = this._lastColorId + 1;
        this._lastColorId = i;
        this._color2id.put(upperCase, Integer.valueOf(i));
        int size = this._id2color.size();
        List<String> list = this._id2color;
        if (i >= size) {
            list.add(upperCase);
            return i;
        }
        list.set(i, upperCase);
        return i;
    }

    public int hashCode() {
        return ((this._lastColorId + 31) * 31) + this._color2id.hashCode();
    }

    public ColorMap() {
        this(null);
    }
}
