call runcrud.bat
if %ERRORLEVEL% == 0 goto browserlaunch
echo.
echo RUNCRUD launch problem
goto fail

:browserlaunch
start chrome http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were error

:end
echo.
echo Work is finished