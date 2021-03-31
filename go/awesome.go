package main

import "C"

import (
	"fmt"
	"math"
	s "sort"
	"sync"
)

var count int
var mtx sync.Mutex

//export add
func add(a, b int) int {
	return a + b
}

//export cosine
func cosine(x float64) float64 {
	return math.Cos(x)
}

//export sort
func sort(arrays []int) {
	s.Ints(arrays)
}

//export print
func print(msg string) int {
	mtx.Lock()
	defer mtx.Unlock()
	fmt.Println(msg)
	count++
	return count
}

//export echoString
func echoString(msg string) *C.char {
	return C.CString(msg)
}

//export echoWString
func echoWString(msg string) *C.char {
	return C.CString(msg)
}

//export echoGoString
func echoGoString(msg string) *C.char {
	return C.CString(msg)
}

func main() {}
