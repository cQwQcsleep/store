package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Ole32;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WbemcliUtil {
    public static final String DEFAULT_NAMESPACE = "ROOT\\CIMV2";
    public static final WbemcliUtil INSTANCE = new WbemcliUtil();

    public enum NamespaceProperty {
        NAME
    }

    public class WmiResult<T extends Enum<T>> {
        private Map<T, Integer> cimTypeMap;
        private Map<T, List<Object>> propertyMap;
        private int resultCount = 0;
        private Map<T, Integer> vtTypeMap;

        public WmiResult(Class<T> cls) {
            this.propertyMap = new EnumMap(cls);
            this.vtTypeMap = new EnumMap(cls);
            this.cimTypeMap = new EnumMap(cls);
            for (T t : cls.getEnumConstants()) {
                this.propertyMap.put(t, new ArrayList());
                this.vtTypeMap.put(t, 1);
                this.cimTypeMap.put(t, 0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void add(int i, int i2, T t, Object obj) {
            this.propertyMap.get(t).add(obj);
            if (i != 1 && this.vtTypeMap.get(t).equals(1)) {
                this.vtTypeMap.put(t, Integer.valueOf(i));
            }
            if (this.cimTypeMap.get(t).equals(0)) {
                this.cimTypeMap.put(t, Integer.valueOf(i2));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void incrementResultCount() {
            this.resultCount++;
        }

        public int getCIMType(T t) {
            return this.cimTypeMap.get(t).intValue();
        }

        public int getResultCount() {
            return this.resultCount;
        }

        public Object getValue(T t, int i) {
            return this.propertyMap.get(t).get(i);
        }

        public int getVtType(T t) {
            return this.vtTypeMap.get(t).intValue();
        }
    }

    public static Wbemcli.IWbemServices connectServer(String str) {
        Wbemcli.IWbemLocator iWbemLocatorCreate = Wbemcli.IWbemLocator.create();
        if (iWbemLocatorCreate == null) {
            throw new COMException("Failed to create WbemLocator object.");
        }
        Wbemcli.IWbemServices iWbemServicesConnectServer = iWbemLocatorCreate.ConnectServer(str, null, null, null, 0, null, null);
        iWbemLocatorCreate.Release();
        WinNT.HRESULT hresultCoSetProxyBlanket = Ole32.INSTANCE.CoSetProxyBlanket(iWbemServicesConnectServer, 10, 0, null, 3, 3, null, 0);
        if (!COMUtils.FAILED(hresultCoSetProxyBlanket)) {
            return iWbemServicesConnectServer;
        }
        iWbemServicesConnectServer.Release();
        throw new COMException("Could not set proxy blanket.", hresultCoSetProxyBlanket);
    }

    public static boolean hasNamespace(String str) {
        if (str.toUpperCase().startsWith("ROOT\\")) {
            str = str.substring(5);
        }
        WmiResult wmiResultExecute = new WmiQuery("ROOT", "__NAMESPACE", NamespaceProperty.class).execute();
        for (int i = 0; i < wmiResultExecute.getResultCount(); i++) {
            if (str.equalsIgnoreCase((String) wmiResultExecute.getValue(NamespaceProperty.NAME, i))) {
                return true;
            }
        }
        return false;
    }

    public static class WmiQuery<T extends Enum<T>> {
        private String nameSpace;
        private Class<T> propertyEnum;
        private String wmiClassName;

        public WmiQuery(String str, String str2, Class<T> cls) {
            this.nameSpace = str;
            this.wmiClassName = str2;
            this.propertyEnum = cls;
        }

        private static <T extends Enum<T>> WmiResult<T> enumerateProperties(Wbemcli.IEnumWbemClassObject iEnumWbemClassObject, Class<T> cls, int i) throws TimeoutException {
            WbemcliUtil wbemcliUtil = WbemcliUtil.INSTANCE;
            wbemcliUtil.getClass();
            Class<T> cls2 = cls;
            WmiResult<T> wmiResult = wbemcliUtil.new WmiResult<>(cls2);
            int i2 = 1;
            Pointer[] pointerArr = new Pointer[1];
            int i3 = 0;
            IntByReference intByReference = new IntByReference(0);
            HashMap map = new HashMap();
            for (T t : cls2.getEnumConstants()) {
                map.put(t, new WString(t.name()));
            }
            while (iEnumWbemClassObject.getPointer() != Pointer.NULL) {
                WinNT.HRESULT hresultNext = iEnumWbemClassObject.Next(i, i2, pointerArr, intByReference);
                if (hresultNext.intValue() == i2 || hresultNext.intValue() == 262149) {
                    break;
                }
                if (hresultNext.intValue() == 262148) {
                    throw new TimeoutException("No results after " + i + " ms.");
                }
                if (COMUtils.FAILED(hresultNext)) {
                    throw new COMException("Failed to enumerate results.", hresultNext);
                }
                Variant.VARIANT.ByReference byReference = new Variant.VARIANT.ByReference();
                IntByReference intByReference2 = new IntByReference();
                Wbemcli.IWbemClassObject iWbemClassObject = new Wbemcli.IWbemClassObject(pointerArr[i3]);
                T[] enumConstants = cls2.getEnumConstants();
                int length = enumConstants.length;
                int i4 = i3;
                while (i4 < length) {
                    T t2 = enumConstants[i4];
                    int i5 = i4;
                    iWbemClassObject.Get((WString) map.get(t2), 0, byReference, intByReference2, (IntByReference) null);
                    int iIntValue = (byReference.getValue() == null ? Integer.valueOf(i2) : byReference.getVarType()).intValue();
                    int value = intByReference2.getValue();
                    if (iIntValue == 0 || iIntValue == i2) {
                        wmiResult.add(iIntValue, value, t2, null);
                    } else if (iIntValue == 2) {
                        wmiResult.add(iIntValue, value, t2, Short.valueOf(byReference.shortValue()));
                    } else if (iIntValue == 3) {
                        wmiResult.add(iIntValue, value, t2, Integer.valueOf(byReference.intValue()));
                    } else if (iIntValue == 4) {
                        wmiResult.add(iIntValue, value, t2, Float.valueOf(byReference.floatValue()));
                    } else if (iIntValue == 5) {
                        wmiResult.add(iIntValue, value, t2, Double.valueOf(byReference.doubleValue()));
                    } else if (iIntValue == 8) {
                        wmiResult.add(iIntValue, value, t2, byReference.stringValue());
                    } else if (iIntValue == 11) {
                        wmiResult.add(iIntValue, value, t2, Boolean.valueOf(byReference.booleanValue()));
                    } else if (iIntValue == 17) {
                        wmiResult.add(iIntValue, value, t2, Byte.valueOf(byReference.byteValue()));
                    } else if ((iIntValue & 8192) == 8192 || (iIntValue & 13) == 13 || (iIntValue & 9) == 9 || (iIntValue & 4096) == 4096) {
                        wmiResult.add(iIntValue, value, t2, null);
                    } else {
                        wmiResult.add(iIntValue, value, t2, byReference.getValue());
                    }
                    OleAuto.INSTANCE.VariantClear(byReference);
                    i4 = i5 + 1;
                    i2 = 1;
                }
                iWbemClassObject.Release();
                wmiResult.incrementResultCount();
                cls2 = cls;
                i2 = 1;
                i3 = 0;
            }
            return wmiResult;
        }

        private static <T extends Enum<T>> Wbemcli.IEnumWbemClassObject selectProperties(Wbemcli.IWbemServices iWbemServices, WmiQuery<T> wmiQuery) {
            T[] enumConstants = wmiQuery.getPropertyEnum().getEnumConstants();
            StringBuilder sb = new StringBuilder("SELECT ");
            sb.append(enumConstants[0].name());
            for (int i = 1; i < enumConstants.length; i++) {
                sb.append(',');
                sb.append(enumConstants[i].name());
            }
            sb.append(" FROM ");
            sb.append(wmiQuery.getWmiClassName());
            return iWbemServices.ExecQuery("WQL", sb.toString().replaceAll("\\\\", "\\\\\\\\"), 48, null);
        }

        public WmiResult<T> execute(int i) throws TimeoutException {
            if (getPropertyEnum().getEnumConstants().length < 1) {
                w01.a("The query's property enum has no values.");
                return null;
            }
            Wbemcli.IWbemServices iWbemServicesConnectServer = WbemcliUtil.connectServer(getNameSpace());
            try {
                Wbemcli.IEnumWbemClassObject iEnumWbemClassObjectSelectProperties = selectProperties(iWbemServicesConnectServer, this);
                try {
                    WmiResult<T> wmiResultEnumerateProperties = enumerateProperties(iEnumWbemClassObjectSelectProperties, getPropertyEnum(), i);
                    iEnumWbemClassObjectSelectProperties.Release();
                    iWbemServicesConnectServer.Release();
                    return wmiResultEnumerateProperties;
                } catch (Throwable th) {
                    iEnumWbemClassObjectSelectProperties.Release();
                    throw th;
                }
            } catch (Throwable th2) {
                iWbemServicesConnectServer.Release();
                throw th2;
            }
        }

        public String getNameSpace() {
            return this.nameSpace;
        }

        public Class<T> getPropertyEnum() {
            return this.propertyEnum;
        }

        public String getWmiClassName() {
            return this.wmiClassName;
        }

        public void setNameSpace(String str) {
            this.nameSpace = str;
        }

        public void setWmiClassName(String str) {
            this.wmiClassName = str;
        }

        public WmiQuery(String str, Class<T> cls) {
            this(WbemcliUtil.DEFAULT_NAMESPACE, str, cls);
        }

        public WmiResult<T> execute() {
            try {
                return execute(-1);
            } catch (TimeoutException unused) {
                throw new COMException("Got a WMI timeout when infinite wait was specified. This should never happen.");
            }
        }
    }
}
