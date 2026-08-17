package com.sun.jna.platform.win32;

import com.intellij.psi.PsiKeyword;
import com.sun.jna.FromNativeContext;
import com.sun.jna.IntegerType;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WinNT extends BaseTSD, WinBase, WinDef, WinError {
    public static final byte ACCESS_ALLOWED_ACE_TYPE = 0;
    public static final byte ACCESS_ALLOWED_CALLBACK_ACE_TYPE = 9;
    public static final byte ACCESS_ALLOWED_CALLBACK_OBJECT_ACE_TYPE = 11;
    public static final byte ACCESS_ALLOWED_COMPOUND_ACE_TYPE = 4;
    public static final byte ACCESS_ALLOWED_OBJECT_ACE_TYPE = 5;
    public static final byte ACCESS_DENIED_ACE_TYPE = 1;
    public static final byte ACCESS_DENIED_CALLBACK_ACE_TYPE = 10;
    public static final byte ACCESS_DENIED_CALLBACK_OBJECT_ACE_TYPE = 12;
    public static final byte ACCESS_DENIED_OBJECT_ACE_TYPE = 6;
    public static final int ACCESS_SYSTEM_SECURITY = 16777216;
    public static final int ACL_REVISION = 2;
    public static final int ACL_REVISION1 = 1;
    public static final int ACL_REVISION2 = 2;
    public static final int ACL_REVISION3 = 3;
    public static final int ACL_REVISION4 = 4;
    public static final int ACL_REVISION_DS = 4;
    public static final byte BOOLEAN_FALSE = 0;
    public static final byte BOOLEAN_TRUE = 1;
    public static final byte CACHE_FULLY_ASSOCIATIVE = -1;
    public static final int COMPRESSION_ENGINE_HIBER = 512;
    public static final int COMPRESSION_ENGINE_MAXIMUM = 256;
    public static final int COMPRESSION_ENGINE_STANDARD = 0;
    public static final int COMPRESSION_FORMAT_DEFAULT = 1;
    public static final int COMPRESSION_FORMAT_LZNT1 = 2;
    public static final int COMPRESSION_FORMAT_NONE = 0;
    public static final int COMPRESSION_FORMAT_XPRESS = 3;
    public static final int COMPRESSION_FORMAT_XPRESS_HUFF = 4;
    public static final byte CONTAINER_INHERIT_ACE = 2;
    public static final int CREATE_ALWAYS = 2;
    public static final int CREATE_NEW = 1;
    public static final int DACL_SECURITY_INFORMATION = 4;
    public static final int DDD_EXACT_MATCH_ON_REMOVE = 4;
    public static final int DDD_NO_BROADCAST_SYSTEM = 8;
    public static final int DDD_RAW_TARGET_PATH = 1;
    public static final int DDD_REMOVE_DEFINITION = 2;
    public static final int DELETE = 65536;
    public static final int EVENTLOG_AUDIT_FAILURE = 16;
    public static final int EVENTLOG_AUDIT_SUCCESS = 8;
    public static final int EVENTLOG_BACKWARDS_READ = 8;
    public static final int EVENTLOG_ERROR_TYPE = 1;
    public static final int EVENTLOG_FORWARDS_READ = 4;
    public static final int EVENTLOG_INFORMATION_TYPE = 4;
    public static final int EVENTLOG_SEEK_READ = 2;
    public static final int EVENTLOG_SEQUENTIAL_READ = 1;
    public static final int EVENTLOG_SUCCESS = 0;
    public static final int EVENTLOG_WARNING_TYPE = 2;
    public static final int EVENT_ALL_ACCESS = 2031619;
    public static final int EVENT_MODIFY_STATE = 2;
    public static final int FILE_ACTION_ADDED = 1;
    public static final int FILE_ACTION_MODIFIED = 3;
    public static final int FILE_ACTION_REMOVED = 2;
    public static final int FILE_ACTION_RENAMED_NEW_NAME = 5;
    public static final int FILE_ACTION_RENAMED_OLD_NAME = 4;
    public static final int FILE_ADD_FILE = 2;
    public static final int FILE_ADD_SUBDIRECTORY = 4;
    public static final int FILE_ALL_ACCESS = 2032127;
    public static final int FILE_APPEND_DATA = 4;
    public static final int FILE_ATTRIBUTE_ARCHIVE = 32;
    public static final int FILE_ATTRIBUTE_COMPRESSED = 2048;
    public static final int FILE_ATTRIBUTE_DEVICE = 64;
    public static final int FILE_ATTRIBUTE_DIRECTORY = 16;
    public static final int FILE_ATTRIBUTE_ENCRYPTED = 16384;
    public static final int FILE_ATTRIBUTE_HIDDEN = 2;
    public static final int FILE_ATTRIBUTE_NORMAL = 128;
    public static final int FILE_ATTRIBUTE_NOT_CONTENT_INDEXED = 8192;
    public static final int FILE_ATTRIBUTE_OFFLINE = 4096;
    public static final int FILE_ATTRIBUTE_READONLY = 1;
    public static final int FILE_ATTRIBUTE_REPARSE_POINT = 1024;
    public static final int FILE_ATTRIBUTE_SPARSE_FILE = 512;
    public static final int FILE_ATTRIBUTE_SYSTEM = 4;
    public static final int FILE_ATTRIBUTE_TEMPORARY = 256;
    public static final int FILE_ATTRIBUTE_VIRTUAL = 65536;
    public static final int FILE_CASE_PRESERVED_NAMES = 2;
    public static final int FILE_CASE_SENSITIVE_SEARCH = 1;
    public static final int FILE_CREATE_PIPE_INSTANCE = 4;
    public static final int FILE_DELETE_CHILD = 64;
    public static final int FILE_EXECUTE = 32;
    public static final int FILE_FILE_COMPRESSION = 16;
    public static final int FILE_FLAG_BACKUP_SEMANTICS = 33554432;
    public static final int FILE_FLAG_DELETE_ON_CLOSE = 67108864;
    public static final int FILE_FLAG_NO_BUFFERING = 536870912;
    public static final int FILE_FLAG_OPEN_NO_RECALL = 1048576;
    public static final int FILE_FLAG_OPEN_REPARSE_POINT = 2097152;
    public static final int FILE_FLAG_OVERLAPPED = 1073741824;
    public static final int FILE_FLAG_POSIX_SEMANTICS = 16777216;
    public static final int FILE_FLAG_RANDOM_ACCESS = 268435456;
    public static final int FILE_FLAG_SEQUENTIAL_SCAN = 134217728;
    public static final int FILE_FLAG_WRITE_THROUGH = Integer.MIN_VALUE;
    public static final int FILE_GENERIC_EXECUTE = 1179808;
    public static final int FILE_GENERIC_READ = 1179785;
    public static final int FILE_GENERIC_WRITE = 1179926;
    public static final int FILE_LIST_DIRECTORY = 1;
    public static final int FILE_NAMED_STREAMS = 262144;
    public static final int FILE_NOTIFY_CHANGE_ATTRIBUTES = 4;
    public static final int FILE_NOTIFY_CHANGE_CREATION = 64;
    public static final int FILE_NOTIFY_CHANGE_DIR_NAME = 2;
    public static final int FILE_NOTIFY_CHANGE_FILE_NAME = 1;
    public static final int FILE_NOTIFY_CHANGE_LAST_ACCESS = 32;
    public static final int FILE_NOTIFY_CHANGE_LAST_WRITE = 16;
    public static final int FILE_NOTIFY_CHANGE_NAME = 3;
    public static final int FILE_NOTIFY_CHANGE_SECURITY = 256;
    public static final int FILE_NOTIFY_CHANGE_SIZE = 8;
    public static final int FILE_PERSISTENT_ACLS = 8;
    public static final int FILE_READ_ATTRIBUTES = 128;
    public static final int FILE_READ_DATA = 1;
    public static final int FILE_READ_EA = 8;
    public static final int FILE_READ_ONLY_VOLUME = 524288;
    public static final int FILE_SEQUENTIAL_WRITE_ONCE = 1048576;
    public static final int FILE_SHARE_DELETE = 4;
    public static final int FILE_SHARE_READ = 1;
    public static final int FILE_SHARE_WRITE = 2;
    public static final int FILE_SUPPORTS_ENCRYPTION = 131072;
    public static final int FILE_SUPPORTS_EXTENDED_ATTRIBUTES = 8388608;
    public static final int FILE_SUPPORTS_HARD_LINKS = 4194304;
    public static final int FILE_SUPPORTS_OBJECT_IDS = 65536;
    public static final int FILE_SUPPORTS_OPEN_BY_FILE_ID = 16777216;
    public static final int FILE_SUPPORTS_REMOTE_STORAGE = 256;
    public static final int FILE_SUPPORTS_REPARSE_POINTS = 128;
    public static final int FILE_SUPPORTS_SPARSE_FILES = 64;
    public static final int FILE_SUPPORTS_TRANSACTIONS = 2097152;
    public static final int FILE_SUPPORTS_USN_JOURNAL = 33554432;
    public static final int FILE_TRAVERSE = 32;
    public static final int FILE_TYPE_CHAR = 2;
    public static final int FILE_TYPE_DISK = 1;
    public static final int FILE_TYPE_PIPE = 3;
    public static final int FILE_TYPE_REMOTE = 32768;
    public static final int FILE_TYPE_UNKNOWN = 0;
    public static final int FILE_UNICODE_ON_DISK = 4;
    public static final int FILE_VOLUME_IS_COMPRESSED = 32768;
    public static final int FILE_VOLUME_QUOTAS = 32;
    public static final int FILE_WRITE_ATTRIBUTES = 256;
    public static final int FILE_WRITE_DATA = 2;
    public static final int FILE_WRITE_EA = 16;
    public static final int GENERIC_ALL = 268435456;
    public static final int GENERIC_EXECUTE = 536870912;
    public static final int GENERIC_READ = Integer.MIN_VALUE;
    public static final int GENERIC_WRITE = 1073741824;
    public static final int GROUP_SECURITY_INFORMATION = 2;
    public static final byte INHERITED_ACE = 16;
    public static final byte INHERIT_ONLY_ACE = 8;
    public static final int IO_REPARSE_TAG_CSV = -2147483639;
    public static final int IO_REPARSE_TAG_DFS = -2147483638;
    public static final int IO_REPARSE_TAG_DFSR = -2147483630;
    public static final int IO_REPARSE_TAG_HSM = -1073741820;
    public static final int IO_REPARSE_TAG_HSM2 = -2147483642;
    public static final int IO_REPARSE_TAG_MOUNT_POINT = -1610612733;
    public static final int IO_REPARSE_TAG_SIS = -2147483641;
    public static final int IO_REPARSE_TAG_SYMLINK = -1610612724;
    public static final int IO_REPARSE_TAG_WIM = -2147483640;
    public static final int KEY_ALL_ACCESS = 983103;
    public static final int KEY_CREATE_LINK = 32;
    public static final int KEY_CREATE_SUB_KEY = 4;
    public static final int KEY_ENUMERATE_SUB_KEYS = 8;
    public static final int KEY_EXECUTE = 131097;
    public static final int KEY_NOTIFY = 16;
    public static final int KEY_QUERY_VALUE = 1;
    public static final int KEY_READ = 131097;
    public static final int KEY_SET_VALUE = 2;
    public static final int KEY_WOW64_32KEY = 512;
    public static final int KEY_WOW64_64KEY = 256;
    public static final int KEY_WOW64_RES = 768;
    public static final int KEY_WRITE = 131078;
    public static final int LABEL_SECURITY_INFORMATION = 16;
    public static final int LANG_AFRIKAANS = 54;
    public static final int LANG_ALBANIAN = 28;
    public static final int LANG_ARABIC = 1;
    public static final int LANG_ARMENIAN = 43;
    public static final int LANG_ASSAMESE = 77;
    public static final int LANG_AZERI = 44;
    public static final int LANG_BASQUE = 45;
    public static final int LANG_BELARUSIAN = 35;
    public static final int LANG_BENGALI = 69;
    public static final int LANG_BULGARIAN = 2;
    public static final int LANG_CATALAN = 3;
    public static final int LANG_CHINESE = 4;
    public static final int LANG_CROATIAN = 26;
    public static final int LANG_CZECH = 5;
    public static final int LANG_DANISH = 6;
    public static final int LANG_DIVEHI = 101;
    public static final int LANG_DUTCH = 19;
    public static final int LANG_ENGLISH = 9;
    public static final int LANG_ESTONIAN = 37;
    public static final int LANG_FAEROESE = 56;
    public static final int LANG_FARSI = 41;
    public static final int LANG_FINNISH = 11;
    public static final int LANG_FRENCH = 12;
    public static final int LANG_GALICIAN = 86;
    public static final int LANG_GEORGIAN = 55;
    public static final int LANG_GERMAN = 7;
    public static final int LANG_GREEK = 8;
    public static final int LANG_GUJARATI = 71;
    public static final int LANG_HEBREW = 13;
    public static final int LANG_HINDI = 57;
    public static final int LANG_HUNGARIAN = 14;
    public static final int LANG_ICELANDIC = 15;
    public static final int LANG_INDONESIAN = 33;
    public static final int LANG_INVARIANT = 127;
    public static final int LANG_ITALIAN = 16;
    public static final int LANG_JAPANESE = 17;
    public static final int LANG_KANNADA = 75;
    public static final int LANG_KASHMIRI = 96;
    public static final int LANG_KAZAK = 63;
    public static final int LANG_KONKANI = 87;
    public static final int LANG_KOREAN = 18;
    public static final int LANG_KYRGYZ = 64;
    public static final int LANG_LATVIAN = 38;
    public static final int LANG_LITHUANIAN = 39;
    public static final int LANG_MACEDONIAN = 47;
    public static final int LANG_MALAY = 62;
    public static final int LANG_MALAYALAM = 76;
    public static final int LANG_MANIPURI = 88;
    public static final int LANG_MARATHI = 78;
    public static final int LANG_MONGOLIAN = 80;
    public static final int LANG_NEPALI = 97;
    public static final int LANG_NEUTRAL = 0;
    public static final int LANG_NORWEGIAN = 20;
    public static final int LANG_ORIYA = 72;
    public static final int LANG_POLISH = 21;
    public static final int LANG_PORTUGUESE = 22;
    public static final int LANG_PUNJABI = 70;
    public static final int LANG_ROMANIAN = 24;
    public static final int LANG_RUSSIAN = 25;
    public static final int LANG_SANSKRIT = 79;
    public static final int LANG_SERBIAN = 26;
    public static final int LANG_SINDHI = 89;
    public static final int LANG_SLOVAK = 27;
    public static final int LANG_SLOVENIAN = 36;
    public static final int LANG_SPANISH = 10;
    public static final int LANG_SWAHILI = 65;
    public static final int LANG_SWEDISH = 29;
    public static final int LANG_SYRIAC = 90;
    public static final int LANG_SYSTEM_DEFAULT;
    public static final int LANG_TAMIL = 73;
    public static final int LANG_TATAR = 68;
    public static final int LANG_TELUGU = 74;
    public static final int LANG_THAI = 30;
    public static final int LANG_TURKISH = 31;
    public static final int LANG_UKRAINIAN = 34;
    public static final int LANG_URDU = 32;
    public static final int LANG_USER_DEFAULT;
    public static final int LANG_UZBEK = 67;
    public static final int LANG_VIETNAMESE = 42;
    public static final WinDef.LCID LOCALE_INVARIANT;
    public static final WinDef.LCID LOCALE_NEUTRAL;
    public static final WinDef.LCID LOCALE_SYSTEM_DEFAULT;
    public static final WinDef.LCID LOCALE_USER_DEFAULT;
    public static final int LTP_PC_SMT = 1;
    public static final int MAXBYTE = 255;
    public static final int MAXCHAR = 127;
    public static final int MAXDWORD = -1;
    public static final int MAXLONG = Integer.MAX_VALUE;
    public static final int MAXSHORT = 32767;
    public static final int MAXWORD = 65535;
    public static final int MAX_ACL_REVISION = 4;
    public static final int MEM_COALESCE_PLACEHOLDERS = 1;
    public static final int MEM_COMMIT = 4096;
    public static final int MEM_DECOMMIT = 16384;
    public static final int MEM_FREE = 65536;
    public static final int MEM_IMAGE = 16777216;
    public static final int MEM_LARGE_PAGES = 536870912;
    public static final int MEM_MAPPED = 262144;
    public static final int MEM_PHYSICAL = 4194304;
    public static final int MEM_PRESERVE_PLACEHOLDER = 2;
    public static final int MEM_PRIVATE = 131072;
    public static final int MEM_RELEASE = 32768;
    public static final int MEM_RESERVE = 8192;
    public static final int MEM_RESET = 524288;
    public static final int MEM_RESET_UNDO = 16777216;
    public static final int MEM_TOP_DOWN = 1048576;
    public static final int MINCHAR = 128;
    public static final int MINLONG = Integer.MIN_VALUE;
    public static final int MINSHORT = 32768;
    public static final int MIN_ACL_REVISION = 2;
    public static final int MUTANT_ALL_ACCESS = 2031617;
    public static final int MUTANT_QUERY_STATE = 1;
    public static final int NLS_VALID_LOCALE_MASK = 1048575;
    public static final byte NO_PROPAGATE_INHERIT_ACE = 4;
    public static final int NUM_DISCHARGE_POLICIES = 4;
    public static final byte OBJECT_INHERIT_ACE = 1;
    public static final int OPEN_ALWAYS = 4;
    public static final int OPEN_EXISTING = 3;
    public static final int OWNER_SECURITY_INFORMATION = 1;
    public static final int PAGE_EXECUTE = 16;
    public static final int PAGE_EXECUTE_READ = 32;
    public static final int PAGE_EXECUTE_READWRITE = 64;
    public static final int PAGE_GUARD = 256;
    public static final int PAGE_NOACCESS = 1;
    public static final int PAGE_READONLY = 2;
    public static final int PAGE_READWRITE = 4;
    public static final int PAGE_WRITECOPY = 8;
    public static final int PROCESS_ALL_ACCESS = 2039803;
    public static final int PROCESS_CREATE_PROCESS = 128;
    public static final int PROCESS_CREATE_THREAD = 2;
    public static final int PROCESS_DUP_HANDLE = 64;
    public static final int PROCESS_NAME_NATIVE = 1;
    public static final int PROCESS_QUERY_INFORMATION = 1024;
    public static final int PROCESS_QUERY_LIMITED_INFORMATION = 4096;
    public static final int PROCESS_SET_INFORMATION = 512;
    public static final int PROCESS_SET_QUOTA = 256;
    public static final int PROCESS_SUSPEND_RESUME = 2048;
    public static final int PROCESS_SYNCHRONIZE = 1048576;
    public static final int PROCESS_TERMINATE = 1;
    public static final int PROCESS_VM_OPERATION = 8;
    public static final int PROCESS_VM_READ = 16;
    public static final int PROCESS_VM_WRITE = 32;
    public static final int PROTECTED_DACL_SECURITY_INFORMATION = Integer.MIN_VALUE;
    public static final int PROTECTED_SACL_SECURITY_INFORMATION = 1073741824;
    public static final int READ_CONTROL = 131072;
    public static final int REG_APP_HIVE = 16;
    public static final int REG_BINARY = 3;
    public static final int REG_CREATED_NEW_KEY = 1;
    public static final int REG_DWORD = 4;
    public static final int REG_DWORD_BIG_ENDIAN = 5;
    public static final int REG_DWORD_LITTLE_ENDIAN = 4;
    public static final int REG_EXPAND_SZ = 2;
    public static final int REG_FORCE_RESTORE = 8;
    public static final int REG_FORCE_UNLOAD = 1;
    public static final int REG_FULL_RESOURCE_DESCRIPTOR = 9;
    public static final int REG_HIVE_EXACT_FILE_GROWTH = 128;
    public static final int REG_HIVE_NO_RM = 256;
    public static final int REG_HIVE_SINGLE_LOG = 512;
    public static final int REG_LATEST_FORMAT = 2;
    public static final int REG_LEGAL_CHANGE_FILTER = 268435471;
    public static final int REG_LEGAL_OPTION = 15;
    public static final int REG_LINK = 6;
    public static final int REG_MULTI_SZ = 7;
    public static final int REG_NONE = 0;
    public static final int REG_NOTIFY_CHANGE_ATTRIBUTES = 2;
    public static final int REG_NOTIFY_CHANGE_LAST_SET = 4;
    public static final int REG_NOTIFY_CHANGE_NAME = 1;
    public static final int REG_NOTIFY_CHANGE_SECURITY = 8;
    public static final int REG_NOTIFY_THREAD_AGNOSTIC = 268435456;
    public static final int REG_NO_COMPRESSION = 4;
    public static final int REG_NO_LAZY_FLUSH = 4;
    public static final int REG_OPENED_EXISTING_KEY = 2;
    public static final int REG_OPTION_BACKUP_RESTORE = 4;
    public static final int REG_OPTION_CREATE_LINK = 2;
    public static final int REG_OPTION_NON_VOLATILE = 0;
    public static final int REG_OPTION_OPEN_LINK = 8;
    public static final int REG_OPTION_RESERVED = 0;
    public static final int REG_OPTION_VOLATILE = 1;
    public static final int REG_PROCESS_PRIVATE = 32;
    public static final int REG_QWORD = 11;
    public static final int REG_QWORD_LITTLE_ENDIAN = 11;
    public static final int REG_REFRESH_HIVE = 2;
    public static final int REG_RESOURCE_LIST = 8;
    public static final int REG_RESOURCE_REQUIREMENTS_LIST = 10;
    public static final int REG_STANDARD_FORMAT = 1;
    public static final int REG_START_JOURNAL = 64;
    public static final int REG_SZ = 1;
    public static final int REG_WHOLE_HIVE_VOLATILE = 1;
    public static final int SACL_SECURITY_INFORMATION = 8;
    public static final int SECTION_EXTEND_SIZE = 16;
    public static final int SECTION_MAP_EXECUTE = 8;
    public static final int SECTION_MAP_READ = 4;
    public static final int SECTION_MAP_WRITE = 2;
    public static final int SECTION_QUERY = 1;
    public static final int SECURITY_DESCRIPTOR_REVISION = 1;
    public static final byte SECURITY_DYNAMIC_TRACKING = 1;
    public static final int SECURITY_MAX_SID_SIZE = 68;
    public static final byte SECURITY_STATIC_TRACKING = 0;
    public static final int SERVICE_ADAPTER = 4;
    public static final int SERVICE_AUTO_START = 2;
    public static final int SERVICE_BOOT_START = 0;
    public static final int SERVICE_DEMAND_START = 3;
    public static final int SERVICE_DISABLED = 4;
    public static final int SERVICE_DRIVER = 11;
    public static final int SERVICE_ERROR_CRITICAL = 3;
    public static final int SERVICE_ERROR_IGNORE = 0;
    public static final int SERVICE_ERROR_NORMAL = 1;
    public static final int SERVICE_ERROR_SEVERE = 2;
    public static final int SERVICE_FILE_SYSTEM_DRIVER = 2;
    public static final int SERVICE_INTERACTIVE_PROCESS = 256;
    public static final int SERVICE_KERNEL_DRIVER = 1;
    public static final int SERVICE_RECOGNIZER_DRIVER = 8;
    public static final int SERVICE_SYSTEM_START = 1;
    public static final int SERVICE_TYPE_ALL = 319;
    public static final int SERVICE_WIN32 = 48;
    public static final int SERVICE_WIN32_OWN_PROCESS = 16;
    public static final int SERVICE_WIN32_SHARE_PROCESS = 32;
    public static final String SE_ASSIGNPRIMARYTOKEN_NAME = "SeAssignPrimaryTokenPrivilege";
    public static final String SE_AUDIT_NAME = "SeAuditPrivilege";
    public static final String SE_BACKUP_NAME = "SeBackupPrivilege";
    public static final String SE_CHANGE_NOTIFY_NAME = "SeChangeNotifyPrivilege";
    public static final String SE_CREATE_GLOBAL_NAME = "SeCreateGlobalPrivilege";
    public static final String SE_CREATE_PAGEFILE_NAME = "SeCreatePagefilePrivilege";
    public static final String SE_CREATE_PERMANENT_NAME = "SeCreatePermanentPrivilege";
    public static final String SE_CREATE_TOKEN_NAME = "SeCreateTokenPrivilege";
    public static final int SE_DACL_AUTO_INHERITED = 1024;
    public static final int SE_DACL_AUTO_INHERIT_REQ = 256;
    public static final int SE_DACL_DEFAULTED = 8;
    public static final int SE_DACL_PRESENT = 4;
    public static final int SE_DACL_PROTECTED = 4096;
    public static final String SE_DEBUG_NAME = "SeDebugPrivilege";
    public static final String SE_ENABLE_DELEGATION_NAME = "SeEnableDelegationPrivilege";
    public static final int SE_GROUP_DEFAULTED = 2;
    public static final String SE_IMPERSONATE_NAME = "SeImpersonatePrivilege";
    public static final String SE_INCREASE_QUOTA_NAME = "SeIncreaseQuotaPrivilege";
    public static final String SE_INC_BASE_PRIORITY_NAME = "SeIncreaseBasePriorityPrivilege";
    public static final String SE_LOAD_DRIVER_NAME = "SeLoadDriverPrivilege";
    public static final String SE_LOCK_MEMORY_NAME = "SeLockMemoryPrivilege";
    public static final String SE_MACHINE_ACCOUNT_NAME = "SeMachineAccountPrivilege";
    public static final String SE_MANAGE_VOLUME_NAME = "SeManageVolumePrivilege";
    public static final int SE_OWNER_DEFAULTED = 1;
    public static final int SE_PRIVILEGE_ENABLED = 2;
    public static final int SE_PRIVILEGE_ENABLED_BY_DEFAULT = 1;
    public static final int SE_PRIVILEGE_REMOVED = 4;
    public static final int SE_PRIVILEGE_USED_FOR_ACCESS = Integer.MIN_VALUE;
    public static final String SE_PROF_SINGLE_PROCESS_NAME = "SeProfileSingleProcessPrivilege";
    public static final String SE_REMOTE_SHUTDOWN_NAME = "SeRemoteShutdownPrivilege";
    public static final String SE_RESTORE_NAME = "SeRestorePrivilege";
    public static final int SE_RM_CONTROL_VALID = 16384;
    public static final int SE_SACL_AUTO_INHERITED = 2048;
    public static final int SE_SACL_AUTO_INHERIT_REQ = 512;
    public static final int SE_SACL_DEFAULTED = 32;
    public static final int SE_SACL_PRESENT = 16;
    public static final int SE_SACL_PROTECTED = 8192;
    public static final String SE_SECURITY_NAME = "SeSecurityPrivilege";
    public static final int SE_SELF_RELATIVE = 32768;
    public static final String SE_SHUTDOWN_NAME = "SeShutdownPrivilege";
    public static final String SE_SYNC_AGENT_NAME = "SeSyncAgentPrivilege";
    public static final String SE_SYSTEMTIME_NAME = "SeSystemtimePrivilege";
    public static final String SE_SYSTEM_ENVIRONMENT_NAME = "SeSystemEnvironmentPrivilege";
    public static final String SE_SYSTEM_PROFILE_NAME = "SeSystemProfilePrivilege";
    public static final String SE_TAKE_OWNERSHIP_NAME = "SeTakeOwnershipPrivilege";
    public static final String SE_TCB_NAME = "SeTcbPrivilege";
    public static final String SE_UNDOCK_NAME = "SeUndockPrivilege";
    public static final String SE_UNSOLICITED_INPUT_NAME = "SeUnsolicitedInputPrivilege";
    public static final int SID_MAX_SUB_AUTHORITIES = 15;
    public static final int SID_RECOMMENDED_SUB_AUTHORITIES = 1;
    public static final int SID_REVISION = 1;
    public static final int SORT_CHINESE_BIG5 = 0;
    public static final int SORT_CHINESE_BOPOMOFO = 3;
    public static final int SORT_CHINESE_PRC = 2;
    public static final int SORT_CHINESE_PRCP = 0;
    public static final int SORT_CHINESE_UNICODE = 1;
    public static final int SORT_DEFAULT = 0;
    public static final int SORT_GEORGIAN_MODERN = 1;
    public static final int SORT_GEORGIAN_TRADITIONAL = 0;
    public static final int SORT_GERMAN_PHONE_BOOK = 1;
    public static final int SORT_HUNGARIAN_DEFAULT = 0;
    public static final int SORT_HUNGARIAN_TECHNICAL = 1;
    public static final int SORT_JAPANESE_UNICODE = 1;
    public static final int SORT_JAPANESE_XJIS = 0;
    public static final int SORT_KOREAN_KSC = 0;
    public static final int SORT_KOREAN_UNICODE = 1;
    public static final int SPECIFIC_RIGHTS_ALL = 65535;
    public static final int STANDARD_RIGHTS_ALL = 2031616;
    public static final int STANDARD_RIGHTS_EXECUTE = 131072;
    public static final int STANDARD_RIGHTS_READ = 131072;
    public static final int STANDARD_RIGHTS_REQUIRED = 983040;
    public static final int STANDARD_RIGHTS_WRITE = 131072;
    public static final int STATUS_PENDING = 259;
    public static final int SUBLANG_ARABIC_ALGERIA = 5;
    public static final int SUBLANG_ARABIC_BAHRAIN = 15;
    public static final int SUBLANG_ARABIC_EGYPT = 3;
    public static final int SUBLANG_ARABIC_IRAQ = 2;
    public static final int SUBLANG_ARABIC_JORDAN = 11;
    public static final int SUBLANG_ARABIC_KUWAIT = 13;
    public static final int SUBLANG_ARABIC_LEBANON = 12;
    public static final int SUBLANG_ARABIC_LIBYA = 4;
    public static final int SUBLANG_ARABIC_MOROCCO = 6;
    public static final int SUBLANG_ARABIC_OMAN = 8;
    public static final int SUBLANG_ARABIC_QATAR = 16;
    public static final int SUBLANG_ARABIC_SAUDI_ARABIA = 1;
    public static final int SUBLANG_ARABIC_SYRIA = 10;
    public static final int SUBLANG_ARABIC_TUNISIA = 7;
    public static final int SUBLANG_ARABIC_UAE = 14;
    public static final int SUBLANG_ARABIC_YEMEN = 9;
    public static final int SUBLANG_AZERI_CYRILLIC = 2;
    public static final int SUBLANG_AZERI_LATIN = 1;
    public static final int SUBLANG_CHINESE_HONGKONG = 3;
    public static final int SUBLANG_CHINESE_MACAU = 5;
    public static final int SUBLANG_CHINESE_SIMPLIFIED = 2;
    public static final int SUBLANG_CHINESE_SINGAPORE = 4;
    public static final int SUBLANG_CHINESE_TRADITIONAL = 1;
    public static final int SUBLANG_DEFAULT = 1;
    public static final int SUBLANG_DUTCH = 1;
    public static final int SUBLANG_DUTCH_BELGIAN = 2;
    public static final int SUBLANG_ENGLISH_AUS = 3;
    public static final int SUBLANG_ENGLISH_BELIZE = 10;
    public static final int SUBLANG_ENGLISH_CAN = 4;
    public static final int SUBLANG_ENGLISH_CARIBBEAN = 9;
    public static final int SUBLANG_ENGLISH_EIRE = 6;
    public static final int SUBLANG_ENGLISH_JAMAICA = 8;
    public static final int SUBLANG_ENGLISH_NZ = 5;
    public static final int SUBLANG_ENGLISH_PHILIPPINES = 13;
    public static final int SUBLANG_ENGLISH_SOUTH_AFRICA = 7;
    public static final int SUBLANG_ENGLISH_TRINIDAD = 11;
    public static final int SUBLANG_ENGLISH_UK = 2;
    public static final int SUBLANG_ENGLISH_US = 1;
    public static final int SUBLANG_ENGLISH_ZIMBABWE = 12;
    public static final int SUBLANG_FRENCH = 1;
    public static final int SUBLANG_FRENCH_BELGIAN = 2;
    public static final int SUBLANG_FRENCH_CANADIAN = 3;
    public static final int SUBLANG_FRENCH_LUXEMBOURG = 5;
    public static final int SUBLANG_FRENCH_MONACO = 6;
    public static final int SUBLANG_FRENCH_SWISS = 4;
    public static final int SUBLANG_GERMAN = 1;
    public static final int SUBLANG_GERMAN_AUSTRIAN = 3;
    public static final int SUBLANG_GERMAN_LIECHTENSTEIN = 5;
    public static final int SUBLANG_GERMAN_LUXEMBOURG = 4;
    public static final int SUBLANG_GERMAN_SWISS = 2;
    public static final int SUBLANG_ITALIAN = 1;
    public static final int SUBLANG_ITALIAN_SWISS = 2;
    public static final int SUBLANG_KASHMIRI_INDIA = 2;
    public static final int SUBLANG_KASHMIRI_SASIA = 2;
    public static final int SUBLANG_KOREAN = 1;
    public static final int SUBLANG_LITHUANIAN = 1;
    public static final int SUBLANG_MALAY_BRUNEI_DARUSSALAM = 2;
    public static final int SUBLANG_MALAY_MALAYSIA = 1;
    public static final int SUBLANG_NEPALI_INDIA = 2;
    public static final int SUBLANG_NEUTRAL = 0;
    public static final int SUBLANG_NORWEGIAN_BOKMAL = 1;
    public static final int SUBLANG_NORWEGIAN_NYNORSK = 2;
    public static final int SUBLANG_PORTUGUESE = 2;
    public static final int SUBLANG_PORTUGUESE_BRAZILIAN = 1;
    public static final int SUBLANG_SERBIAN_CYRILLIC = 3;
    public static final int SUBLANG_SERBIAN_LATIN = 2;
    public static final int SUBLANG_SPANISH = 1;
    public static final int SUBLANG_SPANISH_ARGENTINA = 11;
    public static final int SUBLANG_SPANISH_BOLIVIA = 16;
    public static final int SUBLANG_SPANISH_CHILE = 13;
    public static final int SUBLANG_SPANISH_COLOMBIA = 9;
    public static final int SUBLANG_SPANISH_COSTA_RICA = 5;
    public static final int SUBLANG_SPANISH_DOMINICAN_REPUBLIC = 7;
    public static final int SUBLANG_SPANISH_ECUADOR = 12;
    public static final int SUBLANG_SPANISH_EL_SALVADOR = 17;
    public static final int SUBLANG_SPANISH_GUATEMALA = 4;
    public static final int SUBLANG_SPANISH_HONDURAS = 18;
    public static final int SUBLANG_SPANISH_MEXICAN = 2;
    public static final int SUBLANG_SPANISH_MODERN = 3;
    public static final int SUBLANG_SPANISH_NICARAGUA = 19;
    public static final int SUBLANG_SPANISH_PANAMA = 6;
    public static final int SUBLANG_SPANISH_PARAGUAY = 15;
    public static final int SUBLANG_SPANISH_PERU = 10;
    public static final int SUBLANG_SPANISH_PUERTO_RICO = 20;
    public static final int SUBLANG_SPANISH_URUGUAY = 14;
    public static final int SUBLANG_SPANISH_VENEZUELA = 8;
    public static final int SUBLANG_SWEDISH = 1;
    public static final int SUBLANG_SWEDISH_FINLAND = 2;
    public static final int SUBLANG_SYS_DEFAULT = 2;
    public static final int SUBLANG_URDU_INDIA = 2;
    public static final int SUBLANG_URDU_PAKISTAN = 1;
    public static final int SUBLANG_UZBEK_CYRILLIC = 2;
    public static final int SUBLANG_UZBEK_LATIN = 1;
    public static final int SYNCHRONIZE = 1048576;
    public static final byte SYSTEM_ALARM_ACE_TYPE = 3;
    public static final byte SYSTEM_ALARM_CALLBACK_ACE_TYPE = 14;
    public static final byte SYSTEM_ALARM_CALLBACK_OBJECT_ACE_TYPE = 16;
    public static final byte SYSTEM_ALARM_OBJECT_ACE_TYPE = 8;
    public static final byte SYSTEM_AUDIT_ACE_TYPE = 2;
    public static final byte SYSTEM_AUDIT_CALLBACK_ACE_TYPE = 13;
    public static final byte SYSTEM_AUDIT_CALLBACK_OBJECT_ACE_TYPE = 15;
    public static final byte SYSTEM_AUDIT_OBJECT_ACE_TYPE = 7;
    public static final byte SYSTEM_MANDATORY_LABEL_ACE_TYPE = 17;
    public static final int THREAD_ALL_ACCESS = 2032639;
    public static final int THREAD_DIRECT_IMPERSONATION = 512;
    public static final int THREAD_GET_CONTEXT = 8;
    public static final int THREAD_IMPERSONATE = 256;
    public static final int THREAD_QUERY_INFORMATION = 64;
    public static final int THREAD_QUERY_LIMITED_INFORMATION = 2048;
    public static final int THREAD_SET_CONTEXT = 16;
    public static final int THREAD_SET_INFORMATION = 32;
    public static final int THREAD_SET_LIMITED_INFORMATION = 1024;
    public static final int THREAD_SET_THREAD_TOKEN = 128;
    public static final int THREAD_SUSPEND_RESUME = 2;
    public static final int THREAD_TERMINATE = 1;
    public static final int TOKEN_ADJUST_DEFAULT = 128;
    public static final int TOKEN_ADJUST_GROUPS = 64;
    public static final int TOKEN_ADJUST_PRIVILEGES = 32;
    public static final int TOKEN_ADJUST_SESSIONID = 256;
    public static final int TOKEN_ALL_ACCESS = 983551;
    public static final int TOKEN_ALL_ACCESS_P = 983295;
    public static final int TOKEN_ASSIGN_PRIMARY = 1;
    public static final int TOKEN_DUPLICATE = 2;
    public static final int TOKEN_EXECUTE = 131072;
    public static final int TOKEN_IMPERSONATE = 4;
    public static final int TOKEN_QUERY = 8;
    public static final int TOKEN_QUERY_SOURCE = 16;
    public static final int TOKEN_READ = 131080;
    public static final int TOKEN_WRITE = 131296;
    public static final int TRUNCATE_EXISTING = 5;
    public static final int UNPROTECTED_DACL_SECURITY_INFORMATION = 536870912;
    public static final int UNPROTECTED_SACL_SECURITY_INFORMATION = 268435456;
    public static final byte VALID_INHERIT_FLAGS = 31;
    public static final int VER_AND = 6;
    public static final int VER_BUILDNUMBER = 4;
    public static final int VER_CONDITION_MASK = 7;
    public static final int VER_EQUAL = 1;
    public static final int VER_GREATER = 2;
    public static final int VER_GREATER_EQUAL = 3;
    public static final int VER_LESS = 4;
    public static final int VER_LESS_EQUAL = 5;
    public static final int VER_MAJORVERSION = 2;
    public static final int VER_MINORVERSION = 1;
    public static final int VER_NT_DOMAIN_CONTROLLER = 2;
    public static final int VER_NT_SERVER = 3;
    public static final int VER_NT_WORKSTATION = 1;
    public static final int VER_NUM_BITS_PER_CONDITION_MASK = 3;
    public static final int VER_OR = 7;
    public static final int VER_PLATFORMID = 8;
    public static final int VER_PLATFORM_WIN32_NT = 2;
    public static final int VER_PLATFORM_WIN32_WINDOWS = 1;
    public static final int VER_PLATFORM_WIN32s = 0;
    public static final int VER_PRODUCT_TYPE = 128;
    public static final int VER_SERVICEPACKMAJOR = 32;
    public static final int VER_SERVICEPACKMINOR = 16;
    public static final int VER_SUITENAME = 64;
    public static final short WIN32_WINNT_LONGHORN = 1536;
    public static final short WIN32_WINNT_NT4 = 1024;
    public static final short WIN32_WINNT_VISTA = 1536;
    public static final short WIN32_WINNT_WIN10 = 2560;
    public static final short WIN32_WINNT_WIN2K = 1280;
    public static final short WIN32_WINNT_WIN6 = 1536;
    public static final short WIN32_WINNT_WIN7 = 1537;
    public static final short WIN32_WINNT_WIN8 = 1538;
    public static final short WIN32_WINNT_WINBLUE = 1539;
    public static final short WIN32_WINNT_WINTHRESHOLD = 2560;
    public static final short WIN32_WINNT_WINXP = 1281;
    public static final short WIN32_WINNT_WS03 = 1282;
    public static final short WIN32_WINNT_WS08 = 1536;
    public static final int WRITE_DAC = 262144;
    public static final int WRITE_OWNER = 524288;

    @Structure.FieldOrder({"Granularity", "Capacity"})
    public static class BATTERY_REPORTING_SCALE extends Structure {
        public int Capacity;
        public int Granularity;
    }

    @Structure.FieldOrder({Constants.ATTRNAME_LEVEL, "associativity", "lineSize", "size", "type"})
    public static class CACHE_DESCRIPTOR extends Structure {
        public WinDef.BYTE associativity;
        public WinDef.BYTE level;
        public WinDef.WORD lineSize;
        public WinDef.DWORD size;
        public int type;
    }

    @Structure.FieldOrder({"genericRead", "genericWrite", "genericExecute", "genericAll"})
    public static class GENERIC_MAPPING extends Structure {
        public WinDef.DWORD genericAll;
        public WinDef.DWORD genericExecute;
        public WinDef.DWORD genericRead;
        public WinDef.DWORD genericWrite;

        public static class ByReference extends GENERIC_MAPPING implements Structure.ByReference {
        }
    }

    public interface LOGICAL_PROCESSOR_RELATIONSHIP {
        public static final int RelationAll = 65535;
        public static final int RelationCache = 2;
        public static final int RelationGroup = 4;
        public static final int RelationNumaNode = 1;
        public static final int RelationNumaNodeEx = 6;
        public static final int RelationProcessorCore = 0;
        public static final int RelationProcessorDie = 5;
        public static final int RelationProcessorModule = 7;
        public static final int RelationProcessorPackage = 3;
    }

    @Structure.FieldOrder({"LowPart", "HighPart"})
    public static class LUID extends Structure {
        public int HighPart;
        public int LowPart;
    }

    public static final class LocaleMacros {
        public static final int LANGIDFROMLCID(WinDef.LCID lcid) {
            return lcid.intValue() & 65535;
        }

        public static final int MAKELANGID(int i, int i2) {
            return (i & 65535) | (i2 << 10);
        }

        public static final WinDef.LCID MAKELCID(int i, int i2) {
            return new WinDef.LCID(_MAKELCID(i, i2));
        }

        public static final WinDef.LCID MAKESORTLCID(int i, int i2, int i3) {
            return new WinDef.LCID(_MAKELCID(i, i2) | (i3 << 20));
        }

        public static final int PRIMARYLANGID(int i) {
            return i & 1023;
        }

        public static final int SORTIDFROMLCID(WinDef.LCID lcid) {
            return (lcid.intValue() >>> 16) & 15;
        }

        public static final int SORTVERSIONFROMLCID(WinDef.LCID lcid) {
            return (lcid.intValue() >>> 20) & 15;
        }

        public static final int SUBLANGID(int i) {
            return (i & 65535) >>> 10;
        }

        private static final int _MAKELCID(int i, int i2) {
            return i | (i2 << 16);
        }
    }

    @Structure.FieldOrder({"baseAddress", "allocationBase", "allocationProtect", "regionSize", "state", "protect", "type"})
    public static class MEMORY_BASIC_INFORMATION extends Structure {
        public Pointer allocationBase;
        public WinDef.DWORD allocationProtect;
        public Pointer baseAddress;
        public WinDef.DWORD protect;
        public BaseTSD.SIZE_T regionSize;
        public WinDef.DWORD state;
        public WinDef.DWORD type;
    }

    public interface OVERLAPPED_COMPLETION_ROUTINE extends StdCallLibrary.StdCallCallback {
        void callback(int i, int i2, WinBase.OVERLAPPED overlapped);
    }

    public interface POWER_ACTION {
        public static final int PowerActionDisplayOff = 8;
        public static final int PowerActionHibernate = 3;
        public static final int PowerActionNone = 0;
        public static final int PowerActionReserved = 1;
        public static final int PowerActionShutdown = 4;
        public static final int PowerActionShutdownOff = 6;
        public static final int PowerActionShutdownReset = 5;
        public static final int PowerActionSleep = 2;
        public static final int PowerActionWarmEject = 7;
    }

    @Structure.FieldOrder({"Action", "Flags", "EventCode"})
    public static class POWER_ACTION_POLICY extends Structure {
        public int Action;
        public int EventCode;
        public int Flags;
    }

    public static abstract class PROCESSOR_CACHE_TYPE {
        public static int CacheData = 2;
        public static int CacheInstruction = 1;
        public static int CacheTrace = 3;
        public static int CacheUnified;
    }

    public static abstract class SECURITY_IMPERSONATION_LEVEL {
        public static final int SecurityAnonymous = 0;
        public static final int SecurityDelegation = 3;
        public static final int SecurityIdentification = 1;
        public static final int SecurityImpersonation = 2;
    }

    @Structure.FieldOrder({"Length", "ImpersonationLevel", "ContextTrackingMode", "EffectiveOnly"})
    public static class SECURITY_QUALITY_OF_SERVICE extends Structure {
        public byte ContextTrackingMode;
        public byte EffectiveOnly;
        public int ImpersonationLevel;
        public int Length;

        @Override // com.sun.jna.Structure
        public void write() {
            this.Length = size();
            super.write();
        }
    }

    public static abstract class SID_NAME_USE {
        public static final int SidTypeAlias = 4;
        public static final int SidTypeComputer = 9;
        public static final int SidTypeDeletedAccount = 6;
        public static final int SidTypeDomain = 3;
        public static final int SidTypeGroup = 2;
        public static final int SidTypeInvalid = 7;
        public static final int SidTypeLabel = 10;
        public static final int SidTypeUnknown = 8;
        public static final int SidTypeUser = 1;
        public static final int SidTypeWellKnownGroup = 5;
    }

    @Structure.FieldOrder({"Enable", "Spare", "BatteryLevel", "PowerPolicy", "MinSystemState"})
    public static class SYSTEM_POWER_LEVEL extends Structure {
        public int BatteryLevel;
        public byte Enable;
        public int MinSystemState;
        public POWER_ACTION_POLICY PowerPolicy;
        public byte[] Spare = new byte[3];
    }

    public interface SYSTEM_POWER_STATE {
        public static final int PowerSystemHibernate = 5;
        public static final int PowerSystemMaximum = 7;
        public static final int PowerSystemShutdown = 6;
        public static final int PowerSystemSleeping1 = 2;
        public static final int PowerSystemSleeping2 = 3;
        public static final int PowerSystemSleeping3 = 4;
        public static final int PowerSystemUnspecified = 0;
        public static final int PowerSystemWorking = 1;
    }

    public static abstract class TOKEN_INFORMATION_CLASS {
        public static final int TokenAccessInformation = 22;
        public static final int TokenAuditPolicy = 16;
        public static final int TokenDefaultDacl = 6;
        public static final int TokenElevation = 20;
        public static final int TokenElevationType = 18;
        public static final int TokenGroups = 2;
        public static final int TokenGroupsAndPrivileges = 13;
        public static final int TokenHasRestrictions = 21;
        public static final int TokenImpersonationLevel = 9;
        public static final int TokenIntegrityLevel = 25;
        public static final int TokenLinkedToken = 19;
        public static final int TokenLogonSid = 28;
        public static final int TokenMandatoryPolicy = 27;
        public static final int TokenOrigin = 17;
        public static final int TokenOwner = 4;
        public static final int TokenPrimaryGroup = 5;
        public static final int TokenPrivileges = 3;
        public static final int TokenRestrictedSids = 11;
        public static final int TokenSandBoxInert = 15;
        public static final int TokenSessionId = 12;
        public static final int TokenSessionReference = 14;
        public static final int TokenSource = 7;
        public static final int TokenStatistics = 10;
        public static final int TokenType = 8;
        public static final int TokenUIAccess = 26;
        public static final int TokenUser = 1;
        public static final int TokenVirtualizationAllowed = 23;
        public static final int TokenVirtualizationEnabled = 24;
    }

    public static abstract class TOKEN_TYPE {
        public static final int TokenImpersonation = 2;
        public static final int TokenPrimary = 1;
    }

    public static abstract class WELL_KNOWN_SID_TYPE {
        public static final int WinAccountAdministratorSid = 38;
        public static final int WinAccountCertAdminsSid = 46;
        public static final int WinAccountComputersSid = 44;
        public static final int WinAccountControllersSid = 45;
        public static final int WinAccountDomainAdminsSid = 41;
        public static final int WinAccountDomainGuestsSid = 43;
        public static final int WinAccountDomainUsersSid = 42;
        public static final int WinAccountEnterpriseAdminsSid = 48;
        public static final int WinAccountGuestSid = 39;
        public static final int WinAccountKrbtgtSid = 40;
        public static final int WinAccountPolicyAdminsSid = 49;
        public static final int WinAccountRasAndIasServersSid = 50;
        public static final int WinAccountReadonlyControllersSid = 75;
        public static final int WinAccountSchemaAdminsSid = 47;
        public static final int WinAnonymousSid = 13;
        public static final int WinAuthenticatedUserSid = 17;
        public static final int WinBatchSid = 10;
        public static final int WinBuiltinAccountOperatorsSid = 30;
        public static final int WinBuiltinAdministratorsSid = 26;
        public static final int WinBuiltinAuthorizationAccessSid = 59;
        public static final int WinBuiltinBackupOperatorsSid = 33;
        public static final int WinBuiltinCryptoOperatorsSid = 64;
        public static final int WinBuiltinDCOMUsersSid = 61;
        public static final int WinBuiltinDomainSid = 25;
        public static final int WinBuiltinEventLogReadersGroup = 76;
        public static final int WinBuiltinGuestsSid = 28;
        public static final int WinBuiltinIUsersSid = 62;
        public static final int WinBuiltinIncomingForestTrustBuildersSid = 56;
        public static final int WinBuiltinNetworkConfigurationOperatorsSid = 37;
        public static final int WinBuiltinPerfLoggingUsersSid = 58;
        public static final int WinBuiltinPerfMonitoringUsersSid = 57;
        public static final int WinBuiltinPowerUsersSid = 29;
        public static final int WinBuiltinPreWindows2000CompatibleAccessSid = 35;
        public static final int WinBuiltinPrintOperatorsSid = 32;
        public static final int WinBuiltinRemoteDesktopUsersSid = 36;
        public static final int WinBuiltinReplicatorSid = 34;
        public static final int WinBuiltinSystemOperatorsSid = 31;
        public static final int WinBuiltinTerminalServerLicenseServersSid = 60;
        public static final int WinBuiltinUsersSid = 27;
        public static final int WinCacheablePrincipalsGroupSid = 72;
        public static final int WinCreatorGroupServerSid = 6;
        public static final int WinCreatorGroupSid = 4;
        public static final int WinCreatorOwnerRightsSid = 71;
        public static final int WinCreatorOwnerServerSid = 5;
        public static final int WinCreatorOwnerSid = 3;
        public static final int WinDialupSid = 8;
        public static final int WinDigestAuthenticationSid = 52;
        public static final int WinEnterpriseControllersSid = 15;
        public static final int WinEnterpriseReadonlyControllersSid = 74;
        public static final int WinHighLabelSid = 68;
        public static final int WinIUserSid = 63;
        public static final int WinInteractiveSid = 11;
        public static final int WinLocalServiceSid = 23;
        public static final int WinLocalSid = 2;
        public static final int WinLocalSystemSid = 22;
        public static final int WinLogonIdsSid = 21;
        public static final int WinLowLabelSid = 66;
        public static final int WinMediumLabelSid = 67;
        public static final int WinNTLMAuthenticationSid = 51;
        public static final int WinNetworkServiceSid = 24;
        public static final int WinNetworkSid = 9;
        public static final int WinNonCacheablePrincipalsGroupSid = 73;
        public static final int WinNtAuthoritySid = 7;
        public static final int WinNullSid = 0;
        public static final int WinOtherOrganizationSid = 55;
        public static final int WinProxySid = 14;
        public static final int WinRemoteLogonIdSid = 20;
        public static final int WinRestrictedCodeSid = 18;
        public static final int WinSChannelAuthenticationSid = 53;
        public static final int WinSelfSid = 16;
        public static final int WinServiceSid = 12;
        public static final int WinSystemLabelSid = 69;
        public static final int WinTerminalServerSid = 19;
        public static final int WinThisOrganizationSid = 54;
        public static final int WinUntrustedLabelSid = 65;
        public static final int WinWorldSid = 1;
        public static final int WinWriteRestrictedCodeSid = 70;
    }

    static {
        int iMAKELANGID = LocaleMacros.MAKELANGID(0, 2);
        LANG_SYSTEM_DEFAULT = iMAKELANGID;
        int iMAKELANGID2 = LocaleMacros.MAKELANGID(0, 1);
        LANG_USER_DEFAULT = iMAKELANGID2;
        LOCALE_SYSTEM_DEFAULT = LocaleMacros.MAKELCID(iMAKELANGID, 0);
        LOCALE_USER_DEFAULT = LocaleMacros.MAKELCID(iMAKELANGID2, 0);
        LOCALE_NEUTRAL = LocaleMacros.MAKELCID(LocaleMacros.MAKELANGID(0, 0), 0);
        LOCALE_INVARIANT = LocaleMacros.MAKELCID(LocaleMacros.MAKELANGID(127, 0), 0);
    }

    @Structure.FieldOrder({"ReadOperationCount", "WriteOperationCount", "OtherOperationCount", "ReadTransferCount", "WriteTransferCount", "OtherTransferCount"})
    public static class IO_COUNTERS extends Structure {
        public long OtherOperationCount;
        public long OtherTransferCount;
        public long ReadOperationCount;
        public long ReadTransferCount;
        public long WriteOperationCount;
        public long WriteTransferCount;

        public IO_COUNTERS() {
        }

        public IO_COUNTERS(Pointer pointer) {
            super(pointer);
        }
    }

    @Structure.FieldOrder({"Sid", "Attributes"})
    public static class SID_AND_ATTRIBUTES extends Structure {
        public int Attributes;
        public PSID.ByReference Sid;

        public SID_AND_ATTRIBUTES() {
        }

        public SID_AND_ATTRIBUTES(Pointer pointer) {
            super(pointer);
        }
    }

    @Structure.FieldOrder({"relationship", "size"})
    public static abstract class SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX extends Structure {
        public int relationship;
        public int size;

        public SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX() {
        }

        public static SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX fromPointer(Pointer pointer) {
            SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX processor_relationship;
            switch (pointer.getInt(0L)) {
                case 0:
                case 3:
                case 5:
                case 7:
                    processor_relationship = new PROCESSOR_RELATIONSHIP(pointer);
                    break;
                case 1:
                case 6:
                    processor_relationship = new NUMA_NODE_RELATIONSHIP(pointer);
                    break;
                case 2:
                    processor_relationship = new CACHE_RELATIONSHIP(pointer);
                    break;
                case 4:
                    processor_relationship = new GROUP_RELATIONSHIP(pointer);
                    break;
                default:
                    processor_relationship = new UNKNOWN_RELATIONSHIP(pointer);
                    break;
            }
            processor_relationship.read();
            return processor_relationship;
        }

        public SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX(Pointer pointer) {
            super(pointer);
        }
    }

    @Structure.FieldOrder({})
    public static class UNKNOWN_RELATIONSHIP extends SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX {
        public UNKNOWN_RELATIONSHIP() {
        }

        public UNKNOWN_RELATIONSHIP(Pointer pointer) {
            super(pointer);
        }
    }

    public static class ACCESS_ALLOWED_ACE extends ACCESS_ACEStructure {
        public ACCESS_ALLOWED_ACE(int i, byte b, PSID psid) {
            super(i, (byte) 0, b, psid);
        }

        public ACCESS_ALLOWED_ACE(Pointer pointer) {
            super(pointer);
        }

        public ACCESS_ALLOWED_ACE() {
        }
    }

    public static class ACCESS_DENIED_ACE extends ACCESS_ACEStructure {
        public ACCESS_DENIED_ACE(int i, byte b, PSID psid) {
            super(i, (byte) 1, b, psid);
        }

        public ACCESS_DENIED_ACE(Pointer pointer) {
            super(pointer);
        }

        public ACCESS_DENIED_ACE() {
        }
    }

    public static class HRESULT extends NativeLong {
        public HRESULT(int i) {
            super(i);
        }

        public HRESULT() {
        }
    }

    @Structure.FieldOrder({"Length", "Reserved", "RecordNumber", "TimeGenerated", "TimeWritten", "EventID", "EventType", "NumStrings", "EventCategory", "ReservedFlags", "ClosingRecordNumber", "StringOffset", "UserSidLength", "UserSidOffset", "DataLength", "DataOffset"})
    public static class EVENTLOGRECORD extends Structure {
        public WinDef.DWORD ClosingRecordNumber;
        public WinDef.DWORD DataLength;
        public WinDef.DWORD DataOffset;
        public WinDef.WORD EventCategory;
        public WinDef.DWORD EventID;
        public WinDef.WORD EventType;
        public WinDef.DWORD Length;
        public WinDef.WORD NumStrings;
        public WinDef.DWORD RecordNumber;
        public WinDef.DWORD Reserved;
        public WinDef.WORD ReservedFlags;
        public WinDef.DWORD StringOffset;
        public WinDef.DWORD TimeGenerated;
        public WinDef.DWORD TimeWritten;
        public WinDef.DWORD UserSidLength;
        public WinDef.DWORD UserSidOffset;

        public EVENTLOGRECORD(Pointer pointer) {
            super(pointer);
            read();
        }

        public EVENTLOGRECORD() {
        }
    }

    @Structure.FieldOrder({"u"})
    public static class LARGE_INTEGER extends Structure implements Comparable<LARGE_INTEGER> {
        public UNION u;

        public static class ByReference extends LARGE_INTEGER implements Structure.ByReference {
            @Override // com.sun.jna.platform.win32.WinNT.LARGE_INTEGER, java.lang.Comparable
            public /* bridge */ /* synthetic */ int compareTo(LARGE_INTEGER large_integer) {
                return super.compareTo(large_integer);
            }
        }

        public LARGE_INTEGER(long j) {
            this.u = new UNION(j);
        }

        public static int compare(LARGE_INTEGER large_integer, LARGE_INTEGER large_integer2) {
            if (large_integer == large_integer2) {
                return 0;
            }
            if (large_integer == null) {
                return 1;
            }
            if (large_integer2 == null) {
                return -1;
            }
            return IntegerType.compare(large_integer.getValue(), large_integer2.getValue());
        }

        public WinDef.DWORD getHigh() {
            return this.u.lh.HighPart;
        }

        public WinDef.DWORD getLow() {
            return this.u.lh.LowPart;
        }

        public long getValue() {
            return this.u.value;
        }

        @Override // com.sun.jna.Structure
        public String toString() {
            return this.u == null ? PsiKeyword.NULL : Long.toString(getValue());
        }

        @Override // java.lang.Comparable
        public int compareTo(LARGE_INTEGER large_integer) {
            return compare(this, large_integer);
        }

        public LARGE_INTEGER() {
        }

        public static class UNION extends Union {
            public LowHigh lh;
            public long value;

            public UNION(long j) {
                this.value = j;
                this.lh = new LowHigh(j);
            }

            public long longValue() {
                return this.value;
            }

            @Override // com.sun.jna.Structure
            public void read() {
                readField("lh");
                readField("value");
            }

            @Override // com.sun.jna.Structure
            public String toString() {
                return Long.toString(longValue());
            }

            public UNION() {
            }
        }

        @Structure.FieldOrder({"LowPart", "HighPart"})
        public static class LowHigh extends Structure {
            public WinDef.DWORD HighPart;
            public WinDef.DWORD LowPart;

            public LowHigh(long j) {
                this(new WinDef.DWORD(j & 4294967295L), new WinDef.DWORD((j >> 32) & 4294967295L));
            }

            public long longValue() {
                return (this.LowPart.longValue() & 4294967295L) | ((this.HighPart.longValue() << 32) & (-4294967296L));
            }

            @Override // com.sun.jna.Structure
            public String toString() {
                return (this.LowPart == null || this.HighPart == null) ? PsiKeyword.NULL : Long.toString(longValue());
            }

            public LowHigh() {
            }

            public LowHigh(WinDef.DWORD dword, WinDef.DWORD dword2) {
                this.LowPart = dword;
                this.HighPart = dword2;
            }
        }

        public static int compare(LARGE_INTEGER large_integer, long j) {
            if (large_integer == null) {
                return 1;
            }
            return IntegerType.compare(large_integer.getValue(), j);
        }
    }

    @Structure.FieldOrder({"Number", "MaxMhz", "CurrentMhz", "MhzLimit", "MaxIdleState", "CurrentIdleState"})
    public static class PROCESSOR_POWER_INFORMATION extends Structure {
        public int CurrentIdleState;
        public int CurrentMhz;
        public int MaxIdleState;
        public int MaxMhz;
        public int MhzLimit;
        public int Number;

        public PROCESSOR_POWER_INFORMATION(Pointer pointer) {
            super(pointer);
            read();
        }

        public PROCESSOR_POWER_INFORMATION() {
        }
    }

    @Structure.FieldOrder({"processorMask", "relationship", "payload"})
    public static class SYSTEM_LOGICAL_PROCESSOR_INFORMATION extends Structure {
        public AnonymousUnionPayload payload;
        public BaseTSD.ULONG_PTR processorMask;
        public int relationship;

        @Structure.FieldOrder({"nodeNumber"})
        public static class AnonymousStructNumaNode extends Structure {
            public WinDef.DWORD nodeNumber;
        }

        @Structure.FieldOrder({"flags"})
        public static class AnonymousStructProcessorCore extends Structure {
            public WinDef.BYTE flags;
        }

        public static class AnonymousUnionPayload extends Union {
            public CACHE_DESCRIPTOR cache;
            public AnonymousStructNumaNode numaNode;
            public AnonymousStructProcessorCore processorCore;
            public WinDef.ULONGLONG[] reserved = new WinDef.ULONGLONG[2];
        }

        public SYSTEM_LOGICAL_PROCESSOR_INFORMATION(Pointer pointer) {
            super(pointer);
            read();
        }

        public SYSTEM_LOGICAL_PROCESSOR_INFORMATION() {
        }
    }

    @Structure.FieldOrder({"MaxIdlenessAllowed", "Idleness", "TimeRemaining", "CoolingMode"})
    public static class SYSTEM_POWER_INFORMATION extends Structure {
        public byte CoolingMode;
        public int Idleness;
        public int MaxIdlenessAllowed;
        public int TimeRemaining;

        public SYSTEM_POWER_INFORMATION(Pointer pointer) {
            super(pointer);
            read();
        }

        public SYSTEM_POWER_INFORMATION() {
        }
    }

    @Structure.FieldOrder({"Luid", "Attributes"})
    public static class LUID_AND_ATTRIBUTES extends Structure {
        public WinDef.DWORD Attributes;
        public LUID Luid;

        public LUID_AND_ATTRIBUTES(LUID luid, WinDef.DWORD dword) {
            this.Luid = luid;
            this.Attributes = dword;
        }

        public LUID_AND_ATTRIBUTES() {
        }
    }

    @Structure.FieldOrder({"mask", "group", "reserved"})
    public static class GROUP_AFFINITY extends Structure {
        public short group;
        public BaseTSD.ULONG_PTR mask;
        public short[] reserved;

        public GROUP_AFFINITY(Pointer pointer) {
            super(pointer);
            this.reserved = new short[3];
        }

        public GROUP_AFFINITY() {
            this.reserved = new short[3];
        }
    }

    public static class HANDLEByReference extends ByReference {
        public HANDLEByReference(HANDLE handle) {
            super(Native.POINTER_SIZE);
            setValue(handle);
        }

        public HANDLE getValue() {
            Pointer pointer = getPointer().getPointer(0L);
            if (pointer == null) {
                return null;
            }
            HANDLE handle = WinBase.INVALID_HANDLE_VALUE;
            if (handle.getPointer().equals(pointer)) {
                return handle;
            }
            HANDLE handle2 = new HANDLE();
            handle2.setPointer(pointer);
            return handle2;
        }

        public void setValue(HANDLE handle) {
            getPointer().setPointer(0L, handle != null ? handle.getPointer() : null);
        }

        public HANDLEByReference() {
            this(null);
        }
    }

    public static class PACLByReference extends ByReference {
        public PACLByReference(ACL acl) {
            super(Native.POINTER_SIZE);
            setValue(acl);
        }

        public ACL getValue() {
            Pointer pointer = getPointer().getPointer(0L);
            if (pointer == null) {
                return null;
            }
            return new ACL(pointer);
        }

        public void setValue(ACL acl) {
            getPointer().setPointer(0L, acl != null ? acl.getPointer() : null);
        }

        public PACLByReference() {
            this(null);
        }
    }

    public static class PSIDByReference extends ByReference {
        public PSIDByReference(PSID psid) {
            super(Native.POINTER_SIZE);
            setValue(psid);
        }

        public PSID getValue() {
            Pointer pointer = getPointer().getPointer(0L);
            if (pointer == null) {
                return null;
            }
            return new PSID(pointer);
        }

        public void setValue(PSID psid) {
            getPointer().setPointer(0L, psid != null ? psid.getPointer() : null);
        }

        public PSIDByReference() {
            this(null);
        }
    }

    @Structure.FieldOrder({Constants.ATTRNAME_LEVEL, "associativity", "lineSize", "cacheSize", "type", "reserved", "groupMask"})
    public static class CACHE_RELATIONSHIP extends SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX {
        public byte associativity;
        public int cacheSize;
        public GROUP_AFFINITY groupMask;
        public byte level;
        public short lineSize;
        public byte[] reserved;
        public int type;

        public CACHE_RELATIONSHIP() {
            this.reserved = new byte[20];
        }

        public CACHE_RELATIONSHIP(Pointer pointer) {
            super(pointer);
            this.reserved = new byte[20];
        }
    }

    public static class HANDLE extends PointerType {
        private boolean immutable;

        public HANDLE(Pointer pointer) {
            setPointer(pointer);
            this.immutable = true;
        }

        @Override // com.sun.jna.PointerType, com.sun.jna.NativeMapped
        public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
            Object objFromNative = super.fromNative(obj, fromNativeContext);
            HANDLE handle = WinBase.INVALID_HANDLE_VALUE;
            return handle.equals(objFromNative) ? handle : objFromNative;
        }

        @Override // com.sun.jna.PointerType
        public void setPointer(Pointer pointer) {
            if (this.immutable) {
                c41.a("immutable reference");
            } else {
                super.setPointer(pointer);
            }
        }

        @Override // com.sun.jna.PointerType
        public String toString() {
            return String.valueOf(getPointer());
        }

        public HANDLE() {
        }
    }

    @Structure.FieldOrder({"maximumProcessorCount", "activeProcessorCount", "reserved", "activeProcessorMask"})
    public static class PROCESSOR_GROUP_INFO extends Structure {
        public byte activeProcessorCount;
        public BaseTSD.ULONG_PTR activeProcessorMask;
        public byte maximumProcessorCount;
        public byte[] reserved;

        public PROCESSOR_GROUP_INFO(Pointer pointer) {
            super(pointer);
            this.reserved = new byte[38];
        }

        public PROCESSOR_GROUP_INFO() {
            this.reserved = new byte[38];
        }
    }

    @Structure.FieldOrder({"GroupCount", "Group0"})
    public static class TOKEN_GROUPS extends Structure {
        public SID_AND_ATTRIBUTES Group0;
        public int GroupCount;

        public TOKEN_GROUPS(int i) {
            super(new Memory(i));
        }

        public SID_AND_ATTRIBUTES[] getGroups() {
            return (SID_AND_ATTRIBUTES[]) this.Group0.toArray(this.GroupCount);
        }

        public TOKEN_GROUPS(Pointer pointer) {
            super(pointer);
            read();
        }

        public TOKEN_GROUPS() {
        }
    }

    @Structure.FieldOrder({"Owner"})
    public static class TOKEN_OWNER extends Structure {
        public PSID.ByReference Owner;

        public TOKEN_OWNER(int i) {
            super(new Memory(i));
        }

        public TOKEN_OWNER() {
        }

        public TOKEN_OWNER(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"PrimaryGroup"})
    public static class TOKEN_PRIMARY_GROUP extends Structure {
        public PSID.ByReference PrimaryGroup;

        public TOKEN_PRIMARY_GROUP(int i) {
            super(new Memory(i));
        }

        public TOKEN_PRIMARY_GROUP(Pointer pointer) {
            super(pointer);
            read();
        }

        public TOKEN_PRIMARY_GROUP() {
        }
    }

    @Structure.FieldOrder({"User"})
    public static class TOKEN_USER extends Structure {
        public SID_AND_ATTRIBUTES User;

        public TOKEN_USER(int i) {
            super(new Memory(i));
        }

        public TOKEN_USER(Pointer pointer) {
            super(pointer);
            read();
        }

        public TOKEN_USER() {
        }
    }

    @Structure.FieldOrder({"AcOnLine", "BatteryPresent", "Charging", "Discharging", "Spare1", "Tag", "MaxCapacity", "RemainingCapacity", "Rate", "EstimatedTime", "DefaultAlert1", "DefaultAlert2"})
    public static class SYSTEM_BATTERY_STATE extends Structure {
        public byte AcOnLine;
        public byte BatteryPresent;
        public byte Charging;
        public int DefaultAlert1;
        public int DefaultAlert2;
        public byte Discharging;
        public int EstimatedTime;
        public int MaxCapacity;
        public int Rate;
        public int RemainingCapacity;
        public byte[] Spare1;
        public byte Tag;

        public SYSTEM_BATTERY_STATE(Pointer pointer) {
            super(pointer);
            this.Spare1 = new byte[3];
            read();
        }

        public SYSTEM_BATTERY_STATE() {
            this.Spare1 = new byte[3];
        }
    }

    @Structure.FieldOrder({"AceType", "AceFlags", "AceSize"})
    public static class ACE_HEADER extends Structure {
        public byte AceFlags;
        public short AceSize;
        public byte AceType;

        public ACE_HEADER(byte b, byte b2, short s) {
            this.AceType = b;
            this.AceFlags = b2;
            this.AceSize = s;
            write();
        }

        public ACE_HEADER(Pointer pointer) {
            super(pointer);
            read();
        }

        public ACE_HEADER() {
        }
    }

    @Structure.FieldOrder({"AclRevision", "Sbz1", "AclSize", "AceCount", "Sbz2"})
    public static class ACL extends Structure {
        public static int MAX_ACL_SIZE = 65536;
        public short AceCount;
        public byte AclRevision;
        public short AclSize;
        public byte Sbz1;
        public short Sbz2;

        public ACL(int i) {
            useMemory(new Memory(i));
        }

        public ACE_HEADER[] getACEs() {
            ACE_HEADER[] ace_headerArr = new ACE_HEADER[this.AceCount];
            Pointer pointer = getPointer();
            int size = size();
            for (int i = 0; i < this.AceCount; i++) {
                Pointer pointerShare = pointer.share(size);
                byte b = pointerShare.getByte(0L);
                if (b == 0) {
                    ace_headerArr[i] = new ACCESS_ALLOWED_ACE(pointerShare);
                } else if (b != 1) {
                    ace_headerArr[i] = new ACE_HEADER(pointerShare);
                } else {
                    ace_headerArr[i] = new ACCESS_DENIED_ACE(pointerShare);
                }
                size += ace_headerArr[i].AceSize;
            }
            return ace_headerArr;
        }

        public ACL() {
        }

        public ACL(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"maximumGroupCount", "activeGroupCount", "reserved", "groupInfo"})
    public static class GROUP_RELATIONSHIP extends SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX {
        public short activeGroupCount;
        public PROCESSOR_GROUP_INFO[] groupInfo;
        public short maximumGroupCount;
        public byte[] reserved;

        public GROUP_RELATIONSHIP() {
            this.reserved = new byte[20];
            this.groupInfo = new PROCESSOR_GROUP_INFO[1];
        }

        @Override // com.sun.jna.Structure
        public void read() {
            readField("activeGroupCount");
            int i = this.activeGroupCount;
            if (i != this.groupInfo.length) {
                this.groupInfo = new PROCESSOR_GROUP_INFO[i];
            }
            super.read();
        }

        public GROUP_RELATIONSHIP(Pointer pointer) {
            super(pointer);
            this.reserved = new byte[20];
            this.groupInfo = new PROCESSOR_GROUP_INFO[1];
        }
    }

    @Structure.FieldOrder({"nodeNumber", "reserved", "groupCount", "groupMasks"})
    public static class NUMA_NODE_RELATIONSHIP extends SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX {
        public short groupCount;
        public GROUP_AFFINITY groupMask;
        public GROUP_AFFINITY[] groupMasks;
        public int nodeNumber;
        public byte[] reserved;

        public NUMA_NODE_RELATIONSHIP() {
            this.reserved = new byte[18];
            this.groupMasks = new GROUP_AFFINITY[1];
        }

        @Override // com.sun.jna.Structure
        public List<Field> getFieldList() {
            ArrayList arrayList = new ArrayList(super.getFieldList());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if ("groupMask".equals(((Field) it.next()).getName())) {
                    it.remove();
                }
            }
            return arrayList;
        }

        @Override // com.sun.jna.Structure
        public void read() {
            readField("groupCount");
            int iMax = Math.max(1, (int) this.groupCount);
            if (iMax != this.groupMasks.length) {
                this.groupMasks = new GROUP_AFFINITY[iMax];
            }
            super.read();
            this.groupMask = this.groupMasks[0];
        }

        public NUMA_NODE_RELATIONSHIP(Pointer pointer) {
            super(pointer);
            this.reserved = new byte[18];
            this.groupMasks = new GROUP_AFFINITY[1];
        }
    }

    @Structure.FieldOrder({"flags", "efficiencyClass", "reserved", "groupCount", "groupMask"})
    public static class PROCESSOR_RELATIONSHIP extends SYSTEM_LOGICAL_PROCESSOR_INFORMATION_EX {
        public byte efficiencyClass;
        public byte flags;
        public short groupCount;
        public GROUP_AFFINITY[] groupMask;
        public byte[] reserved;

        public PROCESSOR_RELATIONSHIP() {
            this.reserved = new byte[20];
            this.groupMask = new GROUP_AFFINITY[1];
        }

        @Override // com.sun.jna.Structure
        public void read() {
            readField("groupCount");
            int i = this.groupCount;
            if (i != this.groupMask.length) {
                this.groupMask = new GROUP_AFFINITY[i];
            }
            super.read();
        }

        public PROCESSOR_RELATIONSHIP(Pointer pointer) {
            super(pointer);
            this.reserved = new byte[20];
            this.groupMask = new GROUP_AFFINITY[1];
        }
    }

    @Structure.FieldOrder({"data"})
    public static class SECURITY_DESCRIPTOR extends Structure {
        public byte[] data;

        public static class ByReference extends SECURITY_DESCRIPTOR implements Structure.ByReference {
        }

        public SECURITY_DESCRIPTOR(int i) {
            useMemory(new Memory(i));
            this.data = new byte[i];
        }

        public SECURITY_DESCRIPTOR(byte[] bArr) {
            this.data = bArr;
            useMemory(new Memory(bArr.length));
        }

        public SECURITY_DESCRIPTOR() {
        }

        public SECURITY_DESCRIPTOR(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"PowerButtonPresent", "SleepButtonPresent", "LidPresent", "SystemS1", "SystemS2", "SystemS3", "SystemS4", "SystemS5", "HiberFilePresent", "FullWake", "VideoDimPresent", "ApmPresent", "UpsPresent", "ThermalControl", "ProcessorThrottle", "ProcessorMinThrottle", "ProcessorMaxThrottle", "FastSystemS4", "Hiberboot", "WakeAlarmPresent", "AoAc", "DiskSpinDown", "HiberFileType", "AoAcConnectivitySupported", "spare3", "SystemBatteriesPresent", "BatteriesAreShortTerm", "BatteryScale", "AcOnLineWake", "SoftLidWake", "RtcWake", "MinDeviceWakeState", "DefaultLowLatencyWake"})
    public static class SYSTEM_POWER_CAPABILITIES extends Structure {
        public int AcOnLineWake;
        public byte AoAc;
        public byte AoAcConnectivitySupported;
        public byte ApmPresent;
        public byte BatteriesAreShortTerm;
        public BATTERY_REPORTING_SCALE[] BatteryScale;
        public int DefaultLowLatencyWake;
        public byte DiskSpinDown;
        public byte FastSystemS4;
        public byte FullWake;
        public byte HiberFilePresent;
        public byte HiberFileType;
        public byte Hiberboot;
        public byte LidPresent;
        public int MinDeviceWakeState;
        public byte PowerButtonPresent;
        public byte ProcessorMaxThrottle;
        public byte ProcessorMinThrottle;
        public byte ProcessorThrottle;
        public int RtcWake;
        public byte SleepButtonPresent;
        public int SoftLidWake;
        public byte SystemBatteriesPresent;
        public byte SystemS1;
        public byte SystemS2;
        public byte SystemS3;
        public byte SystemS4;
        public byte SystemS5;
        public byte ThermalControl;
        public byte UpsPresent;
        public byte VideoDimPresent;
        public byte WakeAlarmPresent;
        public byte[] spare3;

        public SYSTEM_POWER_CAPABILITIES(Pointer pointer) {
            super(pointer);
            this.spare3 = new byte[6];
            this.BatteryScale = new BATTERY_REPORTING_SCALE[3];
            read();
        }

        public SYSTEM_POWER_CAPABILITIES() {
            this.spare3 = new byte[6];
            this.BatteryScale = new BATTERY_REPORTING_SCALE[3];
        }
    }

    @Structure.FieldOrder({"dwOSVersionInfoSize", "dwMajorVersion", "dwMinorVersion", "dwBuildNumber", "dwPlatformId", "szCSDVersion"})
    public static class OSVERSIONINFO extends Structure {
        public WinDef.DWORD dwBuildNumber;
        public WinDef.DWORD dwMajorVersion;
        public WinDef.DWORD dwMinorVersion;
        public WinDef.DWORD dwOSVersionInfoSize;
        public WinDef.DWORD dwPlatformId;
        public char[] szCSDVersion;

        public OSVERSIONINFO() {
            this.szCSDVersion = new char[128];
            this.dwOSVersionInfoSize = new WinDef.DWORD(size());
        }

        public OSVERSIONINFO(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"dwOSVersionInfoSize", "dwMajorVersion", "dwMinorVersion", "dwBuildNumber", "dwPlatformId", "szCSDVersion", "wServicePackMajor", "wServicePackMinor", "wSuiteMask", "wProductType", "wReserved"})
    public static class OSVERSIONINFOEX extends Structure {
        public WinDef.DWORD dwBuildNumber;
        public WinDef.DWORD dwMajorVersion;
        public WinDef.DWORD dwMinorVersion;
        public WinDef.DWORD dwOSVersionInfoSize;
        public WinDef.DWORD dwPlatformId;
        public char[] szCSDVersion;
        public byte wProductType;
        public byte wReserved;
        public WinDef.WORD wServicePackMajor;
        public WinDef.WORD wServicePackMinor;
        public WinDef.WORD wSuiteMask;

        public OSVERSIONINFOEX() {
            this.szCSDVersion = new char[128];
            this.dwOSVersionInfoSize = new WinDef.DWORD(size());
        }

        public int getBuildNumber() {
            return this.dwBuildNumber.intValue();
        }

        public int getMajor() {
            return this.dwMajorVersion.intValue();
        }

        public int getMinor() {
            return this.dwMinorVersion.intValue();
        }

        public int getPlatformId() {
            return this.dwPlatformId.intValue();
        }

        public byte getProductType() {
            return this.wProductType;
        }

        public String getServicePack() {
            return Native.toString(this.szCSDVersion);
        }

        public int getSuiteMask() {
            return this.wSuiteMask.intValue();
        }

        public OSVERSIONINFOEX(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"Revision", "PowerButton", "SleepButton", "LidClose", "LidOpenWake", "Reserved", "Idle", "IdleTimeout", "IdleSensitivity", "DynamicThrottle", "Spare2", "MinSleep", "MaxSleep", "ReducedLatencySleep", "WinLogonFlags", "Spare3", "DozeS4Timeout", "BroadcastCapacityResolution", "DischargePolicy", "VideoTimeout", "VideoDimDisplay", "VideoReserved", "SpindownTimeout", "OptimizeForPower", "FanThrottleTolerance", "ForcedThrottle", "MinThrottle", "OverThrottled"})
    public static class SYSTEM_POWER_POLICY extends Structure {
        public int BroadcastCapacityResolution;
        public SYSTEM_POWER_LEVEL[] DischargePolicy;
        public int DozeS4Timeout;
        public byte DynamicThrottle;
        public byte FanThrottleTolerance;
        public byte ForcedThrottle;
        public POWER_ACTION_POLICY Idle;
        public byte IdleSensitivity;
        public int IdleTimeout;
        public POWER_ACTION_POLICY LidClose;
        public int LidOpenWake;
        public int MaxSleep;
        public int MinSleep;
        public byte MinThrottle;
        public byte OptimizeForPower;
        public POWER_ACTION_POLICY OverThrottled;
        public POWER_ACTION_POLICY PowerButton;
        public int ReducedLatencySleep;
        public int Reserved;
        public int Revision;
        public POWER_ACTION_POLICY SleepButton;
        public byte[] Spare2;
        public int Spare3;
        public int SpindownTimeout;
        public byte VideoDimDisplay;
        public int[] VideoReserved;
        public int VideoTimeout;
        public int WinLogonFlags;

        public SYSTEM_POWER_POLICY(Pointer pointer) {
            super(pointer);
            this.Spare2 = new byte[2];
            this.DischargePolicy = new SYSTEM_POWER_LEVEL[4];
            this.VideoReserved = new int[3];
            read();
        }

        public SYSTEM_POWER_POLICY() {
            this.Spare2 = new byte[2];
            this.DischargePolicy = new SYSTEM_POWER_LEVEL[4];
            this.VideoReserved = new int[3];
        }
    }

    @Structure.FieldOrder({"PrivilegeCount", "Privileges"})
    public static class TOKEN_PRIVILEGES extends Structure {
        public WinDef.DWORD PrivilegeCount;
        public LUID_AND_ATTRIBUTES[] Privileges;

        public TOKEN_PRIVILEGES(Pointer pointer) {
            super(pointer);
            int i = pointer.getInt(0L);
            this.PrivilegeCount = new WinDef.DWORD(i);
            this.Privileges = new LUID_AND_ATTRIBUTES[i];
            read();
        }

        public TOKEN_PRIVILEGES(int i) {
            this.PrivilegeCount = new WinDef.DWORD(i);
            this.Privileges = new LUID_AND_ATTRIBUTES[i];
        }

        public TOKEN_PRIVILEGES() {
            this(0);
        }
    }

    @Structure.FieldOrder({"sid"})
    public static class PSID extends Structure {
        public Pointer sid;

        public static class ByReference extends PSID implements Structure.ByReference {
        }

        public PSID(byte[] bArr) {
            super(new Memory(bArr.length));
            getPointer().write(0L, bArr, 0, bArr.length);
            read();
        }

        public byte[] getBytes() {
            return getPointer().getByteArray(0L, Advapi32.INSTANCE.GetLengthSid(this));
        }

        public String getSidString() {
            return Advapi32Util.convertSidToStringSid(this);
        }

        public PSID() {
        }

        public PSID(int i) {
            super(new Memory(i));
        }

        public PSID(Pointer pointer) {
            super(pointer);
            read();
        }
    }

    @Structure.FieldOrder({"Revision", "Sbz1", "Control", "Owner", "Group", "Sacl", "Dacl"})
    public static class SECURITY_DESCRIPTOR_RELATIVE extends Structure {
        public short Control;
        private ACL DACL;
        public int Dacl;
        private PSID GROUP;
        public int Group;
        private PSID OWNER;
        public int Owner;
        public byte Revision;
        private ACL SACL;
        public int Sacl;
        public byte Sbz1;

        public static class ByReference extends SECURITY_DESCRIPTOR_RELATIVE implements Structure.ByReference {
        }

        public SECURITY_DESCRIPTOR_RELATIVE(byte[] bArr) {
            super(new Memory(bArr.length));
            getPointer().write(0L, bArr, 0, bArr.length);
            setMembers();
        }

        private final void setMembers() {
            read();
            if (this.Dacl != 0) {
                this.DACL = new ACL(getPointer().share(this.Dacl));
            }
            if (this.Sacl != 0) {
                this.SACL = new ACL(getPointer().share(this.Sacl));
            }
            if (this.Group != 0) {
                this.GROUP = new PSID(getPointer().share(this.Group));
            }
            if (this.Owner != 0) {
                this.OWNER = new PSID(getPointer().share(this.Owner));
            }
        }

        public ACL getDiscretionaryACL() {
            return this.DACL;
        }

        public PSID getGroup() {
            return this.GROUP;
        }

        public PSID getOwner() {
            return this.OWNER;
        }

        public ACL getSystemACL() {
            return this.SACL;
        }

        public SECURITY_DESCRIPTOR_RELATIVE() {
        }

        public SECURITY_DESCRIPTOR_RELATIVE(int i) {
            super(new Memory(i));
        }

        public SECURITY_DESCRIPTOR_RELATIVE(Pointer pointer) {
            super(pointer);
            setMembers();
        }
    }

    @Structure.FieldOrder({"PrivilegeCount", "Control", "Privileges"})
    public static class PRIVILEGE_SET extends Structure {
        public WinDef.DWORD Control;
        public WinDef.DWORD PrivilegeCount;
        public LUID_AND_ATTRIBUTES[] Privileges;

        public PRIVILEGE_SET(Pointer pointer) {
            super(pointer);
            int i = pointer.getInt(0L);
            this.PrivilegeCount = new WinDef.DWORD(i);
            if (i > 0) {
                this.Privileges = new LUID_AND_ATTRIBUTES[i];
            }
            read();
        }

        public PRIVILEGE_SET(int i) {
            this.PrivilegeCount = new WinDef.DWORD(i);
            if (i > 0) {
                this.Privileges = new LUID_AND_ATTRIBUTES[i];
            }
        }

        public PRIVILEGE_SET() {
            this(0);
        }
    }

    @Structure.FieldOrder({"NextEntryOffset", "Action", "FileNameLength", "FileName"})
    public static class FILE_NOTIFY_INFORMATION extends Structure {
        public int Action;
        public char[] FileName;
        public int FileNameLength;
        public int NextEntryOffset;

        public FILE_NOTIFY_INFORMATION(int i) {
            this.FileName = new char[1];
            if (i >= size()) {
                allocateMemory(i);
            } else {
                eq7.a("Size must greater than ", size(), ", requested ", i);
                throw null;
            }
        }

        public String getFilename() {
            return new String(this.FileName, 0, this.FileNameLength / 2);
        }

        public FILE_NOTIFY_INFORMATION next() {
            if (this.NextEntryOffset == 0) {
                return null;
            }
            FILE_NOTIFY_INFORMATION file_notify_information = new FILE_NOTIFY_INFORMATION();
            file_notify_information.useMemory(getPointer(), this.NextEntryOffset);
            file_notify_information.read();
            return file_notify_information;
        }

        @Override // com.sun.jna.Structure
        public void read() {
            this.FileName = new char[0];
            super.read();
            this.FileName = getPointer().getCharArray(12L, this.FileNameLength / 2);
        }

        private FILE_NOTIFY_INFORMATION() {
            this.FileName = new char[1];
        }
    }

    @Structure.FieldOrder({"Mask", "SidStart"})
    public static abstract class ACCESS_ACEStructure extends ACE_HEADER {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public int Mask;
        public byte[] SidStart;
        PSID psid;

        public ACCESS_ACEStructure(int i, byte b, byte b2, PSID psid) {
            this.SidStart = new byte[4];
            calculateSize(true);
            this.AceType = b;
            this.AceFlags = b2;
            this.AceSize = (short) (super.fieldOffset("SidStart") + psid.getBytes().length);
            this.psid = psid;
            this.Mask = i;
            this.SidStart = psid.getPointer().getByteArray(0L, this.SidStart.length);
            allocateMemory(this.AceSize);
            write();
        }

        public PSID getSID() {
            return this.psid;
        }

        public String getSidString() {
            return Advapi32Util.convertSidToStringSid(this.psid);
        }

        @Override // com.sun.jna.Structure
        public void read() {
            if (this.SidStart == null) {
                this.SidStart = new byte[4];
            }
            super.read();
            int iFieldOffset = super.fieldOffset("SidStart");
            int iFieldOffset2 = this.AceSize - super.fieldOffset("SidStart");
            if (iFieldOffset2 > 0) {
                this.psid = new PSID(getPointer().getByteArray(iFieldOffset, iFieldOffset2));
            } else {
                this.psid = new PSID();
            }
        }

        @Override // com.sun.jna.Structure
        public void write() {
            super.write();
            int iFieldOffset = super.fieldOffset("SidStart");
            int iFieldOffset2 = this.AceSize - super.fieldOffset("SidStart");
            PSID psid = this.psid;
            if (psid != null) {
                getPointer().write(iFieldOffset, psid.getBytes(), 0, iFieldOffset2);
            }
        }

        public ACCESS_ACEStructure() {
            this.SidStart = new byte[4];
        }

        public ACCESS_ACEStructure(Pointer pointer) {
            super(pointer);
            this.SidStart = new byte[4];
            read();
        }
    }
}
