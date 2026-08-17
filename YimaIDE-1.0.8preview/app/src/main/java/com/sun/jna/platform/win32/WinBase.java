package com.sun.jna.platform.win32;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APITypeMapper;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WinBase extends BaseTSD, WinDef {
    public static final int CBR_110 = 110;
    public static final int CBR_1200 = 1200;
    public static final int CBR_128000 = 128000;
    public static final int CBR_14400 = 14400;
    public static final int CBR_19200 = 19200;
    public static final int CBR_2400 = 2400;
    public static final int CBR_256000 = 256000;
    public static final int CBR_300 = 300;
    public static final int CBR_38400 = 38400;
    public static final int CBR_4800 = 4800;
    public static final int CBR_56000 = 56000;
    public static final int CBR_600 = 600;
    public static final int CBR_9600 = 9600;
    public static final int CREATE_BREAKAWAY_FROM_JOB = 16777216;
    public static final int CREATE_DEFAULT_ERROR_MODE = 67108864;
    public static final int CREATE_FORCEDOS = 8192;
    public static final int CREATE_FOR_DIR = 2;
    public static final int CREATE_FOR_IMPORT = 1;
    public static final int CREATE_NEW_CONSOLE = 16;
    public static final int CREATE_NEW_PROCESS_GROUP = 512;
    public static final int CREATE_NO_WINDOW = 134217728;
    public static final int CREATE_PRESERVE_CODE_AUTHZ_LEVEL = 33554432;
    public static final int CREATE_PROTECTED_PROCESS = 262144;
    public static final int CREATE_SEPARATE_WOW_VDM = 2048;
    public static final int CREATE_SHARED_WOW_VDM = 4096;
    public static final int CREATE_SUSPENDED = 4;
    public static final int CREATE_UNICODE_ENVIRONMENT = 1024;
    public static final int DEBUG_ONLY_THIS_PROCESS = 2;
    public static final int DEBUG_PROCESS = 1;
    public static final int DETACHED_PROCESS = 8;
    public static final int DRIVE_CDROM = 5;
    public static final int DRIVE_FIXED = 3;
    public static final int DRIVE_NO_ROOT_DIR = 1;
    public static final int DRIVE_RAMDISK = 6;
    public static final int DRIVE_REMOTE = 4;
    public static final int DRIVE_REMOVABLE = 2;
    public static final int DRIVE_UNKNOWN = 0;
    public static final int DTR_CONTROL_DISABLE = 0;
    public static final int DTR_CONTROL_ENABLE = 1;
    public static final int DTR_CONTROL_HANDSHAKE = 2;
    public static final int ES_AWAYMODE_REQUIRED = 64;
    public static final int ES_CONTINUOUS = Integer.MIN_VALUE;
    public static final int ES_DISPLAY_REQUIRED = 2;
    public static final int ES_SYSTEM_REQUIRED = 1;
    public static final int ES_USER_PRESENT = 4;
    public static final int EVENPARITY = 2;
    public static final int EXTENDED_STARTUPINFO_PRESENT = 524288;
    public static final int FILE_DIR_DISALOWED = 9;
    public static final int FILE_ENCRYPTABLE = 0;
    public static final int FILE_IS_ENCRYPTED = 1;
    public static final int FILE_READ_ONLY = 8;
    public static final int FILE_ROOT_DIR = 3;
    public static final int FILE_SYSTEM_ATTR = 2;
    public static final int FILE_SYSTEM_DIR = 4;
    public static final int FILE_SYSTEM_NOT_SUPPORT = 6;
    public static final int FILE_UNKNOWN = 5;
    public static final int FILE_USER_DISALLOWED = 7;
    public static final int FORMAT_MESSAGE_ALLOCATE_BUFFER = 256;
    public static final int FORMAT_MESSAGE_ARGUMENT_ARRAY = 8192;
    public static final int FORMAT_MESSAGE_FROM_HMODULE = 2048;
    public static final int FORMAT_MESSAGE_FROM_STRING = 1024;
    public static final int FORMAT_MESSAGE_FROM_SYSTEM = 4096;
    public static final int FORMAT_MESSAGE_IGNORE_INSERTS = 512;
    public static final int FileAlignmentInfo = 17;
    public static final int FileAllocationInfo = 5;
    public static final int FileAttributeTagInfo = 9;
    public static final int FileBasicInfo = 0;
    public static final int FileCompressionInfo = 8;
    public static final int FileDispositionInfo = 4;
    public static final int FileEndOfFileInfo = 6;
    public static final int FileFullDirectoryInfo = 14;
    public static final int FileFullDirectoryRestartInfo = 15;
    public static final int FileIdBothDirectoryInfo = 10;
    public static final int FileIdBothDirectoryRestartInfo = 11;
    public static final int FileIdExtdDirectoryInfo = 19;
    public static final int FileIdExtdDirectoryRestartInfo = 20;
    public static final int FileIdInfo = 18;
    public static final int FileIoPriorityHintInfo = 12;
    public static final int FileNameInfo = 2;
    public static final int FileRemoteProtocolInfo = 13;
    public static final int FileRenameInfo = 3;
    public static final int FileStandardInfo = 1;
    public static final int FileStorageInfo = 16;
    public static final int FileStreamInfo = 7;
    public static final int FindExInfoBasic = 1;
    public static final int FindExInfoMaxInfoLevel = 2;
    public static final int FindExInfoStandard = 0;
    public static final int FindExSearchLimitToDevices = 2;
    public static final int FindExSearchLimitToDirectories = 1;
    public static final int FindExSearchNameMatch = 0;
    public static final int HANDLE_FLAG_INHERIT = 1;
    public static final int HANDLE_FLAG_PROTECT_FROM_CLOSE = 2;
    public static final int INFINITE = -1;
    public static final int INHERIT_PARENT_AFFINITY = 65536;
    public static final int INVALID_FILE_ATTRIBUTES = -1;
    public static final int INVALID_FILE_SIZE = -1;
    public static final WinNT.HANDLE INVALID_HANDLE_VALUE;
    public static final int INVALID_SET_FILE_POINTER = -1;
    public static final int LHND = 66;
    public static final int LMEM_DISCARDABLE = 3840;
    public static final int LMEM_DISCARDED = 16384;
    public static final int LMEM_FIXED = 0;
    public static final int LMEM_INVALID_HANDLE = 32768;
    public static final int LMEM_LOCKCOUNT = 255;
    public static final int LMEM_MODIFY = 128;
    public static final int LMEM_MOVEABLE = 2;
    public static final int LMEM_NOCOMPACT = 16;
    public static final int LMEM_NODISCARD = 32;
    public static final int LMEM_VALID_FLAGS = 3954;
    public static final int LMEM_ZEROINIT = 64;
    public static final int LOGON32_LOGON_BATCH = 4;
    public static final int LOGON32_LOGON_INTERACTIVE = 2;
    public static final int LOGON32_LOGON_NETWORK = 3;
    public static final int LOGON32_LOGON_NETWORK_CLEARTEXT = 8;
    public static final int LOGON32_LOGON_NEW_CREDENTIALS = 9;
    public static final int LOGON32_LOGON_SERVICE = 5;
    public static final int LOGON32_LOGON_UNLOCK = 7;
    public static final int LOGON32_PROVIDER_DEFAULT = 0;
    public static final int LOGON32_PROVIDER_WINNT35 = 1;
    public static final int LOGON32_PROVIDER_WINNT40 = 2;
    public static final int LOGON32_PROVIDER_WINNT50 = 3;
    public static final int LPTR = 64;
    public static final int MARKPARITY = 3;
    public static final int MAX_COMPUTERNAME_LENGTH;
    public static final int MOVEFILE_COPY_ALLOWED = 2;
    public static final int MOVEFILE_CREATE_HARDLINK = 16;
    public static final int MOVEFILE_DELAY_UNTIL_REBOOT = 4;
    public static final int MOVEFILE_FAIL_IF_NOT_TRACKABLE = 32;
    public static final int MOVEFILE_REPLACE_EXISTING = 1;
    public static final int MOVEFILE_WRITE_THROUGH = 8;
    public static final int MUTEX_ALL_ACCESS = 2031617;
    public static final int MUTEX_MODIFY_STATE = 1;
    public static final int NMPWAIT_NOWAIT = 1;
    public static final int NMPWAIT_USE_DEFAULT_WAIT = 0;
    public static final int NMPWAIT_WAIT_FOREVER = -1;
    public static final int NOPARITY = 0;
    public static final int ODDPARITY = 1;
    public static final int ONE5STOPBITS = 1;
    public static final int ONESTOPBIT = 0;
    public static final int OVERWRITE_HIDDEN = 4;
    public static final int PIPE_ACCEPT_REMOTE_CLIENTS = 0;
    public static final int PIPE_ACCESS_DUPLEX = 3;
    public static final int PIPE_ACCESS_INBOUND = 1;
    public static final int PIPE_ACCESS_OUTBOUND = 2;
    public static final int PIPE_CLIENT_END = 0;
    public static final int PIPE_NOWAIT = 1;
    public static final int PIPE_READMODE_BYTE = 0;
    public static final int PIPE_READMODE_MESSAGE = 2;
    public static final int PIPE_REJECT_REMOTE_CLIENTS = 8;
    public static final int PIPE_SERVER_END = 1;
    public static final int PIPE_TYPE_BYTE = 0;
    public static final int PIPE_TYPE_MESSAGE = 4;
    public static final int PIPE_UNLIMITED_INSTANCES = 255;
    public static final int PIPE_WAIT = 0;
    public static final int RTS_CONTROL_DISABLE = 0;
    public static final int RTS_CONTROL_ENABLE = 1;
    public static final int RTS_CONTROL_HANDSHAKE = 2;
    public static final int RTS_CONTROL_TOGGLE = 3;
    public static final int SPACEPARITY = 4;
    public static final int STARTF_FORCEOFFFEEDBACK = 128;
    public static final int STARTF_FORCEONFEEDBACK = 64;
    public static final int STARTF_RUNFULLSCREEN = 32;
    public static final int STARTF_USECOUNTCHARS = 8;
    public static final int STARTF_USEFILLATTRIBUTE = 16;
    public static final int STARTF_USEPOSITION = 4;
    public static final int STARTF_USESHOWWINDOW = 1;
    public static final int STARTF_USESIZE = 2;
    public static final int STARTF_USESTDHANDLES = 256;
    public static final int STILL_ACTIVE = 259;
    public static final int TWOSTOPBITS = 2;
    public static final int WAIT_ABANDONED = 128;
    public static final int WAIT_ABANDONED_0 = 128;
    public static final int WAIT_FAILED = -1;
    public static final int WAIT_OBJECT_0 = 0;

    @Structure.FieldOrder({"ReadIntervalTimeout", "ReadTotalTimeoutMultiplier", "ReadTotalTimeoutConstant", "WriteTotalTimeoutMultiplier", "WriteTotalTimeoutConstant"})
    public static class COMMTIMEOUTS extends Structure {
        public WinDef.DWORD ReadIntervalTimeout;
        public WinDef.DWORD ReadTotalTimeoutConstant;
        public WinDef.DWORD ReadTotalTimeoutMultiplier;
        public WinDef.DWORD WriteTotalTimeoutConstant;
        public WinDef.DWORD WriteTotalTimeoutMultiplier;
    }

    public interface COMPUTER_NAME_FORMAT {
        public static final int ComputerNameDnsDomain = 2;
        public static final int ComputerNameDnsFullyQualified = 3;
        public static final int ComputerNameDnsHostname = 1;
        public static final int ComputerNameMax = 8;
        public static final int ComputerNameNetBIOS = 0;
        public static final int ComputerNamePhysicalDnsDomain = 6;
        public static final int ComputerNamePhysicalDnsFullyQualified = 7;
        public static final int ComputerNamePhysicalDnsHostname = 5;
        public static final int ComputerNamePhysicalNetBIOS = 4;
    }

    @Structure.FieldOrder({"DCBlength", "BaudRate", "controllBits", "wReserved", "XonLim", "XoffLim", "ByteSize", "Parity", "StopBits", "XonChar", "XoffChar", "ErrorChar", "EofChar", "EvtChar", "wReserved1"})
    public static class DCB extends Structure {
        public WinDef.DWORD BaudRate;
        public WinDef.BYTE ByteSize;
        public WinDef.DWORD DCBlength = new WinDef.DWORD(size());
        public char EofChar;
        public char ErrorChar;
        public char EvtChar;
        public WinDef.BYTE Parity;
        public WinDef.BYTE StopBits;
        public char XoffChar;
        public WinDef.WORD XoffLim;
        public char XonChar;
        public WinDef.WORD XonLim;
        public DCBControllBits controllBits;
        public WinDef.WORD wReserved;
        public WinDef.WORD wReserved1;

        public static class DCBControllBits extends WinDef.DWORD {
            private static final long serialVersionUID = 8574966619718078579L;

            private static int leftShiftMask(int i, byte b, int i2, int i3) {
                return ((i & i2) << b) | (i3 & (~(i2 << b)));
            }

            public boolean getfAbortOnError() {
                return (intValue() & 16384) != 0;
            }

            public boolean getfBinary() {
                return (intValue() & 1) != 0;
            }

            public boolean getfDsrSensitivity() {
                return (intValue() & 64) != 0;
            }

            public int getfDtrControl() {
                return (intValue() >>> 4) & 3;
            }

            public int getfDummy2() {
                return (intValue() >>> 15) & 131071;
            }

            public boolean getfErrorChar() {
                return (intValue() & 1024) != 0;
            }

            public boolean getfInX() {
                return (intValue() & 512) != 0;
            }

            public boolean getfNull() {
                return (intValue() & 2048) != 0;
            }

            public boolean getfOutX() {
                return (intValue() & 256) != 0;
            }

            public boolean getfOutxCtsFlow() {
                return (intValue() & 4) != 0;
            }

            public boolean getfOutxDsrFlow() {
                return (intValue() & 8) != 0;
            }

            public boolean getfParity() {
                return (intValue() & 2) != 0;
            }

            public int getfRtsControl() {
                return (intValue() >>> 12) & 3;
            }

            public boolean getfTXContinueOnXoff() {
                return (intValue() & 128) != 0;
            }

            public void setfAbortOnError(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 14, 1, intValue()));
            }

            public void setfBinary(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 0, 1, intValue()));
            }

            public void setfDsrSensitivity(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 6, 1, intValue()));
            }

            public void setfDtrControl(int i) {
                setValue(leftShiftMask(i, (byte) 4, 3, intValue()));
            }

            public void setfErrorChar(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 10, 1, intValue()));
            }

            public void setfInX(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 9, 1, intValue()));
            }

            public void setfNull(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 11, 1, intValue()));
            }

            public void setfOutX(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 8, 1, intValue()));
            }

            public void setfOutxCtsFlow(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 2, 1, intValue()));
            }

            public void setfOutxDsrFlow(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 3, 1, intValue()));
            }

            public void setfParity(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 1, 1, intValue()));
            }

            public void setfRtsControl(int i) {
                setValue(leftShiftMask(i, (byte) 12, 3, intValue()));
            }

            public void setfTXContinueOnXoff(boolean z) {
                setValue(leftShiftMask(z ? 1 : 0, (byte) 7, 1, intValue()));
            }

            @Override // com.sun.jna.IntegerType
            public String toString() {
                StringBuilder sb = new StringBuilder("<fBinary:1=");
                sb.append(getfBinary() ? '1' : '0');
                sb.append(", fParity:1=");
                sb.append(getfParity() ? '1' : '0');
                sb.append(", fOutxCtsFlow:1=");
                sb.append(getfOutxCtsFlow() ? '1' : '0');
                sb.append(", fOutxDsrFlow:1=");
                sb.append(getfOutxDsrFlow() ? '1' : '0');
                sb.append(", fDtrControl:2=");
                sb.append(getfDtrControl());
                sb.append(", fDsrSensitivity:1=");
                sb.append(getfDsrSensitivity() ? '1' : '0');
                sb.append(", fTXContinueOnXoff:1=");
                sb.append(getfTXContinueOnXoff() ? '1' : '0');
                sb.append(", fOutX:1=");
                sb.append(getfOutX() ? '1' : '0');
                sb.append(", fInX:1=");
                sb.append(getfInX() ? '1' : '0');
                sb.append(", fErrorChar:1=");
                sb.append(getfErrorChar() ? '1' : '0');
                sb.append(", fNull:1=");
                sb.append(getfNull() ? '1' : '0');
                sb.append(", fRtsControl:2=");
                sb.append(getfRtsControl());
                sb.append(", fAbortOnError:1=");
                sb.append(getfAbortOnError() ? '1' : '0');
                sb.append(", fDummy2:17=");
                sb.append(getfDummy2());
                sb.append('>');
                return sb.toString();
            }
        }
    }

    public interface EnumResNameProc extends Callback {
        boolean invoke(WinDef.HMODULE hmodule, Pointer pointer, Pointer pointer2, Pointer pointer3);
    }

    public interface EnumResTypeProc extends Callback {
        boolean invoke(WinDef.HMODULE hmodule, Pointer pointer, Pointer pointer2);
    }

    public interface FE_EXPORT_FUNC extends StdCallLibrary.StdCallCallback {
        WinDef.DWORD callback(Pointer pointer, Pointer pointer2, WinDef.ULONG ulong);
    }

    public interface FE_IMPORT_FUNC extends StdCallLibrary.StdCallCallback {
        WinDef.DWORD callback(Pointer pointer, Pointer pointer2, WinDef.ULONGByReference uLONGByReference);
    }

    @Structure.FieldOrder({"foreignLocation"})
    public static class FOREIGN_THREAD_START_ROUTINE extends Structure {
        public WinDef.LPVOID foreignLocation;
    }

    @Structure.FieldOrder({"dwLength", "dwMemoryLoad", "ullTotalPhys", "ullAvailPhys", "ullTotalPageFile", "ullAvailPageFile", "ullTotalVirtual", "ullAvailVirtual", "ullAvailExtendedVirtual"})
    public static class MEMORYSTATUSEX extends Structure {
        public WinDef.DWORD dwLength = new WinDef.DWORD(size());
        public WinDef.DWORD dwMemoryLoad;
        public WinDef.DWORDLONG ullAvailExtendedVirtual;
        public WinDef.DWORDLONG ullAvailPageFile;
        public WinDef.DWORDLONG ullAvailPhys;
        public WinDef.DWORDLONG ullAvailVirtual;
        public WinDef.DWORDLONG ullTotalPageFile;
        public WinDef.DWORDLONG ullTotalPhys;
        public WinDef.DWORDLONG ullTotalVirtual;
    }

    @Structure.FieldOrder({"Internal", "InternalHigh", "Offset", "OffsetHigh", "hEvent"})
    public static class OVERLAPPED extends Structure {
        public BaseTSD.ULONG_PTR Internal;
        public BaseTSD.ULONG_PTR InternalHigh;
        public int Offset;
        public int OffsetHigh;
        public WinNT.HANDLE hEvent;
    }

    @Structure.FieldOrder({"dwLength", "lpSecurityDescriptor", "bInheritHandle"})
    public static class SECURITY_ATTRIBUTES extends Structure {
        public boolean bInheritHandle;
        public WinDef.DWORD dwLength = new WinDef.DWORD(size());
        public Pointer lpSecurityDescriptor;
    }

    @Structure.FieldOrder({"cb", "lpReserved", "lpDesktop", "lpTitle", "dwX", "dwY", "dwXSize", "dwYSize", "dwXCountChars", "dwYCountChars", "dwFillAttribute", "dwFlags", "wShowWindow", "cbReserved2", "lpReserved2", "hStdInput", "hStdOutput", "hStdError"})
    public static class STARTUPINFO extends Structure {
        public WinDef.DWORD cb;
        public WinDef.WORD cbReserved2;
        public WinDef.DWORD dwFillAttribute;
        public int dwFlags;
        public WinDef.DWORD dwX;
        public WinDef.DWORD dwXCountChars;
        public WinDef.DWORD dwXSize;
        public WinDef.DWORD dwY;
        public WinDef.DWORD dwYCountChars;
        public WinDef.DWORD dwYSize;
        public WinNT.HANDLE hStdError;
        public WinNT.HANDLE hStdInput;
        public WinNT.HANDLE hStdOutput;
        public String lpDesktop;
        public String lpReserved;
        public ByteByReference lpReserved2;
        public String lpTitle;
        public WinDef.WORD wShowWindow;

        public STARTUPINFO() {
            super(W32APITypeMapper.DEFAULT);
            this.cb = new WinDef.DWORD(size());
        }
    }

    @Structure.FieldOrder({"processorArchitecture", "dwPageSize", "lpMinimumApplicationAddress", "lpMaximumApplicationAddress", "dwActiveProcessorMask", "dwNumberOfProcessors", "dwProcessorType", "dwAllocationGranularity", "wProcessorLevel", "wProcessorRevision"})
    public static class SYSTEM_INFO extends Structure {
        public BaseTSD.DWORD_PTR dwActiveProcessorMask;
        public WinDef.DWORD dwAllocationGranularity;
        public WinDef.DWORD dwNumberOfProcessors;
        public WinDef.DWORD dwPageSize;
        public WinDef.DWORD dwProcessorType;
        public Pointer lpMaximumApplicationAddress;
        public Pointer lpMinimumApplicationAddress;
        public UNION processorArchitecture;
        public WinDef.WORD wProcessorLevel;
        public WinDef.WORD wProcessorRevision;

        @Structure.FieldOrder({"wProcessorArchitecture", "wReserved"})
        public static class PI extends Structure {
            public WinDef.WORD wProcessorArchitecture;
            public WinDef.WORD wReserved;

            public static class ByReference extends PI implements Structure.ByReference {
            }
        }

        public static class UNION extends Union {
            public WinDef.DWORD dwOemID;
            public PI pi;

            public static class ByReference extends UNION implements Structure.ByReference {
            }

            @Override // com.sun.jna.Structure
            public void read() {
                setType("dwOemID");
                super.read();
                setType(Constants.ELEMNAME_PI_OLD_STRING);
                super.read();
            }
        }
    }

    public interface THREAD_START_ROUTINE extends StdCallLibrary.StdCallCallback {
        WinDef.DWORD apply(WinDef.LPVOID lpvoid);
    }

    @Structure.FieldOrder({"Bias", "StandardName", "StandardDate", "StandardBias", "DaylightName", "DaylightDate", "DaylightBias"})
    public static class TIME_ZONE_INFORMATION extends Structure {
        public WinDef.LONG Bias;
        public WinDef.LONG DaylightBias;
        public SYSTEMTIME DaylightDate;
        public String DaylightName;
        public WinDef.LONG StandardBias;
        public SYSTEMTIME StandardDate;
        public String StandardName;

        public TIME_ZONE_INFORMATION() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    static {
        INVALID_HANDLE_VALUE = new WinNT.HANDLE(Pointer.createConstant(Native.POINTER_SIZE == 8 ? -1L : 4294967295L));
        MAX_COMPUTERNAME_LENGTH = Platform.isMac() ? 15 : 31;
    }

    @Structure.FieldOrder({"dwLowDateTime", "dwHighDateTime"})
    public static class FILETIME extends Structure {
        private static final long EPOCH_DIFF = 11644473600000L;
        public int dwHighDateTime;
        public int dwLowDateTime;

        public FILETIME(Date date) {
            long jDateToFileTime = dateToFileTime(date);
            this.dwHighDateTime = (int) ((jDateToFileTime >> 32) & 4294967295L);
            this.dwLowDateTime = (int) (jDateToFileTime & 4294967295L);
        }

        public static long dateToFileTime(Date date) {
            return (date.getTime() + EPOCH_DIFF) * 10000;
        }

        public static Date filetimeToDate(int i, int i2) {
            return new Date((((((long) i2) & 4294967295L) | (((long) i) << 32)) / 10000) - EPOCH_DIFF);
        }

        public WinDef.DWORDLONG toDWordLong() {
            return new WinDef.DWORDLONG((((long) this.dwHighDateTime) << 32) | (((long) this.dwLowDateTime) & 4294967295L));
        }

        public Date toDate() {
            return filetimeToDate(this.dwHighDateTime, this.dwLowDateTime);
        }

        @Override // com.sun.jna.Structure
        public String toString() {
            return super.toString() + ": " + toDate().toString();
        }

        public long toTime() {
            return toDate().getTime();
        }

        public static class ByReference extends FILETIME implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public FILETIME(WinNT.LARGE_INTEGER large_integer) {
            this.dwHighDateTime = large_integer.getHigh().intValue();
            this.dwLowDateTime = large_integer.getLow().intValue();
        }

        public FILETIME() {
        }

        public FILETIME(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"FileAttributes", "ReparseTag"})
    public static class FILE_ATTRIBUTE_TAG_INFO extends Structure {
        public int FileAttributes;
        public int ReparseTag;

        public FILE_ATTRIBUTE_TAG_INFO(int i, int i2) {
            this.FileAttributes = i;
            this.ReparseTag = i2;
            write();
        }

        public static int sizeOf() {
            return Native.getNativeSize(FILE_ATTRIBUTE_TAG_INFO.class, null);
        }

        public static class ByReference extends FILE_ATTRIBUTE_TAG_INFO implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public FILE_ATTRIBUTE_TAG_INFO(Pointer pointer) {
            super(pointer);
            read();
        }

        public FILE_ATTRIBUTE_TAG_INFO() {
        }
    }

    @Structure.FieldOrder({"CreationTime", "LastAccessTime", "LastWriteTime", "ChangeTime", "FileAttributes"})
    public static class FILE_BASIC_INFO extends Structure {
        public WinNT.LARGE_INTEGER ChangeTime;
        public WinNT.LARGE_INTEGER CreationTime;
        public int FileAttributes;
        public WinNT.LARGE_INTEGER LastAccessTime;
        public WinNT.LARGE_INTEGER LastWriteTime;

        public FILE_BASIC_INFO(Pointer pointer) {
            super(pointer);
            read();
            this.CreationTime = new WinNT.LARGE_INTEGER(this.CreationTime.getValue());
            this.LastAccessTime = new WinNT.LARGE_INTEGER(this.LastAccessTime.getValue());
            this.LastWriteTime = new WinNT.LARGE_INTEGER(this.LastWriteTime.getValue());
            this.ChangeTime = new WinNT.LARGE_INTEGER(this.ChangeTime.getValue());
        }

        public static int sizeOf() {
            return Native.getNativeSize(FILE_BASIC_INFO.class, null);
        }

        public static class ByReference extends FILE_BASIC_INFO implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public FILE_BASIC_INFO() {
        }

        public FILE_BASIC_INFO(FILETIME filetime, FILETIME filetime2, FILETIME filetime3, FILETIME filetime4, int i) {
            this.CreationTime = new WinNT.LARGE_INTEGER(filetime.toTime());
            this.LastAccessTime = new WinNT.LARGE_INTEGER(filetime2.toTime());
            this.LastWriteTime = new WinNT.LARGE_INTEGER(filetime3.toTime());
            this.ChangeTime = new WinNT.LARGE_INTEGER(filetime4.toTime());
            this.FileAttributes = i;
            write();
        }

        public FILE_BASIC_INFO(WinNT.LARGE_INTEGER large_integer, WinNT.LARGE_INTEGER large_integer2, WinNT.LARGE_INTEGER large_integer3, WinNT.LARGE_INTEGER large_integer4, int i) {
            this.CreationTime = large_integer;
            this.LastAccessTime = large_integer2;
            this.LastWriteTime = large_integer3;
            this.ChangeTime = large_integer4;
            this.FileAttributes = i;
            write();
        }
    }

    @Structure.FieldOrder({"CompressedFileSize", "CompressionFormat", "CompressionUnitShift", "ChunkShift", "ClusterShift", "Reserved"})
    public static class FILE_COMPRESSION_INFO extends Structure {
        public byte ChunkShift;
        public byte ClusterShift;
        public WinNT.LARGE_INTEGER CompressedFileSize;
        public short CompressionFormat;
        public byte CompressionUnitShift;
        public byte[] Reserved;

        public FILE_COMPRESSION_INFO(WinNT.LARGE_INTEGER large_integer, short s, byte b, byte b2, byte b3) {
            this.CompressedFileSize = large_integer;
            this.CompressionFormat = s;
            this.CompressionUnitShift = b;
            this.ChunkShift = b2;
            this.ClusterShift = b3;
            this.Reserved = new byte[3];
            write();
        }

        public static int sizeOf() {
            return Native.getNativeSize(FILE_COMPRESSION_INFO.class, null);
        }

        public static class ByReference extends FILE_COMPRESSION_INFO implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public FILE_COMPRESSION_INFO(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
            this.Reserved = new byte[3];
            read();
        }

        public FILE_COMPRESSION_INFO() {
            super(W32APITypeMapper.DEFAULT);
            this.Reserved = new byte[3];
        }
    }

    @Structure.FieldOrder({"DeleteFile"})
    public static class FILE_DISPOSITION_INFO extends Structure {
        public boolean DeleteFile;

        public FILE_DISPOSITION_INFO(boolean z) {
            this.DeleteFile = z;
            write();
        }

        public static int sizeOf() {
            return Native.getNativeSize(FILE_DISPOSITION_INFO.class, null);
        }

        public static class ByReference extends FILE_DISPOSITION_INFO implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public FILE_DISPOSITION_INFO(Pointer pointer) {
            super(pointer);
            read();
        }

        public FILE_DISPOSITION_INFO() {
        }
    }

    @Structure.FieldOrder({"VolumeSerialNumber", "FileId"})
    public static class FILE_ID_INFO extends Structure {
        public FILE_ID_128 FileId;
        public long VolumeSerialNumber;

        public FILE_ID_INFO(long j, FILE_ID_128 file_id_128) {
            this.VolumeSerialNumber = j;
            this.FileId = file_id_128;
            write();
        }

        public static int sizeOf() {
            return Native.getNativeSize(FILE_ID_INFO.class, null);
        }

        public static class ByReference extends FILE_ID_INFO implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public FILE_ID_INFO(Pointer pointer) {
            super(pointer);
            read();
        }

        @Structure.FieldOrder({"Identifier"})
        public static class FILE_ID_128 extends Structure {
            public WinDef.BYTE[] Identifier;

            public FILE_ID_128(Pointer pointer) {
                super(pointer);
                this.Identifier = new WinDef.BYTE[16];
                read();
            }

            public FILE_ID_128() {
                this.Identifier = new WinDef.BYTE[16];
            }

            public FILE_ID_128(WinDef.BYTE[] byteArr) {
                this.Identifier = byteArr;
                write();
            }
        }

        public FILE_ID_INFO() {
        }
    }

    @Structure.FieldOrder({"AllocationSize", "EndOfFile", "NumberOfLinks", "DeletePending", "Directory"})
    public static class FILE_STANDARD_INFO extends Structure {
        public WinNT.LARGE_INTEGER AllocationSize;
        public boolean DeletePending;
        public boolean Directory;
        public WinNT.LARGE_INTEGER EndOfFile;
        public int NumberOfLinks;

        public FILE_STANDARD_INFO(WinNT.LARGE_INTEGER large_integer, WinNT.LARGE_INTEGER large_integer2, int i, boolean z, boolean z2) {
            this.AllocationSize = large_integer;
            this.EndOfFile = large_integer2;
            this.NumberOfLinks = i;
            this.DeletePending = z;
            this.Directory = z2;
            write();
        }

        public static int sizeOf() {
            return Native.getNativeSize(FILE_STANDARD_INFO.class, null);
        }

        public static class ByReference extends FILE_STANDARD_INFO implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public FILE_STANDARD_INFO(Pointer pointer) {
            super(pointer);
            read();
        }

        public FILE_STANDARD_INFO() {
        }
    }

    @Structure.FieldOrder({"hProcess", "hThread", "dwProcessId", "dwThreadId"})
    public static class PROCESS_INFORMATION extends Structure {
        public WinDef.DWORD dwProcessId;
        public WinDef.DWORD dwThreadId;
        public WinNT.HANDLE hProcess;
        public WinNT.HANDLE hThread;

        public PROCESS_INFORMATION(Pointer pointer) {
            super(pointer);
            read();
        }

        public static class ByReference extends PROCESS_INFORMATION implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public PROCESS_INFORMATION() {
        }
    }

    @Structure.FieldOrder({"dwFileAttributes", "ftCreationTime", "ftLastAccessTime", "ftLastWriteTime", "nFileSizeHigh", "nFileSizeLow", "dwReserved0", "dwReserved1", "cFileName", "cAlternateFileName"})
    public static class WIN32_FIND_DATA extends Structure {
        public char[] cAlternateFileName;
        public char[] cFileName;
        public int dwFileAttributes;
        public int dwReserved0;
        public int dwReserved1;
        public FILETIME ftCreationTime;
        public FILETIME ftLastAccessTime;
        public FILETIME ftLastWriteTime;
        public int nFileSizeHigh;
        public int nFileSizeLow;

        public WIN32_FIND_DATA(int i, FILETIME filetime, FILETIME filetime2, FILETIME filetime3, int i2, int i3, int i4, int i5, char[] cArr, char[] cArr2) {
            this.dwFileAttributes = i;
            this.ftCreationTime = filetime;
            this.ftLastAccessTime = filetime2;
            this.ftLastWriteTime = filetime3;
            this.nFileSizeHigh = i2;
            this.nFileSizeLow = i3;
            this.dwReserved0 = i4;
            this.cFileName = cArr;
            this.cAlternateFileName = cArr2;
            write();
        }

        public static int sizeOf() {
            return Native.getNativeSize(WIN32_FIND_DATA.class, null);
        }

        public String getAlternateFileName() {
            return Native.toString(this.cAlternateFileName);
        }

        public String getFileName() {
            return Native.toString(this.cFileName);
        }

        public static class ByReference extends WIN32_FIND_DATA implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public WIN32_FIND_DATA(Pointer pointer) {
            super(pointer, 0, W32APITypeMapper.DEFAULT);
            this.cFileName = new char[260];
            this.cAlternateFileName = new char[14];
            read();
        }

        public WIN32_FIND_DATA() {
            super(W32APITypeMapper.DEFAULT);
            this.cFileName = new char[260];
            this.cAlternateFileName = new char[14];
        }
    }

    @Structure.FieldOrder({"wYear", "wMonth", "wDayOfWeek", "wDay", "wHour", "wMinute", "wSecond", "wMilliseconds"})
    public static class SYSTEMTIME extends Structure {
        public short wDay;
        public short wDayOfWeek;
        public short wHour;
        public short wMilliseconds;
        public short wMinute;
        public short wMonth;
        public short wSecond;
        public short wYear;

        public SYSTEMTIME(long j) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            fromCalendar(calendar);
        }

        public void fromCalendar(Calendar calendar) {
            this.wYear = (short) calendar.get(1);
            this.wMonth = (short) (calendar.get(2) + 1);
            this.wDay = (short) calendar.get(5);
            this.wHour = (short) calendar.get(11);
            this.wMinute = (short) calendar.get(12);
            this.wSecond = (short) calendar.get(13);
            this.wMilliseconds = (short) calendar.get(14);
            this.wDayOfWeek = (short) (calendar.get(7) - 1);
        }

        public Calendar toCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, this.wYear);
            calendar.set(2, this.wMonth - 1);
            calendar.set(5, this.wDay);
            calendar.set(11, this.wHour);
            calendar.set(12, this.wMinute);
            calendar.set(13, this.wSecond);
            calendar.set(14, this.wMilliseconds);
            return calendar;
        }

        @Override // com.sun.jna.Structure
        public String toString() {
            return (this.wYear == 0 && this.wMonth == 0 && this.wDay == 0 && this.wHour == 0 && this.wMinute == 0 && this.wSecond == 0 && this.wMilliseconds == 0) ? super.toString() : DateFormat.getDateTimeInstance().format(toCalendar().getTime());
        }

        public SYSTEMTIME(Date date) {
            this(date.getTime());
        }

        public SYSTEMTIME() {
        }

        public SYSTEMTIME(Calendar calendar) {
            fromCalendar(calendar);
        }
    }
}
