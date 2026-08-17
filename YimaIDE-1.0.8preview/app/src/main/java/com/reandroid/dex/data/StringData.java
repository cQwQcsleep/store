package com.reandroid.dex.data;

import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.DexBlockItem;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.base.OffsetReceiver;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.io.ByteReader;
import com.reandroid.dex.io.StreamUtil;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.HexUtil;
import com.sun.jna.platform.win32.WinError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringData extends DataItem implements SmaliFormat, BlockRefresh, OffsetSupplier, OffsetReceiver, Comparable<StringData> {
    private final StringDataContainer mDataContainer;
    private StringKey mKey;

    public static class StringDataContainer extends BlockItem {
        private final StringData stringData;

        public StringDataContainer(StringData stringData) {
            super(0);
            this.stringData = stringData;
        }

        public byte[] getBytesInternal() {
            return super.getBytesInternal();
        }

        public void onBytesChanged() {
            this.stringData.onStringBytesChanged();
        }

        public int onWriteBytes(OutputStream outputStream) throws IOException {
            return super.onWriteBytes(outputStream);
        }

        public void setLength(int i) {
            setBytesLength(i, false);
        }
    }

    public StringData() {
        super(1);
        StringDataContainer stringDataContainer = new StringDataContainer(this);
        this.mDataContainer = stringDataContainer;
        addChildBlock(0, stringDataContainer);
    }

    private static String decodeString(ByteReader byteReader) throws IOException {
        char c;
        int i;
        int uleb128 = DexBlockItem.readUleb128(byteReader);
        char[] cArr = new char[uleb128];
        int i2 = 0;
        int i3 = 0;
        while (uleb128 > 0) {
            int i4 = byteReader.read();
            switch (i4 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    if (i4 == 0) {
                        return throwBadUtf8(i4, i3);
                    }
                    c = (char) i4;
                    i3++;
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    return throwBadUtf8(i4, i3);
                case 12:
                case 13:
                    int i5 = byteReader.read();
                    int i6 = i5 & 255;
                    if ((i5 & 192) != 128) {
                        return throwBadUtf8(i6, i3 + 1);
                    }
                    int i7 = ((i4 & 31) << 6) | (i5 & 63);
                    if (i7 != 0 && i7 < 128) {
                        return throwBadUtf8(i6, i3 + 1);
                    }
                    c = (char) i7;
                    i3 += 2;
                    break;
                    break;
                case 14:
                    int i8 = byteReader.read();
                    if ((i8 & 192) != 128) {
                        return throwBadUtf8(i8, i3 + 1);
                    }
                    int i9 = byteReader.read();
                    if ((i9 & 192) == 128 && (i = ((i4 & 15) << 12) | ((i8 & 63) << 6) | (i9 & 63)) >= 2048) {
                        c = (char) i;
                        i3 += 3;
                        break;
                    }
                    return throwBadUtf8(i9, i3 + 2);
            }
            cArr[i2] = c;
            i2++;
            uleb128--;
        }
        return new String(cArr, 0, i2);
    }

    private void encodeString(String str) {
        int length = str.length();
        this.mDataContainer.setLength((length * 3) + 4);
        byte[] bytesInternal = this.mDataContainer.getBytesInternal();
        int iWriteUleb128 = DexBlockItem.writeUleb128(bytesInternal, 0, length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt != 0 && cCharAt < 128) {
                bytesInternal[iWriteUleb128] = (byte) cCharAt;
                iWriteUleb128++;
            } else if (cCharAt < 2048) {
                int i2 = iWriteUleb128 + 1;
                bytesInternal[iWriteUleb128] = (byte) (((cCharAt >> 6) & 31) | 192);
                iWriteUleb128 += 2;
                bytesInternal[i2] = (byte) ((cCharAt & '?') | 128);
            } else {
                bytesInternal[iWriteUleb128] = (byte) (((cCharAt >> '\f') & 15) | WinError.ERROR_FORMS_AUTH_REQUIRED);
                int i3 = iWriteUleb128 + 2;
                bytesInternal[iWriteUleb128 + 1] = (byte) (((cCharAt >> 6) & 63) | 128);
                iWriteUleb128 += 3;
                bytesInternal[i3] = (byte) ((cCharAt & '?') | 128);
            }
        }
        bytesInternal[iWriteUleb128] = 0;
        this.mDataContainer.setLength(iWriteUleb128 + 1);
    }

    private static String throwBadUtf8(int i, int i2) throws IOException {
        throw new IOException("bad utf-8 byte " + HexUtil.toHex2("", (byte) i) + " at offset " + i2);
    }

    private StringKey writeKey(StringKey stringKey) {
        StringKey key = getKey();
        if (stringKey.equals(key)) {
            return key;
        }
        encodeString(stringKey.getString());
        this.mKey = stringKey;
        return stringKey;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        if (isRemoved()) {
            a16.a("REMOVED string data");
            return;
        }
        StringKey key = getKey();
        if (key != null) {
            key.append(smaliWriter, smaliWriter.isCommentUnicodeStrings());
        } else {
            throw new IOException("Null string key: " + toString());
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(StringData stringData) {
        if (stringData == null) {
            return -1;
        }
        if (stringData == this) {
            return 0;
        }
        return getString().compareTo(stringData.getString());
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public int countBytes() {
        return this.mDataContainer.countBytes();
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public byte[] getBytes() {
        return this.mDataContainer.getBytes();
    }

    @Override // com.reandroid.dex.common.SectionItemContainer, com.reandroid.arsc.base.OffsetSupplier, com.reandroid.dex.base.DexArraySupplier
    public StringId getOffsetReference() {
        return (StringId) super.getOffsetReference();
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<StringData> getSectionType() {
        return SectionType.STRING_DATA;
    }

    public String getString() {
        StringKey key = getKey();
        if (key != null) {
            return key.getString();
        }
        return null;
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.base.UsageMarker
    public int getUsageType() {
        StringId offsetReference = getOffsetReference();
        if (offsetReference != null) {
            return offsetReference.getUsageType();
        }
        return 0;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public boolean isBlank() {
        return getOffsetReference() == null;
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        if (blockCounter.END == this) {
            blockCounter.FOUND = true;
        } else {
            blockCounter.addCount(countBytes());
        }
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        int position = blockReader.getPosition();
        int offset = getOffset();
        blockReader.seek(offset);
        String strDecodeString = decodeString(StreamUtil.createByteReader((InputStream) blockReader));
        int position2 = blockReader.getPosition() - offset;
        blockReader.seek(offset);
        StringDataContainer stringDataContainer = this.mDataContainer;
        stringDataContainer.setLength(position2 + 1);
        blockReader.readFully(stringDataContainer.getBytesInternal());
        blockReader.seek(position);
        this.mKey = StringKey.create(strDecodeString);
    }

    @Override // com.reandroid.dex.common.SectionItem
    public void onRemovedInternal() {
        super.onRemovedInternal();
        setOffsetReference(null);
        this.mKey = null;
        this.mDataContainer.setLength(0);
    }

    public void onStringBytesChanged() {
        this.mKey = StringKey.create(decodeString());
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public int onWriteBytes(OutputStream outputStream) throws IOException {
        return this.mDataContainer.onWriteBytes(outputStream);
    }

    public void removeSelf(StringId stringId) {
        if (stringId == getOffsetReference()) {
            super.removeSelf();
        } else if (!isRemoved()) {
            throw new DexException("Invalid remove request");
        }
    }

    @Override // com.reandroid.dex.common.SectionItemContainer, com.reandroid.dex.base.OffsetReceiver
    public void setOffsetReference(IntegerReference integerReference) {
        StringId stringId = (StringId) integerReference;
        StringId offsetReference = getOffsetReference();
        if (stringId == offsetReference) {
            return;
        }
        if (stringId == null || offsetReference == null) {
            super.setOffsetReference(integerReference);
        } else {
            z01.a("String data already linked: ", getString());
        }
    }

    @Override // com.reandroid.dex.common.SectionItemContainer, com.reandroid.dex.base.PositionedItem
    public void setPosition(int i) {
        StringId offsetReference = getOffsetReference();
        if (offsetReference != null) {
            offsetReference.set(i);
        }
    }

    public void setString(String str) {
        updateString(StringKey.create(str));
    }

    public String toString() {
        String string = getString();
        return string != null ? string : "NULL";
    }

    public StringKey updateString(StringKey stringKey) {
        if (stringKey == null) {
            stringKey = StringKey.EMPTY;
        }
        return writeKey(stringKey);
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public StringKey getKey() {
        return this.mKey;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem
    public void removeSelf() throws DexException {
        throw new DexException("Remove STRING_ID first before STRING_DATA");
    }

    private String decodeString() {
        try {
            return decodeString(StreamUtil.createByteReader(this.mDataContainer.getBytesInternal()));
        } catch (IOException unused) {
            return null;
        }
    }
}
