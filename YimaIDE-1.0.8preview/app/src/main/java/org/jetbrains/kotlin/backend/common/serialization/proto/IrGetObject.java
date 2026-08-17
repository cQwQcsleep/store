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
public final class IrGetObject extends GeneratedMessageLite implements IrGetObjectOrBuilder {
    public static Parser<IrGetObject> PARSER = new AbstractParser<IrGetObject>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrGetObject.1
        public IrGetObject parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrGetObject(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SYMBOL_FIELD_NUMBER = 1;
    private static final IrGetObject defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private long symbol_;
    private final ByteString unknownFields;

    static {
        IrGetObject irGetObject = new IrGetObject(true);
        defaultInstance = irGetObject;
        irGetObject.initFields();
    }

    private IrGetObject(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

    public static IrGetObject getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.symbol_ = 0L;
    }

    public static Builder newBuilder(IrGetObject irGetObject) {
        return newBuilder().mergeFrom(irGetObject);
    }

    public static IrGetObject parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrGetObject) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrGetObject parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrGetObject) PARSER.parseFrom(byteString);
    }

    public Parser<IrGetObject> getParserForType() {
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

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetObjectOrBuilder
    public long getSymbol() {
        return this.symbol_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetObjectOrBuilder
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

    public static final class Builder extends GeneratedMessageLite.Builder<IrGetObject, Builder> implements IrGetObjectOrBuilder {
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
        public IrGetObject m1220build() throws UninitializedMessageException {
            IrGetObject irGetObjectM1221buildPartial = m1221buildPartial();
            if (irGetObjectM1221buildPartial.isInitialized()) {
                return irGetObjectM1221buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irGetObjectM1221buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrGetObject m1221buildPartial() {
            IrGetObject irGetObject = new IrGetObject(this);
            int i = (this.bitField0_ & 1) != 1 ? 0 : 1;
            irGetObject.symbol_ = this.symbol_;
            irGetObject.bitField0_ = i;
            return irGetObject;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1223clear() {
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
        public Builder m1227clone() {
            return create().mergeFrom(m1221buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetObjectOrBuilder
        public long getSymbol() {
            return this.symbol_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrGetObjectOrBuilder
        public boolean hasSymbol() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasSymbol();
        }

        public Builder mergeFrom(IrGetObject irGetObject) {
            if (irGetObject == IrGetObject.getDefaultInstance()) {
                return this;
            }
            if (irGetObject.hasSymbol()) {
                setSymbol(irGetObject.getSymbol());
            }
            setUnknownFields(getUnknownFields().concat(irGetObject.unknownFields));
            return this;
        }

        public Builder setSymbol(long j) {
            this.bitField0_ |= 1;
            this.symbol_ = j;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrGetObject m1229getDefaultInstanceForType() {
            return IrGetObject.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1231mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrGetObject irGetObject = null;
            try {
                try {
                    IrGetObject irGetObject2 = (IrGetObject) IrGetObject.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irGetObject2 != null) {
                        mergeFrom(irGetObject2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrGetObject irGetObject3 = (IrGetObject) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irGetObject = irGetObject3;
                        if (irGetObject != null) {
                            mergeFrom(irGetObject);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irGetObject != null) {
                    mergeFrom(irGetObject);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrGetObject m1216getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1217newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1218toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrGetObject parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetObject) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrGetObject parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrGetObject) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrGetObject parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrGetObject) PARSER.parseFrom(bArr);
    }

    public static IrGetObject parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrGetObject) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrGetObject parseFrom(InputStream inputStream) throws IOException {
        return (IrGetObject) PARSER.parseFrom(inputStream);
    }

    public static IrGetObject parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetObject) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrGetObject parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrGetObject) PARSER.parseFrom(codedInputStream);
    }

    public static IrGetObject parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrGetObject) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrGetObject(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrGetObject(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
