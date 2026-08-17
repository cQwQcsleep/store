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
public final class IrLocalDelegatedPropertyReference extends GeneratedMessageLite implements IrLocalDelegatedPropertyReferenceOrBuilder {
    public static final int DELEGATE_FIELD_NUMBER = 1;
    public static final int GETTER_FIELD_NUMBER = 2;
    public static final int ORIGIN_NAME_FIELD_NUMBER = 5;
    public static Parser<IrLocalDelegatedPropertyReference> PARSER = new AbstractParser<IrLocalDelegatedPropertyReference>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReference.1
        public IrLocalDelegatedPropertyReference parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrLocalDelegatedPropertyReference(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SETTER_FIELD_NUMBER = 3;
    public static final int SYMBOL_FIELD_NUMBER = 4;
    private static final IrLocalDelegatedPropertyReference defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private long delegate_;
    private long getter_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int originName_;
    private long setter_;
    private long symbol_;
    private final ByteString unknownFields;

    static {
        IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReference = new IrLocalDelegatedPropertyReference(true);
        defaultInstance = irLocalDelegatedPropertyReference;
        irLocalDelegatedPropertyReference.initFields();
    }

    private IrLocalDelegatedPropertyReference(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.delegate_ = codedInputStream.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.getter_ = codedInputStream.readInt64();
                        } else if (tag == 24) {
                            this.bitField0_ |= 4;
                            this.setter_ = codedInputStream.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ |= 8;
                            this.symbol_ = codedInputStream.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ |= 16;
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

    public static IrLocalDelegatedPropertyReference getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.delegate_ = 0L;
        this.getter_ = 0L;
        this.setter_ = 0L;
        this.symbol_ = 0L;
        this.originName_ = 0;
    }

    public static Builder newBuilder(IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReference) {
        return newBuilder().mergeFrom(irLocalDelegatedPropertyReference);
    }

    public static IrLocalDelegatedPropertyReference parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public long getDelegate() {
        return this.delegate_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public long getGetter() {
        return this.getter_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public int getOriginName() {
        return this.originName_;
    }

    public Parser<IrLocalDelegatedPropertyReference> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt64Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.delegate_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt64Size += CodedOutputStream.computeInt64Size(2, this.getter_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeInt64Size += CodedOutputStream.computeInt64Size(3, this.setter_);
        }
        if ((this.bitField0_ & 8) == 8) {
            iComputeInt64Size += CodedOutputStream.computeInt64Size(4, this.symbol_);
        }
        if ((this.bitField0_ & 16) == 16) {
            iComputeInt64Size += CodedOutputStream.computeInt32Size(5, this.originName_);
        }
        int size = iComputeInt64Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public long getSetter() {
        return this.setter_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public long getSymbol() {
        return this.symbol_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public boolean hasDelegate() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public boolean hasGetter() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public boolean hasOriginName() {
        return (this.bitField0_ & 16) == 16;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public boolean hasSetter() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
    public boolean hasSymbol() {
        return (this.bitField0_ & 8) == 8;
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
            codedOutputStream.writeInt64(1, this.delegate_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt64(2, this.getter_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt64(3, this.setter_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeInt64(4, this.symbol_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeInt32(5, this.originName_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrLocalDelegatedPropertyReference, Builder> implements IrLocalDelegatedPropertyReferenceOrBuilder {
        private int bitField0_;
        private long delegate_;
        private long getter_;
        private int originName_;
        private long setter_;
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
        public IrLocalDelegatedPropertyReference m1316build() throws UninitializedMessageException {
            IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReferenceM1317buildPartial = m1317buildPartial();
            if (irLocalDelegatedPropertyReferenceM1317buildPartial.isInitialized()) {
                return irLocalDelegatedPropertyReferenceM1317buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irLocalDelegatedPropertyReferenceM1317buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrLocalDelegatedPropertyReference m1317buildPartial() {
            IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReference = new IrLocalDelegatedPropertyReference(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            irLocalDelegatedPropertyReference.delegate_ = this.delegate_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            irLocalDelegatedPropertyReference.getter_ = this.getter_;
            if ((i & 4) == 4) {
                i2 |= 4;
            }
            irLocalDelegatedPropertyReference.setter_ = this.setter_;
            if ((i & 8) == 8) {
                i2 |= 8;
            }
            irLocalDelegatedPropertyReference.symbol_ = this.symbol_;
            if ((i & 16) == 16) {
                i2 |= 16;
            }
            irLocalDelegatedPropertyReference.originName_ = this.originName_;
            irLocalDelegatedPropertyReference.bitField0_ = i2;
            return irLocalDelegatedPropertyReference;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1319clear() {
            super.clear();
            this.delegate_ = 0L;
            int i = this.bitField0_;
            this.getter_ = 0L;
            this.setter_ = 0L;
            this.symbol_ = 0L;
            this.originName_ = 0;
            this.bitField0_ = i & (-32);
            return this;
        }

        public Builder clearDelegate() {
            this.bitField0_ &= -2;
            this.delegate_ = 0L;
            return this;
        }

        public Builder clearGetter() {
            this.bitField0_ &= -3;
            this.getter_ = 0L;
            return this;
        }

        public Builder clearOriginName() {
            this.bitField0_ &= -17;
            this.originName_ = 0;
            return this;
        }

        public Builder clearSetter() {
            this.bitField0_ &= -5;
            this.setter_ = 0L;
            return this;
        }

        public Builder clearSymbol() {
            this.bitField0_ &= -9;
            this.symbol_ = 0L;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1323clone() {
            return create().mergeFrom(m1317buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public long getDelegate() {
            return this.delegate_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public long getGetter() {
            return this.getter_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public int getOriginName() {
            return this.originName_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public long getSetter() {
            return this.setter_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public long getSymbol() {
            return this.symbol_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public boolean hasDelegate() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public boolean hasGetter() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public boolean hasOriginName() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public boolean hasSetter() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrLocalDelegatedPropertyReferenceOrBuilder
        public boolean hasSymbol() {
            return (this.bitField0_ & 8) == 8;
        }

        public final boolean isInitialized() {
            return hasSymbol();
        }

        public Builder mergeFrom(IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReference) {
            if (irLocalDelegatedPropertyReference == IrLocalDelegatedPropertyReference.getDefaultInstance()) {
                return this;
            }
            if (irLocalDelegatedPropertyReference.hasDelegate()) {
                setDelegate(irLocalDelegatedPropertyReference.getDelegate());
            }
            if (irLocalDelegatedPropertyReference.hasGetter()) {
                setGetter(irLocalDelegatedPropertyReference.getGetter());
            }
            if (irLocalDelegatedPropertyReference.hasSetter()) {
                setSetter(irLocalDelegatedPropertyReference.getSetter());
            }
            if (irLocalDelegatedPropertyReference.hasSymbol()) {
                setSymbol(irLocalDelegatedPropertyReference.getSymbol());
            }
            if (irLocalDelegatedPropertyReference.hasOriginName()) {
                setOriginName(irLocalDelegatedPropertyReference.getOriginName());
            }
            setUnknownFields(getUnknownFields().concat(irLocalDelegatedPropertyReference.unknownFields));
            return this;
        }

        public Builder setDelegate(long j) {
            this.bitField0_ |= 1;
            this.delegate_ = j;
            return this;
        }

        public Builder setGetter(long j) {
            this.bitField0_ |= 2;
            this.getter_ = j;
            return this;
        }

        public Builder setOriginName(int i) {
            this.bitField0_ |= 16;
            this.originName_ = i;
            return this;
        }

        public Builder setSetter(long j) {
            this.bitField0_ |= 4;
            this.setter_ = j;
            return this;
        }

        public Builder setSymbol(long j) {
            this.bitField0_ |= 8;
            this.symbol_ = j;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrLocalDelegatedPropertyReference m1325getDefaultInstanceForType() {
            return IrLocalDelegatedPropertyReference.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1327mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReference = null;
            try {
                try {
                    IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReference2 = (IrLocalDelegatedPropertyReference) IrLocalDelegatedPropertyReference.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irLocalDelegatedPropertyReference2 != null) {
                        mergeFrom(irLocalDelegatedPropertyReference2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrLocalDelegatedPropertyReference irLocalDelegatedPropertyReference3 = (IrLocalDelegatedPropertyReference) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irLocalDelegatedPropertyReference = irLocalDelegatedPropertyReference3;
                        if (irLocalDelegatedPropertyReference != null) {
                            mergeFrom(irLocalDelegatedPropertyReference);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irLocalDelegatedPropertyReference != null) {
                    mergeFrom(irLocalDelegatedPropertyReference);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrLocalDelegatedPropertyReference m1312getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1313newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1314toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrLocalDelegatedPropertyReference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(bArr);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(InputStream inputStream) throws IOException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(inputStream);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(codedInputStream);
    }

    public static IrLocalDelegatedPropertyReference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrLocalDelegatedPropertyReference) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrLocalDelegatedPropertyReference(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrLocalDelegatedPropertyReference(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
