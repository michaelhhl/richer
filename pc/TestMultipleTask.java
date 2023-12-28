package com.example.excel.demo.pc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TestMultipleTask {

	public static void main(String[] args) throws ParseException {
		List<Task> taskList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Task task = new ReportTask(i);
			taskList.add(task);
		}
		TaskManager.builder().acceptTask(taskList).start();

	}

}
