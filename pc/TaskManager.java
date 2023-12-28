package com.example.excel.demo.pc;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskManager {

	private static TaskManager instance;

	private BlockingQueue<Task> taskQueue;
	private List<Task> taskList;
	private static final int MAX_THREAD_NUMBERS = Runtime.getRuntime().availableProcessors();

	private TaskManager() {
		taskQueue = new LinkedBlockingQueue<>();
	}

	public static synchronized TaskManager builder() {
		if (instance == null) {
			synchronized (TaskManager.class) {
				if (instance == null) {
					instance = new TaskManager();
				}
			}
		}
		return instance;
	}

	public TaskManager acceptTask(List<Task> taskList) {
		this.taskList = taskList;
		prepareTask();
		return this;
	}

	public void start() {
		for (int i = 1; i <= MAX_THREAD_NUMBERS; i++) {
			new TaskWorker(this.taskQueue).start();
		}
	}

	private void prepareTask() {
		try {
			for (Task task : taskList) {
				this.taskQueue.put(task);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.taskQueue.size());
	}
}
