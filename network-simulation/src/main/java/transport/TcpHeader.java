package transport;

import application.HttpMessage;

// ======================================================
// TCP 헤더 (Transport Layer)
// ======================================================
// - TCP 연결을 맺고 데이터를 신뢰성 있게 전송하기 위한 제어 정보
// - 주요 필드
//   srcPort : 출발지 포트 (브라우저에서 임시 할당한 에페메럴 포트)
//   dstPort : 목적지 포트 (HTTP=80, HTTPS=443 등)
//   seq     : 시퀀스 번호 (보내는 바이트 스트림의 시작 위치를 의미)
//   ack     : 상대방에게 받은 데이터까지 잘 받았다는 확인 번호
//   syn     : 새로운 연결을 시작할 때 세팅 (SYN=1)
//   ackFlag : 상대방 패킷을 잘 받았다는 확인 (ACK=1)
//   fin     : 연결을 종료할 때 세팅 (FIN=1)
//   payload : 응용 계층 데이터 (ex. HTTP 요청). Handshake 단계에서는 없음
// ======================================================
public class TcpHeader {

    int srcPort, dstPort;
    public int seq, ack;
    boolean syn, ackFlag, fin;
    HttpMessage http;

    @Override
    public String toString() {
        return "TCP(srcPort=" + srcPort +
                ", dstPort=" + dstPort +
                ", seq=" + seq +
                ", ack=" + ack +
                ", flags=" + (syn ? "SYN " : "") + (ackFlag ? "ACK " : "") + (fin ? "FIN " : "") +
                ", payload=" + (http == null ? "None" : http.startLine) + ")";
    }
}
