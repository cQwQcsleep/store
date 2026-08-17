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
import org.jetbrains.kotlin.protobuf.InvalidProtocolBufferException;
import org.jetbrains.kotlin.protobuf.Parser;
import org.jetbrains.kotlin.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class IrGetValue extends GeneratedMessageLite implements IrGetValueOrBuilder {
    public static final int ORIGIN_NAME_FIELD_NUMBER = 2;
    public static Parser<IrGetValue> PARSER = new AbstractParser<IrGetValue>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValue.1
        public IrGetValue parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrGetValue(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SYMBOL_FIELD_NUMBER = 1;
    private static final IrGetValue defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int originName_;
    private long symbol_;
    private final ByteString unknownFields;

    static {
        IrGetValue irGetValue = new IrGetValue(true);
        defaultInstance = irGetValue;
        irGetValue.initFields();
    }

    private IrGetValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.symbol_ = codedInputStream.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.originName_ = codedInputStream.readInt32();
                        } else if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
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
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
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

    public static IrGetValue getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.symbol_ = 0L;
        this.originName_ = 0;
    }

    public static Builder newBuilder(IrGetValue irGetValue) {
        return newBuilder().mergeFrom(irGetValue);
    }

    public static IrGetValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrGetValue) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrGetValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrGetValue) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
    public int getOriginName() {
        return this.originName_;
    }

    public Parser<IrGetValue> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt64Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.symbol_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt64Size += CodedOutputStream.computeInt32Size(2, this.originName_);
        }
        int size = iComputeInt64Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
    public long getSymbol() {
        return this.symbol_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
    public boolean hasOriginName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
    public boolean hasSymbol() {
        return (this.bitField0_ & 1) == 1;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (hasSymbol()) {
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }
        this.memoizedIsInitialized = (byte) 0;
        return false;
    }

    public Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt64(1, this.symbol_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.originName_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrGetValue, Builder> implements IrGetValueOrBuilder {
        private int bitField0_;
        private int originName_;
        private long symbol_;

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
        public IrGetValue m1236build() throws UninitializedMessageException {
            IrGetValue irGetValueM1237buildPartial = m1237buildPartial();
            if (irGetValueM1237buildPartial.isInitialized()) {
                return irGetValueM1237buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irGetValueM1237buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrGetValue m1237buildPartial() {
            IrGetValue irGetValue = new IrGetValue(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            irGetValue.symbol_ = this.symbol_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            irGetValue.originName_ = this.originName_;
            irGetValue.bitField0_ = i2;
            return irGetValue;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1239clear() {
            super.clear();
            this.symbol_ = 0L;
            int i = this.bitField0_;
            this.originName_ = 0;
            this.bitField0_ = i & (-4);
            return this;
        }

        public Builder clearOriginName() {
            this.bitField0_ &= -3;
            this.originName_ = 0;
            return this;
        }

        public Builder clearSymbol() {
            this.bitField0_ &= -2;
            this.symbol_ = 0L;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1243clone() {
            return create().mergeFrom(m1237buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
        public int getOriginName() {
            return this.originName_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
        public long getSymbol() {
            return this.symbol_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
        public boolean hasOriginName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetValueOrBuilder
        public boolean hasSymbol() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasSymbol();
        }

        public Builder mergeFrom(IrGetValue irGetValue) {
            if (irGetValue == IrGetValue.getDefaultInstance()) {
                return this;
            }
            if (irGetValue.hasSymbol()) {
                setSymbol(irGetValue.getSymbol());
            }
            if (irGetValue.hasOriginName()) {
                setOriginName(irGetValue.getOriginName());
            }
            setUnknownFields(getUnknownFields().concat(irGetValue.unknownFields));
            return this;
        }

        public Builder setOriginName(int i) {
            this.bitField0_ |= 2;
            this.originName_ = i;
            return this;
        }

        public Builder setSymbol(long j) {
            this.bitField0_ |= 1;
            this.symbol_ = j;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrGetValue m1245getDefaultInstanceForType() {
            return IrGetValue.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1247mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrGetValue irGetValue = null;
            try {
                try {
                    IrGetValue irGetValue2 = (IrGetValue) IrGetValue.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irGetValue2 != null) {
                        mergeFrom(irGetValue2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrGetValue irGetValue3 = (IrGetValue) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irGetValue = irGetValue3;
                        if (irGetValue != null) {
                            mergeFrom(irGetValue);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irGetValue != null) {
                    mergeFrom(irGetValue);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrGetValue m1232getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1233newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1234toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrGetValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetValue) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrGetValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrGetValue) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrGetValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrGetValue) PARSER.parseFrom(bArr);
    }

    public static IrGetValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrGetValue) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrGetValue parseFrom(InputStream inputStream) throws IOException {
        return (IrGetValue) PARSER.parseFrom(inputStream);
    }

    public static IrGetValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetValue) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrGetValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrGetValue) PARSER.parseFrom(codedInputStream);
    }

    public static IrGetValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetValue) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrGetValue(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrGetValue(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
