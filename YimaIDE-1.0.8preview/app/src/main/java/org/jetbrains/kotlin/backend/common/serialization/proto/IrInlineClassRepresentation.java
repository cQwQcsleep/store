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
public final class IrInlineClassRepresentation extends GeneratedMessageLite implements IrInlineClassRepresentationOrBuilder {
    public static Parser<IrInlineClassRepresentation> PARSER = new AbstractParser<IrInlineClassRepresentation>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentation.1
        public IrInlineClassRepresentation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrInlineClassRepresentation(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int UNDERLYING_PROPERTY_NAME_FIELD_NUMBER = 1;
    public static final int UNDERLYING_PROPERTY_TYPE_FIELD_NUMBER = 2;
    private static final IrInlineClassRepresentation defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int underlyingPropertyName_;
    private int underlyingPropertyType_;
    private final ByteString unknownFields;

    static {
        IrInlineClassRepresentation irInlineClassRepresentation = new IrInlineClassRepresentation(true);
        defaultInstance = irInlineClassRepresentation;
        irInlineClassRepresentation.initFields();
    }

    private IrInlineClassRepresentation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.underlyingPropertyName_ = codedInputStream.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.underlyingPropertyType_ = codedInputStream.readInt32();
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

    public static IrInlineClassRepresentation getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.underlyingPropertyName_ = 0;
        this.underlyingPropertyType_ = 0;
    }

    public static Builder newBuilder(IrInlineClassRepresentation irInlineClassRepresentation) {
        return newBuilder().mergeFrom(irInlineClassRepresentation);
    }

    public static IrInlineClassRepresentation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrInlineClassRepresentation) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrInlineClassRepresentation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(byteString);
    }

    public Parser<IrInlineClassRepresentation> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.underlyingPropertyName_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.underlyingPropertyType_);
        }
        int size = iComputeInt32Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
    public int getUnderlyingPropertyName() {
        return this.underlyingPropertyName_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
    public int getUnderlyingPropertyType() {
        return this.underlyingPropertyType_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
    public boolean hasUnderlyingPropertyName() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
    public boolean hasUnderlyingPropertyType() {
        return (this.bitField0_ & 2) == 2;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (!hasUnderlyingPropertyName()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasUnderlyingPropertyType()) {
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
            codedOutputStream.writeInt32(1, this.underlyingPropertyName_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.underlyingPropertyType_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrInlineClassRepresentation, Builder> implements IrInlineClassRepresentationOrBuilder {
        private int bitField0_;
        private int underlyingPropertyName_;
        private int underlyingPropertyType_;

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
        public IrInlineClassRepresentation m1252build() throws UninitializedMessageException {
            IrInlineClassRepresentation irInlineClassRepresentationM1253buildPartial = m1253buildPartial();
            if (irInlineClassRepresentationM1253buildPartial.isInitialized()) {
                return irInlineClassRepresentationM1253buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irInlineClassRepresentationM1253buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrInlineClassRepresentation m1253buildPartial() {
            IrInlineClassRepresentation irInlineClassRepresentation = new IrInlineClassRepresentation(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            irInlineClassRepresentation.underlyingPropertyName_ = this.underlyingPropertyName_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            irInlineClassRepresentation.underlyingPropertyType_ = this.underlyingPropertyType_;
            irInlineClassRepresentation.bitField0_ = i2;
            return irInlineClassRepresentation;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1255clear() {
            super.clear();
            this.underlyingPropertyName_ = 0;
            int i = this.bitField0_;
            this.underlyingPropertyType_ = 0;
            this.bitField0_ = i & (-4);
            return this;
        }

        public Builder clearUnderlyingPropertyName() {
            this.bitField0_ &= -2;
            this.underlyingPropertyName_ = 0;
            return this;
        }

        public Builder clearUnderlyingPropertyType() {
            this.bitField0_ &= -3;
            this.underlyingPropertyType_ = 0;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1259clone() {
            return create().mergeFrom(m1253buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
        public int getUnderlyingPropertyName() {
            return this.underlyingPropertyName_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
        public int getUnderlyingPropertyType() {
            return this.underlyingPropertyType_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
        public boolean hasUnderlyingPropertyName() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrInlineClassRepresentationOrBuilder
        public boolean hasUnderlyingPropertyType() {
            return (this.bitField0_ & 2) == 2;
        }

        public final boolean isInitialized() {
            return hasUnderlyingPropertyName() && hasUnderlyingPropertyType();
        }

        public Builder mergeFrom(IrInlineClassRepresentation irInlineClassRepresentation) {
            if (irInlineClassRepresentation == IrInlineClassRepresentation.getDefaultInstance()) {
                return this;
            }
            if (irInlineClassRepresentation.hasUnderlyingPropertyName()) {
                setUnderlyingPropertyName(irInlineClassRepresentation.getUnderlyingPropertyName());
            }
            if (irInlineClassRepresentation.hasUnderlyingPropertyType()) {
                setUnderlyingPropertyType(irInlineClassRepresentation.getUnderlyingPropertyType());
            }
            setUnknownFields(getUnknownFields().concat(irInlineClassRepresentation.unknownFields));
            return this;
        }

        public Builder setUnderlyingPropertyName(int i) {
            this.bitField0_ |= 1;
            this.underlyingPropertyName_ = i;
            return this;
        }

        public Builder setUnderlyingPropertyType(int i) {
            this.bitField0_ |= 2;
            this.underlyingPropertyType_ = i;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrInlineClassRepresentation m1261getDefaultInstanceForType() {
            return IrInlineClassRepresentation.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1263mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrInlineClassRepresentation irInlineClassRepresentation = null;
            try {
                try {
                    IrInlineClassRepresentation irInlineClassRepresentation2 = (IrInlineClassRepresentation) IrInlineClassRepresentation.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irInlineClassRepresentation2 != null) {
                        mergeFrom(irInlineClassRepresentation2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrInlineClassRepresentation irInlineClassRepresentation3 = (IrInlineClassRepresentation) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irInlineClassRepresentation = irInlineClassRepresentation3;
                        if (irInlineClassRepresentation != null) {
                            mergeFrom(irInlineClassRepresentation);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irInlineClassRepresentation != null) {
                    mergeFrom(irInlineClassRepresentation);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrInlineClassRepresentation m1248getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1249newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1250toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrInlineClassRepresentation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrInlineClassRepresentation) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrInlineClassRepresentation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrInlineClassRepresentation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(bArr);
    }

    public static IrInlineClassRepresentation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrInlineClassRepresentation parseFrom(InputStream inputStream) throws IOException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(inputStream);
    }

    public static IrInlineClassRepresentation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrInlineClassRepresentation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(codedInputStream);
    }

    public static IrInlineClassRepresentation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrInlineClassRepresentation) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrInlineClassRepresentation(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrInlineClassRepresentation(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
