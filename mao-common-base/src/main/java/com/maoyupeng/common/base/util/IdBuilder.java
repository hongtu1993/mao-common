package com.maoyupeng.common.base.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <b>标题: id生成器  </b><br />
 * <pre>
 * </pre>
 *
 * @author 毛宇鹏
 * @date 创建于 15:28 2019-07-08
 */
@Slf4j
@SuppressWarnings("unused")
public class IdBuilder {
	/**
	 * 这个就是代表了机器id
	 */
	private final long workerId;

	/**
	 * 这个就是代表了机房id
	 */
	private final long dataCenterId;

	/**
	 * 这个就是代表了一毫秒内生成的多个id的最新序号
	 */
	private long sequence;

	private static final long WORKER_ID_BITS = 5L;
	private static final long DATACENTER_ID_BITS = 5L;

    /**
     * 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     */
	private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
     */
	private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);
	private static final long SEQUENCE_BITS = 12L;

	private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
	private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
	private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
	private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

	private long lastTimestamp = -1L;

	public IdBuilder(long workerId, long dataCenterId, long sequence) {
		// sanity check for workerId
		// 这儿不就检查了一下，要求就是你传递进来的机房id和机器id不能超过32，不能小于0
		if (workerId > MAX_WORKER_ID || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
		}

		if (dataCenterId > MAX_DATACENTER_ID || dataCenterId < 0) {

			throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
		}

		this.workerId = workerId;
		this.dataCenterId = dataCenterId;
		this.sequence = sequence;
	}


	public long getWorkerId() {
		return workerId;
	}

	public long getDatacenterId() {
		return dataCenterId;
	}

	public long getTimestamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
	 *
	 * @return long
	 */
	@SneakyThrows
	public synchronized long nextId() {

		// 这儿就是获取当前时间戳，单位是毫秒
		long timestamp = timeGen();

		if (timestamp < lastTimestamp) {
			log.error("clock is moving backwards. Rejecting requests until {}.", lastTimestamp);
			String errorMessage = String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp);
			throw new Exception(errorMessage);
		}


		// 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
		// 这个时候就得把seqence序号给递增1，最多就是4096
		if (lastTimestamp == timestamp) {

			// 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
			//这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
			sequence = (sequence + 1) & SEQUENCE_MASK;

			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}

		} else {
			sequence = 0;
		}

		// 这儿记录一下最近一次生成id的时间戳，单位是毫秒
		lastTimestamp = timestamp;

		// 这儿就是最核心的二进制位运算操作，生成一个64bit的id
		// 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
		// 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
		long twepoch = 1288834974657L;
		return ((timestamp - twepoch) << TIMESTAMP_LEFT_SHIFT) |
				(dataCenterId << DATACENTER_ID_SHIFT) |
				(workerId << WORKER_ID_SHIFT) | sequence;
	}

	private long tilNextMillis(long lastTimestamp) {

		long timestamp = timeGen();

		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
}
