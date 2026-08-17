package com.sun.jna.platform.win32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.win32.W32APITypeMapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Ddeml extends StdCallLibrary {
    public static final int APPCLASS_MASK = 15;
    public static final int APPCLASS_MONITOR = 1;
    public static final int APPCLASS_STANDARD = 0;
    public static final int APPCMD_CLIENTONLY = 16;
    public static final int APPCMD_FILTERINITS = 32;
    public static final int APPCMD_MASK = 4080;
    public static final int CADV_LATEACK = 65535;
    public static final int CBF_FAIL_ADVISES = 16384;
    public static final int CBF_FAIL_ALLSVRXACTIONS = 258048;
    public static final int CBF_FAIL_CONNECTIONS = 8192;
    public static final int CBF_FAIL_EXECUTES = 32768;
    public static final int CBF_FAIL_POKES = 65536;
    public static final int CBF_FAIL_REQUESTS = 131072;
    public static final int CBF_FAIL_SELFCONNECTIONS = 4096;
    public static final int CBF_SKIP_ALLNOTIFICATIONS = 3932160;
    public static final int CBF_SKIP_CONNECT_CONFIRMS = 262144;
    public static final int CBF_SKIP_DISCONNECTS = 2097152;
    public static final int CBF_SKIP_REGISTRATIONS = 524288;
    public static final int CBF_SKIP_UNREGISTRATIONS = 1048576;
    public static final int CP_WINANSI = 1004;
    public static final int CP_WINNEUTRAL = 1200;
    public static final int CP_WINUNICODE = 1200;
    public static final int DDE_FACK = 32768;
    public static final int DDE_FACKREQ = 32768;
    public static final int DDE_FACKRESERVED = -49408;
    public static final int DDE_FADVRESERVED = -49153;
    public static final int DDE_FAPPSTATUS = 255;
    public static final int DDE_FBUSY = 16384;
    public static final int DDE_FDATRESERVED = -45057;
    public static final int DDE_FDEFERUPD = 16384;
    public static final int DDE_FNOTPROCESSED = 0;
    public static final int DDE_FPOKRESERVED = -8193;
    public static final int DDE_FRELEASE = 8192;
    public static final int DDE_FREQUESTED = 4096;
    public static final int DMLERR_ADVACKTIMEOUT = 16384;
    public static final int DMLERR_BUSY = 16385;
    public static final int DMLERR_DATAACKTIMEOUT = 16386;
    public static final int DMLERR_DLL_NOT_INITIALIZED = 16387;
    public static final int DMLERR_DLL_USAGE = 16388;
    public static final int DMLERR_EXECACKTIMEOUT = 16389;
    public static final int DMLERR_FIRST = 16384;
    public static final int DMLERR_INVALIDPARAMETER = 16390;
    public static final int DMLERR_LAST = 16401;
    public static final int DMLERR_LOW_MEMORY = 16391;
    public static final int DMLERR_MEMORY_ERROR = 16392;
    public static final int DMLERR_NOTPROCESSED = 16393;
    public static final int DMLERR_NO_CONV_ESTABLISHED = 16394;
    public static final int DMLERR_NO_ERROR = 0;
    public static final int DMLERR_POKEACKTIMEOUT = 16395;
    public static final int DMLERR_POSTMSG_FAILED = 16396;
    public static final int DMLERR_REENTRANCY = 16397;
    public static final int DMLERR_SERVER_DIED = 16398;
    public static final int DMLERR_SYS_ERROR = 16399;
    public static final int DMLERR_UNADVACKTIMEOUT = 16400;
    public static final int DMLERR_UNFOUND_QUEUE_ID = 16401;
    public static final int DNS_FILTEROFF = 8;
    public static final int DNS_FILTERON = 4;
    public static final int DNS_REGISTER = 1;
    public static final int DNS_UNREGISTER = 2;
    public static final int EC_DISABLE = 8;
    public static final int EC_ENABLEALL = 0;
    public static final int EC_ENABLEONE = 128;
    public static final int EC_QUERYWAITING = 2;
    public static final int HDATA_APPOWNED = 1;
    public static final Ddeml INSTANCE = (Ddeml) Native.load("user32", Ddeml.class, W32APIOptions.DEFAULT_OPTIONS);
    public static final int MF_CALLBACKS = 134217728;
    public static final int MF_CONV = 1073741824;
    public static final int MF_ERRORS = 268435456;
    public static final int MF_HSZ_INFO = 16777216;
    public static final int MF_LINKS = 536870912;
    public static final int MF_MASK = -16777216;
    public static final int MF_POSTMSGS = 67108864;
    public static final int MF_SENDMSGS = 33554432;
    public static final int MSGF_DDEMGR = 32769;
    public static final int QID_SYNC = -1;
    public static final int ST_ADVISE = 2;
    public static final int ST_BLOCKED = 8;
    public static final int ST_BLOCKNEXT = 128;
    public static final int ST_CLIENT = 16;
    public static final int ST_CONNECTED = 1;
    public static final int ST_INLIST = 64;
    public static final int ST_ISLOCAL = 4;
    public static final int ST_ISSELF = 256;
    public static final int ST_TERMINATED = 32;
    public static final String SZDDESYS_ITEM_FORMATS = "Formats";
    public static final String SZDDESYS_ITEM_HELP = "Help";
    public static final String SZDDESYS_ITEM_RTNMSG = "ReturnMessage";
    public static final String SZDDESYS_ITEM_STATUS = "Status";
    public static final String SZDDESYS_ITEM_SYSITEMS = "SysItems";
    public static final String SZDDESYS_ITEM_TOPICS = "Topics";
    public static final String SZDDESYS_TOPIC = "System";
    public static final String SZDDE_ITEM_ITEMLIST = "TopicItemList";
    public static final int TIMEOUT_ASYNC = -1;
    public static final int XCLASS_BOOL = 4096;
    public static final int XCLASS_DATA = 8192;
    public static final int XCLASS_FLAGS = 16384;
    public static final int XCLASS_MASK = 64512;
    public static final int XCLASS_NOTIFICATION = 32768;
    public static final int XST_ADVACKRCVD = 13;
    public static final int XST_ADVDATAACKRCVD = 16;
    public static final int XST_ADVDATASENT = 15;
    public static final int XST_ADVSENT = 11;
    public static final int XST_CONNECTED = 2;
    public static final int XST_DATARCVD = 6;
    public static final int XST_EXECACKRCVD = 10;
    public static final int XST_EXECSENT = 9;
    public static final int XST_INCOMPLETE = 1;
    public static final int XST_INIT1 = 3;
    public static final int XST_INIT2 = 4;
    public static final int XST_NULL = 0;
    public static final int XST_POKEACKRCVD = 8;
    public static final int XST_POKESENT = 7;
    public static final int XST_REQSENT = 5;
    public static final int XST_UNADVACKRCVD = 14;
    public static final int XST_UNADVSENT = 12;
    public static final int XTYPF_ACKREQ = 8;
    public static final int XTYPF_NOBLOCK = 2;
    public static final int XTYPF_NODATA = 4;
    public static final int XTYP_ADVDATA = 16400;
    public static final int XTYP_ADVREQ = 8226;
    public static final int XTYP_ADVSTART = 4144;
    public static final int XTYP_ADVSTOP = 32832;
    public static final int XTYP_CONNECT = 4194;
    public static final int XTYP_CONNECT_CONFIRM = 32882;
    public static final int XTYP_DISCONNECT = 32962;
    public static final int XTYP_ERROR = 32770;
    public static final int XTYP_EXECUTE = 16464;
    public static final int XTYP_MASK = 240;
    public static final int XTYP_MONITOR = 33010;
    public static final int XTYP_POKE = 16528;
    public static final int XTYP_REGISTER = 32930;
    public static final int XTYP_REQUEST = 8368;
    public static final int XTYP_SHIFT = 4;
    public static final int XTYP_UNREGISTER = 32978;
    public static final int XTYP_WILDCONNECT = 8418;
    public static final int XTYP_XACT_COMPLETE = 32896;

    @Structure.FieldOrder({"cb", "hUser", "hConvPartner", "hszSvcPartner", "hszServiceReq", "hszTopic", "hszItem", "wFmt", "wType", "wStatus", "wConvst", "wLastError", "hConvList", "ConvCtxt", "hwnd", "hwndPartner"})
    public static class CONVINFO extends Structure {
        public CONVCONTEXT ConvCtxt;
        public int cb;
        public HCONVLIST hConvList;
        public HCONV hConvPartner;
        public BaseTSD.DWORD_PTR hUser;
        public HSZ hszItem;
        public HSZ hszServiceReq;
        public HSZ hszSvcPartner;
        public HSZ hszTopic;
        public WinDef.HWND hwnd;
        public WinDef.HWND hwndPartner;
        public int wConvst;
        public int wFmt;
        public int wLastError;
        public int wStatus;
        public int wType;

        @Override // com.sun.jna.Structure
        public void write() {
            this.cb = size();
            super.write();
        }
    }

    @Structure.FieldOrder({"uiLo", "uiHi", "cbData", "Data"})
    public static class DDEML_MSG_HOOK_DATA extends Structure {
        public byte[] Data = new byte[32];
        public int cbData;
        public WinDef.UINT_PTR uiHi;
        public WinDef.UINT_PTR uiLo;
    }

    public interface DdeCallback extends StdCallLibrary.StdCallCallback {
        WinDef.PVOID ddeCallback(int i, int i2, HCONV hconv, HSZ hsz, HSZ hsz2, HDDEDATA hddedata, BaseTSD.ULONG_PTR ulong_ptr, BaseTSD.ULONG_PTR ulong_ptr2);
    }

    public static class HCONV extends PointerType {
    }

    public static class HCONVLIST extends PointerType {
    }

    public static class HDDEDATA extends WinDef.PVOID {
    }

    public static class HSZ extends PointerType {
    }

    @Structure.FieldOrder({"cb", "dwTime", "hTask", "dwRet", "wType", "wFmt", "hConv", "hsz1", "hsz2", "hData", "dwData1", "dwData2", "cc", "cbData", "Data"})
    public static class MONCBSTRUCT extends Structure {
        public byte[] Data = new byte[32];
        public int cb;
        public int cbData;
        public CONVCONTEXT cc;
        public BaseTSD.ULONG_PTR dwData1;
        public BaseTSD.ULONG_PTR dwData2;
        public WinDef.DWORD dwRet;
        public int dwTime;
        public HCONV hConv;
        public HDDEDATA hData;
        public WinNT.HANDLE hTask;
        public HSZ hsz1;
        public HSZ hsz2;
        public int wFmt;
        public int wType;
    }

    @Structure.FieldOrder({"cb", "fConnect", "dwTime", "hTask", "hszSvc", "hszTopic", "hConvClient", "hConvServer"})
    public static class MONCONVSTRUCT extends Structure {
        public WinDef.UINT cb;
        public WinDef.DWORD dwTime;
        public WinDef.BOOL fConnect;
        public HCONV hConvClient;
        public HCONV hConvServer;
        public WinNT.HANDLE hTask;
        public HSZ hszSvc;
        public HSZ hszTopic;
    }

    @Structure.FieldOrder({"cb", "wLastError", "dwTime", "hTask"})
    public static class MONERRSTRUCT extends Structure {
        public int cb;
        public int dwTime;
        public WinNT.HANDLE hTask;
        public int wLastError;
    }

    @Structure.FieldOrder({"cb", "fsAction", "dwTime", "hsz", "hTask", "str"})
    public static class MONHSZSTRUCT extends Structure {
        public int cb;
        public int dwTime;
        public int fsAction;
        public WinNT.HANDLE hTask;
        public HSZ hsz;
        public byte[] str = new byte[1];

        public String getStr() {
            int iFieldOffset = fieldOffset("str");
            return W32APITypeMapper.DEFAULT == W32APITypeMapper.UNICODE ? getPointer().getWideString(iFieldOffset) : getPointer().getString(iFieldOffset);
        }

        @Override // com.sun.jna.Structure
        public void read() {
            readField("cb");
            allocateMemory(this.cb);
            super.read();
        }

        @Override // com.sun.jna.Structure
        public void write() {
            this.cb = calculateSize(true);
            super.write();
        }
    }

    @Structure.FieldOrder({"cb", "dwTime", "hTask", "fEstablished", "fNoData", "hszSvc", "hszTopic", "hszItem", "wFmt", "fServer", "hConvServer", "hConvClient"})
    public static class MONLINKSTRUCT extends Structure {
        public int cb;
        public int dwTime;
        public WinDef.BOOL fEstablished;
        public WinDef.BOOL fNoData;
        public WinDef.BOOL fServer;
        public HCONV hConvClient;
        public HCONV hConvServer;
        public WinNT.HANDLE hTask;
        public HSZ hszItem;
        public HSZ hszSvc;
        public HSZ hszTopic;
        public int wFmt;
    }

    @Structure.FieldOrder({"cb", "hwndTo", "dwTime", "hTask", "wMsg", "wParam", "lParam", "dmhd"})
    public static class MONMSGSTRUCT extends Structure {
        public int cb;
        public DDEML_MSG_HOOK_DATA dmhd;
        public int dwTime;
        public WinNT.HANDLE hTask;
        public WinDef.HWND hwndTo;
        public WinDef.LPARAM lParam;
        public int wMsg;
        public WinDef.WPARAM wParam;
    }

    boolean DdeAbandonTransaction(int i, HCONV hconv, int i2);

    Pointer DdeAccessData(HDDEDATA hddedata, WinDef.DWORDByReference dWORDByReference);

    HDDEDATA DdeAddData(HDDEDATA hddedata, Pointer pointer, int i, int i2);

    HDDEDATA DdeClientTransaction(Pointer pointer, int i, HCONV hconv, HSZ hsz, int i2, int i3, int i4, WinDef.DWORDByReference dWORDByReference);

    HCONV DdeConnect(int i, HSZ hsz, HSZ hsz2, CONVCONTEXT convcontext);

    HCONVLIST DdeConnectList(int i, HSZ hsz, HSZ hsz2, HCONVLIST hconvlist, CONVCONTEXT convcontext);

    HDDEDATA DdeCreateDataHandle(int i, Pointer pointer, int i2, int i3, HSZ hsz, int i4, int i5);

    HSZ DdeCreateStringHandle(int i, String str, int i2);

    boolean DdeDisconnect(HCONV hconv);

    boolean DdeDisconnectList(HCONVLIST hconvlist);

    boolean DdeEnableCallback(int i, HCONV hconv, int i2);

    boolean DdeFreeDataHandle(HDDEDATA hddedata);

    boolean DdeFreeStringHandle(int i, HSZ hsz);

    int DdeGetData(HDDEDATA hddedata, Pointer pointer, int i, int i2);

    int DdeGetLastError(int i);

    boolean DdeImpersonateClient(HCONV hconv);

    int DdeInitialize(WinDef.DWORDByReference dWORDByReference, DdeCallback ddeCallback, int i, int i2);

    boolean DdeKeepStringHandle(int i, HSZ hsz);

    HDDEDATA DdeNameService(int i, HSZ hsz, HSZ hsz2, int i2);

    boolean DdePostAdvise(int i, HSZ hsz, HSZ hsz2);

    int DdeQueryConvInfo(HCONV hconv, int i, CONVINFO convinfo);

    HCONV DdeQueryNextServer(HCONVLIST hconvlist, HCONV hconv);

    int DdeQueryString(int i, HSZ hsz, Pointer pointer, int i2, int i3);

    HCONV DdeReconnect(HCONV hconv);

    boolean DdeSetUserHandle(HCONV hconv, int i, BaseTSD.DWORD_PTR dword_ptr);

    boolean DdeUnaccessData(HDDEDATA hddedata);

    boolean DdeUninitialize(int i);

    @Structure.FieldOrder({"cb", "wFlags", "wCountryID", "iCodePage", "dwLangID", "dwSecurity", "qos"})
    public static class CONVCONTEXT extends Structure {
        public int cb;
        public int dwLangID;
        public int dwSecurity;
        public int iCodePage;
        public WinNT.SECURITY_QUALITY_OF_SERVICE qos;
        public int wCountryID;
        public int wFlags;

        public CONVCONTEXT() {
        }

        @Override // com.sun.jna.Structure
        public void write() {
            this.cb = size();
            super.write();
        }

        public CONVCONTEXT(Pointer pointer) {
            super(pointer);
        }
    }

    @Structure.FieldOrder({"service", "topic"})
    public static class HSZPAIR extends Structure {
        public HSZ service;
        public HSZ topic;

        public HSZPAIR(HSZ hsz, HSZ hsz2) {
            this.service = hsz;
            this.topic = hsz2;
        }

        public HSZPAIR() {
        }
    }
}
