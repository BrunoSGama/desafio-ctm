package br.com.home.ctm.enums;

import java.io.PrintStream;

public enum Level {

	DEBUG(System.out), INFO(System.out), WARN(System.out), ERROR(System.err), FATAL(System.err);

	private PrintStream out;

	private Level(PrintStream out) {
		this.out = out;
	}

	public PrintStream getStream() {
		return this.out;
	}

}
