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
public final class FileLocalIdSignature extends GeneratedMessageLite implements FileLocalIdSignatureOrBuilder {
    public static final int CONTAINER_FIELD_NUMBER = 1;
    public static final int LOCAL_ID_FIELD_NUMBER = 2;
    public static Parser<FileLocalIdSignature> PARSER = new AbstractParser<FileLocalIdSignature>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignature.1
        public FileLocalIdSignature parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FileLocalIdSignature(codedInputStream, extensionRegistryLite);
        }
    };
    private static final FileLocalIdSignature defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int container_;
    private long localId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        FileLocalIdSignature fileLocalIdSignature = new FileLocalIdSignature(true);
        defaultInstance = fileLocalIdSignature;
        fileLocalIdSignature.initFields();
    }

    private FileLocalIdSignature(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.container_ = codedInputStream.readInt32();
                        } else if (tag == 16) {
                            this.bitField0_ |= 2;
                            this.localId_ = codedInputStream.readInt64();
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

    public static FileLocalIdSignature getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.container_ = 0;
        this.localId_ = 0L;
    }

    public static Builder newBuilder(FileLocalIdSignature fileLocalIdSignature) {
        return newBuilder().mergeFrom(fileLocalIdSignature);
    }

    public static FileLocalIdSignature parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FileLocalIdSignature) PARSER.parseDelimitedFrom(inputStream);
    }

    public static FileLocalIdSignature parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FileLocalIdSignature) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
    public int getContainer() {
        return this.container_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
    public long getLocalId() {
        return this.localId_;
    }

    public Parser<FileLocalIdSignature> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.container_) : 0;
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt64Size(2, this.localId_);
        }
        int size = iComputeInt32Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
    public boolean hasContainer() {
        return (this.bitField0_ & 1) == 1;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
    public boolean hasLocalId() {
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
        if (!hasContainer()) {
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }
        if (hasLocalId()) {
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
            codedOutputStream.writeInt32(1, this.container_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt64(2, this.localId_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FileLocalIdSignature, Builder> implements FileLocalIdSignatureOrBuilder {
        private int bitField0_;
        private int container_;
        private long localId_;

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
        public FileLocalIdSignature m547build() throws UninitializedMessageException {
            FileLocalIdSignature fileLocalIdSignatureM548buildPartial = m548buildPartial();
            if (fileLocalIdSignatureM548buildPartial.isInitialized()) {
                return fileLocalIdSignatureM548buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(fileLocalIdSignatureM548buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public FileLocalIdSignature m548buildPartial() {
            FileLocalIdSignature fileLocalIdSignature = new FileLocalIdSignature(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            fileLocalIdSignature.container_ = this.container_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            fileLocalIdSignature.localId_ = this.localId_;
            fileLocalIdSignature.bitField0_ = i2;
            return fileLocalIdSignature;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m550clear() {
            super.clear();
            this.container_ = 0;
            int i = this.bitField0_;
            this.localId_ = 0L;
            this.bitField0_ = i & (-4);
            return this;
        }

        public Builder clearContainer() {
            this.bitField0_ &= -2;
            this.container_ = 0;
            return this;
        }

        public Builder clearLocalId() {
            this.bitField0_ &= -3;
            this.localId_ = 0L;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m554clone() {
            return create().mergeFrom(m548buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
        public int getContainer() {
            return this.container_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
        public long getLocalId() {
            return this.localId_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
        public boolean hasContainer() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileLocalIdSignatureOrBuilder
        public boolean hasLocalId() {
            return (this.bitField0_ & 2) == 2;
        }

        public final boolean isInitialized() {
            return hasContainer() && hasLocalId();
        }

        public Builder mergeFrom(FileLocalIdSignature fileLocalIdSignature) {
            if (fileLocalIdSignature == FileLocalIdSignature.getDefaultInstance()) {
                return this;
            }
            if (fileLocalIdSignature.hasContainer()) {
                setContainer(fileLocalIdSignature.getContainer());
            }
            if (fileLocalIdSignature.hasLocalId()) {
                setLocalId(fileLocalIdSignature.getLocalId());
            }
            setUnknownFields(getUnknownFields().concat(fileLocalIdSignature.unknownFields));
            return this;
        }

        public Builder setContainer(int i) {
            this.bitField0_ |= 1;
            this.container_ = i;
            return this;
        }

        public Builder setLocalId(long j) {
            this.bitField0_ |= 2;
            this.localId_ = j;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FileLocalIdSignature m556getDefaultInstanceForType() {
            return FileLocalIdSignature.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m558mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            FileLocalIdSignature fileLocalIdSignature = null;
            try {
                try {
                    FileLocalIdSignature fileLocalIdSignature2 = (FileLocalIdSignature) FileLocalIdSignature.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fileLocalIdSignature2 != null) {
                        mergeFrom(fileLocalIdSignature2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    FileLocalIdSignature fileLocalIdSignature3 = (FileLocalIdSignature) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        fileLocalIdSignature = fileLocalIdSignature3;
                        if (fileLocalIdSignature != null) {
                            mergeFrom(fileLocalIdSignature);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileLocalIdSignature != null) {
                    mergeFrom(fileLocalIdSignature);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public FileLocalIdSignature m543getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m544newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m545toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static FileLocalIdSignature parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileLocalIdSignature) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static FileLocalIdSignature parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileLocalIdSignature) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FileLocalIdSignature parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FileLocalIdSignature) PARSER.parseFrom(bArr);
    }

    public static FileLocalIdSignature parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileLocalIdSignature) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FileLocalIdSignature parseFrom(InputStream inputStream) throws IOException {
        return (FileLocalIdSignature) PARSER.parseFrom(inputStream);
    }

    public static FileLocalIdSignature parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileLocalIdSignature) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static FileLocalIdSignature parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FileLocalIdSignature) PARSER.parseFrom(codedInputStream);
    }

    public static FileLocalIdSignature parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileLocalIdSignature) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private FileLocalIdSignature(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private FileLocalIdSignature(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
