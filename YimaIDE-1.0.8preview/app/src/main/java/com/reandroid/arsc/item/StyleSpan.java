package com.reandroid.arsc.item;

import com.reandroid.arsc.pool.StringPool;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.xml.Span;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StyleSpan extends BlockItem implements Span, JSONConvert<JSONObject> {
    private final IndirectInteger firstChar;
    private final IndirectInteger lastChar;
    private final SpanStringReference stringReference;
    public static final String NAME_tag = ObjectsUtil.of("tag");
    public static final String NAME_first = ObjectsUtil.of("first");
    public static final String NAME_last = ObjectsUtil.of("last");

    public static class SpanStringReference extends ReferenceBlock<StyleSpan> {
        public SpanStringReference(StyleSpan styleSpan) {
            super(styleSpan, 0);
            set(-1);
        }

        private StringItem getStringItem() {
            StringPool<?> stringPool = getStringPool();
            if (stringPool != null) {
                return stringPool.get(get());
            }
            return null;
        }

        private StringPool<?> getStringPool() {
            return (StringPool) getBlock().getParentInstance(StringPool.class);
        }

        public String getString() {
            StringItem stringItem = getStringItem();
            if (stringItem != null) {
                return stringItem.get();
            }
            return null;
        }

        public void link() {
            StringItem stringItem = getStringItem();
            if (stringItem != null) {
                stringItem.addReference(this);
            }
        }

        public void setString(String str) {
            unlink();
            StringItem orCreate = getStringPool().getOrCreate(str);
            set(orCreate.getIndex());
            orCreate.addReference(this);
        }

        @Override // com.reandroid.arsc.item.ReferenceBlock
        public String toString() {
            String string = getString();
            if (string != null) {
                return string;
            }
            return "NULL{" + get() + "}";
        }

        public void unlink() {
            StringItem stringItem = getStringItem();
            if (stringItem != null) {
                stringItem.removeReference(this);
            }
        }
    }

    public StyleSpan() {
        super(12);
        this.stringReference = new SpanStringReference(this);
        this.firstChar = new IndirectInteger(this, 4);
        this.lastChar = new IndirectInteger(this, 8);
    }

    public void fromJson(JSONObject jSONObject) {
        setString(jSONObject.getString(NAME_tag));
        setFirstChar(jSONObject.getInt(NAME_first));
        setLastChar(jSONObject.getInt(NAME_last));
    }

    public int getFirstChar() {
        return this.firstChar.get();
    }

    public int getLastChar() {
        return this.lastChar.get();
    }

    public String getSpanAttributes() {
        return Span.splitAttribute(getString());
    }

    public int getSpanOrder() {
        return getIndex();
    }

    public String getString() {
        return this.stringReference.getString();
    }

    public String getTagName() {
        return Span.splitTagName(getString());
    }

    public void link() {
        this.stringReference.link();
    }

    public void onRemoved() {
        this.stringReference.unlink();
        this.stringReference.set(-1);
    }

    public void setFirstChar(int i) {
        this.firstChar.set(i);
    }

    public void setLastChar(int i) {
        this.lastChar.set(i);
    }

    public void setString(String str) {
        this.stringReference.setString(str);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NAME_tag, getString());
        jSONObject.put(NAME_first, getFirstChar());
        jSONObject.put(NAME_last, getLastChar());
        return jSONObject;
    }

    public String toString() {
        return this.stringReference + " [" + getFirstChar() + ", " + getLastChar() + "]";
    }
}
