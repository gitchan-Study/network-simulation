package link;

import network.IpHeader;

// ======================================================
// Ethernet 프레임 (Link Layer)
// ======================================================
// - 실제 물리 네트워크에서 사용되는 헤더
// - 주요 필드
//   srcMAC   : 출발지 MAC 주소 (내 NIC의 하드웨어 주소)
//   dstMAC   : 목적지 MAC 주소 (보통은 게이트웨이/라우터의 MAC)
//   etherType: 상위 계층 프로토콜 종류 (IPv4=0x0800, IPv6=0x86DD, ARP=0x0806)
//   ip       : 상위 계층 데이터 (여기서는 IP 패킷)
// ======================================================
public class EthernetFrame {

    String srcMAC, dstMAC;
    String etherType = "0x0800"; // IPv4
    IpHeader ip;

    @Override
    public String toString() {
        return "Ethernet(" + srcMAC + " → " + dstMAC +
                ", type=" + etherType + ") | " + ip;
    }
}
