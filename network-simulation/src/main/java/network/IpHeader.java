package network;

import transport.TcpHeader;

// ======================================================
// IP 헤더 (Network Layer)
// ======================================================
// - 패킷이 출발지에서 목적지까지 이동할 수 있도록 논리적 주소(IP)를 붙임
// - 주요 필드
//   srcIP   : 출발지 IP 주소 (내 컴퓨터의 공인/사설 IP)
//   dstIP   : 목적지 IP 주소 (웹 서버 IP)
//   ttl     : Time To Live. 라우터를 지날 때마다 1씩 줄어듦.
//             0이 되면 폐기되어 무한 루프 방지
//   tcp     : 상위 계층 데이터 (여기서는 TCP 세그먼트)
// ======================================================
public class IpHeader {

    String srcIP, dstIP;
    int ttl = 64; // 기본 TTL 값
    TcpHeader tcp;

    @Override
    public String toString() {
        return "IP(" + srcIP + " → " + dstIP +
                ", ttl=" + ttl + ") | " + tcp;
    }
}
