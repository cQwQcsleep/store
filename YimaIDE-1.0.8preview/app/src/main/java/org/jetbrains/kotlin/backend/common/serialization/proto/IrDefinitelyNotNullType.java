package org.jetbrains.kotlin.backend.common.serialization.proto;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public final class IrDefinitelyNotNullType extends GeneratedMessageLite implements IrDefinitelyNotNullTypeOrBuilder {
    public static Parser<IrDefinitelyNotNullType> PARSER = new AbstractParser<IrDefinitelyNotNullType>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrDefinitelyNotNullType.1
        public IrDefinitelyNotNullType parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrDefinitelyNotNullType(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TYPES_FIELD_NUMBER = 1;
    private static final IrDefinitelyNotNullType defaultInstance;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int typesMemoizedSerializedSize;
    private List<Integer> types_;
    private final ByteString unknownFields;

    static {
        IrDefinitelyNotNullType irDefinitelyNotNullType = new IrDefinitelyNotNullType(true);
        defaultInstance = irDefinitelyNotNullType;
        irDefinitelyNotNullType.initFields();
    }

    private IrDefinitelyNotNullType(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.typesMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                if (!z2) {
                                    this.types_ = new ArrayList();
                                    z2 = true;
                                }
                                this.types_.add(Integer.valueOf(codedInputStream.readInt32()));
                            } else if (tag == 10) {
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if (!z2 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.types_ = new ArrayList();
                                    z2 = true;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.types_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(iPushLimit);
                            } else if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if (z2) {
                    this.types_ = Collections.unmodifiableList(this.types_);
                }
                try {
                    codedOutputStreamNewInstance.flush();
                } catch (IOException unused) {
                } finally {
                    this.unknownFields = outputNewOutput.toByteString();
                }
                makeExtensionsImmutable();
                throw th;
            }
        }
        if (z2) {
            this.types_ = Collections.unmodifiableList(this.types_);
        }
        try {
            codedOutputStreamNewInstance.flush();
        } catch (IOException unused2) {
        } finally {
            this.unknownFields = outputNewOutput.toByteString();
        }
        makeExtensionsImmutable();
    }

    public static IrDefinitelyNotNullType getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.types_ = Collections.EMPTY_LIST;
    }

    public static Builder newBuilder(IrDefinitelyNotNullType irDefinitelyNotNullType) {
        return newBuilder().mergeFrom(irDefinitelyNotNullType);
    }

    public static IrDefinitelyNotNullType parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrDefinitelyNotNullType) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrDefinitelyNotNullType parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(byteString);
    }

    public Parser<IrDefinitelyNotNullType> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32SizeNoTag = 0;
        for (int i2 = 0; i2 < this.types_.size(); i2++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.types_.get(i2).intValue());
        }
        int iComputeInt32SizeNoTag2 = !getTypesList().isEmpty() ? iComputeInt32SizeNoTag + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag) : iComputeInt32SizeNoTag;
        this.typesMemoizedSerializedSize = iComputeInt32SizeNoTag;
        int size = iComputeInt32SizeNoTag2 + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrDefinitelyNotNullTypeOrBuilder
    public int getTypes(int i) {
        return this.types_.get(i).intValue();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrDefinitelyNotNullTypeOrBuilder
    public int getTypesCount() {
        return this.types_.size();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrDefinitelyNotNullTypeOrBuilder
    public List<Integer> getTypesList() {
        return this.types_;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (getTypesList().size() > 0) {
            codedOutputStream.writeRawVarint32(10);
            codedOutputStream.writeRawVarint32(this.typesMemoizedSerializedSize);
        }
        for (int i = 0; i < this.types_.size(); i++) {
            codedOutputStream.writeInt32NoTag(this.types_.get(i).intValue());
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IrDefinitelyNotNullType, Builder> implements IrDefinitelyNotNullTypeOrBuilder {
        private int bitField0_;
        private List<Integer> types_ = Collections.EMPTY_LIST;

        private Builder() {
            maybeForceBuilderInitialization();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        private void ensureTypesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.types_ = new ArrayList(this.types_);
                this.bitField0_ |= 1;
            }
        }

        private void maybeForceBuilderInitialization() {
        }

        public Builder addAllTypes(Iterable<? extends Integer> iterable) {
            ensureTypesIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.types_);
            return this;
        }

        public Builder addTypes(int i) {
            ensureTypesIsMutable();
            this.types_.add(Integer.valueOf(i));
            return this;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.protobuf.UninitializedMessageException */
        /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
        public IrDefinitelyNotNullType m883build() throws UninitializedMessageException {
            IrDefinitelyNotNullType irDefinitelyNotNullTypeM884buildPartial = m884buildPartial();
            if (irDefinitelyNotNullTypeM884buildPartial.isInitialized()) {
                return irDefinitelyNotNullTypeM884buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irDefinitelyNotNullTypeM884buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrDefinitelyNotNullType m884buildPartial() {
            IrDefinitelyNotNullType irDefinitelyNotNullType = new IrDefinitelyNotNullType(this);
            if ((this.bitField0_ & 1) == 1) {
                this.types_ = Collections.unmodifiableList(this.types_);
                this.bitField0_ &= -2;
            }
            irDefinitelyNotNullType.types_ = this.types_;
            return irDefinitelyNotNullType;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m886clear() {
            super.clear();
            this.types_ = Collections.EMPTY_LIST;
            this.bitField0_ &= -2;
            return this;
        }

        public Builder clearTypes() {
            this.types_ = Collections.EMPTY_LIST;
            this.bitField0_ &= -2;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m890clone() {
            return create().mergeFrom(m884buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrDefinitelyNotNullTypeOrBuilder
        public int getTypes(int i) {
            return this.types_.get(i).intValue();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrDefinitelyNotNullTypeOrBuilder
        public int getTypesCount() {
            return this.types_.size();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.IrDefinitelyNotNullTypeOrBuilder
        public List<Integer> getTypesList() {
            return Collections.unmodifiableList(this.types_);
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(IrDefinitelyNotNullType irDefinitelyNotNullType) {
            if (irDefinitelyNotNullType == IrDefinitelyNotNullType.getDefaultInstance()) {
                return this;
            }
            if (!irDefinitelyNotNullType.types_.isEmpty()) {
                if (this.types_.isEmpty()) {
                    this.types_ = irDefinitelyNotNullType.types_;
                    this.bitField0_ &= -2;
                } else {
                    ensureTypesIsMutable();
                    this.types_.addAll(irDefinitelyNotNullType.types_);
                }
            }
            setUnknownFields(getUnknownFields().concat(irDefinitelyNotNullType.unknownFields));
            return this;
        }

        public Builder setTypes(int i, int i2) {
            ensureTypesIsMutable();
            this.types_.set(i, Integer.valueOf(i2));
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrDefinitelyNotNullType m892getDefaultInstanceForType() {
            return IrDefinitelyNotNullType.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m894mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrDefinitelyNotNullType irDefinitelyNotNullType = null;
            try {
                try {
                    IrDefinitelyNotNullType irDefinitelyNotNullType2 = (IrDefinitelyNotNullType) IrDefinitelyNotNullType.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irDefinitelyNotNullType2 != null) {
                        mergeFrom(irDefinitelyNotNullType2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrDefinitelyNotNullType irDefinitelyNotNullType3 = (IrDefinitelyNotNullType) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irDefinitelyNotNullType = irDefinitelyNotNullType3;
                        if (irDefinitelyNotNullType != null) {
                            mergeFrom(irDefinitelyNotNullType);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irDefinitelyNotNullType != null) {
                    mergeFrom(irDefinitelyNotNullType);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrDefinitelyNotNullType m879getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m880newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m881toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrDefinitelyNotNullType parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrDefinitelyNotNullType) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrDefinitelyNotNullType parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrDefinitelyNotNullType parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(bArr);
    }

    public static IrDefinitelyNotNullType parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrDefinitelyNotNullType parseFrom(InputStream inputStream) throws IOException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(inputStream);
    }

    public static IrDefinitelyNotNullType parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrDefinitelyNotNullType parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(codedInputStream);
    }

    public static IrDefinitelyNotNullType parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrDefinitelyNotNullType) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrDefinitelyNotNullType(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.typesMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrDefinitelyNotNullType(boolean z) {
        this.typesMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
