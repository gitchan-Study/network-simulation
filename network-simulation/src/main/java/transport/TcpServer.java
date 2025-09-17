package transport;

// ======================================================
// TcpServer
// - 실제로는 '서버 컴퓨터'를 단순화한 모형
// - TCP 연결 요청을 받아들이는 쪽 (웹 서버 등)
// - 고정된 서비스 포트 사용 (예: HTTP = 80)
// - 자신의 시퀀스 번호(ISN) 생성 후 SYN+ACK 응답
// - 나중에는 라우터를 거쳐 들어온 패킷을 receive()로 처리하게 확장 가능
// ======================================================
public class TcpServer {

    private int srcPort = 80;  // HTTP 서버 포트
    private int seq = 3000;    // 초기 시퀀스 번호(ISN)

    // 2. SYN+ACK 응답
    public TcpHeader sendSynAck(TcpHeader synPacket) {
        TcpHeader synAck = new TcpHeader();
        synAck.srcPort = srcPort;
        synAck.dstPort = synPacket.srcPort;
        synAck.seq = seq;
        synAck.ack = synPacket.seq + 1;
        synAck.syn = true;
        synAck.ackFlag = true;

        System.out.println("📤 [Server] SYN+ACK 전송: " + synAck);
        return synAck;
    }
}
