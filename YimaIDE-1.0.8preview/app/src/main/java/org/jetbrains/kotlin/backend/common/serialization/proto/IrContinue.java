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
public final class IrContinue extends GeneratedMessageLite implements IrContinueOrBuilder {
    public static final int LABEL_FIELD_NUMBER = 2;
    public static final int LOOP_ID_FIELD_NUMBER = 1;
    public static Parser<IrContinue> PARSER = new AbstractParser<IrContinue>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrContinue.1
        public IrContinue parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrContinue(codedInputStream, extensionRegistryLite);
        }
    };
    private static final IrContinue defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int label_;
    private int loopId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        IrContinue irContinue = new IrContinue(true);
        defaultInstance = irContinue;
        irContinue.initFields();
    }

    private IrContinue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.loopId_ = codedInputStream.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.label_ = codedInputStream.readInt32();
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

    public static IrContinue getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.loopId_ = 0;
        this.label_ = 0;
    }

    public static Builder newBuilder(IrContinue irContinue) {
        return newBuilder().mergeFrom(irContinue);
    }

    public static IrContinue parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrContinue) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrContinue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrContinue) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
    public int getLabel() {
        return this.label_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
    public int getLoopId() {
        return this.loopId_;
    }

    public Parser<IrContinue> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.loopId_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.label_);
        }
        int size = iComputeInt32Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
    public boolean hasLoopId() {
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
        if (hasLoopId()) {
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
            codedOutputStream.writeInt32(1, this.loopId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.label_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrContinue, Builder> implements IrContinueOrBuilder {
        private int bitField0_;
        private int label_;
        private int loopId_;

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
        public IrContinue m835build() throws UninitializedMessageException {
            IrContinue irContinueM836buildPartial = m836buildPartial();
            if (irContinueM836buildPartial.isInitialized()) {
                return irContinueM836buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irContinueM836buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrContinue m836buildPartial() {
            IrContinue irContinue = new IrContinue(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            irContinue.loopId_ = this.loopId_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            irContinue.label_ = this.label_;
            irContinue.bitField0_ = i2;
            return irContinue;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m838clear() {
            super.clear();
            this.loopId_ = 0;
            int i = this.bitField0_;
            this.label_ = 0;
            this.bitField0_ = i & (-4);
            return this;
        }

        public Builder clearLabel() {
            this.bitField0_ &= -3;
            this.label_ = 0;
            return this;
        }

        public Builder clearLoopId() {
            this.bitField0_ &= -2;
            this.loopId_ = 0;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m842clone() {
            return create().mergeFrom(m836buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
        public int getLabel() {
            return this.label_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
        public int getLoopId() {
            return this.loopId_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
        public boolean hasLabel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrContinueOrBuilder
        public boolean hasLoopId() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasLoopId();
        }

        public Builder mergeFrom(IrContinue irContinue) {
            if (irContinue == IrContinue.getDefaultInstance()) {
                return this;
            }
            if (irContinue.hasLoopId()) {
                setLoopId(irContinue.getLoopId());
            }
            if (irContinue.hasLabel()) {
                setLabel(irContinue.getLabel());
            }
            setUnknownFields(getUnknownFields().concat(irContinue.unknownFields));
            return this;
        }

        public Builder setLabel(int i) {
            this.bitField0_ |= 2;
            this.label_ = i;
            return this;
        }

        public Builder setLoopId(int i) {
            this.bitField0_ |= 1;
            this.loopId_ = i;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrContinue m844getDefaultInstanceForType() {
            return IrContinue.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m846mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrContinue irContinue = null;
            try {
                try {
                    IrContinue irContinue2 = (IrContinue) IrContinue.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irContinue2 != null) {
                        mergeFrom(irContinue2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrContinue irContinue3 = (IrContinue) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irContinue = irContinue3;
                        if (irContinue != null) {
                            mergeFrom(irContinue);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irContinue != null) {
                    mergeFrom(irContinue);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrContinue m831getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m832newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m833toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrContinue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrContinue) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrContinue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrContinue) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrContinue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrContinue) PARSER.parseFrom(bArr);
    }

    public static IrContinue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrContinue) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrContinue parseFrom(InputStream inputStream) throws IOException {
        return (IrContinue) PARSER.parseFrom(inputStream);
    }

    public static IrContinue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrContinue) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrContinue parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrContinue) PARSER.parseFrom(codedInputStream);
    }

    public static IrContinue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrContinue) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrContinue(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrContinue(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
