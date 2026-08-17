package com.sun.jna.platform.win32;

import com.sun.jna.ptr.IntByReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Rasapi32Util {
    public static final Map CONNECTION_STATE_TEXT;
    private static final int RASP_PppIp = 32801;
    private static Object phoneBookMutex = new Object();

    public static class Ras32Exception extends RuntimeException {
        private static final long serialVersionUID = 1;
        private final int code;

        public Ras32Exception(int i) {
            super(Rasapi32Util.getRasErrorString(i));
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }
    }

    static {
        HashMap map = new HashMap();
        CONNECTION_STATE_TEXT = map;
        map.put(0, "Opening the port...");
        map.put(1, "Port has been opened successfully");
        map.put(2, "Connecting to the device...");
        map.put(3, "The device has connected successfully.");
        map.put(4, "All devices in the device chain have successfully connected.");
        map.put(5, "Verifying the user name and password...");
        map.put(6, "An authentication event has occurred.");
        map.put(7, "Requested another validation attempt with a new user.");
        map.put(8, "Server has requested a callback number.");
        map.put(9, "The client has requested to change the password");
        map.put(10, "Registering your computer on the network...");
        map.put(11, "The link-speed calculation phase is starting...");
        map.put(12, "An authentication request is being acknowledged.");
        map.put(13, "Reauthentication (after callback) is starting.");
        map.put(14, "The client has successfully completed authentication.");
        map.put(15, "The line is about to disconnect for callback.");
        map.put(16, "Delaying to give the modem time to reset for callback.");
        map.put(17, "Waiting for an incoming call from server.");
        map.put(18, "Projection result information is available.");
        map.put(19, "User authentication is being initiated or retried.");
        map.put(20, "Client has been called back and is about to resume authentication.");
        map.put(21, "Logging on to the network...");
        map.put(22, "Subentry has been connected");
        map.put(23, "Subentry has been disconnected");
        map.put(4096, "Terminal state supported by RASPHONE.EXE.");
        map.put(4097, "Retry authentication state supported by RASPHONE.EXE.");
        map.put(4098, "Callback state supported by RASPHONE.EXE.");
        map.put(4099, "Change password state supported by RASPHONE.EXE.");
        map.put(Integer.valueOf(WinError.ERROR_DHCP_ADDRESS_CONFLICT), "Displaying authentication UI");
        map.put(8192, "Connected to remote server successfully");
        map.put(8193, "Disconnected");
    }

    public static WinNT.HANDLE dialEntry(String str) throws Ras32Exception {
        Rasapi32 rasapi32;
        WinRas.RASCREDENTIALS.ByReference byReference = new WinRas.RASCREDENTIALS.ByReference();
        synchronized (phoneBookMutex) {
            byReference.dwMask = 7;
            rasapi32 = Rasapi32.INSTANCE;
            int iRasGetCredentials = rasapi32.RasGetCredentials(null, str, byReference);
            if (iRasGetCredentials != 0) {
                throw new Ras32Exception(iRasGetCredentials);
            }
        }
        WinRas.RASDIALPARAMS.ByReference byReference2 = new WinRas.RASDIALPARAMS.ByReference();
        System.arraycopy(str.toCharArray(), 0, byReference2.szEntryName, 0, str.length());
        char[] cArr = byReference.szUserName;
        System.arraycopy(cArr, 0, byReference2.szUserName, 0, cArr.length);
        char[] cArr2 = byReference.szPassword;
        System.arraycopy(cArr2, 0, byReference2.szPassword, 0, cArr2.length);
        char[] cArr3 = byReference.szDomain;
        System.arraycopy(cArr3, 0, byReference2.szDomain, 0, cArr3.length);
        WinNT.HANDLEByReference hANDLEByReference = new WinNT.HANDLEByReference();
        int iRasDial = rasapi32.RasDial(null, null, byReference2, 0, null, hANDLEByReference);
        if (iRasDial == 0) {
            return hANDLEByReference.getValue();
        }
        if (hANDLEByReference.getValue() != null) {
            rasapi32.RasHangUp(hANDLEByReference.getValue());
        }
        throw new Ras32Exception(iRasDial);
    }

    public static WinRas.RASPPPIP getIPProjection(WinNT.HANDLE handle) throws Ras32Exception {
        WinRas.RASPPPIP raspppip = new WinRas.RASPPPIP();
        IntByReference intByReference = new IntByReference(raspppip.size());
        raspppip.write();
        int iRasGetProjectionInfo = Rasapi32.INSTANCE.RasGetProjectionInfo(handle, RASP_PppIp, raspppip.getPointer(), intByReference);
        if (iRasGetProjectionInfo != 0) {
            throw new Ras32Exception(iRasGetProjectionInfo);
        }
        raspppip.read();
        return raspppip;
    }

    public static WinRas.RASDIALPARAMS getPhoneBookDialingParams(String str) throws Ras32Exception {
        WinRas.RASDIALPARAMS.ByReference byReference;
        synchronized (phoneBookMutex) {
            try {
                byReference = new WinRas.RASDIALPARAMS.ByReference();
                System.arraycopy(byReference.szEntryName, 0, str.toCharArray(), 0, str.length());
                int iRasGetEntryDialParams = Rasapi32.INSTANCE.RasGetEntryDialParams(null, byReference, new WinDef.BOOLByReference());
                if (iRasGetEntryDialParams != 0) {
                    throw new Ras32Exception(iRasGetEntryDialParams);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return byReference;
    }

    public static WinRas.RASENTRY.ByReference getPhoneBookEntry(String str) throws Ras32Exception {
        WinRas.RASENTRY.ByReference byReference;
        synchronized (phoneBookMutex) {
            try {
                byReference = new WinRas.RASENTRY.ByReference();
                int iRasGetEntryProperties = Rasapi32.INSTANCE.RasGetEntryProperties(null, str, byReference, new IntByReference(byReference.size()), null, null);
                if (iRasGetEntryProperties != 0) {
                    throw new Ras32Exception(iRasGetEntryProperties);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return byReference;
    }

    public static WinNT.HANDLE getRasConnection(String str) throws Ras32Exception {
        IntByReference intByReference = new IntByReference(0);
        IntByReference intByReference2 = new IntByReference();
        int iRasEnumConnections = Rasapi32.INSTANCE.RasEnumConnections(null, intByReference, intByReference2);
        if (iRasEnumConnections != 0 && iRasEnumConnections != 603) {
            throw new Ras32Exception(iRasEnumConnections);
        }
        if (intByReference.getValue() == 0) {
            return null;
        }
        WinRas.RASCONN[] rasconnArr = new WinRas.RASCONN[intByReference2.getValue()];
        for (int i = 0; i < intByReference2.getValue(); i++) {
            rasconnArr[i] = new WinRas.RASCONN();
        }
        int iRasEnumConnections2 = Rasapi32.INSTANCE.RasEnumConnections(rasconnArr, new IntByReference(rasconnArr[0].dwSize * intByReference2.getValue()), intByReference2);
        if (iRasEnumConnections2 != 0) {
            throw new Ras32Exception(iRasEnumConnections2);
        }
        for (int i2 = 0; i2 < intByReference2.getValue(); i2++) {
            if (new String(rasconnArr[i2].szEntryName).equals(str)) {
                return rasconnArr[i2].hrasconn;
            }
        }
        return null;
    }

    public static String getRasConnectionStatusText(int i) {
        Map map = CONNECTION_STATE_TEXT;
        return !map.containsKey(Integer.valueOf(i)) ? Integer.toString(i) : (String) map.get(Integer.valueOf(i));
    }

    public static String getRasErrorString(int i) {
        char[] cArr = new char[1024];
        if (Rasapi32.INSTANCE.RasGetErrorString(i, cArr, 1024) != 0) {
            return "Unknown error " + i;
        }
        int i2 = 0;
        while (i2 < 1024 && cArr[i2] != 0) {
            i2++;
        }
        return new String(cArr, 0, i2);
    }

    public static void hangupRasConnection(String str) throws Ras32Exception {
        int iRasHangUp;
        WinNT.HANDLE rasConnection = getRasConnection(str);
        if (rasConnection != null && (iRasHangUp = Rasapi32.INSTANCE.RasHangUp(rasConnection)) != 0) {
            throw new Ras32Exception(iRasHangUp);
        }
    }

    public static void setPhoneBookEntry(String str, WinRas.RASENTRY.ByReference byReference) throws Ras32Exception {
        synchronized (phoneBookMutex) {
            try {
                int iRasSetEntryProperties = Rasapi32.INSTANCE.RasSetEntryProperties(null, str, byReference, byReference.size(), null, 0);
                if (iRasSetEntryProperties != 0) {
                    throw new Ras32Exception(iRasSetEntryProperties);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void hangupRasConnection(WinNT.HANDLE handle) throws Ras32Exception {
        int iRasHangUp;
        if (handle != null && (iRasHangUp = Rasapi32.INSTANCE.RasHangUp(handle)) != 0) {
            throw new Ras32Exception(iRasHangUp);
        }
    }

    public static WinNT.HANDLE dialEntry(String str, WinRas.RasDialFunc2 rasDialFunc2) throws Ras32Exception {
        Rasapi32 rasapi32;
        WinRas.RASCREDENTIALS.ByReference byReference = new WinRas.RASCREDENTIALS.ByReference();
        synchronized (phoneBookMutex) {
            byReference.dwMask = 7;
            rasapi32 = Rasapi32.INSTANCE;
            int iRasGetCredentials = rasapi32.RasGetCredentials(null, str, byReference);
            if (iRasGetCredentials != 0) {
                throw new Ras32Exception(iRasGetCredentials);
            }
        }
        WinRas.RASDIALPARAMS.ByReference byReference2 = new WinRas.RASDIALPARAMS.ByReference();
        System.arraycopy(str.toCharArray(), 0, byReference2.szEntryName, 0, str.length());
        char[] cArr = byReference.szUserName;
        System.arraycopy(cArr, 0, byReference2.szUserName, 0, cArr.length);
        char[] cArr2 = byReference.szPassword;
        System.arraycopy(cArr2, 0, byReference2.szPassword, 0, cArr2.length);
        char[] cArr3 = byReference.szDomain;
        System.arraycopy(cArr3, 0, byReference2.szDomain, 0, cArr3.length);
        WinNT.HANDLEByReference hANDLEByReference = new WinNT.HANDLEByReference();
        int iRasDial = rasapi32.RasDial(null, null, byReference2, 2, rasDialFunc2, hANDLEByReference);
        if (iRasDial != 0) {
            if (hANDLEByReference.getValue() != null) {
                rasapi32.RasHangUp(hANDLEByReference.getValue());
            }
            throw new Ras32Exception(iRasDial);
        }
        return hANDLEByReference.getValue();
    }
}
