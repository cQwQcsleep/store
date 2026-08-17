package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.coder.ThreeByteCharsetDecoder;
import com.reandroid.arsc.coder.XmlSanitizer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ReferenceItem;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.list.StringItemList;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsStore;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.xml.StyleDocument;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StringItem extends StringBlock implements JSONConvert<JSONObject>, Comparable<StringItem> {
    private Object mReferencedList;
    private StyleItem mStyleItem;
    private boolean mUtf8;
    private static final CharsetDecoder UTF16LE_DECODER = StandardCharsets.UTF_16LE.newDecoder();
    private static final CharsetDecoder DECODER_3B = ThreeByteCharsetDecoder.INSTANCE;
    public static final String NAME_string = ObjectsUtil.of("string");
    public static final String NAME_style = ObjectsUtil.of("style");

    public StringItem(boolean z) {
        this.mUtf8 = z;
    }

    private static byte[] addBytes(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int length;
        if (bArr == null && bArr2 == null && bArr3 == null) {
            return null;
        }
        int length2 = bArr != null ? bArr.length : 0;
        if (bArr2 != null) {
            length2 += bArr2.length;
        }
        if (bArr3 != null) {
            length2 += bArr3.length;
        }
        byte[] bArr4 = new byte[length2];
        if (bArr != null) {
            length = bArr.length;
            System.arraycopy(bArr, 0, bArr4, 0, length);
        } else {
            length = 0;
        }
        if (bArr2 != null) {
            System.arraycopy(bArr2, 0, bArr4, length, bArr2.length);
            length += bArr2.length;
        }
        if (bArr3 != null) {
            System.arraycopy(bArr3, 0, bArr4, length, bArr3.length);
        }
        return bArr4;
    }

    private void clearStyle() {
        StyleItem style = getStyle();
        if (style != null) {
            style.clearStyle();
        }
    }

    private String decodeString(byte[] bArr, boolean z) {
        if (!isNullBytes(bArr)) {
            int[] iArrDecodeUtf8StringByteLength = z ? decodeUtf8StringByteLength(bArr) : decodeUtf16StringByteLength(bArr);
            try {
                return (z ? StringBlock.UTF8_DECODER : UTF16LE_DECODER).decode(ByteBuffer.wrap(bArr, iArrDecodeUtf8StringByteLength[0], iArrDecodeUtf8StringByteLength[1])).toString();
            } catch (CharacterCodingException unused) {
                return z ? tryThreeByteDecoder(bArr, iArrDecodeUtf8StringByteLength[0], iArrDecodeUtf8StringByteLength[1]) : new String(bArr, iArrDecodeUtf8StringByteLength[0], iArrDecodeUtf8StringByteLength[1], StandardCharsets.UTF_16LE);
            }
        }
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return XmlPullParser.NO_NAMESPACE;
    }

    private static int[] decodeUtf16StringByteLength(byte[] bArr) {
        int i = ((bArr[1] & 255) << 8) | (bArr[0] & 255);
        return (32768 & i) != 0 ? new int[]{4, (((i & 32767) << 16) + ((bArr[3] & 255) << 8) + (bArr[2] & 255)) * 2} : new int[]{2, i * 2};
    }

    private static int[] decodeUtf8StringByteLength(byte[] bArr) {
        int i = (bArr[0] & 128) != 0 ? 2 : 1;
        int i2 = bArr[i];
        int i3 = i + 1;
        if ((i2 & 128) != 0) {
            i2 = ((i2 & 127) << 8) + (bArr[i3] & 255);
            i3 = i + 2;
        }
        return new int[]{i3, i2};
    }

    private static byte[] encodeUtf16ToBytes(String str) {
        byte[] bytesInternal;
        if (str == null) {
            return null;
        }
        byte[] utf16Bytes = getUtf16Bytes(str);
        int length = utf16Bytes.length / 2;
        if ((length & (-32768)) != 0) {
            int i = length & 255;
            int i2 = length - i;
            int i3 = i2 & 65280;
            int i4 = i2 - i3;
            bytesInternal = new byte[]{(byte) i, (byte) (((i4 & 65280) >> 8) | 128), (byte) i, (byte) (i3 >> 8)};
            int i5 = i4 & 255;
        } else {
            bytesInternal = new ShortItem((short) length).getBytesInternal();
        }
        return addBytes(bytesInternal, utf16Bytes, new byte[2]);
    }

    private static byte[] encodeUtf8ToBytes(String str) {
        byte[] bArr;
        byte[] bytesInternal;
        byte[] bArr2 = new byte[2];
        if (str != null) {
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;
            if ((65408 & length) != 0) {
                int i = length & 255;
                byte[] bArr3 = {(byte) (i | 128), (byte) i, (byte) (((length - i) >> 8) | 128), (byte) i};
                int length2 = str.length();
                int i2 = length2 & 255;
                int i3 = (length2 - i2) >> 8;
                bytesInternal = bArr3;
            } else {
                bytesInternal = new ShortItem((short) length).getBytesInternal();
                bytesInternal[1] = bytesInternal[0];
                bytesInternal[0] = (byte) str.length();
            }
            bArr = bytes;
            bArr2 = bytesInternal;
        } else {
            bArr = new byte[0];
        }
        return addBytes(bArr2, bArr, new byte[1]);
    }

    public static byte[] getUtf16Bytes(String str) {
        return str.getBytes(StandardCharsets.UTF_16LE);
    }

    public static boolean isNullBytes(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        int length = bArr.length;
        if (length < 2) {
            return true;
        }
        for (int i = 2; i < length; i++) {
            if (bArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isTransferable(ReferenceItem referenceItem) {
        return !(referenceItem instanceof WeakStringReference);
    }

    public static /* synthetic */ boolean k(Object obj) {
        return !(obj instanceof StyleItem.StyleIndexReference);
    }

    public static /* synthetic */ Block o(Class cls, Predicate predicate, ReferenceItem referenceItem) {
        Block referredParent = referenceItem.getReferredParent(cls);
        if (referredParent == null) {
            return null;
        }
        if (predicate == null || predicate.test(referredParent)) {
            return referredParent;
        }
        return null;
    }

    private void reUpdateReferences(int i) {
        Iterator itClonedIterator = ObjectsStore.clonedIterator(this.mReferencedList);
        while (itClonedIterator.hasNext()) {
            ((ReferenceItem) itClonedIterator.next()).set(i);
        }
    }

    private String tryThreeByteDecoder(byte[] bArr, int i, int i2) {
        try {
            return DECODER_3B.decode(ByteBuffer.wrap(bArr, i, i2)).toString();
        } catch (CharacterCodingException unused) {
            return new String(bArr, i, i2, StandardCharsets.UTF_8);
        }
    }

    public void addReference(ReferenceItem referenceItem) {
        if (referenceItem != null) {
            this.mReferencedList = ObjectsStore.add(this.mReferencedList, referenceItem);
            int index = getIndex();
            if (referenceItem.get() != index) {
                referenceItem.set(index);
            }
        }
    }

    public int calculateReadLength(BlockReader blockReader) throws IOException {
        if (blockReader.available() < 4) {
            return blockReader.available();
        }
        byte[] bArr = new byte[4];
        blockReader.readFully(bArr);
        blockReader.offset(-4);
        int[] iArrDecodeUtf8StringByteLength = isUtf8() ? decodeUtf8StringByteLength(bArr) : decodeUtf16StringByteLength(bArr);
        return iArrDecodeUtf8StringByteLength[0] + iArrDecodeUtf8StringByteLength[1] + (isUtf8() ? 1 : 2);
    }

    public boolean canMerge(StringItem stringItem) {
        if (stringItem != null && stringItem != this) {
            Block block = (Block) getParentInstance(StringItemList.class);
            Block block2 = (Block) stringItem.getParentInstance(StringItemList.class);
            if (block != null && block2 != null && block != block2) {
                return true;
            }
        }
        return false;
    }

    public void clearReferences() {
        this.mReferencedList = ObjectsStore.clear(this.mReferencedList);
    }

    public int compareReferences(StringItem stringItem) {
        return CompareUtil.compare(stringItem.getReferencesSize(), getReferencesSize());
    }

    public int compareStringValue(StringItem stringItem) {
        int iCompare = CompareUtil.compare(hasStyle(), stringItem.hasStyle()) * (-1);
        return iCompare != 0 ? iCompare : CompareUtil.compare(get(), stringItem.get());
    }

    @Override // java.lang.Comparable
    public int compareTo(StringItem stringItem) {
        if (stringItem == null) {
            return -1;
        }
        if (stringItem == this) {
            return 0;
        }
        int iCompareStringValue = compareStringValue(stringItem);
        return iCompareStringValue != 0 ? iCompareStringValue : compareReferences(stringItem);
    }

    @Override // com.reandroid.arsc.item.StringBlock
    public byte[] encodeString(String str) {
        return this.mUtf8 ? encodeUtf8ToBytes(str) : encodeUtf16ToBytes(str);
    }

    public void ensureStringLinkUnlocked() {
        StringPool stringPool = (StringPool) getParentInstance(StringPool.class);
        if (stringPool != null) {
            stringPool.ensureStringLinkUnlockedInternal();
        }
    }

    public boolean equalsValue(StyleDocument styleDocument) {
        if (styleDocument == null) {
            return isNull();
        }
        if (hasStyle()) {
            return styleDocument.equals(getStyleDocument());
        }
        if (styleDocument.hasElements()) {
            return false;
        }
        return ObjectsUtil.equals(getXml(), styleDocument.getXml());
    }

    public String getHtml() {
        String str = get();
        if (str == null) {
            return null;
        }
        StyleItem style = getStyle();
        return style == null ? str : style.applyStyle(str, false, false);
    }

    public StyleItem getOrCreateStyle() {
        StyleItem style = getStyle();
        if (style != null) {
            return style;
        }
        linkStyleItemInternal((StyleItem) ((StringPool) getParentInstance(StringPool.class)).getStyleArray().createNext());
        return getStyle();
    }

    public Iterator<ReferenceItem> getReferences() {
        ensureStringLinkUnlocked();
        return ObjectsStore.iterator(this.mReferencedList);
    }

    public int getReferencesSize() {
        return ObjectsStore.size(this.mReferencedList);
    }

    public StyleItem getStyle() {
        return this.mStyleItem;
    }

    public StyleDocument getStyleDocument() {
        if (hasStyle()) {
            return getStyle().build(get());
        }
        return null;
    }

    public <T extends Block> Iterator<T> getUsers(final Class<T> cls, final Predicate<T> predicate) {
        return ComputeIterator.of(getReferences(), new Function() { // from class: kqd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StringItem.o(cls, predicate, (ReferenceItem) obj);
            }
        });
    }

    public String getXml(boolean z) {
        String str = get();
        if (str == null) {
            return null;
        }
        StyleItem style = getStyle();
        return style == null ? str : style.applyStyle(str, true, z);
    }

    public boolean hasReference() {
        ensureStringLinkUnlocked();
        return ObjectsStore.containsIf(this.mReferencedList, new Predicate() { // from class: lqd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return StringItem.k(obj);
            }
        });
    }

    public boolean hasStyle() {
        StyleItem style = getStyle();
        if (style != null) {
            return style.hasSpans();
        }
        return false;
    }

    public boolean isUtf8() {
        return this.mUtf8;
    }

    public void linkStyleItemInternal(StyleItem styleItem) {
        if (styleItem == null) {
            x0e.a("Can not link null style item");
            return;
        }
        StyleItem styleItem2 = this.mStyleItem;
        if (styleItem2 == styleItem) {
            return;
        }
        if (styleItem2 != null) {
            k2d.a("Style item is already linked");
        } else {
            this.mStyleItem = styleItem;
            styleItem.setStringItemInternal(this);
        }
    }

    public boolean merge(StringItem stringItem) {
        if (!canMerge(stringItem)) {
            return false;
        }
        clearStyle();
        set(stringItem.get(), false);
        StyleItem style = stringItem.getStyle();
        if (style != null && style.hasSpans()) {
            getOrCreateStyle().merge(style);
        }
        onStringChanged(null, getXml());
        return true;
    }

    @Override // com.reandroid.arsc.base.Block
    public void onIndexChanged(int i, int i2) {
        reUpdateReferences(i2);
    }

    @Override // com.reandroid.arsc.item.BlockItem, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (blockReader.available() < 4) {
            return;
        }
        setBytesLength(calculateReadLength(blockReader), false);
        blockReader.readFully(getBytesInternal());
        onBytesChanged();
    }

    public void onRemoved() {
        clearStyle();
        setParent(null);
    }

    @Override // com.reandroid.arsc.item.StringBlock
    public void onStringChanged(String str, String str2) {
        super.onStringChanged(str, str2);
        StringItemList stringItemList = (StringItemList) getParentInstance(StringItemList.class);
        if (stringItemList != null) {
            stringItemList.onStringChanged(str, this);
        }
    }

    public void removeReference(ReferenceItem referenceItem) {
        this.mReferencedList = ObjectsStore.remove(this.mReferencedList, referenceItem);
    }

    public void serializeAttribute(XmlSerializer xmlSerializer, String str, String str2) throws IOException {
        String str3 = get();
        if (str3 == null) {
            str3 = XmlPullParser.NO_NAMESPACE;
        }
        xmlSerializer.attribute(str, str2, XmlSanitizer.escapeSpecialCharacter(str3));
    }

    public void serializeText(XmlSerializer xmlSerializer, boolean z) throws IOException {
        String str = get();
        if (str == null) {
            return;
        }
        xmlSerializer.text(z ? XmlSanitizer.escapeDecodedValue(str) : XmlSanitizer.escapeSpecialCharacter(str));
    }

    public void set(JSONObject jSONObject) {
        String xml = getXml();
        if (countBytes() == 0) {
            xml = null;
        }
        clearStyle();
        set(jSONObject.getString(NAME_string), false);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(NAME_style);
        if (jSONObjectOptJSONObject != null) {
            getOrCreateStyle().fromJson(jSONObjectOptJSONObject);
        }
        onStringChanged(xml, getXml());
    }

    public void setUtf8(boolean z) {
        if (z != this.mUtf8) {
            this.mUtf8 = z;
            if (countBytes() != 0) {
                writeStringBytes(get());
            }
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NAME_string, get());
        if (hasStyle()) {
            jSONObject.put(NAME_style, getStyle().toJson());
        }
        return jSONObject;
    }

    @Override // com.reandroid.arsc.item.StringBlock
    public String toString() {
        String xml = getXml();
        if (xml == null) {
            return getIndex() + ": NULL";
        }
        StringPool stringPool = (StringPool) getParentInstance(StringPool.class);
        if (stringPool == null || stringPool.isStringLinkLocked()) {
            return getIndex() + ":" + xml;
        }
        return getIndex() + ": USED BY=" + getReferencesSize() + "{" + xml + "}";
    }

    public void transferReferences(StringItem stringItem) {
        if (stringItem == this || stringItem == null || getParent() != stringItem.getParent() || getIndex() < 0 || stringItem.getIndex() < 0) {
            return;
        }
        Iterator itClonedIterator = ObjectsStore.clonedIterator(stringItem.mReferencedList);
        while (itClonedIterator.hasNext()) {
            ReferenceItem referenceItem = (ReferenceItem) itClonedIterator.next();
            if (isTransferable(referenceItem)) {
                stringItem.removeReference(referenceItem);
                addReference(referenceItem);
            }
        }
    }

    public void unlinkStyleItemInternal(StyleItem styleItem) {
        StyleItem styleItem2 = this.mStyleItem;
        if (styleItem2 == null) {
            return;
        }
        if (styleItem != styleItem2) {
            k2d.a("Wrong style item");
        } else {
            this.mStyleItem = null;
            styleItem.setStringItemInternal(null);
        }
    }

    public void fromJson(JSONObject jSONObject) {
        set(jSONObject);
    }

    public <T extends Block> Iterator<T> getUsers(Class<T> cls) {
        return getUsers(cls, null);
    }

    public String getXml() {
        return getXml(false);
    }

    public void serializeText(XmlSerializer xmlSerializer) throws IOException {
        serializeText(xmlSerializer, false);
    }

    public boolean equalsValue(String str) {
        if (str == null) {
            return isNull();
        }
        return str.equals(getXml());
    }

    public void set(StyleDocument styleDocument) {
        String xml = getXml();
        if (countBytes() == 0) {
            xml = null;
        }
        clearStyle();
        set(styleDocument.getStyledString(), false);
        if (styleDocument.hasElements()) {
            getOrCreateStyle().parse(styleDocument);
        }
        onStringChanged(xml, getXml());
    }

    @Override // com.reandroid.arsc.item.StringBlock
    public void set(String str) {
        StyleItem style;
        boolean z = str == null;
        setNull(z);
        if (z && (style = getStyle()) != null) {
            style.clearStyle();
        }
        super.set(str);
    }

    @Override // com.reandroid.arsc.item.StringBlock
    public String decodeString(byte[] bArr) {
        return decodeString(bArr, this.mUtf8);
    }
}
