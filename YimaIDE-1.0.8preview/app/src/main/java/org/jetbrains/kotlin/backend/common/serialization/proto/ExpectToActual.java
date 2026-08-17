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
public final class ExpectToActual extends GeneratedMessageLite implements ExpectToActualOrBuilder {
    public static final int ACTUAL_FIELD_NUMBER = 2;
    public static final int EXPECT_FIELD_NUMBER = 1;
    public static Parser<ExpectToActual> PARSER = new AbstractParser<ExpectToActual>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActual.1
        public ExpectToActual parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ExpectToActual(codedInputStream, extensionRegistryLite);
        }
    };
    private static final ExpectToActual defaultInstance;
    private static final long serialVersionUID = 0;
    private long actual_;
    private int bitField0_;
    private long expect_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        ExpectToActual expectToActual = new ExpectToActual(true);
        defaultInstance = expectToActual;
        expectToActual.initFields();
    }

    private ExpectToActual(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.expect_ = codedInputStream.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.actual_ = codedInputStream.readInt64();
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

    public static ExpectToActual getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.expect_ = 0L;
        this.actual_ = 0L;
    }

    public static Builder newBuilder(ExpectToActual expectToActual) {
        return newBuilder().mergeFrom(expectToActual);
    }

    public static ExpectToActual parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ExpectToActual) PARSER.parseDelimitedFrom(inputStream);
    }

    public static ExpectToActual parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExpectToActual) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
    public long getActual() {
        return this.actual_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
    public long getExpect() {
        return this.expect_;
    }

    public Parser<ExpectToActual> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt64Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.expect_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt64Size += CodedOutputStream.computeInt64Size(2, this.actual_);
        }
        int size = iComputeInt64Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
    public boolean hasActual() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
    public boolean hasExpect() {
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
        if (hasExpect()) {
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
            codedOutputStream.writeInt64(1, this.expect_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt64(2, this.actual_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExpectToActual, Builder> implements ExpectToActualOrBuilder {
        private long actual_;
        private int bitField0_;
        private long expect_;

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
        public ExpectToActual m499build() throws UninitializedMessageException {
            ExpectToActual expectToActualM500buildPartial = m500buildPartial();
            if (expectToActualM500buildPartial.isInitialized()) {
                return expectToActualM500buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(expectToActualM500buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public ExpectToActual m500buildPartial() {
            ExpectToActual expectToActual = new ExpectToActual(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            expectToActual.expect_ = this.expect_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            expectToActual.actual_ = this.actual_;
            expectToActual.bitField0_ = i2;
            return expectToActual;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m502clear() {
            super.clear();
            this.expect_ = 0L;
            int i = this.bitField0_;
            this.actual_ = 0L;
            this.bitField0_ = i & (-4);
            return this;
        }

        public Builder clearActual() {
            this.bitField0_ &= -3;
            this.actual_ = 0L;
            return this;
        }

        public Builder clearExpect() {
            this.bitField0_ &= -2;
            this.expect_ = 0L;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m506clone() {
            return create().mergeFrom(m500buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
        public long getActual() {
            return this.actual_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
        public long getExpect() {
            return this.expect_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
        public boolean hasActual() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.ExpectToActualOrBuilder
        public boolean hasExpect() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return hasExpect();
        }

        public Builder mergeFrom(ExpectToActual expectToActual) {
            if (expectToActual == ExpectToActual.getDefaultInstance()) {
                return this;
            }
            if (expectToActual.hasExpect()) {
                setExpect(expectToActual.getExpect());
            }
            if (expectToActual.hasActual()) {
                setActual(expectToActual.getActual());
            }
            setUnknownFields(getUnknownFields().concat(expectToActual.unknownFields));
            return this;
        }

        public Builder setActual(long j) {
            this.bitField0_ |= 2;
            this.actual_ = j;
            return this;
        }

        public Builder setExpect(long j) {
            this.bitField0_ |= 1;
            this.expect_ = j;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ExpectToActual m508getDefaultInstanceForType() {
            return ExpectToActual.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m510mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            ExpectToActual expectToActual = null;
            try {
                try {
                    ExpectToActual expectToActual2 = (ExpectToActual) ExpectToActual.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (expectToActual2 != null) {
                        mergeFrom(expectToActual2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    ExpectToActual expectToActual3 = (ExpectToActual) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        expectToActual = expectToActual3;
                        if (expectToActual != null) {
                            mergeFrom(expectToActual);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (expectToActual != null) {
                    mergeFrom(expectToActual);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public ExpectToActual m495getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m496newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m497toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static ExpectToActual parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExpectToActual) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static ExpectToActual parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExpectToActual) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ExpectToActual parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExpectToActual) PARSER.parseFrom(bArr);
    }

    public static ExpectToActual parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExpectToActual) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ExpectToActual parseFrom(InputStream inputStream) throws IOException {
        return (ExpectToActual) PARSER.parseFrom(inputStream);
    }

    public static ExpectToActual parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExpectToActual) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static ExpectToActual parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ExpectToActual) PARSER.parseFrom(codedInputStream);
    }

    public static ExpectToActual parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExpectToActual) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private ExpectToActual(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private ExpectToActual(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
