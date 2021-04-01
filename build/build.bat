@echo off
cd /d "%~dp0..\go"
go build -buildmode=c-shared -o ..\src\main\resources\win32-x86-64\awesome.dll awesome.go
