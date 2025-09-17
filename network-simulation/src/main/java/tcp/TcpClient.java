package tcp;

// ======================================================
// TcpClient
// - 실제로는 '클라이언트 컴퓨터'를 단순화한 모형
// - TCP 연결을 시작하는 쪽 (브라우저, 앱 등)
// - 포트 할당, 시퀀스 번호 관리, SYN/ACK 전송 담당
// - 지금은 단순히 System.out 로그만 남기지만,
//   나중에는 IP/Ethernet으로 감싸서 라우터를 거쳐 전달할 예정
// ======================================================
public class TcpClient {

    private int srcPort = 50000; // 에페메럴(random) 포트 (브라우저가 임시로 할당받는 포트)
    private int seq = 1000;      // 초기 시퀀스 번호(ISN)

    // 1. SYN 전송 (연결 시작)
    public TcpHeader sendSyn(int dstPort) {
        TcpHeader syn = new TcpHeader();
        syn.srcPort = srcPort;
        syn.dstPort = dstPort;
        syn.seq = seq;
        syn.syn = true;

        System.out.println("📤 [Client] SYN 전송: " + syn);
        return syn;
    }

    // 3. ACK 전송 (서버의 SYN+ACK에 대한 응답)
    public TcpHeader sendAck(int dstPort, int serverSeq) {
        TcpHeader ack = new TcpHeader();
        ack.srcPort = srcPort;
        ack.dstPort = dstPort;
        ack.seq = seq + 1;        // 내 시퀀스 +1
        ack.ack = serverSeq + 1;  // 서버 ISN +1
        ack.ackFlag = true;

        System.out.println("📤 [Client] ACK 전송: " + ack);
        return ack;
    }
}
