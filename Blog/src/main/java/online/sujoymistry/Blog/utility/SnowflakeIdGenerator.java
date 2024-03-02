package online.sujoymistry.Blog.utility;


import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator {
    private static final long EPOCH = 1577836800000L;

    private static final long SEQUENCE_BITS = 12;
    private static final long NODE_ID_BITS = 10;
    private static final long MAX_SEQUENCE = (1 << SEQUENCE_BITS) - 1;
    private static final long MAX_NODE_ID = (1 << NODE_ID_BITS) - 1;
    private static final long NODE_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;

    private final long nodeId=1;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

//    public SnowflakeIdGenerator(long nodeId) {
//        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
//            throw new IllegalArgumentException("Node ID must be between 0 and " + MAX_NODE_ID);
//        }
//        this.nodeId = nodeId;
//    }

    public synchronized long generateId() {
        long timestamp = System.currentTimeMillis() - EPOCH;

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // Sequence overflow, wait for next millisecond
                timestamp = waitNextMillis();
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return (timestamp << TIMESTAMP_SHIFT) |
                (nodeId << NODE_ID_SHIFT) |
                sequence;
    }

    private long waitNextMillis() {
        long timestamp = System.currentTimeMillis() - EPOCH;
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis() - EPOCH;
        }
        return timestamp;
    }
}
