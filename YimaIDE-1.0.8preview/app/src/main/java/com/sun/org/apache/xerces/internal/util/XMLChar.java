package com.sun.org.apache.xerces.internal.util;

import com.sun.jna.Function;
import com.sun.jna.platform.win32.GL;
import com.sun.jna.platform.win32.LMErr;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import com.sun.tools.javac.code.Flags;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLChar {
    private static final byte[] CHARS;
    public static final int MASK_CONTENT = 32;
    public static final int MASK_NAME = 8;
    public static final int MASK_NAME_START = 4;
    public static final int MASK_NCNAME = 128;
    public static final int MASK_NCNAME_START = 64;
    public static final int MASK_PUBID = 16;
    public static final int MASK_SPACE = 2;
    public static final int MASK_VALID = 1;

    static {
        byte[] bArr = new byte[65536];
        CHARS = bArr;
        bArr[9] = 35;
        bArr[10] = 19;
        bArr[13] = 19;
        bArr[32] = 51;
        bArr[33] = 49;
        bArr[34] = 33;
        Arrays.fill(bArr, 35, 38, (byte) 49);
        bArr[38] = 1;
        Arrays.fill(bArr, 39, 45, (byte) 49);
        Arrays.fill(bArr, 45, 47, (byte) -71);
        bArr[47] = 49;
        Arrays.fill(bArr, 48, 58, (byte) -71);
        bArr[58] = 61;
        bArr[59] = 49;
        bArr[60] = 1;
        bArr[61] = 49;
        bArr[62] = 33;
        Arrays.fill(bArr, 63, 65, (byte) 49);
        Arrays.fill(bArr, 65, 91, (byte) -3);
        Arrays.fill(bArr, 91, 93, (byte) 33);
        bArr[93] = 1;
        bArr[94] = 33;
        bArr[95] = -3;
        bArr[96] = 33;
        Arrays.fill(bArr, 97, 123, (byte) -3);
        Arrays.fill(bArr, 123, 183, (byte) 33);
        bArr[183] = -87;
        Arrays.fill(bArr, 184, 192, (byte) 33);
        Arrays.fill(bArr, 192, WinError.ERROR_NESTING_NOT_ALLOWED, (byte) -19);
        bArr[215] = 33;
        Arrays.fill(bArr, WinError.ERROR_EXE_MACHINE_TYPE_MISMATCH, Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED, (byte) -19);
        bArr[247] = 33;
        Arrays.fill(bArr, Const.CHOP_FRAME, 306, (byte) -19);
        Arrays.fill(bArr, 306, 308, (byte) 33);
        Arrays.fill(bArr, 308, WinNT.SERVICE_TYPE_ALL, (byte) -19);
        Arrays.fill(bArr, WinNT.SERVICE_TYPE_ALL, 321, (byte) 33);
        Arrays.fill(bArr, 321, 329, (byte) -19);
        bArr[329] = 33;
        Arrays.fill(bArr, 330, 383, (byte) -19);
        bArr[383] = 33;
        Arrays.fill(bArr, Function.USE_VARARGS, 452, (byte) -19);
        Arrays.fill(bArr, 452, 461, (byte) 33);
        Arrays.fill(bArr, 461, 497, (byte) -19);
        Arrays.fill(bArr, 497, WinError.ERROR_USER_PROFILE_LOAD, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_USER_PROFILE_LOAD, 502, (byte) -19);
        Arrays.fill(bArr, 502, 506, (byte) 33);
        Arrays.fill(bArr, 506, WinError.ERROR_PIPE_LISTENING, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_PIPE_LISTENING, WinError.ERROR_DATA_NOT_ACCEPTED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DATA_NOT_ACCEPTED, WinError.ERROR_STOPPED_ON_SYMLINK, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_STOPPED_ON_SYMLINK, WinError.ERROR_THREAD_WAS_SUSPENDED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_THREAD_WAS_SUSPENDED, WinError.ERROR_IMAGE_MACHINE_TYPE_MISMATCH, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_IMAGE_MACHINE_TYPE_MISMATCH, WinError.ERROR_IMAGE_MACHINE_TYPE_MISMATCH_EXE, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_IMAGE_MACHINE_TYPE_MISMATCH_EXE, WinError.ERROR_TIMER_RESUME_IGNORED, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_TIMER_RESUME_IGNORED, 768, (byte) 33);
        Arrays.fill(bArr, 768, 838, (byte) -87);
        Arrays.fill(bArr, 838, 864, (byte) 33);
        Arrays.fill(bArr, 864, 866, (byte) -87);
        Arrays.fill(bArr, 866, 902, (byte) 33);
        bArr[902] = -19;
        bArr[903] = -87;
        Arrays.fill(bArr, 904, 907, (byte) -19);
        bArr[907] = 33;
        bArr[908] = -19;
        bArr[909] = 33;
        Arrays.fill(bArr, 910, 930, (byte) -19);
        bArr[930] = 33;
        Arrays.fill(bArr, 931, 975, (byte) -19);
        bArr[975] = 33;
        Arrays.fill(bArr, 976, 983, (byte) -19);
        Arrays.fill(bArr, 983, 986, (byte) 33);
        bArr[986] = -19;
        bArr[987] = 33;
        bArr[988] = -19;
        bArr[989] = 33;
        bArr[990] = -19;
        bArr[991] = 33;
        bArr[992] = -19;
        bArr[993] = 33;
        Arrays.fill(bArr, WinError.ERROR_EA_ACCESS_DENIED, WinError.ERROR_CANTREAD, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_CANTREAD, Flags.InterfaceMethodFlags, (byte) 33);
        Arrays.fill(bArr, Flags.InterfaceMethodFlags, 1037, (byte) -19);
        bArr[1037] = 33;
        Arrays.fill(bArr, 1038, WinError.ERROR_NO_DATA_DETECTED, (byte) -19);
        bArr[1104] = 33;
        Arrays.fill(bArr, WinError.ERROR_PARTITION_FAILURE, WinError.ERROR_IO_DEVICE, (byte) -19);
        bArr[1117] = 33;
        Arrays.fill(bArr, WinError.ERROR_SERIAL_NO_DEVICE, WinError.ERROR_INVALID_DLL, (byte) -19);
        bArr[1154] = 33;
        Arrays.fill(bArr, WinError.ERROR_NO_ASSOCIATION, WinError.ERROR_MESSAGE_SYNC_ONLY, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_MESSAGE_SYNC_ONLY, WinError.ERROR_NOT_FOUND, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_NOT_FOUND, WinError.ERROR_DUP_DOMAINNAME, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_DUP_DOMAINNAME, WinError.ERROR_CANCELLED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_CANCELLED, WinError.ERROR_CONNECTION_REFUSED, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_CONNECTION_REFUSED, WinError.ERROR_ADDRESS_ALREADY_ASSOCIATED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_ADDRESS_ALREADY_ASSOCIATED, WinError.ERROR_CONNECTION_INVALID, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_CONNECTION_INVALID, WinError.ERROR_HOST_UNREACHABLE, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_HOST_UNREACHABLE, WinError.ERROR_ACCESS_DISABLED_BY_POLICY, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_ACCESS_DISABLED_BY_POLICY, WinError.ERROR_CSCSHARE_OFFLINE, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_CSCSHARE_OFFLINE, 1270, (byte) -19);
        Arrays.fill(bArr, 1270, 1272, (byte) 33);
        Arrays.fill(bArr, 1272, WinError.ERROR_SYNC_FOREGROUND_REFRESH_REQUIRED, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_SYNC_FOREGROUND_REFRESH_REQUIRED, WinError.ERROR_INVALID_WORKSTATION, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_INVALID_WORKSTATION, WinError.ERROR_INVALID_LOGON_TYPE, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_INVALID_LOGON_TYPE, WinError.ERROR_RXACT_INVALID_STATE, (byte) 33);
        bArr[1369] = -19;
        Arrays.fill(bArr, WinError.ERROR_RXACT_COMMIT_FAILURE, WinError.ERROR_MEMBER_NOT_IN_ALIAS, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_MEMBER_NOT_IN_ALIAS, WinError.ERROR_PRIVATE_DIALOG_INDEX, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_PRIVATE_DIALOG_INDEX, WinError.ERROR_DC_NOT_FOUND, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DC_NOT_FOUND, WinError.ERROR_NOT_CHILD_WINDOW, (byte) -87);
        bArr[1442] = 33;
        Arrays.fill(bArr, WinError.ERROR_INVALID_GW_COMMAND, WinError.ERROR_XMLDSIG_ERROR, (byte) -87);
        bArr[1466] = 33;
        Arrays.fill(bArr, WinError.ERROR_RESTART_APPLICATION, 1470, (byte) -87);
        bArr[1470] = 33;
        bArr[1471] = -87;
        bArr[1472] = 33;
        Arrays.fill(bArr, 1473, 1475, (byte) -87);
        bArr[1475] = 33;
        bArr[1476] = -87;
        Arrays.fill(bArr, 1477, 1488, (byte) 33);
        Arrays.fill(bArr, 1488, 1515, (byte) -19);
        Arrays.fill(bArr, 1515, 1520, (byte) 33);
        Arrays.fill(bArr, 1520, 1523, (byte) -19);
        Arrays.fill(bArr, 1523, 1569, (byte) 33);
        Arrays.fill(bArr, 1569, 1595, (byte) -19);
        Arrays.fill(bArr, 1595, 1600, (byte) 33);
        bArr[1600] = -87;
        Arrays.fill(bArr, WinError.ERROR_INSTALL_SERVICE_FAILURE, WinError.ERROR_INDEX_ABSENT, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_INDEX_ABSENT, WinError.ERROR_INSTALL_PACKAGE_OPEN_FAILED, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_INSTALL_PACKAGE_OPEN_FAILED, WinError.ERROR_INSTALL_TEMP_UNWRITABLE, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_INSTALL_TEMP_UNWRITABLE, WinError.ERROR_PATCH_TARGET_NOT_FOUND, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_PATCH_TARGET_NOT_FOUND, WinError.ERROR_PATCH_NO_SEQUENCE, (byte) 33);
        bArr[1648] = -87;
        Arrays.fill(bArr, WinError.ERROR_PATCH_REMOVAL_DISALLOWED, WinError.RPC_S_CANT_CREATE_ENDPOINT, (byte) -19);
        Arrays.fill(bArr, WinError.RPC_S_CANT_CREATE_ENDPOINT, WinError.RPC_S_SERVER_UNAVAILABLE, (byte) 33);
        Arrays.fill(bArr, WinError.RPC_S_SERVER_UNAVAILABLE, WinError.RPC_S_CALL_FAILED_DNE, (byte) -19);
        bArr[1727] = 33;
        Arrays.fill(bArr, WinError.RPC_S_PROTOCOL_ERROR, WinError.RPC_S_STRING_TOO_LONG, (byte) -19);
        bArr[1743] = 33;
        Arrays.fill(bArr, WinError.RPC_S_PROTSEQ_NOT_FOUND, WinError.RPC_S_UNKNOWN_AUTHN_LEVEL, (byte) -19);
        bArr[1748] = 33;
        bArr[1749] = -19;
        Arrays.fill(bArr, WinError.RPC_S_UNKNOWN_AUTHZ_SERVICE, WinError.RPC_S_NO_CONTEXT_AVAILABLE, (byte) -87);
        Arrays.fill(bArr, WinError.RPC_S_NO_CONTEXT_AVAILABLE, WinError.RPC_S_ZERO_DIVIDE, (byte) -19);
        Arrays.fill(bArr, WinError.RPC_S_ZERO_DIVIDE, WinError.RPC_S_FP_DIV_ZERO, (byte) -87);
        bArr[1769] = 33;
        Arrays.fill(bArr, WinError.RPC_S_FP_UNDERFLOW, WinError.RPC_X_SS_CHAR_TRANS_SHORT_FILE, (byte) -87);
        Arrays.fill(bArr, WinError.RPC_X_SS_CHAR_TRANS_SHORT_FILE, 1776, (byte) 33);
        Arrays.fill(bArr, 1776, WinError.ERROR_NO_TRUST_LSA_SECRET, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_NO_TRUST_LSA_SECRET, 2305, (byte) 33);
        Arrays.fill(bArr, 2305, 2308, (byte) -87);
        bArr[2308] = 33;
        Arrays.fill(bArr, 2309, LMErr.NERR_TooManyEntries, (byte) -19);
        Arrays.fill(bArr, LMErr.NERR_TooManyEntries, 2364, (byte) 33);
        bArr[2364] = -87;
        bArr[2365] = -19;
        Arrays.fill(bArr, 2366, LMErr.NERR_BadDest, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_BadDest, LMErr.NERR_RunSrvPaused, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_RunSrvPaused, LMErr.NERR_ErrCommRunSrv, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_ErrCommRunSrv, LMErr.NERR_ShareNotFound, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_ShareNotFound, 2402, (byte) -19);
        Arrays.fill(bArr, 2402, 2404, (byte) -87);
        Arrays.fill(bArr, 2404, 2406, (byte) 33);
        Arrays.fill(bArr, 2406, 2416, (byte) -87);
        Arrays.fill(bArr, 2416, LMErr.NERR_BadRecipient, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_BadRecipient, 2436, (byte) -87);
        bArr[2436] = 33;
        Arrays.fill(bArr, 2437, 2445, (byte) -19);
        Arrays.fill(bArr, 2445, 2447, (byte) 33);
        Arrays.fill(bArr, 2447, 2449, (byte) -19);
        Arrays.fill(bArr, 2449, LMErr.NERR_InvalidUASOp, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_InvalidUASOp, 2473, (byte) -19);
        bArr[2473] = 33;
        Arrays.fill(bArr, 2474, LMErr.NERR_UPSInvalidConfig, (byte) -19);
        bArr[2481] = 33;
        bArr[2482] = -19;
        Arrays.fill(bArr, LMErr.NERR_UPSSignalAsserted, 2486, (byte) 33);
        Arrays.fill(bArr, 2486, 2490, (byte) -19);
        Arrays.fill(bArr, 2490, 2492, (byte) 33);
        bArr[2492] = -87;
        bArr[2493] = 33;
        Arrays.fill(bArr, 2494, LMErr.NERR_ProgNeedsExtraMem, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_ProgNeedsExtraMem, LMErr.NERR_RemoteBootFailed, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_RemoteBootFailed, LMErr.NERR_NoRplBootSystem, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_NoRplBootSystem, LMErr.NERR_RplLoadrDiskErr, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_RplLoadrDiskErr, LMErr.NERR_NonDosFloppyUsed, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_NonDosFloppyUsed, LMErr.NERR_RPL_CONNECTED, (byte) 33);
        bArr[2519] = -87;
        Arrays.fill(bArr, 2520, 2524, (byte) 33);
        Arrays.fill(bArr, 2524, 2526, (byte) -19);
        bArr[2526] = 33;
        Arrays.fill(bArr, 2527, 2530, (byte) -19);
        Arrays.fill(bArr, 2530, 2532, (byte) -87);
        Arrays.fill(bArr, 2532, 2534, (byte) 33);
        Arrays.fill(bArr, 2534, 2544, (byte) -87);
        Arrays.fill(bArr, 2544, 2546, (byte) -19);
        Arrays.fill(bArr, 2546, 2562, (byte) 33);
        bArr[2562] = -87;
        Arrays.fill(bArr, 2563, 2565, (byte) 33);
        Arrays.fill(bArr, 2565, 2571, (byte) -19);
        Arrays.fill(bArr, 2571, 2575, (byte) 33);
        Arrays.fill(bArr, 2575, 2577, (byte) -19);
        Arrays.fill(bArr, 2577, 2579, (byte) 33);
        Arrays.fill(bArr, 2579, 2601, (byte) -19);
        bArr[2601] = 33;
        Arrays.fill(bArr, 2602, 2609, (byte) -19);
        bArr[2609] = 33;
        Arrays.fill(bArr, LMErr.NERR_RplNoAdaptersStarted, LMErr.NERR_RplBadDatabase, (byte) -19);
        bArr[2612] = 33;
        Arrays.fill(bArr, LMErr.NERR_RplRplfilesShare, LMErr.NERR_RplCannotEnum, (byte) -19);
        bArr[2615] = 33;
        Arrays.fill(bArr, LMErr.NERR_RplWkstaInfoCorrupted, LMErr.NERR_RplWkstaNameUnavailable, (byte) -19);
        Arrays.fill(bArr, LMErr.NERR_RplWkstaNameUnavailable, LMErr.NERR_RplProfileNotFound, (byte) 33);
        bArr[2620] = -87;
        bArr[2621] = 33;
        Arrays.fill(bArr, LMErr.NERR_RplProfileNotEmpty, LMErr.NERR_RplVendorInfoCorrupted, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_RplVendorInfoCorrupted, LMErr.NERR_RplBootNotFound, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_RplBootNotFound, LMErr.NERR_RplAdapterNameUnavailable, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_RplAdapterNameUnavailable, LMErr.NERR_RplBootInUse, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_RplBootInUse, LMErr.NERR_RplVendorNotFound, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_RplVendorNotFound, 2649, (byte) 33);
        Arrays.fill(bArr, 2649, 2653, (byte) -19);
        bArr[2653] = 33;
        bArr[2654] = -19;
        Arrays.fill(bArr, 2655, LMErr.NERR_DfsNoSuchVolume, (byte) 33);
        Arrays.fill(bArr, LMErr.NERR_DfsNoSuchVolume, LMErr.NERR_DfsCyclicalName, (byte) -87);
        Arrays.fill(bArr, LMErr.NERR_DfsCyclicalName, LMErr.NERR_DfsCantRemoveLastServerShare, (byte) -19);
        Arrays.fill(bArr, LMErr.NERR_DfsCantRemoveLastServerShare, 2689, (byte) 33);
        Arrays.fill(bArr, 2689, LMErr.NERR_SetupNotJoined, (byte) -87);
        bArr[2692] = 33;
        Arrays.fill(bArr, LMErr.NERR_SetupDomainController, 2700, (byte) -19);
        bArr[2700] = 33;
        bArr[2701] = -19;
        bArr[2702] = 33;
        Arrays.fill(bArr, LMErr.NERR_PasswordTooLong, 2706, (byte) -19);
        bArr[2706] = 33;
        Arrays.fill(bArr, 2707, 2729, (byte) -19);
        bArr[2729] = 33;
        Arrays.fill(bArr, 2730, 2737, (byte) -19);
        bArr[2737] = 33;
        Arrays.fill(bArr, 2738, 2740, (byte) -19);
        bArr[2740] = 33;
        Arrays.fill(bArr, 2741, 2746, (byte) -19);
        Arrays.fill(bArr, 2746, 2748, (byte) 33);
        bArr[2748] = -87;
        bArr[2749] = -19;
        Arrays.fill(bArr, 2750, 2758, (byte) -87);
        bArr[2758] = 33;
        Arrays.fill(bArr, 2759, 2762, (byte) -87);
        bArr[2762] = 33;
        Arrays.fill(bArr, 2763, 2766, (byte) -87);
        Arrays.fill(bArr, 2766, 2784, (byte) 33);
        bArr[2784] = -19;
        Arrays.fill(bArr, 2785, 2790, (byte) 33);
        Arrays.fill(bArr, 2790, 2800, (byte) -87);
        Arrays.fill(bArr, 2800, 2817, (byte) 33);
        Arrays.fill(bArr, 2817, 2820, (byte) -87);
        bArr[2820] = 33;
        Arrays.fill(bArr, 2821, 2829, (byte) -19);
        Arrays.fill(bArr, 2829, 2831, (byte) 33);
        Arrays.fill(bArr, 2831, 2833, (byte) -19);
        Arrays.fill(bArr, 2833, 2835, (byte) 33);
        Arrays.fill(bArr, 2835, 2857, (byte) -19);
        bArr[2857] = 33;
        Arrays.fill(bArr, 2858, 2865, (byte) -19);
        bArr[2865] = 33;
        Arrays.fill(bArr, 2866, 2868, (byte) -19);
        Arrays.fill(bArr, 2868, 2870, (byte) 33);
        Arrays.fill(bArr, 2870, 2874, (byte) -19);
        Arrays.fill(bArr, 2874, 2876, (byte) 33);
        bArr[2876] = -87;
        bArr[2877] = -19;
        Arrays.fill(bArr, 2878, 2884, (byte) -87);
        Arrays.fill(bArr, 2884, 2887, (byte) 33);
        Arrays.fill(bArr, 2887, 2889, (byte) -87);
        Arrays.fill(bArr, 2889, 2891, (byte) 33);
        Arrays.fill(bArr, 2891, 2894, (byte) -87);
        Arrays.fill(bArr, 2894, 2902, (byte) 33);
        Arrays.fill(bArr, 2902, 2904, (byte) -87);
        Arrays.fill(bArr, 2904, 2908, (byte) 33);
        Arrays.fill(bArr, 2908, 2910, (byte) -19);
        bArr[2910] = 33;
        Arrays.fill(bArr, 2911, 2914, (byte) -19);
        Arrays.fill(bArr, 2914, 2918, (byte) 33);
        Arrays.fill(bArr, 2918, 2928, (byte) -87);
        Arrays.fill(bArr, 2928, 2946, (byte) 33);
        Arrays.fill(bArr, 2946, 2948, (byte) -87);
        bArr[2948] = 33;
        Arrays.fill(bArr, 2949, 2955, (byte) -19);
        Arrays.fill(bArr, 2955, 2958, (byte) 33);
        Arrays.fill(bArr, 2958, 2961, (byte) -19);
        bArr[2961] = 33;
        Arrays.fill(bArr, 2962, 2966, (byte) -19);
        Arrays.fill(bArr, 2966, 2969, (byte) 33);
        Arrays.fill(bArr, 2969, 2971, (byte) -19);
        bArr[2971] = 33;
        bArr[2972] = -19;
        bArr[2973] = 33;
        Arrays.fill(bArr, 2974, 2976, (byte) -19);
        Arrays.fill(bArr, 2976, 2979, (byte) 33);
        Arrays.fill(bArr, 2979, 2981, (byte) -19);
        Arrays.fill(bArr, 2981, 2984, (byte) 33);
        Arrays.fill(bArr, 2984, 2987, (byte) -19);
        Arrays.fill(bArr, 2987, 2990, (byte) 33);
        Arrays.fill(bArr, 2990, 2998, (byte) -19);
        bArr[2998] = 33;
        Arrays.fill(bArr, LMErr.MAX_NERR, WinError.ERROR_SPOOL_FILE_NOT_FOUND, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_SPOOL_FILE_NOT_FOUND, WinError.ERROR_PRINT_MONITOR_ALREADY_INSTALLED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_PRINT_MONITOR_ALREADY_INSTALLED, WinError.ERROR_SUCCESS_RESTART_REQUIRED, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_SUCCESS_RESTART_REQUIRED, WinError.ERROR_PRINTER_DRIVER_BLOCKED, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_PRINTER_DRIVER_BLOCKED, WinError.ERROR_FAIL_REBOOT_REQUIRED, (byte) -87);
        bArr[3017] = 33;
        Arrays.fill(bArr, WinError.ERROR_FAIL_REBOOT_INITIATED, 3022, (byte) -87);
        Arrays.fill(bArr, 3022, 3031, (byte) 33);
        bArr[3031] = -87;
        Arrays.fill(bArr, 3032, 3047, (byte) 33);
        Arrays.fill(bArr, 3047, 3056, (byte) -87);
        Arrays.fill(bArr, 3056, 3073, (byte) 33);
        Arrays.fill(bArr, 3073, 3076, (byte) -87);
        bArr[3076] = 33;
        Arrays.fill(bArr, 3077, 3085, (byte) -19);
        bArr[3085] = 33;
        Arrays.fill(bArr, 3086, 3089, (byte) -19);
        bArr[3089] = 33;
        Arrays.fill(bArr, 3090, 3113, (byte) -19);
        bArr[3113] = 33;
        Arrays.fill(bArr, 3114, 3124, (byte) -19);
        bArr[3124] = 33;
        Arrays.fill(bArr, 3125, 3130, (byte) -19);
        Arrays.fill(bArr, 3130, 3134, (byte) 33);
        Arrays.fill(bArr, 3134, 3141, (byte) -87);
        bArr[3141] = 33;
        Arrays.fill(bArr, 3142, 3145, (byte) -87);
        bArr[3145] = 33;
        Arrays.fill(bArr, 3146, 3150, (byte) -87);
        Arrays.fill(bArr, 3150, 3157, (byte) 33);
        Arrays.fill(bArr, 3157, 3159, (byte) -87);
        Arrays.fill(bArr, 3159, 3168, (byte) 33);
        Arrays.fill(bArr, 3168, 3170, (byte) -19);
        Arrays.fill(bArr, 3170, 3174, (byte) 33);
        Arrays.fill(bArr, 3174, 3184, (byte) -87);
        Arrays.fill(bArr, 3184, 3202, (byte) 33);
        Arrays.fill(bArr, 3202, 3204, (byte) -87);
        bArr[3204] = 33;
        Arrays.fill(bArr, 3205, 3213, (byte) -19);
        bArr[3213] = 33;
        Arrays.fill(bArr, 3214, 3217, (byte) -19);
        bArr[3217] = 33;
        Arrays.fill(bArr, 3218, 3241, (byte) -19);
        bArr[3241] = 33;
        Arrays.fill(bArr, 3242, 3252, (byte) -19);
        bArr[3252] = 33;
        Arrays.fill(bArr, 3253, 3258, (byte) -19);
        Arrays.fill(bArr, 3258, 3262, (byte) 33);
        Arrays.fill(bArr, 3262, 3269, (byte) -87);
        bArr[3269] = 33;
        Arrays.fill(bArr, 3270, 3273, (byte) -87);
        bArr[3273] = 33;
        Arrays.fill(bArr, 3274, 3278, (byte) -87);
        Arrays.fill(bArr, 3278, 3285, (byte) 33);
        Arrays.fill(bArr, 3285, 3287, (byte) -87);
        Arrays.fill(bArr, 3287, 3294, (byte) 33);
        bArr[3294] = -19;
        bArr[3295] = 33;
        Arrays.fill(bArr, 3296, 3298, (byte) -19);
        Arrays.fill(bArr, 3298, 3302, (byte) 33);
        Arrays.fill(bArr, 3302, 3312, (byte) -87);
        Arrays.fill(bArr, 3312, 3330, (byte) 33);
        Arrays.fill(bArr, 3330, 3332, (byte) -87);
        bArr[3332] = 33;
        Arrays.fill(bArr, 3333, 3341, (byte) -19);
        bArr[3341] = 33;
        Arrays.fill(bArr, 3342, 3345, (byte) -19);
        bArr[3345] = 33;
        Arrays.fill(bArr, 3346, 3369, (byte) -19);
        bArr[3369] = 33;
        Arrays.fill(bArr, 3370, 3386, (byte) -19);
        Arrays.fill(bArr, 3386, 3390, (byte) 33);
        Arrays.fill(bArr, 3390, 3396, (byte) -87);
        Arrays.fill(bArr, 3396, 3398, (byte) 33);
        Arrays.fill(bArr, 3398, 3401, (byte) -87);
        bArr[3401] = 33;
        Arrays.fill(bArr, 3402, 3406, (byte) -87);
        Arrays.fill(bArr, 3406, 3415, (byte) 33);
        bArr[3415] = -87;
        Arrays.fill(bArr, 3416, 3424, (byte) 33);
        Arrays.fill(bArr, 3424, 3426, (byte) -19);
        Arrays.fill(bArr, 3426, 3430, (byte) 33);
        Arrays.fill(bArr, 3430, 3440, (byte) -87);
        Arrays.fill(bArr, 3440, 3585, (byte) 33);
        Arrays.fill(bArr, 3585, 3631, (byte) -19);
        bArr[3631] = 33;
        bArr[3632] = -19;
        bArr[3633] = -87;
        Arrays.fill(bArr, 3634, 3636, (byte) -19);
        Arrays.fill(bArr, 3636, 3643, (byte) -87);
        Arrays.fill(bArr, 3643, 3648, (byte) 33);
        Arrays.fill(bArr, 3648, 3654, (byte) -19);
        Arrays.fill(bArr, 3654, 3663, (byte) -87);
        bArr[3663] = 33;
        Arrays.fill(bArr, 3664, 3674, (byte) -87);
        Arrays.fill(bArr, 3674, 3713, (byte) 33);
        Arrays.fill(bArr, 3713, 3715, (byte) -19);
        bArr[3715] = 33;
        bArr[3716] = -19;
        Arrays.fill(bArr, 3717, 3719, (byte) 33);
        Arrays.fill(bArr, 3719, 3721, (byte) -19);
        bArr[3721] = 33;
        bArr[3722] = -19;
        Arrays.fill(bArr, 3723, 3725, (byte) 33);
        bArr[3725] = -19;
        Arrays.fill(bArr, 3726, 3732, (byte) 33);
        Arrays.fill(bArr, 3732, 3736, (byte) -19);
        bArr[3736] = 33;
        Arrays.fill(bArr, 3737, 3744, (byte) -19);
        bArr[3744] = 33;
        Arrays.fill(bArr, 3745, 3748, (byte) -19);
        bArr[3748] = 33;
        bArr[3749] = -19;
        bArr[3750] = 33;
        bArr[3751] = -19;
        Arrays.fill(bArr, 3752, 3754, (byte) 33);
        Arrays.fill(bArr, 3754, 3756, (byte) -19);
        bArr[3756] = 33;
        Arrays.fill(bArr, 3757, 3759, (byte) -19);
        bArr[3759] = 33;
        bArr[3760] = -19;
        bArr[3761] = -87;
        Arrays.fill(bArr, 3762, 3764, (byte) -19);
        Arrays.fill(bArr, 3764, 3770, (byte) -87);
        bArr[3770] = 33;
        Arrays.fill(bArr, 3771, 3773, (byte) -87);
        bArr[3773] = -19;
        Arrays.fill(bArr, 3774, 3776, (byte) 33);
        Arrays.fill(bArr, 3776, 3781, (byte) -19);
        bArr[3781] = 33;
        bArr[3782] = -87;
        bArr[3783] = 33;
        Arrays.fill(bArr, 3784, 3790, (byte) -87);
        Arrays.fill(bArr, 3790, 3792, (byte) 33);
        Arrays.fill(bArr, 3792, 3802, (byte) -87);
        Arrays.fill(bArr, 3802, 3864, (byte) 33);
        Arrays.fill(bArr, 3864, 3866, (byte) -87);
        Arrays.fill(bArr, 3866, 3872, (byte) 33);
        Arrays.fill(bArr, 3872, 3882, (byte) -87);
        Arrays.fill(bArr, 3882, 3893, (byte) 33);
        bArr[3893] = -87;
        bArr[3894] = 33;
        bArr[3895] = -87;
        bArr[3896] = 33;
        bArr[3897] = -87;
        Arrays.fill(bArr, 3898, 3902, (byte) 33);
        Arrays.fill(bArr, 3902, 3904, (byte) -87);
        Arrays.fill(bArr, 3904, 3912, (byte) -19);
        bArr[3912] = 33;
        Arrays.fill(bArr, 3913, 3946, (byte) -19);
        Arrays.fill(bArr, 3946, 3953, (byte) 33);
        Arrays.fill(bArr, 3953, 3973, (byte) -87);
        bArr[3973] = 33;
        Arrays.fill(bArr, 3974, 3980, (byte) -87);
        Arrays.fill(bArr, 3980, 3984, (byte) 33);
        Arrays.fill(bArr, 3984, 3990, (byte) -87);
        bArr[3990] = 33;
        bArr[3991] = -87;
        bArr[3992] = 33;
        Arrays.fill(bArr, 3993, 4014, (byte) -87);
        Arrays.fill(bArr, 4014, 4017, (byte) 33);
        Arrays.fill(bArr, 4017, 4024, (byte) -87);
        bArr[4024] = 33;
        bArr[4025] = -87;
        Arrays.fill(bArr, 4026, 4256, (byte) 33);
        Arrays.fill(bArr, 4256, 4294, (byte) -19);
        Arrays.fill(bArr, 4294, WinError.ERROR_MEDIA_OFFLINE, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_MEDIA_OFFLINE, 4343, (byte) -19);
        Arrays.fill(bArr, 4343, WinError.ERROR_REMOTE_STORAGE_MEDIA_ERROR, (byte) 33);
        bArr[4352] = -19;
        bArr[4353] = 33;
        Arrays.fill(bArr, 4354, 4356, (byte) -19);
        bArr[4356] = 33;
        Arrays.fill(bArr, 4357, 4360, (byte) -19);
        bArr[4360] = 33;
        bArr[4361] = -19;
        bArr[4362] = 33;
        Arrays.fill(bArr, 4363, 4365, (byte) -19);
        bArr[4365] = 33;
        Arrays.fill(bArr, 4366, 4371, (byte) -19);
        Arrays.fill(bArr, 4371, 4412, (byte) 33);
        bArr[4412] = -19;
        bArr[4413] = 33;
        bArr[4414] = -19;
        bArr[4415] = 33;
        bArr[4416] = -19;
        Arrays.fill(bArr, 4417, 4428, (byte) 33);
        bArr[4428] = -19;
        bArr[4429] = 33;
        bArr[4430] = -19;
        bArr[4431] = 33;
        bArr[4432] = -19;
        Arrays.fill(bArr, 4433, 4436, (byte) 33);
        Arrays.fill(bArr, 4436, 4438, (byte) -19);
        Arrays.fill(bArr, 4438, 4441, (byte) 33);
        bArr[4441] = -19;
        Arrays.fill(bArr, 4442, 4447, (byte) 33);
        Arrays.fill(bArr, 4447, 4450, (byte) -19);
        bArr[4450] = 33;
        bArr[4451] = -19;
        bArr[4452] = 33;
        bArr[4453] = -19;
        bArr[4454] = 33;
        bArr[4455] = -19;
        bArr[4456] = 33;
        bArr[4457] = -19;
        Arrays.fill(bArr, 4458, 4461, (byte) 33);
        Arrays.fill(bArr, 4461, 4463, (byte) -19);
        Arrays.fill(bArr, 4463, 4466, (byte) 33);
        Arrays.fill(bArr, 4466, 4468, (byte) -19);
        bArr[4468] = 33;
        bArr[4469] = -19;
        Arrays.fill(bArr, 4470, 4510, (byte) 33);
        bArr[4510] = -19;
        Arrays.fill(bArr, 4511, 4520, (byte) 33);
        bArr[4520] = -19;
        Arrays.fill(bArr, 4521, 4523, (byte) 33);
        bArr[4523] = -19;
        Arrays.fill(bArr, 4524, 4526, (byte) 33);
        Arrays.fill(bArr, 4526, 4528, (byte) -19);
        Arrays.fill(bArr, 4528, 4535, (byte) 33);
        Arrays.fill(bArr, 4535, 4537, (byte) -19);
        bArr[4537] = 33;
        bArr[4538] = -19;
        bArr[4539] = 33;
        Arrays.fill(bArr, 4540, 4547, (byte) -19);
        Arrays.fill(bArr, 4547, 4587, (byte) 33);
        bArr[4587] = -19;
        Arrays.fill(bArr, 4588, 4592, (byte) 33);
        bArr[4592] = -19;
        Arrays.fill(bArr, 4593, 4601, (byte) 33);
        bArr[4601] = -19;
        Arrays.fill(bArr, 4602, 7680, (byte) 33);
        Arrays.fill(bArr, 7680, 7836, (byte) -19);
        Arrays.fill(bArr, 7836, 7840, (byte) 33);
        Arrays.fill(bArr, 7840, 7930, (byte) -19);
        Arrays.fill(bArr, 7930, GL.GL_VENDOR, (byte) 33);
        Arrays.fill(bArr, GL.GL_VENDOR, 7958, (byte) -19);
        Arrays.fill(bArr, 7958, 7960, (byte) 33);
        Arrays.fill(bArr, 7960, 7966, (byte) -19);
        Arrays.fill(bArr, 7966, 7968, (byte) 33);
        Arrays.fill(bArr, 7968, WinError.FRS_ERR_SERVICE_COMM, (byte) -19);
        Arrays.fill(bArr, WinError.FRS_ERR_SERVICE_COMM, WinError.FRS_ERR_AUTHENTICATION, (byte) 33);
        Arrays.fill(bArr, WinError.FRS_ERR_AUTHENTICATION, WinError.FRS_ERR_SYSVOL_POPULATE_TIMEOUT, (byte) -19);
        Arrays.fill(bArr, WinError.FRS_ERR_SYSVOL_POPULATE_TIMEOUT, WinError.FRS_ERR_SYSVOL_DEMOTE, (byte) 33);
        Arrays.fill(bArr, WinError.FRS_ERR_SYSVOL_DEMOTE, 8024, (byte) -19);
        bArr[8024] = 33;
        bArr[8025] = -19;
        bArr[8026] = 33;
        bArr[8027] = -19;
        bArr[8028] = 33;
        bArr[8029] = -19;
        bArr[8030] = 33;
        Arrays.fill(bArr, 8031, 8062, (byte) -19);
        Arrays.fill(bArr, 8062, 8064, (byte) 33);
        Arrays.fill(bArr, 8064, 8117, (byte) -19);
        bArr[8117] = 33;
        Arrays.fill(bArr, 8118, 8125, (byte) -19);
        bArr[8125] = 33;
        bArr[8126] = -19;
        Arrays.fill(bArr, 8127, 8130, (byte) 33);
        Arrays.fill(bArr, 8130, 8133, (byte) -19);
        bArr[8133] = 33;
        Arrays.fill(bArr, 8134, 8141, (byte) -19);
        Arrays.fill(bArr, 8141, 8144, (byte) 33);
        Arrays.fill(bArr, 8144, 8148, (byte) -19);
        Arrays.fill(bArr, 8148, 8150, (byte) 33);
        Arrays.fill(bArr, 8150, 8156, (byte) -19);
        Arrays.fill(bArr, 8156, 8160, (byte) 33);
        Arrays.fill(bArr, 8160, 8173, (byte) -19);
        Arrays.fill(bArr, 8173, 8178, (byte) 33);
        Arrays.fill(bArr, 8178, 8181, (byte) -19);
        bArr[8181] = 33;
        Arrays.fill(bArr, 8182, 8189, (byte) -19);
        Arrays.fill(bArr, 8189, WinError.ERROR_DS_BAD_ATT_SCHEMA_SYNTAX, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DS_BAD_ATT_SCHEMA_SYNTAX, WinError.ERROR_DS_SECURITY_CHECKING_ERROR, (byte) -87);
        Arrays.fill(bArr, WinError.ERROR_DS_SECURITY_CHECKING_ERROR, WinError.ERROR_DS_GCVERIFY_ERROR, (byte) 33);
        bArr[8417] = -87;
        Arrays.fill(bArr, 8418, WinError.ERROR_DS_DST_NC_MISMATCH, (byte) 33);
        bArr[8486] = -19;
        Arrays.fill(bArr, WinError.ERROR_DS_NOT_AUTHORITIVE_FOR_DST_NC, WinError.ERROR_DS_PDC_OPERATION_IN_PROGRESS, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DS_PDC_OPERATION_IN_PROGRESS, WinError.ERROR_DS_ILLEGAL_XDOM_MOVE_OPERATION, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_DS_ILLEGAL_XDOM_MOVE_OPERATION, WinError.ERROR_DS_NC_MUST_HAVE_NC_PARENT, (byte) 33);
        bArr[8494] = -19;
        Arrays.fill(bArr, WinError.ERROR_DS_CR_IMPOSSIBLE_TO_VALIDATE, WinError.ERROR_DS_RESERVED_LINK_ID, (byte) 33);
        Arrays.fill(bArr, WinError.ERROR_DS_RESERVED_LINK_ID, WinError.ERROR_DS_MODIFYDN_DISALLOWED_BY_INSTANCE_TYPE, (byte) -19);
        Arrays.fill(bArr, WinError.ERROR_DS_MODIFYDN_DISALLOWED_BY_INSTANCE_TYPE, 12293, (byte) 33);
        bArr[12293] = -87;
        bArr[12294] = 33;
        bArr[12295] = -19;
        Arrays.fill(bArr, 12296, 12321, (byte) 33);
        Arrays.fill(bArr, 12321, 12330, (byte) -19);
        Arrays.fill(bArr, 12330, 12336, (byte) -87);
        bArr[12336] = 33;
        Arrays.fill(bArr, 12337, 12342, (byte) -87);
        Arrays.fill(bArr, 12342, 12353, (byte) 33);
        Arrays.fill(bArr, 12353, 12437, (byte) -19);
        Arrays.fill(bArr, 12437, 12441, (byte) 33);
        Arrays.fill(bArr, 12441, 12443, (byte) -87);
        Arrays.fill(bArr, 12443, 12445, (byte) 33);
        Arrays.fill(bArr, 12445, 12447, (byte) -87);
        Arrays.fill(bArr, 12447, 12449, (byte) 33);
        Arrays.fill(bArr, 12449, 12539, (byte) -19);
        bArr[12539] = 33;
        Arrays.fill(bArr, 12540, 12543, (byte) -87);
        Arrays.fill(bArr, 12543, 12549, (byte) 33);
        Arrays.fill(bArr, 12549, 12589, (byte) -19);
        Arrays.fill(bArr, 12589, 19968, (byte) 33);
        Arrays.fill(bArr, 19968, 40870, (byte) -19);
        Arrays.fill(bArr, 40870, 44032, (byte) 33);
        Arrays.fill(bArr, 44032, 55204, (byte) -19);
        Arrays.fill(bArr, 55204, 55296, (byte) 33);
        Arrays.fill(bArr, 57344, 65534, (byte) 33);
    }

    public static char highSurrogate(int i) {
        return (char) (((i - 65536) >> 10) + 55296);
    }

    public static boolean isContent(int i) {
        if (i >= 65536 || (CHARS[i] & 32) == 0) {
            return 65536 <= i && i <= 1114111;
        }
        return true;
    }

    public static boolean isHighSurrogate(int i) {
        return 55296 <= i && i <= 56319;
    }

    public static boolean isInvalid(int i) {
        return !isValid(i);
    }

    public static boolean isLowSurrogate(int i) {
        return 56320 <= i && i <= 57343;
    }

    public static boolean isMarkup(int i) {
        return i == 60 || i == 38 || i == 37;
    }

    public static boolean isNCName(int i) {
        return i < 65536 && (CHARS[i] & 128) != 0;
    }

    public static boolean isNCNameStart(int i) {
        return i < 65536 && (CHARS[i] & ElementValue.ANNOTATION) != 0;
    }

    public static boolean isName(int i) {
        return i < 65536 && (CHARS[i] & 8) != 0;
    }

    public static boolean isNameStart(int i) {
        return i < 65536 && (CHARS[i] & 4) != 0;
    }

    public static boolean isPubid(int i) {
        return i < 65536 && (CHARS[i] & 16) != 0;
    }

    public static boolean isSpace(int i) {
        return i <= 32 && (CHARS[i] & 2) != 0;
    }

    public static boolean isSupplemental(int i) {
        return i >= 65536 && i <= 1114111;
    }

    public static boolean isValid(int i) {
        return (i < 65536 && (CHARS[i] & 1) != 0) || (65536 <= i && i <= 1114111);
    }

    public static boolean isValidIANAEncoding(String str) {
        int length;
        char cCharAt;
        if (str == null || (length = str.length()) <= 0 || (((cCharAt = str.charAt(0)) < 'A' || cCharAt > 'Z') && (cCharAt < 'a' || cCharAt > 'z'))) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            char cCharAt2 = str.charAt(i);
            if ((cCharAt2 < 'A' || cCharAt2 > 'Z') && ((cCharAt2 < 'a' || cCharAt2 > 'z') && !((cCharAt2 >= '0' && cCharAt2 <= '9') || cCharAt2 == '.' || cCharAt2 == '_' || cCharAt2 == '-'))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidJavaEncoding(String str) {
        int length;
        if (str == null || (length = str.length()) <= 0) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < 'a' || cCharAt > 'z') && !((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '.' || cCharAt == '_' || cCharAt == '-'))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidNCName(String str) {
        int length = str.length();
        if (length == 0 || !isNCNameStart(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (!isNCName(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidName(String str) {
        int length = str.length();
        if (length == 0 || !isNameStart(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (!isName(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidNmtoken(String str) {
        int length = str.length();
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!isName(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static char lowSurrogate(int i) {
        return (char) (((i - 65536) & 1023) + 56320);
    }

    public static int supplemental(char c, char c2) {
        return ((c - 55296) * 1024) + (c2 - 56320) + 65536;
    }

    public static String trim(String str) {
        int length = str.length() - 1;
        int i = 0;
        while (i <= length && isSpace(str.charAt(i))) {
            i++;
        }
        int i2 = length;
        while (i2 >= i && isSpace(str.charAt(i2))) {
            i2--;
        }
        if (i == 0 && i2 == length) {
            return str;
        }
        return i > length ? "" : str.substring(i, i2 + 1);
    }
}
