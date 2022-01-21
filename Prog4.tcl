
set val(chan)   Channel/WirelessChannel;    
set val(prop)   Propagation/TwoRayGround;   
set val(netif)  Phy/WirelessPhy;            
set val(mac)    Mac/802_11;                 
set val(ifq)    Queue/DropTail/PriQueue;    
set val(ll)     LL;                         
set val(ant)    Antenna/OmniAntenna;        
set val(ifqlen) 50;                        
set val(nn)     6;                   
set val(rp)     DSDV;                  
set val(x)      810;                   
set val(y)      600;                   
set val(stop)   10.0;

set ns [new Simulator]

set topo       [new Topography]
$topo load_flatgrid $val(x) $val(y)
create-god $val(nn)

set tracefile [open prog4_009.tr w]
$ns trace-all $tracefile

set namfile [open prog4_009.nam w]
$ns namtrace-all $namfile
$ns namtrace-all-wireless $namfile $val(x) $val(y)
set chan [new $val(chan)];

$ns node-config -adhocRouting  $val(rp) \
                -llType        $val(ll) \
                -macType       $val(mac) \
                -ifqType       $val(ifq) \
                -ifqLen        $val(ifqlen) \
                -antType       $val(ant) \
                -propType      $val(prop) \
                -phyType       $val(netif) \
                -channel       $chan \
                -topoInstance  $topo \
                -agentTrace    ON \
                -routerTrace   ON \
                -macTrace      ON \
                -movementTrace ON

set n0 [$ns node]
$n0 set X_ 270
$n0 set Y_ 408
$n0 set Z_ 0.0
$ns initial_node_pos $n0 20
set n1 [$ns node]
$n1 set X_ 503
$n1 set Y_ 404
$n1 set Z_ 0.0
$ns initial_node_pos $n1 20
set n2 [$ns node]
$n2 set X_ 710
$n2 set Y_ 416
$n2 set Z_ 0.0
$ns initial_node_pos $n2 20
set n3 [$ns node]
$n3 set X_ 621
$n3 set Y_ 191
$n3 set Z_ 0.0
$ns initial_node_pos $n3 20
set n4 [$ns node]
$n4 set X_ 402
$n4 set Y_ 176
$n4 set Z_ 0.0
$ns initial_node_pos $n4 20
set n5 [$ns node]
$n5 set X_ 249
$n5 set Y_ 174
$n5 set Z_ 0.0
$ns initial_node_pos $n5 20
set n6 [$ns node]

$ns at 1.5 "$n1 setdest 390.0 460.0 40.0"
$ns at 1.5 "$n4 setdest 472.0 510.0 50.0"
$ns at 1.5 "$n5 setdest 523.0 570.0 40.0"

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set sink1 [new Agent/TCPSink]
$ns attach-agent $n5 $sink1
$ns connect $tcp0 $sink1
$tcp0 set packetSize_ 1500

set udp2 [new Agent/UDP]
$ns attach-agent $n2 $udp2
set null3 [new Agent/Null]
$ns attach-agent $n3 $null3
$ns connect $udp2 $null3
$udp2 set packetSize_ 1500

set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
$ns at 1.0 "$ftp0 start"
$ns at 2.0 "$ftp0 stop"

set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp2
$cbr1 set packetSize_ 1000
$cbr1 set rate_ 1.0Mb
$cbr1 set random_ null
$ns at 1.0 "$cbr1 start"
$ns at 2.0 "$cbr1 stop"

proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    exec nam prog4_009.nam &
    exit 0
}
for {set i 0} {$i < $val(nn) } { incr i } {
    $ns at $val(stop) "\$n$i reset"
}
$ns at $val(stop) "$ns nam-end-wireless $val(stop)"
$ns at $val(stop) "finish"
$ns at $val(stop) "puts \"done\" ; $ns halt"
$ns run
