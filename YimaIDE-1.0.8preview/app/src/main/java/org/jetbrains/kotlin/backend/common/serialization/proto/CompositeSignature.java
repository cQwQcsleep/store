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
public final class CompositeSignature extends GeneratedMessageLite implements CompositeSignatureOrBuilder {
    public static final int CONTAINER_SIG_FIELD_NUMBER = 1;
    public static final int INNER_SIG_FIELD_NUMBER = 2;
    public static Parser<CompositeSignature> PARSER = new AbstractParser<CompositeSignature>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignature.1
        public CompositeSignature parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CompositeSignature(codedInputStream, extensionRegistryLite);
        }
    };
    private static final CompositeSignature defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int containerSig_;
    private int innerSig_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        CompositeSignature compositeSignature = new CompositeSignature(true);
        defaultInstance = compositeSignature;
        compositeSignature.initFields();
    }

    private CompositeSignature(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.containerSig_ = codedInputStream.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.innerSig_ = codedInputStream.readInt32();
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

    public static CompositeSignature getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.containerSig_ = 0;
        this.innerSig_ = 0;
    }

    public static Builder newBuilder(CompositeSignature compositeSignature) {
        return newBuilder().mergeFrom(compositeSignature);
    }

    public static CompositeSignature parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CompositeSignature) PARSER.parseDelimitedFrom(inputStream);
    }

    public static CompositeSignature parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CompositeSignature) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
    public int getContainerSig() {
        return this.containerSig_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
    public int getInnerSig() {
        return this.innerSig_;
    }

    public Parser<CompositeSignature> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.containerSig_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(2, this.innerSig_);
        }
        int size = iComputeInt32Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
    public boolean hasContainerSig() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
    public boolean hasInnerSig() {
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
        if (!hasContainerSig()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasInnerSig()) {
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
            codedOutputStream.writeInt32(1, this.containerSig_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.innerSig_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CompositeSignature, Builder> implements CompositeSignatureOrBuilder {
        private int bitField0_;
        private int containerSig_;
        private int innerSig_;

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
        public CompositeSignature m467build() throws UninitializedMessageException {
            CompositeSignature compositeSignatureM468buildPartial = m468buildPartial();
            if (compositeSignatureM468buildPartial.isInitialized()) {
                return compositeSignatureM468buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(compositeSignatureM468buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public CompositeSignature m468buildPartial() {
            CompositeSignature compositeSignature = new CompositeSignature(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            compositeSignature.containerSig_ = this.containerSig_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            compositeSignature.innerSig_ = this.innerSig_;
            compositeSignature.bitField0_ = i2;
            return compositeSignature;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m470clear() {
            super.clear();
            this.containerSig_ = 0;
            int i = this.bitField0_;
            this.innerSig_ = 0;
            this.bitField0_ = i & (-4);
            return this;
        }

        public Builder clearContainerSig() {
            this.bitField0_ &= -2;
            this.containerSig_ = 0;
            return this;
        }

        public Builder clearInnerSig() {
            this.bitField0_ &= -3;
            this.innerSig_ = 0;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m474clone() {
            return create().mergeFrom(m468buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
        public int getContainerSig() {
            return this.containerSig_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
        public int getInnerSig() {
            return this.innerSig_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
        public boolean hasContainerSig() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CompositeSignatureOrBuilder
        public boolean hasInnerSig() {
            return (this.bitField0_ & 2) == 2;
        }

        public final boolean isInitialized() {
            return hasContainerSig() && hasInnerSig();
        }

        public Builder mergeFrom(CompositeSignature compositeSignature) {
            if (compositeSignature == CompositeSignature.getDefaultInstance()) {
                return this;
            }
            if (compositeSignature.hasContainerSig()) {
                setContainerSig(compositeSignature.getContainerSig());
            }
            if (compositeSignature.hasInnerSig()) {
                setInnerSig(compositeSignature.getInnerSig());
            }
            setUnknownFields(getUnknownFields().concat(compositeSignature.unknownFields));
            return this;
        }

        public Builder setContainerSig(int i) {
            this.bitField0_ |= 1;
            this.containerSig_ = i;
            return this;
        }

        public Builder setInnerSig(int i) {
            this.bitField0_ |= 2;
            this.innerSig_ = i;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CompositeSignature m476getDefaultInstanceForType() {
            return CompositeSignature.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m478mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            CompositeSignature compositeSignature = null;
            try {
                try {
                    CompositeSignature compositeSignature2 = (CompositeSignature) CompositeSignature.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (compositeSignature2 != null) {
                        mergeFrom(compositeSignature2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    CompositeSignature compositeSignature3 = (CompositeSignature) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        compositeSignature = compositeSignature3;
                        if (compositeSignature != null) {
                            mergeFrom(compositeSignature);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (compositeSignature != null) {
                    mergeFrom(compositeSignature);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public CompositeSignature m463getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m464newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m465toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static CompositeSignature parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeSignature) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static CompositeSignature parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CompositeSignature) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CompositeSignature parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CompositeSignature) PARSER.parseFrom(bArr);
    }

    public static CompositeSignature parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CompositeSignature) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CompositeSignature parseFrom(InputStream inputStream) throws IOException {
        return (CompositeSignature) PARSER.parseFrom(inputStream);
    }

    public static CompositeSignature parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeSignature) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static CompositeSignature parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CompositeSignature) PARSER.parseFrom(codedInputStream);
    }

    public static CompositeSignature parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeSignature) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private CompositeSignature(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private CompositeSignature(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
