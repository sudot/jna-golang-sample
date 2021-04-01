cd $(dirname `$(readlink -f $0)`)/../go
go build -buildmode=c-shared -o ../src/main/resources/darwin/libawesome.dylib awesome.go
