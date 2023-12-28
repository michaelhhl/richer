package com.example.excel.demo.pc;

import java.util.concurrent.BlockingQueue;

public class TaskWorker extends Thread {

	BlockingQueue<Task> taskQueue;

	public TaskWorker(BlockingQueue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while (!taskQueue.isEmpty()) {
			try {
				Task task = this.taskQueue.take();
				task.execute();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
