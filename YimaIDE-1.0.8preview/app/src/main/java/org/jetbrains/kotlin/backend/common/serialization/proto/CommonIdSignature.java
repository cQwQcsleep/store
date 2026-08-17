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
public final class CommonIdSignature extends GeneratedMessageLite implements CommonIdSignatureOrBuilder {
    public static final int DEBUG_INFO_FIELD_NUMBER = 5;
    public static final int DECLARATION_FQ_NAME_FIELD_NUMBER = 2;
    public static final int FLAGS_FIELD_NUMBER = 4;
    public static final int MEMBER_UNIQ_ID_FIELD_NUMBER = 6;
    public static final int MEMBER_UNIQ_ID_PRE_2_4_0_FIELD_NUMBER = 3;
    public static final int PACKAGE_FQ_NAME_FIELD_NUMBER = 1;
    public static Parser<CommonIdSignature> PARSER = new AbstractParser<CommonIdSignature>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignature.1
        public CommonIdSignature parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new CommonIdSignature(codedInputStream, extensionRegistryLite);
        }
    };
    private static final CommonIdSignature defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int debugInfo_;
    private int declarationFqNameMemoizedSerializedSize;
    private List<Integer> declarationFqName_;
    private long flags_;
    private long memberUniqIdPre240_;
    private long memberUniqId_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private int packageFqNameMemoizedSerializedSize;
    private List<Integer> packageFqName_;
    private final ByteString unknownFields;

    static {
        CommonIdSignature commonIdSignature = new CommonIdSignature(true);
        defaultInstance = commonIdSignature;
        commonIdSignature.initFields();
    }

    private CommonIdSignature(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.packageFqNameMemoizedSerializedSize = -1;
        this.declarationFqNameMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        initFields();
        ByteString.Output outputNewOutput = ByteString.newOutput();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputNewOutput, 1);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 8) {
                            if ((i & 1) != 1) {
                                this.packageFqName_ = new ArrayList();
                                i |= 1;
                            }
                            this.packageFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                        } else if (tag == 10) {
                            int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 1) != 1 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.packageFqName_ = new ArrayList();
                                i |= 1;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.packageFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit);
                        } else if (tag == 16) {
                            if ((i & 2) != 2) {
                                this.declarationFqName_ = new ArrayList();
                                i |= 2;
                            }
                            this.declarationFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                        } else if (tag == 18) {
                            int iPushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                            if ((i & 2) != 2 && codedInputStream.getBytesUntilLimit() > 0) {
                                this.declarationFqName_ = new ArrayList();
                                i |= 2;
                            }
                            while (codedInputStream.getBytesUntilLimit() > 0) {
                                this.declarationFqName_.add(Integer.valueOf(codedInputStream.readInt32()));
                            }
                            codedInputStream.popLimit(iPushLimit2);
                        } else if (tag == 24) {
                            this.bitField0_ |= 1;
                            this.memberUniqIdPre240_ = codedInputStream.readInt64();
                        } else if (tag == 32) {
                            this.bitField0_ |= 4;
                            this.flags_ = codedInputStream.readInt64();
                        } else if (tag == 40) {
                            this.bitField0_ |= 8;
                            this.debugInfo_ = codedInputStream.readInt32();
                        } else if (tag == 49) {
                            this.bitField0_ |= 2;
                            this.memberUniqId_ = codedInputStream.readFixed64();
                        } else if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (Throwable th) {
                    if ((i & 1) == 1) {
                        this.packageFqName_ = Collections.unmodifiableList(this.packageFqName_);
                    }
                    if ((i & 2) == 2) {
                        this.declarationFqName_ = Collections.unmodifiableList(this.declarationFqName_);
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
            } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (IOException e2) {
                throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
            }
        }
        if ((i & 1) == 1) {
            this.packageFqName_ = Collections.unmodifiableList(this.packageFqName_);
        }
        if ((i & 2) == 2) {
            this.declarationFqName_ = Collections.unmodifiableList(this.declarationFqName_);
        }
        try {
            codedOutputStreamNewInstance.flush();
        } catch (IOException unused2) {
        } finally {
            this.unknownFields = outputNewOutput.toByteString();
        }
        makeExtensionsImmutable();
    }

    public static CommonIdSignature getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        List<Integer> list = Collections.EMPTY_LIST;
        this.packageFqName_ = list;
        this.declarationFqName_ = list;
        this.memberUniqIdPre240_ = 0L;
        this.memberUniqId_ = 0L;
        this.flags_ = 0L;
        this.debugInfo_ = 0;
    }

    public static Builder newBuilder(CommonIdSignature commonIdSignature) {
        return newBuilder().mergeFrom(commonIdSignature);
    }

    public static CommonIdSignature parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CommonIdSignature) PARSER.parseDelimitedFrom(inputStream);
    }

    public static CommonIdSignature parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CommonIdSignature) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public int getDebugInfo() {
        return this.debugInfo_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public int getDeclarationFqName(int i) {
        return this.declarationFqName_.get(i).intValue();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public int getDeclarationFqNameCount() {
        return this.declarationFqName_.size();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public List<Integer> getDeclarationFqNameList() {
        return this.declarationFqName_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public long getFlags() {
        return this.flags_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public long getMemberUniqId() {
        return this.memberUniqId_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public long getMemberUniqIdPre240() {
        return this.memberUniqIdPre240_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public int getPackageFqName(int i) {
        return this.packageFqName_.get(i).intValue();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public int getPackageFqNameCount() {
        return this.packageFqName_.size();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public List<Integer> getPackageFqNameList() {
        return this.packageFqName_;
    }

    public Parser<CommonIdSignature> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeInt32SizeNoTag = 0;
        for (int i2 = 0; i2 < this.packageFqName_.size(); i2++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.packageFqName_.get(i2).intValue());
        }
        int iComputeInt32SizeNoTag2 = !getPackageFqNameList().isEmpty() ? iComputeInt32SizeNoTag + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag) : iComputeInt32SizeNoTag;
        this.packageFqNameMemoizedSerializedSize = iComputeInt32SizeNoTag;
        int iComputeInt32SizeNoTag3 = 0;
        for (int i3 = 0; i3 < this.declarationFqName_.size(); i3++) {
            iComputeInt32SizeNoTag3 += CodedOutputStream.computeInt32SizeNoTag(this.declarationFqName_.get(i3).intValue());
        }
        int iComputeFixed64Size = iComputeInt32SizeNoTag2 + iComputeInt32SizeNoTag3;
        if (!getDeclarationFqNameList().isEmpty()) {
            iComputeFixed64Size = iComputeFixed64Size + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag3);
        }
        this.declarationFqNameMemoizedSerializedSize = iComputeInt32SizeNoTag3;
        if ((this.bitField0_ & 1) == 1) {
            iComputeFixed64Size += CodedOutputStream.computeInt64Size(3, this.memberUniqIdPre240_);
        }
        if ((this.bitField0_ & 4) == 4) {
            iComputeFixed64Size += CodedOutputStream.computeInt64Size(4, this.flags_);
        }
        if ((this.bitField0_ & 8) == 8) {
            iComputeFixed64Size += CodedOutputStream.computeInt32Size(5, this.debugInfo_);
        }
        if ((this.bitField0_ & 2) == 2) {
            iComputeFixed64Size += CodedOutputStream.computeFixed64Size(6, this.memberUniqId_);
        }
        int size = iComputeFixed64Size + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public boolean hasDebugInfo() {
        return (this.bitField0_ & 8) == 8;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public boolean hasFlags() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public boolean hasMemberUniqId() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
    public boolean hasMemberUniqIdPre240() {
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
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        if (getPackageFqNameList().size() > 0) {
            codedOutputStream.writeRawVarint32(10);
            codedOutputStream.writeRawVarint32(this.packageFqNameMemoizedSerializedSize);
        }
        for (int i = 0; i < this.packageFqName_.size(); i++) {
            codedOutputStream.writeInt32NoTag(this.packageFqName_.get(i).intValue());
        }
        if (getDeclarationFqNameList().size() > 0) {
            codedOutputStream.writeRawVarint32(18);
            codedOutputStream.writeRawVarint32(this.declarationFqNameMemoizedSerializedSize);
        }
        for (int i2 = 0; i2 < this.declarationFqName_.size(); i2++) {
            codedOutputStream.writeInt32NoTag(this.declarationFqName_.get(i2).intValue());
        }
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt64(3, this.memberUniqIdPre240_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt64(4, this.flags_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeInt32(5, this.debugInfo_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeFixed64(6, this.memberUniqId_);
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CommonIdSignature, Builder> implements CommonIdSignatureOrBuilder {
        private int bitField0_;
        private int debugInfo_;
        private List<Integer> declarationFqName_;
        private long flags_;
        private long memberUniqIdPre240_;
        private long memberUniqId_;
        private List<Integer> packageFqName_;

        private Builder() {
            List<Integer> list = Collections.EMPTY_LIST;
            this.packageFqName_ = list;
            this.declarationFqName_ = list;
            maybeForceBuilderInitialization();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        private void ensureDeclarationFqNameIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.declarationFqName_ = new ArrayList(this.declarationFqName_);
                this.bitField0_ |= 2;
            }
        }

        private void ensurePackageFqNameIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.packageFqName_ = new ArrayList(this.packageFqName_);
                this.bitField0_ |= 1;
            }
        }

        private void maybeForceBuilderInitialization() {
        }

        public Builder addAllDeclarationFqName(Iterable<? extends Integer> iterable) {
            ensureDeclarationFqNameIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.declarationFqName_);
            return this;
        }

        public Builder addAllPackageFqName(Iterable<? extends Integer> iterable) {
            ensurePackageFqNameIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.packageFqName_);
            return this;
        }

        public Builder addDeclarationFqName(int i) {
            ensureDeclarationFqNameIsMutable();
            this.declarationFqName_.add(Integer.valueOf(i));
            return this;
        }

        public Builder addPackageFqName(int i) {
            ensurePackageFqNameIsMutable();
            this.packageFqName_.add(Integer.valueOf(i));
            return this;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.protobuf.UninitializedMessageException */
        /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
        public CommonIdSignature m451build() throws UninitializedMessageException {
            CommonIdSignature commonIdSignatureM452buildPartial = m452buildPartial();
            if (commonIdSignatureM452buildPartial.isInitialized()) {
                return commonIdSignatureM452buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(commonIdSignatureM452buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public CommonIdSignature m452buildPartial() {
            CommonIdSignature commonIdSignature = new CommonIdSignature(this);
            int i = this.bitField0_;
            if ((i & 1) == 1) {
                this.packageFqName_ = Collections.unmodifiableList(this.packageFqName_);
                this.bitField0_ &= -2;
            }
            commonIdSignature.packageFqName_ = this.packageFqName_;
            if ((this.bitField0_ & 2) == 2) {
                this.declarationFqName_ = Collections.unmodifiableList(this.declarationFqName_);
                this.bitField0_ &= -3;
            }
            commonIdSignature.declarationFqName_ = this.declarationFqName_;
            int i2 = (i & 4) != 4 ? 0 : 1;
            commonIdSignature.memberUniqIdPre240_ = this.memberUniqIdPre240_;
            if ((i & 8) == 8) {
                i2 |= 2;
            }
            commonIdSignature.memberUniqId_ = this.memberUniqId_;
            if ((i & 16) == 16) {
                i2 |= 4;
            }
            commonIdSignature.flags_ = this.flags_;
            if ((i & 32) == 32) {
                i2 |= 8;
            }
            commonIdSignature.debugInfo_ = this.debugInfo_;
            commonIdSignature.bitField0_ = i2;
            return commonIdSignature;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m454clear() {
            super.clear();
            List<Integer> list = Collections.EMPTY_LIST;
            this.packageFqName_ = list;
            int i = this.bitField0_;
            this.declarationFqName_ = list;
            this.memberUniqIdPre240_ = 0L;
            this.memberUniqId_ = 0L;
            this.flags_ = 0L;
            this.debugInfo_ = 0;
            this.bitField0_ = i & (-64);
            return this;
        }

        public Builder clearDebugInfo() {
            this.bitField0_ &= -33;
            this.debugInfo_ = 0;
            return this;
        }

        public Builder clearDeclarationFqName() {
            this.declarationFqName_ = Collections.EMPTY_LIST;
            this.bitField0_ &= -3;
            return this;
        }

        public Builder clearFlags() {
            this.bitField0_ &= -17;
            this.flags_ = 0L;
            return this;
        }

        public Builder clearMemberUniqId() {
            this.bitField0_ &= -9;
            this.memberUniqId_ = 0L;
            return this;
        }

        public Builder clearMemberUniqIdPre240() {
            this.bitField0_ &= -5;
            this.memberUniqIdPre240_ = 0L;
            return this;
        }

        public Builder clearPackageFqName() {
            this.packageFqName_ = Collections.EMPTY_LIST;
            this.bitField0_ &= -2;
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m458clone() {
            return create().mergeFrom(m452buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public int getDebugInfo() {
            return this.debugInfo_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public int getDeclarationFqName(int i) {
            return this.declarationFqName_.get(i).intValue();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public int getDeclarationFqNameCount() {
            return this.declarationFqName_.size();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public List<Integer> getDeclarationFqNameList() {
            return Collections.unmodifiableList(this.declarationFqName_);
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public long getFlags() {
            return this.flags_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public long getMemberUniqId() {
            return this.memberUniqId_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public long getMemberUniqIdPre240() {
            return this.memberUniqIdPre240_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public int getPackageFqName(int i) {
            return this.packageFqName_.get(i).intValue();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public int getPackageFqNameCount() {
            return this.packageFqName_.size();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public List<Integer> getPackageFqNameList() {
            return Collections.unmodifiableList(this.packageFqName_);
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public boolean hasDebugInfo() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public boolean hasFlags() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public boolean hasMemberUniqId() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.CommonIdSignatureOrBuilder
        public boolean hasMemberUniqIdPre240() {
            return (this.bitField0_ & 4) == 4;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(CommonIdSignature commonIdSignature) {
            if (commonIdSignature == CommonIdSignature.getDefaultInstance()) {
                return this;
            }
            if (!commonIdSignature.packageFqName_.isEmpty()) {
                if (this.packageFqName_.isEmpty()) {
                    this.packageFqName_ = commonIdSignature.packageFqName_;
                    this.bitField0_ &= -2;
                } else {
                    ensurePackageFqNameIsMutable();
                    this.packageFqName_.addAll(commonIdSignature.packageFqName_);
                }
            }
            if (!commonIdSignature.declarationFqName_.isEmpty()) {
                if (this.declarationFqName_.isEmpty()) {
                    this.declarationFqName_ = commonIdSignature.declarationFqName_;
                    this.bitField0_ &= -3;
                } else {
                    ensureDeclarationFqNameIsMutable();
                    this.declarationFqName_.addAll(commonIdSignature.declarationFqName_);
                }
            }
            if (commonIdSignature.hasMemberUniqIdPre240()) {
                setMemberUniqIdPre240(commonIdSignature.getMemberUniqIdPre240());
            }
            if (commonIdSignature.hasMemberUniqId()) {
                setMemberUniqId(commonIdSignature.getMemberUniqId());
            }
            if (commonIdSignature.hasFlags()) {
                setFlags(commonIdSignature.getFlags());
            }
            if (commonIdSignature.hasDebugInfo()) {
                setDebugInfo(commonIdSignature.getDebugInfo());
            }
            setUnknownFields(getUnknownFields().concat(commonIdSignature.unknownFields));
            return this;
        }

        public Builder setDebugInfo(int i) {
            this.bitField0_ |= 32;
            this.debugInfo_ = i;
            return this;
        }

        public Builder setDeclarationFqName(int i, int i2) {
            ensureDeclarationFqNameIsMutable();
            this.declarationFqName_.set(i, Integer.valueOf(i2));
            return this;
        }

        public Builder setFlags(long j) {
            this.bitField0_ |= 16;
            this.flags_ = j;
            return this;
        }

        public Builder setMemberUniqId(long j) {
            this.bitField0_ |= 8;
            this.memberUniqId_ = j;
            return this;
        }

        public Builder setMemberUniqIdPre240(long j) {
            this.bitField0_ |= 4;
            this.memberUniqIdPre240_ = j;
            return this;
        }

        public Builder setPackageFqName(int i, int i2) {
            ensurePackageFqNameIsMutable();
            this.packageFqName_.set(i, Integer.valueOf(i2));
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public CommonIdSignature m460getDefaultInstanceForType() {
            return CommonIdSignature.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m462mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            CommonIdSignature commonIdSignature = null;
            try {
                try {
                    CommonIdSignature commonIdSignature2 = (CommonIdSignature) CommonIdSignature.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (commonIdSignature2 != null) {
                        mergeFrom(commonIdSignature2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    CommonIdSignature commonIdSignature3 = (CommonIdSignature) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        commonIdSignature = commonIdSignature3;
                        if (commonIdSignature != null) {
                            mergeFrom(commonIdSignature);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (commonIdSignature != null) {
                    mergeFrom(commonIdSignature);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public CommonIdSignature m447getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m448newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m449toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static CommonIdSignature parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommonIdSignature) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static CommonIdSignature parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommonIdSignature) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static CommonIdSignature parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CommonIdSignature) PARSER.parseFrom(bArr);
    }

    public static CommonIdSignature parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CommonIdSignature) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static CommonIdSignature parseFrom(InputStream inputStream) throws IOException {
        return (CommonIdSignature) PARSER.parseFrom(inputStream);
    }

    public static CommonIdSignature parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommonIdSignature) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static CommonIdSignature parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CommonIdSignature) PARSER.parseFrom(codedInputStream);
    }

    public static CommonIdSignature parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CommonIdSignature) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private CommonIdSignature(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.packageFqNameMemoizedSerializedSize = -1;
        this.declarationFqNameMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private CommonIdSignature(boolean z) {
        this.packageFqNameMemoizedSerializedSize = -1;
        this.declarationFqNameMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
