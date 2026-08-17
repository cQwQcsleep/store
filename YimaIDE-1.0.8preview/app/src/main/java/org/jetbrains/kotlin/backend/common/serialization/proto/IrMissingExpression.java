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
public final class IrMissingExpression extends GeneratedMessageLite implements IrMissingExpressionOrBuilder {
    public static Parser<IrMissingExpression> PARSER = new AbstractParser<IrMissingExpression>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.IrMissingExpression.1
        public IrMissingExpression parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new IrMissingExpression(codedInputStream, extensionRegistryLite);
        }
    };
    private static final IrMissingExpression defaultInstance;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private final ByteString unknownFields;

    static {
        IrMissingExpression irMissingExpression = new IrMissingExpression(true);
        defaultInstance = irMissingExpression;
        irMissingExpression.initFields();
    }

    private IrMissingExpression(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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

    public static IrMissingExpression getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
    }

    public static Builder newBuilder(IrMissingExpression irMissingExpression) {
        return newBuilder().mergeFrom(irMissingExpression);
    }

    public static IrMissingExpression parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (IrMissingExpression) PARSER.parseDelimitedFrom(inputStream);
    }

    public static IrMissingExpression parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (IrMissingExpression) PARSER.parseFrom(byteString);
    }

    public Parser<IrMissingExpression> getParserForType() {
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

    public static final class Builder extends GeneratedMessageLite.Builder<IrMissingExpression, Builder> implements IrMissingExpressionOrBuilder {
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
        public IrMissingExpression m1332build() throws UninitializedMessageException {
            IrMissingExpression irMissingExpressionM1333buildPartial = m1333buildPartial();
            if (irMissingExpressionM1333buildPartial.isInitialized()) {
                return irMissingExpressionM1333buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(irMissingExpressionM1333buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public IrMissingExpression m1333buildPartial() {
            return new IrMissingExpression(this);
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1339clone() {
            return create().mergeFrom(m1333buildPartial());
        }

        public final boolean isInitialized() {
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1343mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            IrMissingExpression irMissingExpression = null;
            try {
                try {
                    IrMissingExpression irMissingExpression2 = (IrMissingExpression) IrMissingExpression.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (irMissingExpression2 != null) {
                        mergeFrom(irMissingExpression2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    IrMissingExpression irMissingExpression3 = (IrMissingExpression) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        irMissingExpression = irMissingExpression3;
                        if (irMissingExpression != null) {
                            mergeFrom(irMissingExpression);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (irMissingExpression != null) {
                    mergeFrom(irMissingExpression);
                }
                throw th;
            }
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m1335clear() {
            super.clear();
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public IrMissingExpression m1341getDefaultInstanceForType() {
            return IrMissingExpression.getDefaultInstance();
        }

        public Builder mergeFrom(IrMissingExpression irMissingExpression) {
            if (irMissingExpression == IrMissingExpression.getDefaultInstance()) {
                return this;
            }
            setUnknownFields(getUnknownFields().concat(irMissingExpression.unknownFields));
            return this;
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public IrMissingExpression m1328getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m1329newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m1330toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static IrMissingExpression parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrMissingExpression) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static IrMissingExpression parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrMissingExpression) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static IrMissingExpression parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (IrMissingExpression) PARSER.parseFrom(bArr);
    }

    public static IrMissingExpression parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (IrMissingExpression) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static IrMissingExpression parseFrom(InputStream inputStream) throws IOException {
        return (IrMissingExpression) PARSER.parseFrom(inputStream);
    }

    public static IrMissingExpression parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrMissingExpression) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static IrMissingExpression parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (IrMissingExpression) PARSER.parseFrom(codedInputStream);
    }

    public static IrMissingExpression parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (IrMissingExpression) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private IrMissingExpression(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private IrMissingExpression(boolean z) {
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
