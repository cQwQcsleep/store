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
public final class FileEntry extends GeneratedMessageLite implements FileEntryOrBuilder {
    public static final int FIRST_RELEVANT_LINE_INDEX_FIELD_NUMBER = 3;
    public static final int LINE_START_OFFSET_DELTA_FIELD_NUMBER = 5;
    public static final int LINE_START_OFFSET_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 4;
    public static final int NAME_OLD_FIELD_NUMBER = 1;
    public static Parser<FileEntry> PARSER = new AbstractParser<FileEntry>() { // from class: org.jetbrains.kotlin.backend.common.serialization.proto.FileEntry.1
        public FileEntry parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new FileEntry(codedInputStream, extensionRegistryLite);
        }
    };
    private static final FileEntry defaultInstance;
    private static final long serialVersionUID = 0;
    private int bitField0_;
    private int firstRelevantLineIndex_;
    private int lineStartOffsetDeltaMemoizedSerializedSize;
    private List<Integer> lineStartOffsetDelta_;
    private int lineStartOffsetMemoizedSerializedSize;
    private List<Integer> lineStartOffset_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private Object nameOld_;
    private int name_;
    private final ByteString unknownFields;

    static {
        FileEntry fileEntry = new FileEntry(true);
        defaultInstance = fileEntry;
        fileEntry.initFields();
    }

    private FileEntry(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.lineStartOffsetMemoizedSerializedSize = -1;
        this.lineStartOffsetDeltaMemoizedSerializedSize = -1;
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
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                ByteString bytes = codedInputStream.readBytes();
                                this.bitField0_ |= 1;
                                this.nameOld_ = bytes;
                            } else if (tag == 16) {
                                if ((i & 4) != 4) {
                                    this.lineStartOffset_ = new ArrayList();
                                    i |= 4;
                                }
                                this.lineStartOffset_.add(Integer.valueOf(codedInputStream.readInt32()));
                            } else if (tag == 18) {
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 4) != 4 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.lineStartOffset_ = new ArrayList();
                                    i |= 4;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.lineStartOffset_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(iPushLimit);
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.firstRelevantLineIndex_ = codedInputStream.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ |= 2;
                                this.name_ = codedInputStream.readInt32();
                            } else if (tag == 40) {
                                if ((i & 8) != 8) {
                                    this.lineStartOffsetDelta_ = new ArrayList();
                                    i |= 8;
                                }
                                this.lineStartOffsetDelta_.add(Integer.valueOf(codedInputStream.readInt32()));
                            } else if (tag == 42) {
                                int iPushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 8) != 8 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.lineStartOffsetDelta_ = new ArrayList();
                                    i |= 8;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.lineStartOffsetDelta_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(iPushLimit2);
                            } else if (!parseUnknownField(codedInputStream, codedOutputStreamNewInstance, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    }
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                }
            } catch (Throwable th) {
                if ((i & 4) == 4) {
                    this.lineStartOffset_ = Collections.unmodifiableList(this.lineStartOffset_);
                }
                if ((i & 8) == 8) {
                    this.lineStartOffsetDelta_ = Collections.unmodifiableList(this.lineStartOffsetDelta_);
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
        if ((i & 4) == 4) {
            this.lineStartOffset_ = Collections.unmodifiableList(this.lineStartOffset_);
        }
        if ((i & 8) == 8) {
            this.lineStartOffsetDelta_ = Collections.unmodifiableList(this.lineStartOffsetDelta_);
        }
        try {
            codedOutputStreamNewInstance.flush();
        } catch (IOException unused2) {
        } finally {
            this.unknownFields = outputNewOutput.toByteString();
        }
        makeExtensionsImmutable();
    }

    public static FileEntry getDefaultInstance() {
        return defaultInstance;
    }

    private void initFields() {
        this.nameOld_ = "";
        this.name_ = 0;
        List<Integer> list = Collections.EMPTY_LIST;
        this.lineStartOffset_ = list;
        this.lineStartOffsetDelta_ = list;
        this.firstRelevantLineIndex_ = 0;
    }

    public static Builder newBuilder(FileEntry fileEntry) {
        return newBuilder().mergeFrom(fileEntry);
    }

    public static FileEntry parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FileEntry) PARSER.parseDelimitedFrom(inputStream);
    }

    public static FileEntry parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FileEntry) PARSER.parseFrom(byteString);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public int getFirstRelevantLineIndex() {
        return this.firstRelevantLineIndex_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public int getLineStartOffset(int i) {
        return this.lineStartOffset_.get(i).intValue();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public int getLineStartOffsetCount() {
        return this.lineStartOffset_.size();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public int getLineStartOffsetDelta(int i) {
        return this.lineStartOffsetDelta_.get(i).intValue();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public int getLineStartOffsetDeltaCount() {
        return this.lineStartOffsetDelta_.size();
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public List<Integer> getLineStartOffsetDeltaList() {
        return this.lineStartOffsetDelta_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public List<Integer> getLineStartOffsetList() {
        return this.lineStartOffset_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public int getName() {
        return this.name_;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public String getNameOld() {
        Object obj = this.nameOld_;
        if (obj instanceof String) {
            return (String) obj;
        }
        ByteString byteString = (ByteString) obj;
        String stringUtf8 = byteString.toStringUtf8();
        if (byteString.isValidUtf8()) {
            this.nameOld_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public ByteString getNameOldBytes() {
        Object obj = this.nameOld_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.nameOld_ = byteStringCopyFromUtf8;
        return byteStringCopyFromUtf8;
    }

    public Parser<FileEntry> getParserForType() {
        return PARSER;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getNameOldBytes()) : 0;
        int iComputeInt32SizeNoTag = 0;
        for (int i2 = 0; i2 < this.lineStartOffset_.size(); i2++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.lineStartOffset_.get(i2).intValue());
        }
        int iComputeInt32Size = iComputeBytesSize + iComputeInt32SizeNoTag;
        if (!getLineStartOffsetList().isEmpty()) {
            iComputeInt32Size = iComputeInt32Size + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag);
        }
        this.lineStartOffsetMemoizedSerializedSize = iComputeInt32SizeNoTag;
        if ((this.bitField0_ & 4) == 4) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(3, this.firstRelevantLineIndex_);
        }
        if ((this.bitField0_ & 2) == 2) {
            iComputeInt32Size += CodedOutputStream.computeInt32Size(4, this.name_);
        }
        int iComputeInt32SizeNoTag2 = 0;
        for (int i3 = 0; i3 < this.lineStartOffsetDelta_.size(); i3++) {
            iComputeInt32SizeNoTag2 += CodedOutputStream.computeInt32SizeNoTag(this.lineStartOffsetDelta_.get(i3).intValue());
        }
        int iComputeInt32SizeNoTag3 = iComputeInt32Size + iComputeInt32SizeNoTag2;
        if (!getLineStartOffsetDeltaList().isEmpty()) {
            iComputeInt32SizeNoTag3 = iComputeInt32SizeNoTag3 + 1 + CodedOutputStream.computeInt32SizeNoTag(iComputeInt32SizeNoTag2);
        }
        this.lineStartOffsetDeltaMemoizedSerializedSize = iComputeInt32SizeNoTag2;
        int size = iComputeInt32SizeNoTag3 + this.unknownFields.size();
        this.memoizedSerializedSize = size;
        return size;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public boolean hasFirstRelevantLineIndex() {
        return (this.bitField0_ & 4) == 4;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public boolean hasName() {
        return (this.bitField0_ & 2) == 2;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
    public boolean hasNameOld() {
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
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeBytes(1, getNameOldBytes());
        }
        if (getLineStartOffsetList().size() > 0) {
            codedOutputStream.writeRawVarint32(18);
            codedOutputStream.writeRawVarint32(this.lineStartOffsetMemoizedSerializedSize);
        }
        for (int i = 0; i < this.lineStartOffset_.size(); i++) {
            codedOutputStream.writeInt32NoTag(this.lineStartOffset_.get(i).intValue());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(3, this.firstRelevantLineIndex_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(4, this.name_);
        }
        if (getLineStartOffsetDeltaList().size() > 0) {
            codedOutputStream.writeRawVarint32(42);
            codedOutputStream.writeRawVarint32(this.lineStartOffsetDeltaMemoizedSerializedSize);
        }
        for (int i2 = 0; i2 < this.lineStartOffsetDelta_.size(); i2++) {
            codedOutputStream.writeInt32NoTag(this.lineStartOffsetDelta_.get(i2).intValue());
        }
        codedOutputStream.writeRawBytes(this.unknownFields);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FileEntry, Builder> implements FileEntryOrBuilder {
        private int bitField0_;
        private int firstRelevantLineIndex_;
        private List<Integer> lineStartOffsetDelta_;
        private List<Integer> lineStartOffset_;
        private Object nameOld_ = "";
        private int name_;

        private Builder() {
            List<Integer> list = Collections.EMPTY_LIST;
            this.lineStartOffset_ = list;
            this.lineStartOffsetDelta_ = list;
            maybeForceBuilderInitialization();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Builder create() {
            return new Builder();
        }

        private void ensureLineStartOffsetDeltaIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.lineStartOffsetDelta_ = new ArrayList(this.lineStartOffsetDelta_);
                this.bitField0_ |= 8;
            }
        }

        private void ensureLineStartOffsetIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.lineStartOffset_ = new ArrayList(this.lineStartOffset_);
                this.bitField0_ |= 4;
            }
        }

        private void maybeForceBuilderInitialization() {
        }

        public Builder addAllLineStartOffset(Iterable<? extends Integer> iterable) {
            ensureLineStartOffsetIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.lineStartOffset_);
            return this;
        }

        public Builder addAllLineStartOffsetDelta(Iterable<? extends Integer> iterable) {
            ensureLineStartOffsetDeltaIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, this.lineStartOffsetDelta_);
            return this;
        }

        public Builder addLineStartOffset(int i) {
            ensureLineStartOffsetIsMutable();
            this.lineStartOffset_.add(Integer.valueOf(i));
            return this;
        }

        public Builder addLineStartOffsetDelta(int i) {
            ensureLineStartOffsetDeltaIsMutable();
            this.lineStartOffsetDelta_.add(Integer.valueOf(i));
            return this;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.protobuf.UninitializedMessageException */
        /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
        public FileEntry m531build() throws UninitializedMessageException {
            FileEntry fileEntryM532buildPartial = m532buildPartial();
            if (fileEntryM532buildPartial.isInitialized()) {
                return fileEntryM532buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(fileEntryM532buildPartial);
        }

        /* JADX INFO: renamed from: buildPartial, reason: merged with bridge method [inline-methods] */
        public FileEntry m532buildPartial() {
            FileEntry fileEntry = new FileEntry(this);
            int i = this.bitField0_;
            int i2 = (i & 1) != 1 ? 0 : 1;
            fileEntry.nameOld_ = this.nameOld_;
            if ((i & 2) == 2) {
                i2 |= 2;
            }
            fileEntry.name_ = this.name_;
            if ((this.bitField0_ & 4) == 4) {
                this.lineStartOffset_ = Collections.unmodifiableList(this.lineStartOffset_);
                this.bitField0_ &= -5;
            }
            fileEntry.lineStartOffset_ = this.lineStartOffset_;
            if ((this.bitField0_ & 8) == 8) {
                this.lineStartOffsetDelta_ = Collections.unmodifiableList(this.lineStartOffsetDelta_);
                this.bitField0_ &= -9;
            }
            fileEntry.lineStartOffsetDelta_ = this.lineStartOffsetDelta_;
            if ((i & 16) == 16) {
                i2 |= 4;
            }
            fileEntry.firstRelevantLineIndex_ = this.firstRelevantLineIndex_;
            fileEntry.bitField0_ = i2;
            return fileEntry;
        }

        /* JADX INFO: renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m534clear() {
            super.clear();
            this.nameOld_ = "";
            int i = this.bitField0_;
            this.name_ = 0;
            this.bitField0_ = i & (-4);
            List<Integer> list = Collections.EMPTY_LIST;
            this.lineStartOffset_ = list;
            this.lineStartOffsetDelta_ = list;
            this.firstRelevantLineIndex_ = 0;
            this.bitField0_ = i & (-32);
            return this;
        }

        public Builder clearFirstRelevantLineIndex() {
            this.bitField0_ &= -17;
            this.firstRelevantLineIndex_ = 0;
            return this;
        }

        public Builder clearLineStartOffset() {
            this.lineStartOffset_ = Collections.EMPTY_LIST;
            this.bitField0_ &= -5;
            return this;
        }

        public Builder clearLineStartOffsetDelta() {
            this.lineStartOffsetDelta_ = Collections.EMPTY_LIST;
            this.bitField0_ &= -9;
            return this;
        }

        public Builder clearName() {
            this.bitField0_ &= -3;
            this.name_ = 0;
            return this;
        }

        public Builder clearNameOld() {
            this.bitField0_ &= -2;
            this.nameOld_ = FileEntry.getDefaultInstance().getNameOld();
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m538clone() {
            return create().mergeFrom(m532buildPartial());
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public int getFirstRelevantLineIndex() {
            return this.firstRelevantLineIndex_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public int getLineStartOffset(int i) {
            return this.lineStartOffset_.get(i).intValue();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public int getLineStartOffsetCount() {
            return this.lineStartOffset_.size();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public int getLineStartOffsetDelta(int i) {
            return this.lineStartOffsetDelta_.get(i).intValue();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public int getLineStartOffsetDeltaCount() {
            return this.lineStartOffsetDelta_.size();
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public List<Integer> getLineStartOffsetDeltaList() {
            return Collections.unmodifiableList(this.lineStartOffsetDelta_);
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public List<Integer> getLineStartOffsetList() {
            return Collections.unmodifiableList(this.lineStartOffset_);
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public int getName() {
            return this.name_;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public String getNameOld() {
            Object obj = this.nameOld_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.nameOld_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public ByteString getNameOldBytes() {
            Object obj = this.nameOld_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.nameOld_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public boolean hasFirstRelevantLineIndex() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.proto.FileEntryOrBuilder
        public boolean hasNameOld() {
            return (this.bitField0_ & 1) == 1;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(FileEntry fileEntry) {
            if (fileEntry == FileEntry.getDefaultInstance()) {
                return this;
            }
            if (fileEntry.hasNameOld()) {
                this.bitField0_ |= 1;
                this.nameOld_ = fileEntry.nameOld_;
            }
            if (fileEntry.hasName()) {
                setName(fileEntry.getName());
            }
            if (!fileEntry.lineStartOffset_.isEmpty()) {
                if (this.lineStartOffset_.isEmpty()) {
                    this.lineStartOffset_ = fileEntry.lineStartOffset_;
                    this.bitField0_ &= -5;
                } else {
                    ensureLineStartOffsetIsMutable();
                    this.lineStartOffset_.addAll(fileEntry.lineStartOffset_);
                }
            }
            if (!fileEntry.lineStartOffsetDelta_.isEmpty()) {
                if (this.lineStartOffsetDelta_.isEmpty()) {
                    this.lineStartOffsetDelta_ = fileEntry.lineStartOffsetDelta_;
                    this.bitField0_ &= -9;
                } else {
                    ensureLineStartOffsetDeltaIsMutable();
                    this.lineStartOffsetDelta_.addAll(fileEntry.lineStartOffsetDelta_);
                }
            }
            if (fileEntry.hasFirstRelevantLineIndex()) {
                setFirstRelevantLineIndex(fileEntry.getFirstRelevantLineIndex());
            }
            setUnknownFields(getUnknownFields().concat(fileEntry.unknownFields));
            return this;
        }

        public Builder setFirstRelevantLineIndex(int i) {
            this.bitField0_ |= 16;
            this.firstRelevantLineIndex_ = i;
            return this;
        }

        public Builder setLineStartOffset(int i, int i2) {
            ensureLineStartOffsetIsMutable();
            this.lineStartOffset_.set(i, Integer.valueOf(i2));
            return this;
        }

        public Builder setLineStartOffsetDelta(int i, int i2) {
            ensureLineStartOffsetDeltaIsMutable();
            this.lineStartOffsetDelta_.set(i, Integer.valueOf(i2));
            return this;
        }

        public Builder setName(int i) {
            this.bitField0_ |= 2;
            this.name_ = i;
            return this;
        }

        public Builder setNameOld(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.nameOld_ = str;
            return this;
        }

        public Builder setNameOldBytes(ByteString byteString) {
            byteString.getClass();
            this.bitField0_ |= 1;
            this.nameOld_ = byteString;
            return this;
        }

        /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public FileEntry m540getDefaultInstanceForType() {
            return FileEntry.getDefaultInstance();
        }

        /* JADX WARN: Code duplicated, block: B:15:0x001d  */
        /* JADX INFO: renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m542mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
            FileEntry fileEntry = null;
            try {
                try {
                    FileEntry fileEntry2 = (FileEntry) FileEntry.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (fileEntry2 != null) {
                        mergeFrom(fileEntry2);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    FileEntry fileEntry3 = (FileEntry) e.getUnfinishedMessage();
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        fileEntry = fileEntry3;
                        if (fileEntry != null) {
                            mergeFrom(fileEntry);
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileEntry != null) {
                    mergeFrom(fileEntry);
                }
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] */
    public FileEntry m527getDefaultInstanceForType() {
        return defaultInstance;
    }

    /* JADX INFO: renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m528newBuilderForType() {
        return newBuilder();
    }

    /* JADX INFO: renamed from: toBuilder, reason: merged with bridge method [inline-methods] */
    public Builder m529toBuilder() {
        return newBuilder(this);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public static FileEntry parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileEntry) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }

    public static FileEntry parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileEntry) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static FileEntry parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FileEntry) PARSER.parseFrom(bArr);
    }

    public static FileEntry parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FileEntry) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static FileEntry parseFrom(InputStream inputStream) throws IOException {
        return (FileEntry) PARSER.parseFrom(inputStream);
    }

    public static FileEntry parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileEntry) PARSER.parseFrom(inputStream, extensionRegistryLite);
    }

    public static FileEntry parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FileEntry) PARSER.parseFrom(codedInputStream);
    }

    public static FileEntry parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FileEntry) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }

    private FileEntry(GeneratedMessageLite.Builder builder) {
        super(builder);
        this.lineStartOffsetMemoizedSerializedSize = -1;
        this.lineStartOffsetDeltaMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }

    private FileEntry(boolean z) {
        this.lineStartOffsetMemoizedSerializedSize = -1;
        this.lineStartOffsetDeltaMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = ByteString.EMPTY;
    }
}
