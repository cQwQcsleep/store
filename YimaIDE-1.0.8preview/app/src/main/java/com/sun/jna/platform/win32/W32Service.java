package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import defpackage.kka;
import java.io.Closeable;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class W32Service implements Closeable {
    Winsvc.SC_HANDLE _handle;

    public W32Service(Winsvc.SC_HANDLE sc_handle) {
        this._handle = sc_handle;
    }

    private void addShutdownPrivilegeToProcess() {
        WinNT.HANDLEByReference hANDLEByReference = new WinNT.HANDLEByReference();
        WinNT.LUID luid = new WinNT.LUID();
        Advapi32 advapi32 = Advapi32.INSTANCE;
        advapi32.OpenProcessToken(Kernel32.INSTANCE.GetCurrentProcess(), 32, hANDLEByReference);
        advapi32.LookupPrivilegeValue("", WinNT.SE_SHUTDOWN_NAME, luid);
        WinNT.TOKEN_PRIVILEGES token_privileges = new WinNT.TOKEN_PRIVILEGES(1);
        token_privileges.Privileges[0] = new WinNT.LUID_AND_ATTRIBUTES(luid, new WinDef.DWORD(2L));
        advapi32.AdjustTokenPrivileges(hANDLEByReference.getValue(), false, token_privileges, token_privileges.size(), null, new IntByReference());
    }

    private boolean isPendingState(int i) {
        return i == 2 || i == 3 || i == 5 || i == 6;
    }

    private Pointer queryServiceConfig2(int i) {
        IntByReference intByReference = new IntByReference();
        Advapi32 advapi32 = Advapi32.INSTANCE;
        advapi32.QueryServiceConfig2(this._handle, i, Pointer.NULL, 0, intByReference);
        Memory memory = new Memory(intByReference.getValue());
        if (advapi32.QueryServiceConfig2(this._handle, i, memory, intByReference.getValue(), new IntByReference())) {
            return memory;
        }
        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Winsvc.SC_HANDLE sc_handle = this._handle;
        if (sc_handle != null) {
            if (!Advapi32.INSTANCE.CloseServiceHandle(sc_handle)) {
                throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
            }
            this._handle = null;
        }
    }

    public void continueService() {
        waitForNonPendingState();
        if (queryStatus().dwCurrentState == 4) {
            return;
        }
        if (!Advapi32.INSTANCE.ControlService(this._handle, 3, new Winsvc.SERVICE_STATUS())) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        waitForNonPendingState();
        if (queryStatus().dwCurrentState == 4) {
            return;
        }
        f63.a("Unable to continue the service");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public Winsvc.ENUM_SERVICE_STATUS[] enumDependentServices(int i) {
        IntByReference intByReference = new IntByReference(0);
        IntByReference intByReference2 = new IntByReference(0);
        Advapi32 advapi32 = Advapi32.INSTANCE;
        advapi32.EnumDependentServices(this._handle, i, Pointer.NULL, 0, intByReference, intByReference2);
        Kernel32 kernel32 = Kernel32.INSTANCE;
        int iGetLastError = kernel32.GetLastError();
        if (iGetLastError != 234) {
            kka.a(iGetLastError);
            return null;
        }
        Memory memory = new Memory(intByReference.getValue());
        if (!advapi32.EnumDependentServices(this._handle, i, memory, (int) memory.size(), intByReference, intByReference2)) {
            throw new Win32Exception(kernel32.GetLastError());
        }
        if (intByReference2.getValue() == 0) {
            return new Winsvc.ENUM_SERVICE_STATUS[0];
        }
        Winsvc.ENUM_SERVICE_STATUS enum_service_status = (Winsvc.ENUM_SERVICE_STATUS) Structure.newInstance(Winsvc.ENUM_SERVICE_STATUS.class, memory);
        enum_service_status.read();
        return (Winsvc.ENUM_SERVICE_STATUS[]) enum_service_status.toArray(intByReference2.getValue());
    }

    public Winsvc.SERVICE_FAILURE_ACTIONS getFailureActions() {
        return new Winsvc.SERVICE_FAILURE_ACTIONS(queryServiceConfig2(2));
    }

    public boolean getFailureActionsFlag() {
        return new Winsvc.SERVICE_FAILURE_ACTIONS_FLAG(queryServiceConfig2(4)).fFailureActionsOnNonCrashFailures != 0;
    }

    public Winsvc.SC_HANDLE getHandle() {
        return this._handle;
    }

    public void pauseService() {
        waitForNonPendingState();
        if (queryStatus().dwCurrentState == 7) {
            return;
        }
        if (!Advapi32.INSTANCE.ControlService(this._handle, 2, new Winsvc.SERVICE_STATUS())) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        waitForNonPendingState();
        if (queryStatus().dwCurrentState == 7) {
            return;
        }
        f63.a("Unable to pause the service");
    }

    public Winsvc.SERVICE_STATUS_PROCESS queryStatus() {
        IntByReference intByReference = new IntByReference();
        Advapi32 advapi32 = Advapi32.INSTANCE;
        advapi32.QueryServiceStatusEx(this._handle, 0, null, 0, intByReference);
        Winsvc.SERVICE_STATUS_PROCESS service_status_process = new Winsvc.SERVICE_STATUS_PROCESS(intByReference.getValue());
        if (advapi32.QueryServiceStatusEx(this._handle, 0, service_status_process, service_status_process.size(), intByReference)) {
            return service_status_process;
        }
        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
    }

    public int sanitizeWaitTime(int i) {
        int i2 = i / 10;
        if (i2 < 1000) {
            return 1000;
        }
        return i2 > 10000 ? WinError.WSABASEERR : i2;
    }

    public void setFailureActions(List<Winsvc.SC_ACTION> list, int i, String str, String str2) {
        Winsvc.SERVICE_FAILURE_ACTIONS.ByReference byReference = new Winsvc.SERVICE_FAILURE_ACTIONS.ByReference();
        byReference.dwResetPeriod = i;
        byReference.lpRebootMsg = str;
        byReference.lpCommand = str2;
        byReference.cActions = list.size();
        Winsvc.SC_ACTION.ByReference byReference2 = new Winsvc.SC_ACTION.ByReference();
        byReference.lpsaActions = byReference2;
        Winsvc.SC_ACTION[] sc_actionArr = (Winsvc.SC_ACTION[]) byReference2.toArray(list.size());
        boolean z = false;
        int i2 = 0;
        for (Winsvc.SC_ACTION sc_action : list) {
            if (!z && sc_action.type == 2) {
                addShutdownPrivilegeToProcess();
                z = true;
            }
            Winsvc.SC_ACTION sc_action2 = sc_actionArr[i2];
            sc_action2.type = sc_action.type;
            sc_action2.delay = sc_action.delay;
            i2++;
        }
        if (!Advapi32.INSTANCE.ChangeServiceConfig2(this._handle, 2, byReference)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
    }

    public void setFailureActionsFlag(boolean z) {
        Winsvc.SERVICE_FAILURE_ACTIONS_FLAG service_failure_actions_flag = new Winsvc.SERVICE_FAILURE_ACTIONS_FLAG();
        service_failure_actions_flag.fFailureActionsOnNonCrashFailures = z ? 1 : 0;
        if (!Advapi32.INSTANCE.ChangeServiceConfig2(this._handle, 4, service_failure_actions_flag)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
    }

    public void startService() {
        waitForNonPendingState();
        if (queryStatus().dwCurrentState == 4) {
            return;
        }
        if (!Advapi32.INSTANCE.StartService(this._handle, 0, null)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        waitForNonPendingState();
        if (queryStatus().dwCurrentState == 4) {
            return;
        }
        f63.a("Unable to start the service");
    }

    public void stopService(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        waitForNonPendingState();
        if (queryStatus().dwCurrentState == 1) {
            return;
        }
        Winsvc.SERVICE_STATUS service_status = new Winsvc.SERVICE_STATUS();
        if (!Advapi32.INSTANCE.ControlService(this._handle, 1, service_status)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        while (service_status.dwCurrentState != 1) {
            long jCurrentTimeMillis2 = j - (System.currentTimeMillis() - jCurrentTimeMillis);
            if (jCurrentTimeMillis2 < 0) {
                throw new RuntimeException(String.format("Service stop exceeded timeout time of %d ms", Long.valueOf(j)));
            }
            try {
                Thread.sleep(Math.min(sanitizeWaitTime(service_status.dwWaitHint), jCurrentTimeMillis2));
                if (!Advapi32.INSTANCE.QueryServiceStatus(this._handle, service_status)) {
                    throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                }
            } catch (InterruptedException e) {
                rc6.a(e);
                return;
            }
        }
    }

    public void waitForNonPendingState() {
        Winsvc.SERVICE_STATUS_PROCESS service_status_processQueryStatus = queryStatus();
        int i = service_status_processQueryStatus.dwCheckPoint;
        int iGetTickCount = Kernel32.INSTANCE.GetTickCount();
        while (isPendingState(service_status_processQueryStatus.dwCurrentState)) {
            int i2 = service_status_processQueryStatus.dwCheckPoint;
            if (i2 != i) {
                iGetTickCount = Kernel32.INSTANCE.GetTickCount();
                i = i2;
            }
            int iGetTickCount2 = Kernel32.INSTANCE.GetTickCount() - iGetTickCount;
            int i3 = service_status_processQueryStatus.dwWaitHint;
            if (iGetTickCount2 > i3) {
                f63.a("Timeout waiting for service to change to a non-pending state.");
                return;
            }
            try {
                Thread.sleep(sanitizeWaitTime(i3));
                service_status_processQueryStatus = queryStatus();
            } catch (InterruptedException e) {
                rc6.a(e);
                return;
            }
        }
    }

    public void stopService() {
        stopService(30000L);
    }
}
