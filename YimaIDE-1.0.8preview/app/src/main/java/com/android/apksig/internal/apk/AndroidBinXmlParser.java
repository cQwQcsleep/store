package com.android.apksig.internal.apk;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class AndroidBinXmlParser {
    public static final int EVENT_END_DOCUMENT = 2;
    public static final int EVENT_END_ELEMENT = 4;
    public static final int EVENT_START_DOCUMENT = 1;
    public static final int EVENT_START_ELEMENT = 3;
    private static final long NO_NAMESPACE = 4294967295L;
    public static final int VALUE_TYPE_BOOLEAN = 4;
    public static final int VALUE_TYPE_INT = 2;
    public static final int VALUE_TYPE_REFERENCE = 3;
    public static final int VALUE_TYPE_STRING = 1;
    public static final int VALUE_TYPE_UNSUPPORTED = 0;
    private int mCurrentElementAttrSizeBytes;
    private int mCurrentElementAttributeCount;
    private List<Attribute> mCurrentElementAttributes;
    private ByteBuffer mCurrentElementAttributesContents;
    private String mCurrentElementName;
    private String mCurrentElementNamespace;
    private int mCurrentEvent = 1;
    private int mDepth;
    private ResourceMap mResourceMap;
    private StringPool mStringPool;
    private final ByteBuffer mXml;

    public static class Attribute {
        private static final int TYPE_INT_BOOLEAN = 18;
        private static final int TYPE_INT_DEC = 16;
        private static final int TYPE_INT_HEX = 17;
        private static final int TYPE_REFERENCE = 1;
        private static final int TYPE_STRING = 3;
        private final long mNameId;
        private final long mNsId;
        private final ResourceMap mResourceMap;
        private final StringPool mStringPool;
        private final int mValueData;
        private final int mValueType;

        private Attribute(long j, long j2, int i, int i2, StringPool stringPool, ResourceMap resourceMap) {
            this.mNsId = j;
            this.mNameId = j2;
            this.mValueType = i;
            this.mValueData = i2;
            this.mStringPool = stringPool;
            this.mResourceMap = resourceMap;
        }

        public boolean getBooleanValue() throws XmlParserException {
            if (this.mValueType == 18) {
                return this.mValueData != 0;
            }
            throw new XmlParserException("Cannot coerce to boolean: value type " + this.mValueType);
        }

        public int getIntValue() throws XmlParserException {
            int i = this.mValueType;
            if (i != 1) {
                switch (i) {
                    case 16:
                    case 17:
                    case 18:
                        break;
                    default:
                        throw new XmlParserException("Cannot coerce to int: value type " + this.mValueType);
                }
            }
            return this.mValueData;
        }

        public String getName() throws XmlParserException {
            return this.mStringPool.getString(this.mNameId);
        }

        public int getNameResourceId() {
            ResourceMap resourceMap = this.mResourceMap;
            if (resourceMap != null) {
                return resourceMap.getResourceId(this.mNameId);
            }
            return 0;
        }

        public String getNamespace() throws XmlParserException {
            long j = this.mNsId;
            return j != AndroidBinXmlParser.NO_NAMESPACE ? this.mStringPool.getString(j) : "";
        }

        public String getStringValue() throws XmlParserException {
            int i = this.mValueType;
            if (i == 1) {
                return "@" + Integer.toHexString(this.mValueData);
            }
            if (i == 3) {
                return this.mStringPool.getString(((long) this.mValueData) & AndroidBinXmlParser.NO_NAMESPACE);
            }
            switch (i) {
                case 16:
                    return Integer.toString(this.mValueData);
                case 17:
                    return "0x" + Integer.toHexString(this.mValueData);
                case 18:
                    return Boolean.toString(this.mValueData != 0);
                default:
                    throw new XmlParserException("Cannot coerce to string: value type " + this.mValueType);
            }
        }

        public int getValueType() {
            return this.mValueType;
        }
    }

    public static class Chunk {
        static final int HEADER_MIN_SIZE_BYTES = 8;
        public static final int RES_XML_TYPE_END_ELEMENT = 259;
        public static final int RES_XML_TYPE_RESOURCE_MAP = 384;
        public static final int RES_XML_TYPE_START_ELEMENT = 258;
        public static final int TYPE_RES_XML = 3;
        public static final int TYPE_STRING_POOL = 1;
        private final ByteBuffer mContents;
        private final ByteBuffer mHeader;
        private final int mType;

        public Chunk(int i, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
            this.mType = i;
            this.mHeader = byteBuffer;
            this.mContents = byteBuffer2;
        }

        public static Chunk get(ByteBuffer byteBuffer) throws XmlParserException {
            if (byteBuffer.remaining() < 8) {
                byteBuffer.position(byteBuffer.limit());
                return null;
            }
            int iPosition = byteBuffer.position();
            int unsignedInt16 = AndroidBinXmlParser.getUnsignedInt16(byteBuffer);
            int unsignedInt17 = AndroidBinXmlParser.getUnsignedInt16(byteBuffer);
            long unsignedInt32 = AndroidBinXmlParser.getUnsignedInt32(byteBuffer);
            if (unsignedInt32 - 8 > byteBuffer.remaining()) {
                byteBuffer.position(byteBuffer.limit());
                return null;
            }
            if (unsignedInt17 < 8) {
                throw new XmlParserException("Malformed chunk: header too short: " + unsignedInt17 + " bytes");
            }
            if (unsignedInt17 <= unsignedInt32) {
                int i = unsignedInt17 + iPosition;
                long j = ((long) iPosition) + unsignedInt32;
                Chunk chunk = new Chunk(unsignedInt16, AndroidBinXmlParser.sliceFromTo(byteBuffer, iPosition, i), AndroidBinXmlParser.sliceFromTo(byteBuffer, i, j));
                byteBuffer.position((int) j);
                return chunk;
            }
            throw new XmlParserException("Malformed chunk: header too long: " + unsignedInt17 + " bytes. Chunk size: " + unsignedInt32 + " bytes");
        }

        public ByteBuffer getContents() {
            ByteBuffer byteBufferSlice = this.mContents.slice();
            byteBufferSlice.order(this.mContents.order());
            return byteBufferSlice;
        }

        public ByteBuffer getHeader() {
            ByteBuffer byteBufferSlice = this.mHeader.slice();
            byteBufferSlice.order(this.mHeader.order());
            return byteBufferSlice;
        }

        public int getType() {
            return this.mType;
        }
    }

    public static class ResourceMap {
        private final ByteBuffer mChunkContents;
        private final int mEntryCount;

        public ResourceMap(Chunk chunk) throws XmlParserException {
            ByteBuffer byteBufferSlice = chunk.getContents().slice();
            this.mChunkContents = byteBufferSlice;
            byteBufferSlice.order(chunk.getContents().order());
            this.mEntryCount = byteBufferSlice.remaining() / 4;
        }

        public int getResourceId(long j) {
            if (j < 0 || j >= this.mEntryCount) {
                return 0;
            }
            return this.mChunkContents.getInt(((int) j) * 4);
        }
    }

    public static class StringPool {
        private static final int FLAG_UTF8 = 256;
        private final Map<Integer, String> mCachedStrings = new HashMap();
        private final ByteBuffer mChunkContents;
        private final int mStringCount;
        private final ByteBuffer mStringsSection;
        private final boolean mUtf8Encoded;

        public StringPool(Chunk chunk) throws XmlParserException {
            long j;
            int iRemaining;
            ByteBuffer header = chunk.getHeader();
            int iRemaining2 = header.remaining();
            header.position(8);
            if (header.remaining() < 20) {
                throw new XmlParserException("XML chunk's header too short. Required at least 20 bytes. Available: " + header.remaining() + " bytes");
            }
            long unsignedInt32 = AndroidBinXmlParser.getUnsignedInt32(header);
            if (unsignedInt32 > 2147483647L) {
                throw new XmlParserException("Too many strings: " + unsignedInt32);
            }
            int i = (int) unsignedInt32;
            this.mStringCount = i;
            long unsignedInt33 = AndroidBinXmlParser.getUnsignedInt32(header);
            if (unsignedInt33 > 2147483647L) {
                throw new XmlParserException("Too many styles: " + unsignedInt33);
            }
            long unsignedInt34 = AndroidBinXmlParser.getUnsignedInt32(header);
            long unsignedInt35 = AndroidBinXmlParser.getUnsignedInt32(header);
            long unsignedInt36 = AndroidBinXmlParser.getUnsignedInt32(header);
            ByteBuffer contents = chunk.getContents();
            if (i > 0) {
                long j2 = iRemaining2;
                j = 0;
                int i2 = (int) (unsignedInt35 - j2);
                if (unsignedInt33 <= 0) {
                    iRemaining = contents.remaining();
                } else {
                    if (unsignedInt36 < unsignedInt35) {
                        throw new XmlParserException("Styles offset (" + unsignedInt36 + ") < strings offset (" + unsignedInt35 + ")");
                    }
                    iRemaining = (int) (unsignedInt36 - j2);
                }
                this.mStringsSection = AndroidBinXmlParser.sliceFromTo(contents, i2, iRemaining);
            } else {
                j = 0;
                this.mStringsSection = ByteBuffer.allocate(0);
            }
            this.mUtf8Encoded = (256 & unsignedInt34) != j;
            this.mChunkContents = contents;
        }

        private static String getLengthPrefixedUtf16EncodedString(ByteBuffer byteBuffer) throws XmlParserException {
            byte[] bArrArray;
            int iArrayOffset;
            int unsignedInt16 = AndroidBinXmlParser.getUnsignedInt16(byteBuffer);
            if ((32768 & unsignedInt16) != 0) {
                unsignedInt16 = ((unsignedInt16 & 32767) << 16) | AndroidBinXmlParser.getUnsignedInt16(byteBuffer);
            }
            if (unsignedInt16 > 1073741823) {
                throw new XmlParserException("String too long: " + unsignedInt16 + " uint16s");
            }
            int i = unsignedInt16 * 2;
            if (byteBuffer.hasArray()) {
                bArrArray = byteBuffer.array();
                iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                byteBuffer.position(byteBuffer.position() + i);
            } else {
                bArrArray = new byte[i];
                byteBuffer.get(bArrArray);
                iArrayOffset = 0;
            }
            int i2 = iArrayOffset + i;
            if (bArrArray[i2] != 0 || bArrArray[i2 + 1] != 0) {
                throw new XmlParserException("UTF-16 encoded form of string not NULL terminated");
            }
            try {
                return new String(bArrArray, iArrayOffset, i, "UTF-16LE");
            } catch (UnsupportedEncodingException e) {
                g3c.a("UTF-16LE character encoding not supported", e);
                return null;
            }
        }

        private static String getLengthPrefixedUtf8EncodedString(ByteBuffer byteBuffer) throws XmlParserException {
            byte[] bArrArray;
            int iArrayOffset;
            if ((AndroidBinXmlParser.getUnsignedInt8(byteBuffer) & 128) != 0) {
                AndroidBinXmlParser.getUnsignedInt8(byteBuffer);
            }
            int unsignedInt8 = AndroidBinXmlParser.getUnsignedInt8(byteBuffer);
            if ((unsignedInt8 & 128) != 0) {
                unsignedInt8 = ((unsignedInt8 & 127) << 8) | AndroidBinXmlParser.getUnsignedInt8(byteBuffer);
            }
            if (byteBuffer.hasArray()) {
                bArrArray = byteBuffer.array();
                iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                byteBuffer.position(byteBuffer.position() + unsignedInt8);
            } else {
                bArrArray = new byte[unsignedInt8];
                byteBuffer.get(bArrArray);
                iArrayOffset = 0;
            }
            if (bArrArray[iArrayOffset + unsignedInt8] != 0) {
                throw new XmlParserException("UTF-8 encoded form of string not NULL terminated");
            }
            try {
                return new String(bArrArray, iArrayOffset, unsignedInt8, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                g3c.a("UTF-8 character encoding not supported", e);
                return null;
            }
        }

        public String getString(long j) throws XmlParserException {
            if (j < 0) {
                throw new XmlParserException("Unsuported string index: " + j);
            }
            if (j >= this.mStringCount) {
                throw new XmlParserException("Unsuported string index: " + j + ", max: " + (this.mStringCount - 1));
            }
            int i = (int) j;
            String str = this.mCachedStrings.get(Integer.valueOf(i));
            if (str != null) {
                return str;
            }
            long unsignedInt32 = AndroidBinXmlParser.getUnsignedInt32(this.mChunkContents, i * 4);
            if (unsignedInt32 < this.mStringsSection.capacity()) {
                this.mStringsSection.position((int) unsignedInt32);
                boolean z = this.mUtf8Encoded;
                ByteBuffer byteBuffer = this.mStringsSection;
                String lengthPrefixedUtf8EncodedString = z ? getLengthPrefixedUtf8EncodedString(byteBuffer) : getLengthPrefixedUtf16EncodedString(byteBuffer);
                this.mCachedStrings.put(Integer.valueOf(i), lengthPrefixedUtf8EncodedString);
                return lengthPrefixedUtf8EncodedString;
            }
            throw new XmlParserException("Offset of string idx " + i + " out of bounds: " + unsignedInt32 + ", max: " + (this.mStringsSection.capacity() - 1));
        }
    }

    public AndroidBinXmlParser(ByteBuffer byteBuffer) throws XmlParserException {
        Chunk chunk;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        do {
            if (!byteBuffer.hasRemaining() || (chunk = Chunk.get(byteBuffer)) == null) {
                chunk = null;
                break;
            }
        } while (chunk.getType() != 3);
        if (chunk == null) {
            throw new XmlParserException("No XML chunk in file");
        }
        this.mXml = chunk.getContents();
    }

    private Attribute getAttribute(int i) {
        if (this.mCurrentEvent != 3) {
            jb9.a("Current event not a START_ELEMENT");
            return null;
        }
        if (i < 0) {
            jb9.a("index must be >= 0");
            return null;
        }
        if (i < this.mCurrentElementAttributeCount) {
            parseCurrentElementAttributesIfNotParsed();
            return this.mCurrentElementAttributes.get(i);
        }
        throw new IndexOutOfBoundsException("index must be <= attr count (" + this.mCurrentElementAttributeCount + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getUnsignedInt16(ByteBuffer byteBuffer) {
        return byteBuffer.getShort() & 65535;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getUnsignedInt32(ByteBuffer byteBuffer) {
        return ((long) byteBuffer.getInt()) & NO_NAMESPACE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getUnsignedInt8(ByteBuffer byteBuffer) {
        return byteBuffer.get() & 255;
    }

    private void parseCurrentElementAttributesIfNotParsed() {
        if (this.mCurrentElementAttributes != null) {
            return;
        }
        this.mCurrentElementAttributes = new ArrayList(this.mCurrentElementAttributeCount);
        for (int i = 0; i < this.mCurrentElementAttributeCount; i++) {
            int i2 = this.mCurrentElementAttrSizeBytes;
            int i3 = i * i2;
            ByteBuffer byteBufferSliceFromTo = sliceFromTo(this.mCurrentElementAttributesContents, i3, i2 + i3);
            long unsignedInt32 = getUnsignedInt32(byteBufferSliceFromTo);
            long unsignedInt33 = getUnsignedInt32(byteBufferSliceFromTo);
            byteBufferSliceFromTo.position(byteBufferSliceFromTo.position() + 7);
            this.mCurrentElementAttributes.add(new Attribute(unsignedInt32, unsignedInt33, getUnsignedInt8(byteBufferSliceFromTo), (int) getUnsignedInt32(byteBufferSliceFromTo), this.mStringPool, this.mResourceMap));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBuffer sliceFromTo(ByteBuffer byteBuffer, long j, long j2) {
        if (j < 0) {
            t01.a("start: ", j);
            return null;
        }
        if (j2 < j) {
            throw new IllegalArgumentException("end < start: " + j2 + " < " + j);
        }
        int iCapacity = byteBuffer.capacity();
        if (j2 <= byteBuffer.capacity()) {
            return sliceFromTo(byteBuffer, (int) j, (int) j2);
        }
        throw new IllegalArgumentException("end > capacity: " + j2 + " > " + iCapacity);
    }

    public boolean getAttributeBooleanValue(int i) throws XmlParserException {
        return getAttribute(i).getBooleanValue();
    }

    public int getAttributeCount() {
        if (this.mCurrentEvent != 3) {
            return -1;
        }
        return this.mCurrentElementAttributeCount;
    }

    public int getAttributeIntValue(int i) throws XmlParserException {
        return getAttribute(i).getIntValue();
    }

    public String getAttributeName(int i) throws XmlParserException {
        return getAttribute(i).getName();
    }

    public int getAttributeNameResourceId(int i) throws XmlParserException {
        return getAttribute(i).getNameResourceId();
    }

    public String getAttributeNamespace(int i) throws XmlParserException {
        return getAttribute(i).getNamespace();
    }

    public String getAttributeStringValue(int i) throws XmlParserException {
        return getAttribute(i).getStringValue();
    }

    public int getAttributeValueType(int i) throws XmlParserException {
        int valueType = getAttribute(i).getValueType();
        if (valueType == 1) {
            return 3;
        }
        if (valueType == 3) {
            return 1;
        }
        switch (valueType) {
            case 16:
            case 17:
                return 2;
            case 18:
                return 4;
            default:
                return 0;
        }
    }

    public int getDepth() {
        return this.mDepth;
    }

    public int getEventType() {
        return this.mCurrentEvent;
    }

    public String getName() {
        int i = this.mCurrentEvent;
        if (i == 3 || i == 4) {
            return this.mCurrentElementName;
        }
        return null;
    }

    public String getNamespace() {
        int i = this.mCurrentEvent;
        if (i == 3 || i == 4) {
            return this.mCurrentElementNamespace;
        }
        return null;
    }

    public int next() throws XmlParserException {
        Chunk chunk;
        int i = 1;
        if (this.mCurrentEvent == 4) {
            this.mDepth--;
        }
        while (this.mXml.hasRemaining() && (chunk = Chunk.get(this.mXml)) != null) {
            int type = chunk.getType();
            if (type != i) {
                if (type != 384) {
                    if (type == 258) {
                        if (this.mStringPool == null) {
                            throw new XmlParserException("Named element encountered before string pool");
                        }
                        ByteBuffer contents = chunk.getContents();
                        if (contents.remaining() < 20) {
                            throw new XmlParserException("Start element chunk too short. Need at least 20 bytes. Available: " + contents.remaining() + " bytes");
                        }
                        long unsignedInt32 = getUnsignedInt32(contents);
                        long unsignedInt33 = getUnsignedInt32(contents);
                        int unsignedInt16 = getUnsignedInt16(contents);
                        int unsignedInt17 = getUnsignedInt16(contents);
                        int unsignedInt18 = getUnsignedInt16(contents);
                        long j = unsignedInt16;
                        long j2 = (((long) unsignedInt18) * ((long) unsignedInt17)) + j;
                        contents.position(0);
                        if (unsignedInt16 > contents.remaining()) {
                            throw new XmlParserException("Attributes start offset out of bounds: " + unsignedInt16 + ", max: " + contents.remaining());
                        }
                        if (j2 > contents.remaining()) {
                            throw new XmlParserException("Attributes end offset out of bounds: " + j2 + ", max: " + contents.remaining());
                        }
                        this.mCurrentElementName = this.mStringPool.getString(unsignedInt33);
                        this.mCurrentElementNamespace = unsignedInt32 != NO_NAMESPACE ? this.mStringPool.getString(unsignedInt32) : "";
                        this.mCurrentElementAttributeCount = unsignedInt18;
                        this.mCurrentElementAttributes = null;
                        this.mCurrentElementAttrSizeBytes = unsignedInt17;
                        this.mCurrentElementAttributesContents = sliceFromTo(contents, j, j2);
                        this.mDepth++;
                        this.mCurrentEvent = 3;
                        return 3;
                    }
                    if (type == 259) {
                        if (this.mStringPool == null) {
                            throw new XmlParserException("Named element encountered before string pool");
                        }
                        ByteBuffer contents2 = chunk.getContents();
                        if (contents2.remaining() < 8) {
                            throw new XmlParserException("End element chunk too short. Need at least 8 bytes. Available: " + contents2.remaining() + " bytes");
                        }
                        long unsignedInt34 = getUnsignedInt32(contents2);
                        this.mCurrentElementName = this.mStringPool.getString(getUnsignedInt32(contents2));
                        this.mCurrentElementNamespace = unsignedInt34 != NO_NAMESPACE ? this.mStringPool.getString(unsignedInt34) : "";
                        this.mCurrentEvent = 4;
                        this.mCurrentElementAttributes = null;
                        this.mCurrentElementAttributesContents = null;
                        return 4;
                    }
                } else {
                    if (this.mResourceMap != null) {
                        throw new XmlParserException("Multiple resource maps not supported");
                    }
                    this.mResourceMap = new ResourceMap(chunk);
                }
            } else {
                if (this.mStringPool != null) {
                    throw new XmlParserException("Multiple string pools not supported");
                }
                this.mStringPool = new StringPool(chunk);
            }
            i = i;
        }
        this.mCurrentEvent = 2;
        return 2;
    }

    public static class XmlParserException extends Exception {
        private static final long serialVersionUID = 1;

        public XmlParserException(String str) {
            super(str);
        }

        public XmlParserException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getUnsignedInt32(ByteBuffer byteBuffer, int i) {
        return ((long) byteBuffer.getInt(i)) & NO_NAMESPACE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBuffer sliceFromTo(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            qf1.a("start: ", i);
            return null;
        }
        if (i2 >= i) {
            int iCapacity = byteBuffer.capacity();
            if (i2 <= byteBuffer.capacity()) {
                int iLimit = byteBuffer.limit();
                int iPosition = byteBuffer.position();
                try {
                    byteBuffer.position(0);
                    byteBuffer.limit(i2);
                    byteBuffer.position(i);
                    ByteBuffer byteBufferSlice = byteBuffer.slice();
                    byteBufferSlice.order(byteBuffer.order());
                    return byteBufferSlice;
                } finally {
                    byteBuffer.position(0);
                    byteBuffer.limit(iLimit);
                    byteBuffer.position(iPosition);
                }
            }
            dn0.a("end > capacity: ", i2, " > ", iCapacity);
            return null;
        }
        dn0.a("end < start: ", i2, " < ", i);
        return null;
    }
}
