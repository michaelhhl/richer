package com.example.excel.demo.pc;

public class ReportTask implements Task {

	private Integer taskNumber;

	public ReportTask(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	@Override
	public void execute() {
		System.out.println("task number " + this.taskNumber + ":" + Thread.currentThread().getName());

	}

}
