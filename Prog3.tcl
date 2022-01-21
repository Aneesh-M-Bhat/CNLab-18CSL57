set val(stop) 100.0;

LanRouter set debug_ 0
set ns [new Simulator]

set tracefile [open prog3_009.tr w]
$ns trace-all $tracefile

set namfile [open prog3_009.nam w]
$ns namtrace-all $namfile

set WinFile0 [open WinFileReno w]
set WinFile1 [open WinFileNewReno w]

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$ns color 1 red

$ns duplex-link $n0 $n1 100.0Mb 10ms DropTail
$ns queue-limit $n0 $n1 50
$ns duplex-link-op $n0 $n1 color "blue"
$ns duplex-link $n1 $n3 100.0Mb 10ms DropTail
$ns queue-limit $n1 $n3 50
$ns duplex-link-op $n1 $n3 color "green"
$ns duplex-link $n1 $n2 100.0Mb 15ms DropTail
$ns queue-limit $n1 $n2 20

$ns duplex-link-op $n0 $n1 orient right-down
$ns duplex-link-op $n1 $n3 orient right
$ns duplex-link-op $n1 $n2 orient left-down

set lan [$ns newLan "$n3 $n4 $n5" 1Mb 40ms LL Queue / DropTail Mac / 802_3 Channel]

proc PlotWindow {tcpSource file} {
	global ns
	set time 0.1
	set now [$ns now]
	set cwnd [$tcpSource set cwnd_]
	puts $file "$now $cwnd"
	$ns at [expr $now+$time] "PlotWindow $tcpSource $file"
}

set tcp0 [new Agent/TCP/Newreno]
$tcp0 set window_ 8000
$tcp0 set fid_ 1
$ns attach-agent $n0 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n4 $sink0
$ns connect $tcp0 $sink0
$tcp0 set packetSize_ 1500

set tcp1 [new Agent/TCP/Newreno]
$tcp1 set window_ 8000
$ns attach-agent $n2 $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n5 $sink1
$ns connect $tcp1 $sink1
$tcp1 set packetSize_ 1500

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ns at 1.0 "$ftp0 start"
$ns at 0.1 "PlotWindow $tcp0 $WinFile0"
$ns at 100.0 "$ftp0 stop"

set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
$ns at 1.0 "$ftp1 start"
$ns at 0.1 "PlotWindow $tcp1 $WinFile1"
$ns at 100.0 "$ftp1 stop"

proc finish {} {
	global ns tracefile namfile
	$ns flush-trace
	close $tracefile
	close $namfile
	exec nam prog3_009.nam &
	exec xgraph WinFileReno WinFileNewReno & 
	exit 0
}
$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts \"done\" ; $ns halt"
$ns run
