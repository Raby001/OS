#include <stdio.h>
#include <windows.h>

int main(void)
{
    STARTUPINFO si;
    PROCESS_INFORMATION pi;

    ZeroMemory(&si, sizeof(si));
    si.cb = sizeof(si);
    si.dwFlags = STARTF_USESHOWWINDOW;
    si.wShowWindow = SW_SHOW;
    ZeroMemory(&pi, sizeof(pi));

    if (!CreateProcess(NULL, 
        "C:\\Program Files\\WindowsApps\\Microsoft.Paint_11.2509.441.0_x64__8wekyb3d8bbwe\\PaintApp\\mspaint.exe",
        NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi))
    {
        fprintf(stderr, "Create Process Failed");
        return -1;
    }
    
    WaitForSingleObject(pi.hProcess, INFINITE);
    printf("Child Complete\n");
    
    CloseHandle(pi.hProcess);
    CloseHandle(pi.hThread);
    
    return 0;
}