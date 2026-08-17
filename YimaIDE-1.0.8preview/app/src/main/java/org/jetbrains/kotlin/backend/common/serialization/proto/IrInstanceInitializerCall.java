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
public final class IrInstanceInitializerCall extends GeneratedMessageLite implements IrInstanceInitializerCallOrBuilder {
    public static Parser<IrInstanceInitializerCall> PARSER = new AbstractParser<IrInstanceInitializerCall>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrInstanceInitializerCall.1
        public IrInstanceInitializerCall parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrInstanceInitializerCall(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SYMBOL_FIELD_NUMBER = 1;
    private static final IrInstanceInitializerCall defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private long symbol_;
    private final ByteString unknownFields;

    static {
        IrInstanceInitializerCall irInstanceInitializerCall = new IrInstanceInitializerCall(true);
        defaultInstance = irInstanceInitializerCall;
        irInstanceInitializerCall.initFields();
    }

    private IrInstanceInitializerCall(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

    public static IrInstanceInitializerCall getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.symbol_ = 0L;
    }

    public static Builder newBuilder(IrInstanceInitializerCall irInstanceInitializerCall) {
        return newBuilder().mergeFrom(irInstanceInitializerCall);
    }

    public static IrInstanceInitializerCall parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrInstanceInitializerCall) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrInstanceInitializerCall parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(byteString);
    }

    public Parser<IrInstanceInitializerCall> getParserForType() {
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

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInstanceInitializerCallOrBuilder
    public long getSymbol() {
        return this.symbol_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInstanceInitializerCallOrBuilder
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

    public static final class Builder extends GeneratedMessageLite.Builder<IrInstanceInitializerCall, Builder> implements IrInstanceInitializerCallOrBuilder {
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
        public IrInstanceInitializerCall m1284build() throws UninitializedMessageException {
            IrInstanceInitializerCall irInstanceInitializerCallM1285buildPartial = m1285buildPartial();
            if (irInstanceInitializerCallM1285buildPartial.isInitialized()) {
                return irInstanceInitializerCallM1285buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irInstanceInitializerCallM1285buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrInstanceInitializerCall m1285buildPartial() {
            IrInstanceInitializerCall irInstanceInitializerCall = new IrInstanceInitializerCall(this);
            int i = (this.bitField0_ & 1) != 1 ? 0 : 1;
            irInstanceInitializerCall.symbol_ = this.symbol_;
            irInstanceInitializerCall.bitField0_ = i;
            return irInstanceInitializerCall;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1287clear() {
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
        public Builder m1291clone() {
            return create().mergeFrom(m1285buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInstanceInitializerCallOrBuilder
        public long getSymbol() {
            return this.symbol_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInstanceInitializerCallOrBuilder
        public boolean hasSymbol() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasSymbol();
        }

        public Builder mergeFrom(IrInstanceInitializerCall irInstanceInitializerCall) {
            if (irInstanceInitializerCall == IrInstanceInitializerCall.getDefaultInstance()) {
                return this;
            }
            if (irInstanceInitializerCall.hasSymbol()) {
                setSymbol(irInstanceInitializerCall.getSymbol());
            }
            setUnknownFields(getUnknownFields().concat(irInstanceInitializerCall.unknownFields));
            return this;
        }

        public Builder setSymbol(long j) {
            this.bitField0_ |= 1;
            this.symbol_ = j;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrInstanceInitializerCall m1293getDefaultInstanceForType() {
            return IrInstanceInitializerCall.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1295mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrInstanceInitializerCall irInstanceInitializerCall = null;
            try {
                try {
                    IrInstanceInitializerCall irInstanceInitializerCall2 = (IrInstanceInitializerCall) IrInstanceInitializerCall.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irInstanceInitializerCall2 != null) {
                        mergeFrom(irInstanceInitializerCall2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrInstanceInitializerCall irInstanceInitializerCall3 = (IrInstanceInitializerCall) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irInstanceInitializerCall = irInstanceInitializerCall3;
                        if (irInstanceInitializerCall != null) {
                            mergeFrom(irInstanceInitializerCall);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irInstanceInitializerCall != null) {
                    mergeFrom(irInstanceInitializerCall);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrInstanceInitializerCall m1280getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1281newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1282toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrInstanceInitializerCall parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrInstanceInitializerCall) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrInstanceInitializerCall parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrInstanceInitializerCall parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(bArr);
    }

    public static IrInstanceInitializerCall parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrInstanceInitializerCall parseFrom(InputStream inputStream) throws IOException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(inputStream);
    }

    public static IrInstanceInitializerCall parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrInstanceInitializerCall parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(codedInputStream);
    }

    public static IrInstanceInitializerCall parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrInstanceInitializerCall) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrInstanceInitializerCall(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrInstanceInitializerCall(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
