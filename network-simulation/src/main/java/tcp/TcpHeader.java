package tcp;

// -------------------------
// TCP 헤더 (간단화된 모델)
// -------------------------
// - srcPort: 출발지 포트
//   (클라이언트가 사용하는 임시 포트 번호, 보통 49152~65535 사이 값)
//
// - dstPort: 목적지 포트
//   (서버에서 사용하는 서비스 포트 번호, 예: 80=HTTP, 443=HTTPS)
//
// - seq: 시퀀스 번호
//   (송신 측이 보내는 데이터 스트림의 시작 위치를 바이트 단위로 표현,
//    패킷 손실/재전송/순서 보장을 위해 사용)
//
// - ack: ACK 번호
//   (수신 측이 “어디까지 잘 받았다”라고 알려주는 번호,
//    보통 상대방의 seq + 1)
//
// - syn: 연결 시작 플래그
//   (SYN=1이면 “새 연결 시작하자”라는 의미)
//
// - ackFlag: 수신 확인 플래그
//   (ACK=1이면 “네 시퀀스 번호 잘 받았다”라는 의미)
//
// - fin: 연결 종료 플래그
//   (FIN=1이면 “이제 데이터 다 보냈으니 연결 끊자”라는 의미)
//
// - payload: 응용 계층 데이터 (예: HTTP 요청/응답 본문)
//   (3-way handshake 단계에서는 보통 없음, 연결 성립 후에 채워짐)
// -------------------------
public class TcpHeader {

    public int srcPort, dstPort;
    public int seq, ack;
    public boolean syn, ackFlag, fin;
    public String payload;

    public TcpHeader() {
    }

    @Override
    public String toString() {
        return "TCP(srcPort=" + srcPort + ", dstPort=" + dstPort +
                ", seq=" + seq + ", ack=" + ack +
                ", flags=" + (syn ? "SYN " : "") + (ackFlag ? "ACK " : "") + (fin ? "FIN " : "") +
                ", payload=" + (payload != null ? payload : "None") + ")";
    }
}
