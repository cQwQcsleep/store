package org.jetbrains.kotlin.backend.common.serialization.proto;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import org.jetbrains.kotlin.protobuf.AbstractMessageLite;
import org.jetbrains.kotlin.protobuf.AbstractParser;
import org.jetbrains.kotlin.protobuf.ByteString;
import org.jetbrains.kotlin.protobuf.CodedInputStream;
import org.jetbrains.kotlin.protobuf.CodedOutputStream;
import org.jetbrains.kotlin.protobuf.ExtensionRegistryLite;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.protobuf.Internal;
import org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException;
import org.jetbrains.kotlin.protobuf.Parser;
import org.jetbrains.kotlin.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class IrConst extends GeneratedMessageLite implements IrConstOrBuilder {
    public static final int BOOLEAN_FIELD_NUMBER = 2;
    public static final int BYTE_FIELD_NUMBER = 4;
    public static final int CHAR_FIELD_NUMBER = 3;
    public static final int DOUBLE_BITS_FIELD_NUMBER = 9;
    public static final int FLOAT_BITS_FIELD_NUMBER = 8;
    public static final int INT_FIELD_NUMBER = 6;
    public static final int LONG_FIELD_NUMBER = 7;
    public static final int NULL_FIELD_NUMBER = 1;
    public static Parser<IrConst> PARSER = new AbstractParser<IrConst>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrConst.1
        public IrConst parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrConst(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SHORT_FIELD_NUMBER = 5;
    public static final int STRING_FIELD_NUMBER = 10;
    private static final IrConst defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;
    private int valueCase_;
    private Object value_;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.backend.common.serialization.proto.IrConst$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase;

        static {
            int[] iArr = new int[ValueCase.values().length];
            $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase = iArr;
            try {
                iArr[ValueCase.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.BYTE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.SHORT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.INT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.FLOAT_BITS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.DOUBLE_BITS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[ValueCase.VALUE_NOT_SET.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public enum ValueCase implements Internal.EnumLite {
        NULL(1),
        BOOLEAN(2),
        CHAR(3),
        BYTE(4),
        SHORT(5),
        INT(6),
        LONG(7),
        FLOAT_BITS(8),
        DOUBLE_BITS(9),
        STRING(10),
        VALUE_NOT_SET(0);

        private int value;

        ValueCase(int i) {
            this.value = i;
        }

        public static ValueCase valueOf(int i) {
            switch (i) {
                case 0:
                    return VALUE_NOT_SET;
                case 1:
                    return NULL;
                case 2:
                    return BOOLEAN;
                case 3:
                    return CHAR;
                case 4:
                    return BYTE;
                case 5:
                    return SHORT;
                case 6:
                    return INT;
                case 7:
                    return LONG;
                case 8:
                    return FLOAT_BITS;
                case 9:
                    return DOUBLE_BITS;
                case 10:
                    return STRING;
                default:
                    w01.a("Value is undefined for this oneof enum.");
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    static {
        IrConst irConst = new IrConst(true);
        defaultInstance = irConst;
        irConst.initFields();
    }

    private IrConst(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        boolean z = false;
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        switch (tag) {
                            case 0:
                                break;
                            case 8:
                                this.valueCase_ = 1;
                                this.value_ = Boolean.valueOf(codedInputStream.readBool());
                                continue;
                            case 16:
                                this.valueCase_ = 2;
                                this.value_ = Boolean.valueOf(codedInputStream.readBool());
                                continue;
                            case 24:
                                this.valueCase_ = 3;
                                this.value_ = Integer.valueOf(codedInputStream.readInt32());
                                continue;
                            case 32:
                                this.valueCase_ = 4;
                                this.value_ = Integer.valueOf(codedInputStream.readInt32());
                                continue;
                            case 40:
                                this.valueCase_ = 5;
                                this.value_ = Integer.valueOf(codedInputStream.readInt32());
                                continue;
                            case 48:
                                this.valueCase_ = 6;
                                this.value_ = Integer.valueOf(codedInputStream.readInt32());
                                continue;
                            case 56:
                                this.valueCase_ = 7;
                                this.value_ = Long.valueOf(codedInputStream.readInt64());
                                continue;
                            case 69:
                                this.valueCase_ = 8;
                                this.value_ = Integer.valueOf(codedInputStream.readFixed32());
                                continue;
                            case 73:
                                this.valueCase_ = 9;
                                this.value_ = Long.valueOf(codedInputStream.readFixed64());
                                continue;
                            case 80:
                                this.valueCase_ = 10;
                                this.value_ = Integer.valueOf(codedInputStream.readInt32());
                                continue;
                            default:
                                if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                                    break;
                                }
                                break;
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                try {
                    codedOutputStreamNewInstance.flush();
                } catch (IOException unused) {
                } finally {
                    this.unknownFields = outputNewOutput.toByteString();
                }
                makeExtensionsImmutable();
                throw th;
            }
        }
        try {
            codedOutputStreamNewInstance.flush();
        } catch (IOException unused2) {
        } finally {
            this.unknownFields = outputNewOutput.toByteString();
        }
        makeExtensionsImmutable();
    }

    public static IrConst getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
    }

    public static Builder newBuilder(IrConst irConst) {
        return newBuilder().mergeFrom(irConst);
    }

    public static IrConst parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrConst) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrConst parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrConst) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean getBoolean() {
        if (this.valueCase_ == 2) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public int getByte() {
        if (this.valueCase_ == 4) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public int getChar() {
        if (this.valueCase_ == 3) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public long getDoubleBits() {
        if (this.valueCase_ == 9) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public int getFloatBits() {
        if (this.valueCase_ == 8) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public int getInt() {
        if (this.valueCase_ == 6) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public long getLong() {
        if (this.valueCase_ == 7) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean getNull() {
        if (this.valueCase_ == 1) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    public Parser<IrConst> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeBoolSize = this.valueCase_ == 1 ? CodedOutputStream.computeBoolSize(1, ((Boolean) this.value_).booleanValue()) : 0;
        if (this.valueCase_ == 2) {
            iComputeBoolSize += CodedOutputStream.computeBoolSize(2, ((Boolean) this.value_).booleanValue());
        }
        if (this.valueCase_ == 3) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(3, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 4) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(4, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 5) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(5, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 6) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(6, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 7) {
            iComputeBoolSize += CodedOutputStream.computeInt64Size(7, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 8) {
            iComputeBoolSize += CodedOutputStream.computeFixed32Size(8, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 9) {
            iComputeBoolSize += CodedOutputStream.computeFixed64Size(9, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 10) {
            iComputeBoolSize += CodedOutputStream.computeInt32Size(10, ((Integer) this.value_).intValue());
        }
        int size = iComputeBoolSize + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public int getShort() {
        if (this.valueCase_ == 5) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public int getString() {
        if (this.valueCase_ == 10) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    public ValueCase getValueCase() {
        return ValueCase.valueOf(this.valueCase_);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasBoolean() {
        return this.valueCase_ == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasByte() {
        return this.valueCase_ == 4;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasChar() {
        return this.valueCase_ == 3;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasDoubleBits() {
        return this.valueCase_ == 9;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasFloatBits() {
        return this.valueCase_ == 8;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasInt() {
        return this.valueCase_ == 6;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasLong() {
        return this.valueCase_ == 7;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasNull() {
        return this.valueCase_ == 1;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasShort() {
        return this.valueCase_ == 5;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
    public boolean hasString() {
        return this.valueCase_ == 10;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (this.valueCase_ == 1) {
            codedOutputStream.writeBool(1, ((Boolean) this.value_).booleanValue());
        }
        if (this.valueCase_ == 2) {
            codedOutputStream.writeBool(2, ((Boolean) this.value_).booleanValue());
        }
        if (this.valueCase_ == 3) {
            codedOutputStream.writeInt32(3, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 4) {
            codedOutputStream.writeInt32(4, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 5) {
            codedOutputStream.writeInt32(5, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 6) {
            codedOutputStream.writeInt32(6, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 7) {
            codedOutputStream.writeInt64(7, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 8) {
            codedOutputStream.writeFixed32(8, ((Integer) this.value_).intValue());
        }
        if (this.valueCase_ == 9) {
            codedOutputStream.writeFixed64(9, ((Long) this.value_).longValue());
        }
        if (this.valueCase_ == 10) {
            codedOutputStream.writeInt32(10, ((Integer) this.value_).intValue());
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrConst, Builder> implements IrConstOrBuilder {
        private int bitField0_;
        private int valueCase_ = 0;
        private Object value_;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        private void maybeForceBuilderInitialization() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.protobuf.UninitializedMessageException */
        /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
        public IrConst m787build() throws UninitializedMessageException {
            IrConst irConstM788buildPartial = m788buildPartial();
            if (irConstM788buildPartial.isInitialized()) {
                return irConstM788buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irConstM788buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrConst m788buildPartial() {
            IrConst irConst = new IrConst(this);
            if (this.valueCase_ == 1) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 2) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 3) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 4) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 5) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 6) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 7) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 8) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 9) {
                irConst.value_ = this.value_;
            }
            if (this.valueCase_ == 10) {
                irConst.value_ = this.value_;
            }
            irConst.bitField0_ = 0;
            irConst.valueCase_ = this.valueCase_;
            return irConst;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m790clear() {
            super.clear();
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        public Builder clearBoolean() {
            if (this.valueCase_ == 2) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearByte() {
            if (this.valueCase_ == 4) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearChar() {
            if (this.valueCase_ == 3) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearDoubleBits() {
            if (this.valueCase_ == 9) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearFloatBits() {
            if (this.valueCase_ == 8) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearInt() {
            if (this.valueCase_ == 6) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearLong() {
            if (this.valueCase_ == 7) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearNull() {
            if (this.valueCase_ == 1) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearShort() {
            if (this.valueCase_ == 5) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearString() {
            if (this.valueCase_ == 10) {
                this.valueCase_ = 0;
                this.value_ = null;
            }
            return this;
        }

        public Builder clearValue() {
            this.valueCase_ = 0;
            this.value_ = null;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m794clone() {
            return create().mergeFrom(m788buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean getBoolean() {
            if (this.valueCase_ == 2) {
                return ((Boolean) this.value_).booleanValue();
            }
            return false;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public int getByte() {
            if (this.valueCase_ == 4) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public int getChar() {
            if (this.valueCase_ == 3) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public long getDoubleBits() {
            if (this.valueCase_ == 9) {
                return ((Long) this.value_).longValue();
            }
            return 0L;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public int getFloatBits() {
            if (this.valueCase_ == 8) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public int getInt() {
            if (this.valueCase_ == 6) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public long getLong() {
            if (this.valueCase_ == 7) {
                return ((Long) this.value_).longValue();
            }
            return 0L;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean getNull() {
            if (this.valueCase_ == 1) {
                return ((Boolean) this.value_).booleanValue();
            }
            return false;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public int getShort() {
            if (this.valueCase_ == 5) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public int getString() {
            if (this.valueCase_ == 10) {
                return ((Integer) this.value_).intValue();
            }
            return 0;
        }

        public ValueCase getValueCase() {
            return ValueCase.valueOf(this.valueCase_);
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasBoolean() {
            return this.valueCase_ == 2;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasByte() {
            return this.valueCase_ == 4;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasChar() {
            return this.valueCase_ == 3;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasDoubleBits() {
            return this.valueCase_ == 9;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasFloatBits() {
            return this.valueCase_ == 8;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasInt() {
            return this.valueCase_ == 6;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasLong() {
            return this.valueCase_ == 7;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasNull() {
            return this.valueCase_ == 1;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasShort() {
            return this.valueCase_ == 5;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrConstOrBuilder
        public boolean hasString() {
            return this.valueCase_ == 10;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(IrConst irConst) {
            if (irConst == IrConst.getDefaultInstance()) {
                return this;
            }
            switch (AnonymousClass2.$SwitchMap$org$jetbrains$kotlin$backend$common$serialization$proto$IrConst$ValueCase[irConst.getValueCase().ordinal()]) {
                case 1:
                    setNull(irConst.getNull());
                    break;
                case 2:
                    setBoolean(irConst.getBoolean());
                    break;
                case 3:
                    setChar(irConst.getChar());
                    break;
                case 4:
                    setByte(irConst.getByte());
                    break;
                case 5:
                    setShort(irConst.getShort());
                    break;
                case 6:
                    setInt(irConst.getInt());
                    break;
                case 7:
                    setLong(irConst.getLong());
                    break;
                case 8:
                    setFloatBits(irConst.getFloatBits());
                    break;
                case 9:
                    setDoubleBits(irConst.getDoubleBits());
                    break;
                case 10:
                    setString(irConst.getString());
                    break;
            }
            setUnknownFields(getUnknownFields().concat(irConst.unknownFields));
            return this;
        }

        public Builder setBoolean(boolean z) {
            this.valueCase_ = 2;
            this.value_ = Boolean.valueOf(z);
            return this;
        }

        public Builder setByte(int i) {
            this.valueCase_ = 4;
            this.value_ = Integer.valueOf(i);
            return this;
        }

        public Builder setChar(int i) {
            this.valueCase_ = 3;
            this.value_ = Integer.valueOf(i);
            return this;
        }

        public Builder setDoubleBits(long j) {
            this.valueCase_ = 9;
            this.value_ = Long.valueOf(j);
            return this;
        }

        public Builder setFloatBits(int i) {
            this.valueCase_ = 8;
            this.value_ = Integer.valueOf(i);
            return this;
        }

        public Builder setInt(int i) {
            this.valueCase_ = 6;
            this.value_ = Integer.valueOf(i);
            return this;
        }

        public Builder setLong(long j) {
            this.valueCase_ = 7;
            this.value_ = Long.valueOf(j);
            return this;
        }

        public Builder setNull(boolean z) {
            this.valueCase_ = 1;
            this.value_ = Boolean.valueOf(z);
            return this;
        }

        public Builder setShort(int i) {
            this.valueCase_ = 5;
            this.value_ = Integer.valueOf(i);
            return this;
        }

        public Builder setString(int i) {
            this.valueCase_ = 10;
            this.value_ = Integer.valueOf(i);
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrConst m796getDefaultInstanceForType() {
            return IrConst.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m798mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrConst irConst = null;
            try {
                try {
                    IrConst irConst2 = (IrConst) IrConst.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irConst2 != null) {
                        mergeFrom(irConst2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrConst irConst3 = (IrConst) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irConst = irConst3;
                        if (irConst != null) {
                            mergeFrom(irConst);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irConst != null) {
                    mergeFrom(irConst);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrConst m783getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m784newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m785toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrConst parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrConst) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrConst parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrConst) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrConst parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrConst) PARSER.parseFrom(bArr);
    }

    public static IrConst parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrConst) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrConst parseFrom(InputStream inputStream) throws IOException {
        return (IrConst) PARSER.parseFrom(inputStream);
    }

    public static IrConst parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrConst) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrConst parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrConst) PARSER.parseFrom(codedInputStream);
    }

    public static IrConst parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrConst) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrConst(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrConst(boolean z) {
        this.valueCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
