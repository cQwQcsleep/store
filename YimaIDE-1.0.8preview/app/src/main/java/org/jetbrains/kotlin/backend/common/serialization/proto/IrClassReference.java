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
public final class IrClassReference extends GeneratedMessageLite implements IrClassReferenceOrBuilder {
    public static final int CLASS_SYMBOL_FIELD_NUMBER = 1;
    public static final int CLASS_TYPE_FIELD_NUMBER = 2;
    public static Parser<IrClassReference> PARSER = new AbstractParser<IrClassReference>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReference.1
        public IrClassReference parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrClassReference(codedInputStream, extensionRegistryLite);
        }
    };
    private static final IrClassReference defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private long classSymbol_;
    private int classType_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        IrClassReference irClassReference = new IrClassReference(true);
        defaultInstance = irClassReference;
        irClassReference.initFields();
    }

    private IrClassReference(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.classSymbol_ = codedInputStream.readInt64();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.classType_ = codedInputStream.readInt32();
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

    public static IrClassReference getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.classSymbol_ = 0L;
        this.classType_ = 0;
    }

    public static Builder newBuilder(IrClassReference irClassReference) {
        return newBuilder().mergeFrom(irClassReference);
    }

    public static IrClassReference parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrClassReference) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrClassReference parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrClassReference) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
    public long getClassSymbol() {
        return this.classSymbol_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
    public int getClassType() {
        return this.classType_;
    }

    public Parser<IrClassReference> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt64Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.classSymbol_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt64Size += CodedOutputStream.computeInt32Size(2, this.classType_);
        }
        int size = iComputeInt64Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
    public boolean hasClassSymbol() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
    public boolean hasClassType() {
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
        if (!hasClassSymbol()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasClassType()) {
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
            codedOutputStream.writeInt64(1, this.classSymbol_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.classType_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrClassReference, Builder> implements IrClassReferenceOrBuilder {
        private int bitField0_;
        private long classSymbol_;
        private int classType_;

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
        public IrClassReference m755build() throws UninitializedMessageException {
            IrClassReference irClassReferenceM756buildPartial = m756buildPartial();
            if (irClassReferenceM756buildPartial.isInitialized()) {
                return irClassReferenceM756buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irClassReferenceM756buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrClassReference m756buildPartial() {
            IrClassReference irClassReference = new IrClassReference(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            irClassReference.classSymbol_ = this.classSymbol_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            irClassReference.classType_ = this.classType_;
            irClassReference.bitField0_ = i2;
            return irClassReference;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m758clear() {
            super.clear();
            this.classSymbol_ = 0L;
            int i = this.bitField0_;
            this.classType_ = 0;
            this.bitField0_ = i & (-4);
            return this;
        }

        public Builder clearClassSymbol() {
            this.bitField0_ &= -2;
            this.classSymbol_ = 0L;
            return this;
        }

        public Builder clearClassType() {
            this.bitField0_ &= -3;
            this.classType_ = 0;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m762clone() {
            return create().mergeFrom(m756buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
        public long getClassSymbol() {
            return this.classSymbol_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
        public int getClassType() {
            return this.classType_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
        public boolean hasClassSymbol() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrClassReferenceOrBuilder
        public boolean hasClassType() {
            return (this.bitField0_ & 2) == 2;
        }

        public final boolean isInitialized() {
            return hasClassSymbol() && hasClassType();
        }

        public Builder mergeFrom(IrClassReference irClassReference) {
            if (irClassReference == IrClassReference.getDefaultInstance()) {
                return this;
            }
            if (irClassReference.hasClassSymbol()) {
                setClassSymbol(irClassReference.getClassSymbol());
            }
            if (irClassReference.hasClassType()) {
                setClassType(irClassReference.getClassType());
            }
            setUnknownFields(getUnknownFields().concat(irClassReference.unknownFields));
            return this;
        }

        public Builder setClassSymbol(long j) {
            this.bitField0_ |= 1;
            this.classSymbol_ = j;
            return this;
        }

        public Builder setClassType(int i) {
            this.bitField0_ |= 2;
            this.classType_ = i;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrClassReference m764getDefaultInstanceForType() {
            return IrClassReference.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m766mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrClassReference irClassReference = null;
            try {
                try {
                    IrClassReference irClassReference2 = (IrClassReference) IrClassReference.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irClassReference2 != null) {
                        mergeFrom(irClassReference2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrClassReference irClassReference3 = (IrClassReference) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irClassReference = irClassReference3;
                        if (irClassReference != null) {
                            mergeFrom(irClassReference);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irClassReference != null) {
                    mergeFrom(irClassReference);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrClassReference m751getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m752newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m753toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrClassReference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrClassReference) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrClassReference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrClassReference) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrClassReference parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrClassReference) PARSER.parseFrom(bArr);
    }

    public static IrClassReference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrClassReference) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrClassReference parseFrom(InputStream inputStream) throws IOException {
        return (IrClassReference) PARSER.parseFrom(inputStream);
    }

    public static IrClassReference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrClassReference) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrClassReference parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrClassReference) PARSER.parseFrom(codedInputStream);
    }

    public static IrClassReference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrClassReference) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrClassReference(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrClassReference(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
