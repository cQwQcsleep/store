package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.win32.W32APITypeMapper;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Winevt {
    public static final int EVT_ALL_ACCESS = 7;
    public static final int EVT_CLEAR_ACCESS = 4;
    public static final int EVT_READ_ACCESS = 1;
    public static final int EVT_VARIANT_TYPE_ARRAY = 128;
    public static final int EVT_VARIANT_TYPE_MASK = 127;
    public static final int EVT_WRITE_ACCESS = 2;

    /* JADX INFO: renamed from: com.sun.jna.platform.win32.Winevt$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE;

        static {
            int[] iArr = new int[EVT_VARIANT_TYPE.values().length];
            $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE = iArr;
            try {
                iArr[EVT_VARIANT_TYPE.EvtVarTypeAnsiString.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeBoolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeString.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeEvtXml.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeSByte.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeByte.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeInt16.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeUInt16.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeHexInt32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeInt32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeUInt32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeHexInt64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeInt64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeUInt64.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeSingle.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeDouble.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeBinary.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeFileTime.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeEvtHandle.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeSysTime.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeGuid.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeSid.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeSizeT.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[EVT_VARIANT_TYPE.EvtVarTypeNull.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    public interface EVT_CHANNEL_CLOCK_TYPE {
        public static final int EvtChannelClockTypeQPC = 1;
        public static final int EvtChannelClockTypeSystemTime = 0;
    }

    public interface EVT_CHANNEL_CONFIG_PROPERTY_ID {
        public static final int EvtChannelConfigAccess = 5;
        public static final int EvtChannelConfigClassicEventlog = 4;
        public static final int EvtChannelConfigEnabled = 0;
        public static final int EvtChannelConfigIsolation = 1;
        public static final int EvtChannelConfigOwningPublisher = 3;
        public static final int EvtChannelConfigPropertyIdEND = 21;
        public static final int EvtChannelConfigType = 2;
        public static final int EvtChannelLoggingConfigAutoBackup = 7;
        public static final int EvtChannelLoggingConfigLogFilePath = 9;
        public static final int EvtChannelLoggingConfigMaxSize = 8;
        public static final int EvtChannelLoggingConfigRetention = 6;
        public static final int EvtChannelPublisherList = 19;
        public static final int EvtChannelPublishingConfigBufferSize = 13;
        public static final int EvtChannelPublishingConfigClockType = 17;
        public static final int EvtChannelPublishingConfigControlGuid = 12;
        public static final int EvtChannelPublishingConfigFileMax = 20;
        public static final int EvtChannelPublishingConfigKeywords = 11;
        public static final int EvtChannelPublishingConfigLatency = 16;
        public static final int EvtChannelPublishingConfigLevel = 10;
        public static final int EvtChannelPublishingConfigMaxBuffers = 15;
        public static final int EvtChannelPublishingConfigMinBuffers = 14;
        public static final int EvtChannelPublishingConfigSidType = 18;
    }

    public interface EVT_CHANNEL_ISOLATION_TYPE {
        public static final int EvtChannelIsolationTypeApplication = 0;
        public static final int EvtChannelIsolationTypeCustom = 2;
        public static final int EvtChannelIsolationTypeSystem = 1;
    }

    public interface EVT_CHANNEL_REFERENCE_FLAGS {
        public static final int EvtChannelReferenceImported = 1;
    }

    public interface EVT_CHANNEL_SID_TYPE {
        public static final int EvtChannelSidTypeNone = 0;
        public static final int EvtChannelSidTypePublishing = 1;
    }

    public interface EVT_CHANNEL_TYPE {
        public static final int EvtChannelTypeAdmin = 0;
        public static final int EvtChannelTypeAnalytic = 2;
        public static final int EvtChannelTypeDebug = 3;
        public static final int EvtChannelTypeOperational = 1;
    }

    public interface EVT_EVENT_METADATA_PROPERTY_ID {
        public static final int EventMetadataEventChannel = 2;
        public static final int EventMetadataEventID = 0;
        public static final int EventMetadataEventKeyword = 6;
        public static final int EventMetadataEventLevel = 3;
        public static final int EventMetadataEventMessageID = 7;
        public static final int EventMetadataEventOpcode = 4;
        public static final int EventMetadataEventTask = 5;
        public static final int EventMetadataEventTemplate = 8;
        public static final int EventMetadataEventVersion = 1;
        public static final int EvtEventMetadataPropertyIdEND = 9;
    }

    public interface EVT_EVENT_PROPERTY_ID {
        public static final int EvtEventPath = 1;
        public static final int EvtEventPropertyIdEND = 2;
        public static final int EvtEventQueryIDs = 0;
    }

    public interface EVT_EXPORTLOG_FLAGS {
        public static final int EvtExportLogChannelPath = 1;
        public static final int EvtExportLogFilePath = 2;
        public static final int EvtExportLogOverwrite = 8192;
        public static final int EvtExportLogTolerateQueryErrors = 4096;
    }

    public interface EVT_FORMAT_MESSAGE_FLAGS {
        public static final int EvtFormatMessageChannel = 6;
        public static final int EvtFormatMessageEvent = 1;
        public static final int EvtFormatMessageId = 8;
        public static final int EvtFormatMessageKeyword = 5;
        public static final int EvtFormatMessageLevel = 2;
        public static final int EvtFormatMessageOpcode = 4;
        public static final int EvtFormatMessageProvider = 7;
        public static final int EvtFormatMessageTask = 3;
        public static final int EvtFormatMessageXml = 9;
    }

    public interface EVT_LOGIN_CLASS {
        public static final int EvtRpcLogin = 1;
    }

    public interface EVT_LOG_PROPERTY_ID {
        public static final int EvtLogAttributes = 4;
        public static final int EvtLogCreationTime = 0;
        public static final int EvtLogFileSize = 3;
        public static final int EvtLogFull = 7;
        public static final int EvtLogLastAccessTime = 1;
        public static final int EvtLogLastWriteTime = 2;
        public static final int EvtLogNumberOfLogRecords = 5;
        public static final int EvtLogOldestRecordNumber = 6;
    }

    public interface EVT_OPEN_LOG_FLAGS {
        public static final int EvtOpenChannelPath = 1;
        public static final int EvtOpenFilePath = 2;
    }

    public interface EVT_PUBLISHER_METADATA_PROPERTY_ID {
        public static final int EvtPublisherMetadataChannelReferenceFlags = 10;
        public static final int EvtPublisherMetadataChannelReferenceID = 9;
        public static final int EvtPublisherMetadataChannelReferenceIndex = 8;
        public static final int EvtPublisherMetadataChannelReferenceMessageID = 11;
        public static final int EvtPublisherMetadataChannelReferencePath = 7;
        public static final int EvtPublisherMetadataChannelReferences = 6;
        public static final int EvtPublisherMetadataHelpLink = 4;
        public static final int EvtPublisherMetadataKeywordMessageID = 28;
        public static final int EvtPublisherMetadataKeywordName = 26;
        public static final int EvtPublisherMetadataKeywordValue = 27;
        public static final int EvtPublisherMetadataKeywords = 25;
        public static final int EvtPublisherMetadataLevelMessageID = 15;
        public static final int EvtPublisherMetadataLevelName = 13;
        public static final int EvtPublisherMetadataLevelValue = 14;
        public static final int EvtPublisherMetadataLevels = 12;
        public static final int EvtPublisherMetadataMessageFilePath = 3;
        public static final int EvtPublisherMetadataOpcodeMessageID = 24;
        public static final int EvtPublisherMetadataOpcodeName = 22;
        public static final int EvtPublisherMetadataOpcodeValue = 23;
        public static final int EvtPublisherMetadataOpcodes = 21;
        public static final int EvtPublisherMetadataParameterFilePath = 2;
        public static final int EvtPublisherMetadataPropertyIdEND = 29;
        public static final int EvtPublisherMetadataPublisherGuid = 0;
        public static final int EvtPublisherMetadataPublisherMessageID = 5;
        public static final int EvtPublisherMetadataResourceFilePath = 1;
        public static final int EvtPublisherMetadataTaskEventGuid = 18;
        public static final int EvtPublisherMetadataTaskMessageID = 20;
        public static final int EvtPublisherMetadataTaskName = 17;
        public static final int EvtPublisherMetadataTaskValue = 19;
        public static final int EvtPublisherMetadataTasks = 16;
    }

    public interface EVT_QUERY_FLAGS {
        public static final int EvtQueryChannelPath = 1;
        public static final int EvtQueryFilePath = 2;
        public static final int EvtQueryForwardDirection = 256;
        public static final int EvtQueryReverseDirection = 512;
        public static final int EvtQueryTolerateQueryErrors = 4096;
    }

    public interface EVT_QUERY_PROPERTY_ID {
        public static final int EvtQueryNames = 0;
        public static final int EvtQueryPropertyIdEND = 2;
        public static final int EvtQueryStatuses = 1;
    }

    public interface EVT_RENDER_CONTEXT_FLAGS {
        public static final int EvtRenderContextSystem = 1;
        public static final int EvtRenderContextUser = 2;
        public static final int EvtRenderContextValues = 0;
    }

    public interface EVT_RENDER_FLAGS {
        public static final int EvtRenderBookmark = 2;
        public static final int EvtRenderEventValues = 0;
        public static final int EvtRenderEventXml = 1;
    }

    public interface EVT_RPC_LOGIN_FLAGS {
        public static final int EvtRpcLoginAuthDefault = 0;
        public static final int EvtRpcLoginAuthKerberos = 2;
        public static final int EvtRpcLoginAuthNTLM = 3;
        public static final int EvtRpcLoginAuthNegotiate = 1;
    }

    public interface EVT_SEEK_FLAGS {
        public static final int EvtSeekOriginMask = 7;
        public static final int EvtSeekRelativeToBookmark = 4;
        public static final int EvtSeekRelativeToCurrent = 3;
        public static final int EvtSeekRelativeToFirst = 1;
        public static final int EvtSeekRelativeToLast = 2;
        public static final int EvtSeekStrict = 65536;
    }

    public interface EVT_SUBSCRIBE_FLAGS {
        public static final int EvtSubscribeOriginMask = 3;
        public static final int EvtSubscribeStartAfterBookmark = 3;
        public static final int EvtSubscribeStartAtOldestRecord = 2;
        public static final int EvtSubscribeStrict = 65536;
        public static final int EvtSubscribeToFutureEvents = 1;
        public static final int EvtSubscribeTolerateQueryErrors = 4096;
    }

    public interface EVT_SUBSCRIBE_NOTIFY_ACTION {
        public static final int EvtSubscribeActionDeliver = 1;
        public static final int EvtSubscribeActionError = 0;
    }

    public interface EVT_SYSTEM_PROPERTY_ID {
        public static final int EvtSystemActivityID = 10;
        public static final int EvtSystemChannel = 14;
        public static final int EvtSystemComputer = 15;
        public static final int EvtSystemEventID = 2;
        public static final int EvtSystemEventRecordId = 9;
        public static final int EvtSystemKeywords = 7;
        public static final int EvtSystemLevel = 4;
        public static final int EvtSystemOpcode = 6;
        public static final int EvtSystemProcessID = 12;
        public static final int EvtSystemPropertyIdEND = 18;
        public static final int EvtSystemProviderGuid = 1;
        public static final int EvtSystemProviderName = 0;
        public static final int EvtSystemQualifiers = 3;
        public static final int EvtSystemRelatedActivityID = 11;
        public static final int EvtSystemTask = 5;
        public static final int EvtSystemThreadID = 13;
        public static final int EvtSystemTimeCreated = 8;
        public static final int EvtSystemUserID = 16;
        public static final int EvtSystemVersion = 17;
    }

    public enum EVT_VARIANT_TYPE {
        EvtVarTypeNull(""),
        EvtVarTypeString("String"),
        EvtVarTypeAnsiString("AnsiString"),
        EvtVarTypeSByte("SByte"),
        EvtVarTypeByte("Byte"),
        EvtVarTypeInt16("Int16"),
        EvtVarTypeUInt16("UInt16"),
        EvtVarTypeInt32("Int32"),
        EvtVarTypeUInt32("UInt32"),
        EvtVarTypeInt64("Int64"),
        EvtVarTypeUInt64("UInt64"),
        EvtVarTypeSingle("Single"),
        EvtVarTypeDouble("Double"),
        EvtVarTypeBoolean("Boolean"),
        EvtVarTypeBinary("Binary"),
        EvtVarTypeGuid("Guid"),
        EvtVarTypeSizeT("SizeT"),
        EvtVarTypeFileTime("FileTime"),
        EvtVarTypeSysTime("SysTime"),
        EvtVarTypeSid("Sid"),
        EvtVarTypeHexInt32("Int32"),
        EvtVarTypeHexInt64("Int64"),
        EvtVarTypeEvtHandle("EvtHandle"),
        EvtVarTypeEvtXml("Xml");

        private final String field;

        EVT_VARIANT_TYPE(String str) {
            this.field = str;
        }

        public String getArrField() {
            if (this.field.isEmpty()) {
                return "";
            }
            return this.field + "Arr";
        }

        public String getField() {
            if (this.field.isEmpty()) {
                return "";
            }
            return this.field + "Val";
        }
    }

    @Structure.FieldOrder({"field1", "Count", "Type"})
    public static class EVT_VARIANT extends Structure {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public int Count;
        public int Type;
        public field1_union field1;
        private Object holder;

        public static class field1_union extends Union {
            public byte byteValue;
            public double doubleVal;
            public float floatValue;
            public int intValue;
            public long longValue;
            public Pointer pointerValue;
            public short shortValue;
        }

        public EVT_VARIANT(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
        }

        private int getBaseType() {
            return this.Type & 127;
        }

        public Object getValue() {
            EVT_VARIANT_TYPE variantType = getVariantType();
            int i = 0;
            switch (AnonymousClass1.$SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[variantType.ordinal()]) {
                case 1:
                    boolean zIsArray = isArray();
                    field1_union field1_unionVar = this.field1;
                    return zIsArray ? field1_unionVar.getPointer().getPointer(0L).getStringArray(0L, this.Count) : field1_unionVar.getPointer().getPointer(0L).getString(0L);
                case 2:
                    if (!isArray()) {
                        return new WinDef.BOOL(this.field1.getPointer().getInt(0L));
                    }
                    int[] intArray = this.field1.getPointer().getPointer(0L).getIntArray(0L, this.Count);
                    int length = intArray.length;
                    WinDef.BOOL[] boolArr = new WinDef.BOOL[length];
                    while (i < length) {
                        boolArr[i] = new WinDef.BOOL(intArray[i]);
                        i++;
                    }
                    return boolArr;
                case 3:
                case 4:
                    boolean zIsArray2 = isArray();
                    field1_union field1_unionVar2 = this.field1;
                    return zIsArray2 ? field1_unionVar2.getPointer().getPointer(0L).getWideStringArray(0L, this.Count) : field1_unionVar2.getPointer().getPointer(0L).getWideString(0L);
                case 5:
                case 6:
                    boolean zIsArray3 = isArray();
                    field1_union field1_unionVar3 = this.field1;
                    return zIsArray3 ? field1_unionVar3.getPointer().getPointer(0L).getByteArray(0L, this.Count) : Byte.valueOf(field1_unionVar3.getPointer().getByte(0L));
                case 7:
                case 8:
                    boolean zIsArray4 = isArray();
                    field1_union field1_unionVar4 = this.field1;
                    return zIsArray4 ? field1_unionVar4.getPointer().getPointer(0L).getShortArray(0L, this.Count) : Short.valueOf(field1_unionVar4.getPointer().getShort(0L));
                case 9:
                case 10:
                case 11:
                    boolean zIsArray5 = isArray();
                    field1_union field1_unionVar5 = this.field1;
                    return zIsArray5 ? field1_unionVar5.getPointer().getPointer(0L).getIntArray(0L, this.Count) : Integer.valueOf(field1_unionVar5.getPointer().getInt(0L));
                case 12:
                case 13:
                case 14:
                    boolean zIsArray6 = isArray();
                    field1_union field1_unionVar6 = this.field1;
                    return zIsArray6 ? field1_unionVar6.getPointer().getPointer(0L).getLongArray(0L, this.Count) : Long.valueOf(field1_unionVar6.getPointer().getLong(0L));
                case 15:
                    boolean zIsArray7 = isArray();
                    field1_union field1_unionVar7 = this.field1;
                    return zIsArray7 ? field1_unionVar7.getPointer().getPointer(0L).getFloatArray(0L, this.Count) : Float.valueOf(field1_unionVar7.getPointer().getFloat(0L));
                case 16:
                    boolean zIsArray8 = isArray();
                    field1_union field1_unionVar8 = this.field1;
                    return zIsArray8 ? field1_unionVar8.getPointer().getPointer(0L).getDoubleArray(0L, this.Count) : Double.valueOf(field1_unionVar8.getPointer().getDouble(0L));
                case 17:
                    return this.field1.getPointer().getPointer(0L).getByteArray(0L, this.Count);
                case 18:
                    boolean zIsArray9 = isArray();
                    field1_union field1_unionVar9 = this.field1;
                    if (zIsArray9) {
                        WinBase.FILETIME filetime = (WinBase.FILETIME) Structure.newInstance(WinBase.FILETIME.class, field1_unionVar9.getPointer().getPointer(0L));
                        filetime.read();
                        return filetime.toArray(this.Count);
                    }
                    WinBase.FILETIME filetime2 = new WinBase.FILETIME(field1_unionVar9.getPointer());
                    filetime2.read();
                    return filetime2;
                case 19:
                    boolean zIsArray10 = isArray();
                    field1_union field1_unionVar10 = this.field1;
                    if (!zIsArray10) {
                        return new WinNT.HANDLE(field1_unionVar10.getPointer().getPointer(0L));
                    }
                    Pointer[] pointerArray = field1_unionVar10.getPointer().getPointer(0L).getPointerArray(0L, this.Count);
                    int length2 = pointerArray.length;
                    WinNT.HANDLE[] handleArr = new WinNT.HANDLE[length2];
                    while (i < length2) {
                        handleArr[i] = new WinNT.HANDLE(pointerArray[i]);
                        i++;
                    }
                    return handleArr;
                case 20:
                    boolean zIsArray11 = isArray();
                    field1_union field1_unionVar11 = this.field1;
                    if (zIsArray11) {
                        WinBase.SYSTEMTIME systemtime = (WinBase.SYSTEMTIME) Structure.newInstance(WinBase.SYSTEMTIME.class, field1_unionVar11.getPointer().getPointer(0L));
                        systemtime.read();
                        return systemtime.toArray(this.Count);
                    }
                    WinBase.SYSTEMTIME systemtime2 = (WinBase.SYSTEMTIME) Structure.newInstance(WinBase.SYSTEMTIME.class, field1_unionVar11.getPointer().getPointer(0L));
                    systemtime2.read();
                    return systemtime2;
                case 21:
                    boolean zIsArray12 = isArray();
                    field1_union field1_unionVar12 = this.field1;
                    if (zIsArray12) {
                        Guid.GUID guid = (Guid.GUID) Structure.newInstance(Guid.GUID.class, field1_unionVar12.getPointer().getPointer(0L));
                        guid.read();
                        return guid.toArray(this.Count);
                    }
                    Guid.GUID guid2 = (Guid.GUID) Structure.newInstance(Guid.GUID.class, field1_unionVar12.getPointer().getPointer(0L));
                    guid2.read();
                    return guid2;
                case 22:
                    boolean zIsArray13 = isArray();
                    field1_union field1_unionVar13 = this.field1;
                    if (zIsArray13) {
                        WinNT.PSID psid = (WinNT.PSID) Structure.newInstance(WinNT.PSID.class, field1_unionVar13.getPointer().getPointer(0L));
                        psid.read();
                        return psid.toArray(this.Count);
                    }
                    WinNT.PSID psid2 = (WinNT.PSID) Structure.newInstance(WinNT.PSID.class, field1_unionVar13.getPointer().getPointer(0L));
                    psid2.read();
                    return psid2;
                case 23:
                    boolean zIsArray14 = isArray();
                    field1_union field1_unionVar14 = this.field1;
                    if (!zIsArray14) {
                        return new BaseTSD.SIZE_T(field1_unionVar14.getPointer().getLong(0L));
                    }
                    long[] longArray = field1_unionVar14.getPointer().getPointer(0L).getLongArray(0L, this.Count);
                    int length3 = longArray.length;
                    BaseTSD.SIZE_T[] size_tArr = new BaseTSD.SIZE_T[length3];
                    while (i < length3) {
                        size_tArr[i] = new BaseTSD.SIZE_T(longArray[i]);
                        i++;
                    }
                    return size_tArr;
                case 24:
                    return null;
                default:
                    throw new IllegalStateException(String.format("NOT IMPLEMENTED: getValue(%s) (Array: %b, Count: %d)", variantType, Boolean.valueOf(isArray()), Integer.valueOf(this.Count)));
            }
        }

        public EVT_VARIANT_TYPE getVariantType() {
            return EVT_VARIANT_TYPE.values()[getBaseType()];
        }

        public boolean isArray() {
            return (this.Type & 128) == 128;
        }

        public void setValue(EVT_VARIANT_TYPE evt_variant_type, Object obj) {
            allocateMemory();
            if (evt_variant_type == null) {
                w01.a("setValue must not be called with type set to NULL");
                return;
            }
            this.holder = null;
            if (obj == null || evt_variant_type == EVT_VARIANT_TYPE.EvtVarTypeNull) {
                this.Type = EVT_VARIANT_TYPE.EvtVarTypeNull.ordinal();
                this.Count = 0;
                this.field1.writeField("pointerValue", Pointer.NULL);
            } else {
                int i = AnonymousClass1.$SwitchMap$com$sun$jna$platform$win32$Winevt$EVT_VARIANT_TYPE[evt_variant_type.ordinal()];
                Class<?> cls = Byte.TYPE;
                switch (i) {
                    case 1:
                        if (obj.getClass().isArray() && obj.getClass().getComponentType() == String.class) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            String[] strArr = (String[]) obj;
                            StringArray stringArray = new StringArray(strArr, false);
                            this.holder = stringArray;
                            this.Count = strArr.length;
                            this.field1.writeField("pointerValue", stringArray);
                        } else {
                            if (obj.getClass() != String.class) {
                                iu9.a(evt_variant_type.name(), " must be set from String/String[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            String str = (String) obj;
                            Memory memory = new Memory(str.length() + 1);
                            memory.setString(0L, str);
                            this.holder = memory;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory);
                        }
                        break;
                    case 2:
                        if (obj.getClass().isArray() && obj.getClass().getComponentType() == WinDef.BOOL.class) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            WinDef.BOOL[] boolArr = (WinDef.BOOL[]) obj;
                            Memory memory2 = new Memory(boolArr.length * 4);
                            for (int i2 = 0; i2 < boolArr.length; i2++) {
                                memory2.setInt(i2 * 4, boolArr[i2].intValue());
                            }
                            this.holder = memory2;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory2);
                        } else {
                            if (obj.getClass() != WinDef.BOOL.class) {
                                iu9.a(evt_variant_type.name(), " must be set from BOOL/BOOL[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            this.Count = 0;
                            this.field1.writeField(Constants.INT_VALUE, Integer.valueOf(((WinDef.BOOL) obj).intValue()));
                        }
                        break;
                    case 3:
                    case 4:
                        if (obj.getClass().isArray() && obj.getClass().getComponentType() == String.class) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            String[] strArr2 = (String[]) obj;
                            StringArray stringArray2 = new StringArray(strArr2, true);
                            this.holder = stringArray2;
                            this.Count = strArr2.length;
                            this.field1.writeField("pointerValue", stringArray2);
                        } else {
                            if (obj.getClass() != String.class) {
                                iu9.a(evt_variant_type.name(), " must be set from String/String[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            String str2 = (String) obj;
                            Memory memory3 = new Memory((str2.length() + 1) * 2);
                            memory3.setWideString(0L, str2);
                            this.holder = memory3;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory3);
                        }
                        break;
                    case 5:
                    case 6:
                        if (obj.getClass().isArray() && obj.getClass().getComponentType() == cls) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            byte[] bArr = (byte[]) obj;
                            Memory memory4 = new Memory(bArr.length);
                            memory4.write(0L, bArr, 0, bArr.length);
                            this.holder = memory4;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory4);
                        } else {
                            if (obj.getClass() != cls) {
                                iu9.a(evt_variant_type.name(), " must be set from byte/byte[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            this.Count = 0;
                            this.field1.writeField("byteValue", obj);
                        }
                        break;
                    case 7:
                    case 8:
                        boolean zIsArray = obj.getClass().isArray();
                        Class<?> cls2 = Short.TYPE;
                        if (zIsArray && obj.getClass().getComponentType() == cls2) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            short[] sArr = (short[]) obj;
                            Memory memory5 = new Memory(sArr.length * 2);
                            memory5.write(0L, sArr, 0, sArr.length);
                            this.holder = memory5;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory5);
                        } else {
                            if (obj.getClass() != cls2) {
                                iu9.a(evt_variant_type.name(), " must be set from short/short[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            this.Count = 0;
                            this.field1.writeField("shortValue", obj);
                        }
                        break;
                    case 9:
                    case 10:
                    case 11:
                        boolean zIsArray2 = obj.getClass().isArray();
                        Class<?> cls3 = Integer.TYPE;
                        if (zIsArray2 && obj.getClass().getComponentType() == cls3) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            int[] iArr = (int[]) obj;
                            Memory memory6 = new Memory(iArr.length * 4);
                            memory6.write(0L, iArr, 0, iArr.length);
                            this.holder = memory6;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory6);
                        } else {
                            if (obj.getClass() != cls3) {
                                iu9.a(evt_variant_type.name(), " must be set from int/int[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            this.Count = 0;
                            this.field1.writeField(Constants.INT_VALUE, obj);
                        }
                        break;
                    case 12:
                    case 13:
                    case 14:
                        boolean zIsArray3 = obj.getClass().isArray();
                        Class<?> cls4 = Long.TYPE;
                        if (zIsArray3 && obj.getClass().getComponentType() == cls4) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            long[] jArr = (long[]) obj;
                            Memory memory7 = new Memory(jArr.length * 4);
                            memory7.write(0L, jArr, 0, jArr.length);
                            this.holder = memory7;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory7);
                        } else {
                            if (obj.getClass() != cls4) {
                                iu9.a(evt_variant_type.name(), " must be set from long/long[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            this.Count = 0;
                            this.field1.writeField("longValue", obj);
                        }
                        break;
                    case 15:
                        boolean zIsArray4 = obj.getClass().isArray();
                        Class<?> cls5 = Float.TYPE;
                        if (zIsArray4 && obj.getClass().getComponentType() == cls5) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            float[] fArr = (float[]) obj;
                            Memory memory8 = new Memory(fArr.length * 4);
                            memory8.write(0L, fArr, 0, fArr.length);
                            this.holder = memory8;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory8);
                        } else {
                            if (obj.getClass() != cls5) {
                                iu9.a(evt_variant_type.name(), " must be set from float/float[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            this.Count = 0;
                            this.field1.writeField("floatValue", obj);
                        }
                        break;
                    case 16:
                        boolean zIsArray5 = obj.getClass().isArray();
                        Class<?> cls6 = Double.TYPE;
                        if (zIsArray5 && obj.getClass().getComponentType() == cls6) {
                            this.Type = evt_variant_type.ordinal() | 128;
                            double[] dArr = (double[]) obj;
                            Memory memory9 = new Memory(dArr.length * 4);
                            memory9.write(0L, dArr, 0, dArr.length);
                            this.holder = memory9;
                            this.Count = 0;
                            this.field1.writeField("pointerValue", memory9);
                        } else {
                            if (obj.getClass() != cls6) {
                                iu9.a(evt_variant_type.name(), " must be set from double/double[]");
                                return;
                            }
                            this.Type = evt_variant_type.ordinal();
                            this.Count = 0;
                            this.field1.writeField("doubleVal", obj);
                        }
                        break;
                    case 17:
                        if (!obj.getClass().isArray() || obj.getClass().getComponentType() != cls) {
                            iu9.a(evt_variant_type.name(), " must be set from byte[]");
                            return;
                        }
                        this.Type = evt_variant_type.ordinal();
                        byte[] bArr2 = (byte[]) obj;
                        Memory memory10 = new Memory(bArr2.length);
                        memory10.write(0L, bArr2, 0, bArr2.length);
                        this.holder = memory10;
                        this.Count = 0;
                        this.field1.writeField("pointerValue", memory10);
                        break;
                        break;
                    default:
                        throw new IllegalStateException(String.format("NOT IMPLEMENTED: getValue(%s) (Array: %b, Count: %d)", evt_variant_type, Boolean.valueOf(isArray()), Integer.valueOf(this.Count)));
                }
            }
            write();
        }

        public void use(Pointer pointer) {
            useMemory(pointer, 0);
        }

        public static class ByReference extends EVT_VARIANT implements Structure.ByReference {
            public ByReference(Pointer pointer) {
                super(pointer);
            }

            public ByReference() {
            }
        }

        public static class ByValue extends EVT_VARIANT implements Structure.ByValue {
            public ByValue(Pointer pointer) {
                super(pointer);
            }

            public ByValue() {
            }
        }

        public EVT_VARIANT() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    public static class EVT_HANDLE extends WinNT.HANDLE {
        public EVT_HANDLE() {
        }

        public EVT_HANDLE(Pointer pointer) {
            super(pointer);
        }
    }

    @Structure.FieldOrder({"Server", "User", "Domain", "Password", "Flags"})
    public static class EVT_RPC_LOGIN extends Structure {
        public String Domain;
        public int Flags;
        public String Password;
        public String Server;
        public String User;

        public static class ByReference extends EVT_RPC_LOGIN implements Structure.ByReference {
        }

        public static class ByValue extends EVT_RPC_LOGIN implements Structure.ByValue {
        }

        public EVT_RPC_LOGIN(String str, String str2, String str3, String str4, int i) {
            super(W32APITypeMapper.UNICODE);
            this.Server = str;
            this.User = str2;
            this.Domain = str3;
            this.Password = str4;
            this.Flags = i;
        }

        public EVT_RPC_LOGIN() {
            super(W32APITypeMapper.UNICODE);
        }

        public EVT_RPC_LOGIN(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.UNICODE);
        }
    }
}
