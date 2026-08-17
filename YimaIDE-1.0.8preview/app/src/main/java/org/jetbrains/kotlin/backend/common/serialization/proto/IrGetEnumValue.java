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
public final class IrGetEnumValue extends GeneratedMessageLite implements IrGetEnumValueOrBuilder {
    public static Parser<IrGetEnumValue> PARSER = new AbstractParser<IrGetEnumValue>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrGetEnumValue.1
        public IrGetEnumValue parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrGetEnumValue(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SYMBOL_FIELD_NUMBER = 1;
    private static final IrGetEnumValue defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private long symbol_;
    private final ByteString unknownFields;

    static {
        IrGetEnumValue irGetEnumValue = new IrGetEnumValue(true);
        defaultInstance = irGetEnumValue;
        irGetEnumValue.initFields();
    }

    private IrGetEnumValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

    public static IrGetEnumValue getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.symbol_ = 0L;
    }

    public static Builder newBuilder(IrGetEnumValue irGetEnumValue) {
        return newBuilder().mergeFrom(irGetEnumValue);
    }

    public static IrGetEnumValue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrGetEnumValue) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrGetEnumValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrGetEnumValue) PARSER.parseFrom(byteString);
    }

    public Parser<IrGetEnumValue> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt64Size = ((this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.symbol_) : 0) + this.unknownFields.size();
        this.memoizedSerializedSize = iComputeInt64Size;
        return iComputeInt64Size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetEnumValueOrBuilder
    public long getSymbol() {
        return this.symbol_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetEnumValueOrBuilder
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
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrGetEnumValue, Builder> implements IrGetEnumValueOrBuilder {
        private int bitField0_;
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
        public IrGetEnumValue m1188build() throws UninitializedMessageException {
            IrGetEnumValue irGetEnumValueM1189buildPartial = m1189buildPartial();
            if (irGetEnumValueM1189buildPartial.isInitialized()) {
                return irGetEnumValueM1189buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irGetEnumValueM1189buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrGetEnumValue m1189buildPartial() {
            IrGetEnumValue irGetEnumValue = new IrGetEnumValue(this);
            int i = (this.bitField0_ & 1) != 1 ? 0 : 1;
            irGetEnumValue.symbol_ = this.symbol_;
            irGetEnumValue.bitField0_ = i;
            return irGetEnumValue;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1191clear() {
            super.clear();
            this.symbol_ = 0L;
            this.bitField0_ &= -2;
            return this;
        }

        public Builder clearSymbol() {
            this.bitField0_ &= -2;
            this.symbol_ = 0L;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1195clone() {
            return create().mergeFrom(m1189buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetEnumValueOrBuilder
        public long getSymbol() {
            return this.symbol_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetEnumValueOrBuilder
        public boolean hasSymbol() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasSymbol();
        }

        public Builder mergeFrom(IrGetEnumValue irGetEnumValue) {
            if (irGetEnumValue == IrGetEnumValue.getDefaultInstance()) {
                return this;
            }
            if (irGetEnumValue.hasSymbol()) {
                setSymbol(irGetEnumValue.getSymbol());
            }
            setUnknownFields(getUnknownFields().concat(irGetEnumValue.unknownFields));
            return this;
        }

        public Builder setSymbol(long j) {
            this.bitField0_ |= 1;
            this.symbol_ = j;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrGetEnumValue m1197getDefaultInstanceForType() {
            return IrGetEnumValue.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1199mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrGetEnumValue irGetEnumValue = null;
            try {
                try {
                    IrGetEnumValue irGetEnumValue2 = (IrGetEnumValue) IrGetEnumValue.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irGetEnumValue2 != null) {
                        mergeFrom(irGetEnumValue2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrGetEnumValue irGetEnumValue3 = (IrGetEnumValue) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irGetEnumValue = irGetEnumValue3;
                        if (irGetEnumValue != null) {
                            mergeFrom(irGetEnumValue);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irGetEnumValue != null) {
                    mergeFrom(irGetEnumValue);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrGetEnumValue m1184getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1185newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1186toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrGetEnumValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetEnumValue) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrGetEnumValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrGetEnumValue) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrGetEnumValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrGetEnumValue) PARSER.parseFrom(bArr);
    }

    public static IrGetEnumValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrGetEnumValue) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrGetEnumValue parseFrom(InputStream inputStream) throws IOException {
        return (IrGetEnumValue) PARSER.parseFrom(inputStream);
    }

    public static IrGetEnumValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetEnumValue) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrGetEnumValue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrGetEnumValue) PARSER.parseFrom(codedInputStream);
    }

    public static IrGetEnumValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetEnumValue) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrGetEnumValue(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrGetEnumValue(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
