package eu.stratosphere.streaming.partitioner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import eu.stratosphere.streaming.api.streamrecord.StreamRecord;
import eu.stratosphere.types.StringValue;

public class ShufflePartitionerTest {

	private ShufflePartitioner shufflePartitioner;
	private StreamRecord streamRecord = new StreamRecord(new StringValue());

	@Before
	public void setPartitioner() {
		shufflePartitioner = new ShufflePartitioner();
	}

	@Test
	public void testSelectChannelsLength() {
		assertEquals(1,
				shufflePartitioner.selectChannels(streamRecord, 1).length);
		assertEquals(1,
				shufflePartitioner.selectChannels(streamRecord, 2).length);
		assertEquals(1,
				shufflePartitioner.selectChannels(streamRecord, 1024).length);
	}

	@Test
	public void testSelectChannelsInterval() {
		assertEquals(0, shufflePartitioner.selectChannels(streamRecord, 1)[0]);

		assertTrue(0 <= shufflePartitioner.selectChannels(streamRecord, 2)[0]);
		assertTrue(2 > shufflePartitioner.selectChannels(streamRecord, 2)[0]);

		assertTrue(0 <= shufflePartitioner.selectChannels(streamRecord, 1024)[0]);
		assertTrue(1024 > shufflePartitioner.selectChannels(streamRecord, 1024)[0]);
	}
}