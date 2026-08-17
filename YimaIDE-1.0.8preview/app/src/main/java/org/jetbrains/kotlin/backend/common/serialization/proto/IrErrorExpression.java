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
public final class IrErrorExpression extends GeneratedMessageLite implements IrErrorExpressionOrBuilder {
    public static final int DESCRIPTION_FIELD_NUMBER = 1;
    public static Parser<IrErrorExpression> PARSER = new AbstractParser<IrErrorExpression>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrErrorExpression.1
        public IrErrorExpression parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrErrorExpression(codedInputStream, extensionRegistryLite);
        }
    };
    private static final IrErrorExpression defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int description_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        IrErrorExpression irErrorExpression = new IrErrorExpression(true);
        defaultInstance = irErrorExpression;
        irErrorExpression.initFields();
    }

    private IrErrorExpression(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.description_ = codedInputStream.readInt32();
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

    public static IrErrorExpression getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.description_ = 0;
    }

    public static Builder newBuilder(IrErrorExpression irErrorExpression) {
        return newBuilder().mergeFrom(irErrorExpression);
    }

    public static IrErrorExpression parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrErrorExpression) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrErrorExpression parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrErrorExpression) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrErrorExpressionOrBuilder
    public int getDescription() {
        return this.description_;
    }

    public Parser<IrErrorExpression> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = ((this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.description_) : 0) + this.unknownFields.size();
        this.memoizedSerializedSize = iComputeInt32Size;
        return iComputeInt32Size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrErrorExpressionOrBuilder
    public boolean hasDescription() {
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
        if (hasDescription()) {
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
            codedOutputStream.writeInt32(1, this.description_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrErrorExpression, Builder> implements IrErrorExpressionOrBuilder {
        private int bitField0_;
        private int description_;

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
        public IrErrorExpression m1028build() throws UninitializedMessageException {
            IrErrorExpression irErrorExpressionM1029buildPartial = m1029buildPartial();
            if (irErrorExpressionM1029buildPartial.isInitialized()) {
                return irErrorExpressionM1029buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irErrorExpressionM1029buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrErrorExpression m1029buildPartial() {
            IrErrorExpression irErrorExpression = new IrErrorExpression(this);
            int i = (this.bitField0_ & 1) != 1 ? 0 : 1;
            irErrorExpression.description_ = this.description_;
            irErrorExpression.bitField0_ = i;
            return irErrorExpression;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1031clear() {
            super.clear();
            this.description_ = 0;
            this.bitField0_ &= -2;
            return this;
        }

        public Builder clearDescription() {
            this.bitField0_ &= -2;
            this.description_ = 0;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1035clone() {
            return create().mergeFrom(m1029buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrErrorExpressionOrBuilder
        public int getDescription() {
            return this.description_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrErrorExpressionOrBuilder
        public boolean hasDescription() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasDescription();
        }

        public Builder mergeFrom(IrErrorExpression irErrorExpression) {
            if (irErrorExpression == IrErrorExpression.getDefaultInstance()) {
                return this;
            }
            if (irErrorExpression.hasDescription()) {
                setDescription(irErrorExpression.getDescription());
            }
            setUnknownFields(getUnknownFields().concat(irErrorExpression.unknownFields));
            return this;
        }

        public Builder setDescription(int i) {
            this.bitField0_ |= 1;
            this.description_ = i;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrErrorExpression m1037getDefaultInstanceForType() {
            return IrErrorExpression.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1039mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrErrorExpression irErrorExpression = null;
            try {
                try {
                    IrErrorExpression irErrorExpression2 = (IrErrorExpression) IrErrorExpression.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irErrorExpression2 != null) {
                        mergeFrom(irErrorExpression2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrErrorExpression irErrorExpression3 = (IrErrorExpression) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irErrorExpression = irErrorExpression3;
                        if (irErrorExpression != null) {
                            mergeFrom(irErrorExpression);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irErrorExpression != null) {
                    mergeFrom(irErrorExpression);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrErrorExpression m1024getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1025newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1026toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrErrorExpression parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrErrorExpression) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrErrorExpression parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrErrorExpression) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrErrorExpression parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrErrorExpression) PARSER.parseFrom(bArr);
    }

    public static IrErrorExpression parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrErrorExpression) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrErrorExpression parseFrom(InputStream inputStream) throws IOException {
        return (IrErrorExpression) PARSER.parseFrom(inputStream);
    }

    public static IrErrorExpression parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrErrorExpression) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrErrorExpression parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrErrorExpression) PARSER.parseFrom(codedInputStream);
    }

    public static IrErrorExpression parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrErrorExpression) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrErrorExpression(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrErrorExpression(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
