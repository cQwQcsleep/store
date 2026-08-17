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
public final class IrBreak extends GeneratedMessageLite implements IrBreakOrBuilder {
    public static final int LABEL_FIELD_NUMBER = 2;
    public static final int LOOP_ID_FIELD_NUMBER = 1;
    public static Parser<IrBreak> PARSER = new AbstractParser<IrBreak>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrBreak.1
        public IrBreak parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrBreak(codedInputStream, extensionRegistryLite);
        }
    };
    private static final IrBreak defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int label_;
    private int loopId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        IrBreak irBreak = new IrBreak(true);
        defaultInstance = irBreak;
        irBreak.initFields();
    }

    private IrBreak(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

    public static IrBreak getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.loopId_ = 0;
        this.label_ = 0;
    }

    public static Builder newBuilder(IrBreak irBreak) {
        return newBuilder().mergeFrom(irBreak);
    }

    public static IrBreak parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrBreak) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrBreak parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrBreak) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
    public int getLabel() {
        return this.label_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
    public int getLoopId() {
        return this.loopId_;
    }

    public Parser<IrBreak> getParserForType() {
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

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
    public boolean hasLabel() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
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

    public static final class Builder extends GeneratedMessageLite.Builder<IrBreak, Builder> implements IrBreakOrBuilder {
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
        public IrBreak m691build() throws UninitializedMessageException {
            IrBreak irBreakM692buildPartial = m692buildPartial();
            if (irBreakM692buildPartial.isInitialized()) {
                return irBreakM692buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irBreakM692buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrBreak m692buildPartial() {
            IrBreak irBreak = new IrBreak(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            irBreak.loopId_ = this.loopId_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            irBreak.label_ = this.label_;
            irBreak.bitField0_ = i2;
            return irBreak;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m694clear() {
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
        public Builder m698clone() {
            return create().mergeFrom(m692buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
        public int getLabel() {
            return this.label_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
        public int getLoopId() {
            return this.loopId_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
        public boolean hasLabel() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrBreakOrBuilder
        public boolean hasLoopId() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasLoopId();
        }

        public Builder mergeFrom(IrBreak irBreak) {
            if (irBreak == IrBreak.getDefaultInstance()) {
                return this;
            }
            if (irBreak.hasLoopId()) {
                setLoopId(irBreak.getLoopId());
            }
            if (irBreak.hasLabel()) {
                setLabel(irBreak.getLabel());
            }
            setUnknownFields(getUnknownFields().concat(irBreak.unknownFields));
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
        public IrBreak m700getDefaultInstanceForType() {
            return IrBreak.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m702mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrBreak irBreak = null;
            try {
                try {
                    IrBreak irBreak2 = (IrBreak) IrBreak.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irBreak2 != null) {
                        mergeFrom(irBreak2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrBreak irBreak3 = (IrBreak) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irBreak = irBreak3;
                        if (irBreak != null) {
                            mergeFrom(irBreak);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irBreak != null) {
                    mergeFrom(irBreak);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrBreak m687getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m688newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m689toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrBreak parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrBreak) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrBreak parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrBreak) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrBreak parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrBreak) PARSER.parseFrom(bArr);
    }

    public static IrBreak parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrBreak) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrBreak parseFrom(InputStream inputStream) throws IOException {
        return (IrBreak) PARSER.parseFrom(inputStream);
    }

    public static IrBreak parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrBreak) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrBreak parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrBreak) PARSER.parseFrom(codedInputStream);
    }

    public static IrBreak parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrBreak) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrBreak(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrBreak(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
