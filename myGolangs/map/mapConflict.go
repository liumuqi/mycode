package main

import (
	"context"
	"fmt"
	"github.com/pkg/errors"
	"sync"
	"time"
)

func main() {
	//testCloseChan()
	//confict()
	testContext()
}

func testContext() {
	//包含堆栈
	errors.New("aaa")
	cs := make(chan string, 2)
	fmt.Println("chan length:", cap(cs))
	gen := func(ctx context.Context) <-chan int {
		dst := make(chan int)
		n := 1
		go func() {
			for {
				select {
				case <-ctx.Done():
					return
				case dst <- n:
					n++
				}
			}
		}()
		return dst
	}
	ctx, cancelFunc := context.WithCancel(context.Background())
	defer cancelFunc()
	for n := range gen(ctx) {
		fmt.Println(n)
		if n == 10 {
			break
		}
	}
}

func testCloseChan() {
	c := make(chan string)
	var x = &sync.WaitGroup{}
	x.Add(2)
	go func(ci chan string) {
		//x.Add(1)
		select {
		case a, _ := <-ci:
			fmt.Println("select...." + a)
		default:
			fmt.Println("vvvvvvvvvvvvvvvvvvvvv")
			break
		}
		x.Done()
	}(c)
	go func(ic chan string) {
		ic <- "string..........."
		x.Done()
		fmt.Println("x done")
	}(c)
	time.Sleep(1 * time.Second)
	close(c)
	fmt.Printf("chan close...%v \n", IsClosed(c))
	x.Wait()
	select {}
}
func IsClosed(ch <-chan string) bool {
	select {
	case <-ch:
		return true
	default:
	}
	return false
}

func confict() {
	c := make(chan bool)
	m := make(map[string]string)
	go func() {
		m["1"] = "a" // First conflicting access.
		c <- true
	}()
	m["2"] = "b" // Second conflicting access.
	<-c
	for k, v := range m {
		fmt.Println(k, v)
	}
}
