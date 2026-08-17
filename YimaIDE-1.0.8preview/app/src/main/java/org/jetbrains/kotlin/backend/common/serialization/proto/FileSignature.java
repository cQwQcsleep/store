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
public final class FileSignature extends GeneratedMessageLite implements FileSignatureOrBuilder {
    public static Parser<FileSignature> PARSER = new AbstractParser<FileSignature>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.FileSignature.1
        public FileSignature parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FileSignature(codedInputStream, extensionRegistryLite);
        }
    };
    private static final FileSignature defaultInstance;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        FileSignature fileSignature = new FileSignature(true);
        defaultInstance = fileSignature;
        fileSignature.initFields();
    }

    private FileSignature(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    if (tag == 0 || !parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
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
        }
        try {
            codedOutputStreamNewInstance.flush();
        } catch (IOException unused2) {
        } finally {
            this.unknownFields = outputNewOutput.toByteString();
        }
        makeExtensionsImmutable();
    }

    public static FileSignature getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
    }

    public static Builder newBuilder(FileSignature fileSignature) {
        return newBuilder().mergeFrom(fileSignature);
    }

    public static FileSignature parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FileSignature) PARSER.parseDelimitedFrom(inputStream);
    }

    public static FileSignature parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FileSignature) PARSER.parseFrom(byteString);
    }

    public Parser<FileSignature> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int size = this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
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
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FileSignature, Builder> implements FileSignatureOrBuilder {
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
        public FileSignature m563build() throws UninitializedMessageException {
            FileSignature fileSignatureM564buildPartial = m564buildPartial();
            if (fileSignatureM564buildPartial.isInitialized()) {
                return fileSignatureM564buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(fileSignatureM564buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public FileSignature m564buildPartial() {
            return new FileSignature(this);
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m570clone() {
            return create().mergeFrom(m564buildPartial());
        }

        public final boolean isInitialized() {
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m574mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            FileSignature fileSignature = null;
            try {
                try {
                    FileSignature fileSignature2 = (FileSignature) FileSignature.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fileSignature2 != null) {
                        mergeFrom(fileSignature2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    FileSignature fileSignature3 = (FileSignature) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        fileSignature = fileSignature3;
                        if (fileSignature != null) {
                            mergeFrom(fileSignature);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileSignature != null) {
                    mergeFrom(fileSignature);
                }
                throw th;
            }
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m566clear() {
            super.clear();
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FileSignature m572getDefaultInstanceForType() {
            return FileSignature.getDefaultInstance();
        }

        public Builder mergeFrom(FileSignature fileSignature) {
            if (fileSignature == FileSignature.getDefaultInstance()) {
                return this;
            }
            setUnknownFields(getUnknownFields().concat(fileSignature.unknownFields));
            return this;
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public FileSignature m559getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m560newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m561toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static FileSignature parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileSignature) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static FileSignature parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileSignature) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FileSignature parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FileSignature) PARSER.parseFrom(bArr);
    }

    public static FileSignature parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileSignature) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FileSignature parseFrom(InputStream inputStream) throws IOException {
        return (FileSignature) PARSER.parseFrom(inputStream);
    }

    public static FileSignature parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileSignature) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static FileSignature parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FileSignature) PARSER.parseFrom(codedInputStream);
    }

    public static FileSignature parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileSignature) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private FileSignature(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private FileSignature(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
